/**
 * 订单接口统一管理
 */
import { post } from "../axios";

// 门店订单列表查询
export const storeOrderList = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/StoreOrder/storeOrderListQuery", params);

// 订单详情
export const storeOrderDetail = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/StoreOrder/storeOrderDetail", params);

// 订单转发支付查询信息
export const forwardPayProductInsureInfo = (params) =>
  post(
    process.env.VUE_APP_CSN_URL + "/StoreOrder/forwardPayProductInsureInfo",
    params
  );

// 门店保单回写
export const certiNotice = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/StoreOrder/certiNotice", params);

//非车--删除订单
export const deleteStoreOrder = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/StoreOrder/deleteStoreOrder", params);

//查询车险
export const getOrderList = (params) =>
  post(process.env.VUE_APP_CSV_URL + "/order/orderListQuery", params);

// 车险订单详情
export const getDetails = (params) =>
  post(process.env.VUE_APP_CSV_URL + "/order/orderDetailsInquery", params);

export const getPropertList = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/StoreOrder/storeOrderListQuery", params);
//   复制投保单号
export const getoddNumb = (params) =>
  post(process.env.VUE_APP_CSV_URL + "/policy/copyPolicy", params);

// 复制投保单号
export const oneClickRenewal = (params) =>
  post(process.env.VUE_APP_CSV_URL + "/policy/oneClickRenewal", params);

// 核保查询 废弃
export const getPolicyInfomation = (params) =>
  post(process.env.VUE_APP_CSV_URL + "/proposal/getStatus", params);

// 备注接口
export const getRemark = (params) => {
  return post(process.env.VUE_APP_CSV_URL + "/order/updateOrder", params);
};
// 车险删除订单
export const deleteOrder = (params) => {
  return post(process.env.VUE_APP_CSV_URL + "/order/deleteOrder", params);
};
// 获取验证码
export const getVerifyCode = (params) => {
  return post(process.env.VUE_APP_CSV_URL + "/verifyCode/send", params);
};
// 保存验证码verifyCode/update
export const updateVerifyCode = (params) => {
  return post(process.env.VUE_APP_CSV_URL + "/verifyCode/update", params);
};
// 车险影像上传
export const imageUpdate = (params) => {
  return post(process.env.VUE_APP_CST_URL + "/image/imageUpload", params);
};
// 车险检验授权
export const checkAuthorization = (params) => {
  return post(process.env.VUE_APP_CSV_URL + "/ec/check/authorization", params);
};
// 车险发起授权
export const launchAuthorization = (params) => {
  return post(
    process.env.VUE_APP_CSV_URL + "/ec/initiate/authorization",
    params
  );
};
// 车险客户授权
export const customerAuthorization = (params) => {
  return post(process.env.VUE_APP_CSV_URL + "/ec/authorization", params);
};
// 电子保单下载 /electronicPolicy
export const electronicPolicy = (params) =>
  post(process.env.VUE_APP_CST_URL + "/carCode/electronicPolicy", params);

// 电子发票查询 /carCode/einvoiceInquiry
export const einvoiceInquiry = (params) =>
  post(process.env.VUE_APP_CST_URL + "/carCode/einvoiceInquiry", params);

// 发送电子保单 /sendElectronicPolicy
export const sendElectronicPolicy = (params) =>
  post(process.env.VUE_APP_CST_URL + "/carCode/sendElectronicPolicy", params);

// 发送电子发票 /sendElectronicPolicy
export const sendElectronicPolicy1 = (params) =>
  post(process.env.VUE_APP_CST_URL + "/carCode/sendInvoice", params);

// 电子发票开具申请接口 /carCode/electronicInvoicing
export const electronicInvoicing = (params) =>
  post(process.env.VUE_APP_CST_URL + "/carCode/electronicInvoicing", params);

// 非车影像上传  /image/imageUpload
export const imageUpload = (params) =>
  post(process.env.VUE_APP_CST_URL + "/image/imageUpload", params);

// 财产险--修改为待核保状态 /carCode/underwritingStatus
export const underwritingStatus = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/carCode/underwritingStatus", params);

// 财产险--校验影像类型 /image/imageCheck
export const imageCheck = (params) =>
  post(process.env.VUE_APP_CST_URL + "/image/imageCheck", params);

// 电子发票短连接查询 --新
export const toInvoiceLinkQuery = (params) =>
  post(process.env.VUE_APP_CST_URL + "/carCode/toInvoiceLinkQuery", params);

// 电子发票申请  --新
export const eInvoiceApplication = (params) =>
  post(process.env.VUE_APP_CST_URL + "/carCode/einvoiceInquiry", params);

// 非车险（包含动态配置）-非车险（包含动态配置）-- 订单列表查询-new
export const orderList = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/order/orderList", params);

//非车险--动态配置的专用订单详情 order/orderDetail
export const getOrderDetail = (params) =>
  post(process.env.VUE_APP_CSN_URL + "/order/orderDetail", params);
