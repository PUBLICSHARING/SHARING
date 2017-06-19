package base.sqlServer.sqlServer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;

public class LazyLoadProperties {
	
	private static final LazyLoadProperties LAZY_LOAD_PROPERTIES = new LazyLoadProperties();
	private static SqlServer sqlServer = new SqlServer();
	private LazyLoadProperties() {
		
	}
	
	public static void lazyLoadProperties(Object object,String properties) throws Exception {
		try {
			String[] attributes = parsePropertiesString(properties);
			Boolean isList = judgePropertyIsList(attributes[0],object);
			if(isList == null) {
				throw new Exception("懒加载字段不属于关联字段");
			}
			else if(judgePropertyIsList(attributes[0],object)) {
				if(object instanceof ArrayList<?>) {
					for(int i = 0; i < ((ArrayList)object).size();i++) {
						Map<String, Object> valueMap = getFieldByObject(((ArrayList)object).get(i));
						ArrayList<?> lazyLoadObjectList = (ArrayList)(valueMap.get(attributes[0]));
//						Class<?> lazyLoadObjectClazz = lazyLoadObjectList.
//						String sqlString = "select * from " + ().getClass().getSimpleName() + " where ";
					}
				}
				else{
					
				}
			}
			else{
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 判断懒加载字段是否符合要求返回是否为集合
	 */
	private static Boolean judgePropertyIsList(String property,Object lazyObject) throws Exception {
		try{
			Object object = null;
			if(lazyObject instanceof ArrayList<?>) {
				object = ((ArrayList) lazyObject).get(0);
			}
			else{
				object = lazyObject;
			}
			Map<String, Object> valueMap = getFieldByObject(object);
			Field[] fields = object.getClass().getDeclaredFields();
			boolean isEqual = false;
			for(Field field : fields) {
				if(field.getName().equals(property)) {
					isEqual = true;
					if(judgeIsCustomClass(field.getType())) {
						Object bean = field.getType().newInstance();
						if(bean instanceof ArrayList<?>) {
							return true;
						}
						else{
							Object currentObject = valueMap.get(field.getName());
							if(currentObject == null) {
								throw new Exception("该实体含有对象 " + field.getType() + ",但是在懒加载时需要此对象不为空");
							}
							else{
								Map<String, Object> currentObjectMap = getFieldByObject(currentObject);
								if(currentObjectMap.get("id") == null) {
									throw new Exception("该实体含有对象 " + field.getType() + ",但是在懒加载时需要此对象内id值不为空");
								}
								else{
									return false;
								}
							}
						}
					}
					else{
						throw new Exception("懒加载字段不属于关联字段");
					}
				}
				
			}
			if(!isEqual) {
				throw new Exception("懒加载字段：" + property + "但是此字段不在此对象（"+ object.getClass() +"）内");
			}
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private static String[] parsePropertiesString(String properties) {
		return properties.split("\\.");
	}
	
	/**
	 * 通过object得到键值
	 */
	private static Map<String, Object> getFieldByObject(Object object) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field field : fields) {
			/**获取属性访问权限**/
			field.setAccessible(true);
			Object valueObject = field.get(object);
			if(valueObject != null) {
				result.put(field.getName(), valueObject);
			}
			else{
				result.put(field.getName(), null);
			}
		}
		return result;
	}
	
	/**
	 * 判断该class是否为用户自定义类
	 */
	private static boolean judgeIsCustomClass (Class<?> clazz) {
		return !(clazz != null && clazz.getClassLoader() == null);
	}
}
 