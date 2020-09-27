package com.ccic.salesapp.performance.domain.vo.request;

import javax.validation.constraints.NotBlank;

import com.ccic.salesapp.performance.domain.vo.dto.Captcha;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("薪酬密码修改发送短信验证码")
public class SalarySMSRequest {
	@NotBlank(message="用户代码不能为空")
	@ApiModelProperty("用户代码")
	String userCode;
	
	@NotBlank(message="机构代码不能为空")
	@ApiModelProperty("机构代码")
	String comCode;
	
	@NotBlank(message="图形验证码不能为空")
	@ApiModelProperty("图形验证码")
	Captcha captcha;
}
