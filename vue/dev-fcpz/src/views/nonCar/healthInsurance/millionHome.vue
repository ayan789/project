<template>
  <!-- 百万医疗家庭版 -->
  <div
    class="millionHome"
    id="millionHome"
  >
    <!-- <globalNav :title="globalNav.title" @clickBack="goback"></globalNav> -->
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
                alt=""
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
            alt=""
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
              :src="millionHomeImg"
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
          {{ formula.premium }}<span>元起</span>
        </div>
      </div>
    </div>
    <div class="row">
      <h5>保障计划</h5>
      <!-- <p>全程指导，5步快速理赔</p> -->
    </div>
    <div class="productContent">
      <div style="width:100%;height:0.2rem;"></div>
      <div
        class="card"
        style="margin-top:0"
      >
        <van-collapse
          v-model="activeName"
          accordion
        >
          <van-collapse-item
            :title="item.name"
            :value="item.subname"
            v-for="(item, i) in amount1"
            :key="i"
            :name="`${i}` + 1"
          >{{ amount1Desc[i] }}</van-collapse-item>
        </van-collapse>

        <van-checkbox-group v-model="result">
          <van-cell-group>
            <van-cell title="附加责任" />
            <van-cell
              v-for="(item, index) in infoJson2"
              clickable
              :key="item"
              :title="item"
              @click="AddInsurance(index, item)"
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
          <van-cell title="关系人信息" />

          <!-- 保障人数 -->
          <div>
            <van-field
              readonly
              clickable
              required
              :value="ensurePeopleInfo.ensureTypeVal"
              placeholder="请选择"
              @click="ensurePeopleInfo.ensurePicker = true"
              input-align="right"
            >
              <div slot="label">
                <span>保障人数</span>
              </div>
              <div
                slot="right-icon"
                class="rightCenter"
              >
                <van-icon
                  name="arrow"
                  class="grayBf"
                />
              </div>
            </van-field>
            <van-popup
              v-model="ensurePeopleInfo.ensurePicker"
              position="bottom"
            >
              <van-picker
                show-toolbar
                :columns="ensurePeopleInfo.ensuretype"
                @cancel="ensurePeopleInfo.ensurePicker = false"
                @confirm="peopleNumPickerConfirm"
              />
            </van-popup>
          </div>

          <!-- 被保人年龄循环-->
          <agePicker
            v-for="(item, index) in securityNum"
            :key="index"
            :num="index + 1"
            :defaultInsuredAgesVal="insuredAgesVal[index]"
            @changeAges="changeAges"
          />

          <van-cell title="是否有医保">
            <template>
              <van-button
                @click="btnChecked = true"
                disabled
                plain
                :class="[btnChecked ? 'selected-button' : '', 'select-button']"
              >有医保</van-button>
              <!-- <van-button
                disabled
                @click="btnChecked = false"
                plain
                v-if="false"
                :class="[!btnChecked ? 'selected-button' : '', 'select-button']"
                >无医保</van-button
              > -->
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
          <van-cell
            v-if="isProxy && isSharePage == '0'"
            title="代理人"
            is-link
            @click="toVehicleChannel"
          >
            <div class="item-right">
              <span>{{ channelInfo.agentName }}</span>
            </div>
          </van-cell>
          <van-cell
            title="直销/直团渠道"
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
      <edition person="F" />
    </div>
    <div
      class="tabbar __tabbar"
      :style="{'top':clientHeight}"
      ref="tabbar"
    >
      <div class="price">
        <div>{{ totalPrice }}</div>
        <p>保费(元)</p>
      </div>
      <div
        class="show"
        v-if="isSharePage == '0'"
        @click="show = !show"
      >
        展业夹
      </div>
      <div :class="isSharePage == '0' ? 'btn' : 'btnShare'">
        <van-button
          type="info"
          @click="enter"
        >我要投保</van-button>
      </div>
    </div>
    <!-- 弹框 -->
    <van-popup
      v-model="show"
      closeable
    >
      <div class="content">
        <h3>百万医疗2020家庭版</h3>
        <div class="divBtn">
          <div>
            <img
              src="@/assets/nonCar/healthInsurance/l.png"
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
              src="@/assets/nonCar/healthInsurance/l.png"
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
  </div>
</template>
<script>
import globalNav from "../../../components/globalNav";
import edition from "./edition";
import agePicker from "./agePicker";
import { storeQueryFormula } from "@src/http/module/nonCar.js";
import {
  getDefaultChannel,
  getDefaultAgent,
  agentQuery
} from "@src/http/module/vehicle";
import { getShareUrl } from "@src/http/module/customerApi";

