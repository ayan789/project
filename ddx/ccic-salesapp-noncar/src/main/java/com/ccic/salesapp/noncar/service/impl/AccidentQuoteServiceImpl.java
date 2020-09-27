package com.ccic.salesapp.noncar.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.order.AccidentQuoteOrderResponse;
import com.ccic.salesapp.noncar.dto.request.AccidentPolicyRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteDetailResponse;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteResponse;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured;
import com.ccic.salesapp.noncar.service.AccidentQuoteService;
import com.ccic.salesapp.noncar.service.HttpService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
@Service
@Slf4j
public class AccidentQuoteServiceImpl implements  AccidentQuoteService{
	
	@Value("${esb.address.accidentQuote}")
	String accidentQuote ;
	
	@Value("${esb.address.policydetailservicerest}")
	String policydetailservicerest;
	
	@Autowired
	HttpService httpService;
	
	public static JsonConfig jsonConfig = new JsonConfig();
	static{
		jsonConfig.setJsonPropertyFilter(new PropertyFilter(){  
		    @Override  
		    public boolean apply(Object source, String name, Object value)  
		    {  
		    	return value == null;
		    }
		    
		});
		jsonConfig.addIgnoreFieldAnnotation(Request.class);
		
	}
	
	@Override
	public AccidentQuoteRequest getHistoryPolicyInfo(String PolicyNo) throws Exception {
		 RequestHead requestHead = new RequestHead();
		 requestHead.setConsumerID("supsale");
		 AccidentPolicyRequest requestBody = new AccidentPolicyRequest();
		 requestBody.setPolicyNo(PolicyNo);//意健险 7
		 requestBody.setQueryType("7");
		 com.ccic.salesapp.noncar.dto.request.Request requests = new com.ccic.salesapp.noncar.dto.request.Request();
		 requests.setRequestHead(requestHead);
		 requests.setRequestBody(requestBody);
		 String reqJson = JSONObject.fromObject(requests,jsonConfig).toString();
		 log.info("调用意健险保单信息查询接口开始，请求报文："+ reqJson);
		 String respJson = httpService.postJsonRequest(PolicyNo,reqJson, policydetailservicerest);
		 log.info("调用意健险保单信息查询接口开始，请求报文："+ respJson);
		 ObjectMapper mapper = new ObjectMapper();
		 JsonNode actualObj = null;
		 try {
			 actualObj = mapper.readTree(respJson);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 JsonNode jsonNode1 = actualObj.get("responseBody").get("policyElementString");
		 AccidentQuoteRequest req = com.alibaba.fastjson.JSON.parseObject(mapper.writeValueAsString(jsonNode1), AccidentQuoteRequest.class);
		 return req;
	}
	
	@Override
	public boolean isNewInsured(PersonInsured personInsured,List<PersonInsured> personInsuredList) {
		for (PersonInsured item : personInsuredList) {
			if(item.getCustomerName().equals(personInsured.getCustomerName()) && item.getIdNo().equals(personInsured.getIdNo()) ) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Response accidentQuote(RequestBody requestBody, String bussNo,  HttpResult result,UserVO userVO) throws Exception{ 
		Request request =  new Request();
		RequestHead requestHead= this.getConsumerID();
		request.setRequestHead(requestHead);
		request.setRequestBody(requestBody);
		updateHandlerInfo((AccidentQuoteRequest)requestBody,userVO);
		String reqJson = JSONObject.fromObject(request,jsonConfig).toString();
    	log.info("调用意健险试算接口开始，请求报文："+ reqJson);
    	String respJson = httpService.postJsonRequest(bussNo,reqJson, accidentQuote);
		log.info("调用意健险试算接口结束，返回报文："+ respJson);
		
		ResponseHead responseHead = null;
		AccidentQuoteResponse responseBody = null;
		String proposalNo = "";
		JSONObject json = JSONObject.fromObject(respJson);
		responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("responseHead"), ResponseHead.class);
		if(responseHead.getStatus()==0){
			responseBody = accidentQuoteResponse(respJson);
			result.setCode("1");//响应代码 1-成功，0-失败
			result.setMsg("调用意健险试算成功");//响应信息
		}else{
			result.setCode("0");
			result.setMsg("调用意健险试算接口，返回状态为失败。返回信息："+responseHead.getAppMessage());
		}
		Response response = new Response();
		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);
		return response;
		
	}
	
	/**
	 * 更新经办人信息
	 * @param  requestBody 意健险DTO
	 */
	public void updateHandlerInfo(AccidentQuoteRequest requestBody,UserVO userVO){
		String handlerCode="";
		if(null!=requestBody){
			handlerCode=requestBody.getBelongToHandlerCode();
		}
		
		if("agen".equals(handlerCode.substring(0, 4))){
			if(null!=requestBody && null!=userVO){//意健险
				if(StringUtils.isNotBlank(requestBody.getAgentCode())){//代理信息不为空
					//判断经办人2是否取的默认经办人1
					if(requestBody.getBelongToHandlerCode().equals(requestBody.getBelongToHandler2Code())){
						requestBody.setBelongToHandler2Code(userVO.getUserCode());
						requestBody.setBelongToHandler2Name(userVO.getUserName());
					}
				}else {
					// agentCode 存在的话  handlerCode取值代理人agentCode，否则同BelongToHandlerCode
					requestBody.setHandlerCode(userVO.getUserCode());//归属经办人
					requestBody.setHandlerName(userVO.getUserName());//归属经办人
				}
				requestBody.setBelongToHandlerCode(userVO.getUserCode());
				requestBody.setBelongToHandlerName(userVO.getUserName());
				requestBody.setOrgCode(userVO.getCompany());//归属机构
				//requestBody.setIssueOrgCode(userVO.getCompany());//出单机构
			}
		}
	}
	
	public RequestHead getConsumerID(){
		RequestHead requestHead = new RequestHead();
		requestHead.setConsumerID("supsale");
		requestHead.setConsumerSeqNo(getUUID());
		return requestHead;
	}
	
	public String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	
	private AccidentQuoteResponse accidentQuoteResponse(String respJson) {
		AccidentQuoteOrderResponse responseBody =  JSON.parseObject(respJson,AccidentQuoteOrderResponse.class);
		return responseBody.getResponseBody();
	}
	
	
}
