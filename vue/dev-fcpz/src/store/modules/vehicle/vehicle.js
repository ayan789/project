/*
 * @Author: King
 * @Page: 车保数据
 * @since: 2020-03-03 12:06:29
 * @Last Modified by: chenjiafeng
 * @Last Modified time: 2020-07-06 17:56:04
 * @FilePath: /src/store/modules/vehicle/vehicle.js
 */
import Vue from "vue";
import { calculateActualValue, getTaxConfig } from "@src/http/module/vehicle";
import utils from "@src/utils/index";
import { Dialog } from "vant";
Vue.use(Dialog);
import { Channel } from "../channel/channel";
import Config from "@src/common/json/newCoreDataDictionary";
const image =
  "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANwAAAAoCAIAAAAaOwPZAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAQuSURBVHhe7ZptmoMgDIR7rh6o5+lpvEwP01XUGshAokgX+8z+7PKRTF6SoN7e/KMCnSlw68wemkMF3oSSEHSnAKHsLiQ0iFCSge4UIJTdhYQGEUoy0J0ChLK7kNAgQkkGulOAUHYXEhpEKMlAdwpcG8rhcRv/HkN3stIgW4F88DYoX89nObjmANuOc0eMXpHHcyX9+mowhgHKmdlChM0BZzvzet6DSSW7xjEWk8Hu+/O1x7zF1237/Uu4t/O46V6sZuARoZb9KqbO7On4rJlykqcYYnNAjSbx3Gmrj6WTzxirVlA+90F82G+nm4fX3zOxgqyKqRaUU7b8FpRDOeyjJa7k5oByT1yWse4mxfDC3NrrprnQtQeUMuUXoURmCGHdKfl/oTS8MElxu2mudO0BXUCZL8efVGU0EmsQjkGpM2H8y/CwGtW1C3el8ywxhHKWxgOlaPNj0VcRRW+OoiKvCXF0o6YeXWLQDaNQyMf1Clhsi22D9HUNXOBCVZamaBmiO5BxRdRQOt3M3oFUAD4/HDolSChx7AvXzRIJQtgsUfMu6HB+HglNLc5d5KiwpcAqTH7Idk/lvLD9Z0rUx4vYWL2UJ4WY6XbdL91ML57+EjsRNEMnw/LCrKklN9NNkbuLvKsdabjM/ZMByh+PDWuuw6kDEYXPzeSfzGARlNG1M1ENRCfGLlUuJ5MVTg+UyxGzC+1+KN/DkDyuTSVbqo7vNnagfKPTrH9b8pQtgQ/PRCifDTaUJaIWw8adUycklLrcppkyCZfkJ5cYlSZnQTkmsYf58OYAlMpg6JnlhYlC9uxhIdWvbr1NS8Ahc9pgQlkkai3fOorVUK4JGeYTJIgVTm+mnCqrmSfOgDJ0mOlOlhcmClk3M0KmPzeF0mnDGVB6LjqbmKB8p5GRQ34DStRCdpEpp5MRNWRNocwsjk9i7nyqugzPYTWUSZuqe0qVucAT5tgH9ITmxEdCdihjpcCVAgfI8uJ4pgx3K3UhgBeRQ9dtbJmjp1TnYmsKoSH1UGqKE23mxlrsri4yKsuAFnZ5BrAugypw0/IdSvHmxHJbEI6lREzj0asuOc7TR8BONdd9pNKCo4LRNY9CdgCEXjqObDhQvsFpy7z7DsqHP9khxp9DzNeKbSR+Iy3/n31tqVFYe17xFUZkTu507+4px4USFwBRm32lbzFyXphgRMtn3cwqqaef8a0UrMHlaJYM8RC1Iq2DeOXvKUdVjALmzromST8+4N+Egm9rrwzl/DpAVlddnE9su36Jyx6ECtkUxufaUMJOzfwQsxldUbnTLyO/ckCcNsS112yDmkkGF/4xKL8rHndrowChbKMrV61QgFBWiMepbRQglG105aoVChDKCvE4tY0ChLKNrly1QgFCWSEep7ZRgFC20ZWrVihAKCvE49Q2ChDKNrpy1QoF/gDXIhmWmc+CSAAAAABJRU5ErkJggg==";

