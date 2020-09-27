package com.ccic.salesapp.performance.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		System.out.println(Test.isNowFormatStr(new Date()));
		System.out.println(Test.isYestFormatStr(new Date()));
	}

	public static String isNowFormatStr(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(calendar.getTime());
		return dateString;
	}

	public static String isYestFormatStr(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(c.getTime());
		return dateString;
	}

}
