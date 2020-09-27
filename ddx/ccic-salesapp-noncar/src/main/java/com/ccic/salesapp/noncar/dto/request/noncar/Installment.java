package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;


/**
 * ����������Ϣ
 * @author Administrator
 *
 */
public class Installment {
	private String dueDate;//             Ӧ������
	private String installmentAmount;//   ���ڽ��
	private String installmentDate;//     ��������
	private String installmentPeriodSeq;//�������
	private String vat;//                 VAT���(��ֵ˰)
	private String isMergePrint;//        �Ƿ�ϲ���ӡ
	private String ruleEngineRate;//      �������淵��Ӷ�����
	private Map<String,String> tempData; //                  ��ʱ����
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public String getInstallmentDate() {
		return installmentDate;
	}
	public void setInstallmentDate(String installmentDate) {
		this.installmentDate = installmentDate;
	}
	public String getInstallmentPeriodSeq() {
		return installmentPeriodSeq;
	}
	public void setInstallmentPeriodSeq(String installmentPeriodSeq) {
		this.installmentPeriodSeq = installmentPeriodSeq;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getIsMergePrint() {
		return isMergePrint;
	}
	public void setIsMergePrint(String isMergePrint) {
		this.isMergePrint = isMergePrint;
	}
	public String getRuleEngineRate() {
		return ruleEngineRate;
	}
	public void setRuleEngineRate(String ruleEngineRate) {
		this.ruleEngineRate = ruleEngineRate;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
