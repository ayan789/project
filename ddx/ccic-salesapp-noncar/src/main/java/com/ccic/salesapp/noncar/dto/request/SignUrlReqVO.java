package com.ccic.salesapp.noncar.dto.request;

public class SignUrlReqVO extends DataVO{
	private String proposalNo;	//投保单号
	private String forwardDate;	//转发时间
	private String formulaCode; //方案代码

	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getForwardDate() {
		return forwardDate;
	}
	public void setForwardDate(String forwardDate) {
		this.forwardDate = forwardDate;
	}
	public String getFormulaCode() {
		return formulaCode;
	}
	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}
	
	
}
