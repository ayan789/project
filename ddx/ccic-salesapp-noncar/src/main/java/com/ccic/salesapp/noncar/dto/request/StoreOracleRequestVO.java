package com.ccic.salesapp.noncar.dto.request;
import java.util.List;
import com.ccic.salesapp.noncar.dto.AccidentalInjuryInfo;
import com.ccic.salesapp.noncar.dto.AddressInfoVO;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.PupilName;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.VatInfoVO;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("下单")
@Data
public class StoreOracleRequestVO {

	// 页面获取到的信息
	@ApiModelProperty("门店代码")
	private String storeCode;// 门店代码
	@ApiModelProperty("业务员工号、第三方员工工号")
	private String userCode;
	
	@ApiModelProperty("险种代码		")
	private String riskCode;// 险种代码
	@ApiModelProperty("方案代码")
	private String formulaCode;// 方案代码
	@ApiModelProperty("方案名称")
	private String formulaName;// 方案名称
	@ApiModelProperty("方案份数")
	private int formulaSize;// 方案份数
	@ApiModelProperty("起保日期")
	private String startDate;// 起保日期
	@ApiModelProperty("截止日期")
	private String endDate;// 截止日期
	@ApiModelProperty("产品代码")
	private String productCode;// 产品代码
	
	@ApiModelProperty("产品名称")
	private String productName;// 产品代码
	// 投保人信息
	@ApiModelProperty("投保人名称")
	private String appliName;// 投保人名称
	@ApiModelProperty("投保人证件类型")
	private String appliIdentifyType;// 投保人证件类型
	@ApiModelProperty("投保人身份证")
	private String appliIdentifyNumber;// 投保人身份证
	@ApiModelProperty("投保人出生日期")
	private String appliBirthDate;// 投保人出生日期
	@ApiModelProperty("投保人手机号")
	private String appliMobile;// 投保人手机号
	@ApiModelProperty("投保人国籍")
	private String appliNationality;// 投保人国籍
	private String appliEMail; // 投保人邮箱
	@ApiModelProperty("与被保险人关系")
	private String insuredIdentity;// 与被保险人关系
	// 被保险人信息
	@ApiModelProperty("被保险人名称")
	private String insuredName;// 被保险人名称
	@ApiModelProperty("被保险人证件类型")
	private String insuredIdentifyType;// 被保险人证件类型
	@ApiModelProperty("被保险人身份证")
	private String insuredIdentifyNumber;// 被保险人身份证
	@ApiModelProperty("被保险人出生日期")
	private String insuredBirthDate;// 被保险人出生日期
	@ApiModelProperty("被保险人手机号")
	private String insuredMobile;// 被保险人手机号
	@ApiModelProperty("被保人国籍")
	private String insuredNationality;// 被保人国籍
	@ApiModelProperty("被保险人邮箱信息")
	private String insuredEMail;// 被保险人邮箱信息
	private String appliIdentity; // 与投保人关系

	private AgentInfoVO agentInfo; // 代理人信息
	@ApiModelProperty("地址信息")
	private AddressInfoVO addressInfo;// 地址信息
	@ApiModelProperty("是否代理")
	private String isAgent;// 是否代理
	@ApiModelProperty("车牌号")
	private String licensePlateNo;// 车牌号
	@ApiModelProperty("总保费")
	private double sumPremium;// 总保费
	@ApiModelProperty("总保额")
	private double sumAmount;// 总保额
	private double amountBC; // 女性乳腺癌保额
	private double amountOT; // 其他保额
	
	@ApiModelProperty("暂存透出字段")
	private String formatPremium;

	@ApiModelProperty("储存多个保额json数据")
	private String amountsJson;// 储存多个保额json数据
	@ApiModelProperty("储存多个保费json数据")
	private String premiumsJson;// 储存多个保费json数据
	
	private String extensionJson;
	
	private String amountInfoJson;
	
	@ApiModelProperty("保障内容")
	private String addRiskAmountJson;
	@ApiModelProperty("")
	private String addRiskPremiumsJson;

	@ApiModelProperty("app设备号")
	private String imei;// app设备号
	@ApiModelProperty("是否app出单")
	private String isRunApp;// 是否app出单

