package com.ccic.salesapp.noncar.dto.request;
/**
 * 通用代码表
 * @author wangcw
 * */
public class GeneralCode {
	private String codeType;//代码类别
	private String codeCode;//代码
	private String codeDesc;//代码对应内容
	private String relateCodeType;//关联代码类别
	private String relateCodeCode;//关联代码
	private String comCode;//机构代码（前四位）
	private String riskCode;//险种
	private String inputDate;//录入时间
	private String validStatus;//标志位:1-有效，0-无效
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeCode() {
		return codeCode;
	}
	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public String getRelateCodeType() {
		return relateCodeType;
	}
	public void setRelateCodeType(String relateCodeType) {
		this.relateCodeType = relateCodeType;
	}
	public String getRelateCodeCode() {
		return relateCodeCode;
	}
	public void setRelateCodeCode(String relateCodeCode) {
		this.relateCodeCode = relateCodeCode;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

}
