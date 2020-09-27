package com.ccic.salesapp.noncar.dto.response;
import com.ccic.salesapp.noncar.dto.DataVO;



/**
 * 支付号申请信息返回VO
 * @author luyunyan
 *
 */
public class IPaymentCodeVO extends DataVO{
	private String payApplyNo; //支付号
	private String identityCode; //支付验证码
	private Double payFee; //支付金额
	public String getPayApplyNo() {
		return payApplyNo;
	}
	public void setPayApplyNo(String payApplyNo) {
		this.payApplyNo = payApplyNo;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public Double getPayFee() {
		return payFee;
	}
	public void setPayFee(Double payFee) {
		this.payFee = payFee;
	}



}
