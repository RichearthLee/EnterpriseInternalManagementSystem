package com.mftcc.interior.report.filter.bean;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils{
	
	public static String KillNull(String str) {
		return null == str ? "" : str;
	}
	public static String KillNull(String str, String defStr) {
		return null == str ? defStr : str;
	}
	
	/**
	 * 处理null和空串。
	 * @param str
	 * @return
	 * @deprecated
	 * <pre>
	 * 可能为null的值请使用 {@link #killNull(String) }方法。
	 * 空字符串建议使用带默认值的 {@link #KillEmpty(String, String)}。
	 * </pre>
	 */
	@Deprecated
	public static String KillEmpty(String str) {
		return isEmpty(str) ? "" : str;
	}
	/**
	 * 处理null和空串。返回defStr指定的默认值。
	 * @param str
	 * @param defStr
	 * @return
	 */
	public static String KillEmpty(String str, String defStr) {
		return isEmpty(str) ? defStr : str;
	}
	/**
	 * 处理null、空串和全空白字符串。
	 * <pre>如果是空白字符串，则返回defStr，否则返回源字符串的trim。</pre>
	 * @param str
	 * @param defStr
	 * @return
	 */
	public static String KillBlankAndTrim(String str, String defStr) {
		return isBlank(str) ? defStr : str.trim();
	}
	/**
	 * 
	 * @名称 isMore
	 * @功能 num1大于num2 返回 1;num1等于num2 返回 0; num1小于num2 返回 -1;
	 * @param num1
	 * @param num2
	 * @return
	 * 
	 */
	public static int isMore(String num1, String num2) {
		if (isEmpty(num1)) {
			num1 = "0";
		}
		if (isEmpty(num2)) {
			num2 = "0";
		}
		BigDecimal data1 = new BigDecimal(num1);
		BigDecimal data2 = new BigDecimal(num2);
		return data1.compareTo(data2);
	}
	
	public static boolean isBigerZero(String s) {
		if (s == null || "".equals(s.trim())) {
			s = "0.00";
		}
		s = s.replaceAll(",", "");
		return isMore(s, "0.00") > 0;
	}
	
	/**
	 * 
	 * @名称 subStrRight
	 * @描述 从右边截取字符串
	 * @参数
	 * @param inputStr 输入字符串
	 * @参数
	 * @param count 截取长度
	 * @参数
	 * @return 截取后的字符串
	 * @返回值 String
	 * @作者 luanhaowei
	 * @时间 2011-9-29 下午01:59:58
	 */
	public static String subStrRight(String inputStr, int count) {
		if (isEmpty(inputStr)) {
			return "";
		}
		if (count == inputStr.length()) {
			return inputStr;
		}
		count = (count > inputStr.length()) ? inputStr.length() : count;
		return inputStr.substring(inputStr.length() - count, inputStr.length());
	}
	/**
	 * 
	 * 方法描述： 截取日期时分秒 20161202 19:41:09 成 20161202
	 * @param dateTime
	 * @return
	 * String
	 * @author 沈浩兵
	 * @date 2016-12-3 下午2:36:17
	 */
	public static String subDateTimeStr(String dateTime) {
		if (isEmpty(dateTime)) {
			return "";
		}
		return dateTime.split(" ")[0];
	}
	
	/**
	 * 数据库字段转驼峰格式
	 * @param column
	 * @return
	 */
	public static String columnToProperty(String column) {
        StringBuilder result = new StringBuilder();
        if (column == null || column.isEmpty()) {
            return "";
        } else if (!column.contains("_")) {
            return column.substring(0, 1).toLowerCase() + column.substring(1);
        } else {
            String[] columns = column.split("_");
            for (String columnSplit : columns) {
                if (columnSplit.isEmpty()) {
                    continue;
                }
                if (result.length() == 0) {
                    result.append(columnSplit.toLowerCase());
                } else {
                    result.append(columnSplit.substring(0, 1).toUpperCase()).append(columnSplit.substring(1).toLowerCase());
                }
            }
            return result.toString();
        }
    }
	
	/**
	 * 将数字字符串进行自增，并以0补齐长度（对原有长度判断）
	 * @param str
	 * @return
	 * @auth HanGuoXing
	 */
	public static String incrementStr(String str){
		String str1 = "";
		if(null == str || "".equals(str)){
			str1 = "1";
		}else{
			int num = Integer.parseInt(str)+1;
			str1 = String.valueOf(num);
			int length = str.length() - str1.length();
			if(length > 0){
				while(length-->0){
					str1 = "0"+str1;
				}
			}
		}
		return str1;
	}
	
	/**
	 * 
	 * @名称 arrayToString
	 * @描述 数组转换到字符串 StringUtil.join(str, "'", ",");StringUtil.join(str, "", ",")
	 * @参数
	 * @param array
	 *            数组
	 * @参数
	 * @param marks
	 *            数组转换之后每个元素用单引号还是其他可以是空
	 * @参数
	 * @param separator
	 *            每个元素之间的分隔符
	 * @参数
	 * @return
	 * @返回值 String
	 * @作者 luanhaowei
	 * @时间 2011-8-25 下午04:40:36
	 */
	public static String arrayToString(Object[] array, String marks, String separator) {
		if (separator == null) {
			separator = "";
		}
		int arraySize = array.length;
		int bufSize = (arraySize == 0) ? 0 : (array[0].toString().length() + separator.length()) * arraySize;

		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; ++i) {
			if (i > 0) {
				buf.append(separator);
			}
			buf.append(marks);
			buf.append(array[i]);
			buf.append(marks);
		}
		return buf.toString();
	}
	
	/**
	 * 
	 * 方法描述： 判断数组中是否包括某字符串
	 * @param array
	 * @param key
	 * @return
	 * boolean
	 * @author 栾好威
	 * @date 2017-10-24 下午3:55:46
	 */
	public static boolean arrayContainsString(String[] array, String key) {
		for (String tmpString : array) {
			if (tmpString.equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * 方法描述： 验证是否是数值
	 * @param str
	 * @return
	 * boolean
	 * @author 沈浩兵
	 * @date 2017-11-22 下午6:10:51
	 */
	public static boolean isNum(String str){

        Pattern pattern = Pattern.compile("^-?[0-9]+");
        if(pattern.matcher(str).matches()){
            //数字
            return true;
        } else {
            //非数字
            return false;
        }
    }
	/**
	 * 
	 * 方法描述： 验证是否为小数
	 * @param str
	 * @return
	 * boolean
	 * @author 沈浩兵
	 * @date 2017-11-22 下午6:10:32
	 */
    public static boolean isNumFloat(String str){
        //带小数的
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");

        if(pattern.matcher(str).matches()){
            //数字
            return true;
        } else {
            //非数字
            return false;
        }
    }
}
