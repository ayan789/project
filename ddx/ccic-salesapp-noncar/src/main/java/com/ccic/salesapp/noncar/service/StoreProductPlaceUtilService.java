package com.ccic.salesapp.noncar.service;

import java.text.ParseException;
import java.util.List;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.AgentQueryResVO;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salesapp.noncar.dto.response.StoreInsureInfoFormulasResponseVO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.QuotePriceRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.QuotePriceResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.AgentQueryReqVO;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;
import com.ccic.salessapp.common.core.web.HttpResult;

/**
 * 下单方法公共类
 * @author Administrator
 *
 */
public interface StoreProductPlaceUtilService {
//	public int updateSignStatus(StoreInsureInfo storeInsureInfo);
//
//	StoreInsureInfo findStoreInsureInfoByInsureNo(String insureNo);
//	
//	public StoreInsureInfoFormulasResponseVO findStoreInsureInfoAndFormulasByInsureNo(String proposalNo)throws Exception;

	List<StoreFormulas> getProductFormulaList(String productCode, String newCore, String comCode, String userCode);

//	public void getHandlerInfo(StoreOracleRequestVO reqvo);


	AgentInfoVO selectAgentInfo(StoreOracleRequestVO req, HttpResult httpResult);

	public StoreFormulas getProductFormula(String formulaCode, String productCode, String string);

	void getHandlerInfo(StoreOracleRequestVO reqvo);

	StoreInsureInfoFormulasResponseVO findStoreInsureInfoAndFormulasByInsureNo(String proposalNo) throws Exception;

	StoreInsureInfo findStoreInsureInfoByInsureNo(String insureNo);

	int updateSignStatus(StoreInsureInfo storeInsureInfo);

	String getTradeSerialNo();

	StoreInsureInfo saveInsureInfo(StoreInsureInfo storeInsureInfo, StoreOracleRequestVO requestVo,
			HttpResult httpResult, UserVO handlerInfo);

	void updateInsureInfoById(String id, HttpResult result);

	int updateInsureInfo(StoreInsureInfo insureInfo1, HttpResult result);

	QuotePriceResponseDTO quotePriceInfo(QuotePriceRequestDTO quotePriceRequestDTO, HttpResult result,
			UserVO handlerInfo);

	void preUndwrt(String insureNo, HttpResult result);

    AgentQueryResVO agentQuery(AgentQueryReqVO requestVO) throws ParseException;

	NoncarOrder findNoncarOrderByInsureNo(String insureNo);

	int updateSignStatusTwo(NoncarOrder noncarOrder);

	List<NoncarOrder> findNoncarOrderByRelationOrderId(String id);

	NoncarOrder selectNoncarOrderByInsureNoSubmission(String submissionNo);

	int updateSignStatusSubmission(NoncarOrder noncarOrder);
	
 

}
