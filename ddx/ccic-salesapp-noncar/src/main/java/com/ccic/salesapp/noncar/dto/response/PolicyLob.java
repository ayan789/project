package com.ccic.salesapp.noncar.dto.response;
import java.util.List;

public class PolicyLob {

    private String childPlanCode;
    private List<PolicyRisk> policyRiskList;
    public void setChildPlanCode(String childPlanCode) {
         this.childPlanCode = childPlanCode;
     }
     public String getChildPlanCode() {
         return childPlanCode;
     }
    public List<PolicyRisk> getPolicyRiskList() {
        return policyRiskList;
    }
    public void setPolicyRiskList(List<PolicyRisk> policyRiskList) {
        this.policyRiskList = policyRiskList;
    }
    
}