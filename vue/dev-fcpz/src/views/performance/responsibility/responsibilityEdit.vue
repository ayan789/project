<template>
  <div id="responsibility-edit">
    <div class="title-container" v-if="!isNoShare">
      <van-nav-bar
        title="责任书"
        @click-left="globalBack()"
        left-arrow
        right-icon="share"
        @click-right="sharePage"
      >
        <!-- 修改--添加 -->
        <!-- @click-left="$router.back()" -->
        <img
          :src="shareIcon"
          style="width: 22px;
        height: auto;
        vertical-align: middle;"
          slot="right"
        />
      </van-nav-bar>
    </div>
    <div class="content">
      <switch-tabs v-model="activeTab" :tabs="tabs" />
      <responsibility-input
        v-if="activeTab === '团队目标'"
        :activeTab="activeTab"
        :tabs="tabs"
        v-model="teamData"
        pre="team"
        key="团队目标"
        :displayType="displayType"
        ref="teamInput"
      />
      <responsibility-input
        v-else
        v-model="personalData"
        pre="personal"
        key="个人目标"
        :activeTab="activeTab"
        :tabs="tabs"
        :displayType="displayType"
        ref="personInput"
      />
    </div>
    <div class="card">
      <div class="card-title-container signature-pad-container">
        <div class="card-title-text">签名确认</div>
        <div class="card-title-right" @click="onSignaturePadClear">
          <van-icon name="replay" />
          <div class="card-title-right-name right-signature-pad">清除</div>
        </div>
      </div>
      <canvas
        id="canvasId"
        :ref="pre + 'sigCanvas'"
        class="signature-pad-canvas"
      ></canvas>
    </div>
    <div
      class="confirm-button"
      @click="isTeamLeader == 1 && !displayType ? submitMore() : submit()"
    >
      保存
    </div>
    <!-- tips -->
    <div class="tipPArt" v-if="warnTip !== ''">
      <van-icon name="warning" />
      <span>{{ warnTip }}</span>
    </div>
  </div>
</template>

