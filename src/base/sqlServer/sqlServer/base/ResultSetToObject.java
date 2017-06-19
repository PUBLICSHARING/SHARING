package base.sqlServer.sqlServer.base;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.commons.beanutils.BeanUtils;
import base.entity.Column;

public class ResultSetToObject<E> {
	
	/**
	 * 查单表
	 */
	public ArrayList<E> ResultSetToObjects(ResultSet rs, Class<E> outputClass)throws Exception {
		ArrayList<E> list = null;
		try{
			if(rs != null) {
				ResultSetMetaData rsm = rs.getMetaData();
				Field[] fields = outputClass.getDeclaredFields();
				while(rs.next()) {
					E bean = (E)outputClass.newInstance();
					for(int i = 0; i < rsm.getColumnCount(); i++) {
						String columnName = rsm.getColumnName(i + 1);
						Object columnValue = rs.getObject(i + 1);
						for(Field field : fields) {
							if(field.isAnnotationPresent(Column.class)) {
								Column column = field.getAnnotation(Column.class);
								if(column.attribute().equals("null")) {
									if(columnName.equals(field.getName())) {
										if(columnName.equals("id")) {
											BeanUtils.setProperty(bean, "id", columnValue);
										}
										else{
											if(columnValue instanceof java.sql.Timestamp) {
												DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
												String datesString = sdf.format(columnValue);
												java.util.Date date = sdf.parse(datesString);;
												BeanUtils.setProperty(bean, columnName, date);
												break;
											}
											BeanUtils.setProperty(bean, columnName, columnValue);
										}
										break;
									}
								}
								else{
									if(columnName.equals(column.attribute())){
										E foreighBeanE = (E)field.getType().newInstance();
										BeanUtils.setProperty(foreighBeanE, "id", columnValue);
										BeanUtils.setProperty(bean, field.getName(), foreighBeanE);
										break;
									}
								}
							}
						}
					}
					if(list == null) {
						list = new ArrayList<E>();
					}
					list.add(bean);
				}
				
			}
			else{
				System.out.print("结果集为空");
			}
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
