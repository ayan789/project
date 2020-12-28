package com.ccic.salesapp.noncar.dto.response;
import java.util.List;

public class RecommendPlanList {

    private String salesPlanCode;
    private List<PolicyLob> policyLobList;
    public void setSalesPlanCode(String salesPlanCode) {
         this.salesPlanCode = salesPlanCode;
     }
     public String getSalesPlanCode() {
         return salesPlanCode;
     }
    public List<PolicyLob> getPolicyLobList() {
        return policyLobList;
    }
    public void setPolicyLobList(List<PolicyLob> policyLobList) {
        this.policyLobList = policyLobList;
    }

     

}