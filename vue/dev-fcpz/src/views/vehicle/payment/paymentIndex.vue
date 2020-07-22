<template>
  <div class="payCover">
    <!-- 头部 -->
    <van-sticky>
      <div class="payNav">
        <div class="tit">
          <van-icon class="backArrow" color="white" size="18px" name="arrow-left" @click="back" />
          <p class="commonText17 payTit">保单支付</p>
          <div @click="backTitle" class="backTitle">返回订单列表</div>
        </div>
        <div class="info">
          <p class="commonText40">{{ totalPremium | number }}</p>
          <p class="commonText14">应付金额(元)</p>
        </div>
      </div>
    </van-sticky>
    <!-- middleInfo -->
    <div class="middleInfo">
      <van-cell title="交强投保单号" :value="trafficProposalNo" v-if="trafficProposalNo">
        <template #right-icon>
          <span ref="trafficProposalNo" class="copy-button" data-clipboard-action="copy" :data-clipboard-text="trafficProposalNo" @click="(e) => copy(e, 'trafficProposalNo')">复制</span>
        </template>
      </van-cell>
      <van-cell title="商业投保单号" :value="businessProposalNo" v-if="businessProposalNo">
        <template #right-icon>
          <span ref="businessProposalNo" class="copy-button" data-clipboard-action="copy" :data-clipboard-text="businessProposalNo" @click="(e) => copy(e, 'businessProposalNo')">复制</span>
        </template>
      </van-cell>
      <van-cell title="财意投保单号" :value="wealchAccProposalNo" v-if="wealchAccProposalNo">
        <template #right-icon>
          <span ref="wealchAccProposalNo" class="copy-button" data-clipboard-action="copy" :data-clipboard-text="wealchAccProposalNo" @click="(e) => copy(e, 'wealchAccProposalNo')">复制</span>
        </template>
      </van-cell>
      <van-cell title="产品信息" :value="paymentInfo.productName"></van-cell>
      <van-cell title="投保人" :value="policyHolder"></van-cell>
      <van-cell title="支付号" :value="paymentInfo.paymentNo">
        <template #right-icon>
          <span ref="paymentNo" class="copy-button" data-clipboard-action="copy" :data-clipboard-text="paymentInfo.paymentNo">复制</span>
        </template>
      </van-cell>
      <van-cell title="校验码" :value="paymentInfo.checkNo"></van-cell>
    </div>
    <!-- payType -->
    <div class="middleInfo payType">
      <p class="commonText14 color666666 payTit">请选择支付方式</p>
      <van-cell is-link @click="selectPayType(1)">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payCode" alt />
            </div>
            <span class="custom-title">二维码支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(2)">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payShare" alt />
            </div>
            <span class="custom-title">转发支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(3)">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payLogo" alt />
            </div>
            <span class="custom-title">支付宝支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(5)">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payCA" alt />
            </div>
            <span class="custom-title">超A支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(4)">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payCard" alt />
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
files.keys().forEach((key) => {
  const name = path.basename(key, ".vue");
  modules[name] = files(key).default || files(key);
});
import Clipboard from "clipboard";
import { getPaymentUrl, getPaymentRelayUrl } from "@src/http/module/vehicle";
import { mapMutations, mapState, mapActions } from "vuex";
import { getDetails } from "@src/http/module/order";

