package com.ccic.salesapp.noncar.dto.response;


public class PolicyHolder {

    private String identifyNumber;
    private String policyHolderName;
    public void setIdentifyNumber(String identifyNumber) {
         this.identifyNumber = identifyNumber;
     }
     public String getIdentifyNumber() {
         return identifyNumber;
     }

    public void setPolicyHolderName(String policyHolderName) {
         this.policyHolderName = policyHolderName;
     }
     public String getPolicyHolderName() {
         return policyHolderName;
     }

}