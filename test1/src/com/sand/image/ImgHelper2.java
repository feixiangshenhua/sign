package com.sand.image;

import java.io.FileInputStream;
import java.io.IOException;

import com.sand.crypto.util.ByteUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImgHelper2 {

	/**
     * TODO:��byte������Base64��ʽ����Ϊ�ַ���
     * @param bytes �������byte����
     * @return �������ַ���
     * */
    public static String encode(byte[] bytes){
    	//System.out.println("aaa:" + ByteUtil.bytes2Base64str(bytes));
        return new BASE64Encoder().encode(bytes);
    }
    
    public static void en(byte[] bsb) {
    	System.out.println(ByteUtil.bytes2Base64str(bsb).length());
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
    
    public static void encodeImage2(String imgUrl) throws IOException{
        FileInputStream fis = new FileInputStream(imgUrl);
        byte[] rs = new byte[fis.available()];
        fis.read(rs);
        fis.close();
        en(rs);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str;
        try {
            str = encodeImage("D:\\a.png");
            System.out.println(str.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
