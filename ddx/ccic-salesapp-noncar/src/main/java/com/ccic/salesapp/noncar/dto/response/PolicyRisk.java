package com.ccic.salesapp.noncar.dto.response;

public class PolicyRisk {

    private int indiAge;
    private String polHolderInsuredRelaCode;
    private int sequenceNumber;
    private String isMainInsured;
    public void setIndiAge(int indiAge) {
         this.indiAge = indiAge;
     }
     public int getIndiAge() {
         return indiAge;
     }

    public void setPolHolderInsuredRelaCode(String polHolderInsuredRelaCode) {
         this.polHolderInsuredRelaCode = polHolderInsuredRelaCode;
     }
     public String getPolHolderInsuredRelaCode() {
         return polHolderInsuredRelaCode;
     }

    public void setSequenceNumber(int sequenceNumber) {
         this.sequenceNumber = sequenceNumber;
     }
     public int getSequenceNumber() {
         return sequenceNumber;
     }

    public void setIsMainInsured(String isMainInsured) {
         this.isMainInsured = isMainInsured;
     }
     public String getIsMainInsured() {
         return isMainInsured;
     }

}