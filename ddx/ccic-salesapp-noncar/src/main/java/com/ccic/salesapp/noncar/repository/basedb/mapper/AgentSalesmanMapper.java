package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.AgentSalesman;

@Mapper
public interface AgentSalesmanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgentSalesman record);

    int insertSelective(AgentSalesman record);

    AgentSalesman selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentSalesman record);

    int updateByPrimaryKey(AgentSalesman record);
    
    AgentSalesman selectDefaultSalesmanByAgentCode(String agentCode);
}