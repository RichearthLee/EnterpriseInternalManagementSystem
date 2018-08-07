package com.mftcc.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能:读取属性文件
 * 
 * @author dx
 */
public class PropertiesUtil {

	/**
	 * 功能:加载属性文件(该文在tomcat的根目录下面)如在system.propertis
	 * 存在于tomcat/seting/system.propertis
	 * 那么fileName的值为seting/system.propertis或是/seting/system.propertis
	 * 
	 * @param fileName
	 *            属性文件
	 * @return
	 */
	private static Properties load(String fileName) {
		// 获取tomcat的根目录
		String path = PropertiesUtil.class.getResource("").getPath();
		path = path.substring(0, path.indexOf("webapps"));

		if (StringUtils.isEmpty(fileName)) {
			fileName = "seting" + File.separator.toString()
					+ "system.properties";
		}
		try {
			File f = new File(path + fileName);
			if (!f.exists()) {
				System.out.println("-----f.createNewFile(); error ");

				 f.createNewFile();
			}
			Properties p = new Properties();
		 	InputStream is = new FileInputStream(f);
		 	p.load(is);
		 	is.close();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能:读取指定属性属性文件中的值
	 * 
	 * @param fileName
	 *            文件名称(相对于tomcat的根目录) 加载属性文件(该文在tomcat的根目录下面)如在system.propertis
	 *            存在于tomcat/seting/system.propertis
	 *            那么fileName的值为seting/system.propertis或是/seting/system.propertis
	 * @param key
	 *            配置文件中的key
	 * @return
	 */
	public static String getValue(String fileName, String key) {
		String value = load(fileName).get(key).toString();
		return value;
	}

	/**
	 * 功能:读取默认属性文件中的值(tomcat/seting/system.propertis)
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String value = null;
		try {
			  value =  load(null).get(key).toString();
		} catch (Exception e) {
			// TODO: handle exception

		}
	
		return value;
	}
	/**
	 * 功能:读取属性文件cloud.properties中的值(src/cloud.properties)
	 * <br/>
	 * 当key = "cloud.ip"时 返回"http://"+value + "/cloudRegister"
	 * @param key
	 * @return
	 */
	public static String getCloudProperties(String key){
		String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		InputStream in;
		String value = null;
		try {
			in = new FileInputStream(path+"cloud.properties");
			Properties p = new Properties();
			p.load(in);
			value = p.getProperty(key);
			if("cloud.ip".equals(key)){
				value =  "http://"+value + "/cloudRegister";
			}
		} catch (FileNotFoundException e) {
			System.out.println("----FileNotFoundException");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
