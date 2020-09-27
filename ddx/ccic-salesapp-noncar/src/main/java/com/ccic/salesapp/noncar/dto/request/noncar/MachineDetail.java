package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * �����豸�嵥��Ϣ
 * @author Administrator
 *
 */
public class MachineDetail {
	private String annualInspectionCertNo ;//���֤����
	private String cisternType ;//��������
	private Date commissioningDate ;//Ͷ������
	private String engineNo ;//��������
	private String equipmentName ;//�豸����
	private String equipmentType ;//�豸���ͣ���¯/ũ�����벻һ��
	private Date factoryDate ;//��������
	private String factoryNo ;//�������
	private String isSingle ;//��̨/��̨�豸
	private String machineLicenseNo ;//���豸ʹ��֤����
	private Date machineLicenseNoValidThru ;//���豸ʹ��֤����Ч��
	private String manuUnitLicenseNo ;//���쵥λ��ɱ��
	private String manufacturer ;//�������ҡ����쵥λ
	private String model ;//�ͺ�/���/����
	private String modelCode ;//�ͺ�/���/����
	private Double newEquiPurchPrice ;//���豸���ü�
	private String productionPurpose ;//������;
	private String specialEquipLicenseNo ;//�����豸ʹ�õǼ�֤����
	private Date specialEquipLicvalidThru ;//�����豸��Ч����
	private String specialEquipType ;//�����豸����
	private int tonnage ;//��λ
	private String unitNo ;//��λ�ڱ��
	private String vin ;//���ܺ�/VIN��
	private int volume ;//�ݻ�
	private Long sequenceNumber ;//���
    private Map<String, String> tempData;// ��ʱ����
    
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getAnnualInspectionCertNo() {
		return annualInspectionCertNo;
	}
	public void setAnnualInspectionCertNo(String annualInspectionCertNo) {
		this.annualInspectionCertNo = annualInspectionCertNo;
	}
	public String getCisternType() {
		return cisternType;
	}
	public void setCisternType(String cisternType) {
		this.cisternType = cisternType;
	}
	public Date getCommissioningDate() {
		return commissioningDate;
	}
	public void setCommissioningDate(Date commissioningDate) {
		this.commissioningDate = commissioningDate;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public Date getFactoryDate() {
		return factoryDate;
	}
	public void setFactoryDate(Date factoryDate) {
		this.factoryDate = factoryDate;
	}
	public String getFactoryNo() {
		return factoryNo;
	}
	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}
	public String getIsSingle() {
		return isSingle;
	}
	public void setIsSingle(String isSingle) {
		this.isSingle = isSingle;
	}
	public String getMachineLicenseNo() {
		return machineLicenseNo;
	}
	public void setMachineLicenseNo(String machineLicenseNo) {
		this.machineLicenseNo = machineLicenseNo;
	}
	public Date getMachineLicenseNoValidThru() {
		return machineLicenseNoValidThru;
	}
	public void setMachineLicenseNoValidThru(Date machineLicenseNoValidThru) {
		this.machineLicenseNoValidThru = machineLicenseNoValidThru;
	}
	public String getManuUnitLicenseNo() {
		return manuUnitLicenseNo;
	}
	public void setManuUnitLicenseNo(String manuUnitLicenseNo) {
		this.manuUnitLicenseNo = manuUnitLicenseNo;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public Double getNewEquiPurchPrice() {
		return newEquiPurchPrice;
	}
	public void setNewEquiPurchPrice(Double newEquiPurchPrice) {
		this.newEquiPurchPrice = newEquiPurchPrice;
	}
	public String getProductionPurpose() {
		return productionPurpose;
	}
	public void setProductionPurpose(String productionPurpose) {
		this.productionPurpose = productionPurpose;
	}
	public String getSpecialEquipLicenseNo() {
		return specialEquipLicenseNo;
	}
	public void setSpecialEquipLicenseNo(String specialEquipLicenseNo) {
		this.specialEquipLicenseNo = specialEquipLicenseNo;
	}
	public Date getSpecialEquipLicvalidThru() {
		return specialEquipLicvalidThru;
	}
	public void setSpecialEquipLicvalidThru(Date specialEquipLicvalidThru) {
		this.specialEquipLicvalidThru = specialEquipLicvalidThru;
	}
	public String getSpecialEquipType() {
		return specialEquipType;
	}
	public void setSpecialEquipType(String specialEquipType) {
		this.specialEquipType = specialEquipType;
	}
	public int getTonnage() {
		return tonnage;
	}
	public void setTonnage(int tonnage) {
		this.tonnage = tonnage;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
    
    
}
