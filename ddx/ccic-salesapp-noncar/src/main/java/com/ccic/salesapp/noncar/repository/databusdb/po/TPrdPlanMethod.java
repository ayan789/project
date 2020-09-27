package com.ccic.salesapp.noncar.repository.databusdb.po;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class TPrdPlanMethod {
	
	@JSONField(name="Id")
    private Long id;

	@JSONField(name="BuizBusinessLine")
    private String buizBusinessLine;
	
	@JSONField(name="BuizProductLine")
    private String buizProductLine;

	@JSONField(name="BuizProductId")
    private Long buizProductId;

	@JSONField(name="VehicleUseNatureCode")
    private String vehicleUseNatureCode;

	@JSONField(name="InsuredAge")
    private String insuredAge;

	@JSONField(name="InsuredAgeUppertype")
    private String insuredAgeUppertype;

	@JSONField(name="InsuredAgeUpperlimit")
    private Long insuredAgeUpperlimit;

	@JSONField(name="InsuredAgeLowertype")
    private String insuredAgeLowertype;

	@JSONField(name="InsuredAgeLowerlimit")
    private Long insuredAgeLowerlimit;

	@JSONField(name="InsuredSex")
    private String insuredSex;

	@JSONField(name="InsuredOccupationType")
    private String insuredOccupationType;

	@JSONField(name="IdInfo")
    private String idInfo;

	@JSONField(name="ChannelInfo")
    private String channelInfo;

	@JSONField(name="NewVehiclePurchasePrice")
    private String newVehiclePurchasePrice;

	@JSONField(name="RealValue")
    private String realValue;

	@JSONField(name="BrandCode")
    private String brandCode;

	@JSONField(name="ModelCateCode")
    private String modelCateCode;

	@JSONField(name="RenewFlag")
    private String renewFlag;

	@JSONField(name="VehiclePremium")
    private String vehiclePremium;

	@JSONField(name="HealthStatus")
    private String healthStatus;

	@JSONField(name="CustomerLevel")
    private String customerLevel;

	@JSONField(name="PremiumCar")
    private String premiumCar;

	@JSONField(name="RateSeatMax")
    private Long rateSeatMax;

	@JSONField(name="RateSeatMin")
    private Long rateSeatMin;

	@JSONField(name="PlanMethodId")
    private Long planMethodId;

	@JSONField(name="PlanDefId")
    private Long planDefId;

	@JSONField(name="MethodCode")
    private String methodCode;

	@JSONField(name="MethodParentCode")
    private String methodParentCode;

	@JSONField(name="MethodNo")
    private String methodNo;

	@JSONField(name="MethodCategory")
    private String methodCategory;

	@JSONField(name="BuizSource")
    private String buizSource;

	@JSONField(name="ChannelGroupA")
    private String channelGroupA;

	@JSONField(name="ChannelGroupB")
    private String channelGroupB;

	@JSONField(name="SpecialName")
    private String specialName;

	@JSONField(name="PolicyType")
    private String policyType;

	@JSONField(name="ElectronicPolicyTemplateId")
    private String electronicPolicyTemplateId;

	@JSONField(name="SendCt")
    private String sendCt;

	@JSONField(name="LogoForPrintCode")
    private String logoForPrintCode;

	@JSONField(name="IsLoadVisaLetter")
    private String isLoadVisaLetter;

	@JSONField(name="ServiceInstruction")
    private String serviceInstruction;

	@JSONField(name="CoverNotice")
    private String coverNotice;

	@JSONField(name="ClaimNotice")
    private String claimNotice;

	@JSONField(name="IcbcNotice")
    private String icbcNotice;

	@JSONField(name="IsSendSms")
    private String isSendSms;

	@JSONField(name="SmsSendCustRoleCode")
    private String smsSendCustRoleCode;

	@JSONField(name="SmsTemplateId")
    private String smsTemplateId;

	@JSONField(name="DocVersion")
    private String docVersion;

	@JSONField(name="MobileLink")
    private String mobileLink;

}