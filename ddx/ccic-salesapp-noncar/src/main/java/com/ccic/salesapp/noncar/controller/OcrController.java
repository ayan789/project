package com.ccic.salesapp.noncar.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccic.salesapp.noncar.dto.request.PicInfo;
import com.ccic.salesapp.noncar.service.OcrService;
import com.ccic.salessapp.common.core.web.HttpResult;

public class OcrController {

	@Autowired
	OcrService ocrService;
	
	public HttpResult recognizeImage(PicInfo picInfo){
		HttpResult result = HttpResult.success(ocrService.recognizeImage(picInfo));
		result.setCode("1");
		result.setMsg("查询成功");
		return result;
	}
	
}
