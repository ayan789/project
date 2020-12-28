package com.ccic.salesapp.noncar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ccic.salesapp.noncar.dto.request.MyStoreRequest;
import com.ccic.salesapp.noncar.dto.request.news.NewsVo;
import com.ccic.salesapp.noncar.dto.store.request.ContentRequest;
import com.ccic.salesapp.noncar.repository.basedb.mapper.ContentMapper;
import com.ccic.salesapp.noncar.service.StoreProcessService;
import com.ccic.salesapp.noncar.service.StoreService;
import com.ccic.salessapp.common.core.web.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "门店首页配置", description = "门店首页配置", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "myStore")
@Slf4j
public class MyStoreController {

	@Autowired
	StoreService storeService;

	@Autowired
	StoreProcessService storeProcessService;
	
	@Autowired
	ContentMapper contentMapper;

	@Autowired
	StoreCtl storeCtl;

	/**
	 * 门店首页配置
	 * 
	 * @param reqvo
	 * @return
	 */
	@PostMapping(value = "myStoreHomeSet")
	@ApiOperation(value = "门店首页配置", notes = "门店首页配置", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> myStoreHomeSet(@RequestBody MyStoreRequest reqvo) {
		return storeService.myStoreHomeSet(reqvo);
	}
	
	@PostMapping(value = "newsSet")
	@ApiOperation(value = "新闻配置", notes = "新闻配置", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> newsSet(@RequestBody NewsVo reqvo) {
		return storeService.newsSet(reqvo);
	}
	
	/**
	 * 门店资讯列表
	 * 
	 * @param reqvo
	 * @return
	 */
	@PostMapping(value = "contentList")
	@ApiOperation(value = "门店资讯列表", notes = "门店资讯列表", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> contentList(@RequestBody ContentRequest request) {
		return storeService.contentList(request);
	}
	
}
