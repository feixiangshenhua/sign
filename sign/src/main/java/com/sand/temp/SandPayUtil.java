package com.sand.temp;

import java.math.BigInteger;
import java.security.Provider;
import java.security.Security;
import java.util.Date;

import com.sand.crypto.rsa.MessageDig;
import com.sand.crypto.rsa.RSAHelp;
import com.sand.crypto.secret.SandAPICipher;
import com.sand.crypto.secret.SandPayDes;
import com.sand.crypto.util.ByteUtil;
import com.sand.crypto.util.FileUtil;
import com.sand.crypto.util.StringUtil;

public class SandPayUtil {
	

	  private static final long serialVersionUID = 1028148993533301743L;
	  public static String SD_SIGNTYPE_MD5HEX = "MD5HEX";
	  public static String SD_SIGNTYPE_SHA1HEX = "SHA1HEX";
	  public static String SD_SIGNTYPE_MD5RSA = "MD5RSA";

	  public String JAVA_VENDOR = "SUN_JVM";
	  private SandPayDes sandDes;
	  private RSAHelp rsaHelp;
	  private MessageDig dig;
	  private byte[] sandDesKey;
	  public String LastError = null;
	  private BigInteger e;
	  private BigInteger d;
	  private BigInteger en;
	  private BigInteger dn;

	  public SandPayUtil(boolean model)
	  {
	    check();
	    this.rsaHelp = new RSAHelpImpl(model);
	    this.sandDes = new SandPayDes();
	    this.dig = new MessageDig();
	  }

	  public SandPayUtil() {
	    check();
	    this.rsaHelp = new RSAHelpImpl();
	    this.sandDes = new SandPayDes();
	  }

