package com.ccic.salesapp.noncar.dto.response;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

import lombok.Data;

@Data
public class VerifyEpolicyResponse extends ResponseBody{
	private String ePolicyName;//凭证名称
	private String businessNo;//业务号
	private String imageId;//影像ID
	private String businessLine;//一级险类
}
