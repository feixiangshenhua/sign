package com.sand.http.url;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 使用apache的httpClient发送http请求
 * 
 * @author xiaoyun 2015-10-14
 */
public class ApacheHttpClient {
	public static void main(String[] args) {
		String getResp = httpGet("http://www.sina.com.cn", "utf-8");
		String postResp = httpPost("a=a", "http://www.cnblogs.com/", "utf-8");
		System.out.println("get请求：" + getResp);
		System.out.println("post请求：" + postResp);
	}
	
	public static String httpGet(String url,String charset) {
		String httpResult = "";
		AbstractHttpClient httpclient = null;
		try{
			HttpGet httpRequest = new HttpGet(url);
			
		    // 设置 超时机制 毫秒为单位,重连3机制
            HttpParams params = new BasicHttpParams();  
            HttpConnectionParams.setConnectionTimeout(params, 30 * 1000);  
            HttpConnectionParams.setSoTimeout(params, 45 * 1000);    
            HttpConnectionParams.setTcpNoDelay(params, true);
            HttpClientParams.setRedirecting(params, false);
       
            httpclient = new DefaultHttpClient(params); 
            HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(3,true);
            httpclient.setHttpRequestRetryHandler(retryHandler);
            
            
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//请求成功 
				httpResult = EntityUtils.toString(httpResponse.getEntity(),charset);
				
			}else{
				int statusCode = httpResponse.getStatusLine().getStatusCode() ;	
			}
		}catch(Exception e){
		}finally{
			if(httpclient != null)httpclient.getConnectionManager().shutdown();  
		}
		return httpResult;
	}

	public static String httpPost(String paramContent, String url,String charset) {
		String httpResult = "";
		AbstractHttpClient httpclient = null;
		try{
			HttpPost httpRequest = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>(); 
			String[] paramValues = paramContent.split("&");
			for (int i = 0; i < paramValues.length; i++) {
				String[] paramValue = paramValues[i].split("=");
				if(paramValue != null && paramValue.length == 2){
					params.add(new BasicNameValuePair(paramValue[0], paramValue[1]));					
				}
			}
			
			HttpEntity httpentity = new UrlEncodedFormEntity(params, charset);
			httpRequest.setHeader("accept", "*/*");
			httpRequest.setHeader("connection", "Keep-Alive");
			httpRequest.setEntity(httpentity);
			
			//设置超时 毫秒为单位,重连机制
			HttpParams httpParams = new BasicHttpParams();  
	        HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);  
	        HttpConnectionParams.setSoTimeout(httpParams, 45 * 1000);    
	        HttpConnectionParams.setTcpNoDelay(httpParams, true);
	        HttpClientParams.setRedirecting(httpParams, false);
	        
		    httpclient = new DefaultHttpClient(httpParams);
		    HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(3,true);
            httpclient.setHttpRequestRetryHandler(retryHandler);
		   
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			System.out.println(httpResponse.getStatusLine().getStatusCode());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//HttpStatus.SC_OK表示连接成功
				 httpResult = EntityUtils.toString(httpResponse.getEntity(),charset);
			}else{
				 int statusCode = httpResponse.getStatusLine().getStatusCode() ;
				 System.out.println("错误码：" + statusCode);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(httpclient != null)httpclient.getConnectionManager().shutdown();  
		}
		return httpResult;
	} 

}
