package com.sand.image;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImgHelper {

	/**
     * TODO:��byte������Base64��ʽ����Ϊ�ַ���
     * @param bytes �������byte����
     * @return �������ַ���
     * */
    public static String encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }
    
    /**
     * TODO:����Base64��ʽ������ַ�������Ϊbyte����
     * @param encodeStr ��������ַ���
     * @return ������byte����
     * @throws IOException 
     * */
    public static byte[] decode(String encodeStr) throws IOException{
        byte[] bt = null;  
        BASE64Decoder decoder = new BASE64Decoder();  
        bt = decoder.decodeBuffer(encodeStr);
        return bt;
    }
    
    /**
     * TODO:������byte�������������󣬷������Ӻ��Byte����
     * @param front ƴ�Ӻ���ǰ�������
     * @param after ƴ�Ӻ��ں��������
     * @return ƴ�Ӻ������
     * */
    public static byte[] connectBytes(byte[] front, byte[] after){
        byte[] result = new byte[front.length + after.length];
        System.arraycopy(front, 0, result, 0, after.length);
        System.arraycopy(after, 0, result, front.length, after.length);
        return result;
    }
    
    /**
     * ���ֽ�����ת��Ϊ�ֽ���
     * @param bytes
     * @return
     */
    public static InputStream bytes2InputStream(byte[] bytes) {
        InputStream input = new ByteArrayInputStream(bytes);        
        return input;
    }
    
    /**
     * TODO:��ͼƬ��Base64��ʽ����Ϊ�ַ���
     * @param imgUrl ͼƬ�ľ���·�������磺D:\\jsontest\\abc.jpg��
     * @return �������ַ���
     * @throws IOException 
     * */
    public static String encodeImage(String imgUrl) throws IOException{
        FileInputStream fis = new FileInputStream(imgUrl);
        byte[] rs = new byte[fis.available()];
        fis.read(rs);
        fis.close();
        return encode(rs);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str;
        try {
            str = encodeImage("D:\\a.png");
            //c9x8O8k48AjGQbPInw5X0YwCVJwA6HgbwjBMGBwcyDPEer/B7QGmUY6rd9EAAAAAElFTkSuQmCC
            System.out.println(str);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
