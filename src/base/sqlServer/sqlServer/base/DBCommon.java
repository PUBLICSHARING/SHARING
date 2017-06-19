package base.sqlServer.sqlServer.base;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import base.entity.Column;
import base.properties.PropertiesReader;
import base.sqlServer.jdbc.jdbcServer.JdbcServer;
import java.lang.Exception;

public class DBCommon<E> {
	
	private JdbcServer jdbcServer = null;
	
	private ResultSetToObject<E> resultSetToObject;
	
	private String dbName;
	
	public DBCommon() {
		this.jdbcServer = JdbcServer.getInstance();
		this.resultSetToObject = new ResultSetToObject<E>();
		this.dbName = getDBName();
	}
	
	/**
	 * 建表（聚合关系中的（1对多）、多对多待设计）
	 */
	public void createTable(Class<E> needClass) throws Exception{
		String sqlString = "create table " + needClass.getSimpleName() + "(id int(11) primary key not null auto_increment,";
		Field[] fields = needClass.getDeclaredFields();
		for(Field field : fields) {
			try{
				Column column = field.getAnnotation(Column.class);
				if(column.label().equals("id")){
					
				}
				else{
					sqlString += getSqlfromField(field);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		sqlString = sqlString.substring(0,sqlString.length() - 1);
		sqlString += ")";
		excute(sqlString, jdbcServer);
	}
	
	/**
	 * 通过字段来获取创建该属性的sql语句
	 */
	private static String getSqlfromField(Field field) throws Exception{
		if(judgeIsCustomClass(field.getType())){
			return getSqlfromFieldForeignKey(field);
		}
		else{
			return getSqlfromFieldNotForeignKey(field);	
		}
	}
	
	/**
	 * 非外键字段获得创建该字段sql
	 */
	private static String getSqlfromFieldNotForeignKey(Field field) throws Exception{
		String sqlString = "";
		if(field.isAnnotationPresent(Column.class)) {
			sqlString += field.getName();
			Column column = field.getAnnotation(Column.class);
			if(field.getType() == String.class) {
				if(column.maxLength() == 0) {
					throw new Exception("字段最大长度不能为0");
				}
				else{
					sqlString += " varchar("+ column.maxLength() +")";
				}
				if(column.isNullable() == false) {
					sqlString += " not null";
				}
				if(column.isUnique() == true) {
					sqlString += " unique";
				}
				sqlString += ",";
			}
			else if(field.getType() == int.class || field.getType() == Integer.class) {
				if(column.minValue() >= column.maxValue()) {
					throw new Exception("字段最大值不能小于等于最小值");
				}
				else{
					sqlString += " int ";
					if(column.isNullable() == false) {
						sqlString += " not null";
					}
					if(column.isUnique() == true) {
						sqlString += " unique";
					}
					sqlString += ",";
					sqlString += "check ("+ field.getName() +" >="+ column.minValue() +" and "+ field.getName() +" <= "+ column.maxValue() +"),";
				}
			}
			
			else if(field.getType() == Date.class) {
				sqlString += " datetime";
				if(column.isNullable() == false) {
					sqlString += " not null";
				}
				if(column.isUnique() == true) {
					sqlString += " unique";
				}
				sqlString += ",";
			}
			else if(field.getType() == boolean.class){
				sqlString += " boolean";
				if(column.isNullable() == false) {
					sqlString += " not null";
				}
				if(column.isUnique() == true) {
					sqlString += " unique";
				}
				sqlString += ",";
			}
			else{
				return "";
			}
		}
		else{
			throw new Exception("实体内某字段不含有Column注解");
		}
		return sqlString;
	}
	
	/**
	 * 外键字段获得创建该字段sql
	 */
	private static String getSqlfromFieldForeignKey(Field field) throws Exception{
		String sqlString = "";
		if(field.isAnnotationPresent(Column.class)) {
			Column column = field.getAnnotation(Column.class);
			if(column.attribute().equals("null")) {
				throw new Exception("实体内某字段Column注解attribute书写错误");
			}
			else{
				if(column.isNullable() == false) {
					sqlString += column.attribute() + " int(11) not null,"
							+ "foreign key ("+ column.attribute() +") references " + field.getType().getSimpleName() + "(id),";
				}
				else{
					sqlString += column.attribute() + " int(11),"
							+ "foreign key ("+ column.attribute() +") references " + field.getType().getSimpleName() + "(id),";
				}
				
			}
		}
		else{
			throw new Exception("实体内某字段不含有Column注解");
		}
		return sqlString;
	}
	
	/**
	 * 判断该class是否为用户自定义类
	 */
	private static boolean judgeIsCustomClass (Class<?> clazz) {
		return !(clazz != null && clazz.getClassLoader() == null);
	}
	
	/**
	 * 判断表是否存在（仅支持mysql）
	 */
	public boolean judgeTableExit(String tableName) {
		Boolean isExitBoolean = null;
		String sql = "SELECT '" + tableName + "' FROM information_schema.TABLES WHERE table_name ='" + tableName + "' and table_schema='"+ dbName +"'";
		Connection connection = jdbcServer.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int i = 0;
			if(resultSet.next()) {
				isExitBoolean = true;
			}
			else{
				isExitBoolean = false;
			}
			jdbcServer.releaseConnection(connection);
			preparedStatement.close();
			resultSet.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return isExitBoolean;
	}
	
	/**
	 * 得到数据库名
	 */
	private static String getDBName() {
		PropertiesReader propertiesReader = new PropertiesReader("/jdbc.properties");
		String dbUrlString = propertiesReader.getProperty("jdbc.url");
		int indexOfWenhao = 0;
		for(int i = 0; i < dbUrlString.length();i++) {
			if(dbUrlString.charAt(i) == '?') {
				indexOfWenhao = i;
				break;
			}
		}
		dbUrlString = dbUrlString.substring(0, indexOfWenhao);
		int indexOfXieGang = 0;
		for(int i = dbUrlString.length() - 1; i >= 0; i--) {
			if(dbUrlString.charAt(i) == '/') {
				indexOfXieGang = i;
				break;
			}
		}
		String dbNameString = dbUrlString.substring(indexOfXieGang + 1);
		return dbNameString;
	}
	
	/**
	 * 值针对非外键查询，且集合内不包含外键
	 * sql语句，以及sql结果集对应的类Class,
	 * 返回一个包含此对象的列表
	 */
	public ArrayList<E> queryArrayList(String sql, Class<E> currentClass) throws Exception{
		ResultSet rSet = query(sql, jdbcServer);
		ArrayList<E> result =  resultSetToObject.ResultSetToObjects(rSet, currentClass);
		return result;
	}
	
	/**
	 * 值针对非外键查询，且集合内不包含外键
	 * sql语句，以及sql结果集对应的类Class,
	 * 返回一个包含此对象的列表
	 */
	public ArrayList<E> queryArrayListWithAttribute(String sql, Class<E> currentClass) throws Exception{
		ResultSet rSet = query(sql, jdbcServer);
		ArrayList<E> result =  resultSetToObject.ResultSetToObjects(rSet, currentClass);
		return result;
	}
	
	public Boolean queryExcute(String sql) throws Exception{
		return excute(sql,jdbcServer);
	}
	
	public void updateExcute(String sql) throws Exception {
		this.excute(sql, jdbcServer);
	}
	
	public void delete(String sql) throws Exception {
		this.excute(sql, jdbcServer);
	}
	
	/**
	 * update.insert
	 */
	private static Boolean excute(String sql,JdbcServer jdbcServer) throws Exception{
		Connection connection = jdbcServer.getConnection();
		PreparedStatement preparedStatement = null;
		boolean isSucess = false;
		try{
			preparedStatement = connection.prepareStatement(sql);
			isSucess = preparedStatement.execute();
			jdbcServer.releaseConnection(connection);
			preparedStatement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return isSucess;
	}
	
	/**
	 * 输入插入sql返回id
	 */
	public Long excuteInsertReturnId(String sql) throws Exception{
		Connection connection = jdbcServer.getConnection();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			ResultSet rSet = preparedStatement.getGeneratedKeys();
			Long re = null;
			if(rSet.next()) {
				re = (Long)rSet.getObject(1);
			}
			return re;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * 获得SQL查询语句的结果集
	 */
	private static ResultSet query(String sql,JdbcServer jdbcServer) {
		Connection connection = jdbcServer.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			jdbcServer.releaseConnection(connection);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public Map<String, Object> queryOne(String sql) throws Exception {
		try{
			Set<Map<String, Object>> set = this.setHandler(this.query(sql, jdbcServer));
			Map<String , Object> result = new HashMap<String, Object>();
			if(set.size() > 1 || set.size() == 0) {
				throw new SQLException("请确保返回数据为单行且不为空");
			}
			else{
				for(Map map : set) {
					result = map;
					break;
				}
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 通过sql语句，返回map
	 */
	public Set<Map<String, Object>> queryWidthSql(String sql) throws Exception{
		try{
			return this.setHandler(this.query(sql, jdbcServer));
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private Set<Map<String,Object>> setHandler(ResultSet rs) throws Exception {
		Set<Map<String, Object>> result = new HashSet<Map<String,Object>>();
		try{
			while(rs.next()) {
				ResultSetMetaData rsm = rs.getMetaData();
				Map<String, Object> map = new HashMap<String, Object>();
				for(int i = 0; i < rsm.getColumnCount(); i++) {
					String keyString = rsm.getColumnName(i + 1);
					Object value = rs.getObject(i + 1);
					map.put(keyString, value);
				}
				result.add(map);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void update(Object object)  throws Exception {
		try{
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
