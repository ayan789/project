package com.ccic.salesapp.performance.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.performance.domain.vo.dto.TeamRankItem;
import com.ccic.salesapp.performance.domain.vo.dto.TeamUser;
import com.ccic.salesapp.performance.domain.vo.response.TeamResponse;

@Mapper
public interface TeamMapper {

	List<TeamRankItem> selectTeamRankUsersByUserCode(String userCode);
	
	List<TeamUser> selectTeamUserByRankCode(Map map);
	
	TeamResponse selectTeamInfoByUserCode(String userCode);
}
