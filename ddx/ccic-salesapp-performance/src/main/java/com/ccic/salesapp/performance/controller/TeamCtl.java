package com.ccic.salesapp.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.performance.domain.vo.request.TeamRequest;
import com.ccic.salesapp.performance.domain.vo.response.TeamResponse;
import com.ccic.salesapp.performance.service.TeamService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.ValidationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "查询团队", description = "查询团队", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "progress")
@Slf4j
public class TeamCtl {

    @Autowired
    TeamService teamService;

    @PostMapping(value = "teamQuery")
    @ApiOperation(value = "查询团队", notes = "查询团队", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<TeamResponse> teamQuery(@RequestBody TeamRequest request) {
    	ValidationUtils.validate(request);
        return teamService.teamQuery(request);
    }




}
