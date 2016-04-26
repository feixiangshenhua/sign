package com.h2db.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.h2.tools.Server;

/**
 * @Description: 用于启动h2数据库的监听器，tomcat启动时，启动该服务
 * @Author:	xiaoyun
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2015-7-19
 */
public class H2DBServerStartListener implements ServletContextListener {
	/**
	 * 数据库服务
	 */
	private Server server;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(server != null) {
			server.shutdown();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			System.out.println("正在启动h2数据库。。。");
			server = Server.createTcpServer().start();
			System.out.println("h2数据库启动成功。。。");
		} catch (SQLException e) {
			System.out.println("启动h2数据库出错：" + e.toString());  
			e.printStackTrace();  
			throw new RuntimeException(e);  
		}
	}

}
