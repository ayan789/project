package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class TPrdPlanCtChild {

	
	@JsonProperty("Id")
	private Long id;

	@JsonProperty("PlanCoverageId")
	private Long planCoverageId;

	@JsonProperty("PlanDefId")
	private Long planDefId;

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
	
	@JsonProperty("ProductElementCode")
	private String productElementCode;
	
	@JsonProperty("ChildPlanCoverageList")
	private List<TPrdPlanCtChild> ChildPlanCoverageList;

	//TPrdPlanCtRate
	@JsonProperty("PlanCoverageRateList")
	private List<TPrdPlanCtRate> planCoverageRateList;

	//TPrdPlanLimit
	@JsonProperty("PlanLimitList")
	private List<TPrdPlanLimit> planLimitList;
	
	//TPrdPlanCtDetail
	@JsonProperty("ProductPlanCoverageDetail")
	private TPrdPlanCtDetail productPlanCoverageDetail;
}