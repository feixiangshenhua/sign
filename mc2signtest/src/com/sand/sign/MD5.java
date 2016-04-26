/**
 * 
 */
package com.sand.sign;
/**
 * com.sand.util.MD5.java
 */

import java.security.MessageDigest;
import java.util.Random;

public class MD5 {
	
	static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String getMD5(String s) {
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(s.getBytes("utf8"));
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
		String msg = "{\"playload\":{\"txnType\":\"01\",\"productCode\":\"200020\",\"accessMid\":\"888002199990001\",\"txnSubType\":\"12\",\"reserved\":\"\",\"txnAmt\":\"000000000001\",\"signMethod\":\"01\",\"encoding\":\"utf-8\",\"queryId\":\"2016012600000000000001062933\",\"signature\":\"371D00506AD63073172226E73F27E25AE4BF28C8541F503626868528D81010F48A0913BF14A7FE943A929D6533D3FEFEE664F2625E9A4C158BB79FF662F88A9CDDEC02BCF891D2F686ECAFEC79316FD425E3EAB1BA51BAA865EDE76E8DE28A9E59F5062A4FB08E471FDFBB66B14130020333D546CFA78771A6A1D5DB8F8976F4\"},\"playhead\":{\"respCode\":\"000000\",\"tcode\":\"1.0.0-Mc.pay.sandbaoScanPay\",\"respMsg\":\"成功\",\"accessCode\":\"0003\"}}";
		System.out.println(MD5.getMD5(msg));
	}
}
