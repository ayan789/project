package com.ccic.salesapp.noncar.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.AppCheckCode;
import com.ccic.salesapp.noncar.dto.ForwardPayUrl;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.PlanAttach;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.order.response.OrderResponseUrl;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.SendSmsRequestVo;
import com.ccic.salesapp.noncar.dto.request.SignUrlReqVO;
import com.ccic.salesapp.noncar.dto.request.UploadSignBookReqVO;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementDetaiReq;
import com.ccic.salesapp.noncar.dto.request.planelement.FormulaList;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;
import com.ccic.salesapp.noncar.dto.response.StoreOracleResponseVO;
import com.ccic.salesapp.noncar.dto.response.StoreOracleResponseVOTwo;
import com.ccic.salesapp.noncar.dto.submitMiddleGround.UploadSignBookSubmissionVo;
import com.ccic.salesapp.noncar.repository.basedb.mapper.AppCheckCodeMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderCtMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderDetailMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanStrategyMapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;
import com.ccic.salesapp.noncar.service.ImageService;
import com.ccic.salesapp.noncar.service.ModifyUserInfoService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salesapp.noncar.service.SubmitUnderwritService;
import com.ccic.salesapp.noncar.service.ZzbProductPlaceUtilFacade;
import com.ccic.salesapp.noncar.service.impl.OrderServiceImpl;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salesapp.noncar.dto.submitUwVo.ReqSubmitUwVo;

import lombok.extern.slf4j.Slf4j;


@RestController

@Slf4j
public class SignController {
	@Autowired
	OrderCtMapper orderCtMapper;
	@Autowired
	StoreProductPlaceUtilService stu;
	@Autowired
	ModifyUserInfoService impl;
	@Autowired
	ImageService signimpl;
	@Autowired
	ZzbProductPlaceUtilFacade orderUtil;
	@Autowired
	AppCheckCodeMapper appCheckCodeMapper;
	@Value("${system.signForWard}")
	String signForWard;
	@Autowired
	PayCtl payCtl;
	@Autowired
	OrderServiceImpl orderServiceImpl;
	@Autowired
	PlanFormCtl planFormCtl;
	@Autowired
	PlanStrategyMapper planStrategyMapper;
	@Autowired
	OrderDetailMapper orderDetailMapper;
	@Autowired
	SubmitUnderwritService submitUnderwritService;
	/**
	 * 上传签名
	 * @param
	 * @return
	 */
	@PostMapping("/uploadSignBook")
	public HttpResult uploadSignBook(@RequestBody UploadSignBookReqVO uploadSignBookReqVO){

		StoreInsureInfo storeInsureInfo = stu.findStoreInsureInfoByInsureNo(uploadSignBookReqVO.getInsuranceNo());
		System.out.println(System.currentTimeMillis());
		if(null!=storeInsureInfo) {
			if("1".equals(storeInsureInfo.getSignStatus())) {
				return HttpResult.error(0, "请勿重复签名！");
			}
			//		//校验验证码是否正确
			AppCheckCode appCheckCode = impl.getCheckCode(storeInsureInfo.getUserId() + storeInsureInfo.getMobileNo());
			if (appCheckCode != null) {
				long longTime = Long.parseLong(appCheckCode.getLongTime());
				long nowLongTime = Long.parseLong(String.valueOf(System.currentTimeMillis()));
				long cLongTime = nowLongTime - longTime;
				//验证码的时间为10分钟
				if (cLongTime > 0 && cLongTime < 600000) {
					if (!appCheckCode.getCheckCode().equals(uploadSignBookReqVO.getVerificationCode())) {
						return HttpResult.error(0, "验证码不正确！");

					}
				} else {
					return HttpResult.error(0, "验证码过期，请重新获取！");


				}
			}
		}else {
			return HttpResult.error(0, "投保单信息为null");
		}
		try {
			//查询投保信息
			Boolean c = signimpl.uploadSignBook(uploadSignBookReqVO,storeInsureInfo);
			if(c==true) {
				try {
					payCtl.custSignNotice(new String[] {storeInsureInfo.getInsureNo()});
				} catch (Exception e) {
					log.error("发送上传签名消息提醒异常",e);
				}
				int updateSignStatus = stu.updateSignStatus(storeInsureInfo);
				if (updateSignStatus >= 1) {
					StoreOracleResponseVO storeOracleResponseVO = new StoreOracleResponseVO();
					storeOracleResponseVO.setInsureInfo(storeInsureInfo);
					HttpResult result = HttpResult.success(storeOracleResponseVO,1,"上传成功并返回insureInfo信息");
					return result;

				}

				return HttpResult.success(c, 1, "上传成功");
			}
			return HttpResult.error(0,"上传失败");
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.error(0, "上传签名异常！");

		}


	}


