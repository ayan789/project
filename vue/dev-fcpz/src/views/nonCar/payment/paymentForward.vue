<!--
 * @Description: 支付转发确认页面
 * @Date: 2020-04-06 16:52:52
 * @Author: zhangminjie
 * @LastEditTime: 2020-04-21 10:28:57
 * @FilePath: \vue-sales\src\views\nonCar\payment\paymentForward.vue
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
          <h2>{{ queryData.insuranceName }}</h2>
          <p>
            保障期限：{{ queryData.startDate.split(" ")[0] }}至{{
              queryData.endDate.split(" ")[0]
            }}
          </p>
        </van-col>
      </van-row>
      <van-cell-group class="info">
        <van-cell title="投保人信息" class="title">
          <div slot="icon" class="titleIcon"><img :src="toubao" />></div>
        </van-cell>
        <van-cell
          title="姓名"
          :value="queryData.applicantName"
          value-class="textStyle"
        />
        <van-cell
          title="证件类型"
          :value="queryData.appliIdentifyType"
          value-class="textStyle"
        />
        <van-cell title="证件号码" :value="queryData.appliIdentify" />
        <van-cell title="手机号码" :value="queryData.mobileNo" />
        <van-cell title="邮箱" :value="queryData.email" />
      </van-cell-group>
      <van-cell-group class="info">
        <van-cell title="被保人信息" class="title">
          <div slot="icon" class="titleIcon"><img :src="beibao" />></div>
        </van-cell>
        <van-cell
          title="是否同投保人"
          :value="queryData.relateInsured == '01' ? '是' : '否'"
          value-class="textStyle"
        />
        <van-cell
          title="姓名"
          :value="queryData.insuredName"
          value-class="textStyle"
        />
        <van-cell title="证件类型" :value="queryData.insuredIdentifyType" />
        <van-cell title="证件号码" :value="queryData.insuredIdentify" />
        <van-cell title="手机号码" :value="queryData.insuredMobile" />
      </van-cell-group>
      <van-cell-group class="info">
        <van-cell title="运输工具" class="title">
          <div slot="icon" class="titleIcon"><img :src="car" />></div>
        </van-cell>
        <van-cell title="车型" :value="queryData.carInfoVO.vehicleModelCode" />
        <van-cell title="车牌号" :value="queryData.carInfoVO.licenseplateno" />
        <van-cell title="发动机号" :value="queryData.carInfoVO.engineNo" />
        <van-cell
          title="核定载重量/总牵引质量(吨)"
          :value="queryData.carInfoVO.approvalQuality"
        />
        <van-cell title="车龄" :value="queryData.carInfoVO.vehicleAge" />
        <van-cell title="厂牌型号" :value="queryData.carInfoVO.vehicleBrand" />
      </van-cell-group>
      <van-cell-group class="info">
        <van-cell title="保障内容" class="title">
          <div slot="icon" class="titleIcon"><img :src="beibao" />></div>
        </van-cell>
        <van-cell
          v-for="(item, index) in arrary"
          :title="item.name"
          :value="item.value"
          :key="index"
        />
      </van-cell-group>
      <van-cell-group
        class="info"
        v-show="
          queryData.invoiceInfo && Object.keys(queryData.invoiceInfo).length > 0
        "
      >
        <van-cell title="开票信息" class="title">
          <div slot="icon" class="titleIcon"><img :src="carInfo" />></div>
        </van-cell>
        <van-cell title="发票抬头" :value="queryData.invoiceInfo.companyName" />
        <van-cell
          title="纳税人识别码"
          :value="queryData.invoiceInfo.taxPayerNo"
        />
        <van-cell title="联系电话" :value="queryData.invoiceInfo.taxMobile" />
        <van-cell title="邮箱地址" :value="queryData.invoiceInfo.taxEmail" />
      </van-cell-group>
      <van-row type="flex" class="pageBottom">
        <van-col span="12">
          <van-button type="default" class="btn1"
            >保费<span class="strong">{{ queryData.sumPremium }}</span
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
import { Toast } from "vant";
import { getPayUrl, applyPay, getForwardUrl } from "@src/http/module/nonCar";
import paymentMethodSheet from "@src/views/nonCar/common/paymentMethodSheet";
import { forwardPayProductInsureInfo } from "@src/http/module/order";
import globalNav from "../../../components/globalNav";

