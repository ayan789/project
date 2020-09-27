package com.ccic.salesapp.noncar.dto.request;

import java.util.List;

import com.ccic.salesapp.noncar.dto.ExpandDTO;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

import lombok.Data;

@Data
public class VerifyEpolicyRequest extends RequestBody{
	private String certiType;//业务类型	
	private String certiNo;//业务号	
	private String insuredFlag;//关系人标志
	private String insuredName;//关系人名称
	private String identifyNumber;//关系人证件号码
	private String visaCheckCode;//单证校验码
	private String channelType;//内外渠道标志
    private List<ExpandDTO> expandList;//扩张信息列表
}
