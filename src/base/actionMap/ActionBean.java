package base.actionMap;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

public class ActionBean {
	
	private Class<?> clazz;
	
	private Method method;
	
	private Map<String,Map<String,Type>> params = new HashMap<String, Map<String,Type>>();
	
	public ActionBean () {
		
	}
	
	public ActionBean(Class<?> clazz,Method method) {
		this.clazz = clazz;
		this.method = method;
		this.params = getMethodParamsAndType(method);
	}
	
	
	private static Map<String,Map<String,Type>> getMethodParamsAndType(Method actionMethod) {
		Map<String,Map<String,Type>> paramNames = new LinkedHashMap<String,Map<String,Type>>();

		LocalVariableTableParameterNameDiscoverer variableDiscover = new LocalVariableTableParameterNameDiscoverer();
		String[] params = variableDiscover.getParameterNames(actionMethod);
		Class<?>[] parameterTypes = actionMethod.getParameterTypes();
		Type[] genericParameterTypes = actionMethod.getGenericParameterTypes();
		for (int i = 0; i < parameterTypes.length; i++)
		{
			Class<?> parameterType = parameterTypes[i];

			Map<String,Type> parameterTypeMap = new LinkedHashMap<String,Type>();
			if(Collection.class.isAssignableFrom(parameterType))
			{
				Type genericClass = ((ParameterizedType) genericParameterTypes[i]).getActualTypeArguments()[0];
				parameterTypeMap.put("genericClass", genericClass);
			}
			parameterTypeMap.put("parameterType", parameterType);
			parameterTypeMap.put("paraType", genericParameterTypes[i]);

			paramNames.put(params[i], parameterTypeMap);
		}
		return paramNames;
	}

	public Map<String,Map<String,Type>> getParams() {
		return params;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Method getMethod() {
		return method;
	}
}
