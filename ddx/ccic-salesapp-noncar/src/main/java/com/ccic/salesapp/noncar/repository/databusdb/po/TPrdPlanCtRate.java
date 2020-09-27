package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TPrdPlanCtRate {
	
	@JsonProperty("Id")
    private Long id;

	@JsonProperty("PlanCtRateId")
    private Long planCtRateId;

	@JsonProperty("PlanCoverageId")
    private Long planCoverageId;

	@JsonProperty("RateType")
    private String rateType;

	@JsonProperty("RatePeriod")
    private String ratePeriod;

	@JsonProperty("RateAgeMaxType")
    private String rateAgeMaxType;

	@JsonProperty("RateAgeMax")
    private String rateAgeMax;

	@JsonProperty("RateAgeMinType")
    private String rateAgeMinType;

	@JsonProperty("RateAgeMin")
    private Long rateAgeMin;

	@JsonProperty("RateSex")
    private String rateSex;

	@JsonProperty("RateOccupationType")
    private String rateOccupationType;

	@JsonProperty("RateSocialSecurityFlag")
    private String rateSocialSecurityFlag;

	@JsonProperty("RateSeatMax")
    private Long rateSeatMax;

	@JsonProperty("RateSeatMin")
    private Long rateSeatMin;

	@JsonProperty("PeriodMax")
    private Long periodMax;

	@JsonProperty("PeriodMin")
    private Long periodMin;

	@JsonProperty("PeriodPremiumRate")
    private BigDecimal periodPremiumRate;

	@JsonProperty("PeriodMinRate")
    private BigDecimal periodMinRate;

	@JsonProperty("PeriodMaxRate")
    private BigDecimal periodMaxRate;

    
}