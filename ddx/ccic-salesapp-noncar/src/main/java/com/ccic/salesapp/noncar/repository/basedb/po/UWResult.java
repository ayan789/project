package com.ccic.salesapp.noncar.repository.basedb.po;

import java.util.List;


public class UWResult {
	/*业务单号*/
	private String	businessNo;
	/*结果类型*/
	private String uwType;
	/*是否需要验车*/
	private String isNeedCheck;
	/*核保级别*/
	private String	uwLevel;
	/*规则匹配信息*/
	private List<UWRuleInfo>	underwriteRuleInfos;
}
