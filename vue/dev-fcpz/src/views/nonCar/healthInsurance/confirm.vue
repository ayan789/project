<template>
  <!-- 投保确认 -->
  <div class="insuranceinfoConfirm">
    <globalNav
      :title="globalNav.title"
      @clickBack="goback"
      :isHome="isHome"
      @clickRight="clickRight"
    />

    <div class="card">
      <van-cell-group>
        <div class="title-container">
          <div class="topLeft">
            <img
              src="@/assets/nonCar/propertyInsurance/infoInput.png"
              alt="header"
            />
          </div>
          <div class="topRight">
            <div class="title">{{ f.formulaName }}</div>
            <div class="time">保障期限: {{ f.startDate }}至{{ f.endDate }}</div>
          </div>
          <div class="icon" @click="goback">
            <img src="@/assets/common/edit.png" alt="edit" />
          </div>
        </div>

        <van-cell
          title="投保人信息"
          icon="contact"
          class="select-customer-icon"
        />
        <van-cell title="投保人姓名" :value="f.appliName" />
        <van-cell
          title="证件类型"
          value="身份证"
          v-if="f.appliIdentifyType == '01'"
        />
        <van-cell
          title="证件类型"
          value="护照"
          v-if="f.appliIdentifyType == '03'"
        />
        <van-cell
          title="证件类型"
          value="港澳台居住证"
          v-if="f.appliIdentifyType == '28'"
        />
        <van-cell title="证件号码" :value="f.appliIdentifyNumber" />
        <van-cell title="出生日期" :value="f.appliBirthDate" />
        <van-cell title="手机号" :value="f.appliMobile" />
        <van-cell title="邮箱" :value="f.appliEMail" />
      </van-cell-group>
    </div>

    <div class="card">
      <van-cell-group>
        <van-cell
          title="被保人信息"
          icon="contact"
          class="select-customer-icon"
        />
        <van-cell
          title="与投保人关系"
          v-for="(item, i) in appliIdentityData"
          v-show="item.id == f.appliIdentity"
          :key="i"
          :value="item.text"
        />

        <van-cell title="被保人姓名" :value="f.insuredName" />
        <van-cell
          title="证件类型"
          value="身份证"
          v-if="f.insuredIdentifyType == '01'"
        />
        <van-cell
          title="证件类型"
          value="护照"
          v-if="f.insuredIdentifyType == '03'"
        />
        <van-cell
          title="证件类型"
          value="港澳台居住证"
          v-if="f.insuredIdentifyType == '28'"
        />
        <van-cell title="证件号码" :value="f.insuredIdentifyNumber" />
        <van-cell title="出生日期" :value="f.insuredBirthDate" />

        <van-cell title="手机号" :value="f.insuredMobile" />

        <van-cell
          title="社保类型"
          v-for="(item, i) in shebao"
          v-show="item.id == f.insuredSheBaoType"
          :key="i"
          :value="item.text"
        />
      </van-cell-group>
    </div>

    <div class="card">
      <van-cell-group>
        <van-cell
          title="保障期限"
          icon="calender-o"
          class="select-customer-icon"
        />
        <van-cell
          title="起保日期"
          :value="`${f.startDate}` + '-' + `${f.endDate}`"
        />
      </van-cell-group>
    </div>
    <div class="card" v-if="f.invoiceInfo.companyName != ''">
      <van-cell-group>
        <van-cell
          title="开票信息"
          icon="comment-o"
          class="select-customer-icon"
        />
        <van-cell
          title="发票类型"
          :value="
            f.invoiceInfo.invoiceType == '3'
              ? '增值税电子发票'
              : '增值税普通发票'
          "
        />
        <van-cell title="发票抬头" :value="f.invoiceInfo.companyName" />
        <van-cell
          title="手机号"
          :value="f.invoiceInfo.mobile"
          v-if="f.invoiceInfo.mobile != ''"
        />
        <van-cell
          title="电子邮箱"
          :value="f.invoiceInfo.email"
          v-if="f.invoiceInfo.email != ''"
        />
      </van-cell-group>
    </div>

    <div class="tabbar">
      <div class="info">
        保费
        <span>{{ formatSumPremium }}</span>
        元
        <!-- <span>{{ f.sumPremium }}</span>元 -->
      </div>
      <div class="btn">
        <van-button type="info" @click="enter">确认投保</van-button>
      </div>
    </div>
  </div>
</template>
<script>
import globalNav from "../../../components/globalNav";
import { mapGetters } from "vuex";
import { placeOrder, insureStatusZc } from "@src/http/module/nonCar.js";
import { Toast } from "vant";
import { trackMap } from "@src/utils/index.js";

