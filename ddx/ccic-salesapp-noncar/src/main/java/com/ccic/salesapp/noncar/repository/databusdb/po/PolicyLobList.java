package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.util.List;

import com.ccic.salesapp.noncar.dto.request.noncar.PolicyRiskOrderCar;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class PolicyLobList {

	//TPrdPlanForm
	@JsonProperty("PolicyFormList")
	private List<TPrdPlanForm> productPlanFormList;

	//TPrdPlanSharedCtGroup
	@JsonProperty("SharedCoverageGroupList")
	private List<TPrdPlanSharedCtGroup> planSharedGroupList;
	
	
	//TPrdPlanSharedCtGroup
	@JsonProperty("PolicyRiskList")
	private List<PolicyRiskOrderCar> policyRiskList;
	
	//TPrdPlanSharedCtGroup
	@JsonProperty("SharedCoverageGroupList")
	private List<PolicyRiskOrderCar> sharedCoverageGroupList;
		
	@JsonProperty("DuePremium")
	private Double duePremium;

}