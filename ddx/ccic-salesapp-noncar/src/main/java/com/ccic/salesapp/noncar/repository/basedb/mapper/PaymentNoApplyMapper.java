package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;


@Mapper
public interface PaymentNoApplyMapper {

	StoreInsureInfo selectBySureInfoNo(ApplyPayVo applyPayVo);
	
	int doUpdate(StoreInsureInfo insureInfo1);

	int doUpdateTwo(NoncarOrder noncarOrder);

}
