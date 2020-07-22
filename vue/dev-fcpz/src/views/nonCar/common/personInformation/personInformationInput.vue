<template>
  <div>
    <!-- 投保人信息 -->
    <div class="card" v-if="personType=='投'">
      <van-cell :border="false" center class="title-container">
        <template slot="title">
          <van-row type="flex" justify="space-between" align="center">
            <van-col class="title-text">投保人信息</van-col>
            <van-row type="flex" align="center" @click="selectInsuredMethod(false)">
              <van-col class="select-customer-text">{{appliName? '修改' : '选择投保人'}}</van-col>
              <van-col class="select-customer-icon">
                <van-icon name="contact" />
              </van-col>
            </van-row>
          </van-row>
        </template>
      </van-cell>

      <van-field
        label="投保人姓名"
        required
        placeholder="请输入"
        input-align="right"
        v-model="appliName"
        name="appliName"
        @blur="handler('投保人姓名',appliName)"
        maxlength="5"
      />

      <van-field
        readonly
        required
        clickable
        name="picker"
        :value="value"
        label="证件类型"
        input-align="right"
        placeholder="请选择"
        is-link
        @click="picker = true"
      />

      <van-field
        label="证件号码"
        required
        placeholder="请输入证件号码"
        v-model="appliIdentifyNumber"
        @blur="handlerIdNum('证件号码1',appliIdentifyNumber,appliIdentifyType)"
        maxlength="18"
      >
        <template slot="button">
          <van-row type="flex" align="center" @click="ocrDistinguish('appliCard')">
            <van-col class="select-customer-text">身份证识别</van-col>
            <van-col class="select-customer-icon">
              <van-icon name="photograph" />
            </van-col>
          </van-row>
        </template>
      </van-field>

      <!-- 出生日期组件更换 -->

      <div class="date">
        <van-cell
          :value="appliBirthDate"
          required
          label-width="80px"
          @click="showDate = true"
          class="rightCenter"
        >
          <template slot="title">
            <span>出生日期</span>
          </template>
          <van-icon slot="right-icon" name="arrow" class="grayBf left13 verticalMid" />
        </van-cell>
        <van-popup v-model="showDate" position="bottom" :style="{ height: '70%' }">
          <van-datetime-picker
            v-model="currentDate"
            type="date"
            @confirm="selectDateConfirm"
            @cancel="showDate = false"
            :min-date="minDate"
            :max-date="maxDate"
          />
        </van-popup>
      </div>
      <!-- ... -->

      <van-field
        label="手机号"
        required
        type="tel"
        placeholder="请输入手机号"
        input-align="right"
        v-model="appliMobile"
        @blur="handlerMobile('手机号',appliMobile)"
        maxlength="11"
      />

      <van-field label="邮箱" placeholder="请输入邮箱地址，可接收电子保单" input-align="right" v-model="appliEMail" />
    </div>

    <!-- 被保人信息 -->
    <div class="card" v-else>
      <van-cell :border="false" center class="title-container">
        <template slot="title">
          <van-row type="flex" justify="space-between" align="center">
            <van-col class="title-text">被保人信息</van-col>
            <van-row type="flex" align="center" @click="selectInsuredMethod(true)">
              <van-col class="select-customer-text">{{insuredName? '修改' : '选择被保人'}}</van-col>
              <van-col class="select-customer-icon">
                <van-icon name="contact" />
              </van-col>
            </van-row>
          </van-row>
        </template>
      </van-cell>

      <!-- <van-cell :border="false" center class="card-title-container" @click="sameBefore">
        <template slot="title">
          <van-row type="flex" justify="space-between" align="center">
            <van-col class="title-text1">同投保人</van-col>
            <van-switch v-model="checked2" size="20px" />
          </van-row>
        </template>
      </van-cell>-->
      <div>
        <van-field
          readonly
          clickable
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
          required
          label="被保人姓名"
          placeholder="请输入"
          input-align="right"
          v-model="insuredName"
          maxlength="5"
          @blur="handler('被保人姓名',insuredName)"
        />

        <van-field
          required
          readonly
          clickable
          name="picker"
          :value="value2"
          label="证件类型"
          input-align="right"
          placeholder="请选择"
          is-link
          @click="picker2 = true"
        />

        <van-field
          required
          label="证件号码"
          placeholder="请输入证件号码"
          v-model="insuredIdentifyNumber"
          @blur="handlerIdNum('证件号码2',insuredIdentifyNumber,insuredIdentifyType)"
          maxlength="18"
        >
          <template slot="button">
            <van-row type="flex" align="center" @click="ocrDistinguish('insuredCard')">
              <van-col class="select-customer-text">身份证识别</van-col>
              <van-col class="select-customer-icon">
                <van-icon name="photograph" />
              </van-col>
            </van-row>
          </template>
        </van-field>

        <!-- 出生日期组件更换 -->
        <div class="date">
          <van-cell
            :value="insuredBirthDate"
            required
            label-width="80px"
            @click="showDate = true"
            class="rightCenter"
          >
            <template slot="title">
              <span>出生日期</span>
            </template>
            <van-icon slot="right-icon" name="arrow" class="grayBf left13 verticalMid" />
          </van-cell>
          <van-popup v-model="showDate" position="bottom" :style="{ height: '70%' }">
            <van-datetime-picker
              v-model="currentDate"
              type="date"
              @confirm="selectDateConfirm2"
              @cancel="showDate = false"
              :min-date="minDate"
              :max-date="maxDate"
            />
          </van-popup>
        </div>
        <!-- ... -->
        <van-field
          required
          label="手机号"
          placeholder="请输入手机号"
          input-align="right"
          v-model="insuredMobile"
          @blur="handlerMobile('手机号',insuredMobile)"
          maxlength="11"
        />
      </div>

      <!-- 有社保展示 -->
      <van-field
        readonly
        clickable
        name="picker"
        :value="shebao"
        label="社保类型"
        placeholder="请选择"
        @click="showPicker = true"
        input-align="right"
        is-link
        v-if="isSheBao=='Y'"
      />
      <van-popup v-model="showPicker" position="bottom">
        <van-picker
          show-toolbar
          :columns="shebaocolumns"
          @confirm="onConfirmshebao"
          @cancel="showPicker = false"
        />
      </van-popup>
    </div>

    <!-- 投保人证件弹出层 -->
    <van-popup v-model="picker" position="bottom">
      <van-picker show-toolbar :columns="columns" @confirm="selectPicker" @cancel="picker = false" />
    </van-popup>

    <!-- 被保人证件弹出层 -->
    <van-popup v-model="picker2" position="bottom">
      <van-picker
        show-toolbar
        :columns="columns"
        @confirm="selectPicker2"
        @cancel="picker2 = false"
      />
    </van-popup>
  </div>
