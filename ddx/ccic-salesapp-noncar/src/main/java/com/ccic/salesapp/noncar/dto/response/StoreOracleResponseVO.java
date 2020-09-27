package com.ccic.salesapp.noncar.dto.response;

import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.DataVO;
 
public class StoreOracleResponseVO  {
	
	private StoreInsureInfo  insureInfo;		//下单信息
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
		return insureInfo;
	}

	public void setInsureInfo(StoreInsureInfo insureInfo) {
		this.insureInfo = insureInfo;
	}
	
	
}
