<!--
 * @Description: 支付转发确认页面
 * @Date: 2020-06-19 15:39:48
 * @Author: zhangminjie
 * @LastEditTime: 2020-06-19 21:35:13
 * @FilePath: \nonCarCongfig\src\views\nonCar\dynamicPayment\prPaymentForward.vue
-->
<template>
  <div class="paymentForward" id="paymentForward">
    <globalNav title="保单支付" @clickRight="back"></globalNav>
    <div v-show="!isShow">
      <van-row type="flex" class="pageHeader">
        <van-col span="6"
          ><img src="@/assets/nonCar/propertyInsurance/infoInput.png" alt="man"
        /></van-col>
        <van-col span="18">
          <h2>{{ insuranceInfo.productName }}</h2>
          <p>
            保障期限：{{ insuranceInfo.startDate.split(" ")[0] }}至{{
              insuranceInfo.endDate.split(" ")[0]
            }}
          </p>
        </van-col>
      </van-row>
      <van-cell-group
        :border="isNoBorder"
        class="list"
        v-for="(item, index) in elementList"
        :key="index"
      >
        <van-cell :border="!isNoBorder">
          <div slot="title" class="title">{{ item.groupName }}</div>
          <div slot="icon" class="titleIcon"><img :src="toubao" /></div>
        </van-cell>
        <van-cell
          :border="!isNoBorder"
          v-for="(element, i) in item.elementList"
          :key="i"
          v-show="item.elementList.length > 0"
        >
          <div slot="title" class="content">
            {{ element.label }}
          </div>
          <div slot="default" class="content">{{ element.displayValue }}</div>
        </van-cell>
      </van-cell-group>
      <van-cell-group :border="!isNoBorder" class="list">
        <van-cell :border="!isNoBorder">
          <div slot="title" class="title">保障内容</div>
          <div slot="icon" class="titleIcon"><img :src="toubao" /></div>
        </van-cell>
        <div v-for="(item, index) in formulaList" :key="index">
          <van-cell :border="!isNoBorder">
            <div slot="title" class="content">{{ item.productName }}</div>
            <div slot="default" class="content">
              {{ item.insured }}{{ " " + item.insureUnit }}
            </div>
          </van-cell>
        </div>
      </van-cell-group>
      <van-cell-group class="list" v-show="Object.keys(invoiceInfo).length > 0">
        <van-cell :border="!isNoBorder">
          <div slot="title" class="title">开票信息</div>
          <div slot="icon" class="titleIcon"><img :src="carInfo" /></div>
        </van-cell>
        <van-cell title="发票抬头" :value="invoiceInfo.companyName" />
        <van-cell title="纳税人识别码" :value="invoiceInfo.taxPayerNo" />
        <van-cell title="联系电话" :value="invoiceInfo.taxMobile" />
        <van-cell title="邮箱地址" :value="invoiceInfo.taxEmail" />
      </van-cell-group>
      <van-row type="flex" class="pageBottom">
        <van-col span="12">
          <van-button type="default" class="btn1"
            >保费<span class="strong">{{ sumPremium }}</span
            >元</van-button
          ></van-col
        >
        <van-col span="12">
          <van-button type="primary" class="btn" @click="showPayment"
            >确认无误,去支付</van-button
          ></van-col
        >
      </van-row>
    </div>
    <paymentMethodSheet
      v-show="isShow"
      :isShow="isShow"
      @paymentEvent="paymentEvent"
      @closeEvent="closeEvent"
    ></paymentMethodSheet>
  </div>
</template>
<script>
// 引用组件通用方法
const path = require("path");
const files = require.context("@/components/", false, /\.vue$/);
const modules = {};
files.keys().forEach(key => {
  const name = path.basename(key, ".vue");
  modules[name] = files(key).default || files(key);
});
import { getOrderDetail } from "@src/http/module/order";
import { getPayUrl } from "@src/http/module/nonCar";
import paymentMethodSheet from "@src/views/nonCar/common/paymentMethodSheet";
import globalNav from "../../../components/globalNav";

