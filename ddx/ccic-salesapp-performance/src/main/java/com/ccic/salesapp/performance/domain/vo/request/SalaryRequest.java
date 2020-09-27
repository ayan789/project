package com.ccic.salesapp.performance.domain.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("薪酬查询")
public class SalaryRequest {
//	@ApiModelProperty("薪酬查询密码")
//	String salaryPassword;
//	@ApiModelProperty("查询月份")
//	String calcMonth;
//	@ApiModelProperty("查整年标识")
//	String year;
	@ApiModelProperty("查业务员工号")
	String userCode;
	@ApiModelProperty("查询区间")
	String evaluationMonthRange;
}
