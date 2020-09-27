package com.ccic.salesapp.noncar.dto.response;



/**
 * 支付号申请查询接口 返回VO
 * @author Luyunyan
 *
 */
public class IPaymentCodeResponseVO {
	IPaymentCodeVO  paymentCodeVO=new IPaymentCodeVO();

	public IPaymentCodeVO getPaymentCodeVO() {
		return paymentCodeVO;
	}

	public void setPaymentCodeVO(IPaymentCodeVO paymentCodeVO) {
		this.paymentCodeVO = paymentCodeVO;
	}
	

}
