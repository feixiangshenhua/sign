package com.rsa.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSASecurityUtil2 {
    /** ָ�������㷨ΪRSA */
    private static final String ALGORITHM = "RSA";
    /** ��Կ���ȣ�������ʼ�� */
    private static final int KEYSIZE = 1024;
    /** ָ����Կ����ļ� */
    private static String PUBLIC_KEY_FILE = "PublicKey";
    /** ָ��˽Կ����ļ� */
    private static String PRIVATE_KEY_FILE = "PrivateKey";

    /**
     * ������Կ��
     * @throws Exception
     */
    private static void generateKeyPair() throws Exception {
        
//        /** RSA�㷨Ҫ����һ�������ε������Դ */
//        SecureRandom secureRandom = new SecureRandom();
        
        /** ΪRSA�㷨����һ��KeyPairGenerator���� */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        
        /** ����������������Դ��ʼ�����KeyPairGenerator���� */
//        keyPairGenerator.initialize(KEYSIZE, secureRandom);
        keyPairGenerator.initialize(KEYSIZE);
        
        /** �����ܳ׶� */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        /** �õ���Կ */
        Key publicKey = keyPair.getPublic();
        
        /** �õ�˽Կ */
        Key privateKey = keyPair.getPrivate();
        
        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            /** �ö����������ɵ���Կд���ļ� */
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (Exception e) {
            throw e;
        }
        finally{
            /** ��ջ��棬�ر��ļ������ */
            oos1.close();
            oos2.close();
        }
    }

    /**
     * ���ܷ���
     * @param source Դ����
     * @return
     * @throws Exception
     */
    public static String encrypt(String source) throws Exception {
        generateKeyPair();
        Key publicKey;
        ObjectInputStream ois = null;
        try {
            /** ���ļ��еĹ�Կ������� */
            ois = new ObjectInputStream(new FileInputStream(
                    PUBLIC_KEY_FILE));
            publicKey = (Key) ois.readObject();
        } catch (Exception e) {
            throw e;
        }
        finally{
            ois.close();
        }
        
        /** �õ�Cipher������ʵ�ֶ�Դ���ݵ�RSA���� */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /** ִ�м��ܲ��� */
        byte[] b1 = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }

    /**
     * �����㷨
     * @param cryptograph    ����
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph) throws Exception {
        Key privateKey;
        ObjectInputStream ois = null;
        try {
            /** ���ļ��е�˽Կ������� */
            ois = new ObjectInputStream(new FileInputStream(
                    PRIVATE_KEY_FILE));
            privateKey = (Key) ois.readObject();
        } catch (Exception e) {
            throw e;
        }
        finally{
            ois.close();
        }
        
        /** �õ�Cipher��������ù�Կ���ܵ����ݽ���RSA���� */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        
        /** ִ�н��ܲ��� */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String[] args) throws Exception {
        String source = "��ϲ����!";// Ҫ���ܵ��ַ���
        System.out.println("׼���ù�Կ���ܵ��ַ���Ϊ��" + source);
        
        String cryptograph = encrypt(source);// ���ɵ�����
        System.out.print("�ù�Կ���ܺ�Ľ��Ϊ:" + cryptograph);
        System.out.println();

        String target = decrypt(cryptograph);// ��������
        System.out.println("��˽Կ���ܺ���ַ���Ϊ��" + target);
        System.out.println();
    }
}
