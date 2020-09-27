package com.ccic.salesapp.noncar.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("发送短信")
public class SendMessageRequest {
	@ApiModelProperty("电话号码")
	private String mobile;//电话号码
	@ApiModelProperty("机构代码")
	private String comCode;//机构代码
	@ApiModelProperty("短信内容")
	private String message;//短信内容
	@ApiModelProperty("用户代码")
	private String userCode;//用户代码
}
