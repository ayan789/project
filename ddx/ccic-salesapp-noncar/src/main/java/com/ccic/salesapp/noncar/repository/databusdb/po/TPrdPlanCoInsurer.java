package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;


@Data
public class TPrdPlanCoInsurer {
	
	@JSONField(name="Id")
    private Long id;

	@JSONField(name="PlanCoinsuerId")
    private String planCoinsuerId;

	@JSONField(name="PlanCoinsuranceId")
    private String planCoinsuranceId;

	@JSONField(name="CoinsurerBranchName")
    private String coinsurerBranchName;

	@JSONField(name="InsurerPartyId")
    private String insurerPartyId;

	@JSONField(name="IsLeader")
    private String isLeader;

	@JSONField(name="ShareRate")
    private String shareRate;

	@JSONField(name="TotalShareRate")
    private String totalShareRate;

	@JSONField(name="BusinessSourceCode")
    private String businessSourceCode;

	@JSONField(name="BelongTohandlerid")
    private String belongTohandlerid;

	@JSONField(name="BelongToorgid")
    private String belongToorgid;

	@JSONField(name="AgentId")
    private String agentId;

	@JSONField(name="AgreementNo")
    private String agreementNo;

	@JSONField(name="AgreementName")
    private String agreementName;

	@JSONField(name="IssueUserid")
    private String issueUserid;

	@JSONField(name="IssueOrgid")
    private String issueOrgid;

	@JSONField(name="BelongTohandler2id")
    private String belongTohandler2id;
    
    //TPrdPlanCoInsurence
	@JSONField(name="")
    private List<TPrdPlanCoInsurence> tPrdPlanCoInsurence;

  
}