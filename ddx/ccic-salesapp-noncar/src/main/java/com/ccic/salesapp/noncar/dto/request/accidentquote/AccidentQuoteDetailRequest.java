package com.ccic.salesapp.noncar.dto.request.accidentquote;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;



public class AccidentQuoteDetailRequest extends RequestBody {

	private ChannelOpInfo channelOpInfo;
	private PolicyMainInfo policyMainInfo;
	public ChannelOpInfo getChannelOpInfo() {
		return channelOpInfo;
	}
	public void setChannelOpInfo(ChannelOpInfo channelOpInfo) {
		this.channelOpInfo = channelOpInfo;
	}
	public PolicyMainInfo getPolicyMainInfo() {
		return policyMainInfo;
	}
	public void setPolicyMainInfo(PolicyMainInfo policyMainInfo) {
		this.policyMainInfo = policyMainInfo;
	}
}
