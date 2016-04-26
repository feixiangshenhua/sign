/**
 * 
 */
package com.sand.number;

import java.math.BigDecimal;

/**
 *
 * desc:
 * @author Administrator
 * @date 2016-4-11 下午4:00:27
 * @version 
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigDecimal amount = new BigDecimal(10);
		//System.out.println(num.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
		System.out.println(amount.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
	}

}
