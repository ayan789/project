package com.ccic.salesapp.performance.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.performance.controller.MessageSendVO;
import com.ccic.salesapp.performance.domain.mapper.PerfResponsibilityDetailMapper;
import com.ccic.salesapp.performance.domain.mapper.PerfResponsibilityMapper;
import com.ccic.salesapp.performance.domain.mapper.PerformanceCustomerStandardDao;
import com.ccic.salesapp.performance.domain.vo.ResponsibilityEditValid;
import com.ccic.salesapp.performance.domain.vo.ResponsibilitySaveValid;
import com.ccic.salesapp.performance.domain.vo.dto.JsonVO;
import com.ccic.salesapp.performance.domain.vo.dto.MessageDto;
import com.ccic.salesapp.performance.domain.vo.dto.PerfResponsibility;
import com.ccic.salesapp.performance.domain.vo.dto.PerfResponsibilityDetail;
import com.ccic.salesapp.performance.domain.vo.dto.PerformanceCustomerStandard;
import com.ccic.salesapp.performance.domain.vo.dto.SalesResponsibility;
import com.ccic.salesapp.performance.domain.vo.request.HasResponsibilityVo;
import com.ccic.salesapp.performance.domain.vo.request.PageVO;
import com.ccic.salesapp.performance.domain.vo.request.ProgressRequestVo;
import com.ccic.salesapp.performance.domain.vo.request.RankVo;
import com.ccic.salesapp.performance.domain.vo.request.SalesResponsibilityRequestVo;
import com.ccic.salesapp.performance.domain.vo.request.SalesResponsibilityVo;
import com.ccic.salesapp.performance.domain.vo.response.ProgressMonthQuarterResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.ProgressYearResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.RankAndPremiumResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.RankRes;
import com.ccic.salesapp.performance.domain.vo.response.RankResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.RankingM2YPremiumResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.SalesResponsibilityResponseVo;
import com.ccic.salesapp.performance.feign.SupportFeignService;
import com.ccic.salesapp.performance.service.SalesResponsibilityNewService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.ValidationUtils;

import io.swagger.annotations.ApiModelProperty;

@Service
public class SalesResponsibilityNewServiceImpl implements SalesResponsibilityNewService{
	
	@Autowired
	PerfResponsibilityMapper perfResponsibilityMapper;
	
	@Autowired
	PerfResponsibilityDetailMapper perfResponsibilityDetailMapper;
	
	@Autowired
	SupportFeignService supportFeignService;
	
	@Autowired
	PerformanceCustomerStandardDao performanceCustomerStandardDao;

