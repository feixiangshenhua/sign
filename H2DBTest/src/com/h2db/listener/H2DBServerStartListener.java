package com.h2db.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.h2.tools.Server;

/**
 * @Description: ��������h2���ݿ�ļ�������tomcat����ʱ�������÷���
 * @Author:	xiaoyun
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2015-7-19
 */
public class H2DBServerStartListener implements ServletContextListener {
	/**
	 * ���ݿ����
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
			System.out.println("��������h2���ݿ⡣����");
			server = Server.createTcpServer().start();
			System.out.println("h2���ݿ������ɹ�������");
		} catch (SQLException e) {
			System.out.println("����h2���ݿ����" + e.toString());  
			e.printStackTrace();  
			throw new RuntimeException(e);  
		}
	}

}
