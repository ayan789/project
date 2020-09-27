package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;



@Data
public class TPrdPlanDefinition {

	@JSONField(name="Id")
	private Long id;

	@JSONField(name="ApproveBy")
	private String approveBy;

	@JSONField(name="BusinessLine")
	private Long businessLine;

	@JSONField(name="BusinessObjectId")
	private Long businessObjectId;

	@JSONField(name="BusinessUUID")
	private String businessUUID;

	@JSONField(name="EffectFlag")
	private String effectFlag;

	@JSONField(name="IfUnifyIssuePlan")
	private String ifUnifyIssuePlan;

	@JSONField(name="IsCombination")
	private String isCombination;

	@JSONField(name="IsOnceEffected")
	private String isOnceEffected;

	@JSONField(name="PlanCategory")
	private String planCategory;

	@JSONField(name="PlanCode")
	private String planCode;

	@JSONField(name="PlanDefId")
	private Long planDefId;

	@JSONField(name="PlanDescription")
	private String planDescription;

	@JSONField(name="PlanEffectiveDate")
	private String planEffectiveDate;

	@JSONField(name="PlanName")
	private String planName;

	@JSONField(name="PlanStatus")
	private String planStatus;

	@JSONField(name="PlanType")
	private Long planType;

	@JSONField(name="PlanVersion")
	private Long planVersion;

	@JSONField(name="PlanExpiryDate")
	private String planExpiryDate;

	@JSONField(name="ProductId")
	private Long productId;

	@JSONField(name="PlanNameEn")
	private String planNameEn;

	@JSONField(name="SubmitBy")
	private String submitBy;

	@JSONField(name="IsSuspend")
	private String isSuspend;

	@JSONField(name="UwNeedLevel")
	private String uwNeedLevel;

	@JSONField(name="UwHiLevel")
	private String uwHiLevel;

	@JSONField(name="ProductLobCode")
	private String productLobCode;

	@JSONField(name="InsuredCode")
	private String insuredCode;

	@JSONField(name="PolicyPlanBoId")
	private String policyPlanBoId;

	@JSONField(name="PolicyId")
	private String policyId;

	@JSONField(name="SequenceNumber")
	private Long sequenceNumber;

	//请求报文有表里没的字段
	@JSONField(name="PolicyPlanBusinessObjectId")
	private Long policyPlanBusinessObjectId;
	
	@JSONField(name="ProductCode")
	private String productCode;
	
	@JSONField(name="ProductLineId")
	private Long productLineId;
	
	@JSONField(name="ProductName")
	private String productName;
	
	@JSONField(name="ProductVersion")
	private String productVersion;
	
	@JSONField(name="SecondLine")
	private Long secondLine;
	
	private String businessAttributes;
	
	@JSONField(name="PolicyTemple")
	private PolicyTemple policyTemple;

	//TPrdPlanInfo
	@JSONField(name="ProductPlanInfo")
	private TPrdPlanInfo productPlanInfo;

	//TPrdPlanOrg
	@JSONField(name="ProductPlanOrgList")
	private List<TPrdPlanOrg> productPlanOrgList;

	//TPrdPlanMethod
	@JSONField(name="ProductPlanMethodList")
	private List<TPrdPlanMethod> productPlanMethodList;

	//TPrdPlanSharedCtGroup
	@JSONField(name="PlanSharedGroupList")
	private List<TPrdPlanSharedCtGroup> planSharedGroupList;

	//TPrdPlanCoInsurer
	@JSONField(name="tPrdPlanCoInsurer")
	private List<TPrdPlanCoInsurer> tPrdPlanCoInsurer;

	//TPrdPlanCt
	@JSONField(name="PlanCoverageList")
	private List<TPrdPlanCt> planCoverageList;

	//TPrdPlanForm
	@JSONField(name="ProductPlanFormList")
	private List<TPrdPlanForm> productPlanFormList;

	//TPrdPlanRi
	@JSONField(name="ProductPlanRiList")
	private List<TPrdPlanRi> productPlanRiList;


	

}