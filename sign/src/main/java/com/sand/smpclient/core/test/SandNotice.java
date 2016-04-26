package com.sand.smpclient.core.test;

import com.sand.crypto.pay.SandPayUtil;
import com.sand.rsa.MD5Util;

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
       
		String sign = "45A5327AC89614ED8FE5337D89CC8A59A3D9AF6BFE3577B3209E776308817992842F7977594641D576F5EABCA1C823A109699CB64CB283E6C91B32B540A133A2B471758F28A1CE376547907C844A89FD32C81F4EDE487AF070377E535E6B12DC68DE7BA5CA2EEDAFF1C5FB4D15B3918B1936A2F84DF3767F0EF536E882693771";
		String msg = "version=01&charset=UTF-8&trans_type=0002&resp_code=100000&resp_msg=成功&resp_time=20151123152446&revoked_no=&revoked_amount=&revoked_result=00&merchant_id=888002159990131&order_id=&order_amount=&currency=CNY&merchant_attach=";
		System.out.println(MD5Util.MD5(msg, "utf8"));
		//SandPayUtil sandpay = new SandPayUtil(true);
		SandPayUtil sandpay = new SandPayUtil(true);
		boolean r = sandpay.LoadAcqKeyFile("E:PK_48022900_20151120160438.cer","E:PBK_SAND_20110322000410.cer");//商户id,商户私钥,杉德公钥
		//boolean r = sandpay.LoadAcqKeyFile("E:PK_48022900_20151120160438.cer","E:PBK_SAND_20110322000410.cer");//商户id,商户私钥,杉德公钥
		boolean res = false;
		System.out.println(r);		
		if(r){			
			System.out.println(sandpay.getE()+"\n"+sandpay.getN());
			res =  sandpay.verify(msg,SandPayUtil.SD_SIGNTYPE_MD5RSA,sign);
		}
		System.out.println("验签结果:"+res);
	}

}
