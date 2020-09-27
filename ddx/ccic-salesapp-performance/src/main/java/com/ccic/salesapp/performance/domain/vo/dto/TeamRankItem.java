package com.ccic.salesapp.performance.domain.vo.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("团队下职级")
public class TeamRankItem {
	public String rankCode;
	public String rankName;
	public List<TeamUser> teamUserList;
}
