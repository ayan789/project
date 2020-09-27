package com.ccic.salesapp.noncar.dto.request;

public class SendSmsRequestVo extends DataVO{
	
	private String userId;
	private String mobile;
	private String comcode;
	private String insureNo;
	private String submissionNo;

	public String getSubmissionNo() {
		return submissionNo;
	}

	public void setSubmissionNo(String submissionNo) {
		this.submissionNo = submissionNo;
	}
	public String getInsureNo() {
		return insureNo;
	}

	public void setInsureNo(String insureNo) {
		this.insureNo = insureNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getComcode() {
		return comcode;
	}

	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
}
