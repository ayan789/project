package com.ccic.salesapp.noncar.dto.order.group.sales;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;

import lombok.Data;

@Data
public class GroupRequest {
	private RequestHead requestHead;
	private RequestBody requestBody;
}
