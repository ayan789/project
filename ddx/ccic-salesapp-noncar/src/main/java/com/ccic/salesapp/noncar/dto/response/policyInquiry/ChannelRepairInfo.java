package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.util.Map;

@Data
public class ChannelRepairInfo {
	// 修理厂名称
	private String repairChannelName;
	// 修理厂代码
	private String repairChannelCode;
	// 修理厂电话
	private String repairChannelTel;
	// 修理厂地址
	private String repairChannelAddress;
	//扩展字段
	private Map<String,String> extensionMap;


}
