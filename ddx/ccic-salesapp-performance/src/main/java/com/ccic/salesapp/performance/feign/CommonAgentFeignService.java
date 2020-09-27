package com.ccic.salesapp.performance.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ccic.salesapp.performance.domain.vo.request.RankVo;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.feign.FeignSupportConfig;


@FeignClient(value ="CCIC-SALESAPP-PERFORMANCE",url= "http://10.100.172.11:8009", configuration = FeignSupportConfig.class)
public interface CommonAgentFeignService {
	
	@RequestMapping(value = "/queryRankingM2YPremium", method = RequestMethod.POST)
	public HttpResult queryRankingM2YPremium(@RequestBody RankVo rankVo);
	
}


	
	