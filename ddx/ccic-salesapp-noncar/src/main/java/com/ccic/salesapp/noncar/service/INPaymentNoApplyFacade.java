package com.ccic.salesapp.noncar.service;
import com.ccic.salesapp.noncar.dto.response.JsonVO;

/**
 * 支付申请查询 接口
 * @author Luyunyan
 *
 */
public interface INPaymentNoApplyFacade {

	public JsonVO getPaymentCode(String orderNo, String proposalNo )throws Exception;
	
	public JsonVO payResultConfirm(String proposalNo)throws Exception;
}
