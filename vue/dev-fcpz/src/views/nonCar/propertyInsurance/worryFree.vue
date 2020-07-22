<!--
 * @Description:  财产险-货运无忧
 * @Date: 2020-03-21 18:17:22
 * @Author: zhangminjie
 * @Last Modified time: 2020-04-06 19:34:48
 * @FilePath: \dev-scyh\src\views\nonCar\propertyInsurance\worryFree.vue
 -->
<template>
  <div
    class="worryFree vantabClass"
    id="worryFree"
  >
    <div
      class="header"
      ref="wfNavBar"
    >
      <div
        v-show="isScroll"
        class="scrollHead"
      >
        <van-nav-bar
          title="货运无忧"
          left-arrow
          @click-left="onClickLeft"
        >
          <!-- <template #right>
            <div class="rightIcon" @click="sharePage">
              <img :src="shareIcon" alt="" />
            </div>
          </template> -->
        </van-nav-bar>
      </div>
      <div
        class="backIcon"
        v-if="!isScroll"
      >
        <van-icon
          @click="onClickLeft"
          name="arrow-left"
          color="white"
          size="24px"
        />
        <!-- <div class="shareImg" @click="sharePage">
          <img :src="shareImg" alt="" />
        </div> -->
      </div>

      <!-- 轮播 -->
      <van-swipe
        @change="onChange"
        :loop="false"
      >
        <van-swipe-item>
          <div class="swipeImg">
            <img
              :src="worryFreeImg"
              alt=""
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
        </van-swipe-item> -->
        <template #indicator>
          <div class="custom-indicator">{{ current + 1 }}/1</div>
        </template>
      </van-swipe>
      <div class="detail">
        <span class="span-left">保障年限：1年</span>
        <span
          class="span-right"
          v-if="$route.query.saleSum"
        >已售{{ $route.query.saleSum }}份</span>
      </div>
      <div class="productTitle">
        <p class="desc">
          <span>货运无忧</span>
          <span>花小钱，免大灾，承保货物运输过程中遭受货物损失或招致第三者经济赔偿责任。</span>
        </p>

        <p v-if="formulaList[activeTab] && formulaList[activeTab].premium">
          <span class="price">{{ formulaList[activeTab].premium }}</span><span class="yuanqi">元起</span>
        </p>
      </div>
    </div>

    <div class="productContent">
      <div class="title blueBack">
        <productTitle>保障计划</productTitle>
      </div>
      <!-- <div class="title blueBack">保障计划</div> -->
      <div class="card">
        <van-tabs
          v-model="activeTab"
          swipe-threshold="3"
        >
          <van-tab
            v-for="(item, index) in formulaList"
            :title="item.tabName"
            :key="index"
            :name="index.toString()"
          >
            <div slot="default">
              <van-row class="titleStrong">
                <van-col
                  span="16"
                  v-show="
                    item &&
                      item.arrObj &&
                      item.arrObj.arr.length > 0 &&
                      item.arrObj.arr[0].name
                  "
                >{{ item.arrObj.arr[0].name }}</van-col>
                <van-col
                  span="8"
                  class="right"
                  v-show="
                    item &&
                      item.arrObj &&
                      item.arrObj.arr.length > 0 &&
                      item.arrObj.arr[0].value
                  "
                >{{ item.arrObj.arr[0].value }}万</van-col>
              </van-row>
              <div class="insureDesc">
                <van-row>
                  <van-col
                    span="16"
                    class="strong"
                  >附加责任</van-col>
                </van-row>
                <div>
                  <van-row>
                    <van-col
                      span="20"
                      class="light"
                    >{{
                      item.arrObj.arr[1].name
                    }}</van-col>
                    <van-col span="4">
                      <van-checkbox
                        class="right"
                        v-model="checked"
                        :disabled="checked"
                        checked-color="#3B8AFE"
                      />
                    </van-col>
                  </van-row>
                  <van-row>
                    <van-col
                      span="16"
                      class="gray"
                    >{{ item.arrObj.arr[1].value }}万保额</van-col>
                  </van-row>
                </div>
              </div>
            </div>
          </van-tab>
        </van-tabs>
      </div>
      <div class="personInfo">
        <van-cell-group>
          <van-cell
            class="minTitle"
            title="关系人信息"
          />
          <!-- <van-cell title="保障年限" value="1年" />
          <van-cell title="份数(最多一份)" value="1份" />-->
          <van-cell title="是否代理">
            <div solt="default">
              <button
                :disabled="isDisabled"
                :class="[isProxy ? 'blue' : '']"
                @click="changeIsProxy('y')"
              >
                是
              </button>
              <button
                :disabled="isDisabled"
                :class="[!isProxy ? 'blue' : '']"
                @click="changeIsProxy('n')"
              >
                否
              </button>
            </div>
          </van-cell>
          <!-- isDisabled -->
          <van-cell
            v-if="isProxy && isDisabled"
            title="代理人"
            :value="channelInfo.agentName"
            is-link
          />
          <van-cell
            v-if="isProxy && !isDisabled"
            title="代理人"
            :value="channelInfo.agentName"
            is-link
            @click="toVehicleChannel"
          />
          <van-cell
            title="直销/直团渠道"
            :value="
              channelInfo.businessNatureName == '请选择'
                ? ''
                : channelInfo.businessNatureName
            "
            is-link
            v-if="!isProxy"
            @click="toVehicleChannel"
          />
        </van-cell-group>
      </div>
      <vantab :message="message" />
      <div
        class="titleWhite"
        id="vantab0"
      >
        <productTitle>{{ productDetail.productDesc[0].title }}</productTitle>
        <p>{{ productDetail.productDesc[0].subTitle }}</p>
      </div>
      <div class="productInfo">
        <img
          :src="productDetail.productContents[0]"
          class="productInfo-img"
        />
      </div>
      <div
        class="titleWhite"
        id="vantab1"
      >
        <productTitle>{{ productDetail.productDesc[1].title }}</productTitle>
        <p class="warp">{{ productDetail.productDesc[1].subTitle }}</p>
      </div>
      <div class="pic">
        <img
          :src="productDetail.productContents[1]"
          class="pic-img"
        />
      </div>
      <div
        class="titleWhite"
        id="vantab2"
      >
        <productTitle>{{ productDetail.productDesc[2].title }}</productTitle>
        <p class="warp">{{ productDetail.productDesc[2].subTitle }}</p>
      </div>
      <div class="claimExplanation">
        <img
          :src="productDetail.productContents[2]"
          class="claimExplanation-img"
        />
      </div>
      <div
        class="titleWhite"
        id="vantab3"
      >
        <productTitle>{{ productDetail.productDesc[3].title }}</productTitle>
      </div>
      <div class="common-problem">
        <div>
          <question-item
            v-for="(item, index) in questionArr"
            :key="item.question"
            :index="index"
          >
            <span
              slot="question"
              class="question"
            >{{ item.question }}</span>
            <span
              slot="answer"
              class="answer"
            >{{ item.answer }}</span>
          </question-item>
        </div>
        <div
          class="more"
          @click="moreQuesion"
        >
          {{ !isClicked ? "查看更多" : "收起" }}
        </div>
      </div>
      <div
        class="title"
        id="vantab4"
      >
        <productTitle>{{ productDetail.productDesc[4].title }}</productTitle>
      </div>
      <div class="clause">
        <div class="notice-clause"></div>
        <van-cell-group>
          <van-cell
            title="保险条款"
            value
            is-link
            @click="gotoClause('propertyclause')"
          />
          <van-cell
            title="投保须知"
            is-link
            @click="gotoClause('propertyInstructions')"
          />
          <van-cell
            title="特別约定"
            is-link
            @click="gotoClause('propertyArgument')"
          />
          <van-cell
            title="责任免除"
            is-link
            @click="gotoClause('Deductible')"
          />
        </van-cell-group>
      </div>
    </div>
    <div class="bottom">
      <van-row
        class="bottom18"
        type="flex"
      >
        <van-col :span="12">
          <p>
            <span v-if="formulaList[activeTab] && formulaList[activeTab].formatFJSZ">{{ formulaList[activeTab].formatFJSZ }}</span>
            <span>保费(元)</span>
          </p>
        </van-col>
        <!-- <van-col :span="7">
          <div class="middle">
            <img src="@/assets/nonCar/worryFree/fill.png" alt="" />展业夹
          </div>
        </van-col> -->
        <van-col :span="12">
          <button @click="doInsure">我要投保</button>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import { Collapse, CollapseItem, Toast } from "vant";
