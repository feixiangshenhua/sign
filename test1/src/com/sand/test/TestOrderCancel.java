/**
 * 
 */
package com.sand.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

/**
 * TODO 订单撤销测试
 *
 * @author xiaoyun  2015-8-12 
 */
public class TestOrderCancel {
	
	
	@Test 
	public void testGold() {
		HttpURLConnection conn = null;
		try {
			//String params = "version=01&charset=UTF-8&trans_type=0002&merchant_id=888002148160001&pos_order_id=11111112150812163330&reason=&order_amount=000000011111&currency=156&revoked_time=20150812163145&merchant_attach=" +
			//		"&sign_type=00&sign=390826A4D5A7FDA1EA7424678B23EF0D2E7370863B928C9B65716B841C5428D3D3F3CF2CA4D5A5B7FAA566E0E8679E145934BD38D94D7C8D0C035B02E5ECEBEF2D4975B7B7C96D9931C2349407896A8F7140C35A3A4637C23D13EA711E86C03D2067D09E8CA18C4D9FBCB9B3203A766109A8DFFCD9B585C0AC40E61E8CB5AD59";
			String params = "sign=966673528F180B16F1A61D5D0490CB08012A98C6D1A9A3BC8A42C58FD8D3BC69F5FEE5430D1D8C2BCCF9BC36FA86F386B7B32EE9B6C8DBDE0B7B09F72D9634C7D6E672FAAB8A39B24C9417275EC45935F2F3A7006A9E37FD669B4C94A9C403B8160C55EEE350630369118239003AC1BBAE4BDC9357D7AA0C35607186D6200CFD&order_amount=&reason=&pos_order_id=11111112150818173546&sign_type=00&revoked_time=20150818173408&charset=UTF-8&merchant_attach=&trans_type=0002&merchant_id=888002148160001&currency=CNY&version=01";
			String cancelUrl = "http://192.168.22.58:8080/payment/tradition/orderCancelServlet.do";
			URL url = new URL(cancelUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			System.out.println("发送参数：" + params);
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
			osw.write(params);
			osw.flush();
			osw.close();
			
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
			System.out.println("杉德宝返回参数:"+new String(buffer.toString().getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.disconnect();
			}
		}
	}

	@Test
	public void testOld() {
		String params = "version=01&charset=UTF-8&trans_type=0002&merchant_id=888002148160001&order_id=11111112150812163330&reason=&order_amount=000000011111&currency=156&revoked_time=20150812163145&back_url=https://www.baidu.com&merchant_attach=&sign_type=00&sign=441CF8DE1CD597D18595E39614BD245E7887002C96FC596505B56EA24FF2F1A4D3E852FE87D13A1B8D99EA404CEA781C744469C96F5069DD2BC98474EE4F05915B111EF29430EF26C0422144B567F7865032CFAB943CD438F359D0E473ABFF60DA8BBD1FB1570EFF5F90C5693EBD83109E12DF0491338D543A07C51292CCFBA7";
		String cancelUrl = "http://192.168.22.58:8080/payment/tradition/orderCancelServlet.do";
		try {
			URL url = new URL(cancelUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Accept-Charset", "utf-8");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			
			try {
				System.out.println("发送参数：" + params);
				OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "utf-8");
				osw.write(params);
				osw.flush();
				osw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					con.disconnect();
				}
			}
			try {
				StringBuffer buffer = new StringBuffer();
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String temp;
				while ((temp = br.readLine()) != null) {
					buffer.append(temp);
					buffer.append("\n");
				}
				// 返回的参数这么样来解析 xiaoyun 2015-7-14
				System.out.println("杉德宝返回参数:"+new String(buffer.toString().getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	

	}

}
