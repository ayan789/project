<template>
  <div class="starEquityCover" ref="container">
    <img class="bg" src="../assets/vehicle/businessPlan/starBg.png" />
    <!-- 个人信息 -->
    <div class="personInfo">
      <div class="headImg">
        <img src="../assets/vehicle/businessPlan/defaultHead.png" alt="" />
      </div>
      <p class="word commonText16">
        <span class="mr6">被保人</span>
        <span>{{ starInfo.insuredName || "" }}</span>
      </p>
    </div>
    <!-- 现有 -->
    <div class="starItem curBg" v-if="flagShow">
      <p
        :class="[isOut ? 'outTip' : '', 'tipName', 'commonText12', 'curTipBg']"
        style="line-height:inherit"
      >
        现有
      </p>
      <!-- 等级积分 -->
      <div class="starInfo">
        <div class="left">
          <p class="commonText11 mb8">现有星级</p>
          <div class="starCover">
            <img
              v-for="i in 5"
              :key="i"
              :src="i <= now.custLevel ? curGet : curNormal"
              alt=""
            />
          </div>
        </div>
        <div class="right">
          <p class="commonText11 mb8">现有积分</p>
          <p class="commonText16 commonText18">{{ now.custIntegral }}</p>
        </div>
      </div>
      <!-- progress bar -->
      <div class="progressBarCover">
        <div class="scoreNumber">
          <div
            v-for="(item, index) in levelInfo"
            :key="index"
            :class="[
              'commonText12',
              'defaultWord',
              index <= now.custLevel ? 'actWord' : ''
            ]"
          >
            {{ item.point }}
          </div>
        </div>
        <div class="scoreProgress">
          <div
            class="progress"
            :style="progressWidth(now.custLevel, now.custIntegral)"
          ></div>
          <div
            class="dotFather"
            v-for="(item, index) in levelInfo"
            :key="index"
          >
            <div
              :class="[
                'defaultCircle',
                index < now.custLevel
                  ? 'actCircle'
                  : index == now.custLevel
                  ? 'curCircle'
                  : '',
                index == 1 ? 'firstCircle' : '',
                index == 2 ? 'secCircle' : '',
                index == 3 ? 'thirdCircle' : '',
                index == 4 ? 'fourthCircle' : ''
              ]"
            ></div>
          </div>
        </div>
        <div class="scoreChinese">
          <div
            :class="['defaultWord', index < now.custLevel ? 'actWord' : '']"
            v-for="(item, index) in levelInfo"
            :key="index"
          >
            {{ item.name }}
          </div>
        </div>
      </div>
    </div>
    <!-- 出单后 -->
    <div class="starItem orderBg">
      <p
        :class="[
          isOut ? 'outTip' : '',
          'tipName',
          'commonText12',
          'orderTipBg'
        ]"
        style="line-height:inherit"
      >
        出单后
      </p>
      <!-- 等级积分 -->
      <div class="starInfo">
        <div class="left mr30">
          <p class="commonText11 mb8 color896C60">出单后星级</p>
          <div class="starCover">
            <img
              v-for="i in 5"
              :key="i"
              :src="i <= starInfo.newCustLevel ? orderGet : orderNormal"
              alt=""
            />
          </div>
        </div>
        <div class="right mr30">
          <p class="commonText11 mb8 color896C60">出单所获积分</p>
          <p class="commonText16 commonText18 color6D3B28">
            {{ starInfo.newIntegral }}
          </p>
        </div>
        <div class="right">
          <p class="commonText11 mb8 color896C60">积分总计</p>
          <p class="commonText16 commonText18 color6D3B28">
            {{ starInfo.totalCore }}
          </p>
        </div>
      </div>
      <!-- progress bar -->
      <div class="progressBarCover">
        <div class="scoreNumber">
          <div
            v-for="(item, index) in levelInfo"
            :key="index"
            :class="[
              'orderDefaultWord',
              index < starInfo.newCustLevel ? 'orderActWord' : ''
            ]"
          >
            {{ item.point }}
          </div>
        </div>
        <div class="scoreProgress">
          <div
            class="progress orderProgress"
            :style="progressWidth(starInfo.newCustLevel, starInfo.totalCore)"
          ></div>
          <div
            class="dotFather"
            v-for="(item, index) in levelInfo"
            :key="index"
          >
            <div
              :class="[
                'defaultCircle',
                'orderDefault',
                index < starInfo.newCustLevel
                  ? 'orderActCir'
                  : index == starInfo.newCustLevel
                  ? 'orderCurCir'
                  : '',
                index == 1 ? 'firstCircle' : '',
                index == 2 ? 'secCircle' : '',
                index == 3 ? 'thirdCircle' : '',
                index == 4 ? 'fourthCircle' : ''
              ]"
            ></div>
          </div>
        </div>
        <div class="scoreChinese">
          <div
            :class="[
              'orderDefaultWord',
              index < starInfo.newCustLevel ? 'orderActWord' : ''
            ]"
            v-for="(item, index) in levelInfo"
            :key="index"
          >
            {{ item.name }}
          </div>
        </div>
      </div>
    </div>
    <!-- 权益 -->
    <div class="equityCover" v-if="equitiesList.length">
      <p class="tit commonText16 commonText14 color6D3B28">
        {{ starInfo.newCustLevel | levelChinese }}
      </p>
      <div :class="[isEquity ? 'infoPart' : 'planInfoPart']">
        <div
          :class="['item', index % 2 == 0 ? 'mr11' : '']"
          v-for="(item, index) in equitiesList"
          :key="index"
        >
          <div class="infoIcon">
            <img src="../assets/vehicle/businessPlan/gift.png" alt="" />
          </div>
          <div class="content">
            <p class="commonText12 color333333">{{ item.serviceName }}</p>
          </div>
        </div>
      </div>
    </div>
    <!-- weChat -->
    <div class="weChatPart" ref="wechat" v-if="isShare" @click="share">
      <div class="weIcon">
        <img src="../assets/vehicle/businessPlan/wechat.png" alt="" />
      </div>
      <p class="commonText12 color666666">微信分享</p>
    </div>
  </div>
