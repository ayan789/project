package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
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
}
