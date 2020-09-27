package com.ccic.salesapp.noncar.service.impl;

import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreFormulasMapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;
import com.ccic.salesapp.noncar.service.AbstractFacade;
import com.ccic.salesapp.noncar.service.ZzbProductPlaceUtilFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class ZzbProductPlaceUtilFacadeImpl extends AbstractFacade implements ZzbProductPlaceUtilFacade {
	@Autowired
	StoreFormulasMapper storeFormulasMapper;

	/*Log log = LogFactory.getLog(this.getClass().getName());
	DefaultDAO dao = new DefaultDAO();	*/
	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	/*@Override
	public ProposalGenerateResponseBody quoteProposalGenerateInfo(ProposalGenerateRequestBody proposalGenerateRequestBody,ResultVO result,UserVO userVO) {
		ProposalGenerateRequest request=new ProposalGenerateRequest();
		RequestHeadDTO head=new RequestHeadDTO();
		head.setClassCode("Y");
		head.setConsumerID("mobile");
		//更新经办人信息
		updateHandlerInfo(null,null,proposalGenerateRequestBody,null,userVO);
		if(proposalGenerateRequestBody.getAgent()!=null && StringUtils.isNotBlank(proposalGenerateRequestBody.getAgent().getAgentCode())){
			updateCommissionInfo(null,proposalGenerateRequestBody);
		}
		request.setRequestHead(head);
		request.setRequestBody(proposalGenerateRequestBody);
		log.info("####  调用货运险试算接口start####-请求数据"+ JSONObject.fromObject(request).toString());
		ProposalGenerateResponse response=null;
		result.setCode("1");//响应代码 1-成功，0-失败
		result.setMessage("调用货运险试算成功");//响应信息
		//发送试算请求并接收返回值
		ProposalGeneratePort pgPort = (ProposalGeneratePort)this.getBean("proposalGenerateService");
		try {
			response = pgPort.proposalGenerate(request);
			log.info("####  调用货运险试算接口end###"+ JSONObject.fromObject(response).toString());
			if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用货运险试算接口，返回状态为失败。返回信息："+response.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用货运险试算接口异常：", e);
			result.setCode("0");
			result.setMessage("调用货运险试算接口异常："+e.getMessage());
		}
		return response.getResponseBody();
	}


	@Override
	public void SubmitUnderwrite(String InsureNo, String riskCode,ResultVO result) {
		SubmitUnderwriteRequestDTO submitUnderwriteRequestDTO = new SubmitUnderwriteRequestDTO();
		
		submitUnderwriteRequestDTO.setCertiNo(InsureNo);//单号
		submitUnderwriteRequestDTO.setCertiType("T");//单号类型
		submitUnderwriteRequestDTO.setRiskCode(riskCode);//险种
		result.setCode("1");//响应代码 1-成功，0-失败
		result.setMessage("提交核保成功试算成功");//响应信息
		
		SubmitUnderwriteRequest request=new SubmitUnderwriteRequest();
		RequestHeadDTO head=new RequestHeadDTO();
		if("JAB".indexOf(submitUnderwriteRequestDTO.getRiskCode()) > -1){
			head.setClassCode("J");
		}else if("ZKF".indexOf(submitUnderwriteRequestDTO.getRiskCode()) > -1){
			head.setClassCode("Z");
		}else if("YEJ".indexOf(submitUnderwriteRequestDTO.getRiskCode()) > -1){
			head.setClassCode("Y");
		}else if("ZBY".indexOf(submitUnderwriteRequestDTO.getRiskCode()) > -1){
			head.setClassCode("Z");
		}
		head.setConsumerID("mobile");
		head.setSeqNo("");
		request.setRequestHead(head);
		request.setRequestBody(submitUnderwriteRequestDTO);
		log.info("####  调用非车险提交核保接口start###"+ JSONObject.fromObject(request).toString());
		SubmitUnderwrite underwriteService = (SubmitUnderwrite)this.getBean("submitUnderwriteService");
		SubmitUnderwriteResponse repsonse =null;
		try {
			repsonse=underwriteService.submitUnderwrite(request);
			log.info("####  调用非车险提交核保接口end###"+ JSONObject.fromObject(repsonse).toString());
			if (repsonse.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用财产险核保接口，返回状态为失败。返回信息："+repsonse.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用提交核保接口异常：", e);
			result.setCode("0");
			result.setMessage("调用提交核保接口异常："+e.getMessage());
		}
	}
	
	
	
	@Override
	public void preUndwrt(String buinessno, ResultVO result) {
		PreUndwrtRequestDTO requestDTO = new PreUndwrtRequestDTO();
		requestDTO.setBusinessno(buinessno);
		requestDTO.setBusinesstype("T");//单号类型
		
		result.setCode("1");//响应代码 1-成功，0-失败
		result.setMessage("提交核保成功试算成功");//响应信息
		
		PreUndwrtRequest request = new PreUndwrtRequest();
		RequestHeadDTO head=new RequestHeadDTO();
		head.setConsumerID("mobile");
		head.setSeqNo("");
		request.setRequestHead(head);
		request.setRequestBody(requestDTO);
		log.info("####  调用意健险提交核保接口start###"+ JSONObject.fromObject(request).toString());
		PreUndwrt service = (PreUndwrt)this.getBean("preUndwrtService");
		PreUndwrtResponse response =null;
		try {
			response = service.preUndwrt(request);
			log.info("####  调用意健险提交核保接口end###"+ JSONObject.fromObject(response).toString());
			if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用意健险提交核保，返回状态为失败。返回信息："+response.getResponseHead().getAppMessage());
			}
			log.info("意健险提交核保返回报文："+response);
		} catch (Exception e) {
			log.error("调用提交核保接口异常：", e);
			result.setCode("0");
			result.setMessage("调用提交核保接口异常："+e.getMessage());
		}
		
	}
	@Override
	public StoreInsureInfo saveInsureInfo(StoreInsureInfo info, ZzbOracleRequestVO storeReqVO,ResultVO result,UserVO userVO) throws Exception {
		StoreInsureInfo insureInfo = info;
		insureInfo.setCreateDate(new Date());//记录创建日期
		insureInfo.setLicensePlateNo(storeReqVO.getLicensePlateNo());//车牌号
		insureInfo.setStartTime("0");
		try {
			insureInfo.setStartDate(datef.parse(storeReqVO.getStartDate()));//保单起期
			insureInfo.setEndDate(datef.parse(storeReqVO.getEndDate()));
			insureInfo.setBirthDate(datef.parse(storeReqVO.getInsuredBirthDate()));//出生日期
		} catch (ParseException e1) {
		} //保单止期
		insureInfo.setEndTime("24");
		insureInfo.setApplicantName(storeReqVO.getAppliName());//投保人姓名
		insureInfo.setAppliIdentify(storeReqVO.getAppliIdentifyNumber());//投保人身份证
		if (StringUtils.isNotBlank(storeReqVO.getAppliIdentifyType())) {
			insureInfo.setAppliIdentifyType(storeReqVO.getAppliIdentifyType());	//投保人证件类型
		}else{
			insureInfo.setAppliIdentifyType("01");	//投保人证件类型默认身份证
		}
		
				
		insureInfo.setInsuredName(storeReqVO.getInsuredName());//被保人/被监护人
		insureInfo.setInsuredIdentify(storeReqVO.getInsuredIdentifyNumber());//被保人身份证/被监护人身份证
		if (StringUtils.isNotBlank(storeReqVO.getInsuredIdentifyType())) {
			insureInfo.setInsuredIdentifyType(storeReqVO.getInsuredIdentifyType());	//被保人证件类型
		}else{
			insureInfo.setInsuredIdentifyType("01");	//被保人证件类型默认身份证
		}
		if (StringUtils.isNotBlank(storeReqVO.getInsuredBirthDate())) {
			insureInfo.setBeGuardianBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(storeReqVO.getInsuredBirthDate())); //被监护人生日
			
		}
		if (StringUtils.isNotBlank(storeReqVO.getBeGuardianSex())) {
			insureInfo.setBeGuardianSex(storeReqVO.getBeGuardianSex());	//被监护人性别
		}
		insureInfo.setProductCode(storeReqVO.getProductCode());//产品代码
		
		insureInfo.setInsuranceCount(storeReqVO.getFormulaSize());//投保份数
		insureInfo.setHandlerCode2(storeReqVO.getAgentInfo().getHandler2Code());//保存经办人2 
		insureInfo.setMobileNo(storeReqVO.getAppliMobile());//手机号
		if (storeReqVO.getAddressInfo() != null) {
			insureInfo.setAddress(storeReqVO.getAddressInfo().getAddressName());//详细地址信息=======================================
		}
		insureInfo.setRelateInsured(storeReqVO.getAppliIdentity());//被保人与投保人的关系
		insureInfo.setEmail(storeReqVO.getAppliEMail());//邮箱
		insureInfo.setFormulaCode(storeReqVO.getFormulaCode());                  //方案代码
		insureInfo.setFormulaName(storeReqVO.getFormulaName());                  //方案名称
		insureInfo.setImei(storeReqVO.getImei());                                //设备号
		insureInfo.setIsRunApp(storeReqVO.getIsRunApp());
		AgentInfoVO agentInfoVO = storeReqVO.getAgentInfo(); 
		if(agentInfoVO!=null){
			insureInfo.setAgentCode(storeReqVO.getAgentInfo().getAgentCode());  //代理人代码
			insureInfo.setAgreementNo(storeReqVO.getAgentInfo().getAgreementNo());//代理人协议号
			insureInfo.setAgreementName(storeReqVO.getAgentInfo().getAgreementName());//代理人协议名称
		}
		insureInfo.setIsOpenair(storeReqVO.getIsOpenair());
		insureInfo.setShowNo(storeReqVO.getShowNo());
		insureInfo.setShowName(storeReqVO.getShowName());
		//更新经办人信息
		updateHandlerInfo(null,null,null,insureInfo,userVO);
		try {
			Long id = Long.parseLong(dao.getSeq("CCICSALEMOBILE.SEQ_STORE_INSUREINFO_ID").toString());
			insureInfo.setId(id);
			dao.startTransaction();
			dao.doInsert("storeInsureInfo.doInsert", insureInfo);//保单信息表新增记录
			if (storeReqVO.getAccidentalInjuryInfos()!=null && storeReqVO.getAccidentalInjuryInfos().size()>0) {	//附加意外险被保险人信息
				for (AccidentalInjuryInfo accidentalInjuryInfo : storeReqVO.getAccidentalInjuryInfos()) {
					accidentalInjuryInfo.setInsureInfoId(id);
					dao.doInsert("storeAccidentalinjury.doInsert", accidentalInjuryInfo);
				}
			}
			if (storeReqVO.getPupilNameList()!=null && storeReqVO.getPupilNameList().size()>0) {	//被监护人姓名信息
				for (PupilName pupilName : storeReqVO.getPupilNameList()) {
					pupilName.setInsureinfoId(id);
					dao.doInsert("pupilName.insert", pupilName);
				}
			}
		
			if (StringUtils.isNotBlank(storeReqVO.getLicenseNo())||StringUtils.isNotBlank(storeReqVO.getVinNo())) {	//保存车架号和车牌号
				CarInfoVO carInfoVO = new CarInfoVO();
				carInfoVO.setVinno(storeReqVO.getVinNo());
				carInfoVO.setLicenseplateno(storeReqVO.getLicenseNo());
				carInfoVO.setVehicleBrand(storeReqVO.getVehicleBrand());
				carInfoVO.setBrandModel(storeReqVO.getBrandModel());
				carInfoVO.setInsureinfoId(new BigDecimal(id));
				dao.doInsert("carInfo.insert", carInfoVO);
			}
			if("LBU".equals(storeReqVO.getRiskCode()) || "QPU".equals(storeReqVO.getRiskCode())){
				ZzbInsureInfo zzbInsureInfo = new ZzbInsureInfo();
				zzbInsureInfo.setId(Long.parseLong(dao.getSeq("CCICSALEMOBILE.S_STORE_INSUREINFO_ZZB_ID").toString()));
				zzbInsureInfo.setInsureInfoId(id);								//投保信息表ID
				zzbInsureInfo.setStoreName(storeReqVO.getStoreName());			//店面名称
				zzbInsureInfo.setStoreIdType(storeReqVO.getStoreIdType());		//店面证件类型（默认三证合一）
				zzbInsureInfo.setStoreId(storeReqVO.getStoreId());				//店面证件号码
				zzbInsureInfo.setEmployeeNum(storeReqVO.getEmployeeNum());		//雇员人数（必填）
				zzbInsureInfo.setStoreArea(storeReqVO.getStoreArea());			//建筑面积（平方米）
				zzbInsureInfo.setOwnAssets(storeReqVO.getOwnAssets());			//自有资产（万元）
				zzbInsureInfo.setHouseValue(storeReqVO.getHouseValue());		//房屋价值（含装修）万元
				zzbInsureInfo.setStockValue(storeReqVO.getStockValue());		//店内存货价值 (整数)元
				zzbInsureInfo.setEquipmentValue(storeReqVO.getEquipmentValue());//店内机器设备价值 (整数)元
				zzbInsureInfo.setStoreCash(storeReqVO.getStoreCash());			//店内现金数额 (整数)元
				zzbInsureInfo.setContactsName(storeReqVO.getContactsName());	//联系人姓名
				zzbInsureInfo.setContactsMobile(storeReqVO.getContactsMobile());//联系人电话
				zzbInsureInfo.setContactsAddress(storeReqVO.getContactsAddress());//联系人地址
				zzbInsureInfo.setRoomNum(storeReqVO.getRoomNum());				//房间数（旅馆酒店）
				zzbInsureInfo.setInsuredNature(storeReqVO.getInsuredNature());	//关系人人性质  3-自然人，4-法人
				dao.doInsert("zzbInsureInfo.insertZzbInsureInfo",zzbInsureInfo);
			}
			
			dao.commitTransation();
			SystemConfig cofig=new SystemConfig("system");
			String desPassword = cofig.getResourceValue("desPassword");
			insureInfo.setDesProposalNo(DESEncryptUtil.encrypt(insureInfo.getInsureNo(),desPassword));//des加密后的投保单号
		} catch (Exception e) {
			dao.endTransaction();
			log.info("投保单信息保存异常");
			result.setCode("0");
			result.setMessage("投保单信息保存异常");
		}	 
		return insureInfo;
	}

	@Override
	public StoreInsureInfo saveApplyPayment(StoreInsureInfo insureInfo,ResultVO result) {
		PaymentNoApplyRequestDTO paymentNoApplyRequestDTO = new PaymentNoApplyRequestDTO();
		ProposalInfoDTO proposalInfoDTO = new ProposalInfoDTO();
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
		PaymentNoApplyRequest request=new PaymentNoApplyRequest();
		head.setConsumerID("mobile");
		head.setSeqNo("");
		request.setRequestHead(head);
		request.setRequestBody(paymentNoApplyRequestDTO);
		PaymentNoApply payment=(PaymentNoApply)this.getBean("paymentNoApply");
		try {
			PaymentNoApplyResponse response = payment.paymentNoApply(request);
			if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用申请支付号接口出错：返回信息："+response.getResponseHead().getAppMessage());
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

	@Override
	public QuotePriceResponseDTO quotePriceInfo(QuotePriceRequestDTO quotePriceRequestDTO, ResultVO result,UserVO userVO) {
		QuotePriceRequest quotePriceRequest = new QuotePriceRequest();
		RequestHeadDTO head=new RequestHeadDTO();
		head.setClassCode("W");
		head.setConsumerID("mobile");
		//更新经办人信息
		updateHandlerInfo(quotePriceRequestDTO,null,null,null,userVO);
		quotePriceRequest.setRequestHead(head);
		quotePriceRequest.setRequestBody(quotePriceRequestDTO);
		log.info("####  调用意健险试算接口start###"+ JSONObject.fromObject(quotePriceRequest).toString());
		QuotePriceResponse quotePriceResponse= new QuotePriceResponse();
		result.setCode("1");//响应代码 1-成功，0-失败
		result.setMessage("调用意健险试算成功");//响应信息
		QuotePrice quotePrice = (QuotePrice)this.getBean("quotePriceService");
		try {
			quotePriceResponse = quotePrice.quotePrice(quotePriceRequest);
			
			log.info("####  调用意健险试算接口end###"+ JSONObject.fromObject(quotePriceResponse).toString());
			if (quotePriceResponse.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用意健险试算接口，返回状态为失败。返回信息："+quotePriceResponse.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用意健险试算接口异常：", e);
			result.setCode("0");
			result.setMessage("调用意健险试算接口异常："+e.getMessage());
		}
		return quotePriceResponse.getResponseBody();
	}


	@Override
	public StoreInsureInfo findStoreInsureInfoByInsureNo(String insureNo) {
		StoreInsureInfo storeInsureInfo=null;
		try {
			storeInsureInfo = (StoreInsureInfo)dao.toView("storeInsureInfo.selectBySureInfoNo", insureNo);
		} catch (Exception e) {
			log.info("根据投保单号查询投保单信息失败！");
		}
		return storeInsureInfo;
	}


	@Override
	public int updateInsureInfo(StoreInsureInfo insureInfo,
			ResultVO result) {
		int row =0;
		try {
			row =dao.doUpdate("storeInsureInfo.doUpdate", insureInfo);
		} catch (Exception e) {
			log.info("投保单信息更新失败！");
		}
		return (Integer) row;
	}

	public String getTradeSerialNo() {
		
		String strSerialNo = "";
		int iSerialNo = 0;
		Date toDay = new Date();
		BigDecimal serialNo = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			serialNo =(BigDecimal) dao.toView("util.getSeq","S_ETRADE_SERIAL_NO");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (serialNo != null) {
			iSerialNo = serialNo.intValue();
			strSerialNo = format.format(toDay)
					+ String.format("%014d", iSerialNo);
		}
		return strSerialNo;
	}
	@Override
	public ProposalGenerateResponseDTO quotePropertyGenerateInfo(
			ProposalGenerateRequestDTO proposalGenerateRequestDTO,
			ResultVO result,UserVO userVO) {
		com.ccic.service.liabinsurance.proposalliabinsgenerate.bean.ProposalGenerateRequest request=new com.ccic.service.liabinsurance.proposalliabinsgenerate.bean.ProposalGenerateRequest();
		RequestHeadDTO head=new RequestHeadDTO();
		if("ZKF".equals(proposalGenerateRequestDTO.getRiskCode()))
		{
		head.setClassCode("Z");
		}else if("JAB".equals(proposalGenerateRequestDTO.getRiskCode())){
		head.setClassCode("J");	
		}else if("LAD".equals(proposalGenerateRequestDTO.getRiskCode())||"LJS".equals(proposalGenerateRequestDTO.getRiskCode())){
		head.setClassCode("L");	
		}else if("QJA".equals(proposalGenerateRequestDTO.getRiskCode())){
		head.setClassCode("Q");	
		}else if ("JKE".equals(proposalGenerateRequestDTO.getRiskCode())) {
		head.setClassCode("J");
		}else if ("ZBY".equals(proposalGenerateRequestDTO.getRiskCode()) || "ZRU".equals(proposalGenerateRequestDTO.getRiskCode())) {
		head.setClassCode("Z");
		}
		head.setConsumerID("mobile");
		
		//更新经办人信息
		updateHandlerInfo(null,proposalGenerateRequestDTO,null,null,userVO);
		if(proposalGenerateRequestDTO.getAgent()!=null && StringUtils.isNotBlank(proposalGenerateRequestDTO.getAgent().getAgentCode())){
			updateCommissionInfo(proposalGenerateRequestDTO,null);
		}
		request.setRequestHead(head);
		request.setRequestBody(proposalGenerateRequestDTO);

		log.info("####  调用财产险试算接口start###"+ JSONObject.fromObject(request).toString());
		
		com.ccic.service.liabinsurance.proposalliabinsgenerate.bean.ProposalGenerateResponse response=new com.ccic.service.liabinsurance.proposalliabinsgenerate.bean.ProposalGenerateResponse();
		result.setCode("1");//响应代码 1-成功，0-失败
		result.setMessage("调用财产险试算成功");//响应信息
		//发送试算请求并接收返回值
		ProposalLiabInsGeneratePort pgPort = (ProposalLiabInsGeneratePort)this.getBean("proposalLiabInsGenerateService");
		try {
			response = pgPort.proposalGenerate(request);
			log.info("####  调用财产险试算接口end###"+ JSONObject.fromObject(response).toString());
			if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用财产险试算接口，返回状态为失败。返回信息："+response.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用财产险试算接口异常：", e);
			result.setCode("0");
			result.setMessage("调用财产险试算接口异常："+e.getMessage());
		}
		return response.getResponseBody();
	}



	@Override
	public StoreFormulas getProductFormula(String formulaCode,
			String productCode,String newCore) {
		StoreFormulas formula = new StoreFormulas();
		StoreFormulas storeFormulas = new StoreFormulas();
		storeFormulas.setFormulaCode(formulaCode);
		storeFormulas.setProductCode(productCode);
		storeFormulas.setNewCore(newCore); //	0-老核心	1-新核心
		List<StoreFormulas> formulas=new ArrayList<StoreFormulas>();
		try {
			formulas = dao.toList("storeFormulas.searchByProductCode",storeFormulas);
			if(formulas.size()>0){
			formula =formulas.get(0);
			}else{
			formula=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formula;
	}*/
	
	@Override
	public StoreFormulas getProductCodeFormula(String formulaCode) {
		StoreFormulas formula = new StoreFormulas();
		List<StoreFormulas> formulas=new ArrayList<StoreFormulas>();
		try {
			formulas=storeFormulasMapper.queryFormulasInfoLBU(formulaCode);
			//formulas = dao.toList("storeFormulas.queryFormulasInfoLBU",formulaCode);
			if(formulas.size()>0){
			formula =formulas.get(0);
			}else{
			formula=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formula;
	}


	/*@SuppressWarnings("unchecked")
	@Override
	public List<StoreFormulas> getProductFormulaList(String productCode,String comCode) {
		ConfigFacade configFacade = new ConfigFacade();
		boolean flag = false;
		if (comCode != null && !comCode.equals("")) {
			try {
				flag = configFacade.isNewCore(comCode.substring(0, 4), "2");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<StoreFormulas> list=null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productCode",productCode);
		param.put("comCode",comCode);
		if (flag) { //查询新核心
			param.put("newCore","1");//	0-老核心	1-新核心
		}else {
			param.put("newCore","0");//	0-老核心	1-新核心
		}
		try {
			list = dao.toList("storeFormulas.toListByComCode",param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int upPolicyNo(String insureNo, String policyNo) {
		int row =0;
		Map<String, String> param=new HashMap<String, String>();
		param.put("insureNo", insureNo);
		param.put("policyNo", policyNo);
		try {
			row =dao.doUpdate("storeInsureInfo.upPolicyNoInfo", param);
		} catch (Exception e) {
			log.info("投保单信息更新失败！");
		}
		return row;
		
	}
	
	@Override
	public List<StoreInsureInfo> storeListQueryDao(StoreQueryListRequestVO listQueryReqVO) {
		List<StoreInsureInfo> list=null;
		try {
			list=dao.toList("storeInsureInfo.selectOrderList", listQueryReqVO);
			for(int i=0;i<list.size();i++){
				SystemConfig cofig=new SystemConfig("system");
				String desPassword = cofig.getResourceValue("desPassword");
				list.get(i).setDesProposalNo(DESEncryptUtil.encrypt(list.get(i).getInsureNo(),desPassword));//des加密后的投保单号
			}
		} catch (Exception e) {
			log.info("订单列表查询异常异常");
			e.printStackTrace();
		}	 
		return list;
	}
	
	@Override
	public void getHandlerInfo(ZzbOracleRequestVO req){
		ZzbProcessFacade simpl=new ZzbProcessFacadeImpl();
		StoreInfo si=simpl.findStoreByCode(req.getStoreCode());
		
		if("agen".equals(si.getUserCode().substring(0, 4))){
			UserVO u = null;
			try {
				AgentHandlerInfo ah = (AgentHandlerInfo) dao.toView("agentHandlerInfo.selectAgentHandlerInfoByUserCode", si.getUserCode());
			    UserInquiryFacade userInquiryFacade=new UserInquiryFacadeImpl();
			    JsonVO jsonVO = userInquiryFacade.userInquiry(ah.getHandlerCode());
				if("1".equals(jsonVO.getResult().getCode())){
					u =(UserVO) jsonVO.getData();
					req.setHandlerInfo(u);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public AgentInfoVO selectAgentInfo(ZzbOracleRequestVO req,ResultVO result) {
		AgentInfoVO agentInfo=req.getAgentInfo();
		
		if(!"".equals(agentInfo.getAgreementNo()) && null!=agentInfo.getAgreementNo()){
			return agentInfo;
		}
		JsonVO jsonVo=null;
		AgentQueryFacade impl=new AgentQueryFacadeImpl();
		ZzbProcessFacade simpl=new ZzbProcessFacadeImpl();
		
		AgentQueryReqVO agentQueryReq=new AgentQueryReqVO();
		SalesAgentMsgDto salesAgentMsg=new SalesAgentMsgDto();
		salesAgentMsg.setRequestType("01");
		StoreInfo si=simpl.findStoreByCode(req.getStoreCode());
		
		if("agen".equals(si.getUserCode().substring(0, 4))){
			UserVO userVO = req.getHandlerInfo();
			if(userVO!=null){
				salesAgentMsg.setComCode(userVO.getCompany());
			}else{
				salesAgentMsg.setComCode(si.getUserComCode());
			}
		}else{			
			salesAgentMsg.setComCode(si.getUserComCode());
		}
		
		salesAgentMsg.setRiskCode(req.getRiskCode());
		salesAgentMsg.setBusinessNature(agentInfo.getBusinessNature());
		salesAgentMsg.setAgentCode(agentInfo.getAgentCode());
		
		agentQueryReq.setSalesAgentMsg(salesAgentMsg);
		
		try {
			jsonVo= impl.agentQuery(agentQueryReq,null);
			AgentQueryResVO resVo = (AgentQueryResVO) jsonVo.getData();
			SalesMainMsgDto smd= resVo.getSalesMainMsgDto();
			 salesAgentMsg=smd.getSalesAgentMsgDto();
			
			SalesShadowAgentMsgDto[] yefxList=salesAgentMsg.getSalesShadowAgentMsgDto();//业务风险分类信息
			
		    SalesAgreementMsgDto[] xieyiList=smd.getSalesAgreementMsgDto();//协议信息
			if(null!=yefxList && yefxList.length>0){
				agentInfo.setHandler2Code(yefxList[0].getShadowAgentCode());//经办人2 
				agentInfo.setHandler2Name(yefxList[0].getShadowAgentName());//经办人2 名称
				agentInfo.setBusinessNature2(yefxList[0].getShadowAgentType());//业务来源2
			}else{
				agentInfo.setHandler2Code("");
				agentInfo.setHandler2Name("");
				agentInfo.setBusinessNature2("");
			}
			if(null!=xieyiList && xieyiList.length>0){
				agentInfo.setAgreementNo(xieyiList[0].getAgreementNo());//代理协议
				agentInfo.setAgreementName(xieyiList[0].getAgreementName());//代理协议名称
				
			}else{
				result.setCode("0");
				result.setMessage("请联系您的销售专员"+si.getUserName()+",手机号："+si.getUserTel());
			}
			
		    
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		return agentInfo;
	}
	
	*//**
	 * 更新经办人信息
	 * @param eDto 意健险DTO
	 * @param cDto 财产险DTO
	 * @param hDto 货运险DTO
	 * @param stoInsure   保存表对象
	 *//*
	public void updateHandlerInfo(QuotePriceRequestDTO eDto,ProposalGenerateRequestDTO cDto,ProposalGenerateRequestBody hDto,StoreInsureInfo stoInsure,UserVO userVO){
		String handlerCode="";
		if(null!=eDto){
			handlerCode=eDto.getMain().getHandlerCode();
		}else if(null!=cDto){
			handlerCode=cDto.getMain().getHandlerCode();
		}else if(null!=hDto){
			handlerCode=hDto.getMain().getHandlerCode();
		}else if(null!=stoInsure){
			handlerCode=stoInsure.getHandlerCode();
		}
		
		if("agen".equals(handlerCode.substring(0, 4))){
			if(null!=eDto && null!=userVO){//意健险
				if(null!=eDto.getAgent()){//代理信息不为空
					//判断经办人2是否取的默认经办人1
					if(eDto.getMain().getHandlerCode().equals(eDto.getAgent().getHandler2Code())){
						eDto.getAgent().setHandler2Code(userVO.getUserCode());
						eDto.getAgent().setHandler2Name(userVO.getUserName());
					}
				}
				eDto.getMain().setHandlerCode(userVO.getUserCode());//归属经办人
				eDto.getMain().setMakeCom(userVO.getCompany());//出单机构
				eDto.getMain().setComCode(userVO.getCompany());//归属机构
				
			}else if(null!=cDto &&null!=userVO){//财产险
				//判断经办人2是否取的默认经办人1
				if(cDto.getMain().getHandler1Code().equals(cDto.getMain().getHandler2Code())){
					cDto.getMain().setHandler2Code(userVO.getUserCode());
					cDto.getMain().setHandler2Name(userVO.getUserName());
				}
				cDto.getMain().setHandlerCode(userVO.getUserCode());//归属经办人
				cDto.getMain().setMakeCom(userVO.getCompany());//出单机构
				cDto.getMain().setComCode(userVO.getCompany());//归属机构
				cDto.getMain().setHandler1Code(userVO.getUserCode());//归属业务员代码
			}else if(null!=hDto &&null!=userVO){//货运险
				//判断经办人2是否取的默认经办人1
				if(hDto.getMain().getHandler1Code().equals(hDto.getMain().getHandler2Code())){
					hDto.getMain().setHandler2Code(userVO.getUserCode());
					hDto.getMain().setHandler2Name(userVO.getUserName());
				}
				hDto.getMain().setHandlerCode(userVO.getUserCode());//归属经办人
				hDto.getMain().setMakeCom(userVO.getCompany());//出单机构
				hDto.getMain().setComCode(userVO.getCompany());//归属机构
				hDto.getMain().setHandler1Code(userVO.getUserCode());//归属业务员代码
			}else if(null!=stoInsure &&null!=userVO){//保存表对象
				//判断经办人2是否取的默认经办人1
				if(stoInsure.getHandlerCode().equals(stoInsure.getHandlerCode2())){
					stoInsure.setHandlerCode2(userVO.getUserCode());
				}
				stoInsure.setHandlerCode(userVO.getUserCode());//归属经办人
				stoInsure.setComCode(userVO.getCompany());//归属机构
			}
		}
	}
	
	*//**
	 * 更新手续费信息
	 * @param cDto 财产险DTO
	 * @param hDto 货运险DTO
	 *//*
	public void updateCommissionInfo(ProposalGenerateRequestDTO cDto,ProposalGenerateRequestBody hDto){
		JsonVO jsonVo=null;
		
		String comCode = null;
		String riskCode = null;
		String agentCode = null;
		String businessNature = null;
		String agreementNo = null;
		if(cDto!=null){
			comCode = cDto.getMain().getComCode();
			riskCode = cDto.getRiskCode();
			agentCode = cDto.getAgent().getAgentCode();
			businessNature = cDto.getMain().getBusinessNature();
			agreementNo = cDto.getAgent().getAgreementNo();
		}else if(hDto!=null){
			comCode = hDto.getMain().getComCode();
			riskCode = hDto.getRiskCode();
			agentCode = hDto.getAgent().getAgentCode();
			businessNature = hDto.getMain().getBusinessNature();	
			agreementNo = hDto.getAgent().getAgreementNo();
		}
		AgentQueryFacade impl=new AgentQueryFacadeImpl();
		AgentQueryReqVO agentQueryReq=new AgentQueryReqVO();
		SalesAgentMsgDto salesAgentMsg=new SalesAgentMsgDto();
		salesAgentMsg.setRequestType("01");
		salesAgentMsg.setComCode(comCode);
		salesAgentMsg.setRiskCode(riskCode);
		salesAgentMsg.setBusinessNature(businessNature);
		salesAgentMsg.setAgentCode(agentCode);
		salesAgentMsg.setAgreementNo(agreementNo);
		
		agentQueryReq.setSalesAgentMsg(salesAgentMsg);
		
		try {
			jsonVo= impl.agentQuery(agentQueryReq,null);
			AgentQueryResVO resVo = (AgentQueryResVO) jsonVo.getData();
			SalesMainMsgDto smd= resVo.getSalesMainMsgDto();
			salesAgentMsg=smd.getSalesAgentMsgDto();
			
		    SalesAgreementMsgDto[] xieyiList=smd.getSalesAgreementMsgDto();//协议信息
			if(null!=xieyiList && xieyiList.length>0){
				if(cDto!=null){
					cDto.getAgent().setCommission(xieyiList[0].getDefaultTopcommission());
					cDto.getAgent().setOriginCommission(xieyiList[0].getDefaultTopcommission());
					cDto.getAgent().setTopCommission(xieyiList[0].getTopcommission());
				}else if(hDto!=null){
					hDto.getAgent().setCommission(xieyiList[0].getDefaultTopcommission());
					hDto.getAgent().setOriginCommission(xieyiList[0].getDefaultTopcommission());
					hDto.getAgent().setTopCommission(xieyiList[0].getTopcommission());
				}
				
			}
		} catch (Exception e) {
			log.error("查询代理协议异常",e);
		}
		
	}

	*//**
	 * 根据投保单号查询订单详情和产品信息
	 * @throws Exception 
	 *//*
	@Override
	public StoreInsureInfoFormulasResponseVO findStoreInsureInfoAndFormulasByInsureNo(String proposalNo) throws Exception {
		StoreInsureInfoFormulasResponseVO storeInsureInfoFormulas = new StoreInsureInfoFormulasResponseVO();
		try {
			storeInsureInfoFormulas=(StoreInsureInfoFormulasResponseVO) dao.toView("storeInsureInfo.selectInsureInfoAndFormulas", proposalNo);
			
			Map<String, String> param=new HashMap<String, String>();
			param.put("formulaCode", storeInsureInfoFormulas.getFormulaCode());
			param.put("productCode", storeInsureInfoFormulas.getProductCode());
			param.put("newCore", "0");	//	0-老核心	1-新核心
			List<StoreFormulas> list = dao.toList("storeFormulas.queryFormulasInfo",param);
			
			List<AccidentalInjuryInfo> accidentalInjuryInfos= dao.toList("storeAccidentalinjury.doSelect",storeInsureInfoFormulas.getId());
			CarInfoVO carInfoVO = (CarInfoVO) dao.toView("carInfo.queryCarInfo", storeInsureInfoFormulas.getId());
			List<PupilName> pupilName = dao.toList("pupilName.doSelect", storeInsureInfoFormulas.getId());
			storeInsureInfoFormulas.setPupilNameList(pupilName);
			storeInsureInfoFormulas.setCarInfoVO(carInfoVO);
			storeInsureInfoFormulas.setFormulas(list);
			storeInsureInfoFormulas.setAccidentalInjuryInfos(accidentalInjuryInfos);
		} catch (Exception e) {
			log.info("订单详情查询异常");
			throw e;
		}
		return storeInsureInfoFormulas;
	}


	*//* (non-Javadoc)
	 * @see com.estar.app.appsaleddbx.sale.facade.zzb.ZzbProductPlaceUtilFacade#getZzbRiskRate(java.lang.String, java.lang.String, java.lang.String)
	 * 根据省份代码和人数、方案代码返回保费
	 *//*
	@Override
	public Double getZzbPremium(String provinceCode, int employeeNum,
			String productCode) {
		try {
			// 餐饮管家   或者 零售管家
			if("ZZB01".equals(productCode) || "ZZB02".equals(productCode)){
				//  山东、河南					
				if("3701".equals(provinceCode) 
						|| "4101".equals(provinceCode)
						|| "1501".equals(provinceCode)
						|| "2301".equals(provinceCode)
						|| "5301".equals(provinceCode)
						|| "5001".equals(provinceCode)
						|| "1401".equals(provinceCode)
						|| "6201".equals(provinceCode)
						|| "4201".equals(provinceCode)
						|| "2102".equals(provinceCode)
						|| "2101".equals(provinceCode)
						|| "3401".equals(provinceCode)
						|| "3302".equals(provinceCode)){
					return employeeNum * 50.00;
					
				}else if("1301".equals(provinceCode)||"3702".equals(provinceCode)){//河北
					return employeeNum * 30.00;
					
				}
			}else if ("ZZB03".equals(productCode) && ("3701".equals(provinceCode) 
													|| "4101".equals(provinceCode) 
													|| "1301".equals(provinceCode)
													|| "1501".equals(provinceCode)
													|| "2301".equals(provinceCode)
													|| "5301".equals(provinceCode)
													|| "5001".equals(provinceCode)
													|| "1401".equals(provinceCode)
													|| "6201".equals(provinceCode)
													|| "4201".equals(provinceCode)
													|| "2102".equals(provinceCode)
													|| "2101".equals(provinceCode)
													|| "3702".equals(provinceCode)
													|| "3401".equals(provinceCode))){ //旅馆酒店
				return employeeNum * 50.00;
			}else if("ZZB06".equals(productCode)){//洗浴按摩
				return employeeNum * 100.00;
			}else if ("ZZB05".equals(productCode) && ("3701".equals(provinceCode) 
													|| "4101".equals(provinceCode) 
													|| "1301".equals(provinceCode)
													|| "1501".equals(provinceCode)
													|| "2301".equals(provinceCode)
													|| "5301".equals(provinceCode)
													|| "5001".equals(provinceCode)
													|| "1401".equals(provinceCode)
													|| "6201".equals(provinceCode)
													|| "4201".equals(provinceCode)
													|| "5201".equals(provinceCode)
													|| "6101".equals(provinceCode)
													|| "2102".equals(provinceCode)
													|| "3702".equals(provinceCode)
													|| "3401".equals(provinceCode))){ //旅馆酒店
				return employeeNum * 50.00;
			}
		} catch (Exception e) {
			log.error("计算保费异常",e);
		}
		return null;
	}


	*//* (non-Javadoc)
	 * @see com.estar.app.appsaleddbx.sale.facade.zzb.ZzbProductPlaceUtilFacade#getZzbAmount(int)
	 * 根据人数计算保额
	 *//*
	@Override
	public Double getZzbAmount(int employeeNum) {
		return employeeNum*22000.00;
	}
	
	@Override
	public JsonVO findZzbInsureInfoVOByInsureNo(String insureNo) {
		JsonVO jsonVO = new JsonVO();
		ResultVO resultVO = new ResultVO();
		DefaultDAO dao = new DefaultDAO();
        ZzbInsureInfoResponseVO responseVO=new ZzbInsureInfoResponseVO();
        try {        	
        	ZzbInsureInfoVO zzb = (ZzbInsureInfoVO)dao.toView("storeInsureInfo.selectZzbInsureByInsureNo", insureNo);
        	//对象属性赋值
        	CopyUtil.Copy(zzb, responseVO);

			resultVO.setCode("1");
			resultVO.setMessage("掌中宝订单详情查询成功！");
		} catch (Exception e) {
			log.error(e);
			resultVO.setCode("0");
			resultVO.setMessage("掌中宝订单详情查询失败！");
		}
        jsonVO.setData(responseVO);
		jsonVO.setResult(resultVO);
		return jsonVO;
	}
*/
}
