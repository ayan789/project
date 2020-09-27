package com.ccic.salesapp.noncar.dto.order;

import com.ccic.salesapp.noncar.dto.response.PropertyTrialResponse;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;

import lombok.Data;

@Data
public class PropertyTrialOrderResponse {

	ResponseHead responseHead;
	
	PropertyTrialResponse responseBody;
}
