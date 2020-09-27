package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;



public class OrderDetailsInquiryRequestVO extends DataVO{

	private String orderNo;
	private String proposalNo;
	private String policyNo;
	private String quotationType;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getQuotationType() {
		return quotationType;
	}
	public void setQuotationType(String quotationType) {
		this.quotationType = quotationType;
	}
	
	
}
