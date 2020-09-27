package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class NonMotorDetail {
	private String buyDate;// 购买日期
	private String color;// 车身颜色
	private String idNo;// 身份证号
	private String insuredName;// 被保险人姓名
	private String motorNo;// 车牌号/车次号/电机号
	private String outputProductionDate;// 出厂日期
	private String productionManufacturer;// 生产厂家
	private Double purchasePrice;// 购置价
	private String registerLicenseNo;// 登记号牌
	private Double sumInsured;// 保险金额/保额
	private String vehicleCountCode;// 单辆/多辆
	private String vehicleModel;// 型号
	private String vin;// 车架号/VIN码
	private Long sequenceNumber;// 序号
	private Map<String, String> tempData;// 临时数据
}
