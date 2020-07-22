<template>
  <div id="super">
    <van-sticky>
      <nav-bar
        type="vehicle"
        title="二维码支付"
        clear="1"
        @clickBack="$router.back()"
      />
    </van-sticky>
    <div class="content">
      <div
        id="center"
        ref="container"
      >
        <div class="qrTitle">保费</div>
        <div class="price">{{ totalPremium }}</div>
        <div
          id="qrCode"
          v-show="qrcodebase64"
        >
          <img
            v-if="qrcodebase64"
            :src="qrcodebase64"
          />
        </div>
        <div id="spaceLine"></div>
        <div id="info">
          <div class="father">
            <h3 class="left">姓名</h3>
            <h3 class="right">{{ name }}</h3>
          </div>
          <div class="father">
            <h3 class="left">车牌号</h3>
            <h3 class="right">{{ licenseNo || "未上牌" }}</h3>
          </div>
          <div
            class="father"
            v-if="trafficProposalNo"
          >
            <h3 class="left">交强险投保单号</h3>
            <h3 class="right">{{ trafficProposalNo }}</h3>
          </div>
          <div
            class="father"
            v-if="businessProposalNo"
          >
            <h3 class="left">商业险投保单号</h3>
            <h3 class="right">{{ businessProposalNo }}</h3>
          </div>
          <div
            class="father"
            v-if="wealchAccProposalNo"
          >
            <h3 class="left">联合销售投保单号</h3>
            <h3 class="right">{{ wealchAccProposalNo }}</h3>
          </div>
        </div>
      </div>
      <div
        id="bottom"
        v-if="!loading"
      >
        <div id="shareTitle">分享至</div>
        <div
          class="shareFather"
          @click="shareInfo(0)"
        >
          <div class="shareIcon">
            <img src="../../../assets/vehicle/payment/wechatShare.png" />
          </div>
          <div class="shareText">微信</div>
        </div>
        <div
          class="shareFather"
          @click="shareInfo(1)"
        >
          <div class="shareIcon">
            <img src="../../../assets/vehicle/payment/alipayShare.png" />
          </div>
          <div class="shareText">支付宝</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getShareCode,
  getPaymentUrl,
  getShareInfo
} from "@src/http/module/vehicle";
import { mapState } from "vuex";
import QRCode from "qrcodejs2";
import Html2Canvas from "html2canvas";
export default {
  name: "paymentByQRCode",
  components: {},
  data () {
    return {
      loading: false,
      shareCode: "",
      qrcode: null,
      qrcodebase64: "",
      url: ""
    };
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      totalPremium: ({ insurancePlan }) => insurancePlan.totalPremium,
      name: ({ policyHolder: { name } }) => name,
      licenseNo: ({ car: { licenseNo } }) => licenseNo,
      insureNo: ({ proposalNo }) => proposalNo,
      trafficProposalNo: ({ trafficProposalNo }) => trafficProposalNo,
      businessProposalNo: ({ businessProposalNo }) => businessProposalNo,
      wealchAccProposalNo: ({ wealchAccProposalNo }) => wealchAccProposalNo
    })
  },
  created () {
    this.$toast.loading();
    let shareCode = this.$GetRequest("shareCode");
    if (shareCode) {
      this.shareCode = shareCode;
      this.getShareInfo();
    } else {
      // this.loading = false;
      // this.$toast.clear();
      let { query = {}, params = {} } = this.$route;
      let orderId = query.orderId || params.orderId || this.orderId;
      getPaymentUrl({ orderId, flag: "1" }).then(
        res => {
          if (res.code === "1" && res.data) {
            this.qrcodebase64 =
              "data:image/png;base64," + res.data.qrCode.replace("↵", "");
            this.url = res.data.url;
          }
        },
        error => { }
      );
      return;
      //1、	二维码支付，支持微信、支付宝当面扫描支付，也可通过微信、支付宝将此二维码支付页面给客户自助支付
      // let { query = {}, params = {} } = this.$route;
      // let orderId = query.orderId || params.orderId || this.orderId;
      // getShareCode({ orderId: this.orderId })
      //   .then(
      //     res => {
      //       if (res.code == "1") {
      //         let shareCode = res.data;
      //         this.shareCode = shareCode;
      //         let path =
      //           this.$rootPath + "/paymentByQRCode?shareCode=" + shareCode;
      //         this.qrcode = new QRCode("qrCode", {
      //           text: encodeURI(path),
      //           width: 190,
      //           height: 190
      //         });
      //         let qrcodebase64 = "";
      //         if (this.qrcode._oDrawing._elCanvas.style.display == "none") {
      //           qrcodebase64 = this.qrcode._oDrawing._elImage.src;
      //         } else if (
      //           this.qrcode._oDrawing._elImage.style.display == "none"
      //         ) {
      //           qrcodebase64 = this.qrcode._oDrawing._elCanvas.toDataURL(
      //             "image/png",
      //             1
      //           );
      //         }
      //         setTimeout(() => {
      //           this.qrcode._oDrawing._elCanvas.style.display = "none";
      //           this.qrcode._oDrawing._elImage.style.display = "none";
      //           this.qrcodebase64 = qrcodebase64;
      //         }, 100);
      //       }
      //     },
      //     error => {}
      //   )
      //   .catch(error => {
      //     console.log(error);
      //   });
    }
  },
  methods: {
    isWeiXin () {
      var ua = window.navigator.userAgent.toLowerCase();
      if (ua.match(/MicroMessenger/i) == "micromessenger") {
        return true;
      } else {
        return false;
      }
    },
    getShareInfo () {
      let flag = "";
      if (this.isWeiXin()) {
        flag = "7";
      } else {
        flag = "5";
      }
      getShareInfo({ shareCode: this.shareCode, flag: "99" })
        .then(res => {
          if (res.code === "1") {
            window.location.href = res.data.paymentUrl;
          } else {
            console.log("not 1");
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    shareInfo: function (index) {
      this.$toast.loading();
      Html2Canvas(this.$refs.container).then(canvas => {
        let base64image = canvas.toDataURL("image/jpeg", 0.5);
        base64image = base64image.replace("data:image/jpeg;base64,", "");
        console.log(base64image);
        //0:"微信"  1:"支付宝"
        if (index == 0) {
          const jsonObject = {
            sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
            shareType: "img", //"url" - 链接； "img" - 图片； "text" - 文字
            image: base64image //base64
          };
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.share(jsonObject, data => { });
        } else {
          const jsonObject = {
            sharePlatform: ["4"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信; "4"表示支付宝
            shareType: "img", //"url" - 链接； "img" - 图片； "text" - 文字
            image: base64image //base64
          };
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.share(jsonObject, data => { });
        }
        this.$toast.clear();
      });
    }
  }
};
</script>

<style scoped>
.content {
  width: 100%;
  height: calc(100% - 77px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
}
#super {
  width: 100%;
  height: 100%;
  position: absolute;
  background: linear-gradient(
    91deg,
    rgba(30, 70, 246, 1) 0%,
    rgba(39, 154, 255, 1) 100%
  );
}

#center {
  width: calc(100% - 30px);
  margin: 20px 15px;
  min-height: 560px;
  background: white;
  border-radius: 4px;
}
.qrTitle {
  padding: 27px 0px 5px 0px;
  height: 20px;
  width: 100%;
  font-size: 14px;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
  line-height: 20px;
  text-align: center;
  /* background: yellow; */
}
.price {
  height: 56px;
  font-size: 40px;
  /* font-weight: 600; */
  color: rgba(51, 51, 51, 1);
  line-height: 56px;
  text-align: center;
  text-justify: auto;
}
#qrCode {
  width: 190px;
  height: 190px;
  margin: 0 auto;
  margin-top: 15px;
}

#qrCode > img {
  width: 190px;
  height: 190px;
  display: block;
}

#spaceLine {
  margin: 31px 0 0 0;
  height: 9px;
  background: #f5f5f5;
  width: 100%;
}

#info {
  margin: 10px 0;
}

.father {
  margin: 0 18px;
  height: 35px;
}
.father > h3 {
  height: 35px;
  line-height: 35px;
}
.left {
  width: 100px;
  float: left;
  text-align: left;
  font-size: 12px;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
}
.right {
  float: right;
  width: calc(100% - 100px);
  text-align: right;
  font-size: 14px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
}

#bottom {
  margin-top: 20px;
  height: 80px;
  width: 100%;
}

#shareTitle {
  margin: 0 auto;
  color: rgba(255, 255, 255, 0.3);
  font-size: 14px;
  font-weight: 400;
  height: 20px;
  line-height: 20px;
  text-align: center;
}

.shareFather {
  float: left;
  width: 50%;
  height: 40px;
  justify-content: center;
  display: flex;
}

.shareFather > div {
  float: left;
  height: 24px;
  line-height: 24px;
  font-size: 14px;
  font-weight: 400;
  color: rgba(255, 255, 255, 0.8);
}
.shareText {
  margin-left: 10px;
}
</style>
