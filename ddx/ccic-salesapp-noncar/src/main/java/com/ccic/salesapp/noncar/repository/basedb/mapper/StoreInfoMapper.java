package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.StoreInfo;
@Mapper
public interface StoreInfoMapper {

	StoreInfo findStoreByCode(String storeCode);
	
	StoreInfo findStoreByUserCode(String userCode);

}