const BusinessKinds = [
  {
    kindCode: "C101011",
    kindName: "车辆损失保险（全面型）"
  },
  {
    kindCode: "C101012",
    kindName: "附加车轮单独损坏除外特约险（全面型）"
  },
  {
    kindCode: "C101015",
    kindName: "新增设备损失险（全面型）"
  },
  {
    kindCode: "C100120",
    kindName: "机动车交通事故责任强制保险"
  },
  {
    kindCode: "C100121",
    kindName: "车辆损失保险"
  },
  {
    kindCode: "C100122",
    kindName: "第三者责任保险"
  },
  {
    kindCode: "C100123",
    kindName: "车上人员责任保险（司机）"
  },
  {
    kindCode: "C100124",
    kindName: "车上人员责任保险（乘客）"
  },
  {
    kindCode: "C100125",
    kindName: "全车盗抢保险"
  },
  {
    kindCode: "C100126",
    kindName: "玻璃单独破碎险"
  },
  {
    kindCode: "C100129",
    kindName: "车身划痕损失险"
  },
  {
    kindCode: "C100128",
    kindName: "新增加设备损失险"
  },
  {
    kindCode: "C100127",
    kindName: "自燃损失险"
  },
  {
    kindCode: "C100135",
    kindName: "指定修理厂险"
  },
  {
    kindCode: "C100134",
    kindName: "无法找到第三方特约险"
  },
  {
    kindCode: "C100130",
    kindName: "发动机涉水损失险"
  },
  {
    kindCode: "C100131",
    kindName: "修理期间费用补偿险"
  },
  {
    kindCode: "C100148",
    kindName: "起重、装卸、挖掘车辆损失扩展条款"
  },
  {
    kindCode: "C100149",
    kindName: "特种车辆固定设备、仪器损坏扩展条款"
  },
  {
    kindCode: "C100133",
    kindName: "精神损害抚慰金损失险"
  },
  {
    kindCode: "C100132",
    kindName: "车上货物责任险"
  }
];
const defaultState = {
  vehicleType: "", //车辆类型 chenjiafeng非家用车添加
  orderId: "", //订单号
  proposalNo: "", //投保单号
  trafficProposalNo: "", //交强险投保单号
  businessProposalNo: "", //商业险投保单号
  wealchAccProposalNo: "", //联合销售投保单号
  car: {
    vehicleTypeFlag: "", //车辆类型 chenjiafeng非家用车添加
    actualValue: "", //新车实际价值
    arbitBoardcode: "", //仲裁委员会代码
    arbitBoardname: "", //仲裁委员会名称
    argueSolution: "", //争议解决方式
    carCheckStatus: "", //验车情况 1:验车 0:未验车
    carCheckTime: "", //验车时间
    carChecker: "", //验车人
    complementTime: "", //验车后补时间
    carInvoiceDate: "", //购车发票日期(上海北京地区)
    carRegiste: "", //是否新车报备(北京地区) 1:备案 0:未备案
    certificateDate: "", //车辆来历凭证所载日期（北京地区）
    certificateNo: "", //车辆来历凭证编号（北京地区）
    certificateType: "", //车辆来历凭证种类（北京地区）
    colorCode: "", //车身颜色
    ecdemicVehicle: "", //是否外地车 1:外地车 0:非外地车
    energyType: "", //能源类型
    fuelType: "", //能源类型
    engineNo: "", //发动机号
    enrollDate: "", //车辆初登日期
    exhaustScale: "", //排量
    firstyearDate: "", //首年投保时间(上海地区)
    frameNo: "", //车架号
    isAboutAgri: "0", //是否涉农 1:涉农 0:非涉农
    licenseColorCode: "", //号牌底色
    licenseNo: "", //车牌号
    licenseType: "", //号牌种类
    printUseNatureCode: "", //打印使用性质
    loanVehicleFlag: "0", //是否多年车贷投保标志 1:是 0:否
    madeFactory: "", //制造厂名称
    modelCode: "", //行业车型代码
    modelName: "", //车款名称
    net: "", //整备质量
    newVehicle: "", //是否新车 0：非新车 1：新车
    noLicenseFlag: "0", //是否上牌车 0：未上牌，1：上牌
    noticeType: "", //公告型号
    powerScale: "", //功率
    areaFactor: "", //摩托车地区因子
    purchasePrice: "", //新车购置价
    runAreaCode: "", //行驶区域
    runMiles: "", //行驶里程
    salesChannel: "", //销售渠道（上海地区）
    seatCount: "", //核定载客数
    restricFlag: "", //单双号标志(北京地区)
    specialCarFlag: "", //特殊车投保标志
    tonCount: "0", //核定载质量
    haulage: "", //牵引总质量
    rateClassification: "", //拖拉机地区费率分类
    chgOwnerFlag: "", //是否过户车 0：非过户车，1：过户车
    transferDate: "", //转登日期
    travelIssueDate: "", //行驶证发证日期（北京地区）
    travelVehicleModel: "", //行驶证车型
    carTypeAlias: "", //行驶证车型
    useNatureCode: "", //车辆使用性质
    userYear: "", //使用年限
    useMonth: "", //使用月份，用于判断是否新车（小于9个月为新车） TUDO chenjiafeng
    vehicleBrand: "", //车辆厂牌型号
    vehicleCategory: "K33", //交管车辆类型
    renewalFlag: "0", //续保标志
    carKindCode: "", //车辆种类
    vehicleHyCode: "", //行业
    importFlag: "", //车型种类 1 国产(合资) 2 进口
    brandCode: "",
    brandName: "",
    vehicleJingyou: {} //精友车型信息
  },
  carOwner: {
    custType: "01", //客户类型，01-个人，02-单位
    address: "", //客户地址
    age: "", //车主车龄
    areaCode: "", //车主地区代码
    areaDetail: "", //车主地区详细地址
    birthday: "", //生日
    gender: "", //车主性别, 1-男, 2-女
    id: "", //主键
    identityNo: "", //车主证件号
    identityType: "", //证件种类
    identifyLongTerm: "0", //
    identifyStartDate: "",
    identifyEndDate: "",
    name: "", //车主姓名
    nationality: "CHN", //国籍
    nature: "", //车主性质
    occupation: "900", //职业
    phoneNo: "", //电话
    postCode: "", //邮编
    unitType: "", //车主单位性质
    vehicleId: "" //车辆信息id
  },
  policyHolder: {
    ...RelationPeople
  }, //投保人信息
  recognizee: {
    ...RelationPeople
  }, //被保人信息
  invoice: {
    //开票信息
    bankAccount: "", //开户行帐号
    bankAgentCode: "", //开户银行代码
    bankAgentName: "", //开户银行名称
    bankBranchesCode: "", //开户银行支行代码
    bankBranchesName: "", //开户银行支行名称
    clientType: "", //客户类型
    companyNameType: "", //公司名称类型
    email: "", //邮件地址
    invoiceRelation: "1", //发票抬头关联关系：1-同投保人，2-同被保人
    invoiceTitle: "", //发票抬头
    invoiceType: "", //发票类型
    isNeedInvoice: "1", //是否电子发票
    phoneNo: "", //手机号
    taxCompanyName: "", //公司名称
    taxPayerIdentification: "", //纳税人识别号
    taxPayerType: "", //纳税人类型
    taxRegistryAddress: "", //税务登记地址
    taxRegistryPhone: "" //税务登记电话
  },
  restOptions: {
    needElectronicProposal: "1", //电子投保
    clsLevel: "", //反洗钱等级
    needElectronicInvoice: "1" //电子发票
  },
  projectChannel: {
    agentAgreementCode: "", //代理协议代码
    agentAgreementName: "", //代理协议名称
    agentCode: "", //代理人代码
    agentName: "", //代理人
    businessCode: "", //业务编码
    businessName: "", //业务名称
    businessType: "", //业务风险分类
    businessNature: "", //业务来源
    channelName: "", //渠道名称
    isProxy: "",
    id: "", //主键id
    isDefault: "", //是否默认(1:是,0:否)
    mobile: "", //联系电话
    operator: "", //归属经办人
    operatorPhone: "", //归属经办人电话
    orgCode: "", //组织机构代码编号
    agencyOrgCode: "", //中介机构代码
    permitNo: "", //许可证号
    exhibitionNo: "", //执业证书编号
    projectCode: "", //项目编码
    projectName: "", //项目名称
    salesCodeName: "", //销售机构名称
    salesIdNo: "", //销售人员身份证号
    salesName: "", //销售人员
    thirdBusinessInfo: "", //第三方业务员
    thirdPartyHandlerName: "", // 第三方业务员姓名
    thirdPartyHandlerNo: "" // 第三方业务员工号
  },
  quoteInfo: {
    ccicVehicleScore: "", //C指数
    smartscore: "", //是否显示C指数 Y为显示，N为不显示
    smartscoredetails: "", //是否显示风险因子影响度列表 Y为显示，N为不显示
    xcarscore: "", //是否显示Xcar指数 Y为显示，N为不显示
    businessLastClaims: [], //商业险上年理赔信息[{...}]
    commission: {
      //手续费信息表{...}
      insureAwards: [], //活动奖励
      commercialFeec1Limit: "", //商业险手续费金额（不含税）
      commercialFeec1Rate: "", //商业险c1费用率
      commercialFeecLimit: "", //商业险c费用额（不含税）
      commercialFeecRate: "", //商业险c费用率
      compulsoryFeec1Limit: "", //交强险手续费金额（不含税）
      compulsoryFeec1Rate: "", //交强险c1费用率
      compulsoryFeecLimit: "", //交强险c费用额（不含税）
      compulsoryFeecRate: "", //交强险c费用率
      totalC1feeLimit: "", //整单手续费金额（不含税）
      totalC1feeRate: "", //整单c1费用率
      totalCfeeLimit: "", //整单c费用额（不含税）
      totalCfeeRate: "" //整单c费用率
    },
    floating: {
      //商业险浮动系数及原因{...}
      discountCoefficient: "", //整单优惠系数
      floatingItems: [], //商业险浮动信息
      xcarNum: "" //X-Car指数
    },
    planCode: "", //方案代码(s:简约版 e:经济版 l:豪华版)
    plans: [], //方案列表[]
    quoteCount: {
      //报价次数信息表{...}
      businessQuoteMaxNum: "", //商业险报价次数限制
      businessQuoteNum: "", //商业险已报价次数
      compulsoryQuoteMaxNum: "", //交强险报价次数
      compulsoryQuoteNum: "", //交强险已报次数
      sumQuoteCount: "1" //总报价次数（交商报价次数之和）
    },
    trafficLastClaims: [], //强险上年理赔信息[{...}]
    userCode: "" //业务员编号
  },
  taxConfig: null, //车船税信息
  historyPolicy: null, //历史保单数据
  insurancePlan: {
    ...Plan
  }, //报价的计划
  insureAwards: [], //活动
  uwResult: {
    uwType: "3"
  },
  engageList: {
    engages: [],
    bizEngages: []
  }, //特别约定
  rightsInterestsList: [], //星级权益
  control: {
    from: "", //进入车险的路由来源
    aliCount: 0, //阿里预填数据次数
    vehicleInfoInit: false,
    vehicleModelChecked: false,
    vehicleQuote: false,
    vehiclePrivyInfo: false,
    vehicleConfirm: false,
    quoted: false,
    licenseNoImage: "",
    frameNoImage: "",
    enrollDateImage: "",
    engineNoImage: "",
    vehicleBrandImage: "",
    carOwnerImage: "",
    carOwnerIdentifyNoImage: "",
    carOwnerBirthdayImage: "",
    carOwnerGenderImage: "",
    carOwnerAddressImage: "",
    policyHolderIdentifyNoImage: "",
    policyHolderImage: "",
    policyHolderBirthdayImage: "",
    policyHolderGenderImage: "",
    policyHolderAddressImage: "",
    recognizeeIdentifyNoImage: "",
    recognizeeImage: "",
    recognizeeBirthdayImage: "",
    recognizeeGenderImage: "",
    recognizeeAddressImage: ""
  },
  paymentInfo: {
    productName: "",
    paymentNo: "",
    checkNo: ""
  },
  trafficPlatformInfo: {
    adjustRate: "", //调整系数
    claimRate: "", //理赔调整系数
    claimRateFloatCode: "", //理赔调整系数浮动代码
    claimRateFloatDesc: "", //理赔调整系数浮动描述
    decisionId: "", //决定书编号
    decisionType: "", //决定书类型
    driverName: "", //违法驾驶员姓名（仅贵州使用）
    drivingLicence: "", //违法驾驶员驾驶证号；如果无证驾驶，则为空
    peccancyList: [],
    manageTime: "", //违法处理时间
    peccancTime: "", //违法时间
    peccancyCode: "", //违法行为代码
    peccancyDetail: "", //违法行为内容（仅贵州使用）
    peccancyId: "", //违法编号（全国唯一标识）
    peccancyPlace: "", //违法地点
    processingStatus: "", //处理状态；(是否已处理) --1 已处理–0 未处理
    querySequenceNo: "", //投保查询码
    renewalFlag: "", //转保业务标志
    trafficViolationRate: "", //违法调整系数
    trafficViolationRateFloatCauseCode: "", //违法系数浮动原因代码
    trafficViolationRateFloatCauseDesc: "" //违法系数浮动原因描述
  },
  businessPlatformInfo: {
    administrationArea: "", //行政区划代码
    billDate: "", //签单日期；
    coverageItemList: "", //险种信息;多个险种代码之间","分割
    decisionCode: "", //决定书编号
    decisionType: "", //决定书类型
    discountCoefficientRate: "", //整单优惠系数
    effectiveDate: "", //起保日期；
    expireDate: "", //终保日期；
    insurerArea: "", //承保地区
    insurerCode: "", //保险公司代码，四位标准的公司名称简写；
    insurerCompanyType: "", //重复投保公司种类
    jurisdictionAgencyCode: "", //违法处理机关代码
    lastEffectiveDate: "", //起保日期
    lastExpireDate: "", //终保日期
    lastPolicyBillDate: "", //签单日期
    lastPolicyId: "", //上年度保单id
    lastPolicyQueryDate: "", //投保查询日期
    lastPolicyTotalPremium: "", //最新总保费
    lastProducerCode: "", //销售渠道代码
    ncdRate: "", //无赔款优待系数
    ncdRateFloatCauseCode: "", //无赔款优待系数浮动原因代码
    ncdRateFloatCauseDesc: "", //无赔款优待系数浮动原因描述
    peccancyDetail: "", //违法行为内容（仅贵州使用）
    policyNo: "", //重复投保保单号
    queryPastDate: "", //查询码有效止期
    querySequenceNo: "", //投保查询码
    reInsureId: "", //重复投保id
    recognitionDate: "", //违法处理时间
    renewalFlag: "", //转保业务标志
    sanctionDate: "", //处罚决定时间
    sanctionPerson: "", //处罚决定人
    trafficViolationRate: "", //交通违法系数
    trafficViolationRateFloatCauseCode: "", //交通违法系数浮动原因代码
    trafficViolationRateFloatCauseDesc: "", //交通违法系数浮动原因描述
    violationList: [],
    violationCode: "", //违法编号（全国唯一标识）
    violationId: "", //主键id
    violationPlace: "", //违法地点
    violationRecordTypeCode: "", //违法行为代码
    violationRecordTypeName: "", //违法行为人姓名
    violationTime: "", //违法时间
    violationType: "", //违法类型
    vouchCode: "" //凭证代码
  },
  rightsInterests: {
    newCustLevel: 0, //新客户等级
    newIntegral: "0", //本次报价预计新增积分
    totalCore: "", //本次报价承保后积分总额
    equities: {},
    custCoreIntegral: {
      //现有值
      custIntegral: "", //客户积分
      custLevel: "", //客户等级
      integralDate: "" //积分计算时间
    }, //已有积分信息
    custCoreInfoDetails: [], //已有客户积分明细列表
    newCustCoreInfoDetails: [] //本次产品报价积分明细列表
  },
  antiInfo: {}, //反洗钱
  queryData: {
    carOwner: "",
    carOwnerImage: "", //车主姓名图片
    licenseNo: "", //车牌号
    licenseNoImage: "", //车牌号图片
    frameNo: "", //车架号
    frameNoImage: "", //车架号图片
    enrollDate: "", //初登日期
    enrollDateImage: "", //初登日期图片
    engineNo: "", //发动机号
    engineNoImage: "", //发动机号图片
    vehicleBrand: "", //厂牌型号
    vehicleBrandImage: "", //厂牌型号图片
    seatCount: "", //核定载客数
    seatCountImage: "", //核定载客数图片
    carKindCode: "K33-客车", //车辆类型
    carKindCodeImage: "", //车辆类型图片
    cardName: "", //车主姓名
    cardNameImage: "", //车主姓名图片
    identityType: "111", //证件类型
    identityNo: "", //证件号码
    identityNoImage: "", //证件号码图片
    phoneNo: "", //移动电话
    planCode: "s", //投保方案
    occupation: "900", //职业
    nationality: "CHN", //国籍
    address: "", //联系地址
    areaCode: "", //车主地区代码
    areaDetail: "", //车主地区详细地址
    gender: "", //性别
    birthday: "", //出生日期
    identifyStartDate: "", //证件有效起期
    identifyEndDate: "", //证件有效止期
    identifyLongTerm: "0" //是否长期有效
  }, //帮我出单扫描信息
  devicesAmount: 0
};

