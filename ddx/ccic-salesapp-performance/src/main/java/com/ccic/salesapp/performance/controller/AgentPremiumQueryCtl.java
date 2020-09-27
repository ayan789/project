package com.ccic.salesapp.performance.controller;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.performance.domain.vo.dto.AgentHandlerInfo;
import com.ccic.salesapp.performance.domain.vo.request.AgentPerformanceRequest;
import com.ccic.salesapp.performance.domain.vo.response.AgentPerformanceResponse;
import com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeInquiryPort;
import com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeInquiryRequest;
import com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeInquiryResponse;
import com.ccic.salesapp.performance.service.AgentPremiumNum;
import com.ccic.salesapp.performance.service.AgentPremiumQueryService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.ValidationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;





@RestController
@Api(tags = "代理人业绩", description = "代理人业绩", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "policyInfo")
@Slf4j
public class AgentPremiumQueryCtl {

	@Autowired
	AgentPremiumQueryService agentPremiumQueryService;
	
	@Autowired
	PlanFeeInquiryPort  planFeeInquiryPort;
	
	
	@PostMapping(value = "agentPremiumQuery")
	@ApiOperation(value = "代理人业绩查询", notes = "代理人业绩", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<AgentPerformanceResponse> agentPremiumQuery(@RequestBody AgentPerformanceRequest request) {
		ValidationUtils.validate(request);
		return agentPremiumQueryService.agentPremiumQuery(request);
	}
	
	@PostMapping(value = "agentPremiumNum")
	@ApiOperation(value = "代理人数量查询", notes = "代理人数量", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<AgentPerformanceResponse> agentPremiumNum(@Validated({AgentPremiumNum.class, Default.class}) @RequestBody AgentHandlerInfo agentHandlerInfo) {
		log.info("代理人数量查询:"+JSONObject.toJSONString(agentHandlerInfo));
		ValidationUtils.validate(agentHandlerInfo);
		com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.RequestHeadDTO headdto=
				new com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.RequestHeadDTO();
		headdto.setConsumerID("supsale");
		PlanFeeInquiryRequest request = new PlanFeeInquiryRequest();
		com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeRequestDTO requestDTO=new 
				com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeRequestDTO();
		requestDTO.setQueryType("02");
//		if(StringUtils.isNotEmpty(agentHandlerInfo.getUserCode()))
//			requestDTO.setHandlerCode(agentHandlerInfo.getUserCode());
		if(StringUtils.isNotEmpty(agentHandlerInfo.getHandlerCode()))
			requestDTO.setHandlerCode(agentHandlerInfo.getHandlerCode());
		request.setRequestHead(headdto);
		request.setRequestBody(requestDTO);
		log.info("调销管系统手续费查询接口 请求:{}",JSONObject.toJSONString(request));
	    PlanFeeInquiryResponse response = planFeeInquiryPort.planFeeInquiry(request);//调销管系统手续费查询接口
	    log.info("调销管系统手续费查询接口 返回:{}",JSONObject.toJSONString(response)); 
	    if(response.getResponseHead().getStatus()==0) {
		    return  HttpResult.success(response.getResponseBody().getPlanFeeInfo().size(), 1, "代理人数量查询成功");
	    }
	    else {
	    	log.error(response.getResponseHead().getAppMessage());
	    	return  HttpResult.success(0, 1, "未查询到代理人数量");
	    }  
	}
}
