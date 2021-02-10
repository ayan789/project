package com.ccic.salesapp.noncar.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.TreeDO;
import com.ccic.salesapp.noncar.dto.TreeDO2;
import com.ccic.salesapp.noncar.dto.request.AccidentPolicyRequest;
import com.ccic.salesapp.noncar.dto.request.Occupation;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElement;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementDetaiReq;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementGroup;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementOption;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanFieldDto;
import com.ccic.salesapp.noncar.dto.request.planelement.ApiMapping;
import com.ccic.salesapp.noncar.dto.request.planelement.BackReasoningFactor;
import com.ccic.salesapp.noncar.dto.request.planelement.BizVo;
import com.ccic.salesapp.noncar.dto.request.planelement.Bottom;
import com.ccic.salesapp.noncar.dto.request.planelement.ClaimStatement;
import com.ccic.salesapp.noncar.dto.request.planelement.CommonProblem;
import com.ccic.salesapp.noncar.dto.request.planelement.CommonProblemContent;
import com.ccic.salesapp.noncar.dto.request.planelement.FieldVo;
import com.ccic.salesapp.noncar.dto.request.planelement.FixedInsureVo;
import com.ccic.salesapp.noncar.dto.request.planelement.FormulaList;
import com.ccic.salesapp.noncar.dto.request.planelement.FormulaObj;
import com.ccic.salesapp.noncar.dto.request.planelement.Header;
import com.ccic.salesapp.noncar.dto.request.planelement.HealthNotice;
import com.ccic.salesapp.noncar.dto.request.planelement.ImgList;
import com.ccic.salesapp.noncar.dto.request.planelement.Insurance;
import com.ccic.salesapp.noncar.dto.request.planelement.InsuredDate;
import com.ccic.salesapp.noncar.dto.request.planelement.LinkElementCode;
import com.ccic.salesapp.noncar.dto.request.planelement.NumOfCopies;
import com.ccic.salesapp.noncar.dto.request.planelement.PbBox;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanButton;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanElementDetailDto;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanFormConstant;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanRate;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanTrialFlag;
import com.ccic.salesapp.noncar.dto.request.planelement.ProductInfo;
import com.ccic.salesapp.noncar.dto.request.planelement.RateDO;
import com.ccic.salesapp.noncar.dto.request.planelement.RateVo;
import com.ccic.salesapp.noncar.dto.request.planelement.Rates;
import com.ccic.salesapp.noncar.dto.request.planelement.RdsRate;
import com.ccic.salesapp.noncar.dto.request.planelement.RelatedPersonInfo;
import com.ccic.salesapp.noncar.dto.request.planelement.SafeguardDetail;
import com.ccic.salesapp.noncar.dto.request.planelement.SafeguardPlan;
import com.ccic.salesapp.noncar.dto.request.planelement.Swipper;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditionsContent;
import com.ccic.salesapp.noncar.dto.request.planelement.Tree;
import com.ccic.salesapp.noncar.dto.request.planelement.Tree2;
import com.ccic.salesapp.noncar.dto.request.planelement.ValueList;
import com.ccic.salesapp.noncar.dto.upload.response.UploadResponse;
import com.ccic.salesapp.noncar.repository.basedb.mapper.InvoiceInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PersonMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalesPlanMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;
import com.ccic.salesapp.noncar.repository.basedb.po.Person;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanInfo;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanFormMapper;
import com.ccic.salesapp.noncar.service.PlanFormService;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salesapp.noncar.service.impl.GroupPlanServiceIml;
import com.ccic.salesapp.noncar.utils.DayCompare;
import com.ccic.salesapp.noncar.utils.DayCompareUtils;
import com.ccic.salesapp.noncar.utils.ExcelRead;
import com.ccic.salesapp.noncar.utils.JsonUtil;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.service.RedisService;
import com.ccic.salessapp.common.utils.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

@RestController
@RequestMapping(value = "planForm")
@Slf4j
public class PlanFormCtl {

	@Autowired
	TPrdPlanFormMapper tPrdPlanFormMapper;

	@Autowired
	PlanFormService planFormService;
	
	@Autowired
	PlanInfoMapper planInfoMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	InvoiceInfoMapper invoiceInfoMapper;
	
	@Autowired
	PlanStrategyService planStrategyService;
	
	@Autowired
    private RedisTemplate redisTemplate;
	
	@Autowired
    SalesPlanMapper salesPlanMapper;
	
	//动态表单
	@PostMapping("/getPlanDynamicForms")
	public HttpResult<?> getPlanDynamicForms(@RequestBody FieldVo fieldVo) {
		List<PlanFieldDto> list2 = new ArrayList<PlanFieldDto>();
		HashMap<String, Object> m_planField = new HashMap<>();
		m_planField.put("strategyId", fieldVo.getStrategyId());
		//方案字段要素
		list2 = tPrdPlanFormMapper.selectPlanField(m_planField);
//		List<Long> plans = planStrategyService.selectPlanIdsByStrategyIdUserCode((long)fieldVo.getStrategyId(), fieldVo.getUserCode());
//		List<PlanFieldDto> list2 = list.stream()
//                .filter(item -> plans.stream().map(e -> e.intValue()).collect(Collectors.toList())
//                        .contains(item.getPlanId())).collect(Collectors.toList());
		for(PlanFieldDto planFieldDto:list2) {
			HttpResult<?> res = buildPlanFields(planFieldDto, fieldVo);
			if(fieldVo.getOrderId()!=null) {
				Order order = orderMapper.selectByPrimaryKey(fieldVo.getOrderId());
				if(order.getPlanId()==planFieldDto.getPlanId()) planFieldDto.setIsDefault(1);
			}
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		}
		return HttpResult.success(list2,1, PlanFormConstant.SUCCESS_MSG);
	}

