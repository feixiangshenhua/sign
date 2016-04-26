/**
 * 
 */
package com.weixin.pay.test;

import java.io.IOException;
import java.text.ParseException;

import com.weixin.pay.util.WeixinUtil;

/**
 *
 * 微信统一下单测试类
 * @author xiaoyun
 * @date 2015-12-22 下午2:14:34
 * @version 1.0.0
 *
 */
public class TestWeixinPayXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xmlData = "<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid><out_trade_no><![CDATA[1409811653]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id><time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
		try {
			WeixinUtil.doPostStr("http://localhost:8081/FAS/paynotice/weiXinAppNotice.action", xmlData);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

}