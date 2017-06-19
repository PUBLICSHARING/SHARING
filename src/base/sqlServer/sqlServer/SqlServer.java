package base.sqlServer.sqlServer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import base.entity.Column;
import base.sqlServer.sqlServer.base.DBCommon;

public class SqlServer<T>{

	private DBCommon<T> dbCommon = new DBCommon<T>();
	
	/**
	 * 查询结果转对象列表(只针对无关联实体查询)
	 */
	public ArrayList<T> query(Class<T> entityClass, String fieldName, Object fieldValue) throws Exception{		
		
		if(fieldName.equals("") || fieldName == null || fieldValue.equals("") || fieldValue == null){
			throw new Exception("查询字段值为空");
		}
		else {
			String sqlString = getSelectSQL(entityClass,fieldName,fieldValue);
			return this.dbCommon.queryArrayList(sqlString, entityClass);
		}
	}
	
	/**
	 * 查询结果转对象列表（值针对当前实体内外键值查询符合需求的实体集）
	 */
	public ArrayList<T> query(Class<T> currentClazz, String attributeName,Long id) throws Exception {
		if(attributeName == null || attributeName.equals("") || id == null) {
			throw new Exception("字段为空");
		}
		else{
			String sqlString = getSelectSQLWithAttribute(currentClazz, attributeName, id);
			return this.dbCommon.queryArrayListWithAttribute(sqlString, currentClazz);
		}
	}
	
