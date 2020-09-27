package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.request.PolicyMessageVO;

@Mapper
public interface PolicyMessageMapper {
	
	int selectCodeByPolicyNoAndUser(Map map);
	
	int insert(PolicyMessageVO policyMessageVO);
}
