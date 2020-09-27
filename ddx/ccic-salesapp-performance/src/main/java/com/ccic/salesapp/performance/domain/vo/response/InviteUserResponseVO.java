package com.ccic.salesapp.performance.domain.vo.response;

import com.ccic.salesapp.performance.domain.vo.dto.AddressInfoVO;
import com.ccic.salesapp.performance.domain.vo.dto.DataVO;



public class InviteUserResponseVO extends DataVO {

	private String inviteUsercode;
	private String userName;
	private String cardid;
	private String mobile;
	private String mobileCode;
	private String checkCodeId;
	private String checkCode;
	private String examState;
	private AddressInfoVO addressInfo;  // 地址信息

	public String getInviteUsercode() {
		return inviteUsercode;
	}

	public void setInviteUsercode(String inviteUsercode) {
		this.inviteUsercode = inviteUsercode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public String getCheckCodeId() {
		return checkCodeId;
	}

	public void setCheckCodeId(String checkCodeId) {
		this.checkCodeId = checkCodeId;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public AddressInfoVO getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfoVO addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getExamState() {
		return examState;
	}

	public void setExamState(String examState) {
		this.examState = examState;
	}

}
