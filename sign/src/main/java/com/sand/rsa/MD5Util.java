package com.sand.rsa;

import java.security.MessageDigest;

public class MD5Util {
	public static void main(String[] args) throws Exception {
		/*RSAPublicKey rsap = RSAUtil.getSandPublicKey();
		String aa = "{\"respCode\":\"100000\",\"respResult\":\"成功\",\"mid\":\"888002148160001\",\"transNo\":\"15071401000000000901\",\"orderCode\":\"150714MO0000641\",\"orderAmt\":\"0.01\",\"currency\":\"156\"}";
		String bb = "916C572C7166CAA22FE14D45B739481DC9732E154C370BD844F4229F9825A3460F0428BDC81564970543E08DCF61112782DFA5AF35BF6B62FAA16A1D94AB0EC07B6CBDB9D94D5B1005631E0F4DD4DD54379672C120429D983764AC4305A8DE7CA113C8AD3A5FD10FFF774A09EC17F020343CC640041EDE806AE5A2990D848FC1";
		String md5T = MD5Util.MD5(aa, "UTF-8");
		System.out.println(md5T);
		boolean falg = RSAUtil.verify(bb.getBytes(), rsap, md5T);
		System.out.println("flag:"+falg);*/
		
		String cc = "{\"respCode\":\"100000\",\"respResult\":\"成功\",\"mid\":\"888002148160001\",\"transNo\":\"15071501000000000613\",\"orderCode\":\"150715MO0000493\",\"orderAmt\":\"0.01\",\"currency\":\"156\"}";
		String ccdd = MD5("qwe123","UTF-8");
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
