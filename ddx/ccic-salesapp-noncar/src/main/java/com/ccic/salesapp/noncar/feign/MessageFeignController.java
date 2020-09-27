//package com.ccic.salesapp.noncar.feign;
//
//import com.ccic.salesapp.noncar.feign.bean.MessageDto;
//import com.ccic.salessapp.common.core.web.HttpResult;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
//@FeignClient(value = "support-service")
//@Component
//public interface MessageFeignController {
//
//    @RequestMapping(value = "/api/message/saveMessage", method = RequestMethod.POST)
//    HttpResult saveMessage(@RequestBody List<MessageDto> messageDtos);
//}