	/**
	 * 产品订单转发
	 *
	 * @param
	 * @return
	 * @throws Exception
	 * 签名页面init
	 */
	@PostMapping("/forWardSignInit")
	public HttpResult forWardSignInit(@RequestBody ForwardPayInsureInfoReqVO forwardPayInsureInfo) throws Exception {
		String forwardDate = forwardPayInsureInfo.getForwardDate();
		String proposalNo = forwardPayInsureInfo.getProposalNo();
		if (StringUtils.isBlank(forwardDate) || StringUtils.isBlank(proposalNo)) {
			return HttpResult.error(0, "请求参数不正确");
		}
		System.out.println(System.currentTimeMillis());

		long time = System.currentTimeMillis() - Long.parseLong(forwardDate);
		int hours = (int) (time / (1000 * 60 * 60));
		if (hours > 24) {
			return HttpResult.error(0, "链接已失效");
		}
		/* ForWardSignInitResponseVO forWardSignInitResponseVO = new ForWardSignInitResponseVO();
        forWardSignInitResponseVO.setInsureNo(proposalNo);
       // StoreProductPlaceUtilService stu=new StoreProductPlaceUtilServiceImpl();
        StoreInsureInfo storeInsureInfo = stu.findStoreInsureInfoByInsureNo(proposalNo);
		if (null == storeInsureInfo) {

			return HttpResult.error(0, "查找失败");
		}

		forWardSignInitResponseVO.setMobile(storeInsureInfo.getMobileNo());
		HttpResult result = HttpResult.success(forWardSignInitResponseVO);*/
		return null;
	}



	//发送手机验证码
	@PostMapping("/signSendSMS")
	public HttpResult signSendSMS(@RequestBody SendSmsRequestVo sendSmsRequestVo){
		try {
			StoreInsureInfo storeInsureInfo = stu.findStoreInsureInfoByInsureNo(sendSmsRequestVo.getInsureNo());
			if(null==storeInsureInfo) {
				return HttpResult.error(0, "保单号不存在");
			}
			boolean isPass = false;
			if (!isPass) {
				SendSmsRequestVo smsRequest = new SendSmsRequestVo();
				smsRequest.setComcode(storeInsureInfo.getComCode());
				smsRequest.setMobile(storeInsureInfo.getMobileNo());
				smsRequest.setUserId(storeInsureInfo.getUserId() + storeInsureInfo.getMobileNo());
				HttpResult result = impl.sendsimplemessageservicerest(smsRequest, "动态密码checkCode,该验证码用于签名确认,切勿告知他人");
				return result;
			}else {
				return HttpResult.error(0, "请稍后，不要频繁操作！");
			}
		}catch (Exception e) {
			return HttpResult.error(0, "发送失败");
		}
	}


