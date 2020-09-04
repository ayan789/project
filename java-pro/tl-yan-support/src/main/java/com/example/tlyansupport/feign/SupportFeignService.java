package com.example.tlyansupport.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tlyansupport.MessageSendVO;

@FeignClient("tl-yan-customer")
@Component
public interface SupportFeignService {
	
	@PostMapping("/message/sendMessage")
    String sendMessage(@RequestBody List<MessageSendVO> messageSendVOS);
	
}
