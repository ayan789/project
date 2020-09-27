package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * 机动车辆信息
 * @author Administrator
 *
 */
public class Vehicle {
	private String announcedModel;//公告型号
	private Double approvalQuality;//核定载质量/载重量
	private Long approvalSeatCount;//核定载客/核定座位数（核定乘客座位数）
	private String brandCode;//车辆品牌
	private String carName;//车款名称
	private String color;//车身颜色
	private String dangerousGoodsName;//危险货物品名
	private String departureDestination;//起运地/目的地
	private Double displacement;//排量（单位：L）
	private String driveFormType;//驱动形式
	private Long driverConductorSeat;//责任险-其中司乘人员座位数
	private String drivingLicenseModel;//行驶证车型
	private String drivingLicenseOwner;//行驶证车主
	private Long drivingMileage;//已行驶里程
	private String drivingType;//车辆行驶类型
	private Date effectiveDate;//起保日期
	private String engineNo;//发动机号
	private Date expiryDate;//终保日期
	private String exteWarrProductCategory;//延长保修产品类别
	private String gasType;//燃料类型/能源种类
	private String hkLicenseNo;//香港号牌
	private String importType;//车辆进口类型(进口、国产、合资)
	private String industryModelCode;//行业车型编码
	private Date inspectValidThru;//检验有效期（年/月）
	private Long insuredSeatNum;//投保座位数
	private String insurerVehiclePolicyNo;//本司车险保单号
	private String isJyDataChanged;//精友数据是否更改
	private String isLoanVehicle;//是否车贷车
	private String isLoanVehiclePlatform;//是否车贷车(平台返回)
	private String isNewVehicle;//是否新车
	private String isNewVehicleFiled;//是否新车备案
	private String isNonlocalVehicle;//外地车标志
	private String isNotRegistered;//是否未上牌
	private String isSpecialShapeVehicle;//是否异型车标志
	private String isTransferVehicle;//是否过户车
	private String jyDataChangeDetail;//精友数据更改信息
	private Long jyVehicleId;//精友系统车辆编码对应明细数据的MD5签名值，用来区分同一车型的不同版本。VEHICLE_CODE+JING_YOU_DATA_HASH到本地缓存的精友库中做查询
	private String lastTimeEngineNo;//上次计算发动机号
	private String lastTimeLicenseNo;//上次计算号牌号码/内地号牌
	private String lastTimeVin;//上次计算车架号/VIN码
	private String licenseBackgroundCode;//号牌底色
	private String licenseIssueDate;//发证日期
	private String licenseNo;//号牌号码/内地号牌
	private String licenseType;//号牌种类
	private String madeFactory;//制造厂名称/厂商名称
	private String model;//厂牌型号
	private String modelCateCode;//车型分类
	private String modelSeries;//车系
	private String modelSeriesCode;//车系代码（车险新增字段）
	private Double newVehiclePurchasePrice;//新车购置价
	private String oddEvenNumber;//单双号标志
	private String positioningNavigation;//定位导航装置
	private Double power;//功率
	private String purchaseInvoiceDate;//购车发票日期
	private String realUseYear;//实际使用年数
	private Double realValue;//实际价值
	private Long repairMileageLimit;//原厂保修里程限制
	private Long repairMonth;//原厂保修期限（月）
	private String runArea;//行驶区域（默认为中华人民共和国境内，不含港澳台）
	private String specialApplicationCode;//特殊车投保标志
	private String specialStore;//专营店
	private Double totalMassOfTraction;//准牵引总质量
	private String tractorTrailerPolicyNo;//牵引车/挂车关联保单号
	private String trailerLicenseNo;//挂车车牌号(和车牌号出现在同一个页面，不能复用)
	private String trailerModel;//挂车厂牌型号(和车牌号出现在同一个页面，不能复用)
	private String trailerVin;//挂车车架号(和车牌号出现在同一个页面，不能复用)
	private Date transCertiIssueDate;//颁发日期
	private String transCertiNo;//道路运输经营许可证编号
	private Date transCertiValidThru;//有效期至
	private String transCertificateNo;//责任险-道路运输经营许可证编号
	private String transmissionFormType;//变速箱形式
	private String travelRoute;//行驶路线
	private String trunkCategory;//货车分类
	private String turbineCode;//涡轮标识
	private Date turningDate;//交车时间
	private String vehicleBuyDate;//车辆购买日期
	private String vehicleCode;//精友系统车辆编码/车型代码，根据这个代码获取其他精友车型相关字段（和liliang确认，是精友返回的，无码表）
	private String vehicleComments;//备注
	private Long vehicleGroupSeqNum;//车辆组别序号
	private Date vehicleInitialRegDate;//车辆初次登记日期
	private String vehicleInvoiceNo;//购车发票号
	private String vehicleIssueCompany;//车险承保公司
	private String vehicleKindCode;//车辆种类
	private String vehicleKindTcCode;//交管车辆类型代码
	private String vehicleOperationNo;//车辆营运证编号
	private String vehicleOwnerIdNo;//车主证件号码
	private String vehiclePolicyNo;//车险保单号
	private Long vehicleQuality;//整备质量/整车质量
	private String vehicleServiceContractNo;//代步车服务合同号
	private Date vehicleSrcCertiDate;//开具车辆来历凭证所载日期
	private String vehicleSrcCertiNo;//车辆来历凭证编号
	private String vehicleSrcCertiType;//车辆来历凭证种类
	private Date vehicleTransferRegDate;//转移登记日期/过户日期
	private String vehicleUseNatureCode;//车辆使用性质
	private String vin;//车架号/VIN码
	private String warrantyPropCertiNo;//延长保修凭证号码
	private String warrantyRepairPeriod;//延长保修期
	private Long sequenceNumber ;//序号
	private Map<String, String> tempData;// 临时数据
	private String vehicleName;//车型
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getAnnouncedModel() {
		return announcedModel;
	}
	public void setAnnouncedModel(String announcedModel) {
		this.announcedModel = announcedModel;
	}
	public Double getApprovalQuality() {
		return approvalQuality;
	}
	public void setApprovalQuality(Double approvalQuality) {
		this.approvalQuality = approvalQuality;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDangerousGoodsName() {
		return dangerousGoodsName;
	}
	public void setDangerousGoodsName(String dangerousGoodsName) {
		this.dangerousGoodsName = dangerousGoodsName;
	}
	public String getDepartureDestination() {
		return departureDestination;
	}
	public void setDepartureDestination(String departureDestination) {
		this.departureDestination = departureDestination;
	}
	public Double getDisplacement() {
		return displacement;
	}
	public void setDisplacement(Double displacement) {
		this.displacement = displacement;
	}
	public String getDriveFormType() {
		return driveFormType;
	}
	public void setDriveFormType(String driveFormType) {
		this.driveFormType = driveFormType;
	}
	public String getDrivingLicenseModel() {
		return drivingLicenseModel;
	}
	public void setDrivingLicenseModel(String drivingLicenseModel) {
		this.drivingLicenseModel = drivingLicenseModel;
	}
	public String getDrivingLicenseOwner() {
		return drivingLicenseOwner;
	}
	public void setDrivingLicenseOwner(String drivingLicenseOwner) {
		this.drivingLicenseOwner = drivingLicenseOwner;
	}
	public String getDrivingType() {
		return drivingType;
	}
	public void setDrivingType(String drivingType) {
		this.drivingType = drivingType;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getExteWarrProductCategory() {
		return exteWarrProductCategory;
	}
	public void setExteWarrProductCategory(String exteWarrProductCategory) {
		this.exteWarrProductCategory = exteWarrProductCategory;
	}
	public String getGasType() {
		return gasType;
	}
	public void setGasType(String gasType) {
		this.gasType = gasType;
	}
	public String getHkLicenseNo() {
		return hkLicenseNo;
	}
	public void setHkLicenseNo(String hkLicenseNo) {
		this.hkLicenseNo = hkLicenseNo;
	}
	public String getImportType() {
		return importType;
	}
	public void setImportType(String importType) {
		this.importType = importType;
	}
	public String getIndustryModelCode() {
		return industryModelCode;
	}
	public void setIndustryModelCode(String industryModelCode) {
		this.industryModelCode = industryModelCode;
	}
	public Date getInspectValidThru() {
		return inspectValidThru;
	}
	public void setInspectValidThru(Date inspectValidThru) {
		this.inspectValidThru = inspectValidThru;
	}
	public String getInsurerVehiclePolicyNo() {
		return insurerVehiclePolicyNo;
	}
	public void setInsurerVehiclePolicyNo(String insurerVehiclePolicyNo) {
		this.insurerVehiclePolicyNo = insurerVehiclePolicyNo;
	}
	public String getIsJyDataChanged() {
		return isJyDataChanged;
	}
	public void setIsJyDataChanged(String isJyDataChanged) {
		this.isJyDataChanged = isJyDataChanged;
	}
	public String getIsLoanVehicle() {
		return isLoanVehicle;
	}
	public void setIsLoanVehicle(String isLoanVehicle) {
		this.isLoanVehicle = isLoanVehicle;
	}
	public String getIsLoanVehiclePlatform() {
		return isLoanVehiclePlatform;
	}
	public void setIsLoanVehiclePlatform(String isLoanVehiclePlatform) {
		this.isLoanVehiclePlatform = isLoanVehiclePlatform;
	}
	public String getIsNewVehicle() {
		return isNewVehicle;
	}
	public void setIsNewVehicle(String isNewVehicle) {
		this.isNewVehicle = isNewVehicle;
	}
	public String getIsNewVehicleFiled() {
		return isNewVehicleFiled;
	}
	public void setIsNewVehicleFiled(String isNewVehicleFiled) {
		this.isNewVehicleFiled = isNewVehicleFiled;
	}
	public String getIsNonlocalVehicle() {
		return isNonlocalVehicle;
	}
	public void setIsNonlocalVehicle(String isNonlocalVehicle) {
		this.isNonlocalVehicle = isNonlocalVehicle;
	}
	public String getIsNotRegistered() {
		return isNotRegistered;
	}
	public void setIsNotRegistered(String isNotRegistered) {
		this.isNotRegistered = isNotRegistered;
	}
	public String getIsSpecialShapeVehicle() {
		return isSpecialShapeVehicle;
	}
	public void setIsSpecialShapeVehicle(String isSpecialShapeVehicle) {
		this.isSpecialShapeVehicle = isSpecialShapeVehicle;
	}
	public String getIsTransferVehicle() {
		return isTransferVehicle;
	}
	public void setIsTransferVehicle(String isTransferVehicle) {
		this.isTransferVehicle = isTransferVehicle;
	}
	public String getJyDataChangeDetail() {
		return jyDataChangeDetail;
	}
	public void setJyDataChangeDetail(String jyDataChangeDetail) {
		this.jyDataChangeDetail = jyDataChangeDetail;
	}
	public Long getJyVehicleId() {
		return jyVehicleId;
	}
	public void setJyVehicleId(Long jyVehicleId) {
		this.jyVehicleId = jyVehicleId;
	}
	public String getLastTimeEngineNo() {
		return lastTimeEngineNo;
	}
	public void setLastTimeEngineNo(String lastTimeEngineNo) {
		this.lastTimeEngineNo = lastTimeEngineNo;
	}
	public String getLastTimeLicenseNo() {
		return lastTimeLicenseNo;
	}
	public void setLastTimeLicenseNo(String lastTimeLicenseNo) {
		this.lastTimeLicenseNo = lastTimeLicenseNo;
	}
	public String getLastTimeVin() {
		return lastTimeVin;
	}
	public void setLastTimeVin(String lastTimeVin) {
		this.lastTimeVin = lastTimeVin;
	}
	public String getLicenseBackgroundCode() {
		return licenseBackgroundCode;
	}
	public void setLicenseBackgroundCode(String licenseBackgroundCode) {
		this.licenseBackgroundCode = licenseBackgroundCode;
	}
	public String getLicenseIssueDate() {
		return licenseIssueDate;
	}
	public void setLicenseIssueDate(String licenseIssueDate) {
		this.licenseIssueDate = licenseIssueDate;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public String getMadeFactory() {
		return madeFactory;
	}
	public void setMadeFactory(String madeFactory) {
		this.madeFactory = madeFactory;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelCateCode() {
		return modelCateCode;
	}
	public void setModelCateCode(String modelCateCode) {
		this.modelCateCode = modelCateCode;
	}
	public String getModelSeries() {
		return modelSeries;
	}
	public void setModelSeries(String modelSeries) {
		this.modelSeries = modelSeries;
	}
	public String getModelSeriesCode() {
		return modelSeriesCode;
	}
	public void setModelSeriesCode(String modelSeriesCode) {
		this.modelSeriesCode = modelSeriesCode;
	}
	public Double getNewVehiclePurchasePrice() {
		return newVehiclePurchasePrice;
	}
	public void setNewVehiclePurchasePrice(Double newVehiclePurchasePrice) {
		this.newVehiclePurchasePrice = newVehiclePurchasePrice;
	}
	public String getOddEvenNumber() {
		return oddEvenNumber;
	}
	public void setOddEvenNumber(String oddEvenNumber) {
		this.oddEvenNumber = oddEvenNumber;
	}
	public String getPositioningNavigation() {
		return positioningNavigation;
	}
	public void setPositioningNavigation(String positioningNavigation) {
		this.positioningNavigation = positioningNavigation;
	}
	public Double getPower() {
		return power;
	}
	public void setPower(Double power) {
		this.power = power;
	}
	public String getPurchaseInvoiceDate() {
		return purchaseInvoiceDate;
	}
	public void setPurchaseInvoiceDate(String purchaseInvoiceDate) {
		this.purchaseInvoiceDate = purchaseInvoiceDate;
	}
	public String getRealUseYear() {
		return realUseYear;
	}
	public void setRealUseYear(String realUseYear) {
		this.realUseYear = realUseYear;
	}
	public Double getRealValue() {
		return realValue;
	}
	public void setRealValue(Double realValue) {
		this.realValue = realValue;
	}
	public String getRunArea() {
		return runArea;
	}
	public void setRunArea(String runArea) {
		this.runArea = runArea;
	}
	public String getSpecialApplicationCode() {
		return specialApplicationCode;
	}
	public void setSpecialApplicationCode(String specialApplicationCode) {
		this.specialApplicationCode = specialApplicationCode;
	}
	public String getSpecialStore() {
		return specialStore;
	}
	public void setSpecialStore(String specialStore) {
		this.specialStore = specialStore;
	}
	public Double getTotalMassOfTraction() {
		return totalMassOfTraction;
	}
	public void setTotalMassOfTraction(Double totalMassOfTraction) {
		this.totalMassOfTraction = totalMassOfTraction;
	}
	public String getTractorTrailerPolicyNo() {
		return tractorTrailerPolicyNo;
	}
	public void setTractorTrailerPolicyNo(String tractorTrailerPolicyNo) {
		this.tractorTrailerPolicyNo = tractorTrailerPolicyNo;
	}
	public String getTrailerLicenseNo() {
		return trailerLicenseNo;
	}
	public void setTrailerLicenseNo(String trailerLicenseNo) {
		this.trailerLicenseNo = trailerLicenseNo;
	}
	public String getTrailerModel() {
		return trailerModel;
	}
	public void setTrailerModel(String trailerModel) {
		this.trailerModel = trailerModel;
	}
	public String getTrailerVin() {
		return trailerVin;
	}
	public void setTrailerVin(String trailerVin) {
		this.trailerVin = trailerVin;
	}
	public Date getTransCertiIssueDate() {
		return transCertiIssueDate;
	}
	public void setTransCertiIssueDate(Date transCertiIssueDate) {
		this.transCertiIssueDate = transCertiIssueDate;
	}
	public String getTransCertiNo() {
		return transCertiNo;
	}
	public void setTransCertiNo(String transCertiNo) {
		this.transCertiNo = transCertiNo;
	}
	public Date getTransCertiValidThru() {
		return transCertiValidThru;
	}
	public void setTransCertiValidThru(Date transCertiValidThru) {
		this.transCertiValidThru = transCertiValidThru;
	}
	public String getTransCertificateNo() {
		return transCertificateNo;
	}
	public void setTransCertificateNo(String transCertificateNo) {
		this.transCertificateNo = transCertificateNo;
	}
	public String getTransmissionFormType() {
		return transmissionFormType;
	}
	public void setTransmissionFormType(String transmissionFormType) {
		this.transmissionFormType = transmissionFormType;
	}
	public String getTravelRoute() {
		return travelRoute;
	}
	public void setTravelRoute(String travelRoute) {
		this.travelRoute = travelRoute;
	}
	public String getTrunkCategory() {
		return trunkCategory;
	}
	public void setTrunkCategory(String trunkCategory) {
		this.trunkCategory = trunkCategory;
	}
	public String getTurbineCode() {
		return turbineCode;
	}
	public void setTurbineCode(String turbineCode) {
		this.turbineCode = turbineCode;
	}
	public Date getTurningDate() {
		return turningDate;
	}
	public void setTurningDate(Date turningDate) {
		this.turningDate = turningDate;
	}
	
	public String getVehicleBuyDate() {
		return vehicleBuyDate;
	}
	public void setVehicleBuyDate(String vehicleBuyDate) {
		this.vehicleBuyDate = vehicleBuyDate;
	}
	public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public String getVehicleComments() {
		return vehicleComments;
	}
	public void setVehicleComments(String vehicleComments) {
		this.vehicleComments = vehicleComments;
	}
	public Long getVehicleGroupSeqNum() {
		return vehicleGroupSeqNum;
	}
	public void setVehicleGroupSeqNum(Long vehicleGroupSeqNum) {
		this.vehicleGroupSeqNum = vehicleGroupSeqNum;
	}
	public Date getVehicleInitialRegDate() {
		return vehicleInitialRegDate;
	}
	public void setVehicleInitialRegDate(Date vehicleInitialRegDate) {
		this.vehicleInitialRegDate = vehicleInitialRegDate;
	}
	public String getVehicleInvoiceNo() {
		return vehicleInvoiceNo;
	}
	public void setVehicleInvoiceNo(String vehicleInvoiceNo) {
		this.vehicleInvoiceNo = vehicleInvoiceNo;
	}
	public String getVehicleIssueCompany() {
		return vehicleIssueCompany;
	}
	public void setVehicleIssueCompany(String vehicleIssueCompany) {
		this.vehicleIssueCompany = vehicleIssueCompany;
	}
	public String getVehicleKindCode() {
		return vehicleKindCode;
	}
	public void setVehicleKindCode(String vehicleKindCode) {
		this.vehicleKindCode = vehicleKindCode;
	}
	public String getVehicleKindTcCode() {
		return vehicleKindTcCode;
	}
	public void setVehicleKindTcCode(String vehicleKindTcCode) {
		this.vehicleKindTcCode = vehicleKindTcCode;
	}
	public String getVehicleOperationNo() {
		return vehicleOperationNo;
	}
	public void setVehicleOperationNo(String vehicleOperationNo) {
		this.vehicleOperationNo = vehicleOperationNo;
	}
	public String getVehicleOwnerIdNo() {
		return vehicleOwnerIdNo;
	}
	public void setVehicleOwnerIdNo(String vehicleOwnerIdNo) {
		this.vehicleOwnerIdNo = vehicleOwnerIdNo;
	}
	public String getVehiclePolicyNo() {
		return vehiclePolicyNo;
	}
	public void setVehiclePolicyNo(String vehiclePolicyNo) {
		this.vehiclePolicyNo = vehiclePolicyNo;
	}
	public String getVehicleServiceContractNo() {
		return vehicleServiceContractNo;
	}
	public void setVehicleServiceContractNo(String vehicleServiceContractNo) {
		this.vehicleServiceContractNo = vehicleServiceContractNo;
	}
	public Date getVehicleSrcCertiDate() {
		return vehicleSrcCertiDate;
	}
	public void setVehicleSrcCertiDate(Date vehicleSrcCertiDate) {
		this.vehicleSrcCertiDate = vehicleSrcCertiDate;
	}
	public String getVehicleSrcCertiNo() {
		return vehicleSrcCertiNo;
	}
	public void setVehicleSrcCertiNo(String vehicleSrcCertiNo) {
		this.vehicleSrcCertiNo = vehicleSrcCertiNo;
	}
	public String getVehicleSrcCertiType() {
		return vehicleSrcCertiType;
	}
	public void setVehicleSrcCertiType(String vehicleSrcCertiType) {
		this.vehicleSrcCertiType = vehicleSrcCertiType;
	}
	public Date getVehicleTransferRegDate() {
		return vehicleTransferRegDate;
	}
	public void setVehicleTransferRegDate(Date vehicleTransferRegDate) {
		this.vehicleTransferRegDate = vehicleTransferRegDate;
	}
	public String getVehicleUseNatureCode() {
		return vehicleUseNatureCode;
	}
	public void setVehicleUseNatureCode(String vehicleUseNatureCode) {
		this.vehicleUseNatureCode = vehicleUseNatureCode;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getWarrantyPropCertiNo() {
		return warrantyPropCertiNo;
	}
	public void setWarrantyPropCertiNo(String warrantyPropCertiNo) {
		this.warrantyPropCertiNo = warrantyPropCertiNo;
	}
	public String getWarrantyRepairPeriod() {
		return warrantyRepairPeriod;
	}
	public void setWarrantyRepairPeriod(String warrantyRepairPeriod) {
		this.warrantyRepairPeriod = warrantyRepairPeriod;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public Long getApprovalSeatCount() {
		return approvalSeatCount;
	}
	public void setApprovalSeatCount(Long approvalSeatCount) {
		this.approvalSeatCount = approvalSeatCount;
	}
	public Long getDriverConductorSeat() {
		return driverConductorSeat;
	}
	public void setDriverConductorSeat(Long driverConductorSeat) {
		this.driverConductorSeat = driverConductorSeat;
	}
	public Long getDrivingMileage() {
		return drivingMileage;
	}
	public void setDrivingMileage(Long drivingMileage) {
		this.drivingMileage = drivingMileage;
	}
	public Long getInsuredSeatNum() {
		return insuredSeatNum;
	}
	public void setInsuredSeatNum(Long insuredSeatNum) {
		this.insuredSeatNum = insuredSeatNum;
	}
	public Long getRepairMileageLimit() {
		return repairMileageLimit;
	}
	public void setRepairMileageLimit(Long repairMileageLimit) {
		this.repairMileageLimit = repairMileageLimit;
	}
	public Long getRepairMonth() {
		return repairMonth;
	}
	public void setRepairMonth(Long repairMonth) {
		this.repairMonth = repairMonth;
	}
	public Long getVehicleQuality() {
		return vehicleQuality;
	}
	public void setVehicleQuality(Long vehicleQuality) {
		this.vehicleQuality = vehicleQuality;
	}
	
	
}
