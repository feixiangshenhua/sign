package com.sand.rsa;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class RSAUtils {
	
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
		
		String eStr = "65537";
		String nStr = "D3FCD87975B5A0CDD128D98677AF51FE978D8BFC1256D1B437CA4AC8194B72BD5BC9A188944E5E40E2664E1607929D5401EB4B7ABACC7CDA22612AA94D74E43ED715AE0691B9E90195580D0F2C628DA8DB0BD2F7C8848C353507CDE4FEDE4108BDA06B2C9A6E334FC1FC9C42C973AB80CBD32BD62999D339739F2AF029B2467B";

		BigInteger N=new BigInteger(nStr,16);
		BigInteger E=new BigInteger(eStr,10);
		
		String randNum = "69F645DA8CFD3D14A2B1E2165DA29E9C5581CD4A53ADAEE4870C8D47C6B35D285DB713FC8C0C5E97C020C4951E76BD640D1D841ECF7BA88188854CA41FE39A48C2A999C86114F45E7BB94D563FF605718A4AAB70EC47ED37B884B7B6E50B3A2F48154A0137BA892565251E8860F02BEDE79B79B822F46F52CE2053F91C85FBC0";
		PublicKey publicKey = (PublicKey)RSAUtils.toKey(E,N,"public");
		
		System.out.println(new String(RSAUtils.decrypt(publicKey, randNum)));

	}
}