package com.ccic.salesapp.noncar.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.PolicyCustomer;
import com.ccic.salesapp.noncar.dto.PolicyLob;
import com.ccic.salesapp.noncar.dto.PropertyTrialRequest;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salesapp.noncar.dto.request.noncar.ChannelOpInfo;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage;
import com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk;
import com.ccic.salesapp.noncar.dto.request.noncar.Vehicle;
import com.ccic.salesapp.noncar.dto.response.PropertyTrialResponse;
import com.ccic.salesapp.noncar.dto.response.StoreOracleResponseVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreProductMapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;
import com.ccic.salesapp.noncar.service.NewCoreOutageLossService;
import com.ccic.salesapp.noncar.service.PropertyTrialService;
import com.ccic.salesapp.noncar.service.StoreProcessService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salessapp.common.config.WebContants;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Service
@Slf4j
public class NewCoreOutageLossServiceImpl implements NewCoreOutageLossService  {
	
	@Autowired
	StoreProductPlaceUtilService storeProductPlaceUtilService;
	
	@Autowired
	StoreProcessService storeProcessService;
	
	@Autowired
	PropertyTrialService propertyTrialService;
	
	@Autowired
	StoreProductMapper storeProductMapper;
	
	@Value("${system.needPayComCode}")
	String needPayComCode;
	
	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public HttpResult placeOrder(StoreOracleRequestVO reqBody) {
		
		HttpResult result = HttpResult.success();
		StoreInsureInfo insure = new StoreInsureInfo();
		StoreOracleResponseVO respBody = new StoreOracleResponseVO();
		StoreInfo storeInfo = null;
		storeInfo = storeProcessService.findStoreByCode(reqBody.getStoreCode());// 获取门店信息
		if (storeInfo == null) {
			result.setCode("0");
			result.setMsg("根据门店信息查询门店代码为空。");
			return result;
		}
		PropertyTrialRequest requestDTO = new PropertyTrialRequest();

		// 渠道信息
		List<ChannelOpInfo> channelList = new ArrayList<ChannelOpInfo>();
		ChannelOpInfo channelInfo = new ChannelOpInfo();
		channelInfo.setChannelCode("sale");
		channelInfo.setChannelComCode("sale");
		channelInfo.setChannelComName("移动销售支持");
		channelInfo.setChannelName("移动销售支持");
		channelInfo.setChannelProductCode("YEF");
		channelInfo.setChannelSeqNo(storeProductPlaceUtilService.getTradeSerialNo());
		channelInfo.setOperatorCode(storeInfo.getUserCode()); // 渠道操作员代码
		channelInfo.setTrxDate(datef.format(new Date())); //交易时间
		channelInfo.setTrxCode("001"); //渠道交易代码
		
		//第三方业务员信息
		if(reqBody.getAgentInfo() != null) {
			channelInfo.setThirdPartyHandlerNo(reqBody.getAgentInfo().getThirdPartyHandlerNo());
			channelInfo.setThirdPartyHandlerName(reqBody.getAgentInfo().getThirdPartyHandlerName());
		}
		
		channelList.add(channelInfo);
		requestDTO.setChannelOpInfoList(channelList);
		
		// 主信息
		requestDTO.setBusinessAttribute("0801");// 业务来源属性（替换原先的出单方式，系统来源，系统业务属性）
		requestDTO.setBusinessSourceCode(StringUtils.isBlank(reqBody.getBusinessNatureCode())  ? "0101" :reqBody.getBusinessNatureCode());// 业务来源1/BusinessNature1，对应归属经办人的业务来源，存放末级的业务原来
		requestDTO.setBusinessSource2Code(StringUtils.isBlank(reqBody.getBusinessNatureCode())  ? "0101" :reqBody.getBusinessNatureCode());// 业务来源2
		requestDTO.setAgricultureRelaType("N"); // 涉农标识
		requestDTO.setCoInsuranceType("01");// 共保标记（主共/从共）
		

		requestDTO.setEffectiveDate(reqBody.getStartDate());// 起保日期/起始生效日期,支持时分
		requestDTO.setExpiryDate(reqBody.getEndDate());// 终保日期，保单止期/止保日期/失效日期，不包含这个时间点在内
														// datef.format(calendar.getTime())
		requestDTO.setInternalCoInsuranceType("01");// 联保标记（主联/从联）(DC:联共保类型代码)
		requestDTO.setIsIssueAfterPay("Y");// 是否见费出单标志
		requestDTO.setJudicalScopeCode("01");// 中华人民共和国管辖(港澳台除外)
		requestDTO.setOverseaBusinessType("1");// 境内外业务标识 境内1
		requestDTO.setPlanCode(reqBody.getFormulaCode());// 方案代码
		requestDTO.setProductCode(reqBody.getRiskCode());// 产品代码
		requestDTO.setProposalDate(datef.format(new Date()));// 投保日期
		requestDTO.setSiCurrencyCode("CNY");// 保额币种
		requestDTO.setArgueSolutionType("01"); // 争议解决方式 诉讼01
		requestDTO.setPremiumCurrencyCode("CNY");
		requestDTO.setInsuredListType("1"); // 分户类型 1标准有分户 2约定有分户
		StoreFormulas formula = storeProductPlaceUtilService.getProductFormula(
				reqBody.getFormulaCode(), reqBody.getProductCode(),"1");
		reqBody.setAddRiskAmountJson(formula.getAddRiskAmountJson());
		reqBody.setAddRiskPremiumsJson(formula.getAddRiskPremiumsJson());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("comCode",storeInfo.getUserComCode());
		map.put("needPayComCode",needPayComCode);
		Map ret = storeProductMapper.isIssueAfterPay(map);
		Long isIssueAfterPay = (Long) ret.get("isIssueAfterPay");
		if(isIssueAfterPay > 0) {
			requestDTO.setIsIssueAfterPay("Y");// 是否见费出单标志
			insure.setIsSeeFee("1"); 
		}else {
			requestDTO.setIsIssueAfterPay("N");// 是否见费出单标志
			insure.setIsSeeFee("0");
		}

		requestDTO.setOrgCode(storeInfo.getUserComCode());
		requestDTO.setIssueOrgCode(storeInfo.getUserComCode());
		requestDTO.setIssueUserId(storeInfo.getUserCode());
		// 经办人，仅在直销业务中使用
		requestDTO.setHandlerCode(storeInfo.getUserCode());
		requestDTO.setHandlerName(storeInfo.getUserName());
		requestDTO.setBelongToHandlerCode(storeInfo.getUserCode());//归属经办人
		requestDTO.setBelongToHandler2Name(storeInfo.getUserName()); // 归属经办人
		requestDTO.setBelongToHandler2Code(storeInfo.getUserCode()); // 归属经办人
		AgentInfoVO agent = null;
		agent = reqBody.getAgentInfo();
		if (agent != null) {
			if ("1".equals(reqBody.getIsAgent())) {
				// 代理人ID，仅在非直销业务中使用
				requestDTO.setAgentCode(agent.getAgentCode());
				// 代理人协议号
				requestDTO.setAgreementNo(agent.getAgreementNo());
				requestDTO.setBusinessSourceCode(agent.getBusinessNature());// 业务来源1
				if(StringUtils.isNotBlank(agent.getBusinessNature2())) {
					requestDTO.setBusinessSource2Code(agent.getBusinessNature2());//业务来源2
					requestDTO.setBelongToHandler2Code(agent.getHandler2Code()); // 经办人2代码
					requestDTO.setBelongToHandler2Name(agent.getHandler2Name()); // 经办人2名称					
				}else {
					requestDTO.setBusinessSource2Code(agent.getBusinessNature());//业务来源2
					requestDTO.setBelongToHandler2Code(agent.getAgentCode()); // 经办人2代码
					requestDTO.setBelongToHandler2Name(agent.getAgentName()); // 经办人2名称
				}
				// 经办人
				requestDTO.setHandlerCode(agent.getAgentCode());
				requestDTO.setHandlerName(agent.getAgentName());
			}
		}
		
		Double sumAmount = 0d;
		Double sumPremium = Double.parseDouble(formula.getPremium())
				* reqBody.getFormulaSize();
		
		JSONObject addRiskAmountJson = JSONObject.fromObject(formula.getAddRiskAmountJson());
		for (Iterator iterator = addRiskAmountJson.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			sumAmount += addRiskAmountJson.getDouble(key);
		}
		JSONObject addRiskPremiumsJson = JSONObject.fromObject(formula.getAddRiskPremiumsJson());
		for (Iterator iterator = addRiskPremiumsJson.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			sumPremium += addRiskPremiumsJson.getDouble(key);
			
		}
		
		
		
		//requestDTO.setDuePremium(sumPremium);// 应收保费/真实保费/RealPremium,=调整保费
		//requestDTO.setSumInsured(sumAmount);// 保额

		// 投、被保人信息
		List<PolicyCustomer> customerList = new ArrayList<PolicyCustomer>();
		// 投保人信息
		PolicyCustomer customerT = new PolicyCustomer();
		customerT.setCustomerRoleCode("1");
		if ("111".equals(reqBody.getAppliIdentifyType()) || "28".equals(reqBody.getAppliIdentifyType())) {
			customerT.setPartyCategory("01"); // 个人01 单位02
		}else {
			customerT.setPartyCategory("02"); // 个人01 单位02
			customerT.setOrgContactPersonName(reqBody.getAppliName()); //机构-联系人姓名        
			customerT.setOrgContactPersonTel(reqBody.getAppliMobile());
			customerT.setOrgRegistrationTel(reqBody.getAppliMobile()); 
			customerT.setOrgContactEmail(reqBody.getAppliEMail());
		}
		customerT.setCustomerName(reqBody.getAppliName()); // 客户姓名/单位名称
		customerT.setIdNo(reqBody.getAppliIdentifyNumber()); // 证件号
		customerT.setIdType(this.transCodeCommon(reqBody.getAppliIdentifyType(),WebContants.IndetifiType)); // 证件类型
		customerT.setEmail(reqBody.getAppliEMail());
		customerT.setTel(reqBody.getAppliMobile());
		customerT.setNationalityCode(reqBody.getAppliNationality());//个人/机构-国籍
		customerList.add(customerT);
		// 被保人信息
		PolicyCustomer customerB = new PolicyCustomer();
		customerB.setCustomerRoleCode("2"); // 投、被保人标识
		if ("111".equals(reqBody.getInsuredIdentifyType()) || "28".equals(reqBody.getInsuredIdentifyType())) {
			customerB.setPartyCategory("01"); // 个人01 单位02
		}else {
			customerB.setPartyCategory("02"); // 个人01 单位02
			customerB.setOrgContactPersonName(reqBody.getInsuredName()); //机构-联系人姓名        
			customerB.setOrgContactPersonTel(reqBody.getInsuredMobile());
			customerB.setOrgRegistrationTel(reqBody.getInsuredMobile()); 
			customerB.setOrgContactEmail(reqBody.getInsuredEMail());
		}
		customerB.setCustomerName(reqBody.getInsuredName());
		customerB.setIdNo(reqBody.getInsuredIdentifyNumber());
		customerB.setIdType(this.transCodeCommon(reqBody.getInsuredIdentifyType(),WebContants.IndetifiType)); // 证件类型
		customerB.setEmail(reqBody.getInsuredEMail());
		customerB.setTel(reqBody.getInsuredMobile());
		customerB.setPolHolderInsuredRelaCode(getPolHolderInsuredRelaCode(reqBody.getAppliIdentity()));
		customerB.setNationalityCode(reqBody.getInsuredNationality());//个人/机构-国籍
		customerList.add(customerB);
		
		//产品信息
		List<PolicyLob> lobList = new ArrayList<PolicyLob>();
		PolicyLob lob = new PolicyLob();
		lob.setEffectiveDate(reqBody.getStartDate());
		lob.setExpiryDate(reqBody.getEndDate());
		lob.setProductCode("YEF");
		lob.setPlanCode(reqBody.getFormulaCode());
		lob.setEachPersonCopies(reqBody.getFormulaSize()); //份数
		
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		Vehicle vehicle = new Vehicle();
		vehicle.setLicenseNo(reqBody.getLicenseNo());//车牌号
		vehicle.setEngineNo(reqBody.getEngineNo());//发动机号
		vehicle.setVehicleName(reqBody.getVehicleBrand());//车型
		vehicle.setVehicleCode(reqBody.getVehicleModelCode());
		vehicle.setApprovalQuality(Double.valueOf(reqBody.getApprovalQuality()));//吨位
		vehicleList.add(vehicle);
		
		List<PolicyRisk> riskList = new ArrayList<PolicyRisk>();
		PolicyRisk risk  = new PolicyRisk();
		risk.setLicenseNo(reqBody.getLicenseNo());
		risk.setEngineNo(reqBody.getEngineNo());
		risk.setVehicleModelCode(reqBody.getVehicleModelCode());
		risk.setApprovalQuality(Double.valueOf(reqBody.getApprovalQuality()));
		risk.setBrandModelName(reqBody.getBrandModelName());
		risk.setVehicleAge(reqBody.getVehicleAge());
		riskList.add(risk);
		lob.setPolicyRiskList(riskList);
		
		requestDTO.setPolicyCustomerList(customerList);
		//标的信息
		List<PolicyRisk> policyRiskList = new ArrayList<PolicyRisk>();
		//经济型
		//条款信息
		//主险1 - 标的信息
		//PolicyRisk risk = new PolicyRisk();
		risk.setEffectiveDate(reqBody.getStartDate());
		risk.setExpiryDate(reqBody.getEndDate());
		risk.setInsuredCategory(""); //标的类别
		risk.setInsuredDescription("");
		//条款信息1
		List <PolicyCoverage> policyCoverageList1 = new ArrayList<PolicyCoverage>();
		//条款信息1
		PolicyCoverage coverage1 = new PolicyCoverage();
		coverage1.setEffectiveDate(reqBody.getStartDate());  
		coverage1.setExpiryDate(reqBody.getEndDate());         
		coverage1.setProductElementCode("CF1101081");
		coverage1.setIsIncludedInSumInsured("Y");
		coverage1.setSumInsured(10000.00*reqBody.getFormulaSize()+2000000);//保额
//		coverage1.setUnitSumInsured(200000.00); //单位保额
		coverage1.setDuePremium(100.00*reqBody.getFormulaSize()*12 + 50); //应收保费/真实保费/RealPremium,=调整保费
//		coverage1.setUnitPremium(20.00); //单位保费
		coverage1.setPremiumRate(6.8);
		coverage1.setInsuredName("");
		coverage1.setNumberOfCopies(String.valueOf(reqBody.getFormulaSize()));
		coverage1.setTotalNumberOfCopies(String.valueOf(reqBody.getFormulaSize()));
		//责任信息
		List <PolicyCoverage> policyCoverageList01 = new ArrayList<PolicyCoverage>();
		PolicyCoverage coverage01 = new PolicyCoverage();
		coverage01.setEffectiveDate(reqBody.getStartDate());  
		coverage01.setExpiryDate(reqBody.getEndDate());         
		coverage01.setProductElementCode("C101123");
		coverage01.setIsIncludedInSumInsured("Y");
		coverage01.setSumInsured(10000.00*reqBody.getFormulaSize()+2000000);//保额                                   
//		coverage01.setUnitSumInsured(200000.00); //单位保额                              
		coverage01.setDuePremium(1250.00*reqBody.getFormulaSize()); //应收保费/真实保费/RealPremium,=调整保费                       
//		coverage01.setUnitPremium(20.00*reqBody.getFormulaSize()); //单位保费                                     
		coverage01.setPremiumRate(6.8);                                                                                 
		policyCoverageList01.add(coverage01);
		coverage1.setPolicyCoverageList(policyCoverageList01);
		policyCoverageList1.add(coverage1);
		risk.setPolicyCoverageList(policyCoverageList1);
		//policyRiskList.add(risk);
		
		//lob.setPolicyRiskList(policyRiskList);
		
		lobList.add(lob);
		requestDTO.setPolicyLobList(lobList);
		
		if(reqBody.getInvoiceInfo() != null) {
			Map<String ,String> temp = new HashMap<String,String>();
			temp.put("noticeInvoice", "Y"); 
			requestDTO.setTempData(temp);
			
			List<InvoiceInfo> invoiceInfoList = new ArrayList<InvoiceInfo>();
			invoiceInfoList.add(reqBody.getInvoiceInfo());
			requestDTO.setInvoiceInfoList(invoiceInfoList);
		}
		 
		StoreProduct product =  storeProductMapper.getProduct("YEF");
		reqBody.setProductName(product.getProductName());
		
		Response rs = new Response();
		try {//试算
			rs = propertyTrialService.propertyTrial(requestDTO,null,result,reqBody.getHandlerInfo(),reqBody.getAgentInfo());
			PropertyTrialResponse propertyTrialRespon = null;
			propertyTrialRespon = (PropertyTrialResponse) rs.getResponseBody();
			if ("1".equals(result.getCode()) && propertyTrialRespon!=null) {
				log.info("######家财险试算成功，投保单已生成");
				//定义投保信息对象存储投保信息
				insure.setInsuranceType("1");//保险类别
				insure.setSumAmount(String.valueOf(sumAmount));
				insure.setSumPremium(String.valueOf(sumPremium));
				insure.setUserId(storeInfo.getUserCode());//归属用户
				insure.setComCode(storeInfo.getUserComCode());//客户经理comcode
			    insure.setHandlerCode(storeInfo.getUserCode());//归属经办人
				insure.setOperatorCode(storeInfo.getUserCode());//操作员代码
				insure.setOperatorName(storeInfo.getUserName());//操作员姓名
				insure.setInsureNo(propertyTrialRespon.getProposalNo());//投保单号
				insure.setInsuranceCode("YEF");//险种代码
				insure.setInsuranceName(product.getProductName());//险种名称
				insure.setStatus(InsuranceApplicationStatus.ZANCUN);//试算成功 ,暂存
				//保存试算信息
				insure = storeProductPlaceUtilService.saveInsureInfo(insure, reqBody, result,reqBody.getHandlerInfo());
				if("0".equals(result.getCode())){
					log.info("##########下单信息保存失败。############");
					return result;
				}
			
			
			}else {
				log.info("##########试算失败，未返回投保单号。############");
				return result;
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("请求下单接口异常！");
			log.info("##########试算失败，未返回投保单号。############");
			return result;
		}
		return result;
	}
	
	/**
	 * 获取与投保人关系
	 * @param polHolderInsuredRelaCode
	 * @return
	 */
	private String getPolHolderInsuredRelaCode(String polHolderInsuredRelaCode) {
		String code = polHolderInsuredRelaCode;
		if(polHolderInsuredRelaCode!=null && !polHolderInsuredRelaCode.equals("")){
			if(polHolderInsuredRelaCode.equals("01")){//本人
				code= "1";
			}
		}
		return code;
	}
	
	/**
	 * 新老核心代码转换
	 * 
	 * */
	public String transCodeCommon(String sourceCode, String[] codes) {
		String newCode="";
		for(String code:codes){
			if(sourceCode.equals(code.split("-")[1])){
				newCode=code.split("-")[0];
				break;
			}
		}
		if(StringUtils.isBlank(newCode)) {
			return sourceCode;
		}
		return newCode;
	}
}
