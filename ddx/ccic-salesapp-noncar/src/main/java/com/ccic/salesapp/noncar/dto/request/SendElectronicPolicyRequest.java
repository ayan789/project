package com.ccic.salesapp.noncar.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("发送电子保单")
public class SendElectronicPolicyRequest {
	@ApiModelProperty("保单号")
	private String policyNo;//保单号
	@ApiModelProperty("发送渠道：1-邮箱  2-短信")
	private String type;//1-邮箱  2-短信
	@ApiModelProperty("邮箱")
	private String email;//邮箱
	@ApiModelProperty("手机号")
	private String mobile;//手机号
	@ApiModelProperty("用户代码")
	private String userCode;//用户代码
	@ApiModelProperty("机构代码")
	private String comCode;//机构代码
}
