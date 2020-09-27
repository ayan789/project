package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * �໤�������ձ��໤����Ϣ
 * @author Administrator
 *
 */
public class Ward {
	private String age;//���໤������
	private String classes;//���໤�˰༶
	private String dateOfBirth;//��������
	private String effectiveDate;//������
	private String expiryDate;//�ձ�����
	private String genderCode;//���໤���Ա�
	private String idNo;//���໤��֤������
	private String idType;//���໤��֤������
	private String name;//���໤������
	private String schoolRegionCode;//ѧУ/����
	private Long sequenceNumber ;//���
	private Map<String, String> tempData;// ��ʱ����
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchoolRegionCode() {
		return schoolRegionCode;
	}
	public void setSchoolRegionCode(String schoolRegionCode) {
		this.schoolRegionCode = schoolRegionCode;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}
