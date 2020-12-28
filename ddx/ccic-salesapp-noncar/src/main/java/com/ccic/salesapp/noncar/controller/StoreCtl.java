package com.ccic.salesapp.noncar.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.AccidentalInjuryInfo;
import com.ccic.salesapp.noncar.dto.PupilName;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.StoreProductMedia;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.product.request.ProductListRequest;
import com.ccic.salesapp.noncar.dto.product.response.ProductListResponse;
import com.ccic.salesapp.noncar.dto.request.AgentRequest;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreQueryFormulaRequest;
import com.ccic.salesapp.noncar.dto.request.StoreRequest;
import com.ccic.salesapp.noncar.dto.response.StoreQueryFormulaResponse;
import com.ccic.salesapp.noncar.dto.response.StoreResponse;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.AgentQueryReqVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.AgentMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PRPDCompanyMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreProductMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SubMillionMedicalService;
import com.ccic.salesapp.noncar.repository.basedb.po.Agent;
import com.ccic.salesapp.noncar.service.ConfigService;
import com.ccic.salesapp.noncar.service.NewCoreOutageLossService;
import com.ccic.salesapp.noncar.service.NewCoreSubMillionMedicalService;
import com.ccic.salesapp.noncar.service.NewcoreSubmitUWService;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salesapp.noncar.service.StoreProcessService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salesapp.noncar.service.SubCancerPreventionPersonal;
import com.ccic.salesapp.noncar.service.SubMillionMedical2019FimalyUpgradeServiceRenewalOfInsurance;
import com.ccic.salesapp.noncar.service.SubMillionMedical2019ServiceRenewalOfInsurance;
import com.ccic.salesapp.noncar.service.SubMillionMedical2020Service;
import com.ccic.salesapp.noncar.service.SubMillionMedical2020ServiceRenewalOfInsurance;
import com.ccic.salesapp.noncar.service.SubMillionMedicalFimalyService;
import com.ccic.salesapp.noncar.service.SubMillionMedicalFimalyUpgradeService;
import com.ccic.salesapp.noncar.service.SubMillionMedicalFimalyUpgradeServiceRenewalOfInsurance;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.utils.TokenUtil;
import com.ccic.salessapp.common.utils.ValidationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;



@RestController
@Api(tags = "方案查询与下单", description = "方案查询与下单", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "noncar")
@Slf4j
public class StoreCtl {

	@Autowired
	ConfigService configService;

	@Autowired
	StoreProcessService storeProcess;

	@Autowired
	StoreProductPlaceUtilService storeProductPlaceUtilService;

	@Autowired
	NewcoreSubmitUWService newcoreSubmitUWService;

	@Autowired
	PRPDCompanyMapper pRPDCompanyMapper;

	@Autowired
	SubMillionMedicalFimalyService subMillionMedicalFimalyService;

	@Autowired
	NewCoreSubMillionMedicalService newCoreSubMillionMedicalService;

	@Autowired
	SubMillionMedical2020Service subMillionMedical2020Service;

	@Autowired
	SubMillionMedicalService subMillionMedicalService;

	@Autowired
	SubMillionMedicalFimalyUpgradeService SubMillionMedicalFimalyUpgradeService;

	@Autowired
	NewCoreOutageLossService  newCoreOutageLossService;

	@Autowired
	StoreProcessService storeProcessService;
	
	@Value("${system.needPayComCode}")
	String needPayComCode;

	@Autowired
	StoreProductMapper storeProductMapper;
	
	@Autowired
	PlanStrategyService planStrategyService;
	
	@Autowired
	AgentMapper agentMapper;
	
	@Autowired
	SubCancerPreventionPersonal subCancerPreventionPersonal;
	
	@Autowired
	SubMillionMedical2020ServiceRenewalOfInsurance subMillionMedical2020ServiceRenewalOfInsurance;
	
	@Autowired
	SubMillionMedicalFimalyUpgradeServiceRenewalOfInsurance subMillionMedicalFimalyUpgradeServiceRenewalOfInsurance;
	
	@Autowired
	SubMillionMedical2019ServiceRenewalOfInsurance subMillionMedical2019ServiceRenewalOfInsurance;
	
	@Autowired
	SubMillionMedical2019FimalyUpgradeServiceRenewalOfInsurance subMillionMedical2019FimalyUpgradeServiceRenewalOfInsurance;

