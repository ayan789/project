//package com.ccic.salesapp.noncar.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.ccic.salessapp.common.core.web.HttpResult;
//
//
//
//@FeignClient(value ="CCIC-SALESAPP-SUPPORT",url= "http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst")
//@Component
//public interface UploadImageFeignService {
//	@RequestMapping(value = "image/uploadImage", method = RequestMethod.POST)
//	HttpResult uploadImage(@RequestBody FileRequest fileRequest);
//}
