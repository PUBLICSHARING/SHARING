package base.sqlServer.jdbc.jdbcServer;

import java.sql.Connection;

import base.properties.PropertiesReader;
import base.sqlServer.jdbc.connection.ConnectionPool;

public class JdbcServer {
	/**单列**/
	private static JdbcServer jdbcServer = null;
	/**链接池**/
	private ConnectionPool connectionPool = null;
	
	private JdbcServer() {
		this.connectionPool = createConnectionPool();
	}
	
	/**
	 * 释放所有空闲链接
	 */
	public void releaseAllFreeConnection() {
		connectionPool.releaseAllFreeConnection();
	}
	
	/**
	 * 释放链接
	 */
	public void releaseConnection(Connection connection) {
		connectionPool.releaseConnection(connection);
	}
	
	/**
	 * 在等待时间内获取连接，在时间以外返回null
	 * @param timeout 等待时间
	 */
	public Connection getConnection(Long timeout) {
		return connectionPool.getConnection(timeout);
	}
	
	/**
	 * 无需等待，在当前连接数大于最大连接数时会返回null
	 */
	public Connection getConnection() {
		return connectionPool.getConnection();
	}
	
	/**
	 * 获得实例
	 */
	public synchronized static JdbcServer getInstance() {
		if(jdbcServer == null) {
			jdbcServer = new JdbcServer();
		}
		return jdbcServer;
	}
	
	/**
	 * 创建连接池
	 */
	public static ConnectionPool createConnectionPool() {
		ConnectionPool connectionPool = null;
		/**配置文件获取jdbc信息**/
		PropertiesReader propertiesReader = new PropertiesReader("/jdbc.properties");
		String driverName = propertiesReader.getProperty("jdbc.driverName");
		String jdbcUser = propertiesReader.getProperty("jdbc.userName");
		String jdbcPassword = propertiesReader.getProperty("jdbc.password");
		String jdbcUrl = propertiesReader.getProperty("jdbc.url");
		Integer maxCountOfConn = Integer.parseInt(propertiesReader.getProperty("connection.maxCountOfConn"));
		Integer minCountOfConn = Integer.parseInt(propertiesReader.getProperty("connection.minCountOfConn"));
		
		if(driverName != null && jdbcUser != null && jdbcPassword != null && jdbcUrl != null && jdbcUser != null 
				&& maxCountOfConn != null && minCountOfConn != null) {
			connectionPool = new ConnectionPool(maxCountOfConn, minCountOfConn, jdbcUrl, jdbcUser, jdbcPassword, driverName);
		}
		else{
			System.out.println("jdbc配置文件信息不完整");
		}
		return connectionPool;
	}

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}
}
