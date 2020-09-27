package com.ccic.salesapp.performance.domain.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("职级考核")
public class CheckRankResponse {
	@ApiModelProperty("维持标准目标保费")
	String keepStandard = "0";
	@ApiModelProperty("晋升标准目标保费")
	String promotionStandard  = "0";
	@ApiModelProperty("实际保费")
	String real  = "0";
	@ApiModelProperty("晋升实际保费")
	String promotionReal  = "0";
	@ApiModelProperty("还需完成单数")
	String num  = "0";
	@ApiModelProperty("日均单数")
	String perDayNum  = "0";
}
