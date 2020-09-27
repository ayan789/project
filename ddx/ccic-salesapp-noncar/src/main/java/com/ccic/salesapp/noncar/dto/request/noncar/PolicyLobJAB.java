package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;

/**
 * ��Ʒ��Ϣ---�Ҳ���
 * @author Administrator
 */
public class PolicyLobJAB {
	private Double duePremium;// Ӧ�ձ���/��ʵ����/RealPremium,=��������
	private String eachPersonCopies;// ÿ��Ͷ������
	private String effectiveDate;// ������/��ʼ��Ч����,֧��ʱ����
	private String productCode;// ��Ʒ����
	private String expiryDate;// �ձ����ڣ�����ֹ��/ֹ������/ʧЧ���ڣ����������ʱ�������
	private String mainClauseId;// ��������ID�����ڷǳ����ƷͳһΪ������ѡ������
	private String mainCoverageId;// ��������ID�����ڷǳ����ƷͳһΪ������ѡ�����������µ�����
	private String otherInsuredCondition;// �����б�����
	private String planCode;// ��������
	private String policyStatus;// ����״̬��1δ��Ч2����Ч3ʧЧ4������ʱɾ��״̬��
	private Double sumInsured;// ����
	private String sumInsuredModeCode;// ����ȷ����ʽ\���պ��ַ�ʽȷ�����ս��
	private String totalNumberOfCopies;// �ܷ���
	private String totalPeopleNumber;// ������
	private Double vat;// VAT���(��ֵ˰)
	private Double vatRate;// ��ֵ˰��
	private String businessLicenseNo;// ��Ӫ���֤/Ӫҵִ�պ���
	private String businessNatureCode;// ��������Ӫҵ����
	private String businessPlace;// Ӫҵ����(��ҵ��Ϣ)
	private String enterpriseNature;// ��ҵ����
	private String enterpriseNatureCode;// ��ҵ����(��ҵ��Ϣ)
	private String industryRisk;// ��������ʧ��ҵ
	private String industryType;// ��ҵ���(�칫��,����ҵ��,��������ҵ)
	private String insuranceArea;// Ͷ���ľ�Ӫ��Χ
	private String insuranceAreaDesc;// ��Ӫ��Χ˵��
	private String insuredAreaCode;// Ͷ������/�б�����Χ
	private String licenseNo;// �ʸ�֤�ţ����ʸ�֤�ţ�
	private String liftCount;// ��������
	private String ownershipSystemCode;// ������
	private String possessNatureCode;// ռ������
	private String quotationNoFromPlatform;// ���۵�����Դ�ڹ㶫�ǳ����ƽ̨
	private String riskLevelCode;// ���յȼ�
	private String riskType;// ��������
	private String homePurposeCode;// ������;����ס�����ޣ�
	private String homeStructure;// ���ݽṹ���ֻ�ṹ��ש��ṹ��שľ�ṹ���ֽṹ�֡��ֽ�������ṹ��ש���ṹ��
	private String homeType;// �������ͣ���ס����ס���á����⡢������
	private String otherDescription;// ����˵��
	private String platformEndoNo;// ƽ̨������
	private String platformPolicyNo;// ƽ̨������
	private Map<String,String> tempData;// ��ʱ����
	private String safeProductionLicenseNo;// �������֤�ţ��󳤰�ȫ��������ʸ�֤�ţ�
	private List<PolicyRisk> policyRiskList;// �����Ϣ
	private List<AddressInfo> addressInfoList;// ��ַ��Ϣ
	private List<PolicyDeductible> policyDeductibleList;// ����ģ����Ϣ
	private List<LimitDeductible> limitDeductibleList;// �������޶�������Ϣ
	private List<HomeDetail> homeDetailList;// �����嵥��Ϣ
	private List<SharedCoverageGroup> sharedCoverageGroupList;// ����������Ϣ
	private List<PolicyForm> policyFormList;// �ر�Լ����Ϣ
	private List<ProductDetail> productDetailList;// ��Ʒ�嵥��Ϣ
	private List<NonMotorDetail> nonMotorDetailList;// �ǻ���������Ϣ
	private List<LiftDetail> liftDetailList;// �����嵥��Ϣ
	public Double getDuePremium() {
		return duePremium;
	}
	public void setDuePremium(Double duePremium) {
		this.duePremium = duePremium;
	}
	public String getEachPersonCopies() {
		return eachPersonCopies;
	}
	public void setEachPersonCopies(String eachPersonCopies) {
		this.eachPersonCopies = eachPersonCopies;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getMainClauseId() {
		return mainClauseId;
	}
	public void setMainClauseId(String mainClauseId) {
		this.mainClauseId = mainClauseId;
	}
	public String getMainCoverageId() {
		return mainCoverageId;
	}
	public void setMainCoverageId(String mainCoverageId) {
		this.mainCoverageId = mainCoverageId;
	}
	public String getOtherInsuredCondition() {
		return otherInsuredCondition;
	}
	public void setOtherInsuredCondition(String otherInsuredCondition) {
		this.otherInsuredCondition = otherInsuredCondition;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public Double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getSumInsuredModeCode() {
		return sumInsuredModeCode;
	}
	public void setSumInsuredModeCode(String sumInsuredModeCode) {
		this.sumInsuredModeCode = sumInsuredModeCode;
	}
	public String getTotalNumberOfCopies() {
		return totalNumberOfCopies;
	}
	public void setTotalNumberOfCopies(String totalNumberOfCopies) {
		this.totalNumberOfCopies = totalNumberOfCopies;
	}
	public String getTotalPeopleNumber() {
		return totalPeopleNumber;
	}
	public void setTotalPeopleNumber(String totalPeopleNumber) {
		this.totalPeopleNumber = totalPeopleNumber;
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
	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}
	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}
	public String getBusinessNatureCode() {
		return businessNatureCode;
	}
	public void setBusinessNatureCode(String businessNatureCode) {
		this.businessNatureCode = businessNatureCode;
	}
	public String getBusinessPlace() {
		return businessPlace;
	}
	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}
	public String getEnterpriseNature() {
		return enterpriseNature;
	}
	public void setEnterpriseNature(String enterpriseNature) {
		this.enterpriseNature = enterpriseNature;
	}
	public String getEnterpriseNatureCode() {
		return enterpriseNatureCode;
	}
	public void setEnterpriseNatureCode(String enterpriseNatureCode) {
		this.enterpriseNatureCode = enterpriseNatureCode;
	}
	public String getIndustryRisk() {
		return industryRisk;
	}
	public void setIndustryRisk(String industryRisk) {
		this.industryRisk = industryRisk;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getInsuranceArea() {
		return insuranceArea;
	}
	public void setInsuranceArea(String insuranceArea) {
		this.insuranceArea = insuranceArea;
	}
	public String getInsuranceAreaDesc() {
		return insuranceAreaDesc;
	}
	public void setInsuranceAreaDesc(String insuranceAreaDesc) {
		this.insuranceAreaDesc = insuranceAreaDesc;
	}
	public String getInsuredAreaCode() {
		return insuredAreaCode;
	}
	public void setInsuredAreaCode(String insuredAreaCode) {
		this.insuredAreaCode = insuredAreaCode;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLiftCount() {
		return liftCount;
	}
	public void setLiftCount(String liftCount) {
		this.liftCount = liftCount;
	}
	public String getOwnershipSystemCode() {
		return ownershipSystemCode;
	}
	public void setOwnershipSystemCode(String ownershipSystemCode) {
		this.ownershipSystemCode = ownershipSystemCode;
	}
	public String getPossessNatureCode() {
		return possessNatureCode;
	}
	public void setPossessNatureCode(String possessNatureCode) {
		this.possessNatureCode = possessNatureCode;
	}
	public String getQuotationNoFromPlatform() {
		return quotationNoFromPlatform;
	}
	public void setQuotationNoFromPlatform(String quotationNoFromPlatform) {
		this.quotationNoFromPlatform = quotationNoFromPlatform;
	}
	public String getRiskLevelCode() {
		return riskLevelCode;
	}
	public void setRiskLevelCode(String riskLevelCode) {
		this.riskLevelCode = riskLevelCode;
	}
	public String getRiskType() {
		return riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	public String getHomePurposeCode() {
		return homePurposeCode;
	}
	public void setHomePurposeCode(String homePurposeCode) {
		this.homePurposeCode = homePurposeCode;
	}
	public String getHomeStructure() {
		return homeStructure;
	}
	public void setHomeStructure(String homeStructure) {
		this.homeStructure = homeStructure;
	}
	public String getHomeType() {
		return homeType;
	}
	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}
	public String getOtherDescription() {
		return otherDescription;
	}
	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}
	public String getPlatformEndoNo() {
		return platformEndoNo;
	}
	public void setPlatformEndoNo(String platformEndoNo) {
		this.platformEndoNo = platformEndoNo;
	}
	public String getPlatformPolicyNo() {
		return platformPolicyNo;
	}
	public void setPlatformPolicyNo(String platformPolicyNo) {
		this.platformPolicyNo = platformPolicyNo;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public String getSafeProductionLicenseNo() {
		return safeProductionLicenseNo;
	}
	public void setSafeProductionLicenseNo(String safeProductionLicenseNo) {
		this.safeProductionLicenseNo = safeProductionLicenseNo;
	}
	public List<PolicyRisk> getPolicyRiskList() {
		return policyRiskList;
	}
	public void setPolicyRiskList(List<PolicyRisk> policyRiskList) {
		this.policyRiskList = policyRiskList;
	}
	public List<AddressInfo> getAddressInfoList() {
		return addressInfoList;
	}
	public void setAddressInfoList(List<AddressInfo> addressInfoList) {
		this.addressInfoList = addressInfoList;
	}
	public List<PolicyDeductible> getPolicyDeductibleList() {
		return policyDeductibleList;
	}
	public void setPolicyDeductibleList(List<PolicyDeductible> policyDeductibleList) {
		this.policyDeductibleList = policyDeductibleList;
	}
	public List<LimitDeductible> getLimitDeductibleList() {
		return limitDeductibleList;
	}
	public void setLimitDeductibleList(List<LimitDeductible> limitDeductibleList) {
		this.limitDeductibleList = limitDeductibleList;
	}
	public List<HomeDetail> getHomeDetailList() {
		return homeDetailList;
	}
	public void setHomeDetailList(List<HomeDetail> homeDetailList) {
		this.homeDetailList = homeDetailList;
	}
	public List<SharedCoverageGroup> getSharedCoverageGroupList() {
		return sharedCoverageGroupList;
	}
	public void setSharedCoverageGroupList(
			List<SharedCoverageGroup> sharedCoverageGroupList) {
		this.sharedCoverageGroupList = sharedCoverageGroupList;
	}
	public List<PolicyForm> getPolicyFormList() {
		return policyFormList;
	}
	public void setPolicyFormList(List<PolicyForm> policyFormList) {
		this.policyFormList = policyFormList;
	}
	public List<ProductDetail> getProductDetailList() {
		return productDetailList;
	}
	public void setProductDetailList(List<ProductDetail> productDetailList) {
		this.productDetailList = productDetailList;
	}
	public List<NonMotorDetail> getNonMotorDetailList() {
		return nonMotorDetailList;
	}
	public void setNonMotorDetailList(List<NonMotorDetail> nonMotorDetailList) {
		this.nonMotorDetailList = nonMotorDetailList;
	}
	public List<LiftDetail> getLiftDetailList() {
		return liftDetailList;
	}
	public void setLiftDetailList(List<LiftDetail> liftDetailList) {
		this.liftDetailList = liftDetailList;
	}

}
