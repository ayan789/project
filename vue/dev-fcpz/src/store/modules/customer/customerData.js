import {
  getQueryIndemnifyInfos,
  getCustomerInteractions,
  addOrUpdateInteractions,
  getQueryViolationRecord,
  getQueryViolationCarList, getQueryRemindTaskList
} from '../../../http/module/customerApi';
import { Toast } from 'vant';

const tempData = {
  carNo: "",//车牌号
  frameNo: "",//车架号
  enginNo: "",//发动机号
  brandName: "",//车种
  isAuth: "",//是否授权 1:授权；2:未授权
  updateTime: "",//更新时间
  violationList: [//违章列表
    {
      behavior: "",//违章类型
      address: "",//违法地点
      time: "",//违法时间
      fine: "",//罚款金额
      score: "",//违法记分
    }
  ]
};

const state = {
  //理赔查询：
  getQueryIndemnifyData: [{
    policyNo: '', //保单号
    licenseNo: '', //车牌号
    insuredName: '', //被保险人
    registNo: '', //报案号
    reportDate: '', //报案时间
    status: '', //状态 10结案，09支付 08定损，07报案
    sumPaid: '', //赔付金额
  }],

  //获取违章记录
  getQueryViolationRecordData: {
    carNo: "",//车牌号
    frameNo: "",//车架号
    enginNo: "",//发动机号
    brandName: "",//车种
    isAuth: "",//是否授权 1:授权；2:未授权
    updateTime: "",//更新时间
    violationList: [//违章列表
      {
        behavior: "",//违章类型
        address: "",//违法地点
        time: "",//违法时间
        fine: "",//罚款金额
        score: "",//违法记分
      }
    ]
  },

  //获取所有车辆
  getCarList: [],

  //影像上传
  uploadImageData: {},

  //查询客户互动纪录
  getCustomerInteractionData: [
    {
      userCode: "",
      id: 85,
      title: "自定义备注",
      type: "12",
      remark: "",
      custNo: "",
      createTime: "",
      updateTime: "",
      year: "2020",
      month: "3",
      createUser: "",
      updateUser: "",
    }
  ],

  //新增或修改互动纪录
  addOrUpdateInteractionsData: {},

  //获取生日祝福界面的贺卡图片
  getQueryConfigPhotoListData: [{
    photoUrl: '', //图片静态链接
    url: '' //图片指向链接
  }],

  followRemindData: {
    doneCount: "", //已完成数量
    renewalCount: "", //续保提醒数量
    custCareCount: "", //客户关怀数量
    unregisteredCount: "", //代注册数量
    claimCount: "", //出险提醒数量
    allCount: "",//今日总任务数
    remindList: [//提醒列表
      {
        id: "", //提醒 ID
        custNo: "", //客户号
        custName: "", //客户名称
        custType: "", //客户类型 todo
        remindType: "", //提醒类型
        remindTitle: "", //提醒标题
        remindTime: "", //提醒时间 todo
        policyNo: "" //保单号
      }
    ],
  },
  active: 0
};

const mutations = {
  //获取跟进提醒数据
  get_followRemindData: (state, data) => {
    state.followRemindData = data;
  },
  //查询理赔
  GET_IndemnifyData: (state, listData) => {
    state.getQueryIndemnifyData = listData;
  },
  //获取违章记录
  getQueryViolationRecordData: (state, listData) => {
    state.getQueryViolationRecordData = listData;
  },
  //获取车辆
  getQueryViolationCarList: (state, listData) => {
    state.getCarList = listData;
  },
  //影像上传
  Upload_ImageData: (state, listData) => {
    state.uploadImageData = listData;
  },

  //查询客户互动纪录
  GET_CustomerInteractionData: (state, listData) => {
    state.getCustomerInteractionData = JSON.parse(JSON.stringify(listData));
  },

  //新增或修改互动纪录
  AddOrUpdate_InteractionData: (state, listData) => {
    state.addOrUpdateInteractionsData = listData;
  },

  //获取生日祝福界面的贺卡图片
  GET_QueryConfigPhotoList: (state, listData) => {
    state.getQueryConfigPhotoListData = listData;
  },
  //清空
  clearData_: (state, data) => {
    state.getQueryViolationRecordData = { ...tempData };
  },
  //缓存跟进标签
  followActive: (state, data) => {
    state.active = data;
  },
};

const actions = {
  //获取跟进数据
  async getfollowRemindDatat({ commit }, promise) {
    let res = await getQueryRemindTaskList(promise);
    if (res.code == '1') {
      commit('followActive', promise.remindType);
      res.data.remindList.map((v, i) => {
        switch (v.remindType) {
          case "1":
            v.tip = '查看';
            break;
          case "2":
            v.tip = '跟进';
            break;
          case "3":
            v.tip = '送上祝福';
            break;
          case "4":
            v.tip = '提醒';
            break;
          case "5":
            v.tip = '转发推荐码';
            break;
        }
      });
      commit('get_followRemindData', res.data ? res.data : {});
    } else {
      Toast(res.msg);
    }
  },
  //理赔查询：
  async get_QueryIndemnifyInfos({ commit }, promise) {
    let res = await getQueryIndemnifyInfos(promise);
    //console.log(res, '理赔详情：====res');
    if (res.code == '1') {
      commit('GET_IndemnifyData', res.data ? res.data : []);
    } else {
      Toast(res.msg);
    }
  },
  //获取违章记录
  async getQueryViolationRecordData({ commit }, promise) {
    let res = await getQueryViolationRecord(promise);
    //console.log(res, '获取违章记录：====res');
    if (res.code == '1') {
      commit('getQueryViolationRecordData', res.data);
    } else {
      Toast(res.msg);
    }
  },
  //获取车辆
  async getQueryViolationCarList({ commit }, promise) {
    let res = await getQueryViolationCarList(promise);
    //console.log(res, '获取所有车辆：====res');
    if (res.code == '1') {
      let tempArr = JSON.parse(JSON.stringify(res.data.carList));
      tempArr.map((v, i) => {
        v.name = v.carNo;
      });
      commit('getQueryViolationCarList', tempArr);
    } else {
      Toast(res.msg);
    }
  },

  //新增或修改互动纪录
  async addOrUpdateInteractions({ commit }, promise) {
    let res = await addOrUpdateInteractions(promise);
    if (res.code == '1') {
      //console.log(res, '查询互动纪录：====res');
    } else {
      Toast(res.msg);
    }
  },

  //查询客户互动纪录
  async getCustomerInteractions({ commit }, promise) {
    let res = await getCustomerInteractions(promise);
    //console.log(res, '新增或修改互动纪录：====res');
    if (res.code == '1') {
      let tempData = JSON.parse(JSON.stringify(res.data));
      commit('GET_CustomerInteractionData', tempData);
    } else {
      Toast(res.msg);
    }
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
