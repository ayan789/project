<template>
  <!-- 百万医疗个人版 -->
  <div class="millionPersonal">
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
    <div class="title">
      <div class="info">
        <h1 v-if="formula">{{ formula.formulaName }}</h1>
        <p>住院、门诊、交通多重保障</p>
      </div>
      <div
        class="price"
        v-if="formula"
      >{{ formula.premium }}元</div>
    </div>

    <div class="row">
      <h5>保障计划</h5>
      <p>全程指导，5步快速理赔</p>
    </div>
    <div class="productContent">
      <div class="card">
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
                <!-- <van-checkbox :name="item" v-model="checked">复选框</van-checkbox> -->
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
          <van-cell>
            <template>
              <van-tag type="primary">1</van-tag>
              <span class="custom-title">投保人</span>
            </template>
          </van-cell>
          <van-field
            v-model="appliName"
            label="投保人姓名"
            placeholder="请输入姓名"
            input-align="right"
            maxlength="50"
            :formatter="removeAllSpace"
          />
          <van-cell>
            <template>
              <van-tag type="primary">2</van-tag>
              <span class="custom-title">为谁投保</span>
            </template>
          </van-cell>
          <div class="insuredPerson">
            <van-field
              readonly
              clickable
              :value="relationInfo.typeVal"
              placeholder="请选择"
              @click="relationInfo.picker = true"
              input-align="right"
              class="after"
            >
              <div slot="label">
                <span>与投保人关系</span>
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
              v-model="relationInfo.picker"
              position="bottom"
            >
              <van-picker
                show-toolbar
                :columns="relationInfo.type"
                @cancel="relationInfo.picker = false"
                @confirm="card_confirm2"
              />
            </van-popup>
            <van-field
              v-model="insuredName"
              label="被保人姓名"
              placeholder="请输入姓名"
              input-align="right"
              maxlength="50"
              style="paddingRight:35px;"
              :formatter="removeAllSpace"
            ></van-field>
            <div
              class="img"
              @click="customerInformation"
            ></div>
          </div>
          <!-- <agePicker :num="1"  @changeAges="changeAges" /> -->
          <agePicker @changeAges="changeAges" />
          <van-cell>
            <template #title>
              <van-tag type="primary">3</van-tag>
              <span class="custom-title">是否有医保</span>
              <van-button
                style="float:right;"
                @click="btnChecked = true"
                plain
                :class="[btnChecked ? 'selected-button' : '', 'select-button']"
              >有医保</van-button>
              <van-button
                style="float:right;"
                @click="btnChecked = false"
                plain
                :class="[!btnChecked ? 'selected-button' : '', 'select-button']"
              >无医保</van-button>
            </template>
          </van-cell>
          <!-- btnChecked2 -->
          <!-- <div v-if="btnChecked2" @click="goVehicleInfo">
          <van-field name="insuredName" label="代理人" input-align="right" is-link />
          </div>-->
        </van-cell-group>
      </div>
      <millionStatic />
    </div>
    <div class="tabbar">
      <div class="price">
        <div>{{ formatSumPremium }}</div>
        <p>保费(元)</p>
      </div>
      <div class="btn">
        <van-button
          type="info"
          @click="enter"
        >生成计划书</van-button>
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

