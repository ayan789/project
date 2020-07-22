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
      <div
        class="shareDiv"
        @click="sharePage"
      >
        <img
          :src="shareIcon"
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
        <van-cell
          title="关系人信息"
          class="card-title"
        />
        <!-- 被保人年龄-->
        <age-picker
          @changeAges="changeAges"
          ref="agePicker"
          lnfa="T"
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

        <!-- 代理  -->
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
        <!-- 代理。 -->

        <van-cell
          title="份数(最多1份)"
          value="1份"
        />
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
    <div
      class="titleWhite"
      id="vantab3"
    >
      <h5>条款须知</h5>
    </div>
    <div class="clause">
      <div class="blueDiv">
        <div class="spaceWhite"></div>
        <div
          class="spaceWhite"
          style="position: absolute;top:0px;right:0px;"
        ></div>
        <van-cell-group style="top:-9px;">
          <van-cell
            :title="protocol.name"
            value
            is-link
            v-for="(protocol, index) in protocols"
            @click="goProtocols(protocol.link)"
            :key="index"
          />
        </van-cell-group>
      </div>
    </div>
    <!-- tabbar -->
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
        <h3>老年防癌</h3>
        <div class="divBtn">
          <div>
            <img
              src="@/assets/nonCar/healthInsurance/glBanner.png"
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
              src="@/assets/nonCar/healthInsurance/glBanner.png"
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
import {
  getDefaultChannel,
  getDefaultAgent,
  agentQuery
} from "@src/http/module/vehicle";
import { getShareUrl } from "@src/http/module/customerApi";

import Config from "@src/common/json/newCoreDataDictionary";
import agePicker from "./agePicker";
import questionItem from "../common/question-item";

import { mapActions, mapGetters, mapMutations } from "vuex";
import { Toast } from "vant";
import { storeQueryFormula } from "@src/http/module/nonCar.js";
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
  riskCode: "WTI"
};
export default {
  data () {
    return {
      show: false,

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
      // 代理字段
      isSharePage: "0",
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
      },
      channelInfo: channelInfoParam,

    };
  },
  methods: {
    // 代理切换
    changeIsProxy (type) {
      // 暂时注释
      if (type == "y") {
        this.isProxy = true;
        channelInfoParam.isProxy = "1";
        this.channelInfo.isProxy = "1";
        this.isAgent = "1";
        if(!agentInfoParam){
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
        riskCode: "WTI"
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
        "&proCode=WTI01&storeCode=" +
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
          title: "“守护一生”中老年防癌险", //分享标题
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


    // 去分享海报
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
    // 去制作计划书
    goMillionPlan () {
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
      this.$router.push({ name: "stopCancerPlan", query: params });
    },

    // 保险条款
    goProtocols (a) {
      this.$router.push({ name: a, params: { from: 'WTI01', isSheBao: this.btnChecked ? "Y" : "W" } });
      let subAges = this.$refs.agePicker.insuredAgesInfo2.insuredAgesTypeVal;
      this.setNewData({
        ages: subAges.substring(0, subAges.length - 1),
        isSheBao: this.btnChecked ? "Y" : "W"
      });
    },
    goback () {
      document.getElementById('app').style.overflow = ""
      this.$router.go(-1)
    },
    refh () {
      let subAges = this.$refs.agePicker.insuredAgesInfo2.insuredAgesTypeVal;
      this.setNewData({
        ages: subAges.substring(0, subAges.length - 1),
        isSheBao: this.btnChecked ? "Y" : "W"
      });
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
      let params = {
        storeCode: this.storeCode || localStorage.getItem("storeCode"),
        productCode: "WTI01",
        riskCode: "WTI",
        formulaCode: this.formula.formulaCode,
        formulaName: this.formula.formulaName,
        sumAmount: this.sumAmount,
        premium: "", //..
        insureNo: "", //..
        sumPremium: this.totalPrice, //总保费
        isSheBao: this.btnChecked ? "Y" : "W", //是否社保
        isAgent: isAgent, //是否代理
        agentInfo: this.agentInfo, //代理信息
        businessNatureCode: this.channelInfo.businessNatureCode || "",
        businessNatureName: this.channelInfo.businessNatureName || "",
        ages: this.ageOne // 被保人年龄段
      };
      this.setNewData(params);
      this.setNewAgesJson({ premiumsJson: this.formula.premiumsJson })
      document.getElementById('app').style.overflow = ""
      this.$router.push({ name: "healthTellNew" });

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
    console.log("channelInfo", this.channelInfo);
    this.$root.$once("channelChange", channel => {
      //TODO suncao取到渠道信息进行赋值,渲染不正确
      this.channelInfo = channel;
      channelInfoParam = channel;
      channelInfoParam.riskCode = "WTI";
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

    //初始是否代理：否
    if (this.isProxy) {
      this.changeIsProxy("y");
    } else {
      this.changeIsProxy("n");
    }
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
      height: 100%;
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
</style>