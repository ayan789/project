package com.ccic.salesapp.noncar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardAddTagsReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardDelTagsReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardUpdateReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardUpdateTagsReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardVo;
import com.ccic.salesapp.noncar.service.BusinessCardService;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "个人名片模块", description = "个人名片模块", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "card")
public class BusinessCardController {

	@Autowired
	BusinessCardService businessCardService;
	
	/**
	 *  个人名片
	 * @param businessCardReq
	 * @return
	 */
	@PostMapping(value = "getBusinessCard")
	public HttpResult<?> getBusinessCard(@RequestBody BusinessCardReq businessCardReq){
		return businessCardService.getBusinessCard(businessCardReq);
	}
	
	/**
	 * 更新名片内容
	 * @param businessCardUpdateTagsReq
	 * @return
	 */
	@PostMapping(value = "businessCardUpdate")
	public HttpResult<?> updateOtherBusinessCard(@RequestBody BusinessCardUpdateReq businessCardUpdateReq){
		return businessCardService.updateOtherBusinessCard(businessCardUpdateReq);
	}
}
