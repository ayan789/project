import {
  storeQueryFormula,
  placeOrder
} from "../../../http/module/nonCar.js";
import {
  Toast
} from 'vant';

const state = {
  newData: {
    storeCode: "",
    productCode: "",
    riskCode: "WTI",
    formulaCode: "",
    formulaName: "",
    formulaSize: "1",
    sumAmount: "",
    premium: "",
    insureNo: "",
    sumPremium: "",
    addressInfo: {
      provinceCode: "",
      prefectureCode: "",
      countyCode: "",
      comcode: "",
      addressName: ""
    },
    ages: "",
    isAgent: "",
    agentInfo: {},
    appliName: "",
    appliIdentifyType: "01",
    appliIdentifyNumber: "",
    appliMobile: "",
    appliBirthDate: "",
    appliEMail: "",
    appliIdentity: "",
    placeInfo: "", //地址
    yjAccount: "",
    isSheBao: "Y",
    insuredName: "",
    insuredIdentifyType: "01",
    insuredIdentifyNumber: "",
    insuredMobile: "",
    startDate: "",
    endDate: "",
    insuredBirthDate: "",
    limQuantity: "1",
    isRunApp: "",
    businessNatureCode: "",
    businessNatureName: ""
  },
  newPremiumsJson: "", //年龄保费
  resultPrice: 0, //附加保费
  sumPremium1: 0, // 计算保费
  sumAmount1: 0, // 更新保额
  formula: "", //方案
  result: [], //附加
  tenMillionData: {
    storeCode: "", //方案代码
    productCode: "", //产品代码
    riskCode: "WTN",
    formulaCode: "",
    formulaName: "",
    formulaSize: "1",
    sumAmount: "",
    sumPremium: "",
    ages: "",
    isAgent: "",
    agentInfo: "",
    invoiceInfo: {
      taxPayerType: "", //纳税人类型 4 个人
      companyName: "", //发票抬头
      customerType: "", //客户类型1 境内个人、政府机关及事业单位
      invoiceType: "", // 发票类型
      mobile: "",
      email: "",
      taxPayerNo: ""
    },
    appliName: "", //投保人信息
    appliIdentifyType: "01",
    appliIdentifyNumber: "",
    appliMobile: "",
    appliNationality: "",
    appliBirthDate: "",
    appliEMail: "",
    appliIdentity: "",
    aKZEXClause: false,
    aEXTZClause: false,
    aEXYLClause: false,
    aEXZZClause: false,
    isSheBao: "Y",
    insuredSheBaoType: "",
    insuredName: "", //被保人信息
    insuredIdentifyType: "01",
    insuredIdentifyNumber: "",
    insuredMobile: "",
    insuredNationality: "",
    startDate: "",
    endDate: "",
    insuredBirthDate: "",
    isRunApp: "",
    businessNatureCode: "",
    businessNatureName: ""
  },
  millionQuery: {}, //查询方案请求数据(共用)
  millionData: {}, //params 表单提交数据
  healthTell: {}, //健康告知数据
  personList: {}, //客户列表数据
  tenMillionDataFamily: {

  }
};

