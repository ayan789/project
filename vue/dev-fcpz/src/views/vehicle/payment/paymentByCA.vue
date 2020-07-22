<template>
  <div id="super">
    <nav-bar
      type="vehicle"
      title="超A支付"
      clear="1"
      @clickBack="$router.back()"
    />
    <div class="content">
      <div id="center" ref="container">
        <div class="qrTitle">保费</div>
        <div class="price">{{ totalPremium }}</div>
        <div class="tip">
          请扫码下方二维码下载“中国大地超级APP”进行支付，注册享好礼，活动乐翻天。
        </div>
        <div id="qrCode">
          <img src="../../../assets/vehicle/ca.jpg" />
        </div>
        <div id="spaceLine"></div>
        <div id="info">
          <div class="father">
            <div class="left">姓名</div>
            <div class="right">{{ name }}</div>
          </div>
          <div class="father">
            <div class="left">车牌号</div>
            <div class="right">{{ licenseNo || "未上牌" }}</div>
          </div>
          <div class="father" v-if="trafficProposalNo">
            <div class="left">交强险投保单号</div>
            <div class="right">{{ trafficProposalNo }}</div>
          </div>
          <div class="father" v-if="businessProposalNo">
            <div class="left">商业险投保单号</div>
            <div class="right">{{ businessProposalNo }}</div>
          </div>
          <div class="father" v-if="wealchAccProposalNo">
            <div class="left">联合销售投保单号</div>
            <div class="right">{{ wealchAccProposalNo }}</div>
          </div>
        </div>
      </div>
      <div id="bottom" v-if="!loading">
        <div id="shareTitle">分享至</div>
        <div class="shareFather" @click="shareInfo(0)">
          <div class="shareIcon">
            <img src="../../../assets/vehicle/payment/wechatShare.png" />
          </div>
          <div class="shareText">微信</div>
        </div>
        <div class="shareFather" @click="shareInfo(1)">
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
import Html2Canvas from "html2canvas";

export default {
  name: "paymentByCA",
  components: {},
  data() {
    return {
      loading: false
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
  created() {},
  methods: {
    shareInfo: function(index) {
      this.$toast.loading();
      Html2Canvas(this.$refs.container).then(canvas => {
        let base64image = canvas.toDataURL("image/jpeg", 0.5);
        base64image = base64image.replace("data:image/jpeg;base64,", "");
        // console.log(base64image);
        //0:"微信"  1:"支付宝"
        if (index == 0) {
          const jsonObject = {
            sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
            shareType: "img", //"url" - 链接； "img" - 图片； "text" - 文字
            image: base64image //base64
          };
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.share(jsonObject, data => {});
        } else {
          const jsonObject = {
            sharePlatform: ["4"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信; "4"表示支付宝
            shareType: "img", //"url" - 链接； "img" - 图片； "text" - 文字
            image: base64image //base64
          };
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.share(jsonObject, data => {});
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
  min-height: 480px;
  background: white;
  border-radius: 4px;
}
.qrTitle {
  padding: 27px 0px 15px 0px;
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
  display: flex;
  flex-direction: row;
  align-items: center;
  min-height: 35px;
}
.left {
  width: 100px;
  text-align: left;
  font-size: 12px;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
}
.right {
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
.tip {
  padding: 0 20px;
  color: red;
  font-size: 14px;
}
</style>
