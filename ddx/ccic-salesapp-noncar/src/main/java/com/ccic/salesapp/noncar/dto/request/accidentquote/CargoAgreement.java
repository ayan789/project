package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;

@Data
public class CargoAgreement {
	private String cargoName;// 货物名称
	private String cargoType;// 货物类别
	private int declareDuration;// 申报周期
	private Double domesticMinimumPremium;// 国内最低保险费
	private Double estimateAnnualPremium;// 协议预计年保险费
	private Double estimateAnnualTraffic;// 协议预计年运量
	private int estimatedAnnualVolume;// 预计年运量
	private String flightVehicleModelCode;// 适用车型
	private Double importExportMinimumPremium;// 进出口最低保险费
	private String isAllManualUw;// 是否全部人工核保
	private String isFixedHolder;// 是否固定投保人
	private String isFixedInsured;// 是否固定被保险人
	private String isFixedSumInsured;// 是否固定保额
	private String isShieldPremiumRate;// 是否屏蔽保费费率
	private String isSpecialAgreementUser;// 是否特殊协议用户
	private String issueUserType;// 出单员类型
	private Double limitPerVehicle;// 每一运输工具赔偿限额
	private int maxShipAge;// 最大船龄
	private Double minimumPremium;// 协议最低保险费
	private Double minimumPremiumRate;// 协议最低保险费比例(小数形式存储的百分比)
	private String packagingType;// 包装方式/类型
	private Double perVehicleLimit;// 每车最大限额
	private Double premiumRate;// 费率
	private String proposalType;// 投保方式
	private String settlementModeCode;// 结算方式
	private Double sumInsuredPromptValue;// 保额提示值
	private int vehicleCount;// 车辆数
	private Long sequenceNumber;// 序号
	private Map <String,String> tempData;// 临时数据
}
