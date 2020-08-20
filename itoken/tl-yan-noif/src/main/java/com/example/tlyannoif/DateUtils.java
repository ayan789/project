package com.example.tlyannoif;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public final static String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public final static String MIN_DATE_FORMAT = "yyyy-MM-dd";
	
	public static final SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
	
	//date 格式化日期 String
	public static String getFullDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FULL_DATE_FORMAT);
        return getDateString(date, dateFormat);
    }
	
	//date 格式化日期 date
    public static Date formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        ParsePosition pos = new ParsePosition(0);
        Date newDate = formatter.parse(dateString, pos);
        return newDate;
    }
	
	public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }
        return dateFormat.format(date);
    }
	
	//String2date
	public static Date getStringDate(String dstr, String dateFormatStr) {
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormatStr);//小写的mm表示的是分钟//yyyy-MM-dd
		//String dstr="2008-4-24";
		java.util.Date date = null;
		try {
			date = sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	//date 年
	
	//date 月
	
	//date 日
	
	/**
	* 当前季度的开始时间，即2012-01-1 00:00:00
	*
	* @return
	*/
	public static Date getCurrentQuarterStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
		if (currentMonth >= 1 && currentMonth <= 3)
		c.set(Calendar.MONTH, 0);
		else if (currentMonth >= 4 && currentMonth <= 6)
		c.set(Calendar.MONTH, 3);
		else if (currentMonth >= 7 && currentMonth <= 9)
		c.set(Calendar.MONTH, 4);
		else if (currentMonth >= 10 && currentMonth <= 12)
		c.set(Calendar.MONTH, 9);
		c.set(Calendar.DATE, 1);
		now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
		e.printStackTrace();
		}
		return now;
	}
	
	/**
	* 当前季度的结束时间，即2012-03-31 23:59:59
	*
	* @return
	*/
	public static Date getCurrentQuarterEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
		if (currentMonth >= 1 && currentMonth <= 3) {
		c.set(Calendar.MONTH, 2);
		c.set(Calendar.DATE, 31);
		} else if (currentMonth >= 4 && currentMonth <= 6) {
		c.set(Calendar.MONTH, 5);
		c.set(Calendar.DATE, 30);
		} else if (currentMonth >= 7 && currentMonth <= 9) {
		c.set(Calendar.MONTH,8);
		c.set(Calendar.DATE, 30);
		} else if (currentMonth >= 10 && currentMonth <= 12) {
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DATE, 31);
		}
		now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
		e.printStackTrace();
		}
		return now;
	}
	
	/**
	* 获取前/后半年的开始时间
	* @return
	*/
	public static Date getHalfYearStartTime(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
		if (currentMonth >= 1 && currentMonth <= 6){
		c.set(Calendar.MONTH, 0);
		}else if (currentMonth >= 7 && currentMonth <= 12){
		c.set(Calendar.MONTH, 6);
		}
		c.set(Calendar.DATE, 1);
		now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
		e.printStackTrace();
		}
		return now;
	}
	
	/**
	* 获取前/后半年的结束时间
	* @return
	*/
	public static Date getHalfYearEndTime(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
		if (currentMonth >= 1 && currentMonth <= 6){
		c.set(Calendar.MONTH, 5);
		c.set(Calendar.DATE, 30);
		}else if (currentMonth >= 7 && currentMonth <= 12){
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DATE, 31);
		}
		now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
		e.printStackTrace();
		}
		return now;
	}
	
	/**
	* 当前年的开始时间，即2012-01-01 00:00:00
	*
	* @return
	*/
	public static Date getCurrentYearStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Date now = null;
		try {
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE, 1);
		now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
		e.printStackTrace();
		}
		return now;
	}
	
	/**
	* 当前年的结束时间，即2012-12-31 23:59:59
	*
	* @return
	*/
	public static Date getCurrentYearEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Date now = null;
		try {
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DATE, 31);
		now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
		e.printStackTrace();
		}
		return now;
	}
	
	/**
	* 获得本月的开始时间，即2012-01-01 00:00:00
	*
	* @return
	*/
	public static Date getCurrentMonthStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Date now = null;
		try {
		c.set(Calendar.DATE, 1);
		now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
		e.printStackTrace();
		}
		return now;
	}

	/**
	* 当前月的结束时间，即2012-01-31 23:59:59
	*
	* @return
	*/
	public static Date getCurrentMonthEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Date now = null;
		try {
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
		e.printStackTrace();
		}
		return now;
	}
	
	public static Date strToDateLong(String strDate) {
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     ParsePosition pos = new ParsePosition(0);
	     Date strtodate = formatter.parse(strDate, pos);
	     return strtodate;
	}
	
	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
		//System.out.println(DateUtils.getStringDate("2008-4-24",MIN_DATE_FORMAT));
		//当季度开始时间
		Date dateStart = DateUtils.getCurrentYearStartTime(strToDateLong("2020-08-10 00:00:00"));
		//当季度结束时间
		Date dateEnd = DateUtils.getCurrentYearEndTime(strToDateLong("2020-08-10 00:00:00"));
		String str1Quarter = sdf.format(dateStart);
		String str2Quarter = sdf.format(dateEnd);
		System.out.println(str1Quarter);
		System.out.println(str2Quarter);
		
	    String str1 = sdf.format(dateStart);
	    String str2 = sdf.format(dateEnd);
	    Calendar c = Calendar.getInstance();
		c.setTime(dateStart);
		int day1 = c.get(Calendar.DAY_OF_YEAR);
		c.setTime(dateEnd);
		int day2 = c.get(Calendar.DAY_OF_YEAR);
		//System.out.println(day1+"-"+day2);
		//1月 1-31
		//2月 32-60
		//3月 61-91
		//4月 92-121
		//5月 122-152
		//6月 153-182
		//7月 183-213
		//8月 214-244
		//9月 245-274
		//10月 275-305
		//11月 306-335
		//12月 336-366
		
		//1季度 1-91
		//2季度 92-182
		//3季度 122-274
		//4季度 275-366
		
		//上半年 1-182
		//下半年 183-366
		
		//一年 1-366
		
		
		
		try {
			Date  smdate = DateUtils.getStringDate("2020-01-01", "yyyy-MM-dd");
			Date  bdate = DateUtils.getStringDate("2020-02-01", "yyyy-MM-dd");
			Date  bdate2 = DateUtils.getStringDate("2020-04-01", "yyyy-MM-dd");
			int nums = DateUtils.daysBetween(smdate, bdate);
			int nums2 = DateUtils.daysBetween(smdate, bdate2);
			//保障 1-3月
			System.out.println(nums+"-"+nums2);
			bdate = DateUtils.getStringDate("2020-05-01", "yyyy-MM-dd");
			bdate2 = DateUtils.getStringDate("2020-07-01", "yyyy-MM-dd");
			//保障 4-6月
			nums = DateUtils.daysBetween(smdate, bdate);
			nums2 = DateUtils.daysBetween(smdate, bdate2);
			System.out.println(nums+"-"+nums2);
			bdate = DateUtils.getStringDate("2020-08-01", "yyyy-MM-dd");
			bdate2 = DateUtils.getStringDate("2020-10-01", "yyyy-MM-dd");
			//保障 7-9月
			nums = DateUtils.daysBetween(smdate, bdate);
			nums2 = DateUtils.daysBetween(smdate, bdate2);
			System.out.println(nums+"-"+nums2);
			//保障 10-12月
			nums = DateUtils.daysBetween(smdate, bdate);
			nums2 = DateUtils.daysBetween(smdate, bdate2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