	/**
	 * 获取转发url
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getSignUrl")
	public HttpResult getSignUrl(SignUrlReqVO signUrlReqVO) throws Exception{
		/*JsonVO json = new JsonVO();
		ResultVO rlt = new ResultVO();*/
		try {
			//SignUrlReqVO signUrlReqVO= (SignUrlReqVO)jsonVO.getData();
			String proposalNo = signUrlReqVO.getProposalNo();
			String formulaCode = signUrlReqVO.getFormulaCode();
			String limQuantity = "";
			String productCode = "";
			if(StringUtils.isNotBlank(formulaCode)) {
				StoreFormulas storeFormulas = orderUtil.getProductCodeFormula(formulaCode);
				limQuantity = storeFormulas.getLimQuantity();
				productCode = storeFormulas.getProductCode();
			}

			if (StringUtils.isBlank(proposalNo)) {
				return HttpResult.error(0, "投保单号不能为空");
				/*rlt.setCode("0");
				rlt.setMessage("投保单号不能为空！");
				json.setResult(rlt);
				return json;*/
			}
			/*SystemConfig cofig=new SystemConfig("system");
			String signForWard = cofig.getResourceValue("signForWard");*/
			//String desProposalNo = DESEncryptUtil.encrypt(proposalNo,signForWard);
			//String desForwardDate = DESEncryptUtil.encrypt(String.valueOf(System.currentTimeMillis()), signForWard);
			String url = "?insureNo="+proposalNo+"&forwardDate="+String.valueOf(System.currentTimeMillis());
			ForwardPayUrl forwardPayUrl = new ForwardPayUrl();
			forwardPayUrl.setUrl(url);
			forwardPayUrl.setLimQuantity(limQuantity);
			forwardPayUrl.setProductCode(productCode);
			HttpResult result = HttpResult.success(forwardPayUrl,1,"成功");
			return result;
			/*log.info("返回体参数：{}"+ new Object[]{forwardPayUrl});
			rlt.setCode("1");
			rlt.setMessage("获取URL成功！");
			json.setData(forwardPayUrl);*/
		} catch (Exception e) {
			return HttpResult.error(0, "系统错误");
			/*rlt.setCode("0");
			rlt.setMessage("系统错误。");
			log.error(e);*/
		}
		/*json.setResult(rlt);
		return json;*/
	}















	/**
	 * 上传签名
	 * @param
	 * @return
	 */
	@PostMapping("/sign/upload")
	public HttpResult uploadSignBookTwo(@RequestBody UploadSignBookReqVO uploadSignBookReqVO){

		NoncarOrder noncarOrder = stu.findNoncarOrderByInsureNo(uploadSignBookReqVO.getInsuranceNo());
		System.out.println(System.currentTimeMillis());
		if(null!=noncarOrder) {
			if("1".equals(noncarOrder.getSignStatus())) {
				return HttpResult.error(0, "请勿重复签名！");
			}
			//		//校验验证码是否正确
			AppCheckCode appCheckCode = impl.getCheckCode(noncarOrder.getUserCode() + noncarOrder.getIndiMobile());
			if (appCheckCode != null) {
				long longTime = Long.parseLong(appCheckCode.getLongTime());
				long nowLongTime = Long.parseLong(String.valueOf(System.currentTimeMillis()));
				long cLongTime = nowLongTime - longTime;
				//验证码的时间为10分钟
				if (cLongTime > 0 && cLongTime < 600000) {
					if (!appCheckCode.getCheckCode().equals(uploadSignBookReqVO.getVerificationCode())) {
						return HttpResult.error(0, "验证码不正确！");
					}
				} else {
					return HttpResult.error(0, "验证码过期，请重新获取！");
				}
			}
		}else {
			return HttpResult.error(0, "投保单信息为null");
		}
		try {
			//查询投保信息
			Boolean c = signimpl.uploadSignBookTwo(uploadSignBookReqVO,noncarOrder);
			if(c==true) {
				try {
					payCtl.custSignNoticeTwo(new String[] {noncarOrder.getInsureNo()});
				} catch (Exception e) {
					log.error("发送上传签名消息提醒异常",e);
				}
				int updateSignStatus = stu.updateSignStatusTwo(noncarOrder);
				if (updateSignStatus >= 1) {
					log.info("####################调取核保开始");
					String insureNo = noncarOrder.getInsureNo();
					orderServiceImpl.underwriting(insureNo);
					log.info("####################调取核保结束");
					StoreOracleResponseVOTwo storeOracleResponseVO = new StoreOracleResponseVOTwo();
					String orderId = noncarOrder.getOrderId();
					Long valueOf = Long.valueOf(orderId);
					log.info("####################检查影像是否上传完整");
					List<String> notUploadImages = orderServiceImpl.getNotUploadImages(valueOf);
					noncarOrder.setNotUploadImgList(notUploadImages);
					storeOracleResponseVO.setNoncarOrder(noncarOrder);
					log.info("####################检查影像是否上传完整");
					HttpResult result = HttpResult.success(storeOracleResponseVO,1,"上传成功并返回insureInfo信息");
					return result;
				}
				return HttpResult.success(c, 1, "上传成功");
			}
			return HttpResult.error(0,"上传失败");
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.error(0, "上传签名异常！");
		}
	}


	/**
	 * 产品订单转发
	 *
	 * @param
	 * @return
	 * @throws Exception
	 * 签名页面init
	 */
	@PostMapping("/sign/initSign")
	public HttpResult forWardSignInitTwo(@RequestBody ForwardPayInsureInfoReqVO forwardPayInsureInfo) throws Exception {
		HttpResult result= HttpResult.success();
//		NoncarOrder noncarOrder = signimpl.findInsureMobile(forwardPayInsureInfo.getProposalNo());
		NoncarOrder noncarOrder = stu.findNoncarOrderByInsureNo(forwardPayInsureInfo.getProposalNo());
		//条款代码
		OrderResponseUrl response = new OrderResponseUrl();
		TermsAndConditions termsAndConditions = new TermsAndConditions() ;
		FormulaList formulaList = new FormulaList();
		formulaList.setPlanId(noncarOrder.getPlanId());
		PlanElementDetaiReq planElementDetaiReq = new PlanElementDetaiReq();
		planElementDetaiReq.setStrategyId(noncarOrder.getStrategyId());
		planFormCtl.buildTermsAndConditionsContent(termsAndConditions, formulaList);
		response.setTermsAndConditions(termsAndConditions);
		
		//投保人手机号
		String mobile = noncarOrder.getIndiMobile();
		String isIssueAfterPay = noncarOrder.getIsIssueAfterPay();
		
		
		
		//健康告知
		PlanAttach planAttach = signimpl.findhealthNotification(noncarOrder.getPlanId());
		Map healthNotificationM = new HashMap();
		if(planAttach != null && StringUtils.isNotBlank(planAttach.getAttachCode())) {
			healthNotificationM.put("title", "健康告知");
			String attachUrl = attachUrl(planAttach.getAttachUrl());
			healthNotificationM.put("url", attachUrl);
		}
		
		response.setHealthNotification(healthNotificationM);
		
		String forwardDate = forwardPayInsureInfo.getForwardDate();
		String proposalNo = forwardPayInsureInfo.getProposalNo();
		if (StringUtils.isBlank(forwardDate) || StringUtils.isBlank(proposalNo)) {
			return HttpResult.error(0, "请求参数不正确");
		}
		System.out.println(System.currentTimeMillis());
		long time = System.currentTimeMillis() - Long.parseLong(forwardDate);
		int hours = (int) (time / (1000 * 60 * 60));
		if (hours > 24) {
			return HttpResult.error(0, "链接已失效");
		}
		response.setMobile(mobile);
		response.setIsIssueAfterPay(isIssueAfterPay);
		result.setCode("1");
		result.setData(response);
		result.setMsg("SUCCESS");
		return result;
	}

	public String attachUrl(String urls) {
		 if(urls!=null) {
			 String[] u=urls.split("\\|\\|");
	 	     if(u.length==1)return u[0];
	 	     else if(u.length==2)return u[1];
	 	     else return null;
		 }
		 return null;
	}
	
	
	//发送手机验证码
	@PostMapping("/signSendSMSTwo")
	public HttpResult signSendSMSTwo(@RequestBody SendSmsRequestVo sendSmsRequestVo){
		try {
			NoncarOrder noncarOrder = null;
			if(StringUtils.isNotBlank(sendSmsRequestVo.getInsureNo())) {
				noncarOrder = stu.findNoncarOrderByInsureNo(sendSmsRequestVo.getInsureNo());
			}else if(StringUtils.isNotBlank(sendSmsRequestVo.getSubmissionNo())) {
				noncarOrder = stu.selectNoncarOrderByInsureNoSubmission(sendSmsRequestVo.getSubmissionNo());
			}
			if(null==noncarOrder) {
				return HttpResult.error(0, "保单号不存在");
			}
			boolean isPass = false;
			if (!isPass) {
				SendSmsRequestVo smsRequest = new SendSmsRequestVo();
				smsRequest.setComcode(noncarOrder.getComCode());
				smsRequest.setMobile(noncarOrder.getIndiMobile());
				smsRequest.setUserId(noncarOrder.getUserCode() + noncarOrder.getIndiMobile());
				if(smsRequest.getMobile()==null) {
					smsRequest.setMobile(noncarOrder.getMobile());
					smsRequest.setUserId(noncarOrder.getUserCode() + noncarOrder.getMobile());
				}
				HttpResult result = impl.sendsimplemessageservicerest(smsRequest, "动态密码checkCode,该验证码用于签名确认,切勿告知他人");
				return result;
			}else {
				return HttpResult.error(0, "请稍后，不要频繁操作！");
			}
		}catch (Exception e) {
			return HttpResult.error(0, "发送失败");
		}
	}
	
	
	//submissionNo
	@PostMapping("/submissionSignUpload")
	public HttpResult submissionSignUpload(@RequestBody UploadSignBookSubmissionVo uploadSignBookSubmissionVo){
		NoncarOrder noncarOrder = stu.selectNoncarOrderByInsureNoSubmission(uploadSignBookSubmissionVo.getSubmissionNo());
		System.out.println(System.currentTimeMillis());
		if(null!=noncarOrder) {
			if("1".equals(noncarOrder.getSignStatus())) {
				return HttpResult.error(0, "请勿重复签名！");
			}
			//		//校验验证码是否正确
			AppCheckCode appCheckCode = impl.getCheckCode(noncarOrder.getUserCode() + noncarOrder.getMobile());
			if (appCheckCode != null) {
				long longTime = Long.parseLong(appCheckCode.getLongTime());
				long nowLongTime = Long.parseLong(String.valueOf(System.currentTimeMillis()));
				long cLongTime = nowLongTime - longTime;
				//验证码的时间为10分钟
				if (cLongTime > 0 && cLongTime < 600000) {
					if (!appCheckCode.getCheckCode().equals(uploadSignBookSubmissionVo.getVerificationCode())) {
						return HttpResult.error(0, "验证码不正确！");
					}
				} else {
					return HttpResult.error(0, "验证码过期，请重新获取！");
				}
			}
		}else {
			return HttpResult.error(0, "投保单信息为null");
		}
		try {
				List<NoncarOrder> requestNoncarOrder = stu.findNoncarOrderByRelationOrderId(noncarOrder.getId());
				//查询投保信息
				Boolean c = signimpl.uploadSubmissionSignUpload(uploadSignBookSubmissionVo,requestNoncarOrder.get(0),requestNoncarOrder,noncarOrder);
			if(c==true) {
				try {
					// 签名成功后消息提醒
					payCtl.custSignNoticeSubmission(new String[] {noncarOrder.getInsureNo()});
				} catch (Exception e) {
					log.error("发送上传签名消息提醒异常",e);
				}
				//成功后更新签名状态
				int updateSignStatus = stu.updateSignStatusSubmission(noncarOrder);
                if (updateSignStatus >= 1) {
                    log.info("####################调取组合核保开始");
                    //封装数据
                    ReqSubmitUwVo reqSubmitUwVo = new ReqSubmitUwVo();
                    reqSubmitUwVo.setSubmissionNo(noncarOrder.getInsureNo());
                    HttpResult submissionUnderwriting = payCtl.submissionUnderwriting(reqSubmitUwVo);
                    String code = submissionUnderwriting.getCode();
                    int parseInt = Integer.parseInt(code);
                    if(parseInt!=1) {
                        return submissionUnderwriting;
                    }
                    log.info("####################调取核保结束");
                    return submissionUnderwriting;
                }
				return HttpResult.success(c, 1, "上传成功");
			}
			return HttpResult.error(0,"上传失败");
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.error(0, "上传签名异常！");
		}
	}
}
