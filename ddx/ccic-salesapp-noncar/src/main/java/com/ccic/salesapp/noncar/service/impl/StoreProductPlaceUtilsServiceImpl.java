package com.ccic.salesapp.noncar.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.outService.esb.balancequery.BalanceQueryPort;
import com.ccic.salesapp.noncar.outService.esb.balancequery.BalanceQueryReqDTO;
import com.ccic.salesapp.noncar.outService.esb.balancequery.BalanceQueryRequest;
import com.ccic.salesapp.noncar.outService.esb.balancequery.BalanceQueryResponse;
import com.ccic.salesapp.noncar.outService.esb.balancequery.BalanceQueryService;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.CertiDTO;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.ObjectFactory;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.PaySuccessNoticePort;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.PaySuccessNoticeReqDTO;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.PaySuccessNoticeRequest;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.PaySuccessNoticeResponse;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.PaySuccessRequest;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.PaySuccessRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.PaySuccessResponse;
import com.ccic.salesapp.noncar.outService.esb.withholdorder.WithholdOrderPort;
import com.ccic.salesapp.noncar.outService.esb.withholdorder.WithholdOrderReqDTO;
import com.ccic.salesapp.noncar.outService.esb.withholdorder.WithholdOrderRequest;
import com.ccic.salesapp.noncar.outService.esb.withholdorder.WithholdOrderRespDTO;
import com.ccic.salesapp.noncar.outService.esb.withholdorder.WithholdOrderResponse;
import com.ccic.salesapp.noncar.outService.esb.withholdstatus.WithholdStatusPort;
import com.ccic.salesapp.noncar.outService.esb.withholdstatus.WithholdStatusReqDTO;
import com.ccic.salesapp.noncar.outService.esb.withholdstatus.WithholdStatusRequest;
import com.ccic.salesapp.noncar.outService.esb.withholdstatus.WithholdStatusResponse;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilsService;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.service.AbstractService;

@Slf4j
@Service
public class StoreProductPlaceUtilsServiceImpl extends AbstractService implements StoreProductPlaceUtilsService {

	@Autowired
	BalanceQueryPort balanceQueryPort;

	@Autowired
	WithholdOrderPort withholdOrderPort;

	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;

	@Autowired
	PaySuccessNoticePort paySuccessNoticePort;

