<template>
  <div id="searchList">
    <div class="title-container1">
      <van-nav-bar title="搜索结果" />
      <van-icon
        class="arrow-left"
        size="18"
        color="#fff"
        name="arrow-left"
        @click="$router.back()"
      />
      <van-sticky>
        <div class="searchOrder">
          <van-icon
            class="searchImg"
            size="20"
            color="#333"
            name="search"
            @click="searchOrderList(queryData.keyWord)"
          />
          <input
            ref="input"
            v-focus
            type="text"
            placeholder="搜索"
            v-model="queryData.keyWord"
            @keyup.enter="searchOrderList(queryData.keyWord)"
          />
          <span @click="searchOrderList(queryData.keyWord)">
            搜索
          </span>
        </div>
      </van-sticky>
    </div>
    <div
      class="searchWord"
      v-if="localStorageList.length"
    >
      <div style="margin-left: .5rem;">最近搜索</div>
      <van-icon
        name="delete"
        @click="del"
      />
    </div>
    <ul class="ul">
      <li
        class="li"
        v-for="(item, index) in localStorageList"
        :key="index"
        v-if="index < 20"
        @click="hisItem(item)"
      >
        {{ item }}
      </li>
    </ul>
    <div class="productList">
      <van-pull-refresh
        v-model="refreshing"
        @refresh="searchOrderList"
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
            :desc="item.desc"
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
          </van-card>
        </van-list>
      </van-pull-refresh>
    </div>
  </div>
</template>

