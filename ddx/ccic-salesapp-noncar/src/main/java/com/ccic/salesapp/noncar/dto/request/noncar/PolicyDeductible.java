package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * ������Ϣ
 * @author Administrator
 *
 */
public class PolicyDeductible {
	private String fullContent;//	�����������ݣ�����ģ�������Լ�����ֵƴ��
	private Double deductibleAmount;//�����
    private Double deductibleRate;//	������
    private Long sequenceNumber;// ���
    private Map<String, String> tempData;// ��ʱ����
    
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getFullContent() {
		return fullContent;
	}
	public void setFullContent(String fullContent) {
		this.fullContent = fullContent;
	}
	public Double getDeductibleRate() {
		return deductibleRate;
	}
	public void setDeductibleRate(double deductibleRate) {
		this.deductibleRate = deductibleRate;
	}
	public Double getDeductibleAmount() {
		return deductibleAmount;
	}
	public void setDeductibleAmount(Double deductibleAmount) {
		this.deductibleAmount = deductibleAmount;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public void setDeductibleRate(Double deductibleRate) {
		this.deductibleRate = deductibleRate;
	}

}
