package com.ccic.salesapp.performance.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.performance.domain.mapper.PartnerAgentMapper;
import com.ccic.salesapp.performance.domain.vo.request.PartnerAgentPerformanceRequest;
import com.ccic.salesapp.performance.domain.vo.response.PartnerAgentPerformanceResponse;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.ValidationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;





@RestController
@Api(tags = "伙伴代理人业绩", description = "伙伴代理人业绩", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "partner")
@Slf4j
public class PartnerOrderAmountQueryCtl {

	@Autowired
	PartnerAgentMapper partnerAgentMapper;
	
	@PostMapping(value = "/performance")
	@ApiOperation(value = "伙伴代理人业绩查询", notes = "伙伴代理人业绩", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<PartnerAgentPerformanceResponse> agentPremiumQuery(@RequestBody PartnerAgentPerformanceRequest request) {
		ValidationUtils.validate(request);
		PartnerAgentPerformanceResponse response = partnerAgentMapper.getPartnerAgentPremiumByAgentCode(request);
		if(null == response) {
			response = new PartnerAgentPerformanceResponse();
		}
		return HttpResult.success(response);
	}
	
	
}
