package com.ccic.salesapp.noncar.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.service.PrpdcompanyService;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "机构测试", description = "机构测试", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "com")
@Slf4j
public class CompanyCtl {
	
	@Autowired
	PrpdcompanyService prpdcompanyService;
    
    @PostMapping(value = "test")
    @ApiOperation(value = "上级机构代码查询 ", notes = "上级机构代码查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult test(String comCode,String comLevel) {
    	List<String>  list = prpdcompanyService.getUpperComCode(comCode);
    	log.debug("机构所有上级" + list);
    	list = prpdcompanyService.getUpperComCodeList(comCode, comLevel);
    	log.debug("机构上级" + list+ "级别限制：" + comLevel);
    	String com = prpdcompanyService.getUpperComCode(comCode, comLevel);
    	log.debug("机构上级" + com+ "级别限制：" + comLevel);
       return HttpResult.success();
    }


	@PostMapping(value = "test2")
	@ApiOperation(value = "查询机构下所有子机构代码 ", notes = "查询机构下所有子机构代码", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult QueryDe(String comCode) {
    	List<String> a=new ArrayList<>();
		prpdcompanyService.getlowComCode(comCode,a);

		return HttpResult.success(a);
	}


}
