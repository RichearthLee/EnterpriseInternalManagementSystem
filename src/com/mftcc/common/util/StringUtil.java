package com.mftcc.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * @作者 乾之轩
 * @功能 String工具类
 * 
 */

public class StringUtil extends StringUtils {
   
	/**
	 * 功能:获取当前系统日期
	 * @param dateFormat 
	 * @return
	 */
	public static String getSystemDate(String dateFormat){
		Date date = new Date();
		SimpleDateFormat myformate = new SimpleDateFormat(dateFormat);
		return myformate.format(date);
	}
	
	
	/**
	 * 功能:系统使用的唯一主键
	 * @param sub 生成主键的前缀
	 * @return
	 */
	public static synchronized String getId(String sub) {
		String id = UUID.randomUUID().toString();
		if(isEmpty(sub)){
			id=id.replace("-", "");
		}else{
			id=sub+"_"+id.replace("-", "");
		}
		return id;
	}
	/**
	 * 功能:获取没有前缀的主键
	 * @return
	 */
	public static  String getId() {
		return getId(null);
	}
	
	public static String killNull(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}
	
	/**
	 * 方法描述： null以及空串，均返回指定的默认字符串
	 * @param str
	 * @param defualt
	 * @return
	 * String
	 * @author LiuYF
	 * @date 2015-12-29 上午10:24:48
	 */
	public static String killEmpty(String str, String defualt) {
		if (str == null) {
			return defualt;
		}
		if (str.isEmpty()) {
			return defualt;
		}
		return str;
	}

	/**
	 * 相减
	 * @param a
	 * @param b
	 * @return
	 */
	public static String Subtraction(String a, String b) {
		if (a==null||b==null) {
			return null;
		}
		return String.valueOf(Double.parseDouble(a)-Double.parseDouble(b));
	}
	/**
	 * 相加 int
	 * @param a
	 * @param b
	 * @return
	 */
	public static String addition(String a, String b) {
		if (a==null||b==null) {
			return null;
		}
		return String.valueOf(Integer.parseInt(a)+Integer.parseInt(b));
	}
	/**
	 * 相加 Double
	 * @param a
	 * @param b
	 * @return
	 */
	public static String addition_d(String a, String b) {
		if (a==null||b==null) {
			return null;
		}
		return String.valueOf(Double.parseDouble(a)+Double.parseDouble(b));
	}
	/**
	 * 相除并取整（用于获取短信条数）
	 * @param a
	 * @param b
	 * @return
	 */
	public static String division(String a, String b) {
		if (a==null||b==null||b.equals("0")) {
			return null;
		}
		
		Double d = Math.ceil(Double.parseDouble(a)/Double.parseDouble(b));
		int i = Integer.parseInt(new DecimalFormat("0").format(d));
		return String.valueOf(i);
	}
	/**
	 * 方法描述：获得主键


	 *
	 * @param _prefix   传入的字符串
	 * @return
	 * String
	 * @author 乾之轩



	 * @date 2012-5-12 上午08:50:52
	 */
	private static int _suffix = 0;
	public static synchronized String getPK(String _prefix) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String strTmp = f.format(now);
		if (_suffix > 9){
			_suffix = 0;
		}
		strTmp = _prefix + strTmp + _suffix;
		_suffix++;
		return strTmp;
	}

}
