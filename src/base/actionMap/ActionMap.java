package base.actionMap;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import base.properties.PropertiesReader;
public class ActionMap {
	private static ActionMap actionMap = null;
	
	public static Map<String, ActionBean> allActions = new HashMap<String,ActionBean>();
	
	private static final String propertiesUrl = "/action.properties";
	
	private static String relativeUrl = "";
	
	static {
		relativeUrl = (getActionUrl()).replace('/', '.');
		String actionUrl = getCompletePath(relativeUrl);
		getAllEntity(actionUrl,"");
		System.out.println(allActions);
	}
	
	public synchronized static ActionMap getInstance() {
		if(actionMap == null) {
			actionMap = new ActionMap();
		}
		return actionMap;
	}
	
	private synchronized static String getActionUrl() {
		PropertiesReader propertiesReader = new PropertiesReader(propertiesUrl);
		return propertiesReader.getProperty("actionUrl");
	}
	
	/**
	 * 实体class路径得到所有的实体名
	 * @param entityPath
	 */
	private static void getAllEntity(String entityPath,String packageName) {
		File file = new File(entityPath);
		if(file.isFile()) {
			String classUrl = relativeUrl + "." + packageName.substring(0,packageName.length() - 7);
			allActions.putAll(getActionBeanFromClass(classUrl));
		}
		else if(file.isDirectory()){
			File[] files = file.listFiles();
			for(int i = 0 ; i < files.length ; i++) {
				getAllEntity(files[i].getPath(),packageName + files[i].getName() + ".");
			}
		}
	}
	
	/**
	 * 根据class名得到ActionBean
	 */
	private static Map<String, ActionBean> getActionBeanFromClass(String className) {
		Map<String, ActionBean> result =  new HashMap<String, ActionBean>();
		try{
			Class<?> clazz = Class.forName(className);
			Method[] methods = clazz.getDeclaredMethods();
			for(int i = 0; i < methods.length; i++) {
				String methodUrl = "/" + clazz.getSimpleName() + "/" + methods[i].getName();
				result.put(methodUrl,new ActionBean(clazz,methods[i]));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 实体相对路径获得绝对路径
	 * @param path 实体相对路径
	 */
	private static String getCompletePath(String relativePath){
		ActionMap actionMap = new ActionMap();
		//得到该类的class文件的路径
		String currentClasspath = actionMap.getClass().getResource("").getPath();
		
		int index = 0;
		for(int i = 0; i < currentClasspath.length(); i++) {
			if(currentClasspath.substring(i, i + 7).equals("classes")){
				index = i + 8;
				break;
			}
		}
		return currentClasspath.substring(0, index) + relativePath.replace('.', '/');
	}

	public static Map<String, ActionBean> getAllActions() {
		return allActions;
	}
	

}
