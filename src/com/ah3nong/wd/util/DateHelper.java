package com.ah3nong.wd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 */
public class DateHelper {

	// define date's formate[yyyy/MM/dd],if you modify printf date formate,so
	// modify this
	private static final String DATE_FORMAT = "dd/MM/yyyy";

	// private static final String DEFAUL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * this method is get date string according date
	 * 
	 * @param date
	 *            want to format's date
	 * @return String result is date string
	 */
	public static String date2Str(Date date) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		try {
			String result = sdf.format(date);
			return result;
		} catch (Exception e) {
			return "";
		}
	}

	public static String date2StrClearDhms(Date date) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String result = sdf.format(date);
			return result;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * this method is change string to date format
	 * 
	 * @param dateStr
	 *            want to change's param
	 * @return return date return null is date string is invlidate
	 */
	public static Date str2Date(String dateStr) {
		Date result = null;
		if (!Validity.isNullAndEmpty(dateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			try {
				result = sdf.parse(dateStr);
			} catch (ParseException e) {
				result = null;
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * this method is check year's validate
	 * 
	 * @param year
	 *            a string for check
	 * @return the String is a year or not
	 */
	public static boolean isYear(String year) {
		boolean is = false;
		if (!Validity.isNullAndEmpty(year)) {

			Pattern p = Pattern.compile("[1-9][0-9][0-9][0-9]");
			if (p.matcher(year).matches()) {
				is = true;
			}
		}
		return is;
	}

	/**
	 * this method is check month's validate
	 * 
	 * @param month
	 *            a string for check
	 * @return the String is a month or not
	 */
	public static boolean isMonth(String month) {
		boolean is = false;
		if (!Validity.isNullAndEmpty(month)) {

			Pattern p = Pattern.compile("0?[1-9]|1[0-2]");
			if (p.matcher(month).matches()) {
				is = true;
			}

		}
		return is;
	}

	/**
	 * this method is check day's validate
	 * 
	 * @param day
	 *            a string for check
	 * @return the String is a day or not
	 */
	public static boolean isDay(String day) {
		boolean is = false;
		if (!Validity.isNullAndEmpty(day)) {

			Pattern p = Pattern.compile("[12][0-9]|0?[1-9]|3[01]");
			if (p.matcher(day).matches()) {
				is = true;
			}
		}
		return is;
	}

	/**
	 * clear HH:mm:ss from a Date object
	 * 
	 * @param date
	 *            a Date object
	 * @return Date object
	 * @author zdxue
	 */
	public static Date clearHHmmss(Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return f.parse(f.format(date));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * add one hours of time
	 * 
	 * @param c
	 *            will changed time
	 * @param step
	 *            want add any hours
	 * @return Calendar object, is add one hours
	 */
	private static Calendar addHours(Calendar c, int step) {
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + step);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 00);
		c.set(Calendar.MILLISECOND, 00);
		return c;
	}

	/**
	 * add one days of time
	 * 
	 * @param c
	 *            will changed time
	 * @param step
	 *            want add any days
	 * @return alendar object, is add one days
	 */
	private static Calendar addDays(Calendar c, int step) {
		c.set(Calendar.DATE, c.get(Calendar.DATE) + step);

		c.set(Calendar.HOUR_OF_DAY, 00);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 00);
		c.set(Calendar.MILLISECOND, 00);

		return c;
	}

	public static Date getLastYearEndMouthDate(Calendar c){
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) -1);
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DATE, c.get(Calendar.DATE));
		c.set(Calendar.HOUR_OF_DAY, 00);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 00);
		c.set(Calendar.MILLISECOND, 00);
		return c.getTime();
	}
	
	public static Date getLastMonthDate(Calendar c){
		if(c.get(Calendar.MONTH)==0){
			c.set(Calendar.YEAR, c.get(Calendar.YEAR)-1);
		}else{
			c.set(Calendar.YEAR, c.get(Calendar.YEAR));
		}
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		c.set(Calendar.DATE, c.get(Calendar.DATE));
		c.set(Calendar.HOUR_OF_DAY, 00);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 00);
		c.set(Calendar.MILLISECOND, 00);
		return c.getTime();
	}
	
	
	public static Calendar addYears(Calendar c, int step) {
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) + step);
		c.set(Calendar.DATE, c.get(Calendar.DATE));
		c.set(Calendar.HOUR_OF_DAY, 00);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 00);
		c.set(Calendar.MILLISECOND, 00);
		return c;
	}

	// public static void main(String[] args) {
	// System.out.println(Calendar.getInstance().getTime());
	// System.out.println(addYears(Calendar.getInstance(),-1).getTime());
	// }

	/**
	 * return add hours of time space
	 * 
	 * @param startDate
	 *            start time
	 * @param endDate
	 *            end time
	 * @param step
	 *            want add any hours
	 * @return List<Calendar> allHours is collection
	 */
	public static List<Calendar> splitHoursOfTime(Calendar startDate,
			Calendar endDate, int step) {
		List<Calendar> allHours = new LinkedList<Calendar>();

		allHours.add((Calendar) startDate.clone());
		startDate = addHours(startDate, step);

		while (startDate.getTimeInMillis() <= endDate.getTimeInMillis()) {
			Calendar cloneDate = (Calendar) startDate.clone();

			allHours.add(cloneDate);
			startDate = addHours(startDate, step);
		}
		if (endDate.get(Calendar.MINUTE) != 0
				|| endDate.get(Calendar.SECOND) != 0) {
			allHours.add(endDate);
		}
		return allHours;
	}

	/**
	 * return add days of time space
	 * 
	 * @param startDate
	 *            start time
	 * @param endDate
	 *            end time
	 * @param step
	 *            want add any hours
	 * @return List<Calendar> allHours is collection
	 */
	public static List<Calendar> splitDaysOfTime(Calendar startDate,
			Calendar endDate, int step) {
		List<Calendar> allDays = new LinkedList<Calendar>();

		allDays.add((Calendar) startDate.clone());
		startDate = addDays(startDate, step);

		while (startDate.getTimeInMillis() <= endDate.getTimeInMillis()) {
			Calendar cloneDate = (Calendar) startDate.clone();

			allDays.add(cloneDate);
			startDate = addDays(startDate, step);
		}

		if (endDate.get(Calendar.HOUR_OF_DAY) != 0
				|| endDate.get(Calendar.MINUTE) != 0
				|| endDate.get(Calendar.SECOND) != 0) {
			allDays.add(endDate);
		}
		return allDays;
	}

	/**
	 * return add hours of time space
	 * 
	 * @see DateHelper#allHoursOfTime(Calendar, Calendar)
	 * @param startDate
	 *            start date
	 * @param endDate
	 *            end date
	 * @param step
	 *            want add any hours
	 * @return List<Calendar> allHours is collection
	 */
	public static List<Calendar> splitHoursOfTime(Date startDate, Date endDate,
			int step) {
		Calendar c = new GregorianCalendar();
		c.setTime(startDate);

		Calendar startCalendar = (Calendar) c.clone();
		c.setTime(endDate);

		return splitHoursOfTime(startCalendar, c, step);
	}

	/**
	 * return add days of time space
	 * 
	 * @see DateHelper#allDaysOfTime(Calendar, Calendar)
	 * @param startDate
	 *            start date
	 * @param endDate
	 *            end date
	 * @param step
	 *            want add any days
	 * @return List<Calendar> allDays is collection
	 */
	public static List<Calendar> splitDaysOfTime(Date startDate, Date endDate,
			int step) {
		Calendar c = new GregorianCalendar();
		c.setTime(startDate);

		Calendar startCalendar = (Calendar) c.clone();
		c.setTime(endDate);

		return splitDaysOfTime(startCalendar, c, step);
	}

	/**
	 * get the first date of current year
	 * 
	 * @return date
	 * 
	 */
	public static Date getFirstDayOfCurrentYear() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.set(currentDate.get(Calendar.YEAR) - 1, 12, 1, 0, 0, 0);
		return currentDate.getTime();
	}

	/**
	 * get the end date of current year
	 * 
	 * @return date
	 * 
	 */
	public static Date getLastDayOfCurrentYear() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.set(currentDate.get(Calendar.YEAR), 11, 31, 23, 59, 59);
		return currentDate.getTime();
	}

	/**
	 * 获得给定日期的前面的所有的月份日期
	 * 
	 * @return
	 */
	public static List<Date> getPassedMonths(Date endDate) {
		List<Date> dateList = new ArrayList<Date>();
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int month = end.get(Calendar.MONTH);
		for (int i = 0; i < month - 1; i++) {
			end.set(Calendar.MONTH, i);
			dateList.add(end.getTime());
		}
		return dateList;
	}

	/**
	 * get the first date of current year
	 * 
	 * @return date
	 * 
	 */
	public static Date getFirstDayOfYear(int year) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.set(year, 0, 1, 0, 0, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * get the end date of current year
	 * 
	 * @return date
	 * 
	 */
	public static Date getLastDayOfYear(int year) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.set(year, 11, 31, 23, 59, 59);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTime();
	}
	
	public static Date getDateByYearAndMonth(int year,int month){
		Calendar c = Calendar.getInstance();
		c.set(year, month-1, 1);
		return clearHHmmss(c.getTime());
	}
	
//	public static void main(String[] args) {
//		System.out.println(getFirstDayOfYear(2010));
//		System.out.println(getFirstDayOfYear(2011));
//		System.out.println(getLastDayOfYear(2010));
//		System.out.println(getLastDayOfYear(2011));
//	}
	
}
