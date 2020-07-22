/*
 * @Description:非车配置共享数据
 * @Date: 2020-06-03 11:36:04
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 16:12:10
 * @FilePath: \nonCarCongfig\src\store\modules\nonCar\config.js
 */

const state = {
  detailData: {
    author:'yan',
    //方案详情页面的共享数据
    isIssueAfterPay: 0, //是否见费 1见费
    sumPremium: 0, //总保费
    basePremium: 0, //基础保费
    sumAmount: 0, //总保额
    baseAmount: 0, //基础保额
    commNumOfCopies: 1, //份数
    insuredObjArr: [], //被保人信息---与保障人数相关联的
    securityNum: "", //当前选中的保障人数 securityNum
    commSecurityPeriod: "", //保障期间
    selectedSubIn: {}, //当前勾选的附险
    selectedMainIn: {}, //当前勾选的主险
    mainPremium: 0, //主险保费
    trialArr: [], //试算要素，和保费相关联
    strategyId: 0, //批次代码
    planId: 1, //当前方案
    formulaCode: "", //当前选中的方案
    formData: [], //动态form表单的数据
    riskCode: "", //产品种类代码
    title: "", //产品名称
    giCoveragePeriod: 1, //保障年限
    giPeriodType: 1, // 保障年限单位 1 年 2 月 3 天
    termsAndConditions: [], //页面底部条款
    isNeedHealthNotice: false, //是否需要健康须知
    healthNoticeObj: {}, //健康须知对象
  },
  topPos: 0, // 记录滚动位置
  // 下单报文--产品详情页面的信息
  productInfo: {
    metaDataList: [],
    planId: 1, //批次
    storeCode: "", //店铺code
    formulaCode: "", //方案code
    riskCode: "", //产品code
    planCategory: "1", //方案险类
    strategyId: 0, //批次代码

    sumPremium: 0, //总保费
    sumAmount: 0, //总保额
    productList: [
      //勾选的主附险
      // {
      //   clauseCode: "",
      //   kindCode: "",
      // },
    ],
    businessNatureName: "", //直销
    businessNatureCode: "", //转换
    isAgent: "", //1 有代理，0 无代理
    agentInfo: {
      comCode: "",
      isAgentMode: "",
      businessNature: "",
      agentCode: "",
      agentName: "",
      agreementNo: "",
      agreementName: "",
      businessNature2: "",
      handler2Code: "",
      handler2Name: "",
    },
  },
  // 下单报文--信息录入页面的信息
  insuranceInfo: {
    startDate: "", //开始时间
    endDate: "", //结束时间
    timeLimit: "1", //保障年限
    sumPremium: 0, //总保费
    sumAmount: 0, //总保额
    metaDataList: [
      // {
      //   id: 1,
      //   planId: 2,
      //   name: "证件类型",
      //   dataType: "String",
      //   submitName: "/policyCustomerList/[]/idType",
      //   value: "111",
      //   arrayIndex: -1, //不是多个传-1,多个传arrayIndex
      // },
    ],
    invoiceInfo: {
      accountNo: "",
      companyName: "", //发票抬头
      copyDataFromType: "", //同投被保人
      customerType: "", //客户类型
      depositeBankName: "", //开户银行
      email: "", //邮箱
      invoiceType: "", //发票类型
      mobile: "", //移动电话
      taxPayerNo: "", //纳税人识别号
      taxPayerType: "", //纳税人类型
      taxRegisterAddress: "", //注册地址
      taxRegisterTel: "", //注册电话
    },
    invoiceFlag: 0, //("0:无发票信息,1:个人，2：企业")
  },
  detailDataPlan: {
    //计划书页面的共享数据
    isIssueAfterPay: 0, //是否见费 1见费
    sumPremium: 0, //总保费
    sheBaoType: "", //当前选中的社保类型，Y 有社保，N 无社保 sheBaoType
    insuredAge: "", //当前选中的年龄  insuredAge
    securityNum: "", //当前选中的保障人数 securityNum
    selectedSubIn: {}, //当前勾选的附险
    selectedMainIn: {}, //当前勾选的主险
    mainPremium: 0, //主险保费
    trialArr: [], //试算要素，和保费相关联
    plantId: 1, //当前方案
    formulaCode: "", //当前选中的方案
  },
  // 表单填写数据
  formCreateData: {
    formatForm: [],
  },
  initRule: [], // 存储动态表单 投保
  insuredRule: [], // 存储动态表单 被保
  totForm: [], // 存储表单初始值
  timeLimitInfo: {}, // 存储保障期限信息
  transportRule: [], //存储动态表单 运输工具
};
const mutations = {
  //方案详情页面--- 更新滚动位置
  setPos(state, data) {
    state.topPos = data;
  },
  //方案详情页面--- 更新见费，非见费
  update_IssueAfterPay(state, value) {
    state.detailData.isIssueAfterPay = value;
  },
  //方案详情页面--- 更新总保费
  update_SumPremium(state, value) {
    state.detailData.sumPremium = value;
  },
  //方案详情页面--- 更新基础保费
  update_basePremium(state, value) {
    state.detailData.basePremium = value;
  },
  // 方案详情页面---更新选中的保障人数
  update_securityNum(state, value) {
    state.detailData.securityNum = value;
  },
  // commNumOfCopies
  update_CommNumOfCopies(state, value) {
    state.detailData.commNumOfCopies = value;
  },
  // 方案详情页面---更新勾选的附险
  update_SelectedSubIn(state, obj) {
    state.detailData.selectedSubIn = {};
    state.detailData.selectedSubIn = JSON.parse(JSON.stringify(obj));
  },
  //方案详情页面--- 更新勾选的主险
  update_SelectedMainIn(state, obj) {
    state.detailData.selectedMainIn = {};
    state.detailData.selectedMainIn = JSON.parse(JSON.stringify(obj));
  },
  //方案详情页面--- 更新主险的总保费
  update_MainPremium(state, value) {
    state.detailData.mainPremium = value;
  },
  //方案详情页面--- 更新试算要素
  update_TrialArr(state, array) {
    state.detailData.trialArr.splice(0, state.detailData.trialArr.length);
    state.detailData.trialArr.push(...array);
  },
  // 方案详情页面--- 更新请求批次代码
  update_strategyId(state, value) {
    state.detailData.strategyId = value;
  },
  //方案详情页面--- 还原方案详情页面数据
  reset_DetailPageData(state) {
    let detailData = {
      //方案详情页面的共享数据
      isIssueAfterPay: 0, //是否见费 1见费
      sumPremium: 0, //总保费
      basePremium: 0, //基础保费
      sumAmount: 0, //总保额
      baseAmount: 0, //基础保额
      commNumOfCopies: 1, //份数
      insuredObjArr: [], //被保人信息---与保障人数相关联的
      securityNum: "", //当前选中的保障人数 securityNum
      commSecurityPeriod: "", //保障期间
      selectedSubIn: {}, //当前勾选的附险
      selectedMainIn: {}, //当前勾选的主险
      mainPremium: 0, //主险保费
      trialArr: [], //试算要素，和保费相关联
      strategyId: 0, //批次代码
      planId: 1, //当前方案
      formulaCode: "", //当前选中的方案
      formData: [], //动态form表单的数据
      riskCode: "", //产品种类代码
      title: "", //产品名称
      giCoveragePeriod: 1, //保障年限
      giPeriodType: 1, // 保障年限单位 1 年 2 月 3 天
      termsAndConditions: [], //页面底部条款
      isNeedHealthNotice: false, //是否需要健康须知
      healthNoticeObj: {}, //健康须知对象
    };
    state.detailData = JSON.parse(JSON.stringify(detailData));
  },
  // 清空数据
  reset_Config(state) {
    let detailData = {
      //方案详情页面的共享数据
      isIssueAfterPay: 0, //是否见费 1见费
      sumPremium: 0, //总保费
      basePremium: 0, //基础保费
      sumAmount: 0, //总保额
      baseAmount: 0, //基础保额
      commNumOfCopies: 1, //份数
      insuredObjArr: [], //被保人信息---与保障人数相关联的
      securityNum: "", //当前选中的保障人数 securityNum
      commSecurityPeriod: "", //保障期间
      selectedSubIn: {}, //当前勾选的附险
      selectedMainIn: {}, //当前勾选的主险
      mainPremium: 0, //主险保费
      trialArr: [], //试算要素，和保费相关联
      strategyId: 0, //批次代码
      planId: 1, //当前方案
      formulaCode: "", //当前选中的方案
      formData: [], //动态form表单的数据
      riskCode: "", //产品种类代码
      title: "", //产品名称
      giCoveragePeriod: 1, //保障年限
      giPeriodType: 1, // 保障年限单位 1 年 2 月 3 天
      termsAndConditions: [], //页面底部条款
      isNeedHealthNotice: false, //是否需要健康须知
      healthNoticeObj: {}, //健康须知对象
    };
    state.detailData = JSON.parse(JSON.stringify(detailData));
    // 下单报文--产品详情页面的信息
    let productInfo = {
      metaDataList: [],
      planId: 1, //批次
      storeCode: "", //店铺code
      formulaCode: "", //方案code
      riskCode: "", //产品code
      // 保险份数
      planCategory: "1", //方案险类
      strategyId: 0, //批次代码
      sumPremium: 0, //总保费
      sumAmount: 0, //总保额
      productList: [
        //勾选的主附险
        // {
        //   clauseCode: "",
        //   kindCode: "",
        // },
      ],
      businessNatureName: "", //直销
      businessNatureCode: "", //转换
      isAgent: "", //1 有代理，0 无代理
      agentInfo: {
        comCode: "",
        isAgentMode: "",
        businessNature: "",
        agentCode: "",
        agentName: "",
        agreementNo: "",
        agreementName: "",
        businessNature2: "",
        handler2Code: "",
        handler2Name: "",
      },
    };
    state.productInfo = JSON.parse(JSON.stringify(productInfo));
    // 下单报文--信息录入页面的信息
    let insuranceInfo = {
      startDate: "", //开始时间
      endDate: "", //结束时间
      timeLimit: "1", //保障年限
      sumPremium: 0, //总保费
      sumAmount: 0, //总保额
      metaDataList: [
        // {
        //   id: 1,
        //   planId: 2,
        //   name: "证件类型",
        //   dataType: "String",
        //   submitName: "/policyCustomerList/[]/idType",
        //   value: "111",
        //   arrayIndex: -1, //不是多个传-1,多个传arrayIndex
        // },
      ],
      invoiceInfo: {
        accountNo: "",
        companyName: "", //发票抬头
        copyDataFromType: "", //同投被保人
        customerType: "", //客户类型
        depositeBankName: "", //开户银行
        email: "", //邮箱
        invoiceType: "", //发票类型
        mobile: "", //移动电话
        taxPayerNo: "", //纳税人识别号
        taxPayerType: "", //纳税人类型
        taxRegisterAddress: "", //注册地址
        taxRegisterTel: "", //注册电话
      },
      invoiceFlag: 0, //("0:无发票信息,1:个人，2：企业")
    };
    state.insuranceInfo = JSON.parse(JSON.stringify(insuranceInfo));
    state.topPos = 0; // 记录滚动位置
    let detailDataPlan = {
      //计划书页面的共享数据
      isIssueAfterPay: 0, //是否见费 1见费
      sumPremium: 0, //总保费
      sheBaoType: "", //当前选中的社保类型，Y 有社保，N 无社保 sheBaoType
      insuredAge: "", //当前选中的年龄  insuredAge
      securityNum: "", //当前选中的保障人数 securityNum
      selectedSubIn: {}, //当前勾选的附险
      selectedMainIn: {}, //当前勾选的主险
      mainPremium: 0, //主险保费
      trialArr: [], //试算要素，和保费相关联
      plantId: 1, //当前方案
      formulaCode: "", //当前选中的方案
    };
    state.detailDataPlan = JSON.parse(JSON.stringify(detailDataPlan));
  },
  //更新下单报文中-- productInfo
  update_ProductInfo(state, obj) {
    for (let k in obj) {
      state.productInfo[k] = obj[k];
    }
  },
  // 重置下单报文-- productInfo
  reset_ProductInfo(state) {
    let productInfo = {
      planId: 1, //批次
      metaDataList: [],
      storeCode: "", //店铺code
      formulaCode: "", //方案code
      riskCode: "", //产品code
      // 保险份数
      planCategory: "1", //方案险类
      strategyId: 0, //批次代码
      sumPremium: 0, //总保费
      sumAmount: 0, //总保额
      productList: [],
      businessNatureName: "", //直销
      businessNatureCode: "", //转换
      isAgent: "", //1 有代理，0 无代理
      agentInfo: {
        comCode: "",
        isAgentMode: "",
        businessNature: "",
        agentCode: "",
        agentName: "",
        agreementNo: "",
        agreementName: "",
        businessNature2: "",
        handler2Code: "",
        handler2Name: "",
      },
    };
    state.productInfo = JSON.parse(JSON.stringify(productInfo));
  },
  //更新下单报文中-- insuranceInfo
  update_InsuranceInfo(state, obj) {
    for (let k in obj) {
      state.insuranceInfo[k] = obj[k];
    }
  },
  //重置下单报文中-- insuranceInfo
  reset_InsuranceInfo(state) {
    let insuranceInfo = {
      startDate: "", //开始时间
      endDate: "", //结束时间
      timeLimit: "1", //保障年限
      sumPremium: 0, //总保费
      sumAmount: 0, //总保额
      metaDataList: [],
      invoiceInfo: {
        accountNo: "",
        companyName: "", //发票抬头
        copyDataFromType: "", //同投被保人
        customerType: "", //客户类型
        depositeBankName: "", //开户银行
        email: "", //邮箱
        invoiceType: "", //发票类型
        mobile: "", //移动电话
        taxPayerNo: "", //纳税人识别号
        taxPayerType: "", //纳税人类型
        taxRegisterAddress: "", //注册地址
        taxRegisterTel: "", //注册电话
      },
    };
    state.insuranceInfo = JSON.parse(JSON.stringify(insuranceInfo));
  },
  //方案详情页面-- 更新被保人信息
  update_insuredObj(state, object) {
    let { type, insuredArr } = object;
    let insuredArray = state.detailData.insuredObjArr;

    for (let i = 0, len = insuredArr.length; i < len; i++) {
      let index = insuredArray.findIndex((item) => {
        return item.field == insuredArr[i].field;
      });
      if (index == -1) {
        if (type != "delete") {
          insuredArray.unshift(insuredArr[i]);
        }
      } else {
        if (type == "delete") {
          insuredArray.splice(index, 1);
        } else {
          insuredArray[index] = insuredArr[i];
        }
      }
    }
    var listArr = [];
    insuredArray.forEach(function(el) {
      for (var i = 0; i < listArr.length; i++) {
        // 对比相同的字段key，相同放入对应的数组
        if (listArr[i].index == el.index) {
          listArr[i].listInfo.push({
            field: el.field,
            index: el.index,
            value: el.value,
          });
          return;
        }
      }
      // 第一次对比没有参照，放入参照
      listArr.push({
        index: el.index,
        listInfo: [
          {
            field: el.field,
            index: el.index,
            value: el.value,
          },
        ],
      });
    });
    insuredArray = listArr;
  },
  // 方案详情的form表单的数据   formData
  update_formData(state, array) {
    state.detailData.formData.splice(0, state.detailData.formData.length);
    state.detailData.formData.push(...array);
  },
  // 存储动态表单--确认投保及确认下单页面 使用
  save_formRule(state, array) {
    state.initRule.push(...array);
  },
  reset_formRule(state) {
    state.initRule = [];
  },
  save_insredFormRule(state, array) {
    state.insuredRule.push(...array);
  },
  reset_insredFormRule(state) {
    state.insuredRule = [];
  },
  save_timeLimitInfo(state, obj) {
    for (let k in obj) {
      state.timeLimitInfo[k] = obj[k];
    }
  },
  reset_timeLimitInfo(state) {
    state.timeLimitInfo = {};
  },
  // 存储动态表单初始值
  save_totForm(state, array) {
    state.totForm.push(...array);
  },
  // 存储动态表单初始值
  save_transportRule(state, array) {
    state.transportRule.splice(0, state.transportRule.length);
    state.transportRule.push(...array);
  },
  reset_totForm(state) {
    state.totForm = [];
  },
  //更新产品详情页面数据--
  update_detailData(state, obj) {
    for (let k in obj) {
      state.detailData[k] = obj[k];
    }
  },
  // 存储动态表单
  save_formatForm(state, array) {
    state.formCreateData.formatForm.splice(
      0,
      state.formCreateData.formatForm.length
    );
    state.formCreateData.formatForm.push(...array);
  },
};
const actions = {
  //方案详情页面--- 记录位置
  updatePos({ commit }, data) {
    commit("setPos", data);
  },
  // 方案详情页面---更新见费非见费
  updateIsIssueAfterPay({ commit }, value) {
    commit("update_IssueAfterPay", value);
  },
  // 方案详情页面---更新总保费
  updateSumPremium({ commit }, value) {
    commit("update_SumPremium", value);
  },
  //方案详情页面--- 更新保障人数
  updateSecurityNum({ commit }, value) {
    commit("update_securityNum", value);
  },
  //方案详情页面--- 更新保险份数 commNumOfCopies
  updateCommNumOfCopies({ commit }, value) {
    commit("update_CommNumOfCopies", value);
  },
  // 方案详情页面---更新勾选的附险
  updateSelectedSubIn({ commit }, value) {
    commit("update_SelectedSubIn", value);
  },
  //方案详情页面--- 更新勾选的主险
  updateSelectedMainIn({ commit }, value) {
    commit("update_SelectedMainIn", value);
  },
  //方案详情页面--- 更新主险的总保费
  updateMainPremium({ commit }, value) {
    commit("update_MainPremium", value);
  },
  //方案详情页面--- 更新试算要素
  updateTrialArr({ commit }, value) {
    commit("update_TrialArr", value);
  },
  // 方案详情页面 --- 还原方案详情页面数据
  resetDetailPageData({ commit }) {
    commit("reset_DetailPageData");
  },

  //更新下单报文-- productInfo
  updateProductInfo({ commit }, obj) {
    commit("update_ProductInfo", obj);
  },
  // 重置下单报文-- productInfo
  resetProductInfo({ commit }) {
    commit("reset_ProductInfo");
  },
  //更新下单报文-- insuranceInfo
  updateInsuranceInfo({ commit }, obj) {
    commit("update_InsuranceInfo", obj);
  },
  // 重置下单报文-- insuranceInfo
  resetInsuranceInfo({ commit }) {
    commit("reset_InsuranceInfo");
  },
  //方案详情页面-- 更新被保人信息
  updateInsuredObj({ commit }, object) {
    commit("update_insuredObj", object);
  },
  //方案详情页面-- 更新form动态表单数据
  updateFormData({ commit }, array) {
    commit("update_formData", array);
  },
  // 方案详情页面--- 更新请求批次代码
  updateStrategyId({ commit }, array) {
    commit("update_strategyId", array);
  },
  // 方案详情---更新方案详情信息
  updateDetailData({ commit }, value) {
    commit("update_detailData", value);
  },
  // 保存运输工具表单数据
  saveTransportRule({ commit }, value) {
    commit("save_transportRule", value);
  },
  //方案详情页面--- 更新基础保费
  updateBasePremium({ commit }, value) {
    commit("update_basePremium", value);
  },
  // 存储动态表单
  saveFormatForm({ commit, value }) {
    commit("save_formatForm", value);
  },
};
export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
