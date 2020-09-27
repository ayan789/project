package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class VehicleInsured {
	private String specialApplicationCode;//	特殊车投保标志
	private String isSpecialShapeVehicle;//是否异型车标志
	private String vehicleCode;//精友系统车辆编码/车型代码
	private String industryModelCode;//行业车型编码
	private String carName;//车款名称
	private String announcedModel;//公告型号
	private String gasType;//能源种类
	private String jyBrand;//精友品牌
	private String jySeries;//精友车系
	private String jyStandardName;//精友车型名称
	private String isLoanVehicle;//是否车贷车
	private String specialApplicationFlag;//特殊车投保标志
	private String isTransferVehicle;//是否过户车
	private String isNotRegistered;//是否未上牌
	private String IsNonlocalVehicle;//外地车标志
	private String hkLicenseNo;//香港号牌
	private String licenseBackgroundCode;//号牌底色
	private String licenseBackgroundDesc;//号牌底色描述
	private String licenseNo;//号牌号码/内地号牌
	private String licenseType;//号牌种类
	private String licenseTypeDesc;//号牌种类名称
	private String vehicleKindCode;//车辆种类
	private String vehicleKindDesc;//车辆种类名称
	private String vehicleKindTcCode;//交管车辆类型代码
	private String vehicleKindTcDesc;//交管车辆类型名称
	private String vehicleUseNatureCode;//车辆使用性质
	private String vehicleUseNatureDesc;//车辆使用性质描述
	private String displacement;//排量
	private String vehicleInitialRegDate;//车辆初次登记日期
	private String vehicleTransferRegDate;//转移登记日期/过户日期
	private String approvalQuality;//核定载质量
	private String approvalSeatCount;//核定载客/核定座位数
	private String drivingLicenseModel;//行驶证车型
	private String engineNo;//发动机号
	private String model;//厂牌型号
	private String modelCateCode;//车型分类
	private String isNewVehicle;//新车
	private BigDecimal newVehiclePurchasePrice;//新车购置价
	private String power;//功率
	private String realUseYear;//实际使用年数
	private BigDecimal realValue;//实际价值
	private BigDecimal totalMassOfTraction;//准牵引总质量
	private String tractorTrailerPolicyNo;//牵引车/挂车关联保单号
	private String trunkCategory;//货车分类
	private String trunkCategoryDesc;//货车分类描述
	private String vehicleQuality;//整备质量
	private String vin;//车架号/VIN码
	private String importType;//车辆进口类型
	private String runArea;//行驶区域
	private String factoryCode;//厂家编码
	private String purchaseInvoiceDate;//购车发票日期
	//private List<PolicyClause> policyClause;//条款
	private List<PolicyCoverage> policyCoverages;
	private List<VehicleAccessory> vehicleAccessorys;//新增设备列表
	private Map<String,String> extensionMap;



}
