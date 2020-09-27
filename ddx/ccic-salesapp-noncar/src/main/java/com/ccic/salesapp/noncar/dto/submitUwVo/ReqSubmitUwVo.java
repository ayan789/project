package com.ccic.salesapp.noncar.dto.submitUwVo;

import java.util.List;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

import lombok.Data;

@Data
public class ReqSubmitUwVo extends RequestBody {

	private String submissionNo;
	private ChannelOpInfo channelOpInfo;
	private List<SubmitProposalInfoList> submitProposalInfoList;
	
}
