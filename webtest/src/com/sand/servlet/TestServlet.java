/**
 * 
 */
package com.sand.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.sand.util.MessageUtil;
/**
 *
 * desc:
 * @author Administrator
 * @date 2015-12-24 下午1:54:10
 * @version 
 *
 */
public class TestServlet extends HttpServlet {
	/**
	 * 接入验证
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {}
	
	
	/**
	 * 消息的接收与响应
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {			
			Map<String, String> map = MessageUtil.xmlToMap(req);
			for (Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
		} catch (DocumentException e) {
				e.printStackTrace();
		}
			
			
	}
}
