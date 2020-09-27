package com.ccic.salesapp.noncar.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ccic.salesapp.noncar.feign.bean.PayResultConfirmRequestVO;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.feign.FeignSupportConfig;

@FeignClient(name = "CCIC-SALESAPP-VEHICLE", configuration = FeignSupportConfig.class)
public interface PayResultFeignService {
	@RequestMapping(method = RequestMethod.POST, value = "/payment/payResultConfirm")
	HttpResult payResultConfirm(@RequestBody PayResultConfirmRequestVO payResultConfirmRequestVO);
}
