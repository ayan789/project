package com.ccic.salesapp.noncar.repository.basedb.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.request.TNoncarInsureZcReqVO;
import com.ccic.salesapp.noncar.dto.response.TNoncarInsureZcResVO;



@Mapper
public interface TNoncarInsureinfoZcMapper {

	//投保信息暂存
	void insertZc(TNoncarInsureZcReqVO req);
	
	//根据订单号获取暂存信息
	TNoncarInsureZcResVO queryZc(String orderNo);
	
	//根据订单号修改暂存信息
	int updateZcInfo(TNoncarInsureZcReqVO req);
	

}
