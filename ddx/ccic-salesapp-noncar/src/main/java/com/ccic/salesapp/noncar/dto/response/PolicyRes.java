package com.ccic.salesapp.noncar.dto.response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PolicyRes {

    private String premiumCurrencyCode;
    private String businessSourceCodeTwo;
    private String licenseNo;
    private String projectNoName;
    private String internalCoInsuranceType;
    private String policyNo;
    private String coInsuranceType;
    private String policyStatus;
    private Date expiryDate;
    private String productCode;
    private String orgCode;
    private String businessLine;
    private String policyType;
    private String businessSourceCodeOne;
    private String siCurrencyCode;
    private String vin;
    private String coinsFlag;
    private String insuredName;
    private Date effectiveDate;
    private PolicyHolder policyHolder;
    private Double duePremium;
    private String productName;
    Map<String, Long> timeMap = new HashMap<>();
    public void setPremiumCurrencyCode(String premiumCurrencyCode) {
         this.premiumCurrencyCode = premiumCurrencyCode;
     }
     public String getPremiumCurrencyCode() {
         return premiumCurrencyCode;
     }

    public void setBusinessSourceCodeTwo(String businessSourceCodeTwo) {
         this.businessSourceCodeTwo = businessSourceCodeTwo;
     }
     public String getBusinessSourceCodeTwo() {
         return businessSourceCodeTwo;
     }

    public void setLicenseNo(String licenseNo) {
         this.licenseNo = licenseNo;
     }
     public String getLicenseNo() {
         return licenseNo;
     }

    public void setProjectNoName(String projectNoName) {
         this.projectNoName = projectNoName;
     }
     public String getProjectNoName() {
         return projectNoName;
     }

    public void setInternalCoInsuranceType(String internalCoInsuranceType) {
         this.internalCoInsuranceType = internalCoInsuranceType;
     }
     public String getInternalCoInsuranceType() {
         return internalCoInsuranceType;
     }

    public void setPolicyNo(String policyNo) {
         this.policyNo = policyNo;
     }
     public String getPolicyNo() {
         return policyNo;
     }

    public void setCoInsuranceType(String coInsuranceType) {
         this.coInsuranceType = coInsuranceType;
     }
     public String getCoInsuranceType() {
         return coInsuranceType;
     }

    public void setPolicyStatus(String policyStatus) {
         this.policyStatus = policyStatus;
     }
     public String getPolicyStatus() {
         return policyStatus;
     }

    public void setExpiryDate(Date expiryDate) {
         this.expiryDate = expiryDate;
     }
     public Date getExpiryDate() {
         return expiryDate;
     }

    public void setProductCode(String productCode) {
         this.productCode = productCode;
     }
     public String getProductCode() {
         return productCode;
     }

    public void setOrgCode(String orgCode) {
         this.orgCode = orgCode;
     }
     public String getOrgCode() {
         return orgCode;
     }

    public void setBusinessLine(String businessLine) {
         this.businessLine = businessLine;
     }
     public String getBusinessLine() {
         return businessLine;
     }

    public void setPolicyType(String policyType) {
         this.policyType = policyType;
     }
     public String getPolicyType() {
         return policyType;
     }

    public void setBusinessSourceCodeOne(String businessSourceCodeOne) {
         this.businessSourceCodeOne = businessSourceCodeOne;
     }
     public String getBusinessSourceCodeOne() {
         return businessSourceCodeOne;
     }

    public void setSiCurrencyCode(String siCurrencyCode) {
         this.siCurrencyCode = siCurrencyCode;
     }
     public String getSiCurrencyCode() {
         return siCurrencyCode;
     }

    public void setVin(String vin) {
         this.vin = vin;
     }
     public String getVin() {
         return vin;
     }

    public void setCoinsFlag(String coinsFlag) {
         this.coinsFlag = coinsFlag;
     }
     public String getCoinsFlag() {
         return coinsFlag;
     }

    public void setInsuredName(String insuredName) {
         this.insuredName = insuredName;
     }
     public String getInsuredName() {
         return insuredName;
     }

    public void setEffectiveDate(Date effectiveDate) {
         this.effectiveDate = effectiveDate;
     }
     public Date getEffectiveDate() {
         return effectiveDate;
     }

    public void setPolicyHolder(PolicyHolder policyHolder) {
         this.policyHolder = policyHolder;
     }
     public PolicyHolder getPolicyHolder() {
         return policyHolder;
     }
    public Double getDuePremium() {
        return duePremium;
    }
    public void setDuePremium(Double duePremium) {
        this.duePremium = duePremium;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Map<String, Long> getTimeMap() {
        return timeMap;
    }
    public void setTimeMap(Map<String, Long> timeMap) {
        this.timeMap = timeMap;
    }
    
     
}