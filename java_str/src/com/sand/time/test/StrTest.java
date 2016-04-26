/**
 * 
 */
package com.sand.time.test;

/**
 *
 * 时间
 * @author xiaoyun
 * @date 2015-12-27 下午2:26:40
 * @version 1.0.0
 *
 */
public class StrTest {
	public static void main(String[] args) {
		/*String st = "asdasda_bbbbbbaaa_cccc";
		System.out.println(st.substring(0,st.indexOf("_")));
		boolean isOk = st.indexOf("_")>0;
		System.out.println(st.indexOf("_"));
		System.out.println(isOk);*/
		Foo foo = new Foo();
		foo.setA("aaa");
		foo.setB("bbbb");
		String a = foo.getA();
		String cc = a;
		a = "cc";
		foo.setA("ccc");
		System.out.println(foo.getA()+","+a+","+cc);
		String s = "http://query-test.sandpay.com.cn/qrshow.jsp?qrcode=bWlkPTg4ODAwMjE5OTk5MDAwMSZvcmRlcklkPTE2MDEyME1PMDAwMTkyMiZwcm9kdWN0SWQ9MjAwMDIwJm9yZGVyQW10PTAwMDAwMDAwMDAwMQ==";
		System.out.println(s.length());
	}
}
