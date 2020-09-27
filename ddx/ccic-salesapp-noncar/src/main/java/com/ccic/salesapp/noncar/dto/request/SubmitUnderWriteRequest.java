package com.ccic.salesapp.noncar.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubmitUnderWriteRequest {
	@NotBlank(message="投保单号不能为空")
	@ApiModelProperty("投保单号")
	private String proposalNo;		//投保单号
	@ApiModelProperty("险种")
	private String riskCode;	//险种
	@NotBlank(message="订单号不能为空")
	@ApiModelProperty("订单号")
	private String	orderNo;	//订单号
}
