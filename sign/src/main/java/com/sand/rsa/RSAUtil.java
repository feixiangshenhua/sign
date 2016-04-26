package com.sand.rsa;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.sand.crypto.shal.Sha1Util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAUtil {
	private static RSAPublicKey sandPublicKey;
	
	public static String sandPublic_exponent = "65537";
	
	public static String a= "D3FCD87975B5A0CDD128D98677A" +
			"F51FE978D8BFC1256D1B437CA4AC8194B72BD5BC9A188944E5E40E" +
			"2664E1607929D5401EB4B7ABACC7CDA22612AA94D74E43ED715AE0" +
			"691B9E90195580D0F2C628DA8DB0BD2F7C8848C353507CDE4FEDE4" +
			"108BDA06B2C9A6E334FC1FC9C42C973AB80CBD32BD62999D339739" +
			"F2AF029B2467B";
	
	/** 生产 */
	public static String sandModulus = "C347CF89487D1E7E2613831FD703892EBE857BA26D5C47688D666A245116DDC1CFF36BABF2F7A18E90811126414D5ABCD7C3CE32DFC5017519E510D40FFBD42491C31C72742AC50416DB40F8D45BF6DD50163343826DFDCEC84BCA3BF204A1C37447F760408320CBF0816CF40E1F3A0812A46FFA19D30DD016986835AFFD9B83";

	public static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIICTqTVD/zvKYVSd/PzSg4R2jm/"
			+"O23HoJM9zcuBrwhFvxt0uDdmXsubEJJBiS7v2wyzvfR9Z82so/hKORLAy59wL71cQ6VY9VNXIzBD"
			+"TQbRGK2eY7cCMtW4h76xOdxfmB0hKjeNJjVfrMofRex6M4FDCxxf93H6nZd8U9+cRORhAgMBAAEC"
			+"gYAqaZ5GX8urdEpTWCgRyZirJl/O3vgARFO6NTth3JjBVh+WUWmXiSn7ukaZsA+U5GLNrgWWMSMg"
			+"EeYcJfqgh4zlq6C+9iPNdUt+DmGDyD+ghyuxgiTFYTJORGFsosQn1oaBTug2HKiFLWpDgyH86gji"
			+"RtUh3BErYhgQf/zAlN/yUQJBALpKKh5NvAVlqO88uKApeifYx8t5HsHrBhMGBhB7j+eZgSCNSYlQ"
			+"VVGEj5XhNBdJ0sq5wWgQNzXLC6TiWyIMCLMCQQCyqKtYv7Lnf4hguJsPAYjYCmQBfbwNnHLLG7Ez"
			+"rSs+x5RrBJxX3CznaqXy78JxsN/B8JlEBTfzq6wTDti0peCbAkBlk8EJiwtlyfvmjy6Arn0cMxkm"
			+"OqHpzRQf1V4DKMzb1RjSvheF1xbefzCn4gc8V5Wn0sfBzW29WDy+KGrW0sb/AkBIfegIur/87uKH"
			+"XGCOVjEQeCbb9Ai5/SEXCMV+EmXZs2GlitllTRFrVPrA+EgNmZcxOk72KqfpV18a3ItRwXzBAkAO"
			+"/pHE6VZHDOLEeffYJcOSYwPjJNZKKq5kIvmiKYE0k7lPFDeNwGAD3gz9V5lNKUu2y3qtHHJXxmni"
			+"TST2PXbn";
	
	public static String pk = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKV6iCUnfALhZ9m1xelY5HHZN32N"
								+"MQs4LbjLApChD/Wrv3cCjoodHtX42rMosxzoTIms30Q2RC4FqLWIN26HOdfjPkzDRfAOSDPmhdnv"
								+"6ZXExDtonY/PXrtnErLEeetBwS4AmWn2hl8iJKXDfgMKOnCQYU/rjBqcqBZAwyn2IneXAgMBAAEC"
								+"gYB5OTxHS5eTXKEBYiIsAYzIwkHeXfKRr4P/HekxHmxcoobOnjbhgte2FGnSWXxtLtfy+4tLox1r"
								+"QYYIBlPlSnbaPIiVDftG0Z4oSqiSvhfrSxP0IWzbRAZtbJiFpS5/Z5B2pcU27OAvowRB3iA9ID+a"
								+"mQxUDJj0FITE1mTLnMmhMQJBANkNxxxUIMW9lk2JTiCYX5mZ5ehZ0kid1kna0LxGMrNlQ7lKlhWJ"
								+"xZ4QlijnAWrMnTDdQPgmpzjVwwbX8ZmLV90CQQDDK66/+OW0t2Vm/KqolJfDqQgk+3wrgpiiSh4r"
								+"oOk6cPR49loJfrl4ij1XdOUPj88KNL22+hPW3XZT0GYoMzADAkALgkqDewZ91a6pj9U/rzSqfVmO"
								+"d2zKAuyA4ARfZ01666yqn4TTwYlj9zcvFgiiNBVFqQWv4sTNI7CCpWKfJJ9VAkEAgANk6ZHtyO0J"
								+"29UactyT9sgmCWvSdA+C4d/PjjpLAAQatkSPpe6y5Hd36KdJnz1qRX12ek/pN2OnOy5T80Yv1QJA"
								+"VCENb2zu90oit8yme0jWLaHzGDJprR5KnC32ZWproTToasPNxox7DtZbj9jls8xkbU5drM6fV4J5"
								+"Nsn7uCJcFA==";
	
	private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG2U45qFpFvKpW+7tZFDzSmSh60XqV6bOSyv3d" +
			"NDVmz6vBXWcxSEnrdheDqpgOZcVV/hhwWBVlWoEpjfx5cGrLB8vMZDcvUUz6EKyqDkA+Kf5QecS/"+
			"nEJ7xABiw8rTyTySrVJ5eIcDh4O6LzeVo0pS8RzDOQwf2w7MsSRVi/QG4QIDAQAB";
	
	private static final String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCleoglJ3wC4WfZtcXpWORx2Td9jTELOC24ywKQ"
					+"oQ/1q793Ao6KHR7V+NqzKLMc6EyJrN9ENkQuBai1iDduhznX4z5Mw0XwDkgz5oXZ7+mVxMQ7aJ2P"
					+"z167ZxKyxHnrQcEuAJlp9oZfIiSlw34DCjpwkGFP64wanKgWQMMp9iJ3lwIDAQAB";
	
	/*public static void main(String[] args) {
		try {
			Map<String, Object> rsaKeys = getKeys();
			String publicK = getPublicKey(rsaKeys);
			String privateK = getPrivateKey(rsaKeys);
			System.out.println(pk.equals(privateKey));
			String publicK = pubKey;
			String privateK = pk;
			
			System.out.println(privateK);
			System.out.println("获取公钥:"+publicK+"["+publicK.length()+"]");
			System.out.println("获取私钥:"+privateK+"["+privateK.length()+"]");
			
			String data = "{\"mid\":\"888002159990131\",\"transNo\":\"\",\"orderCode\":\"151127MO0000098\",\"transTime\":\"20151127092858\",\"attach\":\"\",\"posOrderId\":\"11111111151127092554\"}";
			data = SHA1Util.hex_sha1(data);
			System.out.println("原始数据:"+UTIL.bytesToHexStr(data.getBytes()));
			String data1 = UTIL.bytesToHexStr(encryptByPrivateKey(data.getBytes(), privateK));
			System.out.println("私钥加密:"+data1);
			String data2 = UTIL.bytesToHexStr(decryptByPublicKey(UTIL.HexString2Bytes(data1), publicK));
			System.out.println("公钥解密:"+data2);
			String aaaaa = "{\"mid\":\"888002159990131\",\"transNo\":\"\",\"orderCode\":\"151127MO0000098\",\"transTime\":\"20151127092858\",\"attach\":\"\",\"posOrderId\":\"11111111151127092554\"}";
			System.out.println(data2.equals(UTIL.bytesToHexStr(SHA1Util.hex_sha1(aaaaa).getBytes())));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/* 生成密钥(初始化密钥) */
	public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException{  
        HashMap<String, Object> map = new HashMap<String, Object>();  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        map.put("public", publicKey);  
        map.put("private", privateKey);  
        return map;  
    }
	
	/* 获取公钥 */
	public static String getPublicKey(Map<String, Object> keyMap)throws Exception{
        Key key = (Key) keyMap.get("public");
        return encryptBASE64(key.getEncoded());     
    }
 
	/* 获取私钥 */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception{
        Key key = (Key) keyMap.get("private");  
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("PrivateKey"));
		oos1.writeObject(key);
		oos1.flush();
		oos1.close();
        return encryptBASE64(key.getEncoded());     
    }
    
    /* 私钥加密 */
    public static byte[] encryptByPrivateKey(byte[] data,String key)throws Exception{
        //解密密钥
        byte[] keyBytes = decryptBASE64(key);
        //取私钥
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
         
        //对数据加密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //Cipher cipher = Cipher.getInstance("SHA1WithRSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
         
        return cipher.doFinal(data);
    }
	
    /* 私钥解密 */
    public static byte[] decryptByPrivateKey(byte[] data,String key)throws Exception{
        //对私钥解密
        byte[] keyBytes = decryptBASE64(key);
         
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance("PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
         
        return cipher.doFinal(data);
    }
    
    /* 公钥加密 */
    public static byte[] encryptByPublicKey(byte[] data,String key)throws Exception{
        //对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        //取公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
         
        //对数据解密
        Cipher cipher = Cipher.getInstance("PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
         
        return cipher.doFinal(data);
    }
    
    /* 公钥解密 */
    public static byte[] decryptByPublicKey(byte[] data,String key)throws Exception{
        //对私钥解密
        byte[] keyBytes = decryptBASE64(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
         
        //对数据解密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
         
        return cipher.doFinal(data);
    }
    
    /* 私钥签名 */
    public static String rsaSign(byte[] data,String privateKey)throws Exception{
        //解密私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        //构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //取私钥匙对象
        PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //用私钥对信息生成数字签名
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(privateKey2);
        signature.update(data);
         
        return encryptBASE64(signature.sign());
    }
    
    /* 公钥效验 */
    public static boolean verify(byte[] data,String publicKey,String sign)throws Exception{
        //解密公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        //构造X509EncodedKeySpec对象
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //取公钥匙对象
        PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
         
        Signature signature = Signature.getInstance("RSA/ECB/PKCS1Padding");
        signature.initVerify(publicKey2);
        signature.update(data);
        //验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }
    
    /* 公钥效验 */
    public static boolean verify(byte[] data,RSAPublicKey publicKey,String sign) throws Exception{
        Signature signature = Signature.getInstance("RSA/ECB/PKCS1Padding");
        signature.initVerify(publicKey);
        signature.update(data);
        //验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }
    
    
    /* ***********************杉德宝特殊RSA证书签名/验签************************ */
    public static String getRSASign(String lsh,String path) throws Exception {
		byte[] data = lsh.getBytes("UTF-8");

		File file = new File(path);
		FileInputStream in = new FileInputStream(file);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] tmpbuf = new byte[1024];
		int count = 0;
		while ((count = in.read(tmpbuf)) != -1) {
			bout.write(tmpbuf, 0, count);
			tmpbuf = new byte[1024];
		}
		in.close();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				bout.toByteArray());
		RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory
				.generatePrivate(privateKeySpec);

		Signature signature = Signature.getInstance("SHA1WithRSA");
		signature.initSign(privateKey);
		signature.update(data);

		String sign = new String(Base64.encode(signature.sign()), "UTF-8");
		String sts = URLEncoder.encode(sign, "UTF-8").replace("+", "%20");
		return sts;
	}
    
    public static boolean verifySign(byte[] data, String sign,
			String certificatePath) throws Exception {
		// 获得证书
		X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);
		return verifySign(data, sign, x509Certificate);
	}
    
	private static boolean verifySign(byte[] data, String sign,
			X509Certificate x509Certificate) throws Exception {
		PublicKey publicKey = x509Certificate.getPublicKey();
		Signature signature = Signature.getInstance(x509Certificate
				.getSigAlgName());
		signature.initVerify(publicKey);
		signature.update(data);
		return signature.verify(Base64.decode(sign.getBytes()));
	}
	
	private static Certificate getCertificate(String certificatePath)
			throws Exception {
		// 实例化证书工厂
		CertificateFactory certificateFactory = CertificateFactory
				.getInstance("x.509");
		// 取得证书文件流
		FileInputStream in = new FileInputStream(certificatePath);
		// 生成证书
		Certificate certificate = certificateFactory.generateCertificate(in);
		// 关闭证书文件流
		in.close();
		return certificate;
	}
	
	/* 杉德久彰通过模和指数获取公钥 */
	public static RSAPublicKey getPublicKey(String modulus, String exponent) throws Exception {
		KeyFactory keyFac = null;  
        try {  
            keyFac = KeyFactory.getInstance("RSA",  
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());  
        } catch (NoSuchAlgorithmException ex) {  
            throw new Exception(ex.getMessage());  
        }  
        System.out.println(new BigInteger(modulus,16));
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(  
                modulus,16), new BigInteger(exponent,10));  
        try {  
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);  
        } catch (InvalidKeySpecException ex) {  
            throw new Exception(ex.getMessage());  
        }  
	}
    
	// 获取久彰公钥(指数模数) 
	public static RSAPublicKey getSandPublicKey() throws Exception {
		if (sandPublicKey == null)
			sandPublicKey = getPublicKey(sandModulus, sandPublic_exponent);
		return sandPublicKey;
	}
	
	// 久彰验签 
	public static boolean checkSandJZBac(String bacSign, String data) {
		System.out.println("bac:"+bacSign);
		System.out.println("bac2:"+hex2Bytes(bacSign));
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.DECRYPT_MODE, getSandPublicKey());
	        String bacMd5 = new String(cipher.doFinal(hex2Bytes(bacSign)));
	        System.out.println("后台返回签名:"+bacMd5);
	        System.out.println("MYSign:"+MD5Util.MD5(data, "utf8"));
	        if (bacMd5.equalsIgnoreCase(MD5Util.MD5(data, "utf8")))
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		String sign = "1966B5EBD9220962637F479295887F1FE1C5BDE2BD8CD0C667F37AE2FEA178084178A20C31FD4D0C7333197FACAC2099B5DF6ADB1AAB516D0EAD5DFFA648002B5E7FE5D1194938CB058A255E248E475CF3F6860492EF08969A805FB70466854811DFC650398E3EBEB6C1EE9E8447F911271674C0BECC294F5B634B40B894C285";
		//String msg = "{\"respCode\":\"100000\",\"respResult\":\"成功\",\"mid\":\"888002159990131\",\"transNo\":\"15112401000000002028\",\"orderCode\":\"151124MO0000099\",\"orderAmt\":\"0.01\",\"currency\":\"156\",\"respTime\":\"20151124105058\"}";
		String msg = "{\"respCode\":\"100000\",\"respResult\":\"成功\",\"mid\":\"888002159990131\",\"transNo\":\"15112501000000002726\",\"orderCode\":\"151125MO0000015\",\"orderAmt\":\"0.01\",\"currency\":\"156\",\"respTime\":\"20151125150950\"}";
		//System.out.println(MD5Util.MD5(msg, "utf8"));
		System.out.println(checkSandJZBac(sign, msg));
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
	
	/* *************************支付宝2.0RSA***************************** */
	public static String alipay2RSA (byte[] data, String privateKey) throws Exception {
		/*PrivateKey priKey = getPrivateKeyFromPKCS8("RSA",
				new ByteArrayInputStream(privateKey.getBytes()));

		Signature signature = Signature.getInstance("SHA1WithRSA");

		signature.initSign(priKey);

		if (StringUtils.isEmpty(charset))
			signature.update(content.getBytes());
		else {
			signature.update(content.getBytes(charset));
		}

		byte[] signed = signature.sign();

		return new String(Base64.encodeBase64(signed));*/
		
		
		//解密私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        //取私钥匙对象
        PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //用私钥对信息生成数字签名
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(privateKey2);
        signature.update(data);
         
        return encryptBASE64(signature.sign());
	}
	
	
    /* **************************************************************** */
    /** 
     * BASE64解密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptBASE64(String key) throws Exception {  
        return (new BASE64Decoder()).decodeBuffer(key);  
    }  
      
    /** 
     * BASE64加密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptBASE64(byte[] key) throws Exception {  
        return (new BASE64Encoder()).encodeBuffer(key);  
    }
}

