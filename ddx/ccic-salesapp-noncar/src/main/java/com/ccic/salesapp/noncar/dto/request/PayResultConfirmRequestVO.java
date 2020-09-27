package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;



/**
 * 支付成功后支付状态回写
 * @author wangcw
 *
 */
public class PayResultConfirmRequestVO extends DataVO{

	private String proposalNo;//投保单号

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	
	
}