	  private void check() {
	    try {
	      String CURRENT_JVM_VENDOR = System.getProperty("java.vendor");
	      if (CURRENT_JVM_VENDOR == null) {
	        CURRENT_JVM_VENDOR = "SUN_JVM";
	      }
	      if (CURRENT_JVM_VENDOR.equalsIgnoreCase("IBM_JVM")) {
	        this.JAVA_VENDOR = "IBM";
	        Security.addProvider((Provider)Class.forName("com.ibm.crypto.provider.IBMJCE").newInstance());
	      } else {
	        this.JAVA_VENDOR = "SUN";
	        Security.addProvider((Provider)Class.forName("com.sun.crypto.provider.SunJCE").newInstance());
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  public String GetLastError()
	  {
	    return this.LastError;
	  }

	  public boolean LoadAcqKeyFile(String acqPkFile, String sandPbkFile)
	  {
	    try {
	      byte[] merchant_b = FileUtil.getBytesFromFile(acqPkFile);
	      byte[] sand_b = FileUtil.getBytesFromFile(sandPbkFile);
	      if ((merchant_b == null) || (sand_b == null)) {
	        this.LastError = "证书文件路径错误";
	        return false;
	      }
	      String merchant_Str = new String(merchant_b);
	      String sand_str = new String(sand_b);

	      String[] merchant_Array = merchant_Str.split("\r\n");
	      String[] sand_Array = sand_str.split("\r\n");
	      Date currDate = new Date();

	      StringBuffer mer_SignSb = new StringBuffer();
	      mer_SignSb.setLength(0);

	      int merchant_length = merchant_Array.length;
	      for (int i = 0; i < merchant_length; i++) {
	        merchant_Array[i] = StringUtil.trimString(merchant_Array[i]);
	        if (("[Certificate Type]".equalsIgnoreCase(merchant_Array[i])) && (i + 1 < merchant_length)) {
	          String type = StringUtil.trimString(merchant_Array[(i + 1)]);
	          if (!"Type=SAND.509".equalsIgnoreCase(type)) {
	            this.LastError = "商户证书标准类型错误";
	            return false;
	          }
	        } else if (("[Certificate Data]".equalsIgnoreCase(merchant_Array[i])) && (i + 5 < merchant_length)) {
	          String merchant_Ver = StringUtil.trimString(merchant_Array[(i + 1)]);
	          String merchant_Ser = StringUtil.trimString(merchant_Array[(i + 2)]);
	          String merchant_Iss = StringUtil.trimString(merchant_Array[(i + 3)]);
	          String merchant_Val = StringUtil.trimString(merchant_Array[(i + 4)]);
	          String merchant_Sub = StringUtil.trimString(merchant_Array[(i + 5)]);
	          mer_SignSb.append(merchant_Ver).append(merchant_Ser).append(merchant_Iss).append(merchant_Val).append(merchant_Sub);
	          if (!"Issuer=SAND".equalsIgnoreCase(merchant_Iss)) {
	            this.LastError = "商户证书发行方错误";
	            return false;
	          }

	          String[] merValArray = merchant_Val.split("=");
	          String[] merValTime = merValArray[1].split("-");
	          Date beginDate = StringUtil.getDate(merValTime[0] + "000000", "yyyyMMddHHmmss");
	          Date endDate = StringUtil.getDate(merValTime[1] + "235959", "yyyyMMddHHmmss");

	          if ((currDate.compareTo(beginDate) < 0) || (currDate.compareTo(endDate) > 0)) {
	            this.LastError = "商户证书不在有效期";
	            return false;
	          }
	        }
	        else if (("[Private Key Info]".equalsIgnoreCase(merchant_Array[i])) && (i + 3 < merchant_length)) {
	          String merchant_modulus = StringUtil.trimString(merchant_Array[(i + 1)]);
	          String merchant_pubExponent = StringUtil.trimString(merchant_Array[(i + 2)]);
	          String merchant_priExponent = StringUtil.trimString(merchant_Array[(i + 3)]);
	          mer_SignSb.append(merchant_modulus).append(merchant_pubExponent).append(merchant_priExponent);

	          String[] modulusArray = merchant_modulus.split("=");
	          String[] priExponentArray = merchant_priExponent.split("=");
	          byte[] key = SandAPICipher.getKey();
	          byte[] dnbyte = SandAPICipher.de3des(key, ByteUtil.hex2Bytes(modulusArray[1]));
	          byte[] dbyte = SandAPICipher.de3des(key, ByteUtil.hex2Bytes(priExponentArray[1]));

	          String dnHex = ByteUtil.bytes2Hex(dnbyte);
	          String dHex = ByteUtil.bytes2Hex(dbyte);

	          this.dn = new BigInteger(dnHex, 16);
	          this.d = new BigInteger(dHex, 16);
	        }
	        else if (("[Certificate Signature]".equalsIgnoreCase(merchant_Array[i])) && (i + 2 < merchant_length)) {
	          String merchant_Algorithm = StringUtil.trimString(merchant_Array[(i + 1)]);
	          String merchant_Sign = StringUtil.trimString(merchant_Array[(i + 2)]);
	          String[] merSignArray = merchant_Sign.split("=");
	          BigInteger re = new BigInteger(this.rsaHelp.getRootE());
	          BigInteger rn = new BigInteger(this.rsaHelp.getRootN(), 16);
	          boolean res = this.rsaHelp.verify(re, rn, mer_SignSb.toString(), merSignArray[1]);
	          if (!res) {
	            this.dn = null;
	            this.d = null;
	            return false;
	          }
	        }
	      }

	      StringBuffer sand_SignSb = new StringBuffer();
	      sand_SignSb.setLength(0);
	      int sand_length = sand_Array.length;

	      for (int i = 0; i < sand_length; i++) {
	        sand_Array[i] = StringUtil.trimString(sand_Array[i]);
	        if (("[Certificate Type]".equalsIgnoreCase(sand_Array[i])) && (i + 1 < sand_length)) {
	          String type = StringUtil.trimString(sand_Array[(i + 1)]);
	          if (!"Type=SAND.509".equalsIgnoreCase(type)) {
	            this.LastError = "杉德证书标准类型错误";
	            return false;
	          }
	        } else if (("[Certificate Data]".equalsIgnoreCase(sand_Array[i])) && (i + 5 < sand_length)) {
	          String sand_Ver = StringUtil.trimString(sand_Array[(i + 1)]);
	          String sand_Ser = StringUtil.trimString(sand_Array[(i + 2)]);
	          String sand_Iss = StringUtil.trimString(sand_Array[(i + 3)]);
	          String sand_Val = StringUtil.trimString(sand_Array[(i + 4)]);
	          String sand_Sub = StringUtil.trimString(sand_Array[(i + 5)]);
	          sand_SignSb.append(sand_Ver).append(sand_Ser).append(sand_Iss).append(sand_Val).append(sand_Sub);

	          if (!"Issuer=SAND".equalsIgnoreCase(sand_Iss)) {
	            this.LastError = "杉德证书发行方错误";
	            return false;
	          }

	          if (!"Subject=SAND".equalsIgnoreCase(sand_Sub)) {
	            this.LastError = "杉德证书颁发给的对象错误";
	            return false;
	          }
	        }
	        else if (("[Public Key Info]".equalsIgnoreCase(sand_Array[i])) && (i + 2 < sand_length)) {
	          String sand_modulus = StringUtil.trimString(sand_Array[(i + 1)]);
	          String sand_pubExponent = StringUtil.trimString(sand_Array[(i + 2)]);
	          sand_SignSb.append(sand_modulus).append(sand_pubExponent);

	          String[] sandModulusArray = sand_modulus.split("=");
	          String[] sandPubExponentArray = sand_pubExponent.split("=");
	          byte[] key = SandAPICipher.getKey();
	          byte[] enbyte = SandAPICipher.de3des(key, ByteUtil.hex2Bytes(sandModulusArray[1]));
	          String enHex = ByteUtil.bytes2Hex(enbyte);
	          this.e = new BigInteger(sandPubExponentArray[1]);
	          this.en = new BigInteger(enHex, 16);
	        }
	        else if (("[Certificate Signature]".equalsIgnoreCase(sand_Array[i])) && (i + 2 < sand_length)) {
	          String sand_Algorithm = StringUtil.trimString(sand_Array[(i + 1)]);
	          String sand_Sign = StringUtil.trimString(sand_Array[(i + 2)]);
	          String[] sandSignArray = sand_Sign.split("=");
	          BigInteger re = new BigInteger(this.rsaHelp.getRootE());
	          BigInteger rn = new BigInteger(this.rsaHelp.getRootN(), 16);
	          boolean res = this.rsaHelp.verify(re, rn, sand_SignSb.toString(), sandSignArray[1]);
	          if (!res) {
	            this.e = null;
	            this.en = null;
	            this.LastError = "杉德证书签名验证不通过";
	            return false;
	          }
	        }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      return false;
	    }
	    return true;
	  }

	  public boolean LoadKeyFile(String merId, String merPkFile, String sandPbkFile)
	  {
	    try {
	      byte[] merchant_b = FileUtil.getBytesFromFile(merPkFile);
	      byte[] sand_b = FileUtil.getBytesFromFile(sandPbkFile);
	      if ((merchant_b == null) || (sand_b == null)) {
	        this.LastError = "证书文件路径错误";
	        return false;
	      }
	      String merchant_Str = new String(merchant_b);
	      String sand_str = new String(sand_b);

	      String[] merchant_Array = merchant_Str.split("\r\n");
	      String[] sand_Array = sand_str.split("\r\n");
	      Date currDate = new Date();

	      StringBuffer mer_SignSb = new StringBuffer();
	      mer_SignSb.setLength(0);

	      int merchant_length = merchant_Array.length;
	      for (int i = 0; i < merchant_length; i++) {
	        merchant_Array[i] = StringUtil.trimString(merchant_Array[i]);
	        if (("[Certificate Type]".equalsIgnoreCase(merchant_Array[i])) && (i + 1 < merchant_length)) {
	          String type = StringUtil.trimString(merchant_Array[(i + 1)]);
	          if (!"Type=SAND.509".equalsIgnoreCase(type)) {
	            this.LastError = "商户证书标准类型错误";
	            return false;
	          }
	        } else if (("[Certificate Data]".equalsIgnoreCase(merchant_Array[i])) && (i + 5 < merchant_length)) {
	          String merchant_Ver = StringUtil.trimString(merchant_Array[(i + 1)]);
	          String merchant_Ser = StringUtil.trimString(merchant_Array[(i + 2)]);
	          String merchant_Iss = StringUtil.trimString(merchant_Array[(i + 3)]);
	          String merchant_Val = StringUtil.trimString(merchant_Array[(i + 4)]);
	          String merchant_Sub = StringUtil.trimString(merchant_Array[(i + 5)]);
	          mer_SignSb.append(merchant_Ver).append(merchant_Ser).append(merchant_Iss).append(merchant_Val).append(merchant_Sub);
	          if (!"Issuer=SAND".equalsIgnoreCase(merchant_Iss)) {
	            this.LastError = "商户证书发行方错误";
	            return false;
	          }

	          String[] merValArray = merchant_Val.split("=");
	          String[] merValTime = merValArray[1].split("-");
	          Date beginDate = StringUtil.getDate(merValTime[0] + "000000", "yyyyMMddHHmmss");
	          Date endDate = StringUtil.getDate(merValTime[1] + "235959", "yyyyMMddHHmmss");

	          if ((currDate.compareTo(beginDate) < 0) || (currDate.compareTo(endDate) > 0)) {
	            this.LastError = "商户证书不在有效期";
	            return false;
	          }
	          String[] merSubArray = merchant_Sub.split("=");
	          if (!merId.equalsIgnoreCase(merSubArray[1])) {
	            this.LastError = "商户号不匹配";
	            return false;
	          }
	        }
	        else if (("[Private Key Info]".equalsIgnoreCase(merchant_Array[i])) && (i + 3 < merchant_length)) {
	          String merchant_modulus = StringUtil.trimString(merchant_Array[(i + 1)]);
	          String merchant_pubExponent = StringUtil.trimString(merchant_Array[(i + 2)]);
	          String merchant_priExponent = StringUtil.trimString(merchant_Array[(i + 3)]);
	          mer_SignSb.append(merchant_modulus).append(merchant_pubExponent).append(merchant_priExponent);

	          String[] modulusArray = merchant_modulus.split("=");
	          String[] priExponentArray = merchant_priExponent.split("=");
	          byte[] key = SandAPICipher.getKey();
	          byte[] dnbyte = SandAPICipher.de3des(key, ByteUtil.hex2Bytes(modulusArray[1]));
	          byte[] dbyte = SandAPICipher.de3des(key, ByteUtil.hex2Bytes(priExponentArray[1]));

	          String dnHex = ByteUtil.bytes2Hex(dnbyte);
	          String dHex = ByteUtil.bytes2Hex(dbyte);

	          this.dn = new BigInteger(dnHex, 16);
	          this.d = new BigInteger(dHex, 16);
	        }
	        else if (("[Certificate Signature]".equalsIgnoreCase(merchant_Array[i])) && (i + 2 < merchant_length)) {
	          String merchant_Algorithm = StringUtil.trimString(merchant_Array[(i + 1)]);
	          String merchant_Sign = StringUtil.trimString(merchant_Array[(i + 2)]);
	          String[] merSignArray = merchant_Sign.split("=");
	          BigInteger re = new BigInteger(this.rsaHelp.getRootE());
	          BigInteger rn = new BigInteger(this.rsaHelp.getRootN(), 16);
	          boolean res = this.rsaHelp.verify(re, rn, mer_SignSb.toString(), merSignArray[1]);
	          if (!res) {
	            this.dn = null;
	            this.d = null;
	            return false;
	          }
	        }
	      }

	      StringBuffer sand_SignSb = new StringBuffer();
	      sand_SignSb.setLength(0);
	      int sand_length = sand_Array.length;

	      for (int i = 0; i < sand_length; i++) {
	        sand_Array[i] = StringUtil.trimString(sand_Array[i]);
	        if (("[Certificate Type]".equalsIgnoreCase(sand_Array[i])) && (i + 1 < sand_length)) {
	          String type = StringUtil.trimString(sand_Array[(i + 1)]);
	          if (!"Type=SAND.509".equalsIgnoreCase(type)) {
	            this.LastError = "杉德证书标准类型错误";
	            return false;
	          }
	        } else if (("[Certificate Data]".equalsIgnoreCase(sand_Array[i])) && (i + 5 < sand_length)) {
	          String sand_Ver = StringUtil.trimString(sand_Array[(i + 1)]);
	          String sand_Ser = StringUtil.trimString(sand_Array[(i + 2)]);
	          String sand_Iss = StringUtil.trimString(sand_Array[(i + 3)]);
	          String sand_Val = StringUtil.trimString(sand_Array[(i + 4)]);
	          String sand_Sub = StringUtil.trimString(sand_Array[(i + 5)]);
	          sand_SignSb.append(sand_Ver).append(sand_Ser).append(sand_Iss).append(sand_Val).append(sand_Sub);

	          if (!"Issuer=SAND".equalsIgnoreCase(sand_Iss)) {
	            this.LastError = "杉德证书发行方错误";
	            return false;
	          }

	          if (!"Subject=SAND".equalsIgnoreCase(sand_Sub)) {
	            this.LastError = "杉德证书颁发给的对象错误";
	            return false;
	          }
	        }
	        else if (("[Public Key Info]".equalsIgnoreCase(sand_Array[i])) && (i + 2 < sand_length)) {
	          String sand_modulus = StringUtil.trimString(sand_Array[(i + 1)]);
	          String sand_pubExponent = StringUtil.trimString(sand_Array[(i + 2)]);
	          sand_SignSb.append(sand_modulus).append(sand_pubExponent);

	          String[] sandModulusArray = sand_modulus.split("=");
	          String[] sandPubExponentArray = sand_pubExponent.split("=");
	          byte[] key = SandAPICipher.getKey();
	          byte[] enbyte = SandAPICipher.de3des(key, ByteUtil.hex2Bytes(sandModulusArray[1]));
	          String enHex = ByteUtil.bytes2Hex(enbyte);
	          this.e = new BigInteger(sandPubExponentArray[1]);
	          this.en = new BigInteger(enHex, 16);
	        }
	        else if (("[Certificate Signature]".equalsIgnoreCase(sand_Array[i])) && (i + 2 < sand_length)) {
	          String sand_Algorithm = StringUtil.trimString(sand_Array[(i + 1)]);
	          String sand_Sign = StringUtil.trimString(sand_Array[(i + 2)]);
	          String[] sandSignArray = sand_Sign.split("=");
	          BigInteger re = new BigInteger(this.rsaHelp.getRootE());
	          BigInteger rn = new BigInteger(this.rsaHelp.getRootN(), 16);
	          boolean res = this.rsaHelp.verify(re, rn, sand_SignSb.toString(), sandSignArray[1]);
	          if (!res) {
	            this.e = null;
	            this.en = null;
	            this.LastError = "杉德证书签名验证不通过";
	            return false;
	          }
	        }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      return false;
	    }
	    return true;
	  }

	  public boolean GenWorkKey()
	  {
	    try {
	      this.sandDesKey = this.sandDes.getDeskey();
	      if (this.sandDesKey != null)
	        return true;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return false;
	    }
	    return false;
	  }

	  public void SetWorkKey(byte[] key) {
	    try {
	      this.sandDesKey = key;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  public String decryptString(String s) {
	    try {
	      return new String(this.rsaHelp.decrypt(this.d, this.dn, s));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  public String EncryptWorkKey()
	  {
	    try {
	      return this.rsaHelp.encrypt(this.e, this.en, this.sandDesKey);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  public String EncryptData(String plainText)
	  {
	    return this.sandDes.encrypt(plainText, this.sandDesKey);
	  }

	  public String Sign(String data)
	  {
	    return this.rsaHelp.sign(this.d, this.dn, data);
	  }

	  public boolean DecryptWorkKey(String workKeyCipherText)
	  {
	    this.sandDesKey = this.rsaHelp.decrypt(this.d, this.dn, workKeyCipherText);
	    if (this.sandDesKey != null) {
	      return true;
	    }
	    return false;
	  }

	  public String DecryptData(String cipherText) {
	    return this.sandDes.decrypt(cipherText, this.sandDesKey);
	  }

	  public boolean VerifySign(String data, String sign) {
	    return this.rsaHelp.verify(this.e, this.en, data, sign);
	  }

	  public String signature(String data, String signtype) {
	    String sign = null;
	    try {
	      if (SD_SIGNTYPE_MD5RSA.equalsIgnoreCase(signtype))
	        sign = this.rsaHelp.sign(this.d, this.dn, data.getBytes("UTF-8"));
	      else if (SD_SIGNTYPE_SHA1HEX.equalsIgnoreCase(signtype))
	        sign = this.dig.sign(data.getBytes("UTF-8"), "SHA-1");
	      else if (SD_SIGNTYPE_MD5RSA.equalsIgnoreCase(signtype))
	        sign = this.dig.sign(data.getBytes("UTF-8"), "MD5");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return sign;
	  }

	  public boolean verify(String data, String signtype, String sign) {
	    boolean r = false;
	    try {
	      if (SD_SIGNTYPE_MD5RSA.equalsIgnoreCase(signtype))
	        r = this.rsaHelp.verify(this.e, this.en, data.getBytes("UTF-8"), sign);
	      else if (SD_SIGNTYPE_SHA1HEX.equalsIgnoreCase(signtype))
	        r = this.dig.verify(data.getBytes("UTF-8"), sign, "SHA-1");
	      else if (SD_SIGNTYPE_MD5RSA.equalsIgnoreCase(signtype))
	        r = this.dig.verify(data.getBytes("UTF-8"), sign, "MD5");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return r;
	  }

	  public String enc3des(String plainText, String key)
	  {
	    String cipherText = null;
	    try {
	      cipherText = this.sandDes.encr(plainText, ByteUtil.hex2Bytes(key));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return cipherText;
	  }

	  public String decr3des(String cipherText, String key)
	  {
	    String plainText = null;
	    try {
	      plainText = this.sandDes.decr(cipherText, ByteUtil.hex2Bytes(key));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return plainText;
	  }

	  public String getE() {
	    return this.e.toString(16)+"";
	  }

	  public String getN() {
	    return this.en.toString(16);
	  }

	  public String getD() {
	    return this.d.toString(16);
	  }

	  public String getDN() {
	    return this.dn.toString(16);
	  }

	  public static void main(String[] args) {
	    SandPayUtil sandPay = new SandPayUtil(false);

	    String plainText = "9999999999999999999";
	    String key = ByteUtil.bytes2Hex("123456780987654312345678".getBytes());
	    System.out.println("3des 密钥：" + key);

	    String cipherText = sandPay.enc3des(plainText, key);
	    System.out.println("3des 加密后的数据：" + cipherText);

	    System.out.println("3des 解密后的数据：" + sandPay.decr3des(cipherText, key));

	    boolean r = sandPay.LoadKeyFile("888002148160001", "D:/document/sand/sandpaykey/smp/PK_888002148160001_20121017170124.cer", "D:/document/sand/sandpaykey/smp/PBK_SAND_20110225151631.cer");

	    if (r) {
	      System.out.println("e=" + sandPay.getE());
	      System.out.println("en=" + sandPay.getN());
	      System.out.println("d=" + sandPay.getD());
	      System.out.println("dn=" + sandPay.getDN());
	    }
	  }
	
}
