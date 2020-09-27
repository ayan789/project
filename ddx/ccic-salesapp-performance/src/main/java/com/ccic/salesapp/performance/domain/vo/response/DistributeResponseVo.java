package com.ccic.salesapp.performance.domain.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("总保费分配")
public class DistributeResponseVo {
	@ApiModelProperty("一月目标总保费")
	private String janTotalPremium;
	@ApiModelProperty("二月目标总保费")
	private String febTotalPremium;
	@ApiModelProperty("三月目标总保费")
	private String marTotalPremium;
	@ApiModelProperty("四月目标总保费")
	private String aprTotalPremium;
	@ApiModelProperty("五月目标总保费")
	private String mayTotalPremium;
	@ApiModelProperty("六月目标总保费")
	private String junTotalPremium;
	@ApiModelProperty("七月目标总保费")
	private String julTotalPremium;
	@ApiModelProperty("八月目标总保费")
	private String augTotalPremium;
	@ApiModelProperty("九月目标总保费")
	private String sepTotalPremium;
	@ApiModelProperty("十月目标总保费")
	private String octTotalPremium;
	@ApiModelProperty("十一月目标总保费")
	private String novTotalPremium;
	@ApiModelProperty("十二月目标总保费")
	private String decTotalPremium;
	
}
