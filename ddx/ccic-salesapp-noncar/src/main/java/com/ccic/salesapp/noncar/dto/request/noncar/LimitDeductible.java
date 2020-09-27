package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

import lombok.Data;

@Data
public class LimitDeductible {
	private String isCustomLimitDeductible;// 是否自定义名称，选果选择“其他”为Y
	private String limitDeductibleCode;// 限额免赔代码，来自限额免赔库
	private String limitDeductibleLevel;// 限额免赔层级（保单、标的、条款、责任）
	private String limitDeductibleName;// 限额免赔名称，来自限额免赔库，如果选择“其他”，也可以手工输入
	private String limitDeductibleType;// 限额免赔类型（限额/免赔），来自限额免赔库
	private String limitDeductibleUnit;// 限额免赔单位
	private Double limitDeductibleValue;// 限额免赔值，如果是Rate类型，一律存小数，不存百分比/千分比等
	private Long sequenceNumber;// 序号
	private Map<String, String> tempData;// 临时数据
}
