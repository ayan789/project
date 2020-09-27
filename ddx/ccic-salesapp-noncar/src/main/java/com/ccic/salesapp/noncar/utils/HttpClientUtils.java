package com.ccic.salesapp.noncar.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	public static String postDataWithJson(String url,String json) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setEntity(new StringEntity(json, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			String srtResult = EntityUtils.toString(response.getEntity(),"UTF-8");//获得返回的结果
			return srtResult;
		} catch (HttpHostConnectException e){
			e.printStackTrace();
			/* //请求失败重试次数-redis
			StringCache stringCache = SpringBeanUtils.getBeans("stringCache",StringCache.class);
			stringCache.put(url,""+maxInvokeTimes,ONE_MINUTES);
			if(Integer.parseInt(stringCache.get(url))>0){
				stringCache.put(url,""+--maxInvokeTimes,ONE_MINUTES);
				return postDataWithJson( url, json);
			}*/
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		String json = "[\n" + 
				"    {\n" + 
				"        \"bizNo\": \"P122323\",\n" + 
				"        \"consumerID\": \"supsale\",\n" + 
				"        \"extendMessage\": \"string\",\n" + 
				"        \"isAll\": 0,\n" + 
				"        \"message\": \"非车险出单:【车牌号】沪L99999支付已成功，请尽快确认。\",\n" + 
				"        \"messageType\": \"cust-pay-suceed\",\n" + 
				"        \"platform\": \"supsale\",\n" + 
				"        \"providerID\": \"custApp\",\n" + 
				"        \"source\": 0,\n" + 
				"        \"subTitle\": \"最新特别消息\",\n" + 
				"        \"title\": \"超级APP尊敬的客户\",\n" + 
				"        \"userCode\": 100001,\n" + 
				"        \"userCodes\": [\n" + 
				"            \"1233\",\n" + 
				"            \"32343\"\n" + 
				"        ],\n" + 
				"        \"version\": 1\n" + 
				"    }\n" + 
				"]";
		String aa = HttpClientUtils.postDataWithJson("http://10.100.123.125:8089/message/sendMessage", json);
		System.out.println(aa);
	}
	
	
}
