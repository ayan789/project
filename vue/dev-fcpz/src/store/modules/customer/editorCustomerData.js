/**
 * Created by yuyang
 */
import {
  addOrUpdateSinglecustomeromer,
  addOrUpdateTeamCustomer,
  getqueryCustInfo, getQuerycustomeromerList
} from "../../../http/module/customerApi";
import { Toast } from 'vant';

import mapper from "../../../utils/mapper";
import $router from '../../../router/index';
import * as native from "../../../utils/JSBridge";
import { GetRequest } from "../../../utils";

const tempSate = {
  //公共参数
  createUser: "",//创建人
  updateUser: "",//更新人 （编辑 用）
  id: "",//主键ID （编辑 用）
  custNo: "", //客户号
  custType: "1",//客户类型
  custSourceType: "",//1:本地客户；2 保单客户 （编辑用）
  birthday: "",//生日
  sex: "",//F:女性 M:男性
  userCode: "", //业务号
  custName: "", //姓名
  certificateType: "01", //证件类型
  certificateNo: "", //证件号码
  mobile: "", //手机号
  email: "", //邮箱
  address: "", //联系地址1

  carNo: "", //车牌
  frameNo: "", //车架号
  brandNo: "", //厂牌型号
  engineNo: "", //发动机号
  profession: "", //职业
  addressType: "01", //地址类型

  unitNature: "01", //单位性质
  businessScope: "", //企业经营范围
  contractedProducts: "", //可承保产品
  remark: "", //备注1

  // 临时
  mobileNumber: 1, //手机个数
  mobileList: [
    { phoneNumber: '' }
  ],//手机数组
  // addreeNumber: 1,//联系地址个数
  // addressText: '',//地类型址显示
  // addressList: [],//联系地址数组
  carList: []
};

const state = {
  tempData: {
    //公共参数
    createUser: "",//创建人
    updateUser: "",//更新人 （编辑 用）
    id: "",//主键ID （编辑 用）
    custNo: "", //客户号
    custType: "1",//客户类型
    custSourceType: "",//1:本地客户；2 保单客户 （编辑用）
    birthday: "",//生日
    sex: "",//F:女性 M:男性
    userCode: "", //业务号
    custName: "", //姓名
    certificateType: "01", //证件类型
    certificateNo: "", //证件号码
    mobile: "", //手机号
    email: "", //邮箱
    address: "", //联系地址1

    carNo: "", //车牌
    frameNo: "", //车架号
    brandNo: "", //厂牌型号
    engineNo: "", //发动机号
    profession: "", //职业
    addressType: "01", //地址类型

    unitNature: "01", //单位性质
    businessScope: "", //企业经营范围
    contractedProducts: "", //可承保产品
    remark: "", //备注1

    // 临时
    mobileNumber: 1, //手机个数
    mobileList: [
      { phoneNumber: '' }
    ],//手机数组
    // addreeNumber: 1,//联系地址个数
    // addressText: '',//地类型址显示
    // addressList: [],//联系地址数组
    carList: []
  },
  //客户列表数据
  customerList: [],
  //注册客户列表
  customerList_ZC: [],
  //未注册客户列表
  customerList_wZC: [],
  taskCount: 0,
  active: 0,
  number:0
};

