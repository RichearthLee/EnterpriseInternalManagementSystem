/**
 * Copyright (C) DXHM 版权所有
 * @文件名 MathExtend.java
 * @包名 dxt.financemanage.comm.util
 * @说明 TODO(描述文件做什么)
 * @作者 XieZhenGuo
 * @时间 Dec 11, 2010 3:37:38 PM
 * @版本 V1.0
 */
package com.mftcc.interior.report.filter.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @类名 MathExtend
 * @描述 科学计数法
 * @作者 XieZhenGuo
 * @日期 Dec 11, 2010 3:37:38 PM ========修改日志=======
 * 
 */
public class MathExtend {
	// 默认除法运算精度
	private static final int DEFAULT_DIV_SCALE = 2;
	public static final int DEFAULT_DIV_SCALE_20 = 20;
	
	/**
	 * 舍入模式
	 */
	public enum RoundingType {
		/**
		 * 向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向上舍入。 (此舍入模式就是通常学校里讲的四舍五入)
		 */
		 HALF_UP(RoundingMode.HALF_UP),
		 
		 /**
		  * 向上取整
		  */
		 UP(RoundingMode.UP);

		private RoundingMode roundingMode;

		private RoundingType(RoundingMode roundingMode) {
			this.roundingMode = roundingMode;
		}
	}

	/**
	 * @作者: 王超
	 * @功能: 加
	 * @日期: 2015年1月26日 上午9:01:47
	 * @param addend
	 * @param augend
	 * @return
	 */
	public static double add(double addend, double augend) {
		String result = add(String.valueOf(addend), String.valueOf(augend));
		return Double.valueOf(result);
	}

	/**
	 * @作者: 王超
	 * @功能: 加
	 * @日期: 2015年1月26日 上午9:01:47
	 * @param addend
	 * @param augend
	 * @return
	 */
	public static String add(String addend, String augend) {
		addend = StringUtil.KillBlankAndTrim(addend, "0.00");
		augend = StringUtil.KillBlankAndTrim(augend, "0.00");
		BigDecimal addendBd = new BigDecimal(addend);
		BigDecimal augendBd = new BigDecimal(augend);
		return addendBd.add(augendBd).toPlainString();
	}

	/**
	 * 方法描述： 累加
	 * @param addends
	 * @return
	 * String
	 * @author wd
	 * @date 2016-05-31 
	 */
	public static String adds(String... addends) {
		String sum = "0.00";
		for (String addend : addends) {
			sum = add(addend, sum);
		}
		return sum;
	}
	
	/**
	 * @功能: 减
	 * @作者: 王超
	 * @日期: 2015年1月26日 上午9:02:06
	 * @param subtrahend 减数
	 * @param minuend 被减数
	 * @return
	 */
	public static double subtract(double subtrahend, double minuend) {
		String result =subtract(String.valueOf(subtrahend), String.valueOf(minuend));
		return Double.valueOf(result);
	}

	/**
	 * @功能: 减
	 * @作者: 王超
	 * @日期: 2015年1月26日 上午9:02:06
	 * @param subtrahend 减数
	 * @param minuend 被减数
	 * @return
	 */
	public static String subtract(String subtrahend, String minuend) {
		String result = "0.00";
		subtrahend = StringUtil.KillBlankAndTrim(subtrahend, "0.00");
		minuend = StringUtil.KillBlankAndTrim(minuend, "0.00");
		BigDecimal subtrahendBd = new BigDecimal(subtrahend);
		BigDecimal minuendBd = new BigDecimal(minuend);
		result = subtrahendBd.subtract(minuendBd).toPlainString();
		return result;
	}
	
	/**
	 * @作者: 王超
	 * @功能: 乘
	 * @日期: 2015年1月26日 下午4:23:16
	 * @param multiplier 乘数
	 * @param multiplicand 被乘数
	 * @return 默认四舍五入
	 */
	public static double multiply(double multiplier, double multiplicand) {
		String result=multiply(String.valueOf(multiplier),  String.valueOf(multiplicand));
		return Double.valueOf(result);
	}
	
