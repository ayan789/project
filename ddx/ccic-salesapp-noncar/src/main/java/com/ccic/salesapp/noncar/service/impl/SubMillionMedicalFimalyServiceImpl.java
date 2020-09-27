package com.ccic.salesapp.noncar.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.AccidentalInjuryInfo;
import com.ccic.salesapp.noncar.dto.AddressInfoVO;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.InsuredGroup;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteResponse;
import com.ccic.salesapp.noncar.dto.request.accidentquote.ChannelOpInfo;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyCoverage;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyCustomer;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyLob;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyPaymentInfo;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyRegion;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyRisk;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.response.StoreOracleResponseVO;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;
import com.ccic.salesapp.noncar.service.AccidentQuoteService;
import com.ccic.salesapp.noncar.service.StoreProcessService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salesapp.noncar.service.SubMillionMedicalFimalyService;
import com.ccic.salessapp.common.config.WebContants;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Service
@Slf4j
public class SubMillionMedicalFimalyServiceImpl implements SubMillionMedicalFimalyService {

	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	AccidentQuoteService service;
	@Autowired
	StoreProcessService storeProcessService;
	@Autowired
	StoreProductPlaceUtilService storeProductPlaceUtilService;
	@Autowired
	AccidentUnderwritingService accidentUnderwritingService;
	
	
	@Override
	public HttpResult subMillionMedical(StoreOracleRequestVO requestVo)	throws Exception {
		HttpResult httpResult = HttpResult.error(0, "下单异常");
		StoreOracleResponseVO respBody = new StoreOracleResponseVO();
		StoreInsureInfo storeInsureInfo = new StoreInsureInfo();
		storeInsureInfo.setInsuranceType("0");
		// 获取门店信息
		StoreInfo storeInfo = new StoreInfo();
		storeInfo = storeProcessService
				.findStoreByCode(requestVo.getStoreCode());
		if (storeInfo == null) {
			httpResult.setCode("0");
			httpResult.setMsg("根据门店信息查询门店代码为空。");
			return httpResult;
		}

		// 保额 保费
		StoreFormulas storeFormulas = storeProductPlaceUtilService.getProductFormula(
				requestVo.getFormulaCode(), requestVo.getProductCode(), "1");
		if (null != storeFormulas) {
			Double itemKindPreEX = 0.0; //恶性肿瘤或重大手术疾病医疗
			Double itemKindPreYB = 0.0; //一般医疗
			Double addPremiumTY = 0.0;	//附加险的保费(特定疾病特需医疗)
			Double addPremiumZY = 0.0;	//附加险的保费(质子重离子医疗)
			JSONObject extensionJson = JSONObject.fromObject(storeFormulas.getExtensionJson());
			JSONObject jsonAmounts  = JSONObject.fromObject(storeFormulas.getAmountsJson());
			JSONObject jsonPremiums = JSONObject.fromObject(storeFormulas.getPremiumsJson());
			//JSONObject itemKindPre = null;
			//JSONObject addPremium = null; // 附加险的保费
			JSONObject ysheBao = JSONObject.fromObject(jsonPremiums);
			JSONObject ysheBaoAddPremium = JSONObject.fromObject(extensionJson);
			
			requestVo.setAmountInfoJson(storeFormulas.getAmountInfoJson());
			requestVo.setPremiumsJson(storeFormulas.getPremiumsJson());
			requestVo.setExtensionJson(storeFormulas.getExtensionJson());

			// 封装数据
			AccidentQuoteRequest accrequest = new AccidentQuoteRequest();
			// 投保单号
			accrequest.setProposalNo(requestVo.getInsureNo());
			// 业务来源属性
			accrequest.setBusinessAttribute("0801");
			// 保单性质（团单标记，区分个团） 01 个人 02团体
			accrequest.setPolicyNature("01");
			// 分户类型(被保险人清单类型)
			accrequest.setInsuredListType("1");
			// 产品代码
			accrequest.setProductCode(requestVo.getRiskCode());
			// 请求业务类型
			accrequest.setBusinessType("1");
			// 业务来源1
			accrequest.setBusinessSourceCode("0101");
			// 业务来源2
			accrequest.setBusinessSource2Code("0101");

			if (storeInfo != null) {
				accrequest.setOrgCode(storeInfo.getUserComCode());// 归属机构代码
				accrequest.setIssueUserCode(storeInfo.getUserCode());// 出单员代码
				accrequest.setIssueOrgCode(storeInfo.getUserComCode());// 出单机构代码
				// 归属经办人，仅在直销业务中使用
				accrequest.setHandlerCode(storeInfo.getUserCode());
				accrequest.setHandlerName(storeInfo.getUserName());
				accrequest.setBelongToHandlerCode(storeInfo.getUserCode());// 归属经办人编码
				accrequest.setBelongToHandlerName(storeInfo.getUserName());// 归属经办人名称
				accrequest.setBelongToHandler2Code(storeInfo.getUserCode());// 业务风险分类
				accrequest.setBelongToHandler2Name(storeInfo.getUserName());// 业务风险分类名称
			}

			AgentInfoVO agent = null;
			agent = requestVo.getAgentInfo();
			if (agent != null) {
				if ("1".equals(requestVo.getIsAgent())) {
					// 代理人ID
					accrequest.setAgentCode(agent.getAgentCode());
					// 代理人协议号
					accrequest.setAgreementNo(agent.getAgreementNo());
					accrequest.setBusinessSourceCode(agent.getBusinessNature());// 业务来源
					if (StringUtils.isNotBlank(agent.getBusinessNature2())) {
						accrequest.setBusinessSource2Code(agent
								.getBusinessNature2());// 业务来源2
						accrequest.setBelongToHandler2Code(agent
								.getHandler2Code()); // 经办人2代码
						accrequest.setBelongToHandler2Name(agent
								.getHandler2Name()); // 经办人2名称
					} else {
						accrequest.setBusinessSource2Code(agent
								.getBusinessNature());// 业务来源2
						accrequest
								.setBelongToHandler2Code(agent.getAgentCode()); // 经办人2代码
						accrequest
								.setBelongToHandler2Name(agent.getAgentName()); // 经办人2名称
					}
					// 经办人
					accrequest.setHandlerCode(agent.getAgentCode());
					accrequest.setHandlerName(agent.getAgentName());
				}
			}
			// 起保日期
			accrequest.setEffectiveDate(requestVo.getStartDate());
			// 终保日期
			accrequest.setExpiryDate(requestVo.getEndDate());
			// 投保日期
			accrequest.setProposalDate(datef.format(new Date()));
			// 是否定期结算标识
			accrequest.setIsRegularSettlement("N");
			// 保额
			accrequest.setSumInsured(String.valueOf(requestVo.getSumAmount()));
			// 应收保费
			accrequest.setDuePremium(String.valueOf(requestVo.getSumPremium()));
			// 总折扣率
			accrequest.setTotalDiscountRate(null);
			// 保额币种
			accrequest.setSiCurrencyCode("CNY");
			// 保费币种
			accrequest.setPremiumCurrencyCode("CNY");
			// 短期费率
			accrequest.setShortRate("1");
			// 是否涉农标识
			accrequest.setAgricultureRelaType("N");
			// 共保标记
			accrequest.setCoInsuranceType("01");
			// 联保标记
			accrequest.setInternalCoInsuranceType("01");
			// 争议解决方式
			accrequest.setArgueSolutionType("01");
			// 司法管辖
			accrequest.setJudicalScopeCode("01");
			// 是否见费出单标志
			accrequest.setIsIssueAfterPay("Y");

			// 外部渠道出单时的附加信息
			List<ChannelOpInfo> channelOpInfoList = new ArrayList<ChannelOpInfo>();
			ChannelOpInfo channelOpInfo = new ChannelOpInfo();
			// 渠道代码
			channelOpInfo.setChannelCode("310073");
			// 渠道机构代码
			channelOpInfo.setChannelComCode("310073");
			// 渠道机构名称
			channelOpInfo.setChannelComName("移动销售支持");
			// 渠道名称
			channelOpInfo.setChannelName("移动销售支持");
			// 渠道产品代码
			channelOpInfo.setChannelProductCode("WTH");// 产品代码
			// 渠道交易流水号
			channelOpInfo.setChannelSeqNo(storeProductPlaceUtilService.getTradeSerialNo());
			// 渠道交易日期
			channelOpInfo.setTrxDate(datef.format(new Date()));
			// 渠道交易代码
			channelOpInfo.setTrxCode("001");
			
			//第三方业务员信息
			if(requestVo.getAgentInfo() != null) {
				channelOpInfo.setThirdPartyHandlerNo(requestVo.getAgentInfo().getThirdPartyHandlerNo());
				channelOpInfo.setThirdPartyHandlerName(requestVo.getAgentInfo().getThirdPartyHandlerName());
			}
			channelOpInfoList.add(channelOpInfo);
			accrequest.setChannelOpInfoList(channelOpInfoList);
			// -------------------------------------------------------

			// 投保人
			List<PolicyCustomer> policyCustomerList = new ArrayList<PolicyCustomer>();
			PolicyCustomer policyCustomer = new PolicyCustomer();
			policyCustomer.setCustomerRoleCode("1");// 客户角色类型(投保人/被保人、车主/关键联系人)
			policyCustomer.setPartyCategory("01");// 个人/机构-当事人类型/客户性质，个人/法人
			policyCustomer.setCustomerName(requestVo.getAppliName());// 客户姓名/单位名称
			policyCustomer.setIndiMobile(requestVo.getAppliMobile());// 个人-手机号
			policyCustomer.setTel(requestVo.getAppliMobile());// 个人-手机号
			policyCustomer.setEmail(requestVo.getAppliEMail());// 个人/机构-邮件地址
			policyCustomer.setIdNo(requestVo.getAppliIdentifyNumber());// 个人/机构-证件号
			policyCustomer.setIdType(this.transCodeCommon(requestVo.getAppliIdentifyType(),WebContants.IndetifiType));// 个人/机构-证件类型
			policyCustomer.setIndiDateOfBirth(requestVo.getAppliBirthDate());// 出生日期
			policyCustomer.setNationalityCode("CHN");// 个人/机构-国籍
			policyCustomerList.add(policyCustomer);
			accrequest.setPolicyCustomerList(policyCustomerList);

			// ---------------------------------------------------
			List<PolicyLob> policyLobList = new ArrayList<PolicyLob>();
			PolicyLob policyLob = new PolicyLob();

			// ----------------------------------------------------

			// 被保险人
			List<PolicyRisk> policyRiskList = new ArrayList<PolicyRisk>();
			PolicyRisk policyRisk = new PolicyRisk();
			List<PersonInsured> personInsuredList = new ArrayList<PersonInsured>();
			List<AccidentalInjuryInfo> insureds = requestVo.getAccidentalInjuryInfos();
			for (AccidentalInjuryInfo insuredFamily : insureds) {
				PersonInsured personInsured = new PersonInsured();
				personInsured.setCustomerRoleCode("2");
				personInsured.setPartyCategory("01");// 个人/机构-当事人类型/客户性质，个人/法人
				personInsured.setCustomerName(insuredFamily.getFamilyName());// 客户姓名
				//personInsured.setEmail(insuredFamily.getInsuredEMail());// 个人/机构-邮件地址
				personInsured.setIdNo(insuredFamily.getFamilyIdentifyNo());// 个人/机构-证件号
				personInsured.setIdType(this.transCodeCommon(insuredFamily.getFamilyIdentifyType(),WebContants.IndetifiType));// 证件类型
				personInsured.setIndiMobile(insuredFamily.getMobile());// 个人-手机号
				personInsured.setTel(insuredFamily.getMobile());// 个人-手机号
				personInsured.setDateOfBirth(insuredFamily.getInsuredBirthDate());// 出生日期
				personInsured.setNationalityCode("CHN");// 个人/机构-国籍
				personInsured.setSocialSecurityFlag("Y".equals(requestVo.getIsSheBao())?"Y":"N");// 有无社保
				personInsured.setMedicalInsuranceCode(insuredFamily.getInsuredSheBaoType());// 社保类型
				personInsured.setPolHolderInsuredRelaCode(this.getPolHolderInsuredRelaCode(insuredFamily.getFamilyRelation()));// 投保人与被保人关系
				personInsured.setKeyCoveragePlanGroup("1");// 关联保障计划组别
				personInsuredList.add(personInsured);
				itemKindPreEX = itemKindPreEX + JSONObject.fromObject(ysheBao.getString(insuredFamily.getAge())).getDouble("EX");
				itemKindPreYB = itemKindPreYB + JSONObject.fromObject(ysheBao.getString(insuredFamily.getAge())).getDouble("YB");
				addPremiumTY = addPremiumTY + JSONObject.fromObject(ysheBaoAddPremium.getString(insuredFamily.getAge())).getDouble("TY");
				addPremiumZY = addPremiumZY + JSONObject.fromObject(ysheBaoAddPremium.getString(insuredFamily.getAge())).getDouble("ZY");
				
			}
			policyRisk.setPersonInsuredList(personInsuredList);
			int securityNumDou = insureds.size();
			List<InsuredGroup> insuredGroupList = new ArrayList<InsuredGroup>();
			InsuredGroup insuredGroup = new InsuredGroup();
			insuredGroup.setInsuredGroupNo("1");
			insuredGroup.setPlanCode(requestVo.getFormulaCode().substring(0, 10));
			insuredGroup.setNumberOfCopies(String.valueOf(requestVo.getFormulaSize()));
			insuredGroup.setInsuredCount(String.valueOf(securityNumDou));

			List<PolicyCoverage> policyCoverageList1 = new ArrayList<PolicyCoverage>();

			// 责任信息 1
			PolicyCoverage coverage1 = new PolicyCoverage();
			// 条款代码
			coverage1.setClauseCode("CF1100290");
			// 关联责任ID【关联责任代码】
			//coverage1.setRelatedCoverageCode(null);
			// 责任代码
			coverage1.setKindCode("C100468");
			// 平均保额
			coverage1.setAvgSumInsured(jsonAmounts.getDouble("FJ"));
			// 保额
			coverage1.setSumInsured(jsonAmounts.getDouble("FJ")*securityNumDou);
			// 费率
			//coverage1.setPremiumRate(1.0 / jsonAmounts.getDouble("FJ"));

			// 单位保费
			coverage1.setUnitPremium(Double.valueOf("1.0"));
			// 应收保费/真实保费/RealPremium,=调整保费
			coverage1.setDuePremium(Double.valueOf("1.0")*securityNumDou);
			// 是否计入保额
			//coverage1.setIsIncludedInSumInsured("Y");
			policyCoverageList1.add(coverage1);

			// 责任信息 2
			PolicyCoverage coverage2 = new PolicyCoverage();
			// 条款代码
			coverage2.setClauseCode("CF1100290");
			// 责任代码
			coverage2.setKindCode("C100469");
			// 平均保额
			coverage2.setAvgSumInsured(jsonAmounts.getDouble("LC"));
			// 保额
			coverage2.setSumInsured(jsonAmounts.getDouble("LC")*securityNumDou);
			// 费率
			//coverage2.setPremiumRate(1.0 / jsonAmounts.getDouble("LC"));
			// 单位保费
			coverage2.setUnitPremium(Double.valueOf("1.0"));
			// 应收保费/真实保费/RealPremium,=调整保费
			coverage2.setDuePremium(Double.valueOf("1.0")*securityNumDou);
			policyCoverageList1.add(coverage2);

			// 责任信息 3
			PolicyCoverage coverage3 = new PolicyCoverage();
			// 条款代码
			coverage3.setClauseCode("CF1100290");
			// 责任代码
			coverage3.setKindCode("C100470");
			// 平均保额
			coverage3.setAvgSumInsured(jsonAmounts.getDouble("HC"));
			// 保额
			coverage3.setSumInsured(jsonAmounts.getDouble("HC")*securityNumDou);
			// 费率
			//coverage3.setPremiumRate(1.0 / jsonAmounts.getDouble("HC"));
			// 单位保费
			coverage3.setUnitPremium(Double.valueOf("1.0"));
			// 应收保费/真实保费/RealPremium,=调整保费
			coverage3.setDuePremium(Double.valueOf("1.0")*securityNumDou);
			policyCoverageList1.add(coverage3);

			// 责任信息4
			PolicyCoverage coverage4 = new PolicyCoverage();
			// 条款代码
			coverage4.setClauseCode("CF1100290");
			// 责任代码
			coverage4.setKindCode("C100590");
			// 平均保额
			coverage4.setAvgSumInsured(jsonAmounts.getDouble("QC"));
			// 保额
			coverage4.setSumInsured(jsonAmounts.getDouble("QC")*securityNumDou);
			// 费率
			//coverage4.setPremiumRate(1.0 / jsonAmounts.getDouble("QC"));
			// 单位保费
			coverage4.setUnitPremium(Double.valueOf("1.0"));
			// 应收保费/真实保费/RealPremium,=调整保费
			coverage4.setDuePremium(Double.valueOf("1.0")*securityNumDou);
			policyCoverageList1.add(coverage4);

			// 责任信息 5
			PolicyCoverage coverage5 = new PolicyCoverage();
			// 条款代码
			coverage5.setClauseCode("CF1100934");
			// 责任代码
			coverage5.setKindCode("C200491");
			// 平均保额
			coverage5.setAvgSumInsured(jsonAmounts.getDouble("EY"));
			// 保额
			coverage5.setSumInsured(jsonAmounts.getDouble("EY")*securityNumDou);
			// 费率
			//coverage5.setPremiumRate(itemKindPreEX / (jsonAmounts.getDouble("EY")*securityNumDou));
			// 单位保费
			//coverage5.setUnitPremium(itemKindPre.getDouble("EX"));
			// 应收保费/真实保费/RealPremium,=调整保费
			coverage5.setDuePremium(itemKindPreEX);
			policyCoverageList1.add(coverage5);

			// 责任信息6
			PolicyCoverage coverage6 = new PolicyCoverage();
			// 条款代码
			coverage6.setClauseCode("CF1100934");
			// 责任代码
			coverage6.setKindCode("C200492");
			// 平均保额
			coverage6.setAvgSumInsured(jsonAmounts.getDouble("YB"));
			// 保额
			coverage6.setSumInsured(jsonAmounts.getDouble("YB")*securityNumDou);
			// 费率
			//coverage6.setPremiumRate(itemKindPreYB/(jsonAmounts.getDouble("YB")*securityNumDou));
			// 单位保费
			//coverage6.setUnitPremium(itemKindPre.getDouble("YB"));
			// 应收保费/真实保费/RealPremium,=调整保费
			coverage6.setDuePremium(itemKindPreYB);
			policyCoverageList1.add(coverage6);
			if ("Y".equals(requestVo.getaCTS())) {//特需医疗
				// 责任信息7
				PolicyCoverage coverage7 = new PolicyCoverage();
				// 条款代码
				coverage7.setClauseCode("CF2100649");
				// 责任代码
				coverage7.setKindCode("C101092");
				// 平均保额
				coverage7.setAvgSumInsured(jsonAmounts.getDouble("TY"));
				// 保额
				coverage7.setSumInsured(jsonAmounts.getDouble("TY")*securityNumDou);
				// 费率
				//coverage7.setPremiumRate(addPremiumTY/(jsonAmounts.getDouble("TY")*securityNumDou));
				// 单位保费
				//coverage7.setUnitPremium(addPremium.getDouble("TY"));
				// 应收保费/真实保费/RealPremium,=调整保费
				coverage7.setDuePremium(addPremiumTY);
				policyCoverageList1.add(coverage7);
			}
			if ("Y".equals(requestVo.getHeavyIonMedical())) {//重离子医疗
				// 责任信息8
				PolicyCoverage coverage8 = new PolicyCoverage();
				// 条款代码
				coverage8.setClauseCode("CF2100650");
				// 责任代码
				coverage8.setKindCode("C101093");
				// 平均保额
				coverage8.setAvgSumInsured(jsonAmounts.getDouble("ZY"));
				// 保额
				coverage8.setSumInsured(jsonAmounts.getDouble("ZY")*securityNumDou);
				// 费率
				//coverage8.setPremiumRate(addPremiumZY/(jsonAmounts.getDouble("ZY")*securityNumDou));
				// 单位保费
				//coverage8.setUnitPremium(addPremium.getDouble("ZY"));
				// 应收保费/真实保费/RealPremium,=调整保费
				coverage8.setDuePremium(addPremiumZY);
				policyCoverageList1.add(coverage8);
			}
			
			insuredGroup.setPolicyCoverageList(policyCoverageList1);

			insuredGroupList.add(insuredGroup);
			policyRisk.setInsuredGroupList(insuredGroupList);
			policyRiskList.add(policyRisk);
			policyLob.setPolicyRiskList(policyRiskList);

			// --------------------
			// 地址信息
			List<PolicyRegion> policyRegionList = new ArrayList<PolicyRegion>();
			PolicyRegion policyRegion = new PolicyRegion();
			AddressInfoVO addressInfo = requestVo.getAddressInfo();
			if (addressInfo != null) {
				policyRegion.setRegionCode(addressInfo.getCountyCode());// 县级代码
				policyRegionList.add(policyRegion);
				policyLob.setPolicyRegionList(policyRegionList);
			}
			policyLobList.add(policyLob);
			accrequest.setPolicyLobList(policyLobList);

			// -----------------------------------------------
			List<PolicyPaymentInfo> policyPaymentInfoList = new ArrayList<PolicyPaymentInfo>();
			PolicyPaymentInfo policyPaymentInfo = new PolicyPaymentInfo();
			policyPaymentInfo.setInstallmentPeriodCount("1");
			policyPaymentInfoList.add(policyPaymentInfo);
			accrequest.setPolicyPaymentInfoList(policyPaymentInfoList);
			
			if (requestVo.getInvoiceInfo() != null) {
				List<InvoiceInfo> invoiceInfoList = new ArrayList<InvoiceInfo>();
				invoiceInfoList.add(requestVo.getInvoiceInfo());
				accrequest.setInvoiceInfoList(invoiceInfoList);
			}
			//计算税价分离
			/*Double sumTniPremium = InsuredSumPremium - Double.valueOf(requestVo.getPersonsNum())*0.06*4/(1.06);	//不计税总保费
			storeInsureInfo.setSumTniPremium(sumTniPremium);*/
			// -----------------------------------------
			try {

				Response rs = new Response();
				rs = service.accidentQuote(accrequest,null, httpResult,
						requestVo.getHandlerInfo());
				AccidentQuoteResponse quotePriceResponseDTO = null;
				quotePriceResponseDTO = (AccidentQuoteResponse) rs
						.getResponseBody();
				if (!"0".equals(httpResult.getCode())) {
					if (quotePriceResponseDTO != null) {
						if (quotePriceResponseDTO.getProposalNo() != null
								&& !"".equals(quotePriceResponseDTO
										.getProposalNo())) {
							log.info("######意健险试算成功，投保单已生成。ProposalNo:{},"
									+ new Object[] { quotePriceResponseDTO
											.getProposalNo() });
							// 定义投保信息对象存储投保信息
							storeInsureInfo = new StoreInsureInfo();
							storeInsureInfo.setInsureNo(quotePriceResponseDTO
									.getProposalNo());// 投保单号
							storeInsureInfo.setInsuranceCode("WTH");// 险种代码
							storeInsureInfo.setInsuranceName("天地关爱百万医疗家庭版");// 险种名称
							storeInsureInfo.setStatus(InsuranceApplicationStatus.ZANCUN);// 试算成功,暂存
							storeInsureInfo.setInsuranceType("0");
							storeInsureInfo.setHandlerCode(storeInfo.getUserCode());// 归属经办人
							storeInsureInfo.setOperatorCode(storeInfo.getUserCode());// 操作员代码
							storeInsureInfo.setOperatorName(storeInfo.getUserName());// 操作员姓名
							storeInsureInfo.setComCode(storeInfo.getUserComCode());// 客户经理comcode
							storeInsureInfo.setUserId(storeInfo.getUserCode()); // 所属用户
							storeInsureInfo.setSumPremium(requestVo.getSumPremium()+"");
							storeInsureInfo.setSumAmount(requestVo.getSumAmount()+"");
							storeInsureInfo.setSignStatus("0");//未签名
							String additionalCode = "";
							if (requestVo.getaCTS().equals("Y")) {
								additionalCode+="_aCTS";
							}
							if (requestVo.getHeavyIonMedical().equals("Y")) {
								additionalCode+="_heavyIonMedical";
							}
							storeInsureInfo.setAdditionalCode(additionalCode);
							StoreInsureInfo insureInfo1 = storeProductPlaceUtilService
									.saveInsureInfo(storeInsureInfo, requestVo,
											httpResult, requestVo.getHandlerInfo());// 保存试算信息
							// 提交核保
							Response acciResp = accidentUnderwritingService.underwriting(
									insureInfo1.getInsuranceCode(),
									insureInfo1.getInsureNo());
							if (acciResp.getResponseHead() != null) {
								if(acciResp.getResponseHead().getStatus() == 0){
									insureInfo1.setStatus(InsuranceApplicationStatus.YIHEBAO);//已核保
									insureInfo1.setUnderwriteDate(new Date());
									storeProductPlaceUtilService.updateInsureInfoById(insureInfo1.getId()+"", httpResult);//调用保存下单方法更新记录
									respBody.setInsureInfo(insureInfo1);
									respBody.setCode("1");
									httpResult.setData(respBody);
								}else {
									httpResult.setCode("0");
									httpResult.setMsg(acciResp
											.getResponseHead().getAppMessage());
									log.info("#########意健险---百万医疗险核保失败。############");
									return httpResult;
								}
							}
						}
					}

				} else {
					log.info("##########百万医疗试算失败，未返回投保单号。############");
				}
			} catch (Exception e) {
				httpResult.setCode("0");
				httpResult.setMsg("系统异常，下单失败。");
				log.error(e.getMessage(),e);
				log.info("##########系统异常，下单失败。############");
			}
		}else {
			httpResult.setCode("0");
			httpResult.setMsg("未查询到方案信息");
			log.info("未查询到方案信息");
		}
		return httpResult;
	}


	/**
	 * 获取与投保人关系
	 * 
	 * @param polHolderInsuredRelaCode
	 * @return
	 */
	private String getPolHolderInsuredRelaCode(String polHolderInsuredRelaCode) {
		String code = polHolderInsuredRelaCode;
		if (polHolderInsuredRelaCode != null
				&& !polHolderInsuredRelaCode.equals("")) {
			if (polHolderInsuredRelaCode.equals("01")) {// 本人
				code = "1";
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
