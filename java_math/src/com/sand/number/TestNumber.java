/**
 * 
 */
package com.sand.number;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

/**
 *
 * 数字测试
 * @author xiaoyun
 * @date 2015-12-25 下午3:17:00
 * @version 
 *
 */
public class TestNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//BigDecimal big = new BigDecimal(2.1);
		//System.out.println(big.subtract(new BigDecimal(0.00)).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
		//String s = big.subtract(new BigDecimal(0.00)).setScale(2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_DOWN).toString();
		//String dec = changeAmount2("20.0");
		//System.out.println(dec);
		String payAmt = "000000000000";
		System.out.println(new BigDecimal(payAmt).multiply(new  BigDecimal("0.01")).setScale(2, BigDecimal.ROUND_HALF_DOWN));
		//System.out.println(s);
		//System.out.println(new BigDecimal("7.2").multiply(new  BigDecimal("0.01")).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}
	
	private static String changeAmount(String amount){
		String payment="";
		if(amount.indexOf(".")==-1){
			payment = StringUtils.leftPad(amount+"00",0,'0');
		}else{
			String s = amount.split("\\.")[1];
			if(s.length()==1){
				payment = amount.replace(".", "")+"0";
			}else{
				payment = amount.replace(".", "");
			}
		}
		return payment;
	} 
	
	private static String changeAmount2(String amount){
		String payment="";
		if(amount.indexOf(".")==-1){
			payment = StringUtils.leftPad(amount,12,'0');
		}else{
			String s = amount.split("\\.")[1];
			if(s.length()==1){
				payment = StringUtils.leftPad(amount.replace(".", "")+"0",12,'0');
			}else{
				payment = StringUtils.leftPad(amount.replace(".", ""),12,'0');
			}
		}
		return payment;
	} 


}
