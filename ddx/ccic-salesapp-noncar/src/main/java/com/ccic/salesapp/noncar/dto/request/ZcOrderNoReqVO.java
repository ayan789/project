package com.ccic.salesapp.noncar.dto.request;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@Data
@ApiModel("获取投保暂存信息request")
public class ZcOrderNoReqVO{
	
	@ApiModelProperty("订单号")
	private String orderNo;
	
}
