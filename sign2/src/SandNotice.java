

import com.sand.crypto.pay.SandPayUtil;

/**
 * 验签
 * @author xiaoyun
 *
 */
public class SandNotice {

	/**
	 * 验签
	 */
	public static void main(String[] args) {
		//收到通知数据
       
		String sign = "061B776D80917BA741C10283C4FBE6021353A6C9ABD1A8DA49FD9E619D0109D0C883F59EC794D770854AED50F3718CDE0414085DCB5466D98DC9D49394473CB94ACA6B35CE209077A416AA31710B0CDEBD4F5D5A7BCB100B387047E815772C4FC82811084809E5A30120D0A15DD3E51845B954BD1B2075BCE4090B71DAB460B1";
		String msg = "version=01&charset=UTF-8&trans_type=0002&resp_code=100000&resp_msg=成功&resp_time=20151120173733&revoked_no=&revoked_amount=&revoked_result=00&merchant_id=666002133330099&order_id=&order_amount=&currency=CNY&merchant_attach=";
		SandPayUtil sandpay = new SandPayUtil(true);
		boolean r = sandpay.LoadAcqKeyFile("E:PK_888002148160001_20121017170124.cer","E:PBK_SAND_20110225151631.cer");//商户id,商户私钥,杉德公钥
		boolean res = false;
		System.out.println(r);
		if(r){
			res =  sandpay.verify(msg,SandPayUtil.SD_SIGNTYPE_MD5RSA,sign);
			System.out.println(sign);
		}
		System.out.println("验签结果:"+res);
	}

	//收到通知数据
	/**
	 * version=01&charset=UTF-8&trans_type=0002&resp_code=100000&resp_msg=成功&resp_time=20151120173733&revoked_no=&revoked_amount=&revoked_result=00&merchant_id=666002133330099&order_id=&order_amount=&currency=CNY&merchant_attach=
	 * &sign_type=00&sign=061B776D80917BA741C10283C4FBE6021353A6C9ABD1A8DA49FD9E619D0109D0C883F59EC794D770854AED50F3718CDE0414085DCB5466D98DC9D49394473CB94ACA6B35CE209077A416AA31710B0CDEBD4F5D5A7BCB100B387047E815772C4FC82811084809E5A30120D0A15DD3E51845B954BD1B2075BCE4090B71DAB460B1
	 */
/**
 * 
	String sign = "061B776D80917BA741C10283C4FBE6021353A6C9ABD1A8DA49FD9E619D0109D0C883F59EC794D770854AED50F3718CDE0414085DCB5466D98DC9D49394473CB94ACA6B35CE209077A416AA31710B0CDEBD4F5D5A7BCB100B387047E815772C4FC82811084809E5A30120D0A15DD3E51845B954BD1B2075BCE4090B71DAB460B1";
	String msg = "version=01&charset=UTF-8&trans_type=0002&resp_code=100000&resp_msg=成功&resp_time=20151120173733&revoked_no=&revoked_amount=&revoked_result=00&merchant_id=666002133330099&order_id=&order_amount=&currency=CNY&merchant_attach=";
	
	SandPayUtil sandpay = new SandPayUtil(false);
	boolean r = sandpay.LoadAcqKeyFile("E:PK_888002148160001_20121017170124.cer","E:PK_48022900_20151120160438.cer");//商户id,商户私钥,杉德公钥
	//boolean r = sandpay.LoadKeyFile(merchant_id, "E:PK_888002148160001_20121017170124.cer","E:PBK_SAND_20110225151631.cer");
	boolean res = false;
	msg = MD5.getMD5(msg);
	System.out.println(r);
	if(r){
		res =  sandpay.verify(msg,SandPayUtil.SD_SIGNTYPE_MD5RSA,sign);
		System.out.println(sign);
	}
	System.out.println("验签结果:"+res);

 */
}