<script>
import { getShareUrl } from "@src/http/module/customerApi.js";
import { NavBar, Toast, Notify } from "vant";
import SwitchTabs from "../common/switchTabs";
import ResponsibilityInput from "./responsibilityInput";
import SignaturePad from "signature_pad";
import {
  responsibilitySave,
  signatureUpload,
  firstListQuery
} from "@src/http/module/performance";
// import { display } from 'html2canvas/dist/types/css/property-descriptors/display';
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    [Notify.name]: Notify,
    [NavBar.name]: NavBar,
    [Toast.name]: Toast,
    [SwitchTabs.name]: SwitchTabs,
    [ResponsibilityInput.name]: ResponsibilityInput
  },
  data: () => ({
    userSignUrl: "",
    sharePoster: require("@src/assets/performance/promise.png"),
    isNoShare: false, // 是否显示分享栏
    shareIcon: require("@/assets/nonCar/common/share.png"),
    share: true,
    warnTip: "", // 提示
    displayType: false,
    tabs: "",
    activeTab: "",
    teamData: {},
    personalData: {},
    getListData: "",
    base64Img: "",
    signatureImgUrl: "",
    tparams: "",
    pparams: "",
    pre: "",
    teamCode: "",
    userCode: "",
    param: {},
    signaturePad: "",
    tListData: {},
    pListData: {},
    months: [
      "janTotalPremium",
      "febTotalPremium",
      "marTotalPremium",
      "aprTotalPremium",
      "mayTotalPremium",
      "junTotalPremium",
      "julTotalPremium",
      "augTotalPremium",
      "sepTotalPremium",
      "octTotalPremium",
      "novTotalPremium",
      "decTotalPremium"
    ],
    id: "",
    shareIcon: require("@/assets/nonCar/common/share.png")
  }),
  created() {
    let params = decodeURIComponent(this.$GetRequest("data", true));
    if (!this.$isNull(params)) {
      this.isNoShare = JSON.parse(params).isNoShare;
      this.id = JSON.parse(params).id;
      localStorage.setItem("token", JSON.parse(params).token);
    }
    if (this.$route.query.id) {
      this.id = this.$route.query.id;
    }
    let userInfo = JSON.parse(localStorage.getItem("userInfo"));
    this.isTeamLeader = userInfo.isTeamLeader;
    this.teamCode = userInfo.teamcode;
    this.userCode = userInfo.userCode;
    if (this.$route.query.data) {
      this.getListData = JSON.parse(this.$route.query.data);
      this.changeData();
    } else {
      let params = {
        userCode: this.userCode,
        isYear: new Date().getFullYear().toString(),
        isTeamLeader: this.isTeamLeader
      };
      if (this.isTeamLeader == 1) {
        params.teamCode = this.teamCode;
      }
      firstListQuery(params).then(response => {
        this.getListData = response.data;
        this.changeData();
      });
    }
    this.displayType =
      this.isTeamLeader === "1" &&
      this.tListData.signStatus == 0 &&
      this.pListData.signStatus == 1;
    if (this.isTeamLeader === "0") {
      this.tabs = ["个人目标"];
      this.activeTab = "个人目标";
    } else if (this.isTeamLeader === "1") {
      if (this.tListData.signStatus == 0 && this.pListData.signStatus == 1) {
        this.tabs = ["团队目标"];
        this.activeTab = "团队目标";
        let data = {
          carInsurePremium: "",
          accInsurePremium: "",
          heaInsurePremium: "",
          propInsurePremium: "",
          isAverage: ""
        };
        // this.personalData=this.pListData
        Object.keys(data).forEach(key => {
          data[key] = this.pListData[key];
        });
        this.months.forEach((item, index) => {
          data[item] = {};
          data[
            item
          ].monthTotalPremium = this.pListData.perfResponsibilityDetailList[
            index
          ].insurePremium;
          console.log(item);
        });
        this.personalData = data;
      } else {
        this.tabs = ["团队目标", "个人目标"];
        this.activeTab = "团队目标";
      }
    }
  },
  watch: {
    activeTab: function(value) {}
  },
  mounted() {
    let key = this.pre + "sigCanvas";
    let signaturePad = new SignaturePad(this.$refs[key], {
      backgroundColor: "transparent",
      penColor: "black"
    });
    var ratio = Math.max(window.devicePixelRatio || 1, 1);
    this.$refs[key].width = this.$refs[key].offsetWidth * ratio;
    this.$refs[key].height = this.$refs[key].offsetHeight * ratio;
    this.$refs[key].getContext("2d").scale(ratio, ratio);
    if (this.signaturePad) {
      signaturePad.fromData(this.signaturePad.toData());
    }
    this.signaturePad = signaturePad;
  },
  methods: {
    /* 修改--添加 */
    globalBack() {
      this.$globalBack();
    },
    convertImgToBase64(url, callback, outputFormat) {
      var canvas = document.createElement("CANVAS"),
        ctx = canvas.getContext("2d"),
        img = new Image();
      img.crossOrigin = "Anonymous";
      img.onload = function() {
        canvas.height = img.height;
        canvas.width = img.width;
        ctx.drawImage(img, 0, 0);
        var dataURL = canvas.toDataURL(outputFormat || "image/png");
        callback.call(this, dataURL);
        canvas = null;
      };
      img.src = url;
    },
    // 分享页面信息
    sharePage() {
      this.convertImgToBase64(this.sharePoster, base64Img => {
        //转化后的base64
        base64Img = base64Img.replace("data:image/png;base64,", "");
        this.sharePoster = base64Img;
        const jsonObject = {
          qrcodeURL: this.$rootPath + "/download",
          qrcodeHeaderImage: "",
          backImage: base64Img,
          sharePlatform: ["0", "1"],
          shareType: "img"
        };
        //调用壳方法，传递调用参数和接受响应数据
        this.$native.sharePosterAndqrcode(jsonObject, data => {
          // Toast("share 接口调用成功，壳响应数据:" + data);
        });
      });
    },
    // 隐藏提示
    hideTip() {
      const time = null;
      setTimeout(() => {
        clearTimeout(time);
        this.warnTip = "";
      }, 1000);
    },
    base64ToFile(urlData, fileName) {
      let arr = urlData.split(",");
      let mime = arr[0].match(/:(.*?);/)[1];
      let bytes = atob(arr[1]); // 解码base64
      let n = bytes.length;
      let ia = new Uint8Array(n);
      while (n--) {
        ia[n] = bytes.charCodeAt(n);
      }
      return new File([ia], fileName, { type: mime });
    },
    changeData() {
      if (this.getListData) {
        this.pListData = this.getListData.filter(
          item => item.responsibilityType === "P"
        )[0];
        this.tListData = this.getListData.filter(
          item => item.responsibilityType === "T"
        )[0];
      }
    },
    submitMore() {
      this.pparams = "";
      this.tparams = "";
      let canvas = document.getElementById("canvasId");
      let ctx = canvas.getContext("2d");
      ctx.fillStyle = "rgba(255,255,255,0)";
      ctx.fillRect(0, 100, 100, 100);
      this.base64Img = canvas.toDataURL();
      this.userSignUrl = this.getSignaturePadImageData();
      if (!this.userSignUrl) {
        this.warnTip = "请签名";
        this.hideTip();
        return false;
      }
      this.tData();
      if (this.pparams && this.tparams) {
        // this.pparams.userSign = this.base64Img;
        // this.tparams.userSign = this.base64Img;
        let img = "";
        img = this.base64ToFile(this.userSignUrl, this.getListData.id);
        img.src = canvas.toDataURL("image/png");
        let imgParams = new FormData();
        let fileName = new Date().getTime();
        imgParams.append("file", img);
        imgParams.append("fileName", fileName);
        signatureUpload(imgParams).then(res => {
          if (res.code === "1") {
            this.userSignUrl = res.data.path;
            this.pparams.userSign = this.userSignUrl;
            this.tparams.userSign = this.userSignUrl;
            Promise.all([
              this.submitTandP(this.tparams),
              this.submitTandP(this.pparams)
            ]).then(() => {
              this.$router.push({
                name: "responsibilityList"
              });
            });
          } else {
            Toast(res.msg);
          }
        });
        //   } else {
        //     Toast(response.msg, 3000);
        //   }
        // });
      }
    },
    // 提交团队
    submitTandP(params) {
      return new Promise((resolve, reject) => {
        responsibilitySave(params).then(
          data => {
            if (data.code === "1") {
              console.log("团队或个人提交成功");
              resolve();
            } else {
              Toast(data.msg, 3000);
            }
          },
          error => {}
        );
      });
    },
    submit() {
      let canvas = document.getElementById("canvasId");
      let ctx = canvas.getContext("2d");
      ctx.fillStyle = "rgba(255,255,255,0)";
      ctx.fillRect(0, 100, 100, 100);
      this.base64Img = canvas.toDataURL();
      let key = this.activeTab === "团队目标" ? "teamInput" : "personInput";
      this.userSignUrl = this.getSignaturePadImageData();
      if (!this.userSignUrl) {
        this.warnTip = "请签名";
        this.hideTip();
        return;
        // return Toast.fail("请签名");
      }
      if (this.displayType) {
        key = "teamInput";
      }
      if (this.activeTab == "个人目标" && !this.displayType) {
        this.teamData = this.personalData;
      }
      if (!this.teamData.carInsurePremium) {
        this.warnTip = "请输入车险保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入车险保费");
      }
      if (!this.teamData.accInsurePremium) {
        this.warnTip = "请输入意外险保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入意外险保费");
      }
      if (!this.teamData.heaInsurePremium) {
        this.warnTip = "请输入健康险保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入健康险保费");
      }
      if (!this.teamData.propInsurePremium) {
        this.warnTip = "请输入财产险保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入财产险保费");
      }
      if (!this.teamData.janTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入1月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入1月份保费");
      }
      if (!this.teamData.febTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入2月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入2月份保费");
      }
      if (!this.teamData.marTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入3月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入3月份保费");
      }
      if (!this.teamData.aprTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入4月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入4月份保费");
      }
      if (!this.teamData.mayTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入5月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入5月份保费");
      }
      if (!this.teamData.junTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入6月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入6月份保费");
      }
      if (!this.teamData.julTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入7月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入7月份保费");
      }
      if (!this.teamData.augTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入8月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入8月份保费");
      }
      if (!this.teamData.sepTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入9月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入9月份保费");
      }
      if (!this.teamData.octTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入10月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入10月份保费");
      }
      if (!this.teamData.novTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入11月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入11月份保费");
      }
      if (!this.teamData.decTotalPremium.monthTotalPremium) {
        this.warnTip = "请输入12月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入12月份保费");
      }
      let strMonth1 = this.teamData.janTotalPremium.monthTotalPremium;
      let numberMonth1 = Number(strMonth1);
      let strMonth2 = this.teamData.febTotalPremium.monthTotalPremium;
      let numberMonth2 = Number(strMonth2);
      let strMonth3 = this.teamData.marTotalPremium.monthTotalPremium;
      let numberMonth3 = Number(strMonth3);
      let strMonth4 = this.teamData.aprTotalPremium.monthTotalPremium;
      let numberMonth4 = Number(strMonth4);
      let strMonth5 = this.teamData.mayTotalPremium.monthTotalPremium;
      let numberMonth5 = Number(strMonth5);
      let strMonth6 = this.teamData.junTotalPremium.monthTotalPremium;
      let numberMonth6 = Number(strMonth6);
      let strMonth7 = this.teamData.julTotalPremium.monthTotalPremium;
      let numberMonth7 = Number(strMonth7);
      let strMonth8 = this.teamData.augTotalPremium.monthTotalPremium;
      let numberMonth8 = Number(strMonth8);
      let strMonth9 = this.teamData.sepTotalPremium.monthTotalPremium;
      let numberMonth9 = Number(strMonth9);
      let strMonth10 = this.teamData.octTotalPremium.monthTotalPremium;
      let numberMonth10 = Number(strMonth10);
      let strMonth11 = this.teamData.novTotalPremium.monthTotalPremium;
      let numberMonth11 = Number(strMonth11);
      let strMonth12 = this.teamData.decTotalPremium.monthTotalPremium;
      let numberMonth12 = Number(strMonth12);
      //TODO
      let str1 = this.teamData.carInsurePremium; //车险保费
      let number1 = Number(str1);
      let str2 = this.teamData.accInsurePremium; //意外险保费
      let number2 = Number(str2);
      let str3 = this.teamData.heaInsurePremium; //健康险保费
      let number3 = Number(str3);
      let str4 = this.teamData.propInsurePremium; //财产险保费
      let number4 = Number(str4);
      let allNumber4 = number1 + number2 + number3 + number4; //4个总计
      let allNumber12 =
        numberMonth1 +
        numberMonth2 +
        numberMonth3 +
        numberMonth4 +
        numberMonth5 +
        numberMonth6 +
        numberMonth7 +
        numberMonth8 +
        numberMonth9 +
        numberMonth10 +
        numberMonth11 +
        numberMonth12; //12个总计
      if (allNumber4 != allNumber12) {
        if (allNumber4 > allNumber12) {
          let cha = allNumber4 - allNumber12;
          this.warnTip =
            "各月度保费累加值比年度总保费少" + cha + "元，请调整一致";
          this.hideTip();
          // Toast.fail("各月度保费累加值比年度总保费少" + cha + "元，请调整一致");
        } else {
          let cha = allNumber12 - allNumber4;
          this.warnTip =
            "各月度保费累加值比年度总保费多" + cha + "元，请调整一致";
          this.hideTip();
          // Toast.fail("各月度保费累加值比年度总保费多" + cha + "元，请调整一致");
        }
        return false;
      }
      this.perfResponsibilityDetailList = [];
      for (let key in this.teamData) {
        console.log(this.teamData[key], "this.teamData[key]");
        if (
          !this.$isNull(this.teamData[key]) &&
          !this.$isNull(this.teamData[key].hasOwnProperty("monthTotalPremium"))
        ) {
          let obj = {};
          console.log(key, "key");
          if (key == "janTotalPremium") {
            obj.monthStep = "1"; //月份
            obj.quarterStep = "1"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "febTotalPremium") {
            obj.monthStep = "2"; //月份
            obj.quarterStep = "1"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "marTotalPremium") {
            obj.monthStep = "3"; //月份
            obj.quarterStep = "1"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "aprTotalPremium") {
            obj.monthStep = "4"; //月份
            obj.quarterStep = "2"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "mayTotalPremium") {
            obj.monthStep = "5"; //月份
            obj.quarterStep = "2"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "junTotalPremium") {
            obj.monthStep = "6"; //月份
            obj.quarterStep = "2"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "julTotalPremium") {
            obj.monthStep = "7"; //月份
            obj.quarterStep = "3"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "augTotalPremium") {
            obj.monthStep = "8"; //月份
            obj.quarterStep = "3"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "sepTotalPremium") {
            obj.monthStep = "9"; //月份
            obj.quarterStep = "3"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "octTotalPremium") {
            obj.monthStep = "10"; //月份
            obj.quarterStep = "4"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "novTotalPremium") {
            obj.monthStep = "11"; //月份
            obj.quarterStep = "4"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "decTotalPremium") {
            obj.monthStep = "12"; //月份
            obj.quarterStep = "4"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          }
          this.perfResponsibilityDetailList.push(obj);
        }
      }
      // let params = Object.assign(
      //   {
      //     userCode: '8000154962',
      //     responsibilityName: '责任书名称',
      //     responsibilityNote: '责任书内容'
      //     // leaderSignUrl: "",
      //     // userSignUrl
      //     //other params?
      //   },
      //   this.teamData
      // )
      let numAll =
        Number(this.teamData.accInsurePremium) +
        Number(this.teamData.carInsurePremium) +
        Number(this.teamData.heaInsurePremium) +
        Number(this.teamData.propInsurePremium);
      if (this.activeTab == "团队目标") {
        this.activeTab = "T";
      } else {
        this.activeTab = "P";
      }
      let params = {
        id: this.displayType ? this.tListData.id : this.pListData.id,
        perfResponsibilityDetailList: this.perfResponsibilityDetailList,
        responsibilityName: this.displayType
          ? this.tListData.responsibilityName
          : this.pListData.responsibilityName,
        responsibilityNote: this.displayType
          ? this.tListData.responsibilityNote
          : this.pListData.responsibilityNote, //责任书内容
        // signTime: Date.parse(new Date().getFullYear()),
        isYear: this.pListData.isYear,
        responsibilityType: this.displayType ? "T" : this.activeTab, //责任书类型：个人:
        multiple: this.displayType
          ? this.tListData.multiple
          : this.pListData.multiple,
        // userSign: this.base64Img, //签名图片 base64
        userSign: this.userSignUrl, //签名图片 base64
        teamCode: this.teamCode, //团队编号
        userCode: this.userCode, //业务员编号
        accInsurePremium: this.teamData.accInsurePremium, //意外险保费 年
        carInsurePremium: this.teamData.carInsurePremium, //车险保费 年
        heaInsurePremium: this.teamData.heaInsurePremium, //健康险保费 年
        propInsurePremium: this.teamData.propInsurePremium, //财产险保费 年
        insurePremium: numAll, //年总保费,
        automaticFlag: Number(this.teamData.isAverage) // 自动分配ture 否 false
      };
      // Object.keys(this.teamData).map(item => {
      //   if (typeof params[item] === 'object')
      //     params[item] = JSON.stringify(params[item])
      // })
      Toast.loading();
      // let img = "";
      // img = this.base64ToFile(this.base64Img, this.getListData.id);
      // img.src = canvas.toDataURL("image/png");
      // let imgParams = new FormData();
      // let fileName = new Date().getTime();
      // imgParams.append("file", img);
      // imgParams.append("fileName", fileName);
      // signatureUpload(imgParams).then(response => {
      //   if (response.code === "1") {
      //     this.signatureImgUrl = response.data;
      // params.userSign = this.base64Img;
      let img = "";
      img = this.base64ToFile(this.userSignUrl, this.getListData.id);
      img.src = canvas.toDataURL("image/png");
      let imgParams = new FormData();
      let fileName = new Date().getTime();
      imgParams.append("file", img);
      imgParams.append("fileName", fileName);
      console.log(imgParams);
      signatureUpload(imgParams).then(res => {
        if (res.code === "1") {
          this.userSignUrl = res.data.path;
          params.userSign = this.userSignUrl;
          responsibilitySave(params)
            .then(
              data => {
                if (data.code === "1") {
                  //埋点
                  trackMap(
                    "c_performance_responsibility_letter_target_change",
                    "业绩责任书目标修改",
                    {
                      c_salesperson_type: "N",
                      c_market_type: "Y",
                      c_market_name: "",
                      c_market_id: "",
                      c_event_category: "业绩"
                    }
                  );
                  this.$router.push({
                    name: "responsibilityList"
                  });
                } else {
                  Toast(data.msg, 3000);
                }
              },
              error => {}
            )
            .finally(() => {
              Toast.clear();
            });
        } else {
          Toast(res.msg);
        }
      });

      // } else {
      //   Toast(response.msg, 3000);
      // }
      // });
    },
    // 团队
    tData() {
      let key = this.activeTab === "团队目标" ? "teamInput" : "personInput";
      if (!this.teamData.carInsurePremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入车险保费");
      }
      if (!this.teamData.accInsurePremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入意外险保费");
      }
      if (!this.teamData.heaInsurePremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入健康险保费");
      }
      if (!this.teamData.propInsurePremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入财产险保费");
      }
      if (!this.teamData.janTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入1月份保费");
      }
      if (!this.teamData.febTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入2月份保费");
      }
      if (!this.teamData.marTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入3月份保费");
      }
      if (!this.teamData.aprTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入4月份保费");
      }
      if (!this.teamData.mayTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入5月份保费");
      }
      if (!this.teamData.junTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入6月份保费");
      }
      if (!this.teamData.julTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入7月份保费");
      }
      if (!this.teamData.augTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入8月份保费");
      }
      if (!this.teamData.sepTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入9月份保费");
      }
      if (!this.teamData.octTotalPremium.monthTotalPremium) {
        return Toast.fail("请输入10月份保费");
        this.activeTab = "团队目标";
      }
      if (!this.teamData.novTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入11月份保费");
      }
      if (!this.teamData.decTotalPremium.monthTotalPremium) {
        this.activeTab = "团队目标";
        return Toast.fail("请输入12月份保费");
      }
      let strMonth1 = this.teamData.janTotalPremium.monthTotalPremium;
      let numberMonth1 = Number(strMonth1);
      let strMonth2 = this.teamData.febTotalPremium.monthTotalPremium;
      let numberMonth2 = Number(strMonth2);
      let strMonth3 = this.teamData.marTotalPremium.monthTotalPremium;
      let numberMonth3 = Number(strMonth3);
      let strMonth4 = this.teamData.aprTotalPremium.monthTotalPremium;
      let numberMonth4 = Number(strMonth4);
      let strMonth5 = this.teamData.mayTotalPremium.monthTotalPremium;
      let numberMonth5 = Number(strMonth5);
      let strMonth6 = this.teamData.junTotalPremium.monthTotalPremium;
      let numberMonth6 = Number(strMonth6);
      let strMonth7 = this.teamData.julTotalPremium.monthTotalPremium;
      let numberMonth7 = Number(strMonth7);
      let strMonth8 = this.teamData.augTotalPremium.monthTotalPremium;
      let numberMonth8 = Number(strMonth8);
      let strMonth9 = this.teamData.sepTotalPremium.monthTotalPremium;
      let numberMonth9 = Number(strMonth9);
      let strMonth10 = this.teamData.octTotalPremium.monthTotalPremium;
      let numberMonth10 = Number(strMonth10);
      let strMonth11 = this.teamData.novTotalPremium.monthTotalPremium;
      let numberMonth11 = Number(strMonth11);
      let strMonth12 = this.teamData.decTotalPremium.monthTotalPremium;
      let numberMonth12 = Number(strMonth12);
      //TODO
      let str1 = this.teamData.carInsurePremium; //车险保费
      let number1 = Number(str1);
      let str2 = this.teamData.accInsurePremium; //意外险保费
      let number2 = Number(str2);
      let str3 = this.teamData.heaInsurePremium; //健康险保费
      let number3 = Number(str3);
      let str4 = this.teamData.propInsurePremium; //财产险保费
      let number4 = Number(str4);
      let allNumber4 = number1 + number2 + number3 + number4; //4个总计
      let allNumber12 =
        numberMonth1 +
        numberMonth2 +
        numberMonth3 +
        numberMonth4 +
        numberMonth5 +
        numberMonth6 +
        numberMonth7 +
        numberMonth8 +
        numberMonth9 +
        numberMonth10 +
        numberMonth11 +
        numberMonth12; //12个总计
      if (allNumber4 != allNumber12) {
        if (allNumber4 > allNumber12) {
          let cha = allNumber4 - allNumber12;
          this.activeTab = "团队目标";
          Toast.fail("各月度保费累加值比年度总保费少" + cha + "元，请调整一致");
        } else {
          let cha = allNumber12 - allNumber4;
          this.activeTab = "团队目标";
          Toast.fail("各月度保费累加值比年度总保费多" + cha + "元，请调整一致");
        }
        return false;
      }
      this.perfResponsibilityDetailList = [];
      for (let key in this.teamData) {
        if (
          !this.$isNull(this.teamData[key]) &&
          !this.$isNull(this.teamData[key].hasOwnProperty("monthTotalPremium"))
        ) {
          let obj = {};
          if (key == "janTotalPremium") {
            obj.monthStep = "1"; //月份
            obj.quarterStep = "1"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "febTotalPremium") {
            obj.monthStep = "2"; //月份
            obj.quarterStep = "1"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "marTotalPremium") {
            obj.monthStep = "3"; //月份
            obj.quarterStep = "1"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "aprTotalPremium") {
            obj.monthStep = "4"; //月份
            obj.quarterStep = "2"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "mayTotalPremium") {
            obj.monthStep = "5"; //月份
            obj.quarterStep = "2"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "junTotalPremium") {
            obj.monthStep = "6"; //月份
            obj.quarterStep = "2"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "julTotalPremium") {
            obj.monthStep = "7"; //月份
            obj.quarterStep = "3"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "augTotalPremium") {
            obj.monthStep = "8"; //月份
            obj.quarterStep = "3"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "sepTotalPremium") {
            obj.monthStep = "9"; //月份
            obj.quarterStep = "3"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "octTotalPremium") {
            obj.monthStep = "10"; //月份
            obj.quarterStep = "4"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "novTotalPremium") {
            obj.monthStep = "11"; //月份
            obj.quarterStep = "4"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "decTotalPremium") {
            obj.monthStep = "12"; //月份
            obj.quarterStep = "4"; //季度
            obj.insurePremium = this.teamData[key].monthTotalPremium; //月总数
            let str = this.teamData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          }
          this.perfResponsibilityDetailList.push(obj);
        }
      }
      let numAll =
        Number(this.teamData.accInsurePremium) +
        Number(this.teamData.carInsurePremium) +
        Number(this.teamData.heaInsurePremium) +
        Number(this.teamData.propInsurePremium);
      this.tparams = {
        /* 修改--添加 */
        changeStatus: "0",
        id: this.tListData.id,
        perfResponsibilityDetailList: this.perfResponsibilityDetailList,
        responsibilityName: this.tListData.responsibilityName,
        responsibilityNote: this.tListData.responsibilityNote, //责任书内容
        // signTime: Date.parse(new Date().getFullYear()),
        isYear: this.tListData.isYear,
        responsibilityType: "T", //责任书类型：团队:
        multiple: this.tListData.multiple,
        // userSign: this.base64Img, //签名图片 base64
        userSign: this.userSignUrl, //签名图片 base64
        teamCode: this.teamCode, //团队编号
        userCode: this.userCode, //业务员编号
        accInsurePremium: this.teamData.accInsurePremium, //意外险保费 年
        carInsurePremium: this.teamData.carInsurePremium, //车险保费 年
        heaInsurePremium: this.teamData.heaInsurePremium, //健康险保费 年
        propInsurePremium: this.teamData.propInsurePremium, //财产险保费 年
        insurePremium: numAll, //年总保费,
        automaticFlag: Number(this.teamData.isAverage) // 自动分配ture 否 false
      };
      // this.pDataStatus = 1;
      this.pData();
    },
    // 个人
    pData() {
      let key = this.activeTab === "团队目标" ? "teamInput" : "personInput";
      if (!this.personalData.carInsurePremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入车险保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入车险保费");
      }
      if (!this.personalData.accInsurePremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入意外险保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入意外险保费");
      }
      if (!this.personalData.heaInsurePremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入健康险保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入健康险保费");
      }
      if (!this.personalData.propInsurePremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入财产险保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入财产险保费");
      }
      if (!this.personalData.janTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入1月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入1月份保费");
      }
      if (!this.personalData.febTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入2月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入2月份保费");
      }
      if (!this.personalData.marTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入3月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入3月份保费");
      }
      if (!this.personalData.aprTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入4月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入4月份保费");
      }
      if (!this.personalData.mayTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入5月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入5月份保费");
      }
      if (!this.personalData.junTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入6月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入6月份保费");
      }
      if (!this.personalData.julTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入7月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入7月份保费");
      }
      if (!this.personalData.augTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入8月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入8月份保费");
      }
      if (!this.personalData.sepTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入9月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入9月份保费");
      }
      if (!this.personalData.octTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入10月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入10月份保费");
      }
      if (!this.personalData.novTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入11月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入11月份保费");
      }
      if (!this.personalData.decTotalPremium.monthTotalPremium) {
        this.activeTab = "个人目标";
        this.warnTip = "请输入12月份保费";
        this.hideTip();
        return;
        // return Toast.fail("请输入12月份保费");
      }
      let strMonth1 = this.personalData.janTotalPremium.monthTotalPremium;
      let numberMonth1 = Number(strMonth1);
      let strMonth2 = this.personalData.febTotalPremium.monthTotalPremium;
      let numberMonth2 = Number(strMonth2);
      let strMonth3 = this.personalData.marTotalPremium.monthTotalPremium;
      let numberMonth3 = Number(strMonth3);
      let strMonth4 = this.personalData.aprTotalPremium.monthTotalPremium;
      let numberMonth4 = Number(strMonth4);
      let strMonth5 = this.personalData.mayTotalPremium.monthTotalPremium;
      let numberMonth5 = Number(strMonth5);
      let strMonth6 = this.personalData.junTotalPremium.monthTotalPremium;
      let numberMonth6 = Number(strMonth6);
      let strMonth7 = this.personalData.julTotalPremium.monthTotalPremium;
      let numberMonth7 = Number(strMonth7);
      let strMonth8 = this.personalData.augTotalPremium.monthTotalPremium;
      let numberMonth8 = Number(strMonth8);
      let strMonth9 = this.personalData.sepTotalPremium.monthTotalPremium;
      let numberMonth9 = Number(strMonth9);
      let strMonth10 = this.personalData.octTotalPremium.monthTotalPremium;
      let numberMonth10 = Number(strMonth10);
      let strMonth11 = this.personalData.novTotalPremium.monthTotalPremium;
      let numberMonth11 = Number(strMonth11);
      let strMonth12 = this.personalData.decTotalPremium.monthTotalPremium;
      let numberMonth12 = Number(strMonth12);
      //TODO
      let str1 = this.personalData.carInsurePremium; //车险保费
      let number1 = Number(str1);
      let str2 = this.personalData.accInsurePremium; //意外险保费
      let number2 = Number(str2);
      let str3 = this.personalData.heaInsurePremium; //健康险保费
      let number3 = Number(str3);
      let str4 = this.personalData.propInsurePremium; //财产险保费
      let number4 = Number(str4);
      let allNumber4 = number1 + number2 + number3 + number4; //4个总计
      let allNumber12 =
        numberMonth1 +
        numberMonth2 +
        numberMonth3 +
        numberMonth4 +
        numberMonth5 +
        numberMonth6 +
        numberMonth7 +
        numberMonth8 +
        numberMonth9 +
        numberMonth10 +
        numberMonth11 +
        numberMonth12; //12个总计
      if (allNumber4 != allNumber12) {
        if (allNumber4 > allNumber12) {
          let cha = allNumber4 - allNumber12;
          this.activeTab = "个人目标";
          this.warnTip =
            "各月度保费累加值比年度总保费少" + cha + "元，请调整一致";
          this.hideTip();
          // Toast.fail("各月度保费累加值比年度总保费少" + cha + "元，请调整一致");
        } else {
          let cha = allNumber12 - allNumber4;
          this.activeTab = "个人目标";
          this.warnTip =
            "各月度保费累加值比年度总保费多" + cha + "元，请调整一致";
          this.hideTip();
          // Toast.fail("各月度保费累加值比年度总保费多" + cha + "元，请调整一致");
        }
        return false;
      }
      this.perfResponsibilityDetailList = [];
      for (let key in this.personalData) {
        if (
          !this.$isNull(this.personalData[key]) &&
          !this.$isNull(
            this.personalData[key].hasOwnProperty("monthTotalPremium")
          )
        ) {
          let obj = {};
          if (key == "janTotalPremium") {
            obj.monthStep = "1"; //月份
            obj.quarterStep = "1"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "febTotalPremium") {
            obj.monthStep = "2"; //月份
            obj.quarterStep = "1"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "marTotalPremium") {
            obj.monthStep = "3"; //月份
            obj.quarterStep = "1"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "aprTotalPremium") {
            obj.monthStep = "4"; //月份
            obj.quarterStep = "2"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "mayTotalPremium") {
            obj.monthStep = "5"; //月份
            obj.quarterStep = "2"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "junTotalPremium") {
            obj.monthStep = "6"; //月份
            obj.quarterStep = "2"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "julTotalPremium") {
            obj.monthStep = "7"; //月份
            obj.quarterStep = "3"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "augTotalPremium") {
            obj.monthStep = "8"; //月份
            obj.quarterStep = "3"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "sepTotalPremium") {
            obj.monthStep = "9"; //月份
            obj.quarterStep = "3"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "octTotalPremium") {
            obj.monthStep = "10"; //月份
            obj.quarterStep = "4"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "novTotalPremium") {
            obj.monthStep = "11"; //月份
            obj.quarterStep = "4"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          } else if (key == "decTotalPremium") {
            obj.monthStep = "12"; //月份
            obj.quarterStep = "4"; //季度
            obj.insurePremium = this.personalData[key].monthTotalPremium; //月总数
            let str = this.personalData[key].monthTotalPremium;
            let num = Number(str);
            let carIn = (num / allNumber12) * number1; //车险
            obj.carInsurePremium = carIn;
            let accIn = (num / allNumber12) * number2; //意外险
            obj.accInsurePremium = accIn;
            let heaIn = (num / allNumber12) * number3; //健康险
            obj.heaInsurePremium = heaIn;
            let propIn = (num / allNumber12) * number4; //财产险
            obj.propInsurePremium = propIn;
          }
          this.perfResponsibilityDetailList.push(obj);
          // this.tData();
        }
      }
      let numAll =
        Number(this.personalData.accInsurePremium) +
        Number(this.personalData.carInsurePremium) +
        Number(this.personalData.heaInsurePremium) +
        Number(this.personalData.propInsurePremium);
      this.pparams = {
        /* 修改--添加 */
        changeStatus: "0",
        id: this.pListData.id,
        perfResponsibilityDetailList: this.perfResponsibilityDetailList,
        responsibilityName: this.pListData.responsibilityName,
        responsibilityNote: this.pListData.responsibilityNote, //责任书内容
        // signTime: Date.parse(new Date().getFullYear()),
        isYear: this.pListData.isYear,
        responsibilityType: "P", //责任书类型：个人:
        multiple: this.pListData.multiple,
        // userSign: this.base64Img, //签名图片 base64
        userSign: this.userSignUrl, //签名图片 base64
        teamCode: "", //团队编号
        userCode: this.userCode, //业务员编号
        accInsurePremium: this.personalData.accInsurePremium, //意外险保费 年
        carInsurePremium: this.personalData.carInsurePremium, //车险保费 年
        heaInsurePremium: this.personalData.heaInsurePremium, //健康险保费 年
        propInsurePremium: this.personalData.propInsurePremium, //财产险保费 年
        insurePremium: numAll, //年总保费,
        automaticFlag: Number(this.personalData.isAverage) // 自动分配ture 否 false
      };
      // this.tData();
      // this.tDataStatus = 1;
    },
    onSignaturePadClear() {
      this.signaturePad.clear();
    },
    getSignaturePadImageData() {
      if (this.signaturePad.isEmpty()) {
        return null;
      }
      return this.signaturePad.toDataURL("image/png");
    }
  }
};
</script>

