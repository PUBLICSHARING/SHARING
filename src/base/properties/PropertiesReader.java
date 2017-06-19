package base.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	private String propertiesUrl;
	
	private Properties properties;
	/**
	 * src下的properties文件，url格式：/***.properties;
	 * 此类暂时只支持src下的properties文件
	 * @param relativeSRCPropertiesUrl
	 */
	public PropertiesReader(String relativeSRCPropertiesUrl) {
		this.propertiesUrl = relativeSRCPropertiesUrl;
		this.properties = loadProperties(relativeSRCPropertiesUrl);
	}
	
	/**
	 * 获取value
	 * @param key 键
	 */
	public String getProperty(String key) {
		return this.properties.getProperty(key);
	}
	
	/**
	 * 读取properties
	 * @param relativeSRCPropertiesUrl
	 * @return
	 */
	public synchronized static Properties loadProperties(String relativeSRCPropertiesUrl) {
		Properties properties = new Properties();
		try{
			
			String path = PropertiesReader.class.getResource(relativeSRCPropertiesUrl).getPath();
			InputStream inputStream = new BufferedInputStream(new FileInputStream(path));
			properties.load(inputStream);
			inputStream.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	public String getPropertiesUrl() {
		return propertiesUrl;
	}

	public Properties getProperties() {
		return properties;
	}
}
