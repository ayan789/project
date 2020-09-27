package com.ccic.salesapp.noncar.outService.esb.noCarUnderwriting;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

 

public class SubmitUnderwriteResponse extends ResponseBody{
	private String policyNo;//������
	private String proposalNo;//Ͷ������	
	private String uwStatus;//�˱�״̬
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getUwStatus() {
		return uwStatus;
	}
	public void setUwStatus(String uwStatus) {
		this.uwStatus = uwStatus;
	}
	

}
