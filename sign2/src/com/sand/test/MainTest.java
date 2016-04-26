package com.sand.test;

import com.sand.rsa.RSAUtil;

public class MainTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sign = "061B776D80917BA741C10283C4FBE6021353A6C9ABD1A8DA49FD9E619D0109D0C883F59EC794D770854AED50F3718CDE0414085DCB5466D98DC9D49394473CB94ACA6B35CE209077A416AA31710B0CDEBD4F5D5A7BCB100B387047E815772C4FC82811084809E5A30120D0A15DD3E51845B954BD1B2075BCE4090B71DAB460B1";
		String msg = "version=01&charset=UTF-8&trans_type=0002&resp_code=100000&resp_msg=³É¹¦&resp_time=20151120173733&revoked_no=&revoked_amount=&revoked_result=00&merchant_id=666002133330099&order_id=&order_amount=&currency=CNY&merchant_attach=";

		System.out.println(RSAUtil.checkSandJZBac(sign, msg));
	}

}
