package com.ccic.salesapp.noncar.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.renewal.request.ClaimQueryRequest;
import com.ccic.salesapp.noncar.dto.renewal.request.RenewalRequest;
import com.ccic.salesapp.noncar.dto.renewal.request.RenewalStatusQueryRequestDTO;
import com.ccic.salesapp.noncar.dto.renewal.response.ClaimQueryResponse;
import com.ccic.salesapp.noncar.dto.renewal.response.RenewalStatusQueryResponseDTO;
import com.ccic.salesapp.noncar.dto.request.AccidentPolicyRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;
import com.ccic.salesapp.noncar.service.HttpService;
import com.ccic.salesapp.noncar.service.RenewalService;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;
import com.ccic.salessapp.common.outService.rest.common.bean.RestfulResponse;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
@Slf4j
@Service
public class RenewalServiceImpl implements RenewalService{
	
	@Autowired
	HttpService httpService;
	
	
	@Value("${esb.address.renewalstatusqueryservicerest}")
	String renewalstatusqueryservicerest;
	
	@Value("${esb.address.cyxclaimsearchbypolicyservicerest}")
    String cyxclaimsearchbypolicyservicerest;
	
	
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
	public RenewalStatusQueryResponseDTO healthRenewalStatusQuery(RenewalRequest request) {
		RenewalStatusQueryRequestDTO body = new RenewalStatusQueryRequestDTO();
		BeanUtils.copyProperties(request, body);
		Request res = new Request();
		res.setRequestHead(getConsumerID());
		res.setRequestBody(body);
		String reqJson = JSONObject.fromObject(res,jsonConfig).toString();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpRequest = attributes.getRequest();
        String uuid = (String) httpRequest.getAttribute("requestId");
        if(StringUtils.isBlank(uuid)) {
       	 uuid = UUID.randomUUID().toString().replaceAll("-", "");
        }
		log.info("调用续保状态查询接口开始，请求报文：["+uuid+"]"+ reqJson);
    	String respJson = null;
		try {
			respJson = httpService.postJsonRequest(uuid,reqJson, renewalstatusqueryservicerest);
		} catch (Exception e) {
			throw new PlatformBaseException(e, 0);
		}
		log.info("调用续保状态查询接口结束，返回报文：["+uuid+"]"+ respJson);
		JSONObject json = JSONObject.fromObject(respJson);
		ResponseHead  responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("responseHead"), ResponseHead.class);
		if(responseHead.getStatus()!=0){
			throw new PlatformBaseException(responseHead.getAppMessage(), 0);
		}
		RenewalStatusQueryResponseDTO  responseBody =  (RenewalStatusQueryResponseDTO) JSONObject.toBean(json.getJSONObject("responseBody"), RenewalStatusQueryResponseDTO.class);
		return responseBody;
	}

	
	@Override
    public ClaimQueryResponse claimQuery(ClaimQueryRequest request){
        Request claimRequery= new Request();
        RequestHead requestHead = RequestHead.getDefault();
        requestHead.setClassCode("70000");
        claimRequery.setRequestHead(requestHead);
        claimRequery.setRequestBody(request);
        String reqJson = JSONObject.fromObject(claimRequery,jsonConfig).toString();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpRequest = attributes.getRequest();
        String uuid = (String) httpRequest.getAttribute("requestId");
        if(StringUtils.isBlank(uuid)) {
       	 uuid = UUID.randomUUID().toString().replaceAll("-", "");
        }
		log.info("调用查询理赔接口开始，请求报文：["+uuid+"]"+ reqJson);
    	String respJson = null;
		try {
			respJson = httpService.postJsonRequest(uuid,reqJson, cyxclaimsearchbypolicyservicerest);
		} catch (Exception e) {
			throw new PlatformBaseException(e, 0);
		}
		log.info("调用查询理接口结束，返回报文：["+uuid+"]"+ respJson);
		JSONObject json = JSONObject.fromObject(respJson);
		ResponseHead  responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("responseHead"), ResponseHead.class);
		if(responseHead.getStatus()!=0){
			throw new PlatformBaseException(responseHead.getAppMessage(), 0);
		}
		ClaimQueryResponse  responseBody =  (ClaimQueryResponse) JSONObject.toBean(json.getJSONObject("responseBody"), ClaimQueryResponse.class);
		return responseBody;
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
}
