<template>
  <div class="claimOrders">
    <globalNav title="理赔查询" @clickBack="clickBack"/>
    <!-- <div class="search" @click="clickSearch">
      <img src="@/assets/common/search.png" />
    </div> -->
    <div class="contentStyle">
      <div class="list" v-if="customerData.getQueryIndemnifyData.length > 0">
        <ul
          v-for="(item, index) in customerData.getQueryIndemnifyData"
          v-bind:key="index"
        >
          <div class="item" @click="clickNext(item)">
            <div id="topTitle">
              <h3>保单号：{{ item.policyNo }}</h3>
              <div id="topImg">
                <img src="@/assets/common/next.png"/>
              </div>
            </div>
            <div id="line"></div>
            <div class="subTitle">
              <h3>车牌号</h3>
              <h3 class="value">{{ item.licenseNo }}</h3>
            </div>
            <div class="subTitle">
              <h3>被保险人</h3>
              <h3 class="value">{{ item.insuredName }}</h3>
            </div>
            <div class="subTitle">
              <h3>报案号</h3>
              <h3 class="value">{{ item.registNo }}</h3>
            </div>
            <div class="subTitle">
              <h3>报案时间</h3>
              <h3 class="value">{{ item.reportDate }}</h3>
            </div>
            <div class="subTitle">
              <h3>状态</h3>
              <div class="status">
                <h3
                  class="value"
                  :style="item.curHandlestatus != '10' ? processStyle : finishStyle"
                >
                  {{ item.curHandlestatus != "10" ? "理赔处理中" : "已结案" }}
                </h3>
              </div>
            </div>
            <div class="subTitle" v-if="item.curHandlestatus == '10'">
              <h3>赔付金额</h3>
              <h3 class="value sumPaid">{{ item.sumPaid }}</h3>
            </div>
            <div id="space"></div>
          </div>
        </ul>
      </div>

      <div class="more" v-if="customerData.getQueryIndemnifyData.length > 0" @click="showMore">
        <h3 id="moreLeft">已显示近2年车险理赔</h3>
      </div>
      <div class="noOrders" v-if="customerData.getQueryIndemnifyData.length == 0">
        <img src="@/assets/common/default.png"/>
        <h3>暂无理赔案件哦！</h3>
      </div>
    </div>
  </div>
</template>

<script>
// 理赔查询-车险理赔 理赔查询-查询
import globalNav from "@/components/globalNav.vue";
import { getQueryIndemnifyInfos } from "@src/http/module/customerApi.js";
import { mapState } from "vuex";
export default {
  name: "claimOrders",
  components: {
    globalNav
  },
  props: {
    // msg: String,
  },

  computed: {
    ...mapState(["customerData"])
  },

  created() {
    this.requestData();
  },

  data() {
    return {
      processStyle: {
        color: "rgba(3,204,210,1)",
        background: "rgba(19,216,222,0.1)",
        width: "84px"
        // float:'right',
      },
      finishStyle: {
        color: "#3B8AFE"
      },
      datas: []
    };
  },
  methods: {
    clickBack() {
      this.$router.back();
    },
    //点击导航条搜索：
    clickSearch: function () {
      //console.log("点击了一下搜索");
    },
    //点击item事件
    clickNext: function (item) {
      this.$router.push({ name: 'customerClaimProgress', query: { item: item } });
    },
    //点击了查看更多车险理赔
    showMore: function () {
      //console.log("查看更多车险理赔");
    },

    async requestData() {
      const { custNo, userCode, policyNo, custType } = this.$route.query.item;
      let params = {
        custNo: custNo,//客户号
        userCode: localStorage.getItem("userCode"),//业务号
        custType: custType,
        policyNo: policyNo
      };

      this.$store.dispatch('customerData/get_QueryIndemnifyInfos', params);
    }
  }
};
</script>
<style scoped>
* {
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  font-size: 14px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
}

.claimOrders {
  position: fixed;
  width: 100%;
}

.contentStyle {
  margin-top: 80px;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: calc(100vh - 65px);
  background-color: white;
}

h3 {
  overflow: hidden;
}

body {
  background: #f7f7f7;
  width: 100%;
  height: 100%;
  /* position: absolute; */
}

.search {
  width: 44px;
  height: 44px;
  position: fixed;
  top: 0px;
  right: 10px;
  z-index: 99;
}

.search > img {
  margin-top: 6px;
}

ul {
  background: white;
  border-radius: 8px;
  box-shadow: 4px 2px 24px 0px rgba(19, 63, 104, 0.12);
  margin: 10px 15px 0px 15px;
}

.list {

  padding: 5px 0 0 0;
}

#topTitle {
  display: inline-block;
  width: 100%;
}

#topTitle > h3,
#topImg {
  float: left;
  height: 48px;
}

#topTitle > h3 {
  width: 80%;
  line-height: 48px;
  text-indent: 1em;
  text-align: left;
  font-size: 14px;
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
}

#topTitle > div {
  width: 20%;
  /* float: right; */
  text-align: center;
}

#topImg > img {
  object-fit: none;
  height: 48px;
  margin-left: 15px;
  float: right;
}

#line {
  margin: 0px 14px 5px 14px;
  height: 1px;
  background: #f7f7f7;
  float: none;
}

.subTitle {
  height: 28px;
  /*text-align: left;*/
  /*padding: 0 14px 0 14px;*/
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
}

.subTitle > h3 {
  height: 28px;
  line-height: 28px;
  margin-right: 14px;
  margin-left: 14px;
}

.value {
  height: 28px;
  line-height: 28px;
  margin-right: 14px;
}

.status {
  float: left;
  border-radius: 24px;
  width: 50%;
  height: 24px;
  margin-top: 2px;
}

.status > .value {
  float: right;
  line-height: 24px;
  height: 24px;
  border-radius: 24px;
  text-align: center;
}

.sumPaid {
  color: #3b8afe;
}

#space {
  height: 10px;
}

.more {
  width: 100%;
  height: 40px;
  margin: 27px 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

#moreLeft {
  /*text-align: right;*/
  color: #999999;
}

#moreRight {
  text-decoration: underline;
  color: #3b8afe;
}

.noOrders {
  margin: 0 auto;
  margin-top: 141px;
  color: #999999;
  font-size: 14px;
  font-weight: 400;
}

.noOrders > img {
  display: block;
  /* width: 128px;
  height:128px; */
  margin: 0 auto;
}

.noOrders > h3 {
  text-align: center;
}
</style>
