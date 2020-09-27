package com.ccic.salesapp.noncar.dto;

import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.request.noncar.AgriSubsidy;
import com.ccic.salesapp.noncar.dto.request.noncar.ChannelOpInfo;
import com.ccic.salesapp.noncar.dto.request.noncar.InnerCoInsuranceInfo;
import com.ccic.salesapp.noncar.dto.request.noncar.PolicyPaymentInfo;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;

import lombok.Data;

@Data
public class PropertyTrialRequest  extends RequestBody{
	private String agencyLicenseNo; //           代理机构许可证号
	private String agentCode; //                   代理人ID，仅在非直销业务中使用
	private String agentLicenseNo; //            经办人/个人代理人执业证号
	private String agreementNo; //               代理人协议号
	private String agricultureNatureCode; //     农业性质
	private String agricultureRelaType; //       涉农标识
	private String arbitrationCommissionCode; // 仲裁委员会代码
	private String arbitrationCommissionText; // 仲裁机构（仅非车财产险，水险产品用）
	private String argueSolutionType; //         争议解决方式
	private String associatedInsurerCode; //     关联保单承保公司
	private String associatedPolicyNo; //        关联保单号
	private String associatedProposalNo; //      关联投保单号（用于关联未承保的保单）
	private String belongToHandlerCode; //         归属经办人，对应保险公司内部员工
	private String belongToHandler2Code; //        业务风险分类/归属经办人2
	private String belongToHandler2Name;// 业务风险分类/归属经办人2名称，来源于内部员工或者代理人(或者无资质代理人)，无对应码表，直接存代码和名称
	private String businessAttribute; //         业务来源属性（替换原先的出单方式，系统来源，系统业务属性）
	private String businessCate; //              业务分类
	private String businessSourceCode; //        业务来源1/BusinessNature1，对应归属经办人的业务来源，存放末级的业务原来
	private String businessSource2Code; //       业务来源2/BusinessNature，对应归属经办人2的业务来源生成，非前台录入
	private String certificatePaymentType; //    子单付款方式，货运险协议保单下面的子保单的结算方式
	private String coInsuranceType; //           共保标记（主共/从共）
	private Double duePremium; //                应收保费/真实保费/RealPremium,=调整保费
	private String effectiveDate; //             起保日期/起始生效日期,支持时分秒
	private String expiryDate; //                终保日期，保单止期/止保日期/失效日期，不包含这个时间点在内
	private String handlerCode; //                 经办人，内部员工或者代理人
	private String handlerName;//经办人，来源于内部员工或者代理人无对应码表，直接存代码和名称
	private String historyPolicyNo; //           历史保单号码，用于预填(DC:是否是续保保单号)
	private String insuredCustomerCode; //       冗余的被保险人客户代码
	private String insuredListType; //           分户类型(被保险人清单类型)
	private String insuredName; //               冗余的被保险人姓名
	private String internalCoInsuranceType; //   联保标记（主联/从联）(DC:联共保类型代码)
	private String isGovernmentBiz; //           是否政府业务
	private String isHaveDetailCoverage; //      有无明确条件
	private String isInstallment; //             是否有分期
	private String isIssueAfterPay; //           是否见费出单标志
	private String isLargeBusinessRisk; //       是否大型商业风险
	private String isLargeGroupPolicy; //        是否大额团单
	private String isPackageProduct; //          是否组合产品
	private String isRegularSettlement; //       是否定期结算标识
	private String isRenewable; //               是否可以续保
	private String isRiIn; //                    是否再保分入保单（Y/N）
	private String isSendEPolicy; //             是否发送电子保单标志
	private String isSendSms; //                 是否发送短信
	private String isShortTerm; //               是否短期险保单
	private String isSmallAmount; //             是否小额保险
	private String isSpecialBusiness; //         特殊业务标志(DC:和见费出单相关)
	private String issueAddress; //              出单点地址
	private String issueOrgId; //                出单机构ID
	private String issueOrgCode;//               出单机构代码
	private String issueUserId; //               出单员
	private String judicalScopeCode; //          司法管辖
	private String judicalScopeText; //          司法管辖（仅非车财产险，水险产品用）
	private String latestRegularSettleDate; //   定期结算最晚结算日期
	private Double mpTotalPrepayPremium; //      协议保单预缴总保费
	private Double mpTotalSumInsured; //         协议保单总保额限制
	private String nationalEconomyCate; //       国民经济行业分类，可以是门类/大类/种类/小类，存储最小分类，使用国标代码
	private String nationalEconomyL1Cate; //     国民经济行业门类
	private String nationalEconomyL2Cate; //     国民经济行业大类
	private String nationalEconomyL3Cate; //     国民经济行业中类
	private String nationalEconomyL4Cate; //     国民经济行业小类
	private String orgCode; //                   冗余的归属机构代码
	private String orgId; //                     归属机构ID
	private String overseaBusinessType; //       境内外业务标识
	private String planCode; //                  方案代码
	private String policyHolderCustomerCode; //  冗余的投保人客户代码
	private String policyHolderName; //          冗余的投保人姓名
	private String policyNature; //              保单性质（团单标记，区分个团）
	private String policyNo; //                  保单号码
	private String policyPrDateType; //           保单打印方式，电子保单/纸质保单
	private String policyStatus; //              保单状态（1未生效2已生效3失效4批改临时删除状态）
	private String policyType; //                保单类型需要确认与大地已有代码表映射关系
	private String premiumCurrencyCode; //       保费币种
	private String productCode; //               产品代码
	private String projectNo; //                 项目编码
	private String proposalDate; //              投保日期
	private String proposalNo; //                投保单号
	private String quotationNo; //               询价单号
	private String ratingMethodCode; //          计费方式
	private String regionCode; //                归属地区
	private String regularSettlementDate; //     定期结算日期
	private String regularSettlementModeCode; // 定期结算方式
	private String renewalType; //               新转续类型（续转保、新保、新保续）
	private String repairChannelCode; //         业务编码(RepairChannelCode)/Q码
	private String shortRate; //                 短期费率
	private String shortRateType; //             短期费率方式
	private String siCurrencyCode; //            保额币种
	private String specialFlagCode; //           特殊标识代码(见费出单相关信息)
	private Double sumInsured; //                保额
	private String thirdPartyAgentCode; //       第三方业务员编码（手工录入，无码表）
	private String underwritingDate; //          核保通过时间,Max（核保通过&见费出单收费时间），等价于承保时间
	private Double vat; //                       VAT金额(增值税)
	private Double vatRate; //                   增值税率
	private Long sequenceNumber; //序号
	private Map<String,String> tempData; //                  临时数据
	private List<ChannelOpInfo> channelOpInfoList;//              外部渠道附加信息
	private List<PolicyCustomer> policyCustomerList;//            投、被保人信息
	private List<InnerCoInsuranceInfo> innerCoInsuranceInfoList;//联保信息
	private List<CoInsuranceInfo> coInsuranceInfoList;//          共保信息
	private List<PolicyLob> policyLobList;//                      产品基本信息
    private List<AgriSubsidy> agriSubsidyList;//                  涉农补贴
    private List<InvoiceInfo> invoiceInfoList;//                  开票信息
    private List<NewbizInfo> newbizInfoList;//                    新契约附加信息
    private List<PolicyCommissionRate> policyCommissionRateList;//佣金率信息
    private List<PolicyPaymentInfo> policyPaymentInfoList;//      保单付款信息
    private List<Coins> coinsList; // 老网关使用的联共保信息
}
