package com.ccic.salesapp.noncar.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

public class OrderUtil {

	public static String generatorOrderNo(String comcode) {
		Date date = new Date();
		String num = ("" + date.getTime()).substring(0, 12);
		String currenYear = new SimpleDateFormat("yyyy").format(new Date());
		StringBuffer orderNum = new StringBuffer();
		if(comcode.length()>=4){
			orderNum.append(comcode.substring(0, 4));// 归属机构前四位
		}else{
			orderNum.append("");
		}	
		orderNum.append(currenYear);// 四位年份
		orderNum.append(num);// 12位流水号
		orderNum.append(RandomStringUtils.randomNumeric(4));// 4位随机数
		return orderNum.toString();
	}
}
