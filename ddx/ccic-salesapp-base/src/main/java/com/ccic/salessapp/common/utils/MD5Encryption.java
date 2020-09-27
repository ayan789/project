/**
 * @TitleMD5Encryption.java
 * @Description TODO
 * @company �������
 * @author Ǯ����
 * @date MAR 25, 201515:11:51 PM
 * @version v1.0
 */
package com.ccic.salessapp.common.utils;

import java.security.MessageDigest;

/**
 * MD5���ܷ���
 * @author Administrator
 *
 */
public class MD5Encryption {

	//����ȫ��char����
	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	    'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * ��ȡMD5�����ַ���
	 * ����toMD5(ͷ��8λ������+����������+β��8λ������)
	 * @param str
	 * @return
	 */
	public static String getMD5Encryption(String str){
		try{
			//����ͷ��8λ
			StringBuffer encryHead = new StringBuffer();
			//����β��8λ
			StringBuffer encryEnd = new StringBuffer();
			//��ʼstr��ӦMD5ֵ
			String iniMD5 = toMD5(str);
			//ת��ΪcharArray
			char c[] = iniMD5.toCharArray();
			//��ȡͷ8λ������
			for(int i=0;i<8;i++){
				int asc = toASCII(c[i])%32;//��32ȡĦ��ȡ����λ
				encryHead.append(c[asc]);//��ȡ����λ��Ӧֵ
			}
			//��ȡβ8λ������
			for(int j=c.length-8;j<c.length;j++){
				int asc = toASCII(c[j])%32;//��32ȡĦ��ȡ����λ
				encryEnd.append(c[asc]);//��ȡ����λ��Ӧֵ
			}
			//�������յ�MD5�����ַ���
			return toMD5(encryEnd.toString()+str+encryEnd.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ��ȡ�ַ���Ӧ��ASCII��
	 * @param c
	 * @return
	 */
	private static int toASCII(char c) {
		int asc = (int)c;
		return asc;
	}

	/**
	 * �ַ���MD5����ͨ�÷���
	 * @param str
	 * @return
	 */
	private static String toMD5(String str) {
		try {
			byte[] strTemp = str.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(strTemp);
			byte[] md = messageDigest.digest();
			int j = md.length;
			char c0[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				c0[k++] = hexDigits[byte0 >>> 4 & 0xf];
				c0[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(c0);
		} catch (Exception e) {
			return null;
		}
	}

}
