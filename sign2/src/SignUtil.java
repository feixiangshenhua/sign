import java.math.BigInteger;

import com.sand.crypto.pay.SandPayUtil;
import com.sand.crypto.rsa.MessageDig;
import com.sand.crypto.rsa.RSAHelp;
import com.sand.crypto.secret.SandAPICipher;
import com.sand.crypto.util.ByteUtil;
import com.sand.crypto.util.FileUtil;
import com.sand.crypto.util.StringUtil;


public class SignUtil extends SandPayUtil{

	  public static String SD_SIGNTYPE_MD5HEX = "MD5HEX";
	  public static String SD_SIGNTYPE_SHA1HEX = "SHA1HEX";
	  public static String SD_SIGNTYPE_MD5RSA = "MD5RSA";

	  public String JAVA_VENDOR = "SUN_JVM";
	  private RSAHelp rsaHelp;
	  private MessageDig dig;
	  public String LastError = null;
	  private BigInteger e;
	  private BigInteger d;
	  private BigInteger en;
	  private BigInteger dn;
	  
	  public boolean LoadAcqKeyFile(String sandPbkFile) {
	    try {
	      byte[] sand_b = FileUtil.getBytesFromFile(sandPbkFile);
	      String sand_str = new String(sand_b);

	      String[] sand_Array = sand_str.split("\r\n");

	      StringBuffer mer_SignSb = new StringBuffer();
	      mer_SignSb.setLength(0);

	      StringBuffer sand_SignSb = new StringBuffer();
	      sand_SignSb.setLength(0);
	      int sand_length = sand_Array.length;

	      for (int i = 0; i < sand_length; i++) {
	        sand_Array[i] = StringUtil.trimString(sand_Array[i]);
	        if (("[Certificate Type]".equalsIgnoreCase(sand_Array[i])) && (i + 1 < sand_length)) {
	          String type = StringUtil.trimString(sand_Array[(i + 1)]);
	          if (!"Type=SAND.509".equalsIgnoreCase(type)) {
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
	            return false;
	          }

	          if (!"Subject=SAND".equalsIgnoreCase(sand_Sub)) {
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
}
