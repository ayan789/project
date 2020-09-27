package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.Agent;
@Mapper
public interface AgentMapper {

	Agent selectAgentInfo(String userCode);

}
