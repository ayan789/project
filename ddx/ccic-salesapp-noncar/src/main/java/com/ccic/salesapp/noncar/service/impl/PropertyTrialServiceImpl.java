package com.ccic.salesapp.noncar.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.PropertyTrialRequest;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.order.PropertyTrialOrderResponse;
import com.ccic.salesapp.noncar.dto.response.PropertyTrialResponse;
import com.ccic.salesapp.noncar.service.HttpService;
import com.ccic.salesapp.noncar.service.PropertyTrialService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

@Service
@Slf4j
public class PropertyTrialServiceImpl implements PropertyTrialService {
	
	@Value("${esb.address.nocarPropertyTrial}")
	String nocarPropertyTrial;
	
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
	public Response propertyTrial(PropertyTrialRequest requestBody,String orderNo,HttpResult result,UserVO userVO,AgentInfoVO agentInfoVO) throws Exception{
		Request request = new Request();
		RequestHead requestHead = this.getConsumerID();
    	request.setRequestHead(requestHead);
    	request.setRequestBody(requestBody);
    	updateHandlerInfo(requestBody,userVO);
    	String reqJson = JSONObject.fromObject(request,jsonConfig).toString();
    	log.info("调用财产险试算接口，请求报文："+ reqJson);
    	String respJson = httpService.postJsonRequest(orderNo,reqJson,nocarPropertyTrial);
		log.info("调用财产险试算接口，返回报文："+ respJson);
		
		ResponseHead responseHead = null;
		PropertyTrialResponse responseBody = null;
		String proposalNo = "";
		JSONObject json = JSONObject.fromObject(respJson);
		responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("responseHead"), ResponseHead.class);
		if(responseHead.getStatus()==0){
			responseBody = propertyTrialResponse(respJson);
			result.setCode("1");//响应代码 1-成功，0-失败
			result.setMsg("调用非车险试算成功");//响应信息
			result.setData(responseBody);
		}else{
			result.setCode("0");
			result.setMsg(responseHead.getAppMessage());
		}
		Response response = new Response();
		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);
		return response;
	}
	
	/**
	 * 更新经办人信息
	 * @param  财产险DTO
	 * @param  货运险DTO
	 */
	public void updateHandlerInfo(PropertyTrialRequest requestBody,UserVO userVO){
		String handlerCode="";
		if(null!=requestBody){
			handlerCode=requestBody.getBelongToHandlerCode();
		}
		
		if("agen".equals(handlerCode.substring(0, 4))){
			if(null!=requestBody &&null!=userVO){//财产险
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
	
	/**
	 * 发送post请求
	 * 
	 * @param reqJson :请求的字符串
	 * @param uri :请求路径
	 * @param charset :编码
	 * @return
	 * @throws Exception 
	 */
	public String postJsonRequest(String reqJson, String uri, String charset) throws Exception {
		HttpClient  client=new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
		client.getHttpConnectionManager().getParams().setSoTimeout(60000);
		client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("mobile", "mobile"));
		log.info("##########请求地址：" +uri);
		PostMethod method=new PostMethod(uri);
		StringBuffer response= new StringBuffer();  
		try {
			method.setRequestHeader("Content-Type", "application/json");
			RequestEntity entity = new StringRequestEntity(reqJson,"application/json",charset);
			method.setRequestEntity(entity);
 			int statusCode = client.executeMethod(method);
 			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));  
 			String line;  
 			while ((line = reader.readLine()) != null) {  
                response.append(line);  
            }  
            reader.close(); 
			//log.info("调用核心---返回报文： resp{"+response+"}");
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Method failed: " + method.getStatusLine());
			}
		} catch (Exception e) {		
			log.error("http请求异常：",e);
			throw e;
		}
		return response.toString(); // 返回
	}
	public String postJsonRequest(String reqJson, String uri) throws Exception {
		return postJsonRequest(reqJson,uri,"utf-8");
	}
	
	
	private PropertyTrialResponse propertyTrialResponse(String respJson) {
		PropertyTrialOrderResponse responseBody =  JSON.parseObject(respJson,PropertyTrialOrderResponse.class);
		return responseBody.getResponseBody();
	}
}
