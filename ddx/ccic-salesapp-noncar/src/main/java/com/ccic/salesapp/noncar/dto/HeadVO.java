package com.ccic.salesapp.noncar.dto;
import com.ccic.salessapp.common.request.LoginUser;


public class HeadVO  {
	private String function="";
	private String method="";
	private String deviceType="";
	
	private String userCode;//工号
	private String timestamp;//时间戳
	private String sign;//签名 sign=hex(sha-256(function+method+userCode+timestamp+token))
	private String tokenIdx;//token序号
	
	private String requestIP;//ip地址
	private LoginUser loginUser;//登陆用户信息
	
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public LoginUser getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	public String getRequestIP() {
		return requestIP;
	}
	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}
	public String getTokenIdx() {
		return tokenIdx;
	}
	public void setTokenIdx(String tokenIdx) {
		this.tokenIdx = tokenIdx;
	}
	
}
