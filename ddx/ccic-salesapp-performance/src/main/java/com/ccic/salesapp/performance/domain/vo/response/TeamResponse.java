package com.ccic.salesapp.performance.domain.vo.response;

import java.util.ArrayList;
import java.util.List;

import com.ccic.salesapp.performance.domain.vo.dto.TeamRankItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("团队查询")
public class TeamResponse {
	
	@ApiModelProperty("机构名称")
	String comName;
	@ApiModelProperty("机构代码")
	String comCode;
	@ApiModelProperty("团队名称")
	String teamName;
	@ApiModelProperty("团队代码")
	String teamCode;
	@ApiModelProperty("渠道名称")
	String channelName;
	@ApiModelProperty("团队人数")
	String teamSize = "0";
	@ApiModelProperty("团队下职级列表")
	List<TeamRankItem> teamRankList = new ArrayList<TeamRankItem>();
}
