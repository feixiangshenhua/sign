package com.sand.http.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 通过URLConnection进行http请求
 * 
 * @author xiaoyun 2015-10-14
 */
public class URLClient {
	public static void main(String[] args) {
		String getResp = get("https://www.baidu.com", "a=a");
		String postResp = post("http://www.cnblogs.com/", "a=a&name=xiaoyun", "utf8");
		System.out.println("get请求：" + getResp);
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
			out.print(params.getBytes("gbk"));
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
