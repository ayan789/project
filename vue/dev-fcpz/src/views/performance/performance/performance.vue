<template>
  <div class="performance">
    <van-tabs
      v-model="actived"
      swipeable
      @click="change"
      line-width="0"
      line-height="0"
    >
      <van-tab line-width="0" line-height="0">
        <template #title
          ><span :class="teamCode ? 'personnal' : 'noteamCode'"
            >个人<span class="line" v-if="!show"></span></span
        ></template>
        <div class="personal_performance">
          <personal-performance
            v-if="actived == 0"
            :actived="actived"
          ></personal-performance>
          <rate-progress :actived="actived" v-if="actived == 0"></rate-progress>
          <performance-rank
            :actived="actived"
            v-if="actived == 0"
          ></performance-rank>
        </div>
      </van-tab>
      <van-tab v-if="teamCode" line-width="0" line-height="0">
        <template #title
          ><span class="team"
            >团队<span class="team-line" v-if="show"></span></span
        ></template>
        <div class="team_performance">
          <team-performance
            :actived="actived"
            v-if="actived == 1"
          ></team-performance>
          <rate-progress :actived="actived" v-if="actived == 1"></rate-progress>
          <team-members v-if="actived == 1"></team-members>
          <performance-rank
            :actived="actived"
            v-if="isTeam && actived == 1"
          ></performance-rank>
        </div>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
// import personalPerformance from "./personalPerformance.vue";
// import teamPerformance from "./teamPerformance.vue";
// import rateProgress from "./rateProgress.vue";
// import performanceRank from "./performanceRank.vue";
// import teamMembers from "./teamMembers.vue";
import { trackMap } from "@src/utils/index.js";
export default {
  name: "performance",
  components: {
    personalPerformance: () => import("./personalPerformance.vue"), //昨日业绩
    teamPerformance: () => import("./teamPerformance.vue"), //团队业绩
    rateProgress: () => import("./rateProgress.vue"), //达成进度
    performanceRank: () => import("./performanceRank.vue"), //个人排名
    teamMembers: () => import("./teamMembers.vue") //团队成员
    // personalPerformance,
    // teamPerformance,
    // rateProgress,
    // performanceRank,
    // teamMembers,
  },
  data() {
    return {
      tabItem: ["个人", "团队"],
      actived: 0,
      isTeam: "",
      teamCode: "",
      show: false
    };
  },
  mounted() {
    this.$nextTick(() => {
      const userInfo = JSON.parse(localStorage.getItem("userInfo"));
      //判断是否加入团队
      this.isTeam = userInfo.teamcode ? true : false;
      //判断是否是团队长
      this.actived = userInfo.isTeamLeader === "1" ? 1 : 0;
      this.teamCode = userInfo.teamcode;
      this.show = this.actived;
    });
  },
  methods: {
    change(name, title) {
      console.log(name, title);
      //埋点
      trackMap(
        name == 0
          ? "c_performance_personal_performance"
          : "c_performance_team_performance",
        name == 0 ? "业绩个人业绩" : "业绩团队业绩",
        {
          c_salesperson_type: "N",
          c_market_type: "N",
          c_event_category: "业绩"
        }
      );
      if (name == 1) {
        this.show = true;
      } else {
        this.show = false;
      }
    }
  }
};
</script>

<style lang="less" scoped>
.performance {
  background: #f7f7f7;
  /deep/[class*="van-hairline"]::after {
    border: none;
  }
  // /deep/.van-tab {
  //   width: 73px;
  //   flex: auto;
  //   -webkit-box-flex: auto;
  //   -webkit-box-flex: auto;
  // }
  /deep/.van-tab__text {
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
  }
  /deep/ .van-tab--active {
    .van-tab__text {
      font-size: 18px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 600;
      color: rgba(0, 0, 0, 1);
    }
    .van-tabs__line {
      display: none !important;
    }
  }
  /deep/.van-tabs__line {
    display: block !important;
    width: 28px !important;
    height: 5px;
    background: rgba(59, 138, 254, 1);
    border-radius: 3px;
  }

  .personnal {
    margin-left: 120px;
    position: relative;
    & > .line {
      position: absolute;
      left: 4px;
      bottom: -10px;
      width: 28px;
      height: 5px;
      background: rgba(59, 138, 254, 1);
      border-radius: 3px;
    }
  }
  .noteamCode {
    & > .line {
      position: absolute;
      left: 4px;
      bottom: -10px;
      width: 28px;
      height: 5px;
      background: rgba(59, 138, 254, 1);
      border-radius: 3px;
    }
  }
  .team {
    margin-right: 120px;
    position: relative;
    & > .team-line {
      position: absolute;
      left: 4px;
      bottom: -10px;
      width: 28px;
      height: 5px;
      background: rgba(59, 138, 254, 1);
      border-radius: 3px;
    }
  }
}
</style>
