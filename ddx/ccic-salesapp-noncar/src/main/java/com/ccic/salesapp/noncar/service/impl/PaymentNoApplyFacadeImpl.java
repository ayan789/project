package com.ccic.salesapp.noncar.service.impl;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import com.ccic.salesapp.noncar.repository.basedb.mapper.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.config.SponsorSystem;
import com.ccic.salesapp.noncar.config.SystemConfig;
import com.ccic.salesapp.noncar.controller.PayCtl;
import com.ccic.salesapp.noncar.dto.Applicant;
import com.ccic.salesapp.noncar.dto.CarTax;
import com.ccic.salesapp.noncar.dto.CarTaxTJ;
import com.ccic.salesapp.noncar.dto.ForwardPayUrl;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationNew;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.Kind;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.PaymentNoApply;
import com.ccic.salesapp.noncar.dto.PaymentNoApplyRequestDTO;
import com.ccic.salesapp.noncar.dto.PaymentNoApplyResponseDTO;
import com.ccic.salesapp.noncar.dto.Paytype;
import com.ccic.salesapp.noncar.dto.ProposalInfoDTO;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.GetPayUrlRequestVO;
import com.ccic.salesapp.noncar.dto.request.IPreciseQuoteRequestVO;
import com.ccic.salesapp.noncar.dto.request.JointSaleVO;
import com.ccic.salesapp.noncar.dto.request.PayResultConfirmRequestVO;
import com.ccic.salesapp.noncar.dto.request.PaymentNoApplyRequest;
import com.ccic.salesapp.noncar.dto.request.PrivyVO;
import com.ccic.salesapp.noncar.dto.response.GetPayUrlResponseVO;
import com.ccic.salesapp.noncar.dto.response.IPaymentCodeResponseVO;
import com.ccic.salesapp.noncar.dto.response.IPaymentCodeVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.Order;
import com.ccic.salesapp.noncar.dto.response.PayResultConfirmResVO;
import com.ccic.salesapp.noncar.dto.response.PaymentNoApplyResponse;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.EasyQuoteFacade;
import com.ccic.salesapp.noncar.repository.databusdb.po.InsuranceApplicationNewDAO;
import com.ccic.salesapp.noncar.service.AbstractFacade;
import com.ccic.salesapp.noncar.service.OrderDetailsInqueryFacade;
import com.ccic.salesapp.noncar.service.PaymentNoApplyFacade;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salessapp.common.config.WebContants;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.utils.DESEncryptUtil;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.TokenUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.thoughtworks.xstream.core.util.Base64Encoder;




/**
 * 支付模块接口实现  
 * @author wangpeng
 *
 */


@Slf4j
@Service
public class PaymentNoApplyFacadeImpl extends AbstractFacade implements PaymentNoApplyFacade {

	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	PaymentNoApplyFacade paymentNoApplyFacade;

	@Autowired
	StoreProductPlaceUtilService stu;

	/* @Autowired */
	OrderDetailsInqueryFacade orderDetailFacade;

	@Autowired
	InsuranceApplicationDAO insuranceApplicationDAO;

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	CarTaxTJDAO carTaxTJDAO;

	@Autowired
	KindDAO kindDAO;

	@Autowired
	CarTaxDAO carTaxDAO;

	@Autowired
	ApplicantDAO applicantDAO;

	@Autowired
	InsuranceApplicationNewDAO insuranceApplicationNewDAO;

	@Autowired
	DAOBase dao;

	@Autowired
	com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApply paymentNoApply;
	

	@Autowired
	PaymentNoApplyMapper paymentNoApplyMapper;

	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;
	
	 @Autowired
    private ResourceLoader resourceLoader;
	    

	@Value("${system.systemSourceCode}")
	String systemSourceCode;
	
	@Value("${system.mobileQuickPayUrl}")
	String mobileQuickPayUrl;
	
	@Value("${system.desPassword}")
	String desPassword;
	
	@Value("${system.forwardPay}")
	String forwardPay;


	/**
	 * 联合销售,支付号申请
	 * @param orderNo,proposalNo,userCode
	 * 
	 * @author wangpeng
	 */
	@Override
	public HttpResult getPaymentCode1(String orderNo, String proposalNo,
			String userCode) throws Exception {

		JsonVO jsonVO = new JsonVO();
		HttpResult data=null;

		try {

			if(orderNo==null || "".equals(orderNo)){

				return HttpResult.error(0,"订单号不能为空！");
			}
			if(proposalNo==null || "".equals(proposalNo)){

				return HttpResult.error(0,"投保单号不能为空！");
			}
			jsonVO= this.getPaymentCode2(orderNo, proposalNo,userCode);
			return HttpResult.success(data);
		} catch (Exception e) {
			log.error("支付号申请异常！",e);
			HttpResult.error(0,"支付号申请异常！");

		}

		return data;
	}


	/**
	 * 
	 * @param 订单号,投保单号,用户ID
	 * 
	 * @author wangpeng
	 */
	public JsonVO getPaymentCode2(String orderNo, String proposalNo,String userCode) {
		String code = "1";//响应代码 1-成功，0-失败
		String message = "支付号申请成功！";//响应信息
		IPaymentCodeVO iPaymentCodeVO = null;
		HttpResult data=null;
		try {
			if(proposalNo.length()<24){
				iPaymentCodeVO = this.getPaymentNo(proposalNo,userCode);
			}else{
				iPaymentCodeVO = this.getNewPaymentNo(proposalNo,userCode);
			}
		} catch (Exception e) {
			log.error("支付号申请异常：", e);
			code="0";
			message="支付号申请失败："+e.getMessage();
		}

		JsonVO jsonVO = new JsonVO();
		ResultVO result=new ResultVO();
		IPaymentCodeResponseVO responseVO = new IPaymentCodeResponseVO();
		responseVO.setPaymentCodeVO(iPaymentCodeVO);
		data.setData(responseVO);	
		result.setCode(code);
		result.setMessage(message);				
		jsonVO.setResult(result);
		return jsonVO;
	}

	/**
	 * 支付号申请接口查询
	 *
	 */
	public JsonVO getPaymentCode3(String orderNo, String proposalNo,String userCode) throws Exception {
		String code = "1";//响应代码 1-成功，0-失败
		String message = "支付号申请成功！";//响应信息
		IPaymentCodeVO iPaymentCodeVO = null;
		HttpResult data=null;
		try {
			if(proposalNo.length()<24){
				iPaymentCodeVO = this.getPaymentNo(proposalNo,userCode);
			}else{
				iPaymentCodeVO = this.getNewPaymentNo(proposalNo,userCode);
			}
		} catch (Exception e) {
			log.error("支付号申请异常：", e);
			code="0";
			message="支付号申请失败："+e.getMessage();
		}

		JsonVO jsonVO = new JsonVO();
		ResultVO result=new ResultVO();
		IPaymentCodeResponseVO responseVO = new IPaymentCodeResponseVO();
		responseVO.setPaymentCodeVO(iPaymentCodeVO);
		data.setData(responseVO);	
		result.setCode(code);
		result.setMessage(message);				
		jsonVO.setResult(result);
		return jsonVO;
	}