const getters = {};

export function initBusinessRisks(
  items,
  { actualValue, importFlag, seatCount, frameNo, reset = false }
) {
  //NOTE: reset只重置实际价值变更
  //某些险种, 保险金额默认为车辆实际价格
  let hasDefaultAmountEqActualValue = [
    "C101011", //"车辆损失保险(全面型)"
    "C100121", //"车辆损失保险",
    "C100125", //"全车盗抢保险",
    "C100127" //"自燃损失险"
  ];
  let hasDefaultKindFlag = [
    "C100121", //"车辆损失保险",
    "C100122", //"第三者责任保险",
    "C100125", //"全车盗抢保险",
    "C100123", //"车上人员责任保险（司机）",
    "C100124", //"车上人员责任保险（乘客）",
    "C100127", //"自燃损失险",
    "C100130", //"发动机涉水损失险",
    "C100129", //"车身划痕损失险",
    "C100133" //"精神损害抚慰金损失险"
  ];
  //NOTE: 需要尽量考虑到修改值
  if (items && Array.isArray(items)) {
    //第三者责任保险
    let C100122 = items.find(item => item.kindCode == "C100122");
    items.forEach(item => {
      let kindCode = item.kindCode;
      let kindFlag = hasDefaultKindFlag.includes(kindCode) ? "1" : "0";
      if (typeof item.amount === "number") {
        item.amount = String(item.amount);
      }
      let amount = hasDefaultAmountEqActualValue.includes(kindCode)
        ? actualValue
        : item.amount || "";
      //玻璃的种类根据新核心返回的数据中车辆是否进口车进行预填
      //若返回为“进口车”则玻璃种类预填为“进口”，国产车、合资车预填为“国产”
      if (kindCode == "C100126") {
        //"玻璃单独破碎险"
        let brokenCode = "";
        if (frameNo) {
          //NOTE #5963 使用车架号判断 2020年05月28日15:25:27
          brokenCode = frameNo.charAt(0) == "L" ? "1" : "2";
        } else {
          //NOTE 使用是否进口车判断
          // brokenCode = importFlag ? (importFlag == "2" ? "2" : "1") : "1";
        }
        item.brokenCode = item.brokenCode || brokenCode;
      }
      //指定修理厂险
      if (kindCode == "C100135") {
        let brokenCode = importFlag ? (importFlag == "2" ? "2" : "1") : "";
        item.brokenCode = item.brokenCode || brokenCode;
        // item.deductibleRate = item.deductibleRate;
      }
      //车上人员责任保险（乘客）
      if (kindCode == "C100124") {
        //TODO: 报价时会导致座位数回滚到默认值
        // if (item.quantity != seatCount - 1) {
        //   item.quantity = seatCount - 1;
        // } else
        if (item.quantity && item.quantity < seatCount) {
          item.quantity = item.quantity || seatCount - 1;
        } else {
          item.quantity = seatCount - 1;
        }
        if (!item.unitAmount && item.amount) {
          item.unitAmount = item.amount;
        }
        if (item.quantity && item.unitAmount) {
          let calcAmount = item.unitAmount * item.quantity;
          if (Number.isSafeInteger(calcAmount)) {
            item.amount = calcAmount;
            amount = calcAmount;
          }
        }
      }
      //修理期间费用补偿险
      if (kindCode == "C100131") {
        // item.quantity =
      }
      //第三者责任保险
      if (kindCode == "C100122") {
        amount = item.amount || "500000";
      }
      //三者险法定节假日限额翻倍险 保额同第三者责任保险
      //投保了第三者责任保险，方可投保三者险法定节假日限额翻倍险
      if (kindCode == "C101017") {
        if (C100122) {
          amount = C100122.amount;
        } else {
          console.warn(
            "未投保第三者责任保险,三者险法定节假日限额翻倍险不设置保额"
          );
        }
      }
      item.kindFlag = item.kindFlag || kindFlag;
      item.amount = reset ? amount : item.amount || amount;
    });
  }
  return items || [];
}

