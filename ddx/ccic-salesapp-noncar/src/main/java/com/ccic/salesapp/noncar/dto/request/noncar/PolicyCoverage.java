package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;

import lombok.Data;
@Data
public class PolicyCoverage {
	private Double adjustedPremiumRate;// 人工调整后费率
	private Double avgPremium;// 平均保费，录入责任分组时用
	private Double avgSumInsured;// 平均保额
	private String benchRiskPremium;// 基准纯风险保费(仅CT级别)
	private String benchRiskPremiumBook;// 基准纯风险保费(仅CT级别)，记账币种
	private String benchRiskPremiumLocal;// 基准纯风险保费(仅CT级别),本币
	private Double beforeVatPremium;
	private String clauseCode;// 条款代码
	private String coverageType;// 责任类型(条款、责任、子责任)
	private String customFormContent;// 自定义条款内容
	private String customFormDescription;// 自定义条款描述
	private String customFormNo;// 自定义条款号
	private String customFormTitle;// 自定义条款标题
	private Double duePremium;// 应收保费/真实保费/RealPremium,=调整保费
	private String effectiveDate;// 起保日期/起始生效日期,支持时分秒
	private String employCount;// 雇员人数（同一个责任包含投保户数和雇员人数,不能复用INSURED_COUNT）
	private String expiryDate;// 终保日期，保单止期/止保日期/失效日期，不包含这个时间点在内
	private String insuredCount;// 标的数量/车辆数/台数等
	private String insuredName;// 标的名称
	private String insuredSequenceNo;// 标的物序号
	private String isCustomForm;// 是否自定义条款
	private String isFinalLevelCt;// 是否最终承保级别的责任(Y/N)
	private String isIncludedInSumInsured;// 是否计入保额
	private String isMainCoverage;// 是否主险
	private String isNonDeductible;// 是否投保不计免赔
	private String medicalPremiumRate;// 医疗费费率
	private String nonDeductForCoverage;// 不计免赔险对应的原险种/责任的PolicyCoverageId
	private String numberOfCopies;// 份数
	private Double personPremiumRate;// 人身伤亡费率
	private String policyStatus;// 保单状态（1未生效2已生效3失效4批改临时删除状态）
	private Double premiumRate;// 费率
	private String productElementCode;// 产品元素代码
	private String productionCount;// 生产能力-生产数量
	private String productionUnit;// 生产能力-单位
	private Double rentAmount;// 年租金
	private Double sumInsured;// 保额
	private Double totalFloatRate;// 总浮动系数
	private String totalNumberOfCopies;// 总份数，团单时使用，=份数*人数
	private String clauseContent;// 条款内容
	private Double unitPremium;// 单位保费
	private Double unitSumInsured;// 单位保额
	private Double vat;// VAT金额(增值税)
	private Double vatRate;// 增值税率
	private String vatRateType;// 增值税种类型
	private Double yearSalaryAmount;// 年工资额
	private Long sequenceNumber;// 序号
	private Double basePremium;//基准保费
	private Map<String, String> tempData;// 临时数据
	private List<PolicyCoverage> policyCoverageList;   ;//责任信息（责任信息中不包含PolicyCoverageList节点） 
	private List<LimitDeductible> limitDeductibleList; ;//限额免赔信息（条款信息中不包含限额免赔信息）
	
}
