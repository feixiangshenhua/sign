package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author xiaoyun 2015-10-19
 * 
 */
public class TestBatch {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		String url = "jdbc:oracle:thin:@172.28.250.131:1521:testdb";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "smps", "welcome");
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			String[] sqls = new String[2];
			sqls[0] = "insert into smp_order_success select * from smp_order_info order_info where order_info.id='150416A00002515' and order_info.cdate<>to_char(sysdate,'yyyyMMdd')";
			sqls[1] = "delete from smp_order_info so where so.id='150416A00002515' and cdate<>to_char(sysdate,'yyyyMMdd')";
			//sqls[0] = "insert into smp_order_info select * from smp_order_success order_info where order_info.id='150416A00002515' and order_info.cdate<>to_char(sysdate,'yyyyMMdd')";
			//sqls[1] = "delete from smp_order_success where id='150416A00002515' and cdate<>to_char(sysdate,'yyyyMMdd')";
			for (String sql : sqls) {
				stat.addBatch(sql);
			}
			int[] rs = stat.executeBatch();
			for (int i : rs) {
				System.out.println("影响的记录数为：" + i);
				if(i == 0) {
					conn.rollback();					
				}
			}
			conn.commit();
			System.out.println("订单移动表完毕~");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			try {
				if(conn != null && !conn.isClosed() && !conn.getAutoCommit()){
					  conn.rollback();
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
