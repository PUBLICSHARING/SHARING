package base.createOrUpdateTable;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import base.entity.Column;
import base.properties.PropertiesReader;
import base.sqlServer.sqlServer.base.DBCommon;


public class GenerateTable<E> {
	/*实体配置文件*/
	public static final String PROPERTIESURL = "/entity.properties";
	/*实体路径在properties文件中的key值*/
	public static final String ENTITYKEY = "entityUrl";
	/*相对包名*/
	public static String relativePackageName = "";
	/*所有实体名*/
	public static ArrayList<String> entityNames = new ArrayList<String>();
	
	public DBCommon<E> dbCommon = new DBCommon<E>();
	
	static{
		String entityPath = getEntityPathFromProperties();
		getAllEntity(entityPath,"");
		(new GenerateTable()).createOrUpdateTable();;
	}
	
	/**
	 * 创建表或更新表
	 */
	public void createOrUpdateTable() {
		ArrayList<String> copyEntityNames = depthCloneArrayList(entityNames);
		createTable(copyEntityNames);
	}
	
	/**
	 * 克隆ArrayList
	 */
	private ArrayList<String> depthCloneArrayList (ArrayList<String> needCloneList) {
		ArrayList<String> resultList = new ArrayList<String>();
		for(int i = 0; i < needCloneList.size(); i++) {
			resultList.add(needCloneList.get(i));
		}
		return resultList;
	}
	
	/**
	 * 根据class名获取类名
	 */
	private String getTableName(String entityName) {
		int index = 0;
		for(int i = entityName.length() - 1; i >= 0 ; i--) {
			if(entityName.charAt(i) == '.') {
				index = i;
				break;
			}
		}
		return entityName.substring(index + 1);
	}
	
	/**
	 * 建表
	 */
	private void createTable(ArrayList<String> entityNames) {
		while(entityNames.size() != 0) {
			for(int i = 0; i < entityNames.size(); i++) {
				/**判断表是否存在**/
				if(dbCommon.judgeTableExit(getTableName(entityNames.get(i)))){
					entityNames.remove(i);
				}
				else{
					try{
						Class clazz = Class.forName(entityNames.get(i));
						ArrayList<Class<?>> foreignKeys = hasForeignKey(clazz);
						if(foreignKeys.size() == 0) {
							dbCommon.createTable(clazz);
							entityNames.remove(i);
						}
						else{
							/**判断表是否存在**/
							if(judgeAllForeignTableIsCreated(foreignKeys)) {
								dbCommon.createTable(clazz);
								entityNames.remove(i);
							}
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private boolean judgeAllForeignTableIsCreated (ArrayList<Class<?>> foreignKeys) {
		boolean is = true;
		for(int i= 0; i < foreignKeys.size(); i++) {
			if(dbCommon.judgeTableExit(foreignKeys.get(i).getSimpleName())){
				
			}
			else{
				is = false;
			}
		}
		return is;
	}
	
	private ArrayList<Class<?>> hasForeignKey(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		ArrayList<Class<?>> foreignKeys = new ArrayList<Class<?>>();
		for(Field field:fields) {
			Column column = field.getAnnotation(Column.class);
			if(!(column.attribute().equals("null"))) {
				foreignKeys.add(field.getType());
			}
		}
		return foreignKeys;
	}
	
	/**
	 * 跟新表
	 */
	private void updateTable(String tableName) {
		
	}
	
	/**
	 * 实体class路径得到所有的实体名
	 * @param entityPath
	 */
	public static void getAllEntity(String entityPath,String packageName) {
		File file = new File(entityPath);
		if(file.isFile()) {
			entityNames.add(relativePackageName + "." + packageName.substring(0,packageName.length() - 7));
		}
		else if(file.isDirectory()){
			File[] files = file.listFiles();
			for(int i = 0 ; i < files.length ; i++) {
				getAllEntity(files[i].getPath(),packageName + files[i].getName() + ".");
			}
		}
	}
	
	/**
	 * 读取配置文件得到实体绝对路径
	 * @return String
	 */
	public static String getEntityPathFromProperties() {
		PropertiesReader propertiesReader = new PropertiesReader(PROPERTIESURL);
		String relativePath = propertiesReader.getProperty(ENTITYKEY);
		getRelativePackageName(relativePath);
		return getCompletePath(relativePath);
	}
	
	/**
	 * 通过配置文件配置的实体路径，得到相应的包名
	 * @param relativePath
	 */
	public static void getRelativePackageName(String relativePath) {
		relativePackageName = relativePath.replace('/', '.');
	}
	
	/**
	 * 实体相对路径获得绝对路径
	 * @param path 实体相对路径
	 * @return
	 */
	public static String getCompletePath(String relativePath){
		GenerateTable generateTable = new GenerateTable();
		//得到该类的class文件的路径
		String currentClasspath = generateTable.getClass().getResource("").getPath();
		
		int index = 0;
		for(int i = 0; i < currentClasspath.length(); i++) {
			if(currentClasspath.substring(i, i + 7).equals("classes")){
				index = i + 8;
				break;
			}
		}
		return currentClasspath.substring(0, index) + relativePath;
	}
}
