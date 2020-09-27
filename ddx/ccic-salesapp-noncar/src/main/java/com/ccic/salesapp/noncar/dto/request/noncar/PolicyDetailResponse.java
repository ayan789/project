package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;

import com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

public class PolicyDetailResponse extends ResponseBody {
	private String effectiveDate;
	private String expiryDate;
	private String policyNo;//������
	private String startHour;// ����"0"
	private String endHour;// ����"24"
	private String sumInsured;//����
	private String insureName;//����
	private String proposqlNo;//Ͷ������
	private String duePremium;//����
	private PolicyDetailResponseBody policyElementString;
	private List<PersonInsured> personInsuredList;
	private List<PolicyLob> policyLobList;
	private List<PolicyCustomer> policyCustomerList;
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

	
	public List<PersonInsured> getPersonInsuredList() {
		return personInsuredList;
	}

	public void setPersonInsuredList(List<PersonInsured> personInsuredList) {
		this.personInsuredList = personInsuredList;
	}

	public String getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getInsureName() {
		return insureName;
	}

	public void setInsureName(String insureName) {
		this.insureName = insureName;
	}

	public String getProposqlNo() {
		return proposqlNo;
	}

	public void setProposqlNo(String proposqlNo) {
		this.proposqlNo = proposqlNo;
	}

	

	public List<PolicyLob> getPolicyLobList() {
		return policyLobList;
	}

	public void setPolicyLobList(
			List<PolicyLob> policyLobList) {
		this.policyLobList = policyLobList;
	}

	public List<PolicyCustomer> getPolicyCustomerList() {
		return policyCustomerList;
	}

	public void setPolicyCustomerList(List<PolicyCustomer> policyCustomerList) {
		this.policyCustomerList = policyCustomerList;
	}

	

	public PolicyDetailResponseBody getPolicyElementString() {
		return policyElementString;
	}

	public void setPolicyElementString(PolicyDetailResponseBody policyElementString) {
		this.policyElementString = policyElementString;
	}
	public String getDuePremium() {
		return duePremium;
	}

	public void setDuePremium(String duePremium) {
		this.duePremium = duePremium;
	}
	
}
