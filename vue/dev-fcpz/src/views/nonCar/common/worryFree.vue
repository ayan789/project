<template>
  <div class="worryFree">
    <h3>货运无忧</h3>
    <div class="worryFreeNav">
      <!-- <img src="@/assets/nocarIcon/worryFreeHeader.jpg" alt="header" /> -->
    </div>
    <div class="worryFreeNavTop">
      <div class="worryFreeHeader">
        <span class="space"></span>
        <span class="guaranteedTitle">保障计划</span>
        <button class="guaranteedDetailBtn">保障详情</button>
      </div>
      <div class="worryFreeNavMid">
        <div class="worryFreeNavMidContent">
          <div class="tabs">
            <button
              :class="[isBasicVersion ? 'basicVersion' : 'basicVersion active']"
              @click="changeTabs"
            >基础版</button>
            <button
              :class="[ !isBasicVersion ? 'worryFreeVersion' : 'worryFreeVersion active']"
              @click="changeTabs"
            >无忧版</button>
          </div>
          <p class="tabsContents">
            <span>货物运输车辆停运损失补偿保险</span>
            <span>200万</span>
          </p>
        </div>
      </div>
    </div>
    <div class="userInfo top10">
      <div class="userInfoTop">
        <div class="userInfoTopHeader">
          <span class="space"></span>
          <span class="userInfoTopHeaderTitle">关系人信息</span>
        </div>
        <div class="userInfoTopContent">
          <ul>
            <li>
              <span>是否代理</span>
              <span>
                <button :class="[isProxy ? 'right10 btnActive' : 'right10']" @click="changeProxy">是</button>
                <button :class="[!isProxy ? 'btnActive' : '']" @click="changeProxy">否</button>
              </span>
            </li>
            <li>
              <span>直销/直团渠道</span>
              <span class="directSales">
                <span class="right10">直销</span>
                <van-icon name="arrow" />
              </span>
            </li>
            <li>
              <span>保障年限</span>
              <span>1年</span>
            </li>
            <li>
              <span>份数（最多1份）</span>
              <span>1份</span>
            </li>
          </ul>
        </div>
      </div>
      <div class="userInfoMid top10">
        <div class="userInfoMidHeader">
          <ul class="userInfoTabs">
            <li
              v-for="(item, index) in userInfoTabs"
              class="tabsTitle"
              :class="[item.isActive ? 'tabActive' : '']"
              :key="index"
              @click="changeUserInfoTabs(item)"
            >
              {{ item.name }}
              <span class="tabLine" v-show="item.isActive"></span>
            </li>
          </ul>
        </div>
        <div class="userInfoPic">
          <!-- <img src="" alt=""> -->
        </div>
      </div>
    </div>
    <div class="claimDescription top10">
      <div class="claimDescriptionHeader">
        <span class="space verticalMid"></span>
        <span class="guaranteedTitle">理赔说明</span>
      </div>
      <div class="claimDescriptionBottom">
        <van-steps direction="vertical" active="3" active-icon="bag-o" active-color="#999999">
          <van-step>
            <h3>第一步</h3>
            <p>请在保险事故发生后拨打电话</p>
          </van-step>
          <van-step>
            <h3>第二步</h3>
            <p>请在保险事故发生后拨打电话请在保险事故发生后拨打电话请在保险事故发生后拨打电话</p>
          </van-step>
        </van-steps>
      </div>
    </div>
    <div class="commonProblem top10">
      <div class="commonProblemHeader">
        <span class="space verticalMid"></span>
        <span class="guaranteedTitle">常见问题</span>
      </div>
      <div class="questions">
        <van-collapse v-model="activeNames">
          <van-collapse-item
            class="questionContent"
            v-for="item in questionsList.slice(0,3)"
            :key="item.name"
            :name="item.name"
            :title="item.title"
            icon="shop-o"
          >{{item.answer}}</van-collapse-item>
          <van-collapse-item
            v-if="isCollage"
            class="questionContent"
            v-for="item in questionsList.slice(3)"
            :key="item.name"
            :name="item.name"
            :title="item.title"
            icon="shop-o"
          >{{item.answer}}</van-collapse-item>
        </van-collapse>
        <div class="collageAllOut">
          <button class="collageAll" @click="collageList">更多问题</button>
          <van-icon class="collageAllIcon" name="arrow-down" v-if="!isCollage" />
          <van-icon class="collageAllIcon" name="arrow-up" v-if="isCollage" />
        </div>
      </div>
    </div>
    <div class="worryFreeBottom">
      <span class="red">¥30,000.00</span>
      <button>我要投保</button>
    </div>
  </div>
</template>

<script scoped>
import "@/style/nocar/worryFree.less";
import Vue from "vue";
import { Collapse, CollapseItem } from "vant";

Vue.use(Collapse);
Vue.use(CollapseItem);
export default {
  name: "worryFree",
  components: {},
  data: () => ({
    isBasicVersion: true,
    isProxy: true,
    activeNames: [],
    isCollage: false,
    userInfoTabs: [
      {
        isActive: true,
        name: "产品特色"
      },
      {
        isActive: false,
        name: "理赔服务"
      },
      {
        isActive: false,
        name: "常见问题"
      }
    ],
    questionsList: [
      {
        title: "1.哪些车型的货车作为承运工具时可以承保？",
        name: 1,
        answer:
          "必须是运输货物的车辆，如平板货车、集装箱货车、厢式货车、油罐车等，但下述特种车型外：消防车、救险车、垃圾车、应急车、街道清洗车、扫雪车、清洁车、洒水车等。"
      },
      {
        title: "是否可承保异地牌照货车作为承运工具的业务？",
        name: 2,
        answer:
          "原则上仅限承保当地牌照货车作为承运工具的业务，承保异地业务需事先报总公司审核。"
      },
      {
        title: "3.如投保的货物承运车辆挂车不固定，投保时是否可以仅填写主车车牌号？如投保主车不固定，投保时是否可以仅填写挂车车牌号？",
        name: 3,
        answer:
          "可以"
      },
      {
        title: "4.如运输公司一次性投保，是否可以仅填写一张投保单后附承运车辆清单？",
        name: 4,
        answer:
          "可以"
      }
    ]
  }),
  methods: {
    // 切换运货无忧头部的版本
    changeTabs() {
      if (this.isBasicVersion) {
        this.isBasicVersion = false;
      } else {
        this.isBasicVersion = true;
      }
    },
    //是否收否代理
    changeProxy() {
      if (this.isProxy) {
        this.isProxy = false;
      } else {
        this.isProxy = true;
      }
    },
    // 关系人信息中间的tab切换
    changeUserInfoTabs(item) {
      for (let tabItem of this.userInfoTabs) {
        tabItem.isActive = false;
      }
      item.isActive = true;
    },
    //显示更多问题信息
    collageList() {
      if (this.isCollage) {
        this.isCollage = false;
      } else {
        this.isCollage = true;
      }
    }
  },
 
};
</script>

<style lang="less" scoped></style>
