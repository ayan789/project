package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SMSMessageMapper {

	void insert(Map map);
	Integer countByMobile(String mobile);
}
