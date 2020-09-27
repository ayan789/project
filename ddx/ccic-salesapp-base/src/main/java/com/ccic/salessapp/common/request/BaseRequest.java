package com.ccic.salessapp.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public abstract class BaseRequest {
	@ApiModelProperty("业务员编号")
	private String userCode;

}
