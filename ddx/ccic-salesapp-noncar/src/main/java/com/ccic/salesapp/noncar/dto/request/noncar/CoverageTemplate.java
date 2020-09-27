package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
/**
 * �����λ�����Ϣ
 * @author Administrator
 *
 */
public class CoverageTemplate {
	private String planCode; //��������
	private List<PolicyCoverage> policyCoverageList;//�������μ�����������Ϣ
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public List<PolicyCoverage> getPolicyCoverageList() {
		return policyCoverageList;
	}
	public void setPolicyCoverageList(List<PolicyCoverage> policyCoverageList) {
		this.policyCoverageList = policyCoverageList;
	}
	
	

}
