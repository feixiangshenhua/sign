/**
 * 
 */
package com.sand.json.po;

/**
 *
 * desc:
 * @author Administrator
 * @date 2016-2-22 上午10:30:19
 * @version 
 *
 */
public class OrderInfo {

	private String id;
	private String traceno;
	private String attach;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTraceno() {
		return traceno;
	}
	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	@Override
	public String toString() {
		return "OrderInfo [id=" + id + ", traceno=" + traceno + ", attach="
				+ attach + "]";
	}
	

}
