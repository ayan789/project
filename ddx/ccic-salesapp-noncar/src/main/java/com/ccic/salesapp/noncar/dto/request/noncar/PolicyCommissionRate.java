package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

public class PolicyCommissionRate {
	private Double defaultRate; //   Ĭ��Ӷ����
	private Double maxRate; //       ���Ӷ����
	private Double minRate; //       ��СӶ����
	private Double ruleEngineRate; //�������淵��Ӷ�����
	private Map<String,String> tempData; //��ʱ����
	private Long sequenceNumber ; //���
	
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Double getDefaultRate() {
		return defaultRate;
	}
	public void setDefaultRate(Double defaultRate) {
		this.defaultRate = defaultRate;
	}
	public Double getMaxRate() {
		return maxRate;
	}
	public void setMaxRate(Double maxRate) {
		this.maxRate = maxRate;
	}
	public Double getMinRate() {
		return minRate;
	}
	public void setMinRate(Double minRate) {
		this.minRate = minRate;
	}
	public Double getRuleEngineRate() {
		return ruleEngineRate;
	}
	public void setRuleEngineRate(Double ruleEngineRate) {
		this.ruleEngineRate = ruleEngineRate;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
