<template>
  <!-- 百万医疗家庭版 -->
  <div class="millionHome">
    <div v-show="isScroll" class="scrollHead">
      <van-nav-bar
        :title="globalNav.title"
        left-arrow
        @click-left="goback"
      ></van-nav-bar>
    </div>
    <div class="backIcon" v-if="!isScroll">
      <van-icon @click="goback" name="arrow-left" color="white" size="24px" />
    </div>
    <!-- <globalNav :title="globalNav.title" @clickBack="goback"></globalNav> -->
    <!-- 轮播 -->
    <van-swipe @change="onChange" :loop="false">
      <van-swipe-item>
        <div class="swipeImg">
          <img :src="millionHomeImg" alt="" />
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

    <div class="title">
      <div class="info">
        <h1 v-if="formula">{{ formula.formulaName }}</h1>
        <p>住院、门诊、交通多重保障</p>
      </div>
      <div class="price" v-if="formula">{{ formula.premium }}元</div>
    </div>

    <div class="row">
      <h5>保障计划</h5>
      <p>全程指导，5步快速理赔</p>
    </div>
    <div class="productContent">
      <div style="width:100%;height:0.2rem;"></div>
      <div class="card" style="margin-top:0">
        <van-cell-group>
          <van-cell
            :title="item.name"
            :value="item.subname"
            v-for="(item, i) in amount1"
            :key="i"
          />
        </van-cell-group>

        <van-checkbox-group v-model="result">
          <van-cell-group>
            <van-cell title="附加责任" />
            <van-cell
              v-for="(item, index) in infoJson2"
              clickable
              :key="item"
              :title="item"
              @click="toggle(index)"
            >
              <template #right-icon>
                <van-checkbox :name="item" ref="checkboxes" />
              </template>
            </van-cell>
          </van-cell-group>
        </van-checkbox-group>
      </div>

      <div class="card">
        <van-cell-group>
          <van-cell title="投保人信息" />
          <van-field
            v-model="appliName"
            label="投保人姓名"
            placeholder="请输入投保人姓名"
            input-align="right"
          ></van-field>
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
              <div slot="right-icon" class="rightCenter">
                <van-icon name="arrow" class="grayBf" />
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
                @confirm="ensure_onConfirm"
              />
            </van-popup>
          </div>
          <van-cell v-for="(item, index) in securityNum" clickable :key="index">
            <div>
              <van-field
                readonly
                clickable
                required
                :key="index"
                :value="insuredArrayModel[index].label"
                placeholder="请选择"
                @click="showrelationInfo(index)"
                input-align="right"
              >
                <div slot="label">
                  <span>与投保人关系</span>
                </div>
                <div slot="right-icon" class="rightCenter">
                  <van-icon name="arrow" class="grayBf" />
                </div>
              </van-field>
              <van-popup v-model="relationInfo.picker" position="bottom">
                <van-picker
                  show-toolbar
                  :columns="relationInfo.appliIdentityList"
                  @cancel="relationInfo.picker = false"
                  @confirm="card_confirm"
                />
              </van-popup>
            </div>
            <agePicker
              :key="index"
              :num="index + 1"
              :defaultInsuredAgesVal="insuredAgesVal[index]"
              @changeAges="changeAges"
              :ref="'newage' + `${index + 1}`"
            />
            <van-field
              v-model="insuredArrayModel[index].insuredName"
              :label="'被保人' + `${index + 1}` + '姓名'"
              placeholder="请输入姓名"
              input-align="right"
            ></van-field>
          </van-cell>
          <van-cell title="是否有医保">
            <template>
              <van-button
                @click="btnChecked = true"
                disabled
                plain
                :class="[btnChecked ? 'selected-button' : '', 'select-button']"
                >有医保</van-button
              >
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

          <van-cell title="是否代理" v-if="isSharePage == '0'">
            <template>
              <van-button
                @click="changeIsProxy('y')"
                plain
                :class="[isProxy ? 'selected-button' : '', 'select-button']"
                >是</van-button
              >
              <van-button
                @click="changeIsProxy('n')"
                plain
                :class="[!isProxy ? 'selected-button' : '', 'select-button']"
                >否</van-button
              >
            </template>
          </van-cell>
          <van-cell
            v-if="isProxy && isSharePage == '0'"
            title="代理人"
            :value="channelInfo.agentName"
            is-link
            @click="toVehicleChannel"
          />
          <van-cell
            title="直销/直团渠道"
            :value="channelInfo.businessNatureName"
            is-link
            v-if="!isProxy && isSharePage == '0'"
            @click="toVehicleChannel"
          />
        </van-cell-group>
      </div>
      <millionStatic />
    </div>
    <div class="tabbar">
      <div class="price">
        <div>{{ totalPrice }}</div>
        <p>保费(元)</p>
      </div>
      <div class="btnShare">
        <van-button type="info" @click="enter">生成计划书</van-button>
      </div>
    </div>
  </div>
