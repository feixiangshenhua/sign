/**
 * 
 */
package com.sand.string;


/**
 * TODO
 *
 * @author xiaoyun  2015-9-1 
 */
public class CharCode {
	public static void main(String[] args) throws Exception {
		String t = "hfjkds中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国hfsdkj<img src='sasa' /> fjldsajflkdsjaflkdsjalf <img src='sada' ait=''/>sfdsfadas";  
		//思路：先转为Unicode，然后转为GBK
		String utf8 = new String(t.getBytes( "UTF-8"));  
		System.out.println(utf8);  
		String unicode = new String(utf8.getBytes(),"UTF-8");   
		System.out.println(unicode);  
		String gbk = new String(unicode.getBytes("GBK"));  
		  
		System.out.println(gbk);  
	}
}
