package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.PropertyTrialRequest;
import com.ccic.salesapp.noncar.dto.order.request.OrderRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;

public interface DoBeforeRequestService {
	/**
	 * 意健险险发送下单请求前对报文进行处理
	 */
	void doBefore(AccidentQuoteRequest request,OrderRequest orderRequest) throws Exception;
	/**
	 * 财产险险发送下单请求前对报文进行处理
	 */
	void doBefore(PropertyTrialRequest request,OrderRequest orderRequest) throws Exception;
}
