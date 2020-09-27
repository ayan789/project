package com.ccic.salesapp.noncar.dto.request;
import java.util.List;
import com.ccic.salesapp.noncar.dto.DataVO;


/**
 * 精确报价 车辆信息
 * @author Administrator
 *
 */
public class CarVO extends DataVO{

	private String 	carinsureDrelation;//被保险人与车辆关系 Y 详见代码2.1.34
	private String  licenseNo;//号牌号码 
	private String  licenseColorcode  ;//号牌底色 参见代码2.1.12
	private String  licenseType;//号牌种类 参见代码2.1.20
	private String  importFlag ;//车型种类 Y 参见代码2.1.26
	private String  carKindCode;//车辆种类 Y 参见代码2.1.11
	private String  modelCode;//车型代码 Y 
	private String  modelName;//车型名称 Y 
	private String  engineNo ;//发动机号 Y
	private String  frameNo  ;//车架号 Y 
	private String  vinNo;//VIN Y 最大长度为17位
	private String  seatCount;//核定载客数 Y 单位：个
	private String  tonCount ;//核定载质量 Y 单位：千克
	private String  net;//整备质量 Y 单位：千克
	private String  exhaustScale;//排量 Y 单位：毫升
	private String  powerScale ;//功率 Y 单位：千瓦
	private String  enrollDate ;//车辆初登日期 Y 格式：YYYY-MM-DD
	private String  userYear ;//使用年限 Y 计算方式详见规则文档
	private String  runMiles ;//行驶里程
	private String  colorCode;//车身颜色
	private String  runAreaCode;//行驶区域  参见代码2.1.13
	private String  useNatureCode ;//车辆使用性质 Y 详见代码2.1.8
	private String  purchasePrice ;//新车购置价 Y 
	private String  actualValue;//新车实际价值 Y 
	private String  clauseType ;//条款类别 默认：F21（机动车辆损失保险条款）
	private String  violatedTimes ;//违章次数
	private String  ecdemicVehicle;//是否外地车 Y 详见代码2.1.35
	private String  newVehicle ;//是否新车 Y 详见代码2.1.32
	private String  noLicenseFlag ;//未上牌车辆标志 Y 详见代码2.1.32
	private String  chgOwnerFlag;//过户车辆标志 Y 详见代码2.1.32
	private String  safeDevice ;//安全配置 见代码2.1.6，可多选，名称按逗号隔开
	private String  parkSite ;//固定停放地点 见代码2.1.7
	private String  certificateDate;//行驶证发证日期 格式：yyyy-MM-dd
	private String  vehicleCategory;//交管车辆类型 Y 详见代码2.1.19
	private String  transferDate;//转移登记日期 格式：YYYY-MM-DD，过户车非空
	private String  madeFactory;//制造厂名称 
	private String  vehicleBrand;//车辆品牌 行驶证品牌型号只输入中文，如“帕萨特”
	private String  noDamageYears ;//跨省首年投保未出险证明的年数 如传值，只能送0,1,2,3
	private String  loanVehicleFlag;//车贷投保多年标志 Y 详见代码2.1.32;
	private String  specialCarFlag;//特殊车投保标志 Y 
	private String  fuelType ;//能源种类 Y 参见代码2.1.27
	private String  fleetFlag;//车队标志 Y 详见代码2.1.32;
	private String  carChecker ;//验车人 
	private String  carCheckStatus;//验车情况 
	private String  uncheckReasonCode ;//免验原因 
	private String  carCheckTime;//验车时间 
	private String  carRegiste ;//是否新车报备 Y 详见代码2.1.32
	private String  carTypeAlias;//厂牌型号别名 Y 
	private String vehicleModel;//车辆型号
	
