<template>
  <!-- 百万医疗个人版 -->
  <div
    class="millionPersonal"
    id="millionPersonal"
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
          :title="globalNav.title"
          left-arrow
          @click-left="onClickLeft()"
        >
          <template #right>
            <div
              class="rightIcon"
              @click="sharePage"
            >
              <!-- shareImg -->
              <img
                :src="shareIcon"
                alt
              />
            </div>
          </template>
        </van-nav-bar>
      </div>
      <div
        class="backIcon"
        v-if="!isScroll"
      >
        <van-icon
          @click="onClickLeft()"
          name="arrow-left"
          color="white"
          size="24px"
        />
        <div
          class="shareImg"
          @click="sharePage"
        >
          <img
            :src="shareImg"
            alt
          />
        </div>
      </div>

      <!-- 轮播 -->
      <van-swipe
        @change="onChange"
        :loop="false"
      >
        <van-swipe-item>
          <div class="swipeImg">
            <img
              :src="medicalImg"
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
      <div class="detail">
        <span class="span-left">保障年限：1年</span>
        <span
          class="span-right"
          v-if="$route.query.saleSum"
        >已售{{ $route.query.saleSum }}份</span>
      </div>
      <div class="title">
        <div class="info">
          <h1 v-if="formula">{{ formula.formulaName }}</h1>
          <p>住院、特殊门诊、交通多重保障</p>
        </div>
        <div
          class="price"
          v-if="formula"
        >
          {{ formula.premium }}
          <span>元起</span>
        </div>
      </div>
    </div>

    <div class="row">
      <h5>保障计划</h5>
      <!-- <p>全程指导，5步快速理赔</p> -->
    </div>
    <div class="productContent">
      <div class="card">
        <van-collapse
          v-model="activeName"
          accordion
        >
          <van-collapse-item
            :title="item.name"
            :value="item.subname"
            v-for="(item, i) in amount1"
            :key="i"
            :name="`${i}`+1"
          >{{ amount1Desc[i] }}</van-collapse-item>
        </van-collapse>

        <van-checkbox-group v-model="result">
          <van-cell-group>
            <van-cell
              title="附加责任"
              class="card-title"
            />
            <van-cell
              v-for="(item, index) in infoJson2"
              clickable
              :key="item"
              :title="item"
              @click="toggle(index)"
              :label="infoJson2Desc[index]"
            >
              <template #right-icon>
                <van-checkbox
                  :name="item"
                  ref="checkboxes"
                />
              </template>
            </van-cell>
          </van-cell-group>
        </van-checkbox-group>
      </div>

      <div class="card">
        <van-cell-group>
          <van-cell
            title="关系人信息"
            class="card-title"
          />
          <!-- 被保人年龄-->
          <agePicker
            @changeAges="changeAges"
            ref="agePicker"
          />

          <van-cell title="是否有医保">
            <template>
              <van-button
                @click="btnChecked = true"
                plain
                :class="[btnChecked ? 'selected-button' : '', 'select-button']"
              >有医保</van-button>
              <van-button
                @click="btnChecked = false"
                plain
                :class="[!btnChecked ? 'selected-button' : '', 'select-button']"
              >无医保</van-button>
            </template>
          </van-cell>

          <van-cell
            title="是否代理"
            v-if="isSharePage == '0'"
          >
            <template>
              <van-button
                @click="changeIsProxy('y')"
                plain
                :class="[isProxy ? 'selected-button' : '', 'select-button']"
              >是</van-button>
              <van-button
                @click="changeIsProxy('n')"
                plain
                :class="[!isProxy ? 'selected-button' : '', 'select-button']"
              >否</van-button>
            </template>
          </van-cell>
          <!-- btnChecked2 -->
          <van-cell
            v-if="isProxy && isSharePage == '0'"
            title="代理人"
            :value="channelInfo.agentName"
            is-link
            @click="toVehicleChannel"
          >
            <div class="item-right">
              <span>{{ channelInfo.agentName }}</span>
            </div>
          </van-cell>
          <van-cell
            title="直销/直团渠道"
            :value="channelInfo.businessNatureName"
            is-link
            v-if="!isProxy && isSharePage == '0'"
            @click="toVehicleChannel"
          >
            <div class="item-right">
              <span>{{ channelInfo.businessNatureName }}</span>
            </div>
          </van-cell>
        </van-cell-group>
      </div>
      <div>
        <edition
          person="T"
          @toTipPage="toTipPage"
        />
      </div>
    </div>

    <div class="tabbar">
      <div class="price">
        <div>{{ formatSumPremium }}</div>
        <p>保费(元)</p>
      </div>
      <div
        class="show"
        @click="show = !show"
      >展业夹</div>
      <div class="btn">
        <van-button
          type="info"
          @click="enter"
        >我要投保</van-button>
      </div>
    </div>
    <van-popup
      v-model="show"
      closeable
    >
      <div class="content">
        <h3>百万医疗2020个人版</h3>
        <div class="divBtn">
          <div>
            <img
              src="@/assets/nonCar/healthInsurance/r.png"
              alt="edit"
            />
            <van-button
              type="info"
              plain
              @click="goProductPoster"
            >去分享海报</van-button>
          </div>
          <div>
            <img
              src="@/assets/nonCar/healthInsurance/r.png"
              alt="edit"
            />
            <van-button
              type="info"
              plain
              @click="goMillionPlan"
            >去制作计划书</van-button>
          </div>
        </div>
      </div>
    </van-popup>
    <div
      class="backBtn"
      v-if="showBtn"
      @click="backTop"
    >
      <img
        src="../../../assets/vehicle/businessPlan/backTop.png"
        alt
      />
    </div>
  </div>
