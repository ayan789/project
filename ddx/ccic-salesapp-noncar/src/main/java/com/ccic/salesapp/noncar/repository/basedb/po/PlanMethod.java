package com.ccic.salesapp.noncar.repository.basedb.po;

import java.math.BigDecimal;
import java.util.Date;

public class PlanMethod {
    private Integer id;

    private Integer planId;

    private Integer buizBusinessLine;

    private Integer buizProductLine;

    private Integer buizProductId;

    private String vehicleUseNatureCode;

    private String insuredOccupationType;

    private String idInfo;

    private String channelInfo;

    private String newVehiclePurchasePrice;

    private String brandCode;

    private String vehiclePremium;

    private String premiumCar;

    private Integer rateSeatMax;

    private Integer rateSeatMin;

    private String methodCode;

    private String methodParentCode;

    private String methodNo;

    private String methodCategory;

    private String buizSource;

    private String channelGroupA;

    private String channelGroupB;

    private String specialName;

    private String policyType;

    private String electronicPolicyTemplateId;

    private String sendCt;

    private String logoForPrintCode;

    private String isLoadVisaLetter;

    private String serviceInstruction;

    private String coverNotice;

    private String claimNotice;

    private String icbcNotice;

    private String isSendSms;

    private String smsSendCustRoleCode;

    private String smsTemplateId;

    private String docVersion;

    private String mobileLink;

    private BigDecimal minInsureAmount;

    private Integer allowPartnerBill;

    private BigDecimal maxLoadMass;

    private BigDecimal minLoadMass;

    private String petAgeUppertype;

    private Integer petAgeUpperlimit;

    private String petAgeLowertype;

    private Integer petAgeLowerlimit;

    private Integer hasSocialInsurance;

    private Integer insurantUpperlimit;

    private Integer insurantLowerlimit;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getBuizBusinessLine() {
        return buizBusinessLine;
    }

    public void setBuizBusinessLine(Integer buizBusinessLine) {
        this.buizBusinessLine = buizBusinessLine;
    }

    public Integer getBuizProductLine() {
        return buizProductLine;
    }

    public void setBuizProductLine(Integer buizProductLine) {
        this.buizProductLine = buizProductLine;
    }

    public Integer getBuizProductId() {
        return buizProductId;
    }

    public void setBuizProductId(Integer buizProductId) {
        this.buizProductId = buizProductId;
    }

    public String getVehicleUseNatureCode() {
        return vehicleUseNatureCode;
    }

    public void setVehicleUseNatureCode(String vehicleUseNatureCode) {
        this.vehicleUseNatureCode = vehicleUseNatureCode == null ? null : vehicleUseNatureCode.trim();
    }

    public String getInsuredOccupationType() {
        return insuredOccupationType;
    }

    public void setInsuredOccupationType(String insuredOccupationType) {
        this.insuredOccupationType = insuredOccupationType == null ? null : insuredOccupationType.trim();
    }

    public String getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(String idInfo) {
        this.idInfo = idInfo == null ? null : idInfo.trim();
    }

    public String getChannelInfo() {
        return channelInfo;
    }

    public void setChannelInfo(String channelInfo) {
        this.channelInfo = channelInfo == null ? null : channelInfo.trim();
    }

    public String getNewVehiclePurchasePrice() {
        return newVehiclePurchasePrice;
    }

    public void setNewVehiclePurchasePrice(String newVehiclePurchasePrice) {
        this.newVehiclePurchasePrice = newVehiclePurchasePrice == null ? null : newVehiclePurchasePrice.trim();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getVehiclePremium() {
        return vehiclePremium;
    }

    public void setVehiclePremium(String vehiclePremium) {
        this.vehiclePremium = vehiclePremium == null ? null : vehiclePremium.trim();
    }

    public String getPremiumCar() {
        return premiumCar;
    }

    public void setPremiumCar(String premiumCar) {
        this.premiumCar = premiumCar == null ? null : premiumCar.trim();
    }

    public Integer getRateSeatMax() {
        return rateSeatMax;
    }

    public void setRateSeatMax(Integer rateSeatMax) {
        this.rateSeatMax = rateSeatMax;
    }

    public Integer getRateSeatMin() {
        return rateSeatMin;
    }

    public void setRateSeatMin(Integer rateSeatMin) {
        this.rateSeatMin = rateSeatMin;
    }

    public String getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode == null ? null : methodCode.trim();
    }

    public String getMethodParentCode() {
        return methodParentCode;
    }

    public void setMethodParentCode(String methodParentCode) {
        this.methodParentCode = methodParentCode == null ? null : methodParentCode.trim();
    }

    public String getMethodNo() {
        return methodNo;
    }

    public void setMethodNo(String methodNo) {
        this.methodNo = methodNo == null ? null : methodNo.trim();
    }

    public String getMethodCategory() {
        return methodCategory;
    }

    public void setMethodCategory(String methodCategory) {
        this.methodCategory = methodCategory == null ? null : methodCategory.trim();
    }

    public String getBuizSource() {
        return buizSource;
    }

