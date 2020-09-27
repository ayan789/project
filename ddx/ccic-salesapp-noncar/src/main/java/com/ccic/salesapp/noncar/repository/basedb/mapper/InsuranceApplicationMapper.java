package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.InsuranceApplication;


@Mapper
public interface InsuranceApplicationMapper {

	InsuranceApplication selectOrderDetailsInquiry(Map paramMap);
	void updateByPrimaryKey(InsuranceApplication insuranceApp );
	
}
