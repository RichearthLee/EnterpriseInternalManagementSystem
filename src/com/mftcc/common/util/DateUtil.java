package com.mftcc.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * 功能描述：日期处理工具类（基于Calendar） 主要功能：日期校验；获取系统当前日期（可自定义系统日期）；判断闰年；获取连个日期之间的天数，月数；
 * 判定日期的前后；将字符串转换为Date或Calendar等... 日期格式默认：yyyyMMdd
 *
 * @author dhcc WangShanfang
 * @date 2008-11-21
 * @see null
 * @修改日志：1.0
 */
public class DateUtil extends Object {
	private static Logger log = Logger.getLogger("WOWS");

	/**
	 * 当前操作系统日期 Calendar
	 */
	private static Calendar calendar = new GregorianCalendar(TimeZone
			.getDefault());
	/**
	 * 日期格式 默认：yyyyMMdd
	 */
	private static String pattern = "yyyyMMdd";
	/**
	 * 时间格式 默认：HH:mm:ss
	 */
	private static String timePattern = "HH:mm:ss";

	/**
	 * 年
	 */
	private static int year = 0;
	/**
	 * 月
	 */
	private static int month = 0;
	/**
	 * 日
	 */
	private static int day = 0;
	/**
	 * 时
	 */
	private static int hour = 0;
	/**
	 * 分
	 */
	private static int minute = 0;
	/**
	 * 秒
	 */
	private static int second = 0;

