package com.ccic.salesapp.noncar.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.order.request.OrderDetailRequest;
import com.ccic.salesapp.noncar.dto.order.request.OrderRequest;
import com.ccic.salesapp.noncar.dto.request.RealNameStatusVo;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.service.OrderService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.ValidationUtils;
import com.netflix.discovery.converters.Auto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "产品工厂用下单", description = "产品工厂用下单", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "order")
@Slf4j
public class OrderCtl {
	
	@Autowired
	OrderService orderService;
	
    
    @PostMapping(value = "placeOrder")
    @ApiOperation(value = "下单", notes = "下单", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult placeOrder(@RequestBody OrderRequest orderRequest) {
    	//校验必要参数
    	ValidationUtils.validate(orderRequest);
    	//存储业务数据信息
    	
    	//存储业务日志
    	
    	//整合下单报文
    	
    	//下单试算
    	
    	//提交核保
     return orderService.placeOrder(orderRequest);
    }


    @PostMapping(value = "orderList")
    @ApiOperation(value = "订单列表", notes = "订单列表", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult orderList(@RequestBody StoreQueryListRequestVO orderRequest) {
    	
     return orderService.orderList(orderRequest);
    }
    
    
    @PostMapping(value = "orderDetail")
    @ApiOperation(value = "订单详情", notes = "订单详情", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult orderDetail(@RequestBody OrderDetailRequest request) {
    	
     return orderService.orderDetail(request);
    }
    
    @PostMapping(value = "realNameStatus")
    @ApiOperation(value = "订单实名状态", notes = "订单实名状态", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult realNameStatus(@RequestBody RealNameStatusVo realNameStatusVo) {
      return orderService.realNameStatus(realNameStatusVo.getOrderId());
    }
    
    
    
}
