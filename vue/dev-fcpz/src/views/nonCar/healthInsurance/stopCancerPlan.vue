<template>
  <div class="guardianLife">
    <div
      class="header"
      ref="header"
    >
      <van-icon
        name="arrow-left"
        color="white"
        size="24px"
        class="back"
        ref="back"
        @click="goback"
      />
      <div
        class="text"
        ref="text"
      >“守护一生”中老年防癌险</div>
      <div class="shareDiv">
        <img
          src=""
          alt=""
        >
      </div>
    </div>
    <van-swipe :loop="false">
      <van-swipe-item>
        <div class="swipeImg">
          <img
            :src="medicalImg"
            alt
          />
        </div>
      </van-swipe-item>
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
    <!-- 标题 -->

    <div class="title">
      <div class="info">
        <h1>{{'“守护一生”' + '中老年防癌险' }}</h1>
        <p>住院、特殊门诊、交通多重保障</p>
      </div>
      <div class="price">
        {{ !formula.premium ? "0.00" : formula.premium }}
        <span>元起</span>
      </div>
    </div>

    <div class="row">
      <h5>保障计划</h5>
    </div>
    <!-- 保障内容card -->
    <div class="card">
      <van-cell-group>
        <van-cell
          title="个人恶性肿瘤医疗责任"
          value="200万"
        />
      </van-cell-group>
    </div>
    <!-- 保障期限，有无社保，期限 card  -->
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
        <!-- 被保人年龄-->
        <age-picker
          @changeAges="changeAges"
          ref="agePicker"
          lnfa="T"
        />
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
      </van-cell-group>
    </div>

    <div
      class="card"
      style="min-height:6rem"
    >
      <img
        :src="imgSrc[0]"
        class="lipei"
        style="width:100%;height: 100%;"
      />
    </div>
    <div
      class="card"
      style="min-height:6rem"
    >
      <img
        :src="imgSrc[1]"
        class="lipei"
        style="width:100%;height: 100%;"
      />
    </div>
    <div class="card">
      <img
        :src="imgSrc[2]"
        class="lipei"
        style="width:100%;height: 100%;"
      />
    </div>
    <div
      class="titleWhite"
      id="vantab2"
    >
      <h5 style="margin-bottom:14px;">常见问题</h5>
    </div>
    <div
      class="card"
      style="background:#fff;overflow:hidden;"
    >
      <div style="margin:0 20px 15px;">
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
      </div>
    </div>

    <!-- tabbar -->
    <div class="tabbar">
      <div class="price">
        保费
        <span style="color:#FC820E">{{formatSumPremium}}</span>元
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


import Config from "@src/common/json/newCoreDataDictionary";
import agePicker from "./agePicker";
import questionItem from "../common/question-item";

