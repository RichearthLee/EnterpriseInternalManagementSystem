package com.mftcc.interior.report.filter.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;


/**
 * 日期时间处理工具类。
 * 被继承的类已支持大多数常规日期操作。新增的比较特殊的场景需求，写在此类中。
 * <strong>部分设置在父类中定义，不在子类重复定义（如日期格式）</strong>
 * @author LiuYF
 */
public class DateUtil extends DateHandle {
	/**
	 * 用于展示时的日期分隔符。 TODO 读取系统设置的分隔符。
	 */
	private static final String DATE_SEPARATOR = "-";
	/**
	 * 用于展示时的日期格式，带分隔符。
	 */
	private static final String PATTERN_FOR_SHOW = "yyyy" + DATE_SEPARATOR
			+ "MM" + DATE_SEPARATOR + "dd";
	/**
	 * 当前操作系统日期 Calendar
	 */
	private static Calendar calendar = new GregorianCalendar(TIME_ZONE);
	/**
	 * 时间格式 默认：HH:mm:ss
	 */
	private static String TIME_PATTERN = "HH:mm:ss";

	/**
	 * 构造方法
	 */
	private DateUtil() {
		// Do Nothing
	}

	/**
	 * 功能描述： 初始化系统日期(当前系统日期)调用setCalendarDate()后会用到此方法重新初始化系统日期时间 为当前日期时间
	 * 
	 * @修改日志：1.0
	 * @deprecated 不允许通过静态的calendar在不同的方法中获取时间。用于时间的复杂计算建议私有化运用。
	 * 
	 */
	private static void initCalendar() {
		calendar = new GregorianCalendar(TIME_ZONE);
	}

	/**
	 * 功能描述：获取系统当前日期---年
	 * <p>
	 * <strong>（注意：不一定是最新的，如需最新的应调用initCalendar方法后使用）</strong>
	 * </p>
	 * 
	 * @return int 年
	 * @deprecated 不建议使用calendar。
	 * @修改日志：1.0
	 */
	@Deprecated
	public static int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：获取系统当前日期---年
	 * <p>
	 * <strong>（注意：不一定是最新的，如需最新的应调用initCalendar方法后使用）</strong>
	 * </p>
	 * 
	 * @return String 年
	 * 
	 * @修改日志：1.0
	 * @Deprecated
	 */
	@Deprecated
	public static String getStrYear() {
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	/**
	 * 功能描述：获取系统当前日期---月
	 * <p>
	 * <strong>（注意：不一定是最新的，如需最新的应调用initCalendar方法后使用）</strong>
	 * </p>
	 * 
	 * @return int 月
	 * 
	 * @修改日志：1.0
	 * @Deprecated 不建议使用calendar。
	 */
	@Deprecated
	public static int getMonth() {
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：获取系统当前时间---时:分:秒 （格式：HH:mm:ss）
	 * 
	 * @return String 时:分:秒 -HH:mm:ss
	 * @修改日志：1.0
	 */
	public static String getTime() {
		SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN);

		format.setTimeZone(TIME_ZONE);

		return format.format(new Date());
	}

	/**
	 * 功能描述：根据预定格式取系统当前日期---年月日
	 * 
	 * @param ptn
	 *            日期格式
	 * @return String
	 * 
	 * @修改日志：1.0
	 */
	public static String getDate(String ptn) {
		SimpleDateFormat format = new SimpleDateFormat(ptn);

		format.setTimeZone(TIME_ZONE);

		return format.format(new Date());
	}
	
	/**
	 * 一次获得年、月、日三个值。
	 * @return
	 */
	public static String[] getYearMonthDay() {
		String date = getDate();
		return new String[]{date.substring(0, 4), date.substring(4, 6), date.substring(6, 8)};
	} 

	/**
	 * 功能描述：获取系统时间 格式：yyyyMMdd HH:mm:ss
	 * 
	 * @return String <strong>yyyyMMdd HH:mm:ss</strong>
	 * 
	 * @修改日志：1.0
	 */
	public static String getDateTime() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN + " "
				+ TIME_PATTERN);

