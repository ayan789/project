<template>
  <div class="businessPlanCover">
    <businessHead :back="!share && !screenshots" />
    <div class="setPos">
      <business-normal
        :quotationObjInfo="insurePlan"
        :totalPayment="totalPayment"
        :claimInfo="claimInfo"
        :personInfo="personInfo"
        :share="true"
      />
    </div>
    <!-- share  暂时隐藏链接分享 TODU ZHAOSHUHUI-->
    <div class="bottom">
      <!-- <div class="share-button" @click="sharePage('shareText')"><img src="../../../assets/vehicle/selectProduct/text.png" /></div> -->
      <div
        class="share-button"
        v-if="!share && !screenshots"
        @click="sharePage('shareImage')"
      >
        <img src="../../../assets/vehicle/selectProduct/image.png" />
      </div>
      <div
        class="share-button"
        v-if="!share && !screenshots"
        @click="sharePage('shareLink')"
      >
        <img src="../../../assets/vehicle/selectProduct/link.png" />
      </div>
      <div
        class="share-button"
        v-if="!share && !screenshots"
        @click="sharePage('shareSMS')"
      >
        <img src="../../../assets/vehicle/selectProduct/sms.png" />
      </div>
    </div>
    <!-- back to top -->
    <div class="backBtn" v-if="showBtn && !screenshots" @click="backTop">
      <img src="../../../assets/vehicle/businessPlan/backTop.png" alt="" />
    </div>
  </div>
</template>
<script>
import { mapState, mapMutations, mapActions } from "vuex";
// 头部
import businessHead from "@/components/businessHead.vue";
// 中间
import businessNormal from "@/components/businessNormal.vue";
// 按需引入 vant
import { getShareInfo, getShareCode } from "@src/http/module/vehicle";
import _ from "lodash";

