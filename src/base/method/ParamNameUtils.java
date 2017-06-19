package base.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ParamNameUtils {
	
	/**
	 * 获取参数名
	 */
	public static ArrayList<String> getParamNames(Method method) {
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();  
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {  
            return null;  
        }  
        ArrayList<String> parameterNames = new ArrayList<String>();  
        int i = 0;  
        for (Annotation[] parameterAnnotation : parameterAnnotations) {  
            for (Annotation annotation : parameterAnnotation) {  
                if (annotation instanceof Param) {  
                    Param param = (Param) annotation;  
                    parameterNames.add(param.value());  
                }  
            }  
        }  
        return parameterNames;  
	}
}
