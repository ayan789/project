package com.ccic.salesapp.performance.domain.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.performance.domain.vo.response.AgentPerformanceResponse;

@Mapper
public interface AgentPremiumMapper {
	AgentPerformanceResponse getAgentPremiumByAgentCode(String agentCode);
}
