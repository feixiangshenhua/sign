package com.h2db.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import org.h2.jdbcx.JdbcConnectionPool;

public class JdbcUtil {

    /**
     * H2���ݿ��Դ������ӳ�
     */
    private static JdbcConnectionPool cp = null;
    
    static{
        try {
            //����srcĿ¼�µ�h2config.properties
            InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("h2config.properties");
            Properties prop = new Properties();
            prop.load(in);
            //�������ݿ����ӳ�
            cp = JdbcConnectionPool.create(prop.getProperty("JDBC_URL"), prop.getProperty("USER"), prop.getProperty("PASSWORD"));
        } catch (Exception e) {
            System.out.println("���ӳس�ʼ���쳣");
            e.printStackTrace();
        }
    }
    
    /**
    * @Method: getConnection
    * @Description:��ȡ���ݿ�����
    * @Anthor:�°�����
    * @return
    * @throws Exception
    */ 
    public static Connection getConnection() throws Exception{
        return cp.getConnection();
    }

    public static JdbcConnectionPool getCp() {
        return cp;
    }
    
    public static void main(String[] args) {
		try {
			System.out.println(getConnection());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
