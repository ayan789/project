package com.ccic.salesapp.performance.domain.vo.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("团队成员查询")
public class TeamRequest {
	@NotBlank(message="用户代码不能为空")
	@ApiModelProperty("用户代码")
	String userCode;
}
