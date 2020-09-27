package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * �����嵥��Ϣ
 * 
 * @author Administrator
 *
 */
public class HomeDetail {
	private Date buildDate;// ����ʱ��/����
	private int buildingArea;// �������
	private int buildingFloor;// ����¥��
	private int buildingLayer;// ��������
	private int buildingType;// ��������
	private Date decorateDate;// װ��ʱ��
	private String homeContractNo;// ������Լ��
	private String homePurposeCode;// ������;����ס�����ޣ�
	private String homeStructure;// ���ݽṹ
	private String homeType;// �������ͣ���ס����ס���á����⡢������
	private String houseAddress;// ���ݵ�ַ���嵥��
	private String housingType;// סլ����
	private String isDecorate;// �Ƿ�װ��
	private Double loanAmount;// ������
	private String loanApproveNo;// ������������
	private String loanNatureCode;// ��������
	private String loanOrg;// �������
	private String loanTerm;// ��������
	private String pawnRegNo;// ��Ѻ��ǼǱ��
	private String propertyNo;// ��Ȩ֤��
	private Double squareMeterCost;// ÿƽ�����
	private Double totalPrice;// �ܼۿ�
	private Map<String, String> tempData;// ��ʱ����
	private Long sequenceNumber;// ���

	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Date getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	public int getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(int buildingArea) {
		this.buildingArea = buildingArea;
	}

	public int getBuildingFloor() {
		return buildingFloor;
	}

	public void setBuildingFloor(int buildingFloor) {
		this.buildingFloor = buildingFloor;
	}

	public int getBuildingLayer() {
		return buildingLayer;
	}

	public void setBuildingLayer(int buildingLayer) {
		this.buildingLayer = buildingLayer;
	}

	public int getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(int buildingType) {
		this.buildingType = buildingType;
	}

	public Date getDecorateDate() {
		return decorateDate;
	}

	public void setDecorateDate(Date decorateDate) {
		this.decorateDate = decorateDate;
	}

	public String getHomeContractNo() {
		return homeContractNo;
	}

	public void setHomeContractNo(String homeContractNo) {
		this.homeContractNo = homeContractNo;
	}

	public String getHomePurposeCode() {
		return homePurposeCode;
	}

	public void setHomePurposeCode(String homePurposeCode) {
		this.homePurposeCode = homePurposeCode;
	}

	public String getHomeStructure() {
		return homeStructure;
	}

	public void setHomeStructure(String homeStructure) {
		this.homeStructure = homeStructure;
	}

	public String getHomeType() {
		return homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getHousingType() {
		return housingType;
	}

	public void setHousingType(String housingType) {
		this.housingType = housingType;
	}

	public String getIsDecorate() {
		return isDecorate;
	}

	public void setIsDecorate(String isDecorate) {
		this.isDecorate = isDecorate;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanApproveNo() {
		return loanApproveNo;
	}

	public void setLoanApproveNo(String loanApproveNo) {
		this.loanApproveNo = loanApproveNo;
	}

	public String getLoanNatureCode() {
		return loanNatureCode;
	}

	public void setLoanNatureCode(String loanNatureCode) {
		this.loanNatureCode = loanNatureCode;
	}

	public String getLoanOrg() {
		return loanOrg;
	}

	public void setLoanOrg(String loanOrg) {
		this.loanOrg = loanOrg;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getPawnRegNo() {
		return pawnRegNo;
	}

	public void setPawnRegNo(String pawnRegNo) {
		this.pawnRegNo = pawnRegNo;
	}

	public String getPropertyNo() {
		return propertyNo;
	}

	public void setPropertyNo(String propertyNo) {
		this.propertyNo = propertyNo;
	}

	public Double getSquareMeterCost() {
		return squareMeterCost;
	}

	public void setSquareMeterCost(Double squareMeterCost) {
		this.squareMeterCost = squareMeterCost;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Map<String, String> getTempData() {
		return tempData;
	}

	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
