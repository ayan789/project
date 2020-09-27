package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * ѧ���嵥��Ϣ
 * @author Administrator
 *
 */
public class StudentInfo {
	private int age ;//����
	private String classes ;//�༶
	private Date dateOfBirth ;//��������
	private Date effectiveDate ;//������
	private String elementType ;//����Ԫ������
	private Date expiryDate ;//�ձ�����
	private String genderCode ;//�Ա�
	private String idNo ;//���֤��/ѧ��
	private String parentElementType ;//�ϼ�����Ԫ������
	private Long parentPolicyElementId ;//�ϼ�����Ԫ��ID
	private int policyStatus ;//����״̬��1δ��Ч2����Ч3ʧЧ4������ʱɾ��״̬��
	private Long schoolId ;//ѧУID(��ʾΪ����)
	private Long sequenceNumber ;//���
	private String studentName ;//ѧ������
	private Map<String, String> tempData;// ��ʱ����
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getParentElementType() {
		return parentElementType;
	}
	public void setParentElementType(String parentElementType) {
		this.parentElementType = parentElementType;
	}
	public Long getParentPolicyElementId() {
		return parentPolicyElementId;
	}
	public void setParentPolicyElementId(Long parentPolicyElementId) {
		this.parentPolicyElementId = parentPolicyElementId;
	}
	public int getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(int policyStatus) {
		this.policyStatus = policyStatus;
	}
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
