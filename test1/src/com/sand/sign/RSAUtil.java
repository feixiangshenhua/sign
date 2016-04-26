package com.sand.sign;

import it.sauronsoftware.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sand.crypto.rsa.MessageDig;
import com.sand.sign.util.SHA1Util;
import com.sand.sign.util.UTIL;

public class RSAUtil {
	
	public static void main(String[] args) {
		try {
			/*Map<String, Object> rsaKeys = getKeys();
			String publicK = getPublicKey(rsaKeys);
			String privateK = getPrivateKey(rsaKeys);*/
			//String a  = "{\"mid\":\"888002148160001\",\"shortName\":\"商户合作\",\"checkInOrg\":\"0001\",\"productId\":\"200010\",\"orderAmt\":\"000000000001\",\"currency\":\"156\",\"authCode\":\"333010756703003005\",\"goodsContent\":\"\",\"operType\":\"1\",\"operId\":\"-3\",\"attach\":\"\"}";
			String a = "{\"mid\": \"888002148160001\"," + 
					"\"shortName\": \"商户合作\"," +
					"\"checkInOrg\": \"0001\"," + 
					"\"productId\": \"200010\"," +
					"\"orderAmt\": \"000000000200\"," +
					"\"currency\": \"156\"," +
					"\"authCode\": \"336065041046179078\"," + 
					"\"goodsContent\": \"\"," +
					"\"operType\": \"1\"," +
					"\"operId\": \"-3\"," +
					"\"attach\": \"\"," +
					"\"posOrderId\": \"123456\"" +
					"}";
			a = a.replaceAll(" ", "");
			//String a = "{\"mid\":\"888002148160001\",\"shortName\":\"\",\"checkInOrg\":\"0001\",\"productId\":\"200010\",\"orderAmt\":\"00000000020\",\"currency\":\"156\",\"authCode\":\"339020253003953003\",\"goodsContent\":\"\",\"operType\":\"1\",\"operId\":\"-3\",\"attach\":\"\"}";
			String b = "{\"mid\":\"888002148160001\",\"shortName\":\"商户合作\",\"checkInOrg\":\"0001\",\"productId\":\"200010\",\"orderAmt\":\"000000000200\",\"currency\":\"156\",\"authCode\":\"332000137056946036\",\"goodsContent\":\"\",\"operType\":\"1\",\"operId\":\"-3\",\"attach\":\"\"}";
			System.out.println(a);
			String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIbZTjmoWkW8qlb7u1kUPNKZKHrR" + 
								"epXps5LK/d00NWbPq8FdZzFISet2F4OqmA5lxVX+GHBYFWVagSmN/HlwassHy8xkNy9RTPoQrKoO" +
								"QD4p/lB5xL+cQnvEAGLDytPJPJKtUnl4hwOHg7ovN5WjSlLxHMM5DB/bDsyxJFWL9AbhAgMBAAEC" +
								"gYEAhfSyt6Yc3u/XSwdQieIHtbE9vBwCKY/5uhX6b9agQdyEo1a03wkPnV83Zq191beVe6IqBtgJ" +
								"kSCB9JS+vKhG/KhCumLokzpSv0JSQm+cNRAcFW8vyMgCD5jWJFsHsQiJs0Bn/p1BzIsnZMmptxCz" +
								"owHdPm6X8at+S0vhyjvDoK0CQQDecwDlXXDk8EMYE1rRSihZ8GjV+Qkwn6HhTn29mxNd0XLMvG74" +
								"dD2kyGPjZyqwEREvLa1n6xp9Hod/VWILMx/jAkEAmy/1x7wPE+IV+TfWnhKyiNFEiKWS8dcAfRdN" +
								"WnpGZ9BLNMQthAwUxDLBVs+DsaioeSrBcpa9NnWHzUrZ8/vxawJAeJE6nhasNtnmc6kh4yffgQfl" +
								"unvVOE59TgHd8yOXNpVsNH25Y96U/rhCM6HaKD3H45tNTnLGE6S1ahWcI1fdYwJATq3rM5/yGQeY" +
								"UmlwA2bBY8KNt6YSsVBrbACDdGroBTxC1PbdiMRDRxHgmi3NvCYhfIUj3P/14IWIG5pmlF/ZDwJB" +
								"ALQjiUkyiBz5sdy1C0It6XSTzgd3VRsboz2VSxsGgB5b8NCwP+i6luYl+cyDzcKjEKY9yaQ5nz0p" +
								"2dJplJhCZSE=";
						
			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG2U45qFpFvKpW+7tZFDzSmSh60XqV6bOSyv3dNDVmz6vBXWcxSEnrdheDqpgOZcVV/hhwWBVlWoEpjfx5cGrLB8vMZDcvUUz6EKyqDkA+Kf5QecS/nEJ7xABiw8rTyTySrVJ5eIcDh4O6LzeVo0pS8RzDOQwf2w7MsSRVi/QG4QIDAQAB";
			
			MessageDig dig = new MessageDig();
			String plaintText = UTIL.bytesToHexStr(dig.mDigest(a, "sha-1"));
			System.out.println("杉德测试包" + plaintText);
			//System.out.println(UTIL.bytesToHexStr(decryptByPublicKey(UTIL.HexString2Bytes(token), publicKey)));
			
			/*System.out.println("获取公钥:"+publicK+"["+publicK.length()+"]");
			System.out.println("获取私钥:"+privateK+"["+privateK.length()+"]");*/
			//AD8039D881BC65FB3AE4AF264FEDCD1CEFCCDA11
//			String data = "ad8039d881bc65fb3ae4af264fedcd1cefccda11";
//			System.out.println("原始数据:"+UTIL.bytesToHexStr(data.getBytes()));
//			String data1 = UTIL.bytesToHexStr(encryptByPrivateKey(data.getBytes(), privateKey));
//			System.out.println("私钥加密:"+data1);
//			String data2 = UTIL.bytesToHexStr(decryptByPublicKey(UTIL.HexString2Bytes(data1), publicKey));
//			System.out.println("公钥解密:"+data2);
			/*System.out.println("原始数据:"+UTIL.bytesToHexStr(a.getBytes()));
			String data1 = UTIL.bytesToHexStr(encryptByPrivateKey(a.getBytes(), privateKey));
			System.out.println("私钥加密:"+data1);
			String data2 = UTIL.bytesToHexStr(decryptByPublicKey(UTIL.HexString2Bytes(data1), publicKey));
			System.out.println("公钥解密:"+data2);*/
			String data = SHA1Util.hex_sha1(a);
			System.out.println("对数据进行哈希后的结果：" + data);
			//String data = "ad8039d881bc65fb3ae4af264fedcd1cefccda11";
			String data1 = UTIL.bytesToHexStr(encryptByPrivateKey(data.getBytes(), privateKey));
			System.out.println("token:" + data1);
			System.out.println(new String(decryptByPublicKey(UTIL.HexString2Bytes(data1), publicKey)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
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
         
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initVerify(publicKey2);
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
