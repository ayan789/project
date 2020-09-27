package com.ccic.salesapp.performance.domain.mapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.performance.domain.vo.dto.PerfResponsibility;
import com.ccic.salesapp.performance.domain.vo.request.HasResponsibilityVo;
import com.ccic.salesapp.performance.domain.vo.request.ProgressRequestVo;
import com.ccic.salesapp.performance.domain.vo.request.RankVo;
import com.ccic.salesapp.performance.domain.vo.request.SalesResponsibilityRequestVo;
import com.ccic.salesapp.performance.domain.vo.response.ProgressMonthQuarterResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.ProgressYearResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.RankRes;
import com.ccic.salesapp.performance.domain.vo.response.RankResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.RankingM2YPremiumResponseVo;

@Component
@Mapper
public interface PerfResponsibilityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PerfResponsibility record);

    List<PerfResponsibility> selectAll();

    int updateByPrimaryKey(PerfResponsibility record);
    
    List<SalesResponsibilityRequestVo> selectPerfResponsibility(Map<String,Object> map);
    
    int selectPerfResponsibilityNum(Map<String,Object> map);
    
    List<LinkedHashMap<String,Object>> getPersMonthlyQuarterlyProgress(ProgressRequestVo progressRequestVo);
    
    List<LinkedHashMap<String,Object>> getPersMonthlyTarget(ProgressRequestVo progressRequestVo);
    
    List<LinkedHashMap<String,Object>> getPersQuarterTarget(ProgressRequestVo progressRequestVo);
    
    List<LinkedHashMap<String,Object>> getTeamsMonthlyQuarterlyProgress(ProgressRequestVo progressRequestVo);
    
    List<LinkedHashMap<String,Object>> getTeamsMonthlyTarget(ProgressRequestVo progressRequestVo);
    
    List<LinkedHashMap<String,Object>> getTeamsQuarterTarget(ProgressRequestVo progressRequestVo);
    
    List<ProgressYearResponseVo> getPersYear(ProgressRequestVo progressRequestVo);
    
    List<ProgressYearResponseVo> getTeamYear(ProgressRequestVo progressRequestVo);
    
    List<LinkedHashMap<String,Object>> getPersYearTarget(ProgressRequestVo progressRequestVo);
    
    RankResponseVo rankingIndividualTeam(RankVo rankVo);
    
    List<RankRes> rankingIndividualTeamList(RankVo rankVo);
    
    //int rankingIndividualTeamNum(RankVo rankVo);

    RankResponseVo rankingIndividualBranchCom(RankVo rankVo);
    
    List<RankRes> rankingIndividualBranchComList(RankVo rankVo);
    
    //int rankingIndividualBranchComNum(RankVo rankVo);

    RankResponseVo rankingTeamBranchCom(RankVo rankVo);
    
    List<RankRes> rankingTeamBranchComList(RankVo rankVo);
    
    //int rankingTeamBranchComNum(RankVo rankVo);

    RankResponseVo rankingTeamZComCom(RankVo rankVo);
    
    List<RankRes> rankingTeamZComComList(RankVo rankVo);
    
    //int rankingTeamZComComNum(RankVo rankVo);

    RankingM2YPremiumResponseVo getM2YPremium(RankVo rankVo);

    int hasResponsibility();

    String getChannelCode(String userCode);
    
    long getResponsibilityDetail(HashMap<String,Object> map);
    
    PerfResponsibility hasResponsibilityStatus(SalesResponsibilityRequestVo salesResponsibilityRequestVo);
    
    List<SalesResponsibilityRequestVo> getResponsibilityInit(SalesResponsibilityRequestVo salesResponsibilityRequestVo);
    
    int hasResponsibilityMsgStatus(HashMap<String,Object> map);
    
    int isTeamleader(HashMap<String,Object> map);
    
    int updateCustomerStandardCapacity();
	
	int updateCustomerTotalStandard();
	
	int updateCustomerStandardChannel();
	
	List<HashMap<String,Object>> teamInfo();
	
	int updateTeamerResponsibility(HashMap<String,Object> map);
	
	int updatePserResponsibility(HashMap<String,Object> map);
	
	int updateTeamer(HashMap<String,Object> map);
	
	List<HashMap<String,Object>> salesmanPortraitInfo();
	
	int updatePerformanceSalesmanPortrait(HashMap<String,Object> map);
	
	int noExistsCustomer();
    
}