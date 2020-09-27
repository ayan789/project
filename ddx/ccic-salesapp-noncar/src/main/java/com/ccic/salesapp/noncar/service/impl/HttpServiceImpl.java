package com.ccic.salesapp.noncar.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.repository.basedb.mapper.ApiLogMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.ApiLog;
import com.ccic.salesapp.noncar.service.HttpService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HttpServiceImpl implements HttpService {
	
	@Autowired
	ApiLogMapper apiLogMapper;

	/**
	 * 发送post请求
	 * 
	 * @param reqJson :请求的字符串
	 * @param uri :请求路径
	 * @param charset :编码
	 * @return
	 * @throws Exception 
	 */
	public String postJsonRequest(String bussNo,String reqJson, String uri, String charset) throws Exception {
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
			log.info("返回报文： resp{"+response+"}");
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Method failed: " + method.getStatusLine());
			}
		} catch (Exception e) {		
			log.error("http请求异常：",e);
			throw e;
		}finally {
			ApiLog apiLog = new ApiLog();
			apiLog.setApiUrl(uri);
			apiLog.setRequest(reqJson);
			apiLog.setResponse(response.toString());
			apiLog.setBussNo(bussNo);
			apiLogMapper.insertSelective(apiLog);
			
		}
		return response.toString(); // 返回
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
	public String post(String bussNo,String reqJson, String uri, String charset) throws Exception {
		HttpClient  client=new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
		client.getHttpConnectionManager().getParams().setSoTimeout(60000);
		client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("8000613466", "eBao1234"));
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
			log.info("调用核心---返回报文： resp{"+response+"}");
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Method failed: " + method.getStatusLine());
			}
		} catch (Exception e) {		
			log.error("http请求异常：",e);
			throw e;
		}finally {
			ApiLog apiLog = new ApiLog();
			apiLog.setApiUrl(uri);
			apiLog.setRequest(reqJson);
			apiLog.setResponse(response.toString());
			apiLog.setBussNo(bussNo);
			apiLogMapper.insertSelective(apiLog);
			
		}
		return response.toString(); // 返回
	}
	
	@Override
	public String postJsonRequest(String bussNo,String reqJson, String uri) throws Exception {
		return postJsonRequest(bussNo,reqJson,uri,"utf-8");
	}
	
	
	@Override
	public String postGroupRequest(String bussNo,String reqJson, String uri) throws Exception {
		return post(bussNo,reqJson,uri,"utf-8");
	}
}
