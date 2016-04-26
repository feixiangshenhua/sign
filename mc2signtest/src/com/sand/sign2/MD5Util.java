package com.sand.sign2;

import java.security.MessageDigest;

public class MD5Util {
	public static void main(String[] args) {
		String cc = "{\"playload\":{\"txnType\":\"01\",\"productCode\":\"200020\",\"accessMid\":\"888002199990001\",\"txnSubType\":\"12\",\"reserved\":\"\",\"txnAmt\":\"000000000001\",\"signMethod\":\"01\",\"encoding\":\"utf-8\",\"queryId\":\"2016012600000000000001062933\",\"signature\":\"371D00506AD63073172226E73F27E25AE4BF28C8541F503626868528D81010F48A0913BF14A7FE943A929D6533D3FEFEE664F2625E9A4C158BB79FF662F88A9CDDEC02BCF891D2F686ECAFEC79316FD425E3EAB1BA51BAA865EDE76E8DE28A9E59F5062A4FB08E471FDFBB66B14130020333D546CFA78771A6A1D5DB8F8976F4\"},\"playhead\":{\"respCode\":\"000000\",\"tcode\":\"1.0.0-Mc.pay.sandbaoScanPay\",\"respMsg\":\"成功\",\"accessCode\":\"0003\"}}";
		String ccdd = MD5(cc,"UTF-8");
		System.out.println(ccdd);
	}
	
	
	public final static String MD5(String s, String entype) {
		String result = "";
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes(entype);
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			result = new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} 
}