	@Override
	public HttpResult<?> salesResponsibilityList(SalesResponsibilityVo salesResponsibilityVo) {
		List<SalesResponsibilityRequestVo> list = new ArrayList<SalesResponsibilityRequestVo>();
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(salesResponsibilityVo.getUserCode()))
		map.put("userCode", salesResponsibilityVo.getUserCode());
		if(StringUtils.isNotEmpty(salesResponsibilityVo.getId()));
		map.put("id", salesResponsibilityVo.getId());
		if(StringUtils.isNotEmpty(salesResponsibilityVo.getIsYear()));
		map.put("isYear", salesResponsibilityVo.getIsYear());
		if(StringUtils.isNotEmpty(salesResponsibilityVo.getResponsibilityType()));
		map.put("responsibilityType", salesResponsibilityVo.getResponsibilityType());
		PageVO page = null;
		if(salesResponsibilityVo.getPage()!=null) {
			page = salesResponsibilityVo.getPage();
			int offset = (page.getPage()-1)*page.getSize();
			int limit = page.getSize();
			map.put("offset", offset);
			map.put("limit", limit);
		}
		list = perfResponsibilityMapper.selectPerfResponsibility(map);
		for(SalesResponsibilityRequestVo salesResponsibilityRequest: list){
			List<PerfResponsibilityDetail> detailList = perfResponsibilityDetailMapper.selectByPerfResponsibilityId(salesResponsibilityRequest.getId());
			salesResponsibilityRequest.setPerfResponsibilityDetailList(detailList);
		}
		JsonVO jsonVO = new JsonVO();
		jsonVO.setList(list);
		if(salesResponsibilityVo.getPage()!=null) {
			int count = perfResponsibilityMapper.selectPerfResponsibilityNum(map);
			page.init(count);
			jsonVO.setPage(page);
		}
		return HttpResult.success(jsonVO, 1, "查询成功");
	}

	@Override
	public HttpResult<?> salesResponsibilitySave(SalesResponsibilityRequestVo salesResponsibilityRequestVo) {
		if(StringUtils.isBlank(salesResponsibilityRequestVo.getIsYear()))
		salesResponsibilityRequestVo.setIsYear(is_year(new Date()));
		if(!salesRespIdSaveFlag(salesResponsibilityRequestVo)){
			ValidationUtils.validate(salesResponsibilityRequestVo,ResponsibilitySaveValid.class);
			PerfResponsibility perfResponsibility = new PerfResponsibility();
			perfResponsibility.setUserCode(salesResponsibilityRequestVo.getUserCode());
			perfResponsibility.setResponsibilityType(salesResponsibilityRequestVo.getResponsibilityType());
			if(salesResponsibilityRequestVo.getTeamCode()!=null)
			perfResponsibility.setTeamCode(salesResponsibilityRequestVo.getTeamCode());
			// P 个人责任书 T 团队责任书
			if("P".equals(salesResponsibilityRequestVo.getResponsibilityType()))
			perfResponsibility.setResponsibilityName(salesResponsibilityRequestVo.getIsYear()+"责任书");
			if("T".equals(salesResponsibilityRequestVo.getResponsibilityType()))
			perfResponsibility.setResponsibilityName(salesResponsibilityRequestVo.getIsYear()+"责任书(团队)");	
			perfResponsibility.setMultiple(0);
			perfResponsibility.setIsYear(salesResponsibilityRequestVo.getIsYear());
			perfResponsibility.setSignStatus("0");
			if(salesResponsibilityRequestVo.getChangeStatus()!=null)
			perfResponsibility.setChangeStatus(salesResponsibilityRequestVo.getChangeStatus());
			perfResponsibility.setCreateTime(new Date());
			perfResponsibilityMapper.insert(perfResponsibility);
			for(int i=0;i<12;i++){
				PerfResponsibilityDetail perfResponsibilityDetail = new PerfResponsibilityDetail();
				perfResponsibilityDetail.setResponsibilityId(perfResponsibility.getId());
				perfResponsibilityDetail.setMonthStep((i+1)+"");
				perfResponsibilityDetailMapper.insert(perfResponsibilityDetail);
			}
			return HttpResult.success(perfResponsibility.getId(), 1, "保存成功");
		}
		else if(salesRespIdSaveFlag(salesResponsibilityRequestVo) && salesRespTeamerUpdateFlag(salesResponsibilityRequestVo) && !salesRespPerserFlag(salesResponsibilityRequestVo))
		{	
			ValidationUtils.validate(salesResponsibilityRequestVo,Default.class,ResponsibilityEditValid.class);
			//每月业绩不能为空
			if(salesResponsibilityRequestVo.getPerfResponsibilityDetailList()==null || salesResponsibilityRequestVo.getPerfResponsibilityDetailList().size()==0)
				return HttpResult.error(0, "每月业绩不能为空");
			PerfResponsibility perfResponsibility = new PerfResponsibility();
			perfResponsibility.setId(salesResponsibilityRequestVo.getId());
			perfResponsibility.setUserCode(salesResponsibilityRequestVo.getUserCode());
			perfResponsibility.setResponsibilityType(salesResponsibilityRequestVo.getResponsibilityType());
			perfResponsibility.setTeamCode(salesResponsibilityRequestVo.getTeamCode());
			perfResponsibility.setResponsibilityName(salesResponsibilityRequestVo.getResponsibilityName());
			perfResponsibility.setResponsibilityNote(salesResponsibilityRequestVo.getResponsibilityNote());
			perfResponsibility.setInsurePremium(salesResponsibilityRequestVo.getInsurePremium());
			perfResponsibility.setCarInsurePremium(salesResponsibilityRequestVo.getCarInsurePremium());
			perfResponsibility.setAccInsurePremium(salesResponsibilityRequestVo.getAccInsurePremium());
			perfResponsibility.setHeaInsurePremium(salesResponsibilityRequestVo.getHeaInsurePremium());
			perfResponsibility.setPropInsurePremium(salesResponsibilityRequestVo.getPropInsurePremium());
			perfResponsibility.setMultiple(salesResponsibilityRequestVo.getMultiple());
			perfResponsibility.setSignTime(salesResponsibilityRequestVo.getSignTime());
			perfResponsibility.setIsYear(salesResponsibilityRequestVo.getIsYear());
			perfResponsibility.setAutomaticFlag(salesResponsibilityRequestVo.getAutomaticFlag());
			perfResponsibility.setMultiple(1);
			perfResponsibility.setUpdateTime(new Date());
			perfResponsibility.setSignTime(new Date());
			if(salesResponsibilityRequestVo.getChangeStatus()!=null)
			perfResponsibility.setChangeStatus(salesResponsibilityRequestVo.getChangeStatus());
			if(salesResponsibilityRequestVo.getSignStatus()!=null)
			perfResponsibility.setSignStatus(salesResponsibilityRequestVo.getSignStatus());
			if(StringUtils.isNotEmpty(salesResponsibilityRequestVo.getUserSign())){
				perfResponsibility.setUserSign(salesResponsibilityRequestVo.getUserSign());
				perfResponsibility.setSignStatus("1");
			}
			perfResponsibilityMapper.updateByPrimaryKey(perfResponsibility);
			for(PerfResponsibilityDetail perfResponsibilityDetail: salesResponsibilityRequestVo.getPerfResponsibilityDetailList()){
				ValidationUtils.validate(perfResponsibilityDetail);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("monthStep", perfResponsibilityDetail.getMonthStep());
				map.put("userCode", perfResponsibility.getUserCode());
				map.put("isYear", perfResponsibility.getIsYear());
				map.put("responsibilityType", perfResponsibility.getResponsibilityType());
				long id = perfResponsibilityMapper.getResponsibilityDetail(map);
				if(id>0) {
					perfResponsibilityDetail.setId(id);
					perfResponsibilityDetail.setResponsibilityId(salesResponsibilityRequestVo.getId());
					perfDvalue(perfResponsibilityDetail);
					perfResponsibilityDetailMapper.updateByPrimaryKey(perfResponsibilityDetail);
				}
			}
			return HttpResult.success(1, "团队长变更一次");
		}
		else if(salesRespIdSaveFlag(salesResponsibilityRequestVo) && !salesRespTeamerUpdateFlag(salesResponsibilityRequestVo) && !salesRespPerserFlag(salesResponsibilityRequestVo))
		{
			ValidationUtils.validate(salesResponsibilityRequestVo,Default.class,ResponsibilityEditValid.class);
			return HttpResult.error(0, "团队长只能变更一次");
		}
		else if(salesRespIdSaveFlag(salesResponsibilityRequestVo) && salesRespPerserFlag(salesResponsibilityRequestVo)){
			ValidationUtils.validate(salesResponsibilityRequestVo);
			//每月业绩不能为空
			if(salesResponsibilityRequestVo.getPerfResponsibilityDetailList()==null || salesResponsibilityRequestVo.getPerfResponsibilityDetailList().size()==0)
				return HttpResult.error(0, "每月业绩不能为空");
			PerfResponsibility perfResponsibility = new PerfResponsibility();
			perfResponsibility.setId(salesResponsibilityRequestVo.getId());
			perfResponsibility.setUserCode(salesResponsibilityRequestVo.getUserCode());
			perfResponsibility.setResponsibilityType(salesResponsibilityRequestVo.getResponsibilityType());
			perfResponsibility.setTeamCode(salesResponsibilityRequestVo.getTeamCode());
			perfResponsibility.setResponsibilityName(salesResponsibilityRequestVo.getResponsibilityName());
			perfResponsibility.setResponsibilityNote(salesResponsibilityRequestVo.getResponsibilityNote());
			perfResponsibility.setInsurePremium(salesResponsibilityRequestVo.getInsurePremium());
			perfResponsibility.setCarInsurePremium(salesResponsibilityRequestVo.getCarInsurePremium());
			perfResponsibility.setAccInsurePremium(salesResponsibilityRequestVo.getAccInsurePremium());
			perfResponsibility.setHeaInsurePremium(salesResponsibilityRequestVo.getHeaInsurePremium());
			perfResponsibility.setPropInsurePremium(salesResponsibilityRequestVo.getPropInsurePremium());
			perfResponsibility.setMultiple(salesResponsibilityRequestVo.getMultiple());
			perfResponsibility.setSignTime(salesResponsibilityRequestVo.getSignTime());
			perfResponsibility.setIsYear(salesResponsibilityRequestVo.getIsYear());
			perfResponsibility.setAutomaticFlag(salesResponsibilityRequestVo.getAutomaticFlag());
			perfResponsibility.setMultiple(1);
			perfResponsibility.setUpdateTime(new Date());
			perfResponsibility.setSignTime(new Date());
			if(salesResponsibilityRequestVo.getChangeStatus()!=null)
			perfResponsibility.setChangeStatus(salesResponsibilityRequestVo.getChangeStatus());
			if(salesResponsibilityRequestVo.getSignStatus()!=null)
			perfResponsibility.setSignStatus(salesResponsibilityRequestVo.getSignStatus());
			if(StringUtils.isNotEmpty(salesResponsibilityRequestVo.getUserSign())){
				perfResponsibility.setUserSign(salesResponsibilityRequestVo.getUserSign());
				perfResponsibility.setSignStatus("1");
			}	
			perfResponsibilityMapper.updateByPrimaryKey(perfResponsibility);
			for(PerfResponsibilityDetail perfResponsibilityDetail: salesResponsibilityRequestVo.getPerfResponsibilityDetailList()){
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("monthStep", perfResponsibilityDetail.getMonthStep());
				map.put("userCode", perfResponsibility.getUserCode());
				map.put("isYear", perfResponsibility.getIsYear());
				map.put("responsibilityType", perfResponsibility.getResponsibilityType());
				long id = perfResponsibilityMapper.getResponsibilityDetail(map);
				if(id>0) {
					perfResponsibilityDetail.setId(id);
					perfResponsibilityDetail.setResponsibilityId(salesResponsibilityRequestVo.getId());
					perfDvalue(perfResponsibilityDetail);
					perfResponsibilityDetailMapper.updateByPrimaryKey(perfResponsibilityDetail);
				}
			}
			return HttpResult.success(1, "个人责任书变更");
		}
		else return null;
	}

	@Override
	public HttpResult<?> getMonthlyQuarterlyProgress(ProgressRequestVo progressRequestVo) {
		if(montPerserFlag(progressRequestVo)){
			List<ProgressMonthQuarterResponseVo> list = monthlyQuarterlyProgressBuild(progressRequestVo);
			if(list==null || list.size()==0)
			return HttpResult.error(0, "无数据");
			JsonVO jsonVO = new JsonVO();
			jsonVO.setList(list);
			PageVO page = new PageVO();
			page.setPage(1);
			page.setPages(10);
			int count = 12;
			page.init(count);
			jsonVO.setPage(page);
			return HttpResult.success(jsonVO, 1, "个人月季度查询成功");
		}
		else if(!montPerserFlag(progressRequestVo)){
			List<ProgressMonthQuarterResponseVo> list = monthlyQuarterlyProgressBuild2(progressRequestVo);
			if(list==null || list.size()==0)
			return HttpResult.error(0, "无数据");
			JsonVO jsonVO = new JsonVO();
			jsonVO.setList(list);
			PageVO page = new PageVO();
			page.setPage(1);
			page.setPages(10);
			int count = 12;
			page.init(count);
			jsonVO.setPage(page);
			return HttpResult.success(jsonVO, 1, "团队月季度查询成功");
		}
		else return null;	
	}

	@Override
	public HttpResult<?> getAnnualProgress(ProgressRequestVo progressRequestVo) {
		if(montPerserFlag(progressRequestVo)){
			List<ProgressYearResponseVo> list = new ArrayList<ProgressYearResponseVo>();
			list = perfResponsibilityMapper.getPersYear(progressRequestVo);
			if(list==null || list.size()==0)
			return HttpResult.success( 1, "无数据");
			JsonVO jsonVO = new JsonVO();
			jsonVO.setList(list);
			PageVO page = new PageVO();
			page.setPage(1);
			page.setPages(10);
			int count = 10;
			page.init(count);
			jsonVO.setPage(page);
			return HttpResult.success(jsonVO, 1, "个人年度查询成功");
		}
		else if(!montPerserFlag(progressRequestVo)){
			List<ProgressYearResponseVo> list = new ArrayList<ProgressYearResponseVo>();
			list = perfResponsibilityMapper.getTeamYear(progressRequestVo);
			if(list==null || list.size()==0)
			return HttpResult.success( 1, "无数据");
			JsonVO jsonVO = new JsonVO();
			jsonVO.setList(list);
			PageVO page = new PageVO();
			page.setPage(1);
			page.setSize(10);
			int count = 10;
			page.init(count);
			jsonVO.setPage(page);
			return HttpResult.success(jsonVO, 1, "团队年度查询成功");
		}
		else return null;	
	}

	@Override
	public HttpResult<?> getpersAndTeamRanking(RankVo rankVo) {
		String isNowFormatStr = isNowFormatStr(new Date());
		String isYestFormatStr = isYestFormatStr(new Date());
		if(rankingPers2TeamFlag(rankVo)){
			RankResponseVo rankResponseVo = new RankResponseVo();
			rankVo.setIsNowFormatStr(isNowFormatStr);
			rankVo.setIsYestFormatStr(isYestFormatStr);
			rankResponseVo = perfResponsibilityMapper.rankingIndividualTeam(rankVo);
			perfResponsibilityMapper.rankingIndividualTeamList(rankVo);
			List<RankRes> rankDetail = new ArrayList<>();
			rankDetail = perfResponsibilityMapper.rankingIndividualTeamList(rankVo);
			if(rankDetail==null || rankResponseVo==null) {
				return HttpResult.success( 1, "无数据");
			}
			else {
				rankResponseVo.setRankDetail(rankDetail);
				JsonVO jsonVO = new JsonVO();
				jsonVO.setRankResponseVo(rankResponseVo);
				return HttpResult.success(jsonVO, 1, "个人在团队排名查询成功");
			}
		}
		else if(rankingPers2CompFlag(rankVo)){
			RankResponseVo rankResponseVo = new RankResponseVo();
			rankVo.setIsNowFormatStr(isNowFormatStr);
			rankVo.setIsYestFormatStr(isYestFormatStr);
			rankResponseVo = perfResponsibilityMapper.rankingIndividualBranchCom(rankVo);
			List<RankRes> rankDetail = new ArrayList<>();
			rankDetail = perfResponsibilityMapper.rankingIndividualBranchComList(rankVo);
			if(rankDetail==null || rankResponseVo==null) {
				return HttpResult.success( 1, "无数据");
			}
			else {
				rankResponseVo.setRankDetail(rankDetail);
				JsonVO jsonVO = new JsonVO();
				jsonVO.setRankResponseVo(rankResponseVo);
				return HttpResult.success(jsonVO, 1, "个人在公司排名查询成功");
			}
		}
		else if(rankingTeam2CompFlag(rankVo)){
			RankResponseVo rankResponseVo = new RankResponseVo();
			rankVo.setIsNowFormatStr(isNowFormatStr);
			rankVo.setIsYestFormatStr(isYestFormatStr);
			rankResponseVo = perfResponsibilityMapper.rankingTeamBranchCom(rankVo);
			List<RankRes> rankDetail = new ArrayList<>();
			rankDetail = perfResponsibilityMapper.rankingTeamBranchComList(rankVo);
			if(rankDetail==null || rankResponseVo==null) {
				return HttpResult.success( 1, "无数据");
			}
			else {
				rankResponseVo.setRankDetail(rankDetail);
				JsonVO jsonVO = new JsonVO();
				jsonVO.setRankResponseVo(rankResponseVo);
				return HttpResult.success(jsonVO, 1, "团队在分公司排名查询成功");
			}	
		}
		else if(rankingTeam2TotalCompFlag(rankVo)){
			RankResponseVo rankResponseVo = new RankResponseVo();
			rankVo.setIsNowFormatStr(isNowFormatStr);
			rankVo.setIsYestFormatStr(isYestFormatStr);
			rankResponseVo = perfResponsibilityMapper.rankingTeamZComCom(rankVo);
			List<RankRes> rankDetail = new ArrayList<>();
			rankDetail = perfResponsibilityMapper.rankingTeamZComComList(rankVo);
			if(rankDetail==null || rankResponseVo==null) {
				return HttpResult.success( 1, "无数据");
			}
			else {
				rankResponseVo.setRankDetail(rankDetail);
				JsonVO jsonVO = new JsonVO();
				jsonVO.setRankResponseVo(rankResponseVo);
				return HttpResult.success(jsonVO, 1, "团队在总公司排名查询成功");
			}
		}
		else return null;
	}
	
	@Override
	public HttpResult<?> getRankAndPremium(RankVo rankVo) {
		return HttpResult.success( 1, "暂不使用");
	}
	
	//责任书保存更新
	public boolean salesRespIdSaveFlag(SalesResponsibilityRequestVo salesResponsibilityRequestVo){
		if(salesResponsibilityRequestVo.getId() == null)
				return false;
		else    return true;
	}
	
	//责任书类型团队长只能更新一次
	public boolean salesRespTeamerUpdateFlag(SalesResponsibilityRequestVo salesResponsibilityRequestVo){
		if("T".equals(salesResponsibilityRequestVo.getResponsibilityType()) && salesResponsibilityRequestVo.getMultiple()==0) return true;
		else return false;
	}
	
	//责任书类型是个人
	public boolean salesRespPerserFlag(SalesResponsibilityRequestVo salesResponsibilityRequestVo){
		if("P".equals(salesResponsibilityRequestVo.getResponsibilityType())) return true;
		else return false;
	}
	
	//月季排名责任书类型是个人
	public boolean montPerserFlag(ProgressRequestVo progressRequestVo){
		if("P".equals(progressRequestVo.getResponsibilityType())) return true;
		else return false;
	}
	
	//个人在团队排名
	public boolean rankingPers2TeamFlag(RankVo rankVo){
		 if("P".equals(rankVo.getRankFlag()) && "T".equals(rankVo.getTeamComFlag())) return true;
		 return false;
	}
	
	//个人在公司排名
	public boolean rankingPers2CompFlag(RankVo rankVo){
		if("P".equals(rankVo.getRankFlag()) && "F".equals(rankVo.getTeamComFlag())) return true;
		return false;
	}
	
	//团队在分公司排名
	public boolean rankingTeam2CompFlag(RankVo rankVo){
		if("T".equals(rankVo.getRankFlag()) && "F".equals(rankVo.getTeamComFlag())) return true;
		return false;
	}
	
	//团队在总公司排名
	public boolean rankingTeam2TotalCompFlag(RankVo rankVo){
		if("T".equals(rankVo.getRankFlag()) && "Z".equals(rankVo.getTeamComFlag())) return true;
		return false;
	}
	
	//判断年份
	public String is_year(Date date){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(calendar.getTime());
		return dateString;
	}

	public List<ProgressMonthQuarterResponseVo> monthlyQuarterlyProgressBuild(ProgressRequestVo progressRequestVo) {
		 List<LinkedHashMap<String,Object>> progressMonthQuarter = perfResponsibilityMapper.getPersMonthlyQuarterlyProgress(progressRequestVo);
		 List<LinkedHashMap<String,Object>> listMap = perfResponsibilityMapper.getPersMonthlyTarget(progressRequestVo);
		 List<LinkedHashMap<String,Object>> listMap2 = perfResponsibilityMapper.getPersQuarterTarget(progressRequestVo);
		 List<ProgressMonthQuarterResponseVo> list = new ArrayList<ProgressMonthQuarterResponseVo>();
		 if(progressMonthQuarter!=null && listMap!=null && listMap2!=null && progressMonthQuarter.size()!=0 && listMap.size()!=0 && listMap2.size()!=0 ) {
			 for(int month=1;month<=12;month++){
				 BigDecimal  MZ = new BigDecimal(0) ;
				 BigDecimal MC = new BigDecimal(0) ;
				 BigDecimal MA = new BigDecimal(0) ;
				 BigDecimal MH = new BigDecimal(0) ;
				 BigDecimal MNC = new BigDecimal(0) ;
				 BigDecimal  QZ = new BigDecimal(0) ;
				 BigDecimal QC = new BigDecimal(0) ;
				 BigDecimal QA = new BigDecimal(0) ;
				 BigDecimal QH = new BigDecimal(0) ;
				 BigDecimal QNC = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("MZ")!=null) 
					  MZ = ((BigDecimal)progressMonthQuarter.get(month-1).get("MZ")); 
				 else MZ = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("MC")!=null) 
					  MC = ((BigDecimal)progressMonthQuarter.get(month-1).get("MC"));
				 else MC = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("MA")!=null) 
					 MA = ((BigDecimal) progressMonthQuarter.get(month-1).get("MA"));
				 else MA = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("MH")!=null) 
					 MH = ((BigDecimal) progressMonthQuarter.get(month-1).get("MH"));
				 else MH = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("MNC")!=null)
					 MNC = ((BigDecimal)  progressMonthQuarter.get(month-1).get("MNC"));
				 else MNC = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("QZ")!=null)
					 QZ = ((BigDecimal)  progressMonthQuarter.get(month-1).get("QZ"));
				 else QZ = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("QC")!=null)
					 QC = ((BigDecimal)  progressMonthQuarter.get(month-1).get("QC"));
				 else QC = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("QA")!=null)
					 QA = ((BigDecimal) progressMonthQuarter.get(month-1).get("QA"));
				 else QA = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("QH")!=null)
					 QH = ((BigDecimal)  progressMonthQuarter.get(month-1).get("QH"));
				 else QH = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("QNC")!=null)
					 QNC = ((BigDecimal)  progressMonthQuarter.get(month-1).get("QNC"));
				 else QNC = new BigDecimal(0) ;
					 
				 int quarter = 1+((month)-1)/3;
				 BigDecimal MZ_ = ((BigDecimal)listMap.get(month-1).get("insurePremium"));
				 BigDecimal MC_ = ((BigDecimal)listMap.get(month-1).get("carInsurePremium"));
				 BigDecimal MA_ = ((BigDecimal)listMap.get(month-1).get("accInsurePremium"));
				 BigDecimal MH_ = ((BigDecimal)listMap.get(month-1).get("heaInsurePremium"));
				 BigDecimal MNC_ = ((BigDecimal)listMap.get(month-1).get("propInsurePremium"));
				 BigDecimal QZ_ = ((BigDecimal)listMap2.get(quarter-1).get("insurePremium"));
				 BigDecimal QC_ = ((BigDecimal)listMap2.get(quarter-1).get("carInsurePremium"));
				 BigDecimal QA_ = ((BigDecimal)listMap2.get(quarter-1).get("accInsurePremium"));
				 BigDecimal QH_ = ((BigDecimal)listMap2.get(quarter-1).get("heaInsurePremium"));
				 BigDecimal QNC_ = ((BigDecimal)listMap2.get(quarter-1).get("propInsurePremium"));
				 ProgressMonthQuarterResponseVo progressMonthQuarterResponse = new ProgressMonthQuarterResponseVo();
				 if(!(MZ_.compareTo(BigDecimal.ZERO)==0) && MZ!=null)
				 progressMonthQuarterResponse.setMzRat(MZ.divide(MZ_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(MC_.compareTo(BigDecimal.ZERO)==0) && MC!=null)
				 progressMonthQuarterResponse.setMcRat(MC.divide(MC_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(MA_.compareTo(BigDecimal.ZERO)==0) && MA!=null)
				 progressMonthQuarterResponse.setMaRat(MA.divide(MA_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(MH_.compareTo(BigDecimal.ZERO)==0) && MH!=null)
				 progressMonthQuarterResponse.setMhRat(MH.divide(MH_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(MNC_.compareTo(BigDecimal.ZERO)==0) && MNC!=null)
				 progressMonthQuarterResponse.setMpRat(MNC.divide(MNC_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(MZ!=null)
				 progressMonthQuarterResponse.setMzs(MZ.doubleValue());
				 progressMonthQuarterResponse.setMz(MZ_.doubleValue());
				 if(MC!=null)
				 progressMonthQuarterResponse.setMcs(MC.doubleValue());
				 progressMonthQuarterResponse.setMc(MC_.doubleValue());
				 if(MA!=null)
				 progressMonthQuarterResponse.setMas(MA.doubleValue());
				 progressMonthQuarterResponse.setMa(MA_.doubleValue());
				 if(MH!=null)
				 progressMonthQuarterResponse.setMhs(MH.doubleValue());
				 progressMonthQuarterResponse.setMh(MH_.doubleValue());
				 if(MNC!=null)
				 progressMonthQuarterResponse.setMps(MNC.doubleValue());
				 progressMonthQuarterResponse.setMp(MNC_.doubleValue());
				 if(!(QZ_.compareTo(BigDecimal.ZERO)==0)  && QZ!=null)
				 progressMonthQuarterResponse.setQzRat(QZ.divide(QZ_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(QC_.compareTo(BigDecimal.ZERO)==0) && QC!=null)
				 progressMonthQuarterResponse.setQcRat(QC.divide(QC_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(QA_.compareTo(BigDecimal.ZERO)==0) && QA!=null)
				 progressMonthQuarterResponse.setQaRat(QA.divide(QA_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(QH_.compareTo(BigDecimal.ZERO)==0) && QH!=null)
				 progressMonthQuarterResponse.setQhRat(QH.divide(QH_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(QNC_.compareTo(BigDecimal.ZERO)==0) && QNC!=null)
				 progressMonthQuarterResponse.setQpRat(QNC.divide(QNC_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(QZ!=null)
				 progressMonthQuarterResponse.setQzs(QZ.doubleValue());
				 progressMonthQuarterResponse.setQz(QZ_.doubleValue());
				 if(QC!=null)
				 progressMonthQuarterResponse.setQcs(QC.doubleValue());
				 progressMonthQuarterResponse.setQc(QC_.doubleValue());
				 if(QA!=null)
				 progressMonthQuarterResponse.setQas(QA.doubleValue());
				 progressMonthQuarterResponse.setQa(QA_.doubleValue());
				 if(QH!=null)
				 progressMonthQuarterResponse.setQhs(QH.doubleValue());
				 progressMonthQuarterResponse.setQh(QH_.doubleValue());
				 if(QNC!=null)
				 progressMonthQuarterResponse.setQps(QNC.doubleValue());
				 progressMonthQuarterResponse.setQp(QNC_.doubleValue());
				 list.add(progressMonthQuarterResponse); 
			 }
		 }
		 return list;
	}
	 
	public List<ProgressMonthQuarterResponseVo> monthlyQuarterlyProgressBuild2(ProgressRequestVo progressRequestVo) {
		 List<LinkedHashMap<String,Object>> progressMonthQuarter = perfResponsibilityMapper.getTeamsMonthlyQuarterlyProgress(progressRequestVo);
		 List<LinkedHashMap<String,Object>> listMap = perfResponsibilityMapper.getTeamsMonthlyTarget(progressRequestVo);
		 List<LinkedHashMap<String,Object>> listMap2 = perfResponsibilityMapper.getTeamsQuarterTarget(progressRequestVo);
		 List<ProgressMonthQuarterResponseVo> list = new ArrayList<ProgressMonthQuarterResponseVo>();
		 if(progressMonthQuarter!=null && listMap!=null && listMap2!=null && progressMonthQuarter.size()!=0 && listMap.size()!=0 && listMap2.size()!=0 ) {
			 for(int month=1;month<=12;month++){
				 BigDecimal MZ = new BigDecimal(0) ;
				 BigDecimal MC = new BigDecimal(0) ;
				 BigDecimal MA = new BigDecimal(0) ;
				 BigDecimal MH = new BigDecimal(0) ;
				 BigDecimal MNC = new BigDecimal(0);
				 BigDecimal QZ = new BigDecimal(0) ;
				 BigDecimal QC = new BigDecimal(0) ;
				 BigDecimal QA = new BigDecimal(0) ;
				 BigDecimal QH = new BigDecimal(0) ;
				 BigDecimal QNC = new BigDecimal(0);
				 if(progressMonthQuarter.get(month-1).get("MZ")!=null) 
					 MZ = ((BigDecimal)progressMonthQuarter.get(month-1).get("MZ"));
				 else  MZ = new BigDecimal(0) ;
				 if(progressMonthQuarter.get(month-1).get("MC")!=null) 
					 MC = ((BigDecimal)progressMonthQuarter.get(month-1).get("MC"));
				 else  MC = new BigDecimal(0) ;	
				 if(progressMonthQuarter.get(month-1).get("MA")!=null)
					 MA = ((BigDecimal) progressMonthQuarter.get(month-1).get("MA"));
				 else  MA = new BigDecimal(0) ;	 
				 if(progressMonthQuarter.get(month-1).get("MH")!=null)
					 MH = ((BigDecimal) progressMonthQuarter.get(month-1).get("MH"));
				 else  MH = new BigDecimal(0) ;	
				 if(progressMonthQuarter.get(month-1).get("MNC")!=null)
					 MNC = ((BigDecimal) progressMonthQuarter.get(month-1).get("MNC"));
				 else  MNC = new BigDecimal(0) ;	
				 if(progressMonthQuarter.get(month-1).get("QZ")!=null)
					 QZ = ((BigDecimal) progressMonthQuarter.get(month-1).get("QZ"));
				 else  QZ = new BigDecimal(0) ;	
				 if(progressMonthQuarter.get(month-1).get("QC")!=null)
					 QC = ((BigDecimal) progressMonthQuarter.get(month-1).get("QC"));
				 else  QC = new BigDecimal(0) ;	
				if(progressMonthQuarter.get(month-1).get("QA")!=null)
					QA = ((BigDecimal) progressMonthQuarter.get(month-1).get("QA"));
				else  QA = new BigDecimal(0) ;	
				if(progressMonthQuarter.get(month-1).get("QH")!=null)
					QH = ((BigDecimal) progressMonthQuarter.get(month-1).get("QH"));
				else  QH = new BigDecimal(0) ; 
				if(progressMonthQuarter.get(month-1).get("QNC")!=null)
					QNC = ((BigDecimal) progressMonthQuarter.get(month-1).get("QNC"));
				else  QNC = new BigDecimal(0) ; 
					 
				 int quarter = 1+((month)-1)/3;
				 BigDecimal MZ_ = ((BigDecimal)listMap.get(month-1).get("insurePremium"));
				 BigDecimal MC_ = ((BigDecimal)listMap.get(month-1).get("carInsurePremium"));
				 BigDecimal MA_ = ((BigDecimal)listMap.get(month-1).get("accInsurePremium"));
				 BigDecimal MH_ = ((BigDecimal)listMap.get(month-1).get("heaInsurePremium"));
				 BigDecimal MNC_ = ((BigDecimal)listMap.get(month-1).get("propInsurePremium"));
				 BigDecimal QZ_ = ((BigDecimal)listMap2.get(quarter-1).get("insurePremium"));
				 BigDecimal QC_ = ((BigDecimal)listMap2.get(quarter-1).get("carInsurePremium"));
				 BigDecimal QA_ = ((BigDecimal)listMap2.get(quarter-1).get("accInsurePremium"));
				 BigDecimal QH_ = ((BigDecimal)listMap2.get(quarter-1).get("heaInsurePremium"));
				 BigDecimal QNC_ = ((BigDecimal)listMap2.get(quarter-1).get("propInsurePremium"));
				 ProgressMonthQuarterResponseVo progressMonthQuarterResponse = new ProgressMonthQuarterResponseVo();
				 if(!(MZ_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setMzRat(MZ.divide(MZ_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(MC_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setMcRat(MC.divide(MC_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(MA_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setMaRat(MA.divide(MA_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(MH_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setMhRat(MH.divide(MH_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(MNC_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setMpRat(MNC.divide(MNC_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(MZ!=null)
				 progressMonthQuarterResponse.setMzs(MZ.doubleValue());
				 progressMonthQuarterResponse.setMz(MZ_.doubleValue());
				 if(MC!=null)
				 progressMonthQuarterResponse.setMcs(MC.doubleValue());
				 progressMonthQuarterResponse.setMc(MC_.doubleValue());
				 if(MA!=null)
				 progressMonthQuarterResponse.setMas(MA.doubleValue());
				 progressMonthQuarterResponse.setMa(MA_.doubleValue());
				 if(MH!=null)
				 progressMonthQuarterResponse.setMhs(MH.doubleValue());
				 progressMonthQuarterResponse.setMh(MH_.doubleValue());
				 if(MNC!=null)
				 progressMonthQuarterResponse.setMps(MNC.doubleValue());
				 progressMonthQuarterResponse.setMp(MNC_.doubleValue());
				 if(!(QZ_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setQzRat(QZ.divide(QZ_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(QC_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setQcRat(QC.divide(QC_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(QA_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setQaRat(QA.divide(QA_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(QH_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setQhRat(QH.divide(QH_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(!(QNC_.compareTo(BigDecimal.ZERO)==0))
				 progressMonthQuarterResponse.setQpRat(QNC.divide(QNC_, 2, RoundingMode.HALF_UP).doubleValue());
				 if(QZ!=null)
				 progressMonthQuarterResponse.setQzs(QZ.doubleValue());
				 progressMonthQuarterResponse.setQz(QZ_.doubleValue());
				 if(QC!=null)
				 progressMonthQuarterResponse.setQcs(QC.doubleValue());
				 progressMonthQuarterResponse.setQc(QC_.doubleValue());
				 if(QA!=null)
				 progressMonthQuarterResponse.setQas(QA.doubleValue());
				 progressMonthQuarterResponse.setQa(QA_.doubleValue());
				 if(QH!=null)
				 progressMonthQuarterResponse.setQhs(QH.doubleValue());
				 progressMonthQuarterResponse.setQh(QH_.doubleValue());
				 if(QNC!=null)
				 progressMonthQuarterResponse.setQps(QNC.doubleValue());
				 progressMonthQuarterResponse.setQp(QNC_.doubleValue());
				 list.add(progressMonthQuarterResponse); 
			 }
		 }
		 return list;
	}

	@Override
	public HttpResult<?> getM2YPremium(RankVo rankVo) {
		RankingM2YPremiumResponseVo rankingM2YPremiumResponseVo = perfResponsibilityMapper.getM2YPremium(rankVo);
		if(rankingM2YPremiumResponseVo!=null)
		return HttpResult.success(rankingM2YPremiumResponseVo, 1, "查询成功") ;
		else return HttpResult.error( 0, "查询失败");
	}

	@Override
	public HttpResult<?> hasResponsibilityStatus(SalesResponsibilityRequestVo salesResponsibilityRequestVo) {
		if(StringUtils.isNotEmpty(salesResponsibilityRequestVo.getIsTeamLeader())) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("userCode", salesResponsibilityRequestVo.getUserCode());
			int count = perfResponsibilityMapper.isTeamleader(map);
			if(count>0) salesResponsibilityRequestVo.setIsTeamLeader("1");
			else salesResponsibilityRequestVo.setIsTeamLeader("0");
		}
		if("0".equals(salesResponsibilityRequestVo.getIsTeamLeader())) {
			salesResponsibilityRequestVo.setResponsibilityType("P");
			PerfResponsibility perfResponsibility = perfResponsibilityMapper.hasResponsibilityStatus(salesResponsibilityRequestVo);
			HttpResult httpResult = null;
			List<SalesResponsibilityRequestVo> list = new ArrayList<SalesResponsibilityRequestVo>();
			if(perfResponsibility==null) {
				httpResult = salesResponsibilitySave(salesResponsibilityRequestVo);
				SalesResponsibilityRequestVo salesPers = new SalesResponsibilityRequestVo();
				salesPers.setId(Long.parseLong(httpResult.getData()+""));
				salesPers.setResponsibilityType("P");
				salesPers.setUserCode(salesResponsibilityRequestVo.getUserCode());
				salesPers.setResponsibilityName(salesResponsibilityRequestVo.getIsYear()+"责任书");
				salesPers.setIsYear(salesResponsibilityRequestVo.getIsYear());
				salesPers.setMultiple(0);
				salesPers.setSignStatus("0");
				list.add(salesPers);
			}
			else {
				SalesResponsibilityRequestVo salesPers = new SalesResponsibilityRequestVo();
				salesPers.setId(perfResponsibility.getId());
				salesPers.setResponsibilityType("P");
				salesPers.setUserCode(perfResponsibility.getUserCode());
				salesPers.setResponsibilityName(perfResponsibility.getIsYear()+"责任书");
				salesPers.setIsYear(perfResponsibility.getIsYear());
				salesPers.setMultiple(0);
				salesPers.setSignStatus("0");
				list.add(salesPers);
			}
			return HttpResult.success(list, 1, "调用成功");
		}
		else if("1".equals(salesResponsibilityRequestVo.getIsTeamLeader())) {
			salesResponsibilityRequestVo.setResponsibilityType("P");
			PerfResponsibility perfResponsibility = perfResponsibilityMapper.hasResponsibilityStatus(salesResponsibilityRequestVo);
			List<SalesResponsibilityRequestVo> list = new ArrayList<SalesResponsibilityRequestVo>();
			if(perfResponsibility==null) {
				HttpResult httpResult = null;
				httpResult = salesResponsibilitySave(salesResponsibilityRequestVo);
				SalesResponsibilityRequestVo salesPers = new SalesResponsibilityRequestVo();
				salesPers.setId(Long.parseLong(httpResult.getData()+""));
				salesPers.setResponsibilityType("P");
				salesPers.setUserCode(salesResponsibilityRequestVo.getUserCode());
				salesPers.setResponsibilityName(salesResponsibilityRequestVo.getIsYear()+"责任书");
				salesPers.setIsYear(salesResponsibilityRequestVo.getIsYear());
				salesPers.setMultiple(0);
				salesPers.setSignStatus("0");
				list.add(salesPers);
			}
			else {
				SalesResponsibilityRequestVo salesPers = new SalesResponsibilityRequestVo();
				salesPers.setId(perfResponsibility.getId());
				salesPers.setResponsibilityType("P");
				salesPers.setUserCode(perfResponsibility.getUserCode());
				salesPers.setResponsibilityName(perfResponsibility.getIsYear()+"责任书");
				salesPers.setIsYear(perfResponsibility.getIsYear());
				salesPers.setMultiple(0);
				salesPers.setSignStatus("0");
				list.add(salesPers);
			}
			salesResponsibilityRequestVo.setResponsibilityType("T");
			PerfResponsibility perfResponsibility_ = perfResponsibilityMapper.hasResponsibilityStatus(salesResponsibilityRequestVo);
			if(perfResponsibility_==null) {
				HttpResult httpResult = null;
				httpResult = salesResponsibilitySave(salesResponsibilityRequestVo);
				SalesResponsibilityRequestVo salesTeam = new SalesResponsibilityRequestVo();
				salesTeam.setId(Long.parseLong(httpResult.getData()+""));
				salesTeam.setResponsibilityType("T");
				salesTeam.setUserCode(salesResponsibilityRequestVo.getUserCode());
				salesTeam.setResponsibilityName(salesResponsibilityRequestVo.getIsYear()+"责任书(团队)");
				salesTeam.setIsYear(salesResponsibilityRequestVo.getIsYear());
				salesTeam.setMultiple(0);
				salesTeam.setSignStatus("0");
				list.add(salesTeam);
			}
			else {
				SalesResponsibilityRequestVo salesTeam = new SalesResponsibilityRequestVo();
				salesTeam.setId(perfResponsibility_.getId());
				salesTeam.setResponsibilityType("T");
				salesTeam.setUserCode(perfResponsibility_.getUserCode());
				salesTeam.setResponsibilityName(perfResponsibility_.getIsYear()+"责任书(团队)");
				salesTeam.setIsYear(perfResponsibility_.getIsYear());
				salesTeam.setMultiple(0);
				salesTeam.setSignStatus("0");
				list.add(salesTeam);
			}
			return HttpResult.success(list, 1, "调用成功");
		}else {
			return HttpResult.error(0, "调用失败");
		}
	}
	
	//databus T＋1推送日期
	public static String isNowFormatStr(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(calendar.getTime());
		return dateString;
	}

	public static String isYestFormatStr(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -2);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(c.getTime());
		return dateString;
	}

	@Override
	public HttpResult<?> queryRankingM2YPremium(RankVo rankVo) {
		String isNowFormatStr = isNowFormatStr(new Date());
		String isYestFormatStr = isYestFormatStr(new Date());
		rankVo.setChannelCode(perfResponsibilityMapper.getChannelCode(rankVo.getUserCode()));
		RankingM2YPremiumResponseVo rankingM2YPremiumResponseVo = new RankingM2YPremiumResponseVo();
		rankingM2YPremiumResponseVo = perfResponsibilityMapper.getM2YPremium(rankVo);
		RankResponseVo rankResponseVo = new RankResponseVo();
		rankVo.setIsNowFormatStr(isNowFormatStr);
		rankVo.setIsYestFormatStr(isYestFormatStr);
		rankVo.setTimeFlag("Y");
		rankResponseVo = perfResponsibilityMapper.rankingIndividualBranchCom(rankVo);
		if(rankingM2YPremiumResponseVo==null)
		return HttpResult.success( 1, "无数据");
		if(rankingM2YPremiumResponseVo!=null && rankResponseVo!=null)
		rankingM2YPremiumResponseVo.setPm(rankResponseVo.getJrpm());
		return HttpResult.success(rankingM2YPremiumResponseVo, 1, "查询成功");
	}

	@Override
	public HttpResult<?> responsibilityNoticePush(String[] userCodes) {
		MessageSendVO messageVo = new MessageSendVO(); 
		messageVo.setMessageType("cust-resp-notice");
		messageVo.setSubTitle("最新特别消息");
		messageVo.setTitle("超级APP尊敬的客户");
		messageVo.setBizNo("CUST-RESPONSIBILITY-NOTICE"+System.currentTimeMillis());
		messageVo.setUserCode(userCodes[0]);
		System.out.println(JSONObject.toJSONString(messageVo));
		List<MessageSendVO> messageVoList = new ArrayList<MessageSendVO>();
		messageVoList.add(messageVo);
		System.out.println(JSONArray.toJSONString(messageVoList));
		String msgData = JSONObject.toJSONString(messageVoList);
		System.out.println(msgData);
		HttpResult result = supportFeignService.sendMessage(messageVoList);
		//调用消息通知服务
		return HttpResult.success( 1, "调用成功");
	}

	@Override
	public HttpResult<?> responsibilityNotice(String userCode) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("userCode", userCode);
		int msgFlag = perfResponsibilityMapper.hasResponsibilityMsgStatus(map);
		if(msgFlag>0) {
			return HttpResult.error(0, "一天仅支持提醒一次，请明天再跟进");
		}
		//调用消息通知服务
		List<MessageDto> messageList = new ArrayList<MessageDto>();
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage("您还未签署责任书，你的团队长提醒您尽快签署");
		messageDto.setMessageType("perf-sign-msg");
		messageDto.setPolicyNo("");
		messageDto.setSource("0");
		messageDto.setStatus("0");
		messageDto.setUserCode(userCode);
		messageDto.setExtendMessage("");
		messageList.add(messageDto);
		HttpResult result = supportFeignService.saveMessage(messageList);
		return HttpResult.success( 1, "调用成功");
	}
	
	@Override
	public HttpResult<?> custStandardTotal(){
		List<HashMap<String,Object>> list = perfResponsibilityMapper.salesmanPortraitInfo();
		for(HashMap<String,Object> obj : list) {
			String userCode = obj.get("handlerCode")+"";
			System.out.println("客户对标数 userCode:"+userCode);
			double num = getCustomerStandardTotal(userCode);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("num", num);
			map.put("userCode", userCode);
			perfResponsibilityMapper.updatePerformanceSalesmanPortrait(map);
		}
		return HttpResult.success( 1, "调用成功");
	}	

	public double getCustomerStandardTotal(String userCode) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("userCode", userCode);
		PerformanceCustomerStandard performanceCustomerStandard = performanceCustomerStandardDao.getPerformanceCustomer(map);
		if(!"C0000015".equals(performanceCustomerStandard.getChannel())) {
			HashMap<String,Object> map1 = new HashMap<String, Object>();
			map1.put("userCode", performanceCustomerStandard.getUserCode());
			Double num = performanceCustomerStandardDao.getFGZCustomerNum(map1);
			return num;
		}
		else if ("C0000015".equals(performanceCustomerStandard.getChannel())) {
			HashMap<String,Object> map2 = new HashMap<String, Object>();
			map2.put("userCode", performanceCustomerStandard.getUserCode());
			Double num = performanceCustomerStandardDao.getGZCustomerNum(map2);
			return num;
		}
		else {
			return 0;
		}
	}
	
	//月各项保费总保费一致
	public void perfDvalue(PerfResponsibilityDetail perfResponsibilityDetail) {
		Long insurePremium = perfResponsibilityDetail.getInsurePremium();
		Long accInsurePremium = perfResponsibilityDetail.getAccInsurePremium();
		Long carInsurePremium = perfResponsibilityDetail.getCarInsurePremium();
		Long heaInsurePremium = perfResponsibilityDetail.getHeaInsurePremium();
		Long propInsurePremium = perfResponsibilityDetail.getPropInsurePremium();
		Long insurePremiumT = accInsurePremium+carInsurePremium+heaInsurePremium+propInsurePremium;
		Long dValue = insurePremium - insurePremiumT;
		if(dValue>0) {
			perfResponsibilityDetail.setPropInsurePremium(propInsurePremium+dValue);
		}
	}
}
