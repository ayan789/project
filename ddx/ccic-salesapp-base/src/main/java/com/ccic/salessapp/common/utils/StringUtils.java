package com.ccic.salessapp.common.utils;

import java.nio.charset.Charset;
import java.text.DecimalFormat;

/**
 * 字符串工具类
 *
 * */
public class StringUtils {

	/**
	 * 计算字符长度，中文占两个字符
	 * */
	public static int length(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/**
	 * 格式化金额字符串保留两位小数
	 *
	 * */
	public static String format2MoneyStr(double value) {
		String formatStr = null;
		DecimalFormat df = new DecimalFormat("#0.00");
		formatStr = df.format(value);
		return formatStr;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static byte[] bytes(CharSequence str, String charset){
		if (str == null) {
			return null;
		}
		if (charset == null) {
			return str.toString().getBytes();
		}
		return str.toString().getBytes(Charset.forName(charset));
	}

	public static String str(byte[] bytes, String charsetName){
		return str(bytes, Charset.forName(charsetName));
	}

	public static String str(byte[] bytes, Charset charset){
		if (null == bytes) {
			return null;
		}

		if (null == charset) {
			return new String(bytes);
		}
		return new String(bytes, charset);
	}

}
