/**
 * 保险产品api
 */
import { post } from '../axios'
//获取产品信息数据
export const queryProduct = (params) =>
  // post(process.env.VUE_APP_CSN_URL + '/noncar/productQuery', params)
  post(process.env.VUE_APP_CSN_URL + '/product/productList', params)

//保单查询
export const querylist = (params) =>
  post(process.env.VUE_APP_CSU_URL + '/userPolicyNo/selectUserPolicyNo', params)
//保单详情查询
export const queryDetail = (params) =>
  post(
    process.env.VUE_APP_CSU_URL + '/userPolicyNo/selectUserPolicyNoInfo',
    params
  )
//承诺书
export const queryBook = (params) =>
  post(
    process.env.VUE_APP_CSU_URL + '/userPolicyNo/selectUserPolicyNoInfo',
    params
  )
//获取扫描车辆默认信息
export const getVehicleDefaultInfo = (params) =>
  post(process.env.VUE_APP_CSV_URL + '/quote/getDefaultInfo', params)
//提交扫描车辆信息
export const submitVehicleDefaultInfo = (params) =>
  post(process.env.VUE_APP_CSV_URL + '/quote/quickOrder', params)
