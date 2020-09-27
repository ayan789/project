package com.ccic.salesapp.performance.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.performance.domain.vo.request.AssessRequest;
import com.ccic.salesapp.performance.domain.vo.response.AssessResponse;
import com.ccic.salesapp.performance.service.AssessService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.ValidationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;




@RestController
@Api(tags = "销售行动力评估", description = "销售行动力评估", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "progress")
public class AssessCtl {

    @Autowired
    AssessService assessService;

    @PostMapping(value = "/getAssess")
    @ApiOperation(value = "销售行动力评估", notes = "销售行动力评估", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<AssessResponse> getAssess(@RequestBody AssessRequest request) {
    	ValidationUtils.validate(request);
        return assessService.getAssessItemsByUserCode(request);
    }




}
