package com.ccic.salesapp.performance.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ccic.salesapp.performance.controller.MessageSendVO;
import com.ccic.salesapp.performance.domain.vo.dto.MessageDto;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.feign.FeignSupportConfig;

@FeignClient(name = "CCIC-SALESAPP-SUPPORT", configuration = FeignSupportConfig.class)
public interface SupportFeignService {
    
	@RequestMapping(method = RequestMethod.POST, value = "/message/sendMessage")
    HttpResult sendMessage(@RequestBody List<MessageSendVO> messageSendVOS);
    
	@RequestMapping(method = RequestMethod.POST, value = "/message/saveMessage")
	HttpResult saveMessage(@RequestBody List<MessageDto> messageDtos);

   
}
