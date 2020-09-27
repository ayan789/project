/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.submitMiddleGround;
import java.util.List;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

import java.util.Date;

/**
 * Auto-generated: 2020-09-10 14:42:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean   extends ResponseBody{

    private String belongToHandler2Code;
    private String belongToHandlerCode;
    private String businessAttribute;
    private String businessSource2Code;
    private String businessSourceCode;
    private String businessType;
    private String submissionNo;
    private List<ChannelOpInfoList> channelOpInfoList;
    private Date effectiveDate;
    private Date expiryDate;
    private String isIssueAfterPay;
    private String orgCode;
    private List<PolicyCustomerList> policyCustomerList;
    private List<PolicyFormList> policyFormList;
    private List<PolicyLobList> policyLobList;
    private String premiumCurrencyCode;
    private Date proposalDate;
    public void setBelongToHandler2Code(String belongToHandler2Code) {
         this.belongToHandler2Code = belongToHandler2Code;
     }
     public String getBelongToHandler2Code() {
         return belongToHandler2Code;
     }

    public void setBelongToHandlerCode(String belongToHandlerCode) {
         this.belongToHandlerCode = belongToHandlerCode;
     }
     public String getBelongToHandlerCode() {
         return belongToHandlerCode;
     }

    public void setBusinessAttribute(String businessAttribute) {
         this.businessAttribute = businessAttribute;
     }
     public String getBusinessAttribute() {
         return businessAttribute;
     }

    public void setBusinessSource2Code(String businessSource2Code) {
         this.businessSource2Code = businessSource2Code;
     }
     public String getBusinessSource2Code() {
         return businessSource2Code;
     }

    public void setBusinessSourceCode(String businessSourceCode) {
         this.businessSourceCode = businessSourceCode;
     }
     public String getBusinessSourceCode() {
         return businessSourceCode;
     }

    public void setBusinessType(String businessType) {
         this.businessType = businessType;
     }
     public String getBusinessType() {
         return businessType;
     }

    public void setSubmissionNo(String submissionNo) {
         this.submissionNo = submissionNo;
     }
     public String getSubmissionNo() {
         return submissionNo;
     }

    public void setChannelOpInfoList(List<ChannelOpInfoList> channelOpInfoList) {
         this.channelOpInfoList = channelOpInfoList;
     }
     public List<ChannelOpInfoList> getChannelOpInfoList() {
         return channelOpInfoList;
     }

    public void setEffectiveDate(Date effectiveDate) {
         this.effectiveDate = effectiveDate;
     }
     public Date getEffectiveDate() {
         return effectiveDate;
     }

    public void setExpiryDate(Date expiryDate) {
         this.expiryDate = expiryDate;
     }
     public Date getExpiryDate() {
         return expiryDate;
     }

    public void setIsIssueAfterPay(String isIssueAfterPay) {
         this.isIssueAfterPay = isIssueAfterPay;
     }
     public String getIsIssueAfterPay() {
         return isIssueAfterPay;
     }

    public void setOrgCode(String orgCode) {
         this.orgCode = orgCode;
     }
     public String getOrgCode() {
         return orgCode;
     }

    public void setPolicyCustomerList(List<PolicyCustomerList> policyCustomerList) {
         this.policyCustomerList = policyCustomerList;
     }
     public List<PolicyCustomerList> getPolicyCustomerList() {
         return policyCustomerList;
     }

    public void setPolicyFormList(List<PolicyFormList> policyFormList) {
         this.policyFormList = policyFormList;
     }
     public List<PolicyFormList> getPolicyFormList() {
         return policyFormList;
     }

    public void setPolicyLobList(List<PolicyLobList> policyLobList) {
         this.policyLobList = policyLobList;
     }
     public List<PolicyLobList> getPolicyLobList() {
         return policyLobList;
     }

    public void setPremiumCurrencyCode(String premiumCurrencyCode) {
         this.premiumCurrencyCode = premiumCurrencyCode;
     }
     public String getPremiumCurrencyCode() {
         return premiumCurrencyCode;
     }

    public void setProposalDate(Date proposalDate) {
         this.proposalDate = proposalDate;
     }
     public Date getProposalDate() {
         return proposalDate;
     }

}