package com.ccic.salesapp.noncar.dto.order.group.sales;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;

import lombok.Data;

@Data
public class GroupResponse {

	private ResponseHead responseHead;
	
	private ResponseBody responseBody;
}
