package com.ccic.salesapp.noncar.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.request.PolicyStatusRequest;
import com.ccic.salesapp.noncar.dto.request.UnderwriteStatusRequest;
import com.ccic.salesapp.noncar.service.PolicyService;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "异步通知", description = "异步通知", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "syn")
@Slf4j
public class SynCtl {
	
	@Autowired
	PolicyService policyService;
    
    @PostMapping(value = "status")
    @ApiOperation(value = "保单生效通知", notes = "保单生效通知", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult synStatus(@RequestBody List<PolicyStatusRequest> request) {
       log.info("收到保单生效通知：" + request);
       policyService.synPolicyStatus(request);
       return HttpResult.success();
    }


    @PostMapping(value = "underwrite/status")
    @ApiOperation(value = "核保结果生效通知", notes = "核保结果生效通知", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult synUnderwriteStatus(@RequestBody UnderwriteStatusRequest request) {
    	log.info("核保结果通知：" + request);
           policyService.synUnderwriteStatus(request);
       return HttpResult.success();
    }
    
//    
//    @PostMapping(value = "statusTwo")
//    @ApiOperation(value = "保单生效通知", notes = "保单生效通知", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public HttpResult synStatusTwo(@RequestBody List<PolicyStatusRequest> request) {
//       log.info("收到保单生效通知：" + request);
//       policyService.synPolicyStatusTwo(request);
//       return HttpResult.success();
//    }
//
//
//    @PostMapping(value = "underwriteTwo/statusTwo")
//    @ApiOperation(value = "核保结果生效通知", notes = "核保结果生效通知", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public HttpResult synUnderwriteStatusTwo(@RequestBody UnderwriteStatusRequest request) {
//    	log.info("核保结果通知：" + request);
//           policyService.synUnderwriteStatusTwo(request);
//       return HttpResult.success();
//    }
//    
}