		format.setTimeZone(TIME_ZONE);

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
	 * 
	 * @修改日志：1.0
	 */
	public static String getDateTime(String datePtn, String timePtn) {
		SimpleDateFormat format = new SimpleDateFormat(datePtn + " " + timePtn);
		format.setTimeZone(TIME_ZONE);

		return format.format(new Date());
	}

	/**
	 * 功能描述：判断给定日期（格式yyyymmdd）是否在系统日期之前，是（或等于）：true，否：false
	 * 
	 * @param strdate
	 *            给定日期
	 * @return boolean
	 * 
	 * @修改日志：1.0
	 * @deprecated
	 */
	@Deprecated
	public static boolean isBefore(String strdate) {
		initCalendar();
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
	 * 
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
	 * 
	 * @修改日志：
	 */
	public static String addByDay(int days) {
		Calendar calendar = new GregorianCalendar(TIME_ZONE);
		calendar.add(Calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
		Date date = calendar.getTime();
		return format.format(date);
	}
	public static String addByDay(String beginDate, int days)  {
		Calendar cal = parseCalendar(beginDate);
		if (cal == null) {
			return null;
		}
		cal.add(Calendar.DATE, days);
		Date date = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		return sdf.format(date);
	}
	
	/**
	 * 
	 * 方法描述：获取两个日期之间的天数， 区分先后顺序如：fromdate=20150629， todate=20150627 return=0
	 * 
	 * @param fromdate
	 * @param todate
	 * @return int
	 * @author 栾好威
	 * @date 2017-7-19 下午3:33:45
	 * @see #getIntervalDays(String date, String otherDate)
	 * @see #getIntervalDays(Date date, Date otherDate)
	 */
	public static int getDaysBetween(String fromdate, String todate) {
		Integer tem = 0;
		try {
			long datanumber = 0;
			SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
			long l_end;
			long l_begin;
			l_begin = df.parse(fromdate).getTime();
			l_end = df.parse(todate).getTime();

			long temp = l_end - l_begin;
			datanumber = temp / (1000L * 60L * 60L * 24L);
			if (datanumber <= 0) {
				datanumber = 0;
			}
			tem = Integer.valueOf(String.valueOf(datanumber));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tem;
	}
	
	/**
	 * 返回Date *
	 * 
	 * @param str
	 *            yyyy-MM-dd String
	 * @return Date
	 */
	public static Date parseTenStrToDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 返回Date *
	 * 
	 * @param str
	 *            yyyyMMdd String
	 * @return Date
	 */
	public static Date parseEightStrToDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = null;
		try {
			d = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 返回String *
	 * 
	 * @param date
	 *            Date
	 * @return String yyyyMMdd
	 */
	public static String parseDateToEightStr(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	/**
	 * 返回String *
	 * 
	 * @param date
	 *            Date
	 * @return String yyyy-MM-dd
	 * @deprecated 此方法已禁用，请使用 {@link #getShowDateTime(String)}。
	 */
	@Deprecated
	public static String parseDateToTenStr(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	/**
	 * 功能描述：判断字符串是否可以转换为日期型 是：true，否：false
	 * 
	 * @param strdate
	 *            预转换字符串
	 * @return boolean
	 * 
	 * @修改日志：1.0
	 */
	public static boolean isDateStr(String strdate) {
		if (strdate.length() != 8) {
			return false;
		}

		String reg = "^(\\d{4})((0([1-9]{1}))|(1[012]))((0[1-9]{1})|([1-2]([0-9]{1}))|(3[0|1]))$";

		if (Pattern.matches(reg, strdate)) {
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
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * @修改日志：1.0
	 */
	public static Date parseDate(String strdate) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
		Date d = null;
		try {
			d = format.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 功能描述：返回客户报表期的上一期
	 * 
	 * @param pigdate
	 *            客户报表日期
	 * @return lastTerm 客户报表的上一期
	 * 
	 * @修改日志：
	 */
	public static String GetShangTerm(String pigdate) {
		String lastTerm = "";
		int a = Integer.parseInt(pigdate.substring(0, 4));
		String b = pigdate.substring(4, 6);
		String c = pigdate.substring(4, 5);
		String d = pigdate.substring(5, 6);
		if ("50".equals(pigdate.substring(6, 8))) {
			if ("0".equals(c)) {
				if ("1".equals(d)) {
					lastTerm = String.valueOf(a - 1) + "1250";
				} else {
					lastTerm = String.valueOf(a) + "0"
							+ String.valueOf(Integer.parseInt(d) - 1) + "50";
				}
			} else {
				if ("0".equals(d)) {
					lastTerm = String.valueOf(a) + "0950";
				} else {
					lastTerm = String.valueOf(a)
							+ String.valueOf(Integer.parseInt(b) - 1) + "50";
				}
			}
		} else if ("40".equals(pigdate.substring(6, 8))) {
			if ("1".equals(d)) {
				lastTerm = String.valueOf(a - 1) + "0440";
			} else {
				lastTerm = String.valueOf(a) + "0"
						+ String.valueOf(Integer.parseInt(d) - 1) + "40";
			}
		} else if ("00".equals(pigdate.substring(6, 8))) {
			lastTerm = String.valueOf(a - 1) + "0000";
		}
		return lastTerm;
	}

	/**
	 * 根据日期取得星期几
	 * 
	 * @param DateStr
	 * @return
	 */
	public static String getWeekDay(String DateStr) {
		SimpleDateFormat formatYMD = new SimpleDateFormat("yyyyMMdd");// formatYMD表示的是yyyyMMdd格式
		SimpleDateFormat formatD = new SimpleDateFormat("E");// "E"表示"day in week"
		Date d = null;
		String weekDay = "";
		try {
			d = formatYMD.parse(DateStr);// 将String 转换为符合格式的日期
			weekDay = formatD.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("日期:" + DateStr + " ： " + weekDay);
		return weekDay;
	}

	/**
	 * 根据每月第一天星期几和一月天数确定行数
	 * 
	 * @param week
	 * @param days
	 * @return
	 */
	public static int line(String week, int days) {
		int line = 0;
		if (week.equals("星期日")) {
			if (days >= 29) {
				line = 5;
			} else {
				line = 4;
			}
		} else if (week.equals("星期一") || week.equals("星期二") || week.equals("星期三") || week.equals("星期四")) {
			line = 5;
		} else if (week.equals("星期五")) {
			if (days >= 31) {
				line = 6;
			} else {
				line = 5;
			}
		} else if (week.equals("星期六")) {
			if (days >= 30) {
				line = 6;
			} else {
				line = 5;
			}
		}
		return line;
	}

	/**
	 * 根据参数busidate返回上一月份的6位日期(YYYYMM格式)
	 * 
	 * @param busidate
	 * @return
	 */
	public static String getLastMonth(String busidate) {
		String last_month = null;
		if (busidate == null || busidate.length() < 6)
			return null;
		int month = Integer.parseInt(busidate.substring(4));
		int year = Integer.parseInt(busidate.substring(0, 4));
		if (month == 1) {
			last_month = (year - 1) + "12";
		} else {
			month--;
			if (month > 9) {
				last_month = year + String.valueOf(month);
			} else {
				last_month = year + "0" + String.valueOf(month);
			}
		}
		return last_month;
	}

	/**
	 * 返回日期经过若干月后的日期(传入几位日期便返回多少位的格式)
	 * 
	 * @param dateStr
	 * @param hkm
	 *            int
	 * @return String
	 */
	public static String getDateStr(String dateStr, int hkm) {
		String st_return = "";
		boolean isEight = true;
		if (dateStr.split("-").length == 3) {
			isEight = false;
		} else {
			dateStr = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6)
					+ "-" + dateStr.substring(6, 8);
		}
		try {
			DateFormat daf_date = DateFormat.getDateInstance(DateFormat.MEDIUM,
					Locale.CHINA);
			daf_date.parse(dateStr);
			Calendar calendar = daf_date.getCalendar();
			calendar.add(Calendar.MONTH, hkm);
			String st_m = "";
			String st_d = "";
			int y = calendar.get(Calendar.YEAR);
			int m = calendar.get(Calendar.MONTH) + 1;
			int d = calendar.get(Calendar.DAY_OF_MONTH);
			if (m <= 9) {
				st_m = "0" + m;
			} else {
				st_m = "" + m;
			}
			if (d <= 9) {
				st_d = "0" + d;
			} else {
				st_d = "" + d;
			}
			if (isEight) {
				st_return = y + "" + st_m + "" + st_d;
			} else {
				st_return = y + "-" + st_m + "-" + st_d;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return st_return;
	}

	/**
	 * 返回两位数据字串 *
	 * 
	 * @param sz
	 *            int
	 * @return String
	 */
	public static String bZero(int sz) {
		return (sz < 10 ? ("0" + String.valueOf(sz)) : String.valueOf(sz));
	}

	/**
	 * 将YYYYMMDD转换成YYYY-MM-DD
	 * 
	 * @param date
	 * @return
	 * 
	 * @deprecated 此方法已禁用，请使用 {@link #getShowDateTime(String)}。
	 */
	@Deprecated
	public static String getStr(String date) {
		return getShowDateTime(date);
	}

	/**
	 * 将数据库存储的不带分隔符日期(时间)，转换为显示用的带分隔符的日期格式。 支持8位日期，或者8位日期+时间。
	 * 
	 * @param value
	 *            格式：20170420 14:35:58
	 * @return String 格式：2017-04-20 14:35:58
	 */
	public static String getShowDateTime(String value) {
		// 空直接返回
		if (value == null || value.isEmpty()) {
			return value;
		}

		// 非空才进行处理；少于8位不合法日期，不予处理。
		if (value.length() < 8) {
			return value;
		}
		String patternForDb = DATE_PATTERN;
		DateFormat df = new SimpleDateFormat(patternForDb);
		DateFormat dfForShow = new SimpleDateFormat(PATTERN_FOR_SHOW);
		Date date = null;
		try {
			date = df.parse(value);
			return dfForShow.format(date) + value.substring(8);
		} catch (ParseException e) {
			e.printStackTrace();
			return value;
		}
	}

	/**
	 * 把日期型转化成字符串型 *
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String dateToStr(java.util.Date date) {
		String str = "";
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			str = sdf.format(date);
		} catch (Exception ex) {
			str = "";
		}
		return str;
	}

	/**
	 * 比较日期大小 *
	 * 
	 * @param date1
	 *            String
	 * @param date2
	 *            String
	 * @return int date1>date2 return 1 date1==date2 return 0 date1<date2 return
	 *         -1
	 */
	public static int compareDate(String date1, String date2) {
		// int i = 0;
		// String[] date1Array = date1.split("-");
		// String[] date2Array = date2.split("-");
		// java.sql.Date date11 = new java.sql.Date(Integer.parseInt(
		// date1Array[0], 10), Integer.parseInt(date1Array[1], 10),
		// Integer.parseInt(date1Array[2], 10));
		// java.sql.Date date22 = new java.sql.Date(Integer.parseInt(
		// date2Array[0], 10), Integer.parseInt(date2Array[1], 10),
		// Integer.parseInt(date2Array[2], 10));
		// return date11.compareTo(date22);
		Date date11 = parseTenStrToDate(date1);
		Date date22 = parseTenStrToDate(date2);
		return date11.compareTo(date22);
	}

	public static int compareEightDate(String date1, String date2) {

		Date date11 = parseEightStrToDate(date1);
		Date date22 = parseEightStrToDate(date2);
		return date11.compareTo(date22);
	}

	/**
	 * 返回Date *
	 * 
	 * @param str
	 *            yyyyMMdd HH:mm:ss String
	 * @return Date
	 */
	public static Date parseSeventeenStrToDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date d = null;
		try {
			d = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 得到月的天数 *
	 * 
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @return int
	 */
	public static int getMonthDays(int year, int month) {
		int days = 1;
		boolean isrn = (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) ? true
				: false;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;
		case 2:
			if (isrn)
				days = 29;
			else
				days = 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		}
		return days;
	}

	/**
	 * 得到当前月 格式是2008-9-25 *
	 * 
	 * @param dateStr
	 *            String
	 * @return int
	 * @deprecated
	 */
	@Deprecated
	public static int getCurrentMonth(String dateStr) {
		String date[] = dateStr.split("-");
		return Integer.parseInt(date[1], 10);
	}

	/**
	 * 将YYYY-MM-DD转换成YYYYMMDD
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDD(String date) {
		if (date == null) {
			return "";
		} else if (date.length() == 8) {
			return date;
		} else {
			return date.replace("-", "");
		}
	}

	/**
	 * 得到当前年 格式是2008-9-25 *
	 * 
	 * @param dateStr
	 *            String
	 * @return int
	 */
	public static int getCurrentYear(String dateStr) {
		String date[] = dateStr.split("-");
		return Integer.parseInt(date[0], 10);
	}

	/**
	 * 得到当前天数 格式是2008-9-25 *
	 * 
	 * @param dateStr
	 *            String
	 * @return int
	 */
	public static int getCurrentDay(String dateStr) {
		String date[] = dateStr.split("-");
		return Integer.parseInt(date[2], 10);
	}

	/**
	 * 判断date1是否比date2早 date1<date2 --true date1>=date2 --false 日期格式:yyyy-MM-dd
	 * 
	 * @param date1
	 * @param date2
	 * @return true/false
	 */
	public static boolean checkDate1BeforeDate2(String date1, String date2) {
		Date d1 = strToDate(date1);
		Date d2 = strToDate(date2);
		if (d1.before(d2)) {
			return true;
		}
		return false;
	}

	/**
	 * 把字符串 格式转化成日期型。支持8位日期、10位日期(由于分隔符的问题，尽量不要用)、17位日期+时间。
	 * 
	 * @param dateStr
	 *            要保证入参不为空。
	 * @return Date ——可能为null。转换异常返回null。
	 */
	public static Date strToDate(String dateStr) {
		if (dateStr == null || dateStr.isEmpty()) {
			return null;
		}

		Date date = null;

		try {
			switch (dateStr.length()) {
			case 8:
				DateFormat df1 = new SimpleDateFormat("yyyyMMdd");
				date = df1.parse(dateStr);
				break;

			case 10:
				DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
				date = df2.parse(dateStr);
				break;

			case 17:
				DateFormat df3 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
				date = df3.parse(dateStr);
				break;

			default:
				break;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	
	/**
	 * StringToDate 的封装入口，传递日期格式。
	 * @param dateStr
	 * @return
	 */
	public static Date str2Date(String dateStr) {
		
		return StringToDate(dateStr, DATE_PATTERN);
	}
	/**
	 * 返回两个日期间隔的天数 
	 * 
	 * @param beginDate
	 *            String
	 * @param endDate
	 *            String
	 * @return int
	 * @Deprecated 此方法已禁用。仅供jar包中封装的方法调用。 <br />请使用 {@link #getDaysBetween(String)}。
	 * 
	 * @see #getIntervalDays(Date date, Date otherDate)
	 * @see #getDaysBetween(String fromDate, String toDate)
	 */
	@Deprecated
	public static int getBetweenDays(String beginDate, String endDate) {
		boolean exchangeFlag = false;
		if (DateUtil.compareDate(beginDate, endDate) == 1) {
			String temp = beginDate;
			beginDate = endDate;
			endDate = temp;
			exchangeFlag = true;
		}
		int sum = 0;
		int beginYear = getCurrentYear(beginDate);
		int beginMonth = getCurrentMonth(beginDate);
		int beginDay = getCurrentDay(beginDate);
		int endYear = getCurrentYear(endDate);
		int endMonth = getCurrentMonth(endDate);
		int endDay = getCurrentDay(endDate);
		String startDateStr = String.valueOf(beginYear) + "-"
				+ bZero(beginMonth) + "-01";

		int sumMonth = (endYear - beginYear + 1) * 12 - (beginMonth)
				- (12 - endMonth);
		for (int i = 0; i < sumMonth; i++) {
			String dateStr = getDateStr(startDateStr, i);
			sum = sum
					+ getMonthDays(getCurrentYear(dateStr),
							getCurrentMonth(dateStr));
		}

		sum = sum - beginDay + endDay;
		if (exchangeFlag) {
			sum = -sum;
		}
		return sum;
	}
	public static double getBetweenDayTime(String beginDateTime,
			String endDateTime) {
		Date beginDateTime1 = parseSeventeenStrToDate(beginDateTime);
		Date endDateTime2 = parseSeventeenStrToDate(endDateTime);
		long l_end;
		long l_begin;
		double data = 0;
		l_begin = beginDateTime1.getTime();
		l_end = endDateTime2.getTime();
		long l = l_end - l_begin;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		data = (double) day + (hour / (double) 24);
		data = (double) Math.round(data * 100) / 100;

		return data;
	}

	/**
	 * 格式化自定义时间
	 * 
	 * @param sysday
	 *            传入时间 格式yyyyMMdd
	 * @param format
	 *            格式化 格式
	 * @return
	 */
	public static String getDiyDate(String sysday, String format) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = null;
		String datestr = null;
		try {
			d = df.parse(sysday);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			datestr = sdf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datestr;
	}
	/**
	 * 
	 * 方法描述： 格式化日期和时间。
	 * @param dateTime yyyyMMdd HH:mm:ss
	 * @param format yyyy-MM-dd HH:mm:ss、yyyy/MM/dd HH:mm:ss、yyyy年MM月dd日 HH时mm分ss秒等
	 * @return
	 * String
	 * @author 沈浩兵
	 * @date 2018-1-18 下午4:54:19
	 */
	public static String getFormatDateTime(String dateTime,String format) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date d = null;
		String dateStr = null;
		try {
			d = df.parse(dateTime);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateStr = sdf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	/**
	 * 
	 * 方法描述： 格式化时间
	 * @param sysDayTime HH:mm:ss
	 * @param timeFormat HH时mm分ss秒等
	 * @return
	 * String
	 * @author 沈浩兵
	 * @date 2018-1-18 下午5:05:07
	 */
	public static String getFormatTime(String sysDayTime,String timeFormat) {
		Date dTime = null;
		DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
		String timeStr = null;
		try {
			dTime = dfTime.parse(sysDayTime);
			SimpleDateFormat sdfTime = new SimpleDateFormat(timeFormat);
			timeStr = sdfTime.format(dTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeStr;
	}
	/**
	 * @名称 getMonthsAndDays
	 * @描述 获取两个日期是否足月份
	 * @参数 @param beginDate
	 * @参数 @param endDate
	 * @参数 @param format yyyyMMdd
	 * @返回值 boolean
	 * @作者 luanhaowei
	 * @时间 2012-4-13 上午11:13:45
	 */
	public static boolean isFullMonth(String beginDate, String endDate) {
		return getMonthsAndDays(beginDate, endDate)[1] > 0 ? false : true;
	}

	/**
	 * @名称 getMonthsAndDays
	 * @描述 获取两个日期之间的月份数及天数
	 * @参数 @param beginDate
	 * @参数 @param endDate
	 * @参数 @param format yyyyMMdd
	 * @返回值 int[]
	 * @作者 luanhaowei
	 * @时间 2012-4-13 上午11:13:45
	 */
	public static int[] getMonthsAndDays(String beginDate, String endDate) {
		int[] monthsAndDays = new int[2];
		try {
			int month = getMonths(beginDate, endDate);
			String tempEndDate = addMonth(beginDate, month);
			if (compareTo(tempEndDate, endDate) == -1) {
				if (month > 0) {
					month = month - 1;
				}
				tempEndDate = addMonth(beginDate, month, 0);
			}
			int days = getIntervalDays(tempEndDate, endDate);
			monthsAndDays[0] = month;
			monthsAndDays[1] = days;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monthsAndDays;
	}

	/**
	 * @名称 getMonths
	 * @描述 获取两个日期之间的月份数
	 * @参数 @param beginDate
	 * @参数 @param endDate
	 * @返回值 int
	 * @作者 luanhaowei
	 * @时间 2012-4-13 上午11:13:45
	 */
	public static int getMonths(String beginDate, String endDate) {
		int iMonth = 0;
		int flag = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date1 = dateFormat.parse(beginDate);
			Date date2 = dateFormat.parse(endDate);
			Calendar objCalendarDate1 = Calendar.getInstance(TIME_ZONE);
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance(TIME_ZONE);
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1
					.get(Calendar.DAY_OF_MONTH))
				flag = 1;
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 30
					&& objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 31
					&& objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1
							.get(Calendar.YEAR))
				flag = 0;// 草川禾 20110120 添加 处理特殊情况 此处是唯一一种
							// 即结束日期是30天，开始日期日31时计划日期[)情况
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 28
					&& objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 31
					&& objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1
							.get(Calendar.YEAR))
				flag = 0;// 草川禾 20110120 添加 处理特殊情况 此处是唯一一种
							// 即结束日期是30天，开始日期日31时计划日期[)情况
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 28
					&& objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 29
					&& objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1
							.get(Calendar.YEAR))
				flag = 0;// 处理getMonthsAndDays("20120229", "20130228")这种情况
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 29
					&& objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 31
					&& objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1
							.get(Calendar.YEAR))
				flag = 0;// 草川禾 20110120 添加 处理特殊情况 此处是唯一一种
							// 即结束日期是30天，开始日期日31时计划日期[)情况
			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1
					.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
						.get(Calendar.YEAR))
						* 12
						+ objCalendarDate2.get(Calendar.MONTH) - flag)
						- objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH)
						- objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return iMonth;
	}

	/**
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
	public static boolean isLastDayOfMonth(String begin, String end) {
		boolean result = false;
		if (isLastDayOfMonth(begin) && isLastDayOfMonth(end)) {
			result = true;
		}
		return result;
	}

	/**
	 * @名称 isLastDayOfMonth
	 * @描述 判断一个日期是否该月的月末
	 * @参数 @param beginDate
	 * @参数 @return
	 * @返回值 boolean
	 * @作者 luanhaowei
	 * @时间 2012-4-13 上午11:13:45
	 */
	public static boolean isLastDayOfMonth(String beginDate) {
		boolean result = false;
		try {
			Date date = parseEightStrToDate(beginDate);
			Calendar calendar = Calendar.getInstance(TIME_ZONE);
			calendar.setTime(date);
			calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
			if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 方法描述： begin > end 返回 -1，begin = end 返回 0，begin < end 返回 1.
	 * 
	 * @param begin
	 * @param end
	 * @param format
	 * @return int
	 */
	public static int compareTo(String begin, String end) {
		int flag = 0;
		try {
			Date beginDate = parseEightStrToDate(begin);
			Date endDate = parseEightStrToDate(end);
			flag = endDate.compareTo(beginDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 方法描述：判断一个日期是否在两个日期之间
	 * 
	 * @param begin开始日期
	 * @param end结束日期
	 * @param betweenValue中间值
	 * @param DateFormat日期格式yyyyMMdd
	 * @param leftValue是否包括左边界值
	 * @param rightValue是否包括有边界值
	 * @return boolean
	 */
	public static boolean isBetween(String begin, String end, String betweenValue, String DateFormat, boolean leftValue, boolean rightValue) {
		boolean flag = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat);
		try {
			Date beginDate = dateFormat.parse(begin);
			Date endDate = dateFormat.parse(end);
			Date betweenDate = dateFormat.parse(betweenValue);
			if (betweenDate.after(beginDate) && betweenDate.before(endDate)) {
				flag = true;
			}
			if (leftValue) {
				if (betweenDate.compareTo(beginDate) == 0) {
					flag = true;
				}
			}
			if (rightValue) {
				if (betweenDate.compareTo(endDate) == 0) {
					flag = true;
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * 方法描述： 获取date的月初日期
	 * @param date 20170510/2017-05-10
	 * @param format yyyyMMdd/yyyy-mm-dd
	 * @return
	 * String
	 * @author 栾好威
	 * @date 2017-7-11 下午6:04:13
	 */
	public static String getMinMonthDate(String date, String format) {
		String result = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance(TIME_ZONE);
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			
			result = dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}	
	
	/**
	 * 
	 * 方法描述：  获取date的月末日期
	 * @param date 20170510/2017-05-10
	 * @param format yyyyMMdd/yyyy-mm-dd
	 * @return
	 * String
	 * @author 栾好威
	 * @date 2017-7-11 下午6:05:07
	 */
	public static String getMaxMonthDate(String date, String format) {
		String result = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance(TIME_ZONE);
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			result = dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	 /**
     * 
     * 方法描述：通过天数 获取该天数对应几个月 不足一月按一月取值
     * 每月按30天
     * @param days
     * @param format
     * @return 
     * String
     * @author WD
     * @date 2017-8-29 上午11:20:20
     */
	public static String getMonthByDays(String days) {
		String result = "0";
		try {
		 String[] arrMon=MathExtend.calcQuotientAndRemainder(days, "30", 0);
		 String month=arrMon[0];//整月
		 String day=arrMon[1];//余的天数
		 if(MathExtend.comparison(day, "0")>0){//剩余天数大于0
			 result=MathExtend.add(month, "1");
		 }else{
			 result=month;
		 } 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 方法描述：判断一个日期是否周末
	 * 
	 * @param beginDate指定日期
	 * @param format格式
	 * @return boolean
	 * @author 栾好威
	 * @date 2015-5-11 下午8:38:46
	 */
	public static boolean isWeekend(String beginDate, String format) {
		boolean result = false;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Date date = dateFormat.parse(beginDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if (week == 6 || week == 0) {// 0代表周日，6代表周六
				result = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 方法描述：获取指定日期前后N周的周一
	 * 
	 * @param beginDate指定日期
	 * @param n
	 *            n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
	 * @param format返回格式
	 * @return String
	 * @author 栾好威
	 * @date 2015-5-12 上午11:04:51
	 */
	public static String getNextWeekMonday(String beginDate, String format, int n) {
		String result = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Date date = dateFormat.parse(beginDate);
			Calendar calendar = Calendar.getInstance();
			// 默认时，每周第一天为星期日，需要更改一下
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(date);
			// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
			calendar.add(Calendar.DATE, n * 7);
			// 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			result = dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * 方法描述：获取给定日期的次年开始日期 既是次年的0101
	 * @param baseRateDate
	 * @return 
	 * String
	 * @author WD
	 * @date 2017-8-30 下午6:11:30
	 */
	public static String getNextYearBeaginDate(String baseRateDate) {
		String nextYearBeaginDate=null ;
		try {
		 String year=baseRateDate.substring(0, 4);//获取年
		 year=MathExtend.add(year, "1");
		 nextYearBeaginDate=year+"0101";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nextYearBeaginDate;
	}
	/**
	 * 获取两个日志之间的所有日期 
	 * @param begDte
	 * @param endDte
	 * @return
	 */
	public  static List<String>getAllDateStrBetween(String  begDte,String endDte){
		List<String> result=new ArrayList<String>();
		try {
			String tmpDte=begDte;
			
			if(Integer.parseInt(begDte)<Integer.parseInt(endDte)){
				while(Integer.parseInt(tmpDte)<=Integer.parseInt(endDte)){
					if(Integer.parseInt(tmpDte)>=Integer.parseInt(begDte))
						result.add(tmpDte);
					tmpDte=addByDay(tmpDte, 1);//加一天
				}
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result; 
		
	}
	/**
	 * 
	 * 方法描述： 比较两个时间的大小
	 * @param currDatetime1 yyyyMMdd hh:mm:ss
	 * @param currDatetime2 yyyyMMdd hh:mm:ss
	 * @return
	 * int 返回 -1 currDatetime1小于currDatetime2 0 相等1 currDatetime1大于currDatetime2
	 * @author 沈浩兵
	 * @date 2018-1-16 下午5:27:34
	 */
	public static int compareTime(String currDatetime1,String currDatetime2) {
		int flag=0;
		try {
			DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			Date startDateTime = df.parse(currDatetime1);
            Date endDateTime = df.parse(currDatetime2);
            if(startDateTime.getTime()<endDateTime.getTime()){
            	flag=-1;
            }else if(startDateTime.getTime()>endDateTime.getTime()){
            	flag=1;
            }else if(startDateTime.getTime()==endDateTime.getTime()){
            	flag=0;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public static void main(String[] args) {
		System.out.println(compareTime("20180117 11:29:05", "20180117 12:00:00"));
		//System.out.println(getIntervalDays("20170707", "20170707"));
		/*
		long t1 = System.currentTimeMillis();
		System.out.println(getDaysBetween("20170707", "20170709"));
		for (int i = 0; i < 10000; i++) {
			getDaysBetween("20170707", "20170709");
		}
		long t2 = System.currentTimeMillis();
		System.out.println("times:" + (t2-t1));
		System.out.println(getIntervalDays("2017-07-07", "20170701"));
		for (int i = 0; i < 10000; i++) {
			getIntervalDays(StringToDate("20170707", DATE_PATTERN), StringToDate("20170701", DATE_PATTERN));
//			getIntervalDays("20170707", "20170701");
		}
		long t3 = System.currentTimeMillis();
		System.out.println("times:" + (t3-t2));
		*/
	}
}
