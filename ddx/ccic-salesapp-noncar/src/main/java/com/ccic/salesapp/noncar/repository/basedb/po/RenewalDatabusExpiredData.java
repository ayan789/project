package com.ccic.salesapp.noncar.repository.basedb.po;

import java.math.BigDecimal;
import java.util.Date;

public class RenewalDatabusExpiredData {
    private Long id;

    private String policyNo;

    private String appName;

    private String appCustNo;

    private String planName;

    private Date endDate;

    private BigDecimal premium;

    private String claimStatus;

    private Date createtime;

    private String productCate;

    private Date insertTime;

    private String staffCode;

    private String isIssued;

    private String newPolicyNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo == null ? null : policyNo.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppCustNo() {
        return appCustNo;
    }

    public void setAppCustNo(String appCustNo) {
        this.appCustNo = appCustNo == null ? null : appCustNo.trim();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus == null ? null : claimStatus.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getProductCate() {
        return productCate;
    }

    public void setProductCate(String productCate) {
        this.productCate = productCate == null ? null : productCate.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getIsIssued() {
        return isIssued;
    }

    public void setIsIssued(String isIssued) {
        this.isIssued = isIssued == null ? null : isIssued.trim();
    }

    public String getNewPolicyNo() {
        return newPolicyNo;
    }

    public void setNewPolicyNo(String newPolicyNo) {
        this.newPolicyNo = newPolicyNo == null ? null : newPolicyNo.trim();
    }
}