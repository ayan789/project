<!--
 * @Description:公共部分---非车的头
 * @Date: 2020-05-18 10:42:20
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 18:03:31
 * @FilePath: \nonCarCongfig\src\views\nonCar\autoConfig\common\prHeader.vue
-->
<template>
  <div class="prHeader" id="prHeader">
    <div class="prNavBar" ref="wfNavBar" v-if="proHeader">
      <div v-show="isScroll" class="scrollHead">
        <van-nav-bar
          :title="proHeader.title"
          left-arrow
          @click-left="onClickLeft"
        >
          <div
            slot="right"
            class="shareIcon"
            @click="sharePage"
            v-show="proHeader.isShare == '1'"
          >
            <img :src="shareImg" alt="" v-if="!isSharePage" />
          </div>
        </van-nav-bar>
      </div>
      <div class="backIcon" v-if="!isScroll">
        <van-icon
          @click="onClickLeft"
          name="arrow-left"
          color="white"
          size="24px"
        />
        <div
          class="shareIcon"
          @click="sharePage"
          v-show="proHeader.isShare == '1'"
        >
          <img :src="shareIcon" alt="" v-if="!isSharePage" />
        </div>
      </div>
      <!-- 轮播 -->
      <van-swipe @change="onChange" :loop="false">
        <van-swipe-item v-for="item in proHeader.swipper" :key="item.id">
          <div class="swipeImg" v-show="item.type == 'promotion_image'">
            <img :src="item.value" alt="" />
          </div>
          <template slot="default" v-if="item.type == 'video'">
            <div>
              <!-- <video-player
                class="video-player vjs-custom-skin"
                ref="videoPlayer"
                :playsinline="true"
                :options="playerOptions"
                @pause="onPlayerPause($event)"
              ></video-player> -->
              <!-- <video
                :src="videoUrl"
                controls
                height="210"
                width="100%"
                playsinline
                webkit-playsinline
              /> -->

              <video
                v-if="mobile == 'android'"
                id="video"
                height="210"
                width="100%"
                x5-video-player-fullscreen="true"
                x5-playsinline
                playsinline
                webkit-playsinline
                preload="none"
                :src="videoUrl"
                controls
                poster="http://www.youname.com/images/first.png"
              ></video>
              <video
                v-if="mobile == 'iPhone'"
                id="video"
                height="210"
                width="100%"
                controls
                playsinline="true"
                preload="none"
                :src="videoUrl"
              ></video>
            </div>
          </template>
        </van-swipe-item>
        <!-- <van-swipe-item v-if="item.type == 'video'">
          <template slot="default">
            <div>
              <video-player
                class="video-player vjs-custom-skin"
                ref="videoPlayer"
                :playsinline="true"
                :options="playerOptions"
                @pause="onPlayerPause($event)"
              ></video-player>
            </div>
          </template>
        </van-swipe-item> -->
        <template #indicator>
          <div class="custom-indicator">
            {{ current + 1 }}/{{ headerImgLen }}
          </div>
        </template>
      </van-swipe>

      <div class="detail">
        <span class="span-left"
          >保障年限：{{ giCoveragePeriod }}{{ unit }}</span
        >
        <span class="span-right"
          >已售{{
            proHeader.saleNum
              ? proHeader.saleNum.length >= 5
                ? parseInt(proHeader.saleNum / 10000) + " 万+"
                : proHeader.saleNum || 0
              : 0
          }}份</span
        >
      </div>

      <div class="title">
        <div class="info">
          <h1>{{ proHeader.title }}</h1>
          <p>{{ proHeader.desc }}</p>
        </div>
        <div class="price">
          {{ proHeader.basePremium }}
          <span>元起</span>
        </div>
      </div>
      <!-- <div class="productTitle">
        <p class="desc">
          <span>{{ proHeader.title }}</span>
          <span>{{ proHeader.desc }}</span>
        </p>
        <p>
          <span class="price">{{ proHeader.basePremium }}</span
          ><span class="yuanqi">元起</span>
        </p>
      </div> -->
    </div>
  </div>
