package com.ccic.salesapp.noncar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ccic.salesapp.noncar.dto.MessageDto;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.PaymentInformationVo;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.GetPayUrlRequestVO;
import com.ccic.salesapp.noncar.dto.request.PrePayMentRequestVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.dto.response.StoreOracleResponseVO;
import com.ccic.salesapp.noncar.dto.response.StoreOracleResponseVOTwo;
import com.ccic.salesapp.noncar.dto.submitMiddleGround.CombinationApplyNo;
import com.ccic.salesapp.noncar.dto.submitUwVo.ReqSubmitUwVo;
import com.ccic.salesapp.noncar.dto.submitUwVo.RespSubmitUwVo;
import com.ccic.salesapp.noncar.feign.MessageNoticeFeignClient;
import com.ccic.salesapp.noncar.feign.PayResultFeignService;
import com.ccic.salesapp.noncar.feign.SupportFeignService;
import com.ccic.salesapp.noncar.feign.bean.MessageSendVO;
import com.ccic.salesapp.noncar.feign.bean.PayResultConfirmRequestVO;
import com.ccic.salesapp.noncar.outService.esb.noCarUnderwriting.SubmitUnderwriteRequest;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PaymentNoApplyMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanStrategy;
import com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer;
import com.ccic.salesapp.noncar.service.ExtendedWarrantyFacade;
import com.ccic.salesapp.noncar.service.GroupPlanService;
import com.ccic.salesapp.noncar.service.OrderService;
import com.ccic.salesapp.noncar.service.PaymentNoApplyFacade;
import com.ccic.salesapp.noncar.service.SubmitUnderwritService;
import com.ccic.salesapp.noncar.service.impl.OrderServiceImpl;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.utils.DESEncryptUtil;
import com.ccic.salessapp.common.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@RestController
@Api(tags = "支付模块", description = "支付模块", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "carCode")
@Slf4j
public class PayCtl {

	@Autowired
	PaymentNoApplyMapper paymentNoApplyMapper;

	@Autowired
	PaymentNoApplyFacade paymentNoApplyFacade;

	@Autowired
	SubmitUnderwritService underwritService;

	@Autowired
	PaymentNoApplyFacade spl;

	@Autowired
	ExtendedWarrantyFacade extendedWarrantyFacade;

	@Autowired
	MessageNoticeFeignClient messageNoticeFeignClient;

	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;

	@Autowired
	SupportFeignService supportFeignService;

	@Autowired
	PayResultFeignService payResultFeignService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Value("${system.desPassword}")
	String desPassword;

	@ApiOperation(value = "支付申请查询", notes = "支付申请查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult getPaymentCode(@RequestBody String orderNo, String proposalNo, String userCode) throws Exception {

		return HttpResult.success(paymentNoApplyFacade.getPaymentCode1(orderNo, proposalNo, userCode));
	}

