//客户管理接口
import { post } from "../axios";
//获取首页 banner
export const getContentBanner = params => {
  return post(process.env.VUE_APP_CST_URL + "/content/banner", params);
};
//查询跟进提醒列表
export const getQueryRemindTaskList = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryRemindTaskList",
    params
  );
};
//查询权益、待注册提醒详情
export const getQueryRightsRemind = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryRightsRemind",
    params
  );
};
// 查询理赔进度
export const getQueryIndemnifyDetails = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryIndemnifyDetails",
    params
  );
};
// 影像上传
export const uploadImages = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/customerManage/uploadImage",
    params
  );
};

// 影像查询
export const queryImages = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/customerManage/queryImage",
    params
  );
};

//客户服务--理赔查询
export const getQueryIndemnifyInfos = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryIndemnifyInfo",
    params
  );
};
//查询客户互动纪录
export const getCustomerInteractions = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/interaction/queryCustomerInteraction",
    params
  );
};
//新增或修改互动纪录
export const addOrUpdateInteractions = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/interaction/addOrUpdatefInteraction",
    params
  );
};

//生日祝福：获取手势图片资源数据
export const getQueryConfigPhotoList = params => {
  return post(process.env.VUE_APP_CST_URL + "/content/card", params);
};

//新增/编辑个人客户
export const addOrUpdateSinglecustomeromer = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/customer/addOrUpdateCustomer",
    params
  );
};
//新增/编辑团队客户
export const addOrUpdateTeamCustomer = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/customer/addOrUpdateTeamCustomer",
    params
  );
};
//查询个人客户信息
export const getqueryCustInfo = params => {
  return post(process.env.VUE_APP_CSC_URL + "/customer/queryCustInfo", params);
};
//删除客户信息
export const deleteCustomer = params => {
  return post(process.env.VUE_APP_CSC_URL + "/customer/deleteCustomer", params);
};
//查询团体客户信息
export const queryTeamCustInfo = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/customer/queryTeamCustomerDetails",
    params
  );
};
//查询客户详情个人-里面的客户信息
export const getQueryCustomerInformation = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryCustomerInformation",
    params
  );
};
//客户列表搜索
export const getQuerycustomeromerList = (params, loading) => {
  return post(
    process.env.VUE_APP_CSC_URL + "/customer/queryCustomerList",
    params,
    { loading: loading.loading }
  );
};
//客户授权
export const getCustomerAuthorization = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/customerAuthorization",
    params
  );
};
//获取所有车辆
export const getQueryViolationCarList = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryViolationCarList",
    params
  );
};
//获取违章记录
export const getQueryViolationRecord = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryViolationRecord",
    params
  );
};
//客户动态-根据日期查当天动态
export const getQueryCustomerDynamic = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryCustomerDynamic",
    params
  );
};
//客户动态-所有有权益的日期列表
export const queryCustomerDynamicDate = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryCustomerDynamicDate",
    params
  );
};
//客户权益
export const getQueryCustomerInterests = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryCustomerInterests",
    params
  );
};
// 查询字典配置信息
export const getQueryDicConfigInfo = params => {
  return post(process.env.VUE_APP_CSC_URL + "/dict/queryDicConfigInfo", params, { loading: false });
};
//星级权益
export const getQueryCustomerLevel = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryCustomerLevel",
    params
  );
};
//客户当前积分
export const getQueryCustomerInquiry = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryCustomerInquiry",
    params
  );
};
//客户积分详情
export const getQueryCoreInfo = params => {
  return post(process.env.VUE_APP_CSC_URL + "/custServer/queryCoreInfo", params);
};
//保险信息
export const getQueryInsuranceList = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryInsuranceList",
    params
  );
};
//查询保单号列表
export const getQueryPolicyNoList = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryPolicyNoList",
    params
  );
};
//根据保单号查询保单列表
export const getQueryIndemnifyInfo = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryIndemnifyInfo",
    params
  );
};
//我的-保单查询
export const getSelectUserPolicyNo = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/userPolicyNo/selectUserPolicyNo",
    params
  );
};
//上传图片
export const uploadImage = params => {
  return post(process.env.VUE_APP_CSC_URL + "/image/uploadImage", params);
};
//下载图片
export const getQueryImage = params => {
  return post(process.env.VUE_APP_CSC_URL + "/image/queryImage", params);
};
//推荐产品方案已废弃
// export const getListRecommendProduct = params => {
//   return post(
//     process.env.VUE_APP_CST_URL + "/recommend/listRecommendProduct",
//     params
//   );
// };
//推荐产品方案
export const getIndexHp = params => {
  return post(
    process.env.VUE_APP_CST_URL + "/content/indexHp ",
    params
  );
};
//互动记录删除
export const deleteInteractionClick = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/interaction/deleteInteraction",
    params
  );
};
//邀请客户列表
export const getqQeryUnregistList = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/queryUnregistList",
    params
  );
};
//完成
export const doneTaskClick = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/custServer/doneTask",
    params
  );
};
//客户列表保单客户拨打电话
export const getQueryCustPhone = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/customer/queryCustPhone",
    params
  );
};
//影像上传
export const imageUpload = params => {
  return post(
    process.env.VUE_APP_CST_URL + "/image/imageUpload",
    params
  );
};
//影像查询
export const imageSee = params => {
  return post(
    process.env.VUE_APP_CST_URL + "/image/imageSee",
    params
  );
};
//短链接
export const getShareUrl = params => {
  return post(
    process.env.VUE_APP_CSN_URL + "/shortUrl/getShortUrl",
    params
  );
};
//获取互动记录的时间
export const queryCustomerInteractionDate = params => {
  return post(
    process.env.VUE_APP_CSC_URL + "/interaction/queryCustomerInteractionDate",
    params
  );
};
