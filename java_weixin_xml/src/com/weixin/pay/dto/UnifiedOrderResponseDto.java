/**
 * 
 */
package com.weixin.pay.dto;

import java.io.Serializable;

/**
 * 
 * 微信统一下单响应数据
 * 
 * @author xiaoyun
 * @date 2015-12-27 下午12:14:36
 * @version 1.0.0
 * 
 */
public class UnifiedOrderResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 125782719689576427L;

	private String appid;
	private String mch_id;
	private String device_info;
	private String nonce_str;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	@Override
	public String toString() {
		return "UnifiedOrderResponseDto [appid=" + appid + ", mch_id=" + mch_id
				+ ", device_info=" + device_info + ", nonce_str=" + nonce_str
				+ "]"+serialVersionUID;
	}
	
}
