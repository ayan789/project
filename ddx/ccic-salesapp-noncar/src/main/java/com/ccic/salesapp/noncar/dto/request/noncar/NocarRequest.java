package com.ccic.salesapp.noncar.dto.request.noncar;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

public class NocarRequest extends RequestBody{
	private String policyNo;//������
	private String queryType;//ҵ���ʶ
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

}