export default {
  data () {
    return {
      appliIdentity: "", //与投保人关系
      relationInfo: {
        //与投保人关系
        type: ["本人", "父母", "配偶", "子女"],
        picker: false,
        typeVal: ""
      },
      checked: false,
      medicalImg: require("@/assets/nonCar/healthInsurance/r.png"),
      current: 0, // 轮播索引
      isScroll: false,
      appliName: "", //投保人姓名
      insuredName: "", //被保人姓名
      queryData: "", //请求数据
      formula: "", //方案
      amountInfoJson: "", //一般医疗和附加责任
      amount1: [], //一般医疗循环数组
      infoJson2: [], //附加责任
      infoJsonPrice: [],
      totalPrice: 0, //保费
      resultPrice: 0, //附加保费
      ageOne: "0-4", //传递被保人年龄段
      agesArr: "", //年龄
      sumAmount: "", //总保额
      aKZEXClause: false,
      aEXTZClause: false,
      aEXYLClause: false,
      aEXZZClause: false,
      // 公共顶部
      globalNav: {
        title: "百万医疗2020个人版",
        isShare: true
      },
      // result: ['0','1','2','3'],
      result: [],
      ensurePeopleInfo: {
        // 保障人数
        ensuretype: ["2人", "3人", "4人", "5人", "6人", "7人"],
        ensurePicker: false,
        ensureTypeVal: "2人"
      },
      btnChecked: true, //社保
      isSheBao: "", // 是否有社保
      btnChecked2: false, //是否代理
      isAgent: "",
      agentInfo: {}, //代理信息
      params: "",
      isSheBao: "N",
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
      businessNatureCode: "",
      businessNatureName: "",
      infoObj: {}
    };
  },
  created () {
    //全局获取缓存数据
    this.$globalSetLocalStorage();
    this.params = this.$route.query;
    this.agentInfo = this.params.agentInfo;
    this.isAgent = this.params.isAgent;
    this.isSheBao = this.params.isSheBao;
    // this.btnChecked = this.isSheBao="Y"?true:false; //社保
    // this.btnChecked2 = this.isAgent="1"?true:false;
    this.businessNatureCode = this.params.businessNatureCode || "";
    this.businessNatureName = this.params.businessNatureName || "";
    this.$nextTick(() => {
      this.infoObj = this.$store.state.nonCar.infoObj;
      this.insuredName = this.infoObj.custName;
    });
    //   请求数据
    this.getQueryFormula({
      proCode: "FM50",
      storeCode: localStorage.getItem("storeCode")
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
        this.initFormulaInfo(); //初始化方案信息
        this.initSection1();
        this.updatePremiumsNum();
      })
      .catch(err => {
        Toast("请求失败");
      });
  },
  watch: {
    agesArr: {
      handler () {
        this.updatePremiumsNum();
      },
      deep: true
    },
    btnChecked () {
      if (this.btnChecked) {
        this.initFormulaInfo();
        this.updatePremiumsNum();
      } else {
        this.initFormulaInfo();
        this.updatePremiumsNum();
      }
    }
  },
  destroyed () {
    window.removeEventListener("scroll", this.scrollFunction(), true);
  },
  methods: {
    removeAllSpace (str) {
      return str.replace(/\s+/g, "");
    },
    // 投保人关系
    card_confirm2 (value, index) {
      this.relationInfo.typeVal = value;
      switch (index) {
        case 0:
          this.appliIdentity = "01";
          this.insuredName = this.appliName;
          this.checked2 = true;
          break;
        case 1:
          this.appliIdentity = "50";
          this.checked2 = false;
          break;
        case 2:
          this.appliIdentity = "10";
          this.checked2 = false;
          break;
        case 3:
          this.appliIdentity = "40";
          this.checked2 = false;

          break;
      }
      this.relationInfo.picker = false;
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
    onPlayerPause (player) {
      //监听暂停
      //console.log(player.duration());
      this.$emit("onPlayerPauseFun", player);
    },
    initFormulaInfo () {
      //初始化方案
      if (this.btnChecked) {
        this.formula = this.queryData.data.sfList[0];
      } else {
        this.formula = this.queryData.data.sfList[1];
      }
    },
    // 跳转到客户信息
    customerInformation () {
      this.$router.push({
        path: "/customer/myCustomer",
        query: { flag: "info" }
      });
    },
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
          this.infoJsonPrice.push({
            name: key.split("#")[1],
            subname: this.transfer(amountInfoJson[key])
          });
          console.log(this.infoJson2, "==?334");
        }
      }
    },

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
    transfer (amount) {
      if (amount < 10000) {
        amount = amount + "元";
      } else {
        amount = amount / 10000 + "万";
      }
      return amount;
    },
    enter () {
      if (this.$isNull(this.appliName)) {
        return Toast("请输入投保人姓名");
      }
      if (this.$isNull(this.insuredName)) {
        return Toast("请输入被保人姓名");
      }
      if (this.$isNull(this.appliIdentity)) {
        return Toast("请选择与投保人关系");
      }
      this.cleTenMillionData();

      this.getSumAmount();
      let params = {
        formulaCode: this.formula.formulaCode, //方案代码
        formulaName: this.formula.formulaName, //方案名称
        sumAmount: this.sumAmount, //总保额
        formatSumAmount: this.transfer(this.sumAmount),
        sumPremium: this.totalPrice, //总保费
        formatSumPremium: this.formatSumPremium,
        isAgent: this.isAgent || "0", //是否代理
        agentInfo: JSON.stringify(this.agentInfo), //代理信息
        aKZEXClause: this.aKZEXClause, //附加扩展恶性肿瘤特需医疗保险
        aEXTZClause: this.aEXTZClause, //附加个人恶性肿瘤院外特种药品费用医疗保险
        aEXYLClause: this.aEXYLClause, //附加个人恶性肿瘤赴日医疗保险
        aEXZZClause: this.aEXZZClause, //附加个人恶性肿瘤扩展质子重离子医疗保险
        isSheBao: this.btnChecked ? "Y" : "", //是否社保
        ages: this.ageOne, // 被保人年龄段
        appliName: this.appliName, //投保人姓名
        insuredName: this.insuredName, //被保人姓名
        infoJson2: this.infoJson2,
        amount1: this.amount1,
        infoJsonPrice: this.infoJsonPrice,
        businessMobile:
          JSON.parse(localStorage.getItem("userInfo")).userPhone || "",
        businessName:
          JSON.parse(localStorage.getItem("userInfo")).userName || "",
        storeCode: localStorage.getItem("storeCode") || "",
        businessNatureCode: this.businessNatureCode,
        businessNatureName: this.businessNatureName,
        certificateNo: this.infoObj.certificateNo,
        certificateType: this.infoObj.certificateType,
        custName: this.infoObj.custName,
        appliIdentity: this.appliIdentity //与投保人关系
      };

      //TODO suncao palnbook
      if (params.isAgent == "0" || this.$isNull(params.isAgent)) {
        params.businessNatureCode = this.businessNatureCode || "0101";
        params.businessNatureName =
          this.businessNatureName || "业务员销售（直销）";
      }
      // this.$router.push({ name: "millionInfo", query: params });
      // this.$router.push({ name: "planBook", query: params });

      this.$router.push({
        name: "planBook",
        query: { ...params, result: this.result }
      });
      // this.setFormula({ formula: this.formula, result: this.result }); // 选择方案
    },
    goback () {
      this.$router.go(-1);
    },

    // 去渠道代理页面
    goVehicleInfo () {
      this.$router.push({ name: "vehicleChannel" });
    },

    toggle (index) {
      this.$refs.checkboxes[index].checked = !this.$refs.checkboxes[index]
        .checked;
      this.updatePremiumsNum();
      this.$store.commit("nonCar/getresultList", this.result);
    },
    // 计算总保额
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

    ...mapActions("million", ["getQueryFormula"]),
    ...mapMutations("million", {
      setFormula: "SETFORMULA",
      cleTenMillionData: "CLETENMILLIONDATA"
    })
  },
  mounted () {
    this.result = this.$store.state.nonCar.resultList;
    // 监听滚动
    window.addEventListener("scroll", () => {
      this.scrollFunction();
    });
    setTimeout(() => {
      console.log("dynamic change options", this.player);
      // this.player.muted(false);
    }, 5000);
  },
  computed: {
    formatSumPremium () {
      return this.totalPrice
        .toFixed(2)
        .replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, "$&,");
    },
    player () {
      // return this.$refs.videoPlayer.player;
    },
    ...mapGetters("million", ["millionQuery"])
  },
  components: {
    globalNav,
    agePicker,
    millionStatic
  }
};
</script>
<style lang="less" scoped>
.millionPersonal {
  // padding-top: 2rem;
  box-sizing: border-box;
  height: max-content !important;
  // padding-bottom: 1rem;
  .swipeImg {
    width: 375px;
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
  .card {
    background: rgba(255, 255, 255, 1);
    border-radius: 0.2rem;
    box-sizing: border-box;
    margin: 0.2rem;
    margin-top: 0;
    overflow: hidden;
  }

  .tabbar {
    display: flex;
    position: fixed;
    width: 100%;
    bottom: 0;
    background-color: #fff;
    z-index: 1;
    .price {
      flex: 1;
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
      div {
        color: #fc820e;
        font-size: 0.4rem;
      }
    }

    .btn {
      flex: 1;
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
}
/deep/ .navigatorBottom {
  display: none;
}
.custom-title {
  padding-left: 0.2rem;
}

.checkbox {
  padding: 0.2rem;
  font-size: 0.37333rem;
}
/deep/ .van-icon-star-o::before {
  display: none;
}
.productContent {
  background: rgba(36, 128, 234, 1);
  padding-bottom: 1rem;
  padding-top: 10px;
}
/deep/ .insuredPerson .van-field__control {
  // padding-right: 30px !important;
}
.insuredPerson {
  position: relative;
  .img {
    width: 30px;
    height: 30px;
    position: absolute;
    background: url("../../../assets/nonCar/healthInsurance/insuredPerson.png")
      no-repeat;
    background-size: 30px 30px;
    right: 6px;
    // top: 8px;
    top: 52px;
  }
}
.millionPersonal .card1 {
  height: 316px;
  background: #ffffff;
  border-radius: 0.2rem;
  box-sizing: border-box;
  margin: 0.2rem;
  margin-top: 0;
  margin-bottom: 1.5rem;
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
</style>
