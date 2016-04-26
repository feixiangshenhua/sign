package com.sand.http.test;

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
public class FASHttpClient {
	public static void main(String[] args) {
		//String getResp = httpGet("http://www.sina.com.cn", "utf-8");
		String params = "bizType=TransferAllChannel&bizInfo={\"channelType\":\"07\",\"acqInsCode\":\"48276900\",\"txnSubType\":\"01\",\"txnAmt\":\"000000000001\",\"version\":\"5.0.0\",\"signMethod\":\"01\",\"backUrl\":\"http://localhost:8080/backNotice.action\",\"merAbbr\":\"全渠道测试商户\",\"encoding\":\"UTF-8\",\"merCatCode\":\"5998\",\"encryptCertId\":\"87542682951161536912959476560149378562\",\"orderId\":\"20151028134931\",\"signature\":\"YnlqdkviEAOtRB4+npChaVVYKW5ZJxFHUbTTBq1LLOGYFezLEUpO+fp5VeeiAR/aOqzHYoghVqnavcIuD0dI/BpnXyQZ/uphmEbEcKXwxIoGIwJXK2lhln2LmgNDQCO8BnmPSc8msFAFEooB6+HJv2G9JO/n7flXTYNmleMeSmo=\",\"txnType\":\"01\",\"currencyCode\":\"156\",\"merId\":\"777290058112148\",\"customerInfo\":\"e3Bpbj0walVNWEtqZU14WXYwb1pmRWtCOFNOY29ScjlLOUp3MFdiNklTNTFnRXI5azJxQStNTHRnWGhXUy8zc2tmS1M4czV0U0h2VXVYRWdkc2tSSzNFNXpJTDZXNVdxbTViRG56UkttdTZTQVdFYlRHY3dMSnFnK3M1U01zcDQ3OWRaOVdRbFhLK0JQTkZ6U2hlanU3Z1QvMTJmdGdhaitnQWVMc0ptSVc2WmE1R0U9JmV4cGlyZWQ9R1dhc2V1Skxoc3hadTNuYkJWL2tCdkpsNWpjSjBHTXBSNllCNG94K2xtb2owVUtoODFTcmh4MzJnaVkrc3R6aERUR3RhR1M2dWVZNmM0bWlSUzJjcU0zR2N1N3pWYlRqMjBJdHpub29tK1o2NmpnMkg4NU1RYjM1VUltbXZ1MGlPTDFCMDRIbXpPTzBFZUEzd3RjT3ZrZE5sRzBSaGRUdjdLRXBwa1Vnb0xBPX0=\",\"accType\":\"01\",\"cardTransData\":\"{track2Data=XmnN5ssOrSycS/oq6a3l7dX7xecAgbQM2eQ9csL20Se0KuLgQ5c/4NB6P/PMNRoDKX8GjfLnt4ByUEQ8FbBz3/y0xNR/Yr0o5sjQrT0flhQAXFyHYCZ3X4Sw2IPwkweErpQWnr5MgbkhnxLTDB+DZ23kXKGZkVFqQiLf/xDNUoE2zDBhwEZYtecuAlieSLzHpk8U9ojgu48j2qvV4DOPYIHRhcLmliutvwuUxN5QAmCDz7eDt5ro9tSc17UQk6KgqJXo5us69/XlZel47SfYz37WQqFGNp+pOMeLzK6I6D2kVw4fzKeXXkKqRZD0SqJG4BAjAe1mB7g1QPnpQV3bJA==&carrierAppTp=3&carrierTp=0&ICCardData=9F2608B5795C33329023EF9F2701809F101307010103A0A802010A0100000000008FDB10559F3704A91D0DCE9F360200509505008004E0009A031510289C01009F02060000000000015F2A02015682027C009F1A0201569F03060000000000009F3303E0F9C89F34034203009F3501229F1E0836313031353037318408A0000003330101019F090200309F410400002061&ICCardSeqNumber=000}\",\"accNo\":\"6214830108050419\",\"merName\":\"全渠>道测试商户\",\"certId\":\"1445926840\",\"bizType\":\"000000\",\"accessType\":\"1\",\"txnTime\":\"20151028134931\"}";
		String cnblogs = "http://www.cnblogs.com/";
		String fas = "http://go-test.sandpay.com.cn:8095/FAS/merchant/transData.action";
		String postResp = httpPost(params, fas, "utf-8");
		//System.out.println("get请求：" + getResp);
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
