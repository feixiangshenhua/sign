package jdbc.conn.h2.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

/**
 * @Description:
 * @Author:	xiaoyun
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2015-7-19
 */
public class H2ConnTest {

    //���ݿ�����URL����ǰ���ӵ���E:/H2Ŀ¼�µ�gacl���ݿ�
    //private static final String JDBC_URL = "jdbc:h2:tcp://localhost/E:/H2/test";
	//private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db";
	private static final String JDBC_URL = "jdbc:h2:E:/H2/test";
    //�������ݿ�ʱʹ�õ��û���
    private static final String USER = "sa";
    //�������ݿ�ʱʹ�õ�����
    private static final String PASSWORD = "";
    //����H2���ݿ�ʱʹ�õ������࣬org.h2.Driver���������H2���ݿ��Լ��ṩ�ģ���H2���ݿ��jar���п����ҵ�
    private static final String DRIVER_CLASS="org.h2.Driver";
    
    public static void main(String[] args) throws Exception {
        // ����H2���ݿ�����
        Class.forName(DRIVER_CLASS);
        // ��������URL���û����������ȡ���ݿ�����
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        //�������USER_INFO�����ɾ��USER_INFO��
        //stmt.execute("DROP TABLE IF EXISTS USER_INFO");
        //����USER_INFO��
        //stmt.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
        //����
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','��������','��')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','����','��')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','�׻�','��')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','��ȸ','Ů')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','����','��')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','����','��')");
        //ɾ��
        stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='��������'");
        //�޸�
        stmt.executeUpdate("UPDATE USER_INFO SET name='�°�����' WHERE name='����'");
        //��ѯ
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO");
        //���������
        while (rs.next()) {
            System.out.println(rs.getString("id") + "," + rs.getString("name")+ "," + rs.getString("sex"));
        }
        
        //�ͷ���Դ        
        stmt.close();
        //�ر�����
        conn.close();
    }

}

