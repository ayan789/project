import { post } from "../axios";

//营销活动-》获取活动列表
export const getActivityList = params => {
  return post(process.env.VUE_APP_CSA_URL + '/activity/activityList', params)
};
//营销活动-》活动详情
export const getActivityDetails = params => {
  return post(process.env.VUE_APP_CSA_URL + '/activity/activityDetails', params)
};
//营销活动-》活动报名
export const getActivityApply = params => {
  return post(process.env.VUE_APP_CSA_URL + '/activity/activityApply', params)
};

//营销活动-》查询我的福利
export const getAwardList = params => {
  return post(process.env.VUE_APP_CSA_URL + '/activity/awardList', params)
};
//营销活动-》领取奖励
export const getAward = params => {
  return post(process.env.VUE_APP_CSA_URL + '/activity/getAward', params)
};
//营销活动-》活动轮播图片
export const getPicture = params => {
  return post(process.env.VUE_APP_CSA_URL + '/activity/getBannerPicture', params)
};
//营销活动-》啄木鸟反馈意见
export const suggest = params => {
  // return post(process.env.VUE_APP_CST_URL + '/feedback/submit', params)
  return post(process.env.VUE_APP_CSU_URL + '/qds/feedBack', params)
};
