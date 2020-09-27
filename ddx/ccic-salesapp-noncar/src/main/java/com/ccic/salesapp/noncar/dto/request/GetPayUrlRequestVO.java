package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;



/**
 * 
 */
public class GetPayUrlRequestVO extends DataVO{

	private String proposalNo;//投保单号
	private String paymentNo;//支付号
	private String checkNo;//验证码
	private String flag;// 1:二维码支付 2:直接进快钱 3：兴业银行支付宝 4：兴业银行微信 其它：集中收款主页
	
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
