<template>
  <!-- 家庭版投保信息填写 -->
  <div class="writeForm">
    <globalNav
      :title="globalNav.title"
      @clickBack="goback"
    ></globalNav>
    <van-row
      class="title-container"
      type="flex"
      align="center"
    >
      <van-col class="title-img">
        <img
          src="@/assets/common/default1.png"
          alt="edit"
        />
      </van-col>
      <van-col class="title-text">天地关爱，百万无忧</van-col>
    </van-row>
    <div class="card">
      <van-cell
        :border="false"
        center
        class="title-container"
        style="background:#f7f7f7;"
      >
        <template slot="title">
          <van-row
            type="flex"
            justify="space-between"
            align="center"
          >
            <van-col class="card-title-text">
              投保人信息
            </van-col>
            <van-row
              type="flex"
              align="center"
              @click="selectInsuredMethod('8')"
            >
              <van-col class="select-customer-text">
                {{ appliInfo.appliName ? "选择投保人" : "选择投保人" }}
                <img
                  src="@/assets/common/icon2.png"
                  alt="edit"
                  class="card-img"
                  style="width:20px;height:20px;"
                />
              </van-col>
            </van-row>
          </van-row>
        </template>
      </van-cell>
      <van-field
        v-model="appliInfo.appliName"
        required
        label="投保人姓名"
        placeholder="请输入"
        input-align="right"
        maxlength="15"
      />
      <div>
        <van-field
          readonly
          clickable
          required
          :value="appliInfo.appliIdentifyTypeName"
          placeholder="请选择证件类型"
          @click="cardTypeInfo.picker = true"
          input-align="right"
        >
          <div slot="label">
            <span>证件类型</span>
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
          v-model="cardTypeInfo.picker"
          position="bottom"
        >
          <van-picker
            show-toolbar
            :columns="cardTypeInfo.type"
            @cancel="cardTypeInfo.picker = false"
            @confirm="card_confirm"
          />
        </van-popup>
      </div>
      <van-field
        v-model="appliInfo.appliIdentifyNumber"
        required
        center
        clearable
        label="证件号码"
        placeholder="请输入证件号码"
        maxlength="18"
        input-align="right"
        :formatter="removeAllSpace"
        @blur="handBlur(appliInfo.appliIdentifyNumber)"
      >
        <template
          slot="button"
          v-if="hasCamera"
        >
          <van-row
            type="flex"
            align="center"
            @click="getOCRID()"
          >
            <van-col class="select-customer-text">
              <img
                src="@/assets/common/icon3.png"
                alt="edit"
                style="width:20px;height:20px;"
                class="card-img"
              />
            </van-col>
          </van-row>
        </template>
      </van-field>
      <!-- 出生日期组件更换 -->

      <div class="date">
        <van-cell
          disabled="true"
          :value="appliInfo.appliBirthDate"
          required
          label-width="80px"
          @click="roShowDate()"
          class="rightCenter"
          placeholder="选择身份证，出生日期自动带入"
        >
          <template slot="title">
            <span>出生日期</span>
          </template>
          <van-icon
            slot="right-icon"
            name="arrow"
            class="grayBf left13 verticalMid"
          />
          <!-- <img src="@/assets/common/icon4.png" name="arrow" alt="edit" class="grayBf left13 verticalMid card-img"> -->
        </van-cell>
        <van-popup
          v-model="showDate"
          position="bottom"
          :style="{ height: '70%' }"
        >
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
      <van-field
        v-model="appliInfo.appliMobile"
        required
        label="手机号"
        placeholder="请输入手机号"
        input-align="right"
        maxlength="11"
        type="tel"
        :formatter="removeAllSpace"
      />
      <van-field
        v-model="appliInfo.appliEMail"
        label="邮箱"
        placeholder="请输入邮箱地址，可接收电子保单"
        input-align="right"
        :formatter="removeAllSpace"
      />
    </div>
    <!-- 被保人组件 -->
    <insured-form
      v-for="(item, index) in accidentalInjuryInfos"
      :key="index"
      :num="index + 1"
      :insuredInfo="item"
      :selectInsuredMethod="selectInsuredMethod"
      :familyRelationAction="familyRelationAction"
      :familyNameAction="familyNameAction"
      :familyIdentifyTypeAction="familyIdentifyTypeAction"
      :familyIdentifyNoAction="familyIdentifyNoAction"
      :insuredBirthDateAction="insuredBirthDateAction"
      :mobileAction="mobileAction"
      :insuredSheBaoTypeAction="insuredSheBaoTypeAction"
      :ref="'insuredForm' + `${index + 1}`"
    />
    <div
      class="add-insured-form"
      v-if="accidentalInjuryInfos.length < 7"
      @click="addInsuredForm"
    >
      + 添加被保人
    </div>
    <!-- 保障期限 -->
    <div class="card">
      <van-cell
        :border="false"
        center
        class="card-title-container"
        style="background:#f7f7f7;"
      >
        <template slot="title">
          <div class="card-title-text">
            <!-- <img src="@/assets/common/icon6.png" alt="edit" /> -->
            保障期限
          </div>
        </template>
      </van-cell>
      <van-cell
        title="保障年限"
        value="1年"
      />
      <div class="date">
        <van-cell
          :value="selectDate"
          required
          label-width="80px"
          @click="showDate3 = true"
          class="rightCenter"
        >
          <template slot="title">
            <span>起保日期</span>
          </template>
          <van-icon
            slot="right-icon"
            name="arrow"
            class="grayBf left13 verticalMid"
          />
        </van-cell>
        <van-popup
          v-model="showDate3"
          position="bottom"
          :style="{ height: '70%' }"
        >
          <van-datetime-picker
            v-model="currentDate3"
            type="date"
            :min-date="minDate2"
            :max-date="maxDate2"
            @confirm="selectDateConfirmqj"
            @cancel="showDate3 = false"
          />
        </van-popup>
      </div>
    </div>
    <!-- 开票信息 -->
    <div class="card">
      <van-cell
        :border="false"
        center
        class="card-title-container"
        @click="toggleChecked"
      >
        <template slot="title">
          <van-row
            type="flex"
            justify="space-between"
            align="center"
          >
            <van-col class="card-title-text">
              <!-- <img src="@/assets/common/icon7.png" alt="edit" /> -->
              开票信息
            </van-col>
            <van-switch
              v-model="checked"
              size="20px"
            />
          </van-row>
        </template>
      </van-cell>
      <!-- <invoice-info-input v-if="checked" ref="third" /> -->
      <div v-if="checked">
        <van-row
          type="flex"
          center
          align="center"
          class="tip-container"
        >
          <van-col class="tip-icon">
            <van-icon
              name="question"
              color="#3B8AFE"
              size="16px"
            />
          </van-col>
          <van-col class="tip-text">电子发票与纸质发票具有同等效力，可作为报销凭据</van-col>
        </van-row>
        <van-cell title="是否需要电子发票">
          <template slot="default">
            <van-button
              plain
              type="primary"
              :class="[isShould ? 'selected-button' : '', 'select-button']"
              @click="toggleShould(1)"
            >是</van-button>
            <van-button
              plain
              class="select-button"
              :class="[!isShould ? 'selected-button' : '', 'select-button']"
              @click="toggleShould(2)"
            >否</van-button>
          </template>
        </van-cell>
        <van-cell title="发票抬头">
          <template slot="default">
            <van-button
              plain
              type="primary"
              :class="[isSameInsured ? 'selected-button' : '', 'select-button']"
              @click="toggleSameInsured(1)"
            >同投保人</van-button>
            <van-button
              plain
              class="select-button"
              :class="[
                !isSameInsured ? 'selected-button' : '',
                'select-button'
              ]"
              @click="toggleSameInsured(2)"
            >同被保人</van-button>
          </template>
        </van-cell>
        <van-field
          v-if="isShould"
          type="tel"
          v-model="receiptMobile"
          label="移动手机"
          maxlength="11"
          required
          placeholder="用来接收电子发票链接  必填"
          input-align="right"
        />
        <van-field
          v-if="isShould"
          v-model="receiptEmail"
          label="电子邮箱"
          required
          placeholder="用来接收电子发票链接  必填"
          input-align="right"
        />
      </div>
    </div>

    <van-cell
      class="agree"
      style="padding-bottom:60px;background:#f7f7f7;"
    >
      <template slot="title">
        <van-row
          type="flex"
          justify="space-between"
          align="center"
        >
          <van-col class="allow-protocol-check-box">
            <van-checkbox v-model="allowedProtocol" />
          </van-col>
          <van-col
            class="protocolLink"
            style="margin-left:10px;color: rgb(153, 153, 153);"
          >
            本人已认真阅读并同意
            <span
              v-for="(protocol, index) in protocols"
              :key="protocol.name"
              :to="protocol.link"
              @click="clickProtocol(index)"
              tag="a"
              style="color:#3b8afe;"
            >
              <span v-if="index === protocols.length - 1">和</span>
              {{ protocol.name }}
            </span>
            的全部内容，
            <span>并保证所填《健康告知》内容真实有效</span>
          </van-col>
        </van-row>
      </template>
    </van-cell>
    <div class="tabbar">
      <div class="price">
        保费
        <span style="color:#FC820E">{{ sumPremium }}</span> 元
      </div>
      <div class="btn">
        <van-button
          type="info"
          @click="enter"
        >确认投保</van-button>
      </div>
    </div>
  </div>
