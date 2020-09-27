package com.ccic.salesapp.noncar.dto.request.accidentquote;


/**
 * ��ũ����
 * @author Administrator
 *
 */
public class AgriSubsidy {
	//���
	private String sequenceNumber;
	//��������
	private String subsidyRate;
	//��������
	private String premiumType;
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getSubsidyRate() {
		return subsidyRate;
	}
	public void setSubsidyRate(String subsidyRate) {
		this.subsidyRate = subsidyRate;
	}
	public String getPremiumType() {
		return premiumType;
	}
	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}

}