const mutations = {
  initVehicle(state) {
    let newState = JSON.parse(JSON.stringify(defaultState));
    for (const key in newState) {
      if (newState.hasOwnProperty(key)) {
        let value = newState[key];
        if (Array.isArray(value)) {
          state[key] = [];
        } else if (typeof value === "object") {
          state[key] = Object.assign({}, value);
        } else {
          state[key] = value;
        }
      }
    }
    state.projectChannel = Object.assign({}, state.projectChannel, {
      isProxy: "0",
      businessNature: "0101",
      businessNatureCode: "0101",
      businessNatureName: "业务员销售（直销）"
    });
    state.car = Object.assign({}, state.car, {
      enrollDate: "",
      ecdemicVehicle: "0",
      chgOwnerFlag: "0",
      purchasePrice: "0",
      actualValue: "0",
      userYear: "0",
      fuelType: "D1",
      vehicleCategory: "K33",
      carKindCode: "",
      licenseType: "02",
      isAboutAgri: "0",
      loanVehicleFlag: "0",
      carCheckStatus: "0",
      specialCarFlag: "0",
      argueSolution: "01",
      poCategory: "K01"
    });
    state.carInfo = Object.assign({}, state.carInfo, {
      gender: "1",
      identityType: "111",
      nationality: "CHN"
    });
    this.commit("vehicle/initPolicyHolder");
    this.commit("vehicle/initRecognizee");
  },
  setOrderId(state, orderId) {
    return (state.orderId = orderId);
  },
  setVehicleType(state, vehicleType) {
    return (state.vehicleType = vehicleType);
  },
  setQueryData(state, queryData) {
    if (!queryData) return;
    return (state.queryData = queryData);
  },
  setProposalNo(state, proposalNo) {
    return (state.proposalNo = proposalNo);
  },
  setTrafficProposalNo(state, proposalNo) {
    return (state.trafficProposalNo = proposalNo);
  },
  setBusinessProposalNo(state, proposalNo) {
    return (state.businessProposalNo = proposalNo);
  },
  setWealchAccProposalNo(state, proposalNo) {
    return (state.wealchAccProposalNo = proposalNo);
  },
  setAgentInfo(state, agent) {
    return (state.agent = agent);
  },
  initProjectChannel(state, channel) {
    for (const key in state.projectChannel) {
      if (
        state.projectChannel.hasOwnProperty(key) &&
        !channel.hasOwnProperty(key)
      ) {
        state.projectChannel[key] = "";
      }
    }
    return (state.projectChannel = Object.assign({}, Channel, channel));
  },
  setProjectChannel(state, channel) {
    if (!channel) return;
    let businessNatures = Object.keys(Config.businessNature).map(key => ({
      text: Config.businessNature[key],
      value: key
    }));
    businessNatures.push({
      text: "业务员销售（直销）",
      value: "0101"
    });
    businessNatures.push({
      text: "业务员销售（直团）",
      value: "7101"
    });
    businessNatures.push({
      text: "门店直销",
      value: "0201"
    });
    if (!channel.businessNatureName) {
      channel.businessNatureName = utils.findText(
        businessNatures,
        channel.businessNatureCode
      );
    }
    return (state.projectChannel = Object.assign(
      {},
      state.projectChannel,
      channel
    ));
  },
  setCarInfo(state, carInfo) {
    if (!carInfo) return;
    carInfo = utils.removeEmpty(carInfo);
    let mixProps = {};
    mixProps.fuelType =
      carInfo.fuelType || carInfo.energyType || state.car.fuelType;
    mixProps.chgOwnerFlag =
      carInfo.chgOwnerFlag || carInfo.transfer || state.car.chgOwnerFlag;
    mixProps.industryVehicleCode =
      carInfo.industryVehicleCode ||
      carInfo.noticeType ||
      state.car.industryVehicleCode;
    mixProps.carTypeAlias =
      carInfo.carTypeAlias ||
      carInfo.travelVehicleModel ||
      state.car.carTypeAlias;
    mixProps.travelVehicleModel =
      carInfo.travelVehicleModel ||
      state.car.travelVehicleModel ||
      carInfo.vehicleBrand;
    mixProps = utils.removeEmpty(mixProps);
    return (state.car = Object.assign({}, state.car, carInfo, mixProps));
  },
  initCarOwner(state, carOwner) {
    let newCarOwner = {
      ...state.carOwner
    };
    for (const key in newCarOwner) {
      if (newCarOwner.hasOwnProperty(key)) {
        if (carOwner.hasOwnProperty(key)) {
          newCarOwner[key] = carOwner[key];
        } else {
          newCarOwner[key] = "";
        }
      }
    }
    //NOTE: 需不需要连带清除投被保人信息呢?
    return (state.carOwner = newCarOwner);
  },
  setCarOwner(state, carOwner) {
    if (!carOwner) return;
    carOwner = utils.removeEmpty(carOwner);
    return (state.carOwner = Object.assign(
      state.carOwner,
      utils.removeEmpty(carOwner)
    ));
  },
  calcUseYears(state) {
    /**计算车辆使用年限*/
    var useYears = "";

    var enrollDate = new Date(); //初登日期
    if (state.car.enrollDate) {
      enrollDate = new Date(state.car.enrollDate.replace(/-/g, "/"));
    }
    var startDate = new Date(); //起保日期
    startDate.setDate(startDate.getDate() + 1);
    let { plans = [] } = state.quoteInfo;
    let planIndex = plans.findIndex(
      i => i.planCode === state.quoteInfo.planCode
    );
    if (planIndex !== -1) {
      let plan = plans[planIndex];
      if (plan.businessChecked == "1" && plan.businessEffectiveStartDate) {
        startDate = new Date(
          plan.businessEffectiveStartDate.replace(/-/g, "/")
        );
      } else if (plan.trafficChecked == "1" && plan.trafficEffectiveStartDate) {
        startDate = new Date(plan.trafficEffectiveStartDate.replace(/-/g, "/"));
      }
    }
    var startD = enrollDate.getDate();
    var startM = enrollDate.getMonth() + 1;
    var startY = enrollDate.getFullYear();
    var endD = startDate.getDate();
    var endM = startDate.getMonth() + 1;
    var endY = startDate.getFullYear();
    //年度差
    var yearGap = endY - startY;
    //月度差
    var monthGap = endM - startM;
    //日期差
    var dayGap = endD - startD;
    if (yearGap == 0) {
      useYears = monthGap < 11 ? 0 : dayGap >= 0 ? 1 : 0;
    } else if (yearGap == 1) {
      if (monthGap + 12 < 11) {
        useYears = 0;
      } else if (monthGap + 12 == 11) {
        useYears = dayGap >= 0 ? 1 : 0;
      } else {
        useYears = 1;
      }
    } else if (yearGap >= 2) {
      if (monthGap < 0) {
        useYears = yearGap - 1;
      } else if (monthGap == 0) {
        useYears = dayGap >= 0 ? yearGap : yearGap - 1;
      } else {
        useYears = yearGap;
      }
    }
    state.car.userYear = useYears;
    if (dayGap >= 1) {
      if (useYears > 0) {
        state.car.useMonth = useYears * 12 + monthGap;
      } else {
        if (monthGap >= 0) {
          state.car.useMonth = monthGap;
        } else {
          state.car.useMonth = 12 + monthGap;
        }
      }
    } else {
      if (useYears > 0) {
        state.car.useMonth = useYears * 12 + monthGap - 1;
      } else {
        if (monthGap >= 0) {
          state.car.useMonth = monthGap - 1;
        } else {
          state.car.useMonth = 12 + monthGap - 1;
        }
      }
    }
    console.warn("计算使用年限为 : ", useYears);
    console.warn("计算使用月份为 : ", state.car.useMonth);
  },
  setHistoryPolicy(state, historyPolicy) {
    if (state.quoteInfo.plans.length) {
      let { plans = [] } = state.quoteInfo;
      let planIndex = plans.findIndex(
        i => i.planCode === state.quoteInfo.planCode
      );
      if (planIndex === -1) return;
      let plan = plans[planIndex];
      plan = Object.assign({}, plan, historyPolicy, {
        planCode: plan.planCode
      });
      plans[planIndex] = plan;
      state.quoteInfo.plans = plans;
    }
    return (state.historyPolicy = historyPolicy);
  },
  setQuoteInfo(state, quoteInfo) {
    if (!quoteInfo) return;
    quoteInfo = utils.removeEmpty(quoteInfo);
    if (Array.isArray(quoteInfo.plans)) {
      quoteInfo.plans = quoteInfo.plans.map(plan => {
        //NOTE: 神奇数据转换
        if (/Y/i.test(plan.trafficIsEffective)) {
          plan.trafficIsEffective = "1";
        } else if (/N/i.test(plan.trafficIsEffective)) {
          plan.trafficIsEffective = "0";
        }
        if (/Y/i.test(plan.businessIsEffective)) {
          plan.businessIsEffective = "1";
        } else if (/N/i.test(plan.businessIsEffective)) {
          plan.businessIsEffective = "0";
        }
        //强险 默认立即生效选否
        plan.trafficIsEffective = plan.trafficIsEffective || "0";
        //强险 车船税默认值处理
        plan.tax = Object.assign({}, Tax, plan.tax);
        plan.tax.taxFlag = plan.tax.taxFlag || "T";
        plan.tax.taxpayerName = plan.tax.taxpayerName || state.carOwner.name;
        // if (state.carOwner.identityType == "111") {
        plan.tax.taxpayerNo = plan.tax.taxpayerNo || state.carOwner.identityNo;
        // }
        if (plan.tax.taxFlag == "C") {
          //减税
          plan.tax.adjustTaxValue = plan.tax.adjustTaxValue || "50"; //减免税比例/减免税金额
          plan.tax.deductiondueCode = plan.tax.deductiondueCode || "E"; //减免税原因
          plan.tax.deductiondueType = plan.tax.deductiondueType || "P"; //减免税方案
        } else if (plan.tax.taxFlag == "P") {
          //已完税
        } else if (plan.tax.taxFlag == "E") {
          //免税
          plan.tax.deductiondueCode = plan.tax.deductiondueCode || "E"; //减免税原因
          plan.tax.deductiondueType = "E"; //减免税方案
        } else if (plan.tax.taxFlag == "B") {
          //补税
        } else if (plan.tax.taxFlag == "D") {
          //有争议
        } else if (plan.tax.taxFlag == "R") {
          //拒缴
        }
        //商业险 默认立即生效选否
        plan.businessIsEffective = plan.businessIsEffective || "0";
        //商业险 险别默认值处理
        plan.businessRisks = initBusinessRisks(plan.businessRisks || [], {
          actualValue: state.car.actualValue,
          importFlag: state.car.importFlag,
          seatCount: state.car.seatCount,
          frameNo: state.car.frameNo,
          reset: quoteInfo.reset || false
        });
        // if (plan.wealthAccChecked == "1") {
        //联合销售 保费计算时使用的座位数
        if (!plan.wealthAccSeatCount) {
          plan.wealthAccSeatCount = state.car.seatCount;
        }
        //联合销售 被保人信息默认同车主
        if (state.carOwner.custType == "01") {
          if (
            !plan.wealthAccInsurant ||
            Object.values(plan.wealthAccInsurant).every(v => !v) ||
            plan.wealthAccInsurant.insurantName == state.carOwner.name
          ) {
            let wealthAccInsurant = {
              identityNo: state.carOwner.identityNo,
              identityType: state.carOwner.identityType,
              insurantName: state.carOwner.name,
              phone: state.carOwner.phoneNo
            };
            plan.wealthAccInsurant = Object.assign({}, Plan.wealthAccInsurant, plan.wealthAccInsurant, wealthAccInsurant);
          }
        }
        //联合销售
        //联合销售 是否代理，默认同车险代理
        if (
          !plan.wealthAccChannel ||
          Object.values(plan.wealthAccChannel).every(v => !v)
        ) {
          plan.wealthAccChannel = Object.assign({}, state.projectChannel);
          //联合销售 默认份数为1
          if (plan.wealthAccRiskCode && !plan.wealthAccBuyNum) {
            plan.wealthAccBuyNum = 1;
          }
          //联合销售 合并打印默认 是
          plan.wealthAccMergePrint = plan.wealthAccMergePrint || "1";
        }
        // }
        return Object.assign({}, Plan, utils.removeEmpty(plan));
      });
    }
    return (state.quoteInfo = Object.assign(state.quoteInfo, quoteInfo));
  },
  setTax(state, tax) {
    let { plans = [] } = state.quoteInfo;
    let planIndex = plans.findIndex(
      i => i.planCode === state.quoteInfo.planCode
    );
    if (planIndex === -1) return;
    let plan = plans[planIndex];
    plan.tax = tax;
    plans[planIndex] = plan;
    return (state.quoteInfo.plans = plans);
  },
  setTaxConfig(state, taxConfig) {
    state.taxConfig = taxConfig;
    let plans = state.quoteInfo.plans || [];
    plans = plans.map(plan => {
      let tax = plan.tax || {
        ...Tax
      };
      for (const key in taxConfig) {
        if (taxConfig.hasOwnProperty(key)) {
          if (tax.hasOwnProperty(key)) {
            tax[key] = taxConfig[key];
          }
        }
      }
      if (tax.taxFlag == "C") {
        //减税
        tax.adjustTaxValue = tax.adjustTaxValue || "50"; //减免税比例/减免税金额
        tax.deductiondueCode = tax.deductiondueCode || "E"; //减免税原因
        tax.deductiondueType = tax.deductiondueType || "P"; //减免税方案
      } else if (tax.taxFlag == "P") {
        //已完税
      } else if (tax.taxFlag == "E") {
        //免税
        tax.deductiondueCode = tax.deductiondueCode || "E"; //减免税原因
        tax.deductiondueType = "E"; //减免税方案
      } else if (tax.taxFlag == "B") {
        //补税
      } else if (tax.taxFlag == "D") {
        //有争议
      } else if (tax.taxFlag == "R") {
        //拒缴
      }
      return {
        ...plan,
        tax
      };
    });
    state.quoteInfo = {
      ...state.quoteInfo,
      plans
    };
  },
  setInsurancePlan(state, plan) {
    if (!plan || typeof plan != "object" || !Object.keys(plan).length) return;
    let businessPlatformInfo = JSON.parse(
      JSON.stringify(defaultState.businessPlatformInfo)
    );
    let trafficPlatformInfo = JSON.parse(
      JSON.stringify(defaultState.trafficPlatformInfo)
    );
    let { quoteInfo } = state;
    let { floating } = quoteInfo;
    let {
      //商业转保业务标志
      businessRenewalFlag,
      //商业投保查询码
      businessQuerySequenceNo,
      //商业查询码有效期
      businessQueryPastDate,
      //交强转保业务标志
      trafficRenewalFlag,
      //交强投保查询码
      trafficQuerySequenceNo,
      //交强查询码有效期
      trafficQueryPastDate
    } = plan;
    let { trafficFloatingItems, businessFloatingItems } = floating;
    businessPlatformInfo.queryPastDate = businessQueryPastDate;
    businessPlatformInfo.querySequenceNo = businessQuerySequenceNo;
    businessPlatformInfo.renewalFlag = businessRenewalFlag;
    trafficPlatformInfo.queryPastDate = trafficQueryPastDate;
    trafficPlatformInfo.querySequenceNo = trafficQuerySequenceNo;
    trafficPlatformInfo.renewalFlag = trafficRenewalFlag;
    if (state.quoteInfo.floating) {
      //商业浮动系数
      //整单优惠系数
      businessPlatformInfo.discountCoefficientRate =
        floating.discountCoefficient;
      //discountCode 系数代码;10-自主核保系数,20-自主渠道系数,30-无赔款优待系数,40-交通违法系数
      if (
        Array.isArray(businessFloatingItems) &&
        businessFloatingItems.length
      ) {
        //自主核保系数
        let underwriting = businessFloatingItems.find(
          item => item.discountCode == "10"
        );
        if (underwriting) {
          businessPlatformInfo.underwritingRate = underwriting.floatCoefficient;
          businessPlatformInfo.underwritingRateFloatCauseCode =
            underwriting.floatReasonCode;
          businessPlatformInfo.underwritingRateFloatCauseDesc =
            underwriting.floatReasonDesc;
          businessPlatformInfo.underwritingRateNonFloatCauseCode =
            underwriting.nonFloatReasonCode;
          businessPlatformInfo.underwritingRateFloatCauseDesc =
            underwriting.nonFloatReasonDesc;
        }
        //自主渠道系数
        let channel = businessFloatingItems.find(
          item => item.discountCode == "20"
        );
        if (channel) {
          businessPlatformInfo.channelRate = channel.floatCoefficient;
          businessPlatformInfo.channelRateFloatCauseCode =
            channel.floatReasonCode;
          businessPlatformInfo.channelRateFloatCauseDesc =
            channel.floatReasonDesc;
          businessPlatformInfo.channelRateNonFloatCauseCode =
            channel.nonFloatReasonCode;
          businessPlatformInfo.channelRateFloatCauseDesc =
            channel.nonFloatReasonDesc;
        }
        //无赔款优待系数
        let ncd = businessFloatingItems.find(item => item.discountCode == "30");
        if (ncd) {
          businessPlatformInfo.ncdRate = ncd.floatCoefficient;
          businessPlatformInfo.ncdRateFloatCauseCode = ncd.floatReasonCode;
          businessPlatformInfo.ncdRateFloatCauseDesc = ncd.floatReasonDesc;
          businessPlatformInfo.ncdRateNonFloatCauseCode =
            ncd.nonFloatReasonCode;
          businessPlatformInfo.ncdRateNonFloatCauseDesc =
            ncd.nonFloatReasonDesc;
        }
        //违法调整系数
        let trafficViolation = businessFloatingItems.find(
          item => item.discountCode == "40"
        );
        if (trafficViolation) {
          businessPlatformInfo.trafficViolationRate =
            trafficViolation.floatCoefficient;
          businessPlatformInfo.trafficViolationRateFloatCauseCode =
            trafficViolation.floatReasonCode;
          businessPlatformInfo.trafficViolationRateFloatCauseDesc =
            trafficViolation.floatReasonDesc;
          businessPlatformInfo.trafficViolationRateNonFloatCauseCode =
            trafficViolation.nonFloatReasonCode;
          businessPlatformInfo.trafficViolationRateNonFloatCauseDesc =
            trafficViolation.nonFloatReasonDesc;
        }
      }
      if (Array.isArray(trafficFloatingItems) && trafficFloatingItems.length) {
        //理赔调整系数
        let claims = trafficFloatingItems.find(
          item => item.discountCode == "30"
        );
        if (claims) {
          trafficPlatformInfo.claimRate = claims.floatCoefficient;
          trafficPlatformInfo.claimRateFloatCode = claims.floatReasonCode;
          trafficPlatformInfo.claimRateFloatDesc = claims.floatReasonDesc;
          trafficPlatformInfo.claimRateNonFloatCode = claims.nonFloatReasonCode;
          trafficPlatformInfo.claimRateNonFloatDesc = claims.nonFloatReasonDesc;
        }
        //违法调整系数
        let trafficViolation = trafficFloatingItems.find(
          item => item.discountCode == "40"
        );
        if (trafficViolation) {
          trafficPlatformInfo.trafficViolationRate =
            trafficViolation.floatCoefficient;
          trafficPlatformInfo.trafficViolationRateFloatCauseCode =
            trafficViolation.floatReasonCode;
          trafficPlatformInfo.trafficViolationRateFloatCauseDesc =
            trafficViolation.floatReasonDesc;
          trafficPlatformInfo.trafficViolationRateNonFloatCauseCode =
            trafficViolation.nonFloatReasonCode;
          trafficPlatformInfo.trafficViolationRateNonFloatCauseDesc =
            trafficViolation.nonFloatReasonDesc;
        }
      }
    }
    /**
     *reInsureItem  商业险重复投保信息
     *lastPolicy    商业险上张保单
     *violation     商业险违法信息
     *peccancy      交强险违法信息
     */
    if (Array.isArray(plan.reInsureItem) && plan.reInsureItem.length) {
      let reInsureItem = plan.reInsureItem[0];
      reInsureItem.reInsureId = reInsureItem.id;
      delete reInsureItem.id;
      businessPlatformInfo = Object.assign(businessPlatformInfo, reInsureItem);
    }
    if (
      plan.lastPolicy &&
      typeof plan.lastPolicy == "object" &&
      Object.keys(plan.lastPolicy).length
    ) {
      let lastPolicy = JSON.parse(JSON.stringify(plan.lastPolicy));
      lastPolicy.lastPolicyId = lastPolicy.id;
      delete lastPolicy.id;
      businessPlatformInfo = Object.assign(businessPlatformInfo, lastPolicy);
    }
    if (Array.isArray(plan.violation) && plan.violation.length) {
      let violation = plan.violation[0];
      violation.violationId = violation.id;
      delete violation.id;
      businessPlatformInfo = Object.assign(businessPlatformInfo, violation);
      businessPlatformInfo.violationList = plan.violation;
    }
    if (Array.isArray(plan.peccancy) && plan.peccancy.length) {
      let peccancy = plan.peccancy[0];
      peccancy.peccancyId = peccancy.id;
      delete peccancy.id;
      trafficPlatformInfo = Object.assign(trafficPlatformInfo, peccancy);
      trafficPlatformInfo.peccancyList = plan.peccancy;
    }
    state.trafficPlatformInfo = trafficPlatformInfo;
    state.businessPlatformInfo = businessPlatformInfo;
    let total = 0;
    if (plan.trafficChecked == "1") {
      if (Number(plan.trafficDuePremium)) {
        total += plan.trafficDuePremium;
      }
      if (Number(plan.tax.sumPayment)) {
        total += plan.tax.sumPayment;
      }
    }
    if (plan.businessChecked == "1") {
      if (Number(plan.businessDuePremium)) {
        total += plan.businessDuePremium;
      }
    }
    if (plan.wealthAccChecked == "1") {
      if (Number(plan.wealthAccPremium)) {
        total += plan.wealthAccPremium;
      }
    }
    let totalPremium = Number(total).toFixed(2);
    plan.totalPremium = totalPremium;
    return (state.insurancePlan = Object.assign(
      {},
      {
        ...plan
      }
    ));
  },
  setUwResult(state, uwResult) {
    return (state.uwResult = {
      uwType: uwResult.uwType
    });
  },
  setAntiInfo(state, antiInfo) {
    if (!antiInfo) return;
    return (state.antiInfo = antiInfo);
  },
  /** 新增加设备 */
  setNewDevices(state, newDevices) {
    if (!newDevices) return;
    let { plans = [] } = state.quoteInfo;
    let planIndex = plans.findIndex(
      i => i.planCode === state.quoteInfo.planCode
    );
    if (planIndex === -1) return;

    let plan = plans[planIndex];
    plan.newDevices = newDevices;
    plans[planIndex] = plan;
    return (state.quoteInfo.plans = plans);
  },
  /** 新增加设备损失险 */
  addBusinessRisk(state, item) {
    let { plans = [] } = state.quoteInfo;
    let planIndex = plans.findIndex(
      i => i.planCode === state.quoteInfo.planCode
    );
    if (planIndex === -1) return;
    let plan = plans[planIndex];
    let { businessRisks = [] } = plan;
    let index = businessRisks.findIndex(i => i.kindCode === item.kindCode);
    if (index === -1) {
      businessRisks.push(item);
    } else {
      businessRisks[index] = item;
    }
    businessRisks.sort((a, b) => {
      let aIndex = BusinessKinds.findIndex(item => item.kindCode == a.kindCode);
      let bIndex = BusinessKinds.findIndex(item => item.kindCode == b.kindCode);
      return aIndex - bIndex;
    });
    plan.businessRisks = businessRisks;
    plans[planIndex] = plan;
    return (state.quoteInfo.plans = plans);
  },
  deleteBusinessRisk(state, item) {
    let { plans = [] } = state.quoteInfo;
    let planIndex = plans.findIndex(
      i => i.planCode === state.quoteInfo.planCode
    );
    if (planIndex === -1) return;
    let plan = plans[planIndex];
    let { businessRisks = [] } = plan;
    let index = businessRisks.findIndex(i => i.kindCode === item.kindCode);
    if (index != -1) {
      businessRisks.splice(index, 1);
    }
    businessRisks.sort((a, b) => {
      let aIndex = BusinessKinds.findIndex(item => item.kindCode == a.kindCode);
      let bIndex = BusinessKinds.findIndex(item => item.kindCode == b.kindCode);
      return aIndex - bIndex;
    });
    plan.businessRisks = businessRisks;
    plans[planIndex] = plan;
    return (state.quoteInfo.plans = plans);
  },
  /** 所有商业险种 */
  setBusinessRisks(state, items) {
    let { plans = [] } = state.quoteInfo;
    let planIndex = plans.findIndex(
      i => i.planCode === state.quoteInfo.planCode
    );
    if (planIndex === -1) return;

    let plan = plans[planIndex];
    items.sort((a, b) => {
      let aIndex = BusinessKinds.findIndex(item => item.kindCode == a.kindCode);
      let bIndex = BusinessKinds.findIndex(item => item.kindCode == b.kindCode);
      return aIndex - bIndex;
    });
    plan.businessRisks = items;
    plans[planIndex] = plan;
    return (state.quoteInfo.plans = plans);
  },
  initWealthAccInfo(state) {
    let { plans = [] } = state.quoteInfo;
    let planIndex = plans.findIndex(
      i => i.planCode === state.quoteInfo.planCode
    );
    if (planIndex === -1) return;
    let plan = plans[planIndex];
    //联合销售 被保人信息默认同车主
    if (state.car.custType == "01") {
      let wealthAccInsurant = {
        identityNo: state.carOwner.identityNo,
        identityType: state.carOwner.identityType,
        insurantName: state.carOwner.name,
        phone: state.carOwner.phoneNo
      };
      plan.wealthAccInsurant = Object.assign({}, Plan.wealthAccInsurant, plan.wealthAccInsurant, wealthAccInsurant);
    }
    //联合销售
    //联合销售 是否代理，默认同车险代理
    plan.wealthAccChannel = Object.assign({}, state.projectChannel);
    //联合销售 默认份数为1
    if (plan.wealthAccRiskCode && !plan.wealthAccBuyNum) {
      plan.wealthAccBuyNum = 1;
    }
    //联合销售 合并打印默认 是
    plan.wealthAccMergePrint = plan.wealthAccMergePrint || "1";
  },
  setWealthAccRisk(state, risk) {
    let { plans = [] } = state.quoteInfo;
    let planIndex = plans.findIndex(
      i => i.planCode === state.quoteInfo.planCode
    );
    if (planIndex === -1) return;

    let plan = plans[planIndex];
    plan = Object.assign(plan, risk);
    plans[planIndex] = plan;
    return (state.quoteInfo.plans = plans);
  },
  setInsureAwards(state, insureAwards) {
    return (state.insureAwards = insureAwards || []);
  },
  initPolicyHolder(state, policyHolder) {
    return (state.policyHolder = Object.assign(
      {},
      RelationPeople,
      policyHolder,
      {
        custType: "01",
        relationFlag: "0",
        identityType: "111",
        vehicleRelation: "2",
        relationPeopleType: "1"
      }
    ));
  },
  setPolicyHolder(state, policyHolder) {
    return (state.policyHolder = Object.assign(
      {},
      state.policyHolder,
      policyHolder
    ));
  },
  initRecognizee(state, recognizee) {
    return (state.recognizee = Object.assign({}, RelationPeople, {
      custType: "01",
      relationFlag: "0",
      identityType: "111",
      vehicleRelation: "2",
      relationPeopleType: "2"
    }));
  },
  setRecognizee(state, recognizee) {
    return (state.recognizee = Object.assign({}, state.recognizee, recognizee));
  },
  setRestOptions(state, restOptions) {
    return (state.restOptions = Object.assign(
      {},
      state.restOptions,
      utils.removeEmpty(restOptions)
    ));
  },
  initInvoice(state) {
    let invoice = {
      clientType: "", //客户类型
      taxpayerType: "", //纳税人类型
      invoiceType: "" //发票类型
    };
    if (state.invoice.invoiceRelation == "1") {
      //同投保人
      invoice.invoiceTitle = state.recognizee.name;
      if (state.policyHolder.custType == "01") {
        //个人
      } else if (state.policyHolder.custType == "02") {
        //单位
        invoice.clientType = invoice.clientType || "2";
        invoice.taxPayerType = invoice.taxPayerType || "1";
        invoice.invoiceType = invoice.invoiceType || "1";
      }
    } else if (state.invoice.invoiceRelation == "2") {
      //同被保人
      invoice.invoiceTitle = state.recognizee.name;
      if (state.recognizee.custType == "01") {
        //个人
      } else if (state.recognizee.custType == "02") {
        //单位
        invoice.clientType = invoice.clientType || "2";
        invoice.taxPayerType = invoice.taxPayerType || "1";
        invoice.invoiceType = invoice.invoiceType || "1";
      }
    }
    return (state.invoice = Object.assign(state.invoice, invoice));
  },
  setInvoice(state, invoice) {
    return (state.invoice = Object.assign(
      {},
      state.invoice,
      utils.removeEmpty(invoice)
    ));
  },
  setEngageList(state, engageList) {
    return (state.engageList = engageList);
  },
  setRightsInterests(state, rightsInterests) {
    return (state.rightsInterests = rightsInterests);
  },
  setBusinessPlatformInfo(state, info) {
    return (state.businessPlatformInfo = info);
  },
  setTrafficPlatformInfo(state, info) {
    return (state.trafficPlatformInfo = info);
  },
  setPaymentInfo(state, paymentInfo) {
    return (state.paymentInfo = paymentInfo);
  }
};