	/**
	 * @作者: 王超
	 * @功能: 乘
	 * @日期: 2015年1月26日 下午4:23:16
	 * @param multiplier 乘数
	 * @param multiplicand 被乘数
	 * @param roundingType 舍入方式(为null时默认四舍五入)
	 */
	public static double multiply(double multiplier, double multiplicand, RoundingType roundingType) {
		String result=multiply(String.valueOf(multiplier),  String.valueOf(multiplicand),roundingType);
		return Double.valueOf(result);
	}

	/**
	 * @作者: 王超
	 * @功能: 乘
	 * @日期: 2015年1月26日 下午4:23:16
	 * @param multiplier 乘数
	 * @param multiplicand 被乘数
	 * @return 默认四舍五入
	 */
	public static String multiply(String multiplier, String multiplicand) {
		return multiply(multiplier, multiplicand, null);
	}

	/**
	 * @作者: 王超
	 * @功能: 乘
	 * @日期: 2015年1月26日 下午4:23:16
	 * @param multiplier 乘数
	 * @param multiplicand 被乘数
	 * @param roundingType 舍入方式(为null时默认四舍五入)
	 * @return
	 */
	public static String multiply(String multiplier, String multiplicand, RoundingType roundingType) {
		roundingType = roundingType == null ? RoundingType.HALF_UP : roundingType;
		multiplier = StringUtil.KillBlankAndTrim(multiplier, "0.00");
		multiplicand = StringUtil.KillBlankAndTrim(multiplicand, "0.00");

		BigDecimal multiplierBd = new BigDecimal(multiplier);
		BigDecimal multiplicandBd = new BigDecimal(multiplicand);

		return multiplierBd.multiply(multiplicandBd).toPlainString();
	}
	
	/**
	 * @作者: 王超
	 * @功能: 除
	 * @日期: 2015年1月26日 上午9:02:25
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @return 保留两位小数 四舍五入
	 */
	public static double divide(double divisor, double dividend) {
		return divide(divisor, dividend, DEFAULT_DIV_SCALE);
	}

	/**
	 * @作者: 王超
	 * @功能: 除
	 * @日期: 2015年1月26日 上午9:02:25
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @param scale 保留小数位数
	 * @return 四舍五入
	 */
	public static double divide(double divisor, double dividend, int scale) {
		String result = divide(String.valueOf(divisor), String.valueOf(dividend), scale, null);
		return Double.valueOf(result);
	}
	
	/**
	 * @作者: 王超
	 * @功能: 除
	 * @日期: 2015年1月26日 上午9:02:25
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @param scale 保留小数位数
	 * @param roundingType 舍入方式(为null时默认四舍五入)
	 */
	public static double divide(double divisor, double dividend, int scale, RoundingType roundingType) {
		String result = divide(String.valueOf(divisor), String.valueOf(dividend), scale, roundingType);
		return Double.valueOf(result);
	}

	/**
	 * @作者: 王超
	 * @功能: 除
	 * @日期: 2015年1月26日 上午9:02:25
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @return 保留两位小数 四舍五入
	 */
	public static String divide(String divisor, String dividend) {
		return divide(divisor, dividend, DEFAULT_DIV_SCALE);
	}

	/**
	 * @作者: 王超
	 * @功能: 除
	 * @日期: 2015年1月26日 上午9:02:25
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @param scale 保留小数位数
	 * @return 四舍五入
	 */
	public static String divide(String divisor, String dividend, int scale) {
		return divide(divisor, dividend, scale, null);
	}
	
	/**
	 * @作者: 王超
	 * @功能: 除
	 * @日期: 2015年1月26日 上午9:02:25
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @param scale 保留小数位数
	 * @param roundingType 舍入方式(为null时默认四舍五入)
	 * @return
	 */
	public static String divide(String divisor, String dividend, int scale, RoundingType roundingType) {
		roundingType = roundingType == null ? RoundingType.HALF_UP : roundingType;
		divisor = StringUtil.KillBlankAndTrim(divisor, "0.00");
		dividend = StringUtil.KillBlankAndTrim(dividend, "0.00");

		divisor=divisor.replace(",", "");
		dividend=dividend.replace(",", "");
		
		if (StringUtil.isMore(dividend, "0.00") == 0) {
			// 0不能做除数，0除以1是为了保证返回指定位数的小数
			return new BigDecimal("0").divide(new BigDecimal("1"), scale, roundingType.roundingMode).toPlainString();
		}

		BigDecimal divisorBd = new BigDecimal(divisor);
		BigDecimal dividendBd = new BigDecimal(dividend);

		return divisorBd.divide(dividendBd, scale, roundingType.roundingMode).toPlainString();
	}