// import personInfoCard from '../../nonCar/common/personInfoCard'

export default {
  // components: { personInfoCard, globalNav },
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
      // sumPremium: this.$route.query.sumPremium, //总金额
      // proposalNo: this.$route.query.proposalNo, //投保单号
      // productName: this.$route.query.productName, //投保产品
      // paymentNo: this.$route.query.paymentNo, //支付号
      // checkNo: this.$route.query.checkNo, //校验码
      isShow: true,
      insurePersonInfo: {},
      // 页面数据
      queryData: {
        insureNo: "",
        insuranceName: "",
        startDate: "",
        endDate: "",
        applicantName: "",
        appliIdentifyType: "",
        appliIdentify: "",
        insuredName: "",
        insuredIdentifyType: "",
        insuredIdentify: "",
        sumPremium: "",
        productCode: "",
        invoiceInfo: {
          taxMobile: "",
          taxEmail: "",
          companyName: "",
          taxPayerNo: ""
        },
        carInfoVO: {
          licenseplateno: "",
          engineNo: "",
          approvalQuality: "",
          vehicleAge: "",
          vehicleBrand: "",
          vehicleModelCode: ""
        },
        formulas: [
          {
            addRiskAmountJson: ""
          }
        ]
      },
      arrary: []
    };
  },
  created() {
    this.isShow = false;
    this.initData();
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
    initData() {
      let obj = {
        proposalNo: this.$GetRequest("proposalNo", true),
        forwardDate: this.$GetRequest("forwardDate", true),
        // forwardDate: "7119e1bcdbde237f3a1b894e62d1f87c",
        // proposalNo:
        //   "bd15f2abd97b24e889a2aca778780fc5743122dab8da052a62f3fdd51874a63e",
        status: 0
      };
      forwardPayProductInsureInfo(obj).then(res => {
        this.queryData = res.data;
        // 投保人证件类型
        let appliIdentifyType = this.$getCodeOrName(
          "hyIdCardList",
          this.queryData.appliIdentifyType
        );
        this.queryData.appliIdentifyType = appliIdentifyType
          ? appliIdentifyType
          : "";
        // 被保人证件类型
        let insuredIdentifyType = this.$getCodeOrName(
          "hyIdCardList",
          this.queryData.insuredIdentifyType
        );
        this.queryData.insuredIdentifyType = insuredIdentifyType
          ? appliIdentifyType
          : "";
        // 车辆信息
        let vehicleModelCode = this.$getCodeOrName(
          "vehicleModelCodeList",
          this.queryData.carInfoVO.vehicleModelCode
        );
        this.queryData.carInfoVO.vehicleModelCode = vehicleModelCode
          ? vehicleModelCode
          : "";
        //保障内容
        let [{ addRiskAmountJson }] = this.queryData.formulas;

        let arr = [];
        for (let [k, v] of Object.entries(JSON.parse(addRiskAmountJson))) {
          if (k.indexOf("#") >= 0) {
            k = k.slice(3, k.length);
          }
          v = v / 10000;
          arr.push({ name: k, value: v + "万" });
        }

        this.queryData.formulas[0].myAddRiskAmountJson = [];
        this.queryData.formulas[0].myAddRiskAmountJson = arr;
        this.arrary = arr;
        if (
          !this.queryData.invoiceInfo ||
          Object.keys(this.queryData.invoiceInfo).length == 0
        ) {
          this.queryData.invoiceInfo = {
            taxMobile: "",
            taxEmail: "",
            companyName: "",
            taxPayerNo: ""
          };
        }
        // console.log(this.queryData);
      });
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
        case "二维码支付": {
          let params = {
            proposalNo: this.queryData.insureNo,
            paymentNo: this.queryData.payApplyNo,
            checkNo: this.queryData.identityCode,
            sumPremium: this.queryData.sumPremium,
            productName: this.queryData.productName, //投保产品
            applicantName: this.queryData.applicantName, //投保人
            mobileNo: this.queryData.mobileNo, //投保人手机号码
            insuredName: this.queryData.insuredName, //被投保人姓名
            insuredMobile: this.queryData.insuredMobile //被投保人电话
          };

          //"二维码支付"
          //1、二维码支付，支持微信、支付宝当面扫描支付，也可通过微信、支付宝将此二维码支付页面给客户自助支付
          return this.$router.push({
            name: "nonPaymentByQRCode",
            query: {
              item: JSON.stringify(params)
            }
          });
        }
        case "转发支付": {
          let params = {
            proposalNo: this.queryData.insureNo,
            paymentNo: this.queryData.payApplyNo,
            checkNo: this.queryData.identityCode,
            flag: "1",
            productName: this.queryData.productName,
            sumPremium: this.queryData.sumPremium
          };
          // this.$router.push({
          //   name: "nonPaymentForward",
          //   query: params
          // });
          //"转发支付"
          //2、转发支付，通过微信将订单转发给客户进行自助支付
          getForwardUrl(params).then(
            res => {
              if (res.code === "1" && res.data) {
                // if (window.WebViewJavascriptBridge) {
                //   // window.location.href = res.data.url;
                //   console.log(res.data);
                //   //调用接口参数
                //   const jsonObject = {
                //     sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
                //     shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
                //     title: "投保订单支付", //分享标题
                //     imgURL: this.$rootPath + "/resources/images/icon/logo.png", //分享图片地址, 与image二选一
                //     url:
                //       this.$rootPath +
                //       "/nonCar/payment/paymentForward" +
                //       res.data, //分享链接
                //     desc: "尊敬的客户，您的投保订单已生成，可直接在线支付" //分享描述
                //   };

                //   //调用壳方法，传递调用参数和接受响应数据
                //   this.$native.share(jsonObject, data => {
                //Toast("share 接口调用成功，壳响应数据:" + data);
                //   });
                // } else {
                window.location.href =
                  this.$rootPath + "/nonCar/payment/paymentForward" + res.data;
                // }

                // this.$router.push({
                //   path: '/nonCar/payment/paymentForward' + res.data
                // })
              }
            },
            error => {}
          );
          break;
        }
        case "支付宝短信支付": {
          let param = {
            applicantName: this.queryData.applicantName, //投保人姓名, //投保人姓名
            mobileNo: this.queryData.mobileNo, //投保人手机号码
            insuredName: this.queryData.insuredName, //被投保人姓名
            insuredMobile: this.queryData.insuredMobile, //被投保人电话
            proposalNo: this.queryData.insureNo,
            paymentNo: this.queryData.payApplyNo,
            checkNo: this.queryData.identityCode,
            productName: this.queryData.productName,
            sumPremium: this.queryData.sumPremium
          };
          // "支付宝支付"
          //3、支付宝支付，通过短信发送支付宝支付链接给投保人
          return this.$router.push({
            name: "nonPaymentByAlipayMessage",
            query: param
          });
        }
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
                console.log(res.data.url);
                // if (window.WebViewJavascriptBridge) {
                //   console.log("WebViewJavascriptBridge-2222222222");
                //   let param = {
                //     url: res.data.url,
                //     type: 0,
                //     titleBarTag: 0,
                //     titleBar: "",
                //     sharePlatform: "",
                //     shareType: "",
                //     title: "",
                //     imgURL: "",
                //     image: "", //分享图片（base64编码）, 与imgURL二选一
                //     desc: ""
                //   };
                //   this.$native.openWebView(param, data => {});
                // } else {
                window.location.href = res.data.url;
                // }
                return;
                // window.location.href = res.data.url;
              }
            },
            error => {}
          );
          break;
        }
        default:
          break;
      }
    }
  }
};
</script>
<style lang="less" scoped>
#paymentForward {
  padding: 75px 0 50px 0;
  height: auto;
  .titleIcon {
    width: 22px;
    height: 22px;
    margin-right: 8px;
    img {
      width: 100%;
      height: auto;
    }
  }
  div {
    box-sizing: border-box;
  }
  .title {
    font-size: 16px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
    line-height: 20px;
  }
  .pageHeader {
    // margin-top: 10px;
    height: 70px;
    background: rgba(255, 255, 255, 1);
    padding: 10px 5px;
    img {
      width: 72px;
      height: auto;
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
