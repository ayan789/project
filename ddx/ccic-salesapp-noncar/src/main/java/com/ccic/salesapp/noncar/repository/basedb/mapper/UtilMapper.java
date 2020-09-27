package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UtilMapper {

	BigDecimal getSeq(String string);
	
	String getOccupationGrage(String id);

}
