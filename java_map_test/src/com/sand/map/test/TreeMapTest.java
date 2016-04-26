/**
 * 
 */
package com.sand.map.test;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 *
 * desc:
 * @author Administrator
 * @date 2015-12-24 下午2:58:05
 * @version 
 *
 */
public class TreeMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("c", "cc");
		map.put("xiaoyun", "sdfasdf");
		map.put("a", "aa");
		map.put("b", "bb");
		map.put("d", "dd");
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}

}
