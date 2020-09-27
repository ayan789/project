package com.ccic.salesapp.performance.domain.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("业绩查询")
public class PerformanceResponse {
	
	@ApiModelProperty("实收总保费")
	String payreffee = "0";
	
	@ApiModelProperty("实收车险保费")
	String payreffeeCar = "0";
	
	@ApiModelProperty("实收财产险保费")
	String payreffeeNoCar = "0";
	
	@ApiModelProperty("实收意外险保费")
	String payreffeeAcc = "0";
	
	@ApiModelProperty("实收健康险保费")
	String payreffeeHea = "0";
	
	@ApiModelProperty("实收总件数")
	String numPayreffee = "0";
	
	@ApiModelProperty("实收车险件数")
	String numPayreffeeCar = "0";
	
	@ApiModelProperty("实收财产险件数")
	String numPayreffeeNoCar = "0";
	
	@ApiModelProperty("实收意外险件数")
	String numPayreffeeAcc = "0";
	
	@ApiModelProperty("实收健康险件数")
	String numPayreffeeHea = "0";
	
}
