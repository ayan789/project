package com.ccic.salesapp.performance.domain.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("伙伴代理人业绩")
public class PartnerAgentPerformanceResponse {
	
	@ApiModelProperty("当日总保费")
	String dayAmountTotal = "0";
	@ApiModelProperty("当日总件数")
	String dayNumTotal = "0";
	@ApiModelProperty("当日车险保费")
	String dayAmountCar = "0";
	@ApiModelProperty("当日车险件数")
	String dayNumCar = "0";
	@ApiModelProperty("当日财产险保费")
	String dayAmountPro = "0";
	@ApiModelProperty("当日财产险件数")
	String dayNumPro = "0";
	@ApiModelProperty("当日其他险保费")
	String dayAmountOther = "0";
	@ApiModelProperty("当日其他险件数")
	String dayNumOther = "0";
	@ApiModelProperty("当月总保费")
	String monthAmountTotal = "0";
	@ApiModelProperty("当月总件数")
	String monthNumTotal = "0";
	@ApiModelProperty("当月车险保费")
	String monthAmountCar = "0";
	@ApiModelProperty("当月车险件数")
	String monthNumCar = "0";
	@ApiModelProperty("当月财产险保费")
	String monthAmountPro = "0";
	@ApiModelProperty("当月财产险件数")
	String monthNumPro = "0";
	@ApiModelProperty("当月其他险保费")
	String monthAmountOther = "0";
	@ApiModelProperty("当月其他险件数")
	String monthNumOther = "0";
	@ApiModelProperty("当季总保费")
	String quarterAmountTotal = "0";
	@ApiModelProperty("当季总件数")
	String quarterNumTotal = "0";
	@ApiModelProperty("当季车险保费")
	String quarterAmountCar = "0";
	@ApiModelProperty("当季车险件数")
	String quarterNumCar = "0";
	@ApiModelProperty("当季财产险保费")
	String quarterAmountPro = "0";
	@ApiModelProperty("当季财产险件数")
	String quarterNumPro = "0";
	@ApiModelProperty("当季其他险保费")
	String quarterAmountOther = "0";
	@ApiModelProperty("当日其他险件数")
	String quarterNumOther = "0";
	
	@ApiModelProperty("当年总保费")
	String yearAmountTotal = "0";
	@ApiModelProperty("当年总件数")
	String yearNumTotal = "0";
	@ApiModelProperty("当年车险保费")
	String yearAmountCar = "0";
	@ApiModelProperty("当年车险件数")
	String yearNumCar = "0";
	@ApiModelProperty("当年财产险保费")
	String yearAmountPro = "0";
	@ApiModelProperty("当年财产险件数")
	String yearNumPro = "0";
	@ApiModelProperty("当年其他险保费")
	String yearAmountOther = "0";
	@ApiModelProperty("当年其他险件数")
	String yearNumOther = "0";
	
}
