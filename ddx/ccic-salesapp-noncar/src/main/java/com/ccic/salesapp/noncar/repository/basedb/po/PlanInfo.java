package com.ccic.salesapp.noncar.repository.basedb.po;

import java.math.BigDecimal;
import java.util.Date;

public class PlanInfo {
    private Integer id;

    private Integer strategyId;

    private Long prdPlanDefId;

    private Integer draftStrategyId;

    private String planCode;

    private String planName;

    private String planVersion;

    private String planBatch;

    private String orgSum;

    private String channelSum;

    private Integer userVisibleRange;

    private Integer status;

    private Integer putOnMethod;

    private Date putOnTime;

    private Integer recordStatus;

    private Integer customElement;

    private String testResult;

    private String policyNature;

    private Integer backDatingDays;

    private String planListType;

    private String insuredListType;

    private String planExpenseType;

    private Integer insuredAgeUpperlimit;

    private String insuredAgeUppertype;

    private Integer insuredAgeLowerlimit;

    private String insuredAgeLowertype;

    private String projectName;

    private String isCheckCoverageRela;

    private String ifUnifyIssuePlan;

    private String ifSubInsured;

    private Integer serviceProviderId;

    private Integer serviceContractId;

    private BigDecimal serviceFeeRate;

    private String teamNo;

    private String serviceType;

    private String healthSpecialBusinessType;

    private String vehicleUseNature;

    private Integer approvalSeatCount;

    private String schoolType;

    private String schoolNature;

    private Integer insuredLimit;

    private Integer coInsuredNumber;

    private String isSmallAmount;

    private String rescueCompanyCode;

    private String rescueModelCode;

    private String rescuePhone;

    private Integer rescueType;

    private String ifPeriodFixed;

    private String fixCoveragePeriod;

    private Integer fixCoverageYear;

    private String coveragePeriod;

    private Integer coverageYear;

    private String ifSumFixed;

    private String ifCtFixed;

    private String drivingMethod;

    private String groupDescription;

    private String guaranteeType;

    private String applicationType;

    private String autoCategory;

    private String customCategory;

    private String planCategory;

    private String manualCode;

    private String isHaveCompulsoryIns;

    private String premiumRate;

    private String uwParam;

    private String channelParam;

    private String underwritingType;

    private BigDecimal premiumMin;

    private Integer copyMin;

    private Integer copyMax;

    private String isElecSubmission;

    private String isRenew;

    private String renewPlanCode;

    private String activeFlag;

    private String activeType;

    private Integer activeDays;

    private String healthDeclareCheck;

    private String isSendSms;

    private String smsSendCustRoleCode;

    private String smsTemplateId;

    private String docVersion;

    private String giPeriodType;

    private String giCoveragePeriod;

    private String giStartDate;

    private String buizProductId;

    private String insuredSex;

    private BigDecimal maxNewVehiclePrice;

    private BigDecimal minNewVehiclePrice;

    private BigDecimal realValue;

    private BigDecimal maxRealValue;

    private BigDecimal minRealValue;

    private Integer approvalSeatMax;

    private Integer arprovalSeatMin;

    private String modelCateCode;

    private String renewFlag;

    private String healthStatus;

    private String customerLevel;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
    
    private String secondLine;
    
    private String prdPlanCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public Long getPrdPlanDefId() {
        return prdPlanDefId;
    }

    public void setPrdPlanDefId(Long prdPlanDefId) {
        this.prdPlanDefId = prdPlanDefId;
    }

    public Integer getDraftStrategyId() {
        return draftStrategyId;
    }

