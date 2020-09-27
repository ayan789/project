package com.ccic.salesapp.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.performance.domain.vo.request.PerformanceQueryTypeRequest;
import com.ccic.salesapp.performance.domain.vo.request.PerformanceRequest;
import com.ccic.salesapp.performance.domain.vo.response.PerformanceResponse;
import com.ccic.salesapp.performance.service.PerformanceInfoService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.ValidationUtils;
import com.github.pagehelper.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
@RestController
@Api(tags = "业绩模块", description = "业绩模块", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "progress")
@Slf4j

public class PerformanceCtl {

        @Autowired
        PerformanceInfoService performanceInfoService;
		/*
		 * @PostMapping(value = "getPerformanceInfo")
		 * 
		 * @ApiOperation(value = "个人业绩查询", notes = "个人业绩", consumes =
		 * MediaType.APPLICATION_JSON_UTF8_VALUE) public HttpResult
		 * getPerformanceInfo(@RequestBody IPerformanceRequest request,String userCode)
		 * { return performanceInfoService.getPerformanceInfo(request, userCode); }
		 */
        
        @PostMapping(value = "getPerformance")
        @ApiOperation(value = "业绩查询", notes = "业绩查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
        public HttpResult<PerformanceResponse> getPerformance(@RequestBody PerformanceRequest request) {
        	ValidationUtils.validate(request);
            if(StringUtil.isEmpty(request.getTeam())) {
            	return performanceInfoService.getPerformance(request);
            }
            if("1".equals(request.getTeam())) {
            	return performanceInfoService.getTeamPerformance(request);
            }
            return performanceInfoService.getPerformance(request);
        }
        
        @PostMapping(value = "getPerformanceByQueryType")
        @ApiOperation(value = "个人业绩查询", notes = "个人业绩查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
        public HttpResult<PerformanceResponse> getPerformanceByQueryType(@RequestBody PerformanceQueryTypeRequest request) {
        	ValidationUtils.validate(request);
        	return performanceInfoService.getPerformanceByQueryType(request);
        }
        
}
