package com.ccic.salesapp.noncar.dto.order.group.sales;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PolicyCoverage {

	private String clauseCode;///	N	String	条款代码
	private String coverageCode;//	N	String	责任代码
	private String isMainCoverage;//	N	String	是否主险
	
	private BigDecimal duePremium;//责任保费
	private BigDecimal discountPremium;//折扣责任保费
	
	private BigDecimal sumInsured;//总保额

	
}
