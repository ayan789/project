package com.ccic.salesapp.noncar.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccic.salesapp.noncar.repository.basedb.mapper.GeneralConfigMapper;

public interface ConfigService {

	/**
	 * 判断地区是否为新核心试点地区
	 * @throws Exception 
	 * 
	 * */
	public boolean isNewCore(String comCode,String riskCode) throws Exception;

	boolean flagAddrInsurance(String comCode) throws Exception;
	
	public String yzmAreaFlag(String comCode,GeneralConfigMapper generalConfigMapper);
}
