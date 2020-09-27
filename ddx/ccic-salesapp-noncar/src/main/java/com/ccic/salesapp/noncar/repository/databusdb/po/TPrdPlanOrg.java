package com.ccic.salesapp.noncar.repository.databusdb.po;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;


@Data
public class TPrdPlanOrg {
	
	@JSONField(name="Id")
    private Long id;

	@JSONField(name="PlanOrgId")
    private Long planOrgId;

	@JSONField(name="PlanDefId")
    private Long planDefId;

	@JSONField(name="OrgCode")
    private String orgCode;

	@JSONField(name="OrgId")
    private Long orgId;

	@JSONField(name="OrgName")
    private String orgName;

}