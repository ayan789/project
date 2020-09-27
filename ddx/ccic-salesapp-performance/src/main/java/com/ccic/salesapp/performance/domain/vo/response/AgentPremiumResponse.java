package com.ccic.salesapp.performance.domain.vo.response;

import com.ccic.salesapp.performance.domain.vo.dto.AgentPremium;


public class AgentPremiumResponse  {

	private AgentPremium agentPremiumD;//日业绩
	private AgentPremium agentPremiumM;//月业绩
	private AgentPremium agentPremiumY;//年业绩

	public AgentPremium getAgentPremiumD() {
		return agentPremiumD;
	}

	public void setAgentPremiumD(AgentPremium agentPremiumD) {
		this.agentPremiumD = agentPremiumD;
	}

	public AgentPremium getAgentPremiumM() {
		return agentPremiumM;
	}

	public void setAgentPremiumM(AgentPremium agentPremiumM) {
		this.agentPremiumM = agentPremiumM;
	}

	public AgentPremium getAgentPremiumY() {
		return agentPremiumY;
	}

	public void setAgentPremiumY(AgentPremium agentPremiumY) {
		this.agentPremiumY = agentPremiumY;
	}

}
