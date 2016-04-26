package com.jdbc.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间类型测试
 * 
 * @author xiaoyun 2015-11-5
 */
public class TestDate {
	
	/** 根据手机号查询短信发送情况 */
	public static String getSmsByPhoneNo = "select s.phoneno,s.updatetime,s.frequency,s.totalnum  from smp_sms_count s where s.phoneno=?";
	/** 插入数据 */
	public static String insertSms = "insert into smp_sms_count(phoneNo, updateTime, frequency, totalNum) values(?,?,?,?)";
	/** 更新记录 */
	public static String updateSms = "update smp_sms_count set updateTime=?,frequency=?,totalNum=? where phoneNo=?";
	
	public static void main(String[] args) {

		//insert();
		query();
	
	}
	
	public static void insert() {
		String phoneNo = "18610370532";
		Date updatetime = new Date();
		Timestamp t = new Timestamp(updatetime.getTime());
		int frequency = 1;
		int totalNum = 1;
		Connection conn = null;
		PreparedStatement stat = null;
		String url = "jdbc:oracle:thin:@172.28.250.131:1521:testdb";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "smps", "welcome");
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(insertSms);
			stat.setObject(1, phoneNo);
			stat.setObject(2, t);
			stat.setObject(3, frequency);
			stat.setObject(4, totalNum);
			
			stat.execute();
			System.out.println("已插入.");
			conn.commit();
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
	
	public static void query() {
		Connection conn = null;
		PreparedStatement stat = null;
		String url = "jdbc:oracle:thin:@172.28.250.131:1521:testdb";
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "smps", "welcome");
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(getSmsByPhoneNo);
			stat.setObject(1, "18610370534");
			rs = stat.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int length = metaData.getColumnCount();
            String[] colNames = new String[length];
            if(rs.next()) {
	        	 for (int i = 0; i < length; i++) {
	             	colNames[i] = metaData.getColumnName(i+1);
	             	if(colNames[i].equalsIgnoreCase("UPDATETIME")) {
	             		/*TIMESTAMP date = (TIMESTAMP)rs.getObject(colNames[i]);
	             		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
	             		System.out.println(fmt.format(new Date(date.timestampValue().getTime())));*/
	             		Timestamp date = (Timestamp)rs.getTimestamp(colNames[i]);
	             		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
	             		System.out.println(fmt.format(date));
	             	}else if(colNames[i].equalsIgnoreCase("frequency") || colNames[i].equalsIgnoreCase("totalNum")) {
	             		BigDecimal a = (BigDecimal)rs.getObject("frequency");
	             		System.out.println(a.intValue());
	             	}
	             	System.out.println(colNames[i] + ":类型为：" + metaData.getColumnTypeName(i+1) + ":" + rs.getObject(colNames[i]));
	 			 }
            }
			conn.commit();
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
