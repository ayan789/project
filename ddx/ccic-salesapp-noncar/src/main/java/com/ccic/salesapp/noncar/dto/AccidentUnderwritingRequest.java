package com.ccic.salesapp.noncar.dto;

import com.ccic.salesapp.noncar.dto.request.accidentquote.ChannelOpInfo;
import com.ccic.salesapp.noncar.dto.request.accidentquote.Policy;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

import lombok.Data;
@Data
public class AccidentUnderwritingRequest extends RequestBody {
	private Policy policyDTO; //保单信息
	private ChannelOpInfo channelOpInfoDTO; //渠道信息
}