const actions = {
  calculateActualValue({ state, commit }, { effectiveDate, callback }) {
    let orgCode = "";
    const defaultEffectiveDate = new Date().format("yyyy-MM-dd");
    try {
      const userInfo = JSON.parse(localStorage.getItem("userInfo"));
      orgCode = userInfo.orgCode;
      if (!effectiveDate && state.historyPolicy) {
        if (state.historyPolicy.businessChecked == "1") {
          effectiveDate = state.historyPolicy.businessEffectiveStartDate.substr(
            0,
            10
          );
        } else if (state.historyPolicy.trafficChecked == "1") {
          effectiveDate = state.historyPolicy.trafficEffectiveStartDate.substr(
            0,
            10
          );
        }
      }
    } catch (error) {
      console.error("calculateActualValue\n", error);
    }
    effectiveDate =
      effectiveDate > defaultEffectiveDate
        ? effectiveDate
        : defaultEffectiveDate;
    let { plans = [] } = state.quoteInfo;
    let planIndex = plans.findIndex(
      i => i.planCode === state.quoteInfo.planCode
    );
    let plan = {};
    let vehicleAccessorys = [];
    if (planIndex != -1) {
      plan = plans[planIndex];
      if (plan.newDevices.length > 0) {
        vehicleAccessorys = plan.newDevices;
      }
    }
    let params = {
      effectiveDate: effectiveDate || new Date().format("yyyy-MM-dd"), //string起保日期
      enrollDate: state.car.enrollDate || new Date().format(), //string初登日期
      firstyearDate: state.car.firstyearDate, //string首年投保日期（上海使用）
      orgCode, //string机构代码
      purchasePrice: state.car.purchasePrice, //string新车购置价
      regionCode: state.car.regionCode, //string地区代码
      seatCount: state.car.seatCount, //string核定载客数
      tonCount: state.car.tonCount, //string核定载质量
      useNatureCode: state.car.useNatureCode, //string车辆使用性质
      userCode: state.car.userCode, //string业务员编号
      carKindCode: state.car.carKindCode, //string车辆类型
      vehicleAccessorys: vehicleAccessorys
    };
    calculateActualValue(params)
      .then(
        res => {
          //console.log(res);
          if (res.code === "1") {
            let originActualValue = state.car.actualValue;
            commit("setCarInfo", utils.removeEmpty(res.data));
            let updateActualValue = res.data.actualValue;
            commit("setQuoteInfo", {
              ...state.quoteInfo,
              reset: originActualValue != updateActualValue
            });
            if (
              res.data.vehicleAccessorys &&
              res.data.vehicleAccessorys.length > 0
            ) {
              state.devicesAmount = 0;
              for (let i = 0; i < res.data.vehicleAccessorys.length; i++) {
                state.devicesAmount +=
                  res.data.vehicleAccessorys[i].calculateActualValue;
              }
            }
            commit("calcUseYears");
            callback && callback();
          } else {
            Dialog({ message: res.msg });
          }
        },
        error => {
          //console.log(error);
        }
      )
      .finally(() => {});
  },
  initWithDetail({ state, commit }, detail) {
    let {
      id,
      applicationNo,
      vehicleOrder,
      vehicleModelVO,
      vehicleOwnerVO,
      vehicleChannelVO,
      vehicleTaxVo,
      vehicleInvoice,
      vehicleAntiAgent,
      relationPeopleList,
      vehicleKindVoList,
      wealchAccApplicationVO,
      nonCarChannelVo,
      wealthAccInsurant,
      insuranceApplicationList,
      mergePrint,
      custOrderIntegralRes,
      businessSpecialAgreement,
      trafficSpecialAgreement,
      trafficInfoPlatform,
      businessInfoPlatform
    } = detail;
    //订单号
    if (id) {
      commit("setOrderId", id);
    }
    //投保单号
    if (applicationNo) {
      commit("setProposalNo", applicationNo);
    }
    //车辆信息
    if (vehicleModelVO && typeof vehicleModelVO == "object") {
      commit("setCarInfo", utils.removeEmpty(vehicleModelVO));
    }
    //车主信息
    if (vehicleOwnerVO && typeof vehicleOwnerVO == "object") {
      commit("setCarOwner", utils.removeEmpty(vehicleOwnerVO));
    }
    //渠道信息
    if (vehicleChannelVO && typeof vehicleChannelVO == "object") {
      let businessNatures = Object.keys(Config.businessNature).map(key => ({
        text: Config.businessNature[key],
        value: key
      }));
      businessNatures.push({
        text: "业务员销售（直销）",
        value: "0101"
      });
      businessNatures.push({
        text: "业务员销售（直团）",
        value: "7101"
      });
      businessNatures.push({
        text: "门店直销",
        value: "0201"
      });
      if (!vehicleChannelVO.businessNatureName) {
        vehicleChannelVO.businessNatureName = utils.findText(
          businessNatures,
          vehicleChannelVO.businessNatureCode
        );
      }
      commit("setProjectChannel", {
        ...vehicleChannelVO
      });
    }
    if (!state.control.quoted) {
      //投保方案
      if (
        Array.isArray(insuranceApplicationList) &&
        insuranceApplicationList.length
      ) {
        let trafficInfo = insuranceApplicationList.find(
          i => i.riskName == "交强险"
        );
        let trafficChecked = trafficInfo ? "1" : "0";
        let businessInfo = insuranceApplicationList.find(
          i => i.riskName == "商业险"
        );
        let businessChecked = businessInfo ? "1" : "0";
        let insurancePlan = Object.assign({}, Plan);
        let total = 0;
        if (businessChecked == "1") {
          const businessProposalNo = businessInfo.applicationNo;
          commit("setBusinessProposalNo", businessProposalNo);
          if (/Y/i.test(businessInfo.immediatelyEffective)) {
            businessInfo.immediatelyEffective = "1";
          } else if (/N/i.test(businessInfo.immediatelyEffective)) {
            businessInfo.immediatelyEffective = "0";
          }
          businessInfo.immediatelyEffective =
            businessInfo.immediatelyEffective || "0";
          let businessRisks = [...vehicleKindVoList];
          businessRisks.sort((a, b) => {
            let aIndex = BusinessKinds.findIndex(
              item => item.kindCode == a.kindCode
            );
            let bIndex = BusinessKinds.findIndex(
              item => item.kindCode == b.kindCode
            );
            return aIndex - bIndex;
          });
          insurancePlan = Object.assign(insurancePlan, {
            businessChecked: businessChecked, //是否勾选商业险
            businessBeforeVatPremium: businessInfo.premiumExcludingTax, //商业险税前真实保费,不含税保费
            businessDuePremium: businessInfo.premium, //商业险应收保费
            businessPremium: businessInfo.premium, //商业险保费
            businessStdPremium: businessInfo.stdPremium, //商业险标准保费
            businessSumInsured: businessInfo.sumInsured, //商业险保额
            businessEffectiveEndDate: businessInfo.expireDate, //商业险终保日期
            businessEffectiveStartDate: businessInfo.effictiveDate, //商业险起保日期
            businessIsEffective: businessInfo.immediatelyEffective, //商业险是否即时生效(1:是 0:否)
            businessExpectDiscount: businessInfo.expectDiscount, //商业险期望优惠折扣
            businessRisks, //商业险险别明细列表[...]
            newDevices: [] //新增设备信息[{...}]
          });
          if (Number(insurancePlan.businessDuePremium)) {
            total += insurancePlan.businessDuePremium;
          }
        }
        if (trafficChecked == "1") {
          const trafficProposalNo = trafficInfo.applicationNo;
          commit("setTrafficProposalNo", trafficProposalNo);
          if (/Y/i.test(trafficInfo.immediatelyEffective)) {
            trafficInfo.immediatelyEffective = "1";
          } else if (/N/i.test(trafficInfo.immediatelyEffective)) {
            trafficInfo.immediatelyEffective = "0";
          }
          trafficInfo.immediatelyEffective =
            trafficInfo.immediatelyEffective || "0";
          insurancePlan = Object.assign(insurancePlan, {
            tax: vehicleTaxVo,
            trafficChecked: trafficChecked, //是否勾选交强险
            trafficEffectiveEndDate: trafficInfo.expireDate, //交强险终保日期
            trafficEffectiveStartDate: trafficInfo.effictiveDate, //交强险起保日期
            trafficIsEffective: trafficInfo.immediatelyEffective, //交强险是否即时生效(1:是 0:否)
            trafficBeforeVatPremium: "", //交强险税前真实保费,不含税保费
            trafficDuePremium: trafficInfo.premium, //交强险应收保费
            trafficStdPremium: trafficInfo.stdPremium, //交强险标准保费
            trafficSumInsured: trafficInfo.sumInsured, //交强险保额
            trafficPremium: trafficInfo.premium //交强险保费
          });
          if (Number(insurancePlan.trafficDuePremium)) {
            total += insurancePlan.trafficDuePremium;
          }
          if (Number(insurancePlan.tax.sumPayment)) {
            total += insurancePlan.tax.sumPayment;
          }
        }
        if (wealchAccApplicationVO) {
          let {
            applicationNo: wealchAccProposalNo,
            buyNum,
            premium,
            formulaGroupCode,
            formulaGroupName,
            formulaCode,
            formulaName,
            riskCode,
            riskName
          } = wealchAccApplicationVO;
          commit("setWealchAccProposalNo", wealchAccProposalNo);
          insurancePlan = Object.assign(insurancePlan, {
            wealthAccChecked: "1", //是否勾选联合销售
            wealthAccBuyNum: buyNum, //联合销售购买份数
            wealthAccFormulaGroupCode: formulaGroupCode,
            wealthAccFormulaGroupName: formulaGroupName,
            wealthAccFormulaCode: formulaCode, //联合销售方案代码
            wealthAccFormulaName: formulaName, //联合销售方案名称
            wealthAccPremium: premium, //联合销售保费
            wealthAccRiskCode: riskCode, //联合销售险种代码
            wealthAccRiskName: riskName, //联合销售险种名称
            wealthAccMergePrint: mergePrint, //联合销售与车险保单合并打印
            wealthAccInsurant: wealthAccInsurant || Object.assign({}, Plan.wealthAccInsurant), //联合销售被保人信息
            wealthAccChannel: nonCarChannelVo || {
              //联合销售渠道信息
              agentAgreementCode: "",
              agentAgreementName: "",
              agentCode: "",
              agentName: "",
              businessCode: "",
              businessName: "",
              businessType: "",
              channelName: "",
              isProxy: ""
            }
          });
          if (Number(insurancePlan.wealthAccPremium)) {
            total += insurancePlan.wealthAccPremium;
          }
        }
        insurancePlan.totalPremium = vehicleOrder.payAmount;
        if (!insurancePlan.totalPremium) {
          insurancePlan.totalPremium = total.toFixed(2);
        }
        commit("setInsurancePlan", utils.removeEmpty(insurancePlan));
      }
    }
    let products = [];
    if (state.insurancePlan.trafficChecked == "1") {
      products.push("交强");
    }
    if (state.insurancePlan.businessChecked == "1") {
      products.push("商业");
    }
    if (state.insurancePlan.wealthAccChecked == "1") {
      products.push("财意");
    }
    let productName = "车险(" + products.join("+") + ")";
    let payTypeNo =
      vehicleOrder && typeof vehicleOrder == "object"
        ? vehicleOrder.payTypeNo || ""
        : "";
    let payVerificationCode =
      vehicleOrder && typeof vehicleOrder == "object"
        ? vehicleOrder.payVerificationCode || ""
        : "";
    //支付号
    commit("setPaymentInfo", {
      productName,
      paymentNo: payTypeNo,
      checkNo: payVerificationCode
    });
    //投保人信息
    if (relationPeopleList && Array.isArray(relationPeopleList)) {
      let policyHolder = relationPeopleList.find(
        item => item.relationPeopleType === "1"
      );
      if (policyHolder) {
        commit("initPolicyHolder");
        policyHolder.custType = policyHolder.insuredNature;
        commit("setPolicyHolder", {
          ...policyHolder
        });
      }
    }
    //被保人信息
    if (relationPeopleList && Array.isArray(relationPeopleList)) {
      let recognizee = relationPeopleList.find(
        item => item.relationPeopleType === "2"
      );
      if (recognizee) {
        commit("initRecognizee");
        recognizee.custType = recognizee.insuredNature;
        commit("setRecognizee", {
          ...recognizee
        });
      }
    }
    //特别约定
    let specialAgreement = {};
    if (
      trafficSpecialAgreement &&
      trafficSpecialAgreement.hasOwnProperty("specialAgreementList") &&
      Array.isArray(trafficSpecialAgreement.specialAgreementList)
    ) {
      specialAgreement.engages = trafficSpecialAgreement.specialAgreementList;
    }
    if (
      businessSpecialAgreement &&
      businessSpecialAgreement.hasOwnProperty("specialAgreementList") &&
      Array.isArray(businessSpecialAgreement.specialAgreementList)
    ) {
      specialAgreement.bizEngages =
        businessSpecialAgreement.specialAgreementList;
    }
    commit("setEngageList", specialAgreement);
    //星级权益
    // rightsInterestsList;
    if (
      custOrderIntegralRes &&
      typeof custOrderIntegralRes == "object" &&
      Object.keys(custOrderIntegralRes).length
    ) {
      commit("setRightsInterests", custOrderIntegralRes);
    }
    //平台信息
    if (trafficInfoPlatform && typeof trafficInfoPlatform == "object") {
      commit("setTrafficPlatformInfo", trafficInfoPlatform);
    }
    if (businessInfoPlatform && typeof businessInfoPlatform == "object") {
      commit("setBusinessPlatformInfo", businessInfoPlatform);
    }
    Vue.prototype.$nextTick(() => {
      Vue.prototype.$forceUpdate();
    });
  },
  getTaxConfig({ state, commit }) {
    const userInfo = JSON.parse(localStorage.getItem("userInfo"));
    const comCode = userInfo.comCode;
    getTaxConfig({
      orderId: state.orderId,
      comCode
    }).then(
      res => {
        if (res.code == "1") {
          commit("setTaxConfig", res.data);
        } else {
        }
      },
      error => {}
    );
  }
};

