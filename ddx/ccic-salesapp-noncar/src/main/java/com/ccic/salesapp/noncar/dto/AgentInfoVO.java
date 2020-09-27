package com.ccic.salesapp.noncar.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 * 代理人信息
  */
@Data
public class AgentInfoVO {
	
	@ApiModelProperty("代理人代码")
	private String agentCode;//代理人代码	
	@ApiModelProperty("代理人名称")
	private String agentName;//代理人名称
	@ApiModelProperty("代理人协议号")
	private String agreementNo;//代理人协议号
	@ApiModelProperty("代理人协议名称")
	private String agreementName;//代理人协议名称
	@ApiModelProperty("业务来源1")
	private String businessNature;//业务来源1    
	@ApiModelProperty("业务来源2")
	private String businessNature2;//业务来源2    
	@ApiModelProperty("经办人2")
	private String handler2Code;//经办人2 
	@ApiModelProperty("经办人2名称")
	private String handler2Name;//经办人2名称
	
	@ApiModelProperty("第三方业务员工号")
	private String thirdPartyHandlerNo;// 第三方业务员工号
	@ApiModelProperty("第三方业务员姓名")
	private String thirdPartyHandlerName;// 第三方业务员姓名

	public String getHandler2Name() {
		return handler2Name;
	}
	public void setHandler2Name(String handler2Name) {
		this.handler2Name = handler2Name;
	}
	public String getBusinessNature() {
		return businessNature;
	}
	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}
	public String getHandler2Code() {
		return handler2Code;
	}
	public void setHandler2Code(String handler2Code) {
		this.handler2Code = handler2Code;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getBusinessNature2() {
		return businessNature2;
	}
	public void setBusinessNature2(String businessNature2) {
		this.businessNature2 = businessNature2;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getAgreementName() {
		return agreementName;
	}
	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

}
