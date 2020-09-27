package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.AgentHandlerInfo;
@Mapper
public interface AgentHandlerInfoMapper {

	AgentHandlerInfo selectAgentHandlerInfoByUserCode(String userCode);

}