	@ApiModelProperty("投保单号")
	private String insureNo;// 投保单号
	@ApiModelProperty("投保单号ID")
	private String noCarInsureId;// 投保单号ID
	@ApiModelProperty("业务类型")
	private String bizType;// 业务类型

	// 2016-4-11 学平险新增字段
	@ApiModelProperty("班级")
	private String grade;// 班级
	@ApiModelProperty("学校")
	private String school;// 学校
	// 百万医疗新增的保险
	@ApiModelProperty("年龄周岁")
	private int age;// 年龄周岁
	private String ages; // 选中的年龄范围
	@ApiModelProperty("有无社保")
	private String isSheBao;// 有无社保
	private String insuredSheBaoType; // 社保类型
	@ApiModelProperty("操作类型")
	private String actionType;// 操作类型
	@ApiModelProperty("附加特定疾病特需医疗")
	private String aCTS;// 附加特定疾病特需医疗
	@ApiModelProperty("附件质子重离子医疗保险")
	private String heavyIonMedical;// 附件质子重离子医疗保险
	private String businessDetailTmp;
	@ApiModelProperty("附加扩展恶性肿瘤特需医疗保险")
	private String aKZEXClause;// 附加扩展恶性肿瘤特需医疗保险
	@ApiModelProperty("附加个人恶性肿瘤院外特种药品费用医疗保险")
	private String aEXTZClause;// 附加个人恶性肿瘤院外特种药品费用医疗保险
	@ApiModelProperty("附加个人恶性肿瘤赴日医疗保险")
	private String aEXYLClause;// 附加个人恶性肿瘤赴日医疗保险
	@ApiModelProperty("附加个人恶性肿瘤扩展质子重离子医疗保险")
	private String aEXZZClause;// 附加个人恶性肿瘤扩展质子重离子医疗保险
	// 监护人责任险
	@ApiModelProperty("被监护人性别")
	private String beGuardianSex;// 被监护人性别
	@ApiModelProperty("被监护人生日")
	private String beGuardianBirthDate;// 被监护人生日

	@ApiModelProperty("新华用户标识 ")
	private String nclUserCode;// 新华用户标识
	@ApiModelProperty("归属经办人信息，后台赋值")
	private UserVO handlerInfo;// 归属经办人信息，后台赋值

	// e家无忧险
	@ApiModelProperty("监护人姓名")
	private List<PupilName> pupilNameList;// 监护人姓名
	private List<AccidentalInjuryInfo> accidentalInjuryInfos;// 家庭成员意外伤害保险被保险人信息
	@ApiModelProperty("车牌号")
	private String licenseNo;// 车牌号
	@ApiModelProperty("车架号")
	private String vinNo;// 车架号
	// 车辆品牌
	private String vehicleBrand;
	// 厂牌型号
	private String brandModel;
	// 露天展台 0露天 1非露天
	private String isOpenair;
	// 展位编号
	private String showNo;
	// 展会名称
	private String showName;

	// 车延保险增加字段，
	@ApiModelProperty("车主姓名")
	private String vehicleOwnerName;// 车主姓名
	@ApiModelProperty("证件类型")
	private String vehicleOIdentifyType;// 证件类型
	@ApiModelProperty("证件号码")
	private String vehicleOIdentifyNumber;// 证件号码
	@ApiModelProperty("手机号码")
	private String vehicleOMobile;// 手机号码
	@ApiModelProperty("发动机号")
	private String engineNo;// 发动机号
	@ApiModelProperty("车辆购买日期")
	private String makedate;// 车辆购买日期
	@ApiModelProperty("颜色")
	private String carColor;// 颜色
	@ApiModelProperty("排量")
	private String exhaustScale;// 排量
	private String id;
	@ApiModelProperty("车辆延保责任险校验使用")
	private String token;// 车辆延保责任险校验使用

	// 宠物不良反应保险新增字段
	@ApiModelProperty("投保人地址")
	private String appliAddress;// 投保人地址
	@ApiModelProperty("被保人地址")
	private String insuredAddress;// 被保人地址
	@ApiModelProperty("开票信息")
	private VatInfoVO vatInfo;
	@ApiModelProperty("开票信息")
	InvoiceInfo invoiceInfo;

	// 货营天下680新增字段
	@ApiModelProperty("车型")
	private String vehicleType;// 车型
	@ApiModelProperty("吨位")
	private String tonnage;// 吨位
	
