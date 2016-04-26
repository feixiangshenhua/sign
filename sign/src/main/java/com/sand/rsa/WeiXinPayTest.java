package com.sand.rsa;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * 
 * 模拟微信支付
 * @author xiaoyun
 *
 */
public class WeiXinPayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(">>>模拟微信支付<<<");
		System.out.println("==========华丽的分隔符==========");
		//微信api提供的参数
		String appid = "wxd930ea5d5a258f4f";
		String mch_id = "10000100";
		String device_info = "1000";
		String body = "test";
		String nonce_str = "ibuaiVcKdpRxkhJA";
		
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("device_info", device_info);
		parameters.put("body", body);
		parameters.put("nonce_str", nonce_str);
		
		String characterEncoding = "UTF-8";
		String mySign = createSign(characterEncoding,parameters);
		System.out.println("我     的签名是："+mySign);
	}

	/**
	 * 微信支付签名算法sign
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v) 
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		String Key = UUID.randomUUID().toString();
		sb.append("key=" + Key);
		String sign = MD5Util.MD5(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

}
