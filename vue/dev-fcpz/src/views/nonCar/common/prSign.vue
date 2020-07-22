<!--签名确认-->
<template>
  <div id="signature-confirm">
    <!-- <van-nav-bar title="签名确认" /> -->
    <globalNav
      :title="globalNav.title"
      :isShare="globalNav.isShare"
      @clickBack="clickBack"
      :leftText="globalNav.leftText"
      @clickRight="shareSignPage"
      :leftArrow="globalNav.leftArrow"
    ></globalNav>
    <div class="title-container">
      <div class="title-text">
        <span v-if="termsAndConditions.length > 0">
          本人已阅读
          <a
            :href="item.url"
            v-for="(item, i) in termsAndConditions"
            :key="i"
            @click="gotoClause(item)"
            >{{
              i == termsAndConditions.length - 1
                ? "《" + item.title + "》"
                : "《" + item.title + "》、"
            }}</a
          >
        </span>
        <span v-if="Object.keys(healthNotification).length > 0">
          并承诺提供的
          <a @click="gotoClause(healthNotification)">{{
            "《" + healthNotification.title + "》"
          }}</a>

          内容真实有效！
        </span>
      </div>
    </div>
    <van-row
      type="flex"
      justify="space-between"
      class="signature-title-container"
    >
      <van-col class="signature-title">
        <span class="icon"></span>
        <span>投保人签名</span>
      </van-col>
      <van-col class="signature-anew" @click="showSignaturePadAndReSign"
        >重签</van-col
      >
    </van-row>
    <div class="card">
      <div class="card-title-container signature-pad-container">
        <div class="card-title-text">签名确认</div>
        <div class="card-title-right" @click="onSignaturePadClear">
          <van-icon name="replay" />
          <div class="card-title-right-name right-signature-pad">清除</div>
        </div>
      </div>
      <canvas
        id="canvasId"
        :ref="pre + 'sigCanvas'"
        class="signature-pad-canvas"
      ></canvas>
    </div>

    <van-row class="signature-title-container">
      <van-col class="signature-title">
        <span class="icon"></span>
        <span>投保人信息</span>
      </van-col>
    </van-row>
    <van-cell title="手机号" :value="phone" />
    <van-field
      label="验证码"
      placeholder="请输入验证码"
      input-align="right"
      v-model="codeSign"
    >
      <van-button
        slot="button"
        size="small"
        class="signature-code-button"
        :disabled="disabled"
        @click="onSendCodeClick"
        >{{ disabled ? `重新发送(${resendTime})` : "获取验证码" }}</van-button
      >
    </van-field>
    <van-button
      text="提交"
      class="signature-submit-button"
      @click="doUploadSignBook"
    />
  </div>
</template>

<script>
import { NavBar, Button, Popup, Toast } from "vant";
import SignaturePad from "signature_pad";
import globalNav from "../../../components/globalNav";
import { getShareUrl } from "@src/http/module/customerApi.js";

import {
  // signSendSMS,
  signSendSMSTwo,
  // uploadSignBook,
  upload,
  // applyPay
  applyPayTwo,
  initSign
} from "@src/http/module/nonCar.js";

