package com.sand.util;

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
		String mess = "resp_msg=成功&sign_type=00&charset=UTF-8&order_id=2148160001201310173617065406&currency=156&version=01&"+
		              "sign=65FC9E71EB88308AC3D665D1B919ED21479FC2BF20E9A7EA9C4ED38ADAED471CA119FE41A4C5E1071F4F5331F389F47104C4AA6CB22C1D297838EE6E71F7B9AD9E1B303E357ABF24A1FBFCA1BEE5B52E76FADEDD29DF1F4F52CBEFE62149ADF5B80CCD5587F808A8A439FED89A012D082D9EFB6069C85AA7B876A30E8AA866DD"+
		              "&order_amount=000000000002&resp_time=20131017102027&resp_code=100000&merchant_attach=inner_id=12345678901234|lgn_id=CF1D14734D43DF7B&trans_type=0001&merchant_id=888002148160001";
       
		String sign="65FC9E71EB88308AC3D665D1B919ED21479FC2BF20E9A7EA9C4ED38ADAED471CA119FE41A4C5E1071F4F5331F389F47104C4AA6CB22C1D297838EE6E71F7B9AD9E1B303E357ABF24A1FBFCA1BEE5B52E76FADEDD29DF1F4F52CBEFE62149ADF5B80CCD5587F808A8A439FED89A012D082D9EFB6069C85AA7B876A30E8AA866DD";
		String charset="UTF-8";
		String version = "01";
		String trans_type="0001";
		String resp_code="100000";
		String resp_msg = "成功";
		String resp_time="20131017102027";
		String merchant_id="888002148160001";
		String order_id="2148160001201310173617065406";
		String order_amount="000000000002";
		String currency="156";
		String merchant_attach = "inner_id=12345678901234|lgn_id=CF1D14734D43DF7B";
		StringBuffer buffer = new StringBuffer();
		buffer.append("version").append("=").append(version).append("&")
		      .append("charset").append("=").append(charset).append("&")
		      .append("trans_type").append("=").append(trans_type).append("&")
		      .append("resp_code").append("=").append(resp_code).append("&")
		      .append("resp_msg").append("=").append(resp_msg).append("&")
		      .append("resp_time").append("=").append(resp_time).append("&")
		      .append("merchant_id").append("=").append(merchant_id).append("&")
		      .append("order_id").append("=").append(order_id).append("&")
		      .append("order_amount").append("=").append(order_amount).append("&")
		      .append("currency").append("=").append(currency).append("&")
		      .append("merchant_attach").append("=").append(merchant_attach);
		System.out.println(buffer.toString());
		SandPayUtil sandpay = new SandPayUtil(false);
		boolean r = sandpay.LoadAcqKeyFile("E:PK_888002148160001_20121017170124.cer","E:PBK_SAND_20110225151631.cer");//商户id,商户私钥,杉德公钥
		boolean res = false;
		System.out.println(r);
		if(r){
			res =  sandpay.verify(buffer.toString(),SandPayUtil.SD_SIGNTYPE_MD5RSA,sign);
		}
		System.out.println("验签结果:"+res);
	}

}
