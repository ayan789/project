package com.ccic.salesapp.noncar.dto.response;
import java.util.List;

public class RecommendRankPlanList {

    private List<RecommendPlanList> recommendPlanList;
    public void setRecommendPlanList(List<RecommendPlanList> recommendPlanList) {
         this.recommendPlanList = recommendPlanList;
     }
     public List<RecommendPlanList> getRecommendPlanList() {
         return recommendPlanList;
     }

}