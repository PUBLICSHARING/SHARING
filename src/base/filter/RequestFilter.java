package base.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.actionMap.ActionBean;
import base.actionMap.ActionMap;
import base.actionMap.CastUtil;
import base.actionMap.DDDJSON;
import base.actionMap.ErrorPage;
import base.actionMap.ForwardPage;
import base.actionMap.HTMLPage;
import base.actionMap.JSONResult;
import base.actionMap.RedirectPage;
import base.actionMap.VMPage;
import base.actionMap.VelocityUtil;
import base.ip.IpHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * Servlet Filter implementation class ParseRequest
 */
@WebFilter("/ParseRequest")
public class RequestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RequestFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		/**设置编码**/
		httpServletRequest.setCharacterEncoding("utf-8");
		/**获取请求方法**/
		String currentRequestMethod = httpServletRequest.getMethod();
		/**获取请求路径**/
		String currentRequestPath = getRequestPath(httpServletRequest);
		/**获取ip**/
		IpHandler ipHandler = IpHandler.getInstance();
		ipHandler.setIp(this.getRemortIP(httpServletRequest));
		
		System.out.println("-----------------请求信息----------------");
		System.out.println("请求路径：" + currentRequestPath);
		System.out.println(""
				+ request.getParameterMap().toString());
		/**判断是否为静态资源**/
		if(isStaticResource(currentRequestPath)){
			chain.doFilter(request, response);
		}
		else{
			boolean is = actionHandler((HttpServletRequest)request, (HttpServletResponse)response, chain, currentRequestPath);
			if(!is) {
				chain.doFilter(request, response);
			}
		}
	}
	
	/**
	 * 调用方法处理
	 */
	private boolean actionHandler(HttpServletRequest request,HttpServletResponse response,FilterChain chain,String requestActionPasth) {
		ActionMap actionMap = ActionMap.getInstance();
		Map<String, ActionBean> allActions = actionMap.getAllActions();
		ActionBean actionBean = allActions.get(requestActionPasth);
		if(actionBean == null){
			return false;
		}
		
		Map<String,Map<String,Type>> params = actionBean.getParams();
		Map<String,String[]> paMap = request.getParameterMap();
		request.getParameter("user");
		
		Method method = actionBean.getMethod();
		Class<?> clazz = actionBean.getClazz();
		Object object = null;
		try{
			object = clazz.newInstance();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		method.setAccessible(true);
		System.out.println("调用方法:" + method.toGenericString());
		Object[] paramObjects = null;
		Object resultObject = null;
		try{
			paramObjects = createActionMethodParamList(request, response, actionBean);
		}
		catch(Exception e){
			e.printStackTrace();
			new Exception("数据解析出错").printStackTrace();;
		}
		
		try{
			resultObject = method.invoke(object, paramObjects);
		}
		catch(Exception e) {
			new Exception("调用方法失败!").printStackTrace();
			response.setStatus(500);
		}
		try
		{
			// 处理放回结果
			handleActionMethodReturn(request, response, chain, resultObject);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return true;
		}
	}
	
	public static void writeText(HttpServletResponse response, String data) {
        try {
            // 设置响应头
            response.setContentType("text/plain"); // 以文本的形式写到前台
            response.setCharacterEncoding("utf-8"); // 防止中文乱码

            //data=JSONUtil.toJSON(data);
            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            writer.write(data); // 
            writer.flush();
            writer.close();
        } catch (Exception e) {
            //logger.error("在响应中写数据出错！", e);
            throw new RuntimeException(e);
        }
    }
	
	public static JSONResult  entityToJSON(Object obj,boolean format){
    	JSONResult jsonResult ;
    	if(format){
    		jsonResult = toJSONFormat(obj);
    	}else{
    		jsonResult = toJSON(obj);
    	}
    	return jsonResult;
    }
	
	private static SerializeConfig serializeConfig = new SerializeConfig(); 
	
	// 将 Java 对象转为 JSON 字符串
    public static <T> JSONResult toJSONFormat(T obj) {
    	JSONResult jsonResult = null;
        try {
        	jsonResult = DDDJSON.toJSONString(obj,serializeConfig,
            		SerializerFeature.SkipTransientField,
            		SerializerFeature.PrettyFormat,
            		SerializerFeature.BrowserCompatible,
            		SerializerFeature.WriteNullListAsEmpty,
            		SerializerFeature.WriteNullStringAsEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
    // 将 Java 对象转为 JSON 字符串
    public static <T> JSONResult toJSON(T obj) {
    	JSONResult jsonResult = null;
        try {
        	jsonResult = DDDJSON.toJSONString(obj,serializeConfig,
            		SerializerFeature.SkipTransientField,
            		SerializerFeature.BrowserCompatible,
            		SerializerFeature.WriteNullListAsEmpty,
            		SerializerFeature.WriteNullStringAsEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
    public static void writeJSONString(HttpServletResponse response, String data) {
        try {
            // 设置响应头
            response.setContentType("application/json"); // 指定内容类型为 JSON 格式
            response.setCharacterEncoding("utf-8"); // 防止中文乱码

            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
 
        	writer.write(data); // 转为 JSON 字符串
            writer.flush();
            writer.close();
        } catch (Exception e) {
            //logger.error("在响应中写数据出错！", e);
            throw new RuntimeException(e);
        }
    } 
    public static void writeText1(HttpServletResponse response, String data) {
        try {
            // 设置响应头
            response.setContentType("text/plain"); // 以文本的形式写到前台
            response.setCharacterEncoding("utf-8"); // 防止中文乱码

            //data=JSONUtil.toJSON(data);
            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            writer.write(data); // 
            writer.flush();
            writer.close();
        } catch (Exception e) {
            //logger.error("在响应中写数据出错！", e);
            throw new RuntimeException(e);
        }
    }
    
 // 将数据以 HTML 格式写入响应中（在 JS 中获取的是 JSON 字符串，而不是 JSON 对象）
    public static void writeHTML(HttpServletResponse response, Object data) {
        try {
            // 设置响应头
            response.setContentType("text/html"); // 指定内容类型为 HTML 格式
            response.setCharacterEncoding("utf-8"); // 防止中文乱码

            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            writer.write(data.toString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            //logger.error("在响应中写数据出错！", e);
            throw new RuntimeException(e);
        }
    }
 // 将数据以 HTML 格式写入响应中（在 JS 中获取的是 JSON 字符串，而不是 JSON 对象）
    public static void writeVMPage(HttpServletResponse response, VMPage vmPage) {
        try {
            // 设置响应头
            response.setContentType("text/html"); // 指定内容类型为 HTML 格式
            response.setCharacterEncoding("utf-8"); // 防止中文乱码

            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            
           // String stringTemplate = FileUtil.readeString(new File(vmPage.getVmPath()));
            
            //VelocityUtil.generate(stringTemplate, writer, vmPage.getData());
            VelocityUtil.generateStream(vmPage.getVmPath(), vmPage.getData(), writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            //logger.error("在响应中写数据出错！", e);
            throw new RuntimeException(e);
        }
    }
    
    public static void writeBytes(HttpServletResponse response,byte[] bytes) {
        try
        {
    		// 设置响应头
    		response.setContentType("application/octet-stream");
			OutputStream os = response.getOutputStream();
			os.write(bytes);  
		    os.flush();
		    os.close();
        } catch (Exception e) {
            //logger.error("在响应中写数据出错！",e);
            throw new RuntimeException(e);
        }
    }
    public static void writeStream(HttpServletResponse response,InputStream inputStream) {
        try
        {
    		// 设置响应头
    		response.setContentType("application/octet-stream");
			OutputStream os = response.getOutputStream();
			if(inputStream!=null){
				byte[] bytes = new byte[1024];
				int size = 0;
				while((size=inputStream.read(bytes))!=-1){
					os.write(bytes, 0, size);
				}
				inputStream.close();
			}
		    os.flush();
		    os.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	private void handleActionMethodReturn(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Object actionMethodResult)
	{
		if (actionMethodResult == null)
		{
			response.setHeader("datatype", "null");
			writeText(response, "null");
		}
		else if (Collection.class.isAssignableFrom(actionMethodResult.getClass()))
		{
			response.setHeader("datatype", "array");
			
			JSONResult jsonResult = entityToJSON(actionMethodResult, false);
			if(jsonResult.isHasReference())
			{
				response.setHeader("datatype", "arrayWithReference");
			}
			else
			{
				response.setHeader("datatype", "array");
			}
			writeJSONString(response,jsonResult.getJson());
			return;
		}		
		else if (actionMethodResult instanceof String )
		{
			// 返回text数据
			response.setHeader("datatype", "string");
			writeText1(response, actionMethodResult.toString()); 
			return;
		}
		else if ( actionMethodResult instanceof Integer)
		{
			response.setHeader("datatype", "integer");
			writeText1(response, actionMethodResult.toString()); 
			return;
		}
		else if (actionMethodResult instanceof Boolean)
		{
			response.setHeader("datatype", "boolean");
			writeText1(response, actionMethodResult.toString()); 
			return;
		}
		else if (actionMethodResult instanceof Float || actionMethodResult instanceof Double)
		{
			response.setHeader("datatype", "float");
			writeText1(response, actionMethodResult.toString()); 
			return;
		}		
		else if (actionMethodResult instanceof HTMLPage)
		{
			writeHTML(response, (HTMLPage) actionMethodResult);
		} else if (actionMethodResult instanceof VMPage)
		{
			writeVMPage(response, (VMPage) actionMethodResult);
		}else if (actionMethodResult instanceof byte[])
		{
			writeBytes(response, (byte[]) actionMethodResult);
		} else if (InputStream.class.isAssignableFrom(actionMethodResult.getClass()))
		{
			writeStream(response, (InputStream) actionMethodResult);
		} else if (actionMethodResult instanceof RedirectPage)
		{
			// 若为 Page 类型，则 转发 或 重定向 到相应的页面中
			RedirectPage page = (RedirectPage) actionMethodResult;
			
			// 获取路径
			String path = ((RedirectPage) actionMethodResult).genURL();
			// 让浏览器重定向到页面
			redirectRequest(path, request, response);
		} else if (actionMethodResult instanceof ForwardPage)
		{
			ForwardPage page = (ForwardPage) actionMethodResult;
			// 获取路径
			String jspPath = "";
			String path = jspPath + page.getPath();
			// 初始化请求属性
			Map<String, Object> data = page.getData();
			if (data != null && data.size() > 0)
			{
				for (Map.Entry<String, Object> entry : data.entrySet())
				{
					request.setAttribute(entry.getKey(), entry.getValue());
				}
			}
			// 转发请求
			forwardRequest(path, request, response);
		} else if (actionMethodResult instanceof ErrorPage)
		{// 错误页面的处理
			ErrorPage page = (ErrorPage) actionMethodResult;
			// 获取路径
			String jspPath = "";
			String path = jspPath + "/ErrorPage.jsp";
			request.setAttribute("exception", page.getException());
			Map<String, Object> data = page.getData();
			if (data != null && data.size() > 0)
			{
				for (Map.Entry<String, Object> entry : data.entrySet())
				{
					request.setAttribute(entry.getKey(), entry.getValue());
				}
			}
			// 跳转到错误页面
			forwardRequest(path, request, response);
		}else
		{
			// 返回JSON数据
 
			JSONResult jsonResult = entityToJSON(actionMethodResult, false);
			if(jsonResult.isHasReference())
			{
				response.setHeader("datatype", "objectWithReference");
			}
			else
			{
				response.setHeader("datatype", "object");
			}
			writeJSONString(response,jsonResult.getJson());
		 
		}
		
	}
	
	// 转发请求
    public static void forwardRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception e) {
            //logger.error("转发请求出错！", e);
        	System.err.println("转发请求出错！"+e.getMessage());
            throw new RuntimeException(e);
        }
    }
	
	// 重定向请求
    public static void redirectRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
        	if(path.startsWith("http")){
        		response.sendRedirect(path);
        	}else{
        		response.sendRedirect(request.getContextPath() + path);
        	}
        } catch (Exception e) {
            //logger.error("重定向请求出错！", e);
            System.err.println("重定向请求出错！"+e.getMessage());
            throw new RuntimeException(e);
        }
    }
	
	private Object[] createActionMethodParamList(HttpServletRequest request, HttpServletResponse response, ActionBean actionBean)
	{
		Map<String,Map<String,Type>> methodParamsAndType = actionBean.getParams();
		
		Map<String, String> bodyParameters = null;
		
		Object[] objs = new Object[methodParamsAndType.size()];
		int i = 0;
		for (String parameterName : methodParamsAndType.keySet())
		{
			Map<String, Type> parameterTypeMap = methodParamsAndType.get(parameterName);
			Class<?> parameterType = (Class<?>) parameterTypeMap.get("parameterType");
			
			if (parameterType.equals(HttpServletRequest.class))
			{
				objs[i] = request;
				i++;
				continue;
			} else if (parameterType.equals(HttpServletResponse.class))
			{
				objs[i] = response;
				i++;
				continue;
			}
			
			if(bodyParameters == null){
				bodyParameters=this.getBodyParameters(request);
				request.setAttribute("bodyParams", bodyParameters);
			}
			
			// 根据参数名称查找请求参数，如果找不到，则根据参数类型名取参数
			String parameterValue = getParamter(request, parameterName, bodyParameters);
			if (parameterValue == null)
			{
				parameterName = parameterType.getSimpleName();
				parameterName = parameterName.substring(0, 1).toLowerCase() + parameterName.substring(1);
				parameterValue = getParamter(request, parameterName, bodyParameters);
			}
			
			if (parameterValue == null)
			{
				objs[i] = null;
			}
			
			else if (parameterType.equals(String.class))
			{
				objs[i] = parameterValue;
			} else if (parameterType.equals(int.class) || parameterType.equals(Integer.class))
			{
				objs[i] = CastUtil.castInt(parameterValue);
			} else if (parameterType.equals(long.class) || parameterType.equals(Long.class))
			{
				objs[i] = CastUtil.castLong(parameterValue);
			} else if (parameterType.equals(double.class) || parameterType.equals(Double.class))
			{
				objs[i] = CastUtil.castDouble(parameterValue);
			} else if (parameterType.equals(float.class) || parameterType.equals(Float.class))
			{
				objs[i] = CastUtil.castFloat(parameterValue);
			} else if (parameterType.equals(boolean.class) || parameterType.equals(Boolean.class))
			{
				objs[i] = CastUtil.castBoolean(parameterValue);
			}
			
			else if (Collection.class.isAssignableFrom(parameterType))
			{
				Type type = parameterTypeMap.get("paraType");
				objs[i] = getGenericParamter(type, parameterValue);
			} else if (Map.class.isAssignableFrom(parameterType))
			{
				Type type = parameterTypeMap.get("paraType");
				objs[i] = getHashMapParamter(type, parameterValue);
				
			}
		    else{
				objs[i] = parseParametersWithBean(parameterType, parameterValue);
			}
			
			i++;
		}
		return objs;
	}
	
	 public static <T> T parseParametersWithBean(Class<T> t,String paramenterValue){

    	try {
			if(paramenterValue == null)
			{
				return null;
			}
			//T newT = JSON.parseObject(paramenterValue, t);
			T newT = fromJSON(paramenterValue, t);
			return newT;
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	// 将 JSON 字符串转为 Java 对象
    public static <T> T fromJSON(String json, Class<T> type) {
        T obj = null;
        try {
        	//匹配如：2014/03/02 03/2015/03 08/03/2013 将"/"替换为"-"
        	String dateRegpx = "([0-9]{4}/[0-9]{2}/[0-9]{2})|([0-9]{2}/[0-9]{4}/[0-9]{2})|([0-9]{2}/[0-9]{2}/[0-9]{4})";
        	Pattern p = Pattern.compile(dateRegpx);
    		Matcher m = p.matcher(json);
    		while (m.find()) {
    			String s = m.group();
    			String date=s.replaceAll("/","-");
    			json=json.replaceAll(s, date);
    		}
        	
            obj = JSON.parseObject(json, type);
        } catch (Exception e) {
           new Exception("json解析错误").printStackTrace();
        }
        return obj;
    }
	
	
	public static Map getHashMapParamter(Type type,String paramenterValue)
 	 {
			if(paramenterValue == null )
			{
				return null;
			}
			else
			{ 
				return  JSON.parseObject(paramenterValue, type);
			}
 	 }
	
	public static Collection<?> getGenericParamter(Type type,String paramenterValue)
 	 {
			if(paramenterValue == null )
			{
				return null;
			}
			else
			{ 
				return (Collection<?>) JSON.parseObject(paramenterValue, type);
			}
 	 }
	
	/**
     * 从request对象里面取得前台传递的参数，如果是get方式传递的就进行转码，防止中文乱码的问题
     * @param request
     * @param parameterName
     * @return
     */
    public static String getParamter(HttpServletRequest request,String parameterName,Map<String,String> bodyParameters){
    	String method  = request.getMethod();
    	String value = request.getParameter(parameterName);  
    	if(value!=null && method.toLowerCase().equals("get")){
    		try {
				return new String(value.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	if(value == null)
    	{
    		if(bodyParameters.get(parameterName) == null){
    			value = null;
    		}else{
    		    value = bodyParameters.get(parameterName);
    		}
    	}
    	return value;
    }
	
	private Map<String, String> getBodyParameters(HttpServletRequest request)
	{
		StringBuffer body = new StringBuffer();
		String line;
		try
		{
			line = request.getReader().readLine();
			while (line != null)
			{
				body.append(line + "\n");
				line = request.getReader().readLine();
			}
			if (body.length() == 0)
			{
				return new HashMap<String, String>();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
			return new HashMap<String, String>();
		}
		Map<String, String> bodyParameters = JSON.parseObject(body.toString(), new TypeReference<Map<String, String>>()
		{});
		return bodyParameters;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	/**
	 * 获取请求路径
	 */
	public static String getRequestPath(HttpServletRequest request)
	{
		String servletPath = request.getServletPath();
		String pathInfoString = org.apache.commons.lang.StringUtils.defaultIfEmpty(request.getPathInfo(), "");
		return servletPath + pathInfoString;
	}
	
	/**
	 * 判断是否为静态资源
	 */
	private boolean isStaticResource(String currentRequestPath){
		
		String staticResourceReg = ".+\\.((html)|(htm)|(jsp)|(png)|(gif)|(jpg)|(swf)|(css)|(js)|(map)|(woff))$";
		Matcher requestPathMatcher = Pattern.compile(staticResourceReg).matcher(currentRequestPath);
		boolean ismatcher = requestPathMatcher.matches();
		return ismatcher;
	}
	
	public String getRemortIP(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		return remoteAddr;
	}
}