    public void setBuizSource(String buizSource) {
        this.buizSource = buizSource == null ? null : buizSource.trim();
    }

    public String getChannelGroupA() {
        return channelGroupA;
    }

    public void setChannelGroupA(String channelGroupA) {
        this.channelGroupA = channelGroupA == null ? null : channelGroupA.trim();
    }

    public String getChannelGroupB() {
        return channelGroupB;
    }

    public void setChannelGroupB(String channelGroupB) {
        this.channelGroupB = channelGroupB == null ? null : channelGroupB.trim();
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName == null ? null : specialName.trim();
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType == null ? null : policyType.trim();
    }

    public String getElectronicPolicyTemplateId() {
        return electronicPolicyTemplateId;
    }

    public void setElectronicPolicyTemplateId(String electronicPolicyTemplateId) {
        this.electronicPolicyTemplateId = electronicPolicyTemplateId == null ? null : electronicPolicyTemplateId.trim();
    }

    public String getSendCt() {
        return sendCt;
    }

    public void setSendCt(String sendCt) {
        this.sendCt = sendCt == null ? null : sendCt.trim();
    }

    public String getLogoForPrintCode() {
        return logoForPrintCode;
    }

    public void setLogoForPrintCode(String logoForPrintCode) {
        this.logoForPrintCode = logoForPrintCode == null ? null : logoForPrintCode.trim();
    }

    public String getIsLoadVisaLetter() {
        return isLoadVisaLetter;
    }

    public void setIsLoadVisaLetter(String isLoadVisaLetter) {
        this.isLoadVisaLetter = isLoadVisaLetter == null ? null : isLoadVisaLetter.trim();
    }

    public String getServiceInstruction() {
        return serviceInstruction;
    }

    public void setServiceInstruction(String serviceInstruction) {
        this.serviceInstruction = serviceInstruction == null ? null : serviceInstruction.trim();
    }

    public String getCoverNotice() {
        return coverNotice;
    }

    public void setCoverNotice(String coverNotice) {
        this.coverNotice = coverNotice == null ? null : coverNotice.trim();
    }

    public String getClaimNotice() {
        return claimNotice;
    }

    public void setClaimNotice(String claimNotice) {
        this.claimNotice = claimNotice == null ? null : claimNotice.trim();
    }

    public String getIcbcNotice() {
        return icbcNotice;
    }

    public void setIcbcNotice(String icbcNotice) {
        this.icbcNotice = icbcNotice == null ? null : icbcNotice.trim();
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

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink == null ? null : mobileLink.trim();
    }

    public BigDecimal getMinInsureAmount() {
        return minInsureAmount;
    }

    public void setMinInsureAmount(BigDecimal minInsureAmount) {
        this.minInsureAmount = minInsureAmount;
    }

    public Integer getAllowPartnerBill() {
        return allowPartnerBill;
    }

    public void setAllowPartnerBill(Integer allowPartnerBill) {
        this.allowPartnerBill = allowPartnerBill;
    }

    public BigDecimal getMaxLoadMass() {
        return maxLoadMass;
    }

    public void setMaxLoadMass(BigDecimal maxLoadMass) {
        this.maxLoadMass = maxLoadMass;
    }

    public BigDecimal getMinLoadMass() {
        return minLoadMass;
    }

    public void setMinLoadMass(BigDecimal minLoadMass) {
        this.minLoadMass = minLoadMass;
    }

    public String getPetAgeUppertype() {
        return petAgeUppertype;
    }

    public void setPetAgeUppertype(String petAgeUppertype) {
        this.petAgeUppertype = petAgeUppertype == null ? null : petAgeUppertype.trim();
    }

    public Integer getPetAgeUpperlimit() {
        return petAgeUpperlimit;
    }

    public void setPetAgeUpperlimit(Integer petAgeUpperlimit) {
        this.petAgeUpperlimit = petAgeUpperlimit;
    }

    public String getPetAgeLowertype() {
        return petAgeLowertype;
    }

    public void setPetAgeLowertype(String petAgeLowertype) {
        this.petAgeLowertype = petAgeLowertype == null ? null : petAgeLowertype.trim();
    }

    public Integer getPetAgeLowerlimit() {
        return petAgeLowerlimit;
    }

    public void setPetAgeLowerlimit(Integer petAgeLowerlimit) {
        this.petAgeLowerlimit = petAgeLowerlimit;
    }

    public Integer getHasSocialInsurance() {
        return hasSocialInsurance;
    }

    public void setHasSocialInsurance(Integer hasSocialInsurance) {
        this.hasSocialInsurance = hasSocialInsurance;
    }

    public Integer getInsurantUpperlimit() {
        return insurantUpperlimit;
    }

    public void setInsurantUpperlimit(Integer insurantUpperlimit) {
        this.insurantUpperlimit = insurantUpperlimit;
    }

    public Integer getInsurantLowerlimit() {
        return insurantLowerlimit;
    }

    public void setInsurantLowerlimit(Integer insurantLowerlimit) {
        this.insurantLowerlimit = insurantLowerlimit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}