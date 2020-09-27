package com.ccic.salesapp.noncar.dto;

import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.Engineering;

import com.ccic.salesapp.noncar.dto.request.noncar.AddressDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.AddressInfo;
import com.ccic.salesapp.noncar.dto.request.noncar.CargoAgreement;
import com.ccic.salesapp.noncar.dto.request.noncar.CargoTransport;
import com.ccic.salesapp.noncar.dto.request.noncar.GradeInfo;
import com.ccic.salesapp.noncar.dto.request.noncar.HomeDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.Household;
import com.ccic.salesapp.noncar.dto.request.noncar.LawCaseDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.LiftDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.LimitDeductible;
import com.ccic.salesapp.noncar.dto.request.noncar.MachineDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.MedicalInstitution;
import com.ccic.salesapp.noncar.dto.request.noncar.NonMotorDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.Patent;
import com.ccic.salesapp.noncar.dto.request.noncar.Person;
import com.ccic.salesapp.noncar.dto.request.noncar.PersonDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.PetsModelDtl;
import com.ccic.salesapp.noncar.dto.request.noncar.PolicyDeductible;
import com.ccic.salesapp.noncar.dto.request.noncar.PolicyForm;
import com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk;
import com.ccic.salesapp.noncar.dto.request.noncar.PolicySchoolDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.ProductDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.SecurityProjectDetail;
import com.ccic.salesapp.noncar.dto.request.noncar.SharedCoverageGroup;
import com.ccic.salesapp.noncar.dto.request.noncar.StudentInfo;
import com.ccic.salesapp.noncar.dto.request.noncar.SuperviseeOwner;
import com.ccic.salesapp.noncar.dto.request.noncar.Vehicle;
import com.ccic.salesapp.noncar.dto.request.noncar.Voyage;
import com.ccic.salesapp.noncar.dto.request.noncar.VoyageTransit;
import com.ccic.salesapp.noncar.dto.request.noncar.Ward;

