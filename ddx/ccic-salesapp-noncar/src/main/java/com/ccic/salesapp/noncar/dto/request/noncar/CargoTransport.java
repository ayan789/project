package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * ������ԤԼЭ��-���䷽ʽ
 * @author Administrator
 *
 */
public class CargoTransport {
	//���ò�Ʒ
	private String flightProduct;
	//���䷽ʽ
	private String transportModeCode;
	//�޶����
	private String siCurrencyCode;
	//�����޶ÿ���Σ�
	private double transportLimit;
	//�����
	private int maxShipAge;
	//��С�ܶ�
	private double minSumTon;
	//�����ϴ�����
	private double oldShipPremiumRate;
	private Long sequenceNumber;// ���
	private Map <String,String> tempData;// ��ʱ����
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getFlightProduct() {
		return flightProduct;
	}
	public void setFlightProduct(String flightProduct) {
		this.flightProduct = flightProduct;
	}
	public String getTransportModeCode() {
		return transportModeCode;
	}
	public void setTransportModeCode(String transportModeCode) {
		this.transportModeCode = transportModeCode;
	}
	public String getSiCurrencyCode() {
		return siCurrencyCode;
	}
	public void setSiCurrencyCode(String siCurrencyCode) {
		this.siCurrencyCode = siCurrencyCode;
	}
	public double getTransportLimit() {
		return transportLimit;
	}
	public void setTransportLimit(double transportLimit) {
		this.transportLimit = transportLimit;
	}
	public int getMaxShipAge() {
		return maxShipAge;
	}
	public void setMaxShipAge(int maxShipAge) {
		this.maxShipAge = maxShipAge;
	}
	public double getMinSumTon() {
		return minSumTon;
	}
	public void setMinSumTon(double minSumTon) {
		this.minSumTon = minSumTon;
	}
	public double getOldShipPremiumRate() {
		return oldShipPremiumRate;
	}
	public void setOldShipPremiumRate(double oldShipPremiumRate) {
		this.oldShipPremiumRate = oldShipPremiumRate;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