	@PostMapping(value = "agentInfoQuery")
	@ApiOperation(value = "代理人信息查询", notes = "代理人信息查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<Agent> agentInfoQuery(@RequestBody AgentRequest request ){
		HttpResult<Agent> result = HttpResult.success();
		Agent agent = agentMapper.selectAgentInfo(request.getAgentCode());
		if(agent!= null) {
			result.setData(agent);
		}else {
			result.setMsg("为查询到代理人信息");
		}
		return result;
	}
	
	
    @PostMapping(value = "productList")
    @ApiOperation(value = "产品列表查询", notes = "产品列表查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<ProductListResponse> productList(@RequestBody ProductListRequest request) {
    	
    	
     return planStrategyService.storePlanStrategyList(request);
    }


	@PostMapping(value = "productQuery")
	@ApiOperation(value = "产品列表查询", notes = "产品列表查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> productQuery(@RequestBody StoreRequest reqvo) {

		log.info("产品列表请求参数："+reqvo);
		// 判断门店代码
		if (StringUtils.isEmpty(reqvo.getStoreCode())) {

			return HttpResult.error(0,"门店代码为空！");
		}

		StoreResponse resvo = new StoreResponse();
		StoreInfo sto = storeProcessService.findStoreByCode(reqvo.getStoreCode());

		try {
			if (sto != null) {
				String comCode = sto.getUserComCode();
				if ("agen".equals(sto.getUserCode().substring(0, 4))) {
					UserVO u = storeProcessService.getAgentHandlerInfo(sto.getUserCode());
					if (u != null) {
						comCode = u.getCompany();
					}
				}
				sto.setStoreName(StringEscapeUtils.escapeHtml(sto
						.getStoreName()));

				int page=reqvo.getPage() == null ? 0 : reqvo.getPage();
				int size=reqvo.getSize() == null ? 20 : reqvo.getSize();
				int offset=0;

				//起始页
				if(page>1){
					offset=(page)*size!=0?(page-1)*size:0;
				}else if(page==1){
					offset=1-1;
				}

				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("page",offset);
				paramMap.put("size", size);
				paramMap.put("comCode", comCode);
				paramMap.put("newCore", "1");
				paramMap.put("productRisk", reqvo.getProductType());
				paramMap.put("userCode", sto.getUserCode());
				paramMap.put("keyWord", reqvo.getKeyWord());
				paramMap.put("keyOrderBy", reqvo.getKeyOrderBy());
				paramMap.put("needPayComCode",needPayComCode);
				String forPeopleStatus=reqvo.getForPeopleStatus()!=null&&!"".
						equals(reqvo.getForPeopleStatus())?reqvo.getForPeopleStatus():"0";
				paramMap.put("forPeopleStatus",forPeopleStatus);

				List<StoreProduct> reslst=storeProcessService.getAllStoreProduct(paramMap);

				//获取产品素材信息
				if(reslst!=null&&reslst.size()>0){
					for(StoreProduct storeProduct:reslst){
						List<StoreProductMedia> storeProductMedia=storeProductMapper.getMedia(storeProduct.getProductCode());
						storeProduct.setMediaLst(storeProductMedia);	
					}
				}

				paramMap.put("size",0);
				List<StoreProduct> resRow=storeProcessService.getAllStoreProduct(paramMap);
				resvo.setRows(resRow.size());//获取实际总条数
				Integer pages=size>0?(int)Math.ceil((resRow.size()*1.0)/size):1;//总页数

				resvo.setPages(pages);
				resvo.setAllList(reslst);

				return HttpResult.success(resvo, 1, "获取产品列表信息成功！");

			} else {

				return HttpResult.error(0, "获取门店信息失败！");

			}
		} catch (Exception e) {

			log.error("获取产品列表信息异常！：",e);

			return HttpResult.error(0, "获取产品列表信息失败！");

		}
	}


	@PostMapping(value = "storeQueryFormula")
	@ApiOperation(value = "产品详情查询", notes = "方案查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<StoreQueryFormulaResponse> storeQueryFormula(@RequestBody StoreQueryFormulaRequest reqvo) {
		ValidationUtils.validate(reqvo);
		// 判断
		try {
			StoreQueryFormulaResponse resvo = new StoreQueryFormulaResponse();
			String userCode = reqvo.getUserCode();
			String comCode = null;
			UserToken user = planStrategyService.getAccessTokenByUserCode(userCode);
			if(user != null && "3".equals(user.getLoginFlag())) {
				comCode = planStrategyService.getAgentSalesman(user.getUserCode()).getChannelCode();
			}else {
				StoreInfo sto = storeProcess.findStoreByCode(reqvo.getStoreCode());
				if (sto != null) {
					comCode = sto.getUserComCode();
					userCode = sto.getUserCode();
				}
				
			}
			
			resvo.setSfList(storeProductPlaceUtilService.getProductFormulaList(reqvo.getProCode(), "1", comCode,
					userCode));

			return HttpResult.success(resvo);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.error(0, "异常：" + e.getMessage());
		}
	}