const mutations = {

  // 老年防癌更新保费
  ["UPDTNEWSUMPREMIUM"](state) {
    state.newData.sumPremium = JSON.parse(state.newPremiumsJson)[state.newData.ages].BF
  },

  // 防癌险年龄json
  // newPremiumsJson
  ['SETNEWAGESJSON'](state, data) {
    state.newPremiumsJson = data.premiumsJson
  },
  // 更新保费
  ["UPDTPREMINUMSNUM"](state) {
    if (state.result.length) {
      state.tenMillionData.sumPremium = parseInt(JSON.parse(state.formula.premiumsJson)[state.tenMillionData.ages].BF)
      this.commit('million/TOGGLERESULT', state.tenMillionData.ages)
      state.tenMillionData.sumPremium += parseInt(state.resultPrice)
    } else {
      state.tenMillionData.sumPremium = JSON.parse(state.formula.premiumsJson)[state.tenMillionData.ages].BF
    }
  },
  // 被保人年龄更新保费
  ['TOGGLERESULT'](state, arg) {
    if (state.result.length) {
      state.resultPrice = 0;
      state.result.forEach((item, i) => {
        if (
          item == "扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)"
        ) {
          state.resultPrice += parseInt(
            JSON.parse(state.formula.extensionJson)[arg].ZLTY
          );
        }
        if (item == "恶性肿瘤院外特种药品费用医疗责任") {
          state.resultPrice += parseInt(
            JSON.parse(state.formula.extensionJson)[arg].TY
          );
        }
        if (item == "恶性肿瘤赴日医疗责任") {
          state.resultPrice += parseInt(
            JSON.parse(state.formula.extensionJson)[arg].FRYL
          );
        }
        if (item == "恶性肿瘤扩展质子重离子医疗责任") {
          state.resultPrice += parseInt(
            JSON.parse(state.formula.extensionJson)[arg].ZY
          );
        }
      });
      return state.resultPrice;
    } else {
      return 0;
    }
  },

  // 方案信息
  ['SETFORMULA'](state, data) {
    if (!(data.formula == "" || data.formula == undefined)) {
      state.formula = data.formula;
    }
    if (!(data.result == "" || data.result == undefined)) {
      state.result = data.result
    }
  },
  // 清除数据
  ['CLETENMILLIONDATA'](state) {
    state.sumPremium1 = 0
    state.result = []
    // state.formula = ""
    state.resultPrice = 0
    state.tenMillionData = {
      storeCode: "",
      productCode: "", //产品代码
      riskCode: "WTN",
      formulaCode: "", //方案代码
      formulaName: "", //方案名称
      formulaSize: "1",
      sumAmount: "",
      sumPremium: "",
      ages: "",
      isAgent: "",
      agentInfo: "",
      invoiceInfo: {
        companyName: "",
        customerType: "1",
        invoiceType: "",
        mobile: "",
        email: "",
        taxPayerNo: ""
      },
      appliName: "",
      appliIdentifyType: "01",
      appliIdentifyNumber: "",
      appliMobile: "",
      appliNationality: "",
      appliBirthDate: "",
      appliEMail: "",
      appliIdentity: "",
      aKZEXClause: false,
      aEXTZClause: false,
      aEXYLClause: false,
      aEXZZClause: false,
      isSheBao: "Y",
      insuredSheBaoType: "",
      insuredName: "",
      insuredIdentifyType: "01",
      insuredIdentifyNumber: "",
      insuredMobile: "",
      insuredNationality: "",
      startDate: "", //开始时间
      endDate: "", //结束时间
      insuredBirthDate: "", //被保人生日
      isRunApp: "",
      businessNatureCode: "",
      businessNatureName: ""
    }
  },

  ['CLENEWDATA'](state) {
    state.newPremiumsJson = "";
    state.newData = {
      storeCode: "",
      productCode: "",
      riskCode: "WTI",
      formulaCode: "",
      formulaName: "",
      formulaSize: "1",
      sumAmount: "",
      premium: "",
      insureNo: "",
      sumPremium: "",
      addressInfo: {
        provinceCode: "",
        prefectureCode: "",
        countyCode: "",
        comcode: "",
        addressName: ""
      },
      ages: "",
      isAgent: "",
      agentInfo: {},
      appliName: "",
      appliIdentifyType: "01",
      appliIdentifyNumber: "",
      appliMobile: "",
      appliBirthDate: "",
      appliEMail: "",
      appliIdentity: "",
      placeInfo: "", //地址
      yjAccount: "",
      isSheBao: "Y",
      insuredName: "",
      insuredIdentifyType: "01",
      insuredIdentifyNumber: "",
      insuredMobile: "",
      startDate: "",
      endDate: "",
      insuredBirthDate: "",
      limQuantity: "1",
      isRunApp: "",
      businessNatureCode: "",
      businessNatureName: ""
    }
  },
  // totalData
  ['SETNEWDATA'](state, data) {
    for (let key in data) {
      state.newData[key] = data[key];
    }
  },

  // 请求数据
  ['SETTENMILLIONDATA'](state, data) {
    for (let key in data) {
      state.tenMillionData[key] = data[key];
    }
  },
  // 查询数据
  ['SETMILLIONQUERY'](state, queryData) {
    state.millionQuery = queryData
  },

  ['SETMILLIONDATA'](state, params) {
    state.millionData = params
  },
  // 健康告知数据
  ['SETHEALTHTELL'](state, params) {
    state.healthTell = params
  },

  ['SETPERSONLIST'](state, params) {
    if (params.isInsured == false) {
      state.personList.tinfo = params.data;

    } else if (params.isInsured) {
      state.personList.beinfo = params.data

    }
  },
  ['SETMILLIONDATAFAMILY'](state, params) {
    state.tenMillionDataFamily = params
  },
};

const actions = { //请求数据
  getQueryFormula({
    commit
  }, params) {
    return new Promise(async (resolve, reject) => {
      let res = await storeQueryFormula(params)
      if (res.code == '1') {
        commit('SETMILLIONQUERY', res)
        resolve()
      } else {
        reject()
      }
    })
  }
};

const getters = {
  newData(state) {
    return state.newData
  },
  tenMillionData(state) {
    return state.tenMillionData
  },
  millionQuery(state) {
    return state.millionQuery
  },
  gResult(state) {
    return state.result
  },
  millionData(state) {
    return state.millionData
  },
  healthTell(state) {
    return state.healthTell
  },
  personList(state) {
    return state.personList
  },
  tenMillionDataFamily(state) {
    return state.tenMillionDataFamily
  },
}
export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
};