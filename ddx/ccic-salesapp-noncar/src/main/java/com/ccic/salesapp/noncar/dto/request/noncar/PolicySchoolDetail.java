package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * ѧУ�嵥��Ϣ
 * @author Administrator
 *
 */
public class PolicySchoolDetail {
	private String address ;//��ַ
	private String adminDivision ;//��������У԰�������գ�
	private String schoolName ;//ѧУ���ƣ�У԰�������գ�
	private String schoolNatureCode ;//ѧУ����
	private String schoolType ;//ѧУ���
	private String thirdPartyLiability ;//����������
	private Long sequenceNumber ;//���
	private Map<String, String> tempData;// ��ʱ����
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAdminDivision() {
		return adminDivision;
	}
	public void setAdminDivision(String adminDivision) {
		this.adminDivision = adminDivision;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolNatureCode() {
		return schoolNatureCode;
	}
	public void setSchoolNatureCode(String schoolNatureCode) {
		this.schoolNatureCode = schoolNatureCode;
	}
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	public String getThirdPartyLiability() {
		return thirdPartyLiability;
	}
	public void setThirdPartyLiability(String thirdPartyLiability) {
		this.thirdPartyLiability = thirdPartyLiability;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	
	
}
