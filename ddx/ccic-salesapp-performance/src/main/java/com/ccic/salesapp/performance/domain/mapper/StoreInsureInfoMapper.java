package com.ccic.salesapp.performance.domain.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.performance.domain.vo.dto.ApplyPayVo;
import com.ccic.salesapp.performance.domain.vo.dto.StoreInsureInfo;
 


@Mapper
public interface StoreInsureInfoMapper {

	StoreInsureInfo selectBySureInfoNo(ApplyPayVo applyPayVo);

	void updateInsureInfoById(Map<String, String> map);

	int doUpdate(StoreInsureInfo insureInfo1);

	void doInsert(StoreInsureInfo insureInfo);
	
	int upPolicyNoInfo(Map<String, String> map);
	
	
}
