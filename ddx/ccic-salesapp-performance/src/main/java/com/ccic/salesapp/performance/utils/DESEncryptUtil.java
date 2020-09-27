package com.ccic.salesapp.performance.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/*
 * Copyright (c)  2012, ��ر���CCIC
 * All rights reserved. 
 *
 * 2013-9-27 ����2:53:24 LiMingjun
 */

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccic.salessapp.common.utils.ByteStringConvertUtil;

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
	 * DES����
	 * 
	 * @param datasource
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
			// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
			if (keyFactory == null)
				keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher����ʵ����ɼ��ܲ���
			if (cipher == null)
				cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// ���ڣ���ȡ���ݲ�����
			// ��ʽִ�м��ܲ���
			return ByteStringConvertUtil.byte2HexStr(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			log.info("=====����ʧ��", e);
			throw new Exception("DES����ʧ��!");
		}
	}

	public static String decrypt(String data, String password) throws Exception {
		if (StringUtils.isBlank(data)) {
			return data;
		}
		try {
			// DES�㷨Ҫ����һ�������ε������Դ
			SecureRandom random = new SecureRandom();
			// ����һ��DESKeySpec����
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// ����һ���ܳ׹���
			if (keyFactory == null)
				keyFactory = SecretKeyFactory.getInstance("DES");
			// ��DESKeySpec����ת����SecretKey����
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher����ʵ����ɽ��ܲ���
			if (cipher == null)
				cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// ������ʼ���ܲ���
			return new String(cipher.doFinal(ByteStringConvertUtil.hexStr2Byte(data)));
		} catch (Exception e) {
			log.info("=====����ʧ��", e);
			throw new Exception("DES����ʧ��!");
		}
	}
	
}
