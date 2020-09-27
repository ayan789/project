package com.ccic.salesapp.performance.domain.vo.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("业绩查询")
public class PerformanceRequest {
	@NotBlank(message="查询开始日期不能为空")
	@ApiModelProperty("查询开始日期")
	String dateStart;
	@NotBlank(message="查询结束日期不能为空")
	@ApiModelProperty("查询结束日期")
	String dateEnd;
	@NotBlank(message="用户代码不能为空")
	@ApiModelProperty("用户代码")
	String userCode;
	@ApiModelProperty("是否查询团队：1：查询团队；其他：查询个人")
	String team ="0";
	
}
