package com.sand.smpclient.core.test;

import com.sand.crypto.pay.SandPayUtil;
import com.sand.rsa.MD5Util;

/**
 * 验签
 * @author xiaoyun
 *
 */
public class SandNotice2 {

	/**
	 * 验签
	 */
	public static void main(String[] args) {		
		//收到通知数据
		String sign = "B969F702EA12156EE8C5B4A118FA16FD0F69A4D31C8EB73927CD70351146E6EAD95FEDFB4817F15E8773F90EED6D362D0B8051997E57143798712F362C4A01B86576401C17C7DD352F1C9586373DD7E79C2E16C8D171ECAF1389A6B8FACB997B8CD8E8B4958A83ACDAC47CF7CF9F5EA71D6E36E552FC1523D05587BD96DF4B10";
		String msg = "{\"respCode\":\"100000\",\"respResult\":\"成功\",\"mid\":\"888002159990131\",\"transNo\":\"15112401000000002028\",\"orderCode\":\"151124MO0000099\",\"orderAmt\":\"0.01\",\"currency\":\"156\",\"respTime\":\"20151124105058\"}";
		
		//msg = MD5Util.MD5(msg, "utf-8");
		System.out.println("msg md5:" + msg);
		com.sand.temp.SandPayUtil sandpay = new com.sand.temp.SandPayUtil(true);
		boolean r = sandpay.LoadAcqKeyFile("E:PK_48022900_20151120160438.cer","E:PBK_SAND_20110322000410.cer");//商户id,商户私钥,杉德公钥
		//boolean r = sandpay.LoadKeyFile(merchant_id, "E:PK_888002148160001_20121017170124.cer","E:PBK_SAND_20110225151631.cer");
		boolean res = false;
//		System.out.println(r);
		if(r){
			System.out.println("yuanshi:" + msg);
			System.out.println(sandpay.getE()+"\n"+sandpay.getN());
			res =  sandpay.verify(msg,SandPayUtil.SD_SIGNTYPE_MD5RSA, sign);
		}
		System.out.println("验签结果:"+res);
	}

}