import { storeQueryFormula } from "@src/http/module/nonCar.js";
import { mapMutations } from "vuex";
import vantab from "@src/views/nonCar/common/vantab.vue";
import {
  getDefaultChannel,
  getDefaultAgent,
  agentQuery
} from "@src/http/module/vehicle";
import productTitle from "./worryFreeComponents/title";
import questionItem from "../common/question-item";
import Config from "@src/common/json/newCoreDataDictionary";
Vue.use(Collapse);
Vue.use(CollapseItem);
let channelInfoParam = {
  isProxy: false,
  businessNatureCode: "0101",
  businessNatureName: "业务员销售（直销）",
  agentCode: "",
  agentName: "",
  agreementNo: "",
  agreementName: "",
  agentAgreementCode: "",
  agentAgreementName: "",
  businessNature2Name: "",
  handler2Code: "",
  handler2Name: "",
  businessCode: "",
  businessName: "",
  projectCode: "",
  projectName: "",
  thirdBusinessInfo: "",
  id: "",
  isDefault: "",
  mobile: "",
  operator: "",
  orgCode: "",
  practisingCertificateNo: "",
  salesCodeName: "",
  salesIdNo: "",
  salesName: "",
  businessSourceName: "",
  inputDate: "",
  addressName: "",
  businessNature: "",
  comCode: "",
  permitNo: "",
  phoneNumber: "",
  riskCode: "YEF"
};
export default {
  name: "worryFree",
  components: {
    vantab,
    productTitle,
    questionItem
  },
  data: () => ({
    shareImg: require("@/assets/common/share.png"),
    isScroll: false,
    worryFreeImg: require("@/assets/nonCar/healthInsurance/car.png"),
    current: 0, // 轮播索引
    message: [
      { title: "产品简介", id: 0 },
      { title: "保障详情", id: 1 },
      { title: "理赔说明", id: 2 },
      { title: "常见问题", id: 3 },
      { title: "条款须知", id: 4 }
    ],
    productDetail: {
      productDesc: [
        {
          title: "产品简介",
          subTitle:
            "花小钱，免大灾，承保货物运输过程中遭受的货物损失或招致的第三者经济赔偿责任"
        },
        {
          title: "保障详情",
          subTitle: "承保货物运输过程中遭受的货物损失或招致的第三者经济赔偿责任"
        },
        {
          title: "理赔说明",
          subTitle: "全程指导，4步快速理赔"
        },
        {
          title: "常见问题",
          subTitle: ""
        },
        {
          title: "条款须知",
          subTitle: ""
        }
      ],
      productContents: [
        require("@/assets/nonCar/worryFree/productBrief.png"),
        require("@/assets/nonCar/worryFree/productGuarantee.png"),
        require("@/assets/nonCar/worryFree/claimExplanation.png"),
        [
          {
            question: "哪些车型的货车作为承运工具时可以承保？",
            answer:
              "必须是运输货物的车辆，如平板货车、集装箱货车、厢式货车、油罐车等，但下述特种车型外：消防车、救险车、垃圾车、应急车、街道清洗车、扫雪车、清洁车、洒水车等。"
          },
          {
            question: "是否可承保异地牌照货车作为承运工具的业务？",
            answer:
              "原则上仅限承保当地牌照货车作为承运工具的业务，承保异地业务需事先报总公司审核。"
          },
          {
            question:
              "如投保的货物承运车辆挂车不固定，投保时是否可以仅填写主车车牌号？如投保主车不固定，投保时是否可以仅填写挂车车牌号？",
            answer: "可以。"
          },
          {
            question:
              "如运输公司一次性投保，是否可以仅填写一张投保单后附承运车辆清单？",
            answer: "可以。"
          },
          {
            question: "如运输公司一次性投保，是否可以出具一张保单？？",
            answer:
              "系统内需按货物承运车辆逐车录单，但可与客户签订保险协议或提供与系统录入内容一致的保险凭证。"
          },
          {
            question: "是否可以出具电子保单？",
            answer: "可以，鼓励使用电子保单。"
          },
          {
            question: "客户是否可以网上投保、自行出单？",
            answer:
              "客户可以通过与我司签订预约货运险协议确定承保条件，并使用我司配置的账号密码通过筋斗云系统自行出单。"
          },
          {
            question: "被保险人是否必须与承运车辆行驶证上的所有人一致？",
            answer:
              "不用，但出险时被保险人需提供能够证明保险利益的材料（包括但不限于承运车辆挂靠协议、承运车辆购置发票等）。"
          },
          {
            question: "如作为承运工具的货车为老龄车，承保时是否有限制？",
            answer:
              "老龄承运车辆承保原则上有一定的限制条件，具体可咨询当地分公司。"
          },
          {
            question: "出险时是否需第一时间向我司报案？",
            answer: "是的，便于我司及时安排理赔查勘事宜。。"
          },
          {
            question: "出险时所有理赔单据是否必须为原件？",
            answer:
              "原则上要求所有理赔单据均为原件，如附加险理赔材料中的原件无法提供，可以复印件代替，要求被保险人在复印件盖章，并注明：“与原件内容一致”字样。"
          }
        ]
      ]
    },
    checked: true,
    isProxy: false,
    shareIcon: require("@/assets/nonCar/propertyInsurance/shareBlack.png"),
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
    productIcons: [
      require("@/assets/nonCar/propertyInsurance/copyRenewal.png"),
      require("@/assets/nonCar/propertyInsurance/copyRenewal2.png"),
      require("@/assets/nonCar/propertyInsurance/copyRenewal3.png"),
      require("@/assets/nonCar/propertyInsurance/bianZhi.png")
    ],
    formulaList: [],
    activeTab: "0",
    formatPremium: "",
    channelInfo: channelInfoParam,
    agentInfo: {
      agentCode: "",
      agentName: "",
      agreementName: "",
      agreementNo: "",
      businessNature: "",
      businessNature2: "",
      handler2Code: "",
      handler2Name: ""
    },
    userInfo: {},
    productInfo: {
      storeCode: "",
      productCode: "",
      riskCode: "YEF",
      formulaCode: "",
      formatPremium: "", //保费
      sumPremium: "", //总保费
      isAgent: "",
      businessNatureName: "", //直销
      businessNatureCode: "", //转换
      formulaSize: 1, //保险份数
      agentInfo: {
        agentCode: "",
        agentName: "",
        agreementNo: "",
        agreementName: "",
        businessNature: "",
        businessNature2: "",
        handler2Code: "",
        handler2Name: ""
      }
    },
    saleSum: 0,
    isDisabled: false,
    activeIndex: 0,
    premium: "",
    questionArr: [],
    isClicked: false, // 默认第一次没有点过
    isClear: "1"
  }),
  computed: {
    player () {
      // return this.$refs.videoPlayer.player;
    }
  },
  watch: {
    videoUrl (val) {
      if (val !== "") {
        this.$refs.videoPlayer.player.src(val);
      }
    },
    activeIndex () {
      this.activeTab = this.activeIndex.toString();
    }
  },
  updated () {
    const topPos = this.$store.state.nonCar.topPos;
    if (topPos) {
      document.getElementById("worryFree").scrollTop = `${topPos}`;
      window.scrollTop = `${topPos}`;
      window.scrollTo(0, `${topPos}`);
      // 返回完成 置0
      this.$store.commit("nonCar/setPos", 0);
    }
  },
  mounted () {
    this.activeTab = this.activeIndex.toString();
    // 监听滚动
    document.getElementById("worryFree").addEventListener("scroll", () => {
      this.scrollFunction();
    });
    setTimeout(() => {
      console.log("dynamic change options", this.player);
      // this.player.muted(false);
    }, 5000);
  },
  created () {
    // console.log(this.$store.state.nonCar.shareData);
    this.questionArr = this.productDetail.productContents[3].slice(0, 4);
    // this.productDetail.productContents[3].length = 4
    this.saleSum = this.$route ? this.$route.query.saleSum : 0;
    this.isClear = this.$route ? this.$route.query.isClear : "1";
    // console.log(569, this.isClear, this.$route.query.isClear);
    //isIssueAfterPay 见费出单标志 ：1:见费出单；0：非见费出单
    this.productInfo.isAgent = this.$store.state.nonCar.shareData.productInfo.isAgent;
    // console.log(554, this.$isAgent());
    if (this.$isAgent()) {
      this.isDisabled = true;
      // let userInfo = localStorage.getItem("userInfo")
      //   ? JSON.parse(localStorage.getItem("userInfo"))
      //   : {};
      // let aObj = {
      //   requestType: "01",
      //   riskCode: "YEF",
      //   businessSource: "2401",
      //   agentCode: userInfo.agentCode
      // };
      // this.queryAgent(aObj).then(res => {
      //   console.log(res);
      // });
      this.isProxy = this.$isAgent();
      channelInfoParam = localStorage.getItem("agentInfo")
        ? JSON.parse(localStorage.getItem("agentInfo"))
        : {};
      this.channelInfo = channelInfoParam;
      // channelInfoParam =
    } else {
      this.isDisabled = false;
      this.isProxy = false;
      this.isProxy = this.channelInfo.isProxy == "1" ? true : false;
      this.isProxy = this.productInfo.isAgent == "1" ? true : false;
    }

    // this.isProxy = this.channelInfo.isProxy == "1" ? true : false;

    let shareData = this.$store.state.nonCar.shareData.productInfo;
    for (let key in shareData) {
      this.productInfo[key] = shareData[key];
    }

    // this.isProxy = this.productInfo.isAgent == "1" ? true : false;
    this.userInfo = localStorage.getItem("userInfo")
      ? JSON.parse(localStorage.getItem("userInfo"))
      : {};
    let { storeCode } = this.userInfo;
    this.productInfo.storeCode = storeCode;
    //  this.getDefaultProxyInfo().then(res => {
    //   if (res.code == "1" && res.data) {
    //     this.channelInfo.agentName = res.data.agentName;
    //     this.channelInfo.agentCode = res.data.agentCode;
    //   }
    // });

    this.$root.$once("channelChange", channel => {
      //TODO suncao取到渠道信息进行赋值,渲染不正确
      this.channelInfo = channel;
      channelInfoParam = channel;
      // console.log(499, this.channelInfo);
      channelInfoParam.riskCode = "YEF";
      this.isProxy = this.channelInfo.isProxy == "1" ? true : false;
      this.channelInfo = channelInfoParam;
    });
    //全局获取缓存数据
    this.$globalSetLocalStorage();
    this.queryStoreQueryFormula();
    this.watermarket();

    //初始是否代理：否
    if (this.isProxy) {
      this.changeIsProxy("y");
    } else {
      this.changeIsProxy("n");
    }
    // this.getDefaultProxyInfo().then(res => {
    //   if (res.code == "1" && res.data) {
    //     this.channelInfo.agentName = res.data.agentName;
    //     this.channelInfo.agentCode = res.data.agentCode;
    //   }
    // });
  },
  destroyed () {
    this.channelInfo.isProxy = "0";
    this.channelInfo.businessNatureName = "业务员销售（直销）";
    this.channelInfo.businessNatureCode = "0101";
    // if (!this.$isNull(document.getElementById("worryFree"))) {
    //   document
    //     .getElementById("worryFree")
    //     .removeEventListener("scroll", this.scrollFunction(), true);
    // }
  },
  methods: {
    // 获取代理人信息
    async queryAgent (aObj) {
      return await agentQuery(aObj);
    },

    async sharePage () {
      // let res = await getShareUrl({
      //   url: window.location.href + "&isSharePage=1"
      // });
      // let shareUrl =
      //   res.data.shortUrl || window.location.href + "&isSharePage=1";
      // console.log("分享短连接===" + shareUrl);
      // const desc = `尊敬的 ${this.name}，您的保险专家 ${this.businessName} 为您精心准备的计划书，请查阅！`;
      // const title = this.millionInfo.formulaName;

      //调用接口参数 TODO
      const jsonObject = {
        sharePlatform: ["0", "1"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
        shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
        title: "", //分享标题
        imgURL:
          "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
        image: "",
        url: "", //分享链接
        desc: "", //分享描述
        phoneNum: "" //电话号码,当需要短信分享时获取
      };
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.share(jsonObject, data => {
        // Toast("share 接口调用成功，壳响应数据:" + data);
      });
    },
    // 获取默认渠道
    getDefaultChannel () {
      getDefaultChannel({ isProxy: this.channelInfo.isProxy })
        .then(
          res => {
            if (res.code === "1") {
              if (res.data.isProxy == "1") {
                if (this.$isAgent()) return;
                if (res.data.isProxy == "1") {
                  this.getDefaultAgent();
                }
              } else {
                if (this.$isAgent()) return;
                if (this.channelInfo.isProxy == "1") {
                  this.getDefaultAgent();
                } else {
                  this.channelInfo.channelInfo = "0";
                  this.channelInfo.businessNature =
                    res.data.businessNatureCode || "0101";
                  this.channelInfo.businessNatureCode =
                    res.data.businessNatureCode || "0101";
                  this.channelInfo.businessNatureName =
                    res.data.businessNatureName || "业务员销售（直销）";
                }
              }
            } else {
              this.$dialog.alert({ message: res.msg });
            }
          },
          error => { }
        )
        .finally(() => {
          console.warn("代理人用户", this.$isAgent());
          if (this.$isAgent()) {
            const userInfo = this.localStorage.get("userInfo", {});
            this.channelInfo.isProxy = "1";
            this.channelInfo.agentCode = userInfo.agentCode;
            this.channelInfo.agentName = userInfo.agentName;
            this.channelInfo.businessNatureCode = "2401";
            this.channelInfo.agentAgreementCode = "";
            this.channelInfo.agentAgreementName = "";
            this.channelInfo.handlerCode = "";
            this.channelInfo.handlerName = "";
            this.agentQuery(true);
          }
        });
    },
    getDefaultAgent () {
      getDefaultAgent({}).then(res => {
        this.agentInfo.agentName = res.data.agentName;
        this.agentInfo.agentCode = res.data.agentCode;
        this.agentInfo.businessNature = res.data.businessSource;
        this.agentInfo.businessNatureCode = res.data.businessSource;
        this.agentInfo.agentAgreementCode = res.data.agentAgreementCode;
        this.agentInfo.agentAgreementName = res.data.agentAgreementName;
        this.channelInfo.agentName = res.data.agentName;
        this.channelInfo.agentCode = res.data.agentCode;
        this.channelInfo.businessNature = res.data.businessSource;
        this.channelInfo.businessNatureCode = res.data.businessSource;
        this.channelInfo.agentAgreementCode = res.data.agentAgreementCode;
        this.channelInfo.agentAgreementName = res.data.agentAgreementName;
        for (const key in res.data) {
          if (res.data.hasOwnProperty(key)) {
            const value = res.data[key];
            if (this.agentInfo.hasOwnProperty(key) && value) {
              this.agentInfo[key] = value;
            }
          }
        }
        this.agentQuery(false);
      });
    },
    agentQuery (flag) {
      /**requestType
       * 01:查询代理人协议信息
       * 02:查询业务风险分类 （N码）
       * 03:查询代理人协议信息（模糊查询）
       */
      // let params = {
      //   requestType: "02",
      //   agentCode: "8000189135" || this.agent.agentCode,
      //   comCode: "01",
      //   businessNature: "01",
      //   riskCode: "01"
      // };
      let userInfo = this.localStorage.get("userInfo", {});
      // console.log(777, userInfo);
      //1 不代理 2 代理
      let requestType = this.agentInfo.isProxy == "0" ? "02" : "01";
      let agentCode =
        this.agentInfo.isProxy == "0"
          ? userInfo.userCode
          : this.agentInfo.agentCode;
      let businessSource = this.agentInfo.businessNatureCode;
      this.agentInfo.handler2Code = userInfo.handlerCode;
      this.agentInfo.handler2Name = userInfo.handlerName;
      let params = {
        comCode: this.localStorage.get("userInfo", {}).comCode,
        requestType,
        riskCode: channelInfoParam.riskCode,
        businessSource,
        agentCode,
        agreementNo: this.agentInfo.agentAgreementCode,
        agreementName: this.agentInfo.agentAgreementName
      };
      if (flag) {
        // console.log(800, this.channelInfo);
        params.riskCode = "YEF";
        params.businessSource = this.channelInfo.businessNatureCode;
        params.agentCode = this.channelInfo.agentCode;
      } else {
        if (!agentCode || !businessSource) {
          console.warn("无代理人, 无业务来源");
          return;
          // return this.getDefaultAgent();
        }
      }

      agentQuery(params).then(
        res => {
          if (res.code === "1" && res.data) {
            let { salesMainMsgDto = {} } = res.data;
            this.requestData = salesMainMsgDto;
            let {
              salesAgentMsgDto = {},
              salesAgreementMsgList = []
            } = salesMainMsgDto;
            let { agentName, businessNature } = salesAgentMsgDto;
            let agreementIsVaild = false;
            if (Array.isArray(salesAgreementMsgList)) {
              salesAgreementMsgList.forEach(item => {
                if (item.agreementNo == this.agentInfo.agentAgreementCode) {
                  agreementIsVaild = true;
                }
              });
            }
            //console.log(this.salesAgreementMsgList);
            this.agentInfo.agentCode = agentCode; //归属机构
            this.agentInfo.agentName = agentName; //归属机构
            this.agentInfo.businessNatureCode =
              businessNature || this.agentInfo.businessNatureCode; //业务来源
            for (const key in salesAgentMsgDto) {
              if (salesAgentMsgDto.hasOwnProperty(key)) {
                const value = salesAgentMsgDto[key];
                if (this.agentInfo.hasOwnProperty(key) && value) {
                  this.agentInfo[key] = value;
                }
              }
            }
            let businessNatures = Object.keys(Config.businessNature).map(
              key => ({
                text: Config.businessNature[key],
                value: key
              })
            );
            businessNatures.push({ text: "业务员销售（直销）", value: "0101" });
            businessNatures.push({ text: "业务员销售（直团）", value: "7101" });
            businessNatures.push({ text: "门店直销", value: "0201" });
            this.agentInfo.businessNatureName = this.$utils.findText(
              businessNatures,
              this.agentInfo.businessNatureCode
            );
            // this.agentInfo.agentAgreementCode = agreementNo; //代理人协议号
            // this.agentInfo.agentAgreementName = agreementName; //代理人协议名称
            if (!agreementIsVaild) {
              //代理人协议无效
              if (salesAgreementMsgList.length) {
                //默认首个有效
                this.agentInfo.agreementNo =
                  salesAgreementMsgList[0].agreementNo;
                this.agentInfo.agreementName =
                  salesAgreementMsgList[0].agreementName;
              } else {
                //无代理协议
                this.agentInfo.agreementNo = "";
                this.agentInfo.agreementName = "";
              }
              // agentInfoParam = this.agentInfo;
              // console.log(this.agentInfo,"ahdjasdashdasjsahd")
              for (let key in this.agentInfo) {
                this.channelInfo[key] = this.agentInfo[key];
              }
              // console.log(869, this.channelInfo,this.agentInfo);
            }
          } else {
            if (this.agentInfo.isProxy == "1") {
              this.$dialog.alert({ message: res.msg });
            }
          }
        },
        error => { }
      );
    },
    scrollFunction () {
      let scrTop = document.getElementById("worryFree").scrollTop;
      if (scrTop > 100) {
        this.isScroll = true;
        // console.log("aaa");
      } else {
        this.isScroll = false;
      }
    },
    moreQuesion () {
      if (!this.isClicked) {
        this.questionArr = this.productDetail.productContents[3];
      } else {
        this.questionArr = this.productDetail.productContents[3].slice(0, 4);
      }
      this.isClicked = !this.isClicked;
    },
    // 轮播图on-change
    onChange (index) {
      this.current = index;
    },
    watermarket () {
      this.$native.showOrCloseWatermarket(true, data => { });
    },

    ...mapMutations("channel", ["setChannel"]),
    // 切换运货无忧头部的版本
    changeTabs () {
      if (this.isBasicVersion) {
        this.isBasicVersion = false;
      } else {
        this.isBasicVersion = true;
      }
    },
    // 跳到代理渠道页面
    toVehicleChannel () {
      let query = {
        riskCode: "YEF"
      };
      this.$root.$once("channelChange", channel => {
        this.channelInfo = channel;
      });
      this.setChannel(
        Object.assign(this.channelInfo, { isProxy: this.isProxy ? "1" : "0" })
      );
      // console.log(111, this.channelInfo);
      this.productInfo.formulaCode = this.formulaList[
        this.activeTab
      ].formulaCode;
      this.productInfo.isAgent = this.isProxy ? "1" : "0";
      this.$store.dispatch("nonCar/updateShareData", {
        info: this.productInfo,
        type: 1
      });
      this.$router.push({
        name: "vehicleChannel",
        query: query
      });
    },
    // 是否代理
    changeIsProxy (type) {
      // for (let key in this.channelInfo) {
      //   this.channelInfo[key] = "";
      // }
      if (type == "y") {
        this.isProxy = true;
        this.productInfo.isAgent = "1";
        this.channelInfo.agentName = "";
        this.channelInfo.agentCode = "";
        channelInfoParam.isProxy = "1";
        this.channelInfo.isProxy = "1";
        this.getDefaultChannel();
      } else {
        this.isProxy = false;
        this.productInfo.isAgent = "0";
        this.channelInfo.agentName = "";
        this.channelInfo.businessNatureName = "业务员销售（直销）";
        this.channelInfo.businessNatureCode = "0101";
        channelInfoParam.isProxy = "0";
        this.channelInfo.isProxy = "0";
        this.getDefaultChannel();
      }
      // channelInfo.businessNatureName
      // this.getDefaultProxyInfo().then(res => {
      //   // console.log(res);
      //   if (res.code == "1" && res.data) {
      //     if (res.data.isProxy == "0") {
      //       this.channelInfo.agentName = res.data.businessNatureName;
      //       this.channelInfo.agentCode = res.data.businessNatureCode;
      //     } else {
      //       this.channelInfo.agentName = res.data.agentName;
      //       this.channelInfo.agentCode = res.data.agentCode;
      //     }
      //   }
      // });
    },
    // 返回上一页
    onClickLeft () {
      // this.$globalBack();
      this.$router.push({
        name: "insuranceProducts"
      });
    },
    onPlayerPause (player) {
      //监听暂停
      //console.log(player.duration());
      this.$emit("onPlayerPauseFun", player);
    },
    //格式化钱数
    formatNum (num) {
      const numStr = num.toString().split("."); //先转为字符串，再分开整数和小数部分
      let numInt = numStr[0]; // 整数部分
      let numDec = numStr.length > 1 ? "." + numStr[1] : "";
      // 小数部分，只有原数字存在小数点的时候，分割完长度才会大于1，才需要存储小数，记得前面加小数点
      let resultInt = ""; //存储整数部分处理结果
      while (numInt.length > 3) {
        //当剩余整数部分长度大于3时继续处理
        resultInt = "," + numInt.slice(-3) + resultInt; //把后三位截出来，和处理结果拼在一起，前面加逗号
        numInt = numInt.slice(0, -3); // 剩下的部分是除去刚刚截掉的3位的部分
      }
      return numInt + resultInt + numDec; //结果是“剩余的不足3位的整数”+“处理好的整数结果”+“小数部分”
    },
    // 查询方案信息
    async queryStoreQueryFormula () {
      let obj = {
        proCode: "YEF",
        storeCode: localStorage.getItem("storeCode") //qHbO6cAgkSLMSve3GoRR
        // storeCode: localStorage.getItem("storeCode")
      };
      let res = await storeQueryFormula(obj);
      this.$utils.removeEmpty(this.formulaList);
      if (res.code == "1" && res.data) {
        this.formulaList = res.data.sfList;
        if (res.data.sfList.length > 0) {
          for (let i = 0, len = res.data.sfList.length; i < len; i++) {
            if (res.data.sfList[i].addRiskAmountJson) {
              let addRiskAmountJson = JSON.parse(
                res.data.sfList[i].addRiskAmountJson
              );
              let FJSZ = JSON.parse(res.data.sfList[i].addRiskPremiumsJson)
                .FJSZ;
              let arr = [];
              for (let [k, v] of Object.entries(addRiskAmountJson)) {
                if (k.indexOf("#") >= 0) {
                  k = k.slice(3, k.length);
                }
                v = v / 10000;
                arr.push({ name: k, value: v });
              }
              this.formulaList[i].arrObj = {};
              this.formulaList[i].arrObj.arr = arr;
              this.formulaList[i].formatAmount =
                this.formulaList[i].amount / 10000;
              this.formulaList[i].FJSZ = FJSZ;
              this.formulaList[i].formatFJSZ = this.formatNum(
                (
                  parseInt(FJSZ) + parseInt(this.formulaList[i].premium)
                ).toFixed(2)
              );
              let arg1 = Number(
                this.formulaList[i].FJSZ.toString().replace(".", "")
              );
              let arg2 = Number(
                this.formulaList[i].premium.toString().split(".")[0]
              );
              this.formulaList[i].tabName =
                parseInt(this.formulaList[i].amount) / 10000 +
                this.formulaList[i].arrObj.arr[1].value +
                "万-" +
                parseInt(arg1 + arg2);
              // this.formulaList[i].tabName = res.data.sfList[i].formulaName;

              this.formulaList[i].sumPremium =
                parseInt(FJSZ) + parseInt(this.formulaList[i].premium);
            }
          }
        }
        let index = this.formulaList.findIndex(item => {
          return item.formulaCode == this.productInfo.formulaCode;
        });
        this.activeIndex = index >= 0 ? index.toString() : "0";
        this.activeTab = this.activeIndex;
        // console.log(this.formulaList);
      }
    },
    // 为渠道信息赋值
    setChannelInfo () {
      let {
        agentCode,
        agentName,
        agentAgreementName,
        agreementNo,
        businessNature,
        businessNature2Code,
        handler2Code,
        handler2Name,
        businessNatureName,
        businessNatureCode,
        agreementName
      } = this.channelInfo;
      this.productInfo.agentInfo.agentCode = agentCode;
      this.productInfo.agentInfo.agentName = agentName;
      this.productInfo.agentInfo.agreementName = agreementName;
      this.productInfo.agentInfo.agreementNo = agreementNo;
      this.productInfo.agentInfo.businessNature = businessNature;
      this.productInfo.agentInfo.businessNature2 = businessNature2Code;
      this.productInfo.agentInfo.handler2Code = handler2Code;
      this.productInfo.agentInfo.handler2Name = handler2Name;
      this.productInfo.businessNatureName =
        businessNatureName == "请选择" ? "" : businessNatureName;
      this.productInfo.businessNatureCode = businessNatureCode;
      // this.isProxy = this.channelInfo.isProxy == "1" ? true : false;
      // this.productInfo.isAgent = this.channelInfo.isProxy == "1" ? true : false;
    },
    // 跳转到相应的条款内
    gotoClause (type) {
      const topPos = document.getElementById("worryFree").scrollTop;
      this.$store.commit("nonCar/setPos", topPos);
      this.productInfo.formulaCode = this.formulaList[
        this.activeTab
      ].formulaCode;
      this.productInfo.isAgent = this.isProxy ? "1" : "0";
      this.$store.dispatch("nonCar/updateShareData", {
        info: this.productInfo,
        type: 1
      });
      switch (type) {
        case "propertyArgument":
          this.$router.push({ name: "propropertyArgument" });
          break;
        case "propertyInstructions":
          this.$router.push({ name: "propertyInstructions" });
          break;
        case "Deductible":
          this.$router.push({ name: "Deductible" });
          break;
        default:
          this.$router.push({ name: "propertyCList" });
      }
    },
    // 我要投保按钮
    doInsure () {
      // console.log(753, this.channelInfo.agentName);
      this.productInfo.isAgent = this.isProxy ? "1" : "0"; //是否代理
      let isAgent = this.productInfo.isAgent; //是否代理
      if (isAgent == "1") {
        if (this.$isNull(this.channelInfo.agentName)) {
          this.$dialog.alert({
            message: "代理人信息不能为空"
          });
          return;
        }
        if (this.$isNull(this.channelInfo.businessNature)) {
          this.$dialog.alert({
            message: "代理人对应业务来源为空"
          });
          return;
        }
      }
      this.formatPremium = this.formulaList[this.activeTab].formatFJSZ; //保费

      // if (this.isDisabled && !this.channelInfo.agentName) {
      //   Toast.allowMultiple();
      //   Toast({
      //     forbidClick: true,
      //     message: "请选择代理人",
      //     type: "fail"
      //   });
      //   setTimeout(() => {
      //     Toast.clear();
      //   }, 2000);
      //   return;
      // }
      this.productInfo.formatPremium = this.formulaList[
        this.activeTab
      ].formatFJSZ;
      this.productInfo.formulaCode = this.formulaList[
        this.activeTab
      ].formulaCode;
      this.productInfo.productCode = this.formulaList[
        this.activeTab
      ].productCode;
      this.productInfo.sumPremium = this.formulaList[this.activeTab].sumPremium;
      // console.log(this.productInfo.sumPremium);
      this.setChannelInfo();
      if (this.isClear != "0") {
        this.$store.dispatch("nonCar/clearShareData", 2);
      }
      this.$store.dispatch("nonCar/updateShareData", {
        info: this.productInfo,
        type: 1
      });
      this.$router.push({
        name: "proInsuranceInformation"
      });
    }
  }
};
</script>

<style lang="less">
#worryFree {
  width: 100%;
  height: 100%;
  font-family: PingFangSC-Semibold, PingFang SC;
  overflow: auto;
  // swipePart
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
    bottom: 28px;
    padding: 2px 8px;
    font-size: 12px;
    background: rgba(255, 255, 255, 0.3);
    border-radius: 5px;
    color: white;
  }
  // swipePart

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
  .lipei {
    width: 355px;
    height: 336px;
    margin: auto;
  }
  button {
    outline: none;
    background: none;
    border: none;
  }
  .bottom18 {
    margin-bottom: 18px;
  }
  .header {
    position: relative;
    // padding-top: 46px;
    // height: 224px;
    // background-image: url(../../../assets/nonCar/propertyInsurance/productHeader.png);
    // background-repeat: no-repeat;
    // background-size: 100% auto;
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
    .backIcon {
      position: fixed;
      width: 100%;
      height: 20px;
      top: 10px;
      left: 10px;
      z-index: 9;
      overflow: hidden;
      .shareImg {
        width: 24px;
        height: 24px;
        float: right;
        margin-right: 25px;
        img {
          width: 100%;
        }
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
  .detail {
    width: 100%;
    height: 26px;
    background: -webkit-linear-gradient(
      352deg,
      rgba(59, 138, 254, 0) 0%,
      #3b8afe 100%
    );
    background: linear-gradient(98deg, rgba(59, 138, 254, 0) 0%, #3b8afe 100%);
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
      // .price{
      //   display: inline-block;
      //   white-space: wrap;
      //   border-radius: 50%;
      //   width:58px;
      //   height:58px;
      //   line-height: 58px;
      //   background:linear-gradient(63deg,rgba(255,123,0,0.05) 0%,rgba(255,123,0,0.05) 66%,rgba(255,123,0,0.05) 100%);
      // }
      .yuanqi {
        font-size: 12px;
        font-weight: 400;
      }
    }
  }
  .productContent {
    // height: 2770px;
    background: rgba(36, 128, 234, 1);
    padding-bottom: 65px;
    .title {
      height: 43px;
      padding-top: 10px;
      text-align: center;
    }
    .van-checkbox__icon--disabled.van-checkbox__icon--checked .van-icon {
      color: #fff;
    }

    .van-checkbox__icon--disabled .van-icon {
      background-color: #3b8afe;
      border-color: #3b8afe;
    }
    .card {
      width: 355px;
      // height: 223px;
      background: rgba(255, 255, 255, 1);
      border-radius: 8px;
      margin: 0 auto;
      padding: 10px 0;
      [class*="van-hairline"]::after {
        border: none;
      }
      .van-tab {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
        border: 1px solid rgba(208, 208, 208, 1);
        margin-right: 14px;
        border-radius: 5px;
      }
      .van-tab--active {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 600;
        color: rgba(51, 51, 51, 1);
        flex-basis: 46% !important;
        border: 1px solid rgba(59, 138, 254, 1);
      }
      .van-ellipsis {
        flex-basis: 46% !important;
      }
      .van-tabs__line {
        width: 28px !important;
        height: 5px !important;
        background: none;
        border-radius: 6px;
        bottom: 0;
      }
      .van-tabs__nav--line {
        padding-bottom: 0%;
      }
      .van-tabs__wrap {
        margin: 8px 0 0 20px;
        width: 332px;
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
      // height: 252px;
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
      margin: 10px 0;
      p {
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        padding: 0 39px;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
      }
      .warp {
        padding: 0 50px;
      }
    }
    .productInfo {
      text-align: center;
      .productInfo-img {
        width: 356px;
        height: 435px;
      }
    }
    .pic {
      text-align: center;
      .pic-img {
        width: 356px;
        height: 263px;
      }
    }
    .claimExplanation {
      text-align: center;
      .claimExplanation-img {
        width: 356px;
        height: 491px;
      }
    }
    .common-problem {
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      width: 356px;
      min-height: 295px;
      border-top: 1px solid transparent;
      background: rgb(255, 255, 255);
      border-radius: 8px;
      margin: 0 auto;
      padding: 0 21px 16px 21px;
      .question {
        margin-bottom: 8px;
      }
      .more {
        margin: 30px auto 0 auto;
        text-align: center;
        width: 52px;
        height: 19px;
        font-size: 13px;
        font-weight: 400;
        color: rgb(153, 153, 153);
        line-height: 19px;
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
    height: 206px;
    background: rgb(255, 255, 255);
    border-radius: 12px 12px 8px 8px;
    margin: 0 auto;
    position: relative;
    .notice-clause {
      width: 355px;
      height: 27px;
      background-image: url(../../../assets/nonCar/worryFree/noticeClause.png);
      background-position: 0 0;
      background-repeat: no-repeat;
      background-size: 100% 27px;
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
    .middle {
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgb(72, 104, 149);
      height: 100%;
      background: rgba(42, 137, 255, 0.2);
      img {
        width: 14px;
        height: 12px;
        margin-right: 6px;
      }
    }
  }
}
</style>
