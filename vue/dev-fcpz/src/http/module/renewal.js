/**
 * 续保模块api
 */
import { post } from "../axios";
//获取续保任务信息
export const queryRenewalTaskInfo = params =>
  post(process.env.VUE_APP_CSR_URL + "/renewal/queryRenewalTaskInfo", params, {
    getResponseHeader: true
  });
//获取保单信息
export const queryRenewalPolicyInfo = params =>
  post(process.env.VUE_APP_CSR_URL + "/renewal/queryRenewalPolicyInfo", params);

//认领/推电销
export const renewalClaim = params =>
  post(process.env.VUE_APP_CSR_URL + "/renewalClaim/renewalClaim", params);

//获取脱保原因
export const getRenewalExpireReason = params =>
  post(process.env.VUE_APP_CSR_URL + "/renewal/getRenewalExpireReason", params);

//更新脱保原因
export const updateUnRenewReason = params =>
  post(
    process.env.VUE_APP_CSR_URL + "/renewalClaim/updateRenewalExpireReason",
    params
  );

//分页查询跟踪记录列表
export const renewTraceRecordQuery = params =>
  post(
    process.env.VUE_APP_CSR_URL +
      "/renewTraceRecordQuery/renewTraceRecordQuery",
    params
  );

//新增跟踪记录
export const insertRenewTraceRecord = params =>
  post(
    process.env.VUE_APP_CSR_URL +
      "/renewTraceRecordQuery/insertRenewTraceRecord",
    params
  );

//批量删除跟踪记录
export const deleteRenewTraceRecordList = params =>
  post(
    process.env.VUE_APP_CSR_URL +
      "/renewTraceRecordQuery/deleteRenewTraceRecordList",
    params
  );

//批量修改跟踪记录
export const updateRenewalTraceRecordList = params =>
  post(
    `${process.env.VUE_APP_CSR_URL}/renewTraceRecordQuery/updateRenewalTraceRecordList`,
    params
  );
//查询保单轨迹
export const operateTrajectoryQuery = params =>
  post(
    process.env.VUE_APP_CSR_URL + "/renewalClaim/operateTrajectoryQuery",
    params
  );

//获取续保报价信息
export const queryRenewalQuoteInfo = params =>
  post(process.env.VUE_APP_CSR_URL + "/renewal/queryRenewalQuoteInfo", params);

//生成技术书
export const createProposa = params =>
  post(
    `${process.env.VUE_APP_CSR_URL}/renewal/createProposa
`,
    params
  );
