package com.ccic.salesapp.noncar.dto.request;


public class CustCoreInfoQuoteRequestVO{
	
	private String orderNo;//订单号
	private String desForwardDate;//转发时间
	
	private String custNo;	//客户号
	private String custName;//客户姓名
	private String startDate;		//起保日期
	
	private String riskCode;		//车险险种代码
	private String primium;			//车险保费
	private String riskCodeE;		//意外险险种代码
	private String primiumE;		//意外险保费
	private String riskCodeY;		//随车险险种代码
	private String primiumY;		//随车险保费
	
	private String comCode;//机构代码
	
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getDesForwardDate() {
		return desForwardDate;
	}
	public void setDesForwardDate(String desForwardDate) {
		this.desForwardDate = desForwardDate;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getPrimium() {
		return primium;
	}
	public void setPrimium(String primium) {
		this.primium = primium;
	}
	public String getRiskCodeE() {
		return riskCodeE;
	}
	public void setRiskCodeE(String riskCodeE) {
		this.riskCodeE = riskCodeE;
	}
	public String getPrimiumE() {
		return primiumE;
	}
	public void setPrimiumE(String primiumE) {
		this.primiumE = primiumE;
	}
	public String getRiskCodeY() {
		return riskCodeY;
	}
	public void setRiskCodeY(String riskCodeY) {
		this.riskCodeY = riskCodeY;
	}
	public String getPrimiumY() {
		return primiumY;
	}
	public void setPrimiumY(String primiumY) {
		this.primiumY = primiumY;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
}
