package com.ccic.salesapp.performance.domain.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("图形验证码")
public class RandomNumResponse {
	@ApiModelProperty("图片")
	private String src;
	@ApiModelProperty("随机码")
	private String randomNum;
}