	@PostMapping(value = "placeOrder")
	@ApiOperation(value = "产品下单", notes = "产品下单", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult placeOrder(@RequestBody StoreOracleRequestVO reqvo) throws Exception {
		HttpResult httpResult = HttpResult.success();
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("pupilNameList", PupilName.class);
		classMap.put("accidentalInjuryInfos", AccidentalInjuryInfo.class);
		String riskCode = reqvo.getRiskCode();
		if (null == riskCode || "".equals(riskCode)) {
			httpResult.setCode("0");
			httpResult.setMsg("险种代码不能为空");
			return httpResult;

		}
		if (StringUtils.isBlank(reqvo.getStartDate())) {
			httpResult.setCode("0");
			httpResult.setMsg("保障起期不能为空");
			return httpResult;

		}
		if (StringUtils.isBlank(reqvo.getEndDate())) {
			httpResult.setCode("0");
			httpResult.setMsg("保障止期不能为空");
			return httpResult;

		}
		
		storeProductPlaceUtilService.getHandlerInfo(reqvo);
		UserToken user = planStrategyService.getAccessTokenByUserCode(reqvo.getUserCode());

		try {
			String comCode = null;
			if(user != null && "3".equals(user.getLoginFlag())) {
				comCode = planStrategyService.getAgentSalesman(user.getUserCode()).getChannelCode();
			}else {
				if (reqvo.getHandlerInfo() != null) {
					comCode = reqvo.getHandlerInfo().getCompany();
				} else {
					StoreInfo storeInfo = storeProcess.findStoreByCode(reqvo.getStoreCode());// 获取门店信息
					comCode = storeInfo.getUserComCode();
				}
			}
			
			String comName = pRPDCompanyMapper.getComName(comCode);
			if (StringUtils.isNotBlank(comName) && comName.contains("大病") && !"VTA".equals(riskCode)) {
				httpResult.setCode("0");
				httpResult.setMsg("您的归属机构为大病相关机构，不可以承保非大病相关的险种！");

				return httpResult;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		// 如果是门店分享出去 无法区分险种 选择代理协议 ，后端查询 默认选择第一个
		if ("1".equals(reqvo.getIsAgent())) {
			storeProductPlaceUtilService.selectAgentInfo(reqvo, httpResult);
			if ("0".equals(httpResult.getCode())) {
				return httpResult;
			}
		}
		boolean flagNoAddr = false;
		boolean flagNocar = false;
		boolean flagEJ = false;
		StoreInfo storeInfo = new StoreInfo();
		storeInfo = storeProcess.findStoreByCode(reqvo.getStoreCode());// 获取门店信息
		if (storeInfo != null && !storeInfo.getUserComCode().equals("")) {
			String comcode = storeInfo.getUserComCode();
			
			if(user != null && "3".equals(user.getLoginFlag())) {
				comcode = planStrategyService.getAgentSalesman(user.getUserCode()).getChannelCode();
			}
			
			flagNoAddr = configService.flagAddrInsurance(comcode.substring(0, 4));
			if (flagNoAddr) {
				httpResult.setCode("0");
				httpResult.setMsg("该地区暂时不允许出单！");
				return httpResult;
			}
			flagNocar = configService.isNewCore(comcode.substring(0, 4), "2");
			flagEJ = configService.isNewCore(comcode.substring(0, 4), "3");
			
			/*
			 * if (storeInfo.getUserCode().startsWith("agen")) { httpResult.setCode("0");
			 * httpResult.setMsg("暂不支持此类用户下单"); return httpResult; }
			 */
		}
		StringBuffer endDate = new StringBuffer();
		endDate.append(reqvo.getEndDate());
		endDate.append("T23:59:59");

		if(reqvo.getStartDate() != null) {
			reqvo.setStartDate(reqvo.getStartDate()+"T00:00:00");
		}

		if (flagEJ) { // 新核心意健险
			reqvo.setEndDate(endDate.toString());
			if ("ETX".equals(riskCode)) {// 畅行无忧险

			}else if ("WTN".equals(riskCode) && "FM50".equals(reqvo.getProductCode())) {// 天地关爱百万医疗2020个人版
				try {
					httpResult = subMillionMedical2020Service.subMillionMedical(reqvo);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("天地关爱百万医疗2020个人版试算失败");
				}
				return httpResult;
			}else if ("WTN".equals(riskCode) && "FM51".equals(reqvo.getProductCode())) {// 天地关爱百万医疗2020家庭版
				try {
					httpResult = SubMillionMedicalFimalyUpgradeService.subMillionMedical(reqvo);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("天地关爱百万医疗2020家庭版试算失败");
				}
				return httpResult;
			} else if ("WTH".equals(riskCode) && "FM31".equals(reqvo.getProductCode())) {//百万医疗家庭版
				try {
					httpResult = subMillionMedicalFimalyService.subMillionMedical(reqvo);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("百万医疗险试算失败");
				}
				return httpResult;
			} else if ("WTN".equals(riskCode) && "FM50".equals(reqvo.getProductCode())) {// 天地关爱百万医疗2020个人版
				try {
					httpResult = subMillionMedical2020Service.subMillionMedical(reqvo);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("天地关爱百万医疗2020个人版试算失败");
				}
				return httpResult;
			}else if ("WTN".equals(riskCode) && "FM51".equals(reqvo.getProductCode())) {// 天地关爱百万医疗2020家庭版
				try {
					httpResult = SubMillionMedicalFimalyUpgradeService.subMillionMedical(reqvo);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("天地关爱百万医疗2020家庭版试算失败");
				}
				return httpResult;
			}else if("WTI".equals(riskCode) && "WTI01".equals(reqvo.getProductCode())) {
		        try {
		        	httpResult = subCancerPreventionPersonal.subCancerPrevention(reqvo);
		        } catch (Exception e) {
		        	log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("“守护一生”中老年防癌险试算失败");
		        }
		        return httpResult;
		    }else if("WTN".equals(riskCode) && "XB2020_P".equals(reqvo.getProductCode())) {
		        try {
					httpResult = subMillionMedical2020ServiceRenewalOfInsurance.subMillionMedical(reqvo);
		        } catch (Exception e) {
		        	log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("天地关爱百万医疗保险（2020个人版）（续保）试算失败");
		        }
		        return httpResult;
		    }else if("WTN".equals(riskCode) && "XB2020_F".equals(reqvo.getProductCode())) {
		        try {
		        	httpResult = subMillionMedicalFimalyUpgradeServiceRenewalOfInsurance.subMillionMedical(reqvo);
		        } catch (Exception e) {
		        	log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("天地关爱百万医疗保险（2020家庭版）（续保）试算失败");
		        }
		        return httpResult;
		    }else if("WTH".equals(riskCode) && "XB2019_P".equals(reqvo.getProductCode())) {
		        try {
					httpResult = subMillionMedical2019ServiceRenewalOfInsurance.subMillionMedical(reqvo);
		        } catch (Exception e) {
		        	log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("天地关爱百万医疗保险（2019个人版）（续保）试算失败");
		        }
		        return httpResult;
		    }else if("WTH".equals(riskCode) && "XB2019_F".equals(reqvo.getProductCode())) {
		        try {
		        	httpResult = subMillionMedical2019FimalyUpgradeServiceRenewalOfInsurance.subMillionMedical(reqvo);
		        } catch (Exception e) {
		        	log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("天地关爱百万医疗保险（2019家庭版）（续保）试算失败");
		        }
		        return httpResult;
		    }
		} else {
			if ("ETX".equals(riskCode)) {// 畅行无忧险

			} else if ("WTH".equals(riskCode)) {// 百万医疗保险
				try {
					httpResult = subMillionMedicalService.subMillionMedical(reqvo);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					httpResult.setCode("0");
					httpResult.setMsg("百万医疗险试算失败");
				}
				return httpResult;
			}
		}

		if (flagNocar) {// 新核心 非车险
			reqvo.setEndDate(endDate.toString());
			if ("YEF".equals(riskCode) && "YEF".equals(reqvo.getProductCode())) { // 货运无忧
				httpResult =  newCoreOutageLossService.placeOrder(reqvo);
			}
		} else {

		}

		return httpResult;
	}

	@PostMapping(value = "agentQuery")
	@ApiOperation(value = "查询代理协议信息", notes = "查询代理协议信息", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> agentQuery(@RequestBody AgentQueryReqVO requestVO) throws Exception {
		return HttpResult.success(storeProductPlaceUtilService.agentQuery(requestVO));
	}


}
