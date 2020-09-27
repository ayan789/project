package com.ccic.salesapp.noncar.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.AccidentUnderwritingRequest;
import com.ccic.salesapp.noncar.dto.AccidentUnderwritingResponse;
import com.ccic.salesapp.noncar.dto.order.AccidentQuoteOrderResponse;
import com.ccic.salesapp.noncar.dto.request.accidentquote.ChannelOpInfo;
import com.ccic.salesapp.noncar.dto.request.accidentquote.Policy;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
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
public class AccidentUnderwritingService {
	
	@Autowired
	StoreProductPlaceUtilService storeProductPlaceUtilService;
	
	@Value("${esb.address.accidentUnderwriting}")
	String accidentUnderwriting = "";
	
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
	
	
	public Response underwriting(String productCode,String proposalNo) throws Exception{ 
		// 封装核保数据
		AccidentUnderwritingRequest requestBody = new AccidentUnderwritingRequest();
		Policy policy1 = new Policy();
		policy1.setBusinessNo(proposalNo);// 投保单号/保险单号
		ChannelOpInfo channel = new ChannelOpInfo();
		channel.setChannelCode("310073"); // 渠道代码 Y
		channel.setChannelName("移动销售支持");// 渠道名称
		channel.setChannelComCode("310073");// 渠道机构代码 Y
		channel.setChannelComName("移动销售支持");// 渠道机构名称
		channel.setChannelProductCode(productCode);// 渠道产品代码 Y
		String trxDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		channel.setTrxDate(trxDate);// 渠道交易日期 江西农信社必填
		channel.setChannelSeqNo(storeProductPlaceUtilService.getTradeSerialNo());// 渠道交易流水号
		requestBody.setChannelOpInfoDTO(channel);
		requestBody.setPolicyDTO(policy1);
		
		Request request =  new Request();
		RequestHead requestHead= this.getConsumerID();
		request.setRequestHead(requestHead);
		request.setRequestBody(requestBody);
		String reqJson = JSONObject.fromObject(request,jsonConfig).toString();
    	log.info("调用意健险核保接口开始，请求报文："+ reqJson);
    	String respJson = this.postJsonRequest(reqJson, accidentUnderwriting);
		log.info("调用意健险核保接口结束，返回报文："+ respJson);
		
		ResponseHead responseHead = null;
		AccidentUnderwritingResponse responseBody = null;
		
		JSONObject json = JSONObject.fromObject(respJson);
		try {
			responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("responseHead"), ResponseHead.class);
			//responseBody = (AccidentUnderwritingResponse) JSONObject.toBean(json.getJSONObject("responseBody"), AccidentUnderwritingResponse.class);
			responseBody =  JSON.parseObject(respJson,AccidentUnderwritingResponse.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Response response = new Response();
		response.setResponseBody(responseBody);
		response.setResponseHead(responseHead);
		return response;
}
	
	public RequestHead getConsumerID(){
		RequestHead requestHead = new RequestHead();
		requestHead.setConsumerID("supsale");
		requestHead.setConsumerSeqNo(getUUID());
		return requestHead;
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
	
	public String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
}
