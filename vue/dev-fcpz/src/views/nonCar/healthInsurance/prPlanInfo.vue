<template>
  <!-- 投保确认 -->
  <div class="insuranceinfoConfirm">
    <div
      v-show="isScroll"
      class="scrollHead"
    >
      <van-nav-bar
        :title="globalNav.title"
        left-arrow
        @click-left="goback"
      ></van-nav-bar>
    </div>
    <div
      class="backIcon"
      v-if="!isScroll"
    >
      <van-icon
        @click="goback"
        name="arrow-left"
        color="white"
        size="24px"
      />
    </div>
    <!-- <globalNav :title="globalNav.title" @clickBack="goback" /> -->
    <div class="productContent">
      <!-- 轮播图-->
      <van-swipe
        @change="onChange"
        :loop="false"
      >
        <van-swipe-item>
          <div class="swipeImg">
            <img
              :src="isHome ? millionHomeImg : millionPersonImg"
              alt
            />
          </div>
        </van-swipe-item>
        <!-- <van-swipe-item>
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
        </van-swipe-item>-->
        <template #indicator>
          <div class="custom-indicator">{{ current + 1 }}/1</div>
        </template>
      </van-swipe>

      <div class="card">
        <van-cell-group>
          <van-cell
            title="投保人"
            icon="user-o"
            :value="tName || '未填写'"
          />
          <van-cell
            title="被保人"
            icon="manager-o"
            :value="bName || '未填写'"
          />
          <van-cell
            title="保费"
            icon="bill-o"
            :value="millionInfo.formatSumPremium"
          />
          <div class="table">
            <div class="thead">
              <span>保障计划</span>
              <span>保障方案</span>
              <span>保单保额</span>
            </div>
            <div class="tbody">
              <span>{{ millionInfo.formulaName }}</span>
              <span>基础版</span>
              <span>{{ $store.state.config.productInfo.sumAmount }}</span>
            </div>
          </div>
        </van-cell-group>
      </div>
      <div class="card">
        <van-cell-group>
          <van-cell
            title="保障计划"
            class="bold"
          />
          <van-cell
            :title="item.productName"
            v-for="(item, i) in $store.state.config.productInfo.productList"
            :key="i"
          />
          <van-cell
            title="附加责任"
            class="bold"
          />

        </van-cell-group>
      </div>
      <millionStatic />
    </div>
    <div class="tabbar">
      <div
        class="price"
        @click="dialog()"
      >
        <img
          @click="dialog()"
          class="img"
          :src="
            `https://msupsale-test.ccic-net.com.cn/api/cst/s3/browse/${userCode}`
          "
          alt
        />
        <p>{{ businessName }}</p>
      </div>
      <div
        class="connect"
        @click="openTel"
      >
        <img
          class="img"
          src="../../../assets/nonCar/healthInsurance/Shape.png"
          alt
        />
        <span>联系我</span>
      </div>
      <div class="btn">
        <van-button
          type="info"
          @click="enter"
        >立即投保</van-button>
      </div>
    </div>
    <van-dialog v-model="show" />

  </div>
</template>
<script>
import globalNav from "../../../components/globalNav";
import millionStatic from "./millionStatic";

