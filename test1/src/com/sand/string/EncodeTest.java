/**
 * 
 */
package com.sand.string;

import java.io.UnsupportedEncodingException;

/**
 * TODO
 *
 * @author xiaoyun  2015-9-8 
 */
public class EncodeTest {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String msg = "{'idType':'0','cnaps':'102100004201','idValue':'372901855355863585','mcc':'5137','cityName':'北京市-北京市','bankName':'中国工商银行','idPic1Url':'23B23BE9DA2519C88F11C084310BCC0BF14417F8/199dd8e57f6bbc5d3ca1a0b582af76bfccfa4e07.jpg','cardHoldName':'肖云','blPicUrl':'','midName':'肖云饭店','bankCardNo':'62122602000033456884','city':'100000','idPic2Url':'23B23BE9DA2519C88F11C084310BCC0BF14417F8/f876d5cd0377802fedb14099c7e8560065e0d534.jpg','busiLicense':'','address':'海淀区双泉堡','midContacts':'肖云','mccName':'服装店','paymentTypes':'04,06,01','ctsPhone':'18610370543','subBankName':'北京黄楼支行','memid':'23786','userId':'741'}";
		System.out.println(new String(msg.getBytes("utf8"), "utf8"));
		System.out.println(new String(msg.getBytes("utf8"), "utf8"));
	}

}
