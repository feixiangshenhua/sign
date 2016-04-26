/**
 * 
 */
package com.sand.map.test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * desc:哈希表测试
 * @author xiaoyun
 * @date 2016-2-3 上午11:14:39
 * @version 1.0.0
 *
 */
public class HashMapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, null);
		map.put(null, null);
		System.out.println(map.size());
	}
}
