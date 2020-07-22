<template>
  <div id="insuranceProducts">
    <div class="pro_header">
      <div class="title-container">
        <van-nav-bar title="保险产品" />
        <van-icon
          class="arrow-left"
          size="18"
          color="#fff"
          name="arrow-left"
          @click="back()"
        />
        <van-icon
          class="search1"
          size="20"
          color="#fff"
          name="search"
          @click="$router.push('/nonCar/searchList')"
        />

        <van-tabs
          @click="changeTab"
          swipeable
        >
          <van-tab
            :title="tab.title"
            :name="tab.id"
            v-for="(tab, index) in tabsObj"
            :key="index"
          >
          </van-tab>
        </van-tabs>
      </div>
      <van-row
        type="flex"
        justify="space-around"
        class="sortTabContainer"
        align="center"
      >
        <van-dropdown-menu
          class="sortTitle"
          active-color="#3B8AFE"
        >
          <van-dropdown-item
            v-model="value1"
            :options="option1"
            @change="openedClosed"
          />
        </van-dropdown-menu>
        <van-col
          class="sortTitle"
          @click="onRefresh('sale')"
          :class="[queryData.orderBy == 'salesnum' ? 'active' : '']"
        >
          销量排序
          <van-icon
            :class="[sale ? 'up' : 'down']"
            name="play"
            size="12"
          />
        </van-col>
        <van-col
          class="sortTitle"
          @click="onRefresh('price')"
          :class="[queryData.orderBy == 'price' ? 'active' : '']"
        >
          价格排序
          <van-icon
            :class="[price ? 'up' : 'down']"
            name="play"
            size="12"
          />
        </van-col>
        <van-col
          class="sortTitle"
          @click="onRefresh('time')"
          :class="[queryData.orderBy == 'time' ? 'active' : '']"
        >
          上架时间
          <van-icon
            :class="[time ? 'up' : 'down']"
            name="play"
            size="12"
          />
        </van-col>
      </van-row>
    </div>
    <div class="productList">
      <van-pull-refresh
        v-model="refreshing"
        @refresh="onRefresh"
      >
        <van-list
          v-model="loading"
          :finished="finished"
          finished-text="没有更多了"
          @load="onLoad"
        >
          <van-card
            class="product"
            v-for="item in list"
            :key="item.id"
            :title="item.productName"
            @click="goToProDetailPage(item, item.productBuyUrl)"
          >
            <div slot="thumb">
              <van-image :src="item.productImgUrl" />
            </div>
            <div
              slot="desc"
              class="desc"
            >
              <div>
                <p>{{ item.memo }}</p>
                <!-- desc -->
              </div>
              <div class="insureDiv">
                <!--                 v-if="item.productCode != 'YEF'"
 -->
                <div class="insureAge">
                  <!--                   v-if="item.applicantAge != null"
 -->
                  {{ item.publicityInfo }}
                  <!--  applicantAge-->
                </div>
                <div v-if="
                    item.isHealinsurance != null && item.productCode == 'FM51'
                  ">
                  有无社保:
                  {{ item.isHealinsurance == "0" ? "无社保" : "有社保" }}
                </div>
              </div>
            </div>
            <div
              slot="price"
              class="bottom"
            >
              <p>
                <span class="fontSize20 red">{{ item.productDesc }}</span>
                <span class="fontSize12 red">元起</span>
              </p>
              <p>
                <span class="salesVolume">已售{{ item.saleSum.length>=5? parseInt(item.saleSum /10000) + " 万+": item.saleSum || 0 }}份</span>
              </p>
            </div>
            <!-- <div slot="footer">
              <span class="salesVolume">已售{{ item.sumSale || 0 }}份</span>
            </div> -->
          </van-card>
        </van-list>
      </van-pull-refresh>
    </div>
  </div>
</template>

<script>
import Vue from "vue"

import { Tab, Tabs, Card, Image, Icon, PullRefresh, List } from "vant"

