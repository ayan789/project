package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * �꼶��Ϣ
 * @author Administrator
 *
 */
public class GradeInfo {
	private int additionalInsuredCount ;//���ӱ���������
	private int enrolledStudentCount ;//�ڲ�ѧ����
	private String gradeName ;//�꼶
	private int insuredStudentCount ;//Ͷ��ѧ����
	private String isCommonInsured ;//�Ƿ�ͬ��������
	private String proposalType ;//Ͷ����ʽ
	private int schoolId ;//ѧУID(��ʾΪ����)
	private String schoolSystemCode ;//ѧ��
	private int teacherCount ;//��ǲ��ʦ����
	private Long sequenceNumber ;//���
    private Map<String, String> tempData;// ��ʱ����
    
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public int getAdditionalInsuredCount() {
		return additionalInsuredCount;
	}
	public void setAdditionalInsuredCount(int additionalInsuredCount) {
		this.additionalInsuredCount = additionalInsuredCount;
	}
	public int getEnrolledStudentCount() {
		return enrolledStudentCount;
	}
	public void setEnrolledStudentCount(int enrolledStudentCount) {
		this.enrolledStudentCount = enrolledStudentCount;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public int getInsuredStudentCount() {
		return insuredStudentCount;
	}
	public void setInsuredStudentCount(int insuredStudentCount) {
		this.insuredStudentCount = insuredStudentCount;
	}
	public String getIsCommonInsured() {
		return isCommonInsured;
	}
	public void setIsCommonInsured(String isCommonInsured) {
		this.isCommonInsured = isCommonInsured;
	}
	public String getProposalType() {
		return proposalType;
	}
	public void setProposalType(String proposalType) {
		this.proposalType = proposalType;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolSystemCode() {
		return schoolSystemCode;
	}
	public void setSchoolSystemCode(String schoolSystemCode) {
		this.schoolSystemCode = schoolSystemCode;
	}
	public int getTeacherCount() {
		return teacherCount;
	}
	public void setTeacherCount(int teacherCount) {
		this.teacherCount = teacherCount;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
    
    
}
