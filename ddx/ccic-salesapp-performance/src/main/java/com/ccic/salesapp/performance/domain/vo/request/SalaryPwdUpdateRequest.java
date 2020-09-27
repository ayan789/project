package com.ccic.salesapp.performance.domain.vo.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("薪酬密码修改")
public class SalaryPwdUpdateRequest {
	@NotBlank(message="用户代码不能为空")
	@ApiModelProperty("用户代码")
	String userCode;
	@NotBlank(message="新密码不能为空")
	@ApiModelProperty("新密码")
	String newPassword;	//新密码
	@NotBlank(message="短信验证码不能为空")
	@ApiModelProperty("短信验证码")
	String smsCode;		//短信验证码
	@NotBlank(message="随机验证码不能为空")
	@ApiModelProperty("随机验证码")
	String randomNum;	//随机验证码
	
}