import { queryProduct } from "@src/http/module/product.js"
import * as native from "../../../utils/JSBridge"
Vue.use(Image)
Vue.use(Card)
Vue.use(Tab)
Vue.use(Tabs)
Vue.use(PullRefresh)
export default {
  data () {
    return {
      list: [],
      loading: false,
      finished: false,
      refreshing: false,
      queryData: {
        storeCode: localStorage.getItem("storeCode"),
        page: 0,
        size: 10,
        asc: "asc",
        orderBy: "",
        productType: null,
        peopleList: null,
      },
      tabsObj: [
        { id: null, title: "全部" },
        { id: "01", title: "健康" },
        { id: "02", title: "财产" },
        { id: "03", title: "意外" },
        { id: "04", title: "企业" },
        // { id: 6, title: "全部", isActive: false },
        // { id: 7, title: "意外", isActive: false }
      ],
      value1: "0",
      option1: [
        //全部、成人、老人、少儿、家庭、白领、学生、其他
        { text: "全部人群", value: "0" },
        { text: "成人", value: "1" },
        { text: "老人", value: "2" },
        { text: "少儿", value: "3" },
        { text: "家庭", value: "4" },
        { text: "白领", value: "5" },
        { text: "学生", value: "6" },
        { text: "其他", value: "7" },
      ],
      sale: false,
      price: false,
      time: false,
    }
  },
  created () {
    //埋点 首页进来
    this.$GetRequest("source", true) === "shell" &&
      trackMap("c_home_no_car_insure", "非车投保", {
        c_salesperson_type: "Y",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "首页",
      })
    //全局获取缓存数据
    this.$globalSetLocalStorage()
    this.$native.showOrCloseWatermarket(false, (data) => { })
  },
  mounted () {
    this.$native.showOrCloseWatermarket(false, (data) => { })
  },
  methods: {
    async onLoad () {
      this.queryData.page++
      let res = await queryProduct(this.queryData)
      if (res.code === "1") {
        if (this.refreshing) {
          this.list = []
          this.refreshing = false
        }
        this.list = this.list.concat(res.data.productList)
        this.loading = false

        if (res.data.pageInfo.hasNextPage == false) {
          this.finished = true
        }
      }
    },
    onRefresh (num, e) {
      if (num == "sale") {
        this.queryData.orderBy = "salesnum"
        this.sale = !this.sale
        this.price = false
        this.time = false
        this.list = []
        if (this.sale) {
          this.queryData.asc = "asc"
        } else {
          this.queryData.asc = "desc"
        }
      }

      if (num == "price") {
        this.queryData.orderBy = "price"
        this.price = !this.price
        this.sale = false
        this.time = false
        this.list = []
        if (this.price) {
          this.queryData.asc = "asc"
        } else {
          this.queryData.asc = "desc"
        }
      }

      if (num == "time") {
        this.queryData.orderBy = "time"
        this.time = !this.time
        this.sale = false
        this.price = false

        this.list = []
        if (this.time) {
          this.queryData.asc = "asc"
        } else {
          this.queryData.asc = "desc"
        }
      }

      // page置0
      this.queryData.page = 0
      this.finished = false

      // 重新加载数据
      this.loading = true
      this.onLoad()
    },
    back () {
      if (!window.WebViewJavascriptBridge) {
        console.log("壳子返回")
        this.$native.closeWeb()
        return
      } else {
        this.$native.openHome("", (data) => { })
      }
    },
    changeTab (index) {
      switch (index) {
        case 0:
          this.queryData.productType = null
          this.list = []
          this.onRefresh()
          break
        case "01":
          this.queryData.productType = "01"
          this.list = []
          this.onRefresh()
          break
        case "02":
          this.queryData.productType = "02"
          this.list = []
          this.onRefresh()

          break
        case "03":
          this.queryData.productType = "03"
          this.list = []
          this.onRefresh()

          break
        case "04":
          this.queryData.productType = "04"
          this.list = []
          this.onRefresh()

          break
      }
    },

    openedClosed (index) {
      switch (index) {
        case "0":
          this.queryData.peopleList = null
          this.list = []
          this.onRefresh()
          break
        case "1":
          this.queryData.peopleList = ['01']
          this.list = []
          this.onRefresh()
          break
        case "2":
          this.queryData.peopleList = ['02']
          this.list = []
          this.onRefresh()
          break
        case "3":
          this.queryData.peopleList = ['03']
          this.list = []
          this.onRefresh()
          break
        case "4":
          this.queryData.peopleList = ['04']
          this.list = []
          this.onRefresh()
          break
        case "5":
          this.queryData.peopleList = ['05']
          this.list = []
          this.onRefresh()
          break
        case "6":
          this.queryData.peopleList = ['06']
          this.list = []
          this.onRefresh()
          break
        case "7":
          this.queryData.peopleList = ['07']
          this.list = []
          this.onRefresh()
          break
      }
    },
    goToProDetailPage (item, url) {
      // 如果是货运无忧页面，清除缓存数据
      if (item.productBuyUrl.indexOf("WorryFree") >= 0) {
        this.$store.dispatch("nonCar/clearShareData")
        this.$store.state.nonCar.shareData.isIssueAfterPay =
          item.isIssueAfterPay
      }

      // 清空动态配置方案详情页数据
      if (item.productBuyUrl.indexOf("prIndex") >= 0) {
        this.$store.commit("config/reset_Config")
        this.$store.commit("config/reset_totForm");
        this.$store.commit("config/reset_formRule");
        this.$store.commit("config/reset_insredFormRule");
        this.$store.commit("config/reset_timeLimitInfo");
      }
      if (url == "stopCancer") {
        this.$store.commit("million/CLENEWDATA")
      }
      if (url == "millionMedicalCare") {
        this.$store.commit("million/CLETENMILLIONDATA")
      }
      //清空家庭版数据
      localStorage.setItem("millionHomeStep1", "")
      //清空家庭版数据
      localStorage.setItem("millionHomeStep2", "")
      this.$router.push({
        name: url,
        query: { saleSum: item.sumSale, strategyId: item.strategyId },
      })
      // this.$router.push({ name: 'proWorryFree' })
      // if (item.productCode == 'YEF') {
      // } else {
      //   this.$router.push({ name: 'millionMedicalCare' })
      // }
    },
  },
}
</script>

