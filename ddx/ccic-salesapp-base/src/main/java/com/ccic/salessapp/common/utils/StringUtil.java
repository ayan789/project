package com.ccic.salessapp.common.utils;

import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: 易星科技</p>
 * @author
 * @version 1.0
 */

public class StringUtil {
	static private final String NATIVE = "GBK";
	static private final String UNICODE = "ISO8859-1";
	public StringUtil() {
	}

	/**
	 * 格式化double值,
	 * @param dblValue  double值
	 * @param strFormat 格式串("00000.000")
	 * @return 返回格式串
	 */
	public static String format(double dblValue, String strFormat) {
		java.text.DecimalFormat df = new java.text.DecimalFormat(strFormat);
		return df.format(dblValue);

	}

	/**
	 * 格式化String值,
	 * @param dblValue  double值
	 * @param strFormat 格式串("00000.000")
	 * @return 返回格式串
	 */
	public static String format(String strValue, String strFormat) {
		java.text.DecimalFormat df = new java.text.DecimalFormat(strFormat);
		return df.format(strToDouble(strValue));
	}

	/**
	 * 字符转换成double
	 * @param  str 字符串值
	 * @return double值
	 */
	public static double strToDouble(String str) {
		String loghead = new String("TypeConver:str2float:");
		double ret = 0.00;
		try {
			boolean flag = (null == str);
			flag = flag || (str.trim().length() < 1);
			if (flag) {
				ret = 0.00;
			}
			else {
				ret = Double.parseDouble(str);
			}
		}
		catch (NumberFormatException e) {
			ret = 0.00;
			System.out.println(loghead + e.toString());
		}
		return ret;
	}

	/*
	 * 字符串转换为float
	 */
	public static Float strToFloat(String attribute) {
		if("".equals(attribute) || attribute == null){
			return 0f;
		}else
			return Float.parseFloat(attribute);
	}


	/**
	 * null对象转换为空字符串
	 * @param value 字符串的源对象
	 * @return 处理后的字符串
	 */
	public static String nullToEmpty(String value) {
		if (value == null)
			return "";
		return value;
	}

	/**
	 *如果value==null或value="" 返回true,
	 * @param value 字符串的源对象
	 * @return true or false
	 */
	public static boolean isNullorEmpty(String value) {
		if (value == null || "".equals(value))
			return true;
		return false;
	}

	public static String nullOrEmptyToValue(String sourceString, String value) {
		if (sourceString == null || "".equals(sourceString))
			return value;
		return sourceString;
	}

	/**
	 * 加密一个字符串
	 * @param message 要加密的字符串信息
	 * @return 加密后的字符串信息
	 */
	static public String encode(String message) {
		try {
			MessageDigest alg = MessageDigest.getInstance("SHA-1");
			alg.update(message.getBytes());
			byte[] encoded = alg.digest();
			StringBuffer buf = new StringBuffer(encoded.length * 2);
			for (int n = 0; n < encoded.length; n++) {
				if ( (encoded[n] & 0XFF) < 16) buf.append('0');
				buf.append(java.lang.Integer.toHexString(encoded[n] & 0XFF));
			}
			return buf.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 过滤字符串<,>,',",&
	 * @param value
	 * @return
	 */
	public static String filter(String value) {

		if (value == null || "".equals(value))
			return (null);

		char content[] = new char[value.length()];
		value.getChars(0, value.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
				case '<':
					result.append("&lt;");
					break;
				case '>':
					result.append("&gt;");
					break;
				case '&':
					result.append("&amp;");
					break;
				case '"':
					result.append("&quot;");
					break;
				case '\'':
					result.append("&#39;");
					break;
				default:
					result.append(content[i]);
			}
		}
		return (result.toString());

	}

	/**
	 * 将unicode字符转gbk为字符
	 * @param s 源字串
	 * @return unicode的字串
	 */
	public static String unicodeToNative(String s) {

		if (s == null)
			return null;
		String v = null;
		if (s != null)
			try {
				byte[] bytes = s.getBytes(UNICODE);
				v = new String(bytes, NATIVE);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return v;
	}

	/**
	 * 将gbk字符转为unicode字符
	 * @param s 源字串
	 * @return unicode的字串
	 */
	public static String nativeToUnicode(String s) {

		String v = null;
		if (s != null)
			try {
				byte[] bytes = s.getBytes(NATIVE);
				v = new String(bytes, UNICODE);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return v;
	}

	/**
	 * 将gbk字符转为unicode字符
	 * @param s 源字串
	 * @return unicode的字串
	 */
	public static String unicodeTonative(String s) {

		String v = null;
		if (s != null)
			try {
				byte[] bytes = s.getBytes(UNICODE);
				v = new String(bytes,NATIVE );
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return v;
	}

	/**
	 * 取得带分割符号的字符串
	 * 例如：1,2,3,4,5或者,1,2,3 或者1,2,3,
	 * @param string String  源字符串
	 * @param splitChar String  分割符号
	 * @return ArrayList  返回去掉分割符号后的字符
	 * @see  add by
	 */
	public static ArrayList getSplitString(String string, String splitChar) {
		if (string == null || string.equals("") || string.equals(splitChar)) {
			return new ArrayList();
		}
		boolean flag = true;
		ArrayList list = new ArrayList();
		int count = 0;
		while (flag) {
			int i = string.indexOf(splitChar);
			int j = string.indexOf(splitChar, i + 1);
			if (j > -1) {
				if (count == 0 && i > 0) { //特殊处理第一个字符不是分割符号的情况
					list.add(string.substring(0, i));
					string = string.substring(i);
				}
				else {
					list.add(string.substring(i + 1, j));
					string = string.substring(j);
				}
			}
			else {
				String str = string.substring(i + 1);
				if (!str.equals("")) {
					list.add(str);
				}
				flag = false;
			}
			count++;
		}
		return list;
	}

	/**
	 * 转换成UTF-8编码
	 * @param s String
	 * @return String
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= '\377') {
				sb.append(c);
			}
			else {
				byte b[];
				try {
					b = Character.toString(c).getBytes("utf-8");
				}
				catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) {
						k += 256;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将字符串写入至文件
	 * @param filePaht -- 文件名（含完整的路径）
	 * @param content -- 字符串内容
	 * @return
	 * @throws Exception
	 */
	public static int writeFileString (String filePath,String content) throws Exception{

		FileOutputStream   file = null;
		int retInt = 0;
		try{
			if(filePath != null && !"".equals(filePath))
			{
				file = new   FileOutputStream(filePath);
				byte[] bytes = content.getBytes("utf-8");
				file.write(bytes);
				file.flush();
				file.close();
				retInt = 1;
			}
		} catch(Exception e){
			e.printStackTrace();
			retInt = 0;
		}finally
		{
			if(file != null)
			{
				file.close();
			}
		}
		return retInt;
	}
}