</template>
<script>
import Html2canvas from "html2canvas";
import _ from "lodash";
export default {
  props: {
    name: String,
    starInfo: Object,
    isShare: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      flagShow: true,
      active: 1,
      curNormal: require("../assets/vehicle/businessPlan/curNormal.png"),
      curGet: require("../assets/vehicle/businessPlan/curGet.png"),
      orderNormal: require("../assets/vehicle/businessPlan/orderNormal.png"),
      orderGet: require("../assets/vehicle/businessPlan/orderGet.png"),
      levelInfo: [
        {
          id: 1,
          name: "1星级",
          point: 0
        },
        {
          id: 2,
          name: "2星级",
          point: 3001
        },
        {
          id: 3,
          name: "3星级",
          point: 6001
        },
        {
          id: 4,
          name: "4星级",
          point: 12001
        },
        {
          id: 5,
          name: "5星级",
          point: 20001
        }
      ]
    };
  },
  computed: {
    now() {
      let now = _.result(this.starInfo, "custCoreIntegral");
      if (!now) {
        now = {
          custIntegral: 0,
          custLevel: "0",
          integralDate: ""
        };
      }
      console.log(now.custIntegral);
      return now;
    },
    isEquity() {
      return false;
    },
    isOut() {
      return false;
    },
    equitiesList() {
      return _.result(
        this.starInfo,
        "equities.serviceItemsList[0].serviceItemList",
        []
      );
    }
  },
  mounted() {
    // this.$nextTick(() => {
    //   console.log(this.now.custIntegral, "now.custIntegral");
    //   console.log(this.starInfo.totalCore, "this.starInfo.totalCore");
    // });
    setTimeout(() => {
      // console.log(this.now.custIntegral, "now.custIntegral");
      // console.log(this.starInfo.totalCore, "this.starInfo.totalCore");
      // if (this.now.custIntegral < this.starInfo.totalCore) {
      //   this.flagShow = true
      // } else {
      //   this.flagShow = false
      // }
      // console.log(this.flagShow,'1111')
    }, 1000);
  },
  filters: {
    levelChinese(v) {
      switch (String(v)) {
        case "1":
          return "一星权益";
        case "2":
          return "二星权益";
        case "3":
          return "三星权益";
        case "4":
          return "四星权益";
        case "5":
          return "五星权益";
        default:
          return "";
      }
    }
  },
  methods: {
    progressWidth(level, integral) {
      var wid = "calc(";
      let persent = (level > 0 ? level - 1 : 0) * 25;
      let levelIndex = this.levelInfo.findIndex(item => item.id == level);
      if (levelIndex != -1) {
        let nextLevelIndex = levelIndex + 1;
        if (nextLevelIndex <= this.levelInfo.length - 1) {
          persent +=
            ((integral - this.levelInfo[levelIndex].point) /
              (this.levelInfo[nextLevelIndex].point -
                this.levelInfo[levelIndex].point)) *
            25;
        }
      }
      persent = Math.max(0, persent);
      persent = Math.min(100, persent);
      persent = persent.toFixed(2) + "%)";
      wid += persent;
      return `width: ${wid};`;
    },
    share() {
      this.$refs.wechat.style.display = "none";
      this.$toast.loading();
      Html2canvas(this.$refs.container).then(canvas => {
        let base64image = canvas.toDataURL("image/jpeg", 0.5);
        base64image = base64image.replace("data:image/jpeg;base64,", "");
        console.log(base64image);
        const jsonObject = {
          sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
          shareType: "img", //"url" - 链接； "img" - 图片； "text" - 文字
          image: base64image //base64
        };
        this.$toast.clear();
        //调用壳方法，传递调用参数和接受响应数据
        this.$native.share(jsonObject, data => {
          this.$refs.wechat.style.display = "block";
          // Toast("share 接口调用成功，壳响应数据:" + data);
        });
      });
    }
  },
  updated() {
    if (this.now.custIntegral < this.starInfo.totalCore) {
      this.flagShow = true;
    } else {
      this.flagShow = false;
    }
  }
};
</script>
<style lang="less" scoped>
// 通用
// 字体
.commonText16 {
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(18, 68, 139, 1);
  line-height: 22px;
}
.commonText18 {
  font-size: 18px !important;
}
.commonText14 {
  font-size: 14px !important;
  font-weight: 500 !important;
  line-height: 20px !important;
}
.commonText12 {
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  line-height: 17px;
}
.commonText11 {
  font-size: 11px;
  font-family: PingFangHK-Regular, PingFangHK;
  font-weight: 400;
  color: rgba(100, 134, 181, 1);
  line-height: 16px;
}
.commonText10 {
  font-size: 10px !important;
  line-height: 14px !important;
}
// 边距
.mr6 {
  margin-right: 6px;
}
.mb8 {
  margin-bottom: 8px;
}
.mr30 {
  margin-right: 30px !important;
}
.mr11 {
  margin-right: 11px;
}
// 颜色
.color6D3B28 {
  color: #6d3b28;
}
.color333333 {
  color: #333333 !important;
  line-height: 16px !important;
}
.color666666 {
  color: #666666 !important;
}
.color896C60 {
  color: #896c60;
}
.color6D3B28 {
  color: #6d3b28;
}
.defaultWord {
  color: #829bbe;
}
.actWord {
  color: #1865d5;
}
.orderDefaultWord {
  color: #a57b6b;
}
.orderActWord {
  color: #6d3b28;
}
// 通用
.starEquityCover {
  position: relative;
  padding: 0 12px 25px;
  overflow: visible;
  .personInfo {
    margin: 15px;
    display: flex;
    display: -webkit-flex;
    align-items: center;
    -webkit-align-items: center;
    .headImg {
      width: 32px;
      height: 32px;
      margin-right: 16px;
      img {
        width: 100%;
        border-radius: 50%;
      }
    }
  }
  .starItem {
    margin: 15px 0;
    position: relative;
    margin-top: 20px;
    box-shadow: 0px 4px 4px 0px rgba(242, 247, 255, 1);
    border-radius: 10px;
    padding: 30px 36px 14px;

    // 标签状态
    .tipName {
      position: absolute;
      right: 0;
      top: 12px;
      padding: 2px 15px;
      border-top-left-radius: 100px;
      border-bottom-left-radius: 100px;
    }
    .outTip {
      top: 6px;
      padding-right: 4px;
    }
    .curTipBg {
      background: linear-gradient(
        77deg,
        rgba(35, 99, 219, 1) 0%,
        rgba(55, 147, 255, 1) 100%,
        rgba(55, 147, 255, 1) 100%
      );
    }
    .orderTipBg {
      background: linear-gradient(
        90deg,
        rgba(202, 41, 43, 1) 0%,
        rgba(155, 12, 11, 1) 100%
      );
    }
    // 星级&积分
    .starInfo {
      display: flex;
      display: -webkit-flex;
      .left {
        margin-right: 62px;
        .starCover {
          img {
            width: 14px;
          }
        }
      }
    }
    // progress bar
    .progressBarCover {
      .van-steps {
        background: transparent;
      }
      //   display: flex;
      .item {
        display: flex;
        display: -webkit-flex;
        -webkit-align-items: center;
        align-items: center;
        -webkit-justify-content: space-between;
        justify-content: space-between;
        width: 100%;
        .head {
          position: relative;
          text-align: center;
          margin: 0 auto;
          .defaultWord {
            color: #829bbe;
          }
          .actWord {
            color: #1865d5;
          }
          .defaultCircle {
            width: 7px;
            height: 7px;
            margin: 0 auto;
            background: rgba(232, 244, 255, 1);
            border: 2px solid rgba(157, 198, 255, 1);
            border-radius: 50%;
          }
          .actCircle {
            background: rgba(226, 240, 255, 1);
            border: 2px solid rgba(24, 101, 213, 1);
          }
          .curCircle {
            background: rgba(226, 240, 255, 1);
            border: 2px solid rgba(24, 101, 213, 1);
            box-shadow: 0px 0px 5px rgba(59, 138, 254, 0.44);
          }
        }
        .defaultDevideLine {
          width: 70px;
          height: 2px;
          background: #b8d6ff;
          border-radius: 4px;
          //   position: absolute;
          //   left: 10px;
        }
        .actDevideLine {
          background: rgba(24, 101, 213, 1);
        }
      }
    }
  }
  .curBg {
    // height: 123px;
    background: center/120% 120% no-repeat
      url("../assets/vehicle/businessPlan/curBg.png");
    background: linear-gradient(
      49deg,
      rgba(240, 248, 255, 1) 0%,
      rgba(255, 255, 255, 1) 100%
    );
  }
  .orderBg {
    // height: 123px;
    background: center/120% 120% no-repeat
      url("../assets/vehicle/businessPlan/orderBg.png");
    // background: linear-gradient(
    //   79deg,
    //   rgba(237, 216, 191, 1) 0%,
    //   rgba(245, 241, 229, 1) 100%
    // );
  }
  .equityCover {
    margin: 15px;
    padding: 34px 13px 14px;
    border-radius: 20px;
    border: 1px solid #d2c1bb;
    margin-top: 37px;
    background: rgba(255, 255, 255, 1);
    position: relative;
    .tit {
      position: absolute;
      padding: 4px 50px;
      top: 0;
      background: rgba(255, 255, 255, 1);
      border-radius: 15px;
      border: 1px solid rgba(210, 193, 187, 1);
      left: 50%;
      top: -14px;
      margin-left: -75px;
    }
    .infoPart {
      display: flex;
      display: -webkit-flex;
      flex-wrap: wrap;
      -webkit-flex-wrap: wrap;
      .item {
        width: 42.58%;
        display: flex;
        display: -webkit-flex;
        -webkit-align-items: center;
        align-items: center;
        padding: 7px 9px;
        background: rgba(246, 246, 246, 1);
        border-radius: 2px;
        margin-bottom: 7px;
        .infoIcon {
          width: 28px;
          height: 28px;
          margin-right: 11px;
          img {
            width: 100%;
          }
        }
      }
    }
    .planInfoPart {
      display: flex;
      display: -webkit-flex;
      -webkit-flex-wrap: wrap;
      flex-wrap: wrap;
      -webkit-justify-content: space-between;
      justify-content: space-between;
      .item {
        width: 45%;
        display: flex;
        display: -webkit-flex;
        align-items: center;
        -webkit-align-items: center;
        padding: 9px 0 9px 7px;
        background: rgba(246, 246, 246, 1);
        border-radius: 2px;
        margin-bottom: 7px;
        .infoIcon {
          width: 28px;
          height: 28px;
          margin-right: 11px;
          img {
            width: 100%;
          }
        }
      }
    }
  }
  .weChatPart {
    background: white;
    text-align: center;
    margin: 0 auto;
    padding-top: 23px;
    padding-bottom: 12px;
    .weIcon {
      width: 34px;
      height: 34px;
      margin: 0 auto;
      img {
        width: 100%;
      }
    }
  }
}
</style>
<style scoped>
.last {
  background: white;
  height: 75px;
  width: 100%;
}
.scoreNumber,
.scoreChinese {
  height: 30px;
  /* margin-left: 17.5px; */
  /* margin-right: 17.5px; */
}
.scoreNumber > div,
.scoreChinese > div {
  float: left;
  width: 20%;
  height: 30px;
  text-align: center;
}

