package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * �����嵥
 * 
 * @author Administrator
 *
 */
public class LiftDetail {
	private Double approvalMass;// �˶�������(KG)
	private int approvalSeatCount;// �˶��ؿ�/�˶���λ�����˶��˿���λ����
	private Date commissioningDate;// Ͷ������
	private String liftBrandModel;// ����Ʒ���ͺ�
	private int liftCount;// ��������
	private String liftLicenseNo;// ����׼��֤/ʹ�õǼ�֤����
	private String liftPurpose;// ������;
	private String liftType;// ��������
	private String manuUnit;// ���쵥λ
	private String manuUnitLicenseNo;// ���쵥λ��ɱ��
	private Double speed;// �ٶ�(m/s)
	private String unitNo;// ��λ�ڱ��
	private int useSeniority;// ��ʹ������
	private Date validThru;// ��Ч����
	private Long sequenceNumber;// ���
	private Map<String, String> tempData;// ��ʱ����

	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Double getApprovalMass() {
		return approvalMass;
	}

	public void setApprovalMass(Double approvalMass) {
		this.approvalMass = approvalMass;
	}

	public int getApprovalSeatCount() {
		return approvalSeatCount;
	}

	public void setApprovalSeatCount(int approvalSeatCount) {
		this.approvalSeatCount = approvalSeatCount;
	}

	public Date getCommissioningDate() {
		return commissioningDate;
	}

	public void setCommissioningDate(Date commissioningDate) {
		this.commissioningDate = commissioningDate;
	}

	public String getLiftBrandModel() {
		return liftBrandModel;
	}

	public void setLiftBrandModel(String liftBrandModel) {
		this.liftBrandModel = liftBrandModel;
	}

	public int getLiftCount() {
		return liftCount;
	}

	public void setLiftCount(int liftCount) {
		this.liftCount = liftCount;
	}

	public String getLiftLicenseNo() {
		return liftLicenseNo;
	}

	public void setLiftLicenseNo(String liftLicenseNo) {
		this.liftLicenseNo = liftLicenseNo;
	}

	public String getLiftPurpose() {
		return liftPurpose;
	}

	public void setLiftPurpose(String liftPurpose) {
		this.liftPurpose = liftPurpose;
	}

	public String getLiftType() {
		return liftType;
	}

	public void setLiftType(String liftType) {
		this.liftType = liftType;
	}

	public String getManuUnit() {
		return manuUnit;
	}

	public void setManuUnit(String manuUnit) {
		this.manuUnit = manuUnit;
	}

	public String getManuUnitLicenseNo() {
		return manuUnitLicenseNo;
	}

	public void setManuUnitLicenseNo(String manuUnitLicenseNo) {
		this.manuUnitLicenseNo = manuUnitLicenseNo;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public int getUseSeniority() {
		return useSeniority;
	}

	public void setUseSeniority(int useSeniority) {
		this.useSeniority = useSeniority;
	}

	public Date getValidThru() {
		return validThru;
	}

	public void setValidThru(Date validThru) {
		this.validThru = validThru;
	}

	public Map<String, String> getTempData() {
		return tempData;
	}

	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