import { mapActions, mapGetters, mapMutations } from "vuex";
import { Toast } from "vant";
import { storeQueryFormula } from "@src/http/module/nonCar.js";

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
      appliName: "", //投保人姓名
      insuredName: "",
      show: false,
      checked2: false,
      shareIcon: require("@/assets/common/share.png"), //分享图片
      //底部保险条款
      protocols: [
        { name: "保险条款", link: "bxtk" },
        { name: "投保须知", link: "InsureNotice" },
        { name: "投保人声明", link: "InsureStatement" },
        { name: "特别约定", link: "Aboutpersonal" },
        // { name: "责任免赔", link: "liabilityExempt" }
      ],
      questionArr: [
        {
          question: "1、本产品主要针对哪些人群？",
          answer:
            "本产品是专为45-80周岁的中老年人设计的。针对一，超过60周岁不能购买天地关爱百万医疗产品的老年人；针对二，有除恶性肿瘤外的其他慢性疾病而不能购买天地关爱百万医疗产品的人群。"
        },
        {
          question: "2、我有医保了，还需要购买该产品吗？",
          answer:
            "非常有必要，以恶性肿瘤为例，治疗费用动辄几十万甚至上百万，医保只能覆盖60-70%，且很多疗效好，副作用低的自费药、进口药，医保都不能报销。而这些社保外费用都可通过本产品给予报销。"
        },
        {
          question: "3、本产品有哪些特点？",
          answer:
            "本产品为中老年人专属防癌险，即使对高血压、糖尿病等三高人群，都可以投保。1）45-80周岁均可投保，打破老年人健康险60周岁限制，续保最高可至100周岁；2）恶性肿瘤、原位癌100%赔付；3）质子重离子医疗也能赔；4）保单结束时还未结束治疗的，可以连续投保；"
        },
        {
          question:
            "4、本产品是否有无年免赔额？",
          answer:
            "本产品没有任何年免赔额，为了最大程度减轻被保险人患重疾后的财务负担，保险人就医保报销后剩余的合理部分费用100%报销，保额高达200万。"
        },
        {
          question: "5、如果理赔过，还可以续保吗？",
          answer:
            "该升级版老年防癌险允许上一年理赔过连续投保，前提是不存在未如实告知情况。100%赔付，不限医保范围，进口药自费药均可报销；100种重大疾病医疗无免赔报销。"
        }



      ],
      imgSrc: [
        require("@/assets/nonCar/healthInsurance/1.png"),
        require("@/assets/nonCar/healthInsurance/2.png"),
        require("@/assets/nonCar/healthInsurance/3.png")

      ],
      header: "", //头部
      text: "",//文字
      back: "",//返回
      medicalImg: require("@/assets/nonCar/healthInsurance/glBanner.png"), //轮播图
      current: 0, // 轮播索引
      btnChecked: true, //社保按钮
      isShebao: "", //是否有社保
      queryData: "", //请求数据
      formula: "", // 方案
      totalPrice: 0, // 总保费
      ageOne: "45", // 年龄段
      agesArr: "", //年龄段序号


      businessNatureCode: "",
      businessNatureName: "",
      infoObj: {}
    };
  },
  methods: {

    // 跳转到客户信息
    customerInformation () {
      this.$router.push({
        path: "/customer/myCustomer",
        query: { flag: "info" }
      });
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
    removeAllSpace (str) {
      return str.replace(/\s+/g, "");
    },

    goback () {
      document.getElementById('app').style.overflow = ""
      this.$router.go(-1)
    },

    opti () {
      const scrollTop =
        document.body.scrollTop || document.documentElement.scrollTop;
      const opcaity = scrollTop / 100 > 1 ? 1 : scrollTop / 100;
      this.header.style.background = "rgba(255,255,255," + opcaity + ")";
      this.text.style.color = "rgba(36,41,44," + opcaity + ")";
      if (scrollTop >= 30) {
        this.back.style.color = "rgba(36,41,44, 0.9)";
        this.shareIcon = require("@/assets/nonCar/propertyInsurance/shareBlack.png")
      } else {
        this.back.style.color = "rgba(255,255,255, 0.9)";
        this.shareIcon = require("@/assets/common/share.png")

      }
    },
    // 选择年龄
    changeAges (i, num, ageNum) {
      this.agesArr = i;
      this.ageOne = ageNum;
    },
    // 初始化方案信息
    initFormulaInfo () {
      if (this.btnChecked) {
        this.formula = this.queryData.data.sfList[0];
      } else {
        this.formula = this.queryData.data.sfList[1];
      }
    },

    // 初始化保费
    updatePremiumsNum () {
      this.totalPrice = parseInt(
        JSON.parse(this.formula.premiumsJson)["45"].BF
      );
      this.togglePrice(this.agesArr);
    },

    togglePrice (arg) {
      switch (arg) {
        case 1:
          this.totalPrice = this.totalPrice;
          break;
        case 2:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["46-50"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 3:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["51-55"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 4:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["56-60"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 5:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["61-65"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 6:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["66-70"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 7:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["71-75"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 8:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["76-80"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 9:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["81-85"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 10:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["86-90"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 11:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["91-95"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
        case 12:
          this.totalPrice +=
            parseInt(JSON.parse(this.formula.premiumsJson)["96-100"].BF) -
            parseInt(JSON.parse(this.formula.premiumsJson)["45"].BF);
          break;
      }
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
      this.cleNewData();
      let params = {
        formulaCode: this.formula.formulaCode,//方案代码
        formulaName: this.formula.formulaName, //方案名称
        sumAmount: this.sumAmount, //总保额
        sumPremium: this.totalPrice, //总保费
        formatSumPremium: this.formatSumPremium,
        isAgent: this.isAgent || "0", //是否代理
        agentInfo: JSON.stringify(this.agentInfo), //代理信息
        isSheBao: this.btnChecked ? "Y" : "", //是否社保
        ages: this.ageOne, // 被保人年龄段
        appliName: this.appliName, //投保人姓名
        insuredName: this.insuredName, //被保人姓名
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
        routerName: 'healthTellNew',
        appliIdentity: this.appliIdentity //与投保人关系
      }


      if (params.isAgent == "0" || this.$isNull(params.isAgent)) {
        params.businessNatureCode = this.businessNatureCode || "0101";
        params.businessNatureName =
          this.businessNatureName || "业务员销售（直销）";
      }
      this.$router.push({
        name: "planBook",
        query: { ...params }
      });



      // let isAgent = this.isAgent; //是否代理
      // if (isAgent == "1") {
      //   if (this.$isNull(this.channelInfo.agentName)) {
      //     this.$dialog.alert({
      //       message: "代理人信息不能为空"
      //     });
      //     return;
      //   }
      //   if (this.$isNull(this.channelInfo.businessNature)) {
      //     this.$dialog.alert({
      //       message: "代理人对应业务来源为空"
      //     });
      //     return;
      //   }
      // } else {
      //   //TODO suncao 非代理的也要判断业务来源
      //   if (this.$isNull(this.channelInfo.businessNatureCode)) {
      //     this.$dialog.alert({
      //       message: "业务来源不能为空"
      //     });
      //     return;
      //   }
      // }
      // let params = {
      //   storeCode: this.storeCode || localStorage.getItem("storeCode"),
      //   productCode: "WTI01",
      //   riskCode: "WTI",
      //   formulaCode: this.formula.formulaCode,
      //   formulaName: this.formula.formulaName,
      //   sumAmount: this.sumAmount,
      //   premium: "", //..
      //   insureNo: "", //..
      //   sumPremium: this.totalPrice, //总保费
      //   isSheBao: this.btnChecked ? "Y" : "W", //是否社保
      //   isAgent: isAgent, //是否代理
      //   agentInfo: this.agentInfo, //代理信息
      //   businessNatureCode: this.channelInfo.businessNatureCode || "",
      //   businessNatureName: this.channelInfo.businessNatureName || "",
      //   ages: this.ageOne // 被保人年龄段
      // };
      // this.setNewData(params);
      // this.setNewAgesJson({ premiumsJson: this.formula.premiumsJson })
      // document.getElementById('app').style.overflow = ""
      // this.$router.push({ name: "healthTellNew" });

    },
    backSave () {
      if (this.newData.ages != "") {
        this.$refs.agePicker.insuredAgesInfo2.insuredAgesTypeVal =
          this.newData.ages + "岁";
        this.ageOne = this.newData.ages;
      }
      this.agesArr = this.getIndex(this.newData.ages);
      if (this.newData.isSheBao == "Y") {
        this.btnChecked = true;
      } else {
        this.btnChecked = false;
      }
    },
    getIndex (aa) {
      switch (true) {
        case aa == "45":
          return 1;
          break;
        case aa == "46-50":
          return 2;
          break;
        case aa == "51-55":
          return 3;
          break;
        case aa == "56-60":
          return 4;
          break;
        case aa == "61-65":
          return 5;
          break;
        case aa == "66-70":
          return 6;
          break;
        case aa == "71-75":
          return 7;
          break;
        case aa == "76-80":
          return 8;
          break;
      }
    },
    ...mapActions("million", ["getQueryFormula"]),
    ...mapMutations("million", {
      setNewAgesJson: "SETNEWAGESJSON",
      setNewData: "SETNEWDATA",
      cleNewData: "CLENEWDATA"
    }),
    ...mapMutations("channel", ["setChannel"]),

  },
  beforeDestroy () {
    if (window) {
      window.removeEventListener("scroll", () => { });
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
  beforeRouteEnter: (to, from, next) => {
    if (from.name != "stopCancerForm") {
      localStorage.removeItem("newDataOrderNo");
      if (
        from.name === "bxtk" ||
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
    } else {
      if (
        from.name === "bxtk" ||
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
    }

  },
  beforeRouteLeave (to, from, next) {
    let fPosition =
      document.documentElement && document.documentElement.scrollTop;
    localStorage.setItem("fPosition", fPosition);
    next();
  },
  activated () {
    if (this.newData.ages == "") {
      this.$refs.agePicker.insuredAgesInfo2.insuredAgesTypeVal = "45岁"
      this.ageOne = "45"
    }
    document.getElementById('app').style.overflow = "visible"
    this.header = this.$refs.header;
    this.text = this.$refs.text;
    this.back = this.$refs.back;
    if (window) {
      window.addEventListener("scroll", () => {
        this.opti();
      });
    }
    if (localStorage.getItem("fPosition")) {
      this.timer = setTimeout(() => {
        let fPosition = localStorage.getItem("fPosition");
        window.scroll(0, fPosition);
      }, 30);
    }
    if (localStorage.getItem("isShouldClear") == "true") {
      localStorage.removeItem("fPosition");
    }
    // 回写数据
    this.backSave()
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

    // this.cleNewData()
    // 请求数据
    this.getQueryFormula({
      proCode: "WTI01",
      // storeCode: "GWznde8FEh3Fq2LdxFCK"
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
        this.initFormulaInfo(); //初始化方案信息
        // this.initSection1();
        this.updatePremiumsNum(); //初始化保费
      })
      .catch(err => {
        Toast("网络掉线了");
      });


  },
  computed: {
    formatSumPremium () {
      return this.totalPrice
        .toFixed(2)
        .replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, "$&,");
    },
    ...mapGetters("million", ["millionQuery", "newData"])
  },
  components: {
    agePicker,
    questionItem
  }
};
</script>

<style scoped lang="less">
.guardianLife {
  padding-bottom: 1.5rem;
  height: max-content !important;
  background: #2480ea;
  > .van-popup {
    width: 90%;
    border-radius: 10px;
  }
}
.van-swipe {
  background: #fff;
}
.header {
  width: 100%;
  position: fixed;
  padding: 0.3rem 0;
  text-align: center;
  display: flex;
  font-size: 16px;
  z-index: 101;
  .back {
    width: 10%;
  }
  .text {
    width: 80%;
    text-align: center;
    color: transparent;
  }
  .shareDiv {
    width: 10%;
    img {
      width: 24px;
      height: 24px;
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
  margin-top: -29px;
  // position: absolute;
  // bottom: 24%;
  // z-index: inherit;
  z-index: 100;
  position: relative;
  .span-left {
    margin-left: 14px;
  }
  .span-right {
    margin-right: 14px;
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
      margin-left: 10px;
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

.tabbar {
  position: fixed;
  -webkit-overflow-scroll: touch;
  bottom: 0;
  display: flex;
  width: 100%;
  .price {
    flex: 1;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #fff;
    font-size: 16px;

    span {
      color: #fc820e;
      font-size: 17px;
      margin: 0 11px;
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
    flex: 1;
    font-size: 16px;
    button {
      width: 100%;
    }
  }
}

.card {
  background: rgba(255, 255, 255, 1);
  border-radius: 0.2rem;
  box-sizing: border-box;
  margin: 0.2rem;
  overflow: hidden;
  // margin-top: 0;
  /deep/ .van-checkbox-group {
    box-sizing: border-box;
    margin: 0.2rem;
    overflow: hidden;
    border-radius: 0.2rem;

    .van-cell {
      background: rgba(247, 247, 247, 1);
    }
  }
  .card-title {
    font-weight: 600;
  }
}
.titleWhite {
  text-align: center;
  padding: 15px 0 5px 0;
  background: url("../../../assets/common/icon8.png") no-repeat;
  background-size: 100px 32px;
  background-position: 50% 22%;
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
.clause {
  width: 96%;
  height: 216px;
  background: rgba(255, 255, 255, 1);
  border-radius: 8px 0px 8px 8px;
  margin: 6px auto 0;
  position: relative;
  .blueDiv {
    width: 100%;
    height: 27px;
    background-image: url(../../../assets/nonCar/worryFree/noticeClause.png);
    background-position: 0 0;
    background-repeat: no-repeat;
    background-size: 106% 27px;
    position: relative;
  }
}
.spaceWhite {
  width: 18px;
  height: 36px;
  border-radius: 9px;
  background: #fff;
}

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

.custom-title {
  padding-left: 0.2rem;
}
</style>