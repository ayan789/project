package com.ccic.salesapp.noncar.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccic.salesapp.noncar.dto.request.ShortUrlRequestVO;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface RedirectService {
	
	public void redirect(HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public HttpResult getShortUrl(ShortUrlRequestVO req);
}
