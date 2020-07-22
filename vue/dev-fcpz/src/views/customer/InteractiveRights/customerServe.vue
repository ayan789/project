<template>
  <div class="customerServe">
    <div class="customerServetop">
      <ul v-if="custSourceType == 2">
        <li @click="toNoAuthorizationView">
          <div class="servetopImg">
            <img
              style="width:100%;height:100%"
              src="@/assets/customer/customerEquity/illegal@3x.png"
              alt="edit"
            />
          </div>
          <div class="serveDescribe">查违章</div>
        </li>
        <li>
          <div class="servetopImg servetopImg2" @click="goTell">
            <img
              style="width:100%;height:100%"
              src="@/assets/customer/customerEquity/report@3x.png"
              alt="edit"
            />
          </div>
          <div class="serveDescribe">协助报案</div>
        </li>
        <li @click="toClaimOrdersView">
          <div class="servetopImg servetopImg2">
            <img
              style="width:100%;height:100%"
              src="@/assets/customer/customerEquity/query@3x.png"
              alt="edit"
            />
          </div>
          <div class="serveDescribe">理赔查询</div>
        </li>
      </ul>
      <div v-if="custSourceType == 2" class="customerServecenter">
        <div
          class="servecenter"
          v-for="(item, index) in getQueryRemindTaskList.remindList"
          :key="index"
        >
          <div class="picture">
            <img
              style="width:100%;height:100%"
              :src="Equity"
              alt="edit"
              v-if="item.remindType == 1"
            />
            <img
              style="width:100%;height:100%"
              :src="Onbirthday"
              alt="edit"
              v-else-if="item.remindType == 2"
            />
            <img
              style="width:100%;height:100%"
              :src="Policy"
              alt="edit"
              v-else-if="item.remindType == 3"
            />
            <img
              style="width:100%;height:100%"
              :src="Equity"
              alt="edit"
              v-else-if="item.remindType == 4"
            />
            <img
              style="width:100%;height:100%"
              :src="Policy"
              alt="edit"
              v-else-if="item.remindType == 5"
            />
          </div>
          <div class="serveGive">
            <div class="washCar">{{ item.remindName }}</div>
            <div class="pashDay">{{ item.remindTitle }}</div>
          </div>
          <div
            class="serveRemind"
            v-if="item.remindType == 1"
            @click="toRouter(1, item)"
          >
            查看
          </div>
          <!--<div class="serveRemind" v-else-if="item.remindType == '2'" @click="toRouter(2,item)">-->
          <!--提醒-->
          <!---->
          <!--</div>-->
          <ReasonsInsure
            v-else-if="item.remindType == '2'"
            :showProp="showProp"
            :remind="item"
          />
          <div
            class="serveRemind"
            v-else-if="item.remindType == '3'"
            @click="toRouter(3, item)"
          >
            送上祝福
          </div>
          <div
            class="serveRemind"
            v-else-if="item.remindType == '4'"
            @click="toRouter(4, item)"
          >
            提醒
          </div>
          <div
            class="serveRemind"
            v-else-if="item.remindType == '5'"
            @click="toRouter(5, item)"
          >
            提醒
          </div>
        </div>
      </div>
      <div
        v-if="custSourceType == 1"
        class="nodate-image"
        :style="{ backgroundImage: 'url(' + noDataIMG + ')' }"
      ></div>
      <div
        v-if="custSourceType == 1"
        style="display: flex;justify-content: center;margin-top: -10px"
      >
        <span style="color:#999999">暂无数据</span>
      </div>

      <div class="customerServeactivity">
        <div class="product">
          <productRecommendation :product="product"></productRecommendation>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import productRecommendation from "@/components/productRecommendation.vue";
