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
 * ͨ��URLConnection����http����
 * 
 * @author xiaoyun 2015-10-14
 */
public class URLClient {
	public static void main(String[] args) {
		String getResp = get("https://www.baidu.com", "a=a");
		String postResp = post("http://www.cnblogs.com/", "a=a&name=xiaoyun", "utf8");
		System.out.println("get����" + getResp);
		System.out.println("post����" + postResp);
	}
	
	/**
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return URL ������Զ����Դ����Ӧ���
     */
	public static String get(String url, String params) {
		String resp = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url+"?"+params);
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ��������
            connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();          
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
            // ��ȡ��Ӧ����
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
     * ��ָ�� URL ����POST����������
     * 
     * @param url
     *            ��������� URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return ������Զ����Դ����Ӧ���
     */
	public static String post(String url, String params, String charset) {
		String resp = "";
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// ������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����post����������
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// ��ȡURLConnection��Ӧ�������
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
