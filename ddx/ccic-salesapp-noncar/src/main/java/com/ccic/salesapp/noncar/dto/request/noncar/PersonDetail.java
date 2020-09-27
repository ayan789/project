package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * PersonDetailList����Ա��Ϣ)  
 * @author Administrator
 *
 */
public class PersonDetail {
	private String industryCode ;//��ҵ����
	private String insuredCode ;//������
	private String personName ;//����(��������������)
	private String personIdType ;//֤������
	private String personIdNo ;//֤������*
	private String genderCode ;//�Ա���,Ů��
	private int age ;//����
	private String personEducationCode ;//ѧ��(ר��,����,˶ʿ,��ʿ,����)
	private String licenseQualificationNo ;//ִҵ�ʸ�֤���
	private String licenseTypeName ;//ִ������(�����
	private String licenseNo ;//ִ�պ���
	private String issuerData ;//ǩ������
	private String representativeJobTitle ;//ְλ
	private String occupationQualification ;//ְҵ�ʸ�
	private int jobSeniority ;//��ҵ����
	private String personWorkType ;//��������
	private String personWork ;//����
	private Double yearSalaryAmount ;//�깤�ʶ�
	private Double yearUndertookAmount ;//�꾭�ֽ��
	private String comments ;//��ע��Ϣ/���
	private String incumbentJobTitleCode ;//���θ�λ/����
	private String hospitalComName ;//����ҽ�ƻ�������
	private String doctorCode ;//ҽ����Ա���
	private String jobType ;//��λ������ı���jiafa��
	private Double eachPersonPremium ;//ÿ�˻�������
	private int personCount ;//����
	private Double doctorPremium ;//ҽ����Ա����
	private String managerType ;//��������������
	private String personGroupNo ;//��Ա�������
	private String effectiveDate ;//�����ڣ��������ڣ�
	private String expiryDate ;//�ձ����ڣ�����ֹ�ڣ�
	private Long sequenceNumber ;//���
	private String BenefitPolHolderRelaCode ;//��Ͷ���˹�ϵ
	private Map<String,String> tempData ;//��ʱ����
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getInsuredCode() {
		return insuredCode;
	}
	public void setInsuredCode(String insuredCode) {
		this.insuredCode = insuredCode;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonIdType() {
		return personIdType;
	}
	public void setPersonIdType(String personIdType) {
		this.personIdType = personIdType;
	}
	public String getPersonIdNo() {
		return personIdNo;
	}
	public void setPersonIdNo(String personIdNo) {
		this.personIdNo = personIdNo;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPersonEducationCode() {
		return personEducationCode;
	}
	public void setPersonEducationCode(String personEducationCode) {
		this.personEducationCode = personEducationCode;
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
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getIssuerData() {
		return issuerData;
	}
	public void setIssuerData(String issuerData) {
		this.issuerData = issuerData;
	}
	public String getRepresentativeJobTitle() {
		return representativeJobTitle;
	}
	public void setRepresentativeJobTitle(String representativeJobTitle) {
		this.representativeJobTitle = representativeJobTitle;
	}
	public String getOccupationQualification() {
		return occupationQualification;
	}
	public void setOccupationQualification(String occupationQualification) {
		this.occupationQualification = occupationQualification;
	}
	public int getJobSeniority() {
		return jobSeniority;
	}
	public void setJobSeniority(int jobSeniority) {
		this.jobSeniority = jobSeniority;
	}
	public String getPersonWorkType() {
		return personWorkType;
	}
	public void setPersonWorkType(String personWorkType) {
		this.personWorkType = personWorkType;
	}
	public String getPersonWork() {
		return personWork;
	}
	public void setPersonWork(String personWork) {
		this.personWork = personWork;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getIncumbentJobTitleCode() {
		return incumbentJobTitleCode;
	}
	public void setIncumbentJobTitleCode(String incumbentJobTitleCode) {
		this.incumbentJobTitleCode = incumbentJobTitleCode;
	}
	public String getHospitalComName() {
		return hospitalComName;
	}
	public void setHospitalComName(String hospitalComName) {
		this.hospitalComName = hospitalComName;
	}
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public Double getEachPersonPremium() {
		return eachPersonPremium;
	}
	public void setEachPersonPremium(Double eachPersonPremium) {
		this.eachPersonPremium = eachPersonPremium;
	}
	public int getPersonCount() {
		return personCount;
	}
	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}
	public Double getDoctorPremium() {
		return doctorPremium;
	}
	public void setDoctorPremium(Double doctorPremium) {
		this.doctorPremium = doctorPremium;
	}
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	public String getPersonGroupNo() {
		return personGroupNo;
	}
	public void setPersonGroupNo(String personGroupNo) {
		this.personGroupNo = personGroupNo;
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
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public String getBenefitPolHolderRelaCode() {
		return BenefitPolHolderRelaCode;
	}
	public void setBenefitPolHolderRelaCode(String benefitPolHolderRelaCode) {
		BenefitPolHolderRelaCode = benefitPolHolderRelaCode;
	}

	
}
