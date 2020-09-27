package com.ccic.salesapp.noncar.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salesapp.noncar.dto.response.StoreOracleResponseVO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.AgentDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.ApplicantDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.ChannelDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.InsuredDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.ItemKindDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.MainDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.QuotePriceRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.QuotePriceResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.bean.RationDTO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SubMillionMedicalService;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;
import com.ccic.salesapp.noncar.service.AccidentQuoteService;
import com.ccic.salesapp.noncar.service.StoreProcessService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salessapp.common.core.web.HttpResult;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Service
@Slf4j
public class SubMillionMedicalServiceImpl implements SubMillionMedicalService {
	private SimpleDateFormat datef2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
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
	public HttpResult subMillionMedical(StoreOracleRequestVO requestVo)
			throws Exception {
		HttpResult result = HttpResult.success();
		StoreOracleResponseVO respBody = new StoreOracleResponseVO();
		StoreInsureInfo storeInsureInfo = new StoreInsureInfo();
		storeInsureInfo.setInsuranceType("0");
		QuotePriceRequestDTO quotePriceRequestDTO = new QuotePriceRequestDTO();
		ChannelDTO channelDTO = new ChannelDTO();
		channelDTO.setChannelCode("310073");// 渠道代码
		channelDTO.setChannelName("移动销售支持");// 渠道名称
		channelDTO.setChannelComCode("310073");// 渠道机构代码
		channelDTO.setChannelComName("移动销售支持");// 渠道机构名称
		if(requestVo.getFormulaCode()!=null){
			channelDTO.setChannelProductCode(requestVo.getFormulaCode().substring(0, 10));// 产品代码
		}
		channelDTO.setChannelTradeCode("001");
		channelDTO.setChannelTradeSerialNo(storeProductPlaceUtilService.getTradeSerialNo());
		channelDTO.setChannelTradeDate(datef2.format(new Date()));
		quotePriceRequestDTO.setChannel(channelDTO);
		//查询方案信息
		StoreFormulas productFormula =storeProductPlaceUtilService.getProductFormula(requestVo.getFormulaCode(), requestVo.getProductCode(),"0"); 
		if(null != productFormula){
			JSONObject  itemKindPre=null;
			JSONObject addPremium = null;	//附加险的保费
			JSONObject extensionJson = JSONObject.fromObject(productFormula.getExtensionJson());
			JSONObject jsonAmounts  = JSONObject.fromObject(productFormula.getAmountsJson());
			JSONObject jsonPremiums = JSONObject.fromObject(productFormula.getPremiumsJson());
			
			requestVo.setAmountInfoJson(productFormula.getAmountInfoJson());
			requestVo.setPremiumsJson(productFormula.getPremiumsJson());
			requestVo.setExtensionJson(productFormula.getExtensionJson());
//			Date insdate=null;
//			Date startDate=null;
//			try {
//				startDate = datef.parse(requestVo.getStartDate());
//				if("01".equals(requestVo.getInsuredIdentifyType()) || "28".equals(requestVo.getInsuredIdentifyType())){
//					insdate=this.getIdCardDate(requestVo.getInsuredIdentifyNumber());
//				}else{
//					insdate=datef.parse(requestVo.getInsuredBirthDate());
//				}
//			} catch (ParseException e) {
//					e.printStackTrace();
//			}
//			int age=this.getAge(insdate, startDate);		
//			if((age==0 && this.getDay(insdate, startDate)<30) || age>60 ){				
//				result.setCode("0");
//				result.setMsg("被保人年龄必须大于30天， 小于等于60周岁");
//				jsonVO.setResult(result);
//				return jsonVO;
//			}
			if(requestVo.getIsSheBao()!=null){
				if ("W".equals(requestVo.getIsSheBao())) {
					JSONObject wsheBao = JSONObject.fromObject(jsonPremiums.getString("W"));
					JSONObject wsheBaoAddPremium = JSONObject.fromObject(extensionJson.getString("W"));
					itemKindPre = JSONObject.fromObject(wsheBao.getString(requestVo.getAges()));
					addPremium = JSONObject.fromObject(wsheBaoAddPremium.getString(requestVo.getAges()));
				} else {
					JSONObject ysheBao = JSONObject.fromObject(jsonPremiums.getString("Y"));
					JSONObject ysheBaoAddPremium = JSONObject.fromObject(extensionJson.getString("Y"));
					itemKindPre =JSONObject.fromObject(ysheBao.getString(requestVo.getAges()));
					addPremium = JSONObject.fromObject(ysheBaoAddPremium.getString(requestVo.getAges()));
				}
			}
			//赋值AgentDTO信息
			AgentInfoVO agentInfoVO = requestVo.getAgentInfo();
			if("1".equals(requestVo.getIsAgent()) && requestVo.getAgentInfo() != null && !"".equals(requestVo.getAgentInfo()) ){
				AgentDTO agentDTO = new AgentDTO();
				agentDTO.setAgentCode(agentInfoVO.getAgentCode());
				agentDTO.setAgreementName(agentInfoVO.getAgreementName());
				agentDTO.setAgreementNo(agentInfoVO.getAgreementNo());
				agentDTO.setBusinessNature2(agentInfoVO.getBusinessNature2());// 业务来源2
				agentDTO.setHandler2Code(agentInfoVO.getHandler2Code());// 经办人2代码
				agentDTO.setHandler2Name(agentInfoVO.getHandler2Name());// 经办人2代码
				quotePriceRequestDTO.setAgent(agentDTO);
				
			}
			//赋值Main信息
			MainDTO mainDTO = new MainDTO();
			
			mainDTO.setPlanCode(requestVo.getRiskCode());
			mainDTO.setRiskCode(requestVo.getRiskCode());
			mainDTO.setBusinessAttrubute("0801");//大地通保的业务来源属性
			mainDTO.setBusinessNature("0101");//业务来源
			if("1".equals(requestVo.getIsAgent())){
				mainDTO.setBusinessNature(requestVo.getAgentInfo().getBusinessNature());//业务来源
			}
			StoreInfo storeInfo=new StoreInfo();
			storeInfo=storeProcessService.findStoreByCode(requestVo.getStoreCode());//获取门店信息
			if(storeInfo==null){
				result.setCode("0");
				result.setMsg("根据门店信息查询门店代码为空。");
				return result;
			}else{
				mainDTO.setOperateCode(storeInfo.getUserCode());//操作员代码
				mainDTO.setHandlerCode(storeInfo.getUserCode());//归属经办人
				mainDTO.setMakeCom(storeInfo.getUserComCode());//出单机构
				mainDTO.setComCode(storeInfo.getUserComCode());//归属机构
				storeInsureInfo.setOperatorName(storeInfo.getUserName());
				storeInsureInfo.setHandlerCode(storeInfo.getUserCode());
				storeInsureInfo.setComCode(storeInfo.getUserComCode());
				storeInsureInfo.setUserId(storeInfo.getUserCode());
				storeInsureInfo.setOperatorCode(storeInfo.getUserCode());
			}

			mainDTO.setLanguage("C");//语种
			mainDTO.setPolicyType("01");//保单类型
			mainDTO.setPolicySort("1");//保单种类
			mainDTO.setStartDate(requestVo.getStartDate());//起保日期
			mainDTO.setStartHour(0);//起保小时
			mainDTO.setEndDate(requestVo.getEndDate());//终保日期
			mainDTO.setEndHour(24);//终保小时
			mainDTO.setPayTimes("1");//缴费次数
			mainDTO.setCurrency("CNY");//币别
			mainDTO.setSumAmount(requestVo.getSumAmount());//总保额
			mainDTO.setSumPremium(requestVo.getSumPremium());//总保费
			mainDTO.setCoinsFlag("0");//联/共保标志
			mainDTO.setReinsFlag("0");//商业分保标志
			mainDTO.setArgueSolution("1");//合同争议解决方式
			mainDTO.setJudicalScope("中国境内（港澳台除外）");//司法管辖范围
			mainDTO.setInputDate(datef.format(new Date()));//录入日期
			mainDTO.setJFeeFlag("1");//见费出单业务标志
			mainDTO.setFormulaCode(requestVo.getFormulaCode().substring(0, 10));//方案代码
			mainDTO.setSumQuantity(new Double(requestVo.getFormulaSize()));//保险份数
			quotePriceRequestDTO.setMain(mainDTO);//保存投保单主信息
			//赋值地址信息
//			AddressDTO addressDTO = new AddressDTO();
//			AddressInfoVO addressInfo = requestVo.getAddressInfo();
//			addressDTO.setCountyCode(addressInfo.getCountyCode());//县级代码
//			addressDTO.setPrefectureCode(addressInfo.getPrefectureCode()+ "00");// 辖区代码
//			addressDTO.setProvinceCode(addressInfo.getProvinceCode()+ "0000");// 省份代码
//			requestVo.getAddressInfo().setAddressName(addressInfo.getAddressName());
			//quotePriceRequestDTO.getAddress().add(addressDTO);			
			//投保人
			ApplicantDTO applicationDTO = new ApplicantDTO();
			applicationDTO.setAppliType("1");//投保人类型
			applicationDTO.setAppliName(requestVo.getAppliName());//投保人名称
			applicationDTO.setAppliNature("3");//投保人性质
			applicationDTO.setIdentifyType(requestVo.getAppliIdentifyType());//关系人证件类型
			applicationDTO.setIdentifyNumber(requestVo.getAppliIdentifyNumber());//关系人证件号码
			applicationDTO.setPhoneNumber(requestVo.getAppliMobile());//关系人联系电话
			applicationDTO.setBirthDay(requestVo.getAppliBirthDate());
			applicationDTO.setNationality("CHN");//投保人国籍代码
			applicationDTO.setInsuredIdentity(requestVo.getAppliIdentity());//与被保险人关系
			applicationDTO.setMobile(requestVo.getAppliMobile());//投保人移动电话
			applicationDTO.setNationality(requestVo.getAppliNationality());//投保人国籍
			applicationDTO.setEmail(requestVo.getAppliEMail());//投保人邮箱
			quotePriceRequestDTO.setApplicant(applicationDTO);//保存投保人信息
			//被保人
			InsuredDTO insuredDTO = new InsuredDTO();
			insuredDTO.setInsuredType("1");  //被保险人类型
			insuredDTO.setInsuredCName(requestVo.getInsuredName());//被保险人名称
			insuredDTO.setInsuredNature("3");//被保险人性质
			insuredDTO.setIdentifyType(requestVo.getInsuredIdentifyType());//被保险人证件类型
			insuredDTO.setIdentifyNumber(requestVo.getInsuredIdentifyNumber());//被保险人证件号码
			insuredDTO.setPhoneNumber(requestVo.getInsuredMobile());//被保险人联系电话
			insuredDTO.setBirthDay(requestVo.getInsuredBirthDate());
			insuredDTO.setNationality("CHN");//被保险人国籍代码
			insuredDTO.setAppliIdentity(requestVo.getAppliIdentity());//与投保人关系
			insuredDTO.setMobile(requestVo.getInsuredMobile());//被保险人移动电话
			insuredDTO.setNationality(requestVo.getInsuredNationality());//被保险人国籍
			insuredDTO.setEmail(requestVo.getInsuredEMail());//被保险人邮箱
			quotePriceRequestDTO.getInsured().add(insuredDTO);//保存投保人信息
			
			//方案信息
			RationDTO rationDTO = new RationDTO();
			rationDTO.setRationCount(1.0);//方案份数
			rationDTO.setQuantity(1.0);//被保险人数

			//责任信息1
			ItemKindDTO itemKindDTO1 = new ItemKindDTO();
			itemKindDTO1.setRiskCode(requestVo.getRiskCode());//险种代码
			itemKindDTO1.setClauseCode("11100013");//条款代码
			itemKindDTO1.setClauseName("大地通达公共交通工具意外伤害保险条款");//条款名称
			itemKindDTO1.setKindCode("100055");//责任代码
			itemKindDTO1.setKindName("公共交通意外伤害-飞机");//责任名称
			itemKindDTO1.setStartDate(requestVo.getStartDate());//责任开始日期
			itemKindDTO1.setStartHour(0);//责任开始小时
			itemKindDTO1.setEndDate(requestVo.getEndDate());//责任结束日期
			itemKindDTO1.setEndHour(24);//责任结束小时
			itemKindDTO1.setCurrency("CNY");//币别
			itemKindDTO1.setAmount(jsonAmounts.getDouble("FJ"));
			itemKindDTO1.setUnitAmount(jsonAmounts.getDouble("FJ"));
			itemKindDTO1.setPremium(Double.parseDouble("1.0"));
			itemKindDTO1.setShortRate(100.0000);//责任短期费率
			itemKindDTO1.setRate(1.0/jsonAmounts.getDouble("FJ"));
			//责任信息2
			ItemKindDTO itemKindDTO2 = new ItemKindDTO();
			itemKindDTO2.setRiskCode(requestVo.getRiskCode());//险种代码
			itemKindDTO2.setClauseCode("11100013");//条款代码
			itemKindDTO2.setClauseName("大地通达公共交通工具意外伤害保险条款");//条款名称
			itemKindDTO2.setKindCode("100056");//责任代码
			itemKindDTO2.setKindName("公共交通意外伤害-轮船");//责任名称
			itemKindDTO2.setStartDate(requestVo.getStartDate());//责任开始日期
			itemKindDTO2.setStartHour(0);//责任开始小时
			itemKindDTO2.setEndDate(requestVo.getEndDate());//责任结束日期
			itemKindDTO2.setEndHour(24);//责任结束小时
			itemKindDTO2.setCurrency("CNY");//币别
			itemKindDTO2.setAmount(jsonAmounts.getDouble("LC"));
			itemKindDTO2.setUnitAmount(jsonAmounts.getDouble("LC"));
			itemKindDTO2.setPremium(Double.parseDouble("1.0"));
			itemKindDTO2.setShortRate(100.0000);//责任短期费率
			itemKindDTO2.setRate(1.0/jsonAmounts.getDouble("LC"));
			//责任信息3
			ItemKindDTO itemKindDTO3 = new ItemKindDTO();
			itemKindDTO3.setRiskCode(requestVo.getRiskCode());//险种代码
			itemKindDTO3.setClauseCode("11100013");//条款代码
			itemKindDTO3.setClauseName("大地通达公共交通工具意外伤害保险条款");//条款名称
			itemKindDTO3.setKindCode("100057");//责任代码
			itemKindDTO3.setKindName("公共交通意外伤害-火车（地铁、轻轨）");//责任名称
			itemKindDTO3.setStartDate(requestVo.getStartDate());//责任开始日期
			itemKindDTO3.setStartHour(0);//责任开始小时
			itemKindDTO3.setEndDate(requestVo.getEndDate());//责任结束日期
			itemKindDTO3.setEndHour(24);//责任结束小时
			itemKindDTO3.setCurrency("CNY");//币别
			itemKindDTO3.setAmount(jsonAmounts.getDouble("HC"));
			itemKindDTO3.setUnitAmount(jsonAmounts.getDouble("HC"));
			itemKindDTO3.setPremium(Double.parseDouble("1.0"));
			itemKindDTO3.setShortRate(100.0000);//责任短期费率
			itemKindDTO3.setRate(1.0/jsonAmounts.getDouble("HC"));
			
			//责任信息4
			ItemKindDTO itemKindDTO4 = new ItemKindDTO();
			itemKindDTO4.setRiskCode(requestVo.getRiskCode());//险种代码
			itemKindDTO4.setClauseCode("11100013");//条款代码
			itemKindDTO4.setClauseName("大地通达公共交通工具意外伤害保险条款");//条款名称
			itemKindDTO4.setKindCode("100193");//责任代码
			itemKindDTO4.setKindName("公共交通意外伤害-汽车");//责任名称
			itemKindDTO4.setStartDate(requestVo.getStartDate());//责任开始日期
			itemKindDTO4.setStartHour(0);//责任开始小时
			itemKindDTO4.setEndDate(requestVo.getEndDate());//责任结束日期
			itemKindDTO4.setEndHour(24);//责任结束小时
			itemKindDTO4.setCurrency("CNY");//币别
			itemKindDTO4.setAmount(jsonAmounts.getDouble("QC"));
			itemKindDTO4.setUnitAmount(jsonAmounts.getDouble("QC"));
			itemKindDTO4.setPremium(Double.parseDouble("1.0"));
			itemKindDTO4.setShortRate(100.0000);//责任短期费率
			itemKindDTO4.setRate(1.0/jsonAmounts.getDouble("QC"));
			
			//责任信息5
			ItemKindDTO itemKindDTO5 = new ItemKindDTO();
			itemKindDTO5.setRiskCode(requestVo.getRiskCode());//险种代码
			itemKindDTO5.setClauseCode("11800045");//条款代码
			itemKindDTO5.setClauseName("个人医疗保险条款（B款）");//条款名称
			itemKindDTO5.setKindCode("200476");//责任代码
			itemKindDTO5.setKindName("恶性肿瘤或重大手术疾病医疗");//责任名称
			itemKindDTO5.setStartDate(requestVo.getStartDate());//责任开始日期
			itemKindDTO5.setStartHour(0);//责任开始小时
			itemKindDTO5.setEndDate(requestVo.getEndDate());//责任结束日期
			itemKindDTO5.setEndHour(24);//责任结束小时
			itemKindDTO5.setCurrency("CNY");//币别
			itemKindDTO5.setAmount(jsonAmounts.getDouble("EY"));
			itemKindDTO5.setUnitAmount(jsonAmounts.getDouble("EY"));
			itemKindDTO5.setPremium(itemKindPre.getDouble("EX"));
			itemKindDTO5.setShortRate(100.0000);//责任短期费率
			itemKindDTO5.setRate(itemKindPre.getDouble("EX")/jsonAmounts.getDouble("EY"));
			
			
			//责任信息6
			ItemKindDTO itemKindDTO6 = new ItemKindDTO();
			itemKindDTO6.setRiskCode(requestVo.getRiskCode());//险种代码
			itemKindDTO6.setClauseCode("11800045");//条款代码
			itemKindDTO6.setClauseName("个人医疗保险条款（B款）");//条款名称
			itemKindDTO6.setKindCode("200475");//责任代码
			itemKindDTO6.setKindName("一般医疗");//责任名称
			itemKindDTO6.setStartDate(requestVo.getStartDate());//责任开始日期
			itemKindDTO6.setStartHour(0);//责任开始小时
			itemKindDTO6.setEndDate(requestVo.getEndDate());//责任结束日期
			itemKindDTO6.setEndHour(24);//责任结束小时
			itemKindDTO6.setCurrency("CNY");//币别
			itemKindDTO6.setAmount(jsonAmounts.getDouble("YB"));
			itemKindDTO6.setUnitAmount(jsonAmounts.getDouble("YB"));
			itemKindDTO6.setPremium(itemKindPre.getDouble("YB"));
			itemKindDTO6.setShortRate(100.0000);//责任短期费率
			itemKindDTO6.setRate(itemKindPre.getDouble("YB")/jsonAmounts.getDouble("YB"));
			
			rationDTO.getItemKinds().add(itemKindDTO1);
			rationDTO.getItemKinds().add(itemKindDTO2);
			rationDTO.getItemKinds().add(itemKindDTO3);
			rationDTO.getItemKinds().add(itemKindDTO4);
			rationDTO.getItemKinds().add(itemKindDTO5);
			rationDTO.getItemKinds().add(itemKindDTO6);	
			
			if ("Y".equals(requestVo.getaCTS())) {//特需医疗
				//责任信息7
				ItemKindDTO itemKindDTO7 = new ItemKindDTO();
				itemKindDTO7.setRiskCode(requestVo.getRiskCode());//险种代码
				itemKindDTO7.setClauseCode("21800007");//条款代码
				itemKindDTO7.setClauseName("附加特定疾病特需医疗扩展保险条款");//条款名称
				itemKindDTO7.setKindCode("100331");//责任代码
				itemKindDTO7.setKindName("特定疾病特需医疗");//责任名称
				itemKindDTO7.setStartDate(requestVo.getStartDate());//责任开始日期
				itemKindDTO7.setStartHour(0);//责任开始小时
				itemKindDTO7.setEndDate(requestVo.getEndDate());//责任结束日期
				itemKindDTO7.setEndHour(24);//责任结束小时
				itemKindDTO7.setCurrency("CNY");//币别
				itemKindDTO7.setAmount(jsonAmounts.getDouble("TY"));
				itemKindDTO7.setUnitAmount(jsonAmounts.getDouble("TY"));
				itemKindDTO7.setPremium(addPremium.getDouble("TY"));
				itemKindDTO7.setShortRate(100.0000);//责任短期费率
				itemKindDTO7.setRate(addPremium.getDouble("TY")/jsonAmounts.getDouble("TY"));
				rationDTO.getItemKinds().add(itemKindDTO7);	
			}
			
			if ("Y".equals(requestVo.getHeavyIonMedical())) {//重离子医疗
				//责任信息8
				ItemKindDTO itemKindDTO8 = new ItemKindDTO();
				itemKindDTO8.setRiskCode(requestVo.getRiskCode());//险种代码
				itemKindDTO8.setClauseCode("21800008");//条款代码
				itemKindDTO8.setClauseName("附加扩展质子重离子医疗保险条款");//条款名称
				itemKindDTO8.setKindCode("100332");//责任代码
				itemKindDTO8.setKindName("质子重离子医疗");//责任名称
				itemKindDTO8.setStartDate(requestVo.getStartDate());//责任开始日期
				itemKindDTO8.setStartHour(0);//责任开始小时
				itemKindDTO8.setEndDate(requestVo.getEndDate());//责任结束日期
				itemKindDTO8.setEndHour(24);//责任结束小时
				itemKindDTO8.setCurrency("CNY");//币别
				itemKindDTO8.setAmount(jsonAmounts.getDouble("ZY"));
				itemKindDTO8.setUnitAmount(jsonAmounts.getDouble("ZY"));
				itemKindDTO8.setPremium(addPremium.getDouble("ZY"));
				itemKindDTO8.setShortRate(100.0000);//责任短期费率
				itemKindDTO8.setRate(addPremium.getDouble("ZY")/jsonAmounts.getDouble("ZY"));
				rationDTO.getItemKinds().add(itemKindDTO8);	
			}
			//组装分组方案信息列表
			quotePriceRequestDTO.getRation().add(rationDTO);
			QuotePriceResponseDTO  quotePriceResponseDTO  =	storeProductPlaceUtilService.quotePriceInfo(quotePriceRequestDTO, result,requestVo.getHandlerInfo());
			if (!"0".equals(result.getCode())) {
				if(quotePriceResponseDTO.getProposal().getProposalno()!=null && !"".equals(quotePriceResponseDTO.getProposal().getProposalno())){
					log.info("######意健险试算成功，投保单已生成。ProposalNo:{},"+new Object[]{quotePriceResponseDTO.getProposal().getProposalno()});
					//定义投保信息对象存储投保信息
					storeInsureInfo = new StoreInsureInfo();
					storeInsureInfo.setInsureNo(quotePriceResponseDTO.getProposal().getProposalno());//投保单号
					storeInsureInfo.setInsuranceCode("WTH");//险种代码
					storeInsureInfo.setInsuranceName("天地关爱百万医疗");//险种名称
					storeInsureInfo.setStatus(InsuranceApplicationStatus.ZANCUN);//试算成功 ,暂存
					storeInsureInfo.setInsuranceType("0");
					storeInsureInfo.setHandlerCode(storeInfo.getUserCode());// 归属经办人
					storeInsureInfo.setOperatorCode(storeInfo.getUserCode());// 操作员代码
					storeInsureInfo.setOperatorName(storeInfo.getUserName());// 操作员姓名
					storeInsureInfo.setComCode(storeInfo.getUserComCode());// 客户经理comcode
					storeInsureInfo.setUserId(storeInfo.getUserCode()); //所属用户
					storeInsureInfo.setSumPremium(requestVo.getSumPremium()+"");
					storeInsureInfo.setSumAmount(requestVo.getSumAmount()+"");
					storeInsureInfo.setInsuranceType("0");
					storeInsureInfo.setSignStatus("0");//未签名
					String additionalCode = "";
					if (requestVo.getaCTS().equals("Y")) {
						additionalCode+="_aCTS";
					}
					if (requestVo.getHeavyIonMedical().equals("Y")) {
						additionalCode+="_heavyIonMedical";
					}
					storeInsureInfo.setAdditionalCode(additionalCode);
					StoreInsureInfo  insureInfo1 =	storeProductPlaceUtilService.saveInsureInfo(storeInsureInfo,requestVo, result,requestVo.getHandlerInfo());// 保存试算信息
					// 提交核保
					storeProductPlaceUtilService.preUndwrt(insureInfo1.getInsureNo(), result);
					// 核保成功
					if ("1".equals(result.getCode())) {
						insureInfo1.setStatus(InsuranceApplicationStatus.YIHEBAO);//已核保
						storeProductPlaceUtilService.updateInsureInfo(insureInfo1, result);//调用保存下单方法更新记录
//						StoreInsureInfo insureInfo2=productUtil.saveApplyPayment(insureInfo1, result);
						respBody.setInsureInfo(insureInfo1);
						respBody.setCode("1");
						result.setData(respBody);
					}
	
				}
			}else{
				result.setCode("0");
				//result.setMsg(result.getMessage());
				log.info("##########试算失败，未返回投保单号。############");
			}
		}
		return result;

	}
//	public int getAge(Date date1,Date date2){
//		int c=0;		
//		int y = date2.getYear()-date1.getYear()-1;
//	 	if (date2.getMonth()>date1.getMonth()) {
//	 		c=1;
//	 	}else if (date2.getMonth()==date1.getMonth()) {
//	 		if (date2.getDate()>=date1.getDate()) {
//	 			c=1;
//	 		};
//	 	};
//	 	return (y+c);
//	}
	// 通过身份证获取日期
	private Date getIdCardDate(String IDCardNum) {
		Date d=null;
		int year, month, day, idLength = IDCardNum.length();

		if (idLength == 18) {
			year = Integer.parseInt(IDCardNum.substring(6, 10));
			month = Integer.parseInt(IDCardNum.substring(10, 12));
			day = Integer.parseInt(IDCardNum.substring(12, 14));
		} else if (idLength == 15) {
			year = Integer.parseInt(IDCardNum.substring(6, 8)) + 1900;
			month = Integer.parseInt(IDCardNum.substring(8, 10));
			day = Integer.parseInt(IDCardNum.substring(10, 12));
		} else {
			System.out.println("你输入的身份证无效。");
			return null;
		}
		try {
			d=datef.parse(year+"-"+month+"-"+day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 	return d;
	}
}
