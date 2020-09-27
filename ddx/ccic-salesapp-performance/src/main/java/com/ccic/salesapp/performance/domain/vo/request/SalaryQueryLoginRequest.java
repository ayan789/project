package com.ccic.salesapp.performance.domain.vo.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("薪酬查询登录")
public class SalaryQueryLoginRequest {
	@NotBlank(message="用户代码不能为空")
	@ApiModelProperty("用户代码")
	String userCode;
	@NotBlank(message="薪酬查询密码不能为空")
	@ApiModelProperty("薪酬查询密码")
	String salaryPassword;
	
}
