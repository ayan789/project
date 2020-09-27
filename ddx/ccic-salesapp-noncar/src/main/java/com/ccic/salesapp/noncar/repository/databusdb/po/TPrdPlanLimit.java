package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.math.BigDecimal;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;


@Data
public class TPrdPlanLimit {
	
	@JsonProperty("Id")
    private Long id;

    @JsonProperty("PlanLimitId")
    private Long planLimitId;

    @JsonProperty("PlanCoverageId")
    private Long planCoverageId;

    @JsonProperty("CategoryLevelOne")
    private String categoryLevelOne;

    @JsonProperty("LimitDeductibleName")
    private String limitDeductibleName;

    @JsonProperty("CategoryLevelTwo")
    private String categoryLevelTwo;

    @JsonProperty("LimitDeductibleUnit")
    private String limitDeductibleUnit;

    @JsonProperty("DeductibeType")
    private String deductibeType;

    @JsonProperty("LimitCurrency")
    private String limitCurrency;

    @JsonProperty("PlanLimitName")
    private String planLimitName;

    @JsonProperty("LimitDeductibleAmount")
    private String limitDeductibleAmount;

    @JsonProperty("LimitDeductibleMax")
    private BigDecimal limitDeductibleMax;

    @JsonProperty("LimitDeductibleMin")
    private BigDecimal limitDeductibleMin;

    @JsonProperty("ProductElementId")
    private String productElementId;

 
}