/**
 * 
 */
package com.weixin.pay.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.weixin.pay.util.MD5;

/**
 *
 * 微信统一下单需要传送的数据
 * @author xiaoyun
 * @date 2015-12-22 上午9:54:59
 * @version 1.0.0
 *
 */
public class UnifiedOrderRequestDto implements Serializable {
	
	public static void main(String[] args) {
		//XStream xstream = new XStream();
		UnifiedOrderRequestDto po = new UnifiedOrderRequestDto();		
		//xstream.alias("xml", UnifiedOrderRequestDto.class);
		po.setAppid("1234");
		po.setBody("aaaa");
		po.setLimit_pay("sdfa");
		po.setMch_id("47787111");
		po.setDetail("细节描述");
		//System.out.println(xstream.toXML(po));
		try {
			po.calcSign(po);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public String calcSign(UnifiedOrderRequestDto dto) throws IllegalArgumentException, IllegalAccessException{
		String signValue = "";
		// 获取需要进行签名的属性
		Field[] fields = dto.getClass().getDeclaredFields();
		// 属性名
		Map<String, String> fieldMap = new HashMap<String, String>(); 
		for (Field field : fields) {
			field.setAccessible(true);
			if(!field.getName().equals("serialVersionUID") && field.get(dto)!=null) {
				System.out.println(field.getName()+","+(String)field.get(dto));
				if(StringUtils.isNotBlank((String)field.get(dto))) {
					fieldMap.put(field.getName(), (String)field.get(dto));
				}
			}
		}
		String[] keyArr = new String[fieldMap.size()];
		fieldMap.keySet().toArray(keyArr);
		Arrays.sort(keyArr);
		StringBuilder signStr = new StringBuilder(); 
		for (String str : keyArr) {
			signStr.append(str).append("=").append(fieldMap.get(str)).append("&");
		}
		if(signStr.length() > 0) {
			signStr = signStr.deleteCharAt(signStr.length()-1);
		}
		/*System.out.println("原始字符串：" + signStr.toString());*/
		signValue = MD5.getMD5(signStr.toString());
		//System.out.println("进行md5后的数据：" + signValue);
		return signValue;
	}
		

	/**
	 * 
	 */
	private static final long serialVersionUID = -7954807686185521102L;
	
	/** 公众账号ID 必填 */
	private String appid;
	/** 商户号  必填*/
	private String mch_id;
	/** 设备号  非必填*/
	private String device_info;
	/** 随机字符串  必填*/
	private String nonce_str;
	/** 签名  必填*/
	private String sign;
	/** 商品描述  必填*/
	private String body;
	/** 商品详情  非必填 */
	private String detail;
	/** 附加数据 非必填 */
	private String attach;
	/** 商户订单号 必填 */
	private String out_trade_no;
	/** 货币类型 非必填（默认人民币：CNY） */
	private String fee_type;
	/** 总金额 必填 */
	private String total_fee;
	/** 终端IP 格式：123.12.12.123 */
	private String spbill_create_ip;
	/** 交易起始时间 格式：20091225091010 非必填 */
	private String time_start;
	/** 交易结束时间 格式：20091227091010 非必填 */
	private String time_expire;
	/** 商品标记 非必填 */
	private String goods_tag;
	/** 通知地址 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。必填 */
	private String notify_url;
	/** 交易类型：JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口 必填 */
	private String trade_type;
	/** 商品ID 非必填 */
	private String product_id;
	/** 指定支付方式：no_credit--指定不能使用信用卡支付 非必填 */
	private String limit_pay;
	/** 用户标识 非必填 */
	private String openid;
	
	
	
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
	
	/**
	 * 获取签名
	 * @return
	 */
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getLimit_pay() {
		return limit_pay;
	}
	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
