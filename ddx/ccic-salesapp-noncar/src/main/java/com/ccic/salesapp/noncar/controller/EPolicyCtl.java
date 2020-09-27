package com.ccic.salesapp.noncar.controller;
import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.request.ElectronicPolicyRequest;
import com.ccic.salesapp.noncar.dto.request.SendElectronicPolicyRequest;
import com.ccic.salesapp.noncar.service.ElectronicPolicyService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.LoginUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "电子保单", description = "电子保单", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "")
@Slf4j
public class EPolicyCtl {
	
	@Autowired
	ElectronicPolicyService electronicPolicyService;
	
	@Value("${esb.netType.electronicPolicy}")
	String netType;
	
	/**
	 * 电子保单
	 * */
	@PostMapping(value = "electronicPolicy")
    @ApiOperation(value = "电子保单", notes = "电子保单", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult electronicPolicy(@RequestBody ElectronicPolicyRequest reqVO){
		HttpResult result = HttpResult.success();
		String policyNo=reqVO.getPolicyNo();
		if("".equals(policyNo) || policyNo==null) {
			result.setCode("0");
			result.setMsg("保单号不能为空！");
			return result;
		}
		//ElectronicPolicyFacade facade=new ElectronicPolicyFacadeImpl();
		try {
			return electronicPolicyService.electronicPolicy(reqVO,netType);
		}catch (Exception e) {
			result.setCode("0");
			result.setMsg("获取电子保单下载地址异常！");
			//json.setResult(result);
		}
		return result;
	}
	
	//发送电子保单
	@PostMapping(value = "sendElectronicPolicy")
    @ApiOperation(value = "发送电子保单", notes = "发送电子保单", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult sendElectronicPolicy(@RequestBody SendElectronicPolicyRequest reqVO ){
		HttpResult result = HttpResult.success();
		LoginUser user = new LoginUser();
		user.setComCode(reqVO.getComCode());
		user.setUserCode(reqVO.getUserCode());
		if(StringUtils.isBlank(reqVO.getPolicyNo())) {
			result.setCode("0");
			result.setMsg("保单号不能为空！");
			//json.setResult(result);
			return result;
		}
		if(StringUtils.isBlank(reqVO.getType())) {
			result.setCode("0");
			result.setMsg("发送类型不能为空！");
			//json.setResult(result);
			return result;
		}
		if("1".equals(reqVO.getType()) && StringUtils.isBlank(reqVO.getEmail())) {
			result.setCode("0");
			result.setMsg("邮箱不能为空！");
			//json.setResult(result);
			return result;
		}
		if("2".equals(reqVO.getType()) && StringUtils.isBlank(reqVO.getMobile())) {
			result.setCode("0");
			result.setMsg("手机号不能为空！");
			//result.setResult(result);
			return result;
		}
		try {
			//ElectronicPolicyFacade facade=new ElectronicPolicyFacadeImpl();
			return electronicPolicyService.sendElectronicPolicy(reqVO, user);
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("发送电子保单异常！");
			log.error(e.getMessage(),e);
		}
		return result;
	}
  
}
