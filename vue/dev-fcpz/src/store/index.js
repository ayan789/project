/*
 * @Author: King
 * @since: 2020-03-03 21:53:24
 * @Last Modified by: King
 * @Last Modified time: 2020-05-14 18:20:15
 * @FilePath: \nonCarCongfig\src\store\index.js
 */
import Vue from "vue";
import Vuex from "vuex";
import persistedState from "vuex-persistedstate";

import loginInfo from "./modules/loginInfo/loginInfo";
import vehicle from "./modules/vehicle/vehicle";
import customerData from "./modules/customer/customerData"; //客户中心的模块
import editorCustomerData from "./modules/customer/editorCustomerData"; //客户编辑/新增
import customerInfoData from "./modules/customer/customerInfoData"; //客户个人信息
import orderList from "./modules/order/orderList"; //订单列表信息
import nonCar from "./modules/nonCar/nonCar"; //非车信息
import activityData from "./modules/activity/activityData"; //订单列表信息
import channel from "./modules/channel/channel";
import million from "./modules/nonCar/million"; //
import config from "./modules/nonCar/config";

Vue.use(Vuex);
import createLogger from "vuex/dist/logger";
export default new Vuex.Store({
  plugins: [persistedState({ storage: window.sessionStorage }), createLogger()],
  state: {},
  mutations: {},
  actions: {},
  modules: {
    loginInfo,
    vehicle,
    channel,
    customerData,
    editorCustomerData,
    customerInfoData,
    orderList,
    nonCar,
    activityData,
    million,
    config,
  },
});
