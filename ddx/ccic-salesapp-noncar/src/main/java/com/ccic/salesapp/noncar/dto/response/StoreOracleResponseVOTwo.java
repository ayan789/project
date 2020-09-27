package com.ccic.salesapp.noncar.dto.response;

import com.ccic.salesapp.noncar.dto.NoncarOrder;

public class StoreOracleResponseVOTwo {

	private NoncarOrder noncarOrder; // 下单信息
	private String mes;
	private String code;

	public NoncarOrder getNoncarOrder() {
		return noncarOrder;
	}

	public void setNoncarOrder(NoncarOrder noncarOrder) {
		this.noncarOrder = noncarOrder;
	}

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

}
