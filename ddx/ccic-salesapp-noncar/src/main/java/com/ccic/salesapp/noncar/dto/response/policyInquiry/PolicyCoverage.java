package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class PolicyCoverage {
	
	private String productElementCode;//险种代码
	private String coverageName;//险种名称
	private BigDecimal benchRiskPremium;//基准纯风险保费
	private BigDecimal sumInsured;//保额
	private BigDecimal avgSumInsured;//乘客每座保额
	private PolicyCoverageExtension policyCoverageExtension;//保单险种扩展信息
	private BigDecimal totalFloatRate;//总浮动系数
	private String isNonDeductible;//是否不计免赔
	private BigDecimal duePremium;//应收保费
	private BigDecimal stdPremium;//标准保费
	private BigDecimal deductiblePremium;//不计免赔保费
	private BigDecimal premiumRate;//费率
	private String clauseName;//条款名称
	private String clauseCode;//条款代码
	private String isMainRisk;//是否主险
	private String isIncludedInSumInsured;//是否计入保额
    private BigDecimal shortRate;//短期费率
    private String deductibleCode;//不计免赔代码
    private String absDeductibleCode;//绝对免赔率特约险代码
    private BigDecimal absDeductStdPremium;//绝对免赔率特约险标准保费
    private BigDecimal absDeductDuePremium;//绝对免赔率特约险应收保费
    private BigDecimal absDeductPremiumRate;//绝对免赔率特约险费率
	private Map<String,String> extensionMap;
}
