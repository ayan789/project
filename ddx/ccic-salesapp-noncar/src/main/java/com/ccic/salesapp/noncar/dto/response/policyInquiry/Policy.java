package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class Policy {
    private String proposalDate;//签单日期
    private String underwritingDate;//核保通过时间,Max（核保通过&见费出单收费时间），等价于承保时间
    private String policyStatus;//保单状态
    private String policyStatusDesc;//保单状态描述
    private String productCode;//产品代码
    private String productName;//产品名称
    private String orgCode;//归属机构代码
    private String areaCode;//地区代码
    private String orgName;//归属机构名称
    private String belongToHandlerCode;//归属经办人
    private String belongToHandlerName;//归属经办名称
    private String issueOrgCode;//出单机构代码
    private String handlerCode;//经办人代码
    private String handlerName;//经办人名称
    private BigDecimal sumInsured;//保额
    private String proposalConfirmTime;//投保确认时间
    private String policyGenerationTime;//保单生成时间
    private String issueUserCode;//出单员代码
    private String IssueUserName;//出单员名称
    private String issueAddress;//出单点地址
    private String businessAttribute;//业务属性
    private String businessSourceCode;//业务来源1
    private String businessSourceName;//业务来源名称
    private String businessSource2Code;//业务来源2
    private String businessSource2Name;//业务来源2名称
    private String belongToHandler2Code;//业务风险分类
    private String belongToHandler2Name;//业务风险分类名称
    private String agentCode;//代理人代码
    private String agentName;//代理人名称
    private String coInsuranceType;//共保标记（主共/从共）
    private String renewalType;//新转续类型（续转保、新保、新保续）
    private String renewedFlag;//被续保标志
    private String agreementNo;//代理协议号
    //private String agentLicenseNo;//经办人/个人代理人执业证号
    private String agencyLicenseNo;//代理机构许可证号
    private String thirdPartyAgentCode;//第三方业务员编码
    private String repairChannelCode;//业务编码/Q码
    private String effectiveDate;//起保日期
    private String expiryDate;//终保日期
    private String premiumCurrencyCode;//保费币种
    private String argueSolutionType;//争议解决方式
    private String argueSolutionTypeDesc;//争议解决方式描述
    private String uwUserCode;//核保人代码
    private String uwUserName;//核保人名称
    private String shortRateType;//短期费率方式
    private String shortRateTypeDesc;//短期费率方式描述
    private BigDecimal shortRate;//短期费率
    private String projectNo;//项目编码
    private String quotationNo;//报价单号
    private String policyNo;//保单号
    private String proposalNo;//投保单号
    //	private String beforeVatPremium;//税前真实保费,不含税保费
    private BigDecimal stdPremium;//标准保费
    private BigDecimal duePremium;//应收保费
    private String isShortTerm;//是否短期单
    private String isEffectiveImmediately;//是否即时生效
    private List<PolicyCustomer> policyCustomers;//关系人信息
    private ChannelOpInfoDTO channelOpInfo;//外部渠道信息
    private VehiclePolicyLob policyLob;//车险产品基本信息
    private List<DiscountDetail> discountDetails;//优惠折扣明细
    private InvoiceInfo invoiceInfo;//开票信息
    private List<LastPolicy> lastPolicyList;//上年保单信息列表
    private PlatformInfo platformInfo;//平台信息
    private ChannelRepairInfo channelRepairInfo;//送返修信息
    private String insuredVehicleRelaCode;//被保人与车辆关系
    private CoInsuranceInfo coInsuranceInfo;//联共保的信息
    private String projectName;//项目名称
    private String isPolicyFor459;//是否459
    private String callBackFlag;//回访标志
    private String dcPolicyNo;//回访标志
    private String isRateReform;//费改标识
    private String isSurrender;//保单退保标志
    private String agricultureRelaType;//涉农标识
    private String duePremChangeFlag;//保费批增批减标志
    private String polHolderVehicleRelaCode;//投保人与车辆关系
    //private String fleetNo;//车队号
    //private String purchaseInvoiceDate;//购车发票日期
    private String isEpolicy;
    private String isCompleteEproposal;
    private Map<String,String> extensionMap;
}
