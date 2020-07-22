/* zhangminjie 实时奖励活动-进行中 */
<template>
  <div class="realTimeRewardActivities">
    <ActivityContent :pageType="pageType"></ActivityContent>
    <RedEnvelopeTab
      :pageType="pageType"
      :reciveTab="reciveTab"
      :selected="selected"
      @changeStatusEvent="getChangeStatus"
    ></RedEnvelopeTab>
    <RedEnvelopeList
      :redEnvelopeList="redEnvelopeList"
      :pageType="pageType"
    ></RedEnvelopeList>
    <div class="activitiesFooter">
      <span>查看更多福利</span>
    </div>
  </div>
</template>

<script>
import ActivityContent from "../common/activityContent.vue";
import RedEnvelopeTab from "../common/redEnvelopeTab.vue";
import RedEnvelopeList from "../common/redEnvelopeList.vue";
import {
  getActivityApply,
  getActivityDetails,
  getActivityList,
  getAwardList,
  getAward
} from "@src/http/module/customerApi.js";
import { post } from "@src/http/axios.js";
import axios from "axios";
// let data = {"code":"1234","name":"yyyy"};
// axios.post(`${this.$url}/test/testRequest`,data)
//   .then(res=>{
//     //console.log('res=>',res);
//   })

export default {
  props: {},
  components: {
    ActivityContent,
    RedEnvelopeTab,
    RedEnvelopeList
  },
  created() {
    // this.post_activityApply(); //营销活动-》活动报名
    // this.post_activityDetails(); //营销活动-》活动详情
    // this.post_activityList(); //营销活动-》获取活动列表
    // this.post_awardList(); //营销活动-》查询我的福利
    // this.post_getAward(); //营销活动-》领取奖励
  },
  data: () => ({
    pageType: 1,
    redEnvelopeList: [
      {
        id: 1,
        title: "新年出单，惊喜连连！",
        money: 30,
        num: 3,
        type: 1 // 1 已领取  2 未领取
      },
      {
        id: 2,
        title: "新年出单，惊喜连连！",
        money: 30,
        num: 2,
        type: 1 // 1 已领取  2 未领取
      },
      {
        id: 3,
        title: "新年出单，惊喜连连！",
        money: 30,
        num: 1,
        type: 1 // 1 已领取  2 未领取
      },
      {
        id: 4,
        title: "新年出单，惊喜连连！",
        money: 30,
        num: 4,
        type: 1 // 1 已领取  2 未领取
      }
    ],
    selected: 1,
    reciveTab: [
      { title: "待领取", num: 3, id: 1 },
      { title: "已领取", num: 4, id: 2 }
    ]
  }),

  methods: {
    //营销活动-》活动报名
    async post_activityApply() {
      console.info("请求开始activityApply===>");
      let params = {
        activityCode: "123",
        salesmanCode: "0000000000"
      };
      // post('csa/activity/activityApply', params)
      let res = await getActivityApply(params);
      console.info("得到的返回结果=》",res)
    },

    //营销活动-》活动详情
    async post_activityDetails() {
      console.info("请求开始activityDetails===>");
      let params = {
        activityCode: "123456",
        salesmanCode: "123"
      };
      // post("csa/activity/activityApply", params);
      getActivityDetails(params);
    },
    //营销活动-》获取活动列表
    async post_activityList() {
      console.info("请求开始activityList===>");
      let params = {
        salesmanCode: "12"
      };
      // post("csa/activity/activityApply", params);
      getActivityList(params);
    },
    //营销活动-》查询我的福利
    async post_awardList() {
      console.info("请求开始awardList===>");
      let awardList = {"salesmanCode":"0000000000"};
      // post("csa/activity/activityApply", params);
      getAwardList(awardList);
    },
    //营销活动-》领取奖励
    async post_getAward() {
      console.info("请求开始getAward===>");
      let params = {
        activityCode: "123456",
        awardLevel: "1",
        salesmanCode: "123"
      };
      // post("csa/activity/activityApply", params);
      getAward(params);
    },
    changeActiveTab(tab) {
      for (let item of this.tabsObj) {
        item.isActive = false;
      }
      tab.isActive = true;
    },
    openedClosed() {
      //console.log(11);
    },
    // 切换已领取和待领取红包
    getChangeStatus(val) {
      this.selected = val;
    }
  }
};
</script>

<style lang="less" scoped>
.realTimeRewardActivities {
  background: linear-gradient(
    180deg,
    rgba(30, 70, 246, 1) 0%,
    rgba(39, 154, 255, 0.41) 100%
  );
  opacity: 0.77;
  padding: 52px 15px 41px 15px;
  font-family: PingFangSC-Medium, PingFang SC;
  .activitiesFooter {
    width: 100%;
    height: 22px;
    line-height: 22px;
    position: relative;
    right: 0;
    text-align: right;
    span {
      font-size: 13px;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
    }
  }
}
</style>
