package com.example.tlyannoif;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * HttpClient工具类
 * @author wangmx
 */
public class HttpClientUtils
{
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录
 
    private static RequestConfig requestConfig = null;
 
    static
    {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
    }
 
    /**
     * post请求传输json参数
     * @param url  url地址
     * @param json 参数
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam)
    {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        try
        {
            if (null != jsonParam)
            {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                String str = "";
                try
                {
                    // 读取服务器返回过来的json字符串数据
                    str = EntityUtils.toString(result.getEntity(), "utf-8");
                    // 把json字符串转换成json对象
                    jsonResult = JSONObject.parseObject(str);
                }
                catch (Exception e)
                {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        }
        catch (IOException e)
        {
            logger.error("post请求提交失败:" + url, e);
        }
        finally
        {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }
 
    /**
     * post请求传输String参数 例如：name=Jack&sex=1&type=2
     * Content-type:application/x-www-form-urlencoded
     * @param url            url地址
     * @param strParam       参数
     * @return
     */
    public static JSONObject httpPost(String url, String strParam)
    {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        try
        {
            if (null != strParam)
            {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(strParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                String str = "";
                try
                {
                    // 读取服务器返回过来的json字符串数据
                    str = EntityUtils.toString(result.getEntity(), "utf-8");
                    // 把json字符串转换成json对象
                    jsonResult = JSONObject.parseObject(str);
                }
                catch (Exception e)
                {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        }
        catch (IOException e)
        {
            logger.error("post请求提交失败:" + url, e);
        }
        finally
        {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }
 
    /**
     * 发送get请求
     * @param url 路径
     * @return
     */
    public static JSONObject httpGet(String url)
    {
        // get请求返回结果
        JSONObject jsonResult = null;
        CloseableHttpClient client = HttpClients.createDefault();
        // 发送get请求
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        try
        {
            CloseableHttpResponse response = client.execute(request);
 
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                // 读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();
                String strResult = EntityUtils.toString(entity, "utf-8");
                // 把json字符串转换成json对象
                jsonResult = JSONObject.parseObject(strResult);
            }
            else
            {
                logger.error("get请求提交失败:" + url);
            }
        }
        catch (IOException e)
        {
            logger.error("get请求提交失败:" + url, e);
        }
        finally
        {
            request.releaseConnection();
        }
        return jsonResult;
    }
    
    public static void main(String[] args) throws JsonProcessingException, IOException {
//    	for(int i=1;i<=10;i++) {
//    		JSONObject json = HttpClientUtils.httpGet("https://mdncapi.bqiapp.com/api/coin/web-coinrank?webp=1&pagesize=100&page="+i+"&type=-1");
//        	List<CoinBean> coinBeans = JSONArray.parseArray(json.get("data")+"", CoinBean.class);
//        	for(CoinBean coinBean : coinBeans) {
//        		System.out.println(coinBean.getCode());
//        	}
//    	}
    	
    	//JSONObject json = HttpClientUtils.httpGet("https://mdncapi.bqiapp.com/api/coin/web-coinrank?webp=1&pagesize=100&page=1&type=-1");
    	JsonUtil ju = new JsonUtil();
    	ObjectMapper mapper = new ObjectMapper();
 	    JsonNode actualObj = mapper.readTree("{\"data\":[{\"code\":\"aaa\",\"current_price\":111,\"current_price_usd\":111,\"logo\":\"png\"},{\"code\":\"bbb\",\"current_price\":222,\"current_price_usd\":222,\"logo\":\"png\"}]}");
 	    Map<String,Object> map = ju.listNames("", actualObj);
 	    System.out.println(JSONObject.toJSONString(map));
 	    
		
//		JsonPointer pointer = JsonPointer.compile("/duto1");
//		Duto initRequest = null;
//		initRequest = new Duto();
//		ObjectNode node = (ObjectNode) mapper.readTree(mapper.writeValueAsString(initRequest));
//		JsonNode value =  new TextNode("1");
//		ju.setJsonPointerValue(node, pointer, value);
//		pointer = JsonPointer.compile("/duto2");
//		value =  new TextNode("2");
//		ju.setJsonPointerValue(node, pointer, value);
//		pointer = JsonPointer.compile("/dbos/0/dbo1");
//		value =  new TextNode("1");
//		ju.setJsonPointerValue(node, pointer, value);
//		pointer = JsonPointer.compile("/dbos/0/dbo2");
//		value =  new TextNode("2");
//		ju.setJsonPointerValue(node, pointer, value);
//		System.out.println(node);
		
// 	    /data/55/high_price
 	    
 	    HashMap<String, String> map1 = new HashMap<String, String>();
 	    map1.put("/data/0/current_price_usd", "/data/0/priceUsd");
 	   
 	    
		Data coinBean = new Data();
		ObjectNode node = (ObjectNode) mapper.readTree(mapper.writeValueAsString(coinBean));
		JsonPointer pointer = JsonPointer.compile("/data/0/code");
		JsonNode value =  new TextNode("hpy");
		ju.setJsonPointerValue(node, pointer, value);
		pointer = JsonPointer.compile("/data/0/logo");
		value =  new TextNode("logo.png");
		ju.setJsonPointerValue(node, pointer, value);
//		pointer = JsonPointer.compile("/data/1/priceUsd");
//		value =  new TextNode("111");
//		ju.setJsonPointerValue(node, pointer, value);
		pointer = JsonPointer.compile("/data/2/logo");
		value =  new TextNode("logo2.png");
		ju.setJsonPointerValue(node, pointer, value);
		pointer = JsonPointer.compile("/data/0/priceUsd");
		value =  new TextNode("111");
		ju.setJsonPointerValue(node, pointer, value);
		System.out.println(node);
		
 	    
 	    
    }
}
