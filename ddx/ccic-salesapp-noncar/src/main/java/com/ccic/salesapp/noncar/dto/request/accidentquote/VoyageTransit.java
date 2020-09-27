package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class VoyageTransit {
	private String conveyanceInfo;// 运输工具信息（船名/车牌号/车次号/航班号）
	private int totalTonnage;// 总吨位
	private String trackingNo;// 运单号
	private String transitAddress;// 转运地址
	private String transitCityCode;// 转运城市
	private String transitCountryCode;// 转运国家
	private String transitPortCode;// 转运港口
	private String transportType;// 运输方式
	private int vesselAge;// 船龄
	private Long sequenceNumber;// 序号
	private Map<String, String> tempData;// 临时数据
}
