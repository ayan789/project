package com.ccic.salesapp.performance.service;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salesapp.performance.domain.mapper.AgentPremiumMapper;
import com.ccic.salesapp.performance.domain.vo.dto.AgentHandlerInfo;
import com.ccic.salesapp.performance.domain.vo.request.AgentPerformanceRequest;
import com.ccic.salesapp.performance.domain.vo.response.AgentPerformanceResponse;
import com.ccic.salesapp.performance.repository.basedb.mapper.AgentHandlerMappper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AgentPremiumQueryService {

	@Autowired
	AgentHandlerMappper agentHandlerInfoMapper;

	@Autowired
	AgentPremiumMapper agentPremiumMapper;

	@Value("agentCode")
	String dagentCode;

	public HttpResult<AgentPerformanceResponse> agentPremiumQuery(AgentPerformanceRequest request ) {
		AgentPerformanceResponse response = agentPremiumMapper.getAgentPremiumByAgentCode(request.getAgentCode());
		if(response == null) {
			response = new AgentPerformanceResponse();
		}
		return HttpResult.success(response);
	}

	public int agentHandlerAndPremNum(AgentHandlerInfo agentHandlerInfo) {
		return agentHandlerInfoMapper.agentHandlerAndPremNum(agentHandlerInfo);
	}

	/*
	 * public HttpResult agentPremiumQuery(AgentPremiumRequest request,String
	 * userCode) { DAOBase dao = new DefaultDAO(); JsonVO json = new JsonVO();
	 * ResultVO result = new ResultVO(); //---------这块怎样获取用户信息 // LoginUser
	 * loginUser = jsonVO.getHead().getLoginUser(); AgentPremiumResponse
	 * agentPremiumVO = new AgentPremiumResponse();
	 * 
	 * 
	 * //SystemConfig cofig=new SystemConfig("system"); try { //String dagentCode =
	 * cofig.getResourceValue("agentCode"); String agentCode = null; if
	 * (!"agen".equals(userCode.substring(0, 4))) { agentCode =
	 * DESEncryptUtil.decrypt(request.getDesagentCode(), dagentCode);//解密代理人code
	 * }else{ AgentHandlerInfo
	 * agentHandlerInfo=agentHandlerInfoMapper.selectAgentHandlerInfoByUserCode(
	 * userCode); //AgentHandlerInfo agentHandlerInfo =(AgentHandlerInfo)
	 * dao.toView("agentHandlerInfo.selectAgentHandlerInfoByUserCode",
	 * loginUser.getUserCode()); agentCode = agentHandlerInfo.getAgentCode(); }
	 * 
	 * Map<String, String> map = new HashMap<String, String>(); map.put("agentCode",
	 * agentCode); map.put("month", request.getMonth()); if
	 * (!"agen".equals(userCode.substring(0, 4))) { //只查询归属经办人是当前业务员下的保费记录
	 * map.put("handler1Code", userCode); } //如果查询全部 if
	 * ("0".equals(request.getType())) { //查询日业绩 AgentPremium
	 * agentPremiumD=agentPremiumMapper.selectByYesterday(map); //AgentPremium
	 * agentPremiumD = (AgentPremium) dao.toView("AgentPremium.selectByYesterday",
	 * map);
	 * 
	 * //查询年业绩 AgentPremium agentPremiumY=agentPremiumMapper.selectByYear(map);
	 * //AgentPremium agentPremiumY = (AgentPremium)
	 * dao.toView("AgentPremium.selectByYear", map);
	 * agentPremiumVO.setAgentPremiumD(agentPremiumD);
	 * agentPremiumVO.setAgentPremiumY(agentPremiumY); } //查询月业绩 AgentPremium
	 * agentPremiumM=agentPremiumMapper.selectByMonth(map); //AgentPremium
	 * agentPremiumM = (AgentPremium) dao.toView("AgentPremium.selectByMonth", map);
	 * agentPremiumVO.setAgentPremiumM(agentPremiumM); HttpResult result=
	 * HttpResult.success(agentPremiumVO); result.setCode("1");
	 * result.setMsg("查询代理人业绩成功"); return result; json.setData(agentPremiumVO);
	 * result.setCode("1"); result.setMessage("查询代理人业绩成功"); } catch (Exception e) {
	 * return HttpResult.error(0,"查询代理人业绩失败，请稍后重试！"); log.error("查询业绩失败", e);
	 * result.setCode("0"); result.setMessage("查询代理人业绩失败，请稍后重试！"); }
	 * 
	 * }
	 */
}
