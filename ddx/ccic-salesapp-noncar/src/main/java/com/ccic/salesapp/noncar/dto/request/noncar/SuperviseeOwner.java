package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * �������������Ϣ
 * @author Administrator
 *
 */
public class SuperviseeOwner {
	private Double annualTurnover ;//��Ӫҵ��
	private String creditRating ;//��������
	private Double registeredCapital ;//ע���ʽ�
	private String supervisionAddress ;//��ܵص�
	private String supervisionItemName ;//�����Ʒ����
	private String supervisionItemOwnerName ;//�����Ʒ������
	private String supervisionItemType ;//�����Ʒ����
	private Double supervisionItemValue ;//�����Ʒ��ֵ
	private String supervisionTerm ;//�������
	private Double totalAsset ;//���ʲ�
	private Long sequenceNumber ;//���
	private Map<String, String> tempData;// ��ʱ����
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Double getAnnualTurnover() {
		return annualTurnover;
	}
	public void setAnnualTurnover(Double annualTurnover) {
		this.annualTurnover = annualTurnover;
	}
	public String getCreditRating() {
		return creditRating;
	}
	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	public Double getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getSupervisionAddress() {
		return supervisionAddress;
	}
	public void setSupervisionAddress(String supervisionAddress) {
		this.supervisionAddress = supervisionAddress;
	}
	public String getSupervisionItemName() {
		return supervisionItemName;
	}
	public void setSupervisionItemName(String supervisionItemName) {
		this.supervisionItemName = supervisionItemName;
	}
	public String getSupervisionItemOwnerName() {
		return supervisionItemOwnerName;
	}
	public void setSupervisionItemOwnerName(String supervisionItemOwnerName) {
		this.supervisionItemOwnerName = supervisionItemOwnerName;
	}
	public String getSupervisionItemType() {
		return supervisionItemType;
	}
	public void setSupervisionItemType(String supervisionItemType) {
		this.supervisionItemType = supervisionItemType;
	}
	public Double getSupervisionItemValue() {
		return supervisionItemValue;
	}
	public void setSupervisionItemValue(Double supervisionItemValue) {
		this.supervisionItemValue = supervisionItemValue;
	}
	public String getSupervisionTerm() {
		return supervisionTerm;
	}
	public void setSupervisionTerm(String supervisionTerm) {
		this.supervisionTerm = supervisionTerm;
	}
	public Double getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	
	
}
