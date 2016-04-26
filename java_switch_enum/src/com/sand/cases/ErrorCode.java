/**
 * 
 */
package com.sand.cases;

/**
 *
 * 枚举测试
 * @author xiaoyun
 * @date 2015-12-29 下午5:11:55
 * @version 1.0.0
 *
 */
public enum ErrorCode {
	FAIL("失败"),SUCCESS("成功"),NOAUTH("商户无此接口权限");
	
	private String value;
	
	private ErrorCode(String value){
		this.value = value;
	}
	
	public static ErrorCode getErrorCode(String errorCode) {
		return valueOf(errorCode);
	}
	
	public String getValue() {
		return this.value;
	}
}