	/**
	 * @作者: 王超
	 * @功能: 舍入小数
	 * @日期: 2015年1月26日 上午9:02:35
	 * @param num 需要舍入小数的数字
	 * @param scale 保留小数位数
	 * @return 四舍五入
	 */
	public static double round(double num, int scale) {
		String result = round(String.valueOf(num), scale, null);
		return Double.valueOf(result);
	}
	
	/**
	 * @作者: 王超
	 * @功能: 舍入小数
	 * @日期: 2015年1月26日 上午9:02:35
	 * @param num 需要舍入小数的数字
	 * @param scale 保留小数位数
	 * @return 四舍五入
	 */
	public static String round(String num, int scale) {
		return round(num, scale, null);
	}

	/**
	 * @作者: 王超
	 * @功能: 舍入小数
	 * @日期: 2015年1月26日 上午9:02:35
	 * @param num 需要舍入小数的数字
	 * @param scale 保留小数位数
	 * @param roundingType 舍入方式(为null时默认四舍五入)
	 * @return
	 */
	public static String round(String num, int scale, RoundingType roundingType) {
		return divide(num, "1", scale, roundingType);
	}
	
	/**
	 * @作者: 王超
	 * @功能: 舍入小数
	 * @日期: 2015年1月26日 上午9:02:35
	 * @param num 需要舍入小数的数字
	 * @param scale 保留小数位数
	 * @param roundingType 舍入方式(为null时默认四舍五入)
	 * @return
	 */
	public static double round(double num, int scale, RoundingType roundingType) {
		return divide(num, 1, scale, roundingType);
	}

	/**
	 * @名称 moneyStr
	 * @描述 金额转换
	 * @作者 XieZhenGuo
	 * @时间 Feb 24, 2011 11:16:28 AM
	 * @@param money
	 * @@return  保留两位小数 四舍五入
	 */
	public static String moneyStr(String money) {
		if ("".equals(money) || null == money) {
			money = "0";
		}
		DecimalFormat myFormat = new DecimalFormat("#,##0.00");
		myFormat.setRoundingMode(RoundingType.HALF_UP.roundingMode);
		String temp = myFormat.format(Double.valueOf(money));
		return temp;
	}
	

	/**
	 * @描述 "#,##0.00"金额转换，格式中<strong>“有逗号”</strong>分隔符。
	 * @param money Double
	 * @作者 XieZhenGuo
	 * @时间 Feb 24, 2011 11:16:28 AM
	 * @return String 保留两位小数 四舍五入
	 */
	public static String moneyStr(Double money) {
		if (money == null) {
			money = 0D;
		}
		DecimalFormat myFormat = new DecimalFormat("#,##0.00");
		myFormat.setRoundingMode(RoundingType.HALF_UP.roundingMode);
		String temp = myFormat.format(money);
		return temp;
	}
	
	/**
	 * 格式化金额千分位
	 * @param money
	 * @param scale  保留小数位0,1,2 四舍五入方式
	 * @return
	 */
	public static String moneyStr(String money, int scale) {
		if ("".equals(money) || null == money) {
			money = "0";
		}
		String formatStr = "#,##0.00";
		if (scale==0) {
			formatStr = "#,##0";
		}
		if (scale==1) {
			formatStr = "#,##0.0";
		}
		DecimalFormat myFormat = new DecimalFormat(formatStr);
		myFormat.setRoundingMode(RoundingType.HALF_UP.roundingMode);
		String temp = myFormat.format(Double.valueOf(money));
		return temp;
	}

	/**
	 * 格式化金额千分位
	 * @param money
	 * @param scale 保留小数位0,1,2 四舍五入方式
	 * @return
	 */
	public static String moneyStr(Double money, int scale) {
		if (money == null) {
			money = 0D;
		}
		String formatStr = "#,##0.00";
		if (scale==0) {
			formatStr = "#,##0";
		}
		if (scale==1) {
			formatStr = "#,##0.0";
		}
		DecimalFormat myFormat = new DecimalFormat(formatStr);
		myFormat.setRoundingMode(RoundingType.HALF_UP.roundingMode);
		String temp = myFormat.format(money);
		return temp;
	}
	
