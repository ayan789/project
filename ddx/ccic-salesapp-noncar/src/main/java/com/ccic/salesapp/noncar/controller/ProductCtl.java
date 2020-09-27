package com.ccic.salesapp.noncar.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.product.request.ProductListRequest;
import com.ccic.salesapp.noncar.dto.product.response.ProductListResponse;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "产品工厂用产品列表查询", description = "产品列表查询", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "product")
@Slf4j
public class ProductCtl {
	
	@Autowired
	PlanStrategyService planStrategyService;
    
    @PostMapping(value = "productList")
    @ApiOperation(value = "产品列表查询", notes = "产品列表查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<ProductListResponse> productList(@RequestBody ProductListRequest request) {
    	
    	
     return planStrategyService.planStrategyList(request);
    }

}
