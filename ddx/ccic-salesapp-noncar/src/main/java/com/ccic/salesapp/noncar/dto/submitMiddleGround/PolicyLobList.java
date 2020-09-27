/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.submitMiddleGround;
import java.util.List;

/**
 * Auto-generated: 2020-09-10 14:42:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PolicyLobList {

    private String proposalNo;
    private String policyNo;
    private List<PolicyRiskList> policyRiskList;
    public void setProposalNo(String proposalNo) {
         this.proposalNo = proposalNo;
     }
     public String getProposalNo() {
         return proposalNo;
     }

    public void setPolicyNo(String policyNo) {
         this.policyNo = policyNo;
     }
     public String getPolicyNo() {
         return policyNo;
     }

    public void setPolicyRiskList(List<PolicyRiskList> policyRiskList) {
         this.policyRiskList = policyRiskList;
     }
     public List<PolicyRiskList> getPolicyRiskList() {
         return policyRiskList;
     }

}