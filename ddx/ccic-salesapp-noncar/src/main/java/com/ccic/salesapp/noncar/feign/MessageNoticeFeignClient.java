package com.ccic.salesapp.noncar.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ccic.salesapp.noncar.feign.bean.MessageSendVO;
import com.ccic.salessapp.common.core.web.HttpResult;

@FeignClient(value="CCIC-SALESAPP-PERFORMANCE")
public interface MessageNoticeFeignClient {
	
	 @PostMapping(value = "/salesResponsibility/sendMessage")
     HttpResult sendMessage(@RequestBody List<MessageSendVO> messageVo);

}
