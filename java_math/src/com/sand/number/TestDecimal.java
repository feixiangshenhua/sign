/**
 * 
 */
package com.sand.number;

import java.math.BigInteger;

/**
 *
 * 数值类测试
 * @author xiaoyun
 * @date 2016-1-13 下午2:00:27
 * @version 1.0.0
 *
 */
public class TestDecimal {
	public static void main(String[] args) {
		String modulus = "D3FCD87975B5A0CDD128D98677AF51FE978D8BFC1256D1B437CA4AC8194B72BD5BC9A188944E5E40E2664E1607929D5401EB4B7ABACC7CDA22612AA94D74E43ED715AE0691B9E90195580D0F2C628DA8DB0BD2F7C8848C353507CDE4FEDE4108BDA06B2C9A6E334FC1FC9C42C973AB80CBD32BD62999D339739F2AF029B2467B";
		BigInteger b1 = new BigInteger(modulus);
		System.out.println(b1);
	}
}
