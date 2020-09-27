package com.ccic.salesapp.noncar.dto;

public class AppCheckCode {
	private String id; //主键
	private String userCode;//业务员代码
	private String checkCode;//验证码
	private String mobile;//手机号
	private String create_date;//创建时间
	private String update_date;//更新时间
	private String longTime;//时间戳
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getLongTime() {
		return longTime;
	}
	public void setLongTime(String longTime) {
		this.longTime = longTime;
	}
	
	
	
}
