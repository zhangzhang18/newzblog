package com.zblog.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String SYMBOL_SEP = " ";

	private static final String DATE_STR = "yyyy-MM-dd";

	private static final String TIME_STR = "HH:mm:ss";

	private static final String DATETIME_STR = "yyyy-MM-dd HH:mm:ss";

	private static final String DATEHOUR_STR = "yyyy-MM-dd HH";

	public static String getDateTime(String format, long timeMillis) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		String date = sd.format(new Date(timeMillis));
		return date;
	}

	public static String getNowDate(String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		String date = sd.format(new Date(System.currentTimeMillis()));
		return date;
	}

	/**
	 * @DESC 时间字符串转timestamp
	 * @param time
	 * @return
	 */
	public static Timestamp stringToTimeStamp(String time) {
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == time)
			return null;
		Timestamp ts = Timestamp.valueOf(time);
		return ts;
	}

	/**
	 * 计算两个日期差多少天
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unused")
	public static long dateDiff(String startTime, String endTime) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(DATE_YYYYMMDD);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff / nd;// 计算差多少天
			return day;
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 计算两个日期差多少小时
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unused")
	public static long hourDiff(Date nowTime, Date endTime) throws ParseException {
		// 按照传入的格式生成一个simpledateformate对象
		long l = endTime.getTime() - nowTime.getTime();
		long hour = l / (60 * 60 * 1000);
		return hour;
	}

	/**
	 * 计算两个日期差多少小时
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unused")
	public static long hourDiff(String nowTime, String endTime) throws ParseException {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date now = df.parse(nowTime);

		Date date = df.parse(endTime);
		long l = date.getTime() - now.getTime();
		long hour = l / (60 * 60 * 1000);
		return hour;
	}

	/**
	 * @DESC
	 * @param date
	 * @param mill
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateAddMill(Date date, long mill) throws ParseException {
		long l = date.getTime() + mill;
		return new Date(l);
	}

	/**
	 * @DESC 相差的毫秒数
	 * @param nowTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static long millDiff(Date startTime, Date endTime) {
		long l = startTime.getTime() - endTime.getTime();
		return l;
	}

	/**
	 * 获取日期数组 如果两个日期之间的日子差平均分为 iNum个日期
	 * 
	 * @param startTime
	 * @param endTime
	 * @param iNum
	 * @return
	 */
	public static String[] dateDiff(String startTime, String endTime, int iNum) {
		// Rtn
		String sArray = "";
		SimpleDateFormat sd = new SimpleDateFormat(DATE_YYYYMMDD);
		try {
			// 相差天数
			int day = Integer.parseInt(String.valueOf(dateDiff(startTime, endTime)));
			// 间隔
			int isep = 0;
			if (day <= 0) {
				sArray = startTime + ";";
			} else if (day < iNum) {
				for (int i = 0; i <= day; i++) {
					sArray = sArray + endTime + ";";
					endTime = getSpecifiedDayBefore(endTime);
				}
			} else {
				if (day % iNum == 0) {
					isep = day / iNum;
				} else {
					isep = (day / iNum + 1);
				}
				while (true) {
					sArray = sArray + endTime + ";";
					endTime = getSpecifiedDayBefore(endTime, isep);
					if (sd.parse(endTime).getTime() <= sd.parse(startTime).getTime()) {
						break;
					}
				}
			}
			String[] array = sArray.split(";");
			return array;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unused")
	public static long dateDiff(String startTime, String endTime, String format) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(DATE_YYYYMMDD);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff / nd;// 计算差多少天
			return day;
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unused")
	public static boolean compareTime(String startTime, String endTime, String format) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			return diff < 0 ? true : false;
		}
		catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static final String DATE_YYYYMMDD = "yyyy-MM-dd";

	public static Date str2Date(String sText) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_YYYYMMDD);
		try {
			return sdf.parse(sText);
		}
		catch (ParseException e) {
			//
		}
		return null;
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获取指定日期的前i天
	 * 
	 * @param specifiedDay
	 * @param i
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, int i) {
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - i);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}

	public static String getSpecifiedFormatDayBefore(String format, int i) {
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + i);

		String dayBefore = new SimpleDateFormat(format).format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}

	/** 将今天日期转化成 字符串 */
	public static String getTodayToStr(String sText) {
		if (sText == null || "".equals(sText)) {
			sText = "yyyy-MM-dd";
		}
		return new SimpleDateFormat(sText).format(new Date());
	}

	public static String getNowMonthToStr(String sText) {
		if (sText == null || "".equals(sText)) {
			sText = "yyyy-MM";
		}
		return new SimpleDateFormat(sText).format(new Date());
	}

	public static String getLastMonthToStr(String sText) {
		if (sText == null || "".equals(sText)) {
			sText = "yyyy-MM";
		}
		Calendar c = Calendar.getInstance();
		c.add(c.MONTH, -1);// 得到上个月的月份
		Date date = c.getTime();
		// System.out.println(date);
		return new SimpleDateFormat(sText).format(date);
	}

	public static String getAnyLastMonthToStr(String sText, int lastNum) {
		if (sText == null || "".equals(sText)) {
			sText = "yyyy-MM";
		}
		Calendar c = Calendar.getInstance();
		c.add(c.MONTH, lastNum);// 得到上个月的月份
		Date date = c.getTime();
		// System.out.println(date);
		return new SimpleDateFormat(sText).format(date);
	}

	/** 将指定日期转化成字符串 */
	public static String getTodayToStr(Date sDate) {
		return new SimpleDateFormat("yyyy-MM-dd").format(sDate);
	}

	/** 将指定日期转化成字符串 */
	public static String getDateToStr(Date sDate) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sDate);
	}

	public static String getDateStr(String sDate) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(sDate);
	}

	/** 获取当前日期,格式:<code>yyyy-MM-dd</code> */
	public static final String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_STR);
		String str = sdf.format(new Date());
		return str;
	}

	/** 获取当前时间,格式:<code>HH:mm:ss</code> */
	public static final String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_STR);
		String str = sdf.format(new Date());
		return str;
	}

	/** 获取当前日期时间,格式:<code>yyyy-MM-dd HH:mm:ss</code> */
	public static final String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_STR);
		String str = sdf.format(new Date());
		return str;
	}

	public static final String getNowStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_STR);
		String sdate = sdf.format(Calendar.getInstance().getTime());
		sdate = sdate.replace("-", "");
		sdate = sdate.replace(":", "");
		sdate = sdate.replace(" ", "");
		return sdate;
	}

	/** 获取日期,说明: n=0为当日, 1为明天, -1为昨天 */
	public static final String getDate(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, n);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_STR);
		String str = sdf.format(calendar.getTime());
		return str;
	}

	public static final String getDate(Calendar calendar) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_STR);
		String str = sdf.format(calendar.getTime());
		return str;
	}

	/** 获取时间,说明: n=0为当前小时, 1为后一小时, -1为前一小时 */
	public static final Calendar getHour(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, n);
		return calendar;
	}

	/** 获取小时 */
	public static final int getHour(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取当前日期+小时,格式:<code>yyyy-MM-dd HH</code>
	 * 
	 * @param n
	 *            小时, n=0为当前小时, 1为后一小时, -1为前一小时
	 * @return
	 */
	public static final String getDateAndHour(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, n);

		SimpleDateFormat sdf = new SimpleDateFormat(DATEHOUR_STR);
		String str = sdf.format(calendar.getTime());
		return str;
	}

	public static void main(String[] args) {
		// System.out.println(getNowDate("yyyyMMddHHmmss"));
		// System.out.println(System.currentTimeMillis());
		// System.out.println(getLastMonthToStr("yyyyMM"));
		// System.out.println(getSpecifiedFormatDayBefore("yyyyMMddHHmmss", 1));
		try {
			System.out.println(hourDiff("2013-09-23 12:00:00", "2013-09-24 17:00:00"));
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Timestamp timeStringToTimeStamp(String timeString) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setLenient(false);
		Timestamp ts = null;
		;
		try {
			ts = new Timestamp(format.parse(timeString).getTime());
			// System.out.println(ts.toString());
			return ts;
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ts;
	}

	public static Date timeStringToDate(String timeString) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		Date date = null;
		;
		try {
			date = new Date(format.parse(timeString).getTime());
			// System.out.println(ts.toString());
			return date;
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
