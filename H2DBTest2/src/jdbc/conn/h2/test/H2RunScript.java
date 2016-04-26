package jdbc.conn.h2.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.h2.tools.Server;

/**
 * 执行sql脚本
 * @author xiaoyun
 *
 */
public class H2RunScript {
	//数据库连接URL，当前连接的是E:/H2目录下的gacl数据库
    private static final String JDBC_URL = "jdbc:h2:~/test";
	//private static final String JDBC_URL = "jdbc:h2:~/test";
    //连接数据库时使用的用户名
    private static final String USER = "sa";
    //连接数据库时使用的密码
    private static final String PASSWORD = "";
    //连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
    private static final String DRIVER_CLASS="org.h2.Driver";
    
    public void run() throws Exception {
    	 // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
       
        
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream("/银行代码_插入脚本.sql");
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
        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
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
