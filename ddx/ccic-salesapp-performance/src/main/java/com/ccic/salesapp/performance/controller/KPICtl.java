package com.ccic.salesapp.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.performance.domain.vo.request.KPIRequest;
import com.ccic.salesapp.performance.domain.vo.response.KPIResponse;
import com.ccic.salesapp.performance.service.KPIService;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "KPI查询", description = "KPI查询", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "progress")
@Slf4j
public class KPICtl {

	@Autowired
	KPIService kPIService;

	@PostMapping(value = "getKPI")
	@ApiOperation(value = "KPI考核信息", notes = "KPI考核信息", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<KPIResponse> getKPI(@RequestBody KPIRequest request) {
		return kPIService.getKPI(request);
	}
}
