package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;


@Data
public class TPrdPlanInfo {
	
	@JSONField(name="Id") 
	private Long id;

	@JSONField(name="PlanInfoId") 
	private Long planInfoId;

	@JSONField(name="PlanDefId") 
	private Long planDefId;

	@JSONField(name="PolicyNature") 
	private String policyNature;

	@JSONField(name="BackDatingDays") 
	private Long backDatingDays;

	@JSONField(name="PlanListType")
	private String planListType;

	@JSONField(name="InsuredListType") 
	private String insuredListType;

	@JSONField(name="PlanExpenseType") 
	private String planExpenseType;

	@JSONField(name="InsuredAgeUpperLimit") 
	private Long insuredAgeUpperlimit;

	@JSONField(name="InsuredAgeUpperType") 
	private String insuredAgeUppertype;

	@JSONField(name="InsuredAgeLowerLimit") 
	private Long insuredAgeLowerlimit;

	@JSONField(name="InsuredAgeLowerType") 
	private String insuredAgeLowertype;

	@JSONField(name="ProjectName") 
	private String projectName;

	@JSONField(name="IsCheckCoverageRela") 
	private String isCheckCoverageRela;

	@JSONField(name="IfUnifyIssuePlan") 
	private String ifUnifyIssuePlan;

	@JSONField(name="IfSubInsured") 
	private String ifSubInsured;

	@JSONField(name="ServiceProviderId") 
	private Long serviceProviderId;

	@JSONField(name="ServiceContractId") 
	private Long serviceContractId;

	@JSONField(name="ServiceFeeRate") 
	private BigDecimal serviceFeeRate;

	@JSONField(name="TeamNo") 
	private String teamNo;

	@JSONField(name="ServiceType") 
	private String serviceType;

	@JSONField(name="HealthSpecialBusinessType") 
	private String healthSpecialBusinessType;

	@JSONField(name="VehicleUseNature") 
	private String vehicleUseNature;

	@JSONField(name="ApprovalSeatCount") 
	private Long approvalSeatCount;

	@JSONField(name="SchoolType") 
	private String schoolType;

	@JSONField(name="SchoolNature") 
	private String schoolNature;

	@JSONField(name="InsuredLimit") 
	private Long insuredLimit;

	@JSONField(name="CoInsuredNumber")
	private Long coInsuredNumber;

	@JSONField(name="IsSmallAmount")
	private String isSmallAmount;

	@JSONField(name="RescueCompanyCode") 
	private String rescueCompanyCode;

	@JSONField(name="RescueModelCode") 
	private String rescueModelCode;

	@JSONField(name="RescuePhone")
	private String rescuePhone;

	@JSONField(name="RescueType") 
	private Long rescueType;

	@JSONField(name="IfPeriodFixed") 
	private String ifPeriodFixed;

	@JSONField(name="FfixCoveragePeriod")
	private String fixCoveragePeriod;

	@JSONField(name="FixCoverageYear")
	private Long fixCoverageYear;

	@JSONField(name="CoveragePeriod")
	private String coveragePeriod;

	@JSONField(name="CoverageYear")
	private Long coverageYear;

	@JSONField(name="IfSumFixed")
	private String ifSumFixed;

	@JSONField(name="IfCtFixed")
	private String ifCtFixed;

	@JSONField(name="DrivingMethod")
	private String drivingMethod;

	@JSONField(name="GroupDescription") 
	private String groupDescription;

	@JSONField(name="GuaranteeType")
	private String guaranteeType;

	@JSONField(name="ApplicationType")
	private String applicationType;

	@JSONField(name="AutoCategory") 
	private String autoCategory;

	@JSONField(name="CustomCategory")
	private String customCategory;

	@JSONField(name="PlanCategory")
	private String planCategory;

	@JSONField(name="ManualCode") 
	private String manualCode;

	@JSONField(name="IsHaveCompulsoryIns")
	private String isHaveCompulsoryIns;

	@JSONField(name="PremiumRate") 
	private String premiumRate;

	@JSONField(name="UwParam") 
	private String uwParam;

	@JSONField(name="ChannelParam") 
	private String channelParam;

	@JSONField(name="UnderwritingType") 
	private String underwritingType;

	@JSONField(name="CopyMin")
	private Long copyMin;

	@JSONField(name="CopyMax")
	private Long copyMax;

	@JSONField(name="IsElecSubmission")
	private String isElecSubmission;

	@JSONField(name="IsRenew")
	private String isRenew;

	@JSONField(name="RenewPlanCode")
	private String renewPlanCode;

	@JSONField(name="ActiveFlag") 
	private String activeFlag;

	@JSONField(name="ActiveType")
	private String activeType;

	@JSONField(name="ActiveDays")
	private Long activeDays;

	@JSONField(name="HealthDeclareCheck")
	private String healthDeclareCheck;

	@JSONField(name="IsSendSms")
	private String isSendSms;

	@JSONField(name="SmsSendCustRoleCode") 
	private String smsSendCustRoleCode;

	@JSONField(name="SmsTemplateId")
	private String smsTemplateId;

	@JSONField(name="DocVersion") 
	private String docVersion;

	@JSONField(name="GiPeriodType")
	private String giPeriodType;

	@JSONField(name="GiCoveragePeriod")
	private String giCoveragePeriod;

	@JSONField(name="GiStartDate")
	private String giStartDate;

	@JSONField(name="BuizProductId")
	private String buizProductId;

	@JSONField(name="InsuredSex")
	private String insuredSex;

	@JSONField(name="MaxNewVehiclePrice")
	private BigDecimal maxNewVehiclePrice;

	@JSONField(name="MinNewVehiclePrice")
	private BigDecimal minNewVehiclePrice;

	@JSONField(name="RealValue") 
	private BigDecimal realValue;

	@JSONField(name="MaxRealValue")
	private BigDecimal maxRealValue;

	@JSONField(name="MinRealValue")
	private BigDecimal minRealValue;

	@JSONField(name="ModelCateCode")
	private String modelCateCode;

	@JSONField(name="RenewFlag")
	private String renewFlag;

	@JSONField(name="HealthStatus") 
	private String healthStatus;

	@JSONField(name="CustomerLevel")
	private String customerLevel;

	@JSONField(name="ApprovalSeatMax")
	private Long approvalSeatMax;

	@JSONField(name="ApprovalSeatMin")
	private Long approvalSeatMin;

	@JSONField(name="BuizProductCode")
	private String buizProductCode;
	
	@JSONField(name="BuizBusinessLine")
	private String buizBusinessLine;
	
	@JSONField(name="BuizSecondLine")
	private String buizSecondLine;
	
    @JSONField(name="FamilyInsuredCountLimit")
    private Long familyInsuredCountLimit;
}