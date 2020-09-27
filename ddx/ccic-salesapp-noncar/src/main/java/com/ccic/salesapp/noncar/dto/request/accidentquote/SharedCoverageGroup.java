package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class SharedCoverageGroup {
	private Double avgPremium;// 平均保费，录入责任分组时用
	private Double avgSumInsured;// 平均保额
	private Double benchRiskPremium;// 基准纯风险保费(仅CT级别)
	private Double duePremium;// 应收保费/真实保费/RealPremium,=调整保费
	private String groupDescription;// 分组描述
	private String isIncludedInSumInsured;// 是否计入保额
	private Double sumInsured;// 保额
	private Double vat;// VAT金额(增值税)
	private Double vatRate;// 增值税率
	private Long sequenceNumber;//序号
	private Map<String, String> tempData;// 临时数据
	private List<PolicyCoverage> policyCoverageList;// 附加责任信息
}