const mutations = {
  // 新增客户
  ADD_SELF_CUSTOMER_DATA: (state, data) => {
    state.tempData = { ...tempSate };
  },
  //缓存标签
  myCustomer_active: (state, data) => {
    state.active = data;
  },

  //客户列表数据
  CUSTOMER_LIST: (state, res) => {
    state.customerList = res.customerList;
    state.taskCount = res.taskCount;
    state.number = res.customerList.length
    console.log(res.customerList,"heweiwei何韦韦")
    for (var i = 0 ; i <res.customerList.length;i++) {
      state.number += res.customerList[i].customerList.length
    }
    console.log(state.number,"heweiwei")
  },
  //是否注册客户列表
  CUSTOMER_LIST_ZC: (state, res) => {
    state.customerList_ZC = res.customerList_ZC;
    state.customerList_wZC = res.customerList_WZC;
    state.taskCount = res.taskCount;
  },
  //改变渲染的注册未客户列表数据愿
  CHANGE_WCOSTOMER: (state, res) => {
    state.customerList = state.customerList_wZC;
  },
  //改变渲染的注册客户列表数据愿
  CHANGE_COSTOMER: (state, res) => {
    state.customerList = state.customerList_ZC;
  },
  // 选择数据
  changeForm(state, payload) {
    const name_ = payload.name;
    const value_ = payload.value;
    eval(`state.tempData.${name_} = value_`);
  },
  //编辑新增地址
  changeForm_(state, payload) {
    state.addressList.map((v, i) => {
      if (i == payload.index) {
        v.addressType = payload.item.value;
        v.text = payload.item.text;
      }
    });
  },
  // 查询客户信息
  QUERY_SELF_CUSTOMER_DAT: (state, data) => {
    let tempObj = JSON.parse(JSON.stringify(data.data));
    const { mobileList } = tempObj;
    state.tempData = data.data;
  },

  //新增手机号
  addMobile_: (state, data) => {
    if (state.tempData.mobileList.length < 3) {
      state.tempData.mobileList.push({ phoneNumber: "", type: 'N' });
    }
  },
  //删除手机号
  deleteMobile_: (state, i) => {
    state.tempData.mobileList = state.tempData.mobileList.filter((v, index) => {
      return index != i;
    });
  },
  //新增地址
  addAddress_: (state, data) => {
    if (state.addreeNumber < 3) {
      state.addressList.push({ address: "", addressType: "01", text: '家庭地址' });
      state.addreeNumber = state.addreeNumber + 1;
    }
  },
  deleteAddress_: (state, i) => {
    state.addressList = state.addressList.filter((v, index) => {
      return index != i;
    });
    state.addreeNumber = state.addreeNumber - 1;
  },
  //传值
  setData_: (state, data) => {
    state.tempData = data;
    state.tempData.mobileList = data.mobileList.length > 0 ? data.mobileList : [{ phoneNumber: '' }];
    state.tempData.certificateType = data.certificateType || '01';
    state.tempData.mobileList.map((v, i) => {
      v.type = v.phoneNumber ? 'Y' : 'N';
    });
    console.log(state.tempData.mobileList, '==state.tempData.mobileList');
  },
  //清空
  clearData_: (state, data) => {
    state.tempData = { ...tempSate };
    state.tempData.mobileList = [
      { phoneNumber: '' }
    ];
  }
};

const actions = {
  async addSelfCustomerData({ dispatch, commit }, promise) {
    console.log(promise, '===promise');
    let res = await addOrUpdateSinglecustomeromer(promise);
    if (res.code == '1') {
      commit('ADD_SELF_CUSTOMER_DATA', res);
      if (GetRequest("source", true)) {
        native.closeWeb(() => {
        }, { routerName: "myCustomer" });
      } else {
        $router.back();
      }
      commit('clearData_', res);
    } else {
      Toast(res.msg);
    }
  },
  async addTeamCustomerData({ commit }, promise) {
    let res = await addOrUpdateTeamCustomer(promise);
    if (res.code == '1') {
      commit('ADD_TEAM_CUSTOMER_DATA', res);
    } else {
      Toast(res.msg);
    }
  },
  async querySelfCustomerData({ commit }, promise) {
    let res = await getqueryCustInfo(promise);
    if (res.code == '1') {
      commit('QUERY_SELF_CUSTOMER_DAT', res);
    } else {
      Toast(res.msg);
    }
  },
  async queryCustomerList_({ commit }, promise) {
    let res = await getQuerycustomeromerList(promise, { loading: false });
    if (res.code == '1') {
      //是否已注册客户
      let arr_ = [];
      res.data.listMap.map((v, i) => {
        if (v.customerList.length > 0) {
          arr_.push(v);
        }
      });
      let tempArr_ = JSON.parse(JSON.stringify(arr_));
      let tempArr_ZC = [], tempArr_WZC = [];
      tempArr_.map((v_, index_) => {
        let arrZC = v_.customerList.filter((v1, i1) => {
          return v1.isReg == '1';
        });
        tempArr_ZC.push({
          letter: v_.letter,
          customerList: arrZC
        });
      });
      let tempArr_ZC_1 = [];
      tempArr_ZC.map((v_2, i_2) => {
        if (v_2.customerList.length > 0) {
          tempArr_ZC_1.push(v_2);
        }
      });

      tempArr_.map((v_, index_) => {
        let arrWZC = v_.customerList.filter((v1, i1) => {
          return v1.isReg == '0';
        });
        tempArr_WZC.push({
          letter: v_.letter,
          customerList: arrWZC
        });
      });


      let obj = {
        customerList_ZC: tempArr_ZC_1,
        customerList_WZC: tempArr_WZC,
        taskCount: res.data.taskCount
      };
      console.log(obj, '我擦===');
      commit('CUSTOMER_LIST_ZC', obj);
    }
  },
  async queryCustomerList({ commit }, promise) {
    let res = await getQuerycustomeromerList(promise, { loading: true });
    if (res.code == '1') {
      let temApp = [];
      res.data.listMap.map((v, i) => {
        if (v.customerList.length > 0) {
          temApp.push(v);
        }
      });
      let obj = {
        customerList: temApp,
        taskCount: res.data.taskCount
      };
      commit('CUSTOMER_LIST', obj);
    } else {
      Toast(res.msg);
    }
  }
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
