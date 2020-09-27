package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.order.request.GroupOrderRequest;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface GroupOrderService {

	HttpResult placeOrder(GroupOrderRequest request);

}
