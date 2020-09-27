package com.ccic.salesapp.noncar.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccic.salessapp.common.utils.PropertiesUtil;
import com.ccic.salessapp.common.utils.UnirestUtil;
import kong.unirest.JsonNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.response.Order;
import com.ccic.salesapp.noncar.dto.response.ResponseBody;
import com.ccic.salesapp.noncar.dto.response.SendSimpleMessageResponseDTO;
import com.ccic.salesapp.noncar.dto.response.SubmitUnderwriteResponse;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlanInfoRoot;
import com.ccic.salesapp.noncar.dto.submitMiddleGround.CombinationApplyNo;
import com.ccic.salesapp.noncar.dto.submitMiddleGround.JsonRootBean;
import com.ccic.salesapp.noncar.dto.submitMiddleGround.PolicyLobList;
import com.ccic.salesapp.noncar.dto.submitUwVo.ChannelOpInfo;
import com.ccic.salesapp.noncar.dto.submitUwVo.ReqSubmitUwVo;
import com.ccic.salesapp.noncar.dto.submitUwVo.RespSubmitUwVo;
import com.ccic.salesapp.noncar.dto.submitUwVo.SubmitProposalInfoList;
import com.ccic.salesapp.noncar.outService.esb.noCarUnderwriting.SubmitUnderwriteRequest;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;
import com.ccic.salessapp.common.outService.rest.common.service.CommonService;
import com.ccic.salessapp.common.utils.StringUtils;

import net.sf.json.JSONObject;
 
@Service
public class SubmitUnderwritService extends CommonService{
	
	private static Log log=LogFactory.getLog(CommonService.class);
	
	
	@Value("${esb.address.nocarUnderwriting}")
	private String nocarUnderwriting;
	
	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;
	
