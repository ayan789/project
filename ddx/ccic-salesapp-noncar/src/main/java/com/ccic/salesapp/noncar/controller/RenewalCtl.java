package com.ccic.salesapp.noncar.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.renewal.request.ClaimQueryRequest;
import com.ccic.salesapp.noncar.dto.renewal.request.MatchPlanRequest;
import com.ccic.salesapp.noncar.dto.renewal.request.RenewalRequest;
import com.ccic.salesapp.noncar.dto.renewal.response.ClaimQueryResponse;
import com.ccic.salesapp.noncar.dto.renewal.response.MatchPlanResponse;
import com.ccic.salesapp.noncar.dto.renewal.response.RenewalResponse;
import com.ccic.salesapp.noncar.dto.renewal.response.RenewalStatusQueryResponseDTO;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyLob;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyRisk;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanStrategyMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanInfo;
import com.ccic.salesapp.noncar.service.AccidentQuoteService;
import com.ccic.salesapp.noncar.service.RenewalService;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "续保", description = "续保", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "renewal")
@Slf4j
public class RenewalCtl {
	
	@Autowired
	RenewalService renewalService;
	
	@Autowired
	AccidentQuoteService accidentQuoteService;
	
	@Autowired
	PlanStrategyMapper planStrategyMapper;
	
	@Autowired
	PlanInfoMapper planInfoMapper;
	
    
    @PostMapping(value = "product")
    @ApiOperation(value = "检查续保状态获取续保产品代码", notes = "检查续保状态获取续保产品代码", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<RenewalResponse> product(@RequestBody RenewalRequest request) throws Exception {
    	RenewalResponse response = new RenewalResponse();
    	RenewalStatusQueryResponseDTO renewalStatus = renewalService.healthRenewalStatusQuery(request);
    	response.setIsRenewal(renewalStatus.getIsRenewal());
    	response.setRenewalStatus(renewalStatus.getRenewalStatus());
    	if(renewalStatus!= null && "0".equals(renewalStatus.getRenewalStatus())) {
    		throw new PlatformBaseException("该保单不可续保", 0);
    	}
    	if(renewalStatus!= null && "1".equals(renewalStatus.getIsRenewal())) {
			throw new PlatformBaseException("该保单已续保", 0);
		}
    	//查询保单信息
    	AccidentQuoteRequest aqr =  accidentQuoteService.getHistoryPolicyInfo(request.getPolicyNo());
    	//取出被保人信息
    	List<PersonInsured> personInsuredList = null;
    	for (PolicyLob policyLob : aqr.getPolicyLobList()) {
			for (PolicyRisk policyRisk : policyLob.getPolicyRiskList()) {
				personInsuredList = policyRisk.getPersonInsuredList();
				break;
			}
		}
    	response.setPersonInsuredList(personInsuredList);
    	//查询理赔
    	ClaimQueryRequest claimQueryRequest = new ClaimQueryRequest();
    	claimQueryRequest.setPolicyNo(request.getPolicyNo());
    	String isClaim = "0";//有无理赔
    	try {
    		ClaimQueryResponse claimQueryResponse  = renewalService.claimQuery(claimQueryRequest);
    		if(claimQueryResponse != null) {
    			isClaim = "1";
    		}
		} catch (Exception e) {
			log.error(e.getMessage(),e.getMessage());
		}
    	
    	HashMap<String,Object> map = new HashMap<String,Object>();
    	map.put("isClaim", isClaim);
    	List<StoreProduct> productList = planStrategyMapper.selectStaffRenewalProductList(map);
    	response.setHasClaim(isClaim);
    	response.setProductList(productList);
    	return HttpResult.success(response);
    }


    @PostMapping(value = "matchPlan")
    @ApiOperation(value = "检查续保状态获取续保产品代码", notes = "检查续保状态获取续保产品代码", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<MatchPlanResponse> matchPlan(@RequestBody MatchPlanRequest request) {
    	List<PersonInsured> personInsuredList = request.getPersonInsured();
    	if(personInsuredList == null || personInsuredList.isEmpty()) {
    		throw new PlatformBaseException("被保人信息有误", 0);
    	}
    	MatchPlanResponse response = new MatchPlanResponse();
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("insuredPersonNum", personInsuredList.size());
    	map.put("productCode", request.getProductCode());
    	if(personInsuredList.size() > 0 ) {
    		map.put("hasSocialFnsurance", 1);//有社保
    	}
    	
    	map.put("hasSocialFnsurance", 2);//无社保
    	PlanInfo planInfo = planInfoMapper.selectRenewalMatchPlanInfo(map);
    	response.setPlanInfo(planInfo);
    	return HttpResult.success(response);
    }
    
    
}