	//元素组节点
	public HttpResult<?> buildPlanFields(PlanFieldDto planFieldDto, FieldVo fieldVo) {
		HashMap<String, Object> m_order = new HashMap<>();
		m_order.put("orderId", fieldVo.getOrderId());
		m_order.put("planId", planFieldDto.getPlanId());
		int count = tPrdPlanFormMapper.selectOrderByPlanId(m_order);
		//保障期限
		HashMap<String, Object> m_insured_date = new HashMap<>();
		//自定义表单
		if(count==0) {
			m_insured_date.put("planId", planFieldDto.getPlanId());
			InsuredDate insuredDate = tPrdPlanFormMapper.selectInsuredDate2(m_insured_date);
			if(insuredDate==null) insuredDate = new InsuredDate();
			if(insuredDate.getGiPeriodType()!=null) {
				HashMap<String, Object> dateMap = new HashMap<>();
				dateMap.put("strategyId", planFieldDto.getStrategyId());
				HashMap<String, Object> policyStart = tPrdPlanFormMapper.selectPolicyStartRange(dateMap);
				Map<String, String> date = guaranteeInterval(insuredDate.getGiPeriodType(),insuredDate.getGiCoveragePeriod(),Integer.parseInt(policyStart.get("policyStartType")+""));
				insuredDate.setStartDate(date.get("startDate"));
				insuredDate.setEndDate(date.get("endDate"));
				insuredDate.setPolicyStartRange(Integer.parseInt(policyStart.get("policyStartRange")+""));
			}
			planFieldDto.setInsuredDate(insuredDate);
		}
		//复制投保单ssss
		if(count>0) {
			m_insured_date.put("orderId", fieldVo.getOrderId());
			m_insured_date.put("planId", planFieldDto.getPlanId());
			InsuredDate insuredDate = tPrdPlanFormMapper.selectInsuredDate(m_insured_date);
			if(insuredDate==null) insuredDate = new InsuredDate();
			planFieldDto.setInsuredDate(insuredDate);
			HashMap<String, Object> dateMap = new HashMap<>();
			dateMap.put("strategyId", planFieldDto.getStrategyId());
			HashMap<String, Object> policyStart = tPrdPlanFormMapper.selectPolicyStartRange(dateMap);
			insuredDate.setPolicyStartRange(Integer.parseInt(policyStart.get("policyStartRange")+""));
			
			InvoiceInfo invoiceInfo = invoiceInfoMapper.selectInvoiceInfoByOrderId(fieldVo.getOrderId()); 
			if(invoiceInfo!=null)
			planFieldDto.setInvoiceInfo(invoiceInfo);
			String invoiceFlag = tPrdPlanFormMapper.selectOrderInvoiceFlag(fieldVo.getOrderId());
			if(invoiceFlag!=null)
			planFieldDto.setInvoiceFlag(invoiceFlag);
		}
		PlanInfo planInfo = planInfoMapper.selectByPrimaryKey(planFieldDto.getPlanId());
		//关于submitName映射
		fieldVo.setPrdPlanCategory(planInfo.getPrdPlanCategory());
		HttpResult<?> res = buildPlanElementGroups(planFieldDto, fieldVo);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//元素分组
	public HttpResult<?> buildPlanElementGroups(PlanFieldDto planFieldDto, FieldVo fieldVo) {
		HashMap<String, Object> m_elem_group = new HashMap<>();
		m_elem_group.put("planId", planFieldDto.getPlanId());
		//custom_element 自定义投保要素 1:是 0:否
		BizVo biz = tPrdPlanFormMapper.selectPlanCustomElement(m_elem_group);
		if (judgeCustomElement(biz,p->biz.getCustomElement()==1)){
			m_elem_group.put("category", fieldVo.getCategory());
			m_elem_group.put("bizType", "plan");
			m_elem_group.put("bizId", planFieldDto.getPlanId());
		}
		if (judgeCustomElement(biz,p->biz.getCustomElement()!=1)){
			m_elem_group.put("category", fieldVo.getCategory());
			m_elem_group.put("bizType", "strategy");
			m_elem_group.put("bizId", planFieldDto.getStrategyId());
		}
		//检索元素组
		List<PlanElementGroup> planElementGroupList = tPrdPlanFormMapper.selectPlanElementGroup(m_elem_group);
		fieldVo.setPlanId(planFieldDto.getPlanId());
		HttpResult<?> res = buildPlanElements(planElementGroupList, fieldVo);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		planFieldDto.setPlanElementGroup(planElementGroupList);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}
	
	//是否自定义元素
	public boolean judgeCustomElement(BizVo bizVo,Predicate<BizVo> p1) {
	        return p1.test(bizVo);
	}
	 
	//构建元素
	public HttpResult<?> buildPlanElements(List<PlanElementGroup> planElementGroupList, FieldVo fieldVo) {
		for (PlanElementGroup planElementGroup : planElementGroupList) {
			HashMap<String, Object> m_elem = new HashMap<>();
			m_elem.put("groupId", planElementGroup.getGroupId());
			List<PlanElement> planElementList = tPrdPlanFormMapper.selectPlanElement(m_elem);
			HttpResult<?> reshidden =  buildHiddenElement(planElementGroup,planElementList, fieldVo);
			if(judgeHttpResult(reshidden,p->"0".equals(reshidden.getCode()))) return reshidden;
			HttpResult<?> res = buildPlanElementEntry(planElementGroup, planElementList, fieldVo);
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
			HttpResult<?> res1 = buildPlanElementOptions(planElementGroup.getPlanElementList(),fieldVo);
			if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
		}
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}
	
	public HttpResult<?> buildHiddenElement(PlanElementGroup planElementGroup,List<PlanElement> planElementList, FieldVo fieldVo){
		HashMap<String, Object> m_elem = new HashMap<>();
		m_elem.put("groupId", planElementGroup.getGroupId());
		List<PlanElement> phs = tPrdPlanFormMapper.selectElementHidden(m_elem);
	
		for(PlanElement ph:phs) {
			if("applicant".equals(ph.getGroupCode()) && "applicant".equals(planElementGroup.getCode())) {
				HashMap<String, Object> m_api = new HashMap<>();
				m_api.put("elementCode", ph.getCode());
				m_api.put("riskCode", fieldVo.getPrdPlanCategory());
				ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanHiddenElementApiMapping(m_api);
				if (apiMapping != null)
				ph.setSubmitName(apiMapping.getApiNodes());
				planElementList.add(ph);
			}
			else if("insured".equals(ph.getGroupCode()) && "insured".equals(planElementGroup.getCode())) {
				
				HashMap<String, Object> m_api = new HashMap<>();
				m_api.put("elementCode", ph.getCode());
				m_api.put("riskCode", fieldVo.getPrdPlanCategory());
				ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanHiddenElementApiMapping(m_api);
				if (apiMapping != null)
				ph.setSubmitName(apiMapping.getApiNodes());
				planElementList.add(ph);
			}else if("ward".equals(ph.getGroupCode()) && "ward".equals(planElementGroup.getCode())) {
				HashMap<String, Object> m_api = new HashMap<>();
				m_api.put("elementCode", ph.getCode());
				m_api.put("riskCode", fieldVo.getPrdPlanCategory());
				ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanHiddenElementApiMapping(m_api);
				if (apiMapping != null)
				ph.setSubmitName(apiMapping.getApiNodes());
				planElementList.add(ph);
			}else if("keyContacts".equals(ph.getGroupCode()) && "keyContacts".equals(planElementGroup.getCode())) {
				HashMap<String, Object> m_api = new HashMap<>();
				m_api.put("elementCode", ph.getCode());
				m_api.put("riskCode", fieldVo.getPrdPlanCategory());
				ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanHiddenElementApiMapping(m_api);
				if (apiMapping != null)
				ph.setSubmitName(apiMapping.getApiNodes());
				planElementList.add(ph);
			}
			
		}
		
		
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//元素录入
	public HttpResult<?> buildPlanElementEntry(PlanElementGroup planElementGroup, List<PlanElement> planElementList,
			FieldVo fieldVo) {
		List<PlanElement> planElementCopy = new ArrayList<PlanElement>();
		for (PlanElement planElement : planElementList) {
			HashMap<String, Object> m_trialFlag = new HashMap<>();
			m_trialFlag.put("elementCode", planElement.getCode());
			m_trialFlag.put("riskCode", fieldVo.getPrdPlanCategory());
			m_trialFlag.put("groupId", planElementGroup.getGroupId());
			PlanTrialFlag planTrialFlag = tPrdPlanFormMapper.selectPlanTrialFlag(m_trialFlag);
			if(planTrialFlag!=null) planElement.setTrialFlag(1);
			if ("commNumOfCopies".equals(planElement.getCode())) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("planId", fieldVo.getPlanId());
				NumOfCopies numOfCopies = tPrdPlanFormMapper.selectDefaultNumOfCopies(map);
				planElement.setMinValue(numOfCopies.getCopyMin());
				planElement.setMaxValue(numOfCopies.getCopyMax());
			}
			if ("insuredAge".equals(planElement.getCode()) && planElement.getTrialFlag()!=1) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("planId", fieldVo.getPlanId());
				String age = tPrdPlanFormMapper.selectDefaultAge(map);
				map.put("age", age);
				map.put("elementId", planElement.getId());
				tPrdPlanFormMapper.updateElementMinAge(map);
				planElement.setDefaultValue(age);
			}
			else if ("insuredAge".equals(planElement.getCode()) && planElement.getTrialFlag()==1) {
				
				RdsRate rdsRateAge =redisService.getDataFromCacheMap("RdsRateAge",fieldVo.getPlanId()+"",RdsRate.class);
		    	 if(rdsRateAge!=null) {
		    		 planElement.setDefaultValue(rdsRateAge.getRateAgeMin()+"-"+rdsRateAge.getRateAgeMax());
		    	 }
		    	 else {
		    		 PlanRate planRate = new PlanRate();
						HashMap<String, Object> map = new HashMap<>();
						map.put("planId", fieldVo.getPlanId());
						List<HashMap> hashMap = tPrdPlanFormMapper.selectDefaultRateAge(map);
						List<Long> planCoverages = tPrdPlanFormMapper.selectPlanRequiredPlanCoverage(map);
						planRate.setPlanId(fieldVo.getPlanId());
						planRate.setPlanCoverageIds(planCoverages);
						Double fee1 = null ;
						RateVo rateVo2 = new RateVo();
						for(HashMap hm : hashMap) {
							List<RateVo> rate = new ArrayList<RateVo>();
							RateVo rateVo = new RateVo();
							rateVo.setRateAgeMin(Long.parseLong(hm.get("min_code")+""));
							rateVo.setRateAgeMax(Long.parseLong(hm.get("max_code")+""));
							rateVo.setPeriodMin(null);
							rateVo.setPeriodMax(null);
							rateVo.setRateSeatMin(null);
							rateVo.setRateSeatMax(null);
							rate.add(rateVo);
							planRate.setRate(rate);
							HttpResult res = selectPlanRateTrial(planRate);
							if(fee1==null) {
								fee1=(double) res.getData();
								rateVo2.setRateAgeMin(Long.parseLong(hm.get("min_code")+""));
								rateVo2.setRateAgeMax(Long.parseLong(hm.get("max_code")+""));
								rateVo2.setPremium(fee1);
							}
							if((double) res.getData()<fee1) {
								fee1=(double) res.getData();
								rateVo2.setRateAgeMin(Long.parseLong(hm.get("min_code")+""));
								rateVo2.setRateAgeMax(Long.parseLong(hm.get("max_code")+""));
								rateVo2.setPremium(fee1);
							}
						}
						planElement.setDefaultValue(rateVo2.getRateAgeMin()+"-"+rateVo2.getRateAgeMax());
						RdsRate rdsRateAge2 = new RdsRate();
						rdsRateAge2.setPlanId(fieldVo.getPlanId());
						rdsRateAge2.setRateAgeMin(rateVo2.getRateAgeMin());
						rdsRateAge2.setRateAgeMax(rateVo2.getRateAgeMax());
						if(rateVo2.getRateAgeMin()!=null && rateVo2.getRateAgeMax()!=null )
	    	            redisService.putDataToCacheMap("RdsRateAge",fieldVo.getPlanId()+"",rdsRateAge2);
		    	 }
			}
			if ("transSeat".equals(planElement.getCode()) && planElement.getTrialFlag()!=1) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("planId", fieldVo.getPlanId());
				String seats = tPrdPlanFormMapper.selectDefaultSeats(map);
				planElement.setDefaultValue(seats);
			}
			else if("transSeat".equals(planElement.getCode()) && planElement.getTrialFlag()==1) {
				PlanRate planRate = new PlanRate();
				HashMap<String, Object> map = new HashMap<>();
				map.put("planId", fieldVo.getPlanId());
				List<HashMap> hashMap = tPrdPlanFormMapper.selectDefaultRateSeat(map);
				List<Long> planCoverages = tPrdPlanFormMapper.selectPlanRequiredPlanCoverage(map);
				planRate.setPlanId(fieldVo.getPlanId());
				planRate.setPlanCoverageIds(planCoverages);
				Double fee1 = null ;
				RateVo rateVo2 = new RateVo();
				for(HashMap hm : hashMap) {
					List<RateVo> rate = new ArrayList<RateVo>();
					RateVo rateVo = new RateVo();
					rateVo.setRateAgeMin(null);
					rateVo.setRateAgeMax(null);
					rateVo.setPeriodMin(null);
					rateVo.setPeriodMax(null);
					rateVo.setRateSeatMin(Integer.parseInt(hm.get("min_code")+""));
					rateVo.setRateSeatMax(Integer.parseInt(hm.get("max_code")+""));
					rate.add(rateVo);
					planRate.setRate(rate);
					HttpResult res = selectPlanRateTrial(planRate);
					if(fee1==null) {
						fee1=(double) res.getData();
						rateVo2.setRateSeatMin(Integer.parseInt(hm.get("min_code")+""));
						rateVo2.setRateSeatMax(Integer.parseInt(hm.get("max_code")+""));
						rateVo2.setPremium(fee1);
					}
					if((double) res.getData()<fee1) {
						fee1=(double) res.getData();
						rateVo2.setRateSeatMin(Integer.parseInt(hm.get("min_code")+""));
						rateVo2.setRateSeatMax(Integer.parseInt(hm.get("max_code")+""));
						rateVo2.setPremium(fee1);
					}
					
				}
				planElement.setDefaultValue(rateVo2.getRateSeatMin()+"-"+rateVo2.getRateSeatMax());
			}
			if ("commSecurityPeriod".equals(planElement.getCode()) && planElement.getTrialFlag()!=1) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("planId", fieldVo.getPlanId());
				String period = tPrdPlanFormMapper.selectDefaultPeriod(map);
				planElement.setDefaultValue(period);
			}
			else if ("commSecurityPeriod".equals(planElement.getCode()) && planElement.getTrialFlag()==1) {
				RdsRate rdsRatePeriod =redisService.getDataFromCacheMap("RdsRatePeriod",fieldVo.getPlanId()+"",RdsRate.class);
		    	 if(rdsRatePeriod!=null) {
		    		 planElement.setDefaultValue(rdsRatePeriod.getPeriodMin()+"-"+rdsRatePeriod.getPeriodMax());
		    	 }
		    	 else {
						PlanRate planRate = new PlanRate();
						HashMap<String, Object> map = new HashMap<>();
						map.put("planId", fieldVo.getPlanId());
						List<HashMap> hashMap = tPrdPlanFormMapper.selectDefaultRatePeriod(map);
						List<Long> planCoverages = tPrdPlanFormMapper.selectPlanRequiredPlanCoverage(map);
						planRate.setPlanId(fieldVo.getPlanId());
						planRate.setPlanCoverageIds(planCoverages);
						Double fee1 = null ;
						RateVo rateVo2 = new RateVo();
						for(HashMap hm : hashMap) {
							List<RateVo> rate = new ArrayList<RateVo>();
							RateVo rateVo = new RateVo();
							rateVo.setRateAgeMin(null);
							rateVo.setRateAgeMax(null);
							rateVo.setPeriodMin(Long.parseLong(hm.get("min_code")+""));
							rateVo.setPeriodMax(Long.parseLong(hm.get("max_code")+""));
							rateVo.setRateSeatMin(null);
							rateVo.setRateSeatMax(null);
							rate.add(rateVo);
							planRate.setRate(rate);
							HttpResult res = selectPlanRateTrial(planRate);
							if(fee1==null) {
								fee1=(double) res.getData();
								rateVo2.setPeriodMin(Long.parseLong(hm.get("min_code")+""));
								rateVo2.setPeriodMax(Long.parseLong(hm.get("max_code")+""));
								rateVo2.setPremium(fee1);
							}
							if((double) res.getData()<fee1) {
								fee1=(double) res.getData();
								rateVo2.setPeriodMin(Long.parseLong(hm.get("min_code")+""));
								rateVo2.setPeriodMax(Long.parseLong(hm.get("max_code")+""));
								rateVo2.setPremium(fee1);
							}
						}
						planElement.setDefaultValue(rateVo2.getPeriodMin()+"-"+rateVo2.getPeriodMax());
						RdsRate rdsRatePeriod2 = new RdsRate();
						rdsRatePeriod2.setPlanId(fieldVo.getPlanId());
						rdsRatePeriod2.setPeriodMin(rateVo2.getPeriodMin());
						rdsRatePeriod2.setPeriodMax(rateVo2.getPeriodMax());
						if(rateVo2.getPeriodMin()!=null && rateVo2.getPeriodMax()!=null )
						redisService.putDataToCacheMap("RdsRatePeriod",fieldVo.getPlanId()+"",rdsRatePeriod2);
		    	 }
			}
			HashMap<String, Object> m_order = new HashMap<>();
			m_order.put("orderId", fieldVo.getOrderId());
			m_order.put("planId", fieldVo.getPlanId());
			int count = tPrdPlanFormMapper.selectOrderByPlanId(m_order);
			if (count>0) {
				HashMap<String, Object> m_bizVo = new HashMap<>();
				m_bizVo.put("elementId", planElement.getId());
				m_bizVo.put("orderId", fieldVo.getOrderId());
				m_bizVo.put("planId", fieldVo.getPlanId());
				List<BizVo> bizVoList = tPrdPlanFormMapper.selectPlanElementValue(m_bizVo);
				if (bizVoList == null || bizVoList.size()==0){
					planElementCopy.add(planElement);
				}
				else if (bizVoList.size() == 1) {
					planElement.setDefaultValue(bizVoList.get(0).getElementValue());
					planElement.setArrayIndex(bizVoList.get(0).getArrayIndex());
					//调整-2020/06/05 映射核心报文节点
					HashMap<String, Object> m_api = new HashMap<>();
					m_api.put("elementCode", planElement.getCode());
					m_api.put("riskCode", fieldVo.getPrdPlanCategory());
					m_api.put("groupId", planElementGroup.getGroupId());
					ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(m_api);
					if (apiMapping != null)
						planElement.setSubmitName(apiMapping.getApiNodes());
					//调整-2020/06/05 针对不同功能关联节点
					m_api.put("cate", fieldVo.getCategory());
					List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(m_api);
					planElement.setLinkElementCode(linkElementCode);
					planElementCopy.add(planElement);
				} else if (bizVoList.size() > 1) {
					for (int i = 0; i < bizVoList.size(); i++) {
						if (i == 0) {
							planElement.setDefaultValue(bizVoList.get(0).getElementValue());
							//调整-2020/06/05 映射核心报文节点
							HashMap<String, Object> m_api = new HashMap<>();
							m_api.put("elementCode", planElement.getCode());
							m_api.put("riskCode", fieldVo.getPrdPlanCategory());
							m_api.put("groupId", planElementGroup.getGroupId());
							ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(m_api);
							if (apiMapping != null)
								planElement.setSubmitName(apiMapping.getApiNodes());
							planElement.setArrayIndex(bizVoList.get(0).getArrayIndex());
							//调整-2020/06/05 针对不同功能关联节点
							m_api.put("cate", fieldVo.getCategory());
							List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(m_api);
							planElement.setLinkElementCode(linkElementCode);
							planElementCopy.add(planElement);
						} else {
							String objectToJson = JSON.toJSONString(planElement);
							//将Json字符串反序列化为Java对象
							PlanElement planElement2 = JSON.parseObject(objectToJson, PlanElement.class);
							planElement2.setDefaultValue(bizVoList.get(i).getElementValue());
							planElement2.setArrayIndex(bizVoList.get(i).getArrayIndex());
							//调整-2020/06/05 映射核心报文节点
							HashMap<String, Object> m_api = new HashMap<>();
							m_api.put("elementCode", planElement.getCode());
							m_api.put("riskCode", fieldVo.getPrdPlanCategory());
							m_api.put("groupId", planElementGroup.getGroupId());
							ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(m_api);
							if (apiMapping != null)
								planElement.setSubmitName(apiMapping.getApiNodes());
							//调整-2020/06/05 针对不同功能关联节点
							m_api.put("cate", fieldVo.getCategory());
							List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(m_api);
							planElement.setLinkElementCode(linkElementCode);
							planElementCopy.add(planElement2);
						}
					}
				}
			} else {
				//调整-2020/06/05 映射核心报文节点
				HashMap<String, Object> m_api = new HashMap<>();
				m_api.put("elementCode", planElement.getCode());
				m_api.put("riskCode", fieldVo.getPrdPlanCategory());
				m_api.put("groupId", planElementGroup.getGroupId());
				ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(m_api);
				if (apiMapping != null)
					planElement.setSubmitName(apiMapping.getApiNodes());
				//调整-2020/06/05 针对不同功能关联节点
				m_api.put("cate", fieldVo.getCategory());
				List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(m_api);
				planElement.setLinkElementCode(linkElementCode);
				planElementCopy.add(planElement);
			}
		}
		//调整-2020/06/05 重构排序
		//buildSort(planElementCopy);
		planElementGroup.setPlanElementList(planElementCopy);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//排序
	public HttpResult<?> buildSort(List<PlanElement> list) {
		// 排序
		Collections.sort(list, new Comparator<PlanElement>() {
			@Override
			public int compare(PlanElement o1, PlanElement o2) {
				int flag = ("" + o1.getArrayIndex()).compareTo("" + o2.getArrayIndex()); //升降 o2.getName().compareTo(o1.getName())//降序
				if (flag == 0) {
					flag = o1.getSort() - o2.getSort();
				}
				return flag;
			}
		});
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//选择项
	public HttpResult<?> buildPlanElementOptions(List<PlanElement> planElementList, FieldVo fieldVo) {
		for (PlanElement planElement : planElementList) {
			if ("insuredAge".equals(planElement.getCode())) {
				HashMap<String, Object> m_options = new HashMap<>();
				m_options.put("planId", fieldVo.getPlanId());
				List<PlanElementOption> planElementOptionList = tPrdPlanFormMapper.selectAgesOptions(m_options);
				planElement.setPlanElementOptionList(planElementOptionList);
			}
			else if ("commSecurityPeriod".equals(planElement.getCode())) {
				HashMap<String, Object> m_options = new HashMap<>();
				m_options.put("planId", fieldVo.getPlanId());
				List<PlanElementOption> planElementOptionList = tPrdPlanFormMapper.selectPeriodOptions(m_options);
				planElement.setPlanElementOptionList(planElementOptionList);
			}
			else if ("transSeat".equals(planElement.getCode())) {
				HashMap<String, Object> m_options = new HashMap<>();
				m_options.put("planId", fieldVo.getPlanId());
				List<PlanElementOption> planElementOptionList = tPrdPlanFormMapper.selectPeriodSeats(m_options);
				planElement.setPlanElementOptionList(planElementOptionList);
			}
			else if ("select".equals(planElement.getControlType()) || "checkBox".equals(planElement.getControlType())) {
				HashMap<String, Object> m_options = new HashMap<>();
				m_options.put("elementId", planElement.getId());
				List<PlanElementOption> planElementOptionList = tPrdPlanFormMapper.selectPlanElementOption(m_options);
				planElement.setPlanElementOptionList(planElementOptionList);
			}
			else if ("Cascader".equals(planElement.getControlType()) && org.apache.commons.lang3.StringUtils.endsWith(planElement.getCode(), "Area")) {
				List<PlanElementOption> planElementOptionList = new ArrayList<PlanElementOption>();
				Tree addressTree=redisService.getDataFromCacheMap("Address","Area",Tree.class);
				 if(addressTree!=null){
					 PlanElementOption planElementOption = new PlanElementOption();
			         planElementOption.setElementId(planElement.getId());
			         planElementOption.setAddressTree(addressTree.getChildren());
			         planElementOptionList.add(planElementOption);
				 }
		    	 else {
		    		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		    		Tree<TreeDO> tree = planFormService.getTreeList();
		    		PlanElementOption planElementOption = new PlanElementOption();
					planElementOption.setElementId(planElement.getId());
					planElementOption.setAddressTree(tree.getChildren());
					planElementOptionList.add(planElementOption);
					redisService.putDataToCacheMap("Address","Area",tree);
		    	 }
				planElement.setPlanElementOptionList(planElementOptionList);
			}
		}
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	/**产品详情
	复制投保单
	计划书
	*/
	@PostMapping("/getPlanElementDetail")
	public HttpResult<?> getPlanElementDetail(@RequestBody PlanElementDetaiReq planElementDetaiReq) {
			PlanElementDetailDto planElementDetailDto = new PlanElementDetailDto();
			HashMap<String, Object> m_elem_detail = new HashMap<>();
			m_elem_detail.put("strategyId", planElementDetaiReq.getStrategyId());
			m_elem_detail.put("strategyCode", planElementDetaiReq.getProductCode());
			planElementDetailDto = tPrdPlanFormMapper.selectPlanStrategy(m_elem_detail);
			if(planElementDetailDto==null) {
				 return HttpResult.error(2, "产品已下架");
			}
			planElementDetaiReq.setStrategyId(planElementDetailDto.getStrategyId());
			if(planElementDetaiReq.getOrderId()!=null) {
				Order order = orderMapper.selectByPrimaryKey(planElementDetaiReq.getOrderId());
				if(order!=null) planElementDetailDto.setDefaultPlanId(order.getPlanId());
			}
			// 反推
			HttpResult<?> res = buildBackReasoningFactor(planElementDetailDto, planElementDetaiReq);
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
			// 头部信息
			HttpResult<?> res1 = buildHeader(planElementDetailDto, planElementDetaiReq);
			if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
			// 底部信息
			HttpResult<?> res2 = buildBottom(planElementDetailDto, planElementDetaiReq);
			if(judgeHttpResult(res2,p->"0".equals(res2.getCode()))) return res2;
			// 常见问题
			HttpResult<?> res3 = buildCommonProblem(planElementDetailDto, planElementDetaiReq);
			if(judgeHttpResult(res3,p->"0".equals(res3.getCode()))) return res3;
			// 方案列表
			HttpResult<?> res4 = buildFormulaList(planElementDetailDto, planElementDetaiReq);
			if(judgeHttpResult(res4,p->"0".equals(res4.getCode())) || judgeHttpResult(res4,p->"2".equals(res4.getCode()))) return res4;
			return HttpResult.success(planElementDetailDto);
	}

	//反推
	public HttpResult<?> buildBackReasoningFactor(PlanElementDetailDto planElementDetailDto,
		PlanElementDetaiReq planElementDetaiReq) {
		if(planElementDetailDto==null) return HttpResult.success(1, "未配置反推要素");
		if(StringUtils.isNotBlank(planElementDetailDto.getPlanReverseFactor())) {
			//获得反推要素
			List<BackReasoningFactor> backReasoningFactorList = new ArrayList<BackReasoningFactor>();
			//defect 根据Value+Code去匹配所有方案ID
			//因素社保
			if("02".equals(planElementDetailDto.getPlanReverseFactor())) {
				BackReasoningFactor backReasoningFactor1 = new BackReasoningFactor();
				backReasoningFactor1.setFactor(planElementDetailDto.getPlanReverseFactor());
				HashMap<String, Object> map = new HashMap<>();
				map.put("strategyId", planElementDetailDto.getStrategyId());
				backReasoningFactorList = tPrdPlanFormMapper.selectPlanBackReasoningFactor1(map);
				planElementDetailDto.setBackReasoningFactor(backReasoningFactorList);
			}
			//因素保障人数
			if("01".equals(planElementDetailDto.getPlanReverseFactor())) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("strategyId", planElementDetailDto.getStrategyId());
				backReasoningFactorList = tPrdPlanFormMapper.selectPlanBackReasoningFactor2(map);
				planElementDetailDto.setBackReasoningFactor(backReasoningFactorList);
			}
		}
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//方案列表
	public HttpResult<?> buildFormulaList(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		List<FormulaList> formulaList = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		formulaList = tPrdPlanFormMapper.selectPlanInfoFormulas(map);
		List<Long> plans = planStrategyService.selectPlanIdsByStrategyIdUserCode((long)planElementDetaiReq.getStrategyId(), planElementDetaiReq.getUserCode());
		
		List<FormulaList> formulaList2 = formulaList.stream()
                .filter(item -> plans.stream().map(e -> e.intValue()).collect(Collectors.toList())
                        .contains(item.getPlanId())).collect(Collectors.toList());
		if(formulaList2==null || formulaList2.size()==0) {
			 return HttpResult.error(2, "您暂未开放使用权限");
		}
		HttpResult<?> res = buildFormulaDetail(formulaList2,planElementDetailDto,planElementDetaiReq);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		planElementDetailDto.setFormulaList(formulaList2);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//方案详情
	public HttpResult<?> buildFormulaDetail(List<FormulaList> formulaList,PlanElementDetailDto planElementDetailDto,PlanElementDetaiReq planElementDetaiReq) {
		for (FormulaList formula : formulaList) {
			//方案下是否有试算要素
			HashMap<String, Object> m_elem_group = new HashMap<>();
			m_elem_group.put("planId", formula.getPlanId());
			BizVo biz = tPrdPlanFormMapper.selectPlanCustomElement(m_elem_group);
			if (judgeCustomElement(biz,p->biz.getCustomElement()==1)){
				m_elem_group.put("bizType", "plan");
				m_elem_group.put("bizId", formula.getPlanId());
				int count = tPrdPlanFormMapper.selectTrial(m_elem_group);
				if(count>0) formula.setHasTrial("1");
			}
			if (judgeCustomElement(biz,p->biz.getCustomElement()!=1)){
				m_elem_group.put("bizType", "strategy");
				m_elem_group.put("bizId", formula.getStrategyId());
				int count = tPrdPlanFormMapper.selectTrial(m_elem_group);
				if(count>0) formula.setHasTrial("1");
			}
			if(StringUtils.isNotBlank(planElementDetailDto.getScene())){
			    formula.setScene(planElementDetailDto.getScene());
			}
			//保障计划
			HttpResult<?> res = buildSafeguardPlan(formula,planElementDetaiReq);
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
			//产品简介
			HttpResult<?> res1 = buildProductInfo(formula,planElementDetaiReq);
			if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
			//保障详情
			HttpResult<?> res2 = buildSafeguardDetail(formula,planElementDetaiReq);
			if(judgeHttpResult(res2,p->"0".equals(res2.getCode()))) return res2;
			//理赔说明
			HttpResult<?> res3 = buildClaimStatement(formula,planElementDetaiReq);
			if(judgeHttpResult(res3,p->"0".equals(res3.getCode()))) return res3;
			//条款须知
			HttpResult<?> res4 = buildTermsAndConditions(formula,planElementDetaiReq);
			if(judgeHttpResult(res4,p->"0".equals(res4.getCode()))) return res4;
			try {
				HashMap<String, Object> map = new HashMap<>();
				map.put("attachCode", "health_notification");
				map.put("planId", formula.getPlanId());
				List<ImgList> healthList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
				for(ImgList hl:healthList) {
					hl.setImg(attachUrl(hl.getImg()));
				}
				//List<ImgList> healthList = new ArrayList<ImgList>();
				if(healthList!=null && healthList.size()!=0) {
					formula.setIsNeedHealthNotice(true);
					//健康须知
					HttpResult<?> res6 = buildHealthNotice(formula,healthList);
					if(judgeHttpResult(res6,p->"0".equals(res6.getCode()))) return res6;
				}
				else {
					formula.setIsNeedHealthNotice(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//头部信息
	public HttpResult<?> buildHeader(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq) {
		Header header = new Header();
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		header = tPrdPlanFormMapper.selectPlanStrategyHeader(map);
		//轮播图
		HttpResult<?> res = buildSwipper(header, planElementDetailDto);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		planElementDetailDto.setHeader(header);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//产品简介
	public HttpResult<?> buildProductInfo(FormulaList formulaList,PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		ProductInfo productInfo = new ProductInfo();
		productInfo.setTitle(PlanFormConstant.PRODUCT_INTRO);
		productInfo.setDesc("");
		List<ImgList> imgList = new ArrayList<ImgList>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("planId", formulaList.getPlanId());
		map.put("attachType", "product_intro");
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
		productInfo.setContent(imgList);
		formulaList.setProductInfo(productInfo);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//保障详情
	public HttpResult<?> buildSafeguardDetail(FormulaList formulaList,PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		SafeguardDetail safeguardDetail = new SafeguardDetail();
		safeguardDetail.setTitle(PlanFormConstant.SAFEGUARD_DETAIL);
		safeguardDetail.setDesc("");
		List<ImgList> imgList = new ArrayList<ImgList>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("planId", formulaList.getPlanId());
		map.put("attachType", "insure_desc");
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
		safeguardDetail.setContent(imgList);
		formulaList.setSafeguardDetail(safeguardDetail);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//理赔说明
	public HttpResult<?> buildClaimStatement(FormulaList formulaList,PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		ClaimStatement claimStatement = new ClaimStatement();
		claimStatement.setTitle(PlanFormConstant.CLAIM_STATEMENT);
		claimStatement.setDesc("");
		List<ImgList> imgList = new ArrayList<ImgList>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("planId", formulaList.getPlanId());
		map.put("attachType", "claim_guide");
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
		claimStatement.setContent(imgList);
		formulaList.setClaimStatement(claimStatement);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//常见问题
	public HttpResult<?> buildCommonProblem(PlanElementDetailDto planElementDetailDto,
		PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		CommonProblem commonProblem = new CommonProblem();
		commonProblem.setTitle(PlanFormConstant.COMMON_PROBLEM);
		commonProblem.setDesc("");
		// 常见问题内容
		HttpResult<?> res = buildCommonProblemContent(commonProblem, planElementDetaiReq);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		planElementDetailDto.setCommonProblem(commonProblem);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//条款须知
	public HttpResult<?> buildTermsAndConditions(FormulaList formulaList,PlanElementDetaiReq planElementDetaiReq) {
		TermsAndConditions termsAndConditions = new TermsAndConditions();
		termsAndConditions.setTitle(PlanFormConstant.ITEMS_NOTICE_TITIE);
		termsAndConditions.setDesc("");
		// 条款须知内容
		buildTermsAndConditionsContent(termsAndConditions,formulaList);
		formulaList.setTermsAndConditions(termsAndConditions);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}
	
	//展业夹图:第一张轮播图
	public String buildZyjImg(PlanElementDetailDto planElementDetailDto) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		Header header = tPrdPlanFormMapper.selectPlanStrategyHeader(map);
		if(header!=null) {
			List<Swipper> swipperList = new ArrayList<Swipper>();
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("strategyId", planElementDetailDto.getStrategyId());
			swipperList = tPrdPlanFormMapper.selectHeaderSwipper(map2);
			return swipperList.get(0).getValue();
		}
		return null;
	}

	//底部按钮
	public HttpResult<?> buildBottom(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq) {
		PlanButton planButton = buttonShow(planElementDetaiReq);
		if(planButton==null) return HttpResult.error(0, PlanFormConstant.BUTTON_INSU_ERR_MSG);
		List<Bottom> bottomList = new ArrayList<Bottom>();
		Bottom bottom1 = new Bottom();
		bottom1.setId(1);
		bottom1.setType("text");
		bottom1.setDesc(PlanFormConstant.PREMIUM);
		bottomList.add(bottom1);
		if (planElementDetaiReq.getCode().equals("compute")
				&& (1 == planButton.getPoster() || 1 == planButton.getProposal())) {
			Bottom bottom2 = new Bottom();
			bottom2.setId(2);
			bottom2.setType(PlanFormConstant.BUTTON);
			bottom2.setDesc(PlanFormConstant.EXHIBITION_FOLDER);
			List<PbBox> pbBoxList = new ArrayList<PbBox>();
			if (1 == planButton.getPoster()) {
				PbBox pbBo1 = new PbBox();
				pbBo1.setCode("poster");
				pbBo1.setName(PlanFormConstant.TO_SHARE_POSTERS);
				String img = buildZyjImg(planElementDetailDto);
				if(img!=null) pbBo1.setImgUrl(img);	
				pbBoxList.add(pbBo1);
			}
			if (1 == planButton.getProposal()) {
				PbBox pbBo2 = new PbBox();
				pbBo2.setCode("prospectus");
				pbBo2.setName(PlanFormConstant.MAKE_PROSPECTUS);
				String img = buildZyjImg(planElementDetailDto);
				if(img!=null) pbBo2.setImgUrl(img);	
				pbBoxList.add(pbBo2);
			}
			bottom2.setPbBox(pbBoxList);
			bottomList.add(bottom2);
		}
		if (planElementDetaiReq.getCode().equals("compute")) {
			Bottom bottom3 = new Bottom();
			bottom3.setId(3);
			bottom3.setType(PlanFormConstant.BUTTON);
			bottom3.setDesc(PlanFormConstant.TO_POLICY);
			bottomList.add(bottom3);
		} else if (planElementDetaiReq.getCode().equals("prospectus")) {
			Bottom bottom3 = new Bottom();
			bottom3.setId(3);
			bottom3.setType(PlanFormConstant.BUTTON);
			bottom3.setDesc(PlanFormConstant.TO_PROSPECTUS);
			bottomList.add(bottom3);
		}
		planElementDetailDto.setBottom(bottomList);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//健康须知
	public HttpResult<?> buildHealthNotice(FormulaList formulaList,List<ImgList> healthList) {
		for(ImgList health:healthList) {
			HealthNotice healthNotice = new HealthNotice();
			healthNotice.setTitle(PlanFormConstant.HEALTH_NOTICE_TITIE);
			healthNotice.setUrl(health.getImg());
			healthNotice.setInUrl(health.getImgIn());
			healthNotice.setContent("");
			formulaList.setHealthNotice(healthNotice);
		}
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//条款须知内容
	public HttpResult<?> buildTermsAndConditionsContent(TermsAndConditions terms,FormulaList form) {
		// defect 产品详情与计划书差异化展示
		List<TermsAndConditionsContent> contentList = new ArrayList<TermsAndConditionsContent>();
		List<TermsAndConditionsContent> list = new ArrayList<TermsAndConditionsContent>();
		HashMap<String, Object> m = new HashMap<>();
		m.put("planId", form.getPlanId());
		List<ImgList> imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(m);
		
		TermsAndConditionsContent c_terms7 = new TermsAndConditionsContent();
		c_terms7.setId(7);
		c_terms7.setTitle(PlanFormConstant.IMPORTANCE_HINT_TITIE);
		m.put("attachCode", PlanFormConstant.IMPORTANCE_HINT);
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(m);
		if(imgList != null && imgList.size() > 0) {
			c_terms7.setLink(attachUrl(imgList.get(0).getImg()));
			contentList.add(c_terms7);
		}
		terms.setContent(contentList);
		
		TermsAndConditionsContent c_terms2 = new TermsAndConditionsContent();
		c_terms2.setId(2);
		c_terms2.setTitle(PlanFormConstant.INSURANCE_NOTICE_TITIE);
		m.put("attachCode", PlanFormConstant.INSURANCE_NOTICE);
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(m);
		if(imgList != null && imgList.size() > 0) {
			c_terms2.setLink(attachUrl(attachUrl(imgList.get(0).getImg())));
			contentList.add(c_terms2);
		}
		
		TermsAndConditionsContent c_terms5 = new TermsAndConditionsContent();
		c_terms5.setId(5);
		c_terms5.setTitle(PlanFormConstant.EXEMPTION_LIABILITY_TITIE);
		m.put("attachCode", PlanFormConstant.EXEMPTION_LIABILITY);
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(m);
		if(imgList != null && imgList.size() > 0) {
			c_terms5.setLink(attachUrl(imgList.get(0).getImg()));
			contentList.add(c_terms5);
		}
		
		list = tPrdPlanFormMapper.selectConditionsContent(m);
		if(list!=null && list.size()!=0) {
			TermsAndConditionsContent c_terms1 = new TermsAndConditionsContent();
			c_terms1.setId(1);
			c_terms1.setTitle(PlanFormConstant.PRODUCT_CLAUSE_TITIE);
			c_terms1.setLink("");
			for(TermsAndConditionsContent terms1 :list) {
				terms1.setLink(attachUrl(terms1.getLink()));
			}
			c_terms1.setChlidren(list);
			contentList.add(c_terms1);
		}
		
		TermsAndConditionsContent c_terms3 = new TermsAndConditionsContent();
		c_terms3.setId(3);
		c_terms3.setTitle(PlanFormConstant.INSURANCE_DECLARATION_TITIE);
		m.put("attachCode", PlanFormConstant.INSURANCE_DECLARATION);
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(m);
		if(imgList != null && imgList.size() > 0) {
			c_terms3.setLink(attachUrl(imgList.get(0).getImg()));
			contentList.add(c_terms3);
		}
		TermsAndConditionsContent c_terms4 = new TermsAndConditionsContent();
		c_terms4.setId(4);
		c_terms4.setTitle(PlanFormConstant.IMPORTANT_CLAUSE_TITIE);
		m.put("attachCode", PlanFormConstant.IMPORTANT_CLAUSE);
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(m);
		if(imgList != null && imgList.size() > 0) {
			c_terms4.setLink(attachUrl(imgList.get(0).getImg()));
			contentList.add(c_terms4);
		}
		
		TermsAndConditionsContent c_terms6 = new TermsAndConditionsContent();
		c_terms6.setId(6);
		c_terms6.setTitle(PlanFormConstant.OCCUPATION_TYPE_TITLE);
		m.put("attachCode", PlanFormConstant.OCCUPATION_TYPE);
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(m);
		if(imgList != null && imgList.size() > 0) {
			c_terms6.setLink(attachUrl(imgList.get(0).getImg()));
			contentList.add(c_terms6);
		}
		terms.setContent(contentList);
		
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//常见问题内容
	public HttpResult<?> buildCommonProblemContent(CommonProblem comm, PlanElementDetaiReq plane) {
		List<CommonProblemContent> contentList = new ArrayList<CommonProblemContent>();
		HashMap<String, Object> m = new HashMap<>();
		m.put("strategyId", plane.getStrategyId());
		contentList = tPrdPlanFormMapper.selectCommonProblemContent(m);
		comm.setContent(contentList);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//轮播图
	public HttpResult<?> buildSwipper(Header header, PlanElementDetailDto plane) {
		List<Swipper> swip = new ArrayList<Swipper>();
		HashMap<String, Object> m = new HashMap<>();
		m.put("strategyId", plane.getStrategyId());
		swip = tPrdPlanFormMapper.selectHeaderSwipper(m);
		header.setSwipper(swip);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//保障计划
	public HttpResult<?> buildSafeguardPlan(FormulaList form,PlanElementDetaiReq plane) {
		//详情与计划书一致
		SafeguardPlan safe = new SafeguardPlan();
		safe.setTitle(PlanFormConstant.SAFEGUARDPLAN);
		safe.setDesc("");
		//保障计划
		HttpResult<?> res = buildFormulaObj(safe, form,plane);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		//关系人信息
		HttpResult<?> res1 = buildRelatedPersonInfo(safe,plane, form);
		if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
		form.setSafeguardPlan(safe);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//保障计划
	public HttpResult<?> buildFormulaObj(SafeguardPlan safe, FormulaList form,PlanElementDetaiReq plane) {
	    
	    FormulaObj formulaObj = new FormulaObj();
		//责任条款列表
		List<Insurance> insu = new ArrayList<>();
		
		HashMap<String, Object> m = new HashMap<>();
		m.put("planId", form.getPlanId());
		insu = tPrdPlanFormMapper.selectPlanCt(m);
		/*备
		Tree<RateDO>  tree = planFormService.getTree(map);
		//条款
		String tk="";
		//条款id
		long pid = 0;
		buildRateNodes(tree,pid,tk,insuranceList);
		System.out.println(JSONObject.toJSONString(insuranceList));
		*/
		//必选责任条款
		HttpResult<?> res = buildMainInsurance(formulaObj,form,insu,plane);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		//可选责任条款
		HttpResult<?> res1 = buildSubsidiaryInsurance(formulaObj, form,insu,plane);
		if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
		
		safe.setFormulaObj(formulaObj);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//关系人信息
	public HttpResult<?> buildRelatedPersonInfo(SafeguardPlan safe,PlanElementDetaiReq plane, FormulaList forml) {
		RelatedPersonInfo relatedPersonInfo = new RelatedPersonInfo();
		// 动态表单获取
		PlanFieldDto planf = new PlanFieldDto();
		planf.setStrategyId(forml.getStrategyId());
		planf.setPlanId(forml.getPlanId());
		FieldVo fiel = new FieldVo();
		fiel.setPlanId(forml.getPlanId());
		fiel.setCategory(plane.getCode());
		fiel.setOrderId(plane.getOrderId());
		
		HttpResult<?> res = buildPlanFields(planf, fiel);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		//优先取plan里的元素，plan没有去销售方案下的元素 标识 plan_info custom_element
		relatedPersonInfo.setForm(planf.getPlanElementGroup());
		safe.setRelatedPersonInfo(relatedPersonInfo);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//主条款
	public HttpResult<?> buildMainInsurance(FormulaObj formuo, FormulaList forml,List<Insurance> insu,PlanElementDetaiReq plane) {
		List<Insurance> mainInsuranceList = new ArrayList<>();
//		必须责任
		for(Insurance insurance:insu) {
//			if(insurance.getPid()!=-1) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("planCoverageId", insurance.getId());
				m.put("planId", forml.getPlanId());
				int isRequired = 0 ;
				try {
					isRequired = tPrdPlanFormMapper.selectPlanRateRequired(m);
				} catch (Exception e) {
					return HttpResult.error(0, PlanFormConstant.MAIN_INSU_ERR_MSG);
				}
				if(isRequired>0) mainInsuranceList.add(insurance);
//			}
		}
		/*for(Insurance insurance:mainInsuranceList) {
			责任基本信息
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("planCoverageId", insurance.getId());
			map1.put("planId", formulaList.getPlanId());
			Insurance insurance2 = new Insurance();
			insurance2 = tPrdPlanFormMapper.selectPlanCt(map1);
			insurance.setAmount(insurance2.getAmount());
			insurance.setDesc(insurance2.getDesc());
			insurance.setUnit(insurance2.getUnit());
			insurance.setTitle(insurance2.getTitle());
			insurance.setKindCode(insurance2.getKindCode());
			insurance.setFixedInsure(insurance2.getFixedInsure());
			insurance.setInsureAmount(insurance2.getInsureAmount());
			责任条款
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("planCoverageId", insurance.getPid());
			map2.put("planId", formulaList.getPlanId());
			Insurance insurance3 = new Insurance();
			insurance3 = tPrdPlanFormMapper.selectPlanCt(map2);
			insurance.setClauseCode(insurance3.getKindCode());
		}*/
		for (int i = 0; i < mainInsuranceList.size(); i++) {
			mainInsuranceList.get(i).setInsuranceType("1");
			mainInsuranceList.get(i).setType("collage");
			mainInsuranceList.get(i).setDefaultValue("1");
			mainInsuranceList.get(i).setIsRequired(true);
			//定额定费 限额限费 标示 ct fixed_insure 固定：1 查费率表， 限费：0 固定保费
			if (mainInsuranceList.get(i).getFixedInsure() == 1) {
				mainInsuranceList.get(i).setFixedInsure(0);
			}
			else if (mainInsuranceList.get(i).getFixedInsure() == 0)
				mainInsuranceList.get(i).setFixedInsure(1);
		}
		formuo.setMainInsurance(mainInsuranceList);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//附加条款
	public HttpResult<?> buildSubsidiaryInsurance(FormulaObj formo, FormulaList forml,List<Insurance> insu,PlanElementDetaiReq plane) {
		//非必选责任
		List<Insurance> subsidiaryInsuranceList = new ArrayList<>();
		for(Insurance insurance:insu) {
//			if(insurance.getPid()!=-1) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("planCoverageId", insurance.getId());
				m.put("planId", forml.getPlanId());
				int isRequired = 0 ;
				try {
					isRequired = tPrdPlanFormMapper.selectPlanRateRequired(m);
				} catch (Exception e) {
					return HttpResult.error(0, PlanFormConstant.SUBS_INSU_ERR_MSG);
				}
				if(isRequired==0) subsidiaryInsuranceList.add(insurance);
//			}
		}
		for(Insurance insurance:subsidiaryInsuranceList) {
			/*责任基本信息
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("planCoverageId", insurance.getId());
			map1.put("planId", formulaList.getPlanId());
			insurance = tPrdPlanFormMapper.selectPlanCt(map1);
			Insurance insurance2 = new Insurance();
			insurance2 = tPrdPlanFormMapper.selectPlanCt(map1);
			insurance.setAmount(insurance2.getAmount());
			insurance.setDesc(insurance2.getDesc());
			insurance.setUnit(insurance2.getUnit());
			insurance.setTitle(insurance2.getTitle());
			insurance.setClauseCode(insurance2.getClauseCode());
			insurance.setKindCode(insurance2.getKindCode());
			insurance.setFixedInsure(insurance2.getFixedInsure());
			insurance.setInsureAmount(insurance2.getInsureAmount());
			责任条款
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("planCoverageId", insurance.getPid());
			map2.put("planId", formulaList.getPlanId());
			Insurance insurance3 = new Insurance();
			insurance3 = tPrdPlanFormMapper.selectPlanCt(map2);
			insurance.setClauseCode(insurance3.getKindCode());*/
			//责任默认值
			if(plane.getOrderId()!=null) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("orderId", plane.getOrderId());
				m.put("code", insurance.getKindCode());
				int i = tPrdPlanFormMapper.selectPlanRateSelected(m);
				if(i>0) insurance.setDefaultValue("1");
			}
		}
		for (int i = 0; i < subsidiaryInsuranceList.size(); i++) {
			subsidiaryInsuranceList.get(i).setInsuranceType("0");
			subsidiaryInsuranceList.get(i).setType("checkBox");
			subsidiaryInsuranceList.get(i).setIsRequired(true);
			//选项值
			HttpResult<?> res = buildValueList(subsidiaryInsuranceList.get(i));
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
			if (subsidiaryInsuranceList.get(i).getFixedInsure() == 1) {
				subsidiaryInsuranceList.get(i).setFixedInsure(0);
			}
			else if (subsidiaryInsuranceList.get(i).getFixedInsure() == 0)
				subsidiaryInsuranceList.get(i).setFixedInsure(1);
		}
		formo.setSubsidiaryInsurance(subsidiaryInsuranceList);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//选项值
	public HttpResult<?> buildValueList(Insurance insu) {
		List<ValueList> v = new ArrayList<ValueList>();
		ValueList v1 = new ValueList();
		v1.setId(1);
		v1.setValue(true);
		ValueList v2 = new ValueList();
		v2.setId(0);
		v2.setValue(false);
		v.add(v1);
		v.add(v2);
		insu.setValueList(v);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//费率
	public HttpResult<?> buildRates(Insurance insu, FormulaList form) {
		HashMap<String, Object> m = new HashMap<>();
		m.put("strategyId", form.getStrategyId());
		String codes = tPrdPlanFormMapper.selectPlanRateCode(m);
		m.put("codes", codes);
		m.put("planId", form.getPlanId());
		m.put("planCoverageId", insu.getId());
		List<Rates> ratesList = new ArrayList<Rates>();
		try {
			if(codes.indexOf("insuredAge") != -1 )
				ratesList = tPrdPlanFormMapper.selectPlanRateMapping(m);
			if(codes.indexOf("commSecurityPeriod") != -1 )
				ratesList = tPrdPlanFormMapper.selectPlanRateMapping2(m);
		} catch (Exception e) {
			return HttpResult.error(0, PlanFormConstant.RATE_ERR_MSG);
		}
		insu.setRates(ratesList);
		return HttpResult.success(1, PlanFormConstant.SUCCESS_MSG);
	}

	//底部按钮
	public PlanButton buttonShow(PlanElementDetaiReq plane) {
		HashMap<String, Object> m = new HashMap<>();
		m.put("strategyId", plane.getStrategyId());
		PlanButton planb = tPrdPlanFormMapper.selectButton(m);
		return planb;
	}
	
	//错误返回码0
	public boolean judgeHttpResult(HttpResult<?> httpResult,Predicate<HttpResult<?>> p) {
	        return p.test(httpResult);
	}
	
	//责任节点信息
	public void buildRateNodes(Tree<RateDO> nodes,Long pid,String tk,List<Insurance> list) {
		if(nodes.isHasChildren()) {
			if(0==nodes.getParentId()){
				tk=nodes.getText();
				pid=nodes.getId();
			}
			List<Tree<RateDO>> rateList = nodes.getChildren();
			for(Tree<RateDO> rate:rateList) {
				if(!rate.isHasChildren()){
					Insurance rateVo = new Insurance();
					rateVo.setClauseName(tk);
					rateVo.setPid(pid);
					rateVo.setKindName(rate.getText());
					rateVo.setId(rate.getId());
					list.add(rateVo);
				}
				buildRateNodes(rate,pid,tk,list);
			}
		}
	}
	
	//保障区间
	private Map<String,String> guaranteeInterval(int giPeriodType,int giCoveragePeriod,int n) {
		Date d=new Date();//取时间
	    Calendar calendar = new GregorianCalendar(); 
	    calendar.setTime(d); 
	    //calendar.add(calendar.DATE,giCoveragePeriod); 
	    calendar.add(calendar.DATE, n);
	    d=calendar.getTime(); 
	    Map<String,String> m = new HashMap<String, String>();
	    try {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 	    String s = sdf.format(d);
			//d =  sdf.parse(s);
	 	    m.put("startDate", s+" 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    if(giPeriodType==1) {
	    	calendar.add(calendar.YEAR, giCoveragePeriod);
	    	//结束日期默认减去一天
	    	
	    	calendar.add(calendar.DATE, -1);
	    	calendar.set(Calendar.HOUR_OF_DAY, 23);
	    	calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 59);
	    	d=calendar.getTime();
	    	try {
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	    String s = sdf.format(d);
				//d =  sdf.parse(s);
		 	    m.put("endDate", s);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    if(giPeriodType==2) {
	    	calendar.add(Calendar.MONTH, giCoveragePeriod);
	    	calendar.add(calendar.DATE, -1);
	    	calendar.set(Calendar.HOUR_OF_DAY, 23);
	    	calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 59);
	    	d=calendar.getTime();
	    	try {
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	    String s = sdf.format(d);
				//d =  sdf.parse(s);
				m.put("endDate", s);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    if(giPeriodType==3) {
	    	calendar.add(calendar.DATE, giCoveragePeriod);
	    	calendar.add(calendar.DATE, -1);
	    	calendar.set(Calendar.HOUR_OF_DAY, 23);
	    	calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 59);
	    	d=calendar.getTime();
	    	try {
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	    String s = sdf.format(d);
				//d =  sdf.parse(s);
				m.put("endDate", s);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    return m;
	}
	
	@PostMapping("/trial")
	public HttpResult<?> selectPlanRateTrial(@RequestBody PlanRate planr) {
			double fee = 0;
			List<Long> planc = new ArrayList<Long>();
			//限额限费
			for(Long planCoverageId:planr.getPlanCoverageIds()) {
				HashMap<String, Object> map_f = new HashMap<>();
				map_f.put("planId", planr.getPlanId());
				map_f.put("planCoverageId", planCoverageId);
				FixedInsureVo fixedInsure = tPrdPlanFormMapper.selectPlanInsureFixed(map_f);
				int count  = tPrdPlanFormMapper.selectRateByPlanCoverage(map_f);
				if(fixedInsure!=null && fixedInsure.getFixedInsure()==0) fee=fee+fixedInsure.getInsureAmount();
				else if (count==0) {
					Double premium = tPrdPlanFormMapper.selectPremiumByPlanCoverage(map_f);
					if(premium==null) premium=0d;
					fee=fee+premium;
				}
				else if(fixedInsure!=null && fixedInsure.getFixedInsure()==1) planc.add(planCoverageId);
			}
			//获取费率
			for(RateVo rateVo : planr.getRate()) {
				HashMap<String, Object> map_r = new HashMap<>();
				map_r.put("planCoverageIds", planc);
				map_r.put("planId", planr.getPlanId());
				map_r.put("rateAgeMin", rateVo.getRateAgeMin());
				map_r.put("rateAgeMax", rateVo.getRateAgeMax());
				map_r.put("periodMin", rateVo.getPeriodMin());
				map_r.put("periodMax", rateVo.getPeriodMax());
				map_r.put("rateSeatMin", rateVo.getRateSeatMin());
				map_r.put("rateSeatMax", rateVo.getRateSeatMax());
				if(rateVo.getRateAgeMin()==null && rateVo.getRateAgeMax()==null
						&& rateVo.getPeriodMin()==null && rateVo.getPeriodMin()==null
						&& rateVo.getRateSeatMin()==null && rateVo.getRateSeatMax()==null) {
				}
				else if(planc!=null && planc.size()!=0) {
					Double rateFee = tPrdPlanFormMapper.selectPlanRateFee(map_r);
					if(rateFee==null) {
						Double premium = tPrdPlanFormMapper.selectPremiumByPlanCoverage(map_r);
						if(premium==null) premium=0d;
						fee=fee+premium;
					}
					else {
						fee = fee+rateFee;
					}
				}
			}
			return HttpResult.success(fee,1, PlanFormConstant.PREMIUM_SUCCESS_MSG);
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
	
	@PostMapping("/planIds")
	public HttpResult<?> planIds(@RequestBody PlanElementDetaiReq planElementDetaiReq) {
		List<Long> plans = planStrategyService.selectPlanIdsByStrategyIdUserCode((long)planElementDetaiReq.getStrategyId(), planElementDetaiReq.getUserCode());
		return HttpResult.success(plans,1, "OK");
	}
	
	@PostMapping(value = "/menu")
    public HttpResult<?> menu(@RequestBody PlanElementDetaiReq planElementDetaiReq){
    	Tree2<TreeDO2>  tree2 = planFormService.OccupationTreeList();
		return HttpResult.success(tree2.getChildren(),1, "OK");
    }
	
	@Value("${esb.address.policydetailservicerest}")
	String  policydetailservicerest;
	
	@PostMapping(value = "/policyInfo")
    public HttpResult<?> policyInfo(@RequestBody AccidentPolicyRequest requestBody){
		 Request request = new Request();
		 RequestHead requestHead = new RequestHead();
		 requestHead.setConsumerID("supsale");
//		 AccidentPolicyRequest requestBody = new AccidentPolicyRequest();
//		 requestBody.setPolicyNo("PWTN20430104070012400005");//意健险 7
//		 requestBody.setQueryType("7");
		 com.ccic.salesapp.noncar.dto.request.Request requests = new com.ccic.salesapp.noncar.dto.request.Request();
		 requests.setRequestHead(requestHead);
		 requests.setRequestBody(requestBody);
		 String reqJson = net.sf.json.JSONObject.fromObject(requests, jsonConfig).toString();
		 System.out.println("调用保单信息查询接口开始，请求报文：" + reqJson);
		 String resJson = null;
		 try {
		     resJson = postJsonRequest(reqJson, policydetailservicerest);
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		System.out.println("调用保单信息查询接口结束，响应报文：" + resJson);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = null;
		try {
			actualObj = mapper.readTree(resJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 JsonNode jsonNode1 = actualObj.get("responseBody").get("policyElementString");
		 
		 if(jsonNode1==null) {
			 return HttpResult.error(0, "未查询到续保信息");
		 }
		 String invoiceInfoListStr = jsonNode1.get("invoiceInfoList").get(0).toString();
		 //List<com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo> invoiceInfos = JSONObject.parseArray(invoiceInfoListStr,com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo.class);
		 InvoiceInfo invoiceInfo = JSONObject.parseObject(invoiceInfoListStr,InvoiceInfo.class);
		 System.out.println(JSONObject.toJSONString(invoiceInfo));
		 
		 JsonUtil jsonUtil = new JsonUtil();
		 Map<String,Object> map = jsonUtil.listNames("", jsonNode1);
		 Map<String,Object> mapDTO = new HashMap<String, Object>();
		 mapDTO.put("submitNames", map);
		 mapDTO.put("invoiceInfo", invoiceInfo);
		 JSONObject json = JSONObject.parseObject(resJson);
		 JSONObject jsonRes =  json.getJSONObject("responseBody");
		 JSONObject jsonPolicy =  jsonRes.getJSONObject("policyElementString");
		 
		 mapDTO.put("policyInfo", jsonPolicy);
		    //续保责任映射
	        Calendar cal = Calendar.getInstance();
	        int year = cal.get(Calendar.YEAR);
	        HashMap<String,Object> mapReq = new HashMap<String, Object>();
	        mapReq.put("year", year);
	        List<HashMap<String,Object>> list = salesPlanMapper.selectRenewalCoverageMapping(mapReq);
	        List<String> listStr = new ArrayList<String>();
	        if(jsonPolicy!=null && ("WTH".equals(jsonPolicy.get("productCode").toString()) || "WTN".equals(jsonPolicy.get("productCode").toString()))) {
	            //System.out.println(JSONObject.toJSONString(jsonPolicy));
	            JSONArray policyLobListJson = (JSONArray)jsonPolicy.get("policyLobList");
	            //System.out.println(JSONObject.toJSONString(policyLobListJson));
	            JSONObject policyLobListJsonObj = policyLobListJson.getJSONObject(0);
	            JSONArray policyRiskListJson = (JSONArray)policyLobListJsonObj.get("policyRiskList");
	            JSONObject policyRiskListJsonObj = policyRiskListJson.getJSONObject(0);
	            JSONArray insuredGroupListJson = (JSONArray)policyRiskListJsonObj.get("insuredGroupList");
	            JSONObject insuredGroupListJsonObj = insuredGroupListJson.getJSONObject(0);
	            JSONArray json8 = (JSONArray)insuredGroupListJsonObj.get("policyCoverageList");
	            for(int i=0;i<json8.size();i++) {
	               // System.out.println(json8.getJSONObject(i).get("kindCode"));
	               // System.out.println(json8.getJSONObject(i).get("isMainCoverage"));
	                listStr.add(json8.getJSONObject(i).get("kindCode")+"");
	            }
	        }
	        List<HashMap<String,Object>> renewaList = new ArrayList<HashMap<String,Object>>();
	        if(listStr!=null && listStr.size()>0) {
	            for(HashMap<String,Object> renewalMap : list) {
	                for(String renewalSte : listStr) {
	                    if(renewalSte.equals(renewalMap.get("renewalCoverageCode").toString())) {
	                        HashMap<String,Object> dmap = new HashMap<String, Object>();
	                        dmap.put("renewalCoverageCode", renewalMap.get("renewalCoverageCode").toString());
	                        dmap.put("coverageCode", renewalMap.get("coverageCode").toString());
	                        renewaList.add(renewalMap);
	                    }
	                }
	            }
	        }
        mapDTO.put("renewaCoverageList", renewaList);   
		return HttpResult.success(mapDTO,1, "OK");
    }
	
	/**
     * 发送post请求
     *
     * @param reqJson :请求的字符串
     * @param uri :请求路径
     * @param charset :编码
     * @return
     * @throws Exception
     */
    public String postJsonRequest(String reqJson, String uri, String charset) throws Exception {
        HttpClient  client=new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
        client.getHttpConnectionManager().getParams().setSoTimeout(60000);
        client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("mobile", "mobile"));
        System.out.println("##########请求地址：" +uri);
        PostMethod method=new PostMethod(uri);
        StringBuffer response= new StringBuffer();
        try {
            method.setRequestHeader("Content-Type", "application/json");
            RequestEntity entity = new StringRequestEntity(reqJson,"application/json",charset);
            method.setRequestEntity(entity);
            int statusCode = client.executeMethod(method);
            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            //log.info("调用核心---返回报文： resp{"+response+"}");
            if (statusCode != HttpStatus.SC_OK) {
                throw new Exception("Method failed: " + method.getStatusLine());
            }
        } catch (Exception e) {
        	System.out.println("http请求异常："+e);
            throw e;
        }
        return response.toString(); // 返回
    }
    
    public String postJsonRequest(String reqJson, String uri) throws Exception {
        return postJsonRequest(reqJson,uri,"utf-8");
    }
    
    public static JsonConfig jsonConfig = new JsonConfig();

	static {
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				return value == null;
			}

		});
		jsonConfig.addIgnoreFieldAnnotation(Request.class);

	}
	
	@PostMapping(value = "/push")
    public HttpResult<?> iii(MultipartFile file) throws IOException{
		ExcelRead excelRead = new ExcelRead();
		List<ArrayList<String>> datas = excelRead.readExcel(file);
		List<Occupation> list = insertBatch(datas);
		for(Occupation category:list) {
			tPrdPlanFormMapper.insertCategory(category);
		}
		System.out.println("ok");
		return HttpResult.success(datas,1, "ok");
    }
	
	 public List<Occupation> insertBatch(List<ArrayList<String>> datas)
	    {
			//定义主键id 对应数据库中cat_id
			//String index = "1";
		    String index = null;
			String parentId = null;
			String levelName = null;//分类名称
			// tempMap为临时变量,记录那些已经添加过了 key为CatName+层级 value 为记录对象,例如
			// <Home_1,category> 表示存储第一级对象
			Map<String, Occupation> tempMap = new HashMap<String, Occupation>();
			// 记录最终需要插入数据库的数据
			List<Occupation> insertDatas = new ArrayList<Occupation>();
			Occupation level = null;
			//int allCols = datas.get(0).size();//获取导入数据的列数
			int allCols = 2;
			for (int column = 0; column < allCols; column++)
			{
			    for (int i = 0; i < datas.size(); i++)
			    {
			        List<String> rows = datas.get(i);
			        //设置层级的关联关系
			        if (column == 0)
			        {
			            // 如果是第一列,parentid 默认为0
			            parentId = "0";
			        }
			        else
			        {
			            // 如果不是一列,则在tempMap中寻找对应的父类id作为parentid,当前列向后退一列
			        	System.out.println(tempMap.get(rows.get(column - 1) + "_" + String.valueOf(column)));
			            parentId = tempMap.get(rows.get(column - 1) + "_" + String.valueOf(column)).getId();
			        }
			        levelName = rows.get(column);
			        
			        //tempMap中没有记录过则表示是要插入的数据
			        if (!tempMap.containsKey(levelName + "_" + (column + 1)))
			        {
			        	index=rows.get(column+2);
			            level = new Occupation();
			            level.setId(index);
			            level.setName(levelName);
			            level.setPid(parentId);
			            //level.setGrade(Byte.valueOf(String.valueOf(column + 1)));
			            //level.setDhhCatId("0");
			            tempMap.put(levelName + "_" + (column + 1), level);
			            //添加到最终要导入的列表中
			            insertDatas.add(level);
			            //index=rows.get(column+2);
			        }
			    }
			}
			//批量插入
			System.out.println(JSONObject.toJSONString(insertDatas));
			return insertDatas;
	    }
	 
	 @Autowired
     RedisService redisService;
	 
	 @Autowired
	 PersonMapper personMapper;
	 
	 @PostMapping(value = "/upload")
	 @Transactional
	 public HttpResult<UploadResponse> upload(MultipartFile file, String elementCode) throws IOException, ParseException{
		 	if(file == null) {
		 		throw new PlatformBaseException("请上传文件！", 0);
		 	}
		 	//允许上传的文件类型
		 	List<String> allowFileTypeList = new ArrayList<String>();
		 	allowFileTypeList.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		 	allowFileTypeList.add("application/vnd.ms-excel");
		 	String contentType = file.getContentType();
		 	if(!allowFileTypeList.contains(contentType)) {
		 		throw new PlatformBaseException("不正确的文件格式", 0);
		 	}
		 
			ExcelRead excelRead = new ExcelRead();
			List<ArrayList<String>> sheets = null;
			if("application/vnd.ms-excel".equals(contentType)) {
				sheets = excelRead.readXls(file, 1, 1);
			}if("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType)) {
				sheets = excelRead.readXlsx(file, 1, 1);
			}
			
			String  error = checkPersonList(sheets);
			if(StringUtils.isNotBlank(error)) {
				throw new PlatformBaseException(error, 0);
			}
			
			UploadResponse response = new UploadResponse();
			for (int i =0 ;i < sheets.size();i++) {
				
				if(sheets.get(i) == null) {
					continue;
				}
				boolean isEmpty = true;
				for (String value : sheets.get(i)) {
					if(StringUtils.isNotBlank(value)) {
						isEmpty = false;
						break;
					}
				}
				if(isEmpty) {
					sheets.set(i, null);
				}
			}
			//去除空行
			sheets.removeAll(Collections.singleton(null));
			//入库
			Long personListId =  System.currentTimeMillis() ;
			for (ArrayList<String> row : sheets) {
				Person p = new Person();
				/** 0操作类型*	
					1姓名*	
					2证件类型*	
					3证件号码*	
					4年龄	
					5工种类型组别号*	
					6职业代码*	
					7职业细类	
					8职业类别	
					9年工资总额	
					10保险起期	
					11保险止期	
					12备注*/
				SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				p.setPersonListId(personListId);
				p.setPersonName(row.get(1));
				p.setPersonIdType(row.get(2));
				p.setPersonIdNo(row.get(3));
				if(StringUtils.isNotBlank(row.get(4))) {
					p.setAge(Integer.parseInt(row.get(4)));
				}
				p.setPersonWorkType(row.get(5));
				p.setPersonWork(row.get(6));
				if(StringUtils.isNotBlank((row.get(9)))){
					p.setYearSalaryAmount(Double.valueOf(row.get(9)));
				}
				if(StringUtils.isNotBlank((row.get(10)))){
					p.setEffectiveDate(st.parse(row.get(10)));
				}
				if(StringUtils.isNotBlank((row.get(11)))){
					p.setExpiryDate(st.parse(row.get(11)));
				}
				p.setComments(row.get(12));
				//p.setCreateUser(userCode);
				
				personMapper.insertSelective(p);
			}
			
			response.setRows(sheets.size());
			response.setPersonListId(personListId);
			log.info(JSONObject.toJSONString(sheets));
			return HttpResult.success(response,1, "ok");
	 }
	 
	 private String checkPersonList(List<ArrayList<String>> personList) {
		StringBuffer error = new StringBuffer();
		for (int i =0 ;i < personList.size();i++) {
			ArrayList<String> row = personList.get(i);
			
			if(row.size() < 1) {
				continue;
			}
			
			if( row.size() < 2 || org.apache.commons.lang.StringUtils.isBlank(row.get(1))  ) {
				error.append("第"+(i+2) + "行，姓名为空;");
			}
			if( row.size() < 3 || org.apache.commons.lang.StringUtils.isBlank(row.get(2))) {
				error.append("第"+(i+2) + "行，证件类型为空;");
			}else {
				if(row.get(2).indexOf("-")<=0) {
					error.append("第"+(i+2) + "行，证件类型格式不正确;");
				}
			}
			if(row.size() < 4 || org.apache.commons.lang.StringUtils.isBlank(row.get(3))) {
				error.append("第"+(i+2) + "行，证件号码为空;");
			}
			if(row.size() < 6 || org.apache.commons.lang.StringUtils.isBlank(row.get(5))) {
				error.append("第"+(i+2) + "行，工种类型组别号为空;");
			}
			if(row.size() < 7 || org.apache.commons.lang.StringUtils.isBlank(row.get(6))) {
				error.append("第"+(i+2) + "行，职业代码为空;");
			}
			if(row.size() >= 11 && StringUtils.isNotBlank(row.get(10)) && !Pattern.matches("^\\d{4}-(0?[1-9]|[1][012])-(0?[1-9]|[12][0-9]|[3][01])[\\s]+\\d([0-1][0-9]|2?[0-3]):([0-5][0-9]):([0-5][0-9])$", row.get(10))) {
				error.append("第"+(i+2) + "行，保险起期格式不正确;");
			}
			if(row.size() >= 12 && StringUtils.isNotBlank(row.get(11)) && !Pattern.matches("^\\d{4}-(0?[1-9]|[1][012])-(0?[1-9]|[12][0-9]|[3][01])[\\s]+\\d([0-1][0-9]|2?[0-3]):([0-5][0-9]):([0-5][0-9])$", row.get(11))) {
				error.append("第"+(i+2) + "行，保险止期格式不正确;");
			}
		}
		return error.toString();
	 }
	
	 
	 
}
