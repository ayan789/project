package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * ��ũ����
 * @author Administrator
 *
 */
public class AgriSubsidy {
	private Double subsidyRate;// ��������
	private int premiumType;// ��������
	private Long sequenceNumber; //���
	private Map<String,String> tempData;// ��ʱ����
	
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Double getSubsidyRate() {
		return subsidyRate;
	}
	public void setSubsidyRate(Double subsidyRate) {
		this.subsidyRate = subsidyRate;
	}
	public int getPremiumType() {
		return premiumType;
	}
	public void setPremiumType(int premiumType) {
		this.premiumType = premiumType;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
