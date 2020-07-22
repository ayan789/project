/**
 * api接口统一管理
 */
import { get, post } from "../axios";
//个人业绩查询

export const getPerformanceByQueryType = params =>
  post(
    process.env.VUE_APP_CSP_URL + "/progress/getPerformanceByQueryType",
    params,
    { loading: false }
  );

//业绩高级查询

export const getPerformance = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/getPerformance", params);

//月季度达成进度

export const getMonthlyQuarterlyProgress = params =>
  post(
    process.env.VUE_APP_CSP_URL +
      "/salesResponsibility/getMonthlyQuarterlyProgress",
    params
  );

//年度达成进度

export const getAnnualProgress = params =>
  post(
    process.env.VUE_APP_CSP_URL + "/salesResponsibility/getAnnualProgress",
    params
  );

//销售行动力评估

export const getAssess = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/getAssess", params);

//业绩排名

export const getpersAndTeamRanking = params =>
  post(
    process.env.VUE_APP_CSP_URL + "/salesResponsibility/getpersAndTeamRanking",
    params
  );

//查询团队

export const teamQuery = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/teamQuery", params);

//代理人业绩查询/policyInfo/agentPremiumQuery
export const agentPremiumQuery = params =>
  post(process.env.VUE_APP_CSP_URL + "/policyInfo/agentPremiumQuery", params);
/**
 * 是否已设置薪酬查询密码
 * @param {*} params
 */
export const isHaveSetSalaryPwd = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/isHaveSetSalaryPwd", params);

/**
 * 获取图形验证码
 * @param {*} params
 */
export const captcha = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/captcha", params);

/**
 * 发送短信验证码
 * @param {*} params
 */
export const sendSmsCode = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/smscode", params);
/**
 * 薪酬发送短信验证码
 * @param {*} params
 */
export const sendSalarySMS = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/sendSalarySMS", params);
/**
 * 薪酬查询登录
 * @param {*} params
 */
export const salaryQueryLogin = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/salelawSalaryLogin", params);

/**
 * 薪酬查询密码修改
 * @param {*} params
 */
export const salaryQueryChangePassword = params =>
  post(
    process.env.VUE_APP_CSP_URL + "/progress/salelawSalaryUpdatePwd",
    params
  );
/**
 * 薪酬查询
 * @param {*} params
 */
export const salaryQuery = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/salaryQuery", params);

/**
 * KPI考核查询
 * @param {*} params
 */
export const kpiQuery = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/getKPI", params);

/**
 * 职级考核查询
 * @param {*} params
 */
export const checkRank = params =>
  post(process.env.VUE_APP_CSP_URL + "/progress/checkRank", params);
/**
 * 责任书-自动分配
 * @param {*} params
 */
export const responsibilityAutoAllocation = params =>
  post(process.env.VUE_APP_CSP_URL + "/salesResponsibility/distribute", params);

/**
 * 责任书-变更
 * @param {*} params
 */
export const responsibilityEdit = params =>
  post(
    process.env.VUE_APP_CSP_URL +
      "/salesResponsibility/editSalesResponsibility",
    params
  );

/**
 * 责任书-保存
 * @param {*} params
 */
export const responsibilitySave = params =>
  post(
    process.env.VUE_APP_CSP_URL +
      "/salesResponsibility/saveSalesResponsibility",
    params
  );
/**
 * 责任书-判断是否今年第一次
 *
 */
export const firstListQuery = params =>
  post(
    process.env.VUE_APP_CSP_URL +
      "/salesResponsibility/hasResponsibilityStatus",
    params
  );
/**
 * 责任书-查询列表
 * @param {*} params
 */
export const responsibilityListQuery = params =>
  post(
    process.env.VUE_APP_CSP_URL + "/salesResponsibility/getSalesResponsibility",
    params
  );

/* *
 *手续费提现======================================
 */

//佣金页面初始化查询
export const planFeeInquiry = params =>
  post(process.env.VUE_APP_CSP_URL + "/commonAgent/planFeeInquiry", params);

//当日手续费明细查询
export const planFeeDetailInquiry = params =>
  post(
    process.env.VUE_APP_CSP_URL + "/commonAgent/planFeeDetailInquiry",
    params
  );

//查询用户的提现配置
export const queryWithdrawalConfig = params =>
  post(
    process.env.VUE_APP_CSP_URL + "/commonAgent/queryWithdrawalConfig",
    params
  );

//修改提现设置
export const updateConfig = params =>
  post(process.env.VUE_APP_CSP_URL + "/commonAgent/updateConfig", params);

//提现页面初始化列表
export const queryPolicyList = params =>
  post(process.env.VUE_APP_CSP_URL + "/commonAgent/queryPolicyList", params);

//查询提现明细记录
export const feePayDetailQuery = params =>
  post(process.env.VUE_APP_CSP_URL + "/commonAgent/feePayDetailQuery", params);

// 代理人手续费提現
export const billPackToFK = params =>
  post(process.env.VUE_APP_CSP_URL + "/commonAgent/billPackToFK", params);

//代理人注册信息查询
export const queryInviteUserDetail = params =>
  post(
    process.env.VUE_APP_CSP_URL + "/commonAgent/queryInviteUserDetail",
    params
  );

//消息提箱
export const responsibilityNotice = params =>
  post(
    process.env.VUE_APP_CSP_URL + "/salesResponsibility/responsibilityNotice",
    params
  );

// 签名上传/s3/upload
export const signatureUpload = params =>
  post(
    process.env.VUE_APP_CST_URL + "/s3/upload",
    params,
    { loading: true },
    { contentType: "multipart/form-data" }
  );

// 签名获取
// 签名上传/s3/upload
export const getSignature = params =>
  get(process.env.VUE_APP_CST_URL + "/s3/browse/" + params + "");
