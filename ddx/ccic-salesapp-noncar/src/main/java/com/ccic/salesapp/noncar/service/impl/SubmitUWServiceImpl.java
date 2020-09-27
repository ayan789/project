package com.ccic.salesapp.noncar.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.request.SubmitUnderWriteRequest2;
import com.ccic.salesapp.noncar.dto.response.SubmitUnderwriteResponse;
import com.ccic.salesapp.noncar.repository.basedb.po.UWResult;
import com.ccic.salesapp.noncar.repository.basedb.po.UWRuleInfo;
import com.ccic.salesapp.noncar.service.SubmitUWService;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;
import com.ccic.salessapp.common.utils.PropertyStrategyWrapper;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

@Service
@Slf4j
public class SubmitUWServiceImpl implements SubmitUWService{
	
	@Value("submitUnderwrite")
	String submitUnderwrite;
	
	/**
	 * 提交核保
	 * @param requestBody
	 * @return
	 * @throws Exception
	 */
	public Response submitUW(SubmitUnderWriteRequest2 requestBody) throws Exception{
		
		Request request = new Request();
    	RequestHead requestHead = this.getConsumerID();
    	request.setRequestHead(requestHead);
    	request.setRequestBody(requestBody);
    	
    	String reqJson = JSONObject.fromObject(request).toString();
    	log.info("调用提交核保接口开始，请求报文："+ reqJson);
		String resJson = this.postJsonRequest(reqJson, submitUnderwrite);
		log.info("调用提交核保接口结束，响应报文："+ resJson);
		
		ResponseHead responseHead = null;
		SubmitUnderwriteResponse responseBody = null;
		Map<String,Class> classMap = new HashMap<String,Class>();
		classMap.put("uWResult", UWResult.class);
		classMap.put("underwriteRuleInfos", UWRuleInfo.class);
		classMap.put("proposalNos", String.class);
		JSONObject json = JSONObject.fromObject(resJson);
		responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("responseHead"), ResponseHead.class);
		if(responseHead.getStatus()==0){
			// 声明JsonConfig对象
			JsonConfig cfg = new JsonConfig();
			//  设置属性包装器
			cfg.setPropertySetStrategy(new PropertyStrategyWrapper(PropertySetStrategy.DEFAULT));
			cfg.setRootClass(SubmitUnderwriteResponse.class);
			cfg.setClassMap(classMap);
			responseBody = (SubmitUnderwriteResponse) JSONObject.toBean(json.getJSONObject("responseBody"), cfg);
		}
		
		Response response = new Response();
		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);
		
    	return response;
	}
	
	public RequestHead getConsumerID(){
		RequestHead requestHead = new RequestHead();
		requestHead.setConsumerID("supsale");
		requestHead.setConsumerSeqNo(getUUID());
		return requestHead;
	}
	
	public String postJsonRequest(String reqJson, String uri) throws Exception {
		return postJsonRequest(reqJson,uri,"utf-8");
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
	
	public String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
}
