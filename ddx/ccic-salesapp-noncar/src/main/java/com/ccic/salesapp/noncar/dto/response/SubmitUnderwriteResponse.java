package com.ccic.salesapp.noncar.dto.response;

import java.util.List;

import com.ccic.salesapp.noncar.repository.basedb.po.UWResult;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

import lombok.Data;

@Data
public class SubmitUnderwriteResponse  extends ResponseBody{
//	private String msg;//提示信息
//	private List<UWResult> uwResult;//核保结果	
	private String uwType;//结果类型
//	private String uwLevel;//核保级别
//	private List<String> proposalNos;
	private String policyNo;
	
}
