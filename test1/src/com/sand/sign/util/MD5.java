package com.sand.sign.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SignatureException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

/** 
* ���ܣ�֧����MD5ǩ����������ļ�������Ҫ�޸�
* �汾��3.3
* �޸����ڣ�2012-08-17
* ˵����
* ���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
* �ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ��
* */

public class MD5 {

	/**
     * ǩ���ַ���
     * @param text ��Ҫǩ�����ַ���
     * @param key ��Կ
     * @param input_charset �����ʽ
     * @return ǩ�����
     */
    public static String sign(String text, String key, String input_charset) {
    	text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * ǩ���ַ���
     * @param text ��Ҫǩ�����ַ���
     * @param sign ǩ�����
     * @param key ��Կ
     * @param input_charset �����ʽ
     * @return ǩ�����
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
    	text = text + key;
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
        	throw new RuntimeException("MD5ǩ�������г��ִ���,ָ���ı��뼯����,��Ŀǰָ���ı��뼯��:" + charset);
        }
    }

    
    static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String getMD5(String s) {
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(s.getBytes());
			byte[] md = mdTemp.digest();
			char str[] = new char[md.length * 2];
			for (int i = 0, k = 0; i < md.length; i++) {
				str[k++] = hexDigits[md[i] >>> 4 & 0xf];
				str[k++] = hexDigits[md[i] & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static String md5fdert(int g) {	
		char[] kp = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
	            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
	            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
	            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
	            'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
	            '7', '8', '9'};
	   
	   StringBuffer sb = new StringBuffer();
	   for (int i = 0; i < g; i++) {
		   Random ra = new Random();
		   int index =  ra.nextInt(kp.length);
		   sb.append(kp[index]);
	      }
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(getMD5("aaa哇卡"));
	}
    
}