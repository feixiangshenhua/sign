/**
 * 
 */
package com.sand.json.test;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import com.sand.mcs.pub.util.JSONUtils;

/**
 *
 * JSON转xml
 * @author Administrator
 * @date 2015-12-24 下午3:44:31
 * @version 
 *
 */
public class JSON2Xml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JSONObject json = JSONUtils.getNullJsonObject();
		JSONObject xml = JSONUtils.getNullJsonObject();
		xml.put("return_code", "<![CDATA[SUCCESS]]>");
		xml.put("return_msg", "<![CDATA[OK]]");
		json.put("xml", xml);
		String data = new XMLSerializer().write(xml);
		System.out.println(data);
	}

}
