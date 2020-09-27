package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.UrlMapping;

@Mapper
public interface UrlMappingMapper {

	Integer toInsert(UrlMapping urlMapping);
	
	String selectUrlMapping(String urlCode);
	

}