</template>
<script>
import globalNav from "@/components/globalNav";
import agePicker from "./agePicker";
import { mapGetters, mapActions, mapMutations } from "vuex";
import {
  getDefaultChannel,
  getDefaultAgent,
  agentQuery
} from "@src/http/module/vehicle";
import { getShareUrl } from "@src/http/module/customerApi";

import Config from "@src/common/json/newCoreDataDictionary";
import edition from "./edition";
import { Toast } from "vant";

let agentInfoParam = {};
let channelInfoParam = {
  isProxy: "0",
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
  riskCode: "WTN"
};
export default {
  data () {
    return {
      activeName: "0", //展开详情
      timer: "", //定时器
      curHeight: 0, //屏幕高度
      showBtn: false, //返回顶部
      shareImg: require("@/assets/common/share.png"), //分享图片
      shareIcon: require("@/assets/nonCar/propertyInsurance/shareBlack.png"), //分享图片
      isScroll: false, //滚动
      medicalImg: require("@/assets/nonCar/healthInsurance/r.png"), //轮播图
      current: 0, // 轮播索引
      queryData: "", //请求数据
      formula: "", // 方案
      amountInfoJson: "", //保障计划
      amount1: [],
      amount1Desc: [
        "住院医疗、特殊门诊医疗、门诊手术医疗、住院前30天/出院后30天门急诊医疗费用",
        "患本条款约定的重大疾病而发生的住院医疗、特殊门诊医疗、门诊手术医疗、住院前30天/出院后30天门急诊医疗费用",
        "飞机",
        "轮船",
        "火车(地铁、轻轨)",
        "汽车"
      ],
      infoJson2: [],
      infoJson2Desc: [
        "二级以上(含)公立医院特需部或VIP部(不包含观察室、联合病房和康复病房)治疗恶性肿瘤而发生的合理费用",
        "恶性肿瘤外62种特定药品费用",
        "恶性肿瘤赴日医疗，保险金额200万，70%赔付，申请后不可续保该附加险",
        "上海质子重离子医院发生的质子重离子治疗费用"
      ],
      totalPrice: 0, //总保费
      resultPrice: 0, //附加保费

      ageOne: "0-4", //年龄段
      agesArr: "", //年龄序号
      sumAmount: "", //总保费
      aKZEXClause: false,
      aEXTZClause: false,
      aEXYLClause: false,
      aEXZZClause: false,
      // 公共顶部
      globalNav: {
        title: "百万医疗2020个人版",
        isShare: false
      },
      result: [], //附加责任
      btnChecked: true, //社保按钮
      isSheBao: "", // 是否有社保
      isProxy: false, //代理
      isAgent: "",
      agentInfo: {
        agentCode: "",
        agentName: "",
        agreementName: "",
        agreementNo: "",
        businessNature: "",
        businessNature2: "",
        handler2Code: "",
        handler2Name: ""
      }, //代理信息
      channelInfo: channelInfoParam,

      show: false,
      //
      playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: true, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: "zh-CN",
        aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          // {
          //   type: "video/mp4",
          //   src: require("@/assets/nonCar/propertyInsurance/video.mp4")
          //   // "https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm"
          // }
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
      //
      isSharePage: "0"
    };
  },
  activated () {
    if (this.tenMillionData.ages == "") {
      this.$refs.agePicker.insuredAgesInfo.insuredAgesTypeVal = "0-4岁"
      this.ageOne = "0-4"
    }
    document.getElementById('app').style.overflow = "visible"
    if (window) {
      window.addEventListener("scroll", () => {
        this.scrollFunction();
      });
    }
    // this.cleTenMillionData();
    this.aKZEXClause = false;
    this.aEXTZClause = false;
    this.aEXYLClause = false;
    this.aEXZZClause = false;
    localStorage.removeItem("tenmillionOrderNo");

    if (localStorage.getItem("fPosition")) {
      this.timer = setTimeout(() => {
        let fPosition = localStorage.getItem("fPosition");
        window.scroll(0, fPosition);
      }, 30);
    }
    if (this.gResult) {
      this.result = this.gResult;
    }
    if (this.tenMillionData.ages != "") {
      this.$refs.agePicker.insuredAgesInfo.insuredAgesTypeVal =
        this.tenMillionData.ages + "岁";
      this.ageOne = this.tenMillionData.ages;
    }
    this.agesArr = this.getIndex(this.tenMillionData.ages);
    if (this.tenMillionData.isSheBao == "Y") {
      this.btnChecked = true;
    } else {
      this.btnChecked = false;
    }
  },
  created () {
    //全局获取缓存数据
    this.$globalSetLocalStorage();
    localStorage.removeItem("tenmillionOrderNo");
    console.log("channelInfo", this.channelInfo);
    this.$root.$once("channelChange", channel => {
      //TODO suncao取到渠道信息进行赋值,渲染不正确
      this.channelInfo = channel;
      channelInfoParam = channel;
      channelInfoParam.riskCode = "WTN";
      console.log(
        " this.channelInfo=======" + JSON.stringify(this.channelInfo)
      );
      this.isProxy = this.channelInfo.isProxy == "1" ? true : false;
      console.log(" this.isProxy=======" + this.isProxy);
      this.agentInfo.agentCode = channel.agentCode;
      this.agentInfo.agentName = channel.agentName;
      this.agentInfo.agreementName = channel.agentAgreementName;
      this.agentInfo.agreementNo = channel.agreementNo;
      this.agentInfo.businessNature = channel.businessNature;
      this.agentInfo.businessNature2 = channel.businessNature2;
      this.agentInfo.handler2Code = channel.handler2Code;
      this.agentInfo.handler2Name = channel.handler2Name;
      agentInfoParam = this.agentInfo;
      console.log("this.agentInfo", JSON.stringify(agentInfoParam));
    });
    if (!this.$isNull(agentInfoParam.agreementNo)) {
      this.isProxy = "1";
      this.isAgent = "1";
      this.agentInfo = agentInfoParam;
    }
    //获取分享海报地址或计划书进来信息 TODO suncao
    this.isSharePage = this.$GetRequest("isSharePage", true) || "0";
    if (this.isSharePage == "1") {
      this.storeCode =
        this.$GetRequest("storeCode", true) ||
        localStorage.getItem("storeCode");
      this.comCode =
        this.$GetRequest("comCode", true) ||
        JSON.parse(localStorage.getItem("userInfo")).comCode;
      this.isAgent = this.$GetRequest("isAgent", true) || this.isAgent;
      if (this.$GetRequest("agentInfo", true)) {
        this.agentInfo = JSON.parse(
          decodeURIComponent(this.$GetRequest("agentInfo", true))
        ) || this.agentInfo;
      }
      (this.channelInfo.businessNatureCode =
        this.channelInfo.businessNatureCode ||
        this.$GetRequest("businessNatureCode", true)),
        (this.channelInfo.businessNatureName =
          this.channelInfo.businessNatureName ||
          this.$GetRequest("businessNatureName", true));
    } else {
      this.storeCode =
        localStorage.getItem("storeCode") ||
        this.$GetRequest("storeCode", true);
      this.comCode =
        JSON.parse(localStorage.getItem("userInfo")).comCode ||
        this.$GetRequest("comCode", true);
    }
    console.log(this.channelInfo.businessNatureName, "heweiwei ===>309");

    //   请求数据
    this.getQueryFormula({
      proCode: "FM50",
      storeCode: this.storeCode || localStorage.getItem("storeCode")
    })
      .then(res => {
        this.queryData = this.millionQuery;
        return new Promise((resolve, reject) => {
          if (this.queryData.code == "1") {
            resolve();
          } else {
            reject();
          }
        });
      })
      .then(res => {
        this.initFormulaInfo();
        this.initSection1();
        this.updatePremiumsNum();
      })
      .catch(err => {
        Toast("网络掉线了");
      });

    //初始是否代理：否
    if (this.isProxy) {
      this.changeIsProxy("y");
    } else {
      this.changeIsProxy("n");
    }
  },
  watch: {
    agesArr: {
      handler () {
        if (this.formula.premiumsJson) {
          this.updatePremiumsNum();
        }
      },
      deep: true
    },
    btnChecked () {
      if (this.queryData.data) {
        if (this.btnChecked) {
          this.initFormulaInfo();
          this.updatePremiumsNum();
        } else {
          this.initFormulaInfo();
          this.updatePremiumsNum();
        }
      }
    }
  },

  beforeMount () {
    this.curHeight =
      document.documentElement.clientHeight || document.body.clientHeight;
  },
  mounted () {
    if (localStorage.getItem("fPosition")) {
      this.timer = setTimeout(() => {
        let fPosition = localStorage.getItem("fPosition");
        window.scroll(0, fPosition);
      }, 30);
    }
    if (localStorage.getItem("isShouldClear") == "true") {
      localStorage.removeItem("fPosition");
      // this.cleTenMillionData(s);
    }
    if (this.gResult.length) {
      // this.result = this.gResult;
    }
    if (this.tenMillionData.ages != "") {
      this.$refs.agePicker.insuredAgesInfo.insuredAgesTypeVal =
        this.tenMillionData.ages + "岁";
    }
    this.agesArr = this.getIndex(this.tenMillionData.ages);
    if (this.tenMillionData.isSheBao == "Y") {
      this.btnChecked = true;
    } else {
      this.btnChecked = false;
    }
    // 监听滚动

    // setTimeout(() => {
    //   console.log("dynamic change options", this.player);
    //   this.player.muted(false);
    // }, 5000);
  },
  beforeRouteEnter: (to, from, next) => {
    if (
      from.name === "personalTerms" ||
      from.name === "InsureNotice" ||
      from.name === "InsureStatement" ||
      from.name === "Aboutpersonal" ||
      from.name === "liabilityExempt"
    ) {
      localStorage.setItem("isShouldClear", "false");
      next();
    } else if (from.name === "vehicleChannel") {
      localStorage.removeItem("fPosition");
      localStorage.setItem("isShouldClear", "false");
      next();
    } else {
      localStorage.setItem("isShouldClear", "true");
      localStorage.removeItem("fPosition");
      next();
    }
  },
  beforeRouteLeave (to, from, next) {
    let fPosition =
      document.documentElement && document.documentElement.scrollTop;
    localStorage.setItem("fPosition", fPosition);
    next();
  },
  methods: {
    // 分享页面
    async sharePage () {
      // let res = await getShareUrl({
      //   url: window.location.href + "&isSharePage=1"
      // });
      // let shareUrl =
      //   res.data.shortUrl || window.location.href + "&isSharePage=1";
      // console.log("分享短连接===" + shareUrl);
      // const desc = `尊敬的 ${this.name}，您的保险专家 ${this.businessName} 为您精心准备的计划书，请查阅！`;
      // const title = this.millionInfo.formulaName;
      //       proCode: "FM50"
      // storeCode: "GWznde8FEh3Fq2LdxFCK"
      // userCode: "8000506130"
      let shareUrl =
        window.location.href +
        "&proCode=FM50&storeCode=" +
        this.storeCode +
        "&isAgent=" +
        this.isAgent +
        "&agentInfo=" +
        JSON.stringify(this.agentInfo) +
        "&businessNatureName=" +
        this.channelInfo.businessNatureName +
        "&businessNatureCode=" +
        this.channelInfo.businessNatureCode +
        "&userCode=" +
        localStorage.getItem("userCode") +
        "&isSharePage=1" +
        "&comCode=" +
        this.comCode;
      // 转换短链接
      let res = await getShareUrl({ url: shareUrl });
      console.log("分享链接：" + res.data.shortUrl);
      if (res.code == "1") {
        //调用接口参数 TODO
        const jsonObject = {
          sharePlatform: ["0", "1"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
          shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
          title: "天地关爱百万医疗(2020个人版)", //分享标题
          imgURL:
            "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
          image: "",
          url: res.data.shortUrl, //分享链接
          desc: "住院、特殊门诊、交通多重保障", //分享描述
          phoneNum: "" //电话号码,当需要短信分享时获取
        };
        //调用壳方法，传递调用参数和接受响应数据
        this.$native.share(jsonObject, data => {
          // Toast("share 接口调用成功，壳响应数据:" + data);
        });
      }
    },
    getIndex (aa) {
      switch (true) {
        case aa == "0-4":
          return 1;
          break;
        case aa == "5-10":
          return 2;
          break;
        case aa == "11-15":
          return 3;
          break;
        case aa == "16-20":
          return 4;
          break;
        case aa == "21-25":
          return 5;
          break;
        case aa == "26-30":
          return 6;
          break;
        case aa == "31-35":
          return 7;
          break;
        case aa == "36-40":
          return 8;
          break;
        case aa == "41-45":
          return 9;
          break;
        case aa == "46-50":
          return 10;
          break;
        case aa == "51-55":
          return 11;
          break;
        case aa == "56-60":
          return 12;
          break;
      }
    },
    //渠道代理
    refh () {
      // this.cleTenMillionData();
      this.setFormula({ result: this.result });
      let subAges = this.$refs.agePicker.insuredAgesInfo.insuredAgesTypeVal;
      this.setTenMillionData({
        ages: subAges.substring(0, subAges.length - 1),
        isSheBao: this.btnChecked ? "Y" : ""
      });
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
      if (
        document.body.scrollTop > this.curHeight * 1.5 ||
        document.documentElement.scrollTop > this.curHeight * 1.5
      ) {
        this.showBtn = true;
      } else {
        this.showBtn = false;
      }
    },
    backTop () {
      let distance =
        document.documentElement.scrollTop || document.body.scrollTop; //获得当前高度
      let step = distance / 50; //每步的距离
      (function jump () {
        if (distance > 0) {
          distance -= step;
          window.scrollTo(0, distance);
          setTimeout(jump, 10);
        }
      })();
    },
    // 获取默认渠道
    getDefaultChannel () {
      if (this.isSharePage == "1") {
        return;
      } else {
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
              this.agentQuery();
            }
          });
      }
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
        this.agentQuery();
      });
    },
    agentQuery () {
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
      //1 不代理 2 代理
      let requestType = this.agentInfo.isProxy == "0" ? "02" : "01";
      let agentCode =
        this.agentInfo.isProxy == "0"
          ? userInfo.userCode
          : this.agentInfo.agentCode;
      let businessSource = this.agentInfo.businessNatureCode;
      if (!agentCode || !businessSource) {
        console.warn("无代理人, 无业务来源");
        return;
        // return this.getDefaultAgent();
      }
      let params = {
        comCode: this.localStorage.get("userInfo", {}).comCode,
        requestType,
        riskCode: channelInfoParam.riskCode,
        businessSource,
        agentCode,
        agreementNo: this.agentInfo.agentAgreementCode,
        agreementName: this.agentInfo.agentAgreementName
      };
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
    // 轮播图on-change
    onChange (index) {
      this.current = index;
    },
    back () {
      this.$router.push({
        name: "nonCar"
      });
    },
    // 初始化方案信息
    initFormulaInfo () {
      if (this.btnChecked) {
        this.formula = this.queryData.data.sfList[0];
      } else {
        this.formula = this.queryData.data.sfList[1];
      }
    },
    // 保障计划
    initSection1 () {
      this.amountInfoJson = JSON.parse(this.formula.amountInfoJson);
      let amountInfoJson = this.amountInfoJson;
      for (let key in amountInfoJson) {
        if (parseInt(key.substr(0, key.indexOf("#"))) <= 16) {
          this.amount1.push({
            name: key.split("#")[1],
            subname: this.transfer(amountInfoJson[key])
          });
        } else {
          this.infoJson2.push(key.split("#")[1]);
        }
      }
    },
    // 更新保费
    toggleResult (arg = "0-4") {
      if (this.result.length) {
        this.resultPrice = 0;
        this.result.forEach((item, i) => {
          if (
            item == "扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)"
          ) {
            this.resultPrice += parseInt(
              JSON.parse(this.formula.extensionJson)[arg].ZLTY
            );
          }
          if (item == "恶性肿瘤院外特种药品费用医疗责任") {
            this.resultPrice += parseInt(
              JSON.parse(this.formula.extensionJson)[arg].TY
            );
          }
          if (item == "恶性肿瘤赴日医疗责任") {
            this.resultPrice += parseInt(
              JSON.parse(this.formula.extensionJson)[arg].FRYL
            );
          }
          if (item == "恶性肿瘤扩展质子重离子医疗责任") {
            this.resultPrice += parseInt(
              JSON.parse(this.formula.extensionJson)[arg].ZY
            );
          }
        });
        return this.resultPrice;
      } else {
        return 0;
      }
    },
    // 初始化保费
    updatePremiumsNum () {
      if (this.result.length) {
        this.totalPrice = parseInt(
          parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
          parseInt(this.toggleResult("0-4"))
        );
      } else {
        this.totalPrice = parseInt(
          JSON.parse(this.formula.premiumsJson)["0-4"].BF
        );
      }
      this.togglePrice(this.agesArr);
    },

    togglePrice (arg) {
      switch (arg) {
        case 1:
          this.totalPrice = this.totalPrice;
          break;
        case 2:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["5-10"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("5-10")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 3:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["11-15"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("11-15")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 4:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["16-20"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("16-20")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 5:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["21-25"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("21-25")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 6:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["26-30"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("26-30")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 7:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["31-35"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("31-35")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 8:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["36-40"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("36-40")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 9:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["41-45"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("41-45")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 10:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["46-50"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("46-50")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 11:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["51-55"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("51-55")) -
            parseInt(this.toggleResult("0-4"));
          break;
        case 12:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["56-60"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.toggleResult("56-60")) -
            parseInt(this.toggleResult("0-4"));
          break;
      }
    },
    // 改变年龄

    changeAges (i, num, ageNum) {
      this.agesArr = i;
      this.ageOne = ageNum;
    },
    // 格式化s
    transfer (amount) {
      if (amount < 10000) {
        amount = amount + "元";
      } else {
        amount = amount / 10000 + "万";
      }
      return amount;
    },
    toTipPage () {
      this.refh();
    },
    // 投保
    enter () {
      document.getElementById("app").style.overflow = "";
      let isAgent = this.isAgent; //是否代理
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
      } else {
        //TODO suncao 非代理的也要判断业务来源
        if (this.$isNull(this.channelInfo.businessNatureCode)) {
          this.$dialog.alert({
            message: "业务来源不能为空"
          });
          return;
        }
      }
      // 保额
      // 请返回大地向app进行后续操作
      this.getSumAmount();
      let params = {
        storeCode: this.storeCode || localStorage.getItem("storeCode"),
        productCode: "FM50",
        formulaCode: this.formula.formulaCode,
        formulaName: this.formula.formulaName,
        sumAmount: this.sumAmount,
        sumPremium: this.totalPrice, //总保费
        isAgent: isAgent, //是否代理
        agentInfo: this.agentInfo, //代理信息
        aKZEXClause: this.aKZEXClause, //附加扩展恶性肿瘤特需医疗保险
        aEXTZClause: this.aEXTZClause, //附加个人恶性肿瘤院外特种药品费用医疗保险
        aEXYLClause: this.aEXYLClause, //附加个人恶性肿瘤赴日医疗保险
        aEXZZClause: this.aEXZZClause, //附加个人恶性肿瘤扩展质子重离子医疗保险
        isSheBao: this.btnChecked ? "Y" : "", //是否社保
        businessNatureCode: this.channelInfo.businessNatureCode || "",
        businessNatureName: this.channelInfo.businessNatureName || "",
        ages: this.ageOne // 被保人年龄段
      };
      this.setFormula({ formula: this.formula, result: this.result }); // 选择方案
      this.setTenMillionData(params);
      this.$router.push({ name: "healthTell" });
    },

    // 去分享海报页面
    goProductPoster () {
      this.$router.push({
        name: "productPoster",
        query: {
          item: {
            url: window.location.href,
            storeCode: localStorage.getItem("storeCode"),
            comCode: JSON.parse(localStorage.getItem("userInfo")).comCode,
            isAgent: this.isAgent,
            agentInfo: JSON.stringify(this.agentInfo),
            name: JSON.parse(localStorage.getItem("userInfo")).userName,
            mobile: JSON.parse(localStorage.getItem("userInfo")).userPhone || JSON.parse(localStorage.getItem("userInfo")).mobile,
            isSharePage: "1",
            businessNatureCode: this.channelInfo.businessNatureCode || "",
            businessNatureName: this.channelInfo.businessNatureName || ""
          }
        }
      });
    },
    goMillionPlan () {
      this.$store.commit("nonCar/clearresultList");
      this.$store.commit("nonCar/resetInfoObj");
      let params = {
        // isAgent: !this.btnChecked2 ? "0" : "1", //是否代理
        isAgent: this.isAgent || "0",
        agentInfo: this.agentInfo, //代理信息
        isSheBao: this.btnChecked ? "Y" : "", //是否社保
        businessNatureCode: "",
        businessNatureName: ""
      };
      if (params.isAgent == "" || params.isAgent == "0") {
        params.businessNatureCode = this.channelInfo.businessNatureCode;
        params.businessNatureName = this.channelInfo.businessNatureName;
        params.agentInfo = null;
      }
      this.$router.push({ name: "millionPlan", query: params });
    },
    // 返回上一页
    onClickLeft () {
      localStorage.removeItem("topPos");
      if (!this.$isNull(this.$GetRequest("source", true))) {
        this.$native.closeWeb();
        document.getElementById("app").style.overflow = "";
      } else {
        // this.$router.replace({ name: "insuranceProducts" });
        this.$globalBack();
        document.getElementById("app").style.overflow = "";
      }
      // this.$globalBack();
    },
    // 代理切换
    changeIsProxy (type) {
      if (type == "y") {
        this.isProxy = true;
        channelInfoParam.isProxy = "1";
        this.channelInfo.isProxy = "1";
        this.isAgent = "1";
        if (!agentInfoParam) {
          this.getDefaultChannel();
        }
      } else {
        this.isProxy = false;
        this.isAgent = "";
        this.businessNatureCode = this.businessNatureCode || "0101";
        this.businessNatureName =
          this.businessNatureName || "业务员销售（直销）";
        channelInfoParam.isProxy = "0";
        this.channelInfo.isProxy = "0";
        this.getDefaultChannel();
      }
    },
    // 去渠道代理页面
    toVehicleChannel () {
      // this.setFormula({ formula: this.formula, result: this.result }); // 选择方案
      this.refh();
      let query = {
        riskCode: "WTN"
        // businessNatureName:this.businessNatureName
      };
      this.$root.$once("channelChange", channel => {
        this.channelInfo = channel;
      });
      this.setChannel(this.channelInfo);
      console.log(111, this.channelInfo);
      this.$router.push({
        name: "vehicleChannel",
        query: query
      });
    },

    //
    onPlayerPause (player) {
      //监听暂停
      //console.log(player.duration());
      this.$emit("onPlayerPauseFun", player);
    },
    toggle (index) {
      this.$refs.checkboxes[index].checked = !this.$refs.checkboxes[index]
        .checked;
      // 调用初始化保费方法
      this.updatePremiumsNum();
    },
    // 保额
    getSumAmount () {
      this.sumAmount = parseInt(JSON.parse(this.formula.amountsJson)["JZH"]);
      if (this.result.length) {
        this.result.forEach(item => {
          if (
            item == "扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)"
          ) {
            this.sumAmount += parseInt(
              JSON.parse(this.formula.amountsJson)["ZLTY"]
            );
            this.aKZEXClause = "Y";
          }
          if (item == "恶性肿瘤院外特种药品费用医疗责任") {
            this.sumAmount += parseInt(
              JSON.parse(this.formula.amountsJson)["TY"]
            );
            this.aEXTZClause = "Y";
          }
          if (item == "恶性肿瘤赴日医疗责任") {
            this.sumAmount += parseInt(
              JSON.parse(this.formula.amountsJson)["FRYL"]
            );
            this.aEXYLClause = "Y";
          }
          if (item == "恶性肿瘤扩展质子重离子医疗责任") {
            this.sumAmount += parseInt(
              JSON.parse(this.formula.amountsJson)["ZY"]
            );
            this.aEXZZClause = "Y";
          }
        });
      }
    },
    ...mapMutations("million", {
      setFormula: "SETFORMULA",
      cleTenMillionData: "CLETENMILLIONDATA",
      setTenMillionData: "SETTENMILLIONDATA"
    }),
    ...mapMutations("channel", ["setChannel"]),

    ...mapActions("million", ["getQueryFormula"])
  },
  computed: {
    formatSumPremium () {
      return this.totalPrice
        .toFixed(2)
        .replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, "$&,");
    },
    // player() {
    //   return this.$refs.videoPlayer.player;
    // },
    ...mapGetters("million", ["millionQuery", "gResult", "tenMillionData"])
  },
  // destroyed() {
  //   window.removeEventListener("scroll", this.scrollFunction(), true);
  // },
  beforeDestroy () {
    if (window) {
      window.removeEventListener("scroll", () => { });
    }
    this.timer && clearTimeout(this.timer);
  },
  components: {
    // globalNav,
    agePicker,
    edition
  }
};
</script>
<style lang="less" scoped>
/deep/ .van-cell__value {
  overflow: visible;
  word-break: keep-all;
}
.millionPersonal {
  font-family: PingFangSC-Semibold, PingFang SC;
  // padding-top: 2rem;
  box-sizing: border-box;
  height: max-content !important;
  padding-bottom: 1rem;
  // swipePart
  background: #2480ea;
  .header {
    // padding-top: 46px;
    // height: 224px;
    // background-image: url(../../../assets/nonCar/healthInsurance/r.png;
    // background-repeat: no-repeat;
    // background-size: 100% auto;
    background-color: #fff;
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
  .swipeImg {
    width: 100%;
    height: 100%;
    img {
      width: 100%;
      height: 100%;
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
    bottom: 25%;
    z-index: inherit;

    .span-left {
      margin-left: 14px;
    }
    .span-right {
      margin-right: 14px;
    }
  }
  .custom-indicator {
    position: absolute;
    right: 5px;
    bottom: 30px;
    padding: 2px 8px;
    font-size: 12px;
    background: rgba(255, 255, 255, 0.3);
    border-radius: 5px;
    color: white;
  }
  // swipePart

  .title {
    background: rgba(255, 255, 255, 1);
    display: flex;
    box-sizing: border-box;
    padding: 0.3rem 0.2rem;
    min-height: 69px;
    .info {
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

  .row {
    font-size: 0.5rem;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
    background: url("../../../assets/nonCar/healthInsurance/bg1.png") no-repeat;
    background-size: 100% 100%;
    text-align: center;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    height: 47px;
    h5 {
      width: 100%;
      // margin-bottom: -2px;
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
      // line-height: 16px;
    }
    p {
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(255, 255, 255, 1);
      font-size: 0.4rem;
      text-align: center;
      width: 100%;
    }
  }
  .card {
    background: rgba(255, 255, 255, 1);
    border-radius: 0.2rem;
    box-sizing: border-box;
    margin: 0.2rem;
    overflow: hidden;
    margin-top: 0;
    /deep/ .van-checkbox-group {
      box-sizing: border-box;
      margin: 0.2rem;
      overflow: hidden;
      border-radius: 0.2rem;

      .van-cell {
        background: rgba(247, 247, 247, 1);
      }
    }
  }
  .tabbar {
    display: flex;
    position: fixed; // todo suncao fixed
    -webkit-overflow-scroll: touch;
    width: 100%;
    bottom: 0;
    background-color: #fff;
    .price {
      width: 40%;
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
      font-size: 0.3rem;
      div {
        color: #fc820e;
        font-size: 0.4rem;
      }
    }
    .show {
      flex: 1;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(72, 104, 149, 1);
      background-color: #d6e6ff;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .btn {
      width: 37.3%;
      button {
        width: 100%;
        height: 100%;
      }
    }
  }

  .select-button {
    width: 60px;
    height: 27px;
    margin-left: 10px;
    background: #eeeeee;
    border-radius: 6px;
    color: #b9b9b9;
    border: 1px solid #eeeeee;
    font-size: 11px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    line-height: 27px;
    white-space: nowrap;
    padding: 0;
  }
  .selected-button {
    color: #2b79ed;
    // border: 1px solid #3b8afe;
    background: #eef5ff;
  }
  select {
    appearance: none;
    border: none;
    color: #323233;
    background: #fff;
    outline: none;
    -moz-appearance: none; /* Firefox */
    -webkit-appearance: none; /* Safari 和 Chrome */
  }

  > .van-popup {
    width: 90%;
    border-radius: 10px;

    .content {
      padding: 16px;
      h3 {
        text-align: center;
        padding-bottom: 0.2rem;
        font-size: 18px;
      }
      .divBtn {
        display: flex;
        div {
          flex: 1;
          text-align: center;
          img {
            width: 90%;
            border-radius: 10px;
            margin-top: 0.2rem;
          }
          button {
            margin-top: 0.2rem;
          }
        }
      }
    }
  }
}
/deep/ .navigatorBottom {
  display: none;
}
.productContent {
  background: rgba(36, 128, 234, 1);
}

.card-title {
  font-weight: 600;
}
.backBtn {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  position: fixed;
  right: 10px;
  bottom: 100px;
  cursor: pointer;
  img {
    width: 100%;
  }
}
/deep/ #app {
  overflow: hidden;
}
</style>
