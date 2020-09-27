package com.ccic.salesapp.performance.domain.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("薪酬密码修改")
public class SalaryPwdResponse {
	@ApiModelProperty("是否设置薪酬密码：0：未设置，1：已设置")
	String isSetPwd;
}
