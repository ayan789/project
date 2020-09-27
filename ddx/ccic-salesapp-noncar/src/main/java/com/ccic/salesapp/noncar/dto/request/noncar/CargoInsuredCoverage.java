package com.ccic.salesapp.noncar.dto.request.noncar;
/**
 * ������ԤԼЭ��-����������Ϣ
 * @author Administrator
 *
 */
public class CargoInsuredCoverage {
	//���ò�Ʒ
	private String flightProduct;
	//����б�����
	private String cargoIssueTerms;
	//��������
	private String cargoCode;
	//�������
	private String exclusionCargo;
	//��������
	private String mainCoverageLiability;
	//Ԥ��������
	private double preYearVolume;
	//���ʡ�
	private double premiumRate;
	public String getFlightProduct() {
		return flightProduct;
	}
	public void setFlightProduct(String flightProduct) {
		this.flightProduct = flightProduct;
	}
	public String getCargoIssueTerms() {
		return cargoIssueTerms;
	}
	public void setCargoIssueTerms(String cargoIssueTerms) {
		this.cargoIssueTerms = cargoIssueTerms;
	}
	public String getCargoCode() {
		return cargoCode;
	}
	public void setCargoCode(String cargoCode) {
		this.cargoCode = cargoCode;
	}
	public String getExclusionCargo() {
		return exclusionCargo;
	}
	public void setExclusionCargo(String exclusionCargo) {
		this.exclusionCargo = exclusionCargo;
	}
	public String getMainCoverageLiability() {
		return mainCoverageLiability;
	}
	public void setMainCoverageLiability(String mainCoverageLiability) {
		this.mainCoverageLiability = mainCoverageLiability;
	}
	public double getPreYearVolume() {
		return preYearVolume;
	}
	public void setPreYearVolume(double preYearVolume) {
		this.preYearVolume = preYearVolume;
	}
	public double getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(double premiumRate) {
		this.premiumRate = premiumRate;
	}

}