import { mapGetters, mapActions, mapMutations } from "vuex";
import { placeOrder } from "@src/http/module/nonCar.js";
import { Toast, Dialog } from "vant";
let agentInfo = {};
export default {
  data () {
    return {
      showCancelButton: false,
      current: 0, // 轮播索引
      isHome: false,
      millionHomeImg: require("@/assets/nonCar/healthInsurance/l.png"),
      millionPersonImg: require("@/assets/nonCar/healthInsurance/r.png"),
      isScroll: false,
      userCode: localStorage.getItem("userCode"),
      globalNav: {
        title: "查看商业计划书"
      },
      amountInfoJson: "",
      amount1: [], //一般医疗循环数组
      infoJsonPrice: [],
      infoJson2: [], //附加责任
      show: false,
      millionInfo: [],
      queryData: "", //请求数据
      businessMobile: "",
      businessName: "",
      //video
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
            // type: "video/mp4",
            // src: require("@/assets/nonCar/propertyInsurance/video.mp4")
            // "https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm"
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
      accidentalInjuryInfos: []
    };
  },
  methods: {
    dialog () {
      Dialog.alert({
        message: `业务员：${this.businessName} <br/> 电话：${this.businessMobile}`,
        closeOnClickOverlay: true
      })
        .then(() => {
        })

    },
    openTel () {
      if (this.businessMobile) {
        window.location.href = "tel:" + this.businessMobile;
      } else {
        window.location.href = "tel:95590";
      }
    },
    // 轮播图on-change
    onChange (index) {
      this.current = index;
    },
    scrollFunction () {
      if (
        document.body.scrollTop > 20 ||
        document.documentElement.scrollTop > 20
      ) {
        this.isScroll = true;
      } else {
        this.isScroll = false;
      }
    },
    goback () {
      this.$router.go("-1");
    },

    enter () {
      //TODO suncao 判断跳转个人版还是家庭版健康说明  healthTellFamily
      //这里面把信息传到缓存里面 TODO suncao
      if (this.millionInfo.routerName == "healthTell") {
        this.$router.push({
          name: "healthTell",
          params: this.millionInfo
        });
      } else if (this.millionInfo.routerName == "healthTellFamily") {
        console.log("跳转家庭版缓存信息：", JSON.stringify(this.millionInfo));
        /**
         * appliInfo 投保人信息
         *accidentalInjuryInfos,被保人信息
          millionHomeStep1 第一步缓存信息
         */
        // {
        //   appliInfo:{}//投保人信息
        //   accidentalInjuryInfos：[]//被保人数组
        //   securityNum; //保障人数
        //   sumPremium; //总保费
        //   endDate; //结束时间
        //   startDate; //开始时间
        //   formulaName; //方案名称
        //   formulaCode; //方案编码
        //   //以下为基础信息
        //   ...millionHomeStep1
        // }
        this.$router.push({
          name: "healthTellFamily",
          params: this.millionInfo
        });
      } else {
        this.$router.push({
          name: "healthTell",
          params: this.millionInfo
        });
        let {
          storeCode,
          productCode,
          formulaCode,
          formulaName,
          sumAmount,
          sumPremium, //总保费
          isAgent, //是否代理
          agentInfo, //代理信息
          aKZEXClause, //附加扩展恶性肿瘤特需医疗保险
          aEXTZClause, //附加个人恶性肿瘤院外特种药品费用医疗保险
          aEXYLClause, //附加个人恶性肿瘤赴日医疗保险
          aEXZZClause, //附加个人恶性肿瘤扩展质子重离子医疗保险
          isSheBao, //是否社保
          businessNatureCode,
          businessNatureName,
          ages, // 被保人年龄段
          appliName,
          insuredName,
          appliIdentity //与投保人关系
        } = this.millionInfo;
        this.setTenMillionData({
          storeCode,
          productCode: "FM50",
          formulaCode,
          formulaName,
          sumAmount,
          sumPremium, //总保费
          isAgent, //是否代理
          agentInfo, //代理信息
          aKZEXClause, //附加扩展恶性肿瘤特需医疗保险
          aEXTZClause, //附加个人恶性肿瘤院外特种药品费用医疗保险
          aEXYLClause, //附加个人恶性肿瘤赴日医疗保险
          aEXZZClause, //附加个人恶性肿瘤扩展质子重离子医疗保险
          isSheBao, //是否社保
          businessNatureCode,
          businessNatureName,
          ages,
          insuredName,
          appliName,
          // 被保人年龄段
          appliIdentity
        });
      }
    },


  },
  components: {
    globalNav,
    millionStatic
  },

  created () {
    let obj = this.$store.state.config.productInfo.metaDataList.find(item => {
      return item.name == "投保人姓名"
    })
    this.tName = obj.value;
    let obj2 = this.$store.state.config.productInfo.metaDataList.find(item => {
      return item.name == "被保人姓名"
    })
    this.bName = obj2.value;
  },
  mounted () {

  },
};
</script>
<style lang="less" scoped>
.insuranceinfoConfirm {
  /deep/ .van-dialog__cancel {
    display: none;
  }
  padding: 0;
  height: auto !important;
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
  .scrollHead {
    animation: fadeIn 0.5s ease-in;
    opacity: 1;
    position: fixed;
    top: 0;
    z-index: 9;
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
  .backIcon {
    position: fixed;
    width: 100%;
    height: 20px;
    top: 10px;
    left: 10px;
    z-index: 9;
  }
  .swipeImg {
    width: 100%;
    img {
      width: 100%;
      height: 210px;
    }
  }
  .custom-indicator {
    position: absolute;
    right: 5px;
    bottom: 10px;
    padding: 2px 8px;
    font-size: 12px;
    background: rgba(255, 255, 255, 0.3);
    border-radius: 5px;
    color: white;
  }
  .card {
    background: rgba(255, 255, 255, 1);
    border-radius: 0.2rem;
    box-sizing: border-box;
    margin: 0.2rem;
    margin-top: 0.5rem;
    overflow: hidden;
    .table {
      text-align: center;
      box-sizing: border-box;
      border-radius: 10px;
      font-size: 14px;
      border: 1px solid #91beff;
      overflow: hidden;
      width: 99%;
      box-sizing: border-box;
      margin: 0 auto;
      margin-top: 0.3rem;
      margin-bottom: 0.3rem;
      .thead {
        display: flex;
        background-color: #91beff;
        padding: 0.2rem;

        color: #fff;
        span {
          flex: 1;
        }
      }
      .tbody {
        display: flex;
        padding: 0.2rem;

        span {
          flex: 1;
        }
      }
    }
  }
  .tabbar {
    display: flex;
    position: fixed;
    width: 100%;
    bottom: 0;
    background-color: #fff;
    z-index: 1;
    .price {
      // flex: 1;
      padding-left: 10px;
      text-align: center;
      // display: flex;
      // flex-direction: column;
      // justify-content: center;
      // div {
      //   color: #fc820e;
      //   font-size: 0.4rem;
      // }
      .img {
        width: 40px;
        height: 40px;
        float: left;
      }
      p {
        float: left;
        line-height: 40px;
      }
    }
    .connect {
      flex: 1;
      display: flex;
      justify-content: center;
      align-items: center;
      .img {
        width: 15px;
        height: 15px;
        margin-right: 10px;
      }
    }
    .btn {
      flex: 1;
      button {
        width: 100%;
      }
    }
  }
  /deep/ .navigatorBottom {
    display: none;
  }

  .van-cell__left-icon {
    color: #3b8afe;
  }
  .bold {
    font-weight: bold;
    font-size: 14px;
  }
}
.productContent {
  background: rgba(36, 128, 234, 1);
}
/deep/ .van-dialog__message {
  font-size: 16px;
}
</style>