	private String industryModelCode;
	private String basicRateCode;
	private String industryVehicleCode;
	private String industryVehicleName;
	/*新核心新增*/
	private String bankName;//贷款银行名称-车贷车必传
	private String vehicleName;//行驶证车型

	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	private List<ExtendInfoVO> extendInfo  ;//扩展信息
	public String getCarinsureDrelation() {
		return carinsureDrelation;
	}
	public void setCarinsureDrelation(String carinsureDrelation) {
		this.carinsureDrelation = carinsureDrelation;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLicenseColorcode() {
		return licenseColorcode;
	}
	public void setLicenseColorcode(String licenseColorcode) {
		this.licenseColorcode = licenseColorcode;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public String getImportFlag() {
		return importFlag;
	}
	public void setImportFlag(String importFlag) {
		this.importFlag = importFlag;
	}
	public String getCarKindCode() {
		return carKindCode;
	}
	public void setCarKindCode(String carKindCode) {
		this.carKindCode = carKindCode;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getFrameNo() {
		return frameNo;
	}
	public void setFrameNo(String frameNo) {
		this.frameNo = frameNo;
	}
	public String getVinNo() {
		return vinNo;
	}
	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}
	public String getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}
	public String getTonCount() {
		return tonCount;
	}
	public void setTonCount(String tonCount) {
		this.tonCount = tonCount;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public String getExhaustScale() {
		return exhaustScale;
	}
	public void setExhaustScale(String exhaustScale) {
		this.exhaustScale = exhaustScale;
	}
	public String getPowerScale() {
		return powerScale;
	}
	public void setPowerScale(String powerScale) {
		this.powerScale = powerScale;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getUserYear() {
		return userYear;
	}
	public void setUserYear(String userYear) {
		this.userYear = userYear;
	}
	public String getRunMiles() {
		return runMiles;
	}
	public void setRunMiles(String runMiles) {
		this.runMiles = runMiles;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getRunAreaCode() {
		return runAreaCode;
	}
	public void setRunAreaCode(String runAreaCode) {
		this.runAreaCode = runAreaCode;
	}
	public String getUseNatureCode() {
		return useNatureCode;
	}
	public void setUseNatureCode(String useNatureCode) {
		this.useNatureCode = useNatureCode;
	}
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getActualValue() {
		return actualValue;
	}
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}
	public String getClauseType() {
		return clauseType;
	}
	public void setClauseType(String clauseType) {
		this.clauseType = clauseType;
	}
	public String getViolatedTimes() {
		return violatedTimes;
	}
	public void setViolatedTimes(String violatedTimes) {
		this.violatedTimes = violatedTimes;
	}
	public String getEcdemicVehicle() {
		return ecdemicVehicle;
	}
	public void setEcdemicVehicle(String ecdemicVehicle) {
		this.ecdemicVehicle = ecdemicVehicle;
	}
	public String getNewVehicle() {
		return newVehicle;
	}
	public void setNewVehicle(String newVehicle) {
		this.newVehicle = newVehicle;
	}
	public String getNoLicenseFlag() {
		return noLicenseFlag;
	}
	public void setNoLicenseFlag(String noLicenseFlag) {
		this.noLicenseFlag = noLicenseFlag;
	}
	public String getChgOwnerFlag() {
		return chgOwnerFlag;
	}
	public void setChgOwnerFlag(String chgOwnerFlag) {
		this.chgOwnerFlag = chgOwnerFlag;
	}
	public String getSafeDevice() {
		return safeDevice;
	}
	public void setSafeDevice(String safeDevice) {
		this.safeDevice = safeDevice;
	}
	public String getParkSite() {
		return parkSite;
	}
	public void setParkSite(String parkSite) {
		this.parkSite = parkSite;
	}
	public String getCertificateDate() {
		return certificateDate;
	}
	public void setCertificateDate(String certificateDate) {
		this.certificateDate = certificateDate;
	}
	public String getVehicleCategory() {
		return vehicleCategory;
	}
	public void setVehicleCategory(String vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}
	public String getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
	public String getMadeFactory() {
		return madeFactory;
	}
	public void setMadeFactory(String madeFactory) {
		this.madeFactory = madeFactory;
	}
	public String getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	public String getNoDamageYears() {
		return noDamageYears;
	}
	public void setNoDamageYears(String noDamageYears) {
		this.noDamageYears = noDamageYears;
	}
	public String getLoanVehicleFlag() {
		return loanVehicleFlag;
	}
	public void setLoanVehicleFlag(String loanVehicleFlag) {
		this.loanVehicleFlag = loanVehicleFlag;
	}
	public String getSpecialCarFlag() {
		return specialCarFlag;
	}
	public void setSpecialCarFlag(String specialCarFlag) {
		this.specialCarFlag = specialCarFlag;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getFleetFlag() {
		return fleetFlag;
	}
	public void setFleetFlag(String fleetFlag) {
		this.fleetFlag = fleetFlag;
	}
	public String getCarChecker() {
		return carChecker;
	}
	public void setCarChecker(String carChecker) {
		this.carChecker = carChecker;
	}
	public String getCarCheckStatus() {
		return carCheckStatus;
	}
	public void setCarCheckStatus(String carCheckStatus) {
		this.carCheckStatus = carCheckStatus;
	}
	public String getUncheckReasonCode() {
		return uncheckReasonCode;
	}
	public void setUncheckReasonCode(String uncheckReasonCode) {
		this.uncheckReasonCode = uncheckReasonCode;
	}
	public String getCarCheckTime() {
		return carCheckTime;
	}
	public void setCarCheckTime(String carCheckTime) {
		this.carCheckTime = carCheckTime;
	}
	public String getCarRegiste() {
		return carRegiste;
	}
	public void setCarRegiste(String carRegiste) {
		this.carRegiste = carRegiste;
	}
	public String getCarTypeAlias() {
		return carTypeAlias;
	}
	public void setCarTypeAlias(String carTypeAlias) {
		this.carTypeAlias = carTypeAlias;
	}
	
	public List<ExtendInfoVO> getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(
			List<ExtendInfoVO> extendInfo) {
		this.extendInfo = extendInfo;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public String getIndustryModelCode() {
		return industryModelCode;
	}
	public void setIndustryModelCode(String industryModelCode) {
		this.industryModelCode = industryModelCode;
	}
	public String getBasicRateCode() {
		return basicRateCode;
	}
	public void setBasicRateCode(String basicRateCode) {
		this.basicRateCode = basicRateCode;
	}
	public String getIndustryVehicleCode() {
		return industryVehicleCode;
	}
	public void setIndustryVehicleCode(String industryVehicleCode) {
		this.industryVehicleCode = industryVehicleCode;
	}
	public String getIndustryVehicleName() {
		return industryVehicleName;
	}
	public void setIndustryVehicleName(String industryVehicleName) {
		this.industryVehicleName = industryVehicleName;
	}



}
