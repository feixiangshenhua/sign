package com.weixin.pay.util;

import java.io.IOException;
import java.text.ParseException;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 微信工具类
 * @author xiaoyun 2015-12-22
 * @date 2015-12-22 下午1:48:19
 * @version 1.0.0
 *
 */
public class WeixinUtil {
	public static final String APPID = "wxdd76438574cc239e";
	public static final String APPSECRET = "b882d23fedaa93f286bc7d6d5f90c82b";
	
	public static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}
	
	/**
	 * POST请求
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		HttpResponse response = client.execute(httpost);
		String str = EntityUtils.toString(response.getEntity(),"utf-8");
		System.out.println("微信统一下单返回的数据：" + str);
		XMLSerializer xmlSerializer = new XMLSerializer();
    	JSONObject respJson = (JSONObject) xmlSerializer.read(str);
    	return respJson;
	}
		
}