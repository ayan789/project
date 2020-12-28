package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.PropertyTrialRequest;
import com.ccic.salesapp.noncar.dto.order.request.OrderRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

public interface DoBeforeRequestService {
	/**
	 * 意健险险发送下单请求前对报文进行处理
	 */
	void doBefore(AccidentQuoteRequest request,OrderRequest orderRequest) throws Exception;
	/**
	 * 财产险险发送下单请求前对报文进行处理
	 */
	void doBefore(PropertyTrialRequest request,OrderRequest orderRequest) throws Exception;
	
	/**
	 * 在存储订单信息前对报文进行处理
	 * @param orderRequest
	 * @throws Exception
	 */
	void beforeSaveData(OrderRequest orderRequest) throws Exception;
	
	/**
	 * 初始化请求报文后对报文进行处理
	 * @param request
	 * @param orderRequest
	 */
	void afterInitRequestBody(RequestBody request, OrderRequest orderRequest);
}
