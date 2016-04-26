package com.sand.http.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class FASTest {
	public static void main(String[] args) {
		String params = "bizType=TransferAllChannel&bizInfo={\"channelType\":\"07\",\"acqInsCode\":\"48276900\",\"txnSubType\":\"01\",\"txnAmt\":\"000000000001\",\"version\":\"5.0.0\",\"signMethod\":\"01\",\"backUrl\":\"http://localhost:8080/backNotice.action\",\"merAbbr\":\"全渠道测试商户\",\"encoding\":\"UTF-8\",\"merCatCode\":\"5998\",\"encryptCertId\":\"87542682951161536912959476560149378562\",\"orderId\":\"20151028134931\",\"signature\":\"YnlqdkviEAOtRB4+npChaVVYKW5ZJxFHUbTTBq1LLOGYFezLEUpO+fp5VeeiAR/aOqzHYoghVqnavcIuD0dI/BpnXyQZ/uphmEbEcKXwxIoGIwJXK2lhln2LmgNDQCO8BnmPSc8msFAFEooB6+HJv2G9JO/n7flXTYNmleMeSmo=\",\"txnType\":\"01\",\"currencyCode\":\"156\",\"merId\":\"777290058112148\",\"customerInfo\":\"e3Bpbj0walVNWEtqZU14WXYwb1pmRWtCOFNOY29ScjlLOUp3MFdiNklTNTFnRXI5azJxQStNTHRnWGhXUy8zc2tmS1M4czV0U0h2VXVYRWdkc2tSSzNFNXpJTDZXNVdxbTViRG56UkttdTZTQVdFYlRHY3dMSnFnK3M1U01zcDQ3OWRaOVdRbFhLK0JQTkZ6U2hlanU3Z1QvMTJmdGdhaitnQWVMc0ptSVc2WmE1R0U9JmV4cGlyZWQ9R1dhc2V1Skxoc3hadTNuYkJWL2tCdkpsNWpjSjBHTXBSNllCNG94K2xtb2owVUtoODFTcmh4MzJnaVkrc3R6aERUR3RhR1M2dWVZNmM0bWlSUzJjcU0zR2N1N3pWYlRqMjBJdHpub29tK1o2NmpnMkg4NU1RYjM1VUltbXZ1MGlPTDFCMDRIbXpPTzBFZUEzd3RjT3ZrZE5sRzBSaGRUdjdLRXBwa1Vnb0xBPX0=\",\"accType\":\"01\",\"cardTransData\":\"{track2Data=XmnN5ssOrSycS/oq6a3l7dX7xecAgbQM2eQ9csL20Se0KuLgQ5c/4NB6P/PMNRoDKX8GjfLnt4ByUEQ8FbBz3/y0xNR/Yr0o5sjQrT0flhQAXFyHYCZ3X4Sw2IPwkweErpQWnr5MgbkhnxLTDB+DZ23kXKGZkVFqQiLf/xDNUoE2zDBhwEZYtecuAlieSLzHpk8U9ojgu48j2qvV4DOPYIHRhcLmliutvwuUxN5QAmCDz7eDt5ro9tSc17UQk6KgqJXo5us69/XlZel47SfYz37WQqFGNp+pOMeLzK6I6D2kVw4fzKeXXkKqRZD0SqJG4BAjAe1mB7g1QPnpQV3bJA==&carrierAppTp=3&carrierTp=0&ICCardData=9F2608B5795C33329023EF9F2701809F101307010103A0A802010A0100000000008FDB10559F3704A91D0DCE9F360200509505008004E0009A031510289C01009F02060000000000015F2A02015682027C009F1A0201569F03060000000000009F3303E0F9C89F34034203009F3501229F1E0836313031353037318408A0000003330101019F090200309F410400002061&ICCardSeqNumber=000}\",\"accNo\":\"6214830108050419\",\"merName\":\"全渠>道测试商户\",\"certId\":\"1445926840\",\"bizType\":\"000000\",\"accessType\":\"1\",\"txnTime\":\"20151028134931\"}";
		String fasUrl = "http://go-test.sandpay.com.cn:8095/FAS/merchant/transData.action";
		String postResp = post(fasUrl, params, "utf8");
		System.out.println("post请求：" + postResp);
	}
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
	public static String get(String url, String params) {
		String resp = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url+"?"+params);
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立连接
            connection.connect();
			// 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();          
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
            // 读取相应到流
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = in.readLine()) != null) {
            	resp += line;
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resp;
	}
	
	/** 
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
	public static String post(String url, String params, String charset) {
		String resp = "";
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送post请求需配置
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// 获取URLConnection对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			out.print(params.getBytes("utf8"));
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			String line;
			while((line = in.readLine()) != null) {
				resp += line;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
		}
		return resp;
	}
}
