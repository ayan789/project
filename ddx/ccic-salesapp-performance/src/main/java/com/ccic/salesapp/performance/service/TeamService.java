package com.ccic.salesapp.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.performance.domain.mapper.TeamMapper;
import com.ccic.salesapp.performance.domain.vo.dto.TeamRankItem;
import com.ccic.salesapp.performance.domain.vo.dto.TeamUser;
import com.ccic.salesapp.performance.domain.vo.request.TeamRequest;
import com.ccic.salesapp.performance.domain.vo.response.TeamResponse;
import com.ccic.salessapp.common.core.web.HttpResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TeamService {
	
	@Autowired
	TeamMapper teamMapper;

	/***
	 * 返回TeamResponse,团队信息
	 * @return
	 */
	public HttpResult<TeamResponse> teamQuery(TeamRequest request){
		TeamResponse response = new TeamResponse();
		try {
			response = teamMapper.selectTeamInfoByUserCode(request.getUserCode());
			List<TeamRankItem> list = teamMapper.selectTeamRankUsersByUserCode(request.getUserCode());
			for (TeamRankItem teamRankItem : list) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("userCode",request.getUserCode());
				map.put("rankCode",teamRankItem.getRankCode());
				List<TeamUser> teamUserList = teamMapper.selectTeamUserByRankCode(map);
				teamRankItem.setTeamUserList(teamUserList);
			}
			response.setTeamRankList(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return HttpResult.error(0, "获取团队成员保费信息异常"+e.getMessage());
		}
		return HttpResult.success(response);
	}

}
