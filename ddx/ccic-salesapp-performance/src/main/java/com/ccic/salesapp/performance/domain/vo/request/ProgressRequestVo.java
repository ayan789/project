package com.ccic.salesapp.performance.domain.vo.request;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("达成进度")
public class ProgressRequestVo {
	@ApiModelProperty("业务员工号")
	private String userCode;
	@ApiModelProperty("团队编号")
	private String teamCode;
	@ApiModelProperty("责任书编号")
	private String responsibilityType;
	@ApiModelProperty("月：M 季度：Q 年：Y")
	private String timeFlag;

	private String isYear;
}
