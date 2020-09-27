package com.ccic.salessapp.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public abstract class PageQueryRequest extends BaseRequest{
	@ApiModelProperty("页码大小")
	private int pageSize=10;
	@ApiModelProperty("页码")
	private int pageNum=1;
}
