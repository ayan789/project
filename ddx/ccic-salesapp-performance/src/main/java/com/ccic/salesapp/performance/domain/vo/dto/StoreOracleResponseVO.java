package com.ccic.salesapp.performance.domain.vo.dto;

import com.ccic.salesapp.performance.domain.vo.dto.DataVO;

 
public class StoreOracleResponseVO extends DataVO {
	
	private StoreInsureInfo  InsureInfo;		//下单信息
	private String mes;
	private String code;
	

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public StoreInsureInfo getInsureInfo() {
		return InsureInfo;
	}

	public void setInsureInfo(StoreInsureInfo insureInfo) {
		InsureInfo = insureInfo;
	}
	
	
}
