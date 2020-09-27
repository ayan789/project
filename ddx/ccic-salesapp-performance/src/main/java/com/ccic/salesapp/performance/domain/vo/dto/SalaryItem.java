package com.ccic.salesapp.performance.domain.vo.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("薪酬项")
public class SalaryItem {
	@ApiModelProperty("薪酬项目名称")
	public String salaryItem;
	@ApiModelProperty("薪酬")
	public String salary;
	
	@ApiModelProperty("薪酬项目名称")
	public String salaryName;
	@ApiModelProperty("薪酬")
	public BigDecimal salaryResul;
}