</template>
<script>
import globalNav from "../../../components/globalNav";
import millionStatic from "./millionStatic";
import agePicker from "./agePicker";
import { storeQueryFormula } from "@src/http/module/nonCar.js";
import { mapGetters, mapActions, mapMutations } from "vuex";
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
  riskCode: "YEF"
};
export default {
  data() {
    return {
      current: 0, // 轮播索引
      millionHomeImg: require("@/assets/nonCar/healthInsurance/l.png"),
      isScroll: false,
      indexFlag: "",
      relationInfo: {
        appliIdentityList: ["本人", "配偶", "子女", "父母"],
        type: [
          { value: "11", label: "" },
          { value: "12", label: "" },
          { value: "13", label: "" },
          { value: "14", label: "" },
          { value: "15", label: "" },
          { value: "16", label: "" },
          { value: "17", label: "" }
        ],
        picker: false,
        typeVal: ""
      },
      show: false,
      queryData: "", //请求数据
      formula: "", //方案
      amountInfoJson: "", //一般医疗和附加责任
      amount1: [],
      infoJson2: [],
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
      aKZEXClause: false,
      aEXTZClause: false,
      aEXYLClause: false,
      aEXZZClause: false,
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
      agentInfo: {}, //代理信息
      isProxy: false,
      channelInfo: channelInfoParam,

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
      isSharePage: "0",
      agesArray: [],
      insuredArrayModel: [
        {
          insuredName: "",
          age: "",
          label: ""
        },
        {
          insuredName: "",
          age: "",
          label: ""
        },
        {
          insuredName: "",
          age: "",
          label: ""
        },
        {
          insuredName: "",
          age: "",
          label: ""
        },
        {
          insuredName: "",
          age: "",
          label: ""
        },
        {
          insuredName: "",
          age: "",
          label: ""
        },
        {
          insuredName: "",
          age: "",
          label: ""
        }
      ],
      appliName: "",
      accidentalInjuryInfos: []
    };
  },
  computed: {
    ...mapGetters("million", ["millionQuery"]),
    // player() {
    //   return this.$refs.videoPlayer.player;
    // },
    insuredAgesVal() {
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
  created() {
    //全局获取缓存数据
    this.$globalSetLocalStorage();
    const {
      agesArray,
      storeCode,
      comCode,
      isAgent,
      agentInfo,
      isSharePage,
      securityNum
    } = this.$route.query.item;
    if (agesArray) {
      this.agesArray = agesArray;
      this.securityNum = securityNum;
      this.ensurePeopleInfo.ensureTypeVal = securityNum + "人";
    }
    if (storeCode) {
      this.storeCode = storeCode;
    }
    if (comCode) {
      this.comCode = comCode;
    }
    if (isAgent) {
      this.isAgent = isAgent;
    }
    if (agentInfo) {
      this.agentInfo = decodeURIComponent(agentInfo);
    }
    if (isSharePage) {
      this.isSharePage = isSharePage;
    }

    //全局获取缓存数据
    this.$globalSetLocalStorage();
    this.resetPageData();

    this.$root.$once("channelChange", channel => {
      //TODO suncao取到渠道信息进行赋值,渲染不正确
      this.channelInfo = channel;
      channelInfoParam = channel;
      channelInfoParam.riskCode = "YEF";
      console.log(
        " this.channelInfo=======" + JSON.stringify(this.channelInfo)
      );
      this.isProxy = this.channelInfo.isProxy == "1" ? true : false;
      console.log(" this.isProxy=======" + this.isProxy);
      this.agentInfo.agentCode = channel.agentCode;
      this.agentInfo.agentName = channel.agentName;
      this.agentInfo.agreementName = channel.agentAgreementName;
      this.agentInfo.agreementNo = channel.agreementNo;
      this.agentInfo.businessNature = channel.businessNature2Name;
      this.agentInfo.businessNature2 = channel.businessNature2Code;
      this.agentInfo.handler2Code = channel.handler2Code;
      this.agentInfo.handler2Name = channel.handler2Name;
      agentInfoParam = this.agentInfo;
      console.log("this.agentInfo", JSON.stringify(this.agentInfo));
    });

    if (!this.$isNull(agentInfoParam.agreementNo)) {
      this.isProxy = "1";
      this.isAgent = "1";
      this.agentInfo = agentInfoParam;
    }
    this.getQueryFormula({
      proCode: "FM51",
      storeCode: localStorage.getItem("storeCode") //qHbO6cAgkSLMSve3GoRR
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
        this.resetPageData();

        this.initFormulaInfo(); //初始化方案信息
        this.initSection1();
        this.updatePremiumsNum();
      })
      .catch(err => {
        Toast("请求失败");
      });
  },
  destroyed() {
    window.removeEventListener("scroll", this.scrollFunction(), true);
  },
  mounted() {
    // 监听滚动
    window.addEventListener("scroll", () => {
      this.scrollFunction();
    });
    setTimeout(() => {
      // console.log("dynamic change options", this.player);
      // this.player.muted(false);
    }, 5000);
    if (this.agesArray) {
      for (let i = 0; i < this.agesArray.length; i++) {
        if (i == 0) {
          this.$refs.newage1[0].insuredAgesInfo.insuredAgesTypeVal =
            this.agesArray[0].ages + "岁";
          this.insuredArrayModel[0].age = this.agesArray[0].ages;
        }
        if (i == 1) {
          this.$refs.newage2[0].insuredAgesInfo.insuredAgesTypeVal =
            this.agesArray[1].ages + "岁";
          this.insuredArrayModel[1].age = this.agesArray[1].ages;
        }
        if (i == 2) {
          this.$refs.newage3[0].insuredAgesInfo.insuredAgesTypeVal =
            this.agesArray[2].ages + "岁";
          this.insuredArrayModel[2].age = this.agesArray[2].ages;
        }
        if (i == 3) {
          this.$refs.newage4[0].insuredAgesInfo.insuredAgesTypeVal =
            this.agesArray[3].ages + "岁";
          this.insuredArrayModel[3].age = this.agesArray[3].ages;
        }
        if (i == 4) {
          this.$refs.newage5[0].insuredAgesInfo.insuredAgesTypeVal =
            this.agesArray[4].ages + "岁";
          this.insuredArrayModel[4].age = this.agesArray[4].ages;
        }
        if (i == 5) {
          this.$refs.newage6[0].insuredAgesInfo.insuredAgesTypeVal =
            this.agesArray[5].ages + "岁";
          this.insuredArrayModel[5].age = this.agesArray[5].ages;
        }
        if (i == 6) {
          this.$refs.newage7[0].insuredAgesInfo.insuredAgesTypeVal =
            this.agesArray[6].ages + "岁";
          this.insuredArrayModel[6].age = this.agesArray[6].ages;
        }
      }
    }
  },
  methods: {
    // 轮播图on-change
    onChange(index) {
      this.current = index;
    },
    scrollFunction() {
      if (
        document.body.scrollTop > 20 ||
        document.documentElement.scrollTop > 20
      ) {
        this.isScroll = true;
      } else {
        this.isScroll = false;
      }
    },
    showrelationInfo(index) {
      //第x个位置的 关系
      this.indexFlag = index;
      this.relationInfo.picker = true;
      console.log(index);
    },
    card_confirm(value, index) {
      this.insuredArrayModel[this.indexFlag].label = value;
      this.relationInfo.picker = false;
      if (value == "本人") {
        this.insuredArrayModel[this.indexFlag].insuredName = this.appliName;
      }
    },
    //保存数据
    savePageInfo() {
      this.changeNum();
      this.getSumAmount();

      let isAgent = this.isProxy ? "1" : "0"; //是否代理
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
        appliName: this.appliName,
        insuredArrayModel: this.insuredArrayModel
      };

      //保存第一个页面填写信息
      localStorage.setItem("millionHomeStep1", JSON.stringify(params));

      return params;
    },
    //回复数据
    resetPageData() {
      const val = localStorage.getItem("millionHomeStep1");

      if (this.$isNull(val)) {
        return;
      }
      const params = JSON.parse(val);
      if (params) {
        this.isProxy = params.isAgent;
        this.formula ? (this.formula.formulaCode = params.formulaCode) : ""; //方案代码
        this.formula ? (this.formula.formulaName = params.formulaName) : ""; //方案名称
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
        this.appliName = params.appliName;
        this.insuredArrayModel = params.insuredArrayModel
          ? params.insuredArrayModel
          : this.insuredArrayModel;

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
    addInsureLiability(liability) {
      if (!this.result.includes(liability)) {
        this.result.push(liability);
      }
    },
    // 是否代理
    changeIsProxy(type) {
      if (type == "y") {
        this.isProxy = true;
        this.isAgent = "1";
      } else {
        this.isProxy = false;
        this.isAgent = "";
        this.businessNatureCode = this.businessNatureCode || "0101";
        this.businessNatureName =
          this.businessNatureName || "业务员销售（直销）";
      }
    },
    ...mapMutations("channel", ["setChannel"]),
    // 初始化
    initFormulaInfo() {
      if (this.securityNum <= 3) {
        this.formula = this.queryData.data.sfList[0];
        console.dir("初始化了方案信息1");
      } else {
        this.formula = this.queryData.data.sfList[1];
      }
    },

    initSection1() {
      this.amountInfoJson = JSON.parse(this.formula.amountInfoJson);
      let amountInfoJson = this.amountInfoJson;
      for (let key in amountInfoJson) {
        if (parseInt(key.substr(0, key.indexOf("#"))) <= 17) {
          this.amount1.push({
            name: key.split("#")[1],
            subname: this.transfer(amountInfoJson[key])
          });
          console.dir(this.amount1);
        } else {
          this.infoJson2.push(key.split("#")[1]);
        }
      }
    },

    // 初始化保费
    updatePremiumsNum() {
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
          for (let key in this.agesArr) {
            if (key == "ages5" && key == "ages6" && key == "ages7") {
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
    getAgePrice(arg) {
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
    getAddPremiumByAge(ageSection = "0-4") {
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

    changeAges(i, num, ageNum) {
      switch (num) {
        case 1:
          this.agesArr.ages1 = i;
          this.ageOne = ageNum;
          break;
        case 2:
          this.agesArr.ages2 = i;
          this.ageTwo = ageNum;
          break;
        case 3:
          this.agesArr.ages3 = i;
          this.ageThree = ageNum;
          break;
        case 4:
          this.agesArr.ages4 = i;
          this.ageFour = ageNum;
          break;
        case 5:
          this.agesArr.ages5 = i;
          this.ageFive = ageNum;
          break;
        case 6:
          this.agesArr.ages6 = i;
          this.ageSix = ageNum;
          break;
        case 7:
          this.agesArr.ages7 = i;
          this.ageSeven = ageNum;
          break;
      }

      //年龄变化重新计算保费
      this.updatePremiumsNum();
    },
    transfer(amount) {
      if (amount < 10000) {
        amount = amount + "元";
      } else {
        amount = amount / 10000 + "万/人";
      }
      return amount;
    },
    changeNum(securityNum = {}) {
      switch (securityNum || this.securityNum) {
        case 3:
          if (this.ageThree == "") {
            this.ageThree = "0-4";
          }
          break;
        case 4:
          if (this.ageThree == "") {
            this.ageThree = "0-4";
          }
          if (this.ageFour == "") {
            this.ageFour = "0-4";
          }
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
    enter() {
      if (this.$isNull(this.appliName)) {
        return Toast("请输入投保人姓名");
      }

      for (let pos = 0; pos < this.securityNum; pos++) {
        if (
          this.insuredArrayModel[pos] &&
          this.$isNull(this.insuredArrayModel[pos].insuredName)
        ) {
          return Toast(`请输入第${pos + 1}个被保人姓名`);
        }
      }

      this.changeNum();
      this.getSumAmount();
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
      console.log("被保人数组：" + JSON.stringify(this.insuredArrayModel));
      for (let index = 0; index < this.insuredArrayModel.length; index++) {
        const element = this.insuredArrayModel[index];
        if (!this.$isNull(element.insuredName)) {
          let obj = {
            age: element.age,
            familyIdentifyNo: "",
            familyIdentifyType: "",
            familyName: element.insuredName,
            familyRelation: element.label,
            insuredBirthDate: "",
            insuredSheBaoType: "",
            mobile: ""
          };
          this.accidentalInjuryInfos.push(obj);
        }
      }
      let params = {
        formulaCode: this.formula.formulaCode, //方案代码
        formulaName: this.formula.formulaName, //方案名称
        sumAmount: this.sumAmount, //总保额
        formatSumAmount: this.sumAmount, //总保额
        sumPremium: this.totalPrice, //总保费
        formatSumPremium: this.totalPrice, //总保费
        securityNum: this.securityNum, //保障人数
        isAgent: isAgent, //是否代理
        agentInfo: JSON.stringify(this.agentInfo), //代理信息
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
        storeCode: this.storeCode
          ? this.storeCode
          : localStorage.getItem("storeCode"),
        routerName: "healthTellFamily",
        appliName: this.appliName,
        accidentalInjuryInfos: JSON.stringify(this.accidentalInjuryInfos),
        endDate: "", //结束时间
        startDate: "", //开始时间
        businessName:
          JSON.parse(localStorage.getItem("userInfo")).userName || "",
        businessMobile:
          JSON.parse(localStorage.getItem("userInfo")).mobile || ""
      };
      //保存数据
      this.savePageInfo();
      console.log("跳转计划书页面参数：" + JSON.stringify(params));
      this.$router.push({ name: "planBook", query: params });
    },
    goback() {
      this.$router.go(-1);
    },
    toggle(index) {
      this.$refs.checkboxes[index].checked = !this.$refs.checkboxes[index]
        .checked;
      this.updatePremiumsNum();
    },
    // 去分享海报页面
    goProductPoster() {
      console.log("海报地址：" + window.location.href);
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
            mobile: JSON.parse(localStorage.getItem("userInfo")).userPhone,
            isSharePage: "1"
          }
        }
      });
    },
    goMillionPlan() {
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
      let query = {
        agesArray: agesArray
      };
      this.$router.push({
        name: "millionPlanHome",
        query: query
      });
    },
    ensure_onConfirm(value, index) {
      this.ensurePeopleInfo.ensureTypeVal = value;
      this.securityNum = index + 2;
      this.ensurePeopleInfo.ensurePicker = false;
      this.initFormulaInfo();
      this.updatePremiumsNum();
      this.changeNum(this.securityNum);
    },
    getSumAmount() {
      this.sumAmount = parseInt(JSON.parse(this.formula.amountsJson)["JZH"]);
      // 循环result
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
    toVehicleChannel() {
      let query = {
        riskCode: "WTN"
      };
      this.$root.$once("channelChange", channel => {
        console.dir(channel);
        this.channelInfo = channel;
      });
      this.setChannel({ isProxy: this.isProxy ? "1" : "0" });
      console.log(111, this.channelInfo);
      this.$router.push({
        name: "vehicleChannel",
        query: query
      });
    }
  },
  components: {
    globalNav,
    agePicker,
    millionStatic
  }
};
</script>
<style lang="less" scoped>
.millionHome {
  // padding-top: 2rem;
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
  box-sizing: border-box;
  .productContent {
    background: rgba(36, 128, 234, 1);
  }
  .title {
    background: rgba(255, 255, 255, 1);
    display: flex;
    box-sizing: border-box;
    padding: 0.5rem 0.2rem;
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
    }
  }

  .row {
    font-size: 0.5rem;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
    background: url("../../../assets/nonCar/healthInsurance/bg1.png");
    background-repeat: repeat;
    background-size: 100%;
    // background-color: #2480ea;
    text-align: center;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    padding: 0.2rem 0;
    h5 {
      width: 100%;
      margin-bottom: 0.1rem;
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
    position: fixed;
    width: 100%;
    bottom: 0;
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
      flex: 1;
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
      }
    }
    .btnShare {
      width: 65.3%;
      button {
        width: 100%;
      }
      flex: 2;
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
