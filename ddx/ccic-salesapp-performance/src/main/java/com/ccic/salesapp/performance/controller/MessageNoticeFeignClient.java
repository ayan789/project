package com.ccic.salesapp.performance.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ccic.salessapp.common.core.web.HttpResult;

@FeignClient(value="CCIC-SALESAPP-SUPPORT")
public interface MessageNoticeFeignClient {
	
	 @PostMapping(value = "/message/sendMessage")
     HttpResult sendMessage(@RequestBody List<MessageSendVO> messageVo);

}
