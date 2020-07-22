<template>
  <div class="wrap">
    <div class="labelPage">
      <div class="optionsTabList">
        <div class="left">
          <van-icon name="arrow-left" size="20" @click="back()" />
          <div style="width: 20px;background-color:transparent " />
        </div>
        <div
          class="optionListitem"
          v-for="(item, index) in optionList"
          :key="index"
          :class="[active == index ? 'amplification' : '']"
          @click="changeTabs(index)"
        >
          {{ item.name }}
        </div>
        <div class="right">
          <van-row
            type="flex"
            justify="end"
            align="center"
            style="margin-right:10px;"
            @click="$router.push({ name: 'responsibilityList' })"
          >
            <van-col class="right-title">责任书</van-col>
          </van-row>
        </div>
      </div>
      <div class="navigatorBottom" />
    </div>
    <div class="view">
      <div v-if="active === 0">
        <performance />
      </div>
      <div v-if="active === 1">
        <salary-assess />
      </div>
    </div>
  </div>
  <!-- <van-tabs
    v-if="isLogin"
    v-model="model"
    line-width="23px"
    line-height="3px"
    color="white"
    :border="false"
    title-active-color="#ffffff"
    title-inactive-color="#ffffffb8"
    background="linear-gradient(129deg, #1e46f6 0%, #279aff 100%)"
    sticky
    @click="changeTab"
  >
    <template slot="nav-left">
      <van-row type="flex" align="center" style="width:100px;">
        <van-icon name="arrow-left" @click="back()" color="white" size="22" />
      </van-row>
    </template>
    <template slot="nav-right">
      <van-row
        type="flex"
        justify="end"
        align="center"
        style="width:100px;margin-right:10px;"
        @click="$router.push({ name: 'responsibilityList' })"
      >
        <van-col class="right-title">责任书</van-col>
      </van-row>
    </template>
    <van-tab title="我的业绩">
      <performance />
    </van-tab>
    <van-tab title="薪酬&考核">
      <salary-assess />
    </van-tab>
  </van-tabs> -->
</template>

<script>
import Performance from "./performance/performance";
import SalaryAssess from "./salary/salaryAssess";
import { isHaveSetSalaryPwd } from "@src/http/module/performance";
import { Toast } from "vant";
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    [Performance.name]: Performance,
    [SalaryAssess.name]: SalaryAssess
  },
  data: () => ({
    isSetPwd: false,
    isLogin: false,
    model: "我的业绩",
    active: 0,
    optionList: [{ name: "我的业绩" }, { name: "我的薪酬" }],
    isAgentCode: false
  }),
  created() {
    //埋点 首页进来
    this.$GetRequest("source", true) === "shell" &&
      trackMap("c_home_achive_pay", "业绩薪酬", {
        c_salesperson_type: "N",
        c_market_type: "N",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "首页"
      });
    //全局获取缓存数据
    this.$globalSetLocalStorage();
    console.log(
      "agentcode======" + JSON.parse(localStorage.getItem("userInfo")).agentCode
    );
    let agentCode = JSON.parse(localStorage.getItem("userInfo")).agentCode;
    /* 判断是否是代理人 */
    this.isAgentCode = agentCode ? true : false;
    console.log("this.isAgentCode", this.isAgentCode);
    if (this.isAgentCode) {
      this.$router.push({ name: "agentPerformance" });
    }
    let { isLogin = false } = this.$route.params || {};
    // isLogin = true;
    this.isLogin = isLogin;
    /* 跳转薪酬 */
    if (this.isLogin) {
      this.active = 1;
    }
    if (isLogin) return;
  },
  methods: {
    changeTabs(index) {
      const userPhone = JSON.parse(localStorage.getItem("userInfo")).userPhone;
      // if(userPhone) {
      //埋点
      trackMap(
        index == 0
          ? "c_performance_my_performance"
          : "c_performance_salary_appraisal",
        index == 0 ? "业绩我的业绩" : "业绩薪酬考核",
        {
          c_salesperson_type: "N",
          c_market_type: "N",
          c_event_category: "业绩"
        }
      );
      if (userPhone || index == 0) {
        this.active = index;
      } else {
        Toast("请您先在个人中心补充手机号码");
      }
    },
    back() {
      if (window.WebViewJavascriptBridge) {
        console.log("业绩壳子返回");
        this.$native.closeWeb();
      } else {
        this.$globalBack();
      }
    }
  }
};
</script>

<style lang="less" scoped>
.right-title {
  font-size: 14px;
  color: #fff;
}
.wrap {
  position: fixed;
  width: 100%;
  .labelPage {
    .optionsTabList {
      height: 80px;
      color: #fff;
      font-size: 15px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      background: linear-gradient(129deg, #1e46f6 0%, #279aff 100%);
      align-items: center;
      .left {
        // width: 80px;
        // height: 50px;
        display: flex;
        margin-left: 10px;
        flex-direction: row;
        align-items: center;
        justify-content: space-around;
      }
      .right {
        margin-right: 10px;
      }

      .optionListitem {
        position: relative;
      }

      .amplification {
        font-size: 17px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
      }
    }
    .navigatorBottom {
      width: 100vw;
      height: 15px;
      border-radius: 15px 15px 0px 0px;
      background-color: #ffffff;
      top: 65px;
      z-index: 1000;
      position: fixed;
      border: 1px solid white;
    }
  }
  .view {
    // height: 100%;
    height: calc(100vh - 80px);
    overflow: scroll;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
  }
}
</style>
