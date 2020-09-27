package com.ccic.salesapp.noncar.repository.basedb.po;

import lombok.Data;

@Data
public class UWRuleInfo {
	/*1-拒保，2-返回修改，3-自动核保,4-一级、5-二级、6-三级、7-四级，默认值为7*/
	private Integer	uwLevel;
	/*结果类型*/
	private String uwType;
	/*规则名称*/		
	private String	ruleName;
	/*规则提示信息*/
	private String	ruleDescription;
	/*规则类型*/
	private String	ruleType;
}
