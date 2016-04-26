/**
 * com.sand.util.MD5.java
 */
package com.sand.rsa;

import java.security.MessageDigest;
import java.util.Random;

public class MD5 {
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
		System.out.println(getMD5("qwe123"));
	}
}