	@Autowired
	WithholdStatusPort withholdStatusPort;

//	@Override
//	public Double  balanceQuery(ResultVO result,String accountCode) {
//		//查询账户
//		BalanceQueryRequest balanceQueryRequest = new BalanceQueryRequest();
//		RequestHeadDTO requestHeadDTO = this.getConsumerID();
//		BalanceQueryReqDTO BalanceQueryReqDTO = new BalanceQueryReqDTO();
//		BalanceQueryReqDTO.setAccountCode(accountCode+0);
//		balanceQueryRequest.setRequestBody(BalanceQueryReqDTO);
//		balanceQueryRequest.setRequestHead(requestHeadDTO);
//		BalanceQueryResponse response = new BalanceQueryResponse();
//		Double accountBalance = 0.00;
//		try {
//			response = balanceQueryPort.balanceQuery(balanceQueryRequest);
//			if (response.getResponseHead().getStatus()==0 && response.getResponseBody()!=null) {
//				if(response.getResponseBody().getAccountBalance()!=null) {
//					accountBalance= response.getResponseBody().getAccountBalance();
//				}
//			}else {
//				result.setCode("0");
//				result.setMessage("预付款余额查询接口，返回状态为失败。返回信息："+response.getResponseHead().getAppMessage());				
//			}
//		} catch (Exception e) {
//			//log.error("预付款余额查询接口异常：", e);
//			result.setCode("0");
//			result.setMessage("预付款余额查询接口异常："+e.getMessage());
//		}
//		return accountBalance;
//		
//	}
//
//	@Override
//	public WithholdOrderRespDTO withholdOrderService(ResultVO result, String accountCode, StoreInsureInfo storeInsureInfo) {	
//		WithholdOrderRequest withholdOrderRequest = new WithholdOrderRequest();
//		RequestHeadDTO requestHeadDTO = this.getConsumerID();
//		WithholdOrderReqDTO withholdOrderReqDTO = new  WithholdOrderReqDTO();
//		withholdOrderReqDTO.setAccountCode(accountCode+0);
//		withholdOrderReqDTO.setPremiumAmount(Double.parseDouble(storeInsureInfo.getSumPremium()));
//		withholdOrderReqDTO.setOperatorcode(storeInsureInfo.getHandlerCode());
//		withholdOrderRequest.setRequestBody(withholdOrderReqDTO);
//		withholdOrderRequest.setRequestHead(requestHeadDTO);
//		WithholdOrderResponse response = null;
//		try {
//			response = withholdOrderPort.withholdOrder(withholdOrderRequest);
//			if (response.getResponseHead().getStatus() != 0 || response.getResponseBody()==null) {
//				result.setCode("0");
//				result.setMessage("预扣单生成接口，返回状态为失败。返回信息："+response.getResponseHead().getAppMessage());
//			}
//		} catch (Exception e) {
//			result.setCode("0");
//			result.setMessage("调用预扣单生成接口异常："+e.getMessage());
//		}
//		return response.getResponseBody();
//	}
//
//	@Override
//	public void updateInsureInfoByIdorWithholdNo(String proposalNo, WithholdOrderRespDTO withholdOrderResp, String accountCode,String itemName) throws Exception {
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("proposalNo", proposalNo);
//		map.put("withholdNo", withholdOrderResp.getWithholdNo());
//		map.put("paymentNo", withholdOrderResp.getPaymentNo());
//		map.put("accountCode", accountCode);
//		map.put("itemName", itemName);
//		try {
//			storeInsureInfoMapper.updateInsureInfoById(map);
//		} catch (Exception e) {
//			//log.error(e);
//			throw e;
//		}
//	}
//
//	@Override
//	public void paySuccess(ResultVO result, StoreInsureInfo storeInsureInfo,String paymentNo,String accountCode) {
//		
//		RequestHeadDTO headDto = this.getConsumerID();
//		PaySuccessRequestDTO requestDTO = new PaySuccessRequestDTO();
//		requestDTO.setSize(1);
//		CertiDTO certiDTO= new CertiDTO();
//		certiDTO.setCertiNo(storeInsureInfo.getInsureNo());
//		certiDTO.setPayWay("0");
//		certiDTO.setPayer("08");
//		certiDTO.setPayApplyNo(paymentNo);
//		certiDTO.setRealPayCurrency("CNY");
//		certiDTO.setRealPayFee(Double.parseDouble(storeInsureInfo.getSumPremium()));
//		certiDTO.setRealExchangeRate(1.0);
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		ObjectFactory objFac=new ObjectFactory();
//		//备certiDTO.setDebitTime(objFac.createCertiDTODebitTime(sf.format(new Date()).toString()));
//		certiDTO.setDealSerialno(accountCode);
//		requestDTO.getCertiList().add(certiDTO);
//
//		PaySuccessRequest request= new PaySuccessRequest();
//		request.setRequestHead(headDto);
//		request.setRequestBody(requestDTO);
//		PaySuccessResponse resp = null;
//		try {
//			resp = paySuccess.paySuccess(request);
//			if (resp.getResponseHead().getStatus() != 0) {
//				result.setCode("0");
//				result.setMessage("支付通知接口，返回状态为失败。返回信息："+resp.getResponseHead().getAppMessage());
//			}
//		} catch (Exception e) {
//			//log.error("调用支付通知接口异常：",e);
//			result.setCode("0");
//			result.setMessage("调用收付费支付通知接口异常："+e.getMessage());
//		}
//		
//	}
//
//	@Override
//	public void withholdStatusPort(String operatorcode, String withholdNo, String accountCode,String paymentNo) {
//		RequestHeadDTO headDto =  this.getConsumerID();
//		WithholdStatusReqDTO requestVO = new WithholdStatusReqDTO();
//		requestVO.setAccountCode(accountCode+"0");
//		requestVO.setOperatorcode(operatorcode);
//		requestVO.setWithholdNo(withholdNo);
//		requestVO.setWithholdStatus("1_"+paymentNo);
//		WithholdStatusRequest request = new WithholdStatusRequest();
//		request.setRequestHead(headDto);
//		request.setRequestBody(requestVO);
//		WithholdStatusResponse resp = null;
//		try {
//			resp = withholdStatusPort.withholdStatus(request);
//			if (resp.getResponseHead().getStatus() != 0) {
//				log.info("预扣余额回滚失败，返回信息："+resp.getResponseHead().getAppMessage());
//			}
//		} catch (Exception e) {
//			log.error("调用预扣余额回滚异常：",e);
//		}	
//	}

