package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.request.InsuredGroup;

import lombok.Data;

@Data
public class PolicyRisk {
	private Double duePremium;// 应收保费/真实保费/RealPremium,=调整保费
	private String effectiveDate;// 起保日期/起始生效日期,支持时分秒
	private String expiryDate;// 终保日期，保单止期/止保日期/失效日期，不包含这个时间点在内
	private String insuredCategory;// 标的分类
	private String insuredDescription;// 标的描述
	private String insuredGroupSeqNo;// 责任分组序号
	private String insuredName;// 标的名称，内部冗余，根据一定规则从不同的标的子类中取值，用于报表/查询等
	private String insuredNo;// 标的编号(平台过来)
	private String numberOfCopies;// 份数
	private String planCode;// 方案代码
	private String productElementCode;// 产品元素代码
	private String policyStatus;// 保单状态（1未生效2已生效3失效4批改临时删除状态）
	private Double premiumRate;// 费率
	private Double sumInsured;// 保额
	private String totalNumberOfCopies;// 总份数，团单时使用，=份数*人数
	private Double vat;// VAT金额(增值税)
	private Double vatRate;// 增值税率
	private String addressSequenceNumber;// 地址序号
	private String insuredItemName;// 标的项目名称
	private String itemCategory;// 项目类别
	private String itemValueBasisCode;// 保险价值确定依据(出险时的重置价值,出险时的账面余额,出险时的市场价值,出险时的账面原值,其他价值)
	private String otherValueBasisDesc;// 其他价值说明
	private String sumInsuredBasisCode;// 保险金额确定依据(账面原值,账面余额,重置重建价,账面原值加成*%,账面余额加成*%,市场价,评估价,净值,定值,其他)
	private String wordUnitSecurityEquip;// 保额确定依据说明
	private Double approvalQuality;// 核定载质量\总牵引质量(吨)
	private String cargoName;// 货物名称
	private String cargoType;// 货物类别
	private String engineNo;// 发动机号
	private String licenseNo;// 车辆牌号
	private String packagingCount;// 包装数量
	private String packagingType;// 包装方式/类型
	private String vehicleModelCode;// 车型
	private String vin;// 车架号
	private String adminDivision;// 行政区域（校园方责任险）
	private String brand;// 品牌
	private String count;// 人数（学生人数）}
	private Double deadPremiumRate;// 人身伤亡费率
	private Double estimateSalesAmount;// 预计销售额
	private String insuredCount;// 标的数
	private String insuredSubCategory;// 标的分类，不同产品需要加各自的码表过滤条件
	private String listString;// 上市时间
	private String listPlace;// 上市地点
	private Double medicalPremiumRate;// 医疗费费率
	private Double minSponsorFee;// 最低承销及保荐费
	private String modelCode;// 型号规格，不同产品需要加各自的码表过滤条件
	private String otherInsuredCategoryDesc;// 其他标的分类描述
	private Double price;// 单价
	private String productName;// 产品名称\工程名称
	private String productionCount;// 生产能力-生产数量
	private String productionUnit;// 生产能力-单位
	private String projectName;// 工程名称/发行人/项目名称
	private Double projectTotalCost;// 工程总造价
	private Double raiseAmount;// 募集资金额
	private String refGroupDesc;// 组别描述
	private String refGroupSeq;// 车辆组别序号
	private String schoolName;// 学校名称（校园方责任险）
	private String schoolNatureCode;// 学校性质
	private String schoolType;// 学校类别
	private String sellAreaCode;// 销售区域
	private Double sponsorFee;// 承销及保荐费
	private String stockCode;// 股票代码
	private String thirdPartyLiability;// 第三者责任
	private Double unitPremium;// 单位保费
	private String warrantyYears;// 有限质保年限
	private Double yearSalaryAmount;// 年工资额
	private String insuredGroupNo;// 责任分组序号
	private String insuredUid;// 标的唯一键
	private Map<String, String> tempData;// 临时数据
	private Long sequenceNumber;//序号
	private List<PolicyCoverage> policyCoverageList;// 主条款信息
	private List<Vehicle> vehicleList;//车辆信息
	private List<PersonInsured> personInsuredList ;
	private List<InsuredGroup> insuredGroupList;
}
