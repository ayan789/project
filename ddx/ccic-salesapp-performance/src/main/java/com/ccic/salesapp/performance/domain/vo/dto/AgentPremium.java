package com.ccic.salesapp.performance.domain.vo.dto;

public class AgentPremium {

	//总额
	private String total;
	//车险
	private String cx;
    //非车险
	private String fcx;
	//意外险
	private String ywx;
	//健康险
	private String jkx;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}

	public String getFcx() {
		return fcx;
	}

	public void setFcx(String fcx) {
		this.fcx = fcx;
	}

	public String getYwx() {
		return ywx;
	}

	public void setYwx(String ywx) {
		this.ywx = ywx;
	}

	public String getJkx() {
		return jkx;
	}

	public void setJkx(String jkx) {
		this.jkx = jkx;
	}

}