export default {
  namespaced: true,
  state: JSON.parse(JSON.stringify(defaultState)),
  getters,
  mutations,
  actions
};

export const RelationPeople = {
  address: "", //地址
  age: "", //年龄
  indiIdGatherCaptcha: "", //身份证采集器编码
  relationFlag: "", //"关系类型,1-同车主,2-同投保人"
  agentIdentifyEndDate: "", //代办人证件有效期结束日期
  agentIdentifyLongTerm: "", //代办人证件是否长期有效 1:是,0:否
  agentIdentityCheck: "", //证件审核情况
  agentIdentityType: "", //代办人证件类型
  agentIdentityNo: "", //代办人证件号
  agentPhone: "", //代办人联系方式
  agentName: "", //代办人姓名
  agentType: "", //代办人身份
  agentProcessCheck: "", //代理业务手续情况
  applicantInsuredRelation: "", //投被保人关系（被保人使用）
  birthDate: "", //出生日期
  countyCode: "", //县级代码
  customerAgentCertificatenum: "", //客户经办人证件号(投保人特有)
  customerAgentCertificatetype: "", //客户经办人证件类型(投保人特有)
  customerAgentName: "", //客户经办人姓名(投保人特有)
  email: "", //电子邮件地址
  identifyCom: "", //签发机构
  identifyEndDate: "", //证件有效期结束日期
  identifyLongTerm: "0", //证件是否长期有效 1:是,0:否
  identifyStartDate: "", //证件有效期开始日期
  identityNo: "", //证件号码
  identityType: "", //证件类型
  insuredNature: "", //使用性质
  isPhoneHolder: "", //手机持有人是否本人 1:是 0:否
  name: "", //姓名
  nation: "", //民族
  nationality: "CHN", //国籍
  occupation: "900", //职业
  profession: "900", //职业
  phone: "", //联系人电话
  phoneHolderIdno: "", //手机持有人身份证号
  phoneHolderName: "", //手机持有人姓名
  postCode: "", //邮编
  prefectureCode: "", //辖区代码
  provinceCode: "", //省份代码
  relationPeopleCode: "", //关系人号，ecif客户号
  relationPeopleType: "", //客户类型 1:投保人,2:被保人
  sex: "1", //性别 1:男,2:女,0:未知
  unitOccupationName: "", //单位性质/职业名称
  unitType: "", //单位性质
  unitNature: "", //单位性质
  vehicleRelation: "", //关系人与车辆关系，1-所有，2-使用，3-管理
  taxRegistryNo: "", //税务登记证号码
  businessRange: "", //经营范围
  shareholderType: "", //股东类型
  legalReprese: "", //法人代表/负责人姓名
  legalRepreseType: "111", //法人代表/负责人证件类型
  legalRepreseNo: "", //法人代表/负责人证件号码
  legalRepreseED: "", //法人代表/负责人证件有效止期
  legalIdentifyLongTerm: "0", //法人代表/负责人证件是否长期有效 1:是,0:否
  controlHolder: "", //控股股东/实际控制人姓名
  controlHolderType: "111", //控股股东/实际控制人 证件类型
  controlHolderNo: "", //控股股东/实际控制人证件号码
  controlHolderED: "", //控股股东/实际控制人 证件有效止期
  controlIdentifyLongTerm: "0", //控股股东/实际控制人证件是否长期有效 1:是,0:否
  authorization: "", //授权经办人姓名
  authorizationType: "111", //授权经办人 证件类型
  authorizationNo: "", //授权经办人证件号码
  authorizationED: "", //授权经办人 证件有效止期
  authorIdentifyLongTerm: "0" //授权经办人证件是否长期有效 1:是,0:否
};

