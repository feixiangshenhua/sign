/**
 * 
 */
package com.sand.time.test;

/**
 *
 * @author xiaoyun
 * @date 2016-2-23 下午3:08:49
 * @version 1.0.0
 *
 */
public class SubStr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "6216261000000000018|平安银行                      ";
		String s2 = "asbdfsaf$asaa|asssd";
		testSplit(s2, "\\$");
		/*String[] arr = s.split("\\|");
		for (String s1 : arr) {
			System.out.println(s1);
		}*/

	}
	
	public static void testSplit(String str, String reg) {
		String[] arr = str.split(reg);
		for (String s1 : arr) {
			System.out.println(s1);
		}
	}

}