	/**
	 * @param money String  保留两位小数 四舍五入
	 * @描述 金额转换，格式中<strong>“不带逗号”</strong>分隔符。
	 * @作者 XieZhenGuo
	 * @时间 Feb 24, 2011 11:16:28 AM
	 * @return String
	 */
	public static String moneyStrNum(String money) {
		if ("".equals(money) || null == money) {
			money = "0.00";
		}
		String temp = round(money, 2);
		return temp;
	}
	
	/**
	 * 方法描述： 金额转换 保留两位小数 四舍五入
	 * @param money Double
	 * @return
	 * String
	 * @author Javelin
	 * @date 2017-5-25 上午10:48:31
	 */
	public static String moneyStrNum(Double money) {
		if (money == null) {
			money = 0D;
		}
		String temp = String.valueOf(round(money, 2));
		return temp;
	}


	/**
	 * 
	 * @名称 comparison
	 * @描述 两个数字进行比较
	 * @作者 XieZhenGuo
	 * @时间 Apr 1, 2011 10:20:10 AM
	 * @param v1
	 * @param v2
	 * @return 0：相等；1：大于；-1：小于
	 */
	public static int comparison(String v1, String v2) {
		int rflag = 0;
		v1 = StringUtil.KillBlankAndTrim(v1, "0.00");
		v2 = StringUtil.KillBlankAndTrim(v2, "0.00");
		BigDecimal a = new BigDecimal(v1);
		BigDecimal b = new BigDecimal(v2);
		rflag = a.compareTo(b);
		return rflag;
	}
	
	/**
	 * 计算两个数之间的商和余数
	 * @param divisor
	 * @param dividend
	 * @param scale商保留的小数位
	 * @return
	 */
	public static double[] calcQuotientAndRemainder(double divisor, int dividend,int scale){
		double result[] = new double[2];
		result[0]=divide(divisor, dividend, scale);
		double totalAmt = multiply(result[0], dividend);
		result[1] = subtract(divisor, totalAmt);
		return result;
	}
	
	/**
	 * 计算两个数之间的商和余数
	 * @param divisor
	 * @param dividend
	 * @param scale 商保留的小数位
	 * @return
	 */
	public static String[] calcQuotientAndRemainder(String divisor, int dividend,int scale){
		String result[] = new String[2];
		result[0]=divide(divisor, String.valueOf(dividend), scale);
		String totalAmt = multiply(result[0], String.valueOf(dividend));
		result[1] = subtract(divisor, totalAmt);
		return result;
	}
	
	/**
	 * 计算两个数之间的商和余数
	 * @param divisor
	 * @param dividend
	 * @param scale商保留的小数位
	 * @return
	 */
	public static double[] calcQuotientAndRemainder(double divisor, double dividend,int scale){
		double result[] = new double[2];
		result[0]=divide(divisor, dividend, scale);
		double totalAmt = multiply(result[0], dividend);
		result[1] = subtract(divisor, totalAmt);
		return result;
	}
	
	/**
	 * 计算两个数之间的商和余数
	 * @param divisor
	 * @param dividend
	 * @param scale 商保留的小数位
	 * @return
	 */
	public static String[] calcQuotientAndRemainder(String divisor, String dividend,int scale){
		String result[] = new String[2];
		result[0]=divide(divisor, String.valueOf(dividend), scale);
		String totalAmt = multiply(result[0], String.valueOf(dividend));
		result[1] = subtract(divisor, totalAmt);
		return result;
	}
	
	/**
	 * 把利率统一换算成年利率保存，年利率不带%号。
	 * @param rateType 利率类型：1--年利率(%)，2--月利率(‰)，3--日利率(‱)，4--月利率(%)
	 * @param rate 页面展示利率：如年利率7.2%，rate=7.2；月利率6‰，rate=6；日利率2‱，rate=2；月利率0.6% rate=0.6
	 * @param years 每年的天数：360/365
	 * @return 返回值 年利率不带%号
	 */
	public static double saveYearRateMethod(String rateType,double rate,int years){
		double result = 0.00;
		result =Double.valueOf(saveYearRateMethod(rateType, String.valueOf(rate), String.valueOf(years)));
		return result;
	}
	
