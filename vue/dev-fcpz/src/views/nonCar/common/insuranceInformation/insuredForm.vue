<template>
  <div class="insuredForm">
    <div class="card" style="position: relative;">
      <van-cell :border="false" center class="title-container">
        <template #title>
          <span class="custom-title">被保人{{ num }}信息</span>
          <span
            class="delete-img"
            @click="deteleItem"
            :style="{ backgroundImage: 'url(' + deleteImg + ')' }"
            v-if="num > 2"
          ></span>
        </template>
        <van-row
          type="flex"
          justify="space-between"
          align="right"
          @click="getCustomer()"
          style="float: right;"
        >
          <van-col align="right" class="select-customer-text">
            {{ insuredInfo.familyName ? "选择投保人" : "选择被保人" }}
            <img src="@/assets/common/icon2.png" class="card-img" />
          </van-col>
        </van-row>
      </van-cell>
      <div>
        <van-field
          readonly
          clickable
          required
          :value="relationInfo.typeVal"
          placeholder="请选择"
          @click="relationInfo.picker = true"
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
            :columns="relationInfo.type"
            @cancel="relationInfo.picker = false"
            @confirm="card_confirm"
          />
        </van-popup>
      </div>
      <div v-show="!checked2">
        <van-field
          v-model="insuredInfo.familyName"
          label="被保人姓名"
          required
          placeholder="请输入被保人姓名"
          input-align="right"
          maxlength="15"
          @input="nameInput"
        />

        <div>
          <van-field
            readonly
            clickable
            required
            :value="cardTypeInfo.typeVal"
            placeholder="请选择证件类型"
            @click="cardTypeInfo.picker = true"
            input-align="right"
          >
            <div slot="label">
              <span>证件类型</span>
            </div>
            <div slot="right-icon" class="rightCenter">
              <van-icon name="arrow" class="grayBf" />
            </div>
          </van-field>
          <van-popup v-model="cardTypeInfo.picker" position="bottom">
            <van-picker
              show-toolbar
              :columns="cardTypeInfo.type"
              @cancel="cardTypeInfo.picker = false"
              @confirm="card_confirm1"
            />
          </van-popup>
        </div>
        <van-field
          v-model="insuredInfo.familyIdentifyNo"
          center
          required
          clearable
          label="证件号码"
          placeholder="请输入证件号码"
          maxlength="18"
          input-align="right"
          :formatter="removeAllSpace"
          @blur="handBlur"
          @input="identifyInput"
        >
          <template slot="button" v-if="hasCamera">
            <van-row type="flex" align="center" @click="getOCRID()">
              <img
                src="@/assets/common/icon3.png"
                class="card-img"
              />
            </van-row>
          </template>
        </van-field>

        <!-- 出生日期组件更换 -->
        <div class="date">
          <van-cell
            :value="insuredInfo.insuredBirthDate"
            required
            label-width="80px"
            @click="roShowDate()"
            class="rightCenter"
            placeholder="请输入出生日期"
          >
            <template slot="title">
              <span>出生日期</span>
            </template>
            <van-icon
              slot="right-icon"
              name="arrow"
              class="grayBf left13 verticalMid"
            />
          </van-cell>
          <van-popup
            v-model="showDate2"
            position="bottom"
            :style="{ height: '70%' }"
          >
            <van-datetime-picker
              v-model="currentDate2"
              type="date"
              @confirm="selectDateConfirm2"
              @cancel="showDate2 = false"
              :min-date="minDate2"
              :max-date="maxDate2"
            />
          </van-popup>
        </div>
        <van-field
          v-model="insuredInfo.mobile"
          maxlength="11"
          label="手机号"
          placeholder="请输入手机号"
          type="tel"
          :formatter="removeAllSpace"
          input-align="right"
          @input="mobileInput"
        />
      </div>
      <div>
        <van-field
          readonly
          clickable
          :value="shebaoInfo.typeVal"
          placeholder="请选择"
          @click="shebaoInfo.picker = true"
          input-align="right"
        >
          <div slot="label">
            <span>社保类型</span>
          </div>
          <div slot="right-icon" class="rightCenter">
            <van-icon name="arrow" class="grayBf" />
          </div>
        </van-field>
        <van-popup v-model="shebaoInfo.picker" position="bottom">
          <van-picker
            show-toolbar
            :columns="shebaoInfo.type"
            @cancel="shebaoInfo.picker = false"
            @confirm="card_confirm2"
          />
        </van-popup>
      </div>
    </div>
  </div>
