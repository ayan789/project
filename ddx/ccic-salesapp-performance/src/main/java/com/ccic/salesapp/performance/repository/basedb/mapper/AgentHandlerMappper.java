package com.ccic.salesapp.performance.repository.basedb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.performance.domain.vo.dto.AgentHandlerInfo;

/**
 * 经办人dao层
 */
@Mapper
public interface AgentHandlerMappper {

    AgentHandlerInfo selectAgentHandlerInfo(String userID);

    void updateAgentHandlerInfo(AgentHandlerInfo agentHandlerInfo);

    AgentHandlerInfo selectAgentHandlerInfoByUserCode(String userCode);
    
    AgentHandlerInfo selectAgentHandlerInfoByAgentCode(String agentCode);
    
    int agentHandlerAndPremNum(AgentHandlerInfo agentHandlerInfo);

}
