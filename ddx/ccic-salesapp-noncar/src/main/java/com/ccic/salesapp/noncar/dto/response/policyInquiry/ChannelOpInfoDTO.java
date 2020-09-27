package com.ccic.salesapp.noncar.dto.response.policyInquiry;


import java.util.Map;

import lombok.Data;
@Data
public class ChannelOpInfoDTO {
	// 渠道代码
	private String channelCode;
	// 渠道机构代码
	private String channelComCode;
	// 渠道机构名称
	private String channelComName;
	// 渠道名称
	private String channelName;
	// 渠道产品代码
	private String channelProductCode;
	// 渠道交易流水号
	private String channelSeqNo;
	// 单渠道
	private String makeChannel;
	// 渠道操作员代码
	private String operatorCode;
	// 地区代码
	private String regionCode;
	// 第三方业务员姓名
	private String thirdPartyHandlerName;
	// 第三方业务员工号
	private String thirdPartyHandlerNo;
	// 渠道交易代码
	private String trxCode;
	// 渠道交易日期
	private String trxDate;
	//项目编码
	private String projectNo;
	//项目名称
	private String projectName;
	//第三方备案代码
	private String thirdPartyRegCode;
	//销售人员姓名
	private String salesmanName;
	//扩展字段
	private Map<String,String> extensionMap;

}
