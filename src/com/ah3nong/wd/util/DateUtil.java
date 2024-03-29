package com.ah3nong.wd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/** 准备第一个模板，从字符串中提取出日期数字 */
	private static String pat1 = "yyyy-MM-dd HH:mm:ss";
	/** 准备第二个模板，将提取后的日期数字变为指定的格式 */
	private static String pat2 = "yyyy年MM月dd日 HH:mm:ss";
	/** 实例化模板对象 */
	private static SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);
	private static SimpleDateFormat sdf2 = new SimpleDateFormat(pat2);

	/*public static void main(String[] args) {
		String time = "2013-09-04 13:59:21";
		System.out.println(getTime(time));
	}*/

	public static Long farmatTime(String string) {
		Date date = null;
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = Date(sf.parse(string));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	public static Date Date(Date date) {
		Date datetimeDate;
		datetimeDate = new Date(date.getTime());
		return datetimeDate;
	}

	public static Date Dates() {
		Date datetimeDate;
		Long dates = 1361515285070L;
		datetimeDate = new Date(dates);
		return datetimeDate;
	}

	public static String getTime(String commitDate) {
		// 在主页面中设置当天时间
		Date nowTime = new Date();
		String currDate = sdf1.format(nowTime);
		Date date = null;
		try {
			// 将给定的字符串中的日期提取出来
			date = sdf1.parse(commitDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String monthDay = sdf2.format(date).substring(5, 12);
		String yearMonthDay = sdf2.format(date).substring(0, 12);
		int month = Integer.valueOf(monthDay.substring(0, 2));
		int day = Integer.valueOf(monthDay.substring(3, 5));
		if (month < 10 && day < 10) {
			monthDay = monthDay.substring(1, 3) + monthDay.substring(4);
		} else if (month < 10) {
			monthDay = monthDay.substring(1);
		} else if (day < 10) {
			monthDay = monthDay.substring(0, 3) + monthDay.substring(4);
		}
		int yearMonth = Integer.valueOf(yearMonthDay.substring(5, 7));
		int yearDay = Integer.valueOf(yearMonthDay.substring(8, 10));
		if (yearMonth < 10 && yearDay < 10) {
			yearMonthDay = yearMonthDay.substring(0, 5) + yearMonthDay.substring(6, 8) + yearMonthDay.substring(9);
		} else if (yearMonth < 10) {
			yearMonthDay = yearMonthDay.substring(0, 5) + yearMonthDay.substring(6);
		} else if (yearDay < 10) {
			yearMonthDay = yearMonthDay.substring(0, 8) + yearMonthDay.substring(9);
		}
		int flag = (int) (farmatTime(currDate) / 1000 - farmatTime(commitDate) / 1000);
		String des = null;
		int temp = flag;
		if (temp < 60*60*24) {
			if (temp < 60) {
				des = "1分钟前";
			} else if (temp < 60 * 60) {
				des = temp / 60 + "分钟前";
			} else if (temp < 60 * 60 * 24) {
				int hour = temp / (60 * 60);
				des = hour + "小时前";
			}
		}
		if (des == null) {
			des = commitDate.substring(0,commitDate.indexOf(" "));
		}
		return des;
	}
	public static Date Date() {
		Date datetimeDate;
		Long dates = 1361514787384L;
		datetimeDate = new Date(dates);
		return datetimeDate;
	}

}
