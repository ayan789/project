package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * �ǻ���������Ϣ
 * 
 * @author Administrator
 *
 */
public class NonMotorDetail {

	private String buyDate;// ��������
	private String color;// ������ɫ
	private String idNo;// ���֤��
	private String insuredName;// ������������
	private String motorNo;// ���ƺ�/���κ�/�����
	private String outputProductionDate;// ��������
	private String productionManufacturer;// ��������
	private Double purchasePrice;// ���ü�
	private String registerLicenseNo;// �ǼǺ���
	private Double sumInsured;// ���ս��/����
	private String vehicleCountCode;// ����/����
	private String vehicleModel;// �ͺ�
	private String vin;// ���ܺ�/VIN��
	private Long sequenceNumber;// ���
	private Map<String, String> tempData;// ��ʱ����
	

	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getMotorNo() {
		return motorNo;
	}

	public void setMotorNo(String motorNo) {
		this.motorNo = motorNo;
	}

	public String getProductionManufacturer() {
		return productionManufacturer;
	}

	public void setProductionManufacturer(String productionManufacturer) {
		this.productionManufacturer = productionManufacturer;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getRegisterLicenseNo() {
		return registerLicenseNo;
	}

	public void setRegisterLicenseNo(String registerLicenseNo) {
		this.registerLicenseNo = registerLicenseNo;
	}

	public Double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getVehicleCountCode() {
		return vehicleCountCode;
	}

	public void setVehicleCountCode(String vehicleCountCode) {
		this.vehicleCountCode = vehicleCountCode;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Map<String, String> getTempData() {
		return tempData;
	}

	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getOutputProductionDate() {
		return outputProductionDate;
	}

	public void setOutputProductionDate(String outputProductionDate) {
		this.outputProductionDate = outputProductionDate;
	}

}
