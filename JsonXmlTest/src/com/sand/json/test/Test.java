package com.sand.json.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) {
		/*Map<String, String> paramMap = new HashMap<>();
		for(int i = 0; i < 9; i++) {
			paramMap.put(i+"", i+"");
		}
		Map<String, Object> kvMap = new HashMap<String, Object>(paramMap);
		for (Entry<String, Object> entry : kvMap.entrySet()) {
			System.out.println(entry.getKey()+","+entry.getValue());
		}*/
		/*JSONObject json = new JSONObject();
		json.put("a", "a");
		json.put("b", "b");
		json.put("c", "c");
		//System.out.println(json.remove("c"));
		Object o = json.get("ddd");
		System.out.println(o);
		for(Object key : json.keySet()){
			System.out.println(key+":"+json.get(key));
		}*/
		
		//Map<Object, Object> temp = new HashMap<Object, Object>(json);
		/*for (Entry<Object, Object> entry : temp.entrySet()) {
			System.out.println(entry.getKey()+","+entry.getValue());
		}*/
		JSONObject playload = new JSONObject();
		playload.put("aaa", "aaaa");
		playload.put("bbb", "bbbb");
		JSONObject playhead = new JSONObject();
		playhead.put("ccc", "cccc");
		playhead.put("ddd", "dddd");
		
		Set loadKeys = playload.keySet();
		Set headKeys = playhead.keySet();
		List<String> keys = new ArrayList<String>();
		for (Object k : headKeys) {			
			keys.add(k+"");
		}
		for (Object k : loadKeys) {			
			keys.add(k+"");
		}
		String[] keyArr = new String[keys.size()]; 
		keys.toArray(keyArr);
		Arrays.sort(keyArr);
		System.out.println(Arrays.toString(keyArr));
		//testJson();
	}
	
	public static void testJson() {
		Foo foo = new Foo();
		foo.setId("111");
		foo.setName("xiaoyun");
		foo.setPhoneNo("12245646799");
		JSONObject fooJson = JSONObject.fromObject(foo);
		System.out.println(fooJson.toString());		
	}
}
