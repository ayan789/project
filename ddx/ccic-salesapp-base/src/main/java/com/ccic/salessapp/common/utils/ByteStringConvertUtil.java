package com.ccic.salessapp.common.utils;

/*
 * Copyright (c)  2012, 大地保险CCIC
 * All rights reserved.
 *
 * 2013-10-11 下午3:32:38 LiMingjun
 */
/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 *
 * @author LiMingjun
 * @version 1.0
 */
public class ByteStringConvertUtil {
	/**
	 * 字符串转换为字节数据
	 *
	 * @param hexStr
	 * @return
	 */
	public static byte[] hexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte result[] = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 字节数组转换为字符串
	 *
	 * @param buf
	 * @return
	 */
	public static String byte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 255);
			if (hex.length() == 1)
				hex = (new StringBuilder(String.valueOf('0'))).append(hex).toString();
			sb.append(hex.toLowerCase());
		}
		return sb.toString();
	}
}
