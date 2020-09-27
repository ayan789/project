package com.ccic.salessapp.common.utils;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

//import oracle.sql.TIMESTAMP;

/*
 * 日期型工具类
 */
public class DateUtils {
	public static Date StringToDate(String s) {
		java.util.Date d = new java.util.Date();

		Date date;

		if (s == null)
			return null;

		d = new java.util.Date(s);

		date = new Date(d.getTime());

		return date;
	}
	public static Date StringToDate(Object o) {
		java.util.Date d = new java.util.Date();

		Date date;

		if (o == null)
			return null;

		//		d = new java.util.Date("2004-01-01");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = sdf.parse(o.toString());
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		date = new Date(d.getTime());

		return date;
	}

	public static int getSysYear() {
		java.util.Date d = new java.util.Date();

		Date date = new Date(d.getTime());

		return date.getYear();
	}

	public static int getSysMonth() {
		java.util.Date d = new java.util.Date();

		Date date = new Date(d.getTime());

		return date.getMonth();
	}

	public static int getSysDay() {
		java.util.Date d = new java.util.Date();

		Date date = new Date(d.getTime());

		return date.getDay();
	}

	public static String getSysDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new java.util.Date());
	}

	/**
	 * 取得当前时间的日期字符串
	 * @return
	 */
	public static String getDateNow()
	{
		Calendar cal = Calendar.getInstance();
		String retStr = "";
		retStr = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) +1) + "-" + cal.get(cal.DAY_OF_MONTH);
		return retStr;
	}

	public static String getDateSerial() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
		return sdf.format(new java.util.Date());
	}

	public static String getDateShortSerial() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
		return sdf.format(new java.util.Date());
	}

	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new java.util.Date());
	}

	public static String getDateTOString(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new java.util.Date());
	}

	public static String getDateYear(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = new java.util.Date();
		try {
			d = sdf.parse(ds);
			calendar.setTime(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	public static String getDateMonth(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = new java.util.Date();
		try {
			d = sdf.parse(ds);
			calendar.setTime(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return String.valueOf(calendar.get(Calendar.MONTH)+1);
	}

	public static String getDateDay(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = new java.util.Date();
		try {
			d = sdf.parse(ds);
			calendar.setTime(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}

	public static String getDateHour(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		java.util.Date d = new java.util.Date();
		try {
			d = sdf.parse(ds);
			calendar.setTime(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
	}

	public static String formatDateTime(String ds) {
		//Calendar calendar = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		java.text.SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date d = new java.util.Date();
		String df = null;
		try {
			d = sdf2.parse(ds);
			df = sdf.format(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return df;
	}

	public static String formatDate(String ds) {
		//Calendar calendar = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		java.util.Date d = new java.util.Date();
		String df = null;

		java.text.SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d = sdf2.parse(ds);
			df = sdf.format(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return df;
	}

	public static String formatDate() {
		//Calendar calendar = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		java.util.Date d = new java.util.Date();
		String df = null;

		df = sdf.format(d);

		return df;
	}
	public static String formatDate(Date date) {
		if(date==null) {
			return "";
		}
		//Calendar calendar = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String df = null;

		df = sdf.format(date);

		return df;
	}


	public static String formatDateYM() {
		//Calendar calendar = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		java.util.Date d = new java.util.Date();
		String df = null;

		df = sdf.format(d);

		return df;
	}

	public static String formatShortDateTime(String ds) {
		//Calendar calendar = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		java.text.SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date d = new java.util.Date();
		String df = null;
		try {
			d = sdf2.parse(ds);
			df = sdf.format(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return df;
	}

	/**
	 * 将国际时区的时间转换为中国时区时间
	 * @param date
	 * @return
	 */
	public static String getChinaZoneDate(Date date)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone zone = TimeZone.getTimeZone("GMT+8");
		format.setTimeZone(zone);
		return format.format(date);
	}

	/**
	 * 两个日期相差的天、月、年
	 *
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 * @throws Exception
	 */
	public static int compareDate(String date1, String date2, int stype) throws Exception {
		int n = 0;
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			throw new Exception(e3);
		}
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		// System.out.println(date1+" -- "+date2+" 相差多少"+u[stype]+":"+n);
		return n;
	}


	/**
	 * 比较两个日期的大小
	 *
	 * @param dateStart
	 * @param dateEnd
	 * 如果dateStart大于dateEnd则返回大于 0 的值，相等则返回0，dateStart小于dateEnd则返回小于 0 的值
	 * @throws Exception
	 */
	public static int compare(String dateStart, String dateEnd) {
		int result = 0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = df.parse(dateStart);
			endDate = df.parse(dateEnd);
			result = startDate.compareTo(endDate);
		} catch (Exception e3) {

		}

		return result;
	}

	//HZ添加日期转换字符串格式为2011-10-19
	public static String dateToString(Date date) throws Exception {
		SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
		if(date == null){
			return null;
		}
		try {
			String dateStr = datePattern.format(date);
			return dateStr;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 日期对象格式化
	 * @param date
	 * @param
	 *
	 * */
	public static String dateToString(Date date,String pattern) throws Exception {
		SimpleDateFormat datePattern = new SimpleDateFormat(pattern);
		if(date == null){
			return null;
		}
		try {
			String dateStr = datePattern.format(date);
			return dateStr;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 根据oracle的Timestamp获取字符串日期时间
	 * @param t Timestamp时间
	 * @param formatStr 格式化字符串，如果是null默认yyyy-MM-dd
	 * @return 格式化后的字符串
	 */
	/*public static String getDateBySqlTimestamp(Object obj, String formatStr){
		try {
			TIMESTAMP t = (TIMESTAMP)obj;
			if (formatStr == null || formatStr.equals("")) {
				formatStr = "yyyy-MM-dd";
			}
			Timestamp tt;
			tt = t.timestampValue();
			Date date = new Date(tt.getTime());
			SimpleDateFormat sd = new SimpleDateFormat(formatStr);
			return sd.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}*/


	public static Date parse(String str, String pattern) {
		if (str == null || "".equals(str))
			return null;
		try {
			return new SimpleDateFormat(pattern).parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式转换错误", e);
		}
	}

	/**
	 * 两个日期相差的天、月、年
	 *
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 *
	 * @return 注意与compareDate的区别
	 * @throws Exception
	 */
	public static int dateBetween(String date1, String date2, int stype) throws Exception {
		int n = 0;
		String formatStyle = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			throw new Exception(e3);
		}
		while (c1.compareTo(c2)<0) { // 循环对比，直到相等，n 就是所要的结果
			n++;
			if(stype == 0){
				c1.add(Calendar.DATE, 1); // 比较天数，天数+1
			}else if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.YEAR, 1); // 比较年份，年份+1
			}
		}
		return n;
	}

	/** 比较两个时间相差几天小时 */
	public static String calculatetimeGapHour(String time1, String time2) {
		Date endDate, nowDate;
		String returnStr = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		try {
			endDate = simpleDateFormat.parse(time1);
			nowDate = simpleDateFormat.parse(time2);
			long nd = 1000 * 24 * 60 * 60;
			long nh = 1000 * 60 * 60;
			long nm = 1000 * 60;
			// long ns = 1000;
			// 获得两个时间的毫秒时间差异
			long diff = endDate.getTime() - nowDate.getTime();
			// 计算差多少天
			long day = diff / nd;
			// 计算差多少小时
			long hour = diff % nd / nh;
			String dayString=String.valueOf(Math.abs(day));
			String hourString=String.valueOf(Math.abs(hour));

			if(day<0){
				if(Math.abs(hour)<10){
					returnStr="已过期"+dayString+"天0"+hourString+"小时";
				}else{
					returnStr="已过期"+dayString+"天"+hourString+"小时";
				}
			}else if(day==0){
				returnStr= "已到期";
			}else {
				if(Math.abs(hour)<10){
					returnStr=dayString+"天0"+hourString+"小时到期";
				}else{
					returnStr=dayString+"天"+hourString+"小时到期";
				}
			}
			System.out.println(returnStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnStr;
	}
	public static void main(String[] args){

		System.out.println(DateUtils.formatDate("2006-8-23"));
		System.out.println(DateUtils.formatShortDateTime("2006-08-23 20:12:30"));
		System.out.println(DateUtils.formatDateTime("2006-08-23 20:13:22"));

		//String gs = "2002-01-01"; 


		System.out.println(DateUtils.formatDate());
		System.out.println(DateUtils.formatDateYM());

		System.out.println("revertList[0].kindValue".substring(0,"revertList[0].kindValue".indexOf(".")+1));
       System.out.println("一年以后： "+ afterYear("2020-3-11 10:01:00"));
	}
	public static String afterYear(String startTime) {
		//Calendar calendar = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date date = new java.util.Date();
				
				try {
					date = format.parse(startTime);
					//过去1天
					 Calendar c = Calendar.getInstance();
			        c.setTime(date);
			        c.add(Calendar.YEAR,  1);
			        Date d = c.getTime();
//			        String day = 
			        //System.out.println("过去七天："+day);
					return format.format(d);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return ""; 
	}
	
	public static Date afterYearToDate(String startTime) {
		//Calendar calendar = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date date = new java.util.Date();
				
				try {
					date = format.parse(startTime);
					//过去1天
					 Calendar c = Calendar.getInstance();
			        c.setTime(date);
			        c.add(Calendar.YEAR,  1);
			        Date d = c.getTime();
//			        String day = 
			        //System.out.println("过去七天："+day);
					//return format.format(d);
			        return d;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null; 
	}

}