export default {
  components: { globalNav, paymentMethodSheet },
  name: "nonPaymentForward",
  data() {
    return {
      toubao: require("@/assets/nonCar/worryEdit/toubao.png"),
      beibao: require("@/assets/nonCar/worryEdit/beibao.png"),
      car: require("@/assets/nonCar/worryEdit/car.png"),
      carInfo: require("@/assets/nonCar/worryEdit/carInfo.png"),
      payCard: require("@/assets/payment/payCard.png"),
      payCode: require("@/assets/payment/payCode.png"),
      payLogo: require("@/assets/payment/payLogo.png"),
      payShare: require("@/assets/payment/payShare.png"),
      isNoBorder: false,
      isShow: true,
      insurePersonInfo: {},
      // 页面数据
      queryData: {},
      arrary: [],
      insuranceInfo: {
        productName: "",
        startDate: "",
        endDate: ""
      },
      elementList: [],
      list: [],
      sumPremium: 0,
      formulaList: [],
      invoiceInfo: {}
    };
  },

  mounted() {
    this.isShow = false;
    this.getOrderDetailInfo();
  },
  methods: {
    closeEvent() {
      this.isShow = false;
    },
    // 展示支付方式选择框
    showPayment() {
      this.isShow = true;
    },
    getList(obj) {
      let arr = [];
      for (let i in obj) {
        let o = {};
        o[i] = obj[i];
        return arr.push(o);
      }
    },
    back() {
      if (window.WebViewJavascriptBridge) {
        console.log("WebViewJavascriptBridge-2222222222");
        this.$native.closeWeb();
      } else {
        this.$router.push({ path: "/" });
      }
      this.$destroy();
    },
    // 支付方式
    paymentEvent(item) {
      switch (item.name) {
        case "微信支付": {
          let params = {
            proposalNo: this.queryData.insureNo,
            paymentNo: this.queryData.payApplyNo,
            checkNo: this.queryData.identityCode,
            flag: "7"
          };
          getPayUrl(params).then(
            res => {
              if (res.code === "1" && res.data) {
                console.log(res.data.url);
                window.location.href = res.data.url;
                return;
              }
            },
            error => {}
          );
          break;
        }
        case "银行卡快捷支付": {
          let params = {
            proposalNo: this.queryData.insureNo,
            paymentNo: this.queryData.payApplyNo,
            checkNo: this.queryData.identityCode,
            flag: "6"
          };
          getPayUrl(params).then(
            res => {
              if (res.code === "1" && res.data) {
                window.location.href = res.data.url;

                return;
              }
            },
            error => {}
          );
          break;
        }
        default:
          break;
      }
    },
    // 获取订单详情信息
    getOrderDetailInfo() {
      let obj = { orderId: decodeURI(this.$GetRequest("orderId", true)) };
      getOrderDetail(obj)
        .then(res => {
          this.queryData = JSON.parse(JSON.stringify(res.data));
          let {
            productName,
            startDate,
            endDate,
            productList,
            elementList,
            sumPremium,
            invoiceInfo
          } = res.data;
          this.insuranceInfo = {
            productName,
            startDate: startDate ? startDate.split(" ")[0] : "",
            endDate: endDate ? endDate.split(" ")[0] : ""
          };
          this.formulaList.splice(0, this.formulaList.length);
          if (productList && productList.length > 0) {
            for (let i = 0, len = productList.length; i < len; i++) {
              this.formulaList.push(productList[i]);
            }
          }
          this.elementList.splice(0, this.elementList.length);
          this.elementList = elementList;
          this.sumPremium = sumPremium;
          this.invoiceInfo = invoiceInfo ? invoiceInfo : {};
        })
        .catch(() => {});
    }
  }
};
</script>
<style lang="less" scoped>
#paymentForward {
  padding: 75px 0 50px 0;
  height: auto;

  div {
    box-sizing: border-box;
  }

  .pageHeader {
    // margin-top: 10px;
    min-height: 70px;
    background: rgba(255, 255, 255, 1);
    padding: 10px 5px;
    .title {
      font-size: 16px;
      font-family: PingFangSC-Semibold, PingFang SC;
      font-weight: 600;
      color: rgba(51, 51, 51, 1);
      line-height: 20px;
    }
    img {
      width: 72px;
      height: auto;
      vertical-align: inherit;
    }
    h2 {
      font-size: 18px;
      font-family: PingFangSC-Semibold, PingFang SC;
      font-weight: 600;
      color: rgba(51, 51, 51, 1);
      line-height: 25px;
    }
    p {
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      line-height: 19px;
    }
  }
  .list {
    background: rgba(255, 255, 255, 1);
    .titleIcon {
      width: 22px;
      height: 22px;
      margin-right: 8px;
      img {
        width: 100%;
        height: auto;
      }
    }
    .title {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 22px;
    }
    .content {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
      line-height: 30px;
    }
    .red {
      color: #fc820e;
    }
  }
  .pageBottom {
    height: 46px;
    background: rgba(255, 255, 255, 1);
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
    line-height: 14px;
    position: fixed;
    bottom: 0;
    width: 100%;
    left: 0;
    .btn {
      height: 100%;
      width: 100%;
      background: linear-gradient(
        125deg,
        rgba(42, 128, 255, 1) 0%,
        rgba(42, 165, 255, 1) 100%
      );
    }
    .btn1 {
      width: 100%;
      height: 100%;
    }
    .strong {
      font-size: 17px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(252, 130, 14, 1);
      line-height: 24px;
    }
  }
}
</style>
