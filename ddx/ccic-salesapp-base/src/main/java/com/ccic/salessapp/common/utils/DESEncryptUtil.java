package com.ccic.salessapp.common.utils;


/*
 * Copyright (c)  2012, 大地保险CCIC
 * All rights reserved.
 *
 * 2013-9-27 下午2:53:24 LiMingjun
 */

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

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
public class DESEncryptUtil {
	private static final Log log = LogFactory.getLog(DESEncryptUtil.class);
	private static SecretKeyFactory keyFactory = null;
	private static Cipher cipher = null;
	static {
		try {
			keyFactory = SecretKeyFactory.getInstance("DES");
		} catch (Exception e) {
			keyFactory = null;
		}
		try {
			cipher = Cipher.getInstance("DES");
		} catch (Exception e) {
			cipher = null;
		}
	}

	/**
	 * DES加密
	 *
	 * @param
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String password) throws Exception {
		if (StringUtils.isBlank(data)) {
			return data;
		}
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			if (keyFactory == null)
				keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			if (cipher == null)
				cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return ByteStringConvertUtil.byte2HexStr(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			log.info("=====加密失败", e);
			throw new Exception("DES加密失败!");
		}
	}

	public static String decrypt(String data, String password) {
		if (StringUtils.isBlank(data)) {
			return data;
		}
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂
			if (keyFactory == null)
				keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			if (cipher == null)
				cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			return new String(cipher.doFinal(ByteStringConvertUtil.hexStr2Byte(data)));
		} catch (Exception e) {
			log.info("=====解密失败", e);
			//throw new Exception("DES解密失败!");
		}
		return null;
	}

}
