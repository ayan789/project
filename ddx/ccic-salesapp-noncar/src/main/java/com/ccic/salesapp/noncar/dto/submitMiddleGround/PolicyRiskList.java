/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.submitMiddleGround;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2020-09-10 14:42:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PolicyRiskList {

    private String productLine;
    private String riskType;
    private int insuranceType;
    private int numberOfCopies;
    private String planCode;
    private String customerName;
    private String customerRoleCode;
    private Date dateOfBirth;
    private String idNo;
    private String idType;
    private String partyCategory;
    private String polHolderInsuredRelaCode;
    private int sequenceNumber;
    private String socialSecurityFlag;
    private List<PolicyCoverageList> policyCoverageList;
    public void setProductLine(String productLine) {
         this.productLine = productLine;
     }
     public String getProductLine() {
         return productLine;
     }

    public void setRiskType(String riskType) {
         this.riskType = riskType;
     }
     public String getRiskType() {
         return riskType;
     }

    public void setInsuranceType(int insuranceType) {
         this.insuranceType = insuranceType;
     }
     public int getInsuranceType() {
         return insuranceType;
     }

    public void setNumberOfCopies(int numberOfCopies) {
         this.numberOfCopies = numberOfCopies;
     }
     public int getNumberOfCopies() {
         return numberOfCopies;
     }

    public void setPlanCode(String planCode) {
         this.planCode = planCode;
     }
     public String getPlanCode() {
         return planCode;
     }

    public void setCustomerName(String customerName) {
         this.customerName = customerName;
     }
     public String getCustomerName() {
         return customerName;
     }

    public void setCustomerRoleCode(String customerRoleCode) {
         this.customerRoleCode = customerRoleCode;
     }
     public String getCustomerRoleCode() {
         return customerRoleCode;
     }

    public void setDateOfBirth(Date dateOfBirth) {
         this.dateOfBirth = dateOfBirth;
     }
     public Date getDateOfBirth() {
         return dateOfBirth;
     }

    public void setIdNo(String idNo) {
         this.idNo = idNo;
     }
     public String getIdNo() {
         return idNo;
     }

    public void setIdType(String idType) {
         this.idType = idType;
     }
     public String getIdType() {
         return idType;
     }

    public void setPartyCategory(String partyCategory) {
         this.partyCategory = partyCategory;
     }
     public String getPartyCategory() {
         return partyCategory;
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

    public void setSocialSecurityFlag(String socialSecurityFlag) {
         this.socialSecurityFlag = socialSecurityFlag;
     }
     public String getSocialSecurityFlag() {
         return socialSecurityFlag;
     }

    public void setPolicyCoverageList(List<PolicyCoverageList> policyCoverageList) {
         this.policyCoverageList = policyCoverageList;
     }
     public List<PolicyCoverageList> getPolicyCoverageList() {
         return policyCoverageList;
     }

}