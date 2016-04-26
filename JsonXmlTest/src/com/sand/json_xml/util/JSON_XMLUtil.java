/**
 * 
 */
package com.sand.json_xml.util;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 *
 * JSON,XML测试工具类
 * @author xiaoyun
 * @date 2015-12-27 上午11:49:13
 * @version 1.0.0
 *
 */
public class JSON_XMLUtil {
	public static void xml2Json() {
		String str = "<o><appid>wxdd76438574cc239e</appid><attach>上海杉德</attach><body>Ipad mini</body><detail/><device_info/><fee_type/><goods_tag/><limit_pay/><mch_id>1230000109</mch_id><nonce_str>DAF0uqlH4vh5j6rPRRrqKnaGTxvWuJ</nonce_str><notify_url>http://query-test.sandpay.com.cn</notify_url><openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid><out_trade_no>20150806125346</out_trade_no><product_id/><sign>A74E2B88375FFBE347ABDD739CAA12F3</sign><spbill_create_ip>123.12.12.123</spbill_create_ip><time_expire/><time_start/><total_fee>888</total_fee><trade_type>APP</trade_type></o>";
		XMLSerializer serializer = new XMLSerializer();
		JSONObject json = (JSONObject) serializer.read(str);
		System.out.println(json.toString());
	}
	
	public static void main(String[] args) {
		xml2Json();
	}
}