</template>
<script>
import { Toast, Dialog } from "vant";
import deleteImg from "@/assets/common/delete.png";
import { checkIdcard } from "../../../../utils/index";
export default {
  name: "insuredForm",
  props: [
    "num",
    "insuredInfo",
    "selectInsuredMethod",
    "familyRelationAction",
    "familyNameAction",
    "familyIdentifyTypeAction",
    "familyIdentifyNoAction",
    "insuredBirthDateAction",
    "mobileAction",
    "insuredSheBaoTypeAction"
  ],
  watch: {
    "insuredInfo.insuredBirthDate": {
      handler(value) {
        let getAge = this.$getAge(value);
        let interval = this.compare(getAge);
        this.$parent.collectAgeArr.splice(this.num - 1, 1, interval);
        this.$parent.updatePremiumsNum();
      }
    }
  },
  computed: {
    hasCamera() {
      return (
        this.insuredInfo.familyIdentifyType == "01" &&
        window.WebViewJavascriptBridge
      );
    }
  },
  data() {
    return {
      deleteImg,
      checked2: false, //switch状态
      insuredName: "", //被保人姓名
      relationInfo: {
        //与投保人关系
        type: ["本人", "配偶", "子女", "父母"],
        picker: false,
        typeVal: ""
      },
      appliIdentity: "", //关系
      cardTypeInfo: {
        //证件类型
        //被保人年龄信息
        type: ["身份证", "护照", "港澳台居住证"],
        picker: false,
        typeVal: "身份证"
      },
      shebaoInfo: {
        //证件类型
        //被保人年龄信息
        type: [
          "城镇职工基本医疗保险",
          "城乡居民基本医疗保险",
          "城镇居民基本医疗保险",
          "新农合医疗保险",
          "公费医疗",
          "民政优抚医疗保险",
          "其他"
        ],
        picker: false,
        typeVal: ""
      },
      insuredIdentifyNumber: "", //证件号码
      insuredIdentifyType: "", //证件类型
      insuredBirthDate: "", //出生日期
      showDate2: false,
      currentDate2: new Date(),
      minDate2: new Date(1960, 0, 1), //出生日期组件默认定义最小值
      maxDate2: new Date(), //出生日期组件默认最大值
      insuredMobile: "" //手机号
    };
  },
  created() {
    this.identifyTypeS(this.insuredInfo.familyIdentifyType);
    this.relationChangeName(this.insuredInfo.familyRelation);
    this.shebaoChangeName(this.insuredInfo.insuredSheBaoType);
  },
  methods: {
    removeAllSpace(str) {
      return str.replace(/\s+/g, "");
    },
    roShowDate() {
      if (this.insuredInfo.familyIdentifyType == "01") {
        this.showDate2 = false;
        Toast("被保人"+this.num+"身份证证件类型生日不能选择");
      } else {
        this.showDate2 = true;
      }
    },
    // 比较年龄
    compare(getAge) {
      switch (true) {
        case getAge >= 0 && getAge <= 4:
          return "0-4";
        case getAge >= 5 && getAge <= 10:
          return "5-10";
        case getAge >= 11 && getAge <= 15:
          return "11-15";
        case getAge >= 16 && getAge <= 20:
          return "16-20";
        case getAge >= 21 && getAge <= 25:
          return "21-25";
        case getAge >= 26 && getAge <= 30:
          return "26-30";
        case getAge >= 31 && getAge <= 35:
          return "31-35";
        case getAge >= 36 && getAge <= 40:
          return "36-40";
        case getAge >= 41 && getAge <= 45:
          return "41-45";
        case getAge >= 46 && getAge <= 50:
          return "46-50";
        case getAge >= 51 && getAge <= 55:
          return "51-55";
        case getAge >= 56 && getAge <= 60:
          return "56-60";
      }
    },
    deteleItem() {
      Dialog.confirm({
        message: `确认删除被保人${this.num}信息?`
      })
        .then(() => {
          this.$parent.accidentalInjuryInfos.splice(this.num - 1, 1);
          this.$parent.collectAgeArr.splice(this.num - 1, 1);
          this.$parent.securityNum -= 1;
          this.$parent.updatePremiumsNum();
        })
        .catch(() => {});
    },
    identifyInput(text) {
      this.familyIdentifyNoAction(this.num, text);
    },
    nameInput(text) {
      this.familyNameAction(this.num, text);
    },
    mobileInput(text) {
      this.mobileAction(this.num, text);
    },
    getCustomer() {
      this.selectInsuredMethod(this.num);
    },
    // 身份证识别
    getOCRID() {
      const jsonObject = {
        identifyType: "1", //"0" - 识别行驶证; "1" - 识别身份证
        side: "face" //"face"：正面；"back": 反面;
      };

      this.$native.identifyOCR(jsonObject, data => {
        // eslint-disable-next-line no-undef
        let tempData = JSON.parse(data);
        if (tempData.resultCode == "1") {
          const { name, cardNo } = tempData.responseBody;

          //姓名
          this.familyNameAction(this.num, name);

          //证件号码
          this.familyIdentifyNoAction(this.num, cardNo);

          if (checkIdcard(cardNo) == "验证通过") {
            //获取证件类型的生日
            this.insuredInfo.insuredBirthDate = this.$getIdNum(cardNo);

            this.insuredBirthDate = this.insuredInfo.insuredBirthDate;
            this.insuredBirthDateAction(this.num, this.insuredBirthDate);
          }
        }
      });
    },
    // 获取证件号码的出生日期
    handBlur(val) {
      if (this.cardTypeInfo.typeVal == "身份证") {
        if (checkIdcard(this.insuredInfo.familyIdentifyNo) == "验证通过") {
          //获取证件类型的生日
          this.insuredInfo.insuredBirthDate = this.$getIdNum(
            this.insuredInfo.familyIdentifyNo
          );
          this.insuredBirthDate = this.insuredInfo.insuredBirthDate;
          this.insuredBirthDateAction(this.num, this.insuredBirthDate);
        } else {
          this.insuredBirthDateAction(this.num, "");
          this.$toast(checkIdcard(this.insuredInfo.familyIdentifyNo));
        }
      }
    },
    card_confirm(value, index) {
      this.relationInfo.typeVal = value;
      //"本人","父母", "配偶", "子女"
      //"本人", "配偶", "子女","父母"
      switch (index) {
        case 0:
          //本人
          this.appliIdentity = "01";
          this.checked2 = true;
          this.sameBefore();
          break;
        case 1:
          //配偶
          this.appliIdentity = "10";
          this.checked2 = false;
          break;
        case 2:
          //子女
          this.appliIdentity = "40";
          this.checked2 = false;
          break;
        case 3:
          //父母
          this.appliIdentity = "50";
          this.checked2 = false;
          break;
        case 4:
          this.appliIdentity = "99";
          this.checked2 = false;
      }
      this.relationInfo.picker = false;
      if (!this.checked2) {
        if(this.$parent.accidentalInjuryInfos[this.num - 1].familyRelation == '01'){
          this.$parent.accidentalInjuryInfos[this.num - 1].insuredSheBaoType = "";
          this.shebaoInfo.typeVal = ''
        }
        this.familyRelationAction(this.num, this.appliIdentity);
      }
    },
    // 同投保人切换
    sameBefore() {
      if (this.checked2) {
        this.$parent.accidentalInjuryInfos[this.num - 1].familyRelation = "01";
        this.$parent.accidentalInjuryInfos[
          this.num - 1
        ].familyName = this.$parent.appliInfo.appliName;
        this.$parent.accidentalInjuryInfos[
          this.num - 1
        ].familyIdentifyType = this.$parent.appliInfo.appliIdentifyType;
        this.$parent.accidentalInjuryInfos[
          this.num - 1
        ].familyIdentifyNo = this.$parent.appliInfo.appliIdentifyNumber;
        this.$parent.accidentalInjuryInfos[
          this.num - 1
        ].insuredBirthDate = this.$parent.appliInfo.appliBirthDate;
        this.$parent.accidentalInjuryInfos[
          this.num - 1
        ].mobile = this.$parent.appliInfo.appliMobile;
        this.$parent.accidentalInjuryInfos[
          this.num - 1
        ].insuredSheBaoType = '';
        this.shebaoInfo.typeVal = ''
      }
    },
    card_confirm1(value, index) {
      console.log(index);
      let idCode = "01";
      switch (index) {
        case 0: {
          idCode = "01";
          break;
        }
        case 1: {
          idCode = "03";
          break;
        }
        case 2: {
          idCode = "28";
          break;
        }
      }
      this.cardTypeInfo.typeVal = value;
      this.insuredIdentifyType = idCode;
      this.cardTypeInfo.picker = false;

      this.insuredInfo.familyIdentifyType = idCode;
      this.familyIdentifyTypeAction(this.num, idCode, value);
    },
    card_confirm2(value, index) {
      this.shebaoInfo.typeVal = value;
      switch (index) {
        case 0:
          this.insuredSheBaoType = "1";
          break;
        case 1:
          this.insuredSheBaoType = "8";
          break;
        case 2:
          this.insuredSheBaoType = "2";
          break;
        case 3:
          this.insuredSheBaoType = "3";
          break;
        case 4:
          this.insuredSheBaoType = "7";
          break;
        case 5:
          this.insuredSheBaoType = "4";
          break;
        case 6:
          this.insuredSheBaoType = "6";
          break;
      }
      this.shebaoInfo.picker = false;

      this.insuredSheBaoTypeAction(this.num, this.insuredSheBaoType, value);
    },
    selectDateConfirm2(date) {
      this.insuredBirthDate = this.formatDate(date);
      this.showDate2 = false;
      this.insuredInfo.insuredBirthDate = this.insuredBirthDate;
      this.insuredBirthDateAction(this.num, this.insuredBirthDate);
    },
    // 格式化时间
    formatDate(date) {
      let month = date.getMonth() + 1;
      if (month < 10) {
        month = "0" + month;
      }
      let beginDate = date.getDate();
      if (beginDate < 10) {
        beginDate = "0" + beginDate;
      }
      return `${date.getFullYear()}-${month}-${beginDate}`;
    },
    // 身份证类型转化
    identifyTypeS(type) {
      switch (type) {
        case "01":
          this.cardTypeInfo.typeVal = "身份证";
          break;
        case "03":
          this.cardTypeInfo.typeVal = "护照";
          break;
        case "28":
          this.cardTypeInfo.typeVal = "港澳台居住证";
          break;
      }
    },
    relationChangeName(type) {
      switch (type) {
        case "50":
          this.relationInfo.typeVal = "父母";
          break;
        case "10":
          this.relationInfo.typeVal = "配偶";
          break;
        case "40":
          this.relationInfo.typeVal = "子女";
          break;
        case "01":
          this.relationInfo.typeVal = "本人";
          this.checked2 = true;
          break;
        case "99":
          this.relationInfo.typeVal = "其他";
      }
    },
    shebaoChangeName(type) {
      switch (type) {
        case "1":
          this.shebaoInfo.typeVal = "城镇职工基本医疗保险";
          break;
        case "8":
          this.shebaoInfo.typeVal = "城乡居民基本医疗保险";
          break;
        case "2":
          this.shebaoInfo.typeVal = "城镇居民基本医疗保险";
          break;
        case "3":
          this.shebaoInfo.typeVal = "新农合医疗保险";
          break;
        case "7":
          this.shebaoInfo.typeVal = "公费医疗";
          break;
        case "4":
          this.shebaoInfo.typeVal = "民政优抚医疗保险";
          break;
        case "6":
          this.shebaoInfo.typeVal = "其他";
          break;
      }
    }
  }
};
</script>
<style lang="less" scoped>
.title-text {
  font-size: 18px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 25px;
}
.title-container {
  font-size: 14px;
  height: 50px;
  margin-bottom: 0px;
  font-weight: 600;
  background-color: #f7f7f7;
  .delete-img {
    display: inline-block;
    width: 15px;
    height: 16px;
    background-position: 0 0;
    background-repeat: no-repeat;
    background-size: 100% 100%;
    transform: translateY(2px);
    margin-left: 11px;
  }
}
.title-text {
  font-size: 18px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 25px;
}
.title-text1 {
  font-size: 14px;
  font-family: PingFangSC-Semibold, PingFang SC;
  color: #333333;
  line-height: 25px;
}
.select-customer-text {
  font-size: 14px;
  color: #3b8afe;
  line-height: 20px;
  font-weight: normal;
}
.select-customer-icon {
  width: 20px;
  height: 20px;
}
.long-text-fixed {
  flex-basis: auto;
}
.card-img {
  margin: -4px 0 -4px 5px;
  width: 20px;
  height: 20px;
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
.insuredForm {
  .date {
    .van-cell {
      align-items: center;
    }
  }
}
</style>