<style lang="less" scoped>
#insuranceProducts {
  background: #fff;
  font-family: PingFangSC-Medium, PingFang SC;
  padding-top: 120px;
  // height: calc(100vh - 120px) !important;
  height: 100% !important;
  .pro_header {
    height: 110px;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    z-index: 99;
    /deep/[class*="van-hairline"]::after {
      border: none;
    }
  }
  /deep/.van-tab {
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 400;
    color: #fff;
  }
  /deep/.van-nav-bar__title {
    color: #fff !important;
  }
  .title-container {
    background-image: url(../../../assets/nonCar/healthInsurance/header.png);
    background-size: cover;
    [class*="van-hairline"]::after {
      border: none !important;
    }
    padding-bottom: 10px;
    .arrow-left {
      position: absolute;
      top: 16px;
      left: 10px;
      z-index: 1000;
    }
    .search1 {
      position: absolute;
      top: 16px;
      right: 10px;
      z-index: 1000;
    }
    /deep/.van-nav-bar {
      background: none;
    }
    /deep/.van-tabs__nav {
      background: none;
    }
  }
  /deep/.van-tab--active {
    font-size: 17px;
    color: #fff;
    font-weight: 600;
  }
  /deep/.van-tabs__line {
    width: 23px !important;
    height: 3px !important;
    bottom: 0.5rem;
    border-radius: 6px;
    background: rgb(255, 255, 255);
  }
  .sortTabContainer {
    width: 100%;
    height: 40px;
    background: rgba(247, 247, 247, 1);
    border-radius: 10px;
    margin-top: -10px;
    overflow: hidden;

    .sortTitle {
      font-size: 14px;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
      line-height: 22px;
      background: rgba(247, 247, 247, 1);
      height: auto;
      position: relative;
      .down {
        transform: rotate(90deg);
        -ms-transform: rotate(90deg); /* IE 9 */
        -moz-transform: rotate(90deg); /* Firefox */
        -webkit-transform: rotate(90deg); /* Safari 和 Chrome */
        -o-transform: rotate(90deg);
      }
      .up {
        transform: rotate(-90deg);
        -ms-transform: rotate(-90deg); /* IE 9 */
        -moz-transform: rotate(-90deg); /* Firefox */
        -webkit-transform: rotate(-90deg); /* Safari 和 Chrome */
        -o-transform: rotate(-90deg);
      }
      /deep/.van-icon-arrow-up {
        position: absolute;
        right: -15px;
        top: 1px;
        cursor: pointer;
      }
      /deep/.van-icon-arrow-down {
        position: absolute;
        right: -15px;
        bottom: 1px;
        cursor: pointer;
      }
    }
    .titleActive {
      color: rgba(59, 138, 254, 1);
    }
    /deep/.van-dropdown-menu__title {
      color: rgba(59, 138, 254, 1);
    }
  }
  .productList {
    margin-top: 10px;
    background: #ffffff;
    padding: 0 16px;
    // height: 100%;
    /deep/.van-card {
      background: none;
      padding: 15px 0;
    }
    .product {
      // height: 120px;
      border-bottom: 1px solid #e5e6e8;
    }
    /deep/.van-card:not(:first-child) {
      margin-top: 0px !important;
    }
    /deep/.van-card__thumb {
      margin-right: 13px;
      overflow: hidden;
      div {
        width: 100%;
        height: 100%;
      }
    }
    /deep/.van-card__thumb img {
      border-radius: 0.2rem;
    }
    /deep/.van-card__title {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 22px;
      height: 22px;
      margin-bottom: 5px;
    }
    .desc {
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      line-height: 18px;
    }
    .bottom {
      display: flex;
      justify-content: space-between;
    }
    /deep/.van-card__desc {
      font-size: 12px;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      // line-height: 22px;
      max-height: none;
      word-wrap: break-word !important;
      white-space: inherit !important;
      overflow: visible !important;
    }
    /deep/.van-card__price {
      width: 100%;
      padding-top: 5px;
    }
    .van-card__bottom {
      // margin-top: 19px;
    }
    .fontSize20 {
      font-size: 20px;
      font-weight: bold;
    }
    .fontSize12 {
      font-size: 12px;
    }
    .red {
      color: #fc820e;
    }
    .salesVolume {
      font-size: 12px;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      // position: absolute;
      // right: 0;
      // bottom: 15px;
    }
    .insureDiv {
      width: 250px;
      display: flex;
      justify-content: space-between;
      align-content: space- between;
      color: rgba(153, 153, 153, 1);

      div {
        flex: 1;
      }
    }
  }
  .active {
    color: #3b8afe !important;
  }
  /deep/ .van-dropdown-menu__bar {
    background-color: #f7f7f7;
  }
}
</style>