export default {
  name: "quoteInfoShare",
  components: {
    businessHead,
    businessNormal
  },
  data() {
    return {
      share: false,
      shareCode: "",
      checked: true,
      showBtn: false,
      screenshots: false,
      personInfo: {},
      claimInfo: [], // 上半年理赔
      totalPayment: 0 // 总保费
    };
  },
  created() {
    let orderId = this.$GetRequest("orderId");
    let shareCode = this.$GetRequest("shareCode");
    if (shareCode) {
      this.share = true;
      this.shareCode = shareCode;
      this.getShareInfo(shareCode);
    } else {
      // this.setOrderId(orderId);
      this.personInfo = Object.assign({}, this.car, this.carOwner, {
        phone: this.carOwner.phoneNo
      });
      console.log(this.personInfo);
      // this.getShareInfo();
    }
  },
  mounted() {
    if (window) {
      window.addEventListener("scroll", () => {
        this.scrollFunction();
      });
    }
  },
  computed: {
    ...mapState("vehicle", {
      orderId: state => state.orderId,
      car: state => state.car,
      carOwner: state => state.carOwner,
      insurePlan: state => state.insurancePlan
    })
  },
  methods: {
    ...mapMutations("vehicle", ["setOrderId"]),
    ...mapActions("vehicle", ["initWithDetail"]),
    /**
     * 分享
     * @param {String} 根据参数 shareImage 截图分享 shareLink 链接分享 shareText 文本分享 shareSMS 短信分享
     */
    async sharePage(param) {
      let licenseNo = this.car.licenseNo || "未上牌";
      let carOwner = this.carOwner.name;
      let products = [];
      let productName =
        "尊敬的客户，" +
        licenseNo +
        "，" +
        carOwner +
        "，您投保该车的报价如下：";
      let totalPremiumWithoutTax = 0;
      if (this.insurePlan.trafficChecked == "1") {
        let trafficDuePremium = this.insurePlan.trafficDuePremium || "0";
        trafficDuePremium = Number(trafficDuePremium).toFixed(2);
        productName += `交强险${trafficDuePremium}元；`;
        productName += `保险起止时间：${this.insurePlan.trafficEffectiveStartDate}-${this.insurePlan.trafficEffectiveEndDate}；`;
        totalPremiumWithoutTax =
          Number(totalPremiumWithoutTax) + Number(trafficDuePremium);
      }
      if (this.insurePlan.businessChecked == "1") {
        let businessDuePremium = this.insurePlan.businessDuePremium || "";
        businessDuePremium = Number(businessDuePremium).toFixed(2);
        let SDEWPremium = 0;
        productName += `商业险${businessDuePremium}元，`;
        productName += `保险起止时间：${this.insurePlan.businessEffectiveStartDate}-${this.insurePlan.businessEffectiveEndDate}，其中：`;
        this.insurePlan.businessRisks.forEach(risk => {
          let riskName = "";
          let premium = Number(risk.premium).toFixed(2);
          riskName += `${risk.kindName}${premium}元`;
          if (risk.kindFlag == "1") {
            let amount = (risk.amount / 10000).toFixed(2);
            riskName += `（不计免赔，保额${amount}万元）`;
            SDEWPremium += risk.nonDeductiblePremium;
          } else {
            if (risk.kindCode != "C100134") {
              let amount = (risk.amount / 10000).toFixed(2);
              riskName += `（保额${amount}万元）`;
            }
          }
          products.push(riskName);
        });
        productName += products.join("，");
        if (SDEWPremium > 0) {
          SDEWPremium = SDEWPremium.toFixed(2);
          productName += `，不计免赔特约条款${SDEWPremium}元`;
        }
        productName += "；";
        totalPremiumWithoutTax += Number(businessDuePremium);
      }
      if (this.insurePlan.wealthAccChecked == "1") {
        let wealthAccPremium = this.insurePlan.wealthAccPremium || "";
        let wealthAccFormulaGroupName =
          this.insurePlan.wealthAccFormulaGroupName || "";
        let wealthAccFormulaName = this.insurePlan.wealthAccFormulaName || "";
        wealthAccPremium = Number(wealthAccPremium).toFixed(2);
        productName += `联合销售${wealthAccPremium}元，方案为${wealthAccFormulaGroupName}(${wealthAccFormulaName})；`;
        totalPremiumWithoutTax += Number(wealthAccPremium);
      }
      let name = this.localStorage.get("userInfo", {}).userName;
      let totalPremium = Number(this.insurePlan.totalPremium).toFixed(2);
      let sumPayment = this.insurePlan.tax.sumPayment || "";
      sumPayment = Number(sumPayment).toFixed(2);

      totalPremiumWithoutTax = Number.parseFloat(totalPremiumWithoutTax);
      totalPremiumWithoutTax = totalPremiumWithoutTax.toFixed(2);

      productName += `合计保费${totalPremiumWithoutTax}元。`;
      if (sumPayment > 0) {
        productName += `另代收车船税${sumPayment}元，共计${totalPremium}元。`;
      }
      productName += `如您没有异议，我就给你保上啦。若您有任何疑问，请与${name}联系。[中国大地保险]`;
      console.log(productName);
      if (param === "shareLink" || param === "shareText") {
        let shareCode = this.shareCode;
        if (!shareCode) {
          try {
            const res = await getShareCode({ orderId: this.orderId });
            if (res.code == 1) {
              this.shareCode = res.data;
            } else {
              this.$toast("请稍后重试");
            }
          } catch (error) {
            this.$toast("请稍后重试");
          }
        }
        let shareObject = {
          title: "车险报价分享", //分享标题
          sharePlatform: ["0"] //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
        };
        if (param === "shareLink") {
          shareObject = Object.assign(shareObject, {
            shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
            imgURL:
              "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //base64
            url:
              this.$rootPath +
              "/quoteInfoShare?shareCode=" +
              this.shareCode +
              "&recommendCode=" +
              encodeURIComponent(this.recommendCode), //分享链接
            desc: productName //分享描述
          });
        } else if (param === "shareText") {
          shareObject.shareType = "text";
          shareObject.desc = productName;
        }
        this.$native.share(shareObject, res => {
          console.log(param, "分享到微信");
        });
      } else if (param === "shareImage") {
        let paramsObj = {
          sharePlatform: ["0"],
          shareType: "img"
        };
        this.screenshots = true;
        this.$nextTick(() => {
          this.$native.shareLongShot(paramsObj, data => {
            this.screenshots = false;
            console.log(data, "截图分享");
          });
        });
      } else if (param === "shareSMS") {
        let paramsObj = {
          messageDes: productName,
          phoneNo: this.carOwner.phoneNo
        };
        this.$native.sendMessage(paramsObj, data => {
          console.log(data, "短信分享");
        });
      }
    },
    // 商业计划书
    async getShareInfo() {
      getShareInfo({ shareCode: this.shareCode })
        .then(res => {
          if (res.code === "1") {
            this.initWithDetail(res.data);
            this.personInfo = Object.assign({}, this.car, this.carOwner, {
              phone: this.carOwner.phoneNo
            });
            this.$nextTick(() => {
              this.$forceUpdate();
            });
          } else {
            console.log("not 1");
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    backTop() {
      let distance =
        document.documentElement.scrollTop || document.body.scrollTop; //获得当前高度
      let step = distance / 50; //每步的距离
      (function jump() {
        if (distance > 0) {
          distance -= step;
          window.scrollTo(0, distance);
          setTimeout(jump, 10);
        }
      })();
    },
    scrollFunction() {
      if (
        document.body.scrollTop > 100 ||
        document.documentElement.scrollTop > 100
      ) {
        this.showBtn = true;
      } else {
        this.showBtn = false;
      }
    }
  },
  beforeDestroy() {
    if (window) {
      window.removeEventListener("scroll", () => {});
    }
  }
};
</script>
<style lang="less" scoped>
// common
// font
.commonText15 {
  font-size: 15px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
  line-height: 21px;
}
.commonText18 {
  font-size: 18px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(0, 0, 0, 1);
  line-height: 16px;
}
.commonText14 {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(33, 85, 159, 1);
  line-height: 20px;
}
.commonText20 {
  font-size: 20px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(0, 0, 0, 1);
  line-height: 16px;
}
// margin
.mt9 {
  margin-top: 9px;
}
.mb9 {
  margin-bottom: 9px;
}
// color
.color4A4A4A {
  color: #4a4a4a;
}
// common
.businessPlanCover {
  background: linear-gradient(
    180deg,
    rgba(191, 223, 255, 1) 0%,
    rgba(228, 241, 255, 1) 100%
  );
  // overflow: scroll;
  // -webkit-overflow-scrolling: touch;
  // overflow-scrolling: touch;
  padding-bottom: 65px;
  .setPos {
    position: relative;
    // height: 100%;
    top: -70px;
    margin-bottom: -70px;
  }
  .starItem {
    background-color: white;
    margin: 0 11px;
    border-radius: 10px;
    .starItemTitle {
      display: flex;
      flex-direction: row;
      padding-top: 20px;
      margin-left: 12px;
    }
    // 修改组件部分
    /deep/ .equityCover {
      border: none;
      border-radius: unset;
      padding: 34px 0 14px;
    }
  }
  .saleItem {
    padding: 22px 16px 28px;
    background-color: white;
    margin: 0 11px;
    margin-top: 11px;
    border-radius: 10px;
    .infoPart {
      display: flex;
      margin-top: 23px;
      .headImg {
        width: 49px;
        height: 49px;
        border-radius: 50%;
        margin-right: 18px;
        img {
          width: 100%;
        }
      }
      .info {
        // span {
        //   padding-left: 8px;
        // }
        .lineTip {
          position: relative;
          padding-left: 0px;
          padding-right: 8px;
        }
        .lineTip::after {
          position: absolute;
          box-sizing: border-box;
          content: " ";
          pointer-events: none;
          right: 0;
          top: 4px;
          bottom: 4px;
          border-right: 1px solid #979797;
          // -webkit-transform: scaleX(0.5);
          // transform: scaleX(0.5);
        }
        .contactItem {
          display: flex;
          align-items: center;
          .icon {
            width: 9px;
            height: 9px;
          }
        }
      }
    }
  }
  .qrCodePart {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 20px;
    img {
      width: 116px;
      height: 116px;
      margin-bottom: 6px;
    }
    .QRCode {
      width: 116px;
      height: 116px;
      margin-bottom: 6px;
    }
    p {
      width: 132px;
      text-align: center;
      font-size: 12px;
      color: #486895;
    }
  }
  .bottom {
    height: 46px;
    margin-top: 20px;
    margin-bottom: 20px;
    background: transparent;
    display: flex;
    justify-content: space-around;
    .share-button {
      width: 46px;
      height: 46px;
      border-radius: 23px;
      background: #3b8afe;
      display: flex;
      justify-content: center;
      align-items: center;
      img {
        width: 100%;
        height: 100%;
      }
    }
  }
  .backBtn {
    width: 48px;
    height: 48px;
    border-radius: 50%;
    position: fixed;
    right: 10px;
    bottom: 186px;
    cursor: pointer;
    img {
      width: 100%;
    }
  }
}
</style>