	@PostMapping("/getPayUrl")
	@ApiOperation(value = "获取支付跳转url", notes = "获取支付跳转url", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult getPayUrl(@RequestBody GetPayUrlRequestVO request) throws Exception {
		return paymentNoApplyFacade.getPayUrl(request);
	}

	@PostMapping("/getForwardUrl")
	@ApiOperation(value = "订单转发支付URL", notes = "订单转发支付URL", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult getForwardUrl(@RequestBody ForwardPayInsureInfoReqVO request) throws Exception {
		return paymentNoApplyFacade.getForwardUrl(request);
	}

	@PostMapping("/payResultConfirm")
	@ApiOperation(value = "支付成功后支付状态回写", notes = "支付成功后支付状态回写", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult payResultConfirm(@RequestBody ApplyPayVo applyPayVo) throws Exception {
		String proposalNo = DESEncryptUtil.decrypt(applyPayVo.getInsureNo(), desPassword);
		StoreInsureInfo storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(proposalNo);
		if (proposalNo.endsWith("-UNITE")) {
			groupPlanService.paySyncNotice(applyPayVo);
			return HttpResult.success(1,"SUCCESS");
		}
		else if ("1".equals(vehiclePayRedirect(proposalNo))) {
			PayResultConfirmRequestVO payResultConfirmRequestVO = new PayResultConfirmRequestVO();
			payResultConfirmRequestVO.setApplicationNo(applyPayVo.getInsureNo());
			payResultConfirmRequestVO.setPaySucceedFlag("1");
			return payResultFeignService.payResultConfirm(payResultConfirmRequestVO);
		} else {
			return HttpResult.success(paymentNoApplyFacade.payResultConfirm(applyPayVo, storeInsureInfo.getUserId()));
		}
	}

	@PostMapping("/applyPay")
	@ApiOperation(value = "支付号申请", notes = "支付号申请", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> applyPay(@RequestBody ApplyPayVo applyPayVo) {
		ResultVO result = new ResultVO();
		StoreOracleResponseVO respBody = new StoreOracleResponseVO();
		StoreInsureInfo storeInsureInfo = paymentNoApplyMapper.selectBySureInfoNo(applyPayVo);// insureNo
		StoreInsureInfo saveApplyPayment = spl.saveApplyPayment(storeInsureInfo, result);
		if ("1".equals(result.getCode())) {
			respBody.setInsureInfo(saveApplyPayment);
			HttpResult res = HttpResult.success(respBody);
			res.setCode("1");
			res.setMsg("申请成功");
			return res;
		} else {
			return HttpResult.error(0, "申请支付号失败");
		}
	}

	@PostMapping("/underwriting")
	@ApiOperation(value = "核保", notes = "核保", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> underwriting(@RequestBody ApplyPayVo applyPayVo) throws Exception {
		// 封装核保数据
		SubmitUnderwriteRequest requestBody = new SubmitUnderwriteRequest();
		requestBody.setProposalNo(applyPayVo.getInsureNo());
		StoreInsureInfo storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(applyPayVo.getInsureNo());
		if ("2".equals(storeInsureInfo.getStatus()) || "4".equals(storeInsureInfo.getStatus())) {
			return HttpResult.success(1, "核保成功");
		}
		// 提交核保
		Response acciResp = new Response();
		acciResp = underwritService.submitUW(requestBody);
		if (acciResp.getResponseHead() != null) {
			if (acciResp.getResponseHead().getStatus() != 0) {
				return HttpResult.error(0, "核保失败");
			}
		}
		/*
		 * 需要中为转发提交核保提醒，先注释掉 try { custAuthSuceedNotice(new String[]
		 * {applyPayVo.getInsureNo()}); } catch (Exception e) {
		 * log.error("发送核保消息提醒异常",e); }
		 */
		try {
			custPhotoUploadNotice(new String[] { applyPayVo.getInsureNo() });
		} catch (Exception e) {
			log.error("影像上传提醒异常", e);
		}
		return HttpResult.success(acciResp.getResponseBody(), 1, "核保成功");
	}

	@PostMapping("/underwritingStatus")
	@ApiOperation(value = "待核保状态", notes = "待核保状态", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> underwritingStatus(@RequestBody ApplyPayVo applyPayVo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("insureNo", applyPayVo.getInsureNo());
		int count = storeInsureInfoMapper.upUnderwritingStatus(map);
		if (count > 0)
			return HttpResult.success("", 1, "待核保状态更新成功");
		else
			return HttpResult.error(0, "待核保状态更新失败");
	}

	@PostMapping("/prePayMent")
	@ApiOperation(value = "ZFC支付", notes = "ZFC支付", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult prePayMent(@RequestBody PrePayMentRequestVO prePayMent) {
		ResultVO result = new ResultVO();
		if (StringUtils.isBlank(prePayMent.getInsureNo())) {
			return HttpResult.error(0, "投保单号不能为空！");
		}
		try {
			JsonVO jsonvo = extendedWarrantyFacade.prePayMent(prePayMent);
			return HttpResult.success(jsonvo.getData(), 1, jsonvo.getResult().getMessage());
		} catch (Exception e) {
			return HttpResult.error(0, "预付款请求异常");
		}
	}

	@PostMapping("/paySuccessNotice")
	public HttpResult paySuccessNotice(@RequestBody String[] notice) throws Exception {
		StoreInsureInfo storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(notice[0]);
		String message = "【" + storeInsureInfo.getInsuranceName() + "】客户【" + storeInsureInfo.getApplicantName()
				+ "】已完成订单支付";
		String messageType = "cust-pay-suceed";
		HttpResult result = saveMessage(storeInsureInfo.getUserId(), messageType, message);
		return result;
	}

	@PostMapping("/payFailNotice")
	public HttpResult payFailNotice(@RequestBody String[] notice) throws Exception {
		String proposalNo = DESEncryptUtil.decrypt(notice[0], desPassword);
		if ("1".equals(vehiclePayRedirect(proposalNo))) {
			PayResultConfirmRequestVO payResultConfirmRequestVO = new PayResultConfirmRequestVO();
			payResultConfirmRequestVO.setApplicationNo(notice[0]);
			payResultConfirmRequestVO.setPaySucceedFlag("0");
			return payResultFeignService.payResultConfirm(payResultConfirmRequestVO);
		} else {
			StoreInsureInfo storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(notice[0]);
			String message = "【" + storeInsureInfo.getInsuranceName() + "】客户【" + storeInsureInfo.getApplicantName()
					+ "】订单支付失败";
			String messageType = "cust-pay-fail";
			HttpResult result = saveMessage(storeInsureInfo.getUserId(), messageType, message);
			return result;
		}
	}

	@PostMapping("/custSignNotice")
	public HttpResult custSignNotice(@RequestBody String[] notice) throws Exception {
		StoreInsureInfo storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(notice[0]);
		String message = "【" + storeInsureInfo.getInsuranceName() + "】客户【" + storeInsureInfo.getApplicantName()
				+ "】已经签名确认，请查看订单";
		String messageType = "cust-sign";
		HttpResult result = saveMessage(storeInsureInfo.getUserId(), messageType, message);
		return result;
	}

	@PostMapping("/custPhotoUploadNotice")
	public HttpResult custPhotoUploadNotice(@RequestBody String[] notice) throws Exception {
		StoreInsureInfo storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(notice[0]);
		String message = "【" + storeInsureInfo.getInsuranceName() + "】客户【" + storeInsureInfo.getApplicantName()
				+ "】影像上传成功，请查看订单";
		String messageType = "cust-photo-upload";
		HttpResult result = saveMessage(storeInsureInfo.getUserId(), messageType, message);
		return result;
	}

	@PostMapping("/custAuthSuceedNotice")
	public HttpResult custAuthSuceedNotice(@RequestBody String[] notice) throws Exception {
		StoreInsureInfo storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(notice[0]);
		String message = "【" + storeInsureInfo.getInsuranceName() + "】客户【" + storeInsureInfo.getApplicantName()
				+ "】已经提交核保，请查看订单";
		String messageType = "cust-auth-suceed";
		HttpResult result = saveMessage(storeInsureInfo.getUserId(), messageType, message);
		return result;
	}

	public HttpResult saveMessage(String userCode, String messageType, String message) {
		// 调用消息通知服务
		List<MessageDto> messageList = new ArrayList<MessageDto>();
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(message);
		messageDto.setMessageType(messageType);
		messageDto.setPolicyNo("");
		messageDto.setSource("0");
		messageDto.setStatus("0");
		messageDto.setUserCode(userCode);
		messageDto.setExtendMessage("");
		messageList.add(messageDto);
		HttpResult result = supportFeignService.saveMessage(messageList);
		return HttpResult.success(1, "调用成功");
	}

	// 0非车 1车险
	public static String vehiclePayRedirect(String applicationNo) {
		return applicationNo.startsWith("TD") == true ? "1" : "0";
	}

	
	
	
	
	
	
	
	@Autowired
	GroupPlanService groupPlanService;
	
	/**
	 * ----------------更改
	 */
	@PostMapping("/payResultConfirmTwo")
	@ApiOperation(value = "支付成功后支付状态回写", notes = "支付成功后支付状态回写", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult payResultConfirmTwo(@RequestBody ApplyPayVo applyPayVo) throws Exception {
		String proposalNo = DESEncryptUtil.decrypt(applyPayVo.getInsureNo(), desPassword);
		//String proposalNo = applyPayVo.getInsureNo();
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectBynoncarOrder(proposalNo);
		if (proposalNo.endsWith("-UNITE")) {
			groupPlanService.paySyncNotice(applyPayVo);
			return HttpResult.success(1,"SUCCESS");
		}
		else if ("1".equals(vehiclePayRedirect(proposalNo))) {
			PayResultConfirmRequestVO payResultConfirmRequestVO = new PayResultConfirmRequestVO();
			payResultConfirmRequestVO.setApplicationNo(applyPayVo.getInsureNo());
			payResultConfirmRequestVO.setPaySucceedFlag("1");
			return payResultFeignService.payResultConfirm(payResultConfirmRequestVO);
		} 
		else {
			return HttpResult.success(paymentNoApplyFacade.payResultConfirmTwo(applyPayVo, noncarOrder.getUserCode()));
		}
	}

	@PostMapping("/applyPayTwo")
	@ApiOperation(value = "支付号申请", notes = "支付号申请", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> applyPayTwo(@RequestBody ApplyPayVo applyPayVo) {
		ResultVO result = new ResultVO();
		StoreOracleResponseVOTwo storeOracleResponseVO = new StoreOracleResponseVOTwo();
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectBySureInfoNoTwo(applyPayVo);// insureNo
		NoncarOrder saveApplyPayment = spl.saveApplyPaymentTwo(noncarOrder, result);
		if ("1".equals(result.getCode())) {
			storeOracleResponseVO.setNoncarOrder(saveApplyPayment);
			HttpResult res = HttpResult.success(storeOracleResponseVO);
			res.setCode("1");
			res.setMsg("申请成功");
			return res;
		} else {
			return HttpResult.error(0, "申请支付号失败");
		}
	}
	
	@PostMapping("/underwritingTwo")
	@ApiOperation(value = "核保", notes = "核保", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  HttpResult<?> underwritingTwo(@RequestBody ApplyPayVo applyPayVo) throws Exception {
		
		String insureNo = applyPayVo.getInsureNo();
		HttpResult underwriting = orderServiceImpl.underwriting(insureNo);
		// 封装核保数据
//		SubmitUnderwriteRequest requestBody = new SubmitUnderwriteRequest();
//		requestBody.setProposalNo(applyPayVo.getInsureNo());
//		String insureNo = applyPayVo.getInsureNo();
//		NoncarOrder noncarOrder = storeInsureInfoMapper.selectBynoncarOrder(insureNo);
//		if ("2".equals(noncarOrder.getOrderStatus()) || "4".equals(noncarOrder.getOrderStatus())) {
//			return HttpResult.success(1, "核保成功");
//		}
		// 校验是否上传影像  请--完成再进行核保！
//		PlanStrategy planStrategy = storeInsureInfoMapper.selectByStrategyId(noncarOrder.getStrategyId());
//		orderService.getNotUploadImages(planStrategy);
		
		// 检查是否需要签名
//		if ("1".equals(noncarOrder.getIsNeedSign())) {
//			// 检查是否已签名  
//			if ("1".equals(noncarOrder.getSignStatus())) {
//				// 提交核保
//				Response acciResp = new Response();
//				acciResp = underwritService.submitUWTwo(requestBody);
//				if (acciResp.getResponseHead() != null) {
//					if (acciResp.getResponseHead().getStatus() != 0) {
//						return HttpResult.error(0, "核保失败");
//					}else {
//						return HttpResult.success(acciResp.getResponseBody(), 1, "核保成功");
//					}
//				}
//			}else {
//				return HttpResult.error(0, "请签名完成再进行核保");
//			}
//		}
		// 提交核保
//		Response acciResp = new Response();
//		acciResp = underwritService.submitUWTwo(requestBody);
//		if (acciResp.getResponseHead() != null) {
//			if (acciResp.getResponseHead().getStatus() != 0) {
//				return HttpResult.error(0, "核保失败");
//			}
//		}
//		try {
//			custPhotoUploadNoticeTwo(new String[] { applyPayVo.getInsureNo() });
//		} catch (Exception e) {
//			log.error("影像上传提醒异常", e);
//		}
		return underwriting;
	}
	
	
	@PostMapping("/underwritingStatusTwo")
	@ApiOperation(value = "待核保状态", notes = "待核保状态", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> underwritingStatusTwo(@RequestBody ApplyPayVo applyPayVo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("insureNo", applyPayVo.getInsureNo());
		int count = storeInsureInfoMapper.upUnderwritingStatusTwo(map);
		if (count > 0)
			return HttpResult.success("", 1, "待核保状态更新成功");
		else
			return HttpResult.error(0, "待核保状态更新失败");
	}

	@PostMapping("/custAuthSuceedNoticeTwo")
	public HttpResult custAuthSuceedNoticeTwo(@RequestBody String[] notice) throws Exception {
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectNoncarOrderByInsureNo(notice[0]);
		String message = "【" + noncarOrder.getProductName() + "】客户【" + noncarOrder.getCustomerName()
				+ "】已经提交核保，请查看订单";
		String messageType = "cust-auth-suceed";
		String title = "已经提交核保";
		HttpResult result = saveMessageTwo(noncarOrder.getUserCode(), messageType, message,title);
		return result;
	}

	@PostMapping("/custPhotoUploadNoticeTwo")
	public HttpResult custPhotoUploadNoticeTwo(@RequestBody String[] notice) throws Exception {
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectNoncarOrderByInsureNo(notice[0]);
		String message = "【" + noncarOrder.getProductName() + "】客户【" + noncarOrder.getCustomerName() + "】影像上传成功，请查看订单";
		String messageType = "cust-photo-upload";
		String title = "影像上传成功";
		HttpResult result = saveMessageTwo(noncarOrder.getUserCode(), messageType, message, title);
		return result;
	}

	public HttpResult saveMessageTwo(String userCode, String messageType, String message, String title) {
		// 调用消息通知服务
		List<MessageSendVO> messageList = new ArrayList<MessageSendVO>();
		MessageSendVO messageSendVO = new MessageSendVO();
		messageSendVO.setMessage(message);
		messageSendVO.setMessageType(messageType);
		messageSendVO.setSource("0");
		messageSendVO.setUserCode(userCode);
		messageSendVO.setTitle(title);
		messageList.add(messageSendVO);
		HttpResult result = supportFeignService.sendMessage(messageList);
		return HttpResult.success(1, "调用成功");
	}

	@PostMapping("/custSignNoticeTwo")
	public HttpResult custSignNoticeTwo(@RequestBody String[] notice) throws Exception {
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectNoncarOrderByInsureNo(notice[0]);
		String message = "【" + noncarOrder.getProductName() + "】客户【" + noncarOrder.getCustomerName() + "】已经签名确认，请查看订单";
		String messageType = "cust-sign";
		String title = "已经签名确认";
		HttpResult result = saveMessageTwo(noncarOrder.getUserCode(), messageType, message, title);
		return result;
	}

	@PostMapping("/prePayMentTwo")
	@ApiOperation(value = "ZFC支付", notes = "ZFC支付", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult prePayMentTwo(@RequestBody PrePayMentRequestVO prePayMent) {
		ResultVO result = new ResultVO();
		if (StringUtils.isBlank(prePayMent.getInsureNo())) {
			return HttpResult.error(0, "投保单号不能为空！");
		}
		try {
			JsonVO jsonvo = extendedWarrantyFacade.prePayMentTwo(prePayMent);
			return HttpResult.success(jsonvo.getData(), 1, jsonvo.getResult().getMessage());
		} catch (Exception e) {
			return HttpResult.error(0, "预付款请求异常");
		}
	}

	@PostMapping("/paySuccessNoticeTwo")
	public HttpResult paySuccessNoticeTwo(@RequestBody String[] notice) throws Exception {
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectNoncarOrderByInsureNo(notice[0]);
		String message = "【" + noncarOrder.getProductName() + "】客户【" + noncarOrder.getCustomerName()
				+ "】已完成订单支付";
		String messageType = "cust-pay-suceed";
		String title = "已完成订单支付";
		HttpResult result = saveMessageTwo(noncarOrder.getUserCode(), messageType, message,title);
		return result;
	}

	@PostMapping("/payFailNoticeTwo")
	public HttpResult payFailNoticeTwo(@RequestBody String[] notice) throws Exception {
		String proposalNo = DESEncryptUtil.decrypt(notice[0], desPassword);
		if ("1".equals(vehiclePayRedirect(proposalNo))) {
			PayResultConfirmRequestVO payResultConfirmRequestVO = new PayResultConfirmRequestVO();
			payResultConfirmRequestVO.setApplicationNo(notice[0]);
			payResultConfirmRequestVO.setPaySucceedFlag("0");
			return payResultFeignService.payResultConfirm(payResultConfirmRequestVO);
		} else {
			NoncarOrder noncarOrder = storeInsureInfoMapper.selectNoncarOrderByInsureNo(notice[0]);
			String message = "【" + noncarOrder.getProductName() + "】客户【" + noncarOrder.getCustomerName()
					+ "】订单支付失败";
			String messageType = "cust-pay-fail";
			String title = "订单支付失败";
			HttpResult result = saveMessageTwo(noncarOrder.getUserCode(), messageType, message, title);
			return result;
		}
	}
	
	/**
	 * 支付信息
	 * @param paymentInformationVo
	 * @return
	 */
	@PostMapping("/PaymentInformation")
	public HttpResult paymentInformation(@RequestBody PaymentInformationVo paymentInformationVo) {
		ResultVO result = new ResultVO();
		NoncarOrder noncarOrder = null;
		if(paymentInformationVo.getProposalNo() != null) {
			noncarOrder= storeInsureInfoMapper.findPaymentInformationList(paymentInformationVo.getProposalNo());// insureNo
		}
		List<PaymentInformationVo> paymentInformation = storeInsureInfoMapper.findByInsureName(paymentInformationVo.getProposalNo());
		System.out.println(paymentInformation);
		PolicyCustomer policyCustomer = new PolicyCustomer();
		for (int i = 0; i < paymentInformation.size(); i++) {
			paymentInformation.get(i);
		}
		Map map = new HashMap<String, Object>();
		map.put("noncarOrder", noncarOrder);
		map.put("paymentInformation",paymentInformation);
		HttpResult res = HttpResult.success(JSON.toJSON(map));
		res.setCode("1");
		res.setMsg("支付信息");
		return res;
	}
	
	

	/**
	 * 组合核保
	 * @param applyPayVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/submissionUnderwriting")
	@ApiOperation(value = "组合核保", notes = "组合核保", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult submissionUnderwriting(@RequestBody ReqSubmitUwVo reqSubmitUwVo) throws Exception {
		// 判断核保状态
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectByNocarInfoNo(reqSubmitUwVo.getSubmissionNo());
		if ("2".equals(noncarOrder.getOrderStatus()) || "4".equals(noncarOrder.getOrderStatus())) {
			return HttpResult.success(1, "核保成功");
		}
		// 提交核保
		RespSubmitUwVo response = underwritService.submitMiddleGround(reqSubmitUwVo,noncarOrder.getProductCode());
        if (response.getResponseHead() != null ||response.getResponseBody()==null) {
            if (response.getResponseHead().getStatus() != 1) {
				return HttpResult.error(0, "核保失败");
			}
		}
		return HttpResult.success(response.getResponseBody(), 1, "核保成功");
	}

	@PostMapping("/custSignNoticeSubmission")
	public HttpResult custSignNoticeSubmission(@RequestBody String[] notice) throws Exception {
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectNoncarOrderBySubmission(notice[0]);
		String message = "【" + noncarOrder.getProductName() + "】客户【" + noncarOrder.getApplicantName() + "】已经签名确认，请查看订单";
		String messageType = "cust-sign";
		String title = "已经签名确认";
		HttpResult result = saveMessageTwo(noncarOrder.getUserCode(), messageType, message, title);
		return result;
	}
	
}