export default {
  data() {
    return {
      isWeiXin: false,

      isHome: true,
      globalNav: {
        title: "保单确认"
      },
      cc: localStorage.getItem("cc"),
      appliIdentityData: [
        { id: 50, text: "父母" },
        { id: 10, text: "配偶" },
        { id: 40, text: "子女" },
        { id: "01", text: "本人" }
      ],
      shebao: [
        { id: 1, text: "城镇职工基本医疗保险" },
        { id: 8, text: "城乡居民基本医疗保险" },
        { id: 2, text: "城镇居民基本医疗保险" },
        { id: 3, text: "新农合医疗保险" },
        { id: 7, text: "公费医疗" },
        { id: 4, text: "民政优抚医疗保险" },
        { id: 6, text: "其他" }
      ],
      f: ""
    };
  },
  methods: {
    change() {
      this.$router.go(-1);
    },
    goback() {
      // this.$router.push({ name: "insuranceInformationInput" });
      this.$router.go(-1);
    },
    clickRight() {
      if (window.WebViewJavascriptBridge) {
        this.$native.openHome("", data => {});
      }
    },
    enter() {
      //投保号
      Toast.loading({
        message: "投保中...",
        forbidClick: true
      });
      //埋点
      trackMap("c_no_car_ensure_insure", "非车险确认投保确认", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "非车险出单"
      });
      let zcParams = {
        formulaCode: this.tenMillionData.formulaCode,
        insuranceCode: this.tenMillionData.riskCode,
        storeOracleRequestVO: this.tenMillionData
      };
      // insureStatusZc(zcParams).then(res => {
      if (localStorage.getItem("tenmillionOrderNo")) {
        placeOrder({
          ...this.tenMillionData,
          orderNo: localStorage.getItem("tenmillionOrderNo")
        }).then(res => {
          Toast.clear;
          if (res.code == "1") {
            this.$router.push({
              name: "signatureConfirm",
              query: {
                insureNo: res.data.insureInfo.insureNo,
                sumPremium: this.tenMillionData.sumPremium,
                mobile: this.tenMillionData.appliMobile,
                productName: this.tenMillionData.formulaName,
                applicantName: this.tenMillionData.appliName,
                insuredName: this.tenMillionData.insuredName,
                insuredMobile: this.tenMillionData.insuredMobile,
                invoiceType: this.tenMillionData.invoiceInfo.invoiceType,
                isFlag: "P"
              }
            });
            localStorage.removeItem("tenmillionOrderNo");
          } else {
            this.$dialog.alert({
              message: res.message || res.msg
            });
            localStorage.removeItem("tenmillionOrderNo");
          }
        });
      } else {
        placeOrder(this.tenMillionData).then(res => {
          Toast.clear;
          if (res.code == "1") {
            this.$router.push({
              name: "signatureConfirm",
              query: {
                insureNo: res.data.insureInfo.insureNo,
                sumPremium: this.tenMillionData.sumPremium,
                mobile: this.tenMillionData.appliMobile,
                productName: this.tenMillionData.formulaName,
                applicantName: this.tenMillionData.appliName,
                insuredName: this.tenMillionData.insuredName,
                insuredMobile: this.tenMillionData.insuredMobile
              }
            });
            localStorage.removeItem("tenmillionOrderNo");
          } else {
            this.$dialog.alert({
              message: res.message || res.msg
            });
            localStorage.removeItem("tenmillionOrderNo");
          }
        });
      }
    }
  },
  mounted() {
    const isWeiXin = this.$isWeiXin();
    this.isWeiXin = this.$isWeiXin();
    if (isWeiXin) {
      this.globalNav.isHome = false;
    }
  },
  components: {
    globalNav
  },
  created() {
    this.f = this.tenMillionData;
    // this.cc= localStorage.getItem('cc');
    // localStorage.setItem('baofei',localStorage.getItem('cc'))
  },
  computed: {
    formatSumPremium() {
      return parseInt(this.f.sumPremium)
        .toFixed(2)
        .replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, "$&,");
    },
    // ...mapGetters("million", ["millionData"]),
    ...mapGetters("million", ["tenMillionData"])
  }
};
</script>
<style lang="less" scoped>
.insuranceinfoConfirm {
  padding: 1.5rem 0;
  height: min-content !important ;
  .card {
    margin-top: 0.3rem;
  }
  .tabbar {
    display: flex;
    width: 100%;
    position: fixed;
    bottom: 0;
    .info {
      flex: 1;
      background-color: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 16px;

      span {
        color: #fc820e;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
      }
    }
    .btn {
      flex: 1;
      button {
        width: 100%;
      }
    }
  }
}

.title-container {
  height: 56px;
  padding: 15px;
  // margin: 0 0 10px 0;
  background-color: white;
  display: flex;
  align-items: center;
  flex: 1;
  position: relative;
  .topLeft {
    width: 103px;
    height: 63px;
    img {
      width: 73px;
      height: auto;
    }
  }
  .topRight {
    display: flex;
    flex-direction: column;
  }
  .icon {
    position: absolute;
    right: 26px;
    top: 26px;
    img {
      width: 18px;
      height: auto;
    }
  }
}
.title {
  font-size: 18px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 25px;
  width: 80%;
}
.time {
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 19px;
}
.select-customer-icon {
  color: rgba(59, 138, 254, 1);
  div {
    font-size: 18px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    line-height: 25px;
  }
}
</style>
