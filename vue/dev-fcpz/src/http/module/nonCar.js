/*
 * @Description: 非车模块统一接口管理
 * @Date: 2020-03-24 11:10:56
 * @Author: zhangminjie
 * @LastEditTime: 2020-06-28 14:15:45
 * @FilePath: \nonCarCongfig\src\http\module\nonCar.js
 */
import { post } from "../axios";

// 产品详情查询
export const storeQueryFormula = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/noncar/storeQueryFormula", params);

//货运无忧下单 /noncar/placeOrder
export const placeOrder = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/noncar/placeOrder", params);

// 核保 /carCode/underwriting
export const underwriting = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/underwriting", params);

// 影像上传 /carCode/uploadImage
export const uploadImage = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/uploadImage", params);

// 签名发送手机号验证码
export const signSendSMS = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/signSendSMS", params);

// 签名发送手机号验证码
export const signSendSMSTwo = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/signSendSMSTwo", params);

// 配置签名条款
export const initSign = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/sign/initSign", params);

//上传签名生成投保确认书
export const uploadSignBook = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/uploadSignBook", params);

//上传签名生成投保确认书
export const upload = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/sign/upload", params);

// 查询影像 /carCode/h5imgQueryDownService
export const h5imgQueryDownService = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/h5imgQueryDownService", params);

// 确定支付页面
export const prePayMent = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/prePayMent", params);

// 获取支付页面
export const getPayUrl = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/getPayUrl", params);

// 支付号申请 /salesResponsibility/applyPay
export const applyPay = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/applyPay", params);

// 支付号申请 /salesResponsibility/applyPay
export const applyPayTwo = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/applyPayTwo", params);

// 状态回显
export const payResultConfirm = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/payResultConfirm", params);
// 获取影像上传连接地址
export const getUploadUrl = (params) =>
  post(process.env.VUE_APP_CST_URL + "/image/imageUpload", params);
// 影像文件上传情况校验
export const imageCheck = (params) =>
  post(process.env.VUE_APP_CST_URL + "/image/imageCheck", params);
// 同步影像上传状态
export const updateUnderwritingStatus = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/underwritingStatus", params);

// 获取转发支付URL /carCode/getForwardUrl
export const getForwardUrl = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/getForwardUrl", params);

//货运无忧--- 暂存下单接口 /StoreOrder/insureStatusZc
export const insureStatusZc = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/StoreOrder/insureStatusZc", params);

// 货运无忧--根据订单号查询暂存订单 /StoreOrder/queryZc
export const queryZc = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/StoreOrder/queryZc", params);

// 查看商业计划书-星级权益
export const businessPlan = (params) =>
  post(process.env.VUE_APP_CSV_URL + "/customer/getOrderIntegral", params);

// 获取动态表单-信息录入
export const getPlanDynamicForms = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/planForm/getPlanDynamicForms", params);

// 获取动态表单-下单|暂存
export const createFromPlaceOrder = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/order/placeOrder", params);

//非车配置接口--获取方案详情 getPlanProductFormulas
export const getPlanElementDetail = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/planForm/getPlanElementDetail", params);

//非车配置接口--获取方案详情 getPlanProductFormulas
export const PaymentInformation = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/PaymentInformation", params);

// 非车配置接口 --- 核保 /carCode/underwritingTwo
export const underwritingTwo = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/underwritingTwo", params);

// 非车配置接口 --- 试算接口 计算保费 /planForm/trial
export const trial = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/planForm/trial", params);