/** 上年理赔信息 */
export const Claim = {
  claimAmount: "", //理赔金额
  claimType: "", //理赔种类
  closeDate: "", //结案日期
  riskDate: "" //出险日期
};

/** 新设备信息 */
export const NewDevice = {
  deviceAmount: "", //设备金额
  deviceBuyDate: "", //设备购置日期
  deviceName: "", //设备名称
  deviceNum: "", //设备数量
  calculateActualValue: "" //实际价值
};

/** 方案信息 */
export const Plan = {
  businessChecked: "", //是否勾选商业险
  businessBeforeVatPremium: "", //商业险税前真实保费,不含税保费
  businessDuePremium: "", //商业险应收保费
  businessPremium: "", //商业险保费
  businessStdPremium: "", //商业险标准保费
  businessSumInsured: "", //商业险保额
  businessEffectiveEndDate: "", //商业险终保日期
  businessEffectiveStartDate: "", //商业险起保日期
  businessIsEffective: "", //商业险是否即时生效(1:是 0:否)
  businessExpectDiscount: "", //商业险期望优惠折扣
  businessRisks: [], //商业险险别明细列表[...]
  newDevices: [], //新增设备信息[{...}]

  trafficChecked: "", //是否勾选交强险
  trafficEffectiveEndDate: "", //交强险终保日期
  trafficEffectiveStartDate: "", //交强险起保日期
  trafficIsEffective: "", //交强险是否即时生效(1:是 0:否)
  trafficBeforeVatPremium: "", //交强险税前真实保费,不含税保费
  trafficDuePremium: "", //交强险应收保费
  trafficStdPremium: "", //交强险标准保费
  trafficSumInsured: "", //交强险保额
  trafficPremium: "", //交强险保费

  tax: {
    //车船税表
    adjustRate: "", //减免税比例
    adjustTaxValue: "", //减免税比例/减免税金额
    deductiondueCode: "", //减免税原因
    deductiondueType: "", //减免税方案
    ext: {}, //车船税扩展信息表{...}
    sumPayment: "", //合计税款[保费]
    taxCertificateNo: "", //完(减免)税凭证号
    taxDepartment: "", //税务(凭证)机关名称
    taxDocumentDate: "", //完税凭证填发日期
    taxFlag: "", //缴税标志(1:正常缴税 2:减税 3:已完税 4:免税)
    taxPaidareaCode: "", //开具完税凭证地区代码
    taxpayerName: "", //纳税人名称
    taxpayerNo: "" //纳税人识别号
  },

  planCode: "", //方案code(s:简约版 e:经济版 l:豪华版)
  planName: "",

  wealthAccChecked: "", //是否勾选联合销售
  wealthAccSeatCount: "",
  wealthAccBuyNum: "", //联合销售购买份数
  wealthAccFormulaCode: "", //联合销售方案代码
  wealthAccFormulaName: "", //联合销售方案名称
  wealthAccPremium: "", //联合销售保费
  wealthAccRiskCode: "", //联合销售险种代码
  wealthAccRiskName: "", //联合销售险种名称
  wealthAccMergePrint: "", //联合销售与车险保单合并打印
  wealthAccInsurant: {
    //联合销售被保人信息
    identityNo: "",
    identityType: "",
    insurantName: "",
    phone: "",
    dateOfBirth: "",
    insuredSex: "",
    healthStatus: "",
    age: ""
  },
  wealthAccChannel: {
    //联合销售渠道信息
    agentAgreementCode: "",
    agentAgreementName: "",
    agentCode: "",
    agentName: "",
    businessCode: "",
    businessName: "",
    businessType: "",
    channelName: "",
    isProxy: ""
  },
  totalPremium: "" //总保费
};

