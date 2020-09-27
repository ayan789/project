package com.ccic.salesapp.noncar.dto.request.noncar;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class PolicyRiskOrderCar {
	@JsonProperty("Id")
    private Long id;
	@JsonProperty("PlanCoverageId")
	private Long planCoverageId;
	@JsonProperty("PlanSharedId")
	private Long planSharedId;
	@JsonProperty("PlanCoverageName")
	private String planCoverageName;
	@JsonProperty("IsNonDeductible")
	private String isNondeductible;
	@JsonProperty("ParentPlanCoverageId")
	private Long parentPlanCoverageId;
	@JsonProperty("IsOptional")
	private String isOptional;
	@JsonProperty("IsSelectedByDefault")
	private String isSelectedByDefault;
	@JsonProperty("CoverageType")
	private String CoverageType;
	
	@JsonProperty("GroupType")
	private String SharedGroupType;
	
	
	@JsonProperty("PlanDefId")
	private Long planDefId;
	@JsonProperty("ProductElementCode")
	private String productElementCode;
	@JsonProperty("PremiumRate")
	private Double premiumRate;
	@JsonProperty("SumInsured")
	private Double sumInsured;
	
	@JsonProperty("UnitPremium")
	private Double unitPremium;
	@JsonProperty("UnitSumInsured")
	private Double unitSumInsured;
	
	@JsonProperty("TempData")
	private Map<String, String> tempData;
	@JsonProperty("PolicyCoverageList")
	private List<PolicyRiskOrderCar> policyCoverageList;
	@JsonProperty("AdjustedPremium")
	private Double adjustedPremium;
	@JsonProperty("BusinessObjectId")
	private Long businessObjectId;
	@JsonProperty("CoverageType")
	private String coverageType;
	@JsonProperty("IsMainCoverage")
	private String isMainCoverage;
	@JsonProperty("DirectParentType")
	private String directParentType;
	@JsonProperty("ProductElementName")
	private String productElementName;
	


	@JsonProperty("PlanCtDetailId")
    private Long planCtdetailId;

	@JsonProperty("ClauseCode")
    private String clauseCode;

	@JsonProperty("IsMainCoverage")
    private String clauseProperty;

	@JsonProperty("IfCalTotalSum")
    private String ifCaltotalSum;

	@JsonProperty("CtNecessary")
    private String ctNecessary;

	@JsonProperty("IfChangeDesc")
    private String ifChangeDesc;

	@JsonProperty("CtDesc")
    private String ctDesc;

	@JsonProperty("CtDescEn")
    private String ctDescEn;

	@JsonProperty("DisabilityStandard")
    private String disabilityStandard;

	@JsonProperty("SumInsuredMax")
    private BigDecimal sumInsuredMax;

	@JsonProperty("SumInsuredMin")
    private BigDecimal sumInsuredMin;

	@JsonProperty("SumInsuredDefualt")
    private BigDecimal sumInsuredDefualt;

	@JsonProperty("PremiumOrRate")
    private String premiumOrrate;

	@JsonProperty("PremiumMax")
    private BigDecimal premiumMax;

	@JsonProperty("PremiumMin")
    private BigDecimal premiumMin;

	@JsonProperty("PremiumDefualt")
    private BigDecimal premiumDefault;

    @JsonProperty("IfSuinsured")
    private String ifSuinsured;

    @JsonProperty("InsuredLimit")
    private Long insuredLimit;

    @JsonProperty("IncreasexDay")
    private Long increasexDay;

    @JsonProperty("IncreaseyPrem")
    private BigDecimal increaseyPrem;

    @JsonProperty("PremiumUsetype")
    private String premiumUsetype;

    @JsonProperty("DiscountPercent")
    private String discountPercent;

    @JsonProperty("RelatedCtId")
    private Long relatedCtId;

    @JsonProperty("RateTypes")
    private String rateTypes;

    @JsonProperty("PeriodTypes")
    private String periodTypes;
}
