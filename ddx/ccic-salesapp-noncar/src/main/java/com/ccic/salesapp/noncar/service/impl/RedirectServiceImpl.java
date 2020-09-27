package com.ccic.salesapp.noncar.service.impl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.request.ShortUrlRequestVO;
import com.ccic.salesapp.noncar.dto.response.ShortUrlResponseVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.UrlMappingMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.UrlMapping;
import com.ccic.salesapp.noncar.service.RedirectService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.StringUtils;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedirectServiceImpl implements RedirectService{
	@Autowired
	UrlMappingMapper urlMappingMapper;
		
	@Value("${system.short_url_config}")
	String short_url_config;
		                    

	@Override
	public void redirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String urlCode = request.getParameter("r");
		String urlMapping = "";
		
		String outQueryString = request.getQueryString();
		try{
			if(StringUtils.isNotBlank(urlCode)){
				urlMapping = urlMappingMapper.selectUrlMapping(urlCode);
			}
			if(StringUtils.isNotBlank(urlMapping)){
				if(urlMapping.indexOf("?")>0) {
					urlMapping+="&"+outQueryString;
				}else {
					urlMapping+="?"+outQueryString;
				}
				response.sendRedirect(urlMapping);
			}
		}catch(Exception e){
			log.error("系统异常:"+e);
		}finally{
		}
	}

	@Override
	public HttpResult getShortUrl(ShortUrlRequestVO req) {
		String url = req.getUrl();//原始链接
		
		if(StringUtils.isBlank(url)){
			return HttpResult.error(0, "原始链接不能为空！");
		}
		
		try {
			String urlCode = RandomStringUtils.randomAlphanumeric(10);
			
			UrlMapping urlMapping = new UrlMapping();
			urlMapping.setUrlCode(urlCode);
			urlMapping.setUrlMapping(url);
			urlMappingMapper.toInsert(urlMapping);
			
			String shortUrl = short_url_config+urlCode;
			
			ShortUrlResponseVO resVO = new ShortUrlResponseVO();
			resVO.setShortUrl(shortUrl);
			return HttpResult.success(resVO, 1, "成功");
		} catch (Exception e) {
			log.error("生成短链接异常",e);
			return HttpResult.error(0, "失败");
		}
	}
	
}
