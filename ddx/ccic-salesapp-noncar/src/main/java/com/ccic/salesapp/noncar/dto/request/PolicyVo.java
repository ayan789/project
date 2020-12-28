package com.ccic.salesapp.noncar.dto.request;

import lombok.Data;

@Data
public class PolicyVo {
//	int pageSize;
//	int pageNo;
	String searchType;//查询类型
	/*	* 1-保单号查询（非车和人身险）
    * 2-根据车架号查询
    * 3-发动机号查询
    * 4-车牌号查询
    * 5-保单号+单证流水号（人身险）(如果传被保险人名称，会返回被保险人信息）
    * 6-被保险人三要素查询下
    * 7-根据船舶名称查询保单列表信息
    * 8-超级app，根据投保人或者被保险人三要素查询
    * 9-非车根据被保险人或者投保人三要素查询保单
    *  10-根据联合销售单号查询保单列表
    *11-根据农户证件号、农户姓名、投保耳标号，查询保单列表*/
	
	String submissionNo;//联合单号
	String policyNo;//保单号
	String customerName;//被保人
	String idNo;//证件号
	String indiIdType;//证件类型 
	
}
