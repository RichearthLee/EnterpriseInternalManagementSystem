package com.mftcc.interior.report.common;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mftcc.interior.report.bean.LeaveDaysBean;

public class CountDaysOfLeave {

	
	//节假日查询接口 使用方法 url+yyyyMMdd
	 
	public static final String goSeekURL = "http://api.goseek.cn/Tools/holiday?date=";
	// 时间格式化
	public final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * * 从公认网站上获取 节假日信息
	 * 获取节假日的 公认网站 url="网站地址"+时间 时间格式yyyyMMdd
	 * @return 工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2, 网站失效-1, 数据格式改变-2
	 */
	public static String getHoliday(String url) {
		URL urlTime;
		String json = "";
		try {
			urlTime = new URL(url);
			HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) urlTime.openConnection();// 打开连接
				BufferedReader br = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "UTF-8"));// 获取输入流
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {// 循环读取流
					sb.append(line);
				}
				br.close();// 关闭流
				connection.connect();// 连接会话
				json = sb.toString();
				if (json.contains("data")) {
					//System.err.println(json.substring(json.length()-2,json.length()-1));
					return json.substring(json.length() - 2, json.length() - 1);
				} else {
					return "-2";
				}

			} catch (IOException e) {
				return "-1";
			}
		} catch (MalformedURLException e) {
			return "-1";
		}
	}

	/**
	 * 判断某年某月有多少天
	 * 
	 * @param year 年 "yyyy"
	 * @param moun 月 "m"
	 * @return
	 */
	public static int daysMonth(int year, int moun) {
		switch (moun) {
		case 0:return 31;
		case 1:return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ? 29: 28;
		case 2:return 31;
		case 3:return 30;
		case 4:return 31;
		case 5:return 30;
		case 6:return 31;
		case 7:return 31;
		case 8:return 30;
		case 9:return 31;
		case 10:return 30;
		case 11:return 31;
		default:return 0;
		}
	}

	/**
	 * 通过时间秒毫秒判断两个时间的间隔
	 * 
	 * @param tiny 开始时间
	 * @param large结束时间
	 * @return 返回几天
	 */
	public static int differentDaysByMilliSecond(Date tiny, Date large) {
		int days = (int) ((large.getTime() - tiny.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * 
	 * @param tiny
	 *            开始时间
	 * @param large结束时间
	 * @return 返回几小时
	 */
	public static double differentHoursByMilliSecond(Date tiny, Date large) {
		double hours = ((double) ((large.getTime() - tiny.getTime())) / (1000 * 3600));
		return hours;
	}

	/**
	 * 时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param start 请假开始时间 yyyy-MM-dd HH:mm:ss
	 * @param end 请假结束时间 yyyy-MM-dd HH:mm:ss
	 * @param startM 上班早晨开始时间 HH:mm:ss
	 * @param endE 上班早晨结束时间 HH:mm:ss
	 * @param startAf 上班下午开始时间 HH:mm:ss
	 * @param endAf 上班下午结束时间 HH:mm:ss
	 * @return
	 */
	public String startM;
	public String endE;
	public String startAf;
	public String endAf;
	public void setTime(String startM1,String endE1, String startAf1, String endAf1){
		startM=startM1;
		endE=endE1;
		startAf=startAf1;
		endAf=endAf1;
	}
	public List<LeaveDaysBean> calculateTimeHour(String start, String end,LeaveDaysBean leaveDaysBean){
		List<LeaveDaysBean> leaveDaysBeans = new ArrayList();
		if (start != null && end != null && startM != null && endE != null
				&& startAf != null && endAf != null) {// 确保时间格式正确和值存在
			try {
				Date dataStart = CountDaysOfLeave.format.parse(start);
				Date dataEnd = CountDaysOfLeave.format.parse(end);

				double hours = 0;
				int monthDays = -1;
				int yearTime = dataStart.getYear() + 1900;
				int mounthTime = dataStart.getMonth();
				int dataTime = dataStart.getDate();

				double lunchBreak = differentHoursByMilliSecond(
						CountDaysOfLeave.format.parse("2018" + "-01-" + "01 "+ endE),
						CountDaysOfLeave.format.parse("2018" + "-01-" + "01 "+ startAf));
				int interval = CountDaysOfLeave.differentDaysByMilliSecond(
						dataStart, dataEnd);

				// 判断这年这月有多少天
				monthDays = CountDaysOfLeave.daysMonth(yearTime, mounthTime);
				// 第一天请假开始时间
				String startday = start.substring(start.lastIndexOf(" ") + 1,
						start.length() - 1);
				// 最后一天请假结束时间
				String endday = end.substring(end.lastIndexOf(" ") + 1,
						end.length() - 1);

				for (int i = 0; i <= interval; i++) {

					if (interval == 0) {// 间隔0天 也就是 请假在一天之内
						hours = differentHoursByMilliSecond(dataStart, dataEnd);
					} else {// 间隔超过一天
						dataTime += (i == 0 ? 0 : 1);
						if (dataTime > monthDays) {
							mounthTime++;
							dataTime = 1;
							if (mounthTime > 11) {
								yearTime++;
								mounthTime = 0;
								dataTime = 1;
							}
							monthDays = CountDaysOfLeave.daysMonth(yearTime,
									mounthTime);
						}

						// 判断是否是节假日
						if (!"0".equals(CountDaysOfLeave
								.getHoliday(CountDaysOfLeave.goSeekURL
										+ yearTime
										+ (((mounthTime + 1)) >= 10 ? (mounthTime + 1)
												: "0" + (mounthTime + 1))
										+ (dataTime > 10 ? dataTime : "0"
												+ dataTime)))) {
							String temp=""+ yearTime+"-"+ (((mounthTime + 1)) >= 10 ? (mounthTime + 1): "0" + (mounthTime + 1))+"-"
									+ (dataTime > 10 ? dataTime : "0"+ dataTime);
							LeaveDaysBean ldb=new LeaveDaysBean();
							ldb.setDateoftoday(temp);
							ldb.setFlag("0");
							leaveDaysBeans.add(ldb);
							System.out.println(temp+ "非工作日期不需要计算");
							
							continue;
						}

						dataStart = CountDaysOfLeave.format.parse(yearTime
								+ "-"
								+ (((mounthTime + 1)) >= 10 ? (mounthTime + 1)
										: "0" + (mounthTime + 1)) + "-"
								+ (dataTime >= 10 ? dataTime : "0" + dataTime)
								+ " " + (i == 0 ? startday : startM));
						dataEnd = CountDaysOfLeave.format.parse(yearTime
								+ "-"
								+ (((mounthTime + 1)) >= 10 ? (mounthTime + 1)
										: "0" + (mounthTime + 1)) + "-"
								+ (dataTime >= 10 ? dataTime : "0" + dataTime)
								+ " " + (i == interval ? endday : endAf));
						hours = differentHoursByMilliSecond(dataStart, dataEnd);
						if (i == interval) {// 判断最后一天是否超过午休
							if (endday.compareTo(startAf) < 0) {
								lunchBreak = 0;
							}
						}
					}
					String temp1=yearTime+ "-"+ (((mounthTime + 1)) >= 10 ? (mounthTime + 1) : "0"
									+ (mounthTime + 1)) + "-"+ (dataTime >= 10 ? dataTime : "0" + dataTime);
					double temp2=hours - lunchBreak;
					System.out.println(temp1+ " 请假：" + temp2 + "小时");
					LeaveDaysBean ldb=new LeaveDaysBean();
					ldb.setDateoftoday(temp1);
					ldb.setHours(temp2);
					ldb.setFlag("1");
					leaveDaysBeans.add(ldb);
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		return leaveDaysBeans;
	}

	/*public static void main(String args[]) throws IOException {
		CountDaysOfLeave qj = new CountDaysOfLeave();
		// 测试
		qj.setTime("09:00:00", "12:00:00", "13:00:00", "17:30:00");
		qj.calculateTimeHour("2018-04-27 09:30:00", "2018-05-02 14:00:00");
	}*/
}

