package com.ccic.salesapp.performance.domain.vo.response;
import com.ccic.salesapp.performance.domain.vo.dto.DataVO;



public class QueryConfigResponseVO extends DataVO{
	private String agentCode;  //代理人代码
	private String isAutomatic;//是否自动提现：1，是  2，否
	private String frequency;  //自动提现的频率
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getIsAutomatic() {
		return isAutomatic;
	}
	public void setIsAutomatic(String isAutomatic) {
		this.isAutomatic = isAutomatic;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

}
