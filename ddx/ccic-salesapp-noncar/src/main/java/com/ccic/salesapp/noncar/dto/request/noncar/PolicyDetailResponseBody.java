package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;

public class PolicyDetailResponseBody {
	private String effectiveDate;
	private String expiryDate;
	private String policyNo;
	private String startHour;
	private String endHour;
	private String sumInsured;
	private String insureName;
	private List<PolicyCustomer> policyCustomerList;
	private List<PolicyLob> policyLobList;
	private List<ChannelOpInfo> channelOpInfoList;
	private String proposqlNo;
	public String getProposqlNo() {
		return proposqlNo;
	}

	public List<ChannelOpInfo> getChannelOpInfoList() {
		return channelOpInfoList;
	}

	public void setChannelOpInfoList(List<ChannelOpInfo> channelOpInfoList) {
		this.channelOpInfoList = channelOpInfoList;
	}

	public void setProposqlNo(String proposqlNo) {
		this.proposqlNo = proposqlNo;
	}

	public String getInsureName() {
		return insureName;
	}

	public void setInsureName(String insureName) {
		this.insureName = insureName;
	}

	public List<PolicyCustomer> getPolicyCustomerList() {
		return policyCustomerList;
	}

	public void setPolicyCustomerList(List<PolicyCustomer> policyCustomerList) {
		this.policyCustomerList = policyCustomerList;
	}

	public List<PolicyLob> getPolicyLobList() {
		return policyLobList;
	}

	public void setPolicyLobList(List<PolicyLob> policyLobList) {
		this.policyLobList = policyLobList;
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

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getDuePremium() {
		return duePremium;
	}

	public void setDuePremium(String duePremium) {
		this.duePremium = duePremium;
	}

	private String duePremium;

}
