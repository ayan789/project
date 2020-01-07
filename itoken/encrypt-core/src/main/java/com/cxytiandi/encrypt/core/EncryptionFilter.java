package com.cxytiandi.encrypt.core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cxytiandi.encrypt.springboot.annotation.Encrypt;
import com.cxytiandi.encrypt.springboot.annotation.NoEnPut;
import com.cxytiandi.encrypt.springboot.init.ApiEncryptDataInit;
import com.cxytiandi.encrypt.util.BaseException;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cxytiandi.encrypt.algorithm.AesEncryptAlgorithm;
import com.cxytiandi.encrypt.algorithm.EncryptAlgorithm;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.reflections.Reflections;

/**
 * 数据加解密过滤器
 * 
 * @author yinjihuan
 * 
 * @date 2019-01-12
 * 
 * @about http://cxytiandi.com/about
 *
 */
public class EncryptionFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(EncryptionFilter.class);
	
	private EncryptionConfig encryptionConfig;
	
	private EncryptAlgorithm encryptAlgorithm = new AesEncryptAlgorithm();
	
	public EncryptionFilter() {
		this.encryptionConfig = new EncryptionConfig();
	}
	
	public EncryptionFilter(EncryptionConfig config) {
		this.encryptionConfig = config;
	}
	
	public EncryptionFilter(EncryptionConfig config, EncryptAlgorithm encryptAlgorithm) {
		this.encryptionConfig = config;
		this.encryptAlgorithm = encryptAlgorithm;
	}
	
	public EncryptionFilter(String key) {
		EncryptionConfig config = new EncryptionConfig();
		config.setKey(key);
		this.encryptionConfig = config;
	}
	
	public EncryptionFilter(String key, List<String> responseEncryptUriList, List<String> requestDecyptUriList,
			String responseCharset, boolean debug) {
		this.encryptionConfig = new EncryptionConfig(key, responseEncryptUriList, requestDecyptUriList, responseCharset, debug);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		logger.debug("RequestURI: {}", uri);
		
		// 调试模式不加解密
		if (encryptionConfig.isDebug()) {
			chain.doFilter(req, resp);
			return;
		}

		Map<String,String> map1 = new HashMap<>();
		map1.put("start","/home");
		map1.put("end","_put");
		List<Map<String,String>> list = new ArrayList<>();
		list.add(map1);
		encryptionConfig.setResponseEncryptUriIgnoreMapList(list);
		
		boolean decryptionStatus = this.contains(ApiEncryptDataInit.requestDecyptUriList, uri, req.getMethod());
		boolean encryptionStatus = this.contains(ApiEncryptDataInit.responseEncryptUriList, uri, req.getMethod());
		boolean decryptionIgnoreStatus = this.contains(ApiEncryptDataInit.responseEncryptUriIgnoreList, uri, req.getMethod());
		boolean encryptionIgnoreStatus = this.contains(ApiEncryptDataInit.responseEncryptUriIgnoreList, uri, req.getMethod());

//		System.out.println(decryptionStatus);
//		System.out.println(encryptionStatus);
//		System.out.println(decryptionIgnoreStatus);
//		System.out.println(encryptionIgnoreStatus);


//		System.out.println("aaaafff:"+JSONObject.toJSONString(encryptionConfig.getResponseEncryptUriIgnoreMapList()));
////		System.out.println(JSONObject.toJSONString(uri));
////		System.out.println(JSONObject.toJSONString(req.getMethod()));
		// 没有配置具体加解密的URI默认全部都开启加解密
		if (encryptionConfig.getRequestDecyptUriList().size() == 0 
				&& encryptionConfig.getResponseEncryptUriList().size() == 0) {
			decryptionStatus = true;
			encryptionStatus = true;
		}
		
		// 接口在忽略加密列表中
		if (encryptionIgnoreStatus) {
			encryptionStatus = false;
		}
//		encryptionStatus = false;
		// 接口在忽略解密列表中
		if (decryptionIgnoreStatus) {
			decryptionStatus = false;
		}


		
		// 没有加解密操作
		if (decryptionStatus == false && encryptionStatus == false) {

			chain.doFilter(req, resp);
			return;
		}
		
		
		EncryptionResponseWrapper responseWrapper = null;
		EncryptionReqestWrapper reqestWrapper = null;
		// 配置了需要解密才处理
		if (decryptionStatus) {
			reqestWrapper = new EncryptionReqestWrapper(req);
			String requestData = reqestWrapper.getRequestData();
			logger.debug("RequestData: {}", requestData);
			try {
				String decyptRequestData = encryptAlgorithm.decrypt(requestData, encryptionConfig.getKey());
				logger.debug("DecyptRequestData: {}", decyptRequestData);
				reqestWrapper.setRequestData(decyptRequestData);
			} catch (BaseException e) {
				logger.error("请求数据解密失败", e);
				try {
					throw new BaseException("001","jdsjjkk");
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (encryptionStatus) {
			responseWrapper = new EncryptionResponseWrapper(resp);
		}
		
		// 同时需要加解密
		if (encryptionStatus && decryptionStatus) {
			System.out.println("ooopppp");
			System.out.println(reqestWrapper.getRequestData());
			System.out.println(responseWrapper.getResponseData());


			chain.doFilter(reqestWrapper, responseWrapper);
		} else if (encryptionStatus) { //只需要响应加密
			chain.doFilter(req, responseWrapper);
		} else if (decryptionStatus) { //只需要请求解密
			chain.doFilter(reqestWrapper, resp);
		}
		
		// 配置了需要加密才处理
		if (encryptionStatus) {
			String responeData = responseWrapper.getResponseData();
			logger.debug("ResponeData: {}", responeData);
			ServletOutputStream out = null;
			try {
				responeData = encryptAlgorithm.encrypt(responeData, encryptionConfig.getKey());
				logger.debug("EncryptResponeData: {}", responeData);
				response.setContentLength(responeData.length());
				response.setCharacterEncoding(encryptionConfig.getResponseCharset());
		        out = response.getOutputStream();
		        out.write(responeData.getBytes(encryptionConfig.getResponseCharset()));
			} catch (Exception e) {
				logger.error("响应数据加密失败", e);
				throw new RuntimeException(e);
			} finally {
				if (out != null) {
					out.flush();
				    out.close();
				}
			}
			
		} 
		
	}

	private boolean contains(List<String> list, String uri, String methodType) {
		if (list.contains(uri)) {
			return true;
		}
		String prefixUri = methodType.toLowerCase() + ":" + uri;
		logger.debug("contains uri: {}", prefixUri);
//		if (list.contains(prefixUri)) {
//			return true;
//		}
		for(int i=0;i<list.size();i++){
			boolean a = methodType.equalsIgnoreCase(list.get(i).split(":")[0]);
			boolean b = StringUtils.startsWithIgnoreCase(uri,list.get(i).split(":")[1]);
//			System.out.println("-----======");
//			System.out.println(list.get(i).split(":")[0]);
//			System.out.println(list.get(i).split(":")[1]);
			if(a && b) return true;
		}
		return false;
	}

	private boolean contains2(List<Map<String,String>> list, String uri, String methodType) {
		String url_method = uri + "_" + methodType;
		System.out.println("url_method"+url_method);
		for(int i=0;i<list.size();i++){
			boolean a = StringUtils.startsWithIgnoreCase(url_method,list.get(i).get("start"));
			boolean b = StringUtils.endsWithIgnoreCase(url_method,list.get(i).get("end"));
			if(a && b) return true;
		}
		return false;
	}

	private boolean contains3(List<Map<String,String>> list, String uri, String methodType) {
//		String url_method = uri + "_" + methodType;
//		System.out.println("url_method"+url_method);
//		for(int i=0;i<list.size();i++){
//			boolean a = StringUtils.startsWithIgnoreCase(url_method,list.get(i).get("start"));
//			boolean b = StringUtils.endsWithIgnoreCase(url_method,list.get(i).get("end"));
//			if(a && b) return true;
//		}

		System.out.println("yyuuii");
//		// 需要排除的路径
		List<String> excludeUrls = new ArrayList<>();
		// 设置扫描路径
		Reflections reflections = new Reflections(
				new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("com.example.tlyanencrypt.controller")).setScanners(new MethodAnnotationsScanner()));
		// 扫描指定包内带有@ExcludeDangerousCharacterUrl注解的所有方法集合
		Set<Method> methods = reflections.getMethodsAnnotatedWith(NoEnPut.class);
		if (null != methods && !methods.isEmpty()) {
			for (Method method : methods) {

//				System.out.println(JSONObject.toJSONString(method));
				String excludeUrl = method.getAnnotation(PutMapping.class).value()[0];
//				System.out.println("excludeUrl:"+excludeUrl);
				excludeUrls.add(excludeUrl);
			}
		}

//		Class<?> clz = bean.getClass();
//		Method[] methods = clz.getMethods();
//		for (Method method : methods) {
//			Encrypt encrypt = AnnotationUtils.findAnnotation(method, Encrypt.class);
//			if (encrypt != null) {
//				// 注解中的URI优先级高
//				String uri = encrypt.value();
//				if (!StringUtils.hasText(uri)) {
//					uri = getApiUri(clz, method);
//				}
//				logger.debug("Encrypt URI: {}", uri);
//
//			}
//		}

		System.out.println("需要排除过滤的URL有：" + excludeUrls.toArray().toString());


		return false;
	}
	
	@Override
	public void destroy() {
		
	}
}
