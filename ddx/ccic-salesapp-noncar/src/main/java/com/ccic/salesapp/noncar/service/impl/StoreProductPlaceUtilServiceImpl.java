package com.ccic.salesapp.noncar.service.impl;

import static com.ccic.salessapp.common.outService.rest.common.bean.RequestHead.getUUID;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccic.salesapp.noncar.controller.StoreOrderCtl;
import com.ccic.salesapp.noncar.dto.AccidentalInjuryInfo;
import com.ccic.salesapp.noncar.dto.AgentHandlerInfo;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.AgentQueryResVO;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.PupilName;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.order.request.GroupOrderRequest;
import com.ccic.salesapp.noncar.dto.order.request.OrderRequest;
import com.ccic.salesapp.noncar.dto.request.CarInfoVO;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salesapp.noncar.dto.request.TNoncarInsureZcReqVO;
import com.ccic.salesapp.noncar.dto.response.StoreInsureInfoFormulasResponseVO;
import com.ccic.salesapp.noncar.dto.response.TNoncarInsureZcResVO;
import com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean.ProposalGenerateRequestBody;
import com.ccic.salesapp.noncar.outService.esb.proposalliabinsgenerate.bean.ProposalGenerateRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.QuotePriceRequest;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.QuotePriceRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.QuotePriceResponse;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.QuotePriceResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.intf.QuotePrice;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.AgentAgreeInquiryRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.AgentAgreeInquiryResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.AgentQueryReqVO;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.service.ReimbursementOutService;
import com.ccic.salesapp.noncar.repository.basedb.mapper.AgentHandlerInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.CarInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.InvoiceInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrgBranchMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PupilNameMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreAccidentalinjuryMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreFormulasMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.UtilMapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;
import com.ccic.salesapp.noncar.service.AgentQueryService;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salesapp.noncar.service.StoreProcessService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salesapp.noncar.service.UserInquiryService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.utils.DESEncryptUtil;
import com.ccic.salessapp.common.utils.ValidationUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
@Service
@Slf4j
public class StoreProductPlaceUtilServiceImpl implements
		StoreProductPlaceUtilService {
	
	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;
	@Autowired
	StoreFormulasMapper storeFormulasMapper;
	
	@Autowired
	AgentQueryService agentQueryService;
	
	@Autowired
	StoreProcessService storeProcessService;
	@Autowired
	AgentHandlerInfoMapper  agentHandlerInfoMapper;
	@Autowired
	UserInquiryService userInquiryService;
	@Autowired
	UtilMapper utilMapper;
	
	@Autowired
	QuotePrice QuotePrice;
	
	@Autowired
	StoreAccidentalinjuryMapper storeAccidentalinjuryMapper;
	
	@Autowired
	PupilNameMapper pupilNameMapper;
	
	@Autowired
	CarInfoMapper carInfoMapper;
	
	@Autowired
	InvoiceInfoMapper invoiceInfoMapper;
	
	@Value("${system.desPassword}")
	String desPassword;
	
	@Autowired
    ReimbursementOutService sellsManageOutService;
	
	@Autowired
	PlanStrategyService planStrategyService;
	
	@Autowired
	StoreOrderCtl orderCtl;
	
	@Autowired
	OrgBranchMapper orgBranchMapper;
	
	@Override
	public int updateSignStatus(StoreInsureInfo storeInsureInfo) {
		int row =0;
		try {
			row=storeInsureInfoMapper.updateSignStatus(storeInsureInfo);
			//row =dao.doUpdate("storeInsureInfo.updateSignStatus",storeInsureInfo);
		} catch (Exception e) {
			log.info("投保单信息更新签名失败！");
		}
		return (Integer) row;
	}
	
	

	@Override
	public StoreInsureInfoFormulasResponseVO findStoreInsureInfoAndFormulasByInsureNo(
			String proposalNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreInsureInfo findStoreInsureInfoByInsureNo(String insureNo) {
		StoreInsureInfo storeInsureInfo=null;
		try {
			storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(insureNo);
		} catch (Exception e) {
			//log.info("根据投保单号查询投保单信息失败");
			log.info("cxbdsb");

		}
		return storeInsureInfo;
	}
	
	@Override
	public List<StoreFormulas> getProductFormulaList(String productCode, String newCore,String comCode,String userCode) {
		
		List<StoreFormulas> list=null;
		
		try {
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("newCore", newCore);
			paramMap.put("productCode", productCode);
			paramMap.put("comCode", comCode);
			paramMap.put("userCode", userCode);
			list = storeFormulasMapper.toList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	@Override
	public UserVO getHandlerInfo(OrderRequest req){
		UserToken user = planStrategyService.getAccessTokenByUserCode(req.getUserCode());
		StoreInfo si = null;
		if(user != null && "3".equals(user.getLoginFlag())) {
			UserToken handlerToken = planStrategyService.getAgentSalesman(user.getUserCode());
			si = new StoreInfo();
			si.setStoreCode(user.getUserCode());
			si.setUserComCode(handlerToken.getComCode());
			si.setUserCode(handlerToken.getUserCode());
			si.setUserName(handlerToken.getUserName());
		}else {
			 si=storeProcessService.findStoreByCode(req.getStoreCode());
		}
		
		if("agen".equals(si.getUserCode().substring(0, 4))){
			UserVO u = null;
			try {
				AgentHandlerInfo ah =  agentHandlerInfoMapper.selectAgentHandlerInfoByUserCode( si.getUserCode());
			    HttpResult httpResult = userInquiryService.userInquiry(ah.getHandlerCode());
				if("1".equals(httpResult.getCode())){
					u =(UserVO) httpResult.getData();
					return u;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public UserVO getHandlerInfo(GroupOrderRequest req){
		UserToken user = planStrategyService.getAccessTokenByUserCode(req.getUserCode());
		StoreInfo si = null;
		if(user != null && "3".equals(user.getLoginFlag())) {
			UserToken handlerToken = planStrategyService.getAgentSalesman(user.getUserCode());
			si = new StoreInfo();
			si.setStoreCode(user.getUserCode());
			si.setUserComCode(handlerToken.getComCode());
			si.setUserCode(handlerToken.getUserCode());
			si.setUserName(handlerToken.getUserName());
		}else {
			 si=storeProcessService.findStoreByCode(user.getStoreCode());
		}
		
		if("agen".equals(si.getUserCode().substring(0, 4))){
			UserVO u = null;
			try {
				AgentHandlerInfo ah =  agentHandlerInfoMapper.selectAgentHandlerInfoByUserCode( si.getUserCode());
			    HttpResult httpResult = userInquiryService.userInquiry(ah.getHandlerCode());
				if("1".equals(httpResult.getCode())){
					u =(UserVO) httpResult.getData();
					return u;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}



	@Override
	public void getHandlerInfo(StoreOracleRequestVO req){
		UserToken user = planStrategyService.getAccessTokenByUserCode(req.getUserCode());
		StoreInfo si = null;
		if(user != null && "3".equals(user.getLoginFlag())) {
			UserToken handlerToken = planStrategyService.getAgentSalesman(user.getUserCode());
			si = new StoreInfo();
			si.setStoreCode(user.getUserCode());
			si.setUserComCode(handlerToken.getComCode());
			si.setUserCode(handlerToken.getUserCode());
			si.setUserName(handlerToken.getUserName());
		}else {
			 si=storeProcessService.findStoreByCode(req.getStoreCode());
		}
		
		if("agen".equals(si.getUserCode().substring(0, 4))){
			UserVO u = null;
			try {
				AgentHandlerInfo ah =  agentHandlerInfoMapper.selectAgentHandlerInfoByUserCode( si.getUserCode());
			    HttpResult httpResult = userInquiryService.userInquiry(ah.getHandlerCode());
				if("1".equals(httpResult.getCode())){
					u =(UserVO) httpResult.getData();
					req.setHandlerInfo(u);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public AgentInfoVO selectAgentInfo(StoreOracleRequestVO req,HttpResult httpResult) {
		AgentInfoVO agentInfo=req.getAgentInfo();
		
		if(!"".equals(agentInfo.getAgreementNo()) && null!=agentInfo.getAgreementNo()){
			return agentInfo;
		}
		
		AgentQueryReqVO agentQueryRequest = new AgentQueryReqVO();
		agentQueryRequest.setRequestType("01");
		UserToken user = planStrategyService.getAccessTokenByUserCode(req.getUserCode());
		StoreInfo si = null;
		if(user != null && "3".equals(user.getLoginFlag())) {
			UserToken handlerToken = planStrategyService.getAgentSalesman(user.getUserCode());
			si = new StoreInfo();
			si.setStoreCode(user.getUserCode());
			si.setUserComCode(handlerToken.getComCode());
			si.setUserCode(handlerToken.getUserCode());
			si.setUserName(handlerToken.getUserName());
		}else {
			 si=storeProcessService.findStoreByCode(req.getStoreCode());
		}
		
		if("agen".equals(si.getUserCode().substring(0, 4))){
			UserVO userVO = req.getHandlerInfo();
			if(userVO!=null){
				agentQueryRequest.setComCode(userVO.getCompany());
			}else{
				agentQueryRequest.setComCode(si.getUserComCode());
			}
		}else{			
			agentQueryRequest.setComCode(si.getUserComCode());
		}
		
		agentQueryRequest.setRiskCode(req.getRiskCode());
		agentQueryRequest.setBusinessSource(agentInfo.getBusinessNature());
		agentQueryRequest.setAgentCode(agentInfo.getAgentCode());
		
		
		try {
			AgentQueryResVO resVo= agentQuery(agentQueryRequest);
			AgentAgreeInquiryResponseDTO smd= resVo.getSalesMainMsgDto();
			com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.SalesAgentMsgDto salesAgentMsg=smd.getSalesAgentMsgDto();
			
			com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.SalesShadowAgentMsgDto[] yefxList=salesAgentMsg.getSalesShadowAgentMsgList();//业务风险分类信息
			
		    com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.SalesAgreementMsgDto[] xieyiList=smd.getSalesAgreementMsgList();//协议信息
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
				httpResult.setCode("0");
				httpResult.setMsg("请联系您的销售专员"+si.getUserName()+",手机号："+si.getUserTel());
			}
			
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return agentInfo;
	}



	@Override
	public StoreFormulas getProductFormula(String formulaCode,
			String productCode, String newCore) {
		StoreFormulas formula = new StoreFormulas();
		StoreFormulas storeFormulas = new StoreFormulas();
		storeFormulas.setFormulaCode(formulaCode);
		storeFormulas.setProductCode(productCode);
		storeFormulas.setNewCore(newCore);
		List<StoreFormulas> formulas=new ArrayList<StoreFormulas>();
		try {
			formulas = storeFormulasMapper.searchByProductCode(storeFormulas);
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



	@Override
	public String getTradeSerialNo() {
		String strSerialNo = "";
		int iSerialNo = 0;
		Date toDay = new Date();
		BigDecimal serialNo = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			serialNo =utilMapper.getSeq("S_ETRADE_SERIAL_NO");
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
	@Transactional
	public StoreInsureInfo saveInsureInfo(StoreInsureInfo info, StoreOracleRequestVO storeReqVO,
			HttpResult result, UserVO userVO) {
		StoreInsureInfo insureInfo = info;
		insureInfo.setCreateDate(new Date());//记录创建日期
		insureInfo.setLicensePlateNo(storeReqVO.getLicensePlateNo());//车牌号
		insureInfo.setStartTime("0");
		try {
			TNoncarInsureZcReqVO zc = new TNoncarInsureZcReqVO();
			zc.setOrderNo(storeReqVO.getOrderNo());
			zc.setInsureNo(info.getInsureNo());
			storeReqVO.setOrderNo(null);
			zc.setStoreOracleRequestVO(storeReqVO);
			HttpResult http = orderCtl.insureStatusZc(zc);//临时默认保存暂存信息
			TNoncarInsureZcResVO zcres = (TNoncarInsureZcResVO)http.getData();
			insureInfo.setOrderNo(zcres.getOrderNo());
			insureInfo.setStartDate(datef.parse(storeReqVO.getStartDate()));//保单起期
			insureInfo.setEndDate(datef.parse(storeReqVO.getEndDate()));
			if(storeReqVO.getInsuredBirthDate() != null) {
				insureInfo.setBirthDate(datef.parse(storeReqVO.getInsuredBirthDate()));//出生日期
			}
			if(storeReqVO.getAppliBirthDate() != null) {
				insureInfo.setApplicantBirthDate(datef.parse(storeReqVO.getAppliBirthDate()));//出生日期
			}
		} catch (ParseException e1) {
		} //保单止期
		catch (Exception e) {
			e.printStackTrace();
		}
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
		insureInfo.setInsuredSheBaoType(storeReqVO.getInsuredSheBaoType());
		if (StringUtils.isNotBlank(storeReqVO.getInsuredBirthDate())) {
			try {
				insureInfo.setBeGuardianBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(storeReqVO.getInsuredBirthDate()));
			} catch (ParseException e) {
				result.setCode("0");
				result.setMsg("投保单信息保存异常");
				e.printStackTrace();
			} //被监护人生日
			
		}
		if(StringUtils.isNotBlank(storeReqVO.getInsuredMobile())) {
			insureInfo.setInsuredMobile(storeReqVO.getInsuredMobile());
		}
		if (StringUtils.isNotBlank(storeReqVO.getBeGuardianSex())) {
			insureInfo.setBeGuardianSex(storeReqVO.getBeGuardianSex());	//被监护人性别
		}
		insureInfo.setProductCode(storeReqVO.getProductCode());//产品代码
		
		insureInfo.setInsuranceCount(storeReqVO.getFormulaSize());//投保份数
		if(storeReqVO.getAgentInfo() != null) {
			insureInfo.setHandlerCode2(storeReqVO.getAgentInfo().getHandler2Code());//保存经办人2 
		}
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
			insureInfo.setAgentName(storeReqVO.getAgentInfo().getAgentName()); //代理人名称
			insureInfo.setBusinessNature(storeReqVO.getAgentInfo().getBusinessNature()); //业务来源1
			insureInfo.setBusinessNature2(storeReqVO.getAgentInfo().getBusinessNature2()); //业务来源2
			insureInfo.setHandlerCode2(storeReqVO.getAgentInfo().getHandler2Code()); //经办人2 
			insureInfo.setHandler2Name(storeReqVO.getAgentInfo().getHandler2Name()); //经办人2名称
			insureInfo.setThirdPartyHandlerNo(storeReqVO.getAgentInfo().getThirdPartyHandlerNo()); // 第三方业务员工号
			insureInfo.setThirdPartyHandlerName(storeReqVO.getAgentInfo().getThirdPartyHandlerName()); // 第三方业务员姓名
		}
		insureInfo.setIsOpenair(storeReqVO.getIsOpenair());
		insureInfo.setShowNo(storeReqVO.getShowNo());
		insureInfo.setShowName(storeReqVO.getShowName());
		insureInfo.setExhibitionArea(storeReqVO.getExhibitionArea());
		//如果地址不为空
		if (storeReqVO.getAddressInfo() != null) {
			insureInfo.setProvinceCode(storeReqVO.getAddressInfo().getProvinceCode());
			insureInfo.setPrefectureCode(storeReqVO.getAddressInfo().getPrefectureCode());
			insureInfo.setCountyCode(storeReqVO.getAddressInfo().getCountyCode());
		}
		//
		if (StringUtils.isNotBlank(storeReqVO.getNclUserCode()) && storeReqVO.getNclUserCode().length()==8) {
			insureInfo.setNclUserCode(storeReqVO.getNclUserCode());
			insureInfo.setPlatformFlag("1");
		}
		//更新经办人信息
		updateHandlerInfo(null,null,null,insureInfo,userVO);
		try {
			/*
			 * Long id =
			 * Long.parseLong(utilMapper.getSeq("CCICSALEMOBILE.SEQ_STORE_INSUREINFO_ID").
			 * toString()); insureInfo.setId(id);
			 */
			insureInfo.setRelationPolicyNo(storeReqVO.getRelationPolicyNo());
			insureInfo.setLicensePlateNo(storeReqVO.getLicenseNo());
			insureInfo.setPComCode(orgBranchMapper.selectBranchByComCode(insureInfo.getComCode()));//所属分公司
			storeInsureInfoMapper.doInsert(insureInfo);//保单信息表新增记录
			
			if (storeReqVO.getAccidentalInjuryInfos()!=null && storeReqVO.getAccidentalInjuryInfos().size()>0) {	//附加意外险被保险人信息
				for (AccidentalInjuryInfo accidentalInjuryInfo : storeReqVO.getAccidentalInjuryInfos()) {
					accidentalInjuryInfo.setInsureInfoId(Long.parseLong(insureInfo.getId()) );
					storeAccidentalinjuryMapper.doInsert(accidentalInjuryInfo);
				}
			}
			if (storeReqVO.getPupilNameList()!=null && storeReqVO.getPupilNameList().size()>0) {	//被监护人姓名信息
				for (PupilName pupilName : storeReqVO.getPupilNameList()) {
					pupilName.setInsureinfoId(Long.parseLong(insureInfo.getId()) );
					pupilNameMapper.insert(pupilName);
				}
			}
		
			if (StringUtils.isNotBlank(storeReqVO.getLicenseNo())||StringUtils.isNotBlank(storeReqVO.getVinNo())) {	//保存车架号和车牌号
				CarInfoVO carInfoVO = new CarInfoVO();
				carInfoVO.setVinno(storeReqVO.getVinNo()); //车架号
				carInfoVO.setLicenseplateno(storeReqVO.getLicenseNo());//号牌号码
				carInfoVO.setVehicleBrand(storeReqVO.getVehicleBrand() == null ? storeReqVO.getBrandModelName() : storeReqVO.getVehicleBrand());//车辆品牌
				carInfoVO.setBrandModel(storeReqVO.getBrandModel());//厂牌型号
				carInfoVO.setInsureinfoId(new BigDecimal(insureInfo.getId()));//投保单ID
				carInfoVO.setCarOwner(storeReqVO.getVehicleOwnerName());//车主
				carInfoVO.setIdentifyType(storeReqVO.getVehicleOIdentifyType());//证件类型
				carInfoVO.setIdentifyNumber(storeReqVO.getVehicleOIdentifyNumber());//证件号码
				carInfoVO.setPhoneNumber(storeReqVO.getVehicleOMobile());//手机号码
				carInfoVO.setEnrollDate(storeReqVO.getEndDate());//原厂质保起期
				carInfoVO.setExhaustScale(storeReqVO.getExhaustScale());//排量
				carInfoVO.setColorCode(storeReqVO.getCarColor());//车辆颜色code
				carInfoVO.setEngineNo(storeReqVO.getEngineNo());//发动机号
				carInfoVO.setVehicleType(storeReqVO.getVehicleType());//车型
				carInfoVO.setTonnage(storeReqVO.getTonnage() == null ? storeReqVO.getApprovalQuality():storeReqVO.getTonnage());//吨位
				carInfoVO.setProductionDate(storeReqVO.getProductionDate());//出厂日期
				carInfoVO.setManuFacturer(storeReqVO.getManuFacturer());//生产厂家
				carInfoVO.setPurchasePrice(storeReqVO.getPurchasePrice());//购置价
				carInfoVO.setVehicleAge(storeReqVO.getVehicleAge());//车龄
				carInfoVO.setVehicleModelCode(storeReqVO.getVehicleModelCode());//车型代码
				carInfoVO.setMakedate(storeReqVO.getMakedate());//购买日期
				carInfoMapper.insert(carInfoVO);
			}
			
			if (storeReqVO.getInvoiceInfo() != null && StringUtils.isNotBlank(storeReqVO.getInvoiceInfo().getCompanyName())) {	//保存开票信息
				InvoiceInfo invoiceInfo = new InvoiceInfo();
				invoiceInfo.setInsureinfoId(Long.parseLong(insureInfo.getId()));
				invoiceInfo.setClientType(storeReqVO.getInvoiceInfo().getCustomerType());
				invoiceInfo.setTaxPayerType(storeReqVO.getInvoiceInfo().getTaxPayerType());
				invoiceInfo.setInvoiceType(storeReqVO.getInvoiceInfo().getInvoiceType());
				invoiceInfo.setCompanyName(storeReqVO.getInvoiceInfo().getCompanyName());
				invoiceInfo.setTaxMobile(storeReqVO.getInvoiceInfo().getMobile());
				invoiceInfo.setTaxEmail(storeReqVO.getInvoiceInfo().getEmail());
				invoiceInfo.setTaxPayerNo(storeReqVO.getInvoiceInfo().getTaxPayerNo());
				invoiceInfo.setTaxRegistryAddress(storeReqVO.getInvoiceInfo().getTaxRegisterAddress());
				invoiceInfo.setTaxRegistryPhone(storeReqVO.getInvoiceInfo().getTaxRegisterTel());
				invoiceInfo.setBankName(storeReqVO.getInvoiceInfo().getDepositeBankName());
				invoiceInfo.setAccountNumber(storeReqVO.getInvoiceInfo().getAccountNo());
				
				invoiceInfoMapper.insert( invoiceInfo);
			}
			
			//dao.commitTransation();
			//SystemConfig cofig=new SystemConfig("system");
			//String desPassword = cofig.getResourceValue("desPassword");
			insureInfo.setDesProposalNo(DESEncryptUtil.encrypt(insureInfo.getInsureNo(),desPassword));//des加密后的投保单号
		} catch (Exception e) {
			//dao.endTransaction();
			log.info("投保单信息保存异常",e.fillInStackTrace());
			result.setCode("0");
			result.setMsg("投保单信息保存异常");
		}	 
		return insureInfo;
	}


	@Override
	public void updateInsureInfoById(String id, HttpResult result) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("ID", id);
		try {
			storeInsureInfoMapper.updateInsureInfoById(map);
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("投保单修改待支付异常！");
		}
	}



	@Override
	public int updateInsureInfo(StoreInsureInfo insureInfo1, HttpResult result) {
		// TODO Auto-generated method stub
		int row =0;
		try {
			row =storeInsureInfoMapper.doUpdate(insureInfo1);
		} catch (Exception e) {
			log.info("投保单信息更新失败！");
		}
		return (Integer) row;
	}



	@Override
	public QuotePriceResponseDTO quotePriceInfo(QuotePriceRequestDTO quotePriceRequestDTO, HttpResult result,
			UserVO userVO) {
		QuotePriceRequest quotePriceRequest = new QuotePriceRequest();
		RequestHeadDTO head=new RequestHeadDTO();
		head.setClassCode("W");
		head.setConsumerID("supsale");
		//更新经办人信息
		updateHandlerInfo(quotePriceRequestDTO,null,null,null,userVO);
		quotePriceRequest.setRequestHead(head);
		quotePriceRequest.setRequestBody(quotePriceRequestDTO);
		log.info("####  调用意健险试算接口start###"+JSONObject.fromObject(quotePriceRequest).toString());
		QuotePriceResponse quotePriceResponse= new QuotePriceResponse();
		result.setCode("1");//响应代码 1-成功，0-失败
		result.setMsg("调用意健险试算成功");//响应信息
		//QuotePrice quotePrice = (QuotePrice)this.getBean("quotePriceService");
		try {
			quotePriceResponse = QuotePrice.quotePrice(quotePriceRequest);
			
			log.info("####  调用意健险试算接口end###"+JSONObject.fromObject(quotePriceResponse).toString());
			if (quotePriceResponse.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMsg("调用意健险试算接口，返回状态为失败。返回信息："+quotePriceResponse.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用意健险试算接口异常：", e);
			result.setCode("0");
			result.setMsg("调用意健险试算接口异常："+e.getMessage());
		}
		return quotePriceResponse.getResponseBody();
	}



	/**
	 * 更新经办人信息
	 * @param eDto 意健险DTO
	 * @param cDto 财产险DTO
	 * @param hDto 货运险DTO
	 * @param stoInsure   保存表对象
	 */
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



	@Override
	public void preUndwrt(String insureNo, HttpResult result) {
		// TODO Auto-generated method stub
		
	}



	 /**
     * 代理信息查询
     * @param requestVO
     * @return
     * @throws Exception
     */
    @Override
    public AgentQueryResVO agentQuery(AgentQueryReqVO requestVO)  {
        //参数校验
        String requestType = requestVO.getRequestType();
        String comCode = requestVO.getComCode();
        String businessNature = requestVO.getBusinessSource();
        String riskCode = requestVO.getRiskCode();
        ValidationUtils.isTrue(StringUtils.isNotBlank(requestType), "请求类型不能为空");
        ValidationUtils.isTrue(StringUtils.isNotBlank(requestVO.getAgentCode()), "业务员代码不能为空");
        //01:查询代理人协议信息
        if (StringUtils.equals(requestType,"01")){
            ValidationUtils.isTrue(StringUtils.isNotBlank(comCode), "归属机构不能为空");
            ValidationUtils.isTrue(StringUtils.isNotBlank(businessNature), "业务渠道不能为空");
            ValidationUtils.isTrue(StringUtils.isNotBlank(riskCode), "险种不能为空");
        }
        // 封装请求参数
        Request request = new Request();
        RequestHead requestHead = new RequestHead();
        requestHead.setConsumerID("icore");
        requestHead.setConsumerSeqNo(getUUID());
        AgentAgreeInquiryRequestDTO agentRequestDTO = new AgentAgreeInquiryRequestDTO();
        //必填项
        agentRequestDTO.setAgentCode(requestVO.getAgentCode());
        agentRequestDTO.setRequestType(requestType);
        agentRequestDTO.setInputDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //其它
        agentRequestDTO.setAgreementNo(requestVO.getAgreementNo());
        agentRequestDTO.setAgreementName(requestVO.getAgreementName());
        agentRequestDTO.setComCode(requestVO.getComCode());
        agentRequestDTO.setBusinessNature(requestVO.getBusinessSource());
        agentRequestDTO.setRiskCode(requestVO.getRiskCode());
        request.setRequestHead(requestHead);
        request.setRequestBody(agentRequestDTO);
        //发送请求
        AgentAgreeInquiryResponseDTO reimbursementResponse = sellsManageOutService.agentQuery(request);
        if (reimbursementResponse != null) {
            AgentQueryResVO agentRes = new AgentQueryResVO();
            agentRes.setSalesMainMsgDto(reimbursementResponse);
            return agentRes;
        }
        return null;
    }


    
    
    
    

	@Override
	public NoncarOrder findNoncarOrderByInsureNo(String insureNo) {
		NoncarOrder noncarOrder=null;
		try {
			noncarOrder = storeInsureInfoMapper.selectNoncarOrderByInsureNo(insureNo);
		} catch (Exception e) {
			//log.info("根据投保单号查询投保单信息失败");
			log.info("cxbdsb");

		}
		return noncarOrder;
	}
	
	@Override
	public NoncarOrder selectNoncarOrderByInsureNoSubmission(String insureNo) {
		NoncarOrder noncarOrder=null;
		try {
			noncarOrder = storeInsureInfoMapper.selectNoncarOrderByInsureNoSubmission(insureNo);
		} catch (Exception e) {
			//log.info("根据投保单号查询投保单信息失败");
			log.info("cxbdsb");

		}
		return noncarOrder;
	}
	
	
	@Override
	public int updateSignStatusTwo(NoncarOrder noncarOrder) {
		int row =0;
		try {
			row=storeInsureInfoMapper.updateSignStatusTwo(noncarOrder);
			//row =dao.doUpdate("storeInsureInfo.updateSignStatus",storeInsureInfo);
		} catch (Exception e) {
			log.info("投保单信息更新签名失败！");
		}
		return (Integer) row;
	}



	@Override
	public List<NoncarOrder> findNoncarOrderByRelationOrderId(String id) {
		List<NoncarOrder> submissionNoncarOrder = storeInsureInfoMapper.findNoncarOrderByRelationOrderId(id);
		return submissionNoncarOrder;
	}



	@Override
	public int updateSignStatusSubmission(NoncarOrder noncarOrder) {
		int row =0;
		try {
			row=storeInsureInfoMapper.updateSignStatusSubmission(noncarOrder);
            storeInsureInfoMapper.updateSignStatusSubmissionByRelationOrderId(noncarOrder.getId());
		} catch (Exception e) {
			log.info("投保单信息更新签名失败！");
		}
		return (Integer) row;
	}


}
