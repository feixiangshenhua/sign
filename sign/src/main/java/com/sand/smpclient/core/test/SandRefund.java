package com.sand.smpclient.core.test;

import java.io.File;

import com.sand.crypto.pay.SandPayUtil;

/**
 * 撤销申请(模拟签名)
 *
 */
public class SandRefund {

	
	public static void main(String[] args) {
		String pkPath = SandRefund.class.getResource("/").getPath()+File.separator+"PK_888002148160001_20121017170124.cer";
		String sign="5244FE528218334A69C63A0F4FDC7311A5E8E5F530E693544902F1CD8AA4F8C374209EA4C9B67B6FA941572247E1FCDF2E60F34EE0EFBE6BBC4B0665DAF7FAA83579E3E83DBA89C3DCDC9D9E5DB76B2DEFDBF8DAE6637EE0296C5B794D20841CEB272726F0E8C28B2DE1D819D80D800B8F0DE9D83ED3D173A2285A25409E4A03";
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
		SandPayUtil sandpay = new SandPayUtil(false);//false - 测试模式 true - 生产模式
		boolean r = sandpay.LoadAcqKeyFile(pkPath,"E:PBK_SAND_20110225151631.cer");
		System.out.println(r);
		System.out.println("原始签名串:"+buffer.toString());
		if(r){//加载密钥成功才能使用,否则无法签名
			//sign = sandpay.signature(buffer.toString(), SandPayUtil.SD_SIGNTYPE_MD5RSA);
			//System.out.println(sign);
			System.out.println(sandpay.verify(buffer.toString(), SandPayUtil.SD_SIGNTYPE_MD5RSA, sign));
		}
		//System.out.println("签名："+sign+"\n长度: "+sign.length());
	}
}