	public IPaymentCodeVO getPaymentNo(String proposalNo,String userCode) throws Exception{
		IPaymentCodeVO iPaymentCodeVO=new IPaymentCodeVO();

		try {

			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("proposalNo", proposalNo);
			paramMap.put("userCode", userCode);//业务员代码
			com.ccic.salesapp.noncar.dto.InsuranceApplication ia = insuranceApplicationDAO.selectOrderDetailsInquiry(paramMap);
			Order o = orderDAO.selectByPrimaryKey(ia.getOrderId());



			//判断这个投保单有没有存在支付号
			if(o==null|| o.getOrderNo()==null){
				PaymentNoApplyRequestDTO pa = new PaymentNoApplyRequestDTO();
				pa.setPayType(Paytype.PAYTYPE_POS);
				//				if(StringUtils.isNotBlank(ia.getPayMethod())){
				//					pa.setPayType(ia.getPayMethod());
				//				}
				pa.setSystemCode(SponsorSystem.XINGXIAO);
				if("31".equals(ia.getComCode().substring(0,2))){
					pa.setOperateType("1");//平台交易号状态
				}
				Double sumPremium=0D;//总保费
				List<ProposalInfoDTO> proposalInfoDTOs=new ArrayList<ProposalInfoDTO>();
				ProposalInfoDTO proposalInfoDTO = new ProposalInfoDTO();
				proposalInfoDTO.setCertiNo(ia.getApplicationNo());
				//支付总金额	
				Double sumTax = null;//车船税
				if("1201".equals(ia.getComCode().substring(0,4))){// 天津车船税信息
					if (ia.getCarTaxTjId() != null) {
						CarTaxTJ carTaxTJ = carTaxTJDAO.selectByPrimaryKey(ia.getCarTaxTjId());
						if(carTaxTJ != null && carTaxTJ.getSumPayment() != null){
							sumTax = carTaxTJ.getSumPayment();
						}
					}
				}else{//车船税信息
					if (ia.getCarTaxId() != null) {
						CarTax carTax = carTaxDAO.selectByPrimaryKey(ia.getCarTaxId());
						if(carTax != null && carTax.getSumPayment() != null){
							sumTax = carTax.getSumPayment().doubleValue();
						}
					}
				}
				if(sumTax != null){
					BigDecimal a = new  BigDecimal(ia.getPremium().doubleValue()+sumTax);
					sumPremium  =  a.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					proposalInfoDTO.setAmount(sumPremium);
				}else{
					proposalInfoDTO.setAmount(ia.getPremium().doubleValue());
					sumPremium=ia.getPremium().doubleValue();
				}

				proposalInfoDTO.setComCode(ia.getComCode());
				proposalInfoDTO.setEffectDate(new SimpleDateFormat("yyyy-MM-dd").format(ia.getEffectDate()));
				proposalInfoDTO.setInsuranceCode(ia.getInsuranceCode());

				Applicant applicant = applicantDAO.selectByPrimaryKey(ia.getApplicantId());
				proposalInfoDTO.setMobilePhone(applicant.getPhone());
				proposalInfoDTOs.add(proposalInfoDTO);
				ProposalInfoDTO proposalInfoDTO1=null;
				if(StringUtils.isNotBlank(ia.getApplicationNoE())){//意外险投保单
					proposalInfoDTO1=new ProposalInfoDTO();
					proposalInfoDTO1.setCertiNo(ia.getApplicationNoE());//意外险投保单号
					proposalInfoDTO1.setComCode(ia.getComCode());//归属机构
					//联合销售，目前核心有对非车和新人身险的保险期限重新计算的规则，规则为，如果包含商业险，且起保时间不为0时，且保期满一年，会把保险期限改为第二天0时
					Calendar c = Calendar.getInstance();
					c.setTime(ia.getEffectDate());
					int hour = c.get(Calendar.HOUR_OF_DAY);
					if(("2".equals(ia.getInsuranceCode()) || "3".equals(ia.getInsuranceCode()))){
						//&& hour!=0 && DateUtils.addYears(ia.getEffectDate(), 1).getTime()<ia.getQuitDate().getTime())
						//						proposalInfoDTO1.setEffectDate(new SimpleDateFormat(
						//								"yyyy-MM-dd").format(DateUtils.addDays(ia.getEffectDate(), 1)));
					}else{
						proposalInfoDTO1.setEffectDate(new SimpleDateFormat(
								"yyyy-MM-dd").format(ia.getEffectDate()));	
					}
					proposalInfoDTO1.setInsuranceCode(ia.getInsuranceCode());
					proposalInfoDTO1.setMobilePhone(applicant.getPhone());
					proposalInfoDTO1.setAmount((ia.getSumPremiumCarAndE().subtract(new BigDecimal(proposalInfoDTO.getAmount())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
					sumPremium+=(ia.getSumPremiumCarAndE().subtract(new BigDecimal(proposalInfoDTO.getAmount())).doubleValue());
					proposalInfoDTOs.add(proposalInfoDTO1);
				}

				// 随车险、健康险保单补偿
				List<InsuranceApplicationNew> insuranceApplicationNews = insuranceApplicationNewDAO.selectByInsuranceApplicationNewID(ia.getId());
				if(insuranceApplicationNews!=null && insuranceApplicationNews.size()>0){
					for (InsuranceApplicationNew insuranceApplicationNew : insuranceApplicationNews) {
						proposalInfoDTO1=new ProposalInfoDTO();
						proposalInfoDTO1.setCertiNo(insuranceApplicationNew.getApplicationNo());
						proposalInfoDTO1.setComCode(ia.getComCode());
						//联合销售，目前核心有对非车和新人身险的保险期限重新计算的规则，规则为，如果包含商业险，且起保时间不为0时，且保期满一年，会把保险期限改为第二天0时
						Calendar c = Calendar.getInstance();
						c.setTime(ia.getEffectDate());
						int hour = c.get(Calendar.HOUR_OF_DAY);
						if(("2".equals(ia.getInsuranceCode()) || "3".equals(ia.getInsuranceCode())) ){
							//&& hour!=0 && DateUtils.addYears(ia.getEffectDate(), 1).getTime()<ia.getQuitDate().getTime()){
							//							proposalInfoDTO1.setEffectDate(new SimpleDateFormat(
							//									"yyyy-MM-dd").format(DateUtils.addDays(ia.getEffectDate(), 1)));
						}else{
							proposalInfoDTO1.setEffectDate(new SimpleDateFormat(
									"yyyy-MM-dd").format(ia.getEffectDate()));	
						}
						proposalInfoDTO1.setInsuranceCode(ia.getInsuranceCode());
						proposalInfoDTO1.setMobilePhone(applicant.getPhone());
						BigDecimal a = insuranceApplicationNew.getPremium();
						proposalInfoDTO1.setAmount(a.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
						sumPremium+=a.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
						proposalInfoDTOs.add(proposalInfoDTO1);
					}
				}
				pa.setProposalInfoDTO(proposalInfoDTOs);
				BigDecimal b = new  BigDecimal(Double.toString(sumPremium));
				sumPremium = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				pa.setSumFee(sumPremium);//汇总金额
				//支付号申请
				PaymentNoApplyResponseDTO par=this.paymentNoApply(pa, ia.getRiskCode());

				if (par != null && par.getPayApplyNo() != null){
					//支付号申请成功
					ia.setStatus(InsuranceApplicationStatus.DAIZHIFU);
					if (o == null){
						Order order = new Order();
						order.setId(dao.getSeq("B2BBUSI.S_ORDER_ID"));
						order.setOrderNo(par.getPayApplyNo());//支付号
						order.setIdentityCode(par.getIdentityCode());//支付验证码
						order.setCreateDate(new Date());
						order.setPayAmount(doubleToBigDecimal(sumPremium));//支付金额
						orderDAO.insert(order);
						ia.setOrderId(order.getId());
					}else{
						o.setOrderNo(par.getPayApplyNo());//支付号
						o.setIdentityCode(par.getIdentityCode());//支付验证码
						o.setCreateDate(new Date());
						o.setPayAmount(doubleToBigDecimal(sumPremium));//支付金额
						orderDAO.updateByPrimaryKey(o);
					}

					//更新投保单信息
					insuranceApplicationDAO.updateByPrimaryKey(ia);

					iPaymentCodeVO.setPayApplyNo(par.getPayApplyNo());//支付号
					iPaymentCodeVO.setIdentityCode(par.getIdentityCode());//支付验证码
					iPaymentCodeVO.setPayFee(sumPremium);//支付金额
				}
			}else{
				iPaymentCodeVO.setPayApplyNo(o.getOrderNo());//支付号
				iPaymentCodeVO.setIdentityCode(o.getIdentityCode());//支付验证码
				iPaymentCodeVO.setPayFee(o.getPayAmount().doubleValue());//支付金额
				log.info("投保单有支付号，直接返回。");

			}
			dao.commitTransation();
			return iPaymentCodeVO;
		}catch(Exception e){
			log.error("申请支付号异常", e);
			if(dao!=null){
				try {
					dao.endTransaction();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			throw e;
		}
	}

	/**
	 * 调用支付号申请接口
	 * @param paymentNoApplyRequestDTO
	 * @param riskCode
	 * @return
	 * @throws CustomException 
	 */
	public PaymentNoApplyResponseDTO paymentNoApply(PaymentNoApplyRequestDTO paymentNoApplyRequestDTO,String riskCode) throws Exception{
		log.info("支付号申请接口数据开始封装");
		PaymentNoApplyRequest req = new PaymentNoApplyRequest();
		RequestHeadDTO requestHead = this.getConsumerID();
		requestHead.setRiskCode(riskCode);
		req.setRequestBody(paymentNoApplyRequestDTO);
		req.setRequestHead(requestHead);
		PaymentNoApply service =  (PaymentNoApply) this.getBean("paymentNoApply");
		PaymentNoApplyResponse paymentNoApplyResponse = service.paymentNoApply(req);
		log.info("支付号申请查询接口调用完成");
		if(paymentNoApplyResponse != null && paymentNoApplyResponse.getResponseHead().getStatus()==0 && paymentNoApplyResponse.getResponseBody() != null){
			return paymentNoApplyResponse.getResponseBody();
		}else{
			throw new Exception(paymentNoApplyResponse.getResponseHead().getAppMessage());
		}
	}


	/**
	 * 新核心出单调用支付号申请接口数据封装
	 * 
	 * */
	private IPaymentCodeVO getNewPaymentNo(String proposalNo, String userCode)  throws Exception{
		IPaymentCodeVO iPaymentCodeVO=new IPaymentCodeVO();
		try {
			dao.startTransaction();
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("proposalNo", proposalNo);
			//paramMap.put("userCode", userCode);//业务员代码
			com.ccic.salesapp.noncar.dto.InsuranceApplication ia = insuranceApplicationDAO.selectOrderDetailsInquiry(paramMap);

			Order o = orderDAO.selectByPrimaryKey(ia.getOrderId());
			//判断这个投保单有没有存在支付号
			if(o==null|| o.getOrderNo()==null){

				List <Kind> kindList= kindDAO.selectKindList(ia.getQuotePriceInfoId());
				//商业险保费
				Double sumPremiumBS=0D;
				//交强险保费
				Double sumPremiumTS=0D;
				//总保费
				Double sumPremium=0D;
				if(kindList.size()>0){
					for(Kind kind:kindList){
						//交强险保费
						if(kind.getKindCode().equals(WebContants.N_INSURANCE_Kind_FROCE)){
							sumPremiumTS=sumPremiumTS+kind.getPremium().doubleValue();
							break;
						}	
					}
				}
				//车险保费
				Double premium=ia.getPremium().doubleValue();
				//商业险保费=车险保费-交强险保费
				sumPremiumBS=premium-sumPremiumTS;
				Double sumTax = null;//车船税
				if("1201".equals(ia.getComCode().substring(0,4))){// 天津车船税信息
					if (ia.getCarTaxTjId() != null) {
						CarTaxTJ carTaxTJ = carTaxTJDAO.selectByPrimaryKey(ia.getCarTaxTjId());
						if(carTaxTJ != null && carTaxTJ.getSumPayment() != null){
							sumTax = carTaxTJ.getSumPayment();
						}
					}
				}else{//车船税信息
					if (ia.getCarTaxId() != null) {
						CarTax carTax = carTaxDAO.selectByPrimaryKey(ia.getCarTaxId());
						if(carTax != null && carTax.getSumPayment() != null){
							sumTax = carTax.getSumPayment().doubleValue();
						}
					}
				}
				if(sumTax != null){
					sumPremiumTS  = sumPremiumTS+sumTax;
				}
				PaymentNoApplyRequestDTO pa = new PaymentNoApplyRequestDTO();
				pa.setPayType(Paytype.PAYTYPE_POS);
				//				if(StringUtils.isNotBlank(ia.getPayMethod())){
				//					pa.setPayType(ia.getPayMethod());
				//				}
				pa.setSystemCode(SponsorSystem.XINGXIAO);
				if("31".equals(ia.getComCode().substring(0,2))){
					pa.setOperateType("1");//平台交易号状态
				}

				Applicant applicant = applicantDAO.selectByPrimaryKey(ia.getApplicantId());
				List<ProposalInfoDTO> proposalInfoDTOs=new ArrayList<ProposalInfoDTO>();
				if(ia.gettApplicationNo()!=null){
					ProposalInfoDTO proposalInfoTC = new ProposalInfoDTO();
					proposalInfoTC.setComCode(ia.getComCode());
					proposalInfoTC.setEffectDate(new SimpleDateFormat("yyyy-MM-dd").format(ia.getEffectDate()));
					if(StringUtils.isNotBlank(ia.getEffectiveStartDate())){
						proposalInfoTC.setEffectDate(ia.getEffectiveStartDate().substring(0, 10));
					}
					proposalInfoTC.setInsuranceCode(ia.getInsuranceCode());
					proposalInfoTC.setMobilePhone(applicant.getPhone());
					proposalInfoTC.setCertiNo(ia.gettApplicationNo());
					proposalInfoTC.setAmount(sumPremiumTS);
					proposalInfoDTOs.add(proposalInfoTC);
				}
				ProposalInfoDTO proposalInfoDTO = new ProposalInfoDTO();
				proposalInfoDTO.setComCode(ia.getComCode());
				proposalInfoDTO.setEffectDate(new SimpleDateFormat("yyyy-MM-dd").format(ia.getEffectDate()));
				proposalInfoDTO.setInsuranceCode(ia.getInsuranceCode());
				proposalInfoDTO.setMobilePhone(applicant.getPhone());
				proposalInfoDTO.setCertiNo(ia.getApplicationNo());
				if(ia.getApplicationNo().substring(1,4).equals("DFA")||ia.getApplicationNo().substring(1,4).equals("DFB")){
					proposalInfoDTO.setAmount(sumPremiumTS);
				}else{
					BigDecimal a = new  BigDecimal(Double.toString(sumPremiumBS));
					sumPremiumBS = a.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					proposalInfoDTO.setAmount(sumPremiumBS);
				}
				sumPremium=sumPremiumTS+sumPremiumBS;
				proposalInfoDTOs.add(proposalInfoDTO);
				ProposalInfoDTO proposalInfoDTO1=null;
				if(StringUtils.isNotBlank(ia.getApplicationNoE())){//意外险投保单
					proposalInfoDTO1=new ProposalInfoDTO();
					proposalInfoDTO1.setCertiNo(ia.getApplicationNoE());//意外险投保单号
					proposalInfoDTO1.setComCode(ia.getComCode());//归属机构
					//					//联合销售，目前核心有对非车和新人身险的保险期限重新计算的规则，规则为，如果包含商业险，且起保时间不为0时，且保期满一年，会把保险期限改为第二天0时
					//					Calendar c = Calendar.getInstance();
					//					c.setTime(ia.getEffectDate());
					//					int hour = c.get(Calendar.HOUR_OF_DAY);
					//					if(("2".equals(ia.getInsuranceCode()) || "3".equals(ia.getInsuranceCode())) 
					//							&& hour!=0 && DateUtils.addYears(ia.getEffectDate(), 1).getTime()<ia.getQuitDate().getTime()){
					//						proposalInfoDTO1.setEffectDate(new SimpleDateFormat(
					//								"yyyy-MM-dd").format(DateUtils.addDays(ia.getEffectDate(), 1)));
					//					}else{
					proposalInfoDTO1.setEffectDate(new SimpleDateFormat(
							"yyyy-MM-dd").format(ia.getEffectDate()));	
					//					}
					proposalInfoDTO1.setInsuranceCode(ia.getInsuranceCode());
					proposalInfoDTO1.setMobilePhone(applicant.getPhone());
					proposalInfoDTO1.setAmount(ia.getSumpremiumE().setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
					sumPremium+=proposalInfoDTO1.getAmount();
					proposalInfoDTOs.add(proposalInfoDTO1);
				}

				// 随车险、健康险保单补偿
				List<InsuranceApplicationNew> insuranceApplicationNews = insuranceApplicationNewDAO.selectByInsuranceApplicationNewID(ia.getId());
				if(insuranceApplicationNews!=null && insuranceApplicationNews.size()>0){
					for (InsuranceApplicationNew insuranceApplicationNew : insuranceApplicationNews) {
						proposalInfoDTO1=new ProposalInfoDTO();
						proposalInfoDTO1.setCertiNo(insuranceApplicationNew.getApplicationNo());
						proposalInfoDTO1.setComCode(ia.getComCode());
						//						//联合销售，目前核心有对非车和新人身险的保险期限重新计算的规则，规则为，如果包含商业险，且起保时间不为0时，且保期满一年，会把保险期限改为第二天0时
						//						Calendar c = Calendar.getInstance();
						//						c.setTime(ia.getEffectDate());
						//						int hour = c.get(Calendar.HOUR_OF_DAY);
						//						if(("2".equals(ia.getInsuranceCode()) || "3".equals(ia.getInsuranceCode())) 
						//								&& hour!=0 && DateUtils.addYears(ia.getEffectDate(), 1).getTime()<ia.getQuitDate().getTime()){
						//							proposalInfoDTO1.setEffectDate(new SimpleDateFormat(
						//									"yyyy-MM-dd").format(DateUtils.addDays(ia.getEffectDate(), 1)));
						//						}else{
						proposalInfoDTO1.setEffectDate(new SimpleDateFormat(
								"yyyy-MM-dd").format(ia.getEffectDate()));	
						//						}
						proposalInfoDTO1.setInsuranceCode(ia.getInsuranceCode());
						proposalInfoDTO1.setMobilePhone(applicant.getPhone());
						BigDecimal a = insuranceApplicationNew.getPremium();
						proposalInfoDTO1.setAmount(a.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
						sumPremium+=a.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
						proposalInfoDTOs.add(proposalInfoDTO1);
					}
				}
				pa.setProposalInfoDTO(proposalInfoDTOs);
				BigDecimal b = new  BigDecimal(Double.toString(sumPremium));
				sumPremium = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				pa.setSumFee(sumPremium);//汇总金额
				//支付号申请
				PaymentNoApplyResponseDTO par=this.paymentNoApply(pa, ia.getRiskCode());

				if (par != null && par.getPayApplyNo() != null){
					//支付号申请成功
					ia.setStatus(InsuranceApplicationStatus.DAIZHIFU);
					if (o == null){
						Order order = new Order();
						order.setId(dao.getSeq("B2BBUSI.S_ORDER_ID"));
						order.setOrderNo(par.getPayApplyNo());//支付号
						order.setIdentityCode(par.getIdentityCode());//支付验证码
						order.setCreateDate(new Date());
						order.setPayAmount(doubleToBigDecimal(sumPremium));//支付金额
						orderDAO.insert(order);
						ia.setOrderId(order.getId());
					}else{
						o.setOrderNo(par.getPayApplyNo());//支付号
						o.setIdentityCode(par.getIdentityCode());//支付验证码
						o.setCreateDate(new Date());
						o.setPayAmount(doubleToBigDecimal(sumPremium));//支付金额
						orderDAO.updateByPrimaryKey(o);
					}
					//更新投保单信息
					insuranceApplicationDAO.updateByPrimaryKey(ia);
					iPaymentCodeVO.setPayApplyNo(par.getPayApplyNo());//支付号
					iPaymentCodeVO.setIdentityCode(par.getIdentityCode());//支付验证码
					iPaymentCodeVO.setPayFee(sumPremium);//支付金额
				}
			}else{
				iPaymentCodeVO.setPayApplyNo(o.getOrderNo());//支付号
				iPaymentCodeVO.setIdentityCode(o.getIdentityCode());//支付验证码
				iPaymentCodeVO.setPayFee(o.getPayAmount().doubleValue());//支付金额
				log.info("投保单有支付号，直接返回。");

			}
			dao.commitTransation();
			return iPaymentCodeVO;
		}catch(Exception e){
			log.error("申请支付号异常", e);
			if(dao!=null){
				try {
					dao.endTransaction();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			throw e;
		}	
	}

	/**
	 * 获取支付跳转url
	 * @param vo
	 * @param sc
	 * 
	 * @author wangpeng
	 */
	@Override
	public HttpResult getPayUrl(GetPayUrlRequestVO requestVO) throws Exception {

		try {

			String proposalNo = requestVO.getProposalNo();
			String proposalNoOrgin = proposalNo;
			String paymentNo = requestVO.getPaymentNo();
			String checkNo = requestVO.getCheckNo();
			String flag = requestVO.getFlag();

			if(proposalNo==null || "".equals(proposalNo)){


				return HttpResult.error(0,"投保单号不能为空！");
			}
			if(paymentNo==null || "".equals(paymentNo)){

				return HttpResult.error(0,"支付号不能为空！");
			}
			if(checkNo==null || "".equals(checkNo)){

				return HttpResult.error(0,"验证码不能为空！");
			}
			StoreInsureInfo storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(proposalNo);
			if(storeInsureInfo != null ) {
				if("10".equals(storeInsureInfo.getStatus()) ) {//失效
					throw new PlatformBaseException("该投保单不是有效状态！", 0);
				}
				if("8".equals(storeInsureInfo.getStatus()) ) {//删除
					throw new PlatformBaseException("该投保单已被删除！", 0);
				}
			}else {
				ApplyPayVo applyPayVo  = new ApplyPayVo();
				applyPayVo.setInsureNo(proposalNo);
				NoncarOrder noncarOrder  = storeInsureInfoMapper.selectBySureInfoNoTwo(applyPayVo);
				if(noncarOrder != null && "10".equals(noncarOrder.getOrderStatus()) ) {//失效
					throw new PlatformBaseException("该投保单不是有效状态！", 0);
				}
				if(noncarOrder != null && "1".equals(noncarOrder.getIsDelete()) ) {//删除
					throw new PlatformBaseException("该投保单已被删除！", 0);
				}
			}
			String consumerID = null;
			/*SystemConfig cofig=new SystemConfig("system");
			consumerID = cofig.getResourceValue("consumerID");
			systemSourceCode = cofig.getResourceValue("systemSourceCode");
			mobileQuickPayUrl = cofig.getResourceValue("mobileQuickPayUrl");
			desPassword = cofig.getResourceValue("desPassword");
			systemSourceCode="18";
			mobileQuickPayUrl="https://test.95590.cn/ebw-pay/mobile/mobileQuickPay.htm";
			desPassword="368e1f96fe";*/
			consumerID="supsale";
			String desPayNo = DESEncryptUtil.encrypt(paymentNo,desPassword);
			String desCheckNo= DESEncryptUtil.encrypt(checkNo,desPassword);
			proposalNo = DESEncryptUtil.encrypt(proposalNo,desPassword);
			GetPayUrlResponseVO resVO = new GetPayUrlResponseVO();
			String payUrl = null;
			String basePayUrl = mobileQuickPayUrl+"?consumerID="+consumerID+"&systemSourceCode="+systemSourceCode+"&paymentNo="+desPayNo+"&checkNo="+desCheckNo+"&ext="+proposalNo;
			if ("1".equals(flag)) { // 二维码支付
				payUrl = basePayUrl;
				String qrCode = encode(payUrl);
				resVO.setQrCode(qrCode);
			} else if ("2".equals(flag)) { //直接访问快钱
				payUrl = basePayUrl+ "&goDirect=KUAIQIAN";
			} else if ("3".equals(flag)) { //直接访问兴业银行支付宝
				payUrl = basePayUrl+ "&goDirect=CIB_ALIPAYSCAN";
			} else if ("4".equals(flag)) { //直接访问兴业银行微信
				payUrl = basePayUrl+ "&goDirect=CIB_WECHATSCAN";
			} else if ("5".equals(flag)) {  //直接访问支付宝手机网页支付
				payUrl = basePayUrl+ "&goDirect=ALIPAYTI";
				if("TDD".equals(proposalNoOrgin.substring(0,3))){
					this.getInsureInfo(proposalNoOrgin, resVO);
					String qrCode = encode(mobileQuickPayUrl+"?consumerID="+consumerID+"&systemSourceCode="+systemSourceCode+"&paymentNo="+desPayNo+"&checkNo="+desCheckNo+"&goDirect=ALIPAYTI");
					resVO.setQrCode(qrCode);
				}
			} else if ("6".equals(flag)) {// 银行卡快捷支付
				payUrl = basePayUrl + "&goDirect=QUICKPAY";
			} else if ("7".equals(flag)) {// 微信支付
				payUrl = basePayUrl + "&goDirect=WECHAT";
			} else if ("8".equals(flag)) {// 招行扫码支付
				payUrl = basePayUrl + "&goDirect=CMBC_SCAN";
			} else if ("9".equals(flag)) {// POS支付
				payUrl = null;
			}else { // 其他情况，先访问主页面 
				payUrl = basePayUrl;
			}
			resVO.setUrl(payUrl);
			log.info("返回体参数：{}"+ new Object[]{resVO});
			return HttpResult.success(resVO,1,"成功");
		} catch (Exception e) {

			log.error("接口调用失败！", e);
			return HttpResult.error(0,"接口调用失败！");

		}
	}

	/**
	 * 获取转发url
	 * @param jsonVO
	 * @param servletContext
	 * @return
	 * @throws Exception 
	 */
	@Override
	public HttpResult getForwardUrl(ForwardPayInsureInfoReqVO forwardPayInsureInfo)
			throws Exception {

		try {

			String proposalNo = forwardPayInsureInfo.getProposalNo();
			if (StringUtils.isBlank(proposalNo)) {

				return HttpResult.error(0,"投保单号不能为空！");
			}
			/*DAOBase dao = new DefaultDAO();
			InsuranceApplicationDAO insDao = new InsuranceApplicationDAOImpl(dao.getSqlMapClient());
			List<InsuranceApplication> inss = insDao.selectByProposalNo(proposalNo);*/

			StoreInsureInfo storeInsureInfo = stu.findStoreInsureInfoByInsureNo(proposalNo);
			//			if(!((inss!=null && inss.size()>0 && inss.get(0).getOperatorNo().equals(jsonVO.getHead().getLoginUser().getUserCode())
			//					)||(storeInsureInfo != null && storeInsureInfo.getUserId().equals(jsonVO.getHead().getLoginUser().getUserCode())))){
			//				rlt.setCode("0");
			//				rlt.setMessage("无权操作！");
			//				json.setResult(rlt);
			//				return json;
			//			}
			
			String desProposalNo = DESEncryptUtil.encrypt(proposalNo,forwardPay);
			String desForwardDate = DESEncryptUtil.encrypt(String.valueOf(System.currentTimeMillis()), forwardPay);
			String url = "?proposalNo="+desProposalNo+"&forwardDate="+desForwardDate;
			ForwardPayUrl forwardPayUrl = new ForwardPayUrl();
			forwardPayUrl.setUrl(url);
			log.info("返回体参数：{}"+ new Object[]{forwardPayUrl});
			return HttpResult.success(url,1,"订单转发支付获取URL成功");

		} catch (Exception e) {

			HttpResult.error(0,"系统错误。");
		}
		return null;

	}

	/**
	 *支付成功后支付状态回写
	 * @param vo
	 * @param sc
	 * 
	 * @author wangcw
	 */
	@Override
	public JsonVO payResultConfirm(ApplyPayVo applyPayVo,String userCode)
			throws Exception {
		
		JsonVO json = new JsonVO();
		ResultVO resultVO = new ResultVO();
		//PayResultConfirmRequestVO requestVO= applyPayVo.in;
		//INPaymentNoApplyFacade paymentNoApplyFacade = new INPaymentNoApplyFacadeImpl(this.sc);
		String proposalNo=null;
		try {
			proposalNo=applyPayVo.getInsureNo();
			if(proposalNo==null || "".equals(proposalNo)){
				resultVO.setCode("0");
				resultVO.setMessage("投保单号不能为空！");
				json.setResult(resultVO);
				return json;
			}
			proposalNo = DESEncryptUtil.decrypt(proposalNo, desPassword);
			JsonVO jsonVO2 = this.payResultConfirmLocal(proposalNo,userCode);
			json.setData(jsonVO2.getData());
			json.setResult(jsonVO2.getResult());
		} catch (Exception e) {
			log.error("支付成功状态回写异常！",e);
			resultVO.setCode("0");
			resultVO.setMessage("支付成功状态回写异常！");
			json.setResult(resultVO);
		}
		return json;

	}

	@Autowired
	PayCtl payctl;
	/**
	 * 支付成功状态回写
	 * @param proposalNo 投保单号
	 * */
	public JsonVO payResultConfirmLocal(String proposalNo,String userCode) throws Exception {
		JsonVO jsonVO=new JsonVO();
		ResultVO resultVO = new ResultVO();
		List<com.ccic.salesapp.noncar.dto.InsuranceApplication> insList = null;
		try{
				//
				Map<String, Object> param=new HashMap<String, Object>();
				param.put("insureNo", proposalNo);
				int i = storeInsureInfoMapper.upPayStsate(param);
				//int i = dao.doUpdate("storeInsureInfo.upPayStsate", param);//更新门店信息
				if(i>0){
					resultVO.setCode("1");
					resultVO.setMessage("支付完成，请在订单列表查看保单信息。");
					jsonVO.setResult(resultVO);
					try {
						payctl.paySuccessNotice(new String[] {proposalNo});
					} catch (Exception e) {
						e.printStackTrace();
					}
					return jsonVO;
					
				}else{
					resultVO.setCode("0");
					resultVO.setMessage("支付完成，请在订单列表查看保单信息。");
					jsonVO.setResult(resultVO);
					return jsonVO;
				}
		}catch(Exception e){
			log.error("支付成功状态回写失败：",e);
			resultVO.setCode("0");
			resultVO.setMessage(e.getMessage());
			jsonVO.setResult(resultVO);
			return jsonVO;
		}
	}
	
	
	
	/**
	 * 二维码生成
	 * 
	 * @param contents
	 * @return
	 * @author ligh
	 * @throws Exception
	 */
	private String encode(String contents) throws Exception {
		// 二维码颜色
		final int BLACK = 0xFF000000;// 0xFFFF0000，红色
		// 二维码背景色
		final int WHITE = 0xFFFFFFFF;// 0xFF0000FF，蓝色
		// 注：二维码颜色色差大，扫描快，但如果"BLACK'设置为黑色外其他颜色，可能无法扫描
		// 二维码图片宽度
		final int WIDTH = 240;
		// 二维码图片高度
		final int HEIGHT = WIDTH;
		// 二维码格式参数
		final EnumMap<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
		/*
		 * 二维码的纠错级别(排错率),4个级别： L (7%)、 M (15%)、 Q (25%)、 H (30%)(最高H)
		 * 纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少；共有四级； 选择M，扫描速度快。
		 */
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		// 二维码边界空白大小 1,2,3,4 (4为默认,最大)
		hints.put(EncodeHintType.MARGIN, 1);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MAX_SIZE, 350);
		hints.put(EncodeHintType.MIN_SIZE, 150);

		BufferedImage image = null;
		ByteArrayOutputStream stream = null;
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
			matrix = deleteWhite(matrix);// 去除白边
			image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);

			int width = matrix.getWidth();
			int height = matrix.getHeight();
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
				}
			}
			// 添加logo
			Graphics2D gs = image.createGraphics();
			 ClassPathResource resource=new ClassPathResource("logo.png"); 
			Image img = ImageIO.read(resource.getInputStream());
			int widthLogo = img.getWidth(null);// img.getWidth(null)>image.getWidth()*2/10?(image.getWidth()*2/10):img.getWidth(null);
			int heightLogo = img.getHeight(null);// img.getHeight(null)>image.getHeight()*2/10?(image.getHeight()*2/10):img.getWidth(null);
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - heightLogo) / 2;
			gs.drawImage(img, x, y, widthLogo, heightLogo, null);
			gs.dispose();
			img.flush();
			image.flush();
			// 写入流
			stream = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", stream);
			byte[] data = stream.toByteArray();
			// 通过base64转码字符串
			Base64Encoder base64 = new Base64Encoder();
			String qrCode = base64.encode(data);
			System.out.println("data:image/jpg;base64,"+qrCode);
			return qrCode;
		} catch (Exception e) {
			log.error("生成支付二维码失败！", e);
			throw new Exception("生成支付二维码失败！");
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
	}

	private void getInsureInfo(String proposalNo,GetPayUrlResponseVO resVO) throws Exception{
		//调用查询订单详情方法,获取订单信息

		JsonVO orderDetailJsonVO = orderDetailFacade.OrderDetailsInquery(null,proposalNo, null, null,null);


		//		if ("0".equals(orderDetailJsonVO.getCode())) {
		//			
		//		}

		IPreciseQuoteRequestVO orderDetail = (IPreciseQuoteRequestVO) orderDetailJsonVO.getData();		
		//获取投保人信息
		List<PrivyVO> privys = orderDetail.getPrivy();
		if (privys.size()>0) {
			for (int i = 0; i < privys.size(); i++) {
				PrivyVO privy = privys.get(i);
				if(WebContants.C_INSURANCE_RELATIONSHIP_POLICYHOLDER.equals(privy.getInsuredFlag())) {
					resVO.setApplicantName(privy.getInsuredName());
					resVO.setApplicantIdentifyType(privy.getIdentifyType());
					resVO.setApplicantIdentifyNo(privy.getIdentifyNumber());
					break;
				}	
			}
		}
		resVO.setStartDate(orderDetail.getStartDate());
		resVO.setEndDate(orderDetail.getEndDate());
		resVO.setCiSumPremium(orderDetail.getCiSumPremium());
		resVO.setBiSumPremium(orderDetail.getBiSumPremium());
		resVO.setSumCartax(orderDetail.getSumCartax());
		if("1".equals(orderDetail.getJointSaleFlag())){
			for(JointSaleVO jointSaleVO : orderDetail.getJointSales()){
				if("E".equals(jointSaleVO.getJoinRiskCode().substring(0,1))){
					resVO.setJoinSaleE(jointSaleVO.getFormulaName()+"_"+jointSaleVO.getSumPremium());
				}else if("YEJ".equals(jointSaleVO.getJoinRiskCode())){
					resVO.setJoinSaleY(jointSaleVO.getFormulaName()+"_"+jointSaleVO.getSumPremium());
				}
			}
		}
		resVO.setReceivingSide("中国大地财产保险股份有限公司");
	}

	private BitMatrix deleteWhite(BitMatrix matrix){  
		int[] rec = matrix.getEnclosingRectangle();  
		int resWidth = rec[2] + 1;  
		int resHeight = rec[3] + 1;  

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);  
		resMatrix.clear();  
		for (int i = 0; i < resWidth; i++) {  
			for (int j = 0; j < resHeight; j++) {  
				if (matrix.get(i + rec[0], j + rec[1]))  
					resMatrix.set(i, j);  
			}  
		}  
		return resMatrix;  
	}
	
	@Override
	public StoreInsureInfo saveApplyPayment(StoreInsureInfo insureInfo,ResultVO result) {
		com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequestDTO paymentNoApplyRequestDTO = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequestDTO();
		com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO proposalInfoDTO = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO();
		proposalInfoDTO.setCertiNo(insureInfo.getInsureNo());//投保单号
		proposalInfoDTO.setComCode(insureInfo.getComCode());//机构代码
		proposalInfoDTO.setInsuranceCode(insureInfo.getInsuranceCode());//险种代码
		proposalInfoDTO.setEffectDate(datef.format(insureInfo.getStartDate()));//起保日期
		proposalInfoDTO.setAmount(Double.parseDouble(insureInfo.getSumPremium()));//金额
		paymentNoApplyRequestDTO.setSumFee(Double.parseDouble(insureInfo.getSumPremium()));
		paymentNoApplyRequestDTO.getProposalInfoDTO().add(proposalInfoDTO);
		paymentNoApplyRequestDTO.setSystemCode("00");//申请系统
		paymentNoApplyRequestDTO.setPayType("1");//支付方式
		paymentNoApplyRequestDTO.setSumFee(Double.parseDouble(insureInfo.getSumPremium()));//汇总金额
		result.setCode("1");//响应代码 1-成功，0-失败
		result.setMessage("申请支付号成功");//响应信息
		RequestHeadDTO head=new RequestHeadDTO();
		com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequest request=new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequest();
		head.setConsumerID("supsale");
		head.setSeqNo("");
		request.setRequestHead(head);
		request.setRequestBody(paymentNoApplyRequestDTO);
		//payment=(PaymentNoApply)this.getBean("paymentNoApply");
		try {
			System.out.println(JSONObject.toJSONString(request));
			com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyResponse response = paymentNoApply.paymentNoApply(request);
			System.out.println(JSONObject.toJSONString(response)); 
			if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用申请支付号接口出错：返回信息："+response.getResponseHead().getAppMessage());
				return insureInfo;
			}else{
				insureInfo.setPayApplyNo(response.getResponseBody().getPayApplyNo());//获取支付号
				insureInfo.setIdentityCode(response.getResponseBody().getIdentityCode());//获取验证码
				insureInfo.setStatus(InsuranceApplicationStatus.DAIZHIFU);//待支付
			}
		} catch (Exception e) {
			log.error("调用申请支付号接口异常：", e);
			result.setCode("0");
			result.setMessage("调用申请支付号接口异常："+e.getMessage());	
		}
		updateInsureInfo(insureInfo, result);//调用保存下单方法更新记录
		return insureInfo;
	}
		

		public int updateInsureInfo(StoreInsureInfo insureInfo,
				ResultVO result) {
			int row =0;
			try {
				row = paymentNoApplyMapper.doUpdate(insureInfo);
			} catch (Exception e) {
				log.info("投保单信息更新失败！");
			}
			return (Integer) row;
		}


		/***
		 * 更改新增的
		 */
		@Override
		public JsonVO payResultConfirmTwo(ApplyPayVo applyPayVo, String userCode) throws Exception {
			JsonVO json = new JsonVO();
			ResultVO resultVO = new ResultVO();
			String proposalNo=null;
			try {
				proposalNo=applyPayVo.getInsureNo();
				if(proposalNo==null || "".equals(proposalNo)){
					resultVO.setCode("0");
					resultVO.setMessage("投保单号不能为空！");
					json.setResult(resultVO);
					return json;
				}
				proposalNo = DESEncryptUtil.decrypt(proposalNo, desPassword);
				JsonVO jsonVO2 = this.payResultConfirmLocalTwo(proposalNo,userCode);
				json.setData(jsonVO2.getData());
				json.setResult(jsonVO2.getResult());
			} catch (Exception e) {
				log.error("支付成功状态回写异常！",e);
				resultVO.setCode("0");
				resultVO.setMessage("支付成功状态回写异常！");
				json.setResult(resultVO);
			}
			return json;
		}
		/**
		 * 支付成功状态回写
		 * @param proposalNo 投保单号
		 * */
		public JsonVO payResultConfirmLocalTwo(String proposalNo,String userCode) throws Exception {
			JsonVO jsonVO=new JsonVO();
			ResultVO resultVO = new ResultVO();
			List<com.ccic.salesapp.noncar.dto.InsuranceApplication> insList = null;
			try{
					//
					Map<String, Object> param=new HashMap<String, Object>();
					param.put("insureNo", proposalNo);
					int i = storeInsureInfoMapper.upPayStsateTwo(param);
					//int i = dao.doUpdate("storeInsureInfo.upPayStsate", param);//更新门店信息
					if(i>0){
						resultVO.setCode("1");
						resultVO.setMessage("支付完成，请在订单列表查看保单信息。");
						jsonVO.setResult(resultVO);
						try {
							payctl.paySuccessNoticeTwo(new String[] {proposalNo});
						} catch (Exception e) {
							e.printStackTrace();
						}
						return jsonVO;
						
					}else{
						resultVO.setCode("0");
						resultVO.setMessage("支付完成，请在订单列表查看保单信息。");
						jsonVO.setResult(resultVO);
						return jsonVO;
					}
			}catch(Exception e){
				log.error("支付成功状态回写失败：",e);
				resultVO.setCode("0");
				resultVO.setMessage(e.getMessage());
				jsonVO.setResult(resultVO);
				return jsonVO;
			}
		}


		@Override
		public NoncarOrder saveApplyPaymentTwo(NoncarOrder noncarOrder, ResultVO result) {
			com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequestDTO paymentNoApplyRequestDTO = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequestDTO();
			com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO proposalInfoDTO = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO();
			proposalInfoDTO.setCertiNo(noncarOrder.getInsureNo());//投保单号
			proposalInfoDTO.setComCode(noncarOrder.getComCode());//机构代码
			proposalInfoDTO.setInsuranceCode(noncarOrder.getProductCode());//险种代码
			proposalInfoDTO.setEffectDate(datef.format(noncarOrder.getStartDate()));//起保日期
			proposalInfoDTO.setAmount(Double.parseDouble(noncarOrder.getSumPremium()));//金额
			paymentNoApplyRequestDTO.setSumFee(Double.parseDouble(noncarOrder.getSumPremium()));
			paymentNoApplyRequestDTO.getProposalInfoDTO().add(proposalInfoDTO);
			paymentNoApplyRequestDTO.setSystemCode("00");//申请系统
			paymentNoApplyRequestDTO.setPayType("1");//支付方式
			paymentNoApplyRequestDTO.setSumFee(Double.parseDouble(noncarOrder.getSumPremium()));//汇总金额
			result.setCode("1");//响应代码 1-成功，0-失败
			result.setMessage("申请支付号成功");//响应信息
			RequestHeadDTO head=new RequestHeadDTO();
			com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequest request=new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequest();
			head.setConsumerID("supsale");
			head.setSeqNo("");
			request.setRequestHead(head);
			request.setRequestBody(paymentNoApplyRequestDTO);
			//payment=(PaymentNoApply)this.getBean("paymentNoApply");
			try {
				System.out.println(JSONObject.toJSONString(request));
				com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyResponse response = paymentNoApply.paymentNoApply(request);
				System.out.println(JSONObject.toJSONString(response)); 
				if (response.getResponseHead().getStatus()!=0) {
					result.setCode("0");
					result.setMessage("调用申请支付号接口出错：返回信息："+response.getResponseHead().getAppMessage());
					return noncarOrder;
				}else{
					noncarOrder.setPayApplyNo(response.getResponseBody().getPayApplyNo());//获取支付号
					noncarOrder.setIdentityCode(response.getResponseBody().getIdentityCode());//获取验证码
					noncarOrder.setOrderStatus(InsuranceApplicationStatus.DAIZHIFU);//待支付
				}
			} catch (Exception e) {
				log.error("调用申请支付号接口异常：", e);
				result.setCode("0");
				result.setMessage("调用申请支付号接口异常："+e.getMessage());	
			}
			updateInsureInfoTwo(noncarOrder, result);//调用保存下单方法更新记录
			return noncarOrder;
		}
		
		@Autowired
		OrderMapper orderMapper;
		
		@Override
		public NoncarOrder saveApplyPaymentThree(NoncarOrder noncarOrder, ResultVO result) {
			com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequestDTO paymentNoApplyRequestDTO = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequestDTO();
			
			com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO proposalInfoDTO;
			double allPremium = 0;
			List<HashMap<String, Object>> list = orderMapper.selectByOrderIdPaymentNoApply(Long.parseLong(noncarOrder.getId()));
			for(int i = 0 ;i<list.size();i++) {
				proposalInfoDTO = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO();
				proposalInfoDTO.setCertiNo(list.get(i).get("insureNo")+"");//投保单号
				proposalInfoDTO.setComCode(list.get(i).get("comCode")+"");//机构代码
				proposalInfoDTO.setInsuranceCode(list.get(i).get("productCode")+"");//险种代码
				proposalInfoDTO.setEffectDate(datef.format(list.get(i).get("startDate")));//起保日期
				proposalInfoDTO.setAmount(Double.parseDouble(list.get(i).get("premium")+""));//金额
				paymentNoApplyRequestDTO.getProposalInfoDTO().add(proposalInfoDTO);
				allPremium = add(allPremium, Double.parseDouble(list.get(i).get("premium")+""));
			}
			
			//=================start改造=========================
//			proposalInfoDTO.setCertiNo("TYEF20340119020000090082");//投保单号
//			proposalInfoDTO.setComCode(noncarOrder.getComCode());//机构代码
//			proposalInfoDTO.setInsuranceCode(noncarOrder.getProductCode());//险种代码
//			proposalInfoDTO.setEffectDate(datef.format(noncarOrder.getStartDate()));//起保日期
//			proposalInfoDTO.setAmount(900d);//金额
////			paymentNoApplyRequestDTO.setSumFee(1800d);
//			paymentNoApplyRequestDTO.getProposalInfoDTO().add(proposalInfoDTO);
//			
//			com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO proposalInfoDTO2 = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO();
//			proposalInfoDTO2.setCertiNo("TYEF20340119020000090083");//投保单号
//			proposalInfoDTO2.setComCode(noncarOrder.getComCode());//机构代码
//			proposalInfoDTO2.setInsuranceCode(noncarOrder.getProductCode());//险种代码
//			proposalInfoDTO2.setEffectDate(datef.format(noncarOrder.getStartDate()));//起保日期
//			proposalInfoDTO2.setAmount(900d);//金额
//			paymentNoApplyRequestDTO.getProposalInfoDTO().add(proposalInfoDTO2);
			
//			com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO proposalInfoDTO3 = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO();
//			proposalInfoDTO3.setCertiNo("投保单号C");//投保单号
//			proposalInfoDTO3.setComCode(noncarOrder.getComCode());//机构代码
//			proposalInfoDTO3.setInsuranceCode(noncarOrder.getProductCode());//险种代码
//			proposalInfoDTO3.setEffectDate(datef.format(noncarOrder.getStartDate()));//起保日期
//			proposalInfoDTO3.setAmount(Double.parseDouble(noncarOrder.getSumPremium()));//金额
//			paymentNoApplyRequestDTO.getProposalInfoDTO().add(proposalInfoDTO3);
			//=================end改造=========================
			
			paymentNoApplyRequestDTO.setSystemCode("00");//申请系统
			paymentNoApplyRequestDTO.setPayType("1");//支付方式
			paymentNoApplyRequestDTO.setSumFee(allPremium);//汇总金额
			result.setCode("1");//响应代码 1-成功，0-失败
			result.setMessage("申请支付号成功");//响应信息
			RequestHeadDTO head=new RequestHeadDTO();
			com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequest request=new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequest();
			head.setConsumerID("supsale");
			head.setSeqNo("");
			request.setRequestHead(head);
			request.setRequestBody(paymentNoApplyRequestDTO);
			//payment=(PaymentNoApply)this.getBean("paymentNoApply");
			try {
				System.out.println(JSONObject.toJSONString(request));
				com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyResponse response = paymentNoApply.paymentNoApply(request);
				System.out.println(JSONObject.toJSONString(response)); 
				if (response.getResponseHead().getStatus()!=0) {
					result.setCode("0");
					result.setMessage("调用申请支付号接口出错：返回信息："+response.getResponseHead().getAppMessage());
					return noncarOrder;
				}else{
					noncarOrder.setPayApplyNo(response.getResponseBody().getPayApplyNo());//获取支付号
					noncarOrder.setIdentityCode(response.getResponseBody().getIdentityCode());//获取验证码
					noncarOrder.setOrderStatus(InsuranceApplicationStatus.DAIZHIFU);//待支付
				}
			} catch (Exception e) {
				log.error("调用申请支付号接口异常：", e);
				result.setCode("0");
				result.setMessage("调用申请支付号接口异常："+e.getMessage());	
			}
			updateInsureInfoTwo(noncarOrder, result);//调用保存下单方法更新记录
			return noncarOrder;
		}
		
		
		public int updateInsureInfoTwo(NoncarOrder noncarOrder,
				ResultVO result) {
			int row =0;
			try {
				row = paymentNoApplyMapper.doUpdateTwo(noncarOrder);
			} catch (Exception e) {
				log.info("投保单信息更新失败！");
			}
			return (Integer) row;
		}
		
		public static double add(double v1, double v2) {
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.add(b2).doubleValue();
	    }
}