	/**
	 * 把利率统一换算成年利率保存，年利率不带%号。
	 * @param rateType 利率类型：1--年利率(%),2--月利率(‰),3--日利率(‱)，4--月利率(%)，5--日利率(%)
	 * @param rate 页面展示利率：如年利率7.2%，rate=7.2；月利率6‰，rate=6；日利率2‱，rate=2；月利率0.6% rate=0.6；日利率0.02% rate=0.02
	 * @param years 每年的天数：360/365
	 * @return 返回值 年利率不带%号
	 */
	public static String saveYearRateMethod(String rateType,String rate,String years){
		String result = "0.00";
		//保留小数位 保存时保留6位小数
		int scale = 6;
		if("1".equals(rateType)){
			//年利率
			result = divide(rate, "1", scale);
		}
		if("2".equals(rateType)){
			//月利率6‰ 千分制，换算成年利率
			result = multiply(rate, "12");
			result = divide(result, "10", scale);
		}
		if("3".equals(rateType)){
			//日利率2（‱ ）百分制，换算成年利率
			result = multiply(rate, years);
			result = divide(result, "100", scale);
		}
		if("4".equals(rateType)){
			//月利率0.6% 百分制，换算成年利率
			result = multiply(rate, "12");
			result = divide(result, "1", scale);
		}
		if("5".equals(rateType)){
			//日利率0.02%百分制，换算成年利率
			result = multiply(rate, years);
			result = divide(result, "1", scale);
		}
		return result;
	}
	
	/**
	 * 从数据库取出年利率，换算成对应的利率，在页面展示
	 * @param rateType 利率类型：1--年利率(%),2--月利率(‰),3--日利率(‱)，4--月利率(%)，5--日利率(%)
	 * @param yearRate  数据表取出利率：年利率7.2%，yearRate=7.2
	 * @param years 每年的天数：360/365
	 * @return 返回利率类型对应的结果不带单位。 rateType=1返回年利率7.2；rateType=2 返回月利率6； rateType=3返回日利率2/1.97260；rateType=4 返回月利率0.6；
	  * rateType=5返回日利率0.02
	 */
	public static double showRateMethod(String rateType,double yearRate,int years){
		double result = 0.00;
		result = Double.valueOf(showRateMethod(rateType, String.valueOf(yearRate), String.valueOf(years)));
		return result;
	}
	
	/**
	 * 从数据库取出年利率，换算成对应的利率，在页面展示
	 * @param rateType 利率类型：1--年利率(%),2--月利率(‰),3--日利率(‱)，4--月利率(%)，5--日利率(%)
	 * @param yearRate  数据表取出利率：年利率7.2%，yearRate=7.2
	 * @param years 每年的天数：360/365
	 * @param scale 保留小数位
	 * @return 返回利率类型对应的结果不带单位。 rateType=1返回年利率7.2；rateType=2 返回月利率6； rateType=3返回日利率2/1.97260；rateType=4 返回月利率0.6；
	  * rateType=5返回日利率0.02
	 */
	public static double showRateMethod(String rateType,double yearRate,int years, int scale){
		double result = 0.00;
		result = Double.valueOf(showRateMethod(rateType, String.valueOf(yearRate), String.valueOf(years), scale));
		return result;
	}
	
	/**
	 * 从数据库取出年利率，换算成对应的利率，在页面展示
	 * @param rateType 利率类型：1--年利率(%),2--月利率(‰),3--日利率(‱)，4--月利率(%)，5--日利率(%)
	 * @param yearRate  数据表取出利率：年利率7.2%，yearRate=7.2
	 * @param years 每年的天数：360/365
	 * @return 返回利率类型对应的结果不带单位。 rateType=1返回年利率7.2；rateType=2 返回月利率6； rateType=3返回日利率2/1.97260；rateType=4 返回月利率0.6；
	  * rateType=5返回日利率0.02
	 */
	public static String showRateMethod(String rateType,String yearRate,String years){
		String result = "0.00";
		//保留小数位数
		int scale = 2;
		result = showRateMethod(rateType, yearRate, years, scale);
		return result;
	}
	
