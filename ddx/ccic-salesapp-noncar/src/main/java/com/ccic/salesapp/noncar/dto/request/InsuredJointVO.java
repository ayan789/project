package com.ccic.salesapp.noncar.dto.request;

/**
 * *联合销售被保险人信息
 * @author Administrator
 *
 */
public class InsuredJointVO {

	private String sameToVehicle;//同车险被保人
	
	private String name;//姓名
	
	private String identityNo;//证件号码
	
	private String identityType;//证件类型
	
	private String mobile;//手机号码

	public String getSameToVehicle() {
		return sameToVehicle;
	}

	public void setSameToVehicle(String sameToVehicle) {
		this.sameToVehicle = sameToVehicle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
