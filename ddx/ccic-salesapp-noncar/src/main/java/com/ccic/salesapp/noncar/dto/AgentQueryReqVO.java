package com.ccic.salesapp.noncar.dto;

import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesAgentMsgDto;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesAgreementMsgDto;


public class AgentQueryReqVO  {
	private SalesAgentMsgDto salesAgentMsg;
	private SalesAgreementMsgDto salesAgreementMsg;
	public SalesAgentMsgDto getSalesAgentMsg() {
		return salesAgentMsg;
	}
	public void setSalesAgentMsg(SalesAgentMsgDto salesAgentMsg) {
		this.salesAgentMsg = salesAgentMsg;
	}
	public SalesAgreementMsgDto getSalesAgreementMsg() {
		return salesAgreementMsg;
	}
	public void setSalesAgreementMsg(SalesAgreementMsgDto salesAgreementMsg) {
		this.salesAgreementMsg = salesAgreementMsg;
	}
	
}
