package com.ccic.salesapp.noncar.dto.response;
import java.util.List;

public class RecommendRankPlansRes {

    private String groupPlanCode;
    private List<RecommendRankPlanList> recommendRankPlanList;
    public void setGroupPlanCode(String groupPlanCode) {
         this.groupPlanCode = groupPlanCode;
     }
     public String getGroupPlanCode() {
         return groupPlanCode;
     }

    public void setRecommendRankPlanList(List<RecommendRankPlanList> recommendRankPlanList) {
         this.recommendRankPlanList = recommendRankPlanList;
     }
     public List<RecommendRankPlanList> getRecommendRankPlanList() {
         return recommendRankPlanList;
     }

}