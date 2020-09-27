package com.ccic.salesapp.noncar.service;

import java.util.List;

import com.ccic.salesapp.noncar.dto.Prpdcompany;

public interface PrpdcompanyService {

	/**
	 * 返回指定机构所有上级机构代码列表
	 * @param comCode
	 * @return
	 */
	List<String> getUpperComCode(String comCode);

	/**
	 * 返回指定机构上级机构中机构级别不高于指定级别的机构的代码列表
	 * @param comCode
	 * @param comLevel
	 * @return
	 */
	List<String> getUpperComCodeList(String comCode, String comLevel);

	/**
	 * 返回指定机构上级机构级别等于指定级别的机构的机构代码
	 * @param comCode
	 * @param comLevel
	 * @return
	 */
	String getUpperComCode(String comCode, String comLevel);

	/**
	 * 返回指定机构直接上级机构代码
	 * @param comCode
	 * @return
	 */
	Prpdcompany getNativeUpperComCode(String comCode);


	void getlowComCode(String comCode,List<String> a);
}
