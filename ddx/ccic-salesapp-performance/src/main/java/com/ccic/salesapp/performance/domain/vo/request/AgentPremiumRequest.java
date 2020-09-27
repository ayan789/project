package com.ccic.salesapp.performance.domain.vo.request;

public class AgentPremiumRequest  {

	private String month;// 按月查询
	private String type;// 0年月日都查询 1只查询月份
	private String desagentCode;	//加密的代理人代码
	

	
	public String getDesagentCode() {
		return desagentCode;
	}

	public void setDesagentCode(String desagentCode) {
		this.desagentCode = desagentCode;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
