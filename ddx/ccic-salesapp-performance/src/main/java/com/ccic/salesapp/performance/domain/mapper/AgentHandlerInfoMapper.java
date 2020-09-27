package com.ccic.salesapp.performance.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.performance.domain.vo.dto.AgentHandlerInfo;


@Mapper
public interface AgentHandlerInfoMapper {
    AgentHandlerInfo selectAgentHandlerInfoByUserCode(String userCode);
}
