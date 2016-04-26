/**
 * 
 */
package com.sand.json.test;

import net.sf.json.JSONObject;

/**
 *
 * desc:json测试
 * @author xiaoyun
 * @date 2016-1-25 下午3:45:25
 * @version 
 *
 */
public class Test2Json {
	public static void main(String[] args) {
		/**
		 * 变量名
			tranCode
			id
			memid
			mid
			oid
			transType
			cDate
			cTime
			oAmount
			currency
			cInfo
			mposAddress
			stat
			productId
			orderDesc
		 */
		JSONObject json = new JSONObject();
		json.put("tranCode"       ,"0003");
		json.put("id"             ,"160411A00000115");
		json.put("memid"          ,"24268");
		json.put("mid"            ,"888002199990020");
		json.put("oid"            ,"160411EG0000081");
		json.put("transType"      ,"0001");
		json.put("cDate"          ,"20160411");
		json.put("cTime"          ,"105900");
		json.put("oAmount"        ,"000000000103");
		json.put("currency"       ,"156");
		json.put("cInfo"          ,"UnionPay pre orders");
		json.put("mposAddress"    ,"上海宝石园");
		json.put("stat"           ,"00");
		json.put("productId"      ,"200010");
		json.put("orderDesc"      ,"收款-pay");
		System.out.println(json.toString());
	}
}