	/**
	 * 从数据库取出年利率，换算成对应的利率，在页面展示
	 * @param rateType 利率类型：1--年利率(%),2--月利率(‰),3--日利率(‱)，4--月利率(%)，5--日利率(%)
	 * @param yearRate  数据表取出利率：年利率7.2%，yearRate=7.2
	 * @param years 每年的天数：360/365
	 * @param scale 保留小数位
	 * @return 返回利率类型对应的结果不带单位。 rateType=1返回年利率7.2；rateType=2 返回月利率6； rateType=3返回日利率2/1.97260；rateType=4 返回月利率0.6；
	 * rateType=5返回日利率0.02
	 */
	public static String showRateMethod(String rateType,String yearRate,String years,int scale){
		String result = "0.00";
		if("1".equals(rateType)){
			//年利率百分制（%）
			result = divide(yearRate, "1", scale);
		}
		if("2".equals(rateType)){
			//月利率千分制（‰）
			result = divide(yearRate, "1.2", scale);
		}
		if("3".equals(rateType)){
			//日利率万分制（‱）
			result = divide(years, "100",10);
			result = divide(yearRate, result, scale);
		}
		if("4".equals(rateType)){
			//月利率百分制（%）
			result = divide(yearRate, "12", scale);
		}
		if("5".equals(rateType)){
			//日利率百分制（%）
			result = divide(yearRate, years, scale);
		}
		return result;
	}
	
	/**
	 * 把年利率换算成小数，参与相关利息计算
	 * @param yearRate 年利率 ：7.2
	 * @return 得到年利率 返回 0.072
	 */
	public static double calcRateYear(double yearRate){
		double result = 0.00;
		result = Double.valueOf(calcRateYear(String.valueOf(yearRate)));
		return result;
	}
	
	/**
	 * 把年利率换算成小数，参与相关利息计算
	 * @param yearRate 年利率 ：7.2
	 * @return 得到年利率 返回 0.072
	 */
	public static String calcRateYear(String yearRate){
		String result = "0.00";
		//保留小数位数
		int scale = 10;
		result = divide(yearRate, "100",scale);
		return result;
	}
	
	/**
	 * 把年利率换算成月利率到的小数，参与相关利息计算
	 * @param yearRate 年利率 ：7.2
	 * @return 返回 0.006
	 */
	public static double calcRateMonth(double yearRate){
		double result = 0.00;
		result = Double.valueOf(calcRateMonth(String.valueOf(yearRate)));
		return result;
	}
	
	/**
	 * 把年利率换算成月利率到的小数，参与相关利息计算
	 * @param yearRate 年利率 ：7.2
	 * @return 返回 0.006
	 */
	public static String calcRateMonth(String yearRate){
		String result = "0.00";
		//保留小数位数
		int scale = 13;
		result = divide(yearRate, "100",scale);
		result = divide(result, "12",scale);
		return result;
	}
	
	/**
	 * 把年利率换算成日利率到的小数，参与相关利息计算
	 * @param yearRate 年利率 ：7.2
	 * @param years 每年的天数：360/365
	 * @return 返回 0.0002/0.00019726027
	 */
	public static double calcRateDays(double yearRate,int years){
		double result = 0.00;
		result = Double.valueOf(calcRateDays(String.valueOf(yearRate),years));
		return result;
	}
	
