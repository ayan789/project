package com.ccic.salessapp.common.outService.rest.common.bean;

public class ResponseHead {

    private String seqNo;
    private String consumerSeqNo;
    private String providerSeqNo;
    private int status;
    private String esbCode;
    private String esbMessage;
    private String appCode;
    private String appMessage;
    
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getConsumerSeqNo() {
		return consumerSeqNo;
	}
	public void setConsumerSeqNo(String consumerSeqNo) {
		this.consumerSeqNo = consumerSeqNo;
	}
	public String getProviderSeqNo() {
		return providerSeqNo;
	}
	public void setProviderSeqNo(String providerSeqNo) {
		this.providerSeqNo = providerSeqNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEsbCode() {
		return esbCode;
	}
	public void setEsbCode(String esbCode) {
		this.esbCode = esbCode;
	}
	public String getEsbMessage() {
		return esbMessage;
	}
	public void setEsbMessage(String esbMessage) {
		this.esbMessage = esbMessage;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppMessage() {
		return appMessage;
	}
	public void setAppMessage(String appMessage) {
		this.appMessage = appMessage;
	}

}
