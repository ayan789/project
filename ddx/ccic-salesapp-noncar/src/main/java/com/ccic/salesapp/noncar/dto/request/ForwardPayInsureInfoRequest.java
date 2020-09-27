package com.ccic.salesapp.noncar.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ForwardPayInsureInfoRequest {
	@NotBlank(message="投保单号不能为空")
	@ApiModelProperty("投保单号")
	private String proposalNo;	//投保单号
	
	@NotBlank(message="转发时间不能为空")
	@ApiModelProperty("转发时间")
	private String forwardDate;	//转发时间
}
