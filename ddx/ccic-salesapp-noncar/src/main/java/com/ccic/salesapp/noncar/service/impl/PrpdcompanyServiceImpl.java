package com.ccic.salesapp.noncar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.Prpdcompany;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PRPDCompanyMapper;
import com.ccic.salesapp.noncar.service.PrpdcompanyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrpdcompanyServiceImpl implements PrpdcompanyService {
	
	@Autowired
	PRPDCompanyMapper pRPDCompanyMapper;

	/**
	 * 返回指定机构所有上级机构代码列表
	 * @param comCode
	 * @return
	 */
	@Override
	public List<String> getUpperComCode(String comCode){
		List<String> list = new ArrayList<String>();
		Prpdcompany company = null;
		String upperComCode = comCode;
		do {
			 company = getNativeUpperComCode(upperComCode);
			 upperComCode = company.getUppercomcode();
			 list.add(company.getComcode());
		}while(!company.getComcode().equals(company.getUppercomcode()));
		return list;
	}
	
	
	
	
	/**
	 * 返回指定机构上级机构中机构级别不高于指定级别的机构的代码列表
	 * @param comCode
	 * @param comLevel
	 * @return
	 */
	@Override
	public List<String> getUpperComCodeList(String comCode,String comLevel){
		List<String> list = new ArrayList<String>();
		Prpdcompany company = null;
		String upperComCode = comCode;
		do {
			 company = getNativeUpperComCode(upperComCode);
			 upperComCode = company.getUppercomcode();
			 list.add(company.getComcode());
		}while(Integer.parseInt(company.getComlevel()) > Integer.parseInt(comLevel));
		return list;
	}
	
	/**
	 * 返回指定机构上级机构级别等于指定级别的机构的机构代码
	 * @param comCode
	 * @param comLevel
	 * @return
	 */
	@Override
	public String getUpperComCode(String comCode,String comLevel){
		Prpdcompany company = null;
		String upperComCode = comCode;
		do {
			 company = getNativeUpperComCode(upperComCode);
			 upperComCode = company.getUppercomcode();
		}while(Integer.parseInt(company.getComlevel()) > Integer.parseInt(comLevel));
		
		return company.getComcode();
	}
	
	/**
	 * 返回指定机构直接上级机构代码
	 * @param comCode
	 * @return
	 */
	@Override
	public Prpdcompany getNativeUpperComCode(String comCode){
		Prpdcompany company = pRPDCompanyMapper.getUpperComCode(comCode);
		return company;
	}

	@Override
	public void getlowComCode(String comCode,List<String> a) {

		//根据当前部门id查询所有子部门的id
		List<String> list = pRPDCompanyMapper.getlowComCode(comCode);
		for(int i=0;i<list.size();i++){
			a.add(list.get(i));
			getlowComCode(list.get(i),a);//递归查询
		}

	}


}