<style lang="less" scoped>
#responsibility-edit {
  background: #f7f7f7;
  flex-direction: column;
  .tipPArt {
    transition-duration: 0.2s;
    position: fixed;
    bottom: 90px;
    background-color: #ffe8ca;
    font-size: 13px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgb(119, 81, 46);
    line-height: 19px;
    width: 100%;
    padding: 7.5px 0;
    animation: showNoti 1s 1;
    text-align: center;
    span {
      margin-left: 6px;
    }
  }
  @keyframes showNoti {
    from {
      display: block;
    }
    to {
      display: none;
    }
  }
  .title-container {
    background: linear-gradient(
      129deg,
      rgb(30, 70, 246) 0%,
      rgb(39, 154, 255) 100%
    );
    .van-nav-bar {
      background: none;
    }
    .van-nav-bar__title {
      color: #fff !important;
    }
    img {
      width: 22px;
      height: auto;
      vertical-align: middle;
    }
  }
  .content {
    padding: 10px 0;
  }
}

.confirm-button {
  display: flex;
  flex: 1;
  justify-content: center;
  align-items: center;
  margin: 24px 15px;
  height: 46px;
  border-radius: 23px;
  background: #3b8afe;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: white;
  line-height: 22px;
}
.card {
  margin: 0 15px 12px;
  padding: 10px 17px 25px 20px;
  background: white;
  border-radius: 10px;
  div {
    display: flex;
    align-items: center;
  }
  .card-title-container {
    padding-bottom: 8px;
    border-bottom: 1px solid #66666616;
    justify-content: space-between;
  }
  .signature-pad-container {
    border-bottom: 0;
  }
  .card-title-text {
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: #333333;
    line-height: 25px;
  }
  .card-title-right {
    // align-items: center;
  }
  .card-title-right-name {
    margin-right: 5px;
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
  }
  .right-month {
    color: #333333;
  }
  .right-signature-pad {
    color: #8e8e8e;
  }
  .card-title-right-value {
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: #3b8afe;
  }
  .card-items-container {
    width: 100%;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  .card-item-container {
    width: 141px;
    padding-top: 15px;
    flex-direction: column;
    align-items: flex-start;
  }
  .item-month {
    flex-direction: row;
    justify-content: space-between;
  }
  .card-item-title {
    margin-bottom: 7px;
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
    line-height: 12px;
  }
  .title-month {
    margin-bottom: 0;
    font-size: 14px;
    color: #3c69ab;
    align-self: center;
  }
  .card-item-input {
    width: 141px;
    height: 38px;
    border: 1px solid #dddddd;
    background: white;
    text-align: center;
    font-size: 14px;
    font-family: MicrosoftYaHei;
    color: #575756;
  }
  .input-month {
    width: 100px;
    height: 36px;
  }
  .signature-pad-canvas {
    width: 100%;
    height: 200px;
  }
}
</style>
