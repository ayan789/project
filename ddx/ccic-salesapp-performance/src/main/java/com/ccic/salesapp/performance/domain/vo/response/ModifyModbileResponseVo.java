package com.ccic.salesapp.performance.domain.vo.response;
import com.ccic.salesapp.performance.domain.vo.dto.DataVO;



public class ModifyModbileResponseVo extends DataVO{
	private String code;
	private String mes;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	
}