</template>

<script>
import { Toast, Dialog } from "vant";
import { checkIdcard } from "@src/utils";
export default {
  name: "person-information-input",
  props: {
    personType: String,
    isSheBao: String
  },
  data: () => ({
    appliIdentity: "",
    relationInfo: {
      //与投保人关系
      type: ["父母", "配偶", "子女", "本人"],
      picker: false,
      typeVal: ""
    },
    value: "身份证", //投保人证件类型picker初始值
    value2: "身份证", //被保人证件类型picker初始值
    columns: ["身份证", "护照", "港澳台居住证"], //投(被)保人证件类型数组
    shebaocolumns: [
      //社保picker数组
      "城镇职工基本医疗保险",
      "城乡居民基本医疗保险",
      "城镇居民基本医疗保险",
      "新农合医疗保险",
      "公费医疗",
      "民政优抚医疗保险",
      "其他"
    ],
    checked2: false, //switch状态
    // isSheBao: localStorage.getItem("isShebao") == "" ? "" : "Y", //社保字段 Y 和 ''
    shebao: "", //社保picker选择值
    picker: false, //投保人picker弹出层默认隐藏
    picker2: false, //被保人picker弹出层默认隐藏
    showPicker: false, //社保picker弹出层
    appliName: "", //投保人姓名
    appliIdentifyType: "01", //投保人证件类型
    appliIdentifyNumber: "", //证件号码
    appliMobile: "", //投保人手机号
    appliBirthDate: "", //出生日期
    minDate: new Date(1960, 0, 1), //出生日期组件默认定义最小值
    maxDate: new Date(), //出生日期组件默认最大值
    selectDate: "",
    showDate: false,
    currentDate: new Date(),
    appliEMail: "", //投保人邮箱
    // showCalendar: false, //出生、日期弹出层默认隐藏
    insuredName: "", //被保人姓名
    insuredIdentifyNumber: "", //被保人证件号
    insuredIdentifyType: "01", //被保人证件类型
    insuredMobile: "", //被保人手机号
    insuredBirthDate: "", //被保人出生日期
    insuredSheBaoType: "" //被保人社保类型
  }),
  created() {},
  methods: {
    card_confirm(value, index) {
      this.relationInfo.typeVal = value;
      switch (index) {
        case 0:
          this.appliIdentity = "50";
          this.checked2 = false;

          break;
        case 1:
          this.appliIdentity = "10";
          this.checked2 = false;
          // this.noBefore();

          break;
        case 2:
          this.appliIdentity = "40";
          this.checked2 = false;
          // this.noBefore();

          break;
        case 3:
          this.appliIdentity = "01";
          this.checked2 = true;
          this.sameBefore();
          break;
      }
      this.relationInfo.picker = false;
    },
    getNum(idCard) {
      var birthday = "";
      if (idCard != null && idCard != "") {
        if (idCard.length == 15) {
          birthday = "19" + idCard.slice(6, 12);
        } else if (idCard.length == 18) {
          birthday = idCard.slice(6, 14);
        }
        birthday = birthday.replace(/(.{4})(.{2})/, "$1-$2-");
        //通过正则表达式来指定输出格式为:1990-01-01
      }
      return birthday;
    },
    // ocr识别
    ocrDistinguish(type) {
      let _this = this;
      switch (type) {
        // 投保人身份证识别
        case "appliCard": {
          const jsonObject = {
            identifyType: "1", //"0" - 识别行驶证; "1" - 识别身份证
            side: "face" //"face"：正面；"back": 反面;
          };
          console.log("=========" + JSON.stringify(jsonObject));
          this.$native.identifyOCR(jsonObject, data => {
            Toast("身份证识别调用成功，壳响应数据:" + data.resultMsg);
            let tempData = JSON.parse(data);
            if (tempData.resultCode == "1") {
              const { name, cardNo } = tempData.responseBody;
              _this.appliIdentifyNumber = cardNo;
              _this.appliName = name;
              // _this.requestData.appliIdentity = "111";
            }
          });
          break;
        }
        // 被保人身份证识别
        case "insuredCard": {
          const jsonObject = {
            identifyType: "1", //"0" - 识别行驶证; "1" - 识别身份证
            side: "face" //"face"：正面；"back": 反面;
          };
          console.log("=========" + JSON.stringify(jsonObject));
          this.$native.identifyOCR(jsonObject, data => {
            Toast("身份证识别调用成功，壳响应数据:" + data.resultMsg);
            let tempData = JSON.parse(data);
            if (tempData.resultCode == "1") {
              const { name, cardNo } = tempData.responseBody;
              _this.insuredIdentifyNumber = cardNo;
              _this.insuredName = name;
              // _this.requestData.appliIdentity = "111";
            }
          });
          break;
        }
        // TODO suncao驾驶证识别
        case "vehicleCard": {
          const jsonObject = {
            identifyType: "0", //"0" - 识别行驶证; "1" - 识别身份证
            side: "face" //"face"：正面；"back": 反面;
          };
          this.$native.identifyOCR(jsonObject, data => {
            let dataParam = JSON.parse(data);
            console.log("==========" + dataParam.responseBody.cardNo);
            this.requestData.licenseNo = dataParam.responseBody.cardNo;
            this.requestData.brandModelName = dataParam.responseBody.model;
          });
          break;
        }
      }
    },
    // 选择日期区间方法
    selectDateConfirm(date) {
      this.appliBirthDate = this.formatDate(date);
      this.showDate = false;
    },
    selectDateConfirm2(date) {
      this.insuredBirthDate = this.formatDate(date);
      this.showDate = false;
    },
    // 社保switch切换
    // isHaveSb() {
    //   if (this.checked2) {
    //     this.isSheBao = "Y";
    //   } else {
    //     this.isSheBao = "";
    //   }
    // },

    // 同投保人切换
    sameBefore() {
      if (this.checked2) {
        this.insuredName = this.$parent.$refs.first.appliName;
        this.insuredIdentifyType = this.$parent.$refs.first.appliIdentifyType;
        this.insuredIdentifyNumber = this.$parent.$refs.first.appliIdentifyNumber;
        this.insuredBirthDate = this.$parent.$refs.first.appliBirthDate;
        this.insuredMobile = this.$parent.$refs.first.appliMobile;
      }
    },
    noBefore() {
      this.insuredName = "";
      this.insuredIdentifyType = "";
      this.insuredIdentifyNumber = "";
      this.insuredBirthDate = "";
      this.insuredMobile = "";
    },
    // 社保选择方法
    onConfirmshebao(value) {
      this.shebao = value;
      switch (value) {
        case "城镇职工基本医疗保险":
          this.insuredSheBaoType = "1";
          break;
        case "城乡居民基本医疗保险":
          this.insuredSheBaoType = "8";
          break;
        case "城镇居民基本医疗保险":
          this.insuredSheBaoType = "2";
          break;
        case "新农合医疗保险":
          this.insuredSheBaoType = "3";
          break;
        case "公费医疗":
          this.insuredSheBaoType = "7";
          break;
        case "民政优抚医疗保险":
          this.insuredSheBaoType = "4";
          break;
        case "其他":
          this.insuredSheBaoType = "6";
          break;
      }
      this.showPicker = false;
    },

    // 投保人组件证件类型方法
    selectPicker(value) {
      switch (value) {
        case "身份证":
          this.value = value;
          this.appliIdentifyType = "01";
          this.picker = false;
          break;
        case "护照":
          this.value = value;
          this.appliIdentifyType = "02";
          this.picker = false;
          break;
        case "港澳台居住证":
          this.value = value;
          this.appliIdentifyType = "03";
          this.picker = false;
          break;
      }
    },

    // 被保人组件证件类型方法
    selectPicker2(value) {
      switch (value) {
        case "身份证":
          this.value2 = value;
          this.insuredIdentifyType = "01";
          this.picker2 = false;
          break;
        case "护照":
          this.value2 = value;
          this.insuredIdentifyType = "02";
          this.picker2 = false;
          break;
        case "港澳台居住证":
          this.value2 = value;
          this.insuredIdentifyType = "03";
          this.picker2 = false;
          break;
      }
    },

    // 1 姓名非空验证
    handler(key, val) {
      return new Promise(resolve => {
        if (val == "" || val.length >= 50 || this.$isNull(val)) {
          Toast(key + "不能为空并且最大50字符");
          resolve("null");
        } else {
          resolve("ok");
        }
      });
    },

    // 2 根据类型验证证件号码appliIdentifyType
    handlerIdNum(key, val, type) {
      if (key == "证件号码1") {
        if (this.appliIdentifyType == "01") {
          Toast(checkIdcard(val));
          this.appliBirthDate = this.getNum(val);
        }
      }
      if (key == "证件号码2") {
        if (this.insuredIdentifyType == "01") {
          Toast(checkIdcard(val));
          this.insuredIdentifyType = this.getNum(val);
        }
      }

      return new Promise(resolve => {
        switch (type) {
          case "01": //身份证验证
            if (val == "") {
              Toast("证件号码不能为空");
              resolve("idnull");
            } else {
              resolve("ok");
            }
            break;
          case "02": //护照验证
            if (val == "") {
              Toast("护照不能为空");
              resolve("hunull");
            } else {
              resolve("ok");
            }
            break;
          case "03": //港澳居住验证
            if (val == "") {
              Toast("港澳居住证不能为空");
              resolve("hknull");
            } else {
              resolve("ok");
            }
            break;
        }
      });
    },

    // 3 手机号验证
    handlerMobile(key, val) {
      return new Promise(resolve => {
        if (val == "") {
          Toast("手机号不能为空");
          resolve("mobilenull");
        } else {
          let reg = /^1[3|4|5|6|8|7|9][0-9]\d{8}$/;
          if (!reg.test(val.trim())) {
            Toast("手机号格式不正确");
            resolve("mobilerules");
          } else {
            resolve("ok");
          }
        }
      });
    },

    // 4 邮箱验证
    // handlerEmail() {
    //   return new Promise(resolve => {
    //     let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
    //     if (!reg.test(this.appliEMail)) {
    //       Toast("邮箱格式不正确");
    //       resolve("emailnull");
    //     } else {
    //       resolve("ok");
    //     }
    //   });
    // },

    // 跳转投保人联系人界面
    selectInsuredMethod(isInsured) {
      let parms = {
        custType: "1",
        insureType: "2",
        isInsured: isInsured || false
      };
      if (isInsured) {
        var value = "";

        switch (this.$parent.$refs.first.appliIdentifyType) {
          case "01":
            value = "身份证";
            break;
          case "02":
            value = "护照";
            break;
          case "03":
            value = "港澳台居住证";
            break;
        }
        let formData = {
          appliIdentifyNumber: this.$parent.$refs.first.appliIdentifyNumber,
          appliIdentifyType: this.$parent.$refs.first.appliIdentifyType,
          appliBirthDate: this.$parent.$refs.first.appliBirthDate,
          appliMobile: this.$parent.$refs.first.appliMobile,
          appliEMail: this.$parent.$refs.first.appliEMail,
          value: value
        };
        localStorage.setItem("formData", JSON.stringify(formData));
        // this.$parent.$refs.first.insuredIdentifyNumber = this.$parent.$refs.first.insuredIdentifyNumber;
      }
      this.$router.push({ name: "myCustomer", query: { item: parms } });
    },

    selectCustomer(i) {},

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
    }
  }
};
</script>

<style scoped>
.card {
  margin-bottom: 10px;
}
.title-container {
  height: 50px;
  margin-bottom: 0px;
  background-color: white;
}
.title-text {
  font-size: 18px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 25px;
}
.title-text1 {
  font-size: 18px;
  font-family: PingFangSC-Semibold, PingFang SC;
  color: #333333;
  line-height: 25px;
}
.select-customer-text {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #3b8afe;
  line-height: 20px;
  margin-right: 12px;
}
.select-customer-icon {
  width: 20px;
  height: 20px;
  color: rgba(59, 138, 254, 1);
}
.long-text-fixed {
  flex-basis: auto;
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
</style>