	/**
	 * 提交核保
	 * @param requestBody
	 * @return
	 * @throws Exception
	 */
	public Response submitUW(SubmitUnderwriteRequest requestBody) throws Exception{
		
		Request request = new Request();
    	RequestHead requestHead = new RequestHead();
    	requestHead.setConsumerID("supsale");
    	request.setRequestHead(requestHead);
    	request.setRequestBody(requestBody);
    	
    	String reqJson = JSONObject.fromObject(request,jsonConfig).toString();
    	log.info("调用提交核保接口开始，请求报文："+ reqJson);
		String resJson = this.postJsonRequest(reqJson, nocarUnderwriting);
		log.info("调用提交核保接口结束，响应报文："+ resJson);
		
		
		ResponseHead responseHead = null;
		SubmitUnderwriteResponse responseBody = null;
		JSONObject json = JSONObject.fromObject(resJson);
		responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("responseHead"), ResponseHead.class);
		responseBody = (SubmitUnderwriteResponse) JSONObject.toBean(json.getJSONObject("responseBody"), SubmitUnderwriteResponse.class);
		Response response = new Response();
		response.setResponseHead(responseHead);
		if(responseBody != null ) {
			response.setResponseBody(responseBody);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("insureNo", requestBody.getProposalNo());
			storeInsureInfoMapper.upNoPayStsate(map);
			if(StringUtils.isNotEmpty(responseBody.getPolicyNo())) {
				Map<String,String> param = new HashMap<String, String>();
				param.put("insureNo", requestBody.getProposalNo());
				param.put("policyNo", responseBody.getPolicyNo());
				storeInsureInfoMapper.upPolicyNoInfo(param);
			}
			return response;
		}
		return response;
	}

	
	
	/**
	 * 提交核保
	 * @param requestBody
	 * @return
	 * @throws Exception
	 */
	public Response submitUWTwo(SubmitUnderwriteRequest requestBody) throws Exception{
		
		Request request = new Request();
    	RequestHead requestHead = new RequestHead();
    	requestHead.setConsumerID("supsale");
    	request.setRequestHead(requestHead);
    	request.setRequestBody(requestBody);
    	
    	String reqJson = JSONObject.fromObject(request,jsonConfig).toString();
    	log.info("调用提交核保接口开始，请求报文："+ reqJson);
		String resJson = this.postJsonRequest(reqJson, nocarUnderwriting);
		log.info("调用提交核保接口结束，响应报文："+ resJson);
		
		
		ResponseHead responseHead = null;
		SubmitUnderwriteResponse responseBody = null;
		JSONObject json = JSONObject.fromObject(resJson);
		responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("responseHead"), ResponseHead.class);
		responseBody = (SubmitUnderwriteResponse) JSONObject.toBean(json.getJSONObject("responseBody"), SubmitUnderwriteResponse.class);
		Response response = new Response();
		response.setResponseHead(responseHead);
		if(responseBody != null ) {
			response.setResponseBody(responseBody);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("insureNo", requestBody.getProposalNo());
//			storeInsureInfoMapper.upNoPayStsate(map);
			storeInsureInfoMapper.fupNoPayStsate(map);
			if(StringUtils.isNotEmpty(responseBody.getPolicyNo())) {
				Map<String,String> param = new HashMap<String, String>();
				param.put("insureNo", requestBody.getProposalNo());
				param.put("policyNo", responseBody.getPolicyNo());
//				storeInsureInfoMapper.upPolicyNoInfo(param);
				storeInsureInfoMapper.fupPolicyNoInfo(param);
			}
			return response;
		}
		return response;
	}

	/**
	 * 组合核保
	 * @param reqSubmitUwVo
	 * @param productCode 
	 * @return
	 */
	public RespSubmitUwVo submitMiddleGround(ReqSubmitUwVo reqSubmitUwVo, String productCode) {
		Request request = new Request();
    	RequestHead requestHead = new RequestHead();
    	requestHead.setConsumerID("supsale");
    	request.setRequestHead(requestHead);
    	
    	ChannelOpInfo channelOpInfo = new ChannelOpInfo();
    	channelOpInfo.setChannelCode("310073");
    	channelOpInfo.setChannelComCode("310073");
    	channelOpInfo.setChannelComName("移动销售支持");
    	channelOpInfo.setChannelName("移动销售支持");
    	channelOpInfo.setChannelProductCode(productCode);
    	
    	List<SubmitProposalInfoList> submitProposalInfoList = storeInsureInfoMapper.findByProposalNoAndProductLine(reqSubmitUwVo.getSubmissionNo());
    	//查询订单的是否见费非见费
    	NoncarOrder noncarOrder = storeInsureInfoMapper.findByOrderIsIssueAfterPay(reqSubmitUwVo.getSubmissionNo());
    	String isIssueAfterPay = noncarOrder.getIsIssueAfterPay();
    	
    	ReqSubmitUwVo reqSubmitUw = new ReqSubmitUwVo();
    	reqSubmitUw.setSubmissionNo(reqSubmitUwVo.getSubmissionNo());
    	reqSubmitUw.setChannelOpInfo(channelOpInfo);
    	reqSubmitUw.setSubmitProposalInfoList(submitProposalInfoList);
    	request.setRequestBody(reqSubmitUw);
    	
    	String reqJson = JSONObject.fromObject(request,jsonConfig).toString();
    	log.info("调用提交组合核保接口开始，请求报文："+ reqJson);
    	JsonNode jsonNode = UnirestUtil.postForJson(PropertiesUtil.getProperty("system.submitUw", null), request);
		log.info("调用提交组合核保接口结束，响应报文："+ jsonNode);
		RespSubmitUwVo response = JSON.parseObject(jsonNode.toString(), RespSubmitUwVo.class);
        if(response.getResponseBody()!=null) {
            if(response.getResponseBody().getSubmitProposalInfoList().size()!=0) {
                List<SubmitProposalInfoList> submitProposalIn = response.getResponseBody().getSubmitProposalInfoList();
                for (SubmitProposalInfoList policyLobListTwo : submitProposalIn) {
                    Map<String,String> param = new HashMap<String, String>();
                    param.put("insureNo", policyLobListTwo.getProposalNo());
                    if(policyLobListTwo.getPolicyNo()==null) {
                    		/** 保单号为空：见费   状态改为4**/
                    	 storeInsureInfoMapper.PolicyNoInfoSubmitMiddJf(param);
                    }else {
                    	param.put("policyNo", policyLobListTwo.getPolicyNo());
                    	storeInsureInfoMapper.fupPolicyNoInfoSubmitMidd(param);
                    }
                }
                /** 非见费0   状态改为7 **/
                if("0".equals(noncarOrder.getIsIssueAfterPay())) {
                	storeInsureInfoMapper.updateSubmitMiddleStatus(reqSubmitUwVo.getSubmissionNo());
                }else {
                	storeInsureInfoMapper.updateSubmitMiddleStatusJf(reqSubmitUwVo.getSubmissionNo());
                }
                
                com.ccic.salesapp.noncar.dto.submitUwVo.RespSubmitUwVo.ResponseBody responseBody = new com.ccic.salesapp.noncar.dto.submitUwVo.RespSubmitUwVo.ResponseBody();
                responseBody.setChannelOpInfoDTO(response.getResponseBody().getChannelOpInfoDTO());
                responseBody.setIsIssueAfterPay(isIssueAfterPay);
                responseBody.setOrderId(noncarOrder.getId());
                responseBody.setSubmitProposalInfoList(response.getResponseBody().getSubmitProposalInfoList());
                response.setResponseBody(responseBody);
                return response;
            }
         }
		return response;
	}
}
