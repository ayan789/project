package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.List;

import lombok.Data;
@Data
public class PolicyCoverage {
	//序号
		private Long sequenceNumber;
		//责任代码
		private String kindCode;
		//关联责任ID【关联责任代码】
		private String relatedCoverageCode;
		//单位保费
		private Double unitPremium;
		//不计免赔险对应的原险种/责任的PolicyCoverageId
		private Long nonDeductForCoverage;
		//费率
		private Double premiumRate;
		//应收保费/真实保费/RealPremium,=调整保费
		private Double duePremium;
		//平均保额
		private Double avgSumInsured;
		//是否计入保额
		private String isIncludedInSumInsured;
		//保额
		private Double sumInsured;
		//条款代码
		private String clauseCode;
		//每日补贴金额
		private Double subsidyAmount;
		//补贴天数
		private Long subsidyDay;
	    private String subsidyType;
		//条款名称
		private String clauseName;
		//责任名称
		private String kindName;
		private String beforeVatPremium ;//税前真实保费,不含税保费
		private String vat ;//VAT金额(增值税)
		private String effectiveDate ;//起保日期/起始生效日期
		private String expiryDate ;//终保日期，保单止期/止保日期/失效日期
		private List<PolicyForm> policyFormList; //条款或责任层面的特别约定列表(formCategory为2)
		private List<LimitDeductible> limitDeductibleList ;//限额免赔列表
}
