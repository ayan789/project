package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class PolicyCoverageExtension {
	
	private BigDecimal deductible;//车辆损失保险免赔额
	private BigDecimal deductibleRate;//车辆损失保险免赔额系数
	private Integer passagerSeatNumber;//乘客座位数
	private BigDecimal avgSumInsured;//乘客每座保额
	private String glassType;//玻璃单独破损险玻璃种类（国产\进口）
	private BigDecimal dayPayInsured;//修理期间费用补偿险日赔偿金
	private Long payDays;//修理期间费用补偿险天数
	private String repairFactory;//指定修理厂险（国产\进口）
	private Map<String,String> extensionMap;
}
