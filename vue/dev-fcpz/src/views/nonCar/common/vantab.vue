<!--
 * @Description: 货运无忧tab页面切换
 * @Date: 2020-4-6 14:25:45
 * @Author: LiuMengJuan
 * @LastEditTime: 2020-04-06 14:53:22
 * @FilePath: \vue-sales\src\views\nonCar\common\vantab.vue
 -->
<template>
  <div ref="tabBox">
    <div
      :class="{ position: true, posShow: centerList }"
      ref="posFix"
      :style="{ visibility: centerList ? '' : 'hidden' }"
    >
      <van-tabs
        swipeable
        @click="esent"
        type="line"
        color="#3B8AFE"
        line-height="5px"
        line-width="28px"
      >
        <van-tab
          v-for="(v, i) in message"
          :class="{ active: activeBtn == i }"
          :title="v.title"
          :key="v.id"
          title-style="line-height:0.8rem"
        ></van-tab>
      </van-tabs>
    </div>
    <div
      ref="navHeight"
      style="margin-top:-1.22667rem;height: 1.22667rem;visibility: hidden;"
    ></div>
  </div>
</template>

<script>
// import millionStatic from "@src/views/nonCar/healthInsurance/millionStatic.vue";
// import selection from "./selection";
import { NavBar, Tab, Search, List } from "vant";
export default {
  name: "vantab",
  components: {
    // millionStatic,
    // selection
  },
  props: {
    message: Array //导航栏标题
  },
  data() {
    return {
      posStyle: { visibility: "hidden" },
      fied: "fied",
      isProxy: false,
      productIcons: [
        require("@/assets/nonCar/propertyInsurance/copyRenewal.png"),
        require("@/assets/nonCar/propertyInsurance/copyRenewal2.png"),
        require("@/assets/nonCar/propertyInsurance/copyRenewal3.png"),
        require("@/assets/nonCar/propertyInsurance/bianZhi.png")
      ],
      guaranteeDetailSrc: require("@/assets/nonCar/propertyInsurance/guaranteeDetail.png"),
      formulaList: [],
      activeTab: "0",
      premium: "",
      channelInfo: {},
      activeBtn: 0,
      count: [0, 1, 2, 3, 4],
      centerList: false,
      ding: ""
    };
  },
  mounted() {
    let that = this;
    console.log("root", this);

    document
      .querySelector(".vantabClass")
      .addEventListener("scroll", function() {
        // console.log('into Scroll!!!');

        let scrTop = document.querySelector(".vantabClass").scrollTop;
        if (scrTop > 10) {
          let bannerTop = document.getElementById("vantab0").offsetTop;
          let bannerHeight = that.$refs.posFix.offsetHeight;
          let navHeight = that.$refs.navHeight.offsetHeight;

          let sTop = this.scrollTop;
          // console.log("sTop", sTop);
          // console.log("bannerTop2222222", bannerTop);
          // console.log("bannerHeight", bannerHeight);
          // console.log("++++", bannerTop - bannerHeight - navHeight);

          if (sTop >= bannerTop - bannerHeight - navHeight) {
            that.$refs.posFix.style.display = "block";
            that.centerList = true;
            that.$refs.posFix.style.marginTop = navHeight + "px";
          } else {
            that.$refs.posFix.style.display = "none";
            that.centerList = false;
          }
        }
      });
  },

  methods: {
    handleScroll() {
      let that = this;
      console.log(this);

      //  /垂直滚动的值兼容问题
      let scrollTopE =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop; //offsetTop 属性指定的父坐标的计算顶端位置 offsetHeight获取对象的高度
      let bannerTop = this.scrollTop;
      let bannerHeight = this.offsetHeight;
    },
    esent(event) {
      var wfEle = document.querySelector(".vantabClass");
      let temId = event == 0 ? 0 : event;
      let popDot = document.getElementById(`vantab${temId}`).offsetTop;
      let bannerHeight = this.$refs.posFix.offsetHeight;
      let navHeight = this.$refs.navHeight.offsetHeight;
      // console.log("popDot1", popDot);
      // console.log("popDot2", bannerHeight);
      // console.log("popDot3", navHeight);
      // console.log("popDot4", popDot - bannerHeight - navHeight);

      wfEle.scrollTop = popDot - bannerHeight - navHeight;
    },
    toVehicleChannel() {
      let params = {
        riskCode: "WTN",
        confirm: channel => {
          console.log(channel);
          this.channelInfo = channel;
        }
      };
      this.setChannel({ isProxy: this.isProxy ? "1" : "0" });
      console.log(111, this.channelInfo);
      this.$router.push({
        name: "vehicleChannel",
        params: params
      });
    },

    // 是否代理
    changeIsProxy(type) {
      if (type == "y") {
        this.isProxy = true;
      } else {
        this.isProxy = false;
      }
    },
    // 返回上一页
    onClickLeft() {
      this.$globalBack();
    },

    doInsure() {
      let isAgent = this.isProxy ? "1" : "0"; //是否代理
      // Toast({ forbidClick: true, message: "点击了投保按钮", type: "success" });
      this.premium = this.formulaList[this.activeTab].formatFJSZ;

      let obj = {
        personInformation: {
          isAgent: isAgent,
          premium: this.premium
        }
      };

      this.$store.dispatch("nonCar/savePersonInformation", obj);
      localStorage.setItem("personInformation", JSON.stringify(obj));
      this.$router.push({
        name: "proInsuranceInformation"
      });
    }
  }
};
</script>