</template>
<script>
export default {
  name: "prHeader",
  props: {
    proHeader: Object,
    isScroll: Boolean,
    headerImgLen: Number,
    isSharePage: String
  },
  data() {
    return {
      medicalImg: require("@/assets/nonCar/healthInsurance/r.png"),
      worryFreeImg: require("@/assets/nonCar/healthInsurance/car.png"),
      shareIcon: require("@/assets/common/share.png"),
      shareImg: require("@/assets/nonCar/propertyInsurance/shareBlack.png"),
      current: 0, // 轮播索引
      playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: "zh-CN",
        aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          {
            type: "video/mp4"
            // src: require("@/assets/nonCar/propertyInsurance/video.mp4")
          }
        ],
        // poster: "./01.png", //你的封面地址
        poster:
          "https://surmon-china.github.io/vue-quill-editor/static/images/surmon-1.jpg",
        // width: document.documentElement.clientWidth, //播放器宽度
        notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true //全屏按钮
        }
      },
      isScroll1: false,
      imgLen: 1,
      unit: "年",
      videoUrl: "",
      mobile: ""
    };
  },
  computed: {
    // player() {
    //   return this.$refs.videoPlayer.player;
    // }，
    giCoveragePeriod: {
      get() {
        return this.$store.state.config.detailData.giCoveragePeriod;
      },
      set(newVal) {
        return newVal;
      }
    },
    giPeriodType: {
      get() {
        return this.$store.state.config.detailData.giCoveragePeriod;
      },
      set() {
        let giPeriodType = this.$store.state.config.detailData.giCoveragePeriod;
        if (giPeriodType == 1) {
          this.unit = "年";
        }
        if (giPeriodType == 2) {
          this.unit = "月";
        }
        if (giPeriodType == 3) {
          this.unit = "日";
        }
      }
    }
  },
  watch: {
    // videoUrl(val) {
    //   if (val !== "") {
    //     this.$refs.videoPlayer.player.src(val);
    //   }
    // },
    activeIndex() {
      this.activeTab = this.activeIndex.toString();
    },
    proHeader: {
      handler() {
        if (
          this.proHeader &&
          this.proHeader.swipper &&
          this.proHeader.swipper.length > 0
        ) {
          let array = this.proHeader.swipper.filter(item => {
            return item.type == "video";
          });

          if (array.length > 0) {
            this.playerOptions.sources[0].src = array[0].value;
            // this.videoUrl = array[0].value;
            this.videoUrl = array[0].inUrl;
            let video = document.getElementById("video");
            if (video) {
              video.play();
            }
          }
        }
        return this.proHeader;
      },
      deep: true,
      immediate: true
    }
  },
  mounted() {
    this.mobile =
      navigator.appVersion.indexOf("iPhone") !== -1 ? "iPhone" : "android";
    console.log(259, this.mobile);
    // let {
    //   giCoveragePeriod,
    //   giPeriodType
    // } = this.$store.state.config.detailData;
    // console.log(206, giCoveragePeriod, giPeriodType);
    // if (!this.$isNull(this.proHeader)) {
    //   this.imgLen = this.proHeader.swipper.length;
    // }
    // setTimeout(() => {
    //   console.log("dynamic change options", this.player);
    //   // this.player.muted(false);
    // }, 5000);
  },
  methods: {
    // 返回上一页
    onClickLeft() {
      this.$router.push({
        name: "insuranceProducts"
      });
    },
    //轮播图变化事件
    onChange(index) {
      this.current = index;
    },
    onPlayerPause(player) {
      //监听暂停
      //console.log(player.duration());
      this.$emit("onPlayerPauseFun", player);
    },
    // 分享
    sharePage() {
      this.$emit("sharePage");
    }
  }
};
</script>
<style lang="less" scoped>
#prHeader {
  height: auto;
  /deep/[class*="van-hairline"]::after {
    border: none;
  }
  .prNavBar {
    position: relative;
    .scrollHead {
      animation: fadeIn 0.5s ease-in;
      opacity: 1;
      position: fixed;
      top: 0;
      z-index: 9;
      .rightIcon {
        line-height: 34px;
      }
    }
    fadeIn {
      0% {
        opacity: 0; /*初始状态 透明度为0*/
      }
      50% {
        opacity: 0; /*中间状态 透明度为0*/
      }
      100% {
        opacity: 1; /*结尾状态 透明度为1*/
      }
    }
    @-webkit-keyframes fadeIn {
      0% {
        opacity: 0; /*初始状态 透明度为0*/
      }
      50% {
        opacity: 0; /*中间状态 透明度为0*/
      }
      100% {
        opacity: 1; /*结尾状态 透明度为1*/
      }
    }
    .touMing {
      background: rgba(255, 255, 255, 1);
    }
    .van-nav-bar {
      background: rgba(255, 255, 255, 1);
      // background: none;
      display: flex;
      align-items: center;
      width: 100%;
      position: fixed;
      z-index: 9999;
      top: 0;
      left: 0;
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
  .custom-indicator {
    position: absolute;
    right: 5px;
    bottom: 28px;
    padding: 2px 8px;
    font-size: 12px;
    background: rgba(255, 255, 255, 0.3);
    border-radius: 5px;
    color: white;
  }
  .swipeImg {
    width: 100%;
    img {
      width: 100%;
      height: 210px;
    }
  }
  .backIcon {
    position: fixed;
    width: 100%;
    height: 20px;
    top: 10px;
    left: 10px;
    z-index: 9;
    overflow: hidden;
    display: flex;
    justify-content: space-between;
    .shareIcon {
      width: 24px;
      height: 24px;
      // float: right;
      margin-right: 25px;
      img {
        width: 100%;
        height: auto;
      }
    }
  }
  .touMing {
    background: rgba(255, 255, 255, 1);
  }
  .title {
    background: rgba(255, 255, 255, 1);
    display: flex;
    box-sizing: border-box;
    padding: 5px;
    // min-height: 69px;
    height: 67px;
    align-items: center;
    .info {
      width: 80%;
      h1 {
        font-size: 16px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
        margin-bottom: 0.1rem;
      }
      p {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        margin-bottom: 0;
      }
    }
    .price {
      font-family: DINAlternate-Bold, DINAlternate;
      // font-weight: bold;
      color: rgba(252, 130, 14, 1);
      font-size: 20px;
      flex: 1;
      display: flex;
      align-items: center;
      justify-content: center;
      span {
        font-size: 12px;
        font-weight: 400;
        padding-top: 5px;
      }
    }
  }
  .prNavBar {
    position: relative;
    .productTitle {
      height: 67px;
      background: rgba(255, 255, 255, 1);
      display: flex;
      align-items: center;
      .desc {
        display: flex;
        flex-direction: column;
        padding: 12px 15px;
        width: 76%;
        span:first-child {
          font-size: 16px;
          font-family: PingFangSC-Medium, PingFang SC;
          font-weight: 600;
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
        .yuanqi {
          font-size: 12px;
          font-weight: 400;
        }
      }
    }
    .detail {
      width: 100%;
      height: 26px;
      background: -webkit-linear-gradient(
        352deg,
        rgba(59, 138, 254, 0) 0%,
        #3b8afe 100%
      );
      background: linear-gradient(
        98deg,
        rgba(59, 138, 254, 0) 0%,
        #3b8afe 100%
      );
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: #ffffff;
      line-height: 26px;
      text-align: center;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
      position: absolute;
      bottom: 24%;
      z-index: inherit;
      .span-left {
        margin-left: 14px;
      }
      .span-right {
        margin-right: 14px;
      }
    }
  }
  /deep/.video-player {
    padding-bottom: 26px;
  }
  /deep/.video-js .vjs-tech {
    height: auto;
  }
  .van-swipe {
    min-height: 150px;
  }
}
</style>
