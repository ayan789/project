package com.ccic.salesapp.noncar.repository.basedb.po;

import lombok.Data;

@Data
public class EnPolicyListQueryEntity {
	private String staffCode;//业务员代码
	private String insuranceType;//险类大类
	private String custType;//客户类型 个人客户：10 企业客户：20
	private String contactorName;//联系人姓名",企业客户保单查询使用
	private String minPayStartDate;//投保日期查询下限
	private String maxPayStartDate;//投保日期查询上限	
	private String minRespEndDate;//到期日期查询下限
	private String maxRespEndDate;//到期日期查询上限
	private String policyNo;//保单号
	private String riskCode;//险种代码
	private String riskName;//险种名称
	private String premium;//保单保费
	private String custNo;//客户号
	private String custName;//客户姓名
	private String respEndDate;//责任终止日期
	private String riskType;//险类",
	private String status;//注销退保标志" 0-正常；1-全单退保；2-满期退保；3-部分退保；4-注销
	private String clsLevel;// 客户等级 20141103 增加 客户等级需求增加
	private String licenseNo;//车牌号码
	private String curHandleStatus;//续保任务状态
	private String applicantName;//投保人姓名
	private String insuredName;//被保人姓名
	private String winBack;//是否赢回字段
	private String appDate;//投保日期
	private String trackStatus ;//续保状态	
	private int   riskTypeCode;
	private String agentCode;//代理人代码
	private String productCode;//产品代码
	private String proposalNo;//投保单号
}
