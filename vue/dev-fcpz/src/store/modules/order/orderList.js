/*
 * @Description: 订单数据
 * @Date: 2020-03-16 21:09:16
 * @Author: zhangminjie
 * @LastEditTime: 2020-04-03 17:00:20
 * @FilePath: \vue-sales\src\store\modules\order\orderList.js
 */
import Vue from 'vue'
import { Toast } from 'vant'
Vue.use(Toast)
const state = {
  insuranceTypeTab: {
    insuranceType: 2,
  },
  orderTabObj: {
    orderActive: 0,
  },
  back_page: true,
  backInfoObj: {
    id:"",
    status:"",
    payExpireFlag:"",
    oneClickRenewalFlag:""
  },
}

const mutations = {
  // 车险详情页跳转至定型
  setbackInfoObj(state, obj) {
    state.backInfoObj = obj
  },
  resetbackInfoObj(state) {
    state.backInfoObj = {}
  },
  // 修改orderListTabs的险种类型的头Active
  setInsuranceTypeTab(state, insuranceType) {
    state.insuranceTypeTab.insuranceType = insuranceType
    state.orderTabObj.orderActive = 0
  },
  // 修改orderListTabs的订单类型的头Active
  setOrderTab(state, orderActive) {
    state.orderTabObj.orderActive = orderActive
  },
  setBacktrue(state) {
    state.back_page = true
  },
  setBackfalse(state) {
    state.back_page = false
  },
}
const actions = {
  // 修改orderListTabs的险种类型的头Active
  editInsuranceTypeTab({ commit }, insuranceType) {
    commit('setInsuranceTypeTab', insuranceType)
  },
  // 修改orderListTabs的订单类型的头Active
  editOrderTab({ commit }, orderActive) {
    commit('setOrderTab', orderActive)
  },
}
export default {
  namespaced: true,
  state,
  mutations,
  actions,
}
