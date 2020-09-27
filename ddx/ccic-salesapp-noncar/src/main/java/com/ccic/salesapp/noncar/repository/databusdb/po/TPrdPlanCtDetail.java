package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.math.BigDecimal;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;


@Data
public class TPrdPlanCtDetail {
	
	@JsonProperty("Id")
    private Long id;

	@JsonProperty("PlanCtDetailId")
    private Long planCtdetailId;

	@JsonProperty("PlanCoverageId")
    private Long planCoverageId;

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
    private BigDecimal PremiumDefualt;

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