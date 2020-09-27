package com.ccic.salessapp.common.outService.rest.common.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;;

/**
 * 新系统中rest请求服务不再继承此类，使用CommonServiceUtil
 */
@Deprecated
public class CommonService {
	private static Log log=LogFactory.getLog(CommonService.class);
	
//	private static SystemConfig interfaceConfig = new SystemConfig("interface_config");
	private static Map<String,String> interfaceMap = new ConcurrentHashMap<String,String>();
	
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

	
	
	public RequestHead getConsumerID(){
		RequestHead requestHead = new RequestHead();
		requestHead.setConsumerID("mobile");
		requestHead.setConsumerSeqNo(getUUID());
		return requestHead;
	}
	
	/*public String getInterfaceUrl(String interfaceCode){
		String  interfaceUrl = interfaceMap.get(interfaceCode);
		if (interfaceUrl == null) {
			try {
				interfaceUrl = interfaceConfig.getResourceValue(interfaceCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			interfaceMap.put(interfaceCode, interfaceUrl);
		}
		return interfaceUrl;
	}*/
	
	public String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	
	/**
	 * ����post����
	 * 
	 * @param reqJson :������ַ���
	 * @param uri :����·��
	 * @param charset :����
	 * @return
	 * @throws Exception 
	 */
	public String postJsonRequest(String reqJson, String uri, String charset) throws Exception {
		HttpClient  client=new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
		client.getHttpConnectionManager().getParams().setSoTimeout(60000);
		client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("mobile", "mobile"));
		log.info("##########�����ַ��" +uri);
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
			//log.info("���ú���---���ر��ģ� resp{"+response+"}");
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Method failed: " + method.getStatusLine());
			}
		} catch (Exception e) {		
			log.error("http�����쳣��",e);
			throw e;
		}
		return response.toString(); // ����
	}
	public String postJsonRequest(String reqJson, String uri) throws Exception {
		return postJsonRequest(reqJson,uri,"utf-8");
	}
	
	/**
	 * ����post����
	 * 
	 * @param reqJson :������ַ���
	 * @param uri :����·��
	 * @param charset :����
	 * @return
	 * @throws Exception 
	 */
	public static String postJsonRequest2(String reqJson, String uri, String charset) throws Exception {
		HttpClient  client=new HttpClient();
		//client.getHostConfiguration().setProxy("10.1.27.102", 8080);
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		client.getHttpConnectionManager().getParams().setSoTimeout(60000);
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
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Method failed: " + method.getStatusLine());
			}
		} catch (Exception e) {		
			log.error("http�����쳣��",e);
			throw e;
		}
		return response.toString(); // ����
	}
}
