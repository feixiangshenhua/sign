package com.sand.util;

import net.sf.json.JSONObject;

public interface HttpTool {
	
	public String httpGet(String url,String charset);
	
	public String httpPost(String paramContent, String url,String charset) ;
	
	public JSONObject httpPost(JSONObject paramContent, String url,String charset) ;

	public String simplePost(JSONObject jsonParam, String remoteUrl, String charSet) ;
	
}
