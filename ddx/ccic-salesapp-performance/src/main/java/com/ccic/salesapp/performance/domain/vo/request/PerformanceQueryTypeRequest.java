package com.ccic.salesapp.performance.domain.vo.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("业绩查询")
public class PerformanceQueryTypeRequest {
	@NotBlank(message="查询时间类型不能为空")
	@ApiModelProperty("查询时间类型：L:昨日、M:当月截止昨天、Q:当季截止昨天、Y:当年截止昨天")
	String queryType;
	@NotBlank(message="用户代码不能为空")
	@ApiModelProperty("用户代码")
	String userCode;
	@ApiModelProperty("是否查询团队：1：查询团队；其他：查询个人")
	String team = "0";
	
}
