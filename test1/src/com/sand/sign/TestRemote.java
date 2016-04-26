package com.sand.sign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sand.sign.util.CoffeeBean;

public class TestRemote {
	public static void main(String[] args) {
		//String url = "http://192.168.22.58:8080/payment/tradition/reveOrderSubmit.do";
		String cancelUrl = "http://192.168.22.58:8080/payment/tradition/orderCancelServlet.do";
		//String pollQueryOrderUrl = "http://192.168.22.58:8080/payment/tradition/pollingQueryOrder.do";
		sandBaoHttp(null, cancelUrl, 3);
		//sandBaoHttp(null, pollQueryOrderUrl, 2);
	}
	
	public static void sandBaoHttp(CoffeeBean coffee, String sandBaoUrl, int which) {
		try {
			URL url = new URL(sandBaoUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Accept-Charset", "utf-8");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			
			try {
				String params2 = "";
				switch(which) {
					case 1 : 
						params2 = "head={\"version\":\"ver1.0.0\",\"charset\":\"UTF-8\",\"accessChannelNo\":\"00000012\",\"accessType\":\"0003\",\"commType\":\"0001\",\"deviceType\":\"\",\"tranAttribute\":\"0\",\"responseCode\":\"\",\"traceNo\":\"11111111150713141210\",\"signType\":\"01\",\"securityInfo\":{\"Token\":\"14425fd31ac41e8f27e4b4792252018c595fa6024ee265772cc08ffa918e518153d988c58b72d3c3d7b5ab929d39d905d9aabd7b2527ea352cd72c883103c6cd557716bb9cffddb3e7c981580add40cac4df8c7726c1582c9c18df8c1cb69dff8980e1e68255217f52e16f22738a4026d50e249c548c1e1f6c254d22e13bd62c\"}}" +
								"&body={\"mid\": \"888002148160001\",\"shortName\": \"商户合作\",\"checkInOrg\": \"0001\",\"productId\": \"200010\",\"orderAmt\": \"000000000200\",\"currency\": \"156\",\"authCode\": \"336065041046179078\",\"goodsContent\": \"\",\"operType\": \"1\",\"operId\": \"-3\",\"attach\": \"\",\"posOrderId\": \"123456\"}";
						break;
					case 2 :
						params2 = "head={\"version\":\"ver1.0.0\",\"accessChannelNo\":\"00000012\",\"securityInfo\":{\"Token\":\"0586c51be65694aa1169c41dc5a23aa979922bb5e82957a58131cefc9d5377e137351d67eff9609cd6a14c4ffd867a1d06d1ef487f923f0d394f7047c7bac0f393c134a35e7537585ea5c1627c54abaeb89c2a53e7459c8eb1d26f76c75906036256757b119653e67331d620bd556c854a3fb325782626b5c70f84708c8e4b8c\""+
					"},\"signType\":\"01\"}&body={\"mid\":\"888002148160001\",\"orderAmt\":\"000000000100\"}";
						break;
					default :
						params2 = "version=01&charset=UTF-8&trans_type=0002&merchant_id=888002148160001&order_id=11111112150812101654&reason=&currency=CNY&revoked_time=20150812101429&back_url=&merchant_attach=&sign_type=00&sign=9A1D78ADB9DE5A9E3DCB54D28B4635FD1D3DD34BE10598A38CE4C941182B0A9A70244693D6D81B0EBED686A7D5F705A0B4C43A45C32DFA640A38F1546424EF2CEABC490491CA7B9F1E3141D7E80656F6E2CA642EB1C6F1A9C901140118F0C73E2DC43FC3BEBDCB83638FA0AEF2894FB5A6100ECDBC318F2BB803266C759C6F57";
						break;
				}
				/*String aaa = ImgHelper.encodeImage("D:\\a.png");
				params2 = "aaa="+aaa;*/
				/*if(which == 1) {
					// 浩哥  参数格式改为这样的 xiaoyun 2015-7-14
					params2 = "head={\"version\":\"ver1.0.0\",\"charset\":\"UTF-8\",\"accessChannelNo\":\"00000012\",\"accessType\":\"0003\",\"commType\":\"0001\",\"deviceType\":\"\",\"tranAttribute\":\"0\",\"responseCode\":\"\",\"traceNo\":\"11111111150713141210\",\"signType\":\"01\",\"securityInfo\":{\"Token\":\"0586c51be65694aa1169c41dc5a23aa979922bb5e82957a58131cefc9d5377e137351d67eff9609cd6a14c4ffd867a1d06d1ef487f923f0d394f7047c7bac0f393c134a35e7537585ea5c1627c54abaeb89c2a53e7459c8eb1d26f76c75906036256757b119653e67331d620bd556c854a3fb325782626b5c70f84708c8e4b8c\"}}" +
							"&body={\"mid\":\"888002148160001\",\"shortName\":\"商户合作\",\"checkInOrg\":\"0001\",\"productId\":\"200010\",\"orderAmt\":\"000000000001\",\"currency\":\"156\",\"authCode\":\"33587788\",\"goodsContent\":\"\",\"operType\":\"1\",\"operId\":\"-3\",\"attach\":\"\"}";
				}*/
				System.out.println("发送参数：" + params2);
				OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "utf-8");
				osw.write(params2);
				osw.flush();
				osw.close();
			} catch (Exception e) {
				e.printStackTrace();
				//coffee.setGuide(false);
				//coffee.setErrDesc(MyDic.any_httpConn);
				//return coffee;
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