    public void setDraftStrategyId(Integer draftStrategyId) {
        this.draftStrategyId = draftStrategyId;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode == null ? null : planCode.trim();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getPlanVersion() {
        return planVersion;
    }

    public void setPlanVersion(String planVersion) {
        this.planVersion = planVersion == null ? null : planVersion.trim();
    }

    public String getPlanBatch() {
        return planBatch;
    }

    public void setPlanBatch(String planBatch) {
        this.planBatch = planBatch == null ? null : planBatch.trim();
    }

    public String getOrgSum() {
        return orgSum;
    }

    public void setOrgSum(String orgSum) {
        this.orgSum = orgSum == null ? null : orgSum.trim();
    }

    public String getChannelSum() {
        return channelSum;
    }

    public void setChannelSum(String channelSum) {
        this.channelSum = channelSum == null ? null : channelSum.trim();
    }

    public Integer getUserVisibleRange() {
        return userVisibleRange;
    }

    public void setUserVisibleRange(Integer userVisibleRange) {
        this.userVisibleRange = userVisibleRange;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPutOnMethod() {
        return putOnMethod;
    }

    public void setPutOnMethod(Integer putOnMethod) {
        this.putOnMethod = putOnMethod;
    }

    public Date getPutOnTime() {
        return putOnTime;
    }

    public void setPutOnTime(Date putOnTime) {
        this.putOnTime = putOnTime;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Integer getCustomElement() {
        return customElement;
    }

    public void setCustomElement(Integer customElement) {
        this.customElement = customElement;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult == null ? null : testResult.trim();
    }

    public String getPolicyNature() {
        return policyNature;
    }

    public void setPolicyNature(String policyNature) {
        this.policyNature = policyNature == null ? null : policyNature.trim();
    }

    public Integer getBackDatingDays() {
        return backDatingDays;
    }

    public void setBackDatingDays(Integer backDatingDays) {
        this.backDatingDays = backDatingDays;
    }

    public String getPlanListType() {
        return planListType;
    }

    public void setPlanListType(String planListType) {
        this.planListType = planListType == null ? null : planListType.trim();
    }

    public String getInsuredListType() {
        return insuredListType;
    }

    public void setInsuredListType(String insuredListType) {
        this.insuredListType = insuredListType == null ? null : insuredListType.trim();
    }

    public String getPlanExpenseType() {
        return planExpenseType;
    }

    public void setPlanExpenseType(String planExpenseType) {
        this.planExpenseType = planExpenseType == null ? null : planExpenseType.trim();
    }

    public Integer getInsuredAgeUpperlimit() {
        return insuredAgeUpperlimit;
    }

    public void setInsuredAgeUpperlimit(Integer insuredAgeUpperlimit) {
        this.insuredAgeUpperlimit = insuredAgeUpperlimit;
    }

    public String getInsuredAgeUppertype() {
        return insuredAgeUppertype;
    }

    public void setInsuredAgeUppertype(String insuredAgeUppertype) {
        this.insuredAgeUppertype = insuredAgeUppertype == null ? null : insuredAgeUppertype.trim();
    }

    public Integer getInsuredAgeLowerlimit() {
        return insuredAgeLowerlimit;
    }

    public void setInsuredAgeLowerlimit(Integer insuredAgeLowerlimit) {
        this.insuredAgeLowerlimit = insuredAgeLowerlimit;
    }

    public String getInsuredAgeLowertype() {
        return insuredAgeLowertype;
    }

    public void setInsuredAgeLowertype(String insuredAgeLowertype) {
        this.insuredAgeLowertype = insuredAgeLowertype == null ? null : insuredAgeLowertype.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getIsCheckCoverageRela() {
        return isCheckCoverageRela;
    }

    public void setIsCheckCoverageRela(String isCheckCoverageRela) {
        this.isCheckCoverageRela = isCheckCoverageRela == null ? null : isCheckCoverageRela.trim();
    }

    public String getIfUnifyIssuePlan() {
        return ifUnifyIssuePlan;
    }

    public void setIfUnifyIssuePlan(String ifUnifyIssuePlan) {
        this.ifUnifyIssuePlan = ifUnifyIssuePlan == null ? null : ifUnifyIssuePlan.trim();
    }

    public String getIfSubInsured() {
        return ifSubInsured;
    }

    public void setIfSubInsured(String ifSubInsured) {
        this.ifSubInsured = ifSubInsured == null ? null : ifSubInsured.trim();
    }

    public Integer getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Integer serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public Integer getServiceContractId() {
        return serviceContractId;
    }

    public void setServiceContractId(Integer serviceContractId) {
        this.serviceContractId = serviceContractId;
    }

    public BigDecimal getServiceFeeRate() {
        return serviceFeeRate;
    }

    public void setServiceFeeRate(BigDecimal serviceFeeRate) {
        this.serviceFeeRate = serviceFeeRate;
    }

    public String getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(String teamNo) {
        this.teamNo = teamNo == null ? null : teamNo.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getHealthSpecialBusinessType() {
        return healthSpecialBusinessType;
    }

    public void setHealthSpecialBusinessType(String healthSpecialBusinessType) {
        this.healthSpecialBusinessType = healthSpecialBusinessType == null ? null : healthSpecialBusinessType.trim();
    }

    public String getVehicleUseNature() {
        return vehicleUseNature;
    }

    public void setVehicleUseNature(String vehicleUseNature) {
        this.vehicleUseNature = vehicleUseNature == null ? null : vehicleUseNature.trim();
    }

    public Integer getApprovalSeatCount() {
        return approvalSeatCount;
    }

    public void setApprovalSeatCount(Integer approvalSeatCount) {
        this.approvalSeatCount = approvalSeatCount;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType == null ? null : schoolType.trim();
    }

    public String getSchoolNature() {
        return schoolNature;
    }

    public void setSchoolNature(String schoolNature) {
        this.schoolNature = schoolNature == null ? null : schoolNature.trim();
    }

    public Integer getInsuredLimit() {
        return insuredLimit;
    }

    public void setInsuredLimit(Integer insuredLimit) {
        this.insuredLimit = insuredLimit;
    }

    public Integer getCoInsuredNumber() {
        return coInsuredNumber;
    }

    public void setCoInsuredNumber(Integer coInsuredNumber) {
        this.coInsuredNumber = coInsuredNumber;
    }

    public String getIsSmallAmount() {
        return isSmallAmount;
    }

    public void setIsSmallAmount(String isSmallAmount) {
        this.isSmallAmount = isSmallAmount == null ? null : isSmallAmount.trim();
    }

    public String getRescueCompanyCode() {
        return rescueCompanyCode;
    }

    public void setRescueCompanyCode(String rescueCompanyCode) {
        this.rescueCompanyCode = rescueCompanyCode == null ? null : rescueCompanyCode.trim();
    }

    public String getRescueModelCode() {
        return rescueModelCode;
    }

    public void setRescueModelCode(String rescueModelCode) {
        this.rescueModelCode = rescueModelCode == null ? null : rescueModelCode.trim();
    }

    public String getRescuePhone() {
        return rescuePhone;
    }

    public void setRescuePhone(String rescuePhone) {
        this.rescuePhone = rescuePhone == null ? null : rescuePhone.trim();
    }

    public Integer getRescueType() {
        return rescueType;
    }

    public void setRescueType(Integer rescueType) {
        this.rescueType = rescueType;
    }

    public String getIfPeriodFixed() {
        return ifPeriodFixed;
    }

    public void setIfPeriodFixed(String ifPeriodFixed) {
        this.ifPeriodFixed = ifPeriodFixed == null ? null : ifPeriodFixed.trim();
    }

    public String getFixCoveragePeriod() {
        return fixCoveragePeriod;
    }

    public void setFixCoveragePeriod(String fixCoveragePeriod) {
        this.fixCoveragePeriod = fixCoveragePeriod == null ? null : fixCoveragePeriod.trim();
    }

    public Integer getFixCoverageYear() {
        return fixCoverageYear;
    }

    public void setFixCoverageYear(Integer fixCoverageYear) {
        this.fixCoverageYear = fixCoverageYear;
    }

    public String getCoveragePeriod() {
        return coveragePeriod;
    }

    public void setCoveragePeriod(String coveragePeriod) {
        this.coveragePeriod = coveragePeriod == null ? null : coveragePeriod.trim();
    }

    public Integer getCoverageYear() {
        return coverageYear;
    }

    public void setCoverageYear(Integer coverageYear) {
        this.coverageYear = coverageYear;
    }

    public String getIfSumFixed() {
        return ifSumFixed;
    }

    public void setIfSumFixed(String ifSumFixed) {
        this.ifSumFixed = ifSumFixed == null ? null : ifSumFixed.trim();
    }

    public String getIfCtFixed() {
        return ifCtFixed;
    }

    public void setIfCtFixed(String ifCtFixed) {
        this.ifCtFixed = ifCtFixed == null ? null : ifCtFixed.trim();
    }

    public String getDrivingMethod() {
        return drivingMethod;
    }

    public void setDrivingMethod(String drivingMethod) {
        this.drivingMethod = drivingMethod == null ? null : drivingMethod.trim();
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription == null ? null : groupDescription.trim();
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType == null ? null : guaranteeType.trim();
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType == null ? null : applicationType.trim();
    }

    public String getAutoCategory() {
        return autoCategory;
    }

    public void setAutoCategory(String autoCategory) {
        this.autoCategory = autoCategory == null ? null : autoCategory.trim();
    }

    public String getCustomCategory() {
        return customCategory;
    }

    public void setCustomCategory(String customCategory) {
        this.customCategory = customCategory == null ? null : customCategory.trim();
    }

    public String getPlanCategory() {
        return planCategory;
    }

    public void setPlanCategory(String planCategory) {
        this.planCategory = planCategory == null ? null : planCategory.trim();
    }

    public String getManualCode() {
        return manualCode;
    }

    public void setManualCode(String manualCode) {
        this.manualCode = manualCode == null ? null : manualCode.trim();
    }

    public String getIsHaveCompulsoryIns() {
        return isHaveCompulsoryIns;
    }

    public void setIsHaveCompulsoryIns(String isHaveCompulsoryIns) {
        this.isHaveCompulsoryIns = isHaveCompulsoryIns == null ? null : isHaveCompulsoryIns.trim();
    }

    public String getPremiumRate() {
        return premiumRate;
    }

    public void setPremiumRate(String premiumRate) {
        this.premiumRate = premiumRate == null ? null : premiumRate.trim();
    }

    public String getUwParam() {
        return uwParam;
    }

    public void setUwParam(String uwParam) {
        this.uwParam = uwParam == null ? null : uwParam.trim();
    }

    public String getChannelParam() {
        return channelParam;
    }

    public void setChannelParam(String channelParam) {
        this.channelParam = channelParam == null ? null : channelParam.trim();
    }

    public String getUnderwritingType() {
        return underwritingType;
    }

    public void setUnderwritingType(String underwritingType) {
        this.underwritingType = underwritingType == null ? null : underwritingType.trim();
    }

    public BigDecimal getPremiumMin() {
        return premiumMin;
    }

    public void setPremiumMin(BigDecimal premiumMin) {
        this.premiumMin = premiumMin;
    }

    public Integer getCopyMin() {
        return copyMin;
    }

    public void setCopyMin(Integer copyMin) {
        this.copyMin = copyMin;
    }

    public Integer getCopyMax() {
        return copyMax;
    }

    public void setCopyMax(Integer copyMax) {
        this.copyMax = copyMax;
    }

    public String getIsElecSubmission() {
        return isElecSubmission;
    }

    public void setIsElecSubmission(String isElecSubmission) {
        this.isElecSubmission = isElecSubmission == null ? null : isElecSubmission.trim();
    }

    public String getIsRenew() {
        return isRenew;
    }

    public void setIsRenew(String isRenew) {
        this.isRenew = isRenew == null ? null : isRenew.trim();
    }

    public String getRenewPlanCode() {
        return renewPlanCode;
    }

    public void setRenewPlanCode(String renewPlanCode) {
        this.renewPlanCode = renewPlanCode == null ? null : renewPlanCode.trim();
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag == null ? null : activeFlag.trim();
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType == null ? null : activeType.trim();
    }

    public Integer getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(Integer activeDays) {
        this.activeDays = activeDays;
    }

    public String getHealthDeclareCheck() {
        return healthDeclareCheck;
    }

    public void setHealthDeclareCheck(String healthDeclareCheck) {
        this.healthDeclareCheck = healthDeclareCheck == null ? null : healthDeclareCheck.trim();
    }

    public String getIsSendSms() {
        return isSendSms;
    }

    public void setIsSendSms(String isSendSms) {
        this.isSendSms = isSendSms == null ? null : isSendSms.trim();
    }

    public String getSmsSendCustRoleCode() {
        return smsSendCustRoleCode;
    }

    public void setSmsSendCustRoleCode(String smsSendCustRoleCode) {
        this.smsSendCustRoleCode = smsSendCustRoleCode == null ? null : smsSendCustRoleCode.trim();
    }

    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId == null ? null : smsTemplateId.trim();
    }

    public String getDocVersion() {
        return docVersion;
    }

    public void setDocVersion(String docVersion) {
        this.docVersion = docVersion == null ? null : docVersion.trim();
    }

    public String getGiPeriodType() {
        return giPeriodType;
    }

    public void setGiPeriodType(String giPeriodType) {
        this.giPeriodType = giPeriodType == null ? null : giPeriodType.trim();
    }

    public String getGiCoveragePeriod() {
        return giCoveragePeriod;
    }

    public void setGiCoveragePeriod(String giCoveragePeriod) {
        this.giCoveragePeriod = giCoveragePeriod == null ? null : giCoveragePeriod.trim();
    }

    public String getGiStartDate() {
        return giStartDate;
    }

    public void setGiStartDate(String giStartDate) {
        this.giStartDate = giStartDate == null ? null : giStartDate.trim();
    }

    public String getBuizProductId() {
        return buizProductId;
    }

    public void setBuizProductId(String buizProductId) {
        this.buizProductId = buizProductId == null ? null : buizProductId.trim();
    }

    public String getInsuredSex() {
        return insuredSex;
    }

    public void setInsuredSex(String insuredSex) {
        this.insuredSex = insuredSex == null ? null : insuredSex.trim();
    }

    public BigDecimal getMaxNewVehiclePrice() {
        return maxNewVehiclePrice;
    }

    public void setMaxNewVehiclePrice(BigDecimal maxNewVehiclePrice) {
        this.maxNewVehiclePrice = maxNewVehiclePrice;
    }

    public BigDecimal getMinNewVehiclePrice() {
        return minNewVehiclePrice;
    }

    public void setMinNewVehiclePrice(BigDecimal minNewVehiclePrice) {
        this.minNewVehiclePrice = minNewVehiclePrice;
    }

    public BigDecimal getRealValue() {
        return realValue;
    }

    public void setRealValue(BigDecimal realValue) {
        this.realValue = realValue;
    }

    public BigDecimal getMaxRealValue() {
        return maxRealValue;
    }

    public void setMaxRealValue(BigDecimal maxRealValue) {
        this.maxRealValue = maxRealValue;
    }

    public BigDecimal getMinRealValue() {
        return minRealValue;
    }

    public void setMinRealValue(BigDecimal minRealValue) {
        this.minRealValue = minRealValue;
    }

    public Integer getApprovalSeatMax() {
        return approvalSeatMax;
    }

    public void setApprovalSeatMax(Integer approvalSeatMax) {
        this.approvalSeatMax = approvalSeatMax;
    }

    public Integer getArprovalSeatMin() {
        return arprovalSeatMin;
    }

    public void setArprovalSeatMin(Integer arprovalSeatMin) {
        this.arprovalSeatMin = arprovalSeatMin;
    }

    public String getModelCateCode() {
        return modelCateCode;
    }

    public void setModelCateCode(String modelCateCode) {
        this.modelCateCode = modelCateCode == null ? null : modelCateCode.trim();
    }

    public String getRenewFlag() {
        return renewFlag;
    }

    public void setRenewFlag(String renewFlag) {
        this.renewFlag = renewFlag == null ? null : renewFlag.trim();
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus == null ? null : healthStatus.trim();
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel == null ? null : customerLevel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

	public String getSecondLine() {
		return secondLine;
	}

	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	public String getPrdPlanCategory() {
		return prdPlanCategory;
	}

	public void setPrdPlanCategory(String prdPlanCategory) {
		this.prdPlanCategory = prdPlanCategory;
	}
	
	
    
}