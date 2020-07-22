/* eslint-disable no-unused-vars */
<!--健康险-保单确认-->
<template>
  <div id="policy-confirm">
    <globalNav :title="globalNav.title"></globalNav>
    <div class="top-container">
      <div class="title-container">
        <div class="topLeft"></div>
        <div class="topRight">
          <div class="title">{{ title }}</div>
          <div class="time">保障期限: {{ timeMin }}至{{ timeMax }}</div>
        </div>
        <div class="icon" @click="backToPrePage">
          <img src="@/assets/common/edit.png" alt="edit" />
        </div>
      </div>
      <!-- <div class="title-container">
        <div class="title">{{ title }}</div>
        <div class="time">保障期限: {{ timeMin }}至{{ timeMax }}</div>
      </div> -->
      <person-information-card type="policyHolder"></person-information-card>
      <person-information-card type="recognizee"></person-information-card>
      <person-information-card type="invoiceInfo"></person-information-card>
    </div>
    <van-row
      type="flex"
      justify="space-between"
      align="center"
      class="bottom-confirm"
    >
      <van-row
        type="flex"
        justify="space-between"
        align="center"
        class="confirm-info"
      >
        <van-col class="confirm-info-t">总计</van-col>
        <van-col class="confirm-info-v">¥30,000.00</van-col>
      </van-row>
      <van-col class="confirm-button">投保确认</van-col>
    </van-row>
  </div>
</template>

<script>
import Vue from "vue";
import PersonInformationCard from "./personInformation/personInformationCard";
import { Button, NavBar, Card } from "vant";
import globalNav from "../../../components/globalNav";

Vue.use(Card);
export default {
  components: {
    [PersonInformationCard.name]: PersonInformationCard,
    [Button.name]: Button,
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    title: "百万医疗2020（个人版）",
    timeMin: "2020.01.01",
    timeMax: "2021.01.01",
    globalNav: {
      title: "保单确认"
    }
  }),
  methods: {
    // 返回上一页
    backToPrePage() {}
  }
};
</script>

<style lang="less" scoped>
#policy-confirm {
  padding-top: 65px;
  height: calc(100vh - 90px);
  overflow: scroll;
}
.top-container {
  width: 375px;
  height: 71px;
  background: rgba(255, 255, 255, 1);
  border-radius: 20px 20px 0px 0px;
}
.title-container {
  height: 56px;
  padding: 15px;
  margin: 0 0 10px 0;
  background-color: white;
  display: flex;
  align-items: center;
  flex: 1;
  position: relative;
  .topLeft {
    width: 103px;
    height: 63px;
    background: coral;
  }
  .topRight {
    display: flex;
    flex-direction: column;
  }
  .icon {
    position: absolute;
    right: 26px;
    top: 26px;
    img {
      width: 18px;
      height: auto;
    }
  }
}
.title {
  font-size: 18px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 25px;
}
.time {
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 19px;
}
.bottom-confirm {
  position: fixed;
  bottom: 0;
  left: 0;
  height: 48px;
  width: 100%;
  background-color: white;
  margin-top: 36px;
}
.confirm-info {
  width: 255px;
  background-color: white;
  padding: 0px 15px 0px 22px;
  line-height: 46px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
}
.confirm-info-t {
  font-size: 12px;
}
.confirm-info-v {
  font-size: 17px;
  color: #ff0064;
}
.confirm-button {
  width: 120px;
  height: 46px;
  background: #3b8afe;
  line-height: 46px;
  color: white;
  text-align: center;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
}
</style>
