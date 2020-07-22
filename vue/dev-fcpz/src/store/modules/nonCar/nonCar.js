/*
 * @Description: 非车财产险数据
 * @Date: 2020-03-18 21:41:50
 * @Author: zhangminjie
 * @LastEditTime: 2020-05-13 17:27:30
 * @FilePath: \dev-scyh\src\store\modules\nonCar\nonCar.js
 */

const state = {
  //共享数据（下单信息）
  shareData: {
    productInfo: {
      storeCode: "",
      productCode: "",
      riskCode: "YEF",
      formulaCode: "",
      formatPremium: "", //保费
      premium: "", //保费
      sumPremium: "", //总保费
      isAgent: "",
      businessNatureName: "", //直销
      businessNatureCode: "", //转换
      formulaSize: 1, //保险份数
      agentInfo: {
        agentCode: "",
        agentName: "",
        agreementNo: "",
        agreementName: "",
        businessNature: "",
        businessNature2: "",
        handler2Code: "",
        handler2Name: "",
      },
    },
    insuranceInfo: {
      orderNo: "", //订单id
      custNo: "", //客户号
      custType: "", //客户类型
      custSourceType: "", // 客户标识  1本地客户,2保单客户
      proposalNo: "", //投保单号
      appliName: "",
      appliIdentifyType: "810", //转换
      appliIdentifyNumber: "",
      appliMobile: "",
      appliEMail: "",
      appliIdentity: "01", //转换
      insuredName: "",
      insuredIdentifyType: "810", //转换
      insuredIdentifyNumber: "",
      insuredMobile: "",
      vehicleModelCode: "", //转换
      licenseNo: "",
      engineNo: "",
      approvalQuality: "",
      vehicleAge: "",
      brandModelName: "",
      startDate: "",
      endDate: "",
      invoiceInfo: {
        accountNo: "",
        companyName: "", //发票抬头
        copyDataFromType: "", // 同投被保人
        customerType: "", //转换
        depositeBankName: "",
        email: "",
        invoiceType: "", //转换
        mobile: "",
        taxPayerNo: "",
        taxPayerType: "", //转换
        taxRegisterAddress: "",
        taxRegisterTel: "",
      },
    },
    isIssueAfterPay: 0, //是否见费 1见费
  },
  resultList: [],
  topPos: 0, // 记录滚动位置
};
const mutations = {
  setPos(state, data) {
    state.topPos = data;
  },
  //更新下单共享数据
  update_shareData(state, obj) {
    let { info, type } = obj;
    if (type == 1) {
      //更新产品信息
      for (let key in info) {
        state.shareData.productInfo[key] = info[key];
      }
    } else {
      //更新投保信息填写页面的信息
      for (let key in info) {
        state.shareData.insuranceInfo[key] = info[key];
      }
    }
  },
  //  改变resultList，存值
  getresultList(state, list) {
    state.resultList = list;
  },
  //  清空resultList，存值
  clearresultList(state) {
    state.resultList = [];
  },
  // 清空共享数据
  clear_shareData(state, type) {
    let shareData = {
      productInfo: {
        storeCode: "",
        productCode: "",
        riskCode: "YEF",
        formulaCode: "",
        formatPremium: "", //保费
        sumPremium: "", //总保费
        isAgent: "",
        businessNatureName: "", //直销
        businessNatureCode: "", //转换
        formulaSize: 1, //保险份数
        agentInfo: {
          agentCode: "",
          agentName: "",
          agreementNo: "",
          agreementName: "",
          businessNature: "",
          businessNature2: "",
          handler2Code: "",
          handler2Name: "",
        },
      },
      insuranceInfo: {
        orderNo: "", //订单号
        custNo: "", //客户号
        custType: "", //客户类型
        custSourceType: "", // 客户标识  1本地客户,2保单客户
        proposalNo: "", //投保单号
        appliName: "",
        appliIdentifyType: "810", //转换
        appliIdentifyNumber: "",
        appliMobile: "",
        appliEMail: "",
        appliIdentity: "01", //转换
        insuredName: "",
        insuredIdentifyType: "810", //转换
        insuredIdentifyNumber: "",
        insuredMobile: "",
        vehicleModelCode: "", //转换
        licenseNo: "",
        engineNo: "",
        approvalQuality: "",
        vehicleAge: "",
        brandModelName: "",
        startDate: "",
        endDate: "",
        invoiceInfo: {
          accountNo: "",
          companyName: "", //发票抬头
          copyDataFromType: "", // 同投被保人
          customerType: "", //转换
          depositeBankName: "",
          email: "",
          invoiceType: "", //转换
          mobile: "",
          taxPayerNo: "",
          taxPayerType: "", //转换
          taxRegisterAddress: "",
          taxRegisterTel: "",
        },
      },
      isIssueAfterPay: 0,
    };
    if (type == 1) {
      //清空产品信息
      state.shareData.productInfo = JSON.parse(
        JSON.stringify(shareData.productInfo)
      );
    } else if (type == 2) {
      //清空投保信息填写页面的信息
      state.shareData.insuranceInfo = JSON.parse(
        JSON.stringify(shareData.insuranceInfo)
      );
    } else {
      //全部清空
      state.shareData = JSON.parse(JSON.stringify(shareData));
    }
  },

  getinfoObj(state, obj) {
    const { certificateNo, certificateType, custName } = obj;
    (state.infoObj.certificateNo = certificateNo),
      (state.infoObj.certificateType = certificateType),
      (state.infoObj.custName = custName);
  },
  resetInfoObj(state) {
    state.infoObj = {};
  },
};
const actions = {
  // 记录位置
  updatePos({ commit }, data) {
    commit("setPos", data);
  },
  // 更新数据
  updateShareData({ commit }, obj) {
    commit("update_shareData", obj);
  },
  // 清空共享数据
  clearShareData({ commit }, type) {
    commit("clear_shareData", type);
  },
  // setinfoObj({ commit }, obj) {
  //   commit('getinfoObj', obj)
  // },
};
export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
