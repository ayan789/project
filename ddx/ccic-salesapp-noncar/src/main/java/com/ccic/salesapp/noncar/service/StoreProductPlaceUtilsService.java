package com.ccic.salesapp.noncar.service;

import java.util.List;

import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.outService.esb.withholdorder.WithholdOrderRespDTO;

 

/**
 * 下单方法公共类
 * @author Administrator
 *
 */
public interface StoreProductPlaceUtilsService {
	
	public Double balanceQuery(ResultVO result, String accountCode);
	public WithholdOrderRespDTO withholdOrderService(ResultVO result, String accountCode, StoreInsureInfo storeInsureInfo);
	public void updateInsureInfoByIdorWithholdNo(String id, WithholdOrderRespDTO withholdOrderResp , String accountCode,String itemName) throws Exception;
	public void paySuccess(ResultVO result, StoreInsureInfo storeInsureInfo, String withholdNo,String accountCode);
	public void withholdStatusPort(String operatorcode, String withholdNo, String accountCode,String paymentNo);
	
	public Double balanceQueryTwo(ResultVO result, String accountCode);
	public WithholdOrderRespDTO withholdOrderServiceTwo(ResultVO result, String accountCode, NoncarOrder noncarOrder);
	public void updateInsureInfoByIdorWithholdNoTwo(String insureNo, WithholdOrderRespDTO withholdOrderService,
			String accountCode, String itemName);
	public void paySuccessTwo(ResultVO result, NoncarOrder noncarOrder, String withholdNo, String accountCode);
	public void withholdStatusPortTwo(String operatorcode, String withholdNo, String accountCode, String paymentNo);
 
}
