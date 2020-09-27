package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;
/**
 * ��ַ��Ϣ
 * @author Administrator
 *
 */
public class AddressInfo {
	//�������ͱ������
	private String addressName;
	//�ʱ�
	private String postCode;
	//���Ҵ���
	private String countryCode;
	//��������
	private String regionCode;
	//��ַ
	private String address;
	//������ַ(�ڲ�����)
	private String fullAddress;
	//����
	private String name;
	//֤������
	private String idType;
	//֤������
	private String idNo;
	//Ӧ�ձ���/��ʵ����/RealPremium,=��������
	private double duePremium;
	//����
	private double sumAmout;
	//�����Ϣ
	private List<PolicyRisk> policyRiskList;
	private Map<String, String> tempData;// ��ʱ����
	private Long sequenceNumber;//���
	
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public double getDuePremium() {
		return duePremium;
	}
	public void setDuePremium(double duePremium) {
		this.duePremium = duePremium;
	}
	public double getSumAmout() {
		return sumAmout;
	}
	public void setSumAmout(double sumAmout) {
		this.sumAmout = sumAmout;
	}
	public List<PolicyRisk> getPolicyRiskList() {
		return policyRiskList;
	}
	public void setPolicyRiskList(List<PolicyRisk> policyRiskList) {
		this.policyRiskList = policyRiskList;
	}


}
