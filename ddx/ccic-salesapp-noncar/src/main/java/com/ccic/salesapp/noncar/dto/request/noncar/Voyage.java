package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * �����ա�ԤԼЭ��-������Ϣ
 * 
 * @author Administrator
 *
 */
public class Voyage {
	private Double addPremiumRate;// ���շ���(DC:�Ƿ��Ǽ����ϴ����ʣ�������Ϣ)
	private String claimAddress;// �⸶�ص�
	private String conveyanceInfo;// ���乤����Ϣ���������䷽ʽ��ͬ����ʾ��ͬ���ƣ�
	private String departureAddress;// ���˵�ַ
	private String departureCityCode;// ���˳���
	private String departureContinentCode;// ������
	private String departureCountryCode;// ���˹���
	private Date departureDate;// ��������
	private String departurePortCode;// ���˸ۿ�
	private String departureProvinceCode;// ����ʡ
	private String destinationAddress;// Ŀ�ĵ�ַ
	private String destinationCityCode;// Ŀ�ĳ���
	private String destinationContinentCode;// Ŀ����
	private String destinationCountryCode;// Ŀ�Ĺ���
	private String destinationPortCode;// Ŀ�ĸۿ�
	private String destinationProvinceCode;// Ŀ��ʡ
	private String flightNo;// �����
	private String flightProduct;// ���ò�Ʒ
	private String inspectAgentName;// ���������
	private String insuredCondition;// �б�����(DC:�Ƿ��ǻ���б�������Э���ϣ��������ģ��)
	private String motorNo;// ���ƺ�/���κ�
	private String parcelNo;// �ʰ���
	private int totalTonnage;// �ܶ�λ
	private String trackingNo;// �˵���
	private String transportType;// ���䷽ʽ
	private String vehicleLicenseNo;// ���ƺ�/���κ�
	private int vesselAge;// ����
	private String vesselNameCn;// ��������
	private String vesselNameEn;// ����Ӣ��
	private String voyageNo;// ���κ�/���κ�
	private String voyageType;// �������ͣ����������/Ŀ��/���߼��У�
	private Long sequenceNumber;// ���
    private Map <String,String> tempData;// ��ʱ����
    
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Double getAddPremiumRate() {
		return addPremiumRate;
	}
	public void setAddPremiumRate(Double addPremiumRate) {
		this.addPremiumRate = addPremiumRate;
	}
	public String getClaimAddress() {
		return claimAddress;
	}
	public void setClaimAddress(String claimAddress) {
		this.claimAddress = claimAddress;
	}
	public String getConveyanceInfo() {
		return conveyanceInfo;
	}
	public void setConveyanceInfo(String conveyanceInfo) {
		this.conveyanceInfo = conveyanceInfo;
	}
	public String getDepartureAddress() {
		return departureAddress;
	}
	public void setDepartureAddress(String departureAddress) {
		this.departureAddress = departureAddress;
	}
	public String getDepartureCityCode() {
		return departureCityCode;
	}
	public void setDepartureCityCode(String departureCityCode) {
		this.departureCityCode = departureCityCode;
	}
	public String getDepartureContinentCode() {
		return departureContinentCode;
	}
	public void setDepartureContinentCode(String departureContinentCode) {
		this.departureContinentCode = departureContinentCode;
	}
	public String getDepartureCountryCode() {
		return departureCountryCode;
	}
	public void setDepartureCountryCode(String departureCountryCode) {
		this.departureCountryCode = departureCountryCode;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public String getDeparturePortCode() {
		return departurePortCode;
	}
	public void setDeparturePortCode(String departurePortCode) {
		this.departurePortCode = departurePortCode;
	}
	public String getDepartureProvinceCode() {
		return departureProvinceCode;
	}
	public void setDepartureProvinceCode(String departureProvinceCode) {
		this.departureProvinceCode = departureProvinceCode;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getDestinationCityCode() {
		return destinationCityCode;
	}
	public void setDestinationCityCode(String destinationCityCode) {
		this.destinationCityCode = destinationCityCode;
	}
	public String getDestinationContinentCode() {
		return destinationContinentCode;
	}
	public void setDestinationContinentCode(String destinationContinentCode) {
		this.destinationContinentCode = destinationContinentCode;
	}
	public String getDestinationCountryCode() {
		return destinationCountryCode;
	}
	public void setDestinationCountryCode(String destinationCountryCode) {
		this.destinationCountryCode = destinationCountryCode;
	}
	public String getDestinationPortCode() {
		return destinationPortCode;
	}
	public void setDestinationPortCode(String destinationPortCode) {
		this.destinationPortCode = destinationPortCode;
	}
	public String getDestinationProvinceCode() {
		return destinationProvinceCode;
	}
	public void setDestinationProvinceCode(String destinationProvinceCode) {
		this.destinationProvinceCode = destinationProvinceCode;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getFlightProduct() {
		return flightProduct;
	}
	public void setFlightProduct(String flightProduct) {
		this.flightProduct = flightProduct;
	}
	public String getInspectAgentName() {
		return inspectAgentName;
	}
	public void setInspectAgentName(String inspectAgentName) {
		this.inspectAgentName = inspectAgentName;
	}
	public String getInsuredCondition() {
		return insuredCondition;
	}
	public void setInsuredCondition(String insuredCondition) {
		this.insuredCondition = insuredCondition;
	}
	public String getMotorNo() {
		return motorNo;
	}
	public void setMotorNo(String motorNo) {
		this.motorNo = motorNo;
	}
	public String getParcelNo() {
		return parcelNo;
	}
	public void setParcelNo(String parcelNo) {
		this.parcelNo = parcelNo;
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
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public String getVehicleLicenseNo() {
		return vehicleLicenseNo;
	}
	public void setVehicleLicenseNo(String vehicleLicenseNo) {
		this.vehicleLicenseNo = vehicleLicenseNo;
	}
	public int getVesselAge() {
		return vesselAge;
	}
	public void setVesselAge(int vesselAge) {
		this.vesselAge = vesselAge;
	}
	public String getVesselNameCn() {
		return vesselNameCn;
	}
	public void setVesselNameCn(String vesselNameCn) {
		this.vesselNameCn = vesselNameCn;
	}
	public String getVesselNameEn() {
		return vesselNameEn;
	}
	public void setVesselNameEn(String vesselNameEn) {
		this.vesselNameEn = vesselNameEn;
	}
	public String getVoyageNo() {
		return voyageNo;
	}
	public void setVoyageNo(String voyageNo) {
		this.voyageNo = voyageNo;
	}
	public String getVoyageType() {
		return voyageType;
	}
	public void setVoyageType(String voyageType) {
		this.voyageType = voyageType;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
    
    
}
