package base.sqlServer.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class ConnectionPool {
	/**最大连接数**/
	private int maxCountOfConn;
	/**最小连接数**/
	private int minCountOfConn = 0;
	/**当前创建链接总数**/
	private int currentCountOfConn = 0;
	/**jdbcurl**/
	private String jdbcUrl;
	/**jdbc用户名**/
	private String jdbcUser;
	/**jdbc密码**/
	private String jdbcPassword;
	/**jdbc驱动名**/
	private String driverName;
	/**jdbc空闲链接**/
	private ArrayList<Connection> freeConnections;
	
	/**
	 * @param maxCountOfConn 最大连接数
	 * @param minCountOfConn 最小连接数（初始化一定数量的链接，防止在少链接请求时也出现等待）
	 * @param jdbcUrl 
	 * @param jdbcUser jdbc用户名
	 * @param jdbcPassword jdbc密码
	 * @param driverName jdbcDriver
	 */
	public ConnectionPool(int maxCountOfConn,int minCountOfConn,String jdbcUrl,String jdbcUser,String jdbcPassword,String driverName){
		this.maxCountOfConn = maxCountOfConn;
		this.minCountOfConn = minCountOfConn;
		this.jdbcPassword = jdbcPassword;
		this.jdbcUrl = jdbcUrl;
		this.jdbcUser = jdbcUser;
		this.driverName = driverName;
		this.freeConnections = initConnections(this.minCountOfConn, driverName, jdbcUrl, jdbcUser, jdbcPassword);
		this.currentCountOfConn = minCountOfConn;
	}
	
	/**
	 * 释放所有的空闲连接
	 */
	public synchronized void releaseAllFreeConnection() {
		for(Connection connection : freeConnections) {
			try{
				connection.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		freeConnections.clear();
		freeConnections.trimToSize();
	}
	
	/**
	 * 释放链接
	 */
	public synchronized void releaseConnection(Connection connection) {
		this.freeConnections.add(connection);
		/**通知正在wait的线程获取连接**/
		notifyAll();
	}
	
	/**
	 * 等待时间内获取链接，超出时间返回null
	 * @param timeout 秒
	 */
	public synchronized Connection getConnection(long timeout) {  
        Connection conn = null;  
        long startTime = System.currentTimeMillis();  
        while((conn = getConnection()) == null) {  
            try{  
                wait(timeout);//等待唤醒
            }catch(InterruptedException e) {  
            }  
            if(System.currentTimeMillis() - startTime > timeout*1000000)  
                return null;  
        }  
        return conn;  
    }  
	
	/**
	 * 获取链接
	 */
	public synchronized Connection getConnection() {
		Connection connection = null;
		
		if(this.freeConnections.size() > 0) {
			connection = this.freeConnections.get(0);
			this.freeConnections.remove(0);
			
			try{
				if(connection.isClosed()) {
					connection = getConnection();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(this.currentCountOfConn < this.maxCountOfConn) {
			connection = newConnection(this.driverName, this.jdbcUrl, this.jdbcUser, this.jdbcPassword);
			this.currentCountOfConn++;
		}
		return connection;
	}
	
	
	/**
	 * 初始化minCountOfConn个connection
	 */
	private static ArrayList<Connection> initConnections(int minCountOfConn,String driverName,String jdbcUrl,String jdbcUser,String jdbcPassword) {
		ArrayList<Connection> connections = new ArrayList<Connection>();
		for(int i = 0; i < minCountOfConn; i++) {
			connections.add(newConnection(driverName, jdbcUrl, jdbcUser, jdbcPassword));
		}
		return connections;
	}
	
	/**
	 * 获得新的connection
	 */
	private static Connection newConnection(String driverName,String jdbcUrl,String jdbcUser,String jdbcPassword) {
		Connection connection = null;
		try{
			Class.forName(driverName);
			connection = DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPassword);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
