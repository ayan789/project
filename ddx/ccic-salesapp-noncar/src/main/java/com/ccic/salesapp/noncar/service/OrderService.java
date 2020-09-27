package com.ccic.salesapp.noncar.service;

import java.util.List;

import com.ccic.salesapp.noncar.dto.order.request.OrderDetailRequest;
import com.ccic.salesapp.noncar.dto.order.request.OrderRequest;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanStrategy;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface OrderService {

	/**
	 * 执行下单 提交核心 
	 * @param orderRequest
	 * @return
	 */
	HttpResult placeOrder(OrderRequest orderRequest);
	
	/**
	 * 查询订单列表
	 * @param orderRequest
	 * @return
	 */
	HttpResult orderList(StoreQueryListRequestVO orderRequest);

	/**
	 * 获取需要上传但未上传的影像名称列表
	 * @param orderId
	 * @return 缺失的影像名称列表
	 */
	List<String> getNotUploadImages(Long orderId);

	/**
	 * 是否见费出单
	 * @param planId	方案批次号
	 * @param comCode	出单机构代码
	 * @return false : 非见费； true 见费
	 */
	boolean isBillAfterPay(Long planId, String comCode);
	
	/***
	 * 读取订单详情
	 * @param request
	 * @return
	 */
	HttpResult orderDetail(OrderDetailRequest request);

	/**
	 * 订单核保
	 * @param insureNo
	 * @return
	 */
	HttpResult underwriting(String insureNo);

}
