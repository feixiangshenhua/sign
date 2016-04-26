package com.rsa.common;

import com.sand.crypto.base64.Base64Encoder;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;


public class Rsa {
	
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
		
//		String eStr = "65537";
//		String nStr = "D3FCD87975B5A0CDD128D98677AF51FE978D8BFC1256D1B437CA4AC8194B72BD5BC9A188944E5E40E2664E1607929D5401EB4B7ABACC7CDA22612AA94D74E43ED715AE0691B9E90195580D0F2C628DA8DB0BD2F7C8848C353507CDE4FEDE4108BDA06B2C9A6E334FC1FC9C42C973AB80CBD32BD62999D339739F2AF029B2467B";
		String eStr = "335235993427B110ACAE6F8F154A38FA31E009C93211F7B25FD4430A4415B0057B0D5A9CD73ECEFB9AC1A20D167B6FEE41850D34B194DB5B08CE997C601AD9DAE75B2995FDF257DECDDEADEABCF19502FC4E76331D4FB319AC88D7878F4B35995FA62EFBEC0DA496681891995B258ECAB220DA4B70079B4F5477B6BCBAD941D5";
		String nStr = "F2959C3F794E119F060EE2DFAF85AA00E6E97C861E3A6A3F2C9A0A6C5DB89EFE991DEC6E9864B49EBF1F6BF88CECD2E32AFD4325E23A6652FAA83F465B9A567CC0F19C093D3F70C965E9D59956C4AC6631A936E6FAC89F40CED0361271453CA039BF97E56E79AA8CC6D7D1A763B894D8EEB85E55BAD3BC8564918A4B1DD66891";
		
		
		

		BigInteger N=new BigInteger(nStr,16);
		BigInteger E=new BigInteger(eStr,16);
		
//		PublicKey publicKey = (PublicKey)Rsa.toKey(E,N,"public");
		PrivateKey privateKey = (PrivateKey)Rsa.toKey(E,N,"private");
		System.out.println(privateKey);
		
		byte[] b = privateKey.getEncoded();
		String str = Base64Encoder.encode(b);
		System.out.println(str);

	}
}
