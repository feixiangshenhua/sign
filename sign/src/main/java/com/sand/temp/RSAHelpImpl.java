package com.sand.temp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sand.crypto.base64.Base64Decoder;
import com.sand.crypto.base64.Base64Encoder;
import com.sand.crypto.base64.Base64Helper;
import com.sand.crypto.rsa.MessageDig;
import com.sand.crypto.rsa.RSAHelp;
import com.sand.crypto.util.ByteUtil;
import com.sand.crypto.util.ConfigUtil;
import com.sand.crypto.util.CryptoUtil;

public class RSAHelpImpl
  implements RSAHelp
{
  private static final String algorithm = "RSA";
  private static final String SIGNAlgorithm = "MD5";
  private static final String EDAlgorithm = "RSA/ECB/PKCS1Padding";
  private static final String signalgorithm = "MD5withRSA";
  private String[] keyArray = new String[3];

  private boolean model = false;

  private Signature signature = null;

  public RSAHelpImpl(boolean model)
  {
    init(model);
  }

  public String getRootE() {
    if (this.keyArray[0] != null) {
      return this.keyArray[0];
    }
    return null;
  }

  public String getRootN() {
    if (this.keyArray[1] != null) {
      return this.keyArray[1];
    }
    return null;
  }

  public RSAHelpImpl()
  {
    init(this.model);
  }

  private boolean init(boolean model) {
    try {
      this.model = model;

      this.keyArray[0] = "65537";

      this.keyArray[1] = (this.model ? 
        "B6D363BFE1EDB743C20E4CCF09CE452E00E23FD2C20B0645A477D4CEAF01992B4585D44F4DB043784E2F2A8A673FF63A83B973EB817B169D892E4AA3118E74E857218087378D37386FEE01498E3C787DD56B3E90B9A3A169220DD2B6D0B35A5D2D48963C3D20ABF2AAA48916A0E106C7569BE232C63C5FC5E83F0D5E24313DCF" : 
        "D2F64C5D15BF54288281CFEAF37E949F39FB678E8BEA5936F6D22E47DA0516DC00C02C8B5BE413013FCBEAB563C57E697C81199BB9544E2047C341453BA57E1101F85DBD17BB1503B1D1E77496D168A7C89D7EC6A8C46A2755F3F9C2E92FD1817D2EDD66A94C0AB66F8932D2D230B40FEEC08F6C73391490867C7B7A7BCA8335");

      this.signature = Signature.getInstance("MD5withRSA");
      return true;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return false;
    } catch (Exception e) {
      e.printStackTrace();
    }return false;
  }

  public String sign(BigInteger d, BigInteger n, byte[] plainText)
  {
    try
    {
      MessageDig dig = new MessageDig();
      byte[] pbyte = dig.digest(plainText, "MD5");

      RSAPrivateKey priKey = (RSAPrivateKey)toKey(d, n, "private");
      return encrypt(priKey, pbyte);
    }
    catch (NoSuchAlgorithmException e1) {
      e1.printStackTrace();
    } catch (InvalidKeySpecException e1) {
      e1.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String sign(BigInteger d, BigInteger n, String plainText)
  {
    try {
      MessageDig dig = new MessageDig();
      byte[] pbyte = dig.mDigest(plainText, "MD5");

      RSAPrivateKey priKey = (RSAPrivateKey)toKey(d, n, "private");
      return encrypt(priKey, pbyte);
    }
    catch (NoSuchAlgorithmException e1) {
      e1.printStackTrace();
    } catch (InvalidKeySpecException e1) {
      e1.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String sign(byte[] key, String plainText)
  {
    try {
      MessageDig dig = new MessageDig();
      byte[] pbyte = dig.mDigest(plainText, "MD5");
      return ByteUtil.bytes2Hex(encryptByPrivateKey(pbyte, key));
    }
    catch (NoSuchAlgorithmException e1) {
      e1.printStackTrace();
    } catch (InvalidKeySpecException e1) {
      e1.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean verify(byte[] key, String plainText, String signedStr)
  {
    try {
      MessageDig dig = new MessageDig();
      byte[] pbyte = dig.mDigest(plainText, "MD5");

      byte[] data = ByteUtil.hex2Bytes(signedStr);
      byte[] pbyte2 = decryptByPublicKey(data, key);
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

  public boolean verify(BigInteger e, BigInteger n, byte[] plainText, String signedStr)
  {
    try
    {
      MessageDig dig = new MessageDig();
      byte[] pbyte = dig.digest(plainText, "MD5");
      System.out.println(">>>>" + ByteUtil.bytes2Hex(pbyte));
      PublicKey pubKey = (PublicKey)toKey(e, n, "public");
      byte[] pbyte2 = decrypt(pubKey, signedStr);
 
      System.out.println(new String(pbyte)+"aaa\n"+ new String(pbyte2));
      return Arrays.equals(ByteUtil.bytes2Hex(pbyte).getBytes(), pbyte2);
    } catch (NoSuchAlgorithmException e1) {
      e1.printStackTrace();
    } catch (InvalidKeySpecException e1) {
      e1.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return false;
  }

  public boolean verify(BigInteger e, BigInteger n, String plainText, String signedStr)
  {
    try {
      MessageDig dig = new MessageDig();
      byte[] pbyte = dig.mDigest(plainText, "MD5");

      PublicKey pubKey = (PublicKey)toKey(e, n, "public");
      byte[] pbyte2 = decrypt(pubKey, signedStr);
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

  public String sign(PrivateKey priKey, String plainText)
    throws Exception
  {
    if ((priKey != null) && (this.signature != null)) {
      this.signature.initSign(priKey);
      this.signature.update(plainText.getBytes("utf8"));
      byte[] signByte = this.signature.sign();
      return ByteUtil.bytes2Hex(signByte);
    }
    return null;
  }

  public boolean verify(PublicKey pubKey, String plainText, String signedStr)
    throws Exception
  {
    if ((pubKey != null) && (plainText != null) && (signedStr != null)) {
      this.signature.initVerify(pubKey);
      this.signature.update(plainText.getBytes("utf8"));
      return this.signature.verify(ByteUtil.hex2Bytes(signedStr));
    }
    return false;
  }

  public String encryptRSA(String plaintext, String eStr, String nStr)
    throws Exception
  {
    BigInteger e = new BigInteger(eStr);
    BigInteger n = new BigInteger(nStr);
    byte[] pb = plaintext.getBytes();
    return encryptRSA(pb, e, n);
  }

  public String encryptRSA(byte[] plaintext, BigInteger e, BigInteger n)
  {
    if (plaintext != null) {
      BigInteger m = new BigInteger(plaintext);
      BigInteger c = m.modPow(e, n);
      return c.toString(16);
    }
    return null;
  }

  public String encrypt(BigInteger e, BigInteger en, byte[] plaintext)
  {
    if ((e != null) && (en != null)) {
      try {
        PublicKey publicKey = (PublicKey)toKey(e, en, "public");
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, publicKey);
        byte[] bye = cipher.doFinal(plaintext);
        return ByteUtil.bytes2Hex(bye);
      } catch (Exception ee) {
        ee.printStackTrace();
      }
    }
    return null;
  }

  public byte[] decrypt(BigInteger d, BigInteger dn, String cipherstr)
  {
    if ((d != null) && (dn != null)) {
      try {
        PrivateKey privateKey = (PrivateKey)toKey(d, dn, "private");
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, privateKey);
        byte[] bye = ByteUtil.hex2Bytes(cipherstr);
        return cipher.doFinal(bye);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  private Key toKey(BigInteger exponent, BigInteger modulus, String type) throws Exception {
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

  public String encrypt(PrivateKey privateKey, byte[] pbyte) {
    if (privateKey != null) {
      try {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, privateKey);
        byte[] bye = cipher.doFinal(pbyte);
        return ByteUtil.bytes2Hex(bye);
      } catch (Exception ee) {
        ee.printStackTrace();
      }
    }
    return null;
  }

  public byte[] decrypt(PublicKey publicKey, String cipherStr) {
    if (publicKey != null) {
      try {
        byte[] cbyte = ByteUtil.hex2Bytes(cipherStr);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicKey);
        return cipher.doFinal(cbyte);
      }
      catch (Exception ee) {
        ee.printStackTrace();
      }
    }
    return null;
  }

  public String decryptRSA(String ciphertext, String dStr, String nStr)
  {
    BigInteger d = new BigInteger(dStr);
    BigInteger n = new BigInteger(nStr);
    byte[] mt = decryptRSA(ciphertext, d, n);
    if (mt != null) {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < mt.length; i++) {
        sb.append((char)mt[i]);
      }
      return sb.toString();
    }
    return null;
  }

  public byte[] decryptRSA(String ciphertext, BigInteger d, BigInteger n)
  {
    if (ciphertext != null) {
      BigInteger c = new BigInteger(ciphertext, 16);
      BigInteger m = c.modPow(d, n);
      byte[] mt = m.toByteArray();
      return mt;
    }
    return null;
  }

  public byte[] encryptByPrivateKey(byte[] data, byte[] key)
    throws Exception
  {
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");

    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

    cipher.init(1, privateKey);

    return cipher.doFinal(data);
  }

  public byte[] encryptByPublicKey(byte[] data, byte[] key)
    throws Exception
  {
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");

    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

    PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

    cipher.init(1, pubKey);

    return cipher.doFinal(data);
  }

  public byte[] decryptByPrivateKey(byte[] data, byte[] key)
    throws Exception
  {
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");

    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

    cipher.init(2, privateKey);

    return cipher.doFinal(data);
  }

  public byte[] decryptByPublicKey(byte[] data, byte[] key)
    throws Exception
  {
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");

    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

    PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

    cipher.init(2, pubKey);

    return cipher.doFinal(data);
  }

  public byte[] getKeyOutWithHeaderAndFooter(String keyFilePath) throws Exception
  {
    FileInputStream fin = new FileInputStream(keyFilePath);

    BASE64Decoder base64Decoder = new BASE64Decoder();
    return base64Decoder.decodeBuffer(fin);
  }

  public byte[] getKeyWithHeaderAndFooter(String keyFilePath) throws Exception
  {
    FileInputStream fin = new FileInputStream(keyFilePath);
    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
    StringBuffer sb = new StringBuffer();
    String line = "";
    while ((line = br.readLine()) != null) {
      line = line.trim();
      if ((!line.equalsIgnoreCase("-----BEGIN PUBLIC KEY-----")) && (!line.equalsIgnoreCase("-----END PUBLIC KEY-----")) && 
        (!line.equalsIgnoreCase("-----BEGIN RSA PRIVATE KEY-----")) && (!line.equalsIgnoreCase("-----END RSA PRIVATE KEY-----")) && 
        (!line.equalsIgnoreCase("-----BEGIN PRIVATE KEY-----")) && (!line.equalsIgnoreCase("-----END PRIVATE KEY-----")))
      {
        sb.append(line);
      }
    }
    BASE64Decoder base64Decoder = new BASE64Decoder();
    return base64Decoder.decodeBuffer(sb.toString());
  }

  public byte[] getKey(Key key)
    throws Exception
  {
    return key.getEncoded();
  }

  public String encryptB64Str(PrivateKey privateKey, String plaintext)
  {
    if ((privateKey != null) && (plaintext != null)) {
      try {
        byte[] data = plaintext.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, privateKey);
        byte[] b = cipher.doFinal(data);
        return Base64Encoder.encode(b);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public String decryptB64Str(PublicKey publicKey, String cipher64text)
  {
    if (publicKey != null) {
      try {
        byte[] cipherbyte = Base64Decoder.decode(cipher64text);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicKey);
        byte[] b = cipher.doFinal(cipherbyte);
        return new String(b);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public byte[] getPriKeyByCert(String certPath, String certPwd) throws Exception {
    byte[] privKey = (byte[])null;
    try {
      KeyStore ks = KeyStore.getInstance("PKCS12");
      FileInputStream is = new FileInputStream(certPath);
      ks.load(is, certPwd.toCharArray());
      is.close();
      Enumeration enuma = ks.aliases();
      String keyAlias = null;
      if (enuma.hasMoreElements()) {
        keyAlias = (String)enuma.nextElement();
      }
      PrivateKey privatekey = (PrivateKey)ks.getKey(keyAlias, certPwd.toCharArray());
      privKey = privatekey.getEncoded();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return privKey;
  }

  public byte[] getPubKeyByCert(String certPath) throws Exception
  {
    byte[] pubKey = (byte[])null;
    try {
      CertificateFactory cff = CertificateFactory.getInstance("X.509");
      FileInputStream certIn = new FileInputStream(certPath);
      Certificate cf = cff.generateCertificate(certIn);
      PublicKey publicKey = cf.getPublicKey();
      pubKey = publicKey.getEncoded();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pubKey;
  }

  public Key loadKeyFromObject(String merId, String action, String keyPath)
    throws Exception
  {
    Key key = null;
    StringBuffer sb = new StringBuffer();
    sb.append(merId).append(action);

    String k = ByteUtil.bytes2Base64str(sb.toString().getBytes());
    String bv = ConfigUtil.getValue(k);
    String v = new String(ByteUtil.bas64str2Bytes(bv));

    File kf = new File(keyPath);
    if ((kf.exists()) && (kf.isFile())) {
      String fn = kf.getName();
      if ((!"".equals(v)) && (v.equals(fn))) {
        FileInputStream fin = new FileInputStream(kf);
        ObjectInputStream b = new ObjectInputStream(fin);
        if ("S".equals(action)) {
          SealedObject so = (SealedObject)b.readObject();
          Cipher cipher = CryptoUtil.getCipher(2);
          key = (PrivateKey)so.getObject(cipher);
        } else {
          key = (PublicKey)b.readObject();
        }
      }
    }
    return key;
  }

  public Key loadKey(String merId, String keypath, int type)
    throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
  {
    Properties props = new Properties();

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");

    props.load(new FileInputStream(keypath));

    String loadId = new String(ByteUtil.hex2Bytes(props.getProperty("merchantid")));
    if ((merId == null) || (!merId.equals(loadId))) {
      return null;
    }
    if (type == 0) {
      String privateKeyValue = props.getProperty("privatekey");
      PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(ByteUtil.hex2Bytes(privateKeyValue));

      PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);

      return privateKey;
    }
    String privateKeyValue = props.getProperty("publickey");
    X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(ByteUtil.hex2Bytes(privateKeyValue));
    PublicKey publicKey = keyFactory.generatePublic(bobPubKeySpec);
    return publicKey;
  }

  public String bigIntHex2X509(String exponentHex, String modulusHex) throws Exception
  {
    StringBuffer sb = new StringBuffer();
    try {
      BigInteger exponent = new BigInteger(exponentHex, 16);
      BigInteger modulus = new BigInteger(modulusHex, 16);

      Key key = toKey(exponent, modulus, "public");

      BASE64Encoder encoder = new BASE64Encoder();
      String standardKey = encoder.encodeBuffer(getKey(key));

      sb.append("-----BEGIN PUBLIC KEY-----\n").append(standardKey).append("-----END PUBLIC KEY-----\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sb.toString();
  }

  public Map pscs8KeyAndX509ToBigIntHex(String psck8PrivateKey, String x509PublicKey) throws Exception {
    Map resMap = new HashMap();
    byte[] prikey = getKeyWithHeaderAndFooter(psck8PrivateKey);
    byte[] pubkey = getKeyWithHeaderAndFooter(x509PublicKey);

    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(prikey);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");

    RSAPrivateKey privateKey = (RSAPrivateKey)keyFactory.generatePrivate(pkcs8KeySpec);

    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubkey);

    RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(x509KeySpec);

    resMap.put("d", privateKey.getPrivateExponent().toString(16));
    resMap.put("n", privateKey.getModulus().toString(16));
    resMap.put("e", publicKey.getPublicExponent().toString(16));

    return resMap;
  }

  protected String privateinfoToXMLRSAPriKey(String psck8PrivateKey)
  {
    try
    {
      StringBuffer buff = new StringBuffer(1024);
      byte[] prikey = getKeyWithHeaderAndFooter(psck8PrivateKey);
      PKCS8EncodedKeySpec pvkKeySpec = new PKCS8EncodedKeySpec(prikey);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      RSAPrivateCrtKey pvkKey = (RSAPrivateCrtKey)keyFactory.generatePrivate(pvkKeySpec);

      buff.append("<RSAKeyValue>");
      buff.append("<Modulus>" + Base64Helper.encode(removeMSZero(pvkKey.getModulus().toByteArray())) + "</Modulus>");
      buff.append("<Exponent>" + Base64Helper.encode(removeMSZero(pvkKey.getPublicExponent().toByteArray())) + "</Exponent>");
      buff.append("<P>" + Base64Helper.encode(removeMSZero(pvkKey.getPrimeP().toByteArray())) + "</P>");
      buff.append("<Q>" + Base64Helper.encode(removeMSZero(pvkKey.getPrimeQ().toByteArray())) + "</Q>");
      buff.append("<DP>" + Base64Helper.encode(removeMSZero(pvkKey.getPrimeExponentP().toByteArray())) + "</DP>");
      buff.append("<DQ>" + Base64Helper.encode(removeMSZero(pvkKey.getPrimeExponentQ().toByteArray())) + "</DQ>");
      buff.append("<InverseQ>" + Base64Helper.encode(removeMSZero(pvkKey.getCrtCoefficient().toByteArray())) + "</InverseQ>");
      buff.append("<D>" + Base64Helper.encode(removeMSZero(pvkKey.getPrivateExponent().toByteArray())) + "</D>");
      buff.append("</RSAKeyValue>");
      return buff.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  protected String privateinfoToXMLRSAPubKey(String psck8PrivateKey) {
    try {
      StringBuffer buff = new StringBuffer(1024);
      byte[] prikey = getKeyWithHeaderAndFooter(psck8PrivateKey);
      PKCS8EncodedKeySpec pvkKeySpec = new PKCS8EncodedKeySpec(prikey);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      RSAPrivateCrtKey pvkKey = (RSAPrivateCrtKey)keyFactory.generatePrivate(pvkKeySpec);

      buff.append("<RSAKeyValue>");
      buff.append("<Modulus>" + Base64Helper.encode(removeMSZero(pvkKey.getModulus().toByteArray())) + "</Modulus>");
      buff.append("<Exponent>" + Base64Helper.encode(removeMSZero(pvkKey.getPublicExponent().toByteArray())) + "</Exponent>");
      buff.append("</RSAKeyValue>");
      return buff.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  protected String privateinfoToXMLRSAPriKey(String hex_d, String hex_n)
  {
    try {
      StringBuffer buff = new StringBuffer(1024);
      BigInteger exponent = new BigInteger(hex_d, 16);
      BigInteger modulus = new BigInteger(hex_n, 16);

      Key key = toKey(exponent, modulus, "private");

      PKCS8EncodedKeySpec pvkKeySpec = new PKCS8EncodedKeySpec(key.getEncoded());
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      RSAPrivateKey pvkKey = (RSAPrivateKey)keyFactory.generatePrivate(pvkKeySpec);

      buff.append("<RSAKeyValue>");
      buff.append("<Modulus>" + Base64Helper.encode(removeMSZero(pvkKey.getModulus().toByteArray())) + "</Modulus>");
      buff.append("<Exponent>AQAB</Exponent>");
      buff.append("<P></P>");
      buff.append("<Q></Q>");
      buff.append("<DP></DP>");
      buff.append("<DQ></DQ>");
      buff.append("<InverseQ></InverseQ>");
      buff.append("<D>" + Base64Helper.encode(removeMSZero(pvkKey.getPrivateExponent().toByteArray())) + "</D>");
      buff.append("</RSAKeyValue>");
      return buff.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  protected String publicinfotoXMLRSAPubKey(String hex_e, String hex_n) {
    try {
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      StringBuffer buff = new StringBuffer(1024);

      BigInteger exponent = new BigInteger(hex_e, 16);
      BigInteger modulus = new BigInteger(hex_n, 16);

      Key key = toKey(exponent, modulus, "public");

      X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key.getEncoded());

      RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(x509KeySpec);

      buff.append("<RSAKeyValue>");
      buff.append("<Modulus>" + Base64Helper.encode(removeMSZero(publicKey.getModulus().toByteArray())) + "</Modulus>");
      buff.append("<Exponent>" + Base64Helper.encode(removeMSZero(publicKey.getPublicExponent().toByteArray())) + "</Exponent>");
      buff.append("</RSAKeyValue>");
      return buff.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static byte[] removeMSZero(byte[] data)
  {
    int len = data.length;
    byte[] data1;
    if (data[0] == 0) {
      data1 = new byte[data.length - 1];
      System.arraycopy(data, 1, data1, 0, len - 1);
    } else {
      data1 = data;
    }return data1;
  }

  public static void main(String[] args)
    throws Exception
  {
    RSAHelpImpl rsaHelp = new RSAHelpImpl();

    String psck8PrivateKey = "D:/SecureCRT/download/pkcs8javaprimarykey.pem";
    String x509PublicKey = "D:/SecureCRT/download/rsa_public_key.pem";

    System.out.println(rsaHelp.privateinfoToXMLRSAPriKey(psck8PrivateKey));
    System.out.println(rsaHelp.privateinfoToXMLRSAPubKey(psck8PrivateKey));

    Map map = rsaHelp.pscs8KeyAndX509ToBigIntHex(psck8PrivateKey, x509PublicKey);

    String SMPCLIENT_MERCHANTID_RSA_D = (String)map.get("d");
    String SMPCLIENT_MERCHANTID_RSA_N = (String)map.get("n");
    String SMPCLIENT_MERCHANTID_RSA_E = (String)map.get("e");
    System.out.println("d=" + SMPCLIENT_MERCHANTID_RSA_D);
    System.out.println("n=" + SMPCLIENT_MERCHANTID_RSA_N);
    System.out.println("e=" + SMPCLIENT_MERCHANTID_RSA_E);

    String sandExponentHex = "10001";

    String sandmodulusHex = "d3fcd87975b5a0cdd128d98677af51fe978d8bfc1256d1b437ca4ac8194b72bd5bc9a188944e5e40e2664e1607929d5401eb4b7abacc7cda22612aa94d74e43ed715ae0691b9e90195580d0f2c628da8db0bd2f7c8848c353507cde4fede4108bda06b2c9a6e334fc1fc9c42c973ab80cbd32bd62999d339739f2af029b2467b";
    String x509pub = rsaHelp.bigIntHex2X509(sandExponentHex, sandmodulusHex);
    System.out.println("x509pub -->\n" + x509pub + "\n");

    BigInteger d = new BigInteger("C4E31CBDDE538CCD092A18238A618B8DBFEAD60E2DF6623D751545E6EE51166E9BF8A251EA211A73A397A2809BB3CA3AD56462605FAA567223387C2DF850C10421FB09E1B3BCDFCDEBAA18FEE6D0B0386ED6DC3FA77363F1E3E4E4B576C5D67D9660DE654DB2492F816FF8B05AB4F531C6CDCA7938DF8A831EBC6625422C4721", 16);
    BigInteger n = new BigInteger("C8576B2890F8C439F9A51A6F36FE9ED9DA3C08BB870B204528518A986E7730641BE648E1F906412A20D61640601C42902D77BCD487538574065AA528D594A5E645E000ECF7429209307C44424E793C0B069A2E1A1570AF39293861C5201C9EFEBE8BAB28536D73FE9AF2B96289281A7DFFC377C2D6F7639B0985F62F862F27E7", 16);
    BigInteger e = new BigInteger("010001", 16);

    System.out.println("JAVA 加密后的数据：" + rsaHelp.encrypt(e, n, "测试fdjafjaurei13223545测下...".getBytes("UTF-8")));

    System.out.println("JAVA 验证签名：" + rsaHelp.verify(e, n, "刘栋liudong001".getBytes("UTF-8"), "c4bddd67b620db565bbce0853c54f88804bb365f561caf1dccde887b05d26f48001b2f90477959a8103af846f2db40d0c7ad2e4bfc3b08fb0d38f01654b41f405da531ff9d78ed5d18fb0cdc58977acf5a66dbb33f126c4e75bb2f77e1ef02022134659378e8a0e60d8f2a8067d97aeaea07e4c436264660ced796fe52f3793c"));

    System.out.println("JAVA签名：" + rsaHelp.sign(d, n, "测试2013".getBytes("UTF-8")));
  }
}