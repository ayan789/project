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
          <p class="commonText40">{{ sumPremium }}</p>
          <p class="commonText14">应付金额(元)</p>
        </div>
      </div>
    </van-sticky>
    <!-- middleInfo -->
    <div class="middleInfo">
      <van-cell title="投保单号" :value="proposalNo"></van-cell>
      <van-cell title="产品信息" :value="productName"></van-cell>
      <van-cell title="投保人" :value="applicantName"></van-cell>
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
              <img :src="payCode" alt />
            </div>
            <span class="custom-title">二维码支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(2)" v-if="isWX">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payShare" alt />
            </div>
            <span class="custom-title">转发支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(3)" v-if="isWX">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payLogo" alt />
            </div>
            <span class="custom-title">支付宝短信支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(4)" v-if="isWX">
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
              <img :src="payCard" alt />
            </div>
            <span class="custom-title">银行卡快捷支付</span>
          </div>
        </template>
      </van-cell>
      <van-cell is-link @click="selectPayType(6)" v-if="!isWX">
        <template #title>
          <div class="diyTit">
            <div class="icon">
              <img :src="payWX" alt />
            </div>
            <span class="custom-title">微信支付</span>
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
import { getPayUrl, applyPay, getForwardUrl } from "@src/http/module/nonCar";

