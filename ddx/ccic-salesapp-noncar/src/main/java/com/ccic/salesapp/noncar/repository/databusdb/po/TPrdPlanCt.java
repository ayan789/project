package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;


@Data
public class TPrdPlanCt {


	@JSONField(name="Id")
	private Long id;

	@JSONField(name="PlanCoverageId")
	private Long planCoverageId;

	@JSONField(name="PlanDefId")
	private Long planDefId;

	@JSONField(name="PlanSharedId")
	private Long planSharedId;

	@JSONField(name="PlanCoverageName")
	private String planCoverageName;

	@JSONField(name="IsNonDeductible")
	private String isNondeductible;

	@JSONField(name="ParentPlanCoverageId")
	private Long parentPlanCoverageId;

	@JSONField(name="IsOptional")
	private String isOptional;
	
	@JSONField(name="IsSelectedByDefault")
	private String isSelectedByDefault;

	@JSONField(name="ProductElementCode")
	private String productElementCode;
	
	@JSONField(name="CoverageType")
	private String CoverageType;
	
	@JSONField(name="ProductElementName")
	private String productElementName;
	
	@JSONField(name="PremiumRate")
	private Integer premiumRate;
	
	@JSONField(name="GroupType")
	private String SharedGroupType;

	//TPrdPlanCt
	/*	@JSONField(name="ChildPlanCoverageList")
	private List<TPrdPlanCtChild> ChildPlanCoverageList;*/

	/*	@JSONField(name="ChildPlanCoverageList")
	private List<TPrdPlanCtChild> ChildPlanCoverageList;*/

	@JSONField(name="ChildPlanCoverageList")
	private List<TPrdPlanCt> ChildPlanCoverageList;

	//TPrdPlanCtRate
	@JSONField(name="PlanCoverageRateList")
	private List<TPrdPlanCtRate> planCoverageRateList;

	//TPrdPlanLimit
	@JSONField(name="PlanLimitList")
	private List<TPrdPlanLimit> planLimitList;

	//TPrdPlanCtDetail
	@JSONField(name="ProductPlanCoverageDetail")
	private TPrdPlanCtDetail productPlanCoverageDetail;
	
}