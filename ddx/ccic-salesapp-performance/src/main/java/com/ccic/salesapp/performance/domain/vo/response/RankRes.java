package com.ccic.salesapp.performance.domain.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RankRes {
	@ApiModelProperty("业务员姓名")
	private String userName;
	@ApiModelProperty("团队名称")
	private String teamName;
	@ApiModelProperty("与昨日排名")
	private Integer pm;
	@ApiModelProperty("今日排名")
	private Integer jrpm;
}
