package com.sand.string;

public class AppendTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("a").append("=").append("c");
		System.out.println(sb.toString());
	}

}
