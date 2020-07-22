<template>
  <div class="renewalList">
    <div class="renewalList-head">
      <van-nav-bar title="我的续保" left-arrow @click-left="back()">
        <div slot="right">
          <!-- <van-icon name="chat-o" :size="20" @click="goInfoCenter" /> -->
          <span :style="{ color: 'white' }" @click="show = true">首报规则</span>
        </div>
      </van-nav-bar>
      <!-- <van-tabs :border="false" v-model="insuranceActive">
        <van-tab title="车险" :name="1"></van-tab>
        <van-tab title="健康险" :name="2"></van-tab>
      </van-tabs> -->
      <van-tabs
        v-model="active"
        color="white"
        :border="false"
        @click="checkTab"
      >
        <van-tab
          :title="item.title"
          :name="item.name"
          v-for="(item, index) in renewalContactList"
          :key="index"
        >
        </van-tab>
      </van-tabs>
      <div class="renewalList-borderRadius">
        <div></div>
      </div>
    </div>
    <renewal-contactList
      v-if="insuranceActive == 1"
      :type="renewalContactProps"
      :key="renewalContactProps.name"
      :requestActived="requestActived"
    ></renewal-contactList>
    <renewal-nocar-contactList
      v-if="insuranceActive == 2"
      :type="renewalContactProps"
      :key="renewalContactProps.name"
    >
    </renewal-nocar-contactList>
    <van-dialog v-model="show" title="首报规则" @confirm="show = false">
      <div class="dialog_">
        <p><span style="color:#FD6363">红色</span>：可进行首报或二报</p>
        <p><span style="color:#10C143">绿色</span>：首报或二报操作完成</p>
        <p>
          <span style="color:#FFCC99">黄色</span>：首报或二报未在操作期内完成
        </p>
        <p><span style="color:#999999">灰色</span>：超过二报报价期</p>
      </div>
    </van-dialog>
  </div>
</template>

<script>
import { Tab, Tabs, Sticky } from "vant";
// import { GetRequest } from "@src/utils/index.js";
//续保联系人列表
import renewalContactList from "./renewalContactList";
//非车续保联系人列表
// import renewalNocarContactList from "./renewalNocarContactList";
import * as native from "../../../utils/JSBridge";

export default {
  name: "renewalList",
  components: {
    [Tab.name]: Tab,
    [Tabs.name]: Tabs,
    [Sticky.name]: Sticky,
    renewalContactList
    // renewalNocarContactList
  },
  async created() {
    //全局获取缓存数据
    // await this.$globalSetLocalStorage();
    /**
     * 消息跳转获取URL上的参数
     * ?actived=1
     */
    let actived = this.$GetRequest("actived", true) || "1";
    this.active = actived;
    this.renewalContactProps = this.renewalContactList.find(
      item => item.name === actived
    );
    this.requestActived = this.$GetRequest("actived", true) || "";
    //
  },
  data() {
    return {
      show: false,
      insuranceActive: 1,
      active: 1,
      renewalContactProps: {
        title: "未续保",
        name: "1"
      },
      renewalContactList: [
        { title: "未续保", name: "1" },
        { title: "已续保", name: "2" },
        { title: "已过期", name: "3" },
        { title: "续保认领", name: "4" }
      ],
      requestActived: ""
    };
  },
  watch: {
    //切换险种
    insuranceActive: {
      handler(newVal, oldVal) {
        newVal == 1 &&
          this.$set(this, "renewalContactList", [
            { title: "未续保", name: "1" },
            { title: "已续保", name: "2" },
            { title: "已过期", name: "3" },
            { title: "续保认领", name: "4" }
          ]);
        newVal == 2 &&
          this.$set(this, "renewalContactList", [
            { title: "未续保", name: "1" },
            { title: "已续保", name: "2" },
            { title: "已过期", name: "3" }
          ]);
        if (newVal != oldVal) {
          this.active = 1;
          this.$set(this, "renewalContactProps", {
            title: "未续保",
            name: "1"
          });
        }
      },
      immediate: true
    }
  },
  methods: {
    checkTab(name, title) {
      this.renewalContactProps = { name, title };
      this.requestActived = "";
    },
    back() {
      //从原生调过来的调用back方法 其他的调到首页
      if (this.$GetRequest("actived", true)) {
        this.$native.closeWeb();
      } else {
        this.$router.push({ path: "/" });
      }
    },
    // show() {
    //   this.$dialog.alert({
    //     title: "首报规则",
    //     message: `%c红色：可进行首报或二报\n绿色：首报或二报操作完成\n黄色：首报或二报未在操作期内完成\n灰色：超过二报报价期`,
    //     messageAlign: "left"
    //   });
    // },
    goInfoCenter() {
      let jsonData = {
        route: "message"
      };
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.openNativeWeb(jsonData, data => {});
    }
  }
};
</script>

<style lang="less" scoped>
.renewalList {
  overflow: hidden;
  .dialog_ {
    padding: 12px 24px;
    font-size: 14px;
    p {
      margin-bottom: 3px;
    }
  }

  &-head {
    background: url(../../../assets/renewal/bavBackground.png) no-repeat 100%,
      100%;
    // background-size: cover;
    background-position: 70%, 0;
  }
  &-borderRadius {
    div {
      height: 8px;
      border-radius: 0.21333rem 0.21333rem 0 0;
      background: #fff;
    }
  }
  /deep/.van-hairline--bottom::after {
    border-bottom-width: 0;
  }
  // /deep/ .van-tabs--line {
  //   .van-tabs__wrap {
  //     height: auto;
  //   }
  // }
  /deep/.van-nav-bar__title,
  .van-icon {
    color: #fff;
  }
  /deep/.van-nav-bar {
    background-color: initial;
    .van-icon {
      color: white;
    }
  }
  /deep/.van-tabs__nav {
    background-color: initial;
  }
  /deep/ .van-tab__text {
    color: white;
    font-size: 14px;
  }
  /deep/ .van-tab--active {
    .van-tab__text {
      color: white;
      font-size: 16px;
      font-weight: 600;
    }
  }
  /deep/ .van-tabs__line {
    width: 23px !important;
    display: none;
  }
}
</style>
