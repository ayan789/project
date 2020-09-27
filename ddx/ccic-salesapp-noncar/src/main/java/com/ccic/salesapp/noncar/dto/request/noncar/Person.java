package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * ��Ա��Ϣ
 * @author Administrator
 *
 */
public class Person {
	private int age ;//����
	private String comments ;//��ע��Ϣ/���
	private String doctorCode ;//ҽ����Ա���
	private Date effectiveDate ;//�����ڣ��������ڣ�
	private String elementType ;//����Ԫ������
	private Date expiryDate ;//�ձ����ڣ�����ֹ�ڣ�
	private String genderCode ;//�Ա���,Ů��
	private String hospitalComName ;//����ҽ�ƻ�������
	private String incumbentJobTitleCode ;//���θ�λ/����
	private String insuredCode ;//������
	private Date issuerData ;//ǩ������
	private int jobSeniority ;//��ҵ����
	private String licenseNo ;//ִ�պ���
	private String licenseQualificationNo ;//ִҵ�ʸ�֤���
	private String licenseTypeName ;//ִ������(�����
	private String managerType ;//��������������
	private String occupationQualification ;//ְҵ�ʸ�
	private String parentElementType ;//�ϼ�����Ԫ������
	private Long parentPolicyElementId ;//�ϼ�����Ԫ��ID
	private String personEducationCode ;//ѧ��(ר��,����,˶ʿ,��ʿ,����)
	private String personIdNo ;//֤������*
	private String personIdType ;//֤������
	private String personName ;//����(��������������)
	private String personWork ;//����
	private String personWorkType ;//��������
	private int policyStatus ;//����״̬��1δ��Ч2����Ч3ʧЧ4������ʱɾ��״̬��
	private String representativeJobTitle ;//ְλ
	private Long sequenceNumber ;//���
	private Double yearSalaryAmount ;//�깤�ʶ�
	private Double yearUndertookAmount ;//�꾭�ֽ��
    private Map<String, String> tempData;// ��ʱ����
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
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
	public String getHospitalComName() {
		return hospitalComName;
	}
	public void setHospitalComName(String hospitalComName) {
		this.hospitalComName = hospitalComName;
	}
	public String getIncumbentJobTitleCode() {
		return incumbentJobTitleCode;
	}
	public void setIncumbentJobTitleCode(String incumbentJobTitleCode) {
		this.incumbentJobTitleCode = incumbentJobTitleCode;
	}
	public String getInsuredCode() {
		return insuredCode;
	}
	public void setInsuredCode(String insuredCode) {
		this.insuredCode = insuredCode;
	}
	public Date getIssuerData() {
		return issuerData;
	}
	public void setIssuerData(Date issuerData) {
		this.issuerData = issuerData;
	}
	public int getJobSeniority() {
		return jobSeniority;
	}
	public void setJobSeniority(int jobSeniority) {
		this.jobSeniority = jobSeniority;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLicenseQualificationNo() {
		return licenseQualificationNo;
	}
	public void setLicenseQualificationNo(String licenseQualificationNo) {
		this.licenseQualificationNo = licenseQualificationNo;
	}
	public String getLicenseTypeName() {
		return licenseTypeName;
	}
	public void setLicenseTypeName(String licenseTypeName) {
		this.licenseTypeName = licenseTypeName;
	}
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	public String getOccupationQualification() {
		return occupationQualification;
	}
	public void setOccupationQualification(String occupationQualification) {
		this.occupationQualification = occupationQualification;
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
	public String getPersonEducationCode() {
		return personEducationCode;
	}
	public void setPersonEducationCode(String personEducationCode) {
		this.personEducationCode = personEducationCode;
	}
	public String getPersonIdNo() {
		return personIdNo;
	}
	public void setPersonIdNo(String personIdNo) {
		this.personIdNo = personIdNo;
	}
	public String getPersonIdType() {
		return personIdType;
	}
	public void setPersonIdType(String personIdType) {
		this.personIdType = personIdType;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonWork() {
		return personWork;
	}
	public void setPersonWork(String personWork) {
		this.personWork = personWork;
	}
	public String getPersonWorkType() {
		return personWorkType;
	}
	public void setPersonWorkType(String personWorkType) {
		this.personWorkType = personWorkType;
	}
	public int getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(int policyStatus) {
		this.policyStatus = policyStatus;
	}
	public String getRepresentativeJobTitle() {
		return representativeJobTitle;
	}
	public void setRepresentativeJobTitle(String representativeJobTitle) {
		this.representativeJobTitle = representativeJobTitle;
	}
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Double getYearSalaryAmount() {
		return yearSalaryAmount;
	}
	public void setYearSalaryAmount(Double yearSalaryAmount) {
		this.yearSalaryAmount = yearSalaryAmount;
	}
	public Double getYearUndertookAmount() {
		return yearUndertookAmount;
	}
	public void setYearUndertookAmount(Double yearUndertookAmount) {
		this.yearUndertookAmount = yearUndertookAmount;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
    
    
}