export default {
  components: modules,
  data() {
    return {
      payCard: require("@/assets/payment/payCard.png"),
      payCode: require("@/assets/payment/payCode.png"),
      payLogo: require("@/assets/payment/payLogo.png"),
      payShare: require("@/assets/payment/payShare.png"),
      payCA: require("@/assets/payment/payCA.png"),
      clipboard: {
        trafficProposalNo: null,
        businessProposalNo: null,
        wealchAccProposalNo: null,
        paymentNo: null,
      }
    };
  },
  created() {},
  activated() {
    const { query, params } = this.$route;
    const { orderId } = params;
    const { orderId: id } = query;
    if (orderId || id) {
      this.setOrderId(orderId || id);
    }
    getDetails({
      orderId: orderId || id,
      userCode: localStorage.getItem("userCode"),
    })
      .then((res) => {
        if (res.code === "1") {
          this.initWithDetail(res.data);
          this.$nextTick(() => {
            this.$forceUpdate();
          });
        } else {
          console.log("not 1");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  mounted() {
    this.clipboard.paymentNo = new Clipboard(this.$refs.paymentNo);
    this.clipboard.paymentNo.on("success", () => {
      this.$toast("复制成功");
    });
    this.clipboard.paymentNo.on("error", () => {
      this.$toast("复制失败");
    });
  },
  filters: {
    number(v) {
      if (v && Number(v)) {
        return Number(v).toFixed(2);
      }
      return "0.00";
    },
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      proposalNo: ({ proposalNo }) => proposalNo,
      trafficProposalNo: ({ trafficProposalNo }) => trafficProposalNo,
      businessProposalNo: ({ businessProposalNo }) => businessProposalNo,
      wealchAccProposalNo: ({ wealchAccProposalNo }) => wealchAccProposalNo,
      totalPremium: ({ insurancePlan: { totalPremium } }) => totalPremium,
      policyHolder: ({ policyHolder: { name } }) => name,
      paymentInfo: ({ paymentInfo }) => paymentInfo,
    }),
  },
  methods: {
    ...mapMutations("vehicle", ["setOrderId"]),
    ...mapActions("vehicle", ["initWithDetail"]),
    back() {
      if (this.$store.state.orderList.back_page) {
        this.$store.commit("orderList/setBackfalse");
        this.$router.push({
          name: "insureOrderDetail",
          query: {
            id: this.$route.params.orderId,
            payExpireFlag: this.$route.params.payExpireFlag,
            status: this.$route.params.status,
            oneClickRenewalFlag: this.$route.params.oneClickRenewalFlag,
          },
        });
      } else {
        this.$router.push({ path: "/order" });
      }
    },
    copy(e, id) {
      if (this.clipboard[id]) return;
      this.clipboard[id] = new Clipboard(this.$refs[id]);
      this.clipboard[id].on("success", () => {
        this.$toast("复制成功");
      });
      this.clipboard[id].on("error", () => {
        this.$toast("复制失败");
      });
      this.clipboard[id].onClick(e);
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
            (res) => {
              if (res.code === "1" && res.data) {
                // window.location.href = res.data.url;
                console.log(res.data.url);
                //调用接口参数
                const jsonObject = {
                  sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
                  shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
                  title: "投保订单支付", //分享标题
                  imgURL: "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //base64
                  url: res.data.url + "&share=1?&?&", //分享链接
                  desc: "尊敬的客户，您的投保订单已生成，可直接在线支付。", //分享描述
                };

                //调用壳方法，传递调用参数和接受响应数据
                this.$native.share(jsonObject, (data) => {
                  // Toast(data);
                });
              }
            },
            (error) => {}
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
            (res) => {
              if (res.code === "1" && res.data) {
                console.log(res.data.url);
                if (window.WebViewJavascriptBridge) {
                  this.$native.openWebView({
                    url: res.data.url,
                    type: "0",
                    titleBarTag: "1",
                    titleBar: "支付",
                    isBackHome: "1",
                  });
                } else {
                  window.location.href = res.data.url;
                }
                return;
                // window.location.href = res.data.url;
              }
            },
            (error) => {}
          );
          // return this.$router.push({ name: "paymentByBankCard" });
          break;
        }
        case 5: {
          this.$router.push({ name: 'paymentByCA' })
          break;
        }
        default:
          break;
      }
    },
    backTitle() {
      this.$router.push({
        path: "/order/orderList/orderList?moudle=2&status=2",
      });
    },
  },
};
</script>
<style lang="less" scoped>
// common
.item {
  padding: 15px;
  display: flex;
  width: 100%;
  .proposalNo-container {
    display: flex;
    flex-direction: column;
  }
}
.van-cell__value {
  word-wrap: none;
  word-break: keep-all;
  white-space: nowrap;
  overflow: visible;
  flex: 2;
}
.copy-button {
  height: 100%;
  padding: 0 4px;
  border-radius: 2px;
  border: 1px solid rgb(39, 154, 255);
  margin-left: 8px;
  font-size: 12px;
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
      .backArrow {
        flex: 1;
      }
      .payTit {
        // margin: 0 auto;
        flex: 1;
        text-align: center;
      }
      .backTitle {
        color: #ffffff;
        font-size: 14px;
        flex: 1;
        text-align: right;
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