<style lang="less" scoped>
#worryFree {
  width: 100%;
  height: 100%;
  background: rgba(247, 247, 247, 1);
  font-family: PingFangSC-Semibold, PingFang SC;
  .van-tabs__line {
    color: red;
    top: 30px;
  }
  .van-tabs--line .van-tabs__wrap {
    z-index: 1;
    width: 100%;
    position: relative;
  }

  // padding-top: 46px;
  // height: calc(100vh - 50px);
  // overflow: scroll;
  .top10 {
    margin-top: 10px;
  }
  .right10 {
    margin-right: 10px;
  }
  .right {
    float: right;
  }
  .verticalMid {
    vertical-align: middle;
  }
  * {
    box-sizing: border-box;
  }
  button {
    outline: none;
    background: none;
    border: none;
  }
  .bottom18 {
    margin-bottom: 18px;
  }
  .spaceWhite {
    width: 18px;
    height: 36px;
    border-radius: 9px;
    background: #fff;
  }
  .header {
    // height: 224px;
    background-image: url(../../../assets/nonCar/propertyInsurance/productHeader.png);
    background-repeat: no-repeat;
    background-size: 100% auto;
    .van-nav-bar {
      background: none;
      display: flex;
      align-items: center;
      img {
        width: 22px;
        height: auto;
      }
    }
    .van-nav-bar .van-icon {
      font-size: 24px;
      font-weight: 500;
      color: rgba(74, 74, 74, 1);
    }
    .van-nav-bar__title {
      font-size: 18px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(74, 74, 74, 1);
    }
  }
  .productTitle {
    height: 67px;
    background: rgba(255, 255, 255, 1);
    display: flex;
    align-items: center;
    .desc {
      display: flex;
      flex-direction: column;
      padding: 12px 15px;
      width: 60%;
      span:first-child {
        font-size: 16px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
      }
      span:last-child {
        font-size: 12px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        margin-top: 5px;
      }
    }
    p:last-child {
      text-align: center;
      width: 40%;
      font-size: 20px;
      font-family: DINAlternate-Bold, DINAlternate;
      font-weight: bold;
      color: rgba(252, 130, 14, 1);
    }
  }
  .scrollIntoView {
    height: 30px;
    background-color: rgba(36, 128, 234, 1);
  }
  .productContent {
    // height: 2770px;
    background: rgba(36, 128, 234, 1);
    padding-bottom: 65px;
    // margin-top:40px;
    .title {
      height: 43px;
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgb(37, 36, 36);
      // line-height: 43px;
      text-align: center;
      padding: 18px 0 9px 0;
    }

    .lipei {
      width: 355px;
      height: 336px;
      margin: auto;
    }
    .card {
      width: 355px;
      // height: 223px;
      background: rgba(255, 255, 255, 1);
      border-radius: 8px;
      margin: 0 auto;
      padding: 10px 0;
      .van-tab {
        font-size: 14px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(17, 17, 17, 1);
        line-height: 46px;
      }
      .van-tab--active {
        font-size: 17px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(17, 17, 17, 1);
      }

      .van-tabs__nav--line {
        padding-bottom: 0%;
      }
      .van-tabs__wrap {
        padding: 0 8px;
      }
      .titleStrong {
        height: 40px;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
        // line-height: 40px;
        margin: 11px 23px 17px;
      }
      // .van-tabs van-tabs--line {
      //   position: fixed;
      //   overflow: hidden;
      //   top: 0;
      // }
      .insureDesc {
        background: rgba(247, 247, 247, 1);
        border-radius: 8px;
        // height: 93px;
        margin: 0 10px;
        padding: 10px 13px;
        line-height: 22px;
        .strong {
          font-size: 14px;
          font-family: PingFangSC-Semibold, PingFang SC;
          font-weight: 600;
          color: rgba(51, 51, 51, 1);
          margin-bottom: 7px;
        }
        .light {
          font-size: 14px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(17, 17, 17, 1);
        }
        .gray {
          font-size: 13px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(153, 153, 153, 1);
        }
      }
      .right {
        text-align: right;
      }
    }
    .personInfo {
      width: 355px;
      height: 252px;
      background: rgba(255, 255, 255, 0.99);
      border-radius: 8px;
      margin: 10px auto 0;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      line-height: 20px;
      padding: 5px 0;
      box-sizing: border-box;
      .minTitle {
        font-size: 17px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(17, 17, 17, 1);
        line-height: 46px;
      }
      [class*="van-hairline"]::after {
        border: none;
      }
      button {
        width: 54px;
        height: 27px;
        border-radius: 3px;
        border: 1px solid rgba(238, 238, 238, 1);
        font-size: 12px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(185, 185, 185, 1);
      }
      button:first-child {
        margin-right: 15px;
      }
      .blue {
        background: rgba(238, 245, 255, 1);
        color: rgba(43, 121, 237, 1);
      }
      .van-checkbox {
        float: right;
      }
    }
    .titleWhite {
      text-align: center;
      padding: 15px 0 5px 0;
      h5 {
        font-size: 16px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(255, 255, 255, 1);
        line-height: 16px;
        margin-bottom: 8px;
      }

      p {
        height: 13px;
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
        line-height: 13px;
      }
      .warp {
        width: 221px;
        height: 36px;
        text-align: center;
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
        line-height: 18px;
        margin: 0 auto;
      }
    }
    .productInfo {
      width: 356px;
      height: 232px;
      background: rgba(255, 255, 255, 1);
      border-radius: 8px;
      margin: 0 auto;
      padding: 20px 37px 20px 18px;
      .productText {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(17, 17, 17, 1);
        line-height: 20px;
      }
      img {
        width: 45px;
        height: auto;
      }
    }
    .pic {
      width: 356px;
      height: 379px;
      background: rgba(255, 255, 255, 1);
      border-radius: 8px;
      margin: 0 auto;
      padding: 25px 17px;
      img {
        width: 322px;
        height: auto;
      }
    }
    .van-cell-group {
      border-radius: 8px;
    }
    .van-cell:last-child {
      border-radius: 8px;
    }
  }
  .blueBack {
    background-image: url(../../../assets/nonCar/propertyInsurance/titleBack.png);
    background-repeat: no-repeat;
    background-size: 100% auto;
  }

  .clause {
    width: 355px;
    height: 120px;
    background: rgba(255, 255, 255, 1);
    border-radius: 8px 0px 8px 8px;
    margin: 23px auto 0;
    position: relative;
    .blueDiv {
      width: 355px;
      height: 18px;
      background: linear-gradient(
        360deg,
        rgba(44, 121, 254, 1) 0%,
        rgba(68, 170, 248, 1) 30%,
        rgba(44, 120, 255, 1) 100%
      );
      border-radius: 9px;
      position: absolute;
      top: -7px;
    }
  }
  .bottom {
    width: 100%;
    height: 46px;
    position: fixed;
    bottom: 0;
    background: rgba(255, 255, 255, 1);
    text-align: center;
    button {
      width: 100%;
      // width: 140px;
      height: 46px;
      background: linear-gradient(
        125deg,
        rgba(42, 128, 255, 1) 0%,
        rgba(42, 165, 255, 1) 100%
      );
      font-size: 16px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(255, 255, 255, 1);
      line-height: 46px;
      text-align: center;
    }
    p {
      height: 46px;
      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      padding: 5px 24px;
      span:first-child {
        font-size: 17px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(252, 130, 14, 1);
      }
      span:last-child {
        font-size: 11px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
      }
    }
  }

  .van-tabs__wrap van-tabs__wrap--scrollable van-hairline--top-bottom {
    .van-tabs__line {
      width: 28px !important;
      height: 5px !important;
      background: rgba(36, 128, 234, 1);
      border-radius: 6px;
      bottom: 0;
    }
  }
}
.position {
  width: 100%;
}
.posShow {
  position: fixed;
  top: 0;
  z-index: 11;
}
.visb {
  visibility: hidden;
}
.fied {
  padding-top: 43.8px;
  box-sizing: border-box;
}
.van-tabs__nav--line {
  height: 120%;
}
/deep/.van-tabs__line {
  top: 30px;
}
</style>