	//货运
	@ApiModelProperty("吨位")
	String approvalQuality ;//吨位
	@ApiModelProperty("厂牌型号")
	String brandModelName;//厂牌型号
	@ApiModelProperty("车龄")
	String vehicleAge;//车龄
	@ApiModelProperty("车型")
	String vehicleModelCode;//车型
	
	@ApiModelProperty("客户号")
	String custNo;
	@ApiModelProperty("客户类型")
	String custType;
	@ApiModelProperty("客户标识")
	String custSourceType;
	@ApiModelProperty("订单号")
	String orderNo;
	
	@ApiModelProperty("渠道代码")
	String businessNatureCode;
	@ApiModelProperty("渠道名称")
	String  businessNatureName;
	
	
	// 电动自行车“充电保"新增字段
	@ApiModelProperty("出厂日期")
	private String productionDate;// 出厂日期
	@ApiModelProperty("生产厂家")
	private String manuFacturer;// 生产厂家
	@ApiModelProperty("购置价")
	private String purchasePrice;// 购置价
	// 境外商旅险新增字段
	@ApiModelProperty("附加托运行李延误")
	private String additionalDelay;// 附加托运行李延误
	@ApiModelProperty("附加个人行李及随身物品损失")
	private String additionalItems;// 附加个人行李及随身物品损失
	@ApiModelProperty("附加旅行证件重置")
	private String additionalReset;// 附加旅行证件重置
	@ApiModelProperty("附加签证拒签补偿")
	private String additionalCompensation;// 附加签证拒签补偿
	@ApiModelProperty("时间区间")
	private String theDay;// 时间区间
	// 出境劳务人员保险新增字段
	@ApiModelProperty("附加证件重置")
	private String zhengjian;// 附加证件重置
	@ApiModelProperty("附加电信通讯补助")
	private String dianxing;// 附加电信通讯补助
	@ApiModelProperty("职业类别")
	private String profession;// 职业类别
	// 上海二轮电动车三者险
	@ApiModelProperty("附加险车上人员座位险")
	private String personnelOnBoard;// 附加险车上人员座位险
	// 宠物医疗保险
	@ApiModelProperty("宠物名称")
	private String petName;// 宠物名称
	@ApiModelProperty("宠物出生日期")
	private String petBirthDate;// 宠物出生日期
	@ApiModelProperty("宠物性别")
	private String petsGender;// 宠物性别
	@ApiModelProperty("芯片号码")
	private String chipNumber;// 芯片号码
	@ApiModelProperty("接种医院")
	private String inoculateHospital;// 接种医院
	@ApiModelProperty("定点医院")
	private String fixedPointHospital;// 定点医院
	@ApiModelProperty("行政区划")
	private String addAreaCode;// 行政区划
	// 展览会责任险新增字段
	@ApiModelProperty("展会面积")
	private String exhibitionArea;// 展会面积
	
	
	@ApiModelProperty("上个保期保单号")
	private String relationPolicyNo;// 上个保期保单号
	
	@ApiModelProperty("是否有新的被保人: 0 : 无新被保人，1:有新被保人")
	private String hasNewInsured;//是否有新的被保人
	
	@ApiModelProperty("是否续保 Y:是 N：否")
	private String isRenewal;
	
	@ApiModelProperty("1:过滤掉  其他：过滤")
	private String isFilterInOrderList;//1:过滤掉 （若是续保调用 则需要过滤掉）  其他：过滤 
	

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public String getManuFacturer() {
		return manuFacturer;
	}

	public void setManuFacturer(String manuFacturer) {
		this.manuFacturer = manuFacturer;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getNclUserCode() {
		return nclUserCode;
	}

	public void setNclUserCode(String nclUserCode) {
		this.nclUserCode = nclUserCode;
	}

	public String getAges() {
		return ages;
	}

	public void setAges(String ages) {
		this.ages = ages;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getVinNo() {
		return vinNo;
	}

	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}

	public List<PupilName> getPupilNameList() {
		return pupilNameList;
	}

	public void setPupilNameList(List<PupilName> pupilNameList) {
		this.pupilNameList = pupilNameList;
	}

	public List<AccidentalInjuryInfo> getAccidentalInjuryInfos() {
		return accidentalInjuryInfos;
	}

	public void setAccidentalInjuryInfos(List<AccidentalInjuryInfo> accidentalInjuryInfos) {
		this.accidentalInjuryInfos = accidentalInjuryInfos;
	}

	public String getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(String isAgent) {
		this.isAgent = isAgent;
	}

	public String getBusinessDetailTmp() {
		return businessDetailTmp;
	}

	public void setBusinessDetailTmp(String businessDetailTmp) {
		this.businessDetailTmp = businessDetailTmp;
	}

	public AgentInfoVO getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfoVO agentInfo) {
		this.agentInfo = agentInfo;
	}

