/**
 * Created by yuyang
 */
import {
  getqueryCustInfo,
  queryTeamCustInfo,
  getQueryCustomerLevel,
  getQueryPolicyNoList,
  getSelectUserPolicyNo,
  getQueryIndemnifyInfo,
  uploadImage,
  getQueryImage,
  getIndexHp,
  getQueryRemindTaskList,
  getQueryCustomerInquiry,
  deleteCustomer,
  imageUpload,
  imageSee
} from "../../../http/module/customerApi";
import imageUrl from '../../../utils/globalImg';

const state = {
  CustInfo:
    {
      custNo: "", //客户号
      custSourceType: "",//客户标识
      custType: "", //客户类型
      custName: "", //姓名
      sex: "", //性别
      birthday: "", //出生日期
      custIntegral: "", //客户积分
      custLevel: "", //客户等级
      certificateType: "", //证件类型
      certificateNo: "", //证件号码
      mobile: "", //手机号
      email: "",//邮箱
      profession: "", //职业
      address: "", //联系地址
      policyAdress: "",
      remark: "", //备注
      localArea: "", //所属地
      isMillionsMedical: "", //是否投保百万医疗
      carList: [//车信息列表
        {
          carNo: "", //车牌号
          vim: "", //车架号
          brandNo: "", //厂牌型号
          engineNo: "", //发动机号
        }
      ],
      unitNature: "",//单位性质
      businessScope: "",//企业经营范围
      contractedProducts: ""//可承保产品
    },
  teamCustInfo: {
    custNo: "",//客户号
    customerType: "",//客户类型
    customerName: "",//姓名
    certificateType: "",//证件类型
    certificateNo: "",//证件号码
    mobileNum: "",//手机号
    email: "",//邮箱
    unitNature: "",//单位性质
    businessScope: "",//企业经营范围
    contractedProducts: "",//可承保产品
    address: "",//联系地址
    remark: ""//备注
  },
  customerStartLevel: {
    level: "",//当前星级
    integral: "",//当前积分
    nextLevelIntegral: "",//
  },
  integralList: [//星级列表
    {
      level: "",//星级
      serviceItemList: [
        {
          serviceName: ""//权益名称
        }
      ]
    }
  ],
  policyNoList: {},
  userPolicyNo: {},
  //根据保单号查询保单列表
  ndemnifyInfo: {},
  uploadImage: {},
  getQueryImage: {},
  getIndexHp: {},//产品推荐
  getQueryRemindTaskList: {},//提醒列表
  deleteCustomer: {},//删除客户
  imageUpload: {},//获取影像地址
  imageSee: {},//获取影像图片
};

const mutations = {
  //查询客户个人信息
  getCustInfo: (state, data) => {
    state.CustInfo = data.data && data.data;
  },
  //查询团体客户信息
  getTeamCustInfo: (state, data) => {
    state.teamCustInfo = data.data && data.data;
  },
  //查询客户积分
  getQueryCustomerLevel: (state, data) => {
    state.customerStartLevel = data.data;
  },
  //查询积分权益
  getIntegralList: (state, data) => {
    state.integralList = data.data;
  },
  //理赔查询-查出保单号列表
  getQueryPolicyNoList: (state, data) => {
    state.policyNoList = data.data && data.data;
  },
  //理赔查询-根据保单号查询出保单列表
  getQueryIndemnifyInfo: (state, data) => {
    state.ndemnifyInfo = data.data && data.data;
  },
  //我的-保单查询
  getSelectUserPolicyNo: (state, data) => {
    state.userPolicyNo = data.data && data.data;
  },
  //uploadImage
  uploadImage: (state, data) => {
    state.uploadImage = data.data && data.data;
  },
  //下载影像图片
  getQueryImage: (state, data) => {
    state.getQueryImage = data.data && data.data;
  },
  //产品推荐
  getIndexHp: (state, data) => {
    state.getIndexHp = data.data && data.data;
  },
  //提醒列表
  getQueryRemindTaskList: (state, data) => {
    state.getQueryRemindTaskList = data.data && data.data;
  },
  //删除客户
  deleteCustomer: (state, data) => {
    state.deleteCustomer = data.data && data.data;
  },
  //获取影像地址
  imageUpload: (state, data) => {
    state.imageUpload = data.data && data.data;
  },

  //获取影像图片
  imageSee: (state, data) => {
    state.imageSee = data.data && data.data;
  },


};

