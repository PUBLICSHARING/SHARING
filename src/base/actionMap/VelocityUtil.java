package base.actionMap;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;


public class VelocityUtil {
	private static Properties velocityProperties = new Properties();
	private static DateTool dateTool = new DateTool();
	private static NumberTool numberTool = new NumberTool();
	private static VelocityEngine ve; 
	static{
		try {
			InputStream in = Loader.instance().getClass().getClassLoader().getResourceAsStream("velocity.properties");
			velocityProperties.load(in);
			in.close(); 
			ve = new VelocityEngine(velocityProperties);
			ve.init();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 根据指定的字符串模板返回合并的字符串
	 * @param stringTemplate 字符串模板
	 * @param velocityContext
	 * @return
	 */
	public static String generateString(String stringTemplate,VelocityContext velocityContext)
	{
		return generateString(stringTemplate, velocityContext, "UTF-8");
	}
	
	
	/**
	 * 根据指定的字符串模板返回合并的字符串
	 * @param stringTemplate 字符串模板
	 * @param velocityContext
	 * @return
	 */
	public static String generateString(String stringTemplate,VelocityContext velocityContext,String charset)
	{
		byte[] bytes = generateBytes(stringTemplate, velocityContext,charset);
		
		try {
			return new String(bytes,charset);
		} catch (UnsupportedEncodingException e) {
			return new String(bytes);
		}
	}
	
	/**
	 * 根据指定的字符串模板返回合并的字符串
	 * @param stringTemplate 字符串模板
	 * @param velocityContext 
	 * @return
	 */
	public static byte[] generateBytes(String stringTemplate,VelocityContext velocityContext,String charset) 
	{
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = null;
		try {
			outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream,charset);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
        generate(stringTemplate, outputStreamWriter, velocityContext);
        try {
			outputStreamWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream.toByteArray();
	}
	
	
	
	
	
	/**
	 * 
	 * @param stringTemplate 字符串模板
	 * @param writer  输出
	 * @param velocityContext 
	 */
	public static void generate(String stringTemplate,Writer writer,VelocityContext velocityContext)
	{
        org.apache.velocity.Template t=ve.getTemplate(stringTemplate,"utf-8");  
		t.merge(velocityContext, writer);
	}
	
	/**
	 *  
	 * @param fileTemplate 文件模板
	 * @param map 替换的值
	 * @return
	 * @throws IOException 
	 */
	public static void generateStream(String fileTemplate,Map<String, Object> map,Writer writer) throws IOException
	{
		Template template=ve.getTemplate(fileTemplate);
		
		
		map.put("date",dateTool);
        
        map.put("number", numberTool);
        
		VelocityContext velocityContext = new VelocityContext(map);
		template.merge(velocityContext, writer); 
		
	}	
}