export default {
  components: modules,
  data() {
    const params = JSON.parse(this.$route.query.params);
    const {
      proposalNo, //投保单号
      productName, //产品
      applicantName, //投保人姓名
      paymentNo,
      checkNo,
      sumPremium,
      mobileNo,
      insuredName,
      insuredMobile,
      insuranceType //保险类别 1财产险，2个人版，3家庭版
    } = params;
    // console.log(params);
    return {
      isWX: true,
      payCard: require("@/assets/payment/payCard.png"),
      payCode: require("@/assets/payment/payCode.png"),
      payLogo: require("@/assets/payment/payLogo.png"),
      payShare: require("@/assets/payment/payShare.png"),
      payWX: require("@/assets/payment/payWechat.png"),
      sumPremium, //总金额
      proposalNo, //投保单号
      productName, //投保产品
      applicantName, //投保人
      paymentNo, //支付号
      checkNo, //校验码
      mobileNo, //投保人手机号码
      insuredName, //被投保人姓名
      insuredMobile,
      insuranceType //保险类别 1财产险，2个人版，3家庭版
    };
  },
  created() {},
  mounted() {
    const isWeiXin = this.$isWeiXin();
    if (isWeiXin) {
      this.isWX = false;
    }
    console.log(JSON.parse(this.$route.query.params));
  },
  methods: {
    back() {
      this.$native.showOrCloseWatermarket(false, data => {});
      if(this.$isNull(this.insuranceType) && this.insuranceType == "1"){//跳转财产险
        this.$router.push({
          path: "/order/orderList/orderList?moudle=1&status=2"
        });
      } else {
        this.$router.push({//跳转意健险
          path: "/order/orderList/orderList?moudle=0&status=2"
        });
      }
      this.$destroy();
    },
    // 获取支付号
    async getApplyPay() {
      return await applyPay({ insureNo: this.proposalNo });
    },
    alipayPayment() {
      let params = {
        proposalNo: this.proposalNo,
        paymentNo: this.paymentNo,
        checkNo: this.checkNo,
        flag: "5", //5：支付宝支付
        productName: this.productName,
        sumPremium: this.sumPremium
      };
      console.log("TODO suncao openwebview跳转支付宝支付2222222");
      getPayUrl(params).then(
        res => {
          if (res.code === "1" && res.data) {
            console.log(res.data.url);
            if (window.WebViewJavascriptBridge) {
              //TODO suncao 跳转支付宝支付
              let param = {
                url: res.data.url,
                type: "0",
                titleBarTag: "0",
                titleBar: "支付宝支付"
              };
              // this.$dialog.alert({
              //   message: JSON.stringify(param)
              // });
              this.$native.openWebView(param, data => {});
            } else {
              window.location.href = res.data.url;
            }
          }
        },
        error => {}
      );
    },
    selectPayType(style) {
      if (!this.paymentNo) {
        this.getApplyPay().then(request => {
          if (request.code == "1" && request.data && request.data.insureInfo) {
            let {
              insureNo,
              payApplyNo,
              identityCode
            } = request.data.insureInfo;
            this.proposalNo = insureNo;
            this.paymentNo = payApplyNo;
            this.checkNo = identityCode;
          }
        });
      }
      switch (style) {
        case 1: {
          let params1 = {
            proposalNo: this.proposalNo,
            paymentNo: this.paymentNo,
            checkNo: this.checkNo,
            sumPremium: this.sumPremium,
            productName: this.productName, //投保产品
            applicantName: this.applicantName, //投保人
            mobileNo: this.mobileNo, //投保人手机号码
            insuredName: this.insuredName, //被投保人姓名
            insuredMobile: this.insuredMobile //被投保人电话
          };

          //"二维码支付"
          //1、二维码支付，支持微信、支付宝当面扫描支付，也可通过微信、支付宝将此二维码支付页面给客户自助支付
          return this.$router.push({
            name: "nonPaymentByQRCode",
            // params: JSON.stringify(params1)
            query: {
              item: JSON.stringify(params1)
            }
          });
        }
        case 2: {
          let params = {
            proposalNo: this.proposalNo,
            paymentNo: this.paymentNo,
            checkNo: this.checkNo,
            flag: "1",
            productName: this.productName,
            sumPremium: this.sumPremium
          };
          console.dir(params);
          // this.$router.push({
          //   name: "nonPaymentForward",
          //   // query: params
          // });
          //"转发支付"
          //2、转发支付，通过微信将订单转发给客户进行自助支付
          getForwardUrl(params).then(
            res => {
              if (res.code === "1" && res.data) {
                // window.location.href = res.data.url;
                console.log(res.data);
                let gotoUrlHeader = " ";
                //保险类别 1财产险，2个人版，3家庭版
                if (this.insuranceType == 1) {
                  //1财产险
                  gotoUrlHeader =
                    this.$rootPath + "/nonCar/payment/paymentForward";
                } else if (this.insuranceType == 2) {
                  //2个人版
                  gotoUrlHeader =
                    this.$rootPath + "/nonCar/payment/paymentForwardPerson";
                } else {
                  //3家庭版
                  gotoUrlHeader =
                    this.$rootPath + "/nonCar/payment/paymentForwardPerson";
                }
                //调用接口参数
                const jsonObject = {
                  sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
                  shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
                  title: "投保订单支付", //分享标题
                  imgURL:
                    "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
                  url: gotoUrlHeader + res.data, //分享链接
                  desc: "尊敬的客户，您的投保订单已生成，可直接在线支付。" //分享描述
                };
                console.log("跳转地址", jsonObject.url);
                //调用壳方法，传递调用参数和接受响应数据
                this.$native.share(jsonObject, data => {
                  //Toast("share 接口调用成功，壳响应数据:" + data);
                });
                // this.$router.push({
                //   path: '/nonCar/payment/paymentForward' + res.data
                // })
              }
            },
            error => {}
          );
          break;
          // return this.$router.push({ name: "paymentByAlipayMessage" });
        }
        case 3: {
          // console.log( this.mobileNo)
          // console.log( this.insuredMobile)
          let param = {
            applicantName: this.applicantName, //投保人姓名, //投保人姓名
            mobileNo: this.mobileNo, //投保人手机号码
            insuredName: this.insuredName, //被投保人姓名
            insuredMobile: this.insuredMobile, //被投保人电话
            proposalNo: this.proposalNo,
            paymentNo: this.paymentNo,
            checkNo: this.checkNo,
            productName: this.productName,
            sumPremium: this.sumPremium
          };
          //  appID：2021001154646125
          //  sharePlatform:"4"
          // "支付宝支付"
          //3、支付宝支付，通过短信发送支付宝支付链接给投保人
          return this.$router.push({
            name: "nonPaymentByAlipayMessage",
            query: param
          });
        }
        case 4: {
          // 支付宝支付
          // this.$toast("敬请期待");
          this.alipayPayment();
          break;
        }
        case 5: {
          //"银行卡快捷支付"
          //4、银行卡支付进入集中收款平台页面（H5页面）
          let params = {
            proposalNo: this.proposalNo,
            paymentNo: this.paymentNo,
            checkNo: this.checkNo,
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
              } else {
                this.$dialog.alert({
                  message: "进入集中收款平台失败"
                });
              }
            },
            error => {}
          );
          // return this.$router.push({ name: "paymentByBankCard" });
          break;
        }
        case 6: { //微信支付
          let params = {
            proposalNo: this.proposalNo,
            paymentNo: this.paymentNo,
            checkNo: this.checkNo,
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
        }
      }
    }
  }
};
</script>
<style lang="less" scoped>
// common
.van-cell__title {
  flex: 1;
}
.van-cell__value {
  word-wrap: none;
  word-break: keep-all;
  white-space: nowrap;
  flex: 2;
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
    // background-image: url('../../../assets/nonCar/payment/bg1.png');
    // background-repeat: no-repeat;
    // background-size: 100%;
    background: linear-gradient(92deg, #2153eb 0%, #3793ff 100%);
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
