package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.MediaSize.Engineering;

import lombok.Data;
@Data
public class PolicyLob {
	private Long mainInsuredCount;//主被保人人数，仅团险使用
	private Long addInsuredCount;//附属被保险人人数，仅团险使用
	private Long totalInsuredCount;//总被保险人人数，仅团险使用
	private Long totalEmployeeCount;//单位总人数，仅团险使用
	private BigDecimal insuredRate;//参保率，仅团险使用
	private BigDecimal avgAge;//平均年龄，仅团险使用
	private BigDecimal avgChildCount;//平均附属子女数，仅团险使用
	private BigDecimal childBearingWomanRate;//育龄女性占比，仅团险使用
	private BigDecimal maleFemaleRate;//性别比例男/女，仅团险使用
	private BigDecimal retireRate;//退休比例，仅团险使用
	private String goodsPaymentCurrency;//贷款币别
	private String loanType;//贷款类型
	private String contractNo;//贷款合同号
	private String loanBankCode;//贷款银行代码
	private Date loanPeriodStart;//贷款起期
	private Date loanPeriodEnd;//贷款止期
	private String sumInsuredMode;//保额确定方式
	private String loanAmount;//借款金额
	private String schoolType;//学校类别
	private String schoolNature;//学校性质
	private String healthSpecialBusinessType;//健康专项业务类别
	private BigDecimal fundTotal;//基金总额
	private BigDecimal adminFeeRate;//管理费比例
	private BigDecimal adminFee;//管理费
	private String insuredCrowdType;//参保人群类型
	private List<PolicyRegion> policyRegionList;//地区信息列表
	private List<SharedCoverageGroup> sharedCoverageGroupList;//公共保障分组列表
	private List<PolicyForm> policyFormList;//保单或者产品层面的特别约定列表(formCategory为2)
	private List<PolicyRisk> policyRiskList;//标的信息列表
	private List<Engineering> engineeringList;//工程类信息列表
	private List<Vehicle> vehicleList;//司乘信息列表
	private List<TourGroup> tourGroupList;//旅游信息列表
	private List<AddressDetail> addressDetailList; //工程类地址信息列表
}
