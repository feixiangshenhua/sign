package com.sand.sign.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;

import com.sand.crypto.rsa.MessageDig;
import com.sand.crypto.util.ByteUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String aa = "16eb7efff4913461d2f3522698d401349ac05461b3ab605ec9f8dc808e2e29fe252a5702e9c8bdb3dc2fba6e8542177e0deef5f7bf29930df86efa570b8e83e507951aab36af65f9495e2c221d7f1364d3ede8e44fd66d5a7c9e94a039188daeaae2ed663223120f5d19cb91e5ffa29da4063df7300454277d79a92fb2016d67";
		System.out.println(aa.length());
	}

	/**
	 * 验证签名
	 * @param publicKey 公钥
	 * @param plainText 待检查数据
	 * @param signedStr 密文
	 * @return true-成功 false-失败
	 */
	public boolean verify(byte[] publicKey, String plainText, String signedStr) {
	    try {
	      MessageDig dig = new MessageDig();
	      byte[] pbyte = dig.mDigest(plainText, "SHA1");

	      byte[] data = ByteUtil.hex2Bytes(signedStr);
	      byte[] pbyte2 = decryptByPublicKey(data, publicKey);
	      return Arrays.equals(pbyte, pbyte2);
	    } catch (NoSuchAlgorithmException e1) {
	      e1.printStackTrace();
	    } catch (InvalidKeySpecException e1) {
	      e1.printStackTrace();
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return false;
  }
	
	 public byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
	    PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
	    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    cipher.init(2, pubKey);
	    return cipher.doFinal(data);
	 }
}
