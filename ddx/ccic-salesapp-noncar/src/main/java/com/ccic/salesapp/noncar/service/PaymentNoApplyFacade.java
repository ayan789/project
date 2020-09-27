package com.ccic.salesapp.noncar.service;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.GetPayUrlRequestVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salessapp.common.core.web.HttpResult;




/**
 *支付模块相关接口
 * @author wangpeng
 *
 */
@Service
public interface PaymentNoApplyFacade {

	//支付申请查询
	public HttpResult getPaymentCode1(String orderNo, String proposalNo,String userCode )throws Exception;
	
	//获取支付跳转url
	public HttpResult getPayUrl(GetPayUrlRequestVO request) throws Exception;
	
	//订单转发支付URL
	public HttpResult getForwardUrl(ForwardPayInsureInfoReqVO request) throws Exception;
	
	//支付成功后支付状态回写
	public JsonVO payResultConfirm(ApplyPayVo applyPayVo,String userCode) throws Exception;
	
	StoreInsureInfo saveApplyPayment(StoreInsureInfo insureInfo,ResultVO result);

	public JsonVO payResultConfirmTwo(ApplyPayVo applyPayVo, String userCode) throws Exception;

	NoncarOrder saveApplyPaymentTwo(NoncarOrder noncarOrder, ResultVO result);
	
	NoncarOrder saveApplyPaymentThree(NoncarOrder noncarOrder, ResultVO result);
	
}
