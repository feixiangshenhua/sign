/**
 * 
 */
package com.weixin.pay.util;

import org.apache.commons.lang.StringUtils;

import com.weixin.pay.dto.UnifiedOrderResponseDto;

import net.sf.json.JSONObject;

/**
 *
 * desc:
 * @author Administrator
 * @date 2015-12-27 下午12:17:32
 * @version 
 *
 */
public class TestJson2Dto {
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("appid", "asdfasdf");
		json.put("mch_id", "");
		json.put("device_info", "asdfasdf");
		UnifiedOrderResponseDto dto = (UnifiedOrderResponseDto) JSONObject.toBean(json, UnifiedOrderResponseDto.class);
		System.out.println(StringUtils.isBlank(dto.getMch_id()));
	}
}