export default {
  components: {
    [NavBar.name]: NavBar,
    [Button.name]: Button,
    [Popup.name]: Popup,
    [globalNav.name]: globalNav
  },
  props: {
    person: {
      type: String,
      default: "T"
    }
  }, //判断个人还是家庭
  data: () => ({
    phone: "",
    isIssueAfterPay: "", //是否见费
    insuredName: "", //被保人姓名
    insuredMobile: "", //被保人手机号
    signaturePad: null,
    signatureImage: null,
    signaturePadShow: false,
    globalNav: {
      title: "签名确认",
      isShare: true,
      leftText: "首页",
      leftArrow: true
    },
    insureNo: "",
    codeSign: "", //验证码
    disabled: false,
    resendTime: "",
    interval: "",
    pre: "",
    warnTip: "",
    isFlag: "",
    isWeiXin: false,
    clause: "",
    termsAndConditions: [], //条款
    healthNotification: {} //健康告知
  }),
  created() {
    console.dir(this.$route.query);
    var timestamp = Date.parse(new Date());
    let params = {
      forwardDate: timestamp,
      proposalNo:
        this.$route.query.insureNo || this.$GetRequest("insureNo", true)
    };
    initSign(params).then(res => {
      if (res.code == "1") {
        let {
          termsAndConditions,
          healthNotification,
          mobile,
          isIssueAfterPay
        } = res.data;
        this.phone = mobile || "";
        this.isIssueAfterPay = isIssueAfterPay || "";
        this.termsAndConditions.splice(0, this.termsAndConditions.length);
        this.termsAndConditions = [...termsAndConditions.content];
        this.healthNotification = healthNotification;
      } else {
        this.$toast(res.msg || res.message);
      }
    });
    this.insureNo =
      this.$route.query.insureNo || this.$GetRequest("insureNo", true);
    // this.phone = this.$route.query.mobile;
    this.insuredName = this.$route.query.insuredName;
    this.insuredMobile = this.$route.query.insuredMobile;
  },
  mounted() {
    sessionStorage.clear();
    history.pushState(null, null, document.URL);
    window.addEventListener(
      "popstate",
      function() {
        history.pushState(null, null, document.URL);
      },
      false
    );

    const isWeiXin = this.$isWeiXin();
    this.isWeiXin = this.$isWeiXin();
    if (isWeiXin) {
      this.globalNav.isShare = false;
      this.globalNav.leftArrow = false;
      this.globalNav.leftText = "";
    }
    let key = this.pre + "sigCanvas";
    let signaturePad = new SignaturePad(this.$refs[key], {
      backgroundColor: "transparent",
      penColor: "black"
    });
    var ratio = Math.max(window.devicePixelRatio || 1, 1);
    this.$refs[key].width = this.$refs[key].offsetWidth * ratio;
    this.$refs[key].height = this.$refs[key].offsetHeight * ratio;
    this.$refs[key].getContext("2d").scale(ratio, ratio);
    if (this.signaturePad) {
      signaturePad.fromData(this.signaturePad.toData());
    }
    this.signaturePad = signaturePad;
  },
  methods: {
    gotoClause(item) {
      if (item.chlidren instanceof Array && item.chlidren.length > 0) {
        let obj = {
          list: item.chlidren,
          title: item.title
        };
        this.$router.push({
          name: "clauseTerms",
          query: { list: JSON.stringify(obj) }
        });
      } else {
        // // https://view.officeapps.live.com/op/view.aspx?src=https://msupsale-test.ccic-net.com.cn/api/cst/s3/browse/e8300975-30b9-4c8f-97f4-afcc27848f98.docx
        this.$router.push({
          name: "clausePdf",
          query: {
            title: item.title,
            link: item.link ? item.link : item.url
          }
        });
        // this.$native.openWebView({
        //   url: "https://view.officeapps.live.com/op/view.aspx?src=" + item.link,
        //   type: "0",
        //   titleBarTag: "1",
        //   titleBar: item.title,
        //   isBackHome: "1"
        // });
      }
    },
    // 返回上一页
    clickBack() {
      if (window.WebViewJavascriptBridge) {
        this.$native.openHome("", data => {});
      }
    },
    showSignaturePadAndReSign() {
      this.signaturePadShow = true;
      this.signaturePad.clear();
      this.signatureImage = null;
    },
    onSendCodeClick() {
      //console.log("发送验证码");
      this.sendCode();
    },
    // 发送验证码接口
    async sendCode() {
      // this.disabled = true;
      // this.insureNo = "TWTN20340119020012400069";
      let res = await signSendSMSTwo({ insureNo: this.insureNo });
      //console.log(res);
      if (res.code == "1") {
        this.disabled = true;
        if (this.interval) return;
        this.resendTime = 59;
        this.interval = setInterval(() => {
          if (this.resendTime > 0) {
            this.resendTime -= 1;
          } else {
            this.resendTime = 60;
            clearInterval(this.interval);
            this.disabled = false;
            this.interval = null;
          }
        }, 1000);
        Toast({
          message: res.msg,
          type: "success",
          position: "bottom"
        });
        console.log("验证码======" + res.msg);
        if (!this.$isNull(res.msg.replace(/[^0-9]/gi, ""))) {
          this.codeSign = parseInt(res.msg.replace(/[^0-9]/gi, ""));
        }
        //倒计时 TODO
      } else {
        Toast({
          message: res.msg,
          type: "fail",
          position: "bottom"
        });
      }
    },
    getSignaturePadImageData() {
      if (this.signaturePad.isEmpty()) {
        return null;
      }
      return this.signaturePad.toDataURL("image/png");
    },
    // 隐藏提示
    hideTip() {
      const time = null;
      setTimeout(() => {
        clearTimeout(time);
        this.warnTip = "";
      }, 1000);
    },
    // 上传签名生成投保确认书
    async doUploadSignBook() {
      let userSignUrl = this.getSignaturePadImageData();
      if (!userSignUrl) {
        Toast.fail("请签名");
      }
      let obj = {
        insuranceNo: this.insureNo,
        verificationCode: this.codeSign,
        signBytes: userSignUrl.split(",")[1]
      };
      console.log(obj, "heweiwei ==>237");
      let res = await upload(obj);
      //console.log(res);
      if (res.code == "1" && res.data) {
        Toast({
          message: "投保确认书上传成功",
          type: "success",
          position: "bottom"
        });
        console.log(329, res.data);
        // 见费非见费
        if (this.isIssueAfterPay == "1") {
          this.getApplyPayTwo();
        } else {
          this.$router.push({
            name: "prIndex",
            query: {
              strategyId: this.$store.state.config.detailData.strategyId
            }
          });
        }
      } else {
        Toast(res.msg);
      }
    },
    // 获取支付号
    async getApplyPayTwo() {
      let request = await applyPayTwo({ insureNo: this.insureNo });
      if (request.code == "1") {
        this.$toast({
          message: request.msg,
          type: "success"
        });
        if (request.data && request.data.noncarOrder.insureNo) {
          this.$router.push({
            name: "prPaymentOrder",
            query: {
              proposalNo: request.data.noncarOrder.insureNo
            }
          });
        }
      } else {
        this.$toast({
          message: request.msg || request.message,
          type: "fail"
        });
      }
    },
    // async getApplyPay() {
    //   let res = await applyPayTwo({
    //     insureNo: this.insureNo
    //   });
    //   if (res.code == "1") {
    //     Toast({
    //       message: res.msg,
    //       type: "success",
    //       position: "bottom"
    //     });
    //     if (res.data) {
    //       let {
    //         insureNo,
    //         payApplyNo,
    //         identityCode,
    //         sumPremium
    //       } = res.data.noncarOrder;
    //       this.$router.push({
    //         name: "prPaymentOrder",
    //         query: {
    //           proposalNo: this.insureNo
    //         }
    //       });
    //       this.$router.push({
    //         name: "prPaymentOrder", //fufeikai TODO suncao
    //         query: {
    //           params: JSON.stringify({
    //             proposalNo: insureNo,
    //             paymentNo: payApplyNo,
    //             checkNo: identityCode,
    //             // sumPremium: this.$route.query.sumPremium,
    //             sumPremium: 748,

    //             // startDate: startDate,
    //             // productName: this.$route.query.productName,
    //             productName: "请勿修改",

    //             // applicantName: this.$route.query.applicantName,
    //             applicantName: 0,
    //             // mobileNo: this.$route.query.mobile,
    //             // insuredName: this.$route.query.insuredName,
    //             // insuredMobile: this.$route.query.insuredMobile,
    //             // invoiceType: this.$route.query.invoiceType
    //             mobileNo: 13111111111,
    //             insuredName: 0,
    //             insuredMobile: 13111111111
    //             // invoiceType: this.$route.query.invoiceType
    //           })
    //         }
    //       });
    //     }
    //   } else {
    //     Toast({
    //       message: res.msg,
    //       type: "success",
    //       position: "bottom"
    //     });
    //   }
    // },
    //  签字清除
    onSignaturePadClear() {
      this.signaturePad.clear();
    },
    // 分享页面信息
    async shareSignPage() {
      // console.log(window.location.href);
      let res = await getShareUrl({ url: window.location.href });
      //调用接口参数 TODO
      const jsonObject = {
        sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
        shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
        title: "投保订单签名", //分享标题
        imgURL:
          "https://test.95590.cn/mobile-sales/resources/images/wth/index.png", //分享图片地址, 与image二选一
        image: "", //分享图片（base64编码）, 与imgURL二选一
        url: res.data.shortUrl, //分享链接
        desc: "尊敬的客户，您的投保订单已生成，可以在线签名确认。", //分享描述
        phoneNum: "" //电话号码,当需要短信分享时获取
      };
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.share(jsonObject, data => {
        //Toast("share 接口调用成功，壳响应数据:" + data);
      });
    }
  }
};
</script>