import lombok.Data;
@Data
public class PolicyLob {
	private String businessLicenseNo;// 经营许可证/营业执照号码
	private String businessPlace;// 营业场所(行业信息)
	private String enterpriseNature;// 企业性质
	private String enterpriseNatureCode;// 企业性质(行业信息)
	private String industryRisk;// 纯风险损失行业
	private String industryType;// 行业类别(办公室,工商业者,生产性企业)
	private String liftCount;// 电梯数量
	private String riskType;// 风险类型
	private String homePurposeCode;// 房屋用途（自住、租赁）
	private String homeStructure;// 房屋结构（钢混结构、砖混结构、砖木结构、钢结构钢、钢筋混凝土结构、砖砌结构）
	private String homeType;// 房屋类型（自住、商住两用、出租、其他）
	private String otherDescription;// 其他说明
	private String platformEndoNo;// 平台批单号
	private String platformPolicyNo;// 平台保单号
	private String safeProductionLicenseNo;// 生产许可证号（矿长安全生产许可资格证号）
	private Double additionRatio;// 加成比例（进出口货运险）
	private String airInsurerBranch;// 航保机构
	private String billNumber;// 提单号（进出口货运险）
	private String cargoCount;// 包装及数量（页面录入的是文本）
	private String cargoGoods;// 保险货物项目（进出口货运险）
	private String cargoIssueDate;// 签单日期-进出口货运险
	private String cargoMarks;// 唛头标记
	private String contractNo;// 合同号（进出口货运险）
	private Double contractPrice;// 合同价格
	private String creditNumber;// 信用证号（货物运输保险）
	private String effectiveDatePrint;// 起运日期打印
	private String invoiceAmount;// 发票数量
	private String invoiceCurrencyCode;// 发票币种
	private String invoiceNumber;// 发票号（进出口货运险）
	private String isModifiedClause;// 是否修改条款（DC:是否修改了协议上约定的承保条件）
	private String isModifiedDeductInfo;// 是否修改免赔信息（DC:是否修改了协议上约定的承保条件）
	private String isModifiedForm;// 是否修改特别约定（DC:是否修改了协议上约定的承保条件）
	private String originalCount;// 正本数量
	private String priceCondition;// 价格条件（进出口货运险）
	private Double annualRevenue;// 业务年收入(元)
	private Double auditOperatingRevenue;// 其中审计业务经营收入
	private String autoLiabilityScope;// 车险保单责任范围
	private Double avgPremiumRate;// 费率（合计）
	private String businessNatureCode;// 被保险人营业性质（营业性质）
	private String businessNatureDesc;// 营业性质描述
	private String businessPlaceCount;// 营业场所数量
	private String calcBasis;// 保费计算基础(DC:某几个责任险需选择，有四类选择，公众责任险，例如按面积等)
	private String cargoTypeDesc;// 货物类别（文本框）
	private String companySize;// 公司规模（企业规模/投保人公司规模）
	private String compenProject;// 赔偿项目
	private String compensationStandard;// 赔偿标准
	private String continIssueDay;// 连续承保日
	private String directorCount;// 董监高人数
	private Double estiSumInsured;// 集装箱设备的预计保险金额
	private Double expectedAnnualSalesAmount;// 预计年销售额
	private String extendedReportYears;// 扩展报告期__年
	private String guaranteeYear;// 保障年限
	private String industryDetailDesc;// 行业详细描述
	private String industryTypeDetail;// 行业类型细分
	private String industryTypeFour;// 行业类型四级
	private String industryTypeLarge;// 行业类型大
	private String industryTypeMiddle;// 行业类型中
	private String insuranceArea;// 投保的经营范围
	private String insuranceAreaDesc;// 经营范围说明
	private String insuredAreaCode;// 投保区域/承保区域范围
	private String insuredBasisCode;// 承保基础
	private String isProposalOverseas;// 是否投保境外业务
	private String issueCompanyCount;// 承保企业数量
	private String lastYearOperatingRevenue;// 上年经营收入（万元）
	private String licenseNo;// 执业许可证号
	private String listPlace;// 上市地点
	private Double minPremium;// 最低保险费
	private String openingDate;// 开业年份\成立时间
	private Double operatingRevenue;// 经营收入/营业额/销售收入/业务年收入
	private String organizationalForm;// 组织形式
	private String otherTypeDesc;// 其他类别描述
	private String ownershipSystemCode;// 所有制
	private String partnerCount;// 合伙人数
	private String possessNatureCode;// 占用性质
	private Double premiumRate;// 费率，反算出来，用于打印
	private String purchaseCarType;// 购车类型（新车（中规车）、次新车、新车非中规车、旧车、二手车）
	private String qualiAccountantsCount;// 其中具有执业资格的会计师人数
	private String quotationNoFromPlatform;// 报价单号来源于广东非车监管平台
	private Double registeredCapital;// 注册资金
	private String retrospectivePeriodStart;// 追溯日期，自*年*月*日*时起
	private String retrospectiveYears;// 追溯期：_＿＿年
	private String riskCount;// 风险数量
	private String riskCountType;// 风险数量类型
	private String riskLevelCode;// 风险等级
	private String schoolType;// 学校类别
	private String sellAreaCode;// 销售区域
	private String shareholderCount;// 股东人数   
	private String square;//面积、营业面积（平方米）
	private String staffSumCount;// 员工总人数
	private String stockNo;// 股票代码
	private Double sumSalesAmount;// 累计销售额
	private Double totalAsset;// 总资产
	private String transportArea;// 运输地区
	private String transportType;// 运输方式
	private String unitCode;// 单位（吨、立方米、人公里、吨公里）
	private String waitYear;// 等待期：＿年
	private Double yearSalaryAmount;// 年总工资额/年工资额
	private String yearYield;// 年产量
	private Double duePremium;// 应收保费/真实保费/RealPremium,=调整保费
	private int eachPersonCopies;// 每人投保份数
	private String effectiveDate;// 起保日期/起始生效日期,支持时分秒
	private String expiryDate;// 终保日期，保单止期/止保日期/失效日期，不包含这个时间点在内
	private String insuredCount;// 标的数量/分组人数
	private String insuredNatureCode;// 被保险人性质，代码值（延保服务商,生产商,批发商,零售商,进口商,出口商）等，不同产品使用相同的码表，按产品代码过滤记录，
	private String mainClauseId;// 主险条款ID，用于非车类产品统一为多个标的选择条款
	private String mainCoverageId;// 主险责任ID，用于非车类产品统一为多个标的选择主险条款下的责任
	private String otherInsuredCondition;// 其他承保条件
	private String planCode;// 方案代码
	private String policyStatus;// 保单状态（1未生效2已生效3失效4批改临时删除状态）
	private String productCode;// 产品代码
	private String proposalType;// 投保方式
	private Double sumInsured;// 保额
	private String sumInsuredModeCode;// 保额确定方式\按照何种方式确定保险金额
	private String totalNumberOfCopies;// 总份数
	private String totalPeopleNumber;// 总人数
	private Double vat;// VAT金额(增值税)
	private Double vatRate;// 增值税率
	private String roadInsuredBasisCode; //承保基础(道路危险货物承运人责任险)业务上码表含义不同，要求拆分，经jiafa确认
	private String empInsuredBasisCode; //承保基础(公众雇主责任险)业务上码表含义不同，要求拆分，经jiafa确认
	private String exhibitionName; //展会名称
	private Map<String, String> tempData;// 临时数据
	private Long sequenceNumber;//序号
	private List<AddressInfo> addressInfoList;// 地址信息
	private List<HomeDetail> homeDetailList;// 房屋清单信息
	private List<NonMotorDetail> nonMotorDetailList;// 非机动车辆信息
	private List<LiftDetail> liftDetailList;// 电梯清单信息
	private List<PolicyRisk> policyRiskList;// 标的信息
	private List<CargoAgreement> cargoAgreementList;// 货运险预约协议-货物信息
	private List<CargoTransport> cargoTransportList;// 运输信息
	private List<Voyage> voyageList;// 航程信息
	private List<VoyageTransit> voyageTransitList;// 转运信息
	private List<SharedCoverageGroup> sharedCoverageGroupList;// 附加条款信息
	private List<PolicyForm> policyFormList;// 特别约定信息
	private List<PolicyDeductible> policyDeductibleList;// 免赔模板信息
	private List<LimitDeductible> limitDeductibleList;// 保单级别限额免赔信息
	private List<Person> personList;// 医务人员信息/人员/会计师/设计师/雇员，投保人的投行部负责人、保荐代表人、其分管领导以及各保荐人的简要信息
	private List<MedicalInstitution> medicalInstitutionList;// 医疗机构信息
	private List<Ward> wardList;// 被监护人清单
	private List<MachineDetail> machineDetailList;// 特种设备清单
	private List<PolicySchoolDetail> policySchoolDetailList;// 学校清单
	private List<StudentInfo> studentInfoList;// 学生清单
	private List<Patent> patentList;// 专利清单
	private List<Household> householdList;// 分户清单
	private List<ProductDetail> productDetailList;// 产品信息
	private List<Vehicle> vehicleList;// 机动车辆信息清单
	private List<AddressDetail> addressDetailList;// 地址信息
	private List<GradeInfo> gradeInfoList;// 年级信息
	private List<SecurityProjectDetail> securityProjectDetailList;// 证券经销商项目信息
	private List<LawCaseDetail> lawCaseDetailList;// 诉讼案件信息
	private List<SuperviseeOwner> superviseeOwnerList;// 监管物所有者信息
	private List<PersonDetail>personDetailList; //被保人信息
	private List<Engineering>engineeringList; //工程信息
	private List<PetsModelDtl>petsModelDtlList; //宠物信息
}