	/**
	 * 单表查询，含外键转对象，不含使体内含集合的查询
	 */
	public  ArrayList<T> queryArrayListWithForeign(String sql,Class<T> currentClazz) throws Exception {
		try{
			return this.dbCommon.queryArrayListWithAttribute(sql, currentClazz);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Map<String, Object> queryOne(String sql) throws Exception {
		try{
			return this.dbCommon.queryOne(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 返回结果集
	 */
	public Set<Map<String, Object>> queryWidthSql(String sql) throws Exception{
		try{
			return this.dbCommon.queryWidthSql(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 输入外键名与值，返回查询sql
	 */
	private String getSelectSQLWithAttribute(Class<T> currentClazz, String attributeName,Long id) {
		return "select * from " + currentClazz.getSimpleName() + " where " + attributeName + "=" + id;
	}
	
	/**
	 * 获取查询语句(适用于无外键关联)
	 */
	private String getSelectSQL(Class<?> clazz,String fieldName,Object fieldValue) throws Exception{
		String sqlString = "select * from " + clazz.getSimpleName();
		sqlString += " where " + fieldName + "=";
		try{
			Field field = clazz.getDeclaredField(fieldName);
			if(field.getType().equals(String.class)){
				sqlString += "'" + fieldValue + "';";
			}
			else if(field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
				sqlString +=  fieldValue + ";";
			}
			else if(field.getType().equals(Boolean.class)) {
				sqlString +=  fieldValue + ";";
			}
			else if(field.getType().equals(Date.class)) {
				sqlString +=  fieldValue + ";";
			}
			else if(field.getType().equals(Long.class)) {
				sqlString +=  fieldValue + ";";
			}
			return sqlString;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 一般实体的更新或新增
	 */
	public Boolean queryExcute(Class<?> currentClass,Object o) throws Exception{
		String insertSQlString = getInsertSql(currentClass,o);
		System.out.println(insertSQlString);
		return this.dbCommon.queryExcute(insertSQlString); 
	}
	
	/**
	 * 得到插入SQL
	 */
	private String getInsertSql(Class<?> currentClass,Object o) throws Exception{
		String sqlString = "insert into " + currentClass.getSimpleName()+"(";
		String valueString = "values(";
		if(o.getClass().equals(currentClass)) {
			Map<String, Object> result = getFieldByObject(o);
			int mapSize = result.size();
			int index = 1;
			for(Map.Entry<String, Object> map:result.entrySet()) {
				Object valueObject = map.getValue();
				if(valueObject instanceof ArrayList<?>) {
					/**不作处理**/
				}
				else if(judgeIsCustomClass(valueObject.getClass())){
					/**不作处理**/
				}else{
					if(index == mapSize) {
						sqlString += map.getKey();
					}
					else{
						sqlString += map.getKey() + ",";
					}
					
				}
				
				if(valueObject.getClass().equals(String.class)) {
					if(index == mapSize) {
						valueString += "'" + valueObject + "'";
						break;
					}
					valueString += "'" + valueObject + "',";
					index++;
				}
				else if(valueObject instanceof ArrayList<?>) {
					index++;
				}
				else if(judgeIsCustomClass(valueObject.getClass())) {
					index++;
				}
				else if(valueObject instanceof Date) {
					Date time = new java.sql.Date(((Date)valueObject).getTime());
					if(index == mapSize) {
						valueString += "'" + time + "'";
						break;
					}
					valueString += "'" + time + "',";
					index++;
				}
				else{
					if(index == mapSize) {
						valueString +=  valueObject;
						break;
					}
					valueString +=  valueObject + ",";
					index++;
				}
			}
			Field[] fields = o.getClass().getDeclaredFields();
			boolean isAdd = false;
			for(Field field : fields) {
				field.setAccessible(true);
				if(judgeIsCustomClass(field.getType())) {
					Column column = field.getAnnotation(Column.class);
					Object object = field.get(o);
					if(object != null) {
						Map<String, Object> mapOfo = getFieldByObject(object);
						for(Map.Entry<String, Object> mapEntry : mapOfo.entrySet()) {
							if(mapEntry.getKey().equals("id") && mapEntry.getValue() != null) {
								sqlString += column.attribute() + ",";
								valueString += mapEntry.getValue() + ",";
								isAdd = true;
							}
						}
					}
				}
			}
			if(isAdd) {
				sqlString = sqlString.substring(0,sqlString.length() - 1);
				valueString = valueString.substring(0,valueString.length() - 1);
			}
			sqlString +=") ";
			valueString += ");";
		}
		else{
			throw new Exception("对象与实体不匹配");
		}
		return sqlString + valueString;
	}
	
	/**
	 * 判断该class是否为用户自定义类
	 */
	private static boolean judgeIsCustomClass (Class<?> clazz) {
		return !(clazz != null && clazz.getClassLoader() == null);
	}
	
	/**
	 * 通过object得到键值
	 */
	private Map<String, Object> getFieldByObject(Object object) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field field : fields) {
			/**获取属性访问权限**/
			field.setAccessible(true);
			Object valueObject = field.get(object);
			if(valueObject != null) {
				result.put(field.getName(), valueObject);
			}
		}
		return result;
	}

	/**
	 * 普通实体查询，不含联结
	 */
	public ArrayList<T> query(String sql,Class<T> currentClass) throws Exception{
		return this.dbCommon.queryArrayList(sql, currentClass);
	}
	
	/**
	 * 插入并返回id
	 */
	public Long excuteAndReturnId(String sql) throws Exception{
		return dbCommon.excuteInsertReturnId(sql);
	}
	
	/**
	 * 插入并返回id
	 */
	public Long excuteAndReturnId(Class<?> clazz,Object o) throws Exception{
		String sqlString = getInsertSql(clazz, o);
		return this.dbCommon.excuteInsertReturnId(sqlString);
	}
	
	/**
	 * 属性含ArrayList的实体的添加（组合关系，一对多）
	 */
	public void insertWithArrayList(Class<?> clazz,Object o) throws Exception {
		/**得到对象内含有的所有ArrayList**/
		Map<String, ArrayList<?>> containsList = getAllArrayListFromObject(o);
		if(containsList.size() > 0) {
			Long id = excuteAndReturnId(clazz,o);
			for(Map.Entry<String, ArrayList<?>> mapEntry : containsList.entrySet()) {
				insertArrayListWithForeignKey(o.getClass(),mapEntry.getValue(),id);
			}
		}
		else{
			queryExcute(clazz,o);
		}
	}
	
	/**
	 * 含外键ArrayList的插入
	 */
	private void insertArrayListWithForeignKey(Class<?> clazz,ArrayList<?> list,Long foreignKeyId) throws Exception{
		if(foreignKeyId == null) {
			throw new NullPointerException();
		}
		else{
			for(int i = 0; i < list.size() ; i++) {
				Object currentObject = list.get(i);
				Long id = excuteAndReturnId(getSqlWidthForeignKey(clazz, currentObject, foreignKeyId));
				Map<String, ArrayList<?>> containsList = getAllArrayListFromObject(currentObject);
				if(containsList.size() > 0) {
					for(Map.Entry<String, ArrayList<?>> mapEntry : containsList.entrySet()) {
						insertArrayListWithForeignKey(currentObject.getClass(),mapEntry.getValue(),id);
					}
				}
			}
		}
	}
	
	/**
	 * insert插入外键id
	 * 返回插入SQL
	 */
	private String getSqlWidthForeignKey(Class<?> foreignKeyClazz,Object o,Long foreignKeyId) throws Exception{
		String sqlInsert = "insert into " + o.getClass().getSimpleName() + "(";
		String sqlValues = " values(";
		Field[] fields = o.getClass().getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			/**字段属于外键**/
			if(field.getType().equals(foreignKeyClazz)) {
				Column column = field.getAnnotation(Column.class);
				sqlInsert += column.attribute() + ",";
				sqlValues += foreignKeyId + ",";
			}
			else{
				if(field.getType().equals(String.class)) {
					if(field.get(o) != null) {
						sqlInsert += field.getName() + ",";
						sqlValues += "'" + field.get(o) + "',";
					}
				}
				else if(field.get(o) instanceof ArrayList<?>) {
					/**不作处理**/
				}
				else if(judgeIsCustomClass(field.getType())){
					Object currentObject = field.get(o);
					Map<String, Object> mapO = getFieldByObject(currentObject);
					if(mapO.get("id") != null) {
						Column column = field.getAnnotation(Column.class);
						sqlInsert += column.attribute() + ",";
						sqlValues += mapO.get("id") + ",";
					}
				}
				else{
					if(field.get(o) != null) {
						sqlInsert += field.getName() + ",";
						sqlValues += field.get(o) + ",";
					}
				}
			}
		}
		sqlInsert = sqlInsert.substring(0,sqlInsert.length() - 1) + ")";
		sqlValues = sqlValues.substring(0,sqlValues.length() - 1) + ");";
		return sqlInsert + sqlValues;
	}
	
	
	private void setForeignKey(Class<?> clazz,Map<String, ArrayList<?>> containsList) {
		for(Map.Entry<String, ArrayList<?>> map : containsList.entrySet()) {
			ArrayList<?> list = map.getValue();
			for(int i = 0; i < list.size(); i++) {
				Object object = list.get(i);
			}
		}
	}
	
	/**
	 * 获取对象中的ArrayList
	 */
	private Map<String, ArrayList<?>> getAllArrayListFromObject(Object o) throws Exception{
		Map<String, ArrayList<?>> result = new HashMap<String,ArrayList<?>>();
		try{
			/**得到对象属性的键值对**/
			Map<String, Object> attributes = getFieldByObject(o);
			for(Map.Entry<String, Object> map : attributes.entrySet()) {
				String key = map.getKey();
				Object value = map.getValue();
				/**判断是否为ArrayList**/
				if(value instanceof ArrayList<?> && ((ArrayList) value).size() != 0) {
					result.put(key, (ArrayList<?>) value);
				}
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void update(Object object) throws Exception {
		try{
			if(object instanceof ArrayList<?>) {
				ArrayList<?> objects = (ArrayList)object;
				for(int i = 0; i < objects.size(); i++) {
					Object currentObject = objects.get(i);
					this.updateNotList(currentObject);
				}
			}
			else{
				this.updateNotList(object);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void updateNotList(Object object) throws Exception {
		String sqlString = "update " + object.getClass().getSimpleName() + " set ";
		try{
			Map<String, Object> oMap = this.getFieldByObject(object);
			Field[] fields = object.getClass().getDeclaredFields();
			for(Map.Entry<String, Object> mapEntry : oMap.entrySet()) {
				String key = mapEntry.getKey();
				Object value = mapEntry.getValue();
				if(value instanceof ArrayList<?>) {
					this.update(value);
				}
				else if(judgeIsCustomClass(value.getClass())) {
					for(Field field : fields) {
						if(field.getName().equals(key)) {
							Column column = field.getAnnotation(Column.class);
							Map<String, Object> inMap = this.getFieldByObject(value);
							if(inMap.get("id") == null) {
								throw new Exception("该更新对象不含主键id,不可以进行更新");
							}
							else{
								sqlString += column.attribute() + "=" + inMap.get("id") + ",";
								break;
							}
						}
					}
					updateNotList(value);
				}
				else{
					if(value instanceof String) {
						sqlString += key + "='" + value + "',";
					}
					else{
						sqlString += key + "=" + value + ",";
					}
				}
			}
			sqlString = sqlString.substring(0,sqlString.length() - 1);
			sqlString = sqlString + " where id=" + oMap.get("id");
			this.dbCommon.updateExcute(sqlString);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void delete(Class<?> clazz, Object object) throws Exception{
		try{
			String sqlString = "delete from " + clazz.getSimpleName() + " where id=" + object;
			this.dbCommon.delete(sqlString);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}