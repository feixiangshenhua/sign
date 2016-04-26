/**
 * 
 */
package com.sand.json.test;

import com.sand.json.po.OrderInfo;

import net.sf.json.JSONObject;

/**
 *
 * desc:
 * @author xiaoyun
 * @date 2016-2-22 上午10:19:54
 * @version 
 *
 */
public class JsonClone {
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("id", "aa");
		json.put("traceno", "bb");
		json.put("attach", "备注");
		OrderInfo oi = (OrderInfo) JSONObject.toBean(json, OrderInfo.class);
		System.out.println(oi.toString());
	}
}