const actions = {
  async getqueryCustInfo({ commit }, promise) {
    let res = await getqueryCustInfo(promise);
    //console.log(res, '查询客户个人信息：====res');
    if (res) {
      commit('getCustInfo', res);
    }
  },
  async getTeamCustInfo({ commit }, promise) {
    let res = await queryTeamCustInfo(promise);
    // res = {"code":"1","data":{"userCode":null,"id":null,"custType":"2","custNo":"90020023652","custName":"长丰县一鸣农产品有限公司","sex":null,"birthday":null,"certificateType":null,"certificateNo":null,"mobile":null,"carNo":null,"frameNo":null,"brandNo":null,"engineNo":null,"email":null,"profession":null,"address":null,"addressType":null,"remark":null,"unitNature":null,"businessScope":null,"contractedProducts":null,"status":null,"createTime":null,"updateTime":null,"custSourceType":2,"createUser":null,"updateUser":null,"remindList":["2张车险在保保单","2张百万医疗险在保保单"],"mobileList":null},"msg":""}
    console.log(res, '查询团体客户信息：====res');
    if (res) {
      commit('getTeamCustInfo', res);
    }
  },
  async getQueryCustomerLevel({ commit }, promise) {
    let res = await getQueryCustomerLevel(promise);
    //console.log(res, '查询积分信息：====res');
    if (res.code == '1') {
      res.data.activeName = res.data.level + '';
      console.log(res, '===level==');
      commit('getQueryCustomerLevel', res);
    }
  },
  //getIntegralList
  async getGetIntegralList({ commit }, promise) {
    let res = await getQueryCustomerInquiry(promise);
    if (res.code == '1') {
      res.data.map((v, index) => {
        switch (v.level) {
          case "1":
            v.levelTitle = '一星';
            v.tempStyle = {
              color: "#416EA7"
            };
            v.tempImguRL = imageUrl.lv1;
            v.startValue = +v.level;
            v.tempColor = "#416EA7";
            break;
          case "2":
            v.levelTitle = '二星';
            v.tempStyle = {
              color: "#ffffff"
            };
            v.tempImguRL = imageUrl.lv2;
            v.startValue = +v.level;
            v.tempColor = "#ffffff";
            break;
          case "3":
            v.levelTitle = '三星';
            v.tempStyle = {
              color: "#FFFFFF"
            };
            v.tempImguRL = imageUrl.lv3;
            v.startValue = +v.level;
            v.tempColor = "#ffffff";
            break;
          case "4":
            v.levelTitle = '四星';
            v.tempStyle = {
              color: "#594322"
            };
            v.tempImguRL = imageUrl.lv4;
            v.startValue = +v.level;
            v.tempColor = "#594322";
            break;
          case "5":
            v.levelTitle = '五星';
            v.tempStyle = {
              color: "#D1B792"
            };
            v.tempImguRL = imageUrl.lv5;
            v.startValue = +v.level;
            v.tempColor = "#D1B792";
            break;
        }
      });
      commit('getIntegralList', res);
    }
  },
  async getQueryPolicyNoList({ commit }, promise) {
    let res = await getQueryPolicyNoList(promise);
    //console.log(res, '查询保单号列表：====res');
    if (res) {
      commit('getQueryPolicyNoList', res);
    }
  },
  async getQueryIndemnifyInfo({ commit }, promise) {
    let res = await getQueryIndemnifyInfo(promise);
    //console.log(res, '根据保单号查询保单列表：====res');
    if (res) {
      commit('getQueryIndemnifyInfo', res);
    }
  },
  async getSelectUserPolicyNo({ commit }, promise) {
    let res = await getSelectUserPolicyNo(promise);
    //console.log(res, '我的-个人保单：====res');
    if (res) {
      commit('getSelectUserPolicyNo', res);
    }
  },
  async uploadImage({ commit }, promise) {
    let res = await uploadImage(promise);
    //console.log(res, '上传图片：====res');
    if (res) {
      commit('uploadImage', res);
    }
  },
  async getQueryImage({ commit }, promise) {
    let res = await getQueryImage(promise);
    //console.log(res, '下载图片：====res');
    if (res) {
      commit('getQueryImage', res);
    }
  },
  async getIndexHp({ commit }, promise) {
    let res = await getIndexHp(promise);
    //console.log(res, '推荐产品方案（热销产品推荐）：====res');
    if (res) {
      commit('getIndexHp', res);
    }
  },
  async getQueryRemindTaskList({ commit }, promise) {
    let res = await getQueryRemindTaskList(promise);
    // let res = {"code":"1","data":{"allCount":"3","doneCount":"0","renewalCount":"0","custCareCount":"2","unregisteredCount":"0","claimCount":"1","remindList":[{"userCode":"","id":2,"remindType":"1","remindTitle":"出险提示","custNo":"80291534631","custName":"客户1","policyNo":"PDEA201544010000003819","registNo":null,"taskStatus":"0","outOfInsuranceStatus":"0","remindTimeNode":"2020-02-28 15:52:49","createTime":"2020-02-28 15:52:49","updateTime":"2020-03-25 17:25:04","createUser":null,"updateUser":null,"taskTime":"2020-03-12 11:06:29"},{"userCode":"","id":39,"remindType":"3","remindTitle":"生日提醒","custNo":"80291534631","custName":"客户38","policyNo":"123","registNo":null,"taskStatus":"0","outOfInsuranceStatus":"0","remindTimeNode":"2020-03-29 15:52:49","createTime":"2020-02-28 15:52:49","updateTime":"2020-03-25 17:25:08","createUser":null,"updateUser":null,"taskTime":"2020-03-12 11:06:29"},{"userCode":"","id":5,"remindType":"4","remindTitle":"权益提醒","custNo":"80291534631","custName":"客户4","policyNo":"123","registNo":null,"taskStatus":"0","outOfInsuranceStatus":"0","remindTimeNode":"2020-02-28 15:52:49","createTime":"2020-02-28 15:52:49","updateTime":"2020-03-25 17:25:07","createUser":null,"updateUser":null,"taskTime":"2020-03-12 11:06:29"}]},"msg":""}
    console.log(res, '提醒列表：====res');
    if (res) {
      commit('getQueryRemindTaskList', res);
    }
  },
  //deleteCustomer
  async deleteCustomer({ commit }, promise) {
    let res = await deleteCustomer(promise);
    console.log(res, '删除客户：====res');
    if (res) {
      commit('deleteCustomer', res);
    }
  },
  //上传影像接口，请求影像的地址
  async imageUpload({ commit }, promise) {
    let res = await imageUpload(promise);
    if (res) {
      commit('imageUpload', res);
    }
  },
  //查询影像图片
  async imageSee({ commit }, promise) {
    let res = await imageSee(promise);
    if (res) {
      commit('imageSee', res);
    }
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