<style lang="less" scoped>
#signature-confirm {
  /deep/#rightFather {
    padding: 0;
  }
  /deep/.van-nav-bar__right {
    bottom: 50%;
    transform: translateY(50%);
  }
  height: calc(100vh - 40px);
  padding-top: 30px;
  overflow: scroll;
  .van-field {
    border: 1px;
  }
}
.title-container {
  background: #fff;
  padding: 15px;
  margin: 46px 0 0 0;
}
.title-text {
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 19px;
}
.title-link-text {
  color: #3b8afe;
}
.signature-title-container {
  padding: 15px 15px 10px 15px;
  background: #fff;
}
.signature-title {
  font-size: 16px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 23px;
}
.signature-anew {
  font-size: 15px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 21px;
}
.signature-pad {
  margin: 0 15px;
  height: 230px;
  background: #f6f6f6;
  border-radius: 4px;
}
.signature-image {
  width: 100%;
  height: 100%;
}
.signature-code-button {
  height: 26px;
  background: white;
  border-radius: 13px;
  border: 1px solid #3b8afe;
  font-size: 11px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #3b8afe;
  line-height: 26px;
  text-align: center;
}
.code {
  border: 1px;
}
.signature-submit-button {
  width: 345px;
  height: 46px;
  background: #3b8afe;
  border-radius: 23px;
  margin: 43px 15px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: white;
  line-height: 23px;
}
.signature-pad-popup {
  width: 330px;
  height: 363px;
  background: white;
  border-radius: 4px;
}
.signature-pad-popup-title {
  padding-top: 15.5px;
  padding-bottom: 13.5px;
  background: white;
  border-radius: 4px;
  border-bottom: 1px solid #e0e0e0;
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #39393c;
  line-height: 23px;
}
.signature-current-title {
  margin-top: 16px;
  margin-left: 17.5px;
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #39393c;
  line-height: 19px;
}
.signature-pad-canvas {
  width: 100%;
  height: 231px;
}
.signature-pad-button {
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #39393c;
  line-height: 44px;
  text-align: center;
  border-top: 1px solid #cdd1d5;
  border-right: 1px solid #cdd1d5;
}
.signature-pad-button-confirm {
  color: #3b8afe;
  border-right: 0;
}
.icon {
  display: inline-block;
  width: 11px;
  height: 16px;
  background: rgba(8, 77, 179, 1);
  margin-right: 10px;
  vertical-align: middle;
}
.card {
  margin: 0 15px 12px;
  padding: 10px 17px 25px 20px;
  background: white;
  border-radius: 10px;
  div {
    display: flex;
    align-items: center;
  }
  .card-title-container {
    padding-bottom: 8px;
    border-bottom: 1px solid #66666616;
    justify-content: space-between;
    display: none;
  }
  .signature-pad-container {
    border-bottom: 0;
  }
  .card-title-text {
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: #333333;
    line-height: 25px;
  }
  .card-title-right {
    // align-items: center;
  }
  .card-title-right-name {
    margin-right: 5px;
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
  }
  .right-month {
    color: #333333;
  }
  .right-signature-pad {
    color: #8e8e8e;
  }
  .card-title-right-value {
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: #3b8afe;
  }
  .card-items-container {
    width: 100%;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  .card-item-container {
    width: 141px;
    padding-top: 15px;
    flex-direction: column;
    align-items: flex-start;
  }
  .item-month {
    flex-direction: row;
    justify-content: space-between;
  }
  .card-item-title {
    margin-bottom: 7px;
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
    line-height: 12px;
  }
  .title-month {
    margin-bottom: 0;
    font-size: 14px;
    color: #3c69ab;
    align-self: center;
  }
  .card-item-input {
    width: 141px;
    height: 38px;
    border: 1px solid #dddddd;
    background: white;
    text-align: center;
    font-size: 14px;
    font-family: MicrosoftYaHei;
    color: #575756;
  }
  .input-month {
    width: 100px;
    height: 36px;
  }
  .signature-pad-canvas {
    width: 100%;
    height: 200px;
  }
}
</style>