import { mapGetters, mapActions, mapMutations } from "vuex";
import Config from "@src/common/json/newCoreDataDictionary";
import { Toast } from "vant";
let agentInfoParam = "";
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
      activeName: "0",
      scrollTop: 0,
      shareImg: require("@/assets/common/share.png"),
      shareIcon: require("@/assets/nonCar/propertyInsurance/shareBlack.png"),
      isScroll: false,
      millionHomeImg: require("@/assets/nonCar/healthInsurance/l.png"),
      current: 0, // 轮播索引
      show: false,
      queryData: "", //请求数据
      formula: "", //方案
      amountInfoJson: "", //一般医疗和附加责任
      amount1: [],
      amount1Desc: [
        "住院医疗、特殊门诊医疗、门诊手术医疗、住院前30天/出院后30天门急诊医疗费用",
        "患本条款约定的重大疾病而发生的住院医疗、特殊门诊医疗、门诊手术医疗、住院前30天/出院后30天门急诊医疗费用",
        "家庭共用",
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
      securityNum: 2,
      totalPrice: "", //保费
      resultPrice: 0, //附加保费
      ageOne: "0-4", //传递被保人年龄段
      ageTwo: "0-4",
      ageThree: "",
      ageFour: "",
      ageFive: "",
      ageSix: "",
      ageSeven: "",
      agesArr: {
        ages1: "",
        ages2: "",
        ages3: "",
        ages4: "",
        ages5: "",
        ages6: "",
        ages7: ""
      },
      sumAmount: "", //总保额
      aKZEXClause: "N",
      aEXTZClause: "N",
      aEXYLClause: "N",
      aEXZZClause: "N",
      // 公共顶部
      globalNav: {
        title: "百万医疗2020家庭版",
        isShare: true
      },
      result: [],
      ensurePeopleInfo: {
        // 保障人数
        ensuretype: ["2人", "3人", "4人", "5人", "6人", "7人"],
        ensurePicker: false,
        ensureTypeVal: "2人"
      },
      btnChecked: true, //社保
      btnChecked2: false, //是否代理
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
      isProxy: false,
      channelInfo: channelInfoParam,
      saleSum: 16,
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
            //type: "video/mp4",
            //src: require("@/assets/nonCar/propertyInsurance/video.mp4")
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
      isSharePage: "0",
      clientHeight: ""
    };
  },
  // activated(){
  //   console.log(this.scrollTop)
  //   // window.scrollTo(0,this.scrollTop || 0)
  //   this.$nextTick(()=>{
  //       window.scrollTop = this.scrollTop || 0
  //       document.documentElement.scrollTop = this.scrollTop || 0
  //       document.body.scrollTop = this.scrollTop || 0
  //       setTimeout(() => {
  //         window.addEventListener("scroll", this.scrollFunction);
  //       });
  //   })
  // },
  created () {
    this.isFromOrderList = this.$route.params.isFromOrderList;
    //是否从壳中调整过来,从App首页点击产品走全流程，清理数据
    if (this.$GetRequest("source", true)) {
      this.cleanData();
    }

    this.saleSum = this.$route.query.saleSum;

    //全局获取缓存数据
    this.$globalSetLocalStorage();
    this.resetPageData();

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
      this.agentInfo.businessNature2 = channel.businessNature2Code;
      this.agentInfo.handler2Code = channel.handler2Code;
      this.agentInfo.handler2Name = channel.handler2Name;
      agentInfoParam = this.agentInfo;
    });

    if (!this.$isNull(agentInfoParam.agreementNo)) {
      this.isProxy = "1";
      this.isAgent = "1";
      this.agentInfo = agentInfoParam;
    }
    //获取分享海报地址或计划书进来信息 TODO suncao
    this.isSharePage = this.$GetRequest("isSharePage", true) || "0";
    if (this.isSharePage == "1") {
      this.cleanData();
      this.storeCode =
        this.$GetRequest("storeCode", true) ||
        localStorage.getItem("storeCode");
      localStorage.setItem("storeCode", this.storeCode);
      this.comCode =
        this.$GetRequest("comCode", true) || localStorage.getItem("comCode");
      localStorage.setItem("comCode", this.comCode);
      this.isAgent = this.$GetRequest("isAgent", true) || this.isAgent;
      if (!this.$isNull(this.isAgent) && this.isAgent != "undefined") {
        localStorage.setItem("isAgent", this.isAgent);
      } else {
        localStorage.setItem("isAgent", "0");
      }
      this.agentInfo =
        JSON.parse(decodeURIComponent(this.$GetRequest("agentInfo", true))) ||
        this.agentInfo;
      if (
        this.isAgent == "0" ||
        this.isAgent == "" ||
        this.isAgent == "undefined"
      ) {
        localStorage.setItem("agentInfo", null);
      } else {
        localStorage.setItem("agentInfo", JSON.stringify(this.agentInfo));
      }
    } else {
      this.storeCode = localStorage.getItem("storeCode");
      this.comCode = localStorage.getItem("comCode");
    }
    //   请求数据
    this.getQueryFormula({
      proCode: "FM51",
      storeCode: this.storeCode || localStorage.getItem("storeCode") //qHbO6cAgkSLMSve3GoRR
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
        //缓存数据恢复
        this.resetPageData();

        //初始化方案信息
        this.initFormulaInfo();
        this.initSection1();
        //初始保费计算
        this.updatePremiumsNum();
      })
      .catch(err => {
        Toast("请求失败");
      });

    //初始是否代理：否
    if (this.isProxy) {
      this.changeIsProxy("y");
    } else {
      this.changeIsProxy("n");
    }
  },
  // updated() {
  //   const topPos = localStorage.getItem("topPos");
  //   // const topPos = this.$store.state.nonCar.topPos;
  //   // if (topPos) {
  //   //   document.documentElement.scrollTop = `${topPos}`;
  //   //   document.body.scrollTop = `${topPos}`;
  //   //   window.scrollTop = `${topPos}`;
  //   //   window.scrollTo(0, `${topPos}`);
  //   // }
  // },
  updated () {
    // if(this.scrollTop){
    //   document.getElementById("worryFree").scrollTop = this.scrollTop;
    //   window.scrollTop = this.scrollTop;
    // }
    // const topPos = this.$store.state.nonCar.topPos;
    // if (topPos) {
    //   document.getElementById("worryFree").scrollTop = `${topPos}`;
    //   window.scrollTop = `${topPos}`;
    //   window.scrollTo(0, `${topPos}`);
    //   // 返回完成 置0
    //   this.$store.commit("nonCar/setPos", 0);
    // }
  },
  mounted () {
    this.clientHeight =
      document.documentElement.clientHeight -
      this.$refs.tabbar.offsetHeight +
      "px";
    if ("scrollRestoration" in history) {
      history.scrollRestoration = "manual"; //改为manual之后，就不会记录滚动位置
    }
    // 监听滚动
    // window.addEventListener("scroll", () => {
    //   this.scrollFunction();
    // });
    setTimeout(() => {
      // console.log("dynamic change options", this.player);
      // this.player && this.player.muted(false);
    }, 5000);
  },
  methods: {
    async sharePage () {
      // let res = await getShareUrl({
      //   url: window.location.href + "&isSharePage=1"
      // });
      // let shareUrl =
      //   res.data.shortUrl || window.location.href + "&isSharePage=1";
      // console.log("分享短连接===" + shareUrl);
      // const desc = `尊敬的 ${this.name}，您的保险专家 ${this.businessName} 为您精心准备的计划书，请查阅！`;
      // const title = this.millionInfo.formulaName;
      let shareUrl =
        window.location.href +
        "&proCode=FM51&storeCode=" +
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
      let res = await getShareUrl({ url: shareUrl });
      console.log("分享链接：" + res.data.shortUrl);
      if (res.code == "1") {
        //调用接口参数 TODO
        const jsonObject = {
          sharePlatform: ["0", "1"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
          shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
          title: "天地关爱百万医疗家庭版", //分享标题
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
    scrollFunction () {
      if (
        document.body.scrollTop > 20 ||
        document.documentElement.scrollTop > 20
      ) {
        this.isScroll = true;
        this.scrollTop =
          document.body.scrollTop || document.documentElement.scrollTop;
      } else {
        this.isScroll = false;
        this.scrollTop = 0;
      }
      console.log(this.scrollTop, "this.scrollTop", this);
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
       *
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
    // 是否代理
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
        if (this.isSharePage != "1") {
          localStorage.setItem("agentInfo", "");
        }
      }
    },
    // 返回上一页
    onClickLeft () {
      localStorage.removeItem("topPos");
      if (!this.$isNull(this.$GetRequest("source", true))) {
        this.$native.closeWeb();
      } else {
        this.$router.go(-1);
      }
    },
    ...mapMutations("channel", ["setChannel"]),
    // 初始化
    initFormulaInfo () {
      if (this.securityNum <= 3) {
        this.formula = this.queryData.data.sfList[0];
        console.dir("初始化了方案信息1");
      } else {
        this.formula = this.queryData.data.sfList[1];
      }
    },

    //页面渲染 责任 保额 和 附加责任和保额
    initSection1 () {
      this.amountInfoJson = JSON.parse(this.formula.amountInfoJson);
      let amountInfoJson = this.amountInfoJson;
      for (let key in amountInfoJson) {
        if (parseInt(key.substr(0, key.indexOf("#"))) <= 17) {
          this.amount1.push({
            name: key.split("#")[1], //责任
            subname: this.transfer(amountInfoJson[key]) //保额
          });
          console.dir(this.amount1);
        } else {
          this.infoJson2.push(key.split("#")[1]);
        }
      }
    },

    // 初始化保费
    updatePremiumsNum () {
      this.changeNum(this.securityNum);

      //如果选择附加责任，计算基础年龄区段
      if (this.result.length) {
        this.totalPrice = parseInt(
          (parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("0-4"))) *
          this.securityNum
        );
      } else {
        //如果没有选择附加责任，计算每个人的基础保费
        this.totalPrice = parseInt(
          JSON.parse(this.formula.premiumsJson)["0-4"].BF * this.securityNum
        );
      }

      //计算每个被保人的年龄保费（方案保费 + 附加责任保费）
      switch (this.securityNum) {
        case 2:
          this.getAgePrice(this.agesArr.ages1);
          this.getAgePrice(this.agesArr.ages2);
          for (let key in this.agesArr) {
            if (key != "ages1" && key != "ages2") {
              this.agesArr[key] = "";
            }
          }
          break;
        case 3:
          this.getAgePrice(this.agesArr.ages1);
          this.getAgePrice(this.agesArr.ages2);
          this.getAgePrice(this.agesArr.ages3);
          for (let key in this.agesArr) {
            if (key != "ages1" && key != "ages2" && key != "ages3") {
              this.agesArr[key] = "";
            }
          }
          break;
        case 4:
          this.getAgePrice(this.agesArr.ages1);
          this.getAgePrice(this.agesArr.ages2);
          this.getAgePrice(this.agesArr.ages3);
          this.getAgePrice(this.agesArr.ages4);
          // for (let key in this.agesArr) {
          //   if (key == "ages5" && key == "ages6" && key == "ages7") {
          //     this.agesArr[key] = "";
          //   }
          // }
          for (let key in this.agesArr) {
            if (key == "ages5" || key == "ages6" || key == "ages7") {
              this.agesArr[key] = "";
            }
          }
          break;
        case 5:
          this.getAgePrice(this.agesArr.ages1);
          this.getAgePrice(this.agesArr.ages2);
          this.getAgePrice(this.agesArr.ages3);
          this.getAgePrice(this.agesArr.ages4);
          this.getAgePrice(this.agesArr.ages5);
          this.agesArr.ages6 = this.agesArr.ages7 = "";
          break;
        case 6:
          this.getAgePrice(this.agesArr.ages1);
          this.getAgePrice(this.agesArr.ages2);
          this.getAgePrice(this.agesArr.ages3);
          this.getAgePrice(this.agesArr.ages4);
          this.getAgePrice(this.agesArr.ages5);
          this.getAgePrice(this.agesArr.ages6);
          this.agesArr.ages7 = "";
          break;
        case 7:
          for (let key in this.agesArr) {
            this.getAgePrice(this.agesArr[key]);
          }
          break;
      }
    },
    //获取年龄保费(方案保费 + 附加责任保费)
    getAgePrice (arg) {
      switch (arg) {
        case 1:
          //默认是1，已经计算，价格不变
          this.totalPrice = this.totalPrice;
          break;
        case 2:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["5-10"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("5-10")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 3:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["11-15"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("11-15")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 4:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["16-20"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("16-20")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 5:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["21-25"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("21-25")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 6:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["26-30"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("26-30")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 7:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["31-35"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("31-35")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 8:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["36-40"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("36-40")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 9:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["41-45"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("41-45")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 10:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["46-50"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("46-50")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 11:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["51-55"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("51-55")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
        case 12:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["56-60"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
            parseInt(this.getAddPremiumByAge("56-60")) -
            parseInt(this.getAddPremiumByAge("0-4"));
          break;
      }
    },
    //计算附加责任的年龄区段保费
    getAddPremiumByAge (ageSection = "0-4") {
      //如果有选择附加责任
      if (this.result.length) {
        this.resultPrice = 0;
        this.result.forEach(item => {
          if (
            item == "扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)"
          ) {
            this.resultPrice += parseInt(
              JSON.parse(this.formula.premiumsJson)[ageSection].KZEX
            );
          }
          if (item == "恶性肿瘤院外特种药品费用医疗责任") {
            this.resultPrice += parseInt(
              JSON.parse(this.formula.premiumsJson)[ageSection].EXTZ
            );
          }
          if (item == "恶性肿瘤赴日医疗责任") {
            this.resultPrice += parseInt(
              JSON.parse(this.formula.premiumsJson)[ageSection].EXYL
            );
          }
          if (item == "恶性肿瘤扩展质子重离子医疗责任") {
            this.resultPrice += parseInt(
              JSON.parse(this.formula.premiumsJson)[ageSection].EXZZ
            );
          }
        });
        return this.resultPrice;
      } else {
        return 0;
      }
    },
    /**
     * index: 第几个年龄段
     * num: 第几个人
     * ageNum: 年龄段描述
     */
    changeAges (index, num, ageNum) {
      console.log(index + "====" + num + "====" + ageNum);
      switch (num) {
        case 1:
          //第一个人的年龄段序号和年龄段文本
          this.agesArr.ages1 = index;
          this.ageOne = ageNum;
          break;
        case 2:
          //第二个人的年龄段序号和年龄段文本
          this.agesArr.ages2 = index;
          this.ageTwo = ageNum;
          break;
        case 3:
          //第三个人的年龄段序号和年龄段文本
          this.agesArr.ages3 = index;
          this.ageThree = ageNum;
          break;
        case 4:
          //第四个人的年龄段序号和年龄段文本
          this.agesArr.ages4 = index;
          this.ageFour = ageNum;
          break;
        case 5:
          //第五个人的年龄段序号和年龄段文本
          this.agesArr.ages5 = index;
          this.ageFive = ageNum;
          break;
        case 6:
          //第六个人的年龄段序号和年龄段文本
          this.agesArr.ages6 = index;
          this.ageSix = ageNum;
          break;
        case 7:
          //第七个人的年龄段序号和年龄段文本
          this.agesArr.ages7 = index;
          this.ageSeven = ageNum;
          break;
      }

      //年龄变化重新计算保费
      this.updatePremiumsNum();
    },
    transfer (amount) {
      if (amount < 10000) {
        amount = amount + "元";
      } else {
        amount = amount / 10000 + "万/人";
      }
      return amount;
    },
    changeNum (securityNum = {}) {
      switch (securityNum || this.securityNum) {
        case 3:
          if (this.ageThree == "") {
            this.ageThree = "0-4";
          }
          this.ageFour = "";
          this.ageFive = "";
          this.ageSix = "";
          this.ageSeven = "";
          break;
        case 4:
          if (this.ageThree == "") {
            this.ageThree = "0-4";
          }
          if (this.ageFour == "") {
            this.ageFour = "0-4";
          }
          this.ageFive = "";
          this.ageSix = "";
          this.ageSeven = "";
          break;
        case 5:
          if (this.ageThree == "") {
            this.ageThree = "0-4";
          }
          if (this.ageFour == "") {
            this.ageFour = "0-4";
          }
          if (this.ageFive == "") {
            this.ageFive = "0-4";
          }
          this.ageSix = "";
          this.ageSeven = "";
          break;
        case 6:
          if (this.ageThree == "") {
            this.ageThree = "0-4";
          }
          if (this.ageFour == "") {
            this.ageFour = "0-4";
          }
          if (this.ageFive == "") {
            this.ageFive = "0-4";
          }
          if (this.ageSix == "") {
            this.ageSix = "0-4";
          }
          this.ageSeven = "";
          break;
        case 7:
          if (this.ageThree == "") {
            this.ageThree = "0-4";
          }
          if (this.ageFour == "") {
            this.ageFour = "0-4";
          }
          if (this.ageFive == "") {
            this.ageFive = "0-4";
          }
          if (this.ageSix == "") {
            this.ageSix = "0-4";
          }
          if (this.ageSeven == "") {
            this.ageSeven = "0-4";
          }
          break;
      }
    },
    enter () {
      // this.changeNum();
      // this.getSumAmount();
      let isAgent = this.isProxy ? "1" : "0"; //是否代理
      isAgent = this.isAgent; //是否代理
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

      //保存数据
      this.savePageInfo();



      this.$router.push({
        name: "healthTellFamily"
      });
    },
    savePageInfo () {
      this.changeNum();
      this.getSumAmount();
      let isAgent = this.isProxy ? "1" : "0"; //是否代理
      this.result.forEach(item => { });
      let params = {
        formulaCode: this.formula.formulaCode, //方案代码
        formulaName: this.formula.formulaName, //方案名称
        sumAmount: this.sumAmount, //总保额
        sumPremium: this.totalPrice, //总保费
        securityNum: this.securityNum, //保障人数
        isAgent: isAgent == "0" ? false : true, //是否代理
        agentInfo: this.agentInfo, //代理信息
        aKZEXClause: this.aKZEXClause, //附加扩展恶性肿瘤特需医疗保险
        aEXTZClause: this.aEXTZClause, //附加个人恶性肿瘤院外特种药品费用医疗保险
        aEXYLClause: this.aEXYLClause, //附加个人恶性肿瘤赴日医疗保险
        aEXZZClause: this.aEXZZClause, //附加个人恶性肿瘤扩展质子重离子医疗保险
        isSheBao: this.btnChecked ? "Y" : "", //是否社保
        ageOne: this.ageOne, // 被保人年龄段
        ageTwo: this.ageTwo, //  被保人2年龄段
        ageThree: this.ageThree, //  被保人3年龄段
        ageFour: this.ageFour, // 被保人4年龄段
        ageFive: this.ageFive,
        ageSix: this.ageSix,
        ageSeven: this.ageSeven,
        agesArr: this.agesArr,
        productCode: "FM51", //产品代码
        riskCode: "WTN",
        storeCode: this.storeCode
          ? this.storeCode
          : localStorage.getItem("storeCode")
      };
      this.$store.commit("million/SETMILLIONDATAFAMILY", params);
      //保存第一个页面填写信息
      localStorage.setItem("millionHomeStep1", JSON.stringify(params));
      // 分享以后代理信息设置
      const isWeiXin = this.$isWeiXin();
      if (isWeiXin) {
        let agentInfo = JSON.parse(localStorage.getItem('millionHomeStep1')).agentInfo
        localStorage.setItem('agentInfo', JSON.stringify(agentInfo))
      }
      console.log("agentinfo", localStorage.getItem('agentInfo'))

      return params;
    },
    //回复数据
    resetPageData () {
      const val = localStorage.getItem("millionHomeStep1");

      if (this.$isNull(val)) {
        return;
      }
      const params = JSON.parse(val);
      if (params) {
        this.isProxy = params.isAgent;
        this.formula ? (this.formula.formulaCode = params.formulaCode) : ""; //方案代码
        this.formulaName ? (this.formula.formulaName = params.formulaName) : ""; //方案名称
        this.sumAmount = params.sumAmount; //总保额
        this.totalPrice = params.sumPremium; //总保费
        this.securityNum = params.securityNum; //保障人数
        this.agentInfo = params.agentInfo; //代理信息
        this.aKZEXClause = params.aKZEXClause; //附加扩展恶性肿瘤特需医疗保险
        this.aEXTZClause = params.aEXTZClause; //附加个人恶性肿瘤院外特种药品费用医疗保险
        this.aEXYLClause = params.aEXYLClause; //附加个人恶性肿瘤赴日医疗保险
        this.aEXZZClause = params.aEXZZClause; //附加个人恶性肿瘤扩展质子重离子医疗保险
        this.btnChecked = params.isSheBao == "Y" ? true : false; //是否社保
        this.ageOne = params.ageOne; // 被保人年龄段
        this.ageTwo = params.ageTwo; //  被保人2年龄段
        this.ageThree = params.ageThree; //  被保人3年龄段
        this.ageFour = params.ageFour; // 被保人4年龄段
        this.ageFive = params.ageFive;
        this.ageSix = params.ageSix;
        this.ageSeven = params.ageSeven;
        this.agesArr = params.agesArr;

        this.ensurePeopleInfo.ensureTypeVal = this.securityNum + "人";

        if (this.aKZEXClause == "Y") {
          this.addInsureLiability(
            "扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)"
          );
        }
        if (this.aEXTZClause == "Y") {
          this.addInsureLiability("恶性肿瘤院外特种药品费用医疗责任");
        }
        if (this.aEXYLClause == "Y") {
          this.addInsureLiability("恶性肿瘤赴日医疗责任");
        }
        if (this.aEXZZClause == "Y") {
          this.addInsureLiability("恶性肿瘤扩展质子重离子医疗责任");
        }
      }
    },
    addInsureLiability (liability) {
      if (!this.result.includes(liability)) {
        this.result.push(liability);
      }
    },
    goback () {
      this.cleanData();

      //统一返回，如果直接从壳跳进来，调用壳返回
      this.$globalBack();
    },
    cleanData () {
      //清空家庭版数据
      localStorage.setItem("millionHomeStep1", "");
      //清空家庭版数据
      localStorage.setItem("millionHomeStep2", "");
    },
    toggle (val) {
      this.result = this.result.includes(val)
        ? this.result.filter(item => item != val)
        : [...this.result, val];
    },
    AddInsurance (index, val) {
      console.log(this.result, "this.result");
      this.$refs.checkboxes[index].toggle();
      this.toggle(val);
      console.log(this.result, "this.result~~~~~~~~~~~~~~~~~");
      //附加责任变化，更新保费
      this.updatePremiumsNum();
    },
    // 去分享海报页面
    goProductPoster () {
      console.log("海报地址：" + window.location.href);

      //保存数据
      this.savePageInfo();
      const userInfo = JSON.parse(localStorage.getItem("userInfo"));
      this.$router.push({
        name: "productPoster",
        query: {
          item: {
            url: window.location.href,
            storeCode: localStorage.getItem("storeCode"),
            comCode: localStorage.getItem("comCode"),
            isAgent: this.isAgent,
            agentInfo: JSON.stringify(this.agentInfo),
            name: userInfo.userName,
            mobile: userInfo.userPhone || userInfo.mobile,
            isSharePage: "1",
            businessNatureCode: this.channelInfo.businessNatureCode || "",
            businessNatureName: this.channelInfo.businessNatureName || ""
          }
        }
      });
    },
    //制作商业计划书
    goMillionPlan () {
      //TODO suncao
      console.log("保障人数：" + this.ensurePeopleInfo);
      console.log(this.ageOne); // 被保人年龄段
      console.log(this.ageTwo); //  被保人2年龄段
      console.log(this.ageThree); //  被保人3年龄段
      console.log(this.ageFour); // 被保人4年龄段
      console.log(this.ageFive);
      console.log(this.ageSix);
      console.log(this.ageSeven);
      let agesArray = [];
      if (this.ageOne) {
        let id = "insureName1";
        let ages = this.ageOne;
        let obj = {
          id: id,
          ages: ages
        };
        agesArray.push(obj);
      }
      if (this.ageTwo) {
        let id = "insureName2";
        let ages = this.ageTwo;
        let obj = {
          id: id,
          ages: ages
        };
        agesArray.push(obj);
      }
      if (this.ageThree) {
        let id = "insureName3";
        let ages = this.ageThree;
        let obj = {
          id: id,
          ages: ages
        };
        agesArray.push(obj);
      }
      if (this.ageFour) {
        let id = "insureName4";
        let ages = this.ageFour;
        let obj = {
          id: id,
          ages: ages
        };
        agesArray.push(obj);
      }
      if (this.ageFive) {
        let id = "insureName5";
        let ages = this.ageFive;
        let obj = {
          id: id,
          ages: ages
        };
        agesArray.push(obj);
      }
      if (this.ageSix) {
        let id = "insureName6";
        let ages = this.ageSix;
        let obj = {
          id: id,
          ages: ages
        };
        agesArray.push(obj);
      }
      if (this.ageSeven) {
        let id = "insureName7";
        let ages = this.ageSeven;
        let obj = {
          id: id,
          ages: ages
        };
        agesArray.push(obj);
      }
      console.log("被保人数组:" + agesArray);
      const userInfo = JSON.parse(localStorage.getItem("userInfo"));
      //保存数据
      this.savePageInfo();
      let item = {
        agesArray: agesArray,
        storeCode: localStorage.getItem("storeCode"),
        comCode: localStorage.getItem("comCode"),
        isAgent: this.isAgent || "0",
        securityNum: this.securityNum, //保障人数
        agentInfo: JSON.stringify(this.agentInfo),
        name: userInfo.userName,
        mobile: userInfo.userPhone || userInfo.mobile,
        isSharePage: "1",
        aKZEXClause: this.aKZEXClause, //附加扩展恶性肿瘤特需医疗保险
        aEXTZClause: this.aEXTZClause, //附加个人恶性肿瘤院外特种药品费用医疗保险
        aEXYLClause: this.aEXYLClause, //附加个人恶性肿瘤赴日医疗保险
        aEXZZClause: this.aEXZZClause, //附加个人恶性肿瘤扩展质子重离子医疗保险
        accidentalInjuryInfos: [
          {
            age: "0-4",
            familyIdentifyNo: "",
            familyIdentifyType: "",
            familyName: "",
            familyRelation: "",
            insuredBirthDate: "",
            insuredSheBaoType: "",
            mobile: ""
          }
        ],
        endDate: "", //结束时间
        startDate: "" //开始时间
      };
      this.$router.push({
        name: "millionPlanHome",
        query: {
          item: item
        }
      });
    },
    peopleNumPickerConfirm (value, index) {
      this.ensurePeopleInfo.ensureTypeVal = value;
      this.securityNum = index + 2;
      this.ensurePeopleInfo.ensurePicker = false;

      //被保人数量变化，从新计算保费
      this.initFormulaInfo();
      this.updatePremiumsNum();
    },
    getSumAmount () {
      this.sumAmount = parseInt(JSON.parse(this.formula.amountsJson)["JZH"]);
      // 循环result
      this.aKZEXClause = "N";
      this.aEXTZClause = "N";
      this.aEXYLClause = "N";
      this.aEXZZClause = "N";
      if (this.result.length) {
        this.result.forEach(item => {
          if (
            item == "扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)"
          ) {
            this.sumAmount += parseInt(
              JSON.parse(this.formula.amountsJson)["KZEX"]
            );
            this.aKZEXClause = "Y";
          }
          if (item == "恶性肿瘤院外特种药品费用医疗责任") {
            this.sumAmount += parseInt(
              JSON.parse(this.formula.amountsJson)["EXTZ"]
            );
            this.aEXTZClause = "Y";
          }
          if (item == "恶性肿瘤赴日医疗责任") {
            this.sumAmount += parseInt(
              JSON.parse(this.formula.amountsJson)["EXYL"]
            );
            this.aEXYLClause = "Y";
          }
          if (item == "恶性肿瘤扩展质子重离子医疗责任") {
            this.sumAmount += parseInt(
              JSON.parse(this.formula.amountsJson)["EXZZ"]
            );
            this.aEXZZClause = "Y";
          }
        });
      }
      this.sumAmount *= parseInt(this.securityNum);
    },
    ...mapActions("million", ["getQueryFormula"]),
    // 跳到代理渠道页面
    toVehicleChannel () {
      let query = {
        riskCode: "WTN"
      };
      this.$root.$once("channelChange", channel => {
        this.channelInfo = channel;
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
      this.agentInfo.businessNature2 = channel.businessNature2Code;
      this.agentInfo.handler2Code = channel.handler2Code;
      this.agentInfo.handler2Name = channel.handler2Name;
      agentInfoParam = this.agentInfo;

        if (!this.$isNull(agentInfoParam.agreementNo)) {
          this.isProxy = "1";
          this.isAgent = "1";
          this.agentInfo = agentInfoParam;
        }


      });
      this.setChannel(this.channelInfo);
      //保存数据
      this.savePageInfo();

      this.$router.push({
        name: "vehicleChannel",
        query: query
      });
    },
    //formulas:方案, addIns:附加险, ages:年龄段, peopleNum:总人数
    getTotalPremiums (formulas, addIns, ages, peopleNum) {
      //判断参数有效性
      if (
        !this.$isNull(formulas) &&
        !this.$isNull(addIns) &&
        !this.$isNull(ages) &&
        peopleNum > 2
      ) {
        let formula = {};
        //根据被保人数量选择不同方案
        if (peopleNum <= 3) {
          formula = formulas[0];
        } else {
          formula = formulas[1];
        }
      }
    }
  },
  computed: {
    ...mapGetters("million", ["millionQuery"]),
    // player() {
    //   return this.$refs.videoPlayer && this.$refs.videoPlayer.player;
    // },
    insuredAgesVal () {
      const ages = [
        this.ageOne,
        this.ageTwo,
        this.ageThree,
        this.ageFour,
        this.ageFive,
        this.ageSix,
        this.ageSeven
      ];
      return ages;
    }
  },
  components: {
    globalNav,
    agePicker,
    edition
  }
  // deactivated() {
  //   window.removeEventListener("scroll", this.scrollFunction);
  // }
  // destroyed() {
  //   window.removeEventListener("scroll", this.scrollFunction);
  // }
};
</script>
<style>
.__tabbar {
  position: fixed;
  -webkit-overflow-scroll: touch;
  width: 100%;
  left: 0;
  top: auto !important;
  background-color: #fff;
}
</style>
<style lang="less" scoped>
/deep/ .van-cell__value {
  overflow: visible;
  word-break: keep-all;
}
.millionHome {
  // padding-top: 2rem;
  box-sizing: border-box;
  height: max-content !important;
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
  // swipePart
  .header {
    // padding-top: 46px;
    // height: 224px;
    // background-image: url(../../../assets/nonCar/healthInsurance/l.png);
    // background-repeat: no-repeat;
    // background-size: 100% auto;
    position: relative;
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
    bottom: 28%;
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

  .productContent {
    background: rgba(36, 128, 234, 1);
  }
  .title {
    background: rgba(255, 255, 255, 1);
    display: flex;
    box-sizing: border-box;
    padding: 0.5rem 0.2rem;
    min-height: 79px;
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
    // background-color: #2480ea;
    text-align: center;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    // padding: 0.2rem 0;
    height: 47px;
    h5 {
      width: 100%;
      margin-bottom: -2px;
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
      line-height: 16px;
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
    left: 0;
    // top:
    bottom: 0 !important;
    background-color: #fff;
    .price {
      width: 34.6%;
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
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
    .btnShare {
      width: 65.3%;
      button {
        width: 100%;
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
</style>
