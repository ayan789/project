<template>
  <div id="substance">
    <Global_globalNav clear="1" title="跟进提醒" @clickBack="backClick" />

    <van-swipe :autoplay="4000" class="substance_banner">
      <van-swipe-item
        v-for="(item, index) in images"
        :key="index"
        @click="bannerClick(item)"
      >
        <van-image :src="item.image" fit="contain" style="width: 100%" />
      </van-swipe-item>
    </van-swipe>

    <div class="substance_today">
      <van-tabs
        v-model="customerData.active"
        swipeable
        @change="changeTabs"
        class="substance_vanTabs"
      >
        <div class="bottom"></div>
        <div class="accomplish">
          <span>今日完成 ({{ customerData.followRemindData.doneCount }}</span>
          <span>/{{ customerData.followRemindData.allCount }})</span>
        </div>

        <div class="substance_labeltab">
          <van-tab title="全部">
            <renewalReminder
              :remindList="customerData.followRemindData.remindList"
            />
            <div class="bottom"></div>
          </van-tab>

          <van-tab
            title="续保提醒"
            :info="customerData.followRemindData.renewalCount"
            v-if="customerData.followRemindData.renewalCount > 0"
          >
            <renewalReminder
              :remindList="customerData.followRemindData.remindList"
              @changeTabs="changeTabs"
            />
            <div class="bottom"></div>
          </van-tab>
          <van-tab title="续保提醒" v-else>
            <renewalReminder
              :remindList="customerData.followRemindData.remindList"
              @changeTabs="changeTabs"
            />
            <div class="bottom"></div>
          </van-tab>

          <van-tab
            title="客户关怀"
            :info="customerData.followRemindData.custCareCount"
            v-if="customerData.followRemindData.custCareCount > 0"
          >
            <renewalReminder
              :remindList="customerData.followRemindData.remindList"
              @changeTabs="changeTabs"
            />
            <div class="bottom"></div>
          </van-tab>
          <van-tab title="客户关怀" v-else>
            <renewalReminder
              :remindList="customerData.followRemindData.remindList"
              @changeTabs="changeTabs"
            />
            <div class="bottom"></div>
          </van-tab>

          <van-tab
            title="待注册"
            :info="customerData.followRemindData.unregisteredCount"
            v-if="customerData.followRemindData.unregisteredCount > 0"
          >
            <renewalReminder
              :remindList="customerData.followRemindData.remindList"
            />
            <div class="bottom"></div>
          </van-tab>
          <van-tab title="待注册" v-else>
            <renewalReminder
              :remindList="customerData.followRemindData.remindList"
            />
            <div class="bottom"></div>
          </van-tab>

          <van-tab
            title="出险提醒"
            :info="customerData.followRemindData.claimCount"
            v-if="customerData.followRemindData.claimCount > 0"
          >
            <renewalReminder
              :remindList="customerData.followRemindData.remindList"
            />
            <div class="bottom"></div>
          </van-tab>
          <van-tab title="出险提醒" v-else>
            <renewalReminder
              :remindList="customerData.followRemindData.remindList"
            />
            <div class="bottom"></div>
          </van-tab>
        </div>
      </van-tabs>
    </div>
  </div>
</template>
<script>
import renewalReminder from "./renewalReminder.vue";
import {
  getContentBanner,
  getQueryRemindTaskList,
  getQueryRightsRemind
} from "../../../http/module/customerApi";
import { Toast } from "vant";
import { mapState } from "vuex";
import { trackMap } from "@src/utils/index.js";

