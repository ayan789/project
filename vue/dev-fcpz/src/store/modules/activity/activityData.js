/**
 * Created by 曹征前
 */
import {
  getActivityList, //营销活动-》获取活动列表
  getActivityDetails, //营销活动-》活动详情
  getActivityApply, //营销活动-》活动报名
  getAwardList, //营销活动-》查询我的福利
  getAward, //营销活动-》领取奖励
  getPicture //营销活动-》轮播图列表
} from "../../../http/module/activityApi.js";

const state = {
  activityList: {}, //营销活动-》获取活动列表
  activityDetails: {}, //营销活动-》活动详情
  activityApply: {}, //营销活动-》活动报名
  awardList: {}, //营销活动-》查询我的福利
  award: {}, //营销活动-》领取奖励
  picture: {}, //营销活动-》活动轮播图片
  setActiveData:{} //营销活动-》存放详情页面的活动号+业务员号
};
const mutations = {
  //营销活动-》获取活动列表
  getActivityList: (state, data) => {
    state.activityList = data.data && data.data;
  },
  //营销活动-》活动详情
  getActivityDetails: (state, data) => {
    state.activityDetails = data.data && data.data;
  },
  //营销活动-》活动报名
  getActivityApply: (state, data) => {
    state.activityApply = data && data;
  },
  //营销活动-》查询我的福利
  getAwardList: (state, data) => {
    state.awardList = data.data && data.data;
  },
  //营销活动-》领取奖励
  getAward: (state, data) => {
    state.award = data;
  },
  //营销活动-》活动轮播图片
  getPicture: (state, data) => {
    state.picture = data;
  },
  //营销活动-》存放详情页面的活动号
  setActiveData: (state, data) => {
    state.setActiveData = data;
  },
};

