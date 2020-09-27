package com.ccic.salesapp.performance.domain.vo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("排名")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RankResponseVo {
	@ApiModelProperty("业务员姓名")
	private String userName;
	@ApiModelProperty("团队名称")
	private String teamName;
	@ApiModelProperty("与昨日排名")
	private int pm;
	@ApiModelProperty("今日排名")
	private int jrpm;
	@ApiModelProperty("排名列表")
	private List<RankRes> RankDetail;
	
}