</template>
<script>
import Vue from "vue";
import globalNav from "../../../../components/globalNav";
import insuredForm from "./insuredForm";
import { placeOrder } from "../../../../http/module/nonCar.js";
import { mapGetters, mapActions, mapMutations } from "vuex";
import { Toast } from "vant";
import { insureStatusZc } from "@src/http/module/nonCar.js";
import {
  mobilePhone,
  emailCheck,
  checkIdcard,
  isName,
  isNull
} from "../../../../utils/index";
Date.prototype.Format = function (fmt) {
  //author: zhengsh 2016-9-5
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    S: this.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(
      RegExp.$1,
      (this.getFullYear() + "").substr(4 - RegExp.$1.length)
    );
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(
        RegExp.$1,
        RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)
      );
  return fmt;
};
export default {
  data () {
    return {
      globalNav: {
        title: "投保信息填写",
        isShare: true
      },
      collectAgeArr: [], // 收集年龄数组
      result: [],
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
      startData: "",
      appliInfo: {
        appliName: "", //投保人姓名
        appliIdentifyType: "01", //证件类型
        appliIdentifyTypeName: "身份证", //证件名称
        appliIdentifyNumber: "", //证件号码
        appliBirthDate: "", //出生日期  ...
        appliMobile: "", //手机号
        appliEMail: "" //邮箱
      },
      accidentalInjuryInfos: [], //被保人数组
      securityNum: 2, //保障人数
      sumPremium: "", //总保费
      endDate: "", //结束时间
      startDate: "", //开始时间
      selectDate: "", //选择保险起期
      formulaName: "", //方案名称
      formulaCode: "", //方案编码
      invoiceInfo: {
        mobile: "",
        email: "",
        companyName: "",
        invoiceType: "",
        customerType: "",
        taxPayerType: "",
        taxPayerNo: ""
      },
      currentDate: new Date(),
      // currentDate2: new Date(),//调用了组件
      currentDate3: new Date(),
      minDate: new Date(1960, 0, 1), //出生日期组件默认定义最小值
      maxDate: new Date(), //出生日期组件默认最大值
      minDate2: new Date(new Date().getTime() + 24 * 60 * 60 * 1000),
      maxDate2: new Date(new Date().getTime() + 24 * 60 * 60 * 1000 * 30),
      showDate: false,
      showDate3: false,
      appliName: "333", //投保人姓名
      appliIdentifyType: "01", //证件类型
      appliIdentifyNumber: "", //证件号码
      appliBirthDate: "", //出生日期  ...
      appliMobile: "", //手机号
      appliEMail: "", //邮箱
      insuredName: "", //被保人姓名
      insuredIdentifyType: "", //被保人证件类型
      insuredIdentifyNumber: "", //被保人证件号码
      insuredMobile: "", //被保人手机号
      insuredBirthDate: "", //被保人出生日期

      cardTypeInfo: {
        //证件类型
        //被保人年龄信息
        type: ["身份证", "护照", "港澳台居住证"],
        picker: false,
        typeVal: ""
      },
      checked: false, //开票信息(true,false)
      isSameInsured: true, //同投保人切换
      isShould: true, //是否需要电子发票按钮
      receiptMobile: "", //开票手机号
      receiptEmail: "", //开票邮箱
      // zhiReceipt: false, //纸质发票
      allowedProtocol: false, //底部勾选

      protocols: [
        { name: "《保险条款》", link: "/nonCar/clauseFine/personalTerms" },
        { name: "《投保须知》", link: "/nonCar/clauseFine/InsureNotice" },
        { name: "《投保人声明》", link: "/nonCar/clauseFine/InsureStatement" },
        { name: "《特别约定》", link: "/nonCar/clauseFine/Aboutpersonal" },
        { name: "《责任免赔》", link: "/nonCar/clauseFine/liabilityExempt" }
      ], //底部同意选项

      bool: false,
      queryData: "", //请求数据
      formula: "", //方案
      agentInfo: "",
      isSheBao: ""
    };
  },
  components: {
    globalNav,
    insuredForm
  },
  watch: {
    securityNum (value) {
      setTimeout(() => {
        if (this.queryData && this.queryData.data) {
          if (value <= 3) {
            this.formula = this.queryData.data.sfList[0];
          } else {
            this.formula = this.queryData.data.sfList[1];
          }
          this.formulaName = this.formula.formulaName; //方案名称
          this.formulaCode = this.formula.formulaCode; //方案编码
        }
      }, 3000);
    },
    receiptEmail (v) {
      this.invoiceInfo.email = v;
    }
  },
  created () {
    this.$root.$on("selectUserInfo", item => {
      //检测是否从联系人返回
      this.getCustomerInfo(item);
    });
    //从订单过来
    if (this.$route.params.item1) {
      const data = JSON.parse(this.$route.params.item1);
      this.isFromOrderList = this.$route.params.isFromOrderList;
      this.isFromCopyOrder = this.$route.params.isFromCopyOrder;
      if (!this.$route.params.copy) {
        const orderNo = data.orderNo;
        localStorage.setItem("tenmillionOrderNoFramily", orderNo);
        Vue.prototype.isNeedOrderNo = true;
      }
      const storeOracleRequestVO = data.storeOracleRequestVO;
      this.formulaCode = storeOracleRequestVO.formulaCode;
      this.formulaName = storeOracleRequestVO.formulaName;
      this.accidentalInjuryInfos = storeOracleRequestVO.accidentalInjuryInfos;
      this.startDate = this.formatDate(
        new Date(storeOracleRequestVO.startDate)
      );
      this.endDate = this.formatDate(new Date(storeOracleRequestVO.endDate));
      this.selectDate = `${this.startDate} - ${this.endDate}`;
      // this.productName = storeOracleRequestVO.productName;
      this.sumPremium = storeOracleRequestVO.sumPremium;
      this.sumAmount = storeOracleRequestVO.sumAmount;
      this.isAgent = storeOracleRequestVO.isAgent;
      this.isSheBao = storeOracleRequestVO.isSheBao;
      this.aKZEXClause = storeOracleRequestVO.aKZEXClause;
      this.aEXTZClause = storeOracleRequestVO.aEXTZClause;
      this.aEXYLClause = storeOracleRequestVO.aEXYLClause;
      this.aEXZZClause = storeOracleRequestVO.aEXZZClause;
      this.agesArr = storeOracleRequestVO.agesArr || {};
      this.securityNum = storeOracleRequestVO.accidentalInjuryInfos.length;
      storeOracleRequestVO.accidentalInjuryInfos.forEach(item => {
        this.collectAgeArr.push(item.age);
      });
      this.agentInfo =
        (storeOracleRequestVO.agentInfo && {
          ...storeOracleRequestVO.agentInfo
        }) ||
        {};
      (this.appliInfo = {
        appliName: storeOracleRequestVO.appliName,
        appliIdentifyType: storeOracleRequestVO.appliIdentifyType,
        appliIdentifyNumber: storeOracleRequestVO.appliIdentifyNumber,
        appliBirthDate: storeOracleRequestVO.appliBirthDate,
        appliMobile: storeOracleRequestVO.appliMobile,
        appliNationality: storeOracleRequestVO.appliNationality,
        appliEMail: storeOracleRequestVO.appliEMail,
        appliIdentifyTypeName:
          storeOracleRequestVO.appliIdentifyType == "01"
            ? "身份证"
            : storeOracleRequestVO.appliIdentifyType == "03"
              ? "护照"
              : "港澳台居住证"
      }), //投保人
        (this.invoiceInfo = {
          ...storeOracleRequestVO.invoiceInfo
        });
      if (this.invoiceInfo.companyName) {
        this.checked = true;
        this.receiptMobile = this.invoiceInfo.mobile;
        this.receiptEmail = this.invoiceInfo.email;
      }
    } else {
      let startDate = (this.startDate = this.formatDate(
        new Date(new Date().getTime() + 24 * 60 * 60 * 1000)
      ));
      let endDate = new Date();
      endDate.setFullYear(endDate.getFullYear() + 1);
      endDate.setDate(endDate.getDate());
      this.endDate = endDate = this.formatDate(endDate);
      this.selectDate = `${startDate} - ${endDate}`;
      this.accidentalInjuryInfos = [];
      //获取当前页面缓存信息
      const millionHomeStep2 = localStorage.getItem("millionHomeStep2");
      //获取前一步缓存信息
      const millionHomeStep1 = localStorage.getItem("millionHomeStep1");
      //如果当前页面有值
      if (!this.$isNull(millionHomeStep2) && this.$isNull(millionHomeStep1)) {
        const millionHomeStep2Obj = JSON.parse(millionHomeStep2);
        //存储对象如果有值，并且当前数量一致
        if (
          millionHomeStep2Obj &&
          millionHomeStep2Obj.accidentalInjuryInfos &&
          millionHomeStep2Obj.accidentalInjuryInfos.length > 0
        ) {
          this.appliInfo = millionHomeStep2Obj.appliInfo;
          this.accidentalInjuryInfos =
            millionHomeStep2Obj.accidentalInjuryInfos; //被保人数组
          this.securityNum = +millionHomeStep2Obj.securityNum; //保障人数
          this.sumPremium = millionHomeStep2Obj.sumPremium; //总保费
          this.endDate = millionHomeStep2Obj.endDate; //结束时间
          this.startDate = millionHomeStep2Obj.startDate; //开始时间
          this.selectDate = millionHomeStep2Obj.selectDate;
          this.formulaName = millionHomeStep2Obj.formulaName; //方案名称
          this.formulaCode = millionHomeStep2Obj.formulaCode; //方案编码
          this.selectDate = `${this.startDate} - ${this.endDate}`;
          this.collectAgeArr = millionHomeStep2Obj.collectAgeArr;

          this.aKZEXClause = millionHomeStep2Obj.aKZEXClause || ""; //附加扩展恶性肿瘤特需医疗保险
          this.aEXTZClause = millionHomeStep2Obj.aEXTZClause || ""; //附加个人恶性肿瘤院外特种药品费用医疗保险
          this.aEXYLClause = millionHomeStep2Obj.aEXYLClause || ""; //附加个人恶性肿瘤赴日医疗保险
          this.aEXZZClause = millionHomeStep2Obj.aEXZZClause || ""; //附加个人恶性肿瘤扩展质子重离子医疗保险
        }
      } else if (
        !this.$isNull(millionHomeStep1) &&
        this.$isNull(millionHomeStep2)
      ) {
        let millionHomeStep1Obj = (this.formula = JSON.parse(millionHomeStep1));
        if (millionHomeStep1Obj) {
          this.agentInfo = millionHomeStep1Obj.agentInfo;
          this.isSheBao = millionHomeStep1Obj.isSheBao;
          this.securityNum = +millionHomeStep1Obj.securityNum; //保障人数
          this.sumPremium = millionHomeStep1Obj.sumPremium; //总保费
          this.formulaName = millionHomeStep1Obj.formulaName; //方案名称
          this.formulaCode = millionHomeStep1Obj.formulaCode; //方案编码
          this.endDate = millionHomeStep1Obj.endDate || this.endDate; //结束时间
          this.startDate = millionHomeStep1Obj.startDate || this.startDate; //开始时间

          this.appliInfo.appliName = millionHomeStep1Obj.appliName || ""; //投保人姓名
          this.appliInfo.appliIdentifyType = "01"; //证件类型
          this.appliInfo.appliIdentifyTypeName = "身份证"; //证件名称

          this.aKZEXClause = millionHomeStep1Obj.aKZEXClause || ""; //附加扩展恶性肿瘤特需医疗保险
          this.aEXTZClause = millionHomeStep1Obj.aEXTZClause || ""; //附加个人恶性肿瘤院外特种药品费用医疗保险
          this.aEXYLClause = millionHomeStep1Obj.aEXYLClause || ""; //附加个人恶性肿瘤赴日医疗保险
          this.aEXZZClause = millionHomeStep1Obj.aEXZZClause || ""; //附加个人恶性肿瘤扩展质子重离子医疗保险

          for (let i = 0; i < this.securityNum; i++) {
            let ageRegion = "0-4";
            //获取年龄段
            switch (i) {
              case 0: {
                ageRegion = millionHomeStep1Obj.ageOne;
                break;
              }
              case 1: {
                ageRegion = millionHomeStep1Obj.ageTwo;
                break;
              }
              case 2: {
                ageRegion = millionHomeStep1Obj.ageThree;
                break;
              }
              case 3: {
                ageRegion = millionHomeStep1Obj.ageFour;
                break;
              }
              case 4: {
                ageRegion = millionHomeStep1Obj.ageFive;
                break;
              }
              case 5: {
                ageRegion = millionHomeStep1Obj.ageSix;
                break;
              }
              case 6: {
                ageRegion = millionHomeStep1Obj.ageSeven;
                break;
              }
            }

            //被保人姓名
            let insure = {};
            if (millionHomeStep1Obj.accidentalInjuryInfos) {
              insure = millionHomeStep1Obj.accidentalInjuryInfos[i];
            }
            const insureInfo = {
              familyRelation: insure.familyRelation || "", //与投保人关系
              familyName: insure.familyName || "", //被保人姓名
              familyIdentifyType: insure.familyIdentifyType || "01", //默认身份证，被保人证件类型"01身份证", "02护照", "03港澳台居住证"
              familyIdentifyNo: insure.familyIdentifyNo || "", //被保人证件号码
              insuredBirthDate: insure.insuredBirthDate || "", //被保人生日
              mobile: insure.mobile || "", //被保人手机号
              insuredSheBaoType: insure.insuredSheBaoType || "", //被保人社保类型
              age: ageRegion //被保人年龄段
            };
            this.collectAgeArr.push(ageRegion);
            this.accidentalInjuryInfos.push(insureInfo);
          }
        }
      } else if (
        !this.$isNull(millionHomeStep1) &&
        !this.$isNull(millionHomeStep2)
      ) {
        const millionHomeStep2Obj = JSON.parse(millionHomeStep2);
        let millionHomeStep1Obj = (this.formula = JSON.parse(millionHomeStep1));
        if (millionHomeStep1Obj) {
          this.appliInfo = millionHomeStep2Obj.appliInfo;
          this.agentInfo = millionHomeStep1Obj.agentInfo;
          this.isSheBao = millionHomeStep1Obj.isSheBao;
          this.securityNum = +millionHomeStep1Obj.securityNum; //保障人数
          this.sumPremium = millionHomeStep1Obj.sumPremium; //总保费
          this.formulaName = millionHomeStep1Obj.formulaName; //方案名称
          this.formulaCode = millionHomeStep1Obj.formulaCode; //方案编码
          this.endDate = millionHomeStep1Obj.endDate || this.endDate; //结束时间
          this.startDate = millionHomeStep1Obj.startDate || this.startDate; //开始时间

          // this.appliInfo.appliName = millionHomeStep1Obj.appliName || ""; //投保人姓名
          // this.appliInfo.appliIdentifyType = "01"; //证件类型
          // this.appliInfo.appliIdentifyTypeName = "身份证"; //证件名称

          this.aKZEXClause = millionHomeStep1Obj.aKZEXClause || ""; //附加扩展恶性肿瘤特需医疗保险
          this.aEXTZClause = millionHomeStep1Obj.aEXTZClause || ""; //附加个人恶性肿瘤院外特种药品费用医疗保险
          this.aEXYLClause = millionHomeStep1Obj.aEXYLClause || ""; //附加个人恶性肿瘤赴日医疗保险
          this.aEXZZClause = millionHomeStep1Obj.aEXZZClause || ""; //附加个人恶性肿瘤扩展质子重离子医疗保险

          for (let i = 0; i < this.securityNum; i++) {
            let ageRegion = "0-4";
            //获取年龄段
            switch (i) {
              case 0: {
                ageRegion = millionHomeStep1Obj.ageOne;
                break;
              }
              case 1: {
                ageRegion = millionHomeStep1Obj.ageTwo;
                break;
              }
              case 2: {
                ageRegion = millionHomeStep1Obj.ageThree;
                break;
              }
              case 3: {
                ageRegion = millionHomeStep1Obj.ageFour;
                break;
              }
              case 4: {
                ageRegion = millionHomeStep1Obj.ageFive;
                break;
              }
              case 5: {
                ageRegion = millionHomeStep1Obj.ageSix;
                break;
              }
              case 6: {
                ageRegion = millionHomeStep1Obj.ageSeven;
                break;
              }
            }

            //被保人姓名
            let insure = {};
            if (millionHomeStep1Obj.accidentalInjuryInfos) {
              insure = millionHomeStep1Obj.accidentalInjuryInfos[i];
            }
            const insureInfo = {
              familyRelation: insure.familyRelation || "", //与投保人关系
              familyName: insure.familyName || "", //被保人姓名
              familyIdentifyType: insure.familyIdentifyType || "01", //默认身份证，被保人证件类型"01身份证", "02护照", "03港澳台居住证"
              familyIdentifyNo: insure.familyIdentifyNo || "", //被保人证件号码
              insuredBirthDate: insure.insuredBirthDate || "", //被保人生日
              mobile: insure.mobile || "", //被保人手机号
              insuredSheBaoType: insure.insuredSheBaoType || "", //被保人社保类型
              age: ageRegion //被保人年龄段
            };
            this.collectAgeArr.push(
              (millionHomeStep2Obj.accidentalInjuryInfos[i] &&
                millionHomeStep2Obj.accidentalInjuryInfos[i].age) ||
              ageRegion
            );

            this.accidentalInjuryInfos.push(
              millionHomeStep2Obj.accidentalInjuryInfos[i] || insureInfo
            );
          }
        }
      }
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
        this.initFormulaInfo();
        this.savePageInfo();
        //根据责任和人数获取总保额
        // this.getSumAmount();
        // //根据责任、人数和年龄获取总保费
        // this.updatePremiumsNum();
      });
    if (this.isFromCopyOrder) {
      this.goback();
      return;
    }
  },
  beforeRouteLeave (to, from, next) {
    if (to.name === "myCustomer") {
      from.meta.keepAlive = true;
      next();
    } else {
      from.meta.keepAlive = false;
      next();
    }
  },
  computed: {
    ...mapGetters("million", ["millionQuery"]),
    hasCamera () {
      return (
        this.appliInfo.appliIdentifyType == "01" &&
        window.WebViewJavascriptBridge
      );
    }
  },
  methods: {
    roShowDate () {
      if (this.appliInfo.appliIdentifyType == "01") {
        this.showDate = false;
        Toast("身份证证件类型生日不能选择");
      } else {
        this.showDate = true;
      }
    },
    ...mapActions("million", ["getQueryFormula"]),
    // 初始化
    initFormulaInfo () {
      if (this.securityNum <= 3) {
        this.formula = this.queryData.data.sfList[0];
      } else {
        this.formula = this.queryData.data.sfList[1];
      }
    },

    // 添加被保人
    addInsuredForm () {
      if (this.accidentalInjuryInfos.length >= 7) {
        return;
      }
      const insureInfo = {
        familyRelation: "", //与投保人关系
        familyName: "", //被保人姓名
        familyIdentifyType: "01", //默认身份证，被保人证件类型"01身份证", "02护照", "03港澳台居住证"
        familyIdentifyNo: "", //被保人证件号码
        insuredBirthDate: "", //被保人生日
        mobile: "", //被保人手机号
        insuredSheBaoType: "", //被保人社保类型
        age: "0-4" //被保人年龄段
      };

      //被保人数组
      this.accidentalInjuryInfos.push(insureInfo);
      this.collectAgeArr.push("0-4");

      //被保人人数
      this.securityNum = parseInt(this.securityNum) + 1;
    },
    clickProtocol (index) {
      //保存页面数据
      this.savePageInfo();
      const link = this.protocols[index].link;
      this.$router.push({ path: link });
    },
    // 身份证识别
    getOCRID () {
      const jsonObject = {
        identifyType: "1", //"0" - 识别行驶证; "1" - 识别身份证
        side: "face" //"face"：正面；"back": 反面;
      };

      this.$native.identifyOCR(jsonObject, data => {
        let tempData = JSON.parse(data);
        if (tempData.resultCode == "1") {
          const { name, cardNo } = tempData.responseBody;

          this.appliInfo.appliName = name;
          this.appliInfo.appliIdentifyNumber = cardNo;
          this.appliInfo.appliIdentifyType = "01";
          this.appliInfo.appliBirthDate = this.$getIdNum(cardNo);
        }
      });
    },
    getCustomerInfo (item) {
      //从联系人选择返回
      const customer = JSON.parse(item);

      //判断是否有联系人信息
      if (customer && customer.custName) {
        const index = customer.isInsured;
        const certificateNo = customer.certificateNo;
        const certificateType = customer.certificateType;
        const custName = customer.custName;
        let phoneNumber = "";

        if (customer.mobileList && customer.mobileList.length > 0) {
          phoneNumber = customer.mobileList[0].phoneNumber;
        }

        let birthday = "";
        //根据身份证号码获取生日
        if (certificateType == "01") {
          birthday = this.$getIdNum(certificateNo);
        }
        const certificateName = this.identifyTypeS(certificateType);

        //被保人位置
        const pos = index - 1;

        if (index == "8") {
          this.appliInfo.appliName = custName
            ? custName
            : this.appliInfo.appliName; //投保人姓名
          this.appliInfo.appliIdentifyType = certificateType
            ? certificateType
            : this.appliInfo.appliIdentifyType; //证件类型
          this.appliInfo.appliIdentifyTypeName = certificateName
            ? certificateName
            : this.appliInfo.appliIdentifyTypeName; //证件名称
          this.appliInfo.appliIdentifyNumber = certificateNo
            ? certificateNo
            : this.appliInfo.appliIdentifyNumber; //证件号码
          this.appliInfo.appliBirthDate = birthday
            ? birthday
            : this.appliInfo.appliBirthDate; //出生日期  ...
          this.appliInfo.appliMobile = phoneNumber
            ? phoneNumber
            : this.appliInfo.appliMobile; //手机号
        } else if (this.accidentalInjuryInfos[pos]) {
          this.accidentalInjuryInfos[pos].familyName = custName
            ? custName
            : this.accidentalInjuryInfos[pos].familyName; //被保人姓名
          this.accidentalInjuryInfos[pos].familyIdentifyType = certificateType
            ? certificateType
            : this.accidentalInjuryInfos[pos].familyIdentifyType; //证件类型 //默认身份证，被保人证件类型"01身份证", "02护照", "03港澳台居住证"

          this.accidentalInjuryInfos[pos].familyIdentifyNo = certificateNo
            ? certificateNo
            : this.accidentalInjuryInfos[pos].familyIdentifyNo; //被保人证件号码
          this.accidentalInjuryInfos[pos].insuredBirthDate = birthday
            ? birthday
            : this.accidentalInjuryInfos[pos].insuredBirthDate; //被保人生日
          this.accidentalInjuryInfos[pos].mobile = phoneNumber
            ? phoneNumber
            : this.accidentalInjuryInfos[pos].mobile; //被保人手机号
        }
      }
    },
    // 身份证类型转化
    identifyTypeS (type) {
      let typeVal = "";
      switch (type) {
        case "01":
          typeVal = "身份证";
          break;
        case "03":
          typeVal = "护照";
          break;
        case "28":
          typeVal = "港澳台居住证";
          break;
      }
      return typeVal;
    },
    familyRelationAction (index, value) {
      if (this.accidentalInjuryInfos[index - 1].familyRelation == "01") {
        this.accidentalInjuryInfos[index - 1].familyRelation = value;
        this.accidentalInjuryInfos[index - 1].familyName = "";
        // this.accidentalInjuryInfos[index - 1].familyIdentifyType = "01";
        this.accidentalInjuryInfos[index - 1].familyIdentifyNo = "";
        this.accidentalInjuryInfos[index - 1].insuredBirthDate = "";
        this.accidentalInjuryInfos[index - 1].mobile = "";
        this.accidentalInjuryInfos[index - 1].insuredSheBaoType = "";
      } else {
        this.accidentalInjuryInfos[index - 1].familyRelation = value;
      }
    },
    familyNameAction (index, value) {
      this.accidentalInjuryInfos[index - 1].familyName = value;
    },
    familyIdentifyTypeAction (index, value) {
      this.accidentalInjuryInfos[index - 1].familyIdentifyType = value;
    },
    familyIdentifyNoAction (index, value) {
      this.accidentalInjuryInfos[index - 1].familyIdentifyNo = value;

      if (checkIdcard(value) == "验证通过") {
        //获取证件类型的生日
        this.accidentalInjuryInfos[index - 1].insuredBirthDate = this.$getIdNum(
          value
        );

        //根据责任和人数获取总保额
        this.getSumAmount();
        //根据责任、人数和年龄获取总保费
        this.updatePremiumsNum();
      }
    },
    insuredBirthDateAction (index, value) {
      this.accidentalInjuryInfos[index - 1].insuredBirthDate = value;

      //根据责任和人数获取总保额
      this.getSumAmount();
      //根据责任、人数和年龄获取总保费
      this.updatePremiumsNum();
    },
    mobileAction (index, value) {
      this.accidentalInjuryInfos[index - 1].mobile = value;
    },
    insuredSheBaoTypeAction (index, value) {
      this.accidentalInjuryInfos[index - 1].insuredSheBaoType = value;
    },
    removeAllSpace (str) {
      return str.replace(/\s+/g, "");
    },
    // 获取证件号码的出生日期
    handBlur (val) {
      // val = this.removeAllSpace(val)
      // this.appliInfo.appliIdentifyNumber = val
      if (
        val &&
        checkIdcard(val) !== "验证通过" &&
        this.appliInfo.appliIdentifyType == "01"
      ) {
        this.appliInfo.appliBirthDate = "";
        return Toast(checkIdcard(val));
      }
      if (this.appliInfo.appliIdentifyType == "01")
        this.appliInfo.appliBirthDate = this.$getIdNum(val);
    },
    //保存页面数据
    savePageInfo () {
      //根据责任和人数获取总保额
      this.getSumAmount();
      //根据责任、人数和年龄获取总保费
      this.updatePremiumsNum();
      // const millionHomeStep1 = localStorage.getItem("millionHomeStep1");
      const params = {
        appliInfo: this.appliInfo, //投保人
        accidentalInjuryInfos: this.accidentalInjuryInfos, //被保人数组
        securityNum: this.securityNum, //保障人数
        sumPremium: this.sumPremium, //总保费
        startDate: this.startDate, //开始时间
        endDate: this.endDate, //结束时间
        selectDate: this.selectDate,
        formulaName: this.formulaName, //方案名称; //方案名称
        formulaCode: this.formulaCode, //方案代码
        collectAgeArr: this.collectAgeArr,
        sumAmount: this.sumAmount, //总保额
        ageArr: {},
        invoiceInfo: this.invoiceInfo,
        aKZEXClause: this.aKZEXClause || "", //附加扩展恶性肿瘤特需医疗保险
        aEXTZClause: this.aEXTZClause || "", //附加个人恶性肿瘤院外特种药品费用医疗保险
        aEXYLClause: this.aEXYLClause || "", //附加个人恶性肿瘤赴日医疗保险
        aEXZZClause: this.aEXZZClause || "", //附加个人恶性肿瘤扩展质子重离子医疗保险
        agentInfo: this.agentInfo,
        isSheBao: this.isSheBao,
        ageOne: this.ageOne,
        ageTwo: this.ageTwo,
        ageThree: this.ageThree,
        ageFour: this.ageFour,
        ageFive: this.ageFive,
        ageSix: this.ageSix,
        ageSeven: this.ageSeven,
        agesArr: this.agesArr
      };
      let arr = [
        "ageOne",
        "ageTwo",
        "ageThree",
        "ageFour",
        "ageFive",
        "ageSix",
        "ageSeven"
      ];
      this.collectAgeArr.forEach((item, index) => {
        params.ageArr[arr[index]] = item;
      });
      //保存第一个页面填写信息
      localStorage.setItem("millionHomeStep2", JSON.stringify(params));
    },
    enter () {
      this.accidentalInjuryInfos.forEach(item => {
        if (item.familyRelation == "01") {
          item.familyRelation = "01";
          item.familyName = this.appliInfo.appliName;
          item.familyIdentifyType = this.appliInfo.appliIdentifyType;
          item.familyIdentifyNo = this.appliInfo.appliIdentifyNumber;
          item.insuredBirthDate = this.appliInfo.appliBirthDate;
          item.mobile = this.appliInfo.appliMobile;
          // item.insuredSheBaoType = this.appliInfo
        }
      });
      if (!this.allowedProtocol) {
        return Toast("请阅读协议条款，确认并同意！");
      }
      // 保险人身份效验
      if (this.appliInfo.appliName == "") {
        return Toast("投保人姓名不能为空");
      }
      if (this.appliInfo.appliIdentifyType == "") {
        return Toast("投保人证件类型不能为空");
      }
      if (this.appliInfo.appliMobile == "") {
        return Toast("投保人手机号不能为空");
      }
      const appliIdentifyNumber = this.appliInfo.appliIdentifyNumber.trim();
      if (!appliIdentifyNumber) {
        return Toast("投保人证件号码不能为空");
      }
      if (
        appliIdentifyNumber &&
        checkIdcard(appliIdentifyNumber) !== "验证通过" &&
        !(this.appliInfo.appliIdentifyType == "03")
      ) {
        return Toast(checkIdcard(appliIdentifyNumber));
      }
      if (this.appliInfo.appliBirthDate == "") {
        return Toast("投保人出生日期不能为空");
      }
      let reg = /^1[3|4|5|6|8|7|9][0-9]\d{8}$/;
      if (!reg.test(this.appliInfo.appliMobile.trim())) {
        return Toast("投保人手机号格式不正确");
      }
      const emailValue = this.appliInfo.appliEMail.trim();
      if (emailValue && !emailCheck(emailValue)) {
        return Toast("投保人邮箱格式不正确");
      }
      if (!this.$checkBirth(this.appliInfo.appliBirthDate)) {
        return Toast("投保人出生日期有误");
      }
      if (
        this.appliInfo.appliIdentifyType == "01" &&
        this.$getIdNum(this.appliInfo.appliIdentifyNumber) !=
        this.appliInfo.appliBirthDate
      ) {
        return Toast("投保人证件号对应日期与出生日期不符");
      }
      let appliAge = this.$getAge(this.appliInfo.appliBirthDate);
      if (parseInt(appliAge) < 16) {
        return Toast("投保人年龄不能小于16");
      }
      let arr = this.accidentalInjuryInfos;
      let arrOwner = [];
      let lovePerson = [];
      let parants = [];
      let children = [];
      for (let i = 0; i < arr.length; i++) {
        if (arr[i] !== undefined) {
          if (arr[i].familyRelation == "") {
            return Toast("与投保人关系不能为空");
          }
          if (arr[i].familyName == "") {
            return Toast("被保人" + (i + 1) + "姓名不能为空");
          }
          if (arr[i].familyIdentifyType == "") {
            return Toast("被保人" + (i + 1) + "证件类型不能为空");
          }
          if (arr[i].familyIdentifyNo == "") {
            return Toast("被保人" + (i + 1) + "证件号码不能为空");
          }
          if (arr[i].insuredBirthDate == "") {
            return Toast("被保人" + (i + 1) + "生日不能为空");
          }
          let insuredAge = this.$getAge(arr[i].insuredBirthDate);
          var s1 = arr[i].insuredBirthDate;
          s1 = new Date(s1.replace(/-/g, "/"));
          let s2 = new Date();
          var days = s2.getTime() - s1.getTime();
          var time = parseInt(days / (1000 * 60 * 60 * 24));
          if (time < 30) {
            Toast("被保人" + (i + 1) + "小于30天不能投保");
            return;
          }
          if (+insuredAge > 60) {
            return Toast("被保人" + (i + 1) + "大于60岁不能投保");
          }
          // if (arr[i].mobile == "") {
          //   return Toast("被保人" + (i + 1) + "手机号不能为空");
          // }
          if (
            arr[i].familyIdentifyType == "01" &&
            this.$getIdNum(arr[i].familyIdentifyNo) != arr[i].insuredBirthDate
          ) {
            return Toast("被保人" + (i + 1) + "证件号对应日期与出生日期不符");
          }
          // if (arr[i].insuredSheBaoType == "") {
          //   return Toast("被保人" + (i + 1) + "社保类型不能为空");
          // }
          let reg = /^1[3|4|5|6|8|7|9][0-9]\d{8}$/;
          if (arr[i].mobile && !reg.test(arr[i].mobile.trim())) {
            return Toast("被保人" + (i + 1) + "手机格式不正确");
          }
          if (arr[i].familyRelation == "01") {
            arrOwner.push(arr[i].familyRelation);
          }
          if (arr[i].familyRelation == "10") {
            lovePerson.push(arr[i].familyRelation);
          }
          if (arr[i].familyRelation == "50") {
            parants.push(arr[i].familyRelation);
          }
          if (arr[i].familyRelation == "40") {
            children.push(arr[i].familyRelation);
          }
        }
      }
      if (arrOwner.length > 1) {
        return Toast("本人只能一个人");
      }
      if (lovePerson.length > 1) {
        return Toast("配偶只能一个人");
      }
      if (parants.length > 2) {
        return Toast("父母不能超过两个");
      }
      if (children.length > 3) {
        return Toast("孩子不能超过三个");
      }
      if (this.checked && this.isShould) {
        let receiptMobile = this.receiptMobile.trim();
        let receiptEmail = this.receiptEmail.trim();
        if (!receiptMobile) {
          return Toast("发票接收手机号不能为空");
        }
        if (!receiptEmail) {
          return Toast("发票接收邮箱不能为空");
        }
        let reg = /^1[3|4|5|6|8|7|9][0-9]\d{8}$/;
        if (!reg.test(receiptMobile)) {
          return Toast("发票接收手机号格式不正确");
        }
        if (!emailCheck(receiptEmail)) {
          return Toast("发票接收邮箱格式不正确");
        }
      }
      //保存页面数据
      this.savePageInfo();
      let millionHomeStep2 = JSON.parse(
        localStorage.getItem("millionHomeStep2")
      );
      let params2 = {};
      for (let i = 0; i < millionHomeStep2.accidentalInjuryInfos.length; i++) {
        let key = "";
        switch (i) {
          case 0: {
            key = "";
            break;
          }
          case 1: {
            key = "Two";
            break;
          }
          case 2: {
            key = "Three";
            break;
          }
          case 3: {
            key = "Four";
            break;
          }
          case 4: {
            key = "Five";
            break;
          }
          case 5: {
            key = "Six";
            break;
          }
          case 6: {
            key = "Seven";
            break;
          }
        }
        let appliIdentityObj = "appliIdentity" + key;
        let insuredNameObj = "insuredName" + key;
        let insuredIdentifyTypeObj = "insuredIdentifyType" + key;
        let insuredIdentifyNumberObj = "insuredIdentifyNumber" + key;
        let insuredMobileObj = "insuredMobile" + key;
        let insuredBirthDateObj = "insuredBirthDate" + key;
        let ageObj = "";
        if (i == 0) {
          ageObj = "ageOne";
        } else {
          ageObj = "age" + key;
        }

        let insuredSheBaoTypeObj = "insuredSheBaoType" + key;
        params2[appliIdentityObj] =
          millionHomeStep2.accidentalInjuryInfos[i].familyRelation;
        params2[insuredNameObj] =
          millionHomeStep2.accidentalInjuryInfos[i].familyName;
        params2[insuredIdentifyTypeObj] =
          millionHomeStep2.accidentalInjuryInfos[i].familyIdentifyType;
        params2[insuredIdentifyNumberObj] =
          millionHomeStep2.accidentalInjuryInfos[i].familyIdentifyNo;
        params2[insuredMobileObj] =
          millionHomeStep2.accidentalInjuryInfos[i].mobile;
        params2[insuredBirthDateObj] =
          millionHomeStep2.accidentalInjuryInfos[i].insuredBirthDate;
        params2[ageObj] = millionHomeStep2.accidentalInjuryInfos[i].age;
        params2[insuredSheBaoTypeObj] =
          millionHomeStep2.accidentalInjuryInfos[i].insuredSheBaoType;
      }
      const defaultData = {
        //开始默认数据
        storeCode:
          this.$GetRequest("storeCode", true) ||
          localStorage.getItem("storeCode"),
        productCode: "FM51",
        riskCode: "WTN",
        formulaSize: "1"
      };
      let params = {
        ...millionHomeStep2.appliInfo,
        accidentalInjuryInfos: millionHomeStep2.accidentalInjuryInfos, //被保人数组
        ...params2,
        ...defaultData,
        startDate: millionHomeStep2.startDate, //开始时间
        endDate: millionHomeStep2.endDate, //结束时间
        ...millionHomeStep2.ageArr,
        sumPremium: millionHomeStep2.sumPremium,
        securityNum: millionHomeStep2.securityNum,
        sumAmount: millionHomeStep2.sumAmount,
        formulaCode: millionHomeStep2.formulaCode,
        formulaName: millionHomeStep2.formulaName,
        invoiceInfo: { ...millionHomeStep2.invoiceInfo },
        ...millionHomeStep2,
        orderNo: this.isNeedOrderNo
          ? localStorage.getItem("tenmillionOrderNoFramily")
          : ""
      };
      //暂存保单
      this.zancun(params);
      this.$router.push({ name: "insuranceinfoConfirm" });
    },
    // 暂存方法
    zancun (params = {}) {
      const millionHomeStep2 = JSON.parse(
        localStorage.getItem("millionHomeStep2")
      );

      var zcParams = {
        formulaCode: millionHomeStep2.formulaCode,
        insuranceCode: "WTN",
        storeOracleRequestVO: {
          ...params
        },
        orderNo: this.isNeedOrderNo
          ? localStorage.getItem("tenmillionOrderNoFramily")
          : ""
      };
      insureStatusZc(zcParams).then(res => {
        if (res.code == "1") {
          localStorage.setItem("tenmillionOrderNoFramily", res.data.orderNo);
          Vue.prototype.isNeedOrderNo = true;
        }
      });
    },
    goback () {
      if ("scrollRestoration" in history) {
        history.scrollRestoration = "manual"; //改为manual之后，就不会记录滚动位置
      }
      if (this.isFromOrderList) {
        this.$router.replace({
          name: "millionHome",
          params: {
            isFromOrderList: true
          }
        });
      } else {
        //统一返回，如果直接从壳跳进来，调用壳返回
        this.$globalBack();
      }
    },
    card_confirm (value, index) {
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
      this.appliIdentifyType = idCode;
      //证件类型
      this.appliInfo.appliIdentifyType = idCode;
      this.appliInfo.appliIdentifyTypeName = value;
      this.cardTypeInfo.picker = false;
    },

    selectDateConfirm (date) {
      //出生日期
      this.appliBirthDate = this.formatDate(date);
      //投保人出生日期
      this.appliInfo.appliBirthDate = this.formatDate(date);
      this.showDate = false;
    },
    // 选择日期区间方法
    selectDateConfirmqj (date) {
      let startDate = this.formatDate(date);
      let endDate = new Date(date.getTime());
      endDate.setFullYear(endDate.getFullYear() + 1);
      endDate.setDate(endDate.getDate() - 1);
      this.selectDate = `${startDate} - ${this.formatDate(endDate)}`;
      this.startDate = startDate;
      this.endDate = this.formatDate(endDate);
      this.showDate3 = false;
    },
    // 格式化时间
    formatDate (date) {
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
    ...mapMutations("million", {
      setMillionData: "SETMILLIONDATA"
    }),
    // 跳转投保人联系人界面
    selectInsuredMethod (isInsured) {
      this.bool = false;
      let parms = {
        custType: "1",
        insureType: "3",
        isInsured: isInsured
      };
      //保存页面数据
      this.savePageInfo();
      this.$router.push({ name: "myCustomer", query: { item: parms } });
    },
    // // 同投被保人
    toggleSameInsured (a) {
      if (a == 1) {
        this.isSameInsured = true;
        this.invoiceInfo.mobile = this.appliInfo.appliMobile;
        this.receiptEmail = this.invoiceInfo.email = this.appliInfo.appliEMail;
        this.invoiceInfo.companyName = this.appliInfo.appliName;
        this.receiptMobile = this.appliInfo.appliMobile;
        this.invoiceInfo.taxPayerNo = this.appliInfo.appliIdentifyNumber;
      } else {
        this.isSameInsured = false;
        this.receiptMobile = this.invoiceInfo.mobile = this.accidentalInjuryInfos[0].mobile;
        this.receiptEmail = this.invoiceInfo.email = "";
        this.invoiceInfo.companyName = this.accidentalInjuryInfos[0].familyName;
        this.invoiceInfo.taxPayerNo = this.accidentalInjuryInfos[0].familyIdentifyNo;
      }
    },
    toggleChecked () {
      if (this.checked) {
        this.receiptMobile = this.appliInfo.appliMobile;
        this.receiptEmail = this.appliInfo.appliEMail;
        this.invoiceInfo = {
          mobile: this.receiptMobile,
          email: this.receiptEmail,
          companyName: this.appliInfo.appliName,
          invoiceType: "3",
          customerType: "1",
          taxPayerType: "4",
          taxPayerNo: this.appliInfo.appliIdentifyNumber
        };
      } else {
        this.invoiceInfo = {
          mobile: "",
          email: "",
          companyName: "",
          invoiceType: "",
          customerType: "",
          taxPayerType: "",
          taxPayerNo: ""
        };
      }
    },
    toggleShould (a) {
      if (a == 1) {
        this.isShould = true;
        this.invoiceInfo.mobile = this.appliInfo.appliMobile;
        this.invoiceInfo.email = this.appliInfo.appliEMail;
        this.invoiceInfo.invoiceType = "3";
      } else {
        this.isShould = false;
        this.invoiceInfo.mobile = "";
        this.invoiceInfo.email = "";
        this.invoiceInfo.invoiceType = "2";
      }
    },
    //获取总保额
    getSumAmount () {
      //基础保额
      this.sumAmount = parseInt(JSON.parse(this.formula.amountsJson)["JZH"]);

      //扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)
      if (!this.$isNull(this.aKZEXClause) && this.aKZEXClause == "Y") {
        this.sumAmount += parseInt(
          JSON.parse(this.formula.amountsJson)["KZEX"]
        );
        this.aKZEXClause = "Y";
      } else {
        this.aKZEXClause = "N";
      }

      //恶性肿瘤院外特种药品费用医疗责任
      if (!this.$isNull(this.aEXTZClause) && this.aEXTZClause == "Y") {
        this.sumAmount += parseInt(
          JSON.parse(this.formula.amountsJson)["EXTZ"]
        );
        this.aEXTZClause = "Y";
      } else {
        this.aEXTZClause = "N";
      }

      //恶性肿瘤赴日医疗责任
      if (!this.$isNull(this.aEXYLClause) && this.aEXYLClause == "Y") {
        this.sumAmount += parseInt(
          JSON.parse(this.formula.amountsJson)["EXYL"]
        );
        this.aEXYLClause = "Y";
      } else {
        this.aEXYLClause = "N";
      }

      //恶性肿瘤扩展质子重离子医疗责任
      if (!this.$isNull(this.aEXZZClause) && this.aEXZZClause == "Y") {
        this.sumAmount += parseInt(
          JSON.parse(this.formula.amountsJson)["EXZZ"]
        );
        this.aEXZZClause = "Y";
      } else {
        this.aEXZZClause = "N";
      }

      //总保额
      this.sumAmount *= parseInt(this.securityNum);
    },
    // 初始化保费
    updatePremiumsNum () {
      //初始化方案信息
      this.initAgesIndex();
      this.totalPrice = 0;

      //如果选择附加责任，计算基础年龄区段
      this.totalPrice = parseInt(
        (parseInt(JSON.parse(this.formula.premiumsJson)["0-4"].BF) +
          parseInt(this.getAddPremiumByAge("0-4"))) *
        this.securityNum
      );
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
      this.sumPremium = this.totalPrice;
    },
    //根据被保人生日初始化年龄段和位置
    initAgesIndex () {
      if (this.accidentalInjuryInfos && this.accidentalInjuryInfos.length > 0) {
        const len = this.accidentalInjuryInfos.length;
        //初始化年龄段
        this.ageOne = ""; //传递被保人年龄段
        this.ageTwo = "";
        this.ageThree = "";
        this.ageFour = "";
        this.ageFive = "";
        this.ageSix = "";
        this.ageSeven = "";

        for (let index = 0; index < len; index++) {
          const insureObj = this.accidentalInjuryInfos[index];
          if (insureObj) {
            //获取被保人生日
            const birthday = insureObj.insuredBirthDate;

            //根据生日获取被保人年龄
            // let ages = this.$getAge(birthday) || insureObj.age;
            let ages = this.$getAge(birthday);
            if (/-/.test(ages)) ages = ages.split("-")[0];
            const ageRegion = this.getAgeRegion(ages);

            //年龄段赋值
            insureObj.age = ageRegion.ageRegion;

            const pos = index + 1;
            switch (pos) {
              case 1:
                //第一个人的年龄段序号和年龄段文本
                this.agesArr.ages1 = ageRegion.ageIndex;
                this.ageOne = ageRegion.ageRegion;
                break;
              case 2:
                //第二个人的年龄段序号和年龄段文本
                this.agesArr.ages2 = ageRegion.ageIndex;
                this.ageTwo = ageRegion.ageRegion;
                break;
              case 3:
                //第三个人的年龄段序号和年龄段文本
                this.agesArr.ages3 = ageRegion.ageIndex;
                this.ageThree = ageRegion.ageRegion;
                break;
              case 4:
                //第四个人的年龄段序号和年龄段文本
                this.agesArr.ages4 = ageRegion.ageIndex;
                this.ageFour = ageRegion.ageRegion;
                break;
              case 5:
                //第五个人的年龄段序号和年龄段文本
                this.agesArr.ages5 = ageRegion.ageIndex;
                this.ageFive = ageRegion.ageRegion;
                break;
              case 6:
                //第六个人的年龄段序号和年龄段文本
                this.agesArr.ages6 = ageRegion.ageIndex;
                this.ageSix = ageRegion.ageRegion;
                break;
              case 7:
                //第七个人的年龄段序号和年龄段文本
                this.agesArr.ages7 = ageRegion.ageIndex;
                this.ageSeven = ageRegion.ageRegion;
                break;
            }
          }
        }
      }
    },
    getAgeRegion (age) {
      //获取年龄数字
      const ageNum = parseInt(age);
      let ageIndex = 1;
      let ageRegion = "0-4";

      if (ageNum >= 0 && ageNum <= 4) {
        ageIndex = 1;
        ageRegion = "0-4";
      } else if (ageNum >= 5 && ageNum <= 10) {
        ageIndex = 2;
        ageRegion = "5-10";
      } else if (ageNum >= 11 && ageNum <= 15) {
        ageIndex = 3;
        ageRegion = "11-15";
      } else if (ageNum >= 16 && ageNum <= 20) {
        ageIndex = 4;
        ageRegion = "16-20";
      } else if (ageNum >= 21 && ageNum <= 25) {
        ageIndex = 5;
        ageRegion = "21-25";
      } else if (ageNum >= 26 && ageNum <= 30) {
        ageIndex = 6;
        ageRegion = "26-30";
      } else if (ageNum >= 31 && ageNum <= 35) {
        ageIndex = 7;
        ageRegion = "31-35";
      } else if (ageNum >= 36 && ageNum <= 40) {
        ageIndex = 8;
        ageRegion = "36-40";
      } else if (ageNum >= 41 && ageNum <= 45) {
        ageIndex = 9;
        ageRegion = "41-45";
      } else if (ageNum >= 46 && ageNum <= 50) {
        ageIndex = 10;
        ageRegion = "46-50";
      } else if (ageNum >= 51 && ageNum <= 55) {
        ageIndex = 11;
        ageRegion = "51-55";
      } else if (ageNum >= 56 && ageNum <= 60) {
        ageIndex = 12;
        ageRegion = "56-60";
      } else {
        ageIndex = 13;
        ageRegion = "61-100";
      }

      let obj = {
        ageIndex,
        ageRegion
      };

      return obj;
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
      this.resultPrice = 0;

      //扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)
      if (!this.$isNull(this.aKZEXClause) && this.aKZEXClause == "Y") {
        this.resultPrice += parseInt(
          JSON.parse(this.formula.premiumsJson)[ageSection].KZEX
        );
      } else {
        this.aKZEXClause = "N";
      }

      //恶性肿瘤院外特种药品费用医疗责任
      if (!this.$isNull(this.aEXTZClause) && this.aEXTZClause == "Y") {
        this.resultPrice += parseInt(
          JSON.parse(this.formula.premiumsJson)[ageSection].EXTZ
        );
      } else {
        this.aEXTZClause = "N";
      }

      //恶性肿瘤赴日医疗责任
      if (!this.$isNull(this.aEXYLClause) && this.aEXYLClause == "Y") {
        this.resultPrice += parseInt(
          JSON.parse(this.formula.premiumsJson)[ageSection].EXYL
        );
      } else {
        this.aEXYLClause = "N";
      }

      //恶性肿瘤扩展质子重离子医疗责任
      if (!this.$isNull(this.aEXZZClause) && this.aEXZZClause == "Y") {
        this.resultPrice += parseInt(
          JSON.parse(this.formula.premiumsJson)[ageSection].EXZZ
        );
      } else {
        this.aEXZZClause = "N";
      }

      return this.resultPrice;
    }
  }
};
</script>
<style lang="less" scoped>
.writeForm {
  box-sizing: border-box;
  padding-top: 2rem;
  .add-insured-form {
    margin: 14px auto 0 auto;
    width: 111px;
    height: 27px;
    border-radius: 14px;
    border: 1px solid rgb(59, 138, 254);
    font-size: 14px;
    text-align: center;
    line-height: 27px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgb(59, 138, 254);
  }
  .card {
    margin-bottom: 0.2rem;
    .card-img {
      width: 20px;
      height: 20px;
      margin: -4px 0 -4px 5px;
    }
  }
  .left13 {
    display: flex;
    align-items: center;
    font-size: 16px;
    color: #969799;
  }
  /deep/ .van-field__control {
    text-align: right;
  }
  .date {
    .van-cell__title,
    .van-cell__value {
      flex: none;
    }
    .van-cell__title {
      width: 25%;
      flex-shrink: 0;
    }
    .van-cell__value {
      width: 75%;
      flex-shrink: 1;
    }
    .van-cell__value {
      color: #323233;
    }
  }
  .title-container {
    height: 53px;
    margin-bottom: 0px;
    background-color: white;

    .title-img {
      margin: 8px 5px 4.5px 4.5px;
    }
  }

  .title-text {
    font-size: 18px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    line-height: 25px;
  }
  .card-title-text {
    font-size: 14px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    line-height: 25px;

    img {
      margin: -5px 3px;
    }
  }
  .title-text1 {
    font-size: 18px;
    font-family: PingFangSC-Semibold, PingFang SC;
    color: #333333;
    line-height: 25px;
  }
  .tip-container {
    margin: 0 15px;
    height: 34px;
    background: #3b8afe1a;
    border-radius: 2px;
  }
  .tip-icon {
    margin: 1px 4px 0 5px;
    width: 15px;
    height: 15px;
  }
  .tip-text {
    font-size: 13px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #3b8afeff;
    line-height: 19px;
    white-space: nowrap;
  }
  .select-button {
    width: 70px;
    height: 27px;
    margin-left: 10px;
    background: white;
    border-radius: 14px;
    color: #e5e5e5;
    border: 1px solid #e5e5e5;
    font-size: 11px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    line-height: 27px;
    white-space: nowrap;
    padding: 0;
  }
  .selected-button {
    color: #3b8afe;
    border: 1px solid #3b8afe;
  }
  .select-customer-text {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    // font-weight: 600;
    color: #3b8afe;
    line-height: 20px;
    margin-right: 12px;
    img {
      width: 20px;
      height: 20px;
    }
  }
  .select-customer-icon {
    width: 20px;
    height: 20px;
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
  .allow-protocol-check-box {
    margin-right: 0.3rem;
  }
  .tabbar {
    position: fixed; // todo suncao fixed
    -webkit-overflow-scroll: touch;
    bottom: 0;
    display: flex;
    width: 100%;
    z-index: 999;
    .price {
      flex: 1;
      text-align: center;
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #fff;
      font-size: 12px;

      span {
        color: #fc820e;
        font-size: 17px;
        margin: 0 11px;
      }
    }
    .btn {
      flex: 1;
      button {
        width: 100%;
      }
    }
    .protocolLink {
      margin: 11px 0 11px 10px;
    }
  }
}
</style>
