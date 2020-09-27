package com.ccic.salesapp.noncar.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class NoncarPlanCt {

	private Long orderId;
	private String clauseCode;
	private String kindCode;
	private String duePremium;
	private BigDecimal insured;
	private Integer planId;
	private Integer planCoverageId;
	private String planCoverageName;
	private Integer parentPlanCoverageId;
	private Integer isNondeductible;
	private String isOptional;
	private Integer fixedInsure;
	private BigDecimal insureamount;
	private String productElementCode;
	private String coverageName;
	private String productName;
}
