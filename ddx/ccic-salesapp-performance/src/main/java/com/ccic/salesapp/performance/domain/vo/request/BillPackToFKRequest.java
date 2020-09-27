package com.ccic.salesapp.performance.domain.vo.request;
import java.util.List;


public class BillPackToFKRequest {
	private String userCode;//业务员工号
	private String agentCode;
	private String checkAllFlag;//是否全部提现 1-是，0否
	private List <String> policyNOList;//提现保单集合

	
	public String getCheckAllFlag() {
		return checkAllFlag;
	}

	public void setCheckAllFlag(String checkAllFlag) {
		this.checkAllFlag = checkAllFlag;
	}

	public List<String> getPolicyNOList() {
		return policyNOList;
	}

	public void setPolicyNOList(List<String> policyNOList) {
		this.policyNOList = policyNOList;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	
	

	
}
