package com.ccic.salesapp.noncar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.request.ShortUrlRequestVO;
import com.ccic.salesapp.noncar.dto.request.UrlRequestVO;
import com.ccic.salesapp.noncar.service.RedirectService;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "短链接", description = "短链接", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "shortUrl")
public class RedirectCtl {
	
	@Autowired
	RedirectService redirectService;
	
	@GetMapping(value = "/redirect")
	@ApiOperation(value = "短链接跳转", notes = "短链接跳转", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void redirect(HttpServletRequest request,HttpServletResponse response) throws Exception{
		redirectService.redirect(request, response);
	}
	
	@PostMapping(value = "/getShortUrl")
	@ApiOperation(value = "生成短链接", notes = "生成短链接", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult getShortUrl(@RequestBody ShortUrlRequestVO req) {
		return redirectService.getShortUrl(req);
	}
	
	@PostMapping(value = "/getRedirectUrl")
	@ApiOperation(value = "生成短链接", notes = "获取重定向链接", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult getRedirectUrl(@RequestBody UrlRequestVO req) throws Exception{
		String redirectUrl = redirectService.getRedirectUrl(req);
		return HttpResult.success(redirectUrl,1, "OK");
	}
	
}
