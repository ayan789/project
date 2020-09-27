package com.ccic.salesapp.noncar.dto.order.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("订单详情请求")
public class OrderDetailRequest {

	@ApiModelProperty("订单号")
	String orderNo;
	@ApiModelProperty("订单id")
	Long orderId;
	@ApiModelProperty("是否动态表单订单")
	String isMetaData;
	
}
