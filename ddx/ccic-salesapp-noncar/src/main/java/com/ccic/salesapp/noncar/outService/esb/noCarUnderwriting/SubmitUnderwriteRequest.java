package com.ccic.salesapp.noncar.outService.esb.noCarUnderwriting;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

 

public class SubmitUnderwriteRequest extends RequestBody{
	private String proposalNo;// Ͷ������

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	
	

}