<script>
import Vue from "vue"
import { Tab, Tabs, Card, Image, Icon, PullRefresh, List } from "vant"
import globalNav from "../../../components/globalNav"
import { queryProduct } from "@src/http/module/product.js"
Vue.use(Image)
Vue.use(Card)
Vue.use(Tab)
Vue.use(Tabs)
Vue.use(PullRefresh)
Vue.use(List)
export default {
  components: {
    [globalNav.name]: globalNav,
    [PullRefresh.name]: PullRefresh,
    [List.name]: List,
  },
  data: () => ({
    sale: "5",
    priceD: "1",
    timer: "3",
    loading: false,
    list: [],

    refreshing: false,
    finished: false,
    type: 0,
    value1: 0,
    activeName: "",
    globalNav: {
      title: "保险产品",
    },
    option1: [
      { text: "全部商品", value: 0 },
      { text: "新款商品", value: 1 },
      { text: "活动商品", value: 2 },
    ],
    tabsObj: [
      { id: "", title: "全部", isActive: true },
      { id: 1, title: "健康", isActive: false },
      { id: 2, title: "财产", isActive: false },
      { id: 3, title: "意外", isActive: false },
      { id: 5, title: "企业", isActive: false },
      // { id: 6, title: "全部", isActive: false },
      // { id: 7, title: "意外", isActive: false }
    ],
    queryData: {
      keyWord: "",
      // keyOrderBy: '',
      // productType: '',
      // storeCode: JSON.parse(localStorage.getItem('userInfo')).storeCode
      storeCode: localStorage.getItem("storeCode"),
      page: 1,
      size: 10,
      asc: "asc",
      orderBy: "",
      productType: null,
      peopleList: null,
      keyWord: "",
    },
    productList: [],
    localStorageList: [],
    // tabsIndex: 1
  }),
  created () {
    // this.searchOrderList(this.queryData.keyWord)
    this.las()
  },
  directives: {
    focus: {
      inserted: function (el) {
        el.focus()
      },
    },
  },
  methods: {
    del () {
      this.localStorageList = []
      if (localStorage.getItem("localList")) {
        localStorage.removeItem("localList")
      }
    },
    las () {
      if (localStorage.getItem("localList")) {
        this.localStorageList = localStorage.getItem("localList").split(",")
      }
      console.log(this.localStorageList)
    },
    async onLoad () {
      this.queryData.page++
      let res = await queryProduct(this.queryData)
      if (res.code === "1") {
        this.queryData.keyWord = ""
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
    searchOrderList (item) {
      if (item == "") {
        return
      }
      this.$refs.input.blur()
      if (item != "" && item != undefined) {
        this.list = []
        this.localStorageList.push(item)
        this.localStorageList = this.unique(this.localStorageList)
        if (this.localStorageList.length > 20) {
          this.localStorageList = this.localStorageList.slice(-20)
        }
        localStorage.setItem("localList", this.unique(this.localStorageList))
      }

      // page置0
      this.queryData.page = 0
      this.finished = false

      // 重新加载数据
      this.loading = true
      this.onLoad()
    },
    unique (arr) {
      for (var i = 0; i < arr.length; i++) {
        for (var j = i + 1; j < arr.length; j++) {
          if (arr[i] == arr[j]) {
            //第一个等同于第二个，splice方法删除第二个
            arr.splice(j, 1)
            j--
          }
        }
      }
      return arr
    },
    hisItem (ite) {
      this.queryData.keyWord = ite;
      this.list = [];
      this.searchOrderList()

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
        this.$store.commit("config/reset_totForm")
        this.$store.commit("config/reset_formRule")
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
#searchList {
  background: #fff;
  font-family: PingFangSC-Medium, PingFang SC;
  padding-top: 1.22667rem;
  // height: calc(100vh - 120px) !important;
  height: 100% !important;
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
  .title-container1 {
    // position: fixed;
    // top: 0;
    // left: 0;
    // right: 0;
    // z-index: 99;
  }
  .van-tab {
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: #fff;
  }

  .title-container1 {
    background: #3b8afe;
    [class*="van-hairline"]::after {
      border: none !important;
    }
    /deep/ .van-nav-bar__title {
      color: #fff !important;
    }
    .arrow-left {
      position: fixed;
      top: 14px;
      left: 10px;
      z-index: 100;
    }
    .search {
      position: absolute;
      top: 16px;
      right: 10px;
      z-index: 1000;
    }
    .van-nav-bar {
      background: #3b8afe;
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
    }
    .van-tabs__nav {
      background: none;
    }
    .searchOrder {
      width: 100%;
      height: 1.22667rem;
      // background: rgba(255, 255, 255, 1);
      display: flex;
      justify-content: center;
      align-items: center;
      position: relative;
      margin: 0 auto;
      z-index: 2;
      .searchImg {
        position: absolute;
        top: 15px;
        left: 12%;
        z-index: 1000;
      }
      input {
        width: 80%;
        height: 30px;
        background: rgba(247, 247, 247, 1);
        border-radius: 15px;
        outline: none;
        border: none;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(136, 136, 136, 1);
        margin: 0 auto;
        text-indent: 10%;
        position: absolute;
        box-sizing: border-box;
      }
      span {
        width: 10%;
        position: absolute;
        right: 0;
        color: #fff;
        text-align: center;
        padding: 0.2rem 0;
      }
      div {
        width: 28px;
        height: 20px;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
        line-height: 20px;
        margin-left: 27px;
      }
    }
  }
  .van-tab--active {
    font-size: 17px;
    color: #fff;
  }
  .van-tabs__line {
    width: 28px !important;
    height: 5px !important;
    background: rgba(59, 138, 254, 1);
    border-radius: 6px;
  }
  .searchWord {
    width: 100%;
    height: 30px;
    line-height: 30px;
    color: #333;
    font-size: 14px;
    background: #fff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .van-icon {
      font-size: 16px;
      margin-right: 0.5rem;
    }
  }
  .ul {
    display: flex;
    flex-wrap: wrap;
    margin: 0;
    background: #f7f7f7;
    .li {
      text-align: center;
      font-size: 0.37333rem;
      padding: 0 0.3rem;
      border-radius: 0.3rem;
      margin: 0 0.1rem;
      height: 1rem;
      line-height: 1rem;
    }
  }
  .productList {
    background: #ffffff;
    padding: 0 16px;
    .van-card {
      background: none;
      padding: 15px 0;
    }
    .product {
      height: 120px;
      border-bottom: 1px solid #e5e6e8;
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
      position: absolute;
      right: 0;
      bottom: 15px;
    }
  }
}
</style>
