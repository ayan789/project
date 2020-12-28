package com.ccic.salesapp.noncar.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.response.InsureRes;
import com.ccic.salesapp.noncar.dto.response.PolicyLob;
import com.ccic.salesapp.noncar.dto.response.PolicyRisk;
import com.ccic.salesapp.noncar.dto.response.RecommendPlanList;
import com.ccic.salesapp.noncar.dto.response.RecommendRankPlanList;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlan;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class RecommendRankPlansObj implements Serializable{

    RecommendRankPlansVo recommendRankPlansVo = new RecommendRankPlansVo();
    List<RecommendRankPlanList> recommendRankPlanList = new ArrayList<>();
    List<String> salePlanCodelist = Lists.newArrayList();
    List<String> childPlanCodelist = Lists.newArrayList();
    List<String> relaCodelist = Lists.newArrayList();
    Map<String,List<InsureRes>> relationMap = new HashMap<>();
    Map<String,List<InsureRes>> nameMap = new HashMap<>();
    List<InsureRes> mapList = new ArrayList<>();
    List<Map<String,Object>> mapInsureList = new ArrayList<Map<String,Object>>();
    List<GroupSalesPlan> groupSalesPlan;
    List<RecommendPlanList> recommendPlanList = new ArrayList<>();
    List<PolicyLob> policyLobList = new ArrayList<>();
    List<PolicyRisk> PolicyRiskList = new ArrayList<>();
    String childPlanCode = "";
    String salesPlanCode = "";
}