	public String getBeGuardianSex() {
		return beGuardianSex;
	}

	public void setBeGuardianSex(String beGuardianSex) {
		this.beGuardianSex = beGuardianSex;
	}

	public String getBeGuardianBirthDate() {
		return beGuardianBirthDate;
	}

	public void setBeGuardianBirthDate(String beGuardianBirthDate) {
		this.beGuardianBirthDate = beGuardianBirthDate;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getFormulaCode() {
		return formulaCode;
	}

	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public int getFormulaSize() {
		return formulaSize;
	}

	public void setFormulaSize(int formulaSize) {
		this.formulaSize = formulaSize;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getAppliName() {
		return appliName;
	}

	public void setAppliName(String appliName) {
		this.appliName = appliName;
	}

	public String getAppliIdentifyType() {
		return appliIdentifyType;
	}

	public void setAppliIdentifyType(String appliIdentifyType) {
		this.appliIdentifyType = appliIdentifyType;
	}

	public String getAppliIdentifyNumber() {
		return appliIdentifyNumber;
	}

	public void setAppliIdentifyNumber(String appliIdentifyNumber) {
		this.appliIdentifyNumber = appliIdentifyNumber;
	}

	public String getAppliBirthDate() {
		return appliBirthDate;
	}

	public void setAppliBirthDate(String appliBirthDate) {
		this.appliBirthDate = appliBirthDate;
	}

	public String getAppliMobile() {
		return appliMobile;
	}

	public void setAppliMobile(String appliMobile) {
		this.appliMobile = appliMobile;
	}

	public String getAppliEMail() {
		return appliEMail;
	}

	public void setAppliEMail(String appliEMail) {
		this.appliEMail = appliEMail;
	}

	public String getInsuredIdentity() {
		return insuredIdentity;
	}

	public void setInsuredIdentity(String insuredIdentity) {
		this.insuredIdentity = insuredIdentity;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredIdentifyType() {
		return insuredIdentifyType;
	}

	public void setInsuredIdentifyType(String insuredIdentifyType) {
		this.insuredIdentifyType = insuredIdentifyType;
	}

	public String getInsuredIdentifyNumber() {
		return insuredIdentifyNumber;
	}

	public void setInsuredIdentifyNumber(String insuredIdentifyNumber) {
		this.insuredIdentifyNumber = insuredIdentifyNumber;
	}

	public String getInsuredBirthDate() {
		return insuredBirthDate;
	}

	public void setInsuredBirthDate(String insuredBirthDate) {
		this.insuredBirthDate = insuredBirthDate;
	}

	public String getInsuredMobile() {
		return insuredMobile;
	}

	public void setInsuredMobile(String insuredMobile) {
		this.insuredMobile = insuredMobile;
	}

	public String getInsuredEMail() {
		return insuredEMail;
	}

	public void setInsuredEMail(String insuredEMail) {
		this.insuredEMail = insuredEMail;
	}

	public String getAppliIdentity() {
		return appliIdentity;
	}

	public void setAppliIdentity(String appliIdentity) {
		this.appliIdentity = appliIdentity;
	}

	public AddressInfoVO getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfoVO addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getLicensePlateNo() {
		return licensePlateNo;
	}

	public void setLicensePlateNo(String licensePlateNo) {
		this.licensePlateNo = licensePlateNo;
	}

	public double getSumPremium() {
		return sumPremium;
	}

	public void setSumPremium(double sumPremium) {
		this.sumPremium = sumPremium;
	}

	public double getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(double sumAmount) {
		this.sumAmount = sumAmount;
	}

	public double getAmountBC() {
		return amountBC;
	}

	public void setAmountBC(double amountBC) {
		this.amountBC = amountBC;
	}

	public double getAmountOT() {
		return amountOT;
	}

	public void setAmountOT(double amountOT) {
		this.amountOT = amountOT;
	}

	public String getAmountsJson() {
		return amountsJson;
	}

	public void setAmountsJson(String amountsJson) {
		this.amountsJson = amountsJson;
	}

	public String getPremiumsJson() {
		return premiumsJson;
	}

	public void setPremiumsJson(String premiumsJson) {
		this.premiumsJson = premiumsJson;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getInsureNo() {
		return insureNo;
	}

	public void setInsureNo(String insureNo) {
		this.insureNo = insureNo;
	}

	public String getNoCarInsureId() {
		return noCarInsureId;
	}

	public void setNoCarInsureId(String noCarInsureId) {
		this.noCarInsureId = noCarInsureId;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIsSheBao() {
		return isSheBao;
	}

	public void setIsSheBao(String isSheBao) {
		this.isSheBao = isSheBao;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getIsRunApp() {
		return isRunApp;
	}

	public void setIsRunApp(String isRunApp) {
		this.isRunApp = isRunApp;
	}

	public UserVO getHandlerInfo() {
		return handlerInfo;
	}

	public void setHandlerInfo(UserVO handlerInfo) {
		this.handlerInfo = handlerInfo;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getBrandModel() {
		return brandModel;
	}

	public void setBrandModel(String brandModel) {
		this.brandModel = brandModel;
	}

	public String getIsOpenair() {
		return isOpenair;
	}

	public void setIsOpenair(String isOpenair) {
		this.isOpenair = isOpenair;
	}

	public String getShowNo() {
		return showNo;
	}

	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getAppliNationality() {
		return appliNationality;
	}

	public void setAppliNationality(String appliNationality) {
		this.appliNationality = appliNationality;
	}

	public String getInsuredNationality() {
		return insuredNationality;
	}

	public void setInsuredNationality(String insuredNationality) {
		this.insuredNationality = insuredNationality;
	}

	public String getMakedate() {
		return makedate;
	}

	public void setMakedate(String makedate) {
		this.makedate = makedate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVehicleOwnerName() {
		return vehicleOwnerName;
	}

	public void setVehicleOwnerName(String vehicleOwnerName) {
		this.vehicleOwnerName = vehicleOwnerName;
	}

	public String getVehicleOIdentifyType() {
		return vehicleOIdentifyType;
	}

	public void setVehicleOIdentifyType(String vehicleOIdentifyType) {
		this.vehicleOIdentifyType = vehicleOIdentifyType;
	}

	public String getVehicleOIdentifyNumber() {
		return vehicleOIdentifyNumber;
	}

	public void setVehicleOIdentifyNumber(String vehicleOIdentifyNumber) {
		this.vehicleOIdentifyNumber = vehicleOIdentifyNumber;
	}

	public String getVehicleOMobile() {
		return vehicleOMobile;
	}

	public void setVehicleOMobile(String vehicleOMobile) {
		this.vehicleOMobile = vehicleOMobile;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getExhaustScale() {
		return exhaustScale;
	}

	public void setExhaustScale(String exhaustScale) {
		this.exhaustScale = exhaustScale;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getaCTS() {
		return aCTS;
	}

	public void setaCTS(String aCTS) {
		this.aCTS = aCTS;
	}

	public String getHeavyIonMedical() {
		return heavyIonMedical;
	}

	public void setHeavyIonMedical(String heavyIonMedical) {
		this.heavyIonMedical = heavyIonMedical;
	}

	public String getAppliAddress() {
		return appliAddress;
	}

	public void setAppliAddress(String appliAddress) {
		this.appliAddress = appliAddress;
	}

	public String getInsuredAddress() {
		return insuredAddress;
	}

	public void setInsuredAddress(String insuredAddress) {
		this.insuredAddress = insuredAddress;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getTonnage() {
		return tonnage;
	}

	public void setTonnage(String tonnage) {
		this.tonnage = tonnage;
	}

	public VatInfoVO getVatInfo() {
		return vatInfo;
	}

	public void setVatInfo(VatInfoVO vatInfo) {
		this.vatInfo = vatInfo;
	}

	public String getExhibitionArea() {
		return exhibitionArea;
	}

	public void setExhibitionArea(String exhibitionArea) {
		this.exhibitionArea = exhibitionArea;
	}

	public String getAdditionalDelay() {
		return additionalDelay;
	}

	public void setAdditionalDelay(String additionalDelay) {
		this.additionalDelay = additionalDelay;
	}

	public String getAdditionalItems() {
		return additionalItems;
	}

	public void setAdditionalItems(String additionalItems) {
		this.additionalItems = additionalItems;
	}

	public String getAdditionalReset() {
		return additionalReset;
	}

	public void setAdditionalReset(String additionalReset) {
		this.additionalReset = additionalReset;
	}

	public String getAdditionalCompensation() {
		return additionalCompensation;
	}

	public void setAdditionalCompensation(String additionalCompensation) {
		this.additionalCompensation = additionalCompensation;
	}

	public String getTheDay() {
		return theDay;
	}

	public void setTheDay(String theDay) {
		this.theDay = theDay;
	}

	public String getZhengjian() {
		return zhengjian;
	}

	public void setZhengjian(String zhengjian) {
		this.zhengjian = zhengjian;
	}

	public String getDianxing() {
		return dianxing;
	}

	public void setDianxing(String dianxing) {
		this.dianxing = dianxing;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getPersonnelOnBoard() {
		return personnelOnBoard;
	}

	public void setPersonnelOnBoard(String personnelOnBoard) {
		this.personnelOnBoard = personnelOnBoard;
	}

	public String getInoculateHospital() {
		return inoculateHospital;
	}

	public void setInoculateHospital(String inoculateHospital) {
		this.inoculateHospital = inoculateHospital;
	}

	public String getFixedPointHospital() {
		return fixedPointHospital;
	}

	public void setFixedPointHospital(String fixedPointHospital) {
		this.fixedPointHospital = fixedPointHospital;
	}

	public String getPetsGender() {
		return petsGender;
	}

	public void setPetsGender(String petsGender) {
		this.petsGender = petsGender;
	}

	public String getChipNumber() {
		return chipNumber;
	}

	public void setChipNumber(String chipNumber) {
		this.chipNumber = chipNumber;
	}

	public String getInsuredSheBaoType() {
		return insuredSheBaoType;
	}

	public void setInsuredSheBaoType(String insuredSheBaoType) {
		this.insuredSheBaoType = insuredSheBaoType;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetBirthDate() {
		return petBirthDate;
	}

	public void setPetBirthDate(String petBirthDate) {
		this.petBirthDate = petBirthDate;
	}

	public String getAddAreaCode() {
		return addAreaCode;
	}

	public void setAddAreaCode(String addAreaCode) {
		this.addAreaCode = addAreaCode;
	}

	public String getaKZEXClause() {
		return aKZEXClause;
	}

	public void setaKZEXClause(String aKZEXClause) {
		this.aKZEXClause = aKZEXClause;
	}

	public String getaEXTZClause() {
		return aEXTZClause;
	}

	public void setaEXTZClause(String aEXTZClause) {
		this.aEXTZClause = aEXTZClause;
	}

	public String getaEXYLClause() {
		return aEXYLClause;
	}

	public void setaEXYLClause(String aEXYLClause) {
		this.aEXYLClause = aEXYLClause;
	}

	public String getaEXZZClause() {
		return aEXZZClause;
	}

	public void setaEXZZClause(String aEXZZClause) {
		this.aEXZZClause = aEXZZClause;
	}
	
	
	public String getAkzexclause() {
		return aKZEXClause;
	}

	public void setAkzexclause(String aKZEXClause) {
		this.aKZEXClause = aKZEXClause;
	}
	
	public String getAextzclause() {
		return aEXTZClause;
	}

	public void setAextzclause(String aEXTZClause) {
		this.aEXTZClause = aEXTZClause;
	}

	public String getAexylclause() {
		return aEXYLClause;
	}

	public void setAexylclause(String aEXYLClause) {
		this.aEXYLClause = aEXYLClause;
	}

	public String getAexzzclause() {
		return aEXZZClause;
	}

	public void setAexzzclause(String aEXZZClause) {
		this.aEXZZClause = aEXZZClause;
	}

	public String getActs() {
		return aCTS;
	}

	public void setActs(String aCTS) {
		this.aCTS = aCTS;
	}
}