.scoreChinese > div {
  /* font-size: 10px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1); */
  line-height: 30px;
  height: 30px;
  /* text-align: center; */
}
.scoreNumber > div:first-child {
  text-align: left;
  position: relative;
  left: 20px;
}
.scoreNumber > div:last-child {
  text-align: right;
  position: relative;
  right: -12px;
}
.scoreChinese > div:first-child {
  text-align: left;
  position: relative;
  left: 10px;
}
.scoreChinese > div:last-child {
  text-align: right;
  position: relative;
  right: -12px;
}

.scoreProgress {
  margin: 4.5px 17.5px;
  background: rgba(242, 242, 242, 1);
  width: calc(100% - 35px);
  height: 5px;
  position: relative;
  /* overflow: hidden; */
}
.progress {
  background: #2796ff;
  margin-left: 0px;
  height: 4px;
  border-radius: 4px;
}
.orderProgress {
  background-color: #6d3b28;
}
.dotFather {
  float: left;
  width: 100%;
  height: 5px;
  position: absolute;
  top: -1.5px;
}
.dot {
  width: 5px;
  height: 5px;
  background: #2796ff;
  border-radius: 5px;
}
.defaultCircle {
  width: 7px;
  height: 7px;
  background: rgba(232, 244, 255, 1);
  border: 2px solid rgba(157, 198, 255, 1);
  border-radius: 50%;
  position: relative;
}
.orderDefault {
  border-color: #e8d4c7;
}
.firstCircle {
  position: relative;
  left: 25%;
}
.secCircle {
  left: 50%;
}
.thirdCircle {
  left: 75%;
}
.fourthCircle {
  left: 100%;
}
.actCircle {
  background: rgba(226, 240, 255, 1);
  border: 2px solid rgba(24, 101, 213, 1);
}
.orderActCir {
  background: #e3c6b5;
  border: 2px solid #6d3b28;
}
.curCircle {
  background: rgba(226, 240, 255, 1);
  border: 2px solid rgba(24, 101, 213, 1);
  box-shadow: 0px 0px 5px rgba(59, 138, 254, 0.44);
}
.orderCurCir {
  background: #ffffff;
  border: 2px solid #6d3b28;
  box-shadow: 0px 0px 5px #c4a594;
}
.commonText12 {
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  line-height: 30px;
}
.defaultWord {
  color: #829bbe;
}
.actWord {
  color: #1865d5;
}
.bg {
  position: absolute;
  right: 0px;
  top: -20px;
  width: 125px;
  height: 247px;
}
</style>