	@Override
	public Double balanceQuery(ResultVO result, String accountCode) {

		// 查询账户
		BalanceQueryRequest balanceQueryRequest = new BalanceQueryRequest();
		RequestHeadDTO requestHeadDTO = this.getConsumerID();
		requestHeadDTO.setConsumerID("gateway");
		BalanceQueryReqDTO BalanceQueryReqDTO = new BalanceQueryReqDTO();
		BalanceQueryReqDTO.setAccountCode(accountCode + 0);
		balanceQueryRequest.setRequestBody(BalanceQueryReqDTO);
		balanceQueryRequest.setRequestHead(requestHeadDTO);
		BalanceQueryResponse response = new BalanceQueryResponse();
		Double accountBalance = 0.00;
		try {
			response = balanceQueryPort.balanceQuery(balanceQueryRequest);
			if (response.getResponseHead().getStatus() == 0 && response.getResponseBody() != null) {
				if (response.getResponseBody().getAccountBalance() != null) {
					accountBalance = response.getResponseBody().getAccountBalance();
				}
			} else {
				result.setCode("0");
				result.setMessage("预付款余额查询接口，返回状态为失败。返回信息：" + response.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("预付款余额查询接口异常：", e);
			result.setCode("0");
			result.setMessage("预付款余额查询接口异常：" + e.getMessage());
		}
		return accountBalance;

	}

	@Override
	public WithholdOrderRespDTO withholdOrderService(ResultVO result, String accountCode,
			StoreInsureInfo storeInsureInfo) {

		WithholdOrderRequest withholdOrderRequest = new WithholdOrderRequest();
		RequestHeadDTO requestHeadDTO = this.getConsumerID();
		requestHeadDTO.setConsumerID("gateway");
		WithholdOrderReqDTO withholdOrderReqDTO = new WithholdOrderReqDTO();
		withholdOrderReqDTO.setAccountCode(accountCode + 0);
		withholdOrderReqDTO.setPremiumAmount(Double.parseDouble(storeInsureInfo.getSumPremium()));
		withholdOrderReqDTO.setOperatorcode(storeInsureInfo.getHandlerCode());
		withholdOrderRequest.setRequestBody(withholdOrderReqDTO);
		withholdOrderRequest.setRequestHead(requestHeadDTO);
		WithholdOrderResponse response = null;
		try {
			response = withholdOrderPort.withholdOrder(withholdOrderRequest);
			if (response.getResponseHead().getStatus() != 0 || response.getResponseBody() == null) {
				result.setCode("0");
				result.setMessage("预扣单生成接口，返回状态为失败。返回信息：" + response.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("调用预扣单生成接口异常：" + e.getMessage());
		}
		return response.getResponseBody();
	}

	@Override
	public void updateInsureInfoByIdorWithholdNo(String proposalNo, WithholdOrderRespDTO withholdOrderResp,
			String accountCode, String itemName) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("proposalNo", proposalNo);
		map.put("withholdNo", withholdOrderResp.getWithholdNo());
		map.put("paymentNo", withholdOrderResp.getPaymentNo());
		map.put("accountCode", accountCode);
		map.put("itemName", itemName);
		try {
			// dao.doUpdate("storeInsureInfo.updateInsureInfoByIdorWithholdNo", map);
			storeInsureInfoMapper.updateInsureInfoByIdorWithholdNo(map);
		} catch (Exception e) {
			// log.error(e);
			throw e;
		}
	}

	@Override
	public void paySuccess(ResultVO result, StoreInsureInfo storeInsureInfo, String paymentNo, String accountCode) {

		com.ccic.salesapp.noncar.outService.esb.paysuccess.RequestHeadDTO headDto = new com.ccic.salesapp.noncar.outService.esb.paysuccess.RequestHeadDTO();
		headDto.setConsumerID("gateway");
		PaySuccessNoticeReqDTO requestDTO = new PaySuccessNoticeReqDTO();
		requestDTO.setCertiNo(storeInsureInfo.getInsureNo());
		requestDTO.setPayApplyNo(paymentNo);
		requestDTO.setRealPayFee(Double.parseDouble(storeInsureInfo.getSumPremium()));
		requestDTO.setAccountCode(accountCode);
		PaySuccessNoticeRequest request = new PaySuccessNoticeRequest();
		request.setRequestHead(headDto);
		request.setRequestBody(requestDTO);
		;
		PaySuccessNoticeResponse resp = null;
		try {
			System.out.println(JSONObject.toJSONString(request));
			resp = paySuccessNoticePort.paySuccessNotice(request);
			System.out.println(JSONObject.toJSONString(resp));
			if (resp.getResponseHead().getStatus() != 0) {
				result.setCode("0");
				result.setMessage("支付通知接口，返回状态为失败。返回信息：" + resp.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用支付通知接口异常：", e);
			result.setCode("0");
			result.setMessage("调用收付费支付通知接口异常：" + e.getMessage());
		}
	}

	@Override
	public void withholdStatusPort(String operatorcode, String withholdNo, String accountCode, String paymentNo) {

		RequestHeadDTO headDto = this.getConsumerID();
		headDto.setConsumerID("gateway");
		WithholdStatusReqDTO requestVO = new WithholdStatusReqDTO();
		requestVO.setAccountCode(accountCode + "0");
		requestVO.setOperatorcode(operatorcode);
		requestVO.setWithholdNo(withholdNo);
		requestVO.setWithholdStatus("1_" + paymentNo);
		WithholdStatusRequest request = new WithholdStatusRequest();
		request.setRequestHead(headDto);
		request.setRequestBody(requestVO);
		WithholdStatusResponse resp = null;
		try {
			resp = withholdStatusPort.withholdStatus(request);
			if (resp.getResponseHead().getStatus() != 0) {
				log.info("预扣余额回滚失败，返回信息：" + resp.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用预扣余额回滚异常：", e);
		}

	}

	
	
	
	
	
	
	
	/****
	 * 更改
	 * 
	 */
	@Override
	public Double balanceQueryTwo(ResultVO result, String accountCode) {
		// 查询账户
		BalanceQueryRequest balanceQueryRequest = new BalanceQueryRequest();
		RequestHeadDTO requestHeadDTO = this.getConsumerID();
		requestHeadDTO.setConsumerID("gateway");
		BalanceQueryReqDTO BalanceQueryReqDTO = new BalanceQueryReqDTO();
		BalanceQueryReqDTO.setAccountCode(accountCode + 0);
		balanceQueryRequest.setRequestBody(BalanceQueryReqDTO);
		balanceQueryRequest.setRequestHead(requestHeadDTO);
		BalanceQueryResponse response = new BalanceQueryResponse();
		Double accountBalance = 0.00;
		try {
			response = balanceQueryPort.balanceQuery(balanceQueryRequest);
			if (response.getResponseHead().getStatus() == 0 && response.getResponseBody() != null) {
				if (response.getResponseBody().getAccountBalance() != null) {
					accountBalance = response.getResponseBody().getAccountBalance();
				}
			} else {
				result.setCode("0");
				result.setMessage("预付款余额查询接口，返回状态为失败。返回信息：" + response.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("预付款余额查询接口异常：", e);
			result.setCode("0");
			result.setMessage("预付款余额查询接口异常：" + e.getMessage());
		}
		return accountBalance;
	}

	
	@Override
	public WithholdOrderRespDTO withholdOrderServiceTwo(ResultVO result, String accountCode, NoncarOrder noncarOrder) {
		WithholdOrderRequest withholdOrderRequest = new WithholdOrderRequest();
		RequestHeadDTO requestHeadDTO = this.getConsumerID();
		requestHeadDTO.setConsumerID("gateway");
		WithholdOrderReqDTO withholdOrderReqDTO = new WithholdOrderReqDTO();
		withholdOrderReqDTO.setAccountCode(accountCode + 0);
		withholdOrderReqDTO.setPremiumAmount(Double.parseDouble(noncarOrder.getSumPremium()));
		withholdOrderReqDTO.setOperatorcode(noncarOrder.getHandlerCode());
		withholdOrderRequest.setRequestBody(withholdOrderReqDTO);
		withholdOrderRequest.setRequestHead(requestHeadDTO);
		WithholdOrderResponse response = null;
		try {
			response = withholdOrderPort.withholdOrder(withholdOrderRequest);
			if (response.getResponseHead().getStatus() != 0 || response.getResponseBody() == null) {
				result.setCode("0");
				result.setMessage("预扣单生成接口，返回状态为失败。返回信息：" + response.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("调用预扣单生成接口异常：" + e.getMessage());
		}
		return response.getResponseBody();
	}

	@Override
	public void updateInsureInfoByIdorWithholdNoTwo(String insureNo, WithholdOrderRespDTO withholdOrderService,
			String accountCode, String itemName) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("proposalNo", insureNo);
		map.put("withholdNo", withholdOrderService.getWithholdNo());
		map.put("paymentNo", withholdOrderService.getPaymentNo());
		map.put("accountCode", accountCode);
		map.put("itemName", itemName);
		try {
			// dao.doUpdate("storeInsureInfo.updateInsureInfoByIdorWithholdNo", map);
			storeInsureInfoMapper.updateInsureInfoByIdorWithholdNo(map);
		} catch (Exception e) {
			// log.error(e);
			throw e;
		}
		
	}

	@Override
	public void paySuccessTwo(ResultVO result, NoncarOrder noncarOrder, String withholdNo, String accountCode) {
		com.ccic.salesapp.noncar.outService.esb.paysuccess.RequestHeadDTO headDto = new com.ccic.salesapp.noncar.outService.esb.paysuccess.RequestHeadDTO();
		headDto.setConsumerID("gateway");
		PaySuccessNoticeReqDTO requestDTO = new PaySuccessNoticeReqDTO();
		requestDTO.setCertiNo(noncarOrder.getInsureNo());
		requestDTO.setPayApplyNo(withholdNo);
		requestDTO.setRealPayFee(Double.parseDouble(noncarOrder.getSumPremium()));
		requestDTO.setAccountCode(accountCode);
		PaySuccessNoticeRequest request = new PaySuccessNoticeRequest();
		request.setRequestHead(headDto);
		request.setRequestBody(requestDTO);
		
		PaySuccessNoticeResponse resp = null;
		try {
			System.out.println(JSONObject.toJSONString(request));
			resp = paySuccessNoticePort.paySuccessNotice(request);
			System.out.println(JSONObject.toJSONString(resp));
			if (resp.getResponseHead().getStatus() != 0) {
				result.setCode("0");
				result.setMessage("支付通知接口，返回状态为失败。返回信息：" + resp.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用支付通知接口异常：", e);
			result.setCode("0");
			result.setMessage("调用收付费支付通知接口异常：" + e.getMessage());
		}		
	}

	@Override
	public void withholdStatusPortTwo(String operatorcode, String withholdNo, String accountCode, String paymentNo) {

		RequestHeadDTO headDto = this.getConsumerID();
		headDto.setConsumerID("gateway");
		WithholdStatusReqDTO requestVO = new WithholdStatusReqDTO();
		requestVO.setAccountCode(accountCode + "0");
		requestVO.setOperatorcode(operatorcode);
		requestVO.setWithholdNo(withholdNo);
		requestVO.setWithholdStatus("1_" + paymentNo);
		WithholdStatusRequest request = new WithholdStatusRequest();
		request.setRequestHead(headDto);
		request.setRequestBody(requestVO);
		WithholdStatusResponse resp = null;
		try {
			resp = withholdStatusPort.withholdStatus(request);
			if (resp.getResponseHead().getStatus() != 0) {
				log.info("预扣余额回滚失败，返回信息：" + resp.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用预扣余额回滚异常：", e);
		}
		
	}

}