	/**
	 * 把年利率换算成日利率到的小数，参与相关利息计算
	 * @param yearRate 年利率 ：7.2
	 * @param years 每年的天数：360/365
	 * @return 返回 0.0002/0.00019726027
	 */
	public static String calcRateDays(String yearRate,int years){
		String result = "0.00";
		//保留小数位数
		int scale = 10;
		result = divide(yearRate, "100",scale);
		result = divide(result, String.valueOf(years),scale);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：计提应收利息时计算实际利率，与Excel的函数作用一致 ：RATE(18, 7055.56, 100000)=0.0264690896793
	 * 
	 * @param nper 期数
	 * @param pmt 期供本息合计
	 * @param pv 贷款本金
	 * @return 月利率已经转化为小数 0.0264690896793
	 * @author 栾好威
	 * @date 2017-8-15 下午5:05:53
	 */
	public static double calcRealRate(double nper, double pmt, double pv) {
		double rate = 1.00;
		double x = 0.00;
		double jd = 0.1;
		double side = 0.1;
		double i = 1;
		// 保留小数位数
		int scale = 20;
		// 计算200次，比Excel20次要精确
		int cnt = 200;
		do {
			x = pv / pmt - (Math.pow(1 + rate, nper) - 1) / (Math.pow(rate + 1, nper) * rate);
			if (x * side > 0) {
				side = -side;
				jd *= 10;
			}
			rate += side / jd;
		} while (i++ < cnt && Math.abs(x) >= 1 / Math.pow(10, scale));
		if (i > cnt){
			return 0.00;
		}
		return rate;
	}
	  /**
	   * 
	   * 方法描述：验证字符串是否是金额  是 返回true  否返回false
	   * @param str
	   * @return
	   * boolean
	   * @author WD
	   * @date 2017-11-1 下午5:49:35
	   */
	public static boolean judgeNumber(String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		} else {
			Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
			Matcher match = pattern.matcher(str);
			if (match.matches() == false) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	/**
	 * 
	 * 方法描述： 计算等额本息提前还款后的剩余期数
	 * @param yearRate 年利率
	 * @param prcpIntstSum 月还款额即本息合计
	 * @param loanBal 提前还款后剩余贷款本金
	 * @return
	 * int
	 * @author 栾好威
	 * @date 2017-12-1 下午5:49:48
	 */
	public static int calcFixedPaymentMortgageNper(double yearRate, double prcpIntstSum, double loanBal) {
		/**
		 * 设贷款年利率P,p=P/12,就是月利率；月还款额 R
		 * 提前还款后余下的贷款总额Q
		 * 则还后的期数（月）=ln(R/(R-Q*p)) / ln (1+p)
		 * ln为自然对数  Math.log
		 */
		int nper = 0;
		double monthRate = calcRateMonth(yearRate);
		nper = (int)divide(Math.log(divide(prcpIntstSum,(subtract(prcpIntstSum,multiply(loanBal,monthRate))))),Math.log(add(1, monthRate)),0);
		return nper;
	}
	/**
	 * 
	 * 方法描述： 计算等额本息期供/月还款额
	 * @param loanAmt 贷款金额
	 * @param yearRate 年利率
	 * @param nper 贷款期数
	 * @return
	 * double
	 * @author 栾好威
	 * @date 2017-12-23 下午6:13:34
	 */
	public static double calcNperIntst(double loanAmt, double yearRate, double nper) {
		/**
		 * 还款本息总和＝(贷款金额*(年利率/12)*Math.pow((1+利率/12),贷款期数))/(Math.pow(1+利率/12,贷款期数)-1)
		 */
		double nperIntst = 0.00;
		//月利率
		double monthRate = calcRateMonth(yearRate);
		//1＋月利率
		double monthRateAdd = add(monthRate, 1);
		//(1＋月利率)^还款月数
		double monthRatePow=Math.pow(monthRateAdd, nper);
		//(1＋月利率)^还款月数－1
		double monthRatePowSubtract=monthRatePow-1;
		nperIntst = (loanAmt*monthRate*monthRatePow)/monthRatePowSubtract;
		nperIntst = divide(nperIntst, 1, 2);
		return nperIntst;
	}
	
	/**
	 * 
	 * 方法描述： 等额本息计算本息合计总额
	 * @param loanAmt 贷款金额
	 * @param yearRate 年利率
	 * @param nper 贷款期数
	 * @return
	 * double
	 * @author 栾好威
	 * @date 2017-12-23 下午6:13:34
	 */
	public static double calcPrcpIntstSum(double loanAmt, double yearRate, double nper) {
		/**
		 * 还款本息总和＝(贷款金额*(年利率/12)*Math.pow((1+利率/12),贷款期数))/(Math.pow(1+利率/12,贷款期数)-1)*贷款期数
		 */
		double prcpIntstSum = 0.00;
		prcpIntstSum = calcNperIntst(loanAmt, yearRate, nper)*nper;
		prcpIntstSum = divide(prcpIntstSum, 1, 2);
		return prcpIntstSum;
	}
}
