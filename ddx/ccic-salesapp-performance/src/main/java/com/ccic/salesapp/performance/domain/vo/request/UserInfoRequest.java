package com.ccic.salesapp.performance.domain.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息查询")
public class UserInfoRequest {

	@ApiModelProperty("人员工号")
	private String userCode;
}