const actions = {
  //营销活动-》获取活动列表
  async getActivityList({ commit }, promise) {
    let res = await getActivityList(promise);
    // res= {"code":"1","data":{"msg":"查询成功","code":1,"activityResponseList":[{"id":169,"activityCode":"456aaa","isRenewalActivity":1,"renewalPercentStart":"2020-03-07 16:51:09","renewalPercentEnd":"2020-03-07 16:49:17","activityName":"活动名称","activityStart":"2020-03-07 16:49:36","activityEnd":"2020-03-25 16:49:39","issuedStart":"2020-03-07 16:49:42","issuedEnd":"2020-04-07 16:49:45","activitySummarize":"aada","putProduction":"","sameTimeInsurance":"","sameTimeCount":"","surroundTimeInsurance":"","surroundTimeCount":"","targetInsurance":"","activityBudget":"","activityDetailsSource":"","activityDetails":"","createTime":"2020-03-09 10:42:26","activityCreator":"","organizationCode":"","organizationName":"","activityStatus":2,"checkStatus":"","isPutList":"","isNeedApply":1,"applyStartDate":"2020-03-09 10:42:57","applyEndDate":"2020-03-25 10:43:09","behaviorStatistics":"","autoAdd":"","activityPicture":"","isGet":0,"notGet":0,"amount":"","maxAmount":"","awardName":"","maxAwardName":"","awardForm":12,"awardType":1,"activityPolicyAwardPo":"","isGetAwardList":"","notGetAwardList":"","isApply":0},{"id":263,"activityCode":"123qq","isRenewalActivity":1,"renewalPercentStart":"2020-03-07 16:51:09","renewalPercentEnd":"2020-03-07 16:49:17","activityName":"这是名称","activityStart":"2020-03-07 16:49:36","activityEnd":"2020-03-25 16:49:39","issuedStart":"2020-03-07 16:49:42","issuedEnd":"2020-03-20 16:49:45","activitySummarize":"aada","putProduction":"","sameTimeInsurance":"","sameTimeCount":"","surroundTimeInsurance":"","surroundTimeCount":"","targetInsurance":"","activityBudget":"","activityDetailsSource":"","activityDetails":"","createTime":"2020-03-13 15:23:41","activityCreator":"","organizationCode":"","organizationName":"","activityStatus":2,"checkStatus":"","isPutList":"","isNeedApply":1,"applyStartDate":"2020-03-07 21:26:45","applyEndDate":"2020-03-15 21:26:49","behaviorStatistics":"","autoAdd":1,"activityPicture":"","isGet":2,"notGet":0,"amount":"","maxAmount":"","awardName":"","maxAwardName":"","awardForm":12,"awardType":1,"activityPolicyAwardPo":"","isGetAwardList":"","notGetAwardList":"","isApply":0},{"id":264,"activityCode":"123aa","isRenewalActivity":1,"renewalPercentStart":"2020-03-07 16:51:09","renewalPercentEnd":"2020-03-07 16:49:17","activityName":"这是名称","activityStart":"2020-03-07 16:49:36","activityEnd":"2020-03-25 16:49:39","issuedStart":"2020-03-07 16:49:42","issuedEnd":"2020-03-20 16:49:45","activitySummarize":"aada","putProduction":"","sameTimeInsurance":"","sameTimeCount":"","surroundTimeInsurance":"","surroundTimeCount":"","targetInsurance":"","activityBudget":"","activityDetailsSource":"","activityDetails":"","createTime":"2020-03-13 15:23:41","activityCreator":"","organizationCode":"","organizationName":"","activityStatus":2,"checkStatus":"","isPutList":"","isNeedApply":1,"applyStartDate":"2020-03-07 21:26:45","applyEndDate":"2020-03-15 21:26:49","behaviorStatistics":"","autoAdd":2,"activityPicture":"","isGet":"","notGet":"","amount":10000,"maxAmount":200,"awardName":"","maxAwardName":"","awardForm":34,"awardType":2,"activityPolicyAwardPo":"","isGetAwardList":"","notGetAwardList":"","isApply":0},{"id":295,"activityCode":"0000000","isRenewalActivity":1,"renewalPercentStart":"2020-03-07 16:51:09","renewalPercentEnd":"2020-03-07 16:49:17","activityName":"这是名称","activityStart":"2020-03-07 16:49:36","activityEnd":"2020-03-25 16:49:39","issuedStart":"2020-03-07 16:49:42","issuedEnd":"2020-03-20 16:49:45","activitySummarize":"aada","putProduction":"","sameTimeInsurance":"","sameTimeCount":"","surroundTimeInsurance":"","surroundTimeCount":"","targetInsurance":"","activityBudget":"","activityDetailsSource":"","activityDetails":"","createTime":"2020-03-13 15:23:41","activityCreator":"","organizationCode":"","organizationName":"","activityStatus":2,"checkStatus":"","isPutList":"","isNeedApply":1,"applyStartDate":"2020-03-07 21:26:45","applyEndDate":"2020-03-15 21:26:49","behaviorStatistics":"","autoAdd":1,"activityPicture":"","isGet":1,"notGet":0,"amount":"","maxAmount":"","awardName":"","maxAwardName":"","awardForm":12,"awardType":1,"activityPolicyAwardPo":"","isGetAwardList":"","notGetAwardList":"","isApply":0},{"id":350,"activityCode":"123zz","isRenewalActivity":1,"renewalPercentStart":"2020-03-07 16:51:09","renewalPercentEnd":"2020-03-07 16:49:17","activityName":"这是名称","activityStart":"2020-03-07 16:49:36","activityEnd":"2020-03-25 16:49:39","issuedStart":"2020-03-07 16:49:42","issuedEnd":"2020-03-20 16:49:45","activitySummarize":"aada","putProduction":"","sameTimeInsurance":"","sameTimeCount":"","surroundTimeInsurance":"","surroundTimeCount":"","targetInsurance":"","activityBudget":"","activityDetailsSource":"","activityDetails":"","createTime":"2020-03-13 15:23:41","activityCreator":"","organizationCode":"","organizationName":"","activityStatus":2,"checkStatus":"","isPutList":"","isNeedApply":1,"applyStartDate":"2020-03-07 21:26:45","applyEndDate":"2020-03-15 21:26:49","behaviorStatistics":"","autoAdd":1,"activityPicture":"","isGet":0,"notGet":5,"amount":"","maxAmount":"","awardName":"","maxAwardName":"","awardForm":12,"awardType":1,"activityPolicyAwardPo":"","isGetAwardList":"","notGetAwardList":"","isApply":0},{"id":351,"activityCode":"123xx","isRenewalActivity":1,"renewalPercentStart":"2020-03-07 16:51:09","renewalPercentEnd":"2020-03-07 16:49:17","activityName":"这是名称","activityStart":"2020-03-07 16:49:36","activityEnd":"2020-03-25 16:49:39","issuedStart":"2020-03-07 16:49:42","issuedEnd":"2020-03-20 16:49:45","activitySummarize":"aada","putProduction":"","sameTimeInsurance":"","sameTimeCount":"","surroundTimeInsurance":"","surroundTimeCount":"","targetInsurance":"","activityBudget":"","activityDetailsSource":"","activityDetails":"","createTime":"2020-03-13 15:23:41","activityCreator":"","organizationCode":"","organizationName":"","activityStatus":2,"checkStatus":"","isPutList":"","isNeedApply":1,"applyStartDate":"2020-03-07 21:26:45","applyEndDate":"2020-03-15 21:26:49","behaviorStatistics":"","autoAdd":1,"activityPicture":"","isGet":0,"notGet":5,"amount":"","maxAmount":"","awardName":"","maxAwardName":"","awardForm":12,"awardType":1,"activityPolicyAwardPo":"","isGetAwardList":"","notGetAwardList":"","isApply":0}]},"msg":""}
    if (res) {
      commit("getActivityList", res);
    }
  },
  //营销活动-》活动详情
  async getActivityDetails({ commit }, promise) {
    let res = await getActivityDetails(promise);
    // let desc="活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情活动详情"
    //   res ={"code":"1","data":{"msg":"查询成功","code":1,"activityResponse":{"id":263,"activityCode":"123qq","isRenewalActivity":1,"renewalPercentStart":"2020-03-07 16:51:09","renewalPercentEnd":"2020-03-07 16:49:17","activityName":"这是名称","activityStart":"2020-03-07 16:49:36","activityEnd":"2020-03-25 16:49:39","issuedStart":"2020-03-07 16:49:42","issuedEnd":"2020-04-20 16:49:45","activitySummarize":"aada","putProduction":null,"sameTimeInsurance":null,"sameTimeCount":null,"surroundTimeInsurance":null,"surroundTimeCount":null,"targetInsurance":null,"activityBudget":null,"activityDetailsSource":null,"activityDetails":desc,"createTime":"2020-03-13 15:23:41","activityCreator":"","organizationCode":"","organizationName":"","activityStatus":2,"checkStatus":null,"isPutList":null,"isNeedApply":1,"applyStartDate":"2020-03-07 21:26:45","applyEndDate":"2020-04-15 21:26:49","behaviorStatistics":null,"autoAdd":1,"activityPicture":null,"isGet":6,"notGet":3,"amount":null,"maxAmount":null,"awardName":null,"maxAwardName":null,"awardForm":12,"awardType":1,"activityPolicyAwardPo":null,"isGetAwardList":[{"id":1,"policyCode":"5345","salesmanCode":"0000000000","activityCode":"123qq","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":null,"renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07 13:52:42","getAwardTime":"2020-03-21 17:39:00","statisticsDate":"2020-03-06 13:52:51","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":27,"policyCode":"5345","salesmanCode":"0000000000","activityCode":"123qq","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":null,"renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07 13:52:42","getAwardTime":"2020-03-21 17:39:24","statisticsDate":"2020-03-06 13:52:51","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":28,"policyCode":"5345","salesmanCode":"0000000000","activityCode":"123qq","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":null,"renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07 13:52:42","getAwardTime":"2020-03-23 17:12:13","statisticsDate":"2020-03-06 13:52:51","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":29,"policyCode":"5345","salesmanCode":"0000000000","activityCode":"123qq","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":null,"renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07 13:52:42","getAwardTime":"2020-03-23 17:12:29","statisticsDate":"2020-03-06 13:52:51","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":30,"policyCode":"5345","salesmanCode":"0000000000","activityCode":"123qq","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":null,"renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07 13:52:42","getAwardTime":"2020-03-23 17:57:30","statisticsDate":"2020-03-06 13:52:51","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":31,"policyCode":"5345","salesmanCode":"0000000000","activityCode":"123qq","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":null,"renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07 13:52:42","getAwardTime":"2020-03-24 10:28:10","statisticsDate":"2020-03-06 13:52:51","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"}],"notGetAwardList":[{"id":67,"activityCode":"123qq","awardType":1,"awardForm":1,"isBreakLimit":2,"maximumAmount":22220.0,"getWay":1,"awardLevel":1,"amount":20,"awardName":"111","percent":50,"awardCount":5,"autoSupplyCount":3,"everySupplyCount":4,"levelMaxCount":100,"levelMaxAmount":100000,"isCanGetCount":2,"isPast":1,"activityStart":null,"activityEnd":null,"autoAdd":1,"waitGetCount":3},{"id":67,"activityCode":"123qq","awardType":1,"awardForm":1,"isBreakLimit":2,"maximumAmount":22220.0,"getWay":1,"awardLevel":1,"amount":30,"awardName":"222","percent":50,"awardCount":4,"autoSupplyCount":3,"everySupplyCount":4,"levelMaxCount":100,"levelMaxAmount":100000,"isCanGetCount":3,"isPast":1,"activityStart":null,"activityEnd":null,"autoAdd":1,"waitGetCount":3},{"id":67,"activityCode":"123qq","awardType":1,"awardForm":1,"isBreakLimit":2,"maximumAmount":22220.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"333","percent":50,"awardCount":5,"autoSupplyCount":3,"everySupplyCount":4,"levelMaxCount":100,"levelMaxAmount":100000,"isCanGetCount":1,"isPast":1,"activityStart":null,"activityEnd":null,"autoAdd":1,"waitGetCount":3},{"id":67,"activityCode":"123qq","awardType":1,"awardForm":1,"isBreakLimit":2,"maximumAmount":22220.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"444","percent":50,"awardCount":5,"autoSupplyCount":3,"everySupplyCount":4,"levelMaxCount":100,"levelMaxAmount":100000,"isCanGetCount":2,"isPast":1,"activityStart":null,"activityEnd":null,"autoAdd":1,"waitGetCount":3},{"id":67,"activityCode":"123qq","awardType":1,"awardForm":1,"isBreakLimit":2,"maximumAmount":22220.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"234","percent":50,"awardCount":5,"autoSupplyCount":3,"everySupplyCount":4,"levelMaxCount":100,"levelMaxAmount":100000,"isCanGetCount":2,"isPast":1,"activityStart":null,"activityEnd":null,"autoAdd":1,"waitGetCount":3},{"id":67,"activityCode":"123qq","awardType":1,"awardForm":1,"isBreakLimit":2,"maximumAmount":22220.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"234","percent":50,"awardCount":5,"autoSupplyCount":3,"everySupplyCount":4,"levelMaxCount":100,"levelMaxAmount":100000,"isCanGetCount":2,"isPast":1,"activityStart":null,"activityEnd":null,"autoAdd":1,"waitGetCount":3}],"isApply":1}},"msg":""}
     if (res) {
      commit("getActivityDetails", res);
    }
  },
  //营销活动-》活动报名
  async getActivityApply({ commit }, promise) {
    let res = await getActivityApply(promise);
    // let res = {"code":"1","data":{"msg":"查询成功","code":1},"msg":"该活动不在报名时间内"}
    if (res) {
      commit("getActivityApply", res);
    }
  },
  //营销活动-》查询我的福利
  async getAwardList({ commit }, promise) {
    let res = await getAwardList(promise);
    // res = {"code":"1","data":{"msg":"查询成功","code":1,"activityResponse":{"awardForm":0,"awardType":0,"activityPolicyAwardPo":"","isGetAwardList":[{"id":1,"policyCode":"5345","salesmanCode":"0000000000","activityCode":"123qq","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityName":"看见代价昂贵的哈三国杀","activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":"","renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07T05:52:42.000+0000","statisticsDate":"2020-03-06T05:52:51.000+0000","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":15,"policyCode":"53451","salesmanCode":"0000000000","activityCode":"0000000","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":"","renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07T05:52:42.000+0000","statisticsDate":"2020-03-06T05:52:51.000+0000","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":25,"policyCode":"53452","salesmanCode":"0000000000","activityCode":"123zz","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":"","renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07T05:52:42.000+0000","statisticsDate":"2020-03-06T05:52:51.000+0000","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":26,"policyCode":"53453","salesmanCode":"0000000000","activityCode":"123xx","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":"","renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07T05:52:42.000+0000","statisticsDate":"2020-03-06T05:52:51.000+0000","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":27,"policyCode":"5345","salesmanCode":"0000000000","activityCode":"123qq","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":"","renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07T05:52:42.000+0000","statisticsDate":"2020-03-06T05:52:51.000+0000","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"},{"id":28,"policyCode":"5345","salesmanCode":"0000000000","activityCode":"123qq","awardType":1,"awardForm":1,"awardLevel":1,"isGet":1,"activityOrganization":"3100000000","salesmanName":"张三","salesmanOrganization":"3100000000","teamName":"张三团队","employeeChannel":"C0000001","orderChannel":"1203","realityPremium":180,"carAndXPremium":"","renewalPercent":0,"percentConversion":"80%","realityDate":"2020-03-07T05:52:42.000+0000","statisticsDate":"2020-03-06T05:52:51.000+0000","aheadRenewalDays":5,"amount":1000,"awardCount":1,"awardName":"电话卡"}],"notGetAwardList":[{"id":333,"activityCode":"456aaa","awardType":1,"awardForm":2,"isBreakLimit":2,"maximumAmount":2222.0,"getWay":1,"awardLevel":1,"amount":50,"awardName":"234","percent":50,"awardCount":0,"autoSupplyCount":0,"everySupplyCount":3,"levelMaxCount":10,"levelMaxAmount":10000,"isCanGetCount":1,"isPast":1},{"id":333,"activityCode":"456aaa","awardType":1,"awardForm":2,"isBreakLimit":2,"maximumAmount":2222.0,"getWay":1,"awardLevel":1,"amount":80,"awardName":"234","percent":50,"awardCount":0,"autoSupplyCount":0,"everySupplyCount":3,"levelMaxCount":10,"levelMaxAmount":10000,"isCanGetCount":1,"isPast":1},{"id":333,"activityCode":"456aaa","awardType":1,"awardForm":2,"isBreakLimit":2,"maximumAmount":2222.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"234","percent":50,"awardCount":0,"autoSupplyCount":0,"everySupplyCount":3,"levelMaxCount":10,"levelMaxAmount":10000,"isCanGetCount":1,"isPast":1},{"id":333,"activityCode":"456aaa","awardType":1,"awardForm":2,"isBreakLimit":2,"maximumAmount":2222.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"234","percent":50,"awardCount":0,"autoSupplyCount":0,"everySupplyCount":3,"levelMaxCount":10,"levelMaxAmount":10000,"isCanGetCount":1,"isPast":1},{"id":333,"activityCode":"456aaa","awardType":1,"awardForm":2,"isBreakLimit":2,"maximumAmount":2222.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"234","percent":50,"awardCount":0,"autoSupplyCount":0,"everySupplyCount":3,"levelMaxCount":10,"levelMaxAmount":10000,"isCanGetCount":1,"isPast":1},{"id":333,"activityCode":"456aaa","awardType":1,"awardForm":2,"isBreakLimit":2,"maximumAmount":2222.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"234","percent":50,"awardCount":0,"autoSupplyCount":0,"everySupplyCount":3,"levelMaxCount":10,"levelMaxAmount":10000,"isCanGetCount":1,"isPast":1},{"id":333,"activityCode":"456aaa","awardType":1,"awardForm":2,"isBreakLimit":2,"maximumAmount":2222.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"234","percent":50,"awardCount":0,"autoSupplyCount":0,"everySupplyCount":3,"levelMaxCount":10,"levelMaxAmount":10000,"isCanGetCount":1,"isPast":1},{"id":333,"activityCode":"456aaa","awardType":1,"awardForm":2,"isBreakLimit":2,"maximumAmount":2222.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"234","percent":50,"awardCount":0,"autoSupplyCount":0,"everySupplyCount":3,"levelMaxCount":10,"levelMaxAmount":10000,"isCanGetCount":1,"isPast":1},{"id":333,"activityCode":"456aaa","awardType":1,"awardForm":2,"isBreakLimit":2,"maximumAmount":2222.0,"getWay":1,"awardLevel":1,"amount":200,"awardName":"234","percent":50,"awardCount":0,"autoSupplyCount":0,"everySupplyCount":3,"levelMaxCount":10,"levelMaxAmount":10000,"isCanGetCount":1,"isPast":1}],"isGet":12,"notGet":0}},"msg":""}
    if (res) {
      commit("getAwardList", res);
    }
  },
  //营销活动-》领取奖励
  async getAward({ commit }, promise) {
    let res = await getAward(promise);
    if (res) {
      commit("getAward", res);
    }
  },
  //营销活动-》活动轮播图片
  async getPicture({ commit }, promise) {
    let res = await getPicture(promise);
    if (res) {
      commit("getPicture", res);
    }
  },
  //营销活动-》存放详情页面的活动号
  async setActiveData({ commit }, promise) {
      commit("setActiveData", promise);
  }
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
