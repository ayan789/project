package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class Voyage {
	private Double addPremiumRate;// 加收费率(DC:是否是加收老船费率？运输信息)
	private String claimAddress;// 赔付地点
	private String conveyanceInfo;// 运输工具信息（根据运输方式不同，显示不同名称）
	private String departureAddress;// 起运地址
	private String departureCityCode;// 起运城市
	private String departureContinentCode;// 起运洲
	private String departureCountryCode;// 起运国家
	private Date departureDate;// 起运日期
	private String departurePortCode;// 起运港口
	private String departureProvinceCode;// 起运省
	private String destinationAddress;// 目的地址
	private String destinationCityCode;// 目的城市
	private String destinationContinentCode;// 目的洲
	private String destinationCountryCode;// 目的国家
	private String destinationPortCode;// 目的港口
	private String destinationProvinceCode;// 目的省
	private String flightNo;// 航班号
	private String flightProduct;// 适用产品
	private String inspectAgentName;// 检验代理人
	private String insuredCondition;// 承保条件(DC:是否是货物承保条件？协议上，标的责任模块)
	private String motorNo;// 车牌号/车次号
	private String parcelNo;// 邮包号
	private int totalTonnage;// 总吨位
	private String trackingNo;// 运单号
	private String transportType;// 运输方式
	private String vehicleLicenseNo;// 车牌号/车次号
	private int vesselAge;// 船龄
	private String vesselNameCn;// 船名中文
	private String vesselNameEn;// 船名英文
	private String voyageNo;// 航次号/车次号
	private String voyageType;// 航程类型（代码表：起运/目的/两者兼有）
	private Long sequenceNumber;// 序号
    private Map <String,String> tempData;// 临时数据
}
