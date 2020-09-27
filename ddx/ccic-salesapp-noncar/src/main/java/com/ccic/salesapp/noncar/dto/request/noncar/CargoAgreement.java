package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * ������ԤԼЭ��-������Ϣ
 * 
 * @author Administrator
 *
 */
public class CargoAgreement {
	private String cargoName;// ��������
	private String cargoType;// �������
	private int declareDuration;// �걨����
	private Double domesticMinimumPremium;// ������ͱ��շ�
	private Double estimateAnnualPremium;// Э��Ԥ���걣�շ�
	private Double estimateAnnualTraffic;// Э��Ԥ��������
	private int estimatedAnnualVolume;// Ԥ��������
	private String flightVehicleModelCode;// ���ó���
	private Double importExportMinimumPremium;// ��������ͱ��շ�
	private String isAllManualUw;// �Ƿ�ȫ���˹��˱�
	private String isFixedHolder;// �Ƿ�̶�Ͷ����
	private String isFixedInsured;// �Ƿ�̶���������
	private String isFixedSumInsured;// �Ƿ�̶�����
	private String isShieldPremiumRate;// �Ƿ����α��ѷ���
	private String isSpecialAgreementUser;// �Ƿ�����Э���û�
	private String issueUserType;// ����Ա����
	private Double limitPerVehicle;// ÿһ���乤���⳥�޶�
	private int maxShipAge;// �����
	private Double minimumPremium;// Э����ͱ��շ�
	private Double minimumPremiumRate;// Э����ͱ��շѱ���(С����ʽ�洢�İٷֱ�)
	private String packagingType;// ��װ��ʽ/����
	private Double perVehicleLimit;// ÿ������޶�
	private Double premiumRate;// ����
	private String proposalType;// Ͷ����ʽ
	private String settlementModeCode;// ���㷽ʽ
	private Double sumInsuredPromptValue;// ������ʾֵ
	private int vehicleCount;// ������
	private Long sequenceNumber;// ���
	private Map <String,String> tempData;// ��ʱ����
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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
	public int getDeclareDuration() {
		return declareDuration;
	}
	public void setDeclareDuration(int declareDuration) {
		this.declareDuration = declareDuration;
	}
	public Double getDomesticMinimumPremium() {
		return domesticMinimumPremium;
	}
	public void setDomesticMinimumPremium(Double domesticMinimumPremium) {
		this.domesticMinimumPremium = domesticMinimumPremium;
	}
	public Double getEstimateAnnualPremium() {
		return estimateAnnualPremium;
	}
	public void setEstimateAnnualPremium(Double estimateAnnualPremium) {
		this.estimateAnnualPremium = estimateAnnualPremium;
	}
	public Double getEstimateAnnualTraffic() {
		return estimateAnnualTraffic;
	}
	public void setEstimateAnnualTraffic(Double estimateAnnualTraffic) {
		this.estimateAnnualTraffic = estimateAnnualTraffic;
	}
	public int getEstimatedAnnualVolume() {
		return estimatedAnnualVolume;
	}
	public void setEstimatedAnnualVolume(int estimatedAnnualVolume) {
		this.estimatedAnnualVolume = estimatedAnnualVolume;
	}
	public String getFlightVehicleModelCode() {
		return flightVehicleModelCode;
	}
	public void setFlightVehicleModelCode(String flightVehicleModelCode) {
		this.flightVehicleModelCode = flightVehicleModelCode;
	}
	public Double getImportExportMinimumPremium() {
		return importExportMinimumPremium;
	}
	public void setImportExportMinimumPremium(Double importExportMinimumPremium) {
		this.importExportMinimumPremium = importExportMinimumPremium;
	}
	public String getIsAllManualUw() {
		return isAllManualUw;
	}
	public void setIsAllManualUw(String isAllManualUw) {
		this.isAllManualUw = isAllManualUw;
	}
	public String getIsFixedHolder() {
		return isFixedHolder;
	}
	public void setIsFixedHolder(String isFixedHolder) {
		this.isFixedHolder = isFixedHolder;
	}
	public String getIsFixedInsured() {
		return isFixedInsured;
	}
	public void setIsFixedInsured(String isFixedInsured) {
		this.isFixedInsured = isFixedInsured;
	}
	public String getIsFixedSumInsured() {
		return isFixedSumInsured;
	}
	public void setIsFixedSumInsured(String isFixedSumInsured) {
		this.isFixedSumInsured = isFixedSumInsured;
	}
	public String getIsShieldPremiumRate() {
		return isShieldPremiumRate;
	}
	public void setIsShieldPremiumRate(String isShieldPremiumRate) {
		this.isShieldPremiumRate = isShieldPremiumRate;
	}
	public String getIsSpecialAgreementUser() {
		return isSpecialAgreementUser;
	}
	public void setIsSpecialAgreementUser(String isSpecialAgreementUser) {
		this.isSpecialAgreementUser = isSpecialAgreementUser;
	}
	public String getIssueUserType() {
		return issueUserType;
	}
	public void setIssueUserType(String issueUserType) {
		this.issueUserType = issueUserType;
	}
	public Double getLimitPerVehicle() {
		return limitPerVehicle;
	}
	public void setLimitPerVehicle(Double limitPerVehicle) {
		this.limitPerVehicle = limitPerVehicle;
	}
	public int getMaxShipAge() {
		return maxShipAge;
	}
	public void setMaxShipAge(int maxShipAge) {
		this.maxShipAge = maxShipAge;
	}
	public Double getMinimumPremium() {
		return minimumPremium;
	}
	public void setMinimumPremium(Double minimumPremium) {
		this.minimumPremium = minimumPremium;
	}
	public Double getMinimumPremiumRate() {
		return minimumPremiumRate;
	}
	public void setMinimumPremiumRate(Double minimumPremiumRate) {
		this.minimumPremiumRate = minimumPremiumRate;
	}
	public String getPackagingType() {
		return packagingType;
	}
	public void setPackagingType(String packagingType) {
		this.packagingType = packagingType;
	}
	public Double getPerVehicleLimit() {
		return perVehicleLimit;
	}
	public void setPerVehicleLimit(Double perVehicleLimit) {
		this.perVehicleLimit = perVehicleLimit;
	}
	public Double getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(Double premiumRate) {
		this.premiumRate = premiumRate;
	}
	public String getProposalType() {
		return proposalType;
	}
	public void setProposalType(String proposalType) {
		this.proposalType = proposalType;
	}
	public String getSettlementModeCode() {
		return settlementModeCode;
	}
	public void setSettlementModeCode(String settlementModeCode) {
		this.settlementModeCode = settlementModeCode;
	}
	public Double getSumInsuredPromptValue() {
		return sumInsuredPromptValue;
	}
	public void setSumInsuredPromptValue(Double sumInsuredPromptValue) {
		this.sumInsuredPromptValue = sumInsuredPromptValue;
	}
	public int getVehicleCount() {
		return vehicleCount;
	}
	public void setVehicleCount(int vehicleCount) {
		this.vehicleCount = vehicleCount;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

	
}
