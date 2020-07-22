<template>
  <div class="payCover">
    <!-- 头部 -->
    <van-sticky>
      <div class="payNav">
        <div class="tit">
          <van-icon color="white" size="18px" name="arrow-left" @click="back" />
          <p class="commonText17 payTit">保单支付</p>
        </div>
        <div class="info">
          <p class="commonText40">{{ totalPremium }}</p>
          <p class="commonText14">应付金额(元)</p>
        </div>
      </div>
    </van-sticky>
    <!-- middleInfo -->
    <div class="middleInfo">
      <van-cell title="投保单号" :value="proposalNo"></van-cell>
      <van-cell title="产品信息" :value="productName"></van-cell>
      <van-cell title="投保人" :value="policyHolder"></van-cell>
      <van-cell title="支付号" :value="paymentNo"></van-cell>
      <van-cell title="校验码" :value="checkNo"></van-cell>
    </div>
    <!-- payType -->
    <div class="middleInfo payType">
      <p class="commonText14 color666666 payTit">请选择支付方式</p>
      <van-cell is-link @click="selectPayType(1)">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payCode" alt="" />
            </div>
            <span class="custom-title">二维码支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(2)">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payShare" alt="" />
            </div>
            <span class="custom-title">转发支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(3)">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payLogo" alt="" />
            </div>
            <span class="custom-title">支付宝支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(4)">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payCard" alt="" />
            </div>
            <span class="custom-title">银行卡快捷支付</span>
          </div>
        </template>
      </van-cell>
    </div>
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
import { getPaymentUrl, getPaymentRelayUrl } from "@src/http/module/vehicle";
import { mapMutations, mapState } from "vuex";
export default {
  components: modules,
  data() {
    const { params } = this.$route;
    const {
      orderId, //订单号
      proposalNo, //投保单号
      productName, //产品
      policyHolder, //投保人姓名
      paymentNo, //支付号
      totalPremium, //保费
      checkNo
    } = params;
    return {
      payCard: require("@/assets/payment/payCard.png"),
      payCode: require("@/assets/payment/payCode.png"),
      payLogo: require("@/assets/payment/payLogo.png"),
      payShare: require("@/assets/payment/payShare.png"),
      orderId,
      totalPremium, //总金额
      proposalNo, //投保单号
      productName, //投保产品
      policyHolder, //投保人
      paymentNo, //支付号
      checkNo //校验码
    };
  },
  computed: {
    ...mapState("vehicle", {
      // orderId: ({ orderId }) => orderId,
      // proposalNo: ({ proposalNo }) => proposalNo,
      // totalPremium: ({ quoteInfo: { totalPremium } }) => totalPremium,
      // policyHolder: ({ policyHolder: { name } }) => name,
      // paymentNo: ({ paymentNo }) => paymentNo,
    })
  },
  methods: {
    ...mapMutations("vehicle", ["initVehicle"]),
    back() {
      this.initVehicle();
      if (window.WebViewJavascriptBridge) {
        console.log("WebViewJavascriptBridge-2222222222");
        this.$native.closeWeb();
      } else {
        this.$router.push({ path: "/" });
      }
      this.$destroy();
    },
    selectPayType(style) {
      let params = { orderId: this.orderId };
      switch (style) {
        case 1: {
          //"二维码支付"
          //1、二维码支付，支持微信、支付宝当面扫描支付，也可通过微信、支付宝将此二维码支付页面给客户自助支付
          return this.$router.push({ name: "paymentByQRCode" });
        }
        case 2: {
          //"转发支付"
          //2、转发支付，通过微信将订单转发给客户进行自助支付
          getPaymentRelayUrl(params).then(
            res => {
              if (res.code === "1" && res.data) {
                // window.location.href = res.data.url;
                console.log(res.data.url);
                //调用接口参数
                const jsonObject = {
                  sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
                  shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
                  title: "投保订单支付", //分享标题
                  imgURL: this.$rootPath + "/resources/images/icon/logo.png", //分享图片地址, 与image二选一
                  url:
                    this.$rootPath + "/view/pay/insureInfo.html" + res.data.url, //分享链接
                  desc: "尊敬的客户，您的投保订单已生成，可直接在线支付。" //分享描述
                };

                //调用壳方法，传递调用参数和接受响应数据
                this.$native.share(jsonObject, data => {
                  //Toast("share 接口调用成功，壳响应数据:" + data);
                });
              }
            },
            error => {}
          );
          break;
          // return this.$router.push({ name: "paymentByAlipayMessage" });
        }
        case 3: {
          // "支付宝支付"
          //3、支付宝支付，通过短信发送支付宝支付链接给投保人
          return this.$router.push({ name: "paymentByAlipayMessage" });
        }
        case 4: {
          //"银行卡快捷支付"
          //4、银行卡支付进入集中收款平台页面（H5页面）
          let params = { orderId: this.orderId, flag: "6" };
          getPaymentUrl(params).then(
            res => {
              if (res.code === "1" && res.data) {
                window.location.href = res.data.url;
                return;
                // window.location.href = res.data.url;
              }
            },
            error => {}
          );
          // return this.$router.push({ name: "paymentByBankCard" });
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
// common
.van-cell__value {
  word-wrap: none;
  word-break: keep-all;
  white-space: nowrap;
}
// text
.commonText17 {
  font-size: 17px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 24px;
}
.commonText40 {
  font-size: 40px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: rgba(255, 255, 255, 1);
  line-height: 56px;
}
.commonText14 {
  font-size: 14px;
  font-family: PingFangHK-Regular, PingFangHK;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  line-height: 20px;
}
// color
.color666666 {
  color: #666666;
}
// common
.payCover {
  overflow: scroll;
  .payNav {
    // height: 189px;
    background-image: url("../../../assets/payment/bg.png");
    background-repeat: no-repeat;
    background-size: 100%;
    .tit {
      display: flex;
      align-items: center;
      padding: 18px 16px;
      .payTit {
        margin: 0 auto;
      }
    }
    .info {
      text-align: center;
      padding-bottom: 28px;
    }
  }
  .middleInfo {
    padding-top: 14px;
    background: rgba(255, 255, 255, 1);
    border-radius: 15px 15px 0px 0px;
    margin-top: -10px;
    margin-bottom: 10px;
  }
  .payType {
    margin-top: 0;
    .payTit {
      padding-left: 17px;
      padding-bottom: 12px;
    }
    .diyTit {
      display: flex;
      align-items: center;
      .icon {
        width: 32px;
        height: 32px;
        margin-right: 10px;
        img {
          width: 100%;
        }
      }
    }
  }
}
</style>
