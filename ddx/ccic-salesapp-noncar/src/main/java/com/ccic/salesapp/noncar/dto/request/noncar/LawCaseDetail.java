package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * ���ϰ�����Ϣ
 * @author Administrator
 *
 */
public class LawCaseDetail {
	private String behaviorPreservation ;//����Ϊ��ȫ
	private String caseNo ;//������
	private String caseStatus ;//����״̬
	private String caseType ;//��������
	private String courtName ;//��Ժ����
	private String courtType ;//��Ժ����
	private String evidencePreservation ;//��֤�ݱ�ȫ
	private String insuranceCoverage ;//��������
	private Long lawCaseId ;//���ϰ����嵥ID��������
	private String lawFirmName ;//������ί����������
	private Double lawInsuredAmount ;//���ϱ�Ķ�
	private String preservationInsuredDesc ;//��ȫ�������
	private String preservationInsuredType ;//��ȫ������ͺͱ�ȫģʽ
	private String repondentByName ;//��ȫ������������
	private String repondentName ;//��ȫ����������
	private Long sequenceNumber ;//���
	private Map<String, String> tempData;// ��ʱ����
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getBehaviorPreservation() {
		return behaviorPreservation;
	}
	public void setBehaviorPreservation(String behaviorPreservation) {
		this.behaviorPreservation = behaviorPreservation;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getCourtType() {
		return courtType;
	}
	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}
	public String getEvidencePreservation() {
		return evidencePreservation;
	}
	public void setEvidencePreservation(String evidencePreservation) {
		this.evidencePreservation = evidencePreservation;
	}
	public String getInsuranceCoverage() {
		return insuranceCoverage;
	}
	public void setInsuranceCoverage(String insuranceCoverage) {
		this.insuranceCoverage = insuranceCoverage;
	}
	public Long getLawCaseId() {
		return lawCaseId;
	}
	public void setLawCaseId(Long lawCaseId) {
		this.lawCaseId = lawCaseId;
	}
	public String getLawFirmName() {
		return lawFirmName;
	}
	public void setLawFirmName(String lawFirmName) {
		this.lawFirmName = lawFirmName;
	}
	public Double getLawInsuredAmount() {
		return lawInsuredAmount;
	}
	public void setLawInsuredAmount(Double lawInsuredAmount) {
		this.lawInsuredAmount = lawInsuredAmount;
	}
	public String getPreservationInsuredDesc() {
		return preservationInsuredDesc;
	}
	public void setPreservationInsuredDesc(String preservationInsuredDesc) {
		this.preservationInsuredDesc = preservationInsuredDesc;
	}
	public String getPreservationInsuredType() {
		return preservationInsuredType;
	}
	public void setPreservationInsuredType(String preservationInsuredType) {
		this.preservationInsuredType = preservationInsuredType;
	}
	public String getRepondentByName() {
		return repondentByName;
	}
	public void setRepondentByName(String repondentByName) {
		this.repondentByName = repondentByName;
	}
	public String getRepondentName() {
		return repondentName;
	}
	public void setRepondentName(String repondentName) {
		this.repondentName = repondentName;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	
	
}
