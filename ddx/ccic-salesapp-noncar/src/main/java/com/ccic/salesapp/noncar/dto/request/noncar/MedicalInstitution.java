package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * ҽ�ƻ�����Ϣ
 * @author Administrator
 *
 */
public class MedicalInstitution {
	private int emergeTreatPersonTime ;//�ż����˴�
	private int inHospitalPersonTime ;//סԺ�˴�
	private String licenseQualificationNo ;//ִҵ���֤/ִҵ�ʸ�֤���
	private String medicalInstituLevelCode ;//ҽ�ƻ����ȼ�
	private String medicalInstituNatureCode ;//ҽ�ƻ�������
	private String medicalInstituType ;//ҽ�ƻ������
	private int medicalStaffCount ;//ҽ����Ա����
	private Date openingDate ;//��ҵ���\����ʱ��
	private int sickbedCount ;//������
	private int surgeryPersonTime ;//�����˴�
	private Long sequenceNumber ;//���
    private Map<String, String> tempData;// ��ʱ����
    
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public int getEmergeTreatPersonTime() {
		return emergeTreatPersonTime;
	}
	public void setEmergeTreatPersonTime(int emergeTreatPersonTime) {
		this.emergeTreatPersonTime = emergeTreatPersonTime;
	}
	public int getInHospitalPersonTime() {
		return inHospitalPersonTime;
	}
	public void setInHospitalPersonTime(int inHospitalPersonTime) {
		this.inHospitalPersonTime = inHospitalPersonTime;
	}
	public String getLicenseQualificationNo() {
		return licenseQualificationNo;
	}
	public void setLicenseQualificationNo(String licenseQualificationNo) {
		this.licenseQualificationNo = licenseQualificationNo;
	}
	public String getMedicalInstituLevelCode() {
		return medicalInstituLevelCode;
	}
	public void setMedicalInstituLevelCode(String medicalInstituLevelCode) {
		this.medicalInstituLevelCode = medicalInstituLevelCode;
	}
	public String getMedicalInstituNatureCode() {
		return medicalInstituNatureCode;
	}
	public void setMedicalInstituNatureCode(String medicalInstituNatureCode) {
		this.medicalInstituNatureCode = medicalInstituNatureCode;
	}
	public String getMedicalInstituType() {
		return medicalInstituType;
	}
	public void setMedicalInstituType(String medicalInstituType) {
		this.medicalInstituType = medicalInstituType;
	}
	public int getMedicalStaffCount() {
		return medicalStaffCount;
	}
	public void setMedicalStaffCount(int medicalStaffCount) {
		this.medicalStaffCount = medicalStaffCount;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public int getSickbedCount() {
		return sickbedCount;
	}
	public void setSickbedCount(int sickbedCount) {
		this.sickbedCount = sickbedCount;
	}
	public int getSurgeryPersonTime() {
		return surgeryPersonTime;
	}
	public void setSurgeryPersonTime(int surgeryPersonTime) {
		this.surgeryPersonTime = surgeryPersonTime;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
    
    
}
