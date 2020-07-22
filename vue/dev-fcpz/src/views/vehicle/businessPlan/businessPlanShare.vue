<template>
  <div class="businessPlanCover">
    <businessHead :back="!share" />
    <div class="setPos">
      <business-normal
        :quotationObjInfo="insurePlan"
        :totalPayment="totalPayment"
        :claimInfo="claimInfo"
        :personInfo="personInfo"
        :share="share"
        :quoteInfoVisible="quoteInfoVisible"
        :claimInfoVisible="claimInfoVisible"
        @change="changeCheck"
      />
      <!-- 客户权益 -->
      <div class="starItem" v-show="starVisible">
        <div class="starItemTitle">
          <van-checkbox
            v-if="!share"
            v-model="starChecked"
            icon-size="16px"
            style="margin-right:8px;"
          />
          <span class="commonText15">客户权益</span>
        </div>
        <star-equity :isEquity="false" :isOut="true" :starInfo="starRight" />
      </div>
      <!-- 业务员名片 -->
      <div class="saleItem" v-show="saleVisible">
        <div style="display:flex;flex-direction: column;">
          <div style="display:flex;flex-direction: row;">
            <van-checkbox
              v-if="!share"
              v-model="saleChecked"
              icon-size="16px"
              style="margin-right:8px;"
            />
            <span class="commonText15">业务员名片</span>
          </div>
          <div class="infoPart">
            <div class="headImg">
              <img
                src="../../../assets/vehicle/businessPlan/defaultHead.png"
                alt=""
              />
            </div>
            <div class="info">
              <p class="commonText20 mb9">{{ operatorInfo.optName || "" }}</p>
              <div
                v-show="operatorInfo.rankName"
                class="commonText14 color4A4A4A"
              >
                {{ (operatorInfo.rankName || "").trim() }}
              </div>
              <div
                v-show="operatorInfo.makeCompanyCName"
                class="commonText14 color4A4A4A"
              >
                {{ (operatorInfo.makeCompanyCName || "").trim() }}
              </div>
              <!-- </div> -->
              <a
                class="contactItem mt9"
                v-if="operatorInfo.phone"
                :href="'tel:' + operatorInfo.phone"
                @click="e => (share ? '' : e.preventDefault())"
              >
                <img
                  class="icon"
                  src="../../../assets/vehicle/businessPlan/mobile.png"
                  alt=""
                />
                <span class="commonText12 color4A4A4A">
                  {{ operatorInfo.phone || "" }}
                </span>
              </a>
              <div class="contactItem" v-if="operatorInfo.email">
                <img
                  class="icon"
                  src="../../../assets/vehicle/businessPlan/email.png"
                  alt=""
                />
                <span class="commonText12 color4A4A4A">
                  {{ operatorInfo.email || "" }}
                </span>
              </div>
            </div>
          </div>
        </div>
        <div class="qrcode-container">
          <!-- qrCode -->
          <div id="qrcode"></div>
          <div class="qrCodePart">
            <img class="QRCode" :src="qrcodebase64" />
            <p>关注增值服务微信公众号 立享以上服务</p>
          </div>
        </div>
      </div>
    </div>
    <!-- share  暂时隐藏链接分享 TODU ZHAOSHUHUI-->
    <div class="bottom" v-if="!share">
      <van-button
        id="share"
        @click="sharePage('sharePage')"
        type="primary"
        color="#3B8AFE"
      >
        分享
      </van-button>
    </div>
    <!-- back to top -->
    <div class="backBtn" v-if="showBtn" @click="backTop">
      <img src="../../../assets/vehicle/businessPlan/backTop.png" alt="" />
    </div>
  </div>
