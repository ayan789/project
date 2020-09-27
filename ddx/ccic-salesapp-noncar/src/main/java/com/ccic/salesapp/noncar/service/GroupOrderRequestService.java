package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.order.group.sales.GroupRequest;
import com.ccic.salesapp.noncar.dto.order.group.sales.GroupResponse;

public interface GroupOrderRequestService {

	GroupResponse requestPrice(GroupRequest request, String bussNo) throws Exception;

	GroupResponse requestOrder(GroupRequest request, String bussNo) throws Exception;

}
