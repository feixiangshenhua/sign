package jdbc.conn.h2.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.h2.tools.Server;

/**
 * ִ��sql�ű�
 * @author xiaoyun
 *
 */
public class H2RunScript {
	//���ݿ�����URL����ǰ���ӵ���E:/H2Ŀ¼�µ�gacl���ݿ�
    private static final String JDBC_URL = "jdbc:h2:~/test";
	//private static final String JDBC_URL = "jdbc:h2:~/test";
    //�������ݿ�ʱʹ�õ��û���
    private static final String USER = "sa";
    //�������ݿ�ʱʹ�õ�����
    private static final String PASSWORD = "";
    //����H2���ݿ�ʱʹ�õ������࣬org.h2.Driver���������H2���ݿ��Լ��ṩ�ģ���H2���ݿ��jar���п����ҵ�
    private static final String DRIVER_CLASS="org.h2.Driver";
    
    public void run() throws Exception {
    	 // ����H2���ݿ�����
        Class.forName(DRIVER_CLASS);
        // ��������URL���û����������ȡ���ݿ�����
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
       
        
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream("/���д���_����ű�.sql");
    	//RunScript.execute(conn, new InputStreamReader(is));
    	conn.close();
    }
    
    
    
    
    private static Server server;
	public static void main(String[] args) throws Exception {
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					server = Server.createTcpServer().start();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}).start();
		Thread.sleep(1000);*/
        // ����H2���ݿ�����
        Class.forName(DRIVER_CLASS);
        // ��������URL���û����������ȡ���ݿ�����
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        System.out.println(conn.toString());
        /*System.out.println(H2RunScript.class.getClassLoader().getResource("//").getPath());
        String filePath = System.getProperty("user.dir")+"/src/bank_code.sql";
        System.out.println(new File(filePath).getAbsolutePath());
        RunScript.execute(conn, new InputStreamReader(new FileInputStream(new File(filePath)),"utf-8"));*/
        conn.close();
		/*H2RunScript script = new H2RunScript();
		script.run();*/
		
    
	}

}
