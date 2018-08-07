package com.mftcc.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathExtend {
	//利率运算位数
	public static final int RATE_SCALE = 20;
	// 默认除法运算精度
	private static final int DEFAULT_DIV_SCALE = 2;

	/**
	 * 
	 * 提供精确的加法运算
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @return 两个参数的和
	 * 
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 
	 * 提供精确的加法运算
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @return 两个参数数学加和，以字符串格式返回
	 * 
	 */
	public static String add(String v1, String v2) {
		if ("".equals(v1) || null == v1) {
			v1 = "0";
		}
		if ("".equals(v2) || null == v2) {
			v2 = "0";
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).toString();
	}
	
	/**
	 * 方法描述： 提供精确的加法运算
	 * @param s
	 * @return	多个参数数学加和，以字符串格式返回
	 * String
	 * @author Javelin
	 * @date 2016-8-25 下午4:30:39
	 */
	public static String add(String ...s) {
		BigDecimal temp = new BigDecimal("0");
		for(String str:s){
			if ("".equals(str) || null == str) {
				str = "0";
			}
			BigDecimal b1 = new BigDecimal(str);
			temp = temp.add(b1);
		}
		return temp.toString();
	}

	/**
	 * 
	 * 提供精确的减法运算
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @return 两个参数的差
	 * 
	 */

	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 
	 * 提供精确的减法运算
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @return 两个参数数学差，以字符串格式返回
	 * 
	 */
	public static String subtract(String v1, String v2) {
		if ("".equals(v1) || null == v1) {
			v1 = "0";
		}
		if ("".equals(v2) || null == v2) {
			v2 = "0";
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).toString();
	}

	/**
	 * 
	 * 提供精确的乘法运算
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @return 两个参数的积
	 * 
	 */
	public static double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 
	 * 提供精确的乘法运算
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @return 两个参数的数学积，以字符串格式返回
	 * 
	 */

	public static String multiply(String v1, String v2) {
		if ("".equals(v1) || null == v1) {
			v1 = "0";
		}
		if ("".equals(v2) || null == v2) {
			v2 = "0";
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).toString();
	}

	/**
	 * 
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 
	 * 小数点以后DEFAULT_DIV_SCALE位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @return 两个参数的商
	 * 
	 */
	public static double divide(double v1, double v2) {
		return divide(v1, v2, DEFAULT_DIV_SCALE);
	}

	/**
	 * 
	 * 提供（相对）精确的除法运算当发生除不尽的情况时，由scale参数指
	 * 
	 * 定精度，以后的数字四舍五入舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * 
	 * @return 两个参数的商
	 * 
	 */
	public static double divide(double v1, double v2, int scale) {
		return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 
	 * 提供（相对）精确的除法运算当发生除不尽的情况时，由scale参数指
	 * 
	 * 定精度，以后的数字四舍五入舍入模式采用用户指定舍入模式
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * 
	 * @param round_mode
	 *            表示用户指定的舍入模式
	 * 
	 * @return 两个参数的商
	 * 
	 */

	public static double divide(double v1, double v2, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, round_mode).doubleValue();
	}

	/**
	 * 
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 
	 * 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @return 两个参数的商，以字符串格式返回
	 * 
	 */
	public static String divide(String v1, String v2) {
		return divide(v1, v2, DEFAULT_DIV_SCALE);
	}

	/**
	 * 
	 * 提供（相对）精确的除法运算当发生除不尽的情况时，由scale参数指
	 * 
	 * 定精度，以后的数字四舍五入舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * 
	 * @return 两个参数的商，以字符串格式返回
	 * 
	 */
	public static String divide(String v1, String v2, int scale) {
		return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 方法描述： 定精度，以后的数字四舍五入舍入模式采用ROUND_HALF_EVEN
	 * @param v1
	 * @param v2
	 * @param scale  表示需要精确到小数点以后几位
	 * @return
	 * String	 两个参数的商，以字符串格式返回
	 * @author liujianbing
	 * @date May 10, 2012 9:05:44 AM
	 */
	public static String dividel(String v1, String v2, int scale) {
		return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
	}
	/**
	 * 
	 * 提供（相对）精确的除法运算当发生除不尽的情况时，由scale参数指
	 * 
	 * 定精度，以后的数字四舍五入舍入模式采用用户指定舍入模式
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * 
	 * @param round_mode
	 *            表示用户指定的舍入模式
	 * 
	 * @return 两个参数的商，以字符串格式返回
	 * 
	 */
	public static String divide(String v1, String v2, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		if ("".equals(v1) || null == v1) {
			v1 = "0";
		}
		if ("".equals(v2) || null == v2) {
			v2 = "0";
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, round_mode).toString();
	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @return 四舍五入后的结果
	 * 
	 */
	public static double round(double v, int scale) {
		return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @param round_mode
	 *            指定的舍入模式
	 * 
	 * @return 四舍五入后的结果
	 * 
	 */
	public static double round(double v, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, round_mode).doubleValue();
	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @return 四舍五入后的结果，以字符串格式返回
	 * 
	 */
	public static String round(String v, int scale) {
		return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @param round_mode
	 *            指定的舍入模式
	 * 
	 * @return 四舍五入后的结果，以字符串格式返回
	 * 
	 */
	public static String round(String v, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		if ("".equals(v) || null == v) {
			v = "0";
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, round_mode).toString();
	}

	/**
	 * 
	 * @名称 moneyStr
	 * @描述 金额转换
	 * @作者 XieZhenGuo
	 * @时间 Feb 24, 2011 11:16:28 AM
	 * @@param money
	 * @@return
	 */
	public static String moneyStr(String money) {
		if ("".equals(money) || null == money) {
			money = "0";
		}
		DecimalFormat myFormat = new DecimalFormat("#,##0.00");
		String temp = myFormat.format(Double.valueOf(money));
		return temp;
	}

	/**
	 * 
	 * @名称 moneyStr
	 * @描述 金额转换
	 * @作者 XieZhenGuo
	 * @时间 Feb 24, 2011 11:16:28 AM
	 * @@param money
	 * @@return
	 */
	public static String moneyStr(Double money) {
		DecimalFormat myFormat = new DecimalFormat("#,##0.00");
		String temp = myFormat.format(money);
		return temp;
	}
	
	/**
	 * 
	 * @名称 moneyStr
	 * @描述 金额转换
	 * @作者 XieZhenGuo
	 * @时间 Feb 24, 2011 11:16:28 AM
	 * @@param money
	 * @@return
	 */
	public static String moneyStrNum(String money) {
		if ("".equals(money) || null == money) {
			money = "0.00";
		}
		DecimalFormat myFormat = new DecimalFormat("###0.00");
		String temp = myFormat.format(Double.valueOf(money));
		return temp;
	}

	/**
	 * 
	 * @名称 comparison
	 * @描述 两个数字进行比较
	 * @作者 XieZhenGuo
	 * @时间 Apr 1, 2011 10:20:10 AM
	 * @@param v1
	 * @@param v2
	 * @@return 0：相等；1：大于；-1：小于
	 */
	public static int comparison(String v1, String v2) {
		int rflag = 0;
		if ("".equals(v1) || null == v1) {
			v1 = "0";
		}
		if ("".equals(v2) || null == v2) {
			v2 = "0";
		}
		BigDecimal a = new BigDecimal(v1);
		BigDecimal b = new BigDecimal(v2);
		rflag = a.compareTo(b);
		return rflag;
	}
	/**
	 * 整除方法（例如    123/100=1）
	 * @param v1
	 * @param v2
	 * @param scale
	 * @param round_mode
	 * @return
	 */
	public static String divideToIntegra(String v1, String v2) {
		if ("".equals(v1) || null == v1) {
			v1 = "0";
		}
		if ("".equals(v2) || null == v2) {
			v2 = "0";
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divideToIntegralValue(b2).toString();
	}

	
	public static void main(String[] args) {
		String a="105";
		String b="100";
		 BigDecimal aa=new BigDecimal(a);
		 BigDecimal bb=new BigDecimal(b);
		String c=MathExtend.subtract(a, b);
		String cc=aa.divideToIntegralValue(bb).toString();
		
		System.out.println("cc的值="+cc);
	}
}
