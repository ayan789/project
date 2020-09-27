package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.request.PicInfo;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface OcrService {
	
	public HttpResult recognizeImage(PicInfo picInfo);
	
}