import Equity from "@/assets/customer/customerEquity/equity@3x.png";
import Policy from "@/assets/customer/customerEquity/policy@3x.png";
import Onbirthday from "@/assets/customer/customerEquity/onbirthday@3x.png";
import ReasonsInsure from "./reasonsInsure.vue";
import noDataIMG from "../../../assets/customer/customerEquity/noData.png";
import { trackMap } from "@src/utils/index.js";
export default {
  props: {
    product: Array,
    getQueryRemindTaskList: Object,
    custSourceType: String
  },
  computed: {
    ...mapState(["customerInfoData"])
  },
  created() {
    console.info(this.customerInfoData.CustInfo, "customerInfoData");
  },
  beforeUpdate() {},
  components: {
    productRecommendation,
    ReasonsInsure
  },
  data() {
    return {
      Equity,
      Policy,
      Onbirthday,
      showProp: false, //显示续保提醒
      noDataIMG: noDataIMG
    };
  },
  methods: {
    toRouter(index, item) {
      switch (index) {
        case 1: //出險提醒
          this.$router.push({
            name: "customerClaimProgress",
            query: { item: item }
          });
          break;
        case 2: //续保提醒
          this.showProp = true;
          // this.$router.push({ name: 'registeredView', query: { item: item } });
          break;
        case 3: //生日提醒
          this.$router.push({
            name: "birthdayCelebrate",
            query: { item: item }
          });
          break;
        case 4: //权益提醒
          this.$router.push({ name: "registeredView", query: { item: item } });
          break;
        case 5: //待注册提醒
          this.$router.push({
            name: "RegisteredManView",
            query: { item: item }
          });
          break;
      }
    },
    goTell() {
      this.$native.getPhoneCall("95590");
      // this.$native.getPhoneCall("15618999634");
      let custInfo = this.customerInfoData.CustInfo;
      let params = {
        custNo: custInfo.custNo,
        createUser: localStorage.getItem("userCode"),
        type: "10",
        title: "协助报案",
        userCode: localStorage.getItem("userCode")
      };
      //埋点
      trackMap("c_customer_info_assist_reporting", "客户信息协助报案", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "客户管理"
      });
      this.$store.dispatch("customerData/addOrUpdateInteractions", params);
    },

    toClaimOrdersView() {
      let custInfo = this.customerInfoData.CustInfo;
      console.log(custInfo, "custInfo===");
      //传给理赔
      this.item = {
        custNo: custInfo.custNo, //客户号
        userCode: custInfo.userCode, //业务号,
        custType: custInfo.custType
      };
      //埋点
      trackMap("c_customer_info_claim_enquiry", "客户信息理赔查询", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "客户管理"
      });
      //console.log(this.item, "toClaimOrdersView理赔-===>");
      this.$router.push({ name: "claimOrders", query: { item: this.item } });
      let params = {
        custNo: custInfo.custNo,
        createUser: localStorage.getItem("userCode"),
        type: "11",
        title: "理赔查询",
        userCode: localStorage.getItem("userCode")
      };
      this.$store.dispatch("customerData/addOrUpdateInteractions", params);
    },
    toNoAuthorizationView() {
      let custInfo = this.customerInfoData.CustInfo;
      //传给违章
      this.items = {
        custNo: custInfo.custNo, //客户号
        userCode: custInfo.userCode, //业务号
        carNo: custInfo.carNo, //车牌号
        frameNo: custInfo.frameNo, //车架号
        engineNo: custInfo.engineNo //发动机号
      };
      //埋点
      trackMap("c_customer_info_violation_enquiry", "客户信息违章查询", {
        c_salesperson_type: "N",
        c_market_type: "N",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "客户管理"
      });
      //console.log(this.items, "toNoAuthorizationView违章-===>>");
      this.$router.push({
        name: "noAuthorization",
        query: { item: this.items }
      });
    }
  }
};
</script>
<style lang="less" scoped>
.customerServe {
  background: #fff;
  position: static;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: 60vh;
  /*padding-top: 50px;*/

  .nodate-image {
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 300px;
    margin: 0px auto;
    height: 300px;
  }

  .customerServetop {
    padding-top: 10px;

    ul {
      margin: 0 15px;
      height: 91px;
      /*border-bottom: 1px solid #e5e6e8;*/
      overflow: hidden;

      li {
        height: 91px;
        float: left;
        padding-top: 16px;
        margin-left: 48px;

        .servetopImg {
          width: 40px;
          height: 40px;
          margin-bottom: 3px;
          margin-left: 5px;
        }

        .servetopImg2 {
          margin-left: 10px;
        }

        .serveDescribe {
          height: 26px;
          font-size: 14px;
          font-weight: 500;
          color: rgba(102, 102, 102, 1);
          line-height: 26px;
        }
      }
    }

    .customerServecenter {
      /*margin-top: 20px;*/
      /*margin-bottom: 15px;*/
      margin: 20px 20px 15px 20px;
      padding: 0 15px;
      box-shadow: 0px 0.2px 7px 7px rgba(91, 91, 91, 0.03);

      .servecenter {
        /*width: 345px;*/
        /*height: 72px;*/
        background: rgba(255, 255, 255, 1);
        border-radius: 4px;
        padding: 14px 0px;
        display: flex;
        flex-direction: row;
        align-items: center;

        .picture {
          width: 28px;
          height: 28px;
          margin-right: 11px;
        }

        .serveGive {
          flex: 1;
          padding-left: 6px;

          .washCar {
            /*height: 26px;*/
            font-size: 15px;
            font-weight: 500;
            color: rgba(51, 51, 51, 1);
            /*line-height: 26px;*/
          }

          .pashDay {
            font-size: 13px;
            font-weight: 500;
            color: rgba(153, 153, 153, 1);
            /*line-height: 26px;*/
          }
        }

        .serveRemind {
          /*margin-top: 13px;*/
          width: 60px;
          font-weight: 500;
          color: #3b8afe;
          padding: 4px 0px;
          border-radius: 5px;
          font-size: 10px;
          border: 1px solid #296cf3;
          text-align: center;
        }
      }
    }

    .customerServeactivity {
      margin: 0 15px;
      overflow: hidden;

      .activity {
        float: left;
        height: 210px;
        background: rgba(255, 255, 255, 1);
        box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        padding: 8px 8px 0px 9px;

        .img {
          width: 148px;
          height: 114px;
          background: pink;
          margin-bottom: 4px;
        }

        .noAccident {
          height: 26px;
          font-size: 14px;
          font-weight: 500;
          color: rgba(51, 51, 51, 1);
          line-height: 26px;
        }

        .activityAccident {
          font-weight: 500;
          color: rgba(162, 162, 162, 1);
          margin-bottom: 4px;
        }

        .price {
          height: 26px;
          font-size: 18px;
          font-weight: bold;
          color: rgba(254, 103, 104, 1);
          line-height: 26px;
          overflow: hidden;

          .activityPrice {
            float: left;
          }

          .activityFont {
            float: left;
            margin-left: 4px;
            padding-top: 2px;
            color: #999999;
            font-size: 12px;
          }
        }
      }

      .activity:nth-child(2n) {
        float: right;
      }
    }
  }
}
</style>