</template>
<script>
import { mapState, mapMutations } from "vuex";
// 头部
import businessHead from "@/components/businessHead.vue";
// 中间
import businessNormal from "@/components/businessNormal.vue";
// 星级
import starEquity from "@/components/starEquity.vue";
// 按需引入 vant
import { Toast } from "vant";
import {
  getBizPlan,
  getShareBizPlan,
  getShareCode,
  getBizPlanShareCode
} from "@src/http/module/vehicle";
import _ from "lodash";
import QRCode from "qrcodejs2";
import { trackMap } from "@src/utils/index.js";
export default {
  name: "businessPlanInside",
  components: {
    businessHead,
    businessNormal,
    starEquity
  },
  data() {
    return {
      share: false,
      screenshot: false,
      quoteInfoChecked: true,
      claimInfoChecked: true,
      starChecked: true,
      saleChecked: true,
      showBtn: false,
      operatorInfo: this.localStorage.get("userInfo", {}),
      personInfo: {},
      starRight: {
        custCoreIntegral: {},
        insuredName: "",
        newIntegral: 0,
        newCustLevel: 0
      },
      claimInfo: [], // 上半年理赔
      totalPayment: 0, // 总保费
      recommendCode: "",
      qrcode: null,
      qrcodebase64: ""
    };
  },
  created() {
    let orderId = this.$GetRequest("orderId");
    let shareCode = this.$GetRequest("shareCode");
    if (shareCode) {
      this.share = true;
      this.getShareBizPlan(shareCode);
    } else if (orderId) {
      this.setOrderId(orderId);
      this.getBizPlan();
    }
  },
  computed: {
    ...mapState("vehicle", {
      orderId: state => state.orderId,
      insurePlan: state => state.insurancePlan,
      custType: state => state.recognizee.custType
    }),
    quoteInfoVisible() {
      if (this.share) {
        let s = this.$GetRequest("s");
        if (s && typeof s === "string") {
          return s.includes("q");
        }
      }
      if (this.screenshot) {
        return this.quoteInfoChecked;
      }
      return true;
    },
    claimInfoVisible() {
      if (this.share) {
        let s = this.$GetRequest("s");
        if (s && typeof s === "string") {
          return s.includes("c");
        }
      }
      if (this.screenshot) {
        return this.claimInfoChecked;
      }
      return true;
    },
    starVisible() {
      if (this.share) {
        let s = this.$GetRequest("s");
        if (s && typeof s === "string") {
          return s.includes("r");
        }
      } else if (this.custType != "01") {
        return false;
      }
      if (this.screenshot) {
        return this.starChecked;
      }
      return true;
    },
    saleVisible() {
      if (this.share) {
        let s = this.$GetRequest("s");
        if (s && typeof s === "string") {
          return s.includes("s");
        }
      }
      if (this.screenshot) {
        return this.saleChecked;
      }
      return true;
    }
  },
  mounted() {
    let recommendCode = this.localStorage.get("userInfo", {}).recommendCode;
    if (!recommendCode) {
      recommendCode = decodeURIComponent(
        this.$GetRequest("recommendCode", true) + ""
      );
      this.recommendCode = recommendCode;
    }
    this.recommendCode = encodeURI(recommendCode);
    this.qrcode = new QRCode("qrcode", {
      text: this.recommendCode,
      width: 116,
      height: 116
    });
    let qrcodebase64 = "";
    if (this.qrcode._oDrawing._elCanvas.style.display == "none") {
      qrcodebase64 = this.qrcode._oDrawing._elImage.src;
    } else if (this.qrcode._oDrawing._elImage.style.display == "none") {
      qrcodebase64 = this.qrcode._oDrawing._elCanvas.toDataURL("image/png", 1);
    }
    let t = setTimeout(() => {
      this.qrcode._oDrawing._elCanvas.style.display = "none";
      this.qrcode._oDrawing._elImage.style.display = "none";
      this.qrcodebase64 = qrcodebase64;
      console.log(qrcodebase64);
      clearTimeout(t);
    }, 100);
    if (window) {
      window.addEventListener("scroll", () => {
        this.scrollFunction();
      });
    }
  },
  methods: {
    ...mapMutations("vehicle", ["setOrderId", "setInsurancePlan"]),
    changeCheck({ quoteInfoChecked, claimInfoChecked }) {
      if (typeof quoteInfoChecked === "boolean") {
        this.quoteInfoChecked = quoteInfoChecked;
      }
      if (typeof claimInfoChecked === "boolean") {
        this.claimInfoChecked = claimInfoChecked;
      }
    },
    /**
     * 分享
     * @param {String} 根据参数 shareScreen 截图分享 sharePage 链接分享
     */
    sharePage(param) {
      if (!this.quoteInfoChecked && !this.starChecked && !this.saleChecked) {
        return this.$dialog.alert({ message: "请至少选中一个分享" });
      }
      let s =
        (this.quoteInfoChecked ? "q" : "") +
        (this.claimInfoChecked ? "c" : "") +
        (this.starChecked ? "r" : "") +
        (this.saleChecked ? "s" : "");
      if (param === "sharePage") {
        getBizPlanShareCode({ orderId: this.orderId }).then(
          res => {
            if (res.code == "1") {
              let tempUrl =
                this.$rootPath +
                "/businessPlanShare?shareCode=" +
                res.data.shareCode +
                "&s=" +
                s +
                "&recommendCode=" +
                encodeURIComponent(this.recommendCode);
              let jsonObject = {
                sharePlatform: ["0", "2"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
                shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
                title:
                  "[" + (this.personInfo.licenseNo || "未上牌") + "]商业计划书", //分享标题
                imgURL:
                  "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
                image: "", //分享图片（base64编码）, 与imgURL二选一
                url: tempUrl, //分享链接
                desc:
                  "尊敬的客户，专属客户经理[" +
                  this.operatorInfo.optName +
                  "]为您设计的商业计划书，请查阅！保险让生活更美好！", //分享描述
                phoneNum: "" //电话号码,当需要短信分享时获取
              };
              console.log(jsonObject);
              this.$native.share(jsonObject, res => {
                console.log(res, "分享商业计划书");
                if (res.resultCode === "1") {
                  //埋点
                  trackMap(
                    "c_car_isure_ensure_prospectus_share",
                    "车险投保确认计划书分享",
                    {
                      c_salesperson_type: "N",
                      c_market_type: "Y",
                      c_market_name: "",
                      c_market_id: "",
                      c_share_way:
                        res.responseBody && res.responseBody.shareSuccessName,
                      c_event_category: "车险出单"
                    }
                  );
                }
              });
            }
          },
          error => {}
        );
      } else if (param === "shareScreen") {
        this.screenshot = true;
        let paramsObj = {
          sharePlatform: ["0", "1", "2", "4"],
          shareType: "img"
        };
        this.$native.shareLongShot(paramsObj, data => {
          this.screenshot = false;
          console.log(data, "截图分享");
        });
      }
    },
    // 商业计划书
    async getBizPlan() {
      const res = await getBizPlan({
        orderId: this.orderId
      });
      if (res.code == "1" && res.data) {
        this.resolveData(res);
        console.log(res.data, "heweiwei");
      } else {
        Toast(res.msg);
      }
    },
    resolveData(res) {
      // 上半年理赔
      if (
        Array.isArray(_.result(res.data, "quoteInfoVo.trafficLastClaims", null))
      ) {
        this.claimInfo.push(...res.data.quoteInfoVo.trafficLastClaims);
      }
      if (
        Array.isArray(
          _.result(res.data, "quoteInfoVo.businessLastClaims", null)
        )
      ) {
        this.claimInfo.push(...res.data.quoteInfoVo.businessLastClaims);
      }
      // 总保费
      let plans = _.result(res.data, "quoteInfoVo.plans", []);
      let planCode = _.result(res.data, "quoteInfoVo.planCode");
      let plan = plans.find(plan => plan.planCode == planCode);
      let totalPremium = 0;
      if (plan) {
        if (
          _.result(res.data, "quoteInfoVo.floating.discountCoefficient", "")
        ) {
          plan.discountCoefficient =
            _.result(
              res.data,
              "quoteInfoVo.floating.discountCoefficient",
              "0"
            ) * 100;
        }
        if (_.result(plan, "trafficChecked", "") == "1") {
          if (Number(plan.trafficDuePremium)) {
            totalPremium += plan.trafficDuePremium;
          }
          if (Number(_.result(plan, "tax.sumPayment", NaN))) {
            totalPremium += plan.tax.sumPayment;
          }
        }
        if (_.result(plan, "businessChecked", "") == "1") {
          if (Number(plan.businessDuePremium)) {
            totalPremium += plan.businessDuePremium;
          }
        }
        if (_.result(plan, "wealthAccChecked", "") == "1") {
          if (Number(plan.wealthAccPremium)) {
            totalPremium += plan.wealthAccPremium;
          }
        }
        totalPremium = Number(totalPremium).toFixed(2);
        plan.totalPremium = totalPremium;
        this.totalPayment = totalPremium;
        this.setInsurancePlan(plan);
      }
      // 人员信息
      this.personInfo = res.data.userInfoVO || {}; // 用户信息
      this.operatorInfo = res.data.operatorInfoVO || {}; // 操作员信息
      this.starRight.userName = _.result(res.data, "userInfoVO.name", ""); // 客户名字

      // 星级信息
      this.starRight = res.data.custOrderIntegralRes || {};
    },
    async getShareBizPlan(shareCode) {
      const res = await getShareBizPlan({ shareCode });
      if (res.code == "1" && res.data) {
        this.resolveData(res);
      } else {
        Toast(res.msg);
      }
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
      margin-top: 70px;
    }
  }
  .saleItem {
    padding: 22px 16px 28px;
    background-color: white;
    margin: 0 11px;
    margin-top: 11px;
    border-radius: 10px;
    display: flex;
    flex-direction: row;
    .infoPart {
      display: flex;
      margin-top: 23px;
      .headImg {
        width: 49px;
        height: 49px;
        border-radius: 50%;
        margin-right: 18px;
        display: flex;
        flex: none;
        img {
          width: 100%;
        }
      }
      .info {
        // span {
        //   padding-left: 8px;
        // }
        // word-break: break-all;
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
    img {
      width: 100px;
      height: 100px;
      margin-bottom: 6px;
    }
    .QRCode {
      width: 100px;
      height: 100px;
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
    margin-top: 20px;
    display: flex;
    #share {
      // width: 50%;
      flex: 1;
      height: 46px;
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
.qrcode-container {
}
</style>
