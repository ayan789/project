//package com.ccic.salesapp.noncar.controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.ccic.salesapp.noncar.config.SystemConfig;
//import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
//import com.ccic.salesapp.noncar.dto.response.JsonVO;
//import com.ccic.salesapp.noncar.dto.response.StoreInsureInfoFormulasResponseVO;
//import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
//import com.ccic.salesapp.noncar.service.impl.StoreProductPlaceUtilServiceImpl;
//import com.ccic.salessapp.common.core.web.HttpResult;
//import com.ccic.salessapp.common.utils.DESEncryptUtil;
//import com.ccic.salessapp.common.utils.StringUtils;
//
//@RestController
//public class InsureController {
//	/**
//	 * 产品订单转发
//	 * 
//	 * @param jsonVO
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/forwardPayProductInsureInfo")
//	public HttpResult forwardPayProductInsureInfo(Object jsonVO) throws Exception {
//		
//		JsonVO json = (JsonVO) jsonVO;
//		ForwardPayInsureInfoReqVO forwardPayInsureInfo = (ForwardPayInsureInfoReqVO)json.getData();
//		String forwardDate = forwardPayInsureInfo.getForwardDate();
//		String proposalNo = forwardPayInsureInfo.getProposalNo();
//		if (StringUtils.isBlank(forwardDate) || StringUtils.isBlank(proposalNo)) {
//			HttpResult result = HttpResult.error(0, "请求参数不正确");
//			return result;
//		}
//		SystemConfig cofig = new SystemConfig("system");
//		String forwardPay = cofig.getResourceValue("forwardPay");
//		String desForwardDate = DESEncryptUtil.decrypt(forwardDate, forwardPay);
//		String desProposalNo = DESEncryptUtil.decrypt(proposalNo, forwardPay);
//
//		long time = System.currentTimeMillis() - Long.parseLong(desForwardDate);
//		int hours = (int) (time / (1000 * 60 * 60));
//		if (hours > 24) {
//			HttpResult result = HttpResult.error(0, "链接已失效！");
//			return result;
//		}
//		// 查询订单信息
//		StoreProductPlaceUtilService stu = new StoreProductPlaceUtilServiceImpl();
//		try {
//			StoreInsureInfoFormulasResponseVO fins = stu.findStoreInsureInfoAndFormulasByInsureNo(desProposalNo);
//			HttpResult result = HttpResult.success(json);
//			result.setCode("1");
//			result.setMsg("订单详情查询成功！");
//			return result;
//		} catch (Exception e) {
//			HttpResult result = HttpResult.error(0, "查询订单信息失败！");
//			return result;
//		}
//	}
//	
//}
