package com.ccic.salesapp.performance.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.performance.domain.vo.request.PartnerAgentPerformanceRequest;
import com.ccic.salesapp.performance.domain.vo.response.PartnerAgentPerformanceResponse;

@Mapper
public interface PartnerAgentMapper {
	
	PartnerAgentPerformanceResponse getPartnerAgentPremiumByAgentCode(PartnerAgentPerformanceRequest request );

}
