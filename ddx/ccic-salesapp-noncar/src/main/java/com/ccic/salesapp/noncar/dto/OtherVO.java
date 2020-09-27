package com.ccic.salesapp.noncar.dto;



/**
 * 其他信息
 * @author Administrator
 *
 */
public class OtherVO extends DataVO {

	private String argueSolution;//争议解决方式 Y  详见代码2.1.14                 
	private String arbitBoardname;//仲裁委员会名称  详见代码2.1.15;仲裁时，非空
	
	private String agentCode;//代理机构代码
	private String agreementNum;//代理协议号
	private String agreementName;//代理协议名称
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgreementNum() {
		return agreementNum;
	}
	public void setAgreementNum(String agreementNum) {
		this.agreementNum = agreementNum;
	}
	public String getArgueSolution() {
		return argueSolution;
	}
	public void setArgueSolution(String argueSolution) {
		this.argueSolution = argueSolution;
	}
	public String getArbitBoardname() {
		return arbitBoardname;
	}
	public void setArbitBoardname(String arbitBoardname) {
		this.arbitBoardname = arbitBoardname;
	}
	public String getAgreementName() {
		return agreementName;
	}
	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}
	

}
