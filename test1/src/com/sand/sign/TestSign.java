package com.sand.sign;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import com.sand.sandutil.security.RSAHelpImpl;
import com.sand.sign.util.MD5;

public class TestSign {
	
	private static final String EDAlgorithm = "RSA/ECB/PKCS1Padding"; //RSA/ECB/PKCS1Padding,RSA/ECB/NoPadding
	/**
	 * 
	 * @param exponent 指数
	 * @param modulus 模数
	 * @param type
	 * @return 密钥
	 * @throws Exception
	 */
	private static Key toKey(BigInteger exponent, BigInteger modulus,
			String type) throws Exception {
		Key key = null;
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		if (type.equalsIgnoreCase("private")) {
			RSAPrivateKeySpec prispec = new RSAPrivateKeySpec(modulus, exponent);
			key = keyFactory.generatePrivate(prispec);
		} else if (type.equalsIgnoreCase("public")) {
			RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(modulus, exponent);
			key = keyFactory.generatePublic(pubSpec);
		}
		return key;
	}
	
	/**
	 * 用RSA的公钥解密
	 * @param publicKey 公钥
	 * @param cipherStr 加密结果，十六进制编码
	 * @return
	 */
	public static byte[] decrypt(PublicKey publicKey, String cipherStr) {
		if (publicKey != null) {
			try {
				byte[] cbyte = hex2Bytes(cipherStr);
				Cipher cipher = Cipher.getInstance(EDAlgorithm);
				cipher.init(Cipher.DECRYPT_MODE, publicKey);
				byte[] bye = cipher.doFinal(cbyte);
				return bye;
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 十六进制转二进制数组
	 * @param s
	 * @return
	 */
	public static final byte[] hex2Bytes(String s) {
		if(s == null){
			return null;
		}
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),16);
		}
		return bytes;
	}
	
	public static void main(String[] args) throws Exception {
		String sign = "A183537041DC41BF8BECD2B09ADA0A24752B9176AA47DF5D106AE423A320EB1D189CE30C9314BB477E020DE2475F1D6D4DF7CB02DF15EDED211EAAD01178FA2BA138BDC321BC6E4C60AD58F8EC881FAF3EE049EC8034FDBEA56673B092F6088DEE0687ADBA9F1C502462402F487F85922A5600B48213C90958B0ADD9E863EA0D";
		// 指数
		String eStr = "65537";
		// 模数
		String nStr = "D3FCD87975B5A0CDD128D98677AF51FE978D8BFC1256D1B437CA4AC8194B72BD5BC9A188944E5E40E2664E1607929D5401EB4B7ABACC7CDA22612AA94D74E43ED715AE0691B9E90195580D0F2C628DA8DB0BD2F7C8848C353507CDE4FEDE4108BDA06B2C9A6E334FC1FC9C42C973AB80CBD32BD62999D339739F2AF029B2467B";
		RSAHelpImpl rsa = new RSAHelpImpl();
		String str = "{\"respCode\":\"100000\",\"respResult\":\"成功\",\"mid\":\"888002148160001\",\"transNo\":\"15071501000000000613\"," +
				"\"orderCode\":\"150715MO0000493\",\"orderAmt\":\"0.01\",\"currency\":\"156\"}";	
		String temp = "{\"respCode\":\"100000\",\"respResult\":\"成功\",\"mid\":\"888002148160001\",\"transNo\":\"15071701000000003701\",\"orderCode\":\"150717MO0002081\",\"orderAmt\":\"0.2\",\"currency\":\"156\",\"respTime\":\"20150717160727\"}";
		System.out.println(str.equals(temp));
		System.out.println(MD5.getMD5(temp));
		
		BigInteger E = new BigInteger(eStr, 10);
		BigInteger N = new BigInteger(nStr, 16);
		
		
		PublicKey publicKey = (PublicKey)toKey(E, N, "public");
		
		String msg = new String(decrypt(publicKey, "AEDCFA51A97C44F87D5D14C7FFA7A3C32C79690C030F4CA6F870704DD1075436A5D6B692642AF455718785DAD712E59EF0DF491AC3539E212C7FF5C7093E65469C8E7EF4AAD93954DC9911410348D2651523777CD0574822AC00D8D8CDAFB98A260E7BDB522BA547EF71FEA915EFDBD38DF05490DD2DDE5C31F90AC29F32379B"));
		
		System.out.println(msg);

	}
	
	

	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "ISO-8859-1");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

}
