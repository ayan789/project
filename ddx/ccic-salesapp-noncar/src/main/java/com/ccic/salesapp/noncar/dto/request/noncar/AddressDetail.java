package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * ��ַ��Ϣ
 * @author Administrator
 *
 */
public class AddressDetail {
	private String address ;//��ַ
	private String countryCode ;//���Ҵ���
	private String fullAddress ;//��ַ
	private String insuredName ;//�������
	private int insuredSeatCount ;//Ͷ����λ��
	private String latitude ;//γ��
	private String longitude ;//����
	private String possessNatureCode ;//ռ������
	private String postCode ;//�ʱ�
	private String powerSupplyArea ;//��������
	private String regionCode ;//��������
	private String riskLevelCode ;//�������յȼ�
	private int totalBedCount ;//��λ��
	private Long sequenceNumber ;//���
    private Map<String, String> tempData;// ��ʱ����
    
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public int getInsuredSeatCount() {
		return insuredSeatCount;
	}
	public void setInsuredSeatCount(int insuredSeatCount) {
		this.insuredSeatCount = insuredSeatCount;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPossessNatureCode() {
		return possessNatureCode;
	}
	public void setPossessNatureCode(String possessNatureCode) {
		this.possessNatureCode = possessNatureCode;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPowerSupplyArea() {
		return powerSupplyArea;
	}
	public void setPowerSupplyArea(String powerSupplyArea) {
		this.powerSupplyArea = powerSupplyArea;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRiskLevelCode() {
		return riskLevelCode;
	}
	public void setRiskLevelCode(String riskLevelCode) {
		this.riskLevelCode = riskLevelCode;
	}
	public int getTotalBedCount() {
		return totalBedCount;
	}
	public void setTotalBedCount(int totalBedCount) {
		this.totalBedCount = totalBedCount;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
    
}