	/**
	 * 静态初始化（默认系统当前日期和时间）
	 */
	static {
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);
	}

	/**
	 * 构造方法
	 */
	public DateUtil() {
		// Do Nothing
	}

	/**
	 * 功能描述：自定义系统时间。（谨慎使用）
	 *
	 * @param strdate
	 *            自定义日期字符串，格式：yyyymmdd
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static void setSysDate(String strdate) {
		if (isDateStr(strdate)) {
			calendar = parseCalendar(strdate);

			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
			day = calendar.get(Calendar.DAY_OF_MONTH);
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			second = calendar.get(Calendar.SECOND);
		}
	}

	/**
	 * 功能描述： 初始化系统日期(当前系统日期)调用setSysDate()后会用到次方法重新初始化系统日期时间 为当前日期时间
	 *
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static void initSys() {
		calendar = new GregorianCalendar(TimeZone.getDefault());

		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：获取系统当前日期---年
	 *
	 * @return int 年
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static int getYear() {
		return year;
	}

	/**
	 * 功能描述：获取系统当前日期---年
	 *
	 * @return String 年
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static String getStrYear() {
		return String.valueOf(year);
	}

	/**
	 * 功能描述：获取系统当前日期---月
	 *
	 * @return int 月
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static int getMonth() {
		return month;
	}

	public static int getYear(String strDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		Date startDate;
		int intYear = 0;
		try {
			startDate = f.parse(strDate);
			Calendar starCal = Calendar.getInstance();
			starCal.setTime(startDate);
			intYear = starCal.get(Calendar.YEAR);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return intYear;
	}

	public static int getMonth(String strDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		Date startDate;
		int intMonth = 0;
		try {
			startDate = f.parse(strDate);
			Calendar starCal = Calendar.getInstance();
			starCal.setTime(startDate);
			intMonth = starCal.get(Calendar.MONTH) + 1;
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return intMonth;
	}
	
	/**
	 * 
	 * 方法描述：将短时间格式字符串转换为时间 yyyy-MM-dd 
	 * @param strDate 日期 YYYYMMDD
	 * @return
	 * Date
	 * @author XieZhenGuo
	 * @date 2013-1-5 上午11:36:36
	 */
	public static String shotToDate(String strDate) {
		String strtodate=strDate.substring(0, 4)+"-"+strDate.substring(4, 6)+"-"+strDate.substring(6);
		return strtodate;
	}
	/**
	 * 
	 * 方法描述： 将短时间格式字符串转换为时间 HH:mm:ss
	 * @param strTime 时间 HHmmss
	 * @return
	 * String
	 * @author XieZhenGuo
	 * @date 2013-1-5 上午11:44:01
	 */
	public static String shotToTime(String strTime) {
		String strtodate=strTime.substring(0, 2)+":"+strTime.substring(2, 4)+":"+strTime.substring(4);
		return strtodate;
	}

	/**
	 * 功能描述：获取系统当前日期---月
	 *
	 * @return String 月
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static String getStrMonth() {
		return month >= 10 ? String.valueOf(month) : "0"
				+ String.valueOf(month);
	}

	/**
	 * 功能描述：获取系统当前日期---日
	 *
	 * @return int 日
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static int getDay() {
		return day;
	}

	/**
	 * 功能描述：获取系统当前日期---日
	 *
	 * @return String 日
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static String getStrDay() {
		return day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);
	}

	/**
	 *
	 * 功能描述：获取系统时间--小时
	 *
	 * @return int
	 * @author wangshanfang
	 * @date 2008-11-24
	 * @修改日志：1.0
	 */
	public static int getHour() {
		return hour;
	}

	/**
	 *
	 * 功能描述：获取系统时间--分钟
	 *
	 * @return int
	 * @author wangshanfang
	 * @date 2008-11-24
	 * @修改日志：
	 */
	public static int getMinute() {
		return minute;
	}

	/**
	 *
	 * 功能描述：获取系统时间--秒
	 *
	 * @return int
	 * @author wangshanfang
	 * @date 2008-11-24
	 * @修改日志：
	 */
	public static int getSecond() {
		return second;
	}

	/**
	 * 功能描述：获取系统当前日期---年月日 （格式：yyyymmdd）
	 *
	 * @return String 年月日
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static String getDate() {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = calendar.getTime();
		return format.format(date);
	}

	/**
	 * 功能描述：根据预定格式取系统当前日期---年月日
	 *
	 * @param ptn
	 *            日期格式
	 * @return String
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static String getDate(String ptn) {
		SimpleDateFormat format = new SimpleDateFormat(ptn);
		Date date = calendar.getTime();
		return format.format(date);
	}

	/**
	 * 功能描述：获取系统时间 格式：yyyymmdd hh:mm:ss
	 *
	 * @return String
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static String getDateTime() {
		SimpleDateFormat format = new SimpleDateFormat(pattern + " "
				+ timePattern);
		return format.format(new Date());
	}

	/**
	 *
	 * 功能描述：获取预定义格式的系统时间
	 *
	 * @param datePtn
	 *            日期格式
	 * @param timePtn
	 *            时间格式
	 * @return String
	 * @author wangshanfang
	 * @date 2008-11-24
	 * @修改日志：1.0
	 */
	public static String getDateTime(String datePtn, String timePtn) {
		SimpleDateFormat format = new SimpleDateFormat(datePtn  + timePtn);
		Date date = calendar.getTime();
		return format.format(date);
	}

	/**
	 * 功能描述：判断给定日期（格式yyyymmdd）是否在系统日期之前，是（或等于）：true，否：false
	 *
	 * @param strdate
	 *            给定日期
	 * @return boolean
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static boolean isBefore(String strdate) {
		Calendar cal = parseCalendar(strdate);
		return cal.before(calendar);
	}

	/**
	 *
	 * 功能描述：判断给定的两个日期的前后。strdate1在strdate2之前（或同一天），返回true，反之，false
	 *
	 * @param strdate1
	 *            日期1
	 * @param strdate2
	 *            日期2
	 * @return boolean
	 * @author wangshanfang
	 * @date 2008-11-25
	 * @修改日志：1.0
	 */
	public static boolean isBefore(String strdate1, String strdate2) {
		Calendar cal1 = parseCalendar(strdate1);
		Calendar cal2 = parseCalendar(strdate2);
		return cal1.before(cal2);
	}

	/**
	 *
	 * 功能描述：计算在当前系统日期增加或减少 n 天后的日期
	 *
	 * @param days
	 *            增加或减少的天数，正数增加，反之减少
	 * @author wangshanfang
	 * @date 2008-11-24
	 * @修改日志：
	 */
	public static String addByDay(int days) {
		
		calendar.add(Calendar.DATE, days);
		return getDate();
	}
	
	/**
	 * 方法描述： 日期想加
	 * @param datestr 时间格式yyyy-MM-dd
	 * @param days
	 * @return
	 * String 时间格式yyyy-MM-dd
	 * @author Cuizk
	 * @date 2015-12-11 下午2:04:31
	 */
	public static String addByDay1(String datestr , int days){
		System.out.println("jilai");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateReturn = null;
		try {
			System.out.println("datestr:"+datestr);
			Date date = format.parse(datestr);
			System.out.println("data:"+date);
			System.out.println("days:"+days);
			Date date1 = new Date(date.getTime() + days*24L*60L*60L*1000L);
			
			System.out.println("date1:"+date1);
			dateReturn = format.format(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateReturn;
	}
	/**
	 *
	 * 功能描述：计算在给定的日期加上或减去 n 天后的日期
	 *
	 * @param datestr
	 *            给定的日期
	 * @param days
	 *            正数增加，反之减少
	 * @return String
	 * @author wangshanfang
	 * @date 2008-11-24
	 * @修改日志：
	 */
	public static String addByDay(String datestr, int days) {
		Calendar cal = parseCalendar(datestr);
		cal.add(Calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = cal.getTime();
		return format.format(date);
	}
	/**
	 *
	 * 功能描述：计算在给定的日期加上或减去 n 天后的日期
	 *
	 * @param datestr
	 *            给定的日期
	 * @param days
	 *            正数增加，反之减少
	 * @param days
	 *            格式  yyyyMMdd/yyyy-MM-dd
	 * @return String
	 * @author wangshanfang
	 * @date 2008-11-24
	 * @修改日志：
	 */
	public static String addByDay(String datestr, int days,String pattern) {
		Calendar cal = parseCalendar(datestr);
		cal.add(Calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = cal.getTime();
		return format.format(date);
	}
	public static String addBySecond(String datestr, int seconds,String pattern) {
		Calendar cal = parseCalendar(datestr);
		cal.add(Calendar.SECOND, seconds);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = cal.getTime();
		return format.format(date);
	}
	/**
	 *
	 * 功能描述：在给定日期上加月加天 后的日期
	 *
	 * @param datestr
	 *            合同起始日
	 * @param months
	 *            增加月
	 * @param days
	 *            增加天
	 * @return
	 * @author dhcc lizhiyu
	 * @date Nov 25, 2009
	 * @修改日志：
	 *
	 */
	public static String addByMonth(String datestr, int months, int days) {
		Calendar cal = parseCalendar(datestr);
		cal.add(Calendar.MONTH, months);
		cal.add(Calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = cal.getTime();
		return format.format(date);
	}

	/**
	 *
	 * 方法描述： 在给定的日期之上加相应的月
	 * @param datestr       开始日期
	 * @param months        几个月
	 * @return
	 * @throws Exception
	 * String
	 * @author luanhaowei
	 * @date 2012-7-3 上午11:06:17
	 */
	public static String addByMonth(String datestr, int months) throws Exception {
		//处理20120430 加13个月之后类似的情况
		String result = "";
		Calendar cal = parseCalendar(datestr);
		cal.add(Calendar.MONTH, months);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = cal.getTime();

		result = format.format(date);

		boolean isLastDayOfMonth = isLastDayOfMonth(datestr, "yyyyMMdd");
		if (isLastDayOfMonth) {
			boolean isLastDay = false;
			int days = DateUtil.getDaysOfMonth(datestr);
			if (days == Integer.valueOf(datestr.substring(6, 8))) {
				isLastDay = true;
			}
			String tempReturnDate = String.valueOf(result);
			if (isLastDay) {
				days = DateUtil.getDaysOfMonth(tempReturnDate);
				tempReturnDate = tempReturnDate.substring(0, 6) + String.valueOf(days);
			}
			result = String.valueOf(tempReturnDate);
		}
		return result;
	}

	/**
	 *
	 * 功能描述：获得给定日期与系统当前日期之间的天数
	 *
	 * @param strdate
	 *            给定的日期字符串
	 * @return long 天数
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static long getDays(String strdate) {
		Calendar cal = parseCalendar(strdate);
		Calendar cal1 = parseCalendar(getDate());
		long millis = Math.abs(cal.getTimeInMillis() - cal1.getTimeInMillis());
		return millis / (24L * 60L * 60L * 1000L);
	}

	/**
	 *
	 * 功能描述：获得给定的两个日期之间相差的天数（日期不分前后）
	 *
	 * @param fromdate
	 *            日期字符串 格式：yyyymmdd
	 * @param todate
	 *            日期字符串 格式：yyyymmdd
	 * @return long
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static long getDaysBetween(String fromdate, String todate) {
		Calendar from = parseCalendar(fromdate);
		Calendar to = parseCalendar(todate);
		long millis = Math.abs(from.getTimeInMillis() - to.getTimeInMillis());
		return millis / (24L * 60L * 60L * 1000L);
	}

	/**
	 *
	 * 功能描述：获得给定日期与系统当前日期之间的月数，不记天数
	 *
	 * @param strdate
	 *            给定的日期字符串
	 * @return long 月数
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：待定
	 */
	private static long getMonths(String strdate) {
		long months = getMonth() - Integer.parseInt(strdate.substring(4, 6));
		long years = getYear() - Integer.parseInt(strdate.substring(0, 4));
		if (!isBefore(strdate)) {
			months = -months;
			years = -years;
		}
		if (months >= 0) {
			return years * 12 + months;
		} else {
			return (years - 1) * 12 + months + 12;
		}
	}



	/**
	 *
	 * 功能描述：获得给定日期与系统当前日期之间的月数和天数
	 *
	 * @param strdate
	 *            给定的日期字符串
	 * @return long[] 下标0月数，1天数
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：待定
	 */
	public static long[] getMonthsAndDays(String strdate) {
		long m = getMonths(strdate);
		int d = getDay() - Integer.parseInt(strdate.substring(6, 8));
		String date = "";
		if (!isBefore(strdate)) {
			d = -d;
			date = strdate;
		} else {
			date = getDate();
		}
		while (d < 0) {
			m--;
			d += getDaysOfMonth(date);
		}
		long[] md = { m, d };
		return md;
	}

	/**
	 *
	 * 功能描述：获得给定两个日期之间的月数和天数
	 *
	 * @param strdate1
	 * @param strdate2
	 * @return long[] 下标0月数，1天数
	 * @author wangshanfang
	 * @date 2008-11-25
	 * @修改日志：
	 */
	public static long[] getMonthsAndDays(String strdate1, String strdate2) {
		long[] md = new long[2] ;
		try {
		    int monthnum = getMonths(strdate1,strdate2);
			String tempEndDate= addByMonth(strdate1,monthnum , 0);
			Long days= getDays(tempEndDate,strdate2,"yyyyMMdd");
			md[0]=monthnum;
			md[1]=days;
			tempEndDate=null;
			days=null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return md;
	}

	/**
	 *
	 * 方法描述： 获得给定两个日期之间的月数和天数
	 * @param startDate        开始日期
	 * @param endDate          结束日期
	 * @param format           日期格式 "yyyyMMdd"
	 * @return
	 * long[]
	 * @author luanhaowei
	 * @date 2012-7-3 上午09:22:17
	 */
	public static long[] getMonthsAndDays(String startDate, String endDate, String format) {
		long[] monthsAndDays = new long[2];
		try {
			int monthnum = getMonths(startDate, endDate);
			String tempEndDate = addByMonth(startDate, monthnum);
			if (compareTo(tempEndDate, endDate, format) == -1) {
				if (monthnum > 0) {
					monthnum = monthnum - 1;
				}
				tempEndDate = addByMonth(startDate, monthnum, 0);
			}
			Long days = getDays(tempEndDate, endDate, format);
			monthsAndDays[0] = monthnum;
			monthsAndDays[1] = days;
			tempEndDate = null;
			days = null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return monthsAndDays;
	}

	/**
	 * 功能描述：判断字符串是否可以转换为日期型 是：true，否：false
	 *
	 * @param strdate
	 *            预转换字符串
	 * @return boolean
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static boolean isDateStr(String strdate) {
		if (strdate.length() != 8) {
			return false;
		}

		String reg = "^(\\d{4})((0([1-9]{1}))|(1[012]))((0[1-9]{1})|([1-2]([0-9]{1}))|(3[0|1]))$";

		if (Pattern.matches(reg, strdate)) {
			reg=null;
			return getDaysOfMonth(strdate) >= Integer.parseInt(strdate
					.substring(6, 8));
		} else {
			return false;
		}
	}

	/**
	 * 功能描述：判断是否是闰年（年限1000--9999）是：true，否：false
	 *
	 * @param strdate
	 *            预判断年 格式yyyymmdd 或 yyyy
	 * @return boolean
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static boolean isLeapYear(String strdate) {
		int y = Integer.parseInt(strdate.substring(0, 4));
		if (y <= 999) {
			return false;
		}
		if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能描述：获取某一月份的天数
	 *
	 * @param strdate
	 *            日期 格式：yyyymmdd 或 yyyymm
	 * @return int
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static int getDaysOfMonth(String strdate) {
		int m = Integer.parseInt(strdate.substring(4, 6));
		switch (m) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (isLeapYear(strdate)) {
				return 29;
			} else {
				return 28;
			}
		default:
			return 0;
		}
	}

	/**
	 * 功能描述：把字符串转换为Calendar
	 *
	 * @param strdate
	 *            预转换的字符串
	 * @return Calendar
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static Calendar parseCalendar(String strdate) {
		if (isDateStr(strdate)) {
			int year = Integer.parseInt(strdate.substring(0, 4));
			int month = Integer.parseInt(strdate.substring(4, 6)) - 1;
			int day = Integer.parseInt(strdate.substring(6, 8));
			return new GregorianCalendar(year, month, day);
		} else {
			return null;
		}
	}

	/**
	 * 功能描述：将字符串转换为Date型日期 日期格式yyyymmdd
	 *
	 * @param strdate
	 *            预转换的字符串
	 * @return Date
	 * @author wangshanfang
	 * @date 2008-11-21
	 * @修改日志：1.0
	 */
	public static Date parseDate(String strdate) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = format.parse(strdate);
		} catch (Exception pe) {
			log.error(pe.getMessage(), pe);
			pe.printStackTrace();
		}
		return d;
	}

	/**
	 *
	 * 功能描述：查询下几个月的今天
	 *
	 * @param date
	 *            输入的期限
	 * @param i
	 *            要查询第几个月后的今天的日期
	 * @return 下几个月日期
	 * @author dhcc gouqifeng
	 * @date Nov 2, 2009
	 * @修改日志：
	 *
	 */
	public static String getRepayDay(String date, int val) {

		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6, 8));

		String riqi = "";
		int reapyMonth = 0;
		if ((month + val) % 12 == 0) { // 如 月加上输入的月取模==0 则把初始化的月 赋值=12
			reapyMonth = 12;
		} else { // 否则把除以12 的余数赋值个月参数上
			reapyMonth = (month + val) % 12;
		}

		year += (month + val - 1) / 12; // 原先的月数+输入的要查询的月数 -1/12 如果和 是13 年加1
		if (reapyMonth <= 9) {
			riqi = year + "0" + reapyMonth + date.substring(6, 8);
		} else {
			riqi = year + "" + reapyMonth + date.substring(6, 8);
		}

		if (getDaysOfMonth(riqi) < day) { // 查询日期的日 < 当期日期的日 该日期 如 20090130 的
			// 下个月的期限是多少 ,日应该 是28, 因为不是闰年
			riqi = riqi.substring(0, 6) + DateUtil.getDaysOfMonth(riqi);// 这样
			// 就截取
			// 对应的日期加上
			// 该月应该有的日期是多少就对了,2,4,6,8
			// 等
			// 小月的日期就对了
		}

		return riqi;
	}

	// public static void main(String args []){
	// DateUtil du=new DateUtil();
	// System.out.println(du.getDate().substring(0,6)+"01");
	// }

	/**
	 *
	 * @名称 isBetween
	 * @描述 判断是否在开始日期和结束日期之间
	 * @作者 liupei
	 * @时间 Mar 15, 2011 11:47:58 AM
	 * @参数 begin 开始日期 end 结束日期 betweenValue 中间值 DateFormat 日期格式 boundaryValue
	 *     是否包括边界值
	 */
	public static boolean isBetween(String begin, String end,
			String betweenValue, String DateFormat, boolean boundaryValue) {
		boolean flag = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat);

		System.out.println("********************日期格式为=" + DateFormat);

		try {
			Date beginDate = dateFormat.parse(begin);
			Date endDate = dateFormat.parse(end);
			Date betweenDate = dateFormat.parse(betweenValue);
			if (betweenDate.after(beginDate) && betweenDate.before(endDate)) {
				flag = true;
			}
			if (boundaryValue) {
				if (betweenDate.compareTo(beginDate) == 0) {
					flag = true;
				}
				if (betweenDate.compareTo(endDate) == 0) {
					flag = true;
				}
			}

		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 *
	 * @名称 addDay
	 * @描述 给一个日期添加或减少几天
	 * @作者 liupei
	 * @时间 Mar 16, 2011 9:29:34 AM
	 * @param tempdate需要操作的日期变量
	 * @param days需要添加几天，正数表示添加几天，负数表示减少几天
	 * @param format日期格式
	 */
	public static String addDay(String tempdate, int days, String format) {
		int year = Integer.parseInt(tempdate.substring(0, 4));
		int month = 0;
		int day = 0;
		String s_month = tempdate.substring(4, 6);
		String s_day = tempdate.substring(6, 8);
		if ("0".equals(s_month.substring(0, 1))) {
			month = Integer.parseInt(tempdate.substring(5, 6));
		} else {
			month = Integer.parseInt(tempdate.substring(4, 6));
		}
		if ("0".equals(s_day.substring(0, 1))) {
			day = Integer.parseInt(tempdate.substring(7, 8));
		} else {
			day = Integer.parseInt(tempdate.substring(6, 8));
		}

		GregorianCalendar firstFlight = new GregorianCalendar(year, month - 1,
				day);

		DateFormat df = new SimpleDateFormat(format);

		Date date = firstFlight.getTime();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return df.format(cal.getTime());
	}

	/**
	 *
	 * @名称 getDays
	 * @功能 如果结束日期小余开始日期返回-1 相等返回0否则返回两个日期之间的天数
	 * @作者 乾之轩
	 * @时间 Mar 18, 2011 8:15:42 PM
	 * @param begin开始日期
	 * @param end结束日期
	 * @param format日期格式
	 */
	public static long getDays(String begin, String end, String format) {
		long datanumber = 0;
		SimpleDateFormat df = new SimpleDateFormat(format);
		long l_end;
		long l_begin;
		try {
			l_end = df.parse(end).getTime();
			l_begin = df.parse(begin).getTime();
			long temp = l_end - l_begin;
			datanumber = temp / (1000L * 60L * 60L * 24L);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		if (datanumber < 0) {
			datanumber = -1;
		}
		return datanumber;
	}

	/**
	 *
	 * @名称 isAfter
	 * @功能 判断end是不是在begin之后.是返回true不是返回false
	 * @作者 乾之轩
	 * @时间 Mar 21, 2011 11:03:13 AM
	 * @param begin开始日期
	 * @param end结束日期
	 * @param format日期格式
	 * @return boolean
	 *
	 */
	public static boolean isAfter(String begin, String end, String format) {
		boolean flag = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			Date beginDate = dateFormat.parse(begin);
			Date endDate = dateFormat.parse(end);
			flag = endDate.after(beginDate);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 *
	 * 方法描述： begin > end 返回 -1，begin = end 返回 0，begin < end 返回 1.
	 * @param begin
	 * @param end
	 * @param format
	 * @return
	 * int
	 * @author luanhaowei
	 * @date 2012-6-5 下午07:27:04
	 */
	public static int compareTo(String begin, String end, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		int flag=0;
		try {
			Date beginDate = dateFormat.parse(begin);
			Date endDate = dateFormat.parse(end);
			flag = endDate.compareTo(beginDate);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 *
	 * @名称 getDaysOfYear
	 * @功能 获得一年的天数
	 * @作者 乾之轩
	 * @时间 Mar 21, 2011 11:03:13 AM
	 * @param year
	 *            年
	 * @return boolean
	 *
	 */
	public static String getDaysOfYear(String year) {
		if (isLeapYear(year)) {
			return "366";
		} else {
			return "365";
		}

	}

	/**
	 * 验证日期格式是否符合xxxx-xx-xx这种格式（只验证格式，不验证日期是否正确）
	 *
	 * @param date
	 *            日期字符串
	 * @return 符合：true 不符合：false
	 * @author yxdong
	 */
	public static boolean testDate(String date) {
		boolean isDate = false;
		Pattern pattern = Pattern.compile("^\\d{4}(\\-)\\d{2}(\\-)\\d{2}$");
		Matcher matcher = pattern.matcher(date);
		isDate = matcher.matches();
		return isDate;
	}

	/**
	 *
	 * @名称 isFullMonth
	 * @描述 判断2个日期之间的间隔是不是整月 如2011-01-02和2011-03-02是整月 2011-12-31和2011-4-30 是整月
	 * @作者 乾之轩
	 * @时间 Dec 1, 2011 3:39:09 PM
	 */
	public static boolean isFullMonth(String begin, String end, String format) {
		return getMonthsAndDays(begin,end)[1]>0?false:true;
	}

	/**
	 *
	 * @名称 isLastDayOfMonth
	 * @描述 判断两个日期是否都是月末
	 * @参数 @param begin
	 * @参数 @param end
	 * @参数 @param format
	 * @参数 @return
	 * @返回值 boolean
	 * @作者 luanhaowei
	 * @时间 2012-4-13 上午11:24:48
	 */
	public static boolean isLastDayOfMonth(String begin, String end, String format) {
		boolean result = false;
		if (isLastDayOfMonth(begin, format) && isLastDayOfMonth(end, format)) {
			result = true;
		}
		return result;
	}


	/**
	 *
	 * @名称 getMonth
	 * @描述 判断2个日期相差的月数
	 * @作者 乾之轩
	 * @时间 Dec 1, 2011 4:57:49 PM
	 */
	public static int getMonth(String begin1, String end1, String format)
			throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date s = dateFormat.parse(begin1);
		Date e = dateFormat.parse(end1);

		if (s.after(e)) {
			Date t = s;
			s = e;
			e = t;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(s);
		Calendar end = Calendar.getInstance();
		end.setTime(e);
		Calendar temp = Calendar.getInstance();
		temp.setTime(e);
		temp.add(Calendar.DATE, 1);

		int y = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
		int m = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

		if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {// 前后都不破月
			return y * 12 + m + 1;
		} else if ((start.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {// 前破月后不破月
			return y * 12 + m;
		} else if ((start.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) != 1)) {// 前不破月后破月
			return y * 12 + m;
		} else {// 前破月后破月
			return (y * 12 + m - 1) < 0 ? 0 : (y * 12 + m - 1);
		}
	}

	/**
	 *
	 * @名称 getDay
	 * @描述 判断2个日期相差的天数数
	 * @作者 乾之轩
	 * @时间 Dec 1, 2011 4:58:29 PM
	 */
	public static int getDay(String begin1, String end1, String format)
			throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date s = dateFormat.parse(begin1);
		Date e = dateFormat.parse(end1);

		if (s.after(e)) {
			Date t = s;
			s = e;
			e = t;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(s);
		Calendar end = Calendar.getInstance();
		end.setTime(e);
		Calendar temp = Calendar.getInstance();
		temp.setTime(e);
		temp.add(Calendar.DATE, 1);

		if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {// 前后都不破月
			return 0;
		} else if ((start.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {// 前破月后不破月
			return getDayP(start);
		} else if ((start.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) != 1)) {// 前不破月后破月
			return end.get(Calendar.DATE);
		} else {// 前破月后破月
			if (start.get(Calendar.MONTH) == end.get(Calendar.MONTH)
					&& start.get(Calendar.YEAR) == end.get(Calendar.YEAR)) {
				return end.get(Calendar.DATE) - start.get(Calendar.DATE) + 1;
			} else {
				return getDayP(start) + end.get(Calendar.DATE);
			}
		}
	}

	public static int getDayP(Calendar s) {
		int d;
		if (s.get(Calendar.MONTH) == 1 && s.get(Calendar.YEAR) % 4 == 0
				&& s.get(Calendar.YEAR) % 100 != 0) {// 闰年2月
			d = 29;
		} else {
			Map<Integer, Integer> m = new HashMap<Integer, Integer>();
			m.clear();
			m.put(1, 31);
			m.put(3, 31);
			m.put(5, 31);
			m.put(7, 31);
			m.put(8, 31);
			m.put(10, 31);
			m.put(12, 31);
			m.put(4, 30);
			m.put(6, 30);
			m.put(9, 30);
			m.put(11, 30);
			m.put(2, 28);
			d = m.get(s.get(Calendar.MONTH) + 1);
		}
		return d - s.get(Calendar.DATE);
	}

	@SuppressWarnings("static-access")
	static String GetSysDate(String format, String StrDate, int year,
			int month, int day) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sFmt = new SimpleDateFormat(format);
		cal.setTime(sFmt.parse((StrDate), new ParsePosition(0)));

		if (day != 0) {
			cal.add(cal.DATE, day);
		}
		if (month != 0) {
			cal.add(cal.MONTH, month);
		}
		if (year != 0) {
			cal.add(cal.YEAR, year);

		}
		return sFmt.format(cal.getTime());
	}




	/********************************************************************/

	 public static int getMonths(String begin, String end) {
		int iMonth = 0;
		int flag = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date1 = dateFormat.parse(begin);
			Date date2 = dateFormat.parse(end);
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))
				flag = 1;
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 30 && objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 31
					&& objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1.get(Calendar.YEAR))
				flag = 0;// 草川禾 20110120 添加 处理特殊情况 此处是唯一一种
							// 即结束日期是30天，开始日期日31时计划日期[)情况
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 28 && objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 31
					&& objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1.get(Calendar.YEAR))
				flag = 0;// 草川禾 20110120 添加 处理特殊情况 此处是唯一一种
							// 即结束日期是30天，开始日期日31时计划日期[)情况
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 28 && objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 29
					&& objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1.get(Calendar.YEAR))
				flag = 0;// 处理getMonthsAndDays("20120229", "20130228")这种情况
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 29 && objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 31
					&& objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1.get(Calendar.YEAR))
				flag = 0;// 草川禾 20110120 添加 处理特殊情况 此处是唯一一种
							// 即结束日期是30天，开始日期日31时计划日期[)情况
			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)
						- objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return iMonth;
	}

	 /**
	  *
	  * @名称 isLastDayOfMonth
	  * @描述 判断一个日期是否该月的月末
	  * @参数 @param beginDate
	  * @参数 @return
	  * @返回值 boolean
	  * @作者 luanhaowei
	  * @时间 2012-4-13 上午11:13:45
	  */
	public static boolean isLastDayOfMonth(String beginDate, String format) {
		boolean result = false;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Date date = dateFormat.parse(beginDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
			if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
				result = true;
			}
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}

	/***************************************************************************
	 * @throws Exception
	 **************************************************************************/


	 public static void main(String[] args) {
		 System.out.println(DateUtil.getDateTime("yyyyMMdd","HHmmss"));
		//System.out.println(isAfter("20120415","20120515","yyyyMMdd"));
//		 if(isLastDayOfMonth("20120229", "yyyyMMdd")) {
//             System.out.println( "当前是月末! ");
//		 } else {
//             System.out.println( "当前不是月末! ");
//		 }
//		 System.out.println("compareTo="+compareTo("00000000", "20120102", "yyyyMMdd"));
//		 System.out.println(getMonthsAndDays("20120531", "20130530","yyyyMMdd")[0]);
//		 try {
//			System.out.println(addByMonth("20120130", 5));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	 /**
	 * 方法描述：修改日期格式 yyyyMMdd  to yyyy-MM-dd
	 * @param date
	 * @return
	 * String
	 * @author Javelin
	 * @date 2015-6-19 下午4:14:10
	 */
	public static String getDateFormat(String date) {
		 String format = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
		 return format;
	}
	
	/**
	 * 方法描述： 获得当季度的第一个月的月份，一季度是1-3月，以此类推
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-2-27 上午10:41:18
	 */
	public static String getQuarterInMonth() {  
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());  
        int month = calendar.get(Calendar.MONTH);  
		  //month = month +1 ;
	        int months[] = { 0, 3, 6, 9 };  
	         
	        if (month >= 0 && month <= 2)  
	            month =  months[0];  
	        else if (month >= 3 && month <= 5)  
	        	 month =  months[1];  
	        else if (month >= 6 && month <= 8)  
	        	 month =  months[2];  
	        else  
	        	 month =  months[3];  
	        calendar.set(Calendar.MONTH, month);  
	        calendar.set(Calendar.DAY_OF_MONTH, 1); 
	        DateFormat df = new SimpleDateFormat("yyyy-MM");  
	        return df.format(calendar.getTime());
	}  
	
	/**
	 * 方法描述：获取指定月份的下一个月 
	 * @param thisMonth 如果为空，则取当前月份
	 * @return
	 * String
	 * @author Cuizk
	 * @date 2016-3-2 上午10:27:33
	 */
	public static String getNextMonth(String thisMonth){
		 DateFormat df = new SimpleDateFormat("yyyy-MM");  
		if(StringUtil.isEmpty(thisMonth)){
			thisMonth = df.format(new Date());
		}
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		try {
			date= df.parse(thisMonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);  
        int month = calendar.get(Calendar.MONTH) + 1;
        calendar.set(Calendar.MONTH, month);  
		return df.format(calendar.getTime());
	}
	
	/**
	 * 方法描述：去掉日期中的横线2013-12-10-->20131210
	 * @param s 如果为空，则返回空
	 * @return
	 * String
	 * @author husheng
	 * @date 2017-4-13 上午10:27:33
	 */
	public static String getShortDate(String s){
		String newDate;
		if(StringUtil.isEmpty(s)){
			newDate = "";
		}else{
			newDate = s.substring(0,4) +s.substring(5,7) + s.substring(8);
		}
		return newDate;
	}
	/**
	 * @功能 获得格式化得时间
	 * @param date
	 *            要转化的时间
	 * @param formate
	 *            要转化的时间格式
	 * @return
	 */
	public static String getFormatDate(String date, String formate) {
		SimpleDateFormat myformate = new SimpleDateFormat(formate);
		SimpleDateFormat myformate1 = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = null;
		if (StringUtils.isEmpty(date))
			return "";
		try {
			return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
		} catch (Exception e) {
			try {
				myDate = myformate1.parse(date);
			} catch (ParseException e0) {
				SimpleDateFormat myformate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					myDate = myformate2.parse(date);
				} catch (ParseException e1) {
					SimpleDateFormat myformate3 = new SimpleDateFormat("MM-dd");
					try {
						myDate = myformate3.parse(date);
					} catch (ParseException e2) {
						SimpleDateFormat myformate4 = new SimpleDateFormat("MM-dd HH:mm");
						try {
							myDate = myformate4.parse(date);
						} catch (ParseException e3) {
							SimpleDateFormat myformate5 = new SimpleDateFormat("yyyy\u5E74MM\u6708dd\u65E5");
							try {
								myDate = myformate5.parse(date);
							} catch (ParseException e4) {
								SimpleDateFormat myformate6 = new SimpleDateFormat("yyyy/MM/dd");
								try {
									myDate = myformate6.parse(date);
								} catch (ParseException e5) {
									myDate = new Date();
								}
							}
						}
					}
				}
			}
		}
		return myformate.format(myDate);
	}
}
