package com.ccic.salesapp.performance.utils;

import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.PrintStream;

public class DateDiffMonth{
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		Date start = df.parse("2020-08-31");
	    Date end = df.parse("2020-09-01");
		System.out.println(getMonth(start, end));
	}

	public static int getMonth(Date start, Date end) {
	    if (start.after(end)) {
	        Date t = start;
	        start = end;
	        end = t;
	    }
	    Calendar startCalendar = Calendar.getInstance();
	    startCalendar.setTime(start);
	    Calendar endCalendar = Calendar.getInstance();
	    endCalendar.setTime(end);
	    Calendar temp = Calendar.getInstance();
	    temp.setTime(end);
	    temp.add(Calendar.DATE, 1);
	
	    int year = endCalendar.get(Calendar.YEAR)
	            - startCalendar.get(Calendar.YEAR);
	    int month = endCalendar.get(Calendar.MONTH)
	            - startCalendar.get(Calendar.MONTH);
	
	    if ((startCalendar.get(Calendar.DATE) == 1)
	            && (temp.get(Calendar.DATE) == 1)) {
	        return year * 12 + month + 1;
	    } else if ((startCalendar.get(Calendar.DATE) != 1)
	            && (temp.get(Calendar.DATE) == 1)) {
	        return year * 12 + month;
	    } else if ((startCalendar.get(Calendar.DATE) == 1)
	            && (temp.get(Calendar.DATE) != 1)) {
	        return year * 12 + month;
	    } else {
	        return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
	    }
	}
}