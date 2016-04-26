/**
 * 
 */
package com.weixin.pay.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.weixin.pay.dto.UnifiedOrderRequestDto;
import com.weixin.pay.util.WeixinUtil;


/**
 *
 * 微信统一下单测试类
 * @author xiaoyun
 * @date 2015-12-22 下午2:14:34
 * @version 1.0.0
 *
 */
public class TestWeixinPay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 生成随机数
		char[] arr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		String nonce_str = RandomStringUtils.random(30, arr);
		UnifiedOrderRequestDto dto = new UnifiedOrderRequestDto();
		dto.setAppid(WeixinUtil.APPID);
		dto.setAttach("上海杉德");
		dto.setNotify_url("http://query-test.sandpay.com.cn");
		dto.setMch_id("1230000109");
		dto.setNonce_str(nonce_str);
		dto.setBody("Ipad mini");
		dto.setOut_trade_no("20150806125346");
		dto.setTotal_fee("888");
		dto.setSpbill_create_ip("123.12.12.123");
		dto.setTrade_type("APP");
		dto.setOpenid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
		try {
			String sign = dto.calcSign(dto);
			dto.setSign(sign);
			/*XStream xStream = new XStream();
			xStream.alias("xml", UnifiedOrderRequestDto.class);
			String toWxData = xStream.toXML(dto);*/
			
			//System.out.println(toWxData);
			//xStream本身jar包存在漏洞			
			//toWxData = toWxData.replace("__", "_");
			XMLSerializer xmlSerializer = new XMLSerializer();
			String toWxData = xmlSerializer.write(JSONObject.fromObject(dto)).replace("<o>", "<xml>").replace("</o>", "</xml>").replace(" type=\"string\"", "");
			System.out.println(toWxData);
			/*String testXml = "<xml><appid>wx2421b1c4370ec43b</appid><attach>支付测试</attach><body>JSAPI支付测试</body><mch_id>10000100</mch_id><nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str><notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url><openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid><out_trade_no>1415659990</out_trade_no><spbill_create_ip>14.23.150.211</spbill_create_ip><total_fee>1</total_fee><trade_type>JSAPI</trade_type><sign>0CB01533B8C1EF103065174F50BCA001</sign></xml>";
			System.out.println(testXml);*/
			//JSONObject response = WeixinUtil.doPostStr(WeixinUtil.UNIFIEDORDER_URL, toWxData);
			//JSONObject respJson = WeixinUtil.doPostStr("http://localhost:8080/webtest/test.do", toWxData);
			//System.out.println(respJson.toString());
			//System.out.println(response.toString());		
			/*HttpEntity entity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {            	
            	String str = EntityUtils.toString(response.getEntity(),"utf-8");
            	System.out.println(str);
            	JSONObject respJson = (JSONObject) xmlSerializer.read(str);
            	System.out.println(respJson);
            	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                String text;
                while ((text = bufferedReader.readLine()) != null) {
                    System.out.println(text);
                }
               
            }*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
