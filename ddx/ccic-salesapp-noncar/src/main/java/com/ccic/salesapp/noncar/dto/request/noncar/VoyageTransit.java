package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * ת����Ϣ
 * @author Administrator
 *
 */
public class VoyageTransit {
	private String conveyanceInfo;// ���乤����Ϣ������/���ƺ�/���κ�/����ţ�
	private int totalTonnage;// �ܶ�λ
	private String trackingNo;// �˵���
	private String transitAddress;// ת�˵�ַ
	private String transitCityCode;// ת�˳���
	private String transitCountryCode;// ת�˹���
	private String transitPortCode;// ת�˸ۿ�
	private String transportType;// ���䷽ʽ
	private int vesselAge;// ����
	private Long sequenceNumber;// ���
	private Map<String, String> tempData;// ��ʱ����
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getConveyanceInfo() {
		return conveyanceInfo;
	}
	public void setConveyanceInfo(String conveyanceInfo) {
		this.conveyanceInfo = conveyanceInfo;
	}
	public int getTotalTonnage() {
		return totalTonnage;
	}
	public void setTotalTonnage(int totalTonnage) {
		this.totalTonnage = totalTonnage;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getTransitAddress() {
		return transitAddress;
	}
	public void setTransitAddress(String transitAddress) {
		this.transitAddress = transitAddress;
	}
	public String getTransitCityCode() {
		return transitCityCode;
	}
	public void setTransitCityCode(String transitCityCode) {
		this.transitCityCode = transitCityCode;
	}
	public String getTransitCountryCode() {
		return transitCountryCode;
	}
	public void setTransitCountryCode(String transitCountryCode) {
		this.transitCountryCode = transitCountryCode;
	}
	public String getTransitPortCode() {
		return transitPortCode;
	}
	public void setTransitPortCode(String transitPortCode) {
		this.transitPortCode = transitPortCode;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public int getVesselAge() {
		return vesselAge;
	}
	public void setVesselAge(int vesselAge) {
		this.vesselAge = vesselAge;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	
	
}
