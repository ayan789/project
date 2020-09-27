package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;

/**
 * �����Ϣ ������
 * 
 * @author Administrator
 *
 */
public class PolicyRiskYEJ{

	private Double approvalQuality;// �˶�������\��ǣ������(��)
	private String cargoName;// ��������
	private String cargoType;// �������
	private String engineNo;// ��������
	private String licenseNo;// �����ƺ�
	private String packagingCount;// ��װ����
	private String packagingType;// ��װ��ʽ/����
	private String vehicleModelCode;// ����
	private String vin;// ���ܺ�
	private Double duePremium;// Ӧ�ձ���/��ʵ����/RealPremium,=��������
	private String effectiveDate;// ������/��ʼ��Ч����,֧��ʱ����
	private String expiryDate;// �ձ����ڣ�����ֹ��/ֹ������/ʧЧ���ڣ����������ʱ�������
	private String insuredCategory;// ��ķ���
	private String insuredDescription;// �������
	private String insuredName;// ������ƣ��ڲ����࣬����һ������Ӳ�ͬ�ı��������ȡֵ�����ڱ���/��ѯ��
	private String numberOfCopies;// ����
	private String planCode;// ��������
	private String policyStatus;// ����״̬��1δ��Ч2����Ч3ʧЧ4������ʱɾ��״̬��
	private Double premiumRate;// ����
	private Double sumInsured;// ����
	private String totalNumberOfCopies;// �ܷ������ŵ�ʱʹ�ã�=����*����
	private Double vat;// VAT���(��ֵ˰)
	private Double vatRate;// ��ֵ˰��
	private Map<String, String> tempData;// ��ʱ����
	private List<PolicyCoverage> policyCoveragList;// ��������Ϣ
	public Double getApprovalQuality() {
		return approvalQuality;
	}
	public void setApprovalQuality(Double approvalQuality) {
		this.approvalQuality = approvalQuality;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getCargoType() {
		return cargoType;
	}
	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getPackagingCount() {
		return packagingCount;
	}
	public void setPackagingCount(String packagingCount) {
		this.packagingCount = packagingCount;
	}
	public String getPackagingType() {
		return packagingType;
	}
	public void setPackagingType(String packagingType) {
		this.packagingType = packagingType;
	}
	public String getVehicleModelCode() {
		return vehicleModelCode;
	}
	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
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
	public String getInsuredCategory() {
		return insuredCategory;
	}
	public void setInsuredCategory(String insuredCategory) {
		this.insuredCategory = insuredCategory;
	}
	public String getInsuredDescription() {
		return insuredDescription;
	}
	public void setInsuredDescription(String insuredDescription) {
		this.insuredDescription = insuredDescription;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getNumberOfCopies() {
		return numberOfCopies;
	}
	public void setNumberOfCopies(String numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
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
	public Double getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(Double premiumRate) {
		this.premiumRate = premiumRate;
	}
	public Double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getTotalNumberOfCopies() {
		return totalNumberOfCopies;
	}
	public void setTotalNumberOfCopies(String totalNumberOfCopies) {
		this.totalNumberOfCopies = totalNumberOfCopies;
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
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public List<PolicyCoverage> getPolicyCoveragList() {
		return policyCoveragList;
	}
	public void setPolicyCoveragList(List<PolicyCoverage> policyCoveragList) {
		this.policyCoveragList = policyCoveragList;
	}

}
