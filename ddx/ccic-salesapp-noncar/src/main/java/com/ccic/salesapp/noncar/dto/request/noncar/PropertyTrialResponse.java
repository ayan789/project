package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

public class PropertyTrialResponse extends ResponseBody{
	private String agencyLicenseNo; //           ����������֤��
	private String agentCode; //                   ������ID�����ڷ�ֱ��ҵ����ʹ��
	private String agentLicenseNo; //            ������/���˴�����ִҵ֤��
	private String agreementNo; //               ������Э���
	private String agricultureNatureCode; //     ũҵ����
	private String agricultureRelaType; //       ��ũ��ʶ
	private String arbitrationCommissionCode; // �ٲ�ίԱ�����
	private String arbitrationCommissionText; // �ٲû��������ǳ��Ʋ��գ�ˮ�ղ�Ʒ�ã�
	private String argueSolutionType; //         ��������ʽ
	private String associatedInsurerCode; //     ���������б���˾
	private String associatedPolicyNo; //        ����������
	private String associatedProposalNo; //      ����Ͷ�����ţ����ڹ���δ�б��ı�����
	private String belongToHandlerCode; //         ���������ˣ���Ӧ���չ�˾�ڲ�Ա��
	private String belongToHandler2Code; //        ҵ����շ���/����������2
	private String belongToHandler2Name;// ҵ����շ���/����������2���ƣ���Դ���ڲ�Ա�����ߴ�����(���������ʴ�����)���޶�Ӧ���ֱ�Ӵ���������
	private String businessAttribute; //         ҵ����Դ���ԣ��滻ԭ�ȵĳ�����ʽ��ϵͳ��Դ��ϵͳҵ�����ԣ�
	private String businessCate; //              ҵ�����
	private String businessSourceCode; //        ҵ����Դ1/BusinessNature1����Ӧ���������˵�ҵ����Դ�����ĩ����ҵ��ԭ��
	private String businessSource2Code; //       ҵ����Դ2/BusinessNature����Ӧ����������2��ҵ����Դ���ɣ���ǰ̨¼��
	private String certificatePaymentType; //    �ӵ����ʽ��������Э�鱣��������ӱ����Ľ��㷽ʽ
	private String coInsuranceType; //           ������ǣ�����/�ӹ���
	private Double duePremium; //                Ӧ�ձ���/��ʵ����/RealPremium,=��������
	private String effectiveDate; //             ������/��ʼ��Ч����,֧��ʱ����
	private String expiryDate; //                �ձ����ڣ�����ֹ��/ֹ������/ʧЧ���ڣ����������ʱ�������
	private String handlerCode; //                 �����ˣ��ڲ�Ա�����ߴ�����
	private String handlerName;//�����ˣ���Դ���ڲ�Ա�����ߴ������޶�Ӧ���ֱ�Ӵ���������
	private String historyPolicyNo; //           ��ʷ�������룬����Ԥ��(DC:�Ƿ�������������)
	private String insuredCustomerCode; //       ����ı������˿ͻ�����
	private String insuredListType; //           �ֻ�����(���������嵥����)
	private String insuredName; //               ����ı�����������
	private String internalCoInsuranceType; //   ������ǣ�����/������(DC:���������ʹ���)
	private String isGovernmentBiz; //           �Ƿ�����ҵ��
	private String isHaveDetailCoverage; //      ������ȷ����
	private String isInstallment; //             �Ƿ��з���
	private String isIssueAfterPay; //           �Ƿ���ѳ�����־
	private String isLargeBusinessRisk; //       �Ƿ������ҵ����
	private String isLargeGroupPolicy; //        �Ƿ����ŵ�
	private String isPackageProduct; //          �Ƿ���ϲ�Ʒ
	private String isRegularSettlement; //       �Ƿ��ڽ����ʶ
	private String isRenewable; //               �Ƿ��������
	private String isRiIn; //                    �Ƿ��ٱ����뱣����Y/N��
	private String isSendEPolicy; //             �Ƿ��͵��ӱ�����־
	private String isSendSms; //                 �Ƿ��Ͷ���
	private String isShortTerm; //               �Ƿ�����ձ���
	private String isSmallAmount; //             �Ƿ�С���
	private String isSpecialBusiness; //         ����ҵ���־(DC:�ͼ��ѳ������)
	private String issueAddress; //              �������ַ
	private String issueOrgId; //                ��������ID
	private String issueUserId; //               ����Ա
	private String judicalScopeCode; //          ˾����Ͻ
	private String judicalScopeText; //          ˾����Ͻ�����ǳ��Ʋ��գ�ˮ�ղ�Ʒ�ã�
	private String latestRegularSettleDate; //   ���ڽ��������������
	private Double mpTotalPrepayPremium; //      Э�鱣��Ԥ���ܱ���
	private Double mpTotalSumInsured; //         Э�鱣���ܱ�������
	private String nationalEconomyCate; //       ���񾭼���ҵ���࣬����������/����/����/С�࣬�洢��С���࣬ʹ�ù������
	private String nationalEconomyL1Cate; //     ���񾭼���ҵ����
	private String nationalEconomyL2Cate; //     ���񾭼���ҵ����
	private String nationalEconomyL3Cate; //     ���񾭼���ҵ����
	private String nationalEconomyL4Cate; //     ���񾭼���ҵС��
	private String orgCode; //                   ����Ĺ�����������
	private String orgId; //                     ��������ID
	private String overseaBusinessType; //       ������ҵ���ʶ
	private String planCode; //                  ��������
	private String policyHolderCustomerCode; //  �����Ͷ���˿ͻ�����
	private String policyHolderName; //          �����Ͷ��������
	private String policyNature; //              �������ʣ��ŵ���ǣ����ָ��ţ�
	private String policyNo; //                  ��������
	private String policyPrDateType; //           ������ӡ��ʽ�����ӱ���/ֽ�ʱ���
	private String policyStatus; //              ����״̬��1δ��Ч2����Ч3ʧЧ4������ʱɾ��״̬��
	private String policyType; //                ����������Ҫȷ���������д����ӳ���ϵ
	private String premiumCurrencyCode; //       ���ѱ���
	private String productCode; //               ��Ʒ����
	private String projectNo; //                 ��Ŀ����
	private String proposalDate; //              Ͷ������
	private String proposalNo; //                Ͷ������
	private String quotationNo; //               ѯ�۵���
	private String ratingMethodCode; //          �Ʒѷ�ʽ
	private String regionCode; //                ��������
	private String regularSettlementDate; //     ���ڽ�������
	private String regularSettlementModeCode; // ���ڽ��㷽ʽ
	private String renewalType; //               ��ת�����ͣ���ת�����±����±�����
	private String repairChannelCode; //         ҵ�����(RepairChannelCode)/Q��
	private String shortRate; //                 ���ڷ���
	private String shortRateType; //             ���ڷ��ʷ�ʽ
	private String siCurrencyCode; //            �������
	private String specialFlagCode; //           �����ʶ����(���ѳ��������Ϣ)
	private Double sumInsured; //                ����
	private String thirdPartyAgentCode; //       ������ҵ��Ա���루�ֹ�¼�룬�����
	private String underwritingDate; //          �˱�ͨ��ʱ��,Max���˱�ͨ��&���ѳ����շ�ʱ�䣩���ȼ��ڳб�ʱ��
	private Double vat; //                       VAT���(��ֵ˰)
	private Double vatRate; //                   ��ֵ˰��
	private Long sequenceNumber; //���
	private String issueOrgCode;//����������Code��
	private String agentName;//�����������ƣ�
	private String belongToHandlerName;//���������������ƣ�
	private Map<String,String> tempData; //                  ��ʱ����
	private List<ChannelOpInfo> channelOpInfoList;//              �ⲿ����������Ϣ
	private List<PolicyCustomer> policyCustomerList;//            Ͷ����������Ϣ
	private List<InnerCoInsuranceInfo> innerCoInsuranceInfoList;//������Ϣ
	private List<CoInsuranceInfo> coInsuranceInfoList;//          ������Ϣ
	private List<PolicyLob> policyLobList;//                      ��Ʒ������Ϣ
    private List<AgriSubsidy> agriSubsidyList;//                  ��ũ����
    private List<InvoiceInfo> invoiceInfoList;//                  ��Ʊ��Ϣ
    private List<NewbizInfo> newbizInfoList;//                    ����Լ������Ϣ
    private List<PolicyCommissionRate> policyCommissionRateList;//Ӷ������Ϣ
    private List<PolicyPaymentInfo> policyPaymentInfoList;//      ����������Ϣ
    private List<Coins>coinsList; // ������ʹ�õ���������Ϣ
	public String getAgencyLicenseNo() {
		return agencyLicenseNo;
	}
	public void setAgencyLicenseNo(String agencyLicenseNo) {
		this.agencyLicenseNo = agencyLicenseNo;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentLicenseNo() {
		return agentLicenseNo;
	}
	public void setAgentLicenseNo(String agentLicenseNo) {
		this.agentLicenseNo = agentLicenseNo;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getAgricultureNatureCode() {
		return agricultureNatureCode;
	}
	public void setAgricultureNatureCode(String agricultureNatureCode) {
		this.agricultureNatureCode = agricultureNatureCode;
	}
	public String getAgricultureRelaType() {
		return agricultureRelaType;
	}
	public void setAgricultureRelaType(String agricultureRelaType) {
		this.agricultureRelaType = agricultureRelaType;
	}
	public String getArbitrationCommissionCode() {
		return arbitrationCommissionCode;
	}
	public void setArbitrationCommissionCode(String arbitrationCommissionCode) {
		this.arbitrationCommissionCode = arbitrationCommissionCode;
	}
	public String getArbitrationCommissionText() {
		return arbitrationCommissionText;
	}
	public void setArbitrationCommissionText(String arbitrationCommissionText) {
		this.arbitrationCommissionText = arbitrationCommissionText;
	}
	public String getArgueSolutionType() {
		return argueSolutionType;
	}
	public void setArgueSolutionType(String argueSolutionType) {
		this.argueSolutionType = argueSolutionType;
	}
	public String getAssociatedInsurerCode() {
		return associatedInsurerCode;
	}
	public void setAssociatedInsurerCode(String associatedInsurerCode) {
		this.associatedInsurerCode = associatedInsurerCode;
	}
	public String getAssociatedPolicyNo() {
		return associatedPolicyNo;
	}
	public void setAssociatedPolicyNo(String associatedPolicyNo) {
		this.associatedPolicyNo = associatedPolicyNo;
	}
	public String getAssociatedProposalNo() {
		return associatedProposalNo;
	}
	public void setAssociatedProposalNo(String associatedProposalNo) {
		this.associatedProposalNo = associatedProposalNo;
	}
	public String getBelongToHandlerCode() {
		return belongToHandlerCode;
	}
	public void setBelongToHandlerCode(String belongToHandlerCode) {
		this.belongToHandlerCode = belongToHandlerCode;
	}
	public String getBelongToHandler2Code() {
		return belongToHandler2Code;
	}
	public void setBelongToHandler2Code(String belongToHandler2Code) {
		this.belongToHandler2Code = belongToHandler2Code;
	}
	public String getBelongToHandler2Name() {
		return belongToHandler2Name;
	}
	public void setBelongToHandler2Name(String belongToHandler2Name) {
		this.belongToHandler2Name = belongToHandler2Name;
	}
	public String getBusinessAttribute() {
		return businessAttribute;
	}
	public void setBusinessAttribute(String businessAttribute) {
		this.businessAttribute = businessAttribute;
	}
	public String getBusinessCate() {
		return businessCate;
	}
	public void setBusinessCate(String businessCate) {
		this.businessCate = businessCate;
	}
	public String getBusinessSourceCode() {
		return businessSourceCode;
	}
	public void setBusinessSourceCode(String businessSourceCode) {
		this.businessSourceCode = businessSourceCode;
	}
	public String getBusinessSource2Code() {
		return businessSource2Code;
	}
	public void setBusinessSource2Code(String businessSource2Code) {
		this.businessSource2Code = businessSource2Code;
	}
	public String getCertificatePaymentType() {
		return certificatePaymentType;
	}
	public void setCertificatePaymentType(String certificatePaymentType) {
		this.certificatePaymentType = certificatePaymentType;
	}
	public String getCoInsuranceType() {
		return coInsuranceType;
	}
	public void setCoInsuranceType(String coInsuranceType) {
		this.coInsuranceType = coInsuranceType;
	}
	public Double getDuePremium() {
		return duePremium;
	}
	public void setDuePremium(Double duePremium) {
		this.duePremium = duePremium;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getHandlerCode() {
		return handlerCode;
	}
	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	public String getHistoryPolicyNo() {
		return historyPolicyNo;
	}
	public void setHistoryPolicyNo(String historyPolicyNo) {
		this.historyPolicyNo = historyPolicyNo;
	}
	public String getInsuredCustomerCode() {
		return insuredCustomerCode;
	}
	public void setInsuredCustomerCode(String insuredCustomerCode) {
		this.insuredCustomerCode = insuredCustomerCode;
	}
	public String getInsuredListType() {
		return insuredListType;
	}
	public void setInsuredListType(String insuredListType) {
		this.insuredListType = insuredListType;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getInternalCoInsuranceType() {
		return internalCoInsuranceType;
	}
	public void setInternalCoInsuranceType(String internalCoInsuranceType) {
		this.internalCoInsuranceType = internalCoInsuranceType;
	}
	public String getIsGovernmentBiz() {
		return isGovernmentBiz;
	}
	public void setIsGovernmentBiz(String isGovernmentBiz) {
		this.isGovernmentBiz = isGovernmentBiz;
	}
	public String getIsHaveDetailCoverage() {
		return isHaveDetailCoverage;
	}
	public void setIsHaveDetailCoverage(String isHaveDetailCoverage) {
		this.isHaveDetailCoverage = isHaveDetailCoverage;
	}
	public String getIsInstallment() {
		return isInstallment;
	}
	public void setIsInstallment(String isInstallment) {
		this.isInstallment = isInstallment;
	}
	public String getIsIssueAfterPay() {
		return isIssueAfterPay;
	}
	public void setIsIssueAfterPay(String isIssueAfterPay) {
		this.isIssueAfterPay = isIssueAfterPay;
	}
	public String getIsLargeBusinessRisk() {
		return isLargeBusinessRisk;
	}
	public void setIsLargeBusinessRisk(String isLargeBusinessRisk) {
		this.isLargeBusinessRisk = isLargeBusinessRisk;
	}
	public String getIsLargeGroupPolicy() {
		return isLargeGroupPolicy;
	}
	public void setIsLargeGroupPolicy(String isLargeGroupPolicy) {
		this.isLargeGroupPolicy = isLargeGroupPolicy;
	}
	public String getIsPackageProduct() {
		return isPackageProduct;
	}
	public void setIsPackageProduct(String isPackageProduct) {
		this.isPackageProduct = isPackageProduct;
	}
	public String getIsRegularSettlement() {
		return isRegularSettlement;
	}
	public void setIsRegularSettlement(String isRegularSettlement) {
		this.isRegularSettlement = isRegularSettlement;
	}
	public String getIsRenewable() {
		return isRenewable;
	}
	public void setIsRenewable(String isRenewable) {
		this.isRenewable = isRenewable;
	}
	public String getIsRiIn() {
		return isRiIn;
	}
	public void setIsRiIn(String isRiIn) {
		this.isRiIn = isRiIn;
	}
	public String getIsSendEPolicy() {
		return isSendEPolicy;
	}
	public void setIsSendEPolicy(String isSendEPolicy) {
		this.isSendEPolicy = isSendEPolicy;
	}
	public String getIsSendSms() {
		return isSendSms;
	}
	public void setIsSendSms(String isSendSms) {
		this.isSendSms = isSendSms;
	}
	public String getIsShortTerm() {
		return isShortTerm;
	}
	public void setIsShortTerm(String isShortTerm) {
		this.isShortTerm = isShortTerm;
	}
	public String getIsSmallAmount() {
		return isSmallAmount;
	}
	public void setIsSmallAmount(String isSmallAmount) {
		this.isSmallAmount = isSmallAmount;
	}
	public String getIsSpecialBusiness() {
		return isSpecialBusiness;
	}
	public void setIsSpecialBusiness(String isSpecialBusiness) {
		this.isSpecialBusiness = isSpecialBusiness;
	}
	public String getIssueAddress() {
		return issueAddress;
	}
	public void setIssueAddress(String issueAddress) {
		this.issueAddress = issueAddress;
	}
	public String getIssueOrgId() {
		return issueOrgId;
	}
	public void setIssueOrgId(String issueOrgId) {
		this.issueOrgId = issueOrgId;
	}
	public String getIssueUserId() {
		return issueUserId;
	}
	public void setIssueUserId(String issueUserId) {
		this.issueUserId = issueUserId;
	}
	public String getJudicalScopeCode() {
		return judicalScopeCode;
	}
	public void setJudicalScopeCode(String judicalScopeCode) {
		this.judicalScopeCode = judicalScopeCode;
	}
	public String getJudicalScopeText() {
		return judicalScopeText;
	}
	public void setJudicalScopeText(String judicalScopeText) {
		this.judicalScopeText = judicalScopeText;
	}
	public String getLatestRegularSettleDate() {
		return latestRegularSettleDate;
	}
	public void setLatestRegularSettleDate(String latestRegularSettleDate) {
		this.latestRegularSettleDate = latestRegularSettleDate;
	}
	public Double getMpTotalPrepayPremium() {
		return mpTotalPrepayPremium;
	}
	public void setMpTotalPrepayPremium(Double mpTotalPrepayPremium) {
		this.mpTotalPrepayPremium = mpTotalPrepayPremium;
	}
	public Double getMpTotalSumInsured() {
		return mpTotalSumInsured;
	}
	public void setMpTotalSumInsured(Double mpTotalSumInsured) {
		this.mpTotalSumInsured = mpTotalSumInsured;
	}
	public String getNationalEconomyCate() {
		return nationalEconomyCate;
	}
	public void setNationalEconomyCate(String nationalEconomyCate) {
		this.nationalEconomyCate = nationalEconomyCate;
	}
	public String getNationalEconomyL1Cate() {
		return nationalEconomyL1Cate;
	}
	public void setNationalEconomyL1Cate(String nationalEconomyL1Cate) {
		this.nationalEconomyL1Cate = nationalEconomyL1Cate;
	}
	public String getNationalEconomyL2Cate() {
		return nationalEconomyL2Cate;
	}
	public void setNationalEconomyL2Cate(String nationalEconomyL2Cate) {
		this.nationalEconomyL2Cate = nationalEconomyL2Cate;
	}
	public String getNationalEconomyL3Cate() {
		return nationalEconomyL3Cate;
	}
	public void setNationalEconomyL3Cate(String nationalEconomyL3Cate) {
		this.nationalEconomyL3Cate = nationalEconomyL3Cate;
	}
	public String getNationalEconomyL4Cate() {
		return nationalEconomyL4Cate;
	}
	public void setNationalEconomyL4Cate(String nationalEconomyL4Cate) {
		this.nationalEconomyL4Cate = nationalEconomyL4Cate;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOverseaBusinessType() {
		return overseaBusinessType;
	}
	public void setOverseaBusinessType(String overseaBusinessType) {
		this.overseaBusinessType = overseaBusinessType;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getPolicyHolderCustomerCode() {
		return policyHolderCustomerCode;
	}
	public void setPolicyHolderCustomerCode(String policyHolderCustomerCode) {
		this.policyHolderCustomerCode = policyHolderCustomerCode;
	}
	public String getPolicyHolderName() {
		return policyHolderName;
	}
	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}
	public String getPolicyNature() {
		return policyNature;
	}
	public void setPolicyNature(String policyNature) {
		this.policyNature = policyNature;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPolicyPrDateType() {
		return policyPrDateType;
	}
	public void setPolicyPrDateType(String policyPrDateType) {
		this.policyPrDateType = policyPrDateType;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getPremiumCurrencyCode() {
		return premiumCurrencyCode;
	}
	public void setPremiumCurrencyCode(String premiumCurrencyCode) {
		this.premiumCurrencyCode = premiumCurrencyCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getProposalDate() {
		return proposalDate;
	}
	public void setProposalDate(String proposalDate) {
		this.proposalDate = proposalDate;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getQuotationNo() {
		return quotationNo;
	}
	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}
	public String getRatingMethodCode() {
		return ratingMethodCode;
	}
	public void setRatingMethodCode(String ratingMethodCode) {
		this.ratingMethodCode = ratingMethodCode;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegularSettlementDate() {
		return regularSettlementDate;
	}
	public void setRegularSettlementDate(String regularSettlementDate) {
		this.regularSettlementDate = regularSettlementDate;
	}
	public String getRegularSettlementModeCode() {
		return regularSettlementModeCode;
	}
	public void setRegularSettlementModeCode(String regularSettlementModeCode) {
		this.regularSettlementModeCode = regularSettlementModeCode;
	}
	public String getRenewalType() {
		return renewalType;
	}
	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}
	public String getRepairChannelCode() {
		return repairChannelCode;
	}
	public void setRepairChannelCode(String repairChannelCode) {
		this.repairChannelCode = repairChannelCode;
	}
	public String getShortRate() {
		return shortRate;
	}
	public void setShortRate(String shortRate) {
		this.shortRate = shortRate;
	}
	public String getShortRateType() {
		return shortRateType;
	}
	public void setShortRateType(String shortRateType) {
		this.shortRateType = shortRateType;
	}
	public String getSiCurrencyCode() {
		return siCurrencyCode;
	}
	public void setSiCurrencyCode(String siCurrencyCode) {
		this.siCurrencyCode = siCurrencyCode;
	}
	public String getSpecialFlagCode() {
		return specialFlagCode;
	}
	public void setSpecialFlagCode(String specialFlagCode) {
		this.specialFlagCode = specialFlagCode;
	}
	public Double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getThirdPartyAgentCode() {
		return thirdPartyAgentCode;
	}
	public void setThirdPartyAgentCode(String thirdPartyAgentCode) {
		this.thirdPartyAgentCode = thirdPartyAgentCode;
	}
	public String getUnderwritingDate() {
		return underwritingDate;
	}
	public void setUnderwritingDate(String underwritingDate) {
		this.underwritingDate = underwritingDate;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Double getVatRate() {
		return vatRate;
	}
	public void setVatRate(Double vatRate) {
		this.vatRate = vatRate;
	}
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public List<ChannelOpInfo> getChannelOpInfoList() {
		return channelOpInfoList;
	}
	public void setChannelOpInfoList(List<ChannelOpInfo> channelOpInfoList) {
		this.channelOpInfoList = channelOpInfoList;
	}
	public List<PolicyCustomer> getPolicyCustomerList() {
		return policyCustomerList;
	}
	public void setPolicyCustomerList(List<PolicyCustomer> policyCustomerList) {
		this.policyCustomerList = policyCustomerList;
	}
	public List<InnerCoInsuranceInfo> getInnerCoInsuranceInfoList() {
		return innerCoInsuranceInfoList;
	}
	public void setInnerCoInsuranceInfoList(
			List<InnerCoInsuranceInfo> innerCoInsuranceInfoList) {
		this.innerCoInsuranceInfoList = innerCoInsuranceInfoList;
	}
	public List<CoInsuranceInfo> getCoInsuranceInfoList() {
		return coInsuranceInfoList;
	}
	public void setCoInsuranceInfoList(List<CoInsuranceInfo> coInsuranceInfoList) {
		this.coInsuranceInfoList = coInsuranceInfoList;
	}
	public List<PolicyLob> getPolicyLobList() {
		return policyLobList;
	}
	public void setPolicyLobList(List<PolicyLob> policyLobList) {
		this.policyLobList = policyLobList;
	}
	public List<AgriSubsidy> getAgriSubsidyList() {
		return agriSubsidyList;
	}
	public void setAgriSubsidyList(List<AgriSubsidy> agriSubsidyList) {
		this.agriSubsidyList = agriSubsidyList;
	}
	public List<InvoiceInfo> getInvoiceInfoList() {
		return invoiceInfoList;
	}
	public void setInvoiceInfoList(List<InvoiceInfo> invoiceInfoList) {
		this.invoiceInfoList = invoiceInfoList;
	}
	public List<NewbizInfo> getNewbizInfoList() {
		return newbizInfoList;
	}
	public void setNewbizInfoList(List<NewbizInfo> newbizInfoList) {
		this.newbizInfoList = newbizInfoList;
	}
	public List<PolicyCommissionRate> getPolicyCommissionRateList() {
		return policyCommissionRateList;
	}
	public void setPolicyCommissionRateList(
			List<PolicyCommissionRate> policyCommissionRateList) {
		this.policyCommissionRateList = policyCommissionRateList;
	}
	public List<PolicyPaymentInfo> getPolicyPaymentInfoList() {
		return policyPaymentInfoList;
	}
	public void setPolicyPaymentInfoList(
			List<PolicyPaymentInfo> policyPaymentInfoList) {
		this.policyPaymentInfoList = policyPaymentInfoList;
	}
	public List<Coins> getCoinsList() {
		return coinsList;
	}
	public void setCoinsList(List<Coins> coinsList) {
		this.coinsList = coinsList;
	}
	public String getIssueOrgCode() {
		return issueOrgCode;
	}
	public void setIssueOrgCode(String issueOrgCode) {
		this.issueOrgCode = issueOrgCode;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getBelongToHandlerName() {
		return belongToHandlerName;
	}
	public void setBelongToHandlerName(String belongToHandlerName) {
		this.belongToHandlerName = belongToHandlerName;
	}
	
	
}
