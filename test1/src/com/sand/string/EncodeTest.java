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
		String msg = "{'idType':'0','cnaps':'102100004201','idValue':'372901855355863585','mcc':'5137','cityName':'������-������','bankName':'�й���������','idPic1Url':'23B23BE9DA2519C88F11C084310BCC0BF14417F8/199dd8e57f6bbc5d3ca1a0b582af76bfccfa4e07.jpg','cardHoldName':'Ф��','blPicUrl':'','midName':'Ф�Ʒ���','bankCardNo':'62122602000033456884','city':'100000','idPic2Url':'23B23BE9DA2519C88F11C084310BCC0BF14417F8/f876d5cd0377802fedb14099c7e8560065e0d534.jpg','busiLicense':'','address':'������˫Ȫ��','midContacts':'Ф��','mccName':'��װ��','paymentTypes':'04,06,01','ctsPhone':'18610370543','subBankName':'������¥֧��','memid':'23786','userId':'741'}";
		System.out.println(new String(msg.getBytes("utf8"), "utf8"));
		System.out.println(new String(msg.getBytes("utf8"), "utf8"));
	}

}