export default {
  components: {
    renewalReminder
  },
  data() {
    return {
      images: [{ image: this.$imageUrl.bannerimg, url: "fulan" }],
      remindData: {
        QRCodeUrl: "", //推荐码url
        interestList: [
          //权益列表
          {
            equitiesName: "", //权益名称
            startTime: "", //权益有效开始时间
            endTime: "" //权益有效结束时间
          }
        ]
      }
    };
  },

  computed: {
    ...mapState(["customerData"])
  },

  async created() {
    this.changeTabs(this.customerData.active, "全部");

    // 获取 banner
    let bannerParams = {
      orgCode: this.$localStorage.comCode
    };
    let res_banner = await getContentBanner(bannerParams);

    if (res_banner.code == "1") {
      let tempImages = res_banner.data.images ? res_banner.data.images : [];
      tempImages.map((v, i) => {
        this.images.push(v);
      });
      // this.images = tempImages;
    }
  },

  methods: {
    backClick() {
      this.$globalBack();
    },
    bannerClick(item) {
      console.log(item, "===item111111111");
      const { userCode, custNo } = this.$localStorage;
      // if (custNo == undefined) {
      //   custNo = "";
      // }
      let params = {
        userCode: userCode,
        custNo: custNo
      };
      // params = JSON.stringify(params);
      if (item.url && item.url == "fulan") {
        this.$router.push({
          name: "queryRightsRemind",
          query: { item: params }
        });
      } else {
        if (item.content) {
          this.$router.push({
            name: "contentView",
            query: { content: item.content }
          });
        } else {
          const tempUrl = item.url;
          let params = {
            url: tempUrl,
            titleBarTag: "0"
          };
          this.$native.openWebView(params, data => {});
        }
      }
    },

    async changeTabs(index, title) {
      let params = {
        userCode: localStorage.getItem("userCode"), //业务员号
        custNo: "", //客户号
        remindType: index //提醒类型
      };
      //埋点
      let tempObj = {
        "1": {
          eventId: "c_customer_serve_renewal_reminder",
          targetName: "客户服务续保提醒",
          c_salesperson_type: "N",
          c_market_type: "Y"
        },
        "2": {
          eventId: "c_customer_serve_customer_care",
          targetName: "客户服务客户关怀",
          c_salesperson_type: "Y",
          c_market_type: "Y"
        },
        "3": {
          eventId: "c_customer_serve_registered",
          targetName: "客户服务待注册",
          c_salesperson_type: "N",
          c_market_type: "N"
        },
        "4": {
          eventId: "c_customer_serve_issue_warm",
          targetName: "客户服务出险提醒",
          c_salesperson_type: "Y",
          c_market_type: "Y"
        }
      };
      index != "0" &&
        trackMap(tempObj[index].eventId, tempObj[index].targetName, {
          c_salesperson_type: tempObj[index].c_salesperson_type,
          c_market_type: tempObj[index].c_market_type,
          c_market_name: "",
          c_market_id: "",
          c_event_category: "客户管理"
        });
      tempObj = null;
      await this.$store.dispatch("customerData/getfollowRemindDatat", params);
    }
  }
};
</script>
<style lang="less" scoped>
#substance {
  background: #f7f7f7;
  position: fixed;
  width: 100%;

  /deep/ .van-hairline--bottom::after {
    border-bottom-width: 0px;
  }

  /*/deep/ .van-tabs--line .van-tabs__wrap {*/
  /*  */
  /*  background-color: white !important;*/
  /*}*/

  /deep/ .van-tabs__line {
    background-color: blue;
  }

  .substance_today {
    /*position: fixed;*/
    /*width: 100%;*/
    background: #ffffff !important;

    .substance_labeltab {
      height: calc(100vh - 280px);
      -webkit-overflow-scrolling: touch;
      overflow-scrolling: touch;
      overflow-y: scroll;
    }

    .substance_vanTabs {
      position: fixed;
      width: 100%;
      /*height: calc(100vh - 180px);*/
      /*overflow: scroll;*/
    }

    .accomplish {
      padding: 19px 0 24px 15px;
      height: 12px;
      font-size: 14px;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      background: #ffffff !important;
      line-height: 12px;
    }

    .tabs {
      padding-bottom: 10px;

      .optionsTabList {
        display: flex;
        padding: 0 15px;
        justify-content: space-around;
        align-items: center;
        height: 26px;
        font-size: 15px;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
        line-height: 26px;

        div {
          padding: 10px 0;
          position: relative;

          span {
            height: 5px;
            width: 28px;
            border-radius: 6px;
            background: #3b8afe;
            display: block;
            margin: 0 auto;
          }
        }
      }
    }

    .bottom {
      height: 10px;
      background: #f7f7f7;
    }
  }

  .amplification {
    height: 26px;
    font-size: 15px;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
    line-height: 26px;
  }
}

.substance_banner {
  /*display: flex;*/
  width: 100%;
  height: 189px;
  background-size: cover;
}

/deep/ .discount_dialog {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 10px;

  .discount_list {
    position: absolute;
    z-index: 19;
    top: 140px;
    width: 100%;
    height: 120px;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    overflow-y: scroll;
  }

  .discount_listItem {
    background-color: white;
    padding: 0px 10px;
    font-size: 14px;
    color: #333333;
  }

  .discount_dialog1 {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    font-size: 14px;
    color: #333333;
    width: 100%;
    margin: 8px 0px;
  }
}
</style>
