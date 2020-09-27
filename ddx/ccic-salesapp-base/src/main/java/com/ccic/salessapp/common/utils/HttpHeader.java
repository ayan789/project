package com.ccic.salessapp.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;


public class HttpHeader {
/*
* 新系统用不到send方法，已注释
* */
	private static Log log = LogFactory.getLog(HttpHeader.class);
	// 没有压缩的方式,返回接收到的xml
	public String sendHttpData(String uri, String sendXml){
		DataOutputStream dos = null;
		InputStreamReader isr = null;
		BufferedReader in  = null;
		HttpURLConnection urlConn = null;
		StringBuffer countTaskStr = new StringBuffer();
		try {

			URL url = new URL(uri);
			urlConn = (HttpURLConnection) url.openConnection(); // 打开链接
			urlConn.setRequestProperty("Content-Type",
					"text/xml; charset=UTF-8");
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			// urlConn.addRequestProperty("iszip", "1");//添加压缩标志
			// 设置超时问题
			urlConn.setConnectTimeout(10 * 1000);
			urlConn.setReadTimeout(30 * 1000);
			urlConn.setRequestMethod("POST"); // 设置请求方法
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(true);
			urlConn.connect();
			dos = new DataOutputStream(urlConn.getOutputStream()); // 获取输出流
			sendXml = URLEncoder.encode(sendXml, "UTF-8"); // 转码
			dos.writeBytes(sendXml);
			dos.flush();
			dos.close(); // 关闭输出流

			isr = new InputStreamReader(urlConn.getInputStream(), "UTF-8");
			in = new BufferedReader(isr);
			String inputLine;
			while ((inputLine = in.readLine()) != null) { // 读取返回数据
				countTaskStr.append(inputLine).append("\n");
			}
			in.close(); // 关闭输入流
			isr.close();
		} catch (Exception e) {
			log.error(e);
			return null;
		}finally{
			if(dos!=null){
				try {
					dos.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
			if(isr!=null){
				try {
					isr.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
			if(urlConn!=null){
				urlConn.disconnect(); // 关闭连接
			}
		}
		return countTaskStr.toString(); // 返回
	}
	public String sendHttpData(String sendXml)   throws Exception
	{
		String url = this.getUrl();
		String retStr = this.sendHttpData(url, sendXml);
		return retStr;
	}
	/**
	 * 请求servlet，并转成jsonVO对象返回
	 * @param uri
	 * @param sendXml
	 * @return
	 * @throws Exception
	 */
	/*public String sendHttpData(String uri, JsonVO vo)  throws Exception
	{
		String sendXml = JSONObject.fromObject(vo).toString();
		String retStr =  this.sendHttpData(uri, sendXml);
		//转成vo
		return retStr;
	}*/
	/**
	 *  调用平台
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	/*public String sendHttpData( JsonVO vo)  throws Exception
	{
		String sendXml = JSONObject.fromObject(vo).toString();
		String retStr =  this.sendHttpData(sendXml);
		//转成vo
		return retStr;
	}*/
	/**
	 *  调用平台
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	/*public String sendHttpData( JsonVO vo,ServletContext sc)  throws Exception
	{
		String sendXml = JSONObject.fromObject(vo).toString();
//		String retStr =  this.sendHttpData(sendXml);//20121221注释，由跨平台调用调整为接口调用 licy
		*//**
		 * 通过url调用调整为，合并调用
		 *//*
		SendHttpSerivce sendHttp=new SendHttpSerivce();//20121221修改，licy
		String retStr = sendHttp.send(sendXml,sc);
		//转成vo
		return retStr;
	}*/

	/**
	 * 获取大地返回数据的servlet的url
	 * @return
	 */
	public String getUrl()
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("url");
		String url = resourceBundle.getString("chinareservlet");
		return url;
	}

	/**
	 * 根据url.properties获取配置路径地址
	 * @param httpurl 路径名
	 * @return 路径地址
	 */
	public String gethttpUrl(String httpurl){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("url");
		String url = resourceBundle.getString(httpurl);
		return url;
	}
}