/** 车船税信息 */
export const Tax = {
  adjustRate: "", //减免税比例
  adjustTaxValue: "", //减免税比例/减免税金额
  deductiondueCode: "", //减免税原因
  deductiondueType: "", //减免税方案
  ext: {}, //车船税扩展信息表{...}
  sumPayment: "", //合计税款[保费]
  taxCertificateNo: "", //完(减免)税凭证号
  taxDepartment: "", //税务(凭证)机关名称
  taxDocumentDate: "", //完税凭证填发日期
  taxFlag: "", //缴税标志(1:正常缴税 2:减税 3:已完税 4:免税)
  taxPaidareaCode: "", //开具完税凭证地区代码
  taxpayerName: "", //纳税人名称
  taxpayerNo: "" //纳税人识别号
};

/** 商业险险别信息 */
export const Risk = {
  amount: "", //保额
  brokenCode: "", //玻璃破碎险：玻璃类型
  deductibleRate: "", //浮动比例/绝对免赔率
  deductibleValue: "", //自负额
  premium: "", //保费
  quantity: "", //投保座位数/投保天数
  kindCode: "", //险种代码
  kindFlag: "", //不计免赔标志(1:不计免赔 0:计免赔)
  riskIndex: "", //序号
  kindName: "", //险种名称
  unitAmount: "", //每座保额/每天保额
  rate: "", //费率
  nonDeductiblePremium: "", //不计免赔险实收保费
  nonDeductiblePremiumRate: "", //不计免赔险费率
  nonDeductibleStandardPremium: "", //不计免赔险标准保费
  standardPremium: "", //标准保费
  selected: false
};

/** 商业浮动信息 */
export const FloatingItem = {
  discountCode: "", //优惠代码 SelfUnderwrite-自主核保系数 SelfChannel-自主渠道系数 NCD-无赔款优待系数 SelfViolation-交通违法系数
  floatReasonCode: "", //浮动原因代码
  floatReasonDesc: "", //浮动原因描述
  nonFloatReasonCode: "", //未浮动原因代码
  nonFloatReasonDesc: "" //未浮动原因描述
};

/** 联合销售 */
export const WealthAccRisk = {
  areaCode: "",
  areaLevel: "",
  areaName: "",
  businessFlag: "",
  carNature: "",
  formulaCode: "",
  formulaName: "",
  id: 0,
  insuredAgeLower: 0,
  insuredAgeUpper: 0,
  invalidDate: "",
  jointSaleFlag: "",
  maxCount: 0,
  minCount: 0,
  productCode: "",
  riskCode: "",
  riskName: "",
  validDate: "",
  validFlag: ""
};
