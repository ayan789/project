<template>
  <!-- insuranceInfoInputFormcreate -->
  <!-- 个人版投保信息填写 -->
  <div class="tenmillionForm" id="tenmillionForm">
    <globalNav :title="globalNav.title" @clickBack="goback" />
    <!-- 标题 -->
    <van-row class="rowTitle" type="flex" align="center">
      <van-col class="title-img">
        <img src="@/assets/common/default1.png" alt="edit" />
      </van-col>
      <van-col class="title-text">{{ productTit }}</van-col>
    </van-row>
    <!-- 动态表单元素 -->
    <form-create
      v-model="formCreateApi"
      :rule="formCreateRule"
      :option="option"
    />
    <!-- 出生日期 popup-->
    <div class="date">
      <van-popup
        v-model="showDate"
        position="bottom"
        :style="{ height: '70%' }"
      >
        <van-datetime-picker
          v-model="currentDate"
          type="date"
          @confirm="selectBirthDateConfirm"
          @cancel="showDate = false"
          :min-date="minDate"
          :max-date="maxDate"
        />
      </van-popup>
    </div>
    <!-- 保障期限 年月日-->
    <div class="card">
      <van-cell
        :border="false"
        center
        class="card-title-container"
        style="background:#f7f7f7;"
      >
        <template slot="title">
          <div class="card-title-text">
            <img src="@/assets/common/icon6.png" alt="edit" />
            保障期限
          </div>
        </template>
      </van-cell>
      <van-cell title="保障年限" :value="giPeriod" />
      <!--选择日期区间组件  -->
      <div class="date">
        <van-cell
          :value="startDate"
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
        <van-cell
          :value="endDate"
          required
          label-width="80px"
          @click="showDate4 = true"
          class="rightCenter"
        >
          <template slot="title">
            <span>结束日期</span>
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
            :max-date="maxDate3"
            :min-date="minDate3"
            type="date"
            @confirm="selectDateConfirmStartDate"
            @cancel="showDate3 = false"
          />
        </van-popup>
        <van-popup
          v-model="showDate4"
          position="bottom"
          :style="{ height: '70%' }"
        >
          <van-datetime-picker
            v-model="currentDate4"
            :max-date="maxDate4"
            :min-date="minDate4"
            type="date"
            @confirm="selectDateConfirmEndDate"
            @cancel="showDate4 = false"
          />
        </van-popup>
      </div>
    </div>
    <!-- 开票信息 缺企业信息-->

    <div class="billingInfo policyholdersInfo top10">
      <ul>
        <li class="infoTitle">
          <div>
            <div class="personTitle">
              <img :src="beibao" alt="" />
            </div>
            <span class="policyholdersInfoTitle">开票信息</span>
          </div>

          <span class="selectInsuredInfo rightCenter">
            <van-switch
              v-model="showBilling"
              size="14px"
              @change="showBillingSwitch"
            />
          </span>
        </li>
        <li class="eleInvoiceTip" v-show="showBilling">
          <van-icon name="question" class="right5 verticalMid" />
          <span>电子发票与纸质发票具有同等效力，可作为报销凭据</span>
        </li>
        <li v-show="showBilling && !isGroup" class="insideLine">
          <span>是否需要电子发票</span>
          <span class="rightCenter">
            <button
              class="right10"
              :class="[isNeedEleInvoice ? 'btnActive' : '']"
              @click="changeNeedEleInvoice(true)"
            >
              是
            </button>
            <button
              :class="[!isNeedEleInvoice ? 'btnActive' : '']"
              @click="changeNeedEleInvoice(false)"
            >
              否
            </button>
          </span>
        </li>
        <li v-show="showBilling" class="insideLine">
          <span>个人或企业</span>
          <span class="rightCenter">
            <button
              class="right10"
              :class="[!isGroup ? 'btnActive' : '']"
              @click="changeGroupOrPerson(false)"
            >
              个人
            </button>
            <button
              :class="[isGroup ? 'btnActive' : '']"
              @click="changeGroupOrPerson(true)"
            >
              企业
            </button>
          </span>
        </li>
        <li v-show="showBilling" class="insideLine">
          <span>发票抬头</span>
          <span class="rightCenter">
            <button
              class="right10"
              :class="[sameInsuredType == 'y' ? 'btnActive' : '']"
              @click="changeSameInsuredType('y')"
            >
              同投保人
            </button>
            <button
              :class="[sameInsuredType == 'n' ? 'btnActive' : '']"
              @click="changeSameInsuredType('n')"
            >
              同被保人
            </button>
          </span>
        </li>
        <li v-show="showBilling && isGroup" class="insideLine">
          <van-field
            readonly
            clickable
            required
            :value="billing.cutstomInfo.customerTypeVal"
            placeholder="请选择客户类型"
            @click="billing.cutstomInfo.customerShowPicker = true"
            input-align="right"
          >
            <div slot="label">
              <span>客户类型</span>
            </div>
            <div slot="right-icon" class="rightCenter">
              <van-icon name="arrow" class="grayBf" />
            </div>
          </van-field>
          <van-popup
            v-model="billing.cutstomInfo.customerShowPicker"
            position="bottom"
          >
            <van-picker
              show-toolbar
              :columns="billing.cutstomInfo.cutstomClientType"
              :default-index="1"
              @cancel="billing.cutstomInfo.customerShowPicker = false"
              @confirm="customer_onConfirm"
            />
          </van-popup>
        </li>
        <li v-show="showBilling && isGroup" class="insideLine">
          <van-field
            readonly
            clickable
            required
            :value="billing.taxpayerInfo.taxPayerTypeVal"
            placeholder="请选择纳税人类型"
            @click="billing.taxpayerInfo.taxPayerShowPicker = true"
            input-align="right"
          >
            <div slot="label">
              <span>纳税人类型</span>
            </div>
            <div slot="right-icon" class="rightCenter">
              <van-icon name="arrow" class="grayBf" />
            </div>
          </van-field>
          <van-popup
            v-model="billing.taxpayerInfo.taxPayerShowPicker"
            position="bottom"
          >
            <van-picker
              show-toolbar
              :columns="billing.taxpayerInfo.taxPayerType"
              :default-index="taxPayerTypeIndex"
              @cancel="billing.taxpayerInfo.taxPayerShowPicker = false"
              @confirm="taxpayerInfo_onConfirm"
            />
          </van-popup>
        </li>
        <li v-show="showBilling && isGroup" class="insideLine">
          <van-field
            readonly
            clickable
            required
            :value="billing.invoiceInfo.invoiceTypeVal"
            placeholder="请选择发票类型"
            @click="billing.invoiceInfo.invoicePicker = true"
            input-align="right"
          >
            <div slot="label">
              <span>发票类型</span>
            </div>
            <div slot="right-icon" class="rightCenter">
              <van-icon name="arrow" class="grayBf" />
            </div>
          </van-field>
          <van-popup
            v-model="billing.invoiceInfo.invoicePicker"
            position="bottom"
          >
            <van-picker
              show-toolbar
              :columns="billing.invoiceInfo.invoiceType"
              :default-index="invoiceIndex"
              @cancel="billing.invoiceInfo.invoicePicker = false"
              @confirm="invoiceInfo_onConfirm"
            />
          </van-popup>
        </li>
        <li v-show="showBilling && isGroup" class="insideLine">
          <van-field
            v-model="invoiceInfo.taxPayerNo"
            name="taxPayerNo"
            label="纳税人识别号"
            placeholder="请输入纳税人识别号"
            input-align="right"
            :required="invoiceInfo.customerType != '3'"
            clearable
          />
        </li>
        <li
          class="insideLine"
          v-show="
            showBilling && isNeedEleInvoice && (!isGroup || invoiceType == '3')
          "
        >
          <van-field
            v-model="invoiceInfo.mobile"
            name="mobile"
            label="移动电话"
            input-align="right"
            maxlength="11"
            type="digit"
            placeholder="请输入手机号"
            clearable
            :required="invoiceType == '3'"
          />
        </li>
        <li
          v-show="showBilling && isGroup && isShowCompanyInfo"
          class="insideLine"
        >
          <van-field
            v-model="invoiceInfo.taxRegisterAddress"
            name="taxRegisterAddress"
            label="注册地址"
            placeholder="请输入注册地址"
            input-align="right"
            :required="invoiceType == '1'"
            clearable
          />
        </li>
        <li
          v-show="showBilling && isGroup && isShowCompanyInfo"
          class="insideLine"
        >
          <van-field
            v-model="invoiceInfo.taxRegisterTel"
            name="taxRegisterTel"
            label="注册电话"
            type="digit"
            placeholder="请输入注册电话"
            input-align="right"
            :required="invoiceType == '1'"
            clearable
          />
        </li>
        <li
          v-show="showBilling && isGroup && isShowCompanyInfo"
          class="insideLine"
        >
          <van-field
            v-model="invoiceInfo.depositeBankName"
            name="depositeBankName"
            label="开户银行"
            placeholder="请输入开户银行"
            input-align="right"
            clearable
            :required="invoiceType == '1'"
          />
        </li>

        <li
          v-show="showBilling && isGroup && isShowCompanyInfo"
          class="insideLine"
        >
          <van-field
            v-model="invoiceInfo.companyName"
            name="companyName"
            label="公司名称"
            placeholder="请输入公司名称"
            input-align="right"
            disabled
            :required="invoiceType == '1'"
          />
        </li>
        <li
          v-show="showBilling && isGroup && isShowCompanyInfo"
          class="insideLine"
        >
          <van-field
            v-model="invoiceInfo.accountNo"
            name="accountNo"
            label="银行账号"
            :required="invoiceType == '1'"
            placeholder="请输入银行账号"
            input-align="right"
            clearable
          />
        </li>
        <li v-show="showBilling && isNeedEleInvoice" class="insideLine">
          <van-field
            v-model="invoiceInfo.email"
            name="email"
            label="邮箱"
            input-align="right"
            placeholder="用来接收电子发票邮箱"
            clearable
          />
        </li>

        <li
          v-if="showBilling && isGroup && isShowExpend"
          class="center"
          @click="expendInfo"
        >
          <span class="smallFont" v-if="!isExpend && !isShowCompanyInfo">
            点击展开更多</span
          >
          <span class="smallFont" v-if="isExpend && isShowCompanyInfo">
            点击收起更多</span
          >
          <van-icon name="arrow-down" v-if="!isExpend && !isShowCompanyInfo" />
          <van-icon name="arrow-up" v-if="isExpend && isShowCompanyInfo" />
        </li>
      </ul>
    </div>
    <!-- 条款告知 -->
    <van-cell class="agree" style="background:#f7f7f7;">
      <template slot="title">
        <van-row type="flex" justify="space-between" align="center">
          <van-col class="allow-protocol-check-box">
            <van-checkbox v-model="allowedProtocol" />
          </van-col>
          <van-col style="color: rgb(153, 153, 153);">
            本人已认真阅读并同意
            <a
              v-for="(protocol, index) in protocols"
              :key="protocol.id"
              style="color:#3b8afe;"
              @click="goProtocols(protocol.link)"
            >
              <span v-if="index === protocols.length - 1">和</span>
              {{ "《" + protocol.title + "》" }}
            </a>
            的全部内容，
            <span>并保证所填《健康告知》内容真实有效</span>
          </van-col>
        </van-row>
      </template>
    </van-cell>
    <!-- 保费 投保确认按钮 -->
    <div class="tabbar">
      <div class="price">
        保费
        <span style="color:#FC820E">{{ formatSumPremium }}</span>
        元
      </div>
      <div class="btn">
        <van-button type="info" @click="enter">立即投保</van-button>
      </div>
    </div>
  </div>
</template>
<script>
import globalNav from "@/components/globalNav";
import { mapMutations, mapGetters } from "vuex";
import { Toast } from "vant";
import { checkIdcard, formatDate } from "@src/utils";
import {
  trial,
  getPlanDynamicForms,
  createFromPlaceOrder
} from "@src/http/module/nonCar.js";

import { handlerName, debounce, handlerMobile, handlerEmail } from "./handle";
import { formInfo } from "./form";
import { familyInfo } from "./family";
// import locale from 'antd/lib/date-picker/locale/zh_CN';
//当前选择的元素code
let currentDatePickerCode = "";
let currentContentPickerCode = "";
//需要隐藏的对应投保人信息的字段
let hiddenInfo = [];
//试算元素范围填写错误数组集合
let updataPremiumErrorArr = [];
//渲染后得到新对象
let newObj = {};
let param = "";
export default {
  data() {
    return {
      // 核算字段
      rate: {
        rateAgeMin: null,
        rateAgeMax: null,
        periodMin: null,
        periodMax: null,
        rateSeatMin: null,
        rateSeatMax: null
      },
      testSm: this.$store.state.config.detailData.basePremium || 0,
      baseAmount: this.$store.state.config.detailData.baseAmount || 0,
      selectedSubIn: {}, // 副险
      selectedMainIn: {}, // 主险
      trialArr: [], // 试算要素
      isSaveSuccess: true, // 是否暂存成功
      detailData: {},
      timeLimitInfo: {}, // 保障期限信息
      insuredEleList: [], //被保人list
      fixsumPremium: 0,
      appliEleList: {}, //投保人list
      commonEleList: {}, //公共信息list
      transportEleList: {}, //运输工具list
      KeyContactsEleList: {}, // 关键联络人list
      checkCodeEleList: [],
      // save makeOrder info
      orderInfo: {},
      queryOrderId: "",
      //   formcreate part
      setOptionList: [],
      formInfo: {},
      totForm: [],
      familyInfo: familyInfo,
      //实例对象
      fApi: {},
      insuredFApi: {},
      //表单生成规则
      rule: [],
      insuredRule: [],
      transportFApi: {},
      transportRule: [],
      // 公共信息
      commonRule: [],
      commonFApi: {},
      // 关键联络人
      keycodeRule: [],
      keycodeFApi: {},
      //组件参数配置
      option: {
        submitBtn: false,
        resetBtn: false
        //表单提交事件
        // onSubmit: function(formData) {
        //   console.log("formData", JSON.stringify(formData));
        // }
      },
      appliIdentityList: [],
      insuredItemList: [],
      commonInfoList: [],
      transportInfoList: [],
      KeyContactsInfoList: [],
      insuredList: [],
      cameraIcon: require("@/assets/common/icon3.png"),
      goBack: "prIndex",
      globalNav: {
        title: "投保信息填写"
      },
      protocols: [], //底部同意选项
      tableData: {
        appliName: "", //投保人信息
        appliIdentifyType: "01",
        appliIdentifyNumber: "",
        appliBirthDate: "",
        appliMobile: "",
        appliEMail: "", //投保人邮箱
        insuredName: "", //被保人信息
        appliIdentity: "", //关系
        insuredIdentifyType: "01",
        insuredIdentifyNumber: "",
        insuredBirthDate: "",
        insuredMobile: "",
        startDate: "",
        endDate: "",
        insuredSheBaoType: "",
        // invoiceInfo: {
        //   //发票信息
        //   taxPayerType: "", //纳税人类型 4 个人
        //   companyName: "", //发票抬头
        //   customerType: "", //客户类型1 境内个人、政府机关及事业单位
        //   invoiceType: "", //发票类型 2增值税普通发票 ,3 增值税电子普通发票
        //   mobile: "",
        //   email: "",
        //   taxPayerNo: ""
        // },
        sumPremium: "",
        ages: ""
      },

      showDate: false,
      showDate2: false,
      showDate3: false,
      showDate4: false,
      selectDate: "", //选择日期
      startDate: "",
      endDate: "",
      currentDate: new Date(),
      currentDate2: new Date(),
      currentDate3: new Date(),
      currentDate4: new Date(),
      checked2: false,
      allowedProtocol: false, //底部勾选
      showOCR: true,
      showOCR2: true,
      minDate: new Date(1920, 0, 1), //出生日期组件默认定义最小值
      maxDate: new Date(), //出生日期组件默认最大值
      minDate3: new Date(new Date().getTime() + 24 * 60 * 60 * 1000),
      maxDate3: new Date(new Date().getTime() + 24 * 60 * 60 * 1000 * 30),
      minDate4: new Date(new Date().getTime() + 24 * 60 * 60 * 1001),
      maxDate4: new Date(2099, 12, 31),
      cardTypeInfo: {
        type: [],
        picker: false,
        typeVal: "身份证"
      },
      cardTypeInfo2: {
        type: [],
        picker: false,
        typeVal: "身份证"
      },
      relationInfo: {
        //与投保人关系
        type: [],
        picker: false,
        typeVal: ""
      },
      shebaoInfo: {
        //证件类型
        //被保人年龄信息
        // "城镇职工基本医疗保险",
        // "城乡居民基本医疗保险",
        // "城镇居民基本医疗保险",
        // "新农合医疗保险",
        // "公费医疗",
        // "民政优抚医疗保险",
        // "其他"
        type: [],
        picker: false,
        typeVal: ""
      },
      giPeriod: "1年",
      giPeriodType: "1", //1-年 2-月 3-日
      planGroup: {},
      formCreateApi: {},
      formCreateRule: [],

      beibao: require("@/assets/nonCar/worryEdit/beibao.png"),
      showBilling: false,
      isGroup: false,
      isNeedEleInvoice: true,
      sameInsuredType: "y", //是否同被保人
      // 开票信息
      billing: {
        // 客户类型
        cutstomInfo: {
          cutstomClientType: [
            "境内个人、政府机关及事业单位",
            "境内企业",
            "境外个人或机构"
          ],
          customerShowPicker: false,
          customerTypeVal: ""
        },
        // 纳税人 Taxpayers
        taxpayerInfo: {
          taxPayerType: [],
          taxPayerShowPicker: false,
          taxPayerTypeVal: ""
        },
        //发票
        invoiceInfo: {
          invoiceType: [],
          invoicePicker: false,
          invoiceTypeVal: ""
        }
      },
      taxPayerTypeIndex: 0,
      invoiceType: "1",
      isShowCompanyInfo: true,
      invoiceIndex: 0,
      isShowExpend: false,
      isExpend: false,
      invoiceInfo: {
        //发票信息
        copyDataFromType: "1",
        accountNo: "", // 银行卡账号
        companyName: "", // 公司名称
        customerType: "", // 客户类型
        depositeBankName: "", //开户银行
        email: "", // 邮箱
        invoiceType: "", //发票类型
        mobile: "", //注册电话
        taxPayerNo: "", //纳税人识别号
        taxPayerType: "", // 纳税人类型
        taxRegisterAddress: "", //注册地址
        taxRegisterTel: "" //注册电话
      },
      invoiceFlag: 0, //("0:无发票信息,1:个人，2：企业")
      fixsumAmount: 0,
      initFormData: []
    };
  },
  // created() {
  //   document.getElementById("app").style.overflow = "visible";

  //   if (!Array.isArray(this.gResult)) {
  //     this.setFormula({ result: [this.gResult] });
  //   }
  //   let startDate = this.formatDate(
  //     new Date(new Date().getTime() + 24 * 60 * 60 * 1000)
  //   );
  //   this.tableData.startDate = startDate;
  //   let endDate = new Date();
  //   endDate.setFullYear(endDate.getFullYear() + 1);
  //   endDate.setDate(endDate.getDate());
  //   endDate = this.formatDate(endDate);
  //   this.tableData.endDate = endDate;
  //   this.selectDate = `${startDate} - ${endDate}`;

  //   this.setTenMillionData({ startDate, endDate });
  //   let tS = this.tenMillionData;
  //   // 路由返回保单数据
  //   if (this.$route.params.copy) {
  //     let copy = JSON.parse(this.$route.params.copy);
  //     for (let k in tS) {
  //       tS[k] = copy[k];
  //     }
  //     if (this.$route.params.orderNo) {
  //       localStorage.setItem("tenmillionOrderNo", this.$route.params.orderNo);
  //     } else {
  //       localStorage.removeItem("tenmillionOrderNo");
  //     }
  //     tS.startDate = tS.startDate.split(" ")[0];
  //     tS.endDate = tS.endDate.split(" ")[0];
  //     this.setTenMillionData(tS);

  //     // copy
  //     let {
  //       premiumsJson,
  //       extensionJson,
  //       aKZEXClause,
  //       aEXTZClause,
  //       aEXYLClause,
  //       aEXZZClause
  //     } = copy;
  //     let formula = { premiumsJson, extensionJson };
  //     let result = [];
  //     if (aEXYLClause == "Y") {
  //       result.push("恶性肿瘤赴日医疗责任");
  //     }
  //     if (aEXZZClause == "Y") {
  //       result.push("恶性肿瘤扩展质子重离子医疗责任");
  //     }
  //     if (aKZEXClause == "Y") {
  //       result.push("扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)");
  //     }
  //     if (aEXTZClause == "Y") {
  //       result.push("恶性肿瘤院外特种药品费用医疗责任");
  //     }
  //     this.setFormula({ formula, result });
  //   }

  //   for (let key in tS) {
  //     this.tableData[key] = tS[key];
  //   }

  //   this.cardTypeInfo.typeVal = this.$getCodeOrName(
  //     "bwIdCardList",
  //     this.tableData.appliIdentifyType
  //   );
  //   this.relationInfo.typeVal = this.$getCodeOrName(
  //     "bwRelateInsured",
  //     this.tableData.appliIdentity
  //   );
  //   this.cardTypeInfo2.typeVal = this.$getCodeOrName(
  //     "bwIdCardList",
  //     this.tableData.insuredIdentifyType
  //   );
  //   this.shebaoInfo.typeVal = this.$getCodeOrName(
  //     "shebaoList",
  //     this.tableData.insuredSheBaoType
  //   );
  //   if (this.tableData.startDate != "" || this.tableData.endDate != "") {
  //     this.selectDate = `${this.tableData.startDate} - ${this.tableData.endDate}`;
  //   }
  //   // 去客户列表
  //   if (this.$route.query.item) {
  //     if (this.$route.query.item.isInsured == false) {
  //       let tInfo = this.$route.query.item;
  //       this.tableData.appliName = tInfo.custName;
  //       this.tableData.appliIdentifyType = tInfo.certificateType;
  //       // if (tInfo.certificateType == "01") {
  //       this.cardTypeInfo.typeVal = this.$getCodeOrName(
  //         "bwIdCardList",
  //         this.tableData.appliIdentifyType
  //       );
  //       this.tableData.appliIdentifyNumber = tInfo.certificateNo;
  //       // }
  //       if (Array.isArray(tInfo.mobileList) && tInfo.mobileList.length > 0) {
  //         this.tableData.appliMobile = tInfo.mobileList[0].phoneNumber;
  //       }
  //       this.tableData.appliBirthDate = this.getNum(
  //         this.tableData.appliIdentifyNumber
  //       );
  //       let params = {
  //         appliName: this.tableData.appliName,
  //         appliIdentifyType: this.tableData.appliIdentifyType,
  //         appliIdentifyNumber: this.tableData.appliIdentifyNumber,
  //         appliMobile: this.tableData.appliMobile,
  //         appliBirthDate: this.tableData.appliBirthDate
  //       };
  //       this.setTenMillionData(params);
  //     } else if (this.$route.query.item.isInsured == true) {
  //       let bInfo = this.$route.query.item;
  //       this.tableData.insuredName = bInfo.custName;
  //       this.tableData.insuredIdentifyType = bInfo.certificateType;
  //       this.cardTypeInfo2.typeVal = this.$getCodeOrName(
  //         "bwIdCardList",
  //         this.tableData.insuredIdentifyType
  //       );
  //       // if (bInfo.certificateType == "01") {
  //       this.tableData.insuredIdentifyNumber = bInfo.certificateNo;
  //       // }
  //       if (Array.isArray(bInfo.mobileList) && bInfo.mobileList.length > 0) {
  //         this.tableData.insuredMobile = bInfo.mobileList[0].phoneNumber;
  //       }
  //       this.tableData.insuredBirthDate = this.getNum(
  //         this.tableData.insuredIdentifyNumber
  //       );
  //       let params = {
  //         insuredName: this.tableData.insuredName,
  //         insuredIdentifyType: this.tableData.insuredIdentifyType,
  //         insuredIdentifyNumber: this.tableData.insuredIdentifyNumber,
  //         insuredMobile: this.tableData.insuredMobile,
  //         insuredBirthDate: this.tableData.insuredBirthDate
  //       };

  //       this.setTenMillionData(params);
  //       this.againCompute();
  //     }
  //   }
  // },
  mounted() {
    // 判断是否是录入部分跳转进入
    // if (Object.keys(this.$route.query).length) {
    //   this.queryOrderId = this.$route.query.orderId;
    //   this.$store.commit("config/reset_totForm");
    //   this.$store.commit("config/reset_formRule");
    //   this.$store.commit("config/reset_insredFormRule");
    //   this.$store.commit("config/reset_timeLimitInfo");
    // }
    this.queryOrderId = this.$route ? this.$route.query.orderId : "";

    const {
      // initRule,
      productInfo,
      // totForm,
      detailData
      // insuredRule,
      // timeLimitInfo,
      // transportRule
    } = this.$store.state.config;
    this.orderInfo = JSON.parse(JSON.stringify(productInfo));

    this.detailData = detailData;
    const {
      selectedSubIn,
      selectedMainIn,
      trialArr,
      termsAndConditions
    } = this.detailData;
    this.selectedSubIn = selectedSubIn;
    this.selectedMainIn = selectedMainIn;
    this.trialArr = trialArr;
    this.protocols = termsAndConditions;
    console.log("config", this.$store.state.config);

    // 获取表单信息
    this.getPlanDynamicForms();
    // OCR切换
    if (this.tableData.appliIdentifyType != "01") {
      this.showOCR = false;
    }
    if (this.tableData.insuredIdentifyType != "01") {
      this.showOCR2 = false;
    }
  },
  methods: {
    //  切换个人或企业
    changeGroupOrPerson(flag) {
      this.showBillingSwitch();

      this.isGroup = flag;
      if (flag) {
        this.initInvoiceInfo(true);
      }
    },
    // 初始化发票信息
    initInvoiceInfo(flag) {
      if (flag) {
        this.invoiceInfo = {
          //发票信息
          copyDataFromType: "1",
          accountNo: "", // 银行卡账号
          companyName: "", // 公司名称
          customerType: "", // 客户类型
          depositeBankName: "", //开户银行
          email: "", // 邮箱
          invoiceType: "", //发票类型
          mobile: "", //注册电话
          taxPayerNo: "", //纳税人识别号
          taxPayerType: "", // 纳税人类型
          taxRegisterAddress: "", //注册地址
          taxRegisterTel: "" //注册电话
        };
      }
      // 客户类型
      let { customerType } = this.invoiceInfo;
      let typeVal1 = this.$getCodeOrName("customerTypeList", customerType);
      this.billing.cutstomInfo.customerTypeVal = typeVal1
        ? typeVal1
        : "境内企业";
      let index = this.$getCodeOrName("customerTypeList", typeVal1)
        ? this.$getCodeOrName("customerTypeList", typeVal1)
        : 2;
      this.customer_onConfirm(
        this.billing.cutstomInfo.customerTypeVal,
        index - 1,
        true
      );
      // 纳税人类型
      if (this.invoiceInfo.taxPayerType) {
        let id2 = this.invoiceInfo.taxPayerType;
        let typeVal2 = this.$getCodeOrName("taxpayerTypeList", id2);
        this.billing.taxpayerInfo.taxPayerTypeVal = typeVal2 ? typeVal2 : "";
      }
      // 发票类型
      if (this.invoiceInfo.invoiceType) {
        let id3 = this.invoiceInfo.invoiceType;
        let typeVal3 = this.$getCodeOrName("invoiceTypeList", id3);
        this.billing.invoiceInfo.invoiceTypeVal = typeVal3 ? typeVal3 : "";
      }

      // this.customer_onConfirm("境内企业", 1);
      // this.taxpayerInfo_onConfirm("一般纳税人", 0);
      // this.invoiceInfo_onConfirm("增值税专用发票", 0);
    },
    removePropertyOfNull(obj) {
      Object.keys(obj).forEach(item => {
        if (!obj[item]) {
          obj[item] = "";
        }
      });
      return obj;
    },
    arrayNonRepeatfy(arrays) {
      for (var i = 0; i < arrays.length; i++) {
        for (let j = i + 1; j < arrays.length; j++) {
          if (
            arrays[i].value == arrays[j].value &&
            arrays[i].id == arrays[j].id
          ) {
            arrays.splice(j, 1);
            j--;
          }
        }
      }
      return arrays;
    },

    //   formcreate
    validateName(val) {
      handlerName("name", val).then(res => {
        if (res == "null") {
          console.log("name is null");
          return false;
        } else {
          console.log("val---", val);
          console.log("next");
        }
      });
    },
    /**
     * 校验同关系的数据 todo
     */
    relationCheckData(tableListParm) {
      let checkParam = "";
      let ischange = false;
      // let tableList = tableListParm || this.orderInfo.metaDataList;
      for (let index = 0; index < this.orderInfo.metaDataList.length; index++) {
        checkParam = this.orderInfo.metaDataList[index].code
          .toLowerCase()
          .split("appli")[0];
        const val = this.orderInfo.metaDataList[index].code.split("appli")[1];
        // const aa = val.replace(val[0],val[0].toUpperCase());
        if (
          this.orderInfo.metaDataList[index].code
            .toLowerCase()
            .includes("relation") &&
          (this.orderInfo.metaDataList[index].value == "11" ||
            this.orderInfo.metaDataList[index].value == "Y")
        ) {
          ischange = true;
        }
      }
      console.log(
        "metaDataList-------" + JSON.stringify(this.orderInfo.metaDataList)
      );
    },
    //关系校验
    // 判断是初始化进来的
    validateRelationtype(code, val, isInit) {
      // hiddenInfo.splice(0, hiddenInfo.length);
      //如果是初始化的，联动不删除元素
      if (isInit) {
        hiddenInfo = [];
      }
      // 与投保人关系  用被保的字段
      const codeLow = code.toLowerCase();
      let tableData =
        JSON.stringify(this.formCreateApi.formData()) == "{}"
          ? newObj
          : this.formCreateApi.formData();
      // let tableData =  this.formCreateApi.formData();
      tableData = this.removePropertyOfNull(tableData);
      if (val == "11" || val == "Y") {
        if (codeLow.includes("appli")) {
          param = codeLow.split("appli")[0];
          //把所有appli开头的元素的值 赋值给
          for (const key in tableData) {
            if (tableData.hasOwnProperty(key)) {
              const element = tableData[key];
              // tableData =
              // // let keyObj = key;
              if (key.indexOf("appli") != -1) {
                for (const target in tableData) {
                  if (
                    key.toLowerCase().split("appli")[1] ==
                    target.toLowerCase().split(param)[1]
                  ) {
                    if (target.toLowerCase().indexOf("role") < 0) {
                      //TODO 生日特殊处理
                      if (target.toLowerCase().includes("birthdate")) {
                        this.formCreateApi.updateRule(target + "1", {
                          title: tableData[key]
                        });
                        this.formCreateApi.setValue(target, tableData[key]);
                        //todo 日期试算保费
                        let getAge = this.$getAge(tableData[key]);
                        let code =
                          target.toLowerCase().split("birthdate")[0] + "Age";
                        console.log("---getAge---", getAge * 365);
                        this.updatePremiumsNum(code, getAge * 365);
                      } else {
                        this.formCreateApi.setValue(target, tableData[key]);
                      }
                      hiddenInfo.push(target);
                    } else {
                      hiddenInfo.push(target);
                    }
                  }
                }
              }
            }
          }
        }
        //TODO suncao
        this.formCreateApi.hidden(true, hiddenInfo);
      } else {
        this.formCreateApi.hidden(false);
        //不是同投保人的，字段对应清空 code keyContactsInsuredRelation
        if (hiddenInfo.length > 0 && code.toLowerCase().includes(param)) {
          for (let key in tableData) {
            if (tableData.hasOwnProperty(key)) {
              for (let i = 0; i < hiddenInfo.length; i++) {
                if (
                  key == hiddenInfo[i] &&
                  hiddenInfo[i].toLowerCase().includes(param)
                ) {
                  this.formCreateApi.setValue(key, "");
                  this.formCreateApi.updateRule(key + "1", {
                    title: ""
                  });
                }
              }
            }
          }
        }
      }
    },
    /**
     * code:对应的表单元素code
     * val：传入信息包含具体值
     */
    changeSelect(code, val) {
      // TODO suncao
      console.log("当前code======" + code);
      console.log("当前val======" + val);
      //元素code
      const codeLow = code.toLowerCase();
      //关系联动
      if (
        (codeLow.includes("relation") || codeLow.includes("polholder")) &&
        code != "keyContactsInsuredRelation"
      ) {
        this.validateRelationtype(code, val);
      }
      // //证件号码联动
      // if(codeLow.includes('identifyno')){
      //   let type = code.split("IdentifyNo")[0] + "IdentifyType";
      //   type = this.formCreateApi.getValue(type);
      //   this.handlerIdNum(code, type, val);
      // }
      // IdentifyNo

      // currentContentPickerCode   TODO  试算
      // if (val == "insuredBirthDate") {

      //   console.log("val", date);
      //   // this.selectDateConfirm2(new Date(date));
      this.roShowDate(false);
      // } else if (val == "appliBirthDate") {
      //   console.log("val", date);
      //   // this.roShowDate(true);
      // }
    },
    validateMobile(val, isRequire, groupName) {
      handlerMobile(val, isRequire, groupName);
    },
    validateEMail(val, isRequire, groupName) {
      console.log("val", val);
      handlerEmail(val, isRequire, groupName);
    },
    //   formcreate
    /**
     * a:true,false  code:对应表单元素code
     */
    roShowDate(a, code) {
      const formData = this.formCreateApi.formData();
      this.showDate = a;
      currentDatePickerCode = code;
      // currentContentPickerCode   TODO  试算
    },
    // 算日期天数差
    dateDifference(sDate1, sDate2) {
      //sDate1和sDate2是2006-12-18格式
      let dateSpan, iDays;
      sDate1 = Date.parse(sDate1);
      sDate2 = Date.parse(sDate2);
      dateSpan = sDate2 - sDate1;
      dateSpan = Math.abs(dateSpan);
      iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
      return iDays;
    },
    // 算日期月数差
    monthDifference(date1, date2) {
      //date1和date2是2019-3-12格式
      //用-分成数组
      date1 = date1.split("-");
      date2 = date2.split("-");
      //获取年,月数
      var year1 = parseInt(date1[0]),
        month1 = parseInt(date1[1]),
        year2 = parseInt(date2[0]),
        month2 = parseInt(date2[1]),
        //通过年,月差计算月份差
        months = (year2 - year1) * 12 + (month2 - month1) + 1;
      return months;
    },
    // 算日期年数差
    yearDifference(startDateStr, endDateStr) {
      var day = 24 * 60 * 60 * 1000;

      var sDate = new Date(Date.parse(startDateStr.replace(/-/g, "/")));
      var eDate = new Date(Date.parse(endDateStr.replace(/-/g, "/")));

      //得到前一天(算头不算尾)
      sDate = new Date(sDate.getTime() - day);

      //获得各自的年、月、日
      var sY = sDate.getFullYear();
      // var sM = sDate.getMonth() + 1;
      // var sD = sDate.getDate();
      var eY = eDate.getFullYear();
      // var eM = eDate.getMonth() + 1;
      // var eD = eDate.getDate();

      // if (eY > sY && sM == eM && sD == eD) {
      return eY - sY;
      // } else {
      //   Toast("两个日期之间并非整年，请重新选择");
      //   return 0;
      // }
    },
    // fApi 内容校验
    infoInterceptor() {
      const tableData = this.formCreateApi.formData();
      console.log("tableData---", tableData);
      // 校验发票
      if (!this.checkInvoice()) {
        return false;
      }
      // 判断保障期限
      //  giPeriodType 1 年 2 月 3 天
      let timeLimitDate = "";
      if (this.giPeriodType === 1) {
        timeLimitDate = this.yearDifference(this.startDate, this.endDate);
      } else if (this.giPeriodType === 2) {
        timeLimitDate = this.monthDifference(this.startDate, this.endDate);
      } else {
        timeLimitDate = this.dateDifference(this.startDate, this.endDate);
      }
      console.info("timeLimitDate---", timeLimitDate);
      // return;
      // 判断保障期限

      if (!this.allowedProtocol) {
        Toast("请阅读并同意保险条款和投保须知等");
        return;
      }
      let metaList = [];
      let tempArr = [];
      for (let item in tableData) {
        tempArr.push(item);
      }
      tempArr = [...this.checkCodeEleList, ...tempArr];
      let totForm = this.totForm;
      console.log("tempArr---", tempArr);
      console.log("totForm---", totForm);
      for (let len = tempArr.length, i = 0; i < len; i++) {
        for (let len = totForm.length, j = 0; j < len; j++) {
          totForm[j].forEach(function(item) {
            // body
            if (item.code === tempArr[i]) {
              //显示displayvalue的逻辑
              let displayValue = "";
              if (item.controlType == "select") {
                for (
                  let index = 0;
                  index < item.planElementOptionList.length;
                  index++
                ) {
                  const element = item.planElementOptionList[index];
                  if (
                    tableData[tempArr[i]] == element.value ||
                    tableData[tempArr[i]] == element.label
                  ) {
                    displayValue = element.label;
                  }
                }
              } else {
                displayValue = tableData[tempArr[i]];
              }
              console.log("displayValue", displayValue);
              if (item.name == "角色") {
                console.log("角色========" + tableData[tempArr[i]]);
              }
              var obj = {
                id: item.id,
                code: item.code,
                name: item.name,
                dataType: item.elementType,
                submitName: item.submitName,
                groupId: item.groupId,
                displayValue: displayValue,
                value: tableData[tempArr[i]]
                  ? tableData[tempArr[i]] == "身份证"
                    ? "111"
                    : tableData[tempArr[i]]
                  : null,
                arrayIndex: item.arrayIndex
              };
              if (obj.code.toLowerCase().includes("admiarea")) {
                let arr = tableData[tempArr[i]];
                obj.value = arr ? arr[arr.length - 1] : "";
              }
              metaList.push(obj);
            }
          });
        }
      }
      console.log("metaList---", metaList);
      // metaList = this.arrayNonRepeatfy(metaList);
      //
      if (this.isSaveSuccess) {
        const defaultMetaDataList = this.$store.state.config.productInfo
          .metaDataList;
        // const defaultMetaDataList = this.orderInfo.metaDataList;
        this.orderInfo.metaDataList = [...defaultMetaDataList, ...metaList];
      }
      console.log(
        "metaDataList-------" + JSON.stringify(this.orderInfo.metaDataList)
      );
      console.log("this.initFormData=======" + this.initFormData);
      //校验对应必填元素的非空判断
      for (let index = 0; index < this.initFormData.length; index++) {
        const element = this.initFormData[index];
        let filedName = "";
        if (this.initFormData[index].name.indexOf("信息") != -1) {
          filedName = this.initFormData[index].name.split("信息")[0];
        }
        for (let i = 0; i < element.planElementList.length; i++) {
          const initObj = element.planElementList[i];
          for (let j = 0; j < this.orderInfo.metaDataList.length; j++) {
            const target = this.orderInfo.metaDataList[j];
            if (
              target.groupId == initObj.groupId &&
              target.name == initObj.name &&
              initObj.requireInput &&
              initObj.controlType !== "hidden"
            ) {
              if (!target.value) {
                this.$toast(filedName + initObj.name + "不能为空");
                return;
              }
            }
          }
        }
      }
      //对试算要素试算错误判断校验
      if (updataPremiumErrorArr.length > 0) {
        for (let index = 0; index < updataPremiumErrorArr.length; index++) {
          const name = updataPremiumErrorArr[index].name;
          if (!this.$isNull(name)) {
            this.$toast(name + "填写不正确");
            return;
          }
        }
      }
      // this.relationCheckData(); TODO
      // validateRelationtype (code, val, isInit)
      let params = {
        metaDataList: this.orderInfo.metaDataList, // 动态表单
        startDate: this.startDate, // 开始时间
        endDate: this.endDate, //formatDate(newEndDate).slice(0, 10), // this.tableData.endDate 截止日期
        timeLimit: timeLimitDate, // 保障期限
        strategyId: this.$store.state.config.detailData.strategyId, // 销售方案id
        storeCode: this.orderInfo.storeCode, // 门店代码
        userCode: localStorage.getItem("userCode"), // 用户代码
        planId: this.orderInfo.planId, // 批次id
        orderId: null, // 暂存订单Id
        sumPremium: this.testSm, // 应收保费
        insureNo: null, // 投保单号
        isAgent: this.orderInfo.isAgent, // 是否代理人 1 是 0 否
        sumAmount: this.fixsumAmount, // 保额
        formulaCode: this.orderInfo.formulaCode, // 方案代码
        riskCode: this.orderInfo.riskCode, // 产品代码
        productList: this.orderInfo.productList, // 已选产品
        isTemp: 1, // 暂存标识  1暂存 0提交核心
        isInvoice: this.showBilling, //是否需要发票
        invoiceFlag: this.invoiceFlag
      };
      if (this.orderInfo.isAgent == "1") {
        params.agentInfo = this.orderInfo.agentInfo; // 代理信息
      }
      if (this.showBilling) {
        //如果开发票
        params.invoiceInfo = this.invoiceInfo; // 开票信息
        if (!this.isGroup) {
          params.invoiceFlag = 1; //个人
        } else {
          params.invoiceFlag = 2; //企业
        }
      }
      console.log("params---", params);
      // 先把上一次store中的rule清空  防止叠加
      // this.$store.commit("config/reset_formRule");
      // this.$store.commit("config/reset_insredFormRule");
      // this.$store.commit("config/reset_timeLimitInfo");
      console.log("resetProductInfo---", this.$store.state.config);
      createFromPlaceOrder(params)
        .then(res => {
          if (res.code == 1) {
            // 保存提交订单信息 并且跳转下一页面带个订单id
            this.$router.push({
              name: "prConfirm",
              query: {
                // orderId: 1,
                orderId: res.data.orderId
                // orderParams: JSON.stringify(params)
              }
            });
            // this.$store.commit("config/save_insredFormRule", this.insuredRule);
            // this.$store.commit("config/saveTransportRule", this.transportRule);
            const timeLimitInfo = {
              startDate: this.startDate,
              endDate: this.endDate,
              giPeriod: this.giPeriod,
              giPeriodType: this.giPeriodType
            };
            // TODO suncao
            this.$store.commit("config/save_formatForm", this.formCreateRule);
            this.$store.commit("config/save_timeLimitInfo", timeLimitInfo);
            console.log("updateRules---", this.$store.state.config);
            for (let k in params) {
              this.$store.state.config.insuranceInfo[k] = params[k];
            }
            // this.$store.dispatch("config/updateInsuranceInfo", params);
            console.log("saveOrderParams---", this.$store.state);
          } else {
            // 暂存失败 防止继续点击 字段重复
            this.isSaveSuccess = false;
          }
        })
        .catch(error => {
          this.isSaveSuccess = false;
          console.info("error---", error);
        });
      document.getElementById("app").style.overflow = "";
    },

    removeAllSpace(str) {
      return str.replace(/\s+/g, "");
    },
    // 返回上一页
    goback() {
      let isNeedHealthNotice = this.$store.state.config.detailData
        .isNeedHealthNotice; //是否需要健康须知
      let healthNoticeObj = this.$store.state.config.detailData.healthNoticeObj; //是否需要健康须知
      if (isNeedHealthNotice) {
        this.$router.push({
          name: "prHealthNotice",
          query: {
            obj: JSON.stringify(healthNoticeObj),
            orderId: this.queryOrderId
          }
        });
      } else {
        this.$router.push({
          name: "prIndex",
          query: {
            strategyId: this.orderInfo.strategyId,
            orderId: this.queryOrderId
          }
        });
      }

      // if (this.queryOrderId !== "") {
      //   this.$router.push({
      //     name: "prIndex",
      //     query: { orderId: this.queryOrderId }
      //   });
      //   // this.$router.push({ name: "millionMedicalCare" });
      // } else {
      //   this.$router.go(-1);
      // }
      document.getElementById("app").style.overflow = "";
    },
    // 证件类型选择
    card_confirm(value, index) {
      this.cardTypeInfo.typeVal = value;
      const appliFormData = this.fApi.model();
      const insuredFormData = this.fApi.model();
      switch (value) {
        case "身份证":
          appliFormData.appliIdentifyType.value = "身份证";
          break;
        case "港澳居民居住证":
          appliFormData.appliIdentifyType.value = "港澳居民居住证";
          break;
        case "普通护照":
          appliFormData.appliIdentifyType.value = "普通护照";
          break;
        case "军官证":
          appliFormData.appliIdentifyType.value = "军官证";
          break;
        case "统一社会信用代码":
          appliFormData.appliIdentifyType.value = "统一社会信用代码";
          break;
        case "组织机构代码证":
          appliFormData.appliIdentifyType.value = "组织机构代码证";
          break;
      }
      this.cardTypeInfo.picker = false;
      if (appliFormData.appliIdentifyType.value == "身份证") {
        this.cardTypeInfo2.typeVal = value;
        switch (value) {
          case "身份证":
            insuredFormData.appliIdentifyType.value = "身份证";
            break;
          case "港澳居民居住证":
            insuredFormData.appliIdentifyType.value = "港澳居民居住证";
            break;
          case "普通护照":
            insuredFormData.appliIdentifyType.value = "普通护照";
            break;
          case "军官证":
            insuredFormData.appliIdentifyType.value = "军官证";
            break;
          case "统一社会信用代码":
            insuredFormData.appliIdentifyType.value = "统一社会信用代码";
            break;
          case "组织机构代码证":
            insuredFormData.appliIdentifyType.value = "组织机构代码证";
            break;
        }
      }
      if (this.tableData.appliIdentifyType != "111") {
        this.showOCR = false;
      } else {
        this.showOCR = true;
      }
    },
    // 选择出生日期区间方法
    selectBirthDateConfirm(date) {
      if (currentDatePickerCode.toLowerCase().indexOf("birthdate") >= 0) {
        this.formCreateApi.updateRule(currentDatePickerCode + "1", {
          title: this.formatDate(date)
        });
        this.formCreateApi.setValue(
          currentDatePickerCode,
          this.formatDate(date)
        );
        let getAge = this.$getAge(this.formatDate(date));
        let code =
          currentDatePickerCode.toLowerCase().split("birthdate")[0] + "Age";
        console.log("---getAge---", getAge * 365);
        this.updatePremiumsNum(code, getAge * 365);
      } else {
        this.formCreateApi.setValue(
          currentDatePickerCode,
          this.formatDate(date)
        );
      }
      this.showDate = false;
    },
    // 选择保障期限-起期
    selectDateConfirmStartDate(date) {
      let startDate = this.formatDate(date);
      this.startDate = startDate;
      this.tableData.startDate = startDate;
      this.showDate3 = false;
      let days = this.$getDayNumFutrue(this.startDate, this.endDate);
      console.log("保障天数====" + days);
      this.updatePremiumsNum("commSecurityPeriod", days, "保障期限");
    },
    // 选择日期区间-止期
    selectDateConfirmEndDate(date) {
      let endDate = this.formatDate(date);
      this.endDate = endDate;
      this.tableData.endDate = endDate;
      this.showDate4 = false;
      let days = this.$getDayNumFutrue(this.startDate, this.endDate);
      console.log("保障天数====" + days);
      this.updatePremiumsNum("commSecurityPeriod", days, "保障期限");
    },
    // 格式化时间
    formatDate(date) {
      console.log("date=======", date);
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
    // ocr识别
    ocrDistinguish(type) {
      let _this = this;
      const fApiValue = _this.formCreateApi.model();
      const insuredFApiValue = _this.formCreateApi.model();
      switch (type) {
        // 投保人身份证识别
        case "appliCard": {
          const jsonObject = {
            identifyType: "1", //"0" - 识别行驶证; "1" - 识别身份证
            side: "face" //"face"：正面；"back": 反面;
          };
          console.log("=========" + JSON.stringify(jsonObject));
          this.$native.identifyOCR(jsonObject, data => {
            console.log("身份证识别调用成功，壳响应数据:" + data.resultMsg);
            let tempData = JSON.parse(data);
            if (tempData.resultCode == "1") {
              const { name, cardNo } = tempData.responseBody;
              fApiValue.appliIdentifyNumber.value = cardNo;
              fApiValue.appliName.value = name;
              fApiValue.appliIdentifyType.value = "01";
              fApiValue.appliBirthDate.value = _this.getNum(cardNo);
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
            console.log("身份证识别调用成功，壳响应数据:" + data.resultMsg);
            let tempData = JSON.parse(data);
            if (tempData.resultCode == "1") {
              const { name, cardNo } = tempData.responseBody;
              insuredFApiValue.insuredIdentifyNumber.value = cardNo;
              insuredFApiValue.insuredName.value = name;
              insuredFApiValue.insuredIdentifyType.value = "01";
              insuredFApiValue.insuredBirthDate.value = _this.getNum(cardNo);
              let temporaryVariable = insuredFApiValue.insuredBirthDate.value;

              if (this.$getAge(temporaryVariable) > 60) {
                Toast("年龄不在范围内");
                insuredFApiValue.insuredIdentifyNumber.value = "";
                insuredFApiValue.insuredBirthDate.value = "";
                insuredFApiValue.insuredName.value = "";
                return;
              }

              this.againCompute();
            }
          });
          break;
        }
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
    // 得到日期
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
    // 去保险条款
    refh() {
      let r = {
        appliName: this.tableData.appliName,
        appliIdentifyType: this.tableData.appliIdentifyType,
        appliIdentifyNumber: this.tableData.appliIdentifyNumber,
        appliBirthDate: this.tableData.appliBirthDate,
        appliMobile: this.tableData.appliMobile,
        appliEMail: this.tableData.appliEMail,
        insuredName: this.tableData.insuredName,
        appliIdentity: this.tableData.appliIdentity,
        insuredIdentifyType: this.tableData.insuredIdentifyType,
        insuredIdentifyNumber: this.tableData.insuredIdentifyNumber,
        insuredBirthDate: this.tableData.insuredBirthDate,
        insuredMobile: this.tableData.insuredMobile,
        startDate: this.tableData.startDate,
        endDate: this.tableData.endDate,
        insuredSheBaoType: this.tableData.insuredSheBaoType,
        invoiceInfo: {
          mobile: this.tableData.invoiceInfo.mobile,
          email: this.tableData.invoiceInfo.email
        }
      };
      this.setTenMillionData(r);
    },

    // goProtocols (a) {
    //   this.$router.push(a);
    //   this.refh();
    // },
    // 跳转到相应的条款内
    gotoClause(item) {
      if (item.chlidren instanceof Array && item.chlidren.length > 0) {
        let obj = {
          list: item.chlidren,
          title: item.title
        };
        this.$router.push({
          name: "clauseTerms",
          query: { list: JSON.stringify(obj) }
        });
      } else {
        // // https://view.officeapps.live.com/op/view.aspx?src=https://msupsale-test.ccic-net.com.cn/api/cst/s3/browse/e8300975-30b9-4c8f-97f4-afcc27848f98.docx
        this.$router.push({
          name: "clausePdf",
          query: { title: item.title, link: item.link }
        });
        // this.$native.openWebView({
        //   url: "https://view.officeapps.live.com/op/view.aspx?src=" + item.link,
        //   type: "0",
        //   titleBarTag: "1",
        //   titleBar: item.title,
        //   isBackHome: "1"
        // });
      }
    },
    // 暂存方法
    zancun() {
      if (!localStorage.getItem("tenmillionOrderNo")) {
        var zcParams = {
          formulaCode: this.tenMillionData.formulaCode,
          insuranceCode: this.tenMillionData.riskCode,
          storeOracleRequestVO: this.tenMillionData
        };
      } else {
        var zcParams = {
          formulaCode: this.tenMillionData.formulaCode,
          insuranceCode: this.tenMillionData.riskCode,
          storeOracleRequestVO: {
            ...this.tenMillionData
          },
          orderNo: localStorage.getItem("tenmillionOrderNo")
        };
      }
    },
    /**
     * 更新计算保费试算
     * code:对应的影响元素
     * value：对应的影响因子的值 具体的一个值，不带-
     * name: 对应的元素名称
     */
    updatePremiumsNum(code, value, name) {
      updataPremiumErrorArr = []; // 错误试算集合置空
      let selectedMainIn = this.$store.state.config.detailData.selectedMainIn;
      let selectedSubIn = this.$store.state.config.detailData.selectedSubIn;
      let commNumOfCopies = this.$store.state.config.detailData.commNumOfCopies;
      let planCoverageIds = [];
      for (let k in selectedMainIn) {
        let { id } = selectedMainIn[k];
        planCoverageIds.push(id);
      }

      for (let k in selectedSubIn) {
        let { id } = selectedSubIn[k];
        planCoverageIds.push(id);
      }
      let rate = [
        {
          rateAgeMin: null,
          rateAgeMax: null,
          periodMin: null,
          periodMax: null,
          rateSeatMin: null,
          rateSeatMax: null
        }
      ];
      let codeList = []; //影响试算的列表
      let trialArr = this.$store.state.config.detailData.trialArr;
      let formData = this.formCreateRule;
      for (let i = 0, len = trialArr.length; i < len; i++) {
        let { listInfo } = trialArr[i];
        for (let j = 0, len1 = listInfo.length; j < len1; j++) {
          let { field } = listInfo[j];
          let obj = formData.find(item => {
            return item.field == field;
          });

          // if (obj) {
          //   listInfo[j].value = obj.value;
          // } else {
          //   listInfo[j].value = "";
          // }
          if (field) {
            codeList.push(listInfo[j]);
          }
        }
      }

      console.log(1609, codeList);
      if (codeList.length == 0) {
        if (
          !code ||
          (code && code.toLowerCase().indexOf("commnumofcopies") >= 0)
        ) {
          let obj = formData.find(item => {
            return item.field == "commNumOfCopies";
          });
          let fs = obj ? obj.value : commNumOfCopies;
          let sum = this.fixsumPremium;
          this.fixsumAmount = 0;
          let sumAmount = this.baseAmount;
          if (fs && fs != 0) {
            sum = sum * fs;
            sumAmount = sumAmount * fs;
          }
          this.testSm = sum;
          this.fixsumAmount = sumAmount;
          console.log(
            "111计算之后的保费===" + sum,
            this.fixsumAmount,
            sumAmount
          );
          return;
        }
      }
      let sum = 0;
      let hasChange = false;
      for (let index = 0; index < codeList.length; index++) {
        const element = codeList[index];
        if (element.field.indexOf(code) != -1 && element.children) {
          element.children.forEach(item => {
            const min = item.split("-")[0];
            const max = item.split("-")[1];
            if (value >= min && value <= max) {
              value = item;
              rate = this.getRate(code, value, rate);
              rate.forEach(item => {
                if (!item.rateAgeMin) {
                  item.rateAgeMin = null;
                }
                if (!item.rateAgeMax) {
                  item.rateAgeMax = null;
                }
                if (!item.periodMin) {
                  item.periodMin = null;
                }
                if (!item.periodMax) {
                  item.periodMax = null;
                }
                if (!item.rateSeatMin) {
                  item.rateSeatMin = null;
                }
                if (!item.rateSeatMax) {
                  item.rateSeatMax = null;
                }
                if (!item.rateSex) {
                  item.rateSex = null;
                }
                if (!item.rateSocialSecurityFlag) {
                  item.rateSocialSecurityFlag = null;
                }
                if (!item.rateOccupationType) {
                  item.rateOccupationType = null;
                }
              });
              let obj = {
                planId: this.orderInfo.planId,
                planCoverageIds: [...planCoverageIds],
                rate: [...rate]
              };
              trial(obj)
                .then(res => {
                  if (res.code == "1") {
                    sum = res.data ? parseInt(res.data) : 0;
                    this.fixsumPremium = res.data ? parseInt(res.data) : 0;

                    let obj = formData.find(item => {
                      return item.field == "commNumOfCopies";
                    });
                    let fs = obj ? obj.value : commNumOfCopies;
                    commNumOfCopies = fs ? fs : commNumOfCopies;
                    this.fixsumAmount = 0;
                    let sumAmount = this.baseAmount;
                    if (commNumOfCopies != 0) {
                      sum = sum * commNumOfCopies;
                      sumAmount = sumAmount * commNumOfCopies;
                    }
                    this.fixsumAmount = sumAmount;
                    console.log(
                      "22计算之后的保费===" + sum,
                      this.fixsumAmount,
                      sumAmount
                    );
                    hasChange = true;
                    this.testSm = Number(sum).toFixed(2);
                  } else {
                    this.$toast(res.msg);
                  }
                })
                .catch();
            }
          });
        }
        console.log("保费是否修改=======" + hasChange);
        if (hasChange) {
          updataPremiumErrorArr.push({
            code: code,
            name: name
          });
        }
        return;
      }
    },
    //
    /**获取更新保费的请求rate参数
     * code 对应表单元素code
     * value 对应影响因素的值
     *
     */
    getRate(code, value, rate) {
      let obj = {
        rateAgeMin: null,
        rateAgeMax: null,
        periodMin: null,
        periodMax: null,
        rateSeatMin: null,
        rateSeatMax: null,
        rateSex: null,
        rateSocialSecurityFlag: null,
        rateOccupationType: null
      };
      // 年龄
      if (code.indexOf("Age") >= 0) {
        if (code.indexOf("_") >= 0 && value) {
          obj.rateAgeMin = parseInt(value.split("-")[0]);
          obj.rateAgeMax = parseInt(value.split("-")[1]);
          rate[code.split("_")[0] - 1] = obj;
        } else if (code.indexOf("_") < 0 && value) {
          // rate[0].rateAgeMin = parseInt(value.split("-")[0]);
          // rate[0].rateAgeMax = parseInt(value.split("-")[1]);
          obj.rateAgeMin = parseInt(value.split("-")[0]);
          obj.rateAgeMax = parseInt(value.split("-")[1]);
          rate[0] = obj;
        }
      }
      // 保障期间
      if (code.indexOf("Period") >= 0 && value) {
        rate.forEach(item => {
          item.periodMin = parseInt(value.split("-")[0]);
          item.periodMax = parseInt(value.split("-")[1]);
        });
      }
      // 座位
      if (code.indexOf("Seat") >= 0) {
        if (code.indexOf("_") >= 0 && value) {
          obj.rateSeatMin = parseInt(value.split("-")[0]);
          obj.rateSeatMax = parseInt(value.split("-")[1]);
          rate[code.split("_")[0] - 1] = obj;
        } else if (code.indexOf("_") < 0 && value) {
          obj.rateSeatMin = parseInt(value.split("-")[0]);
          obj.rateSeatMax = parseInt(value.split("-")[1]);
          rate[0] = obj;
        }
      }
      console.log("试算费率数组信息======" + JSON.stringify(rate));
      return rate;
    },
     //  获取带有icon的标题，例如：投保人信息，被保人信息标题
    getTitleItem (code, name, sort, groupId) {
      let me = this;
      let labelObj = {
        type: "label",
        field: code,
        sort,
        groupId,
        className: "labelForm",
        groupName: "fenzu",
        children: [
          {
            type: "label",
            field: code + "1",
            className: "labelItem",
            children: [
              {
                type: "div",
                children: [
                  {
                    name: "img",
                    type: "img",
                    attrs: {
                      src: me.beibao,
                      height: "20px"
                    },
                    className: "titleIcon"
                  },
                  {
                    title: name,
                    type: "label",
                    className: "labelItem2",
                    field: code + "3"
                  }
                ],
                slot: "prefix",
                on: {
                  click: () => {
                    console.log("点击了");
                  }
                }
              }
            ]
          }
          // {
          //   type: "label",
          //   field: code + "2",
          //   title: code.toLowerCase().includes("applicant")
          //     ? "选择投保人"
          //     : code.toLowerCase().includes("insured")
          //     ? "选择被保人"
          //     : "",
          //   className: "labelItem1",
          //   children: [
          //     {
          //       type: "div",
          //       children: [
          //         {
          //           name: "img",
          //           type: "img",
          //           attrs: {
          //             src: me.selectPerson,
          //             height: "20px"
          //           },
          //           className: "titleIcon"
          //         }
          //       ],
          //       slot: "suffix",
          //       on: {
          //         click: () => {
          //           console.log("点击了");
          //         }
          //       }
          //     }
          //   ]
          // }
        ]
      };
      return labelObj;
    },
    // 渲染动态form
    renderForm(info) {
      const formInfo = info || this.formInfo.data;
      console.info("1953 formInfo---", formInfo);
      let planGroup = formInfo.planElementGroup;
      this.initFormData = planGroup; //初始化表单数据
      this.fixsumPremium = this.$store.state.config.detailData.basePremium;
      // 加字段验证 slot事件
      const me = this;
      this.totForm = [];
      for (let index = 0; index < planGroup.length; index++) {
        const element = planGroup[index];
        const planElementList = element.planElementList;
        const initPlanElementList = element.planElementList;
        this.totForm.push(element.planElementList);
        let { code, name, sort, groupId } = element;
        this.formCreateRule.push(this.getTitleItem(code, name, sort, groupId));
        // this.formCreateRule.push({
        //   type: "label",
        //   field: code,
        //   title: name,
        //   sort,
        //   groupId,
        //   className: "formTitle"
        // });

        for (let i = 0; i < planElementList.length; i++) {
          const item = planElementList[i];
          this.setOptionList = [];
          const optionInfo = item.planElementOptionList || "";
          if (optionInfo) {
            this.setOptionList = optionInfo.map(v => ({
              value: v.value,
              label: v.label
            }));
            for (let item of optionInfo) {
              this.cardTypeInfo.type.push(item.label);
            }
          }
          // hidden 为试算字段不需要展示
          if (item.controlType !== "hidden" || item.controlType !== "") {
            if (!item.defaultValue) {
              item.defaultValue = "";
            }
            if (item.controlType != "DatePicker") {
              this.showDate = false;
            }

            if (item.controlType === "DatePicker") {
              this.formCreateRule.push({
                type: "label",
                field: item.code,
                title: item.name,
                className: "birth",
                groupName: element.name,
                value:
                  item.defaultValue != "0" &&
                  item.defaultValue != null &&
                  item.defaultValue != undefined
                    ? item.defaultValue
                    : "",
                children: [
                  {
                    type: "label",
                    field: item.code + "1",
                    title:
                      item.defaultValue != "0" &&
                      item.defaultValue != null &&
                      item.defaultValue != undefined
                        ? item.defaultValue
                        : "",
                    className: "birth"
                  }
                ],
                on: {
                  click: () => {
                    if (item.code.toLowerCase().includes("date")) {
                      console.log("me.roShowDate(true);-------", item.code);
                      me.roShowDate(true, item.code);
                    }
                  }
                },
                validate: [
                  {
                    type:
                      item.controlType === "DatePicker"
                        ? "string"
                        : item.elementType.toLowerCase()
                    // required: item.requireInput ? true : false,
                    // message: `请${
                    //   item.controlType == "input" ? "输入" : "选择"
                    //   }${item.name}`
                  },
                  {
                    validator(rule, value, callback, source, options) {
                      console.log("rule.field=======" + rule.field);
                      const codeLow = rule.field.toLowerCase();
                      const code = rule.field;

                      //出生日期校验
                      if (rule.field == "birthdate") {
                        //TODO
                      }
                    }
                  }
                ]
              });
            } else if (item.controlType == "inputNumber") {
              let obj = {
                type: "a-row",
                field: item.code,
                title: item.name,
                className: "formRow",
                value: item.defaultValue ? item.defaultValue : "1",
                groupName: element.name,
                props: {
                  span: 24,
                  type: "flex",
                  align: "middle",
                  justify: "space-around"
                },
                children: [
                  {
                    type: "a-icon",
                    props: {
                      type: "minus"
                    },
                    field: "minus",
                    title: "",
                    on: {
                      click: () => {
                        this.numBlur(
                          "minus",
                          obj.field,
                          obj.children[1].value,
                          item.maxValue,
                          item.minValue,
                          obj.backReasoning
                        );
                      }
                    }
                  },

                  {
                    field: item.code + "1",
                    value: item.defaultValue ? item.defaultValue : "1",
                    groupName: element.name,
                    type: "input",
                    props: {
                      field: item.code + "1",
                      maxLength: item.maxValue,
                      minLength: item.minValue,
                      defaultValue: !this.$isNull(item.defaultValue)
                        ? parseInt(item.defaultValue)
                        : 1
                    },
                    on: {
                      blur: () => {
                        this.numBlur(
                          "",
                          obj.field,
                          obj.children[1].value,
                          item.maxValue,
                          item.minValue,
                          obj.backReasoning
                        );
                      }
                    }
                  },

                  {
                    type: "a-icon",
                    props: {
                      type: "plus"
                    },
                    field: "plus",
                    title: "",
                    on: {
                      click: () => {
                        this.numBlur(
                          "plus",
                          obj.field,
                          obj.children[1].value,
                          item.maxValue,
                          item.inValue,
                          obj.backReasoning
                        );
                      }
                    }
                  }
                ],
                options: this.setOptionList || []
              };
              this.formCreateRule.push(obj);
            }
            else if (item.controlType == "Cascader") {
              let obj = {
                type: "cascader",
                field: item.code,
                title: item.name,
                groupName: element.name,
                className: "diySelect",
                value: item.defaultValue ? [item.defaultValue] : [],
                props: {
                  fieldNames: {
                    label: "text",
                    value: "id",
                    children: "children"
                  },
                  options: [],
                  showSearch: true,
                  placeholder: "请输入关键字进行搜索" + item.name
                },
                validate: [
                  {
                    required: item.requireInput ? true : false
                    // message: item.value? "":`请${
                    //   item.controlType == "input" ? "输入" : "选择"
                    //   }${item.name}`,
                    // trigger: 'blur'
                  }
                ]
              };
              item.planElementOptionList.forEach(el => {
                obj.props.options = el.addressTree;
              });
              let nodeList = [];
              let idArr = [];
              if (item.defaultValue) {
                this.findPathById(
                  item.defaultValue,
                  obj.props.options,
                  nodeList
                );
                nodeList.forEach(el => {
                  idArr.push(el.id);
                });
              }
              obj.value = idArr;
              this.formCreateRule.push(obj);
            } 
            else {
              console.log(item.code + "======" + item.defaultValue);
              // 格式化后的表单数据塞进统一formCreateRule里面
              this.formCreateRule.push({
                type:
                  item.controlType === "DatePicker"
                    ? "input"
                    : item.controlType,
                field: item.code,
                className:
                  item.controlType === "DatePicker" ||
                  item.controlType === "input"
                    ? "diyInput"
                    : "diySelect",
                title: item.name,
                groupName: element.name,
                value:
                  item.defaultValue != "0" &&
                  item.defaultValue != null &&
                  item.defaultValue != undefined
                    ? item.defaultValue
                    : "",
                options: this.setOptionList || [],
                props: {
                  maxLength:
                    item.code.toLowerCase().includes("mobile") ||
                    item.code.toLowerCase().includes("phone")
                      ? 11
                      : item.code.toLowerCase().includes("zipcode")
                      ? 6
                      : 30
                },
                attrs: {
                  placeholder:
                    item.code == "appliIdentifyNo" ||
                    item.code == "insuredIdentifyNo"
                      ? "请输入证件号码"
                      : item.code == "appliMobile" ||
                        item.code == "insuredMobile"
                      ? "请输入手机号"
                      : item.code == "appliEmail"
                      ? "请输入邮箱地址，可接收电子保单"
                      : "请输入" + item.name
                },
                on: {
                  change: val => {
                    me.changeSelect(item.code, val);
                    // me.changeSelect(item.code, date);
                  },
                  click: () => {
                    if (item.controlType === "DatePicker") {
                      console.log("me.roShowDate(true);-------");
                      me.roShowDate(true, item.code);
                    }
                  }
                },
                children: [
                  // item.code.indexOf("IdentifyNo") >= 0
                  //   ? {
                  //       type: "div",
                  //       children: [
                  //         {
                  //           name: "icon",
                  //           type: "img",
                  //           attrs: {
                  //             src: this.cameraIcon,
                  //             height: "20px"
                  //           }
                  //         }
                  //       ],
                  //       slot: "suffix",
                  //       on: {
                  //         click: () => {
                  //           me.ocrDistinguish("appliCard");
                  //           console.log("value 发生变化");
                  //         }
                  //       }
                  //     }
                  //   : ""
                ],
                validate: [
                  {
                    type:
                      item.controlType === "DatePicker"
                        ? "string"
                        : item.elementType.toLowerCase(),
                    required: item.requireInput ? true : false
                    // message: item.value? "":`请${
                    //   item.controlType == "input" ? "输入" : "选择"
                    //   }${item.name}`,
                    // trigger: 'blur'
                  },
                  {
                    validator(rule, value, callback, source, options) {
                      // console.log(rule);
                      console.log("rule.field=======" + rule.field);
                      const codeLow = rule.field.toLowerCase();
                      const code = rule.field;
                      //证件号码联动
                      if (codeLow.includes("identifyno")) {
                        let type1 =
                          code.split("IdentifyNo")[0] + "IdentifyType";
                        type1 = me.formCreateApi.getValue(type1);
                        if (type1 == "身份证") {
                          type1 = "111";
                        }
                        if (type1 != "111" || type1 != "01") {
                          me.showOCR = false;
                        } else {
                          me.showOCR = true;
                        }
                        me.handlerIdNum(code, type1, value);
                      }
                    }
                  }
                ]
              });
            }
          }
        }
      }
      // console.table(this.formCreateRule);
      //接口获得的form节点存入store
      // this.$store.commit("config/save_totForm", this.totForm);
      this.checkCodeEleList.splice(0, this.checkCodeEleList.length);
      for (let item of this.totForm) {
        if (item.controlType === "hidden" || item.controlType === "") {
          // console.log(2448, this.checkCodeEleList);
          this.checkCodeEleList.push(item.code);
        }
      }
      console.log(this.formCreateRule);
      console.log("totForm---", this.totForm);
      //判断同投保人等关系调用 todo suncao
      for (let index = 0; index < this.formCreateRule.length; index++) {
        const elementTarget = this.formCreateRule[index];
        let key = elementTarget.field;
        let value = elementTarget.value;
        newObj[key] = value;
        console.log("元素code=====" + elementTarget.field);
      }
      console.log("得到的对象=======" + newObj);
      for (let index = 0; index < this.formCreateRule.length; index++) {
        const elementTarget = this.formCreateRule[index];
        if (elementTarget.field.toLowerCase().includes("relation")) {
          //todo suncao
          // setTimeout(() => {
          //   this.validateRelationtype (elementTarget.field, elementTarget.value, true);
          // }, 2000);
        }
      }
      this.updatePremiumsNum();
    },
    // inputNumber输入框失去焦点事件 （份数等）
    numBlur(type, field, value, maxValue, minValue) {
      const reg = /^[0-9]*$/;

      value = reg.test(value) ? value : minValue;
      if (type && type == "plus") {
        let tempValue = parseInt(value);
        tempValue++;
        value = tempValue + "";
      }
      if (type && type == "minus") {
        let tempValue1 = parseInt(value);
        tempValue1--;
        value = tempValue1 + "";
      }

      if (parseInt(value) >= maxValue) {
        this.formCreateApi.setValue(field, maxValue + "");
        this.formCreateApi.setValue(field + "1", maxValue + "");
        value = maxValue + "";
      } else if (parseInt(value) <= minValue) {
        this.formCreateApi.setValue(field, minValue + "");
        this.formCreateApi.setValue(field + "1", minValue + "");
        value = minValue + "";
      } else {
        this.formCreateApi.setValue(field, value);
        this.formCreateApi.setValue(field + "1", value);
      }
      this.updatePremiumsNum(field, value);
      // this.$store.dispatch(
      //   "config/updateCommNumOfCopies",
      //   !this.$isNull(value) ? parseInt(value) : 1
      // );
      // this.$emit("updateValueAndPremium", field, value, backReasoning);
    },
    // 分数修改保费
    setCommNumOfCopies(value, maxValue) {
      const a = this.fixsumPremium;
      const formData = this.commonFApi.model();
      if (value && value <= maxValue) {
        // this.orderInfo.sumPremium = value * a;
        this.testSm = value * a;
      } else if (value === 0) {
        formData.commNumOfCopies.value = 1;
        Toast("份数不能为0!");
      } else if (value > maxValue) {
        Toast(`份数不能大于${maxValue}!`);
        formData.commNumOfCopies.value = maxValue;
      }
    },
    // 获取表单信息
    getPlanDynamicForms() {
      let params = {
        strategyId: 1543,
        cate: "pl",
        category: "policy"
      };

      if (this.queryOrderId) {
        params.orderId = Number(this.queryOrderId);
      }
      const me = this;
      const planId = this.orderInfo.planId;
       getPlanDynamicForms(params).then(res => {
            if (res.code === "1") {
              console.log(res.data[0])
              me.formInfo=res.data[0]
              this.renderForm(me.formInfo);
            }
        });
     },
    //立即投保
    enter() {
      // 立即投保校验
      this.infoInterceptor();
      // this.interceptor();
    },
    // 1 姓名非空验证
    handler(key, val) {
      if (this.tableData.appliIdentity == "01") {
        this.tableData.insuredName = this.tableData.appliName;
      }
      return new Promise(resolve => {
        if (val == "" || val.length >= 50) {
          Toast(key + "不能为空并且最大50字符");
          resolve("null");
        } else {
          resolve("ok");
        }
      });
    },
    parserDate(date) {
      var t = Date.parse(date);
      if (!isNaN(t)) {
        return new Date(Date.parse(date.replace(/-/g, "/")));
      } else {
        return new Date();
      }
    },
    /**
     * 根据类型验证证件号码appliIdentifyType
     * code:对应的表单元素code
     * type:对应的证件类型
     * val:对应的证件号码
     */
    handlerIdNum(code, type, val) {
      const tableData = this.formCreateApi.formData();
      if (type == "01" || type == "111") {
        // Toast(checkIdcard(val));
        // if (
        //   checkIdcard(val) == "证件号码位数不对" ||
        //   checkIdcard(val) == "证件号码出生日期超出范围或含有非法字符" ||
        //   checkIdcard(val) == "证件号码校验错误" ||
        //   checkIdcard(val) == "证件地区非法"
        // ) {
        //   e.target.focus();
        // }
        try {
          const key = code.split("IdentifyNo")[0] + "BirthDate";
          console.log("出生日期=====" + this.getNum(val));
          console.log("对应的key=====" + key);
          this.formCreateApi.updateRule(key + "1", {
            title: this.getNum(val)
          });
          this.formCreateApi.setValue(key, this.getNum(val));
          this.currentDate = this.parserDate(this.getNum(val));
        } catch (error) {
          console.error(error);
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
          case "111": //身份证验证
            if (val == "") {
              Toast("证件号码不能为空");
              resolve("idnull");
            } else {
              resolve("ok");
            }
            break;
          case "03": //护照验证
            if (val == "") {
              Toast("护照不能为空");
              resolve("hunull");
            } else {
              resolve("ok");
            }
            break;
          case "28": //港澳居住验证
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
    handlerMobile(val, isRequire, groupName) {
      return new Promise(resolve => {
        if (isRequire) {
          if (val == "") {
            Toast(groupName + "中手机号不能为空");
            resolve("mobilenull");
          } else {
            let reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
            if (!reg.test(val)) {
              Toast(groupName + "中手机号格式不正确");
              resolve("mobilerules");
            } else {
              resolve("ok");
            }
          }
        } else {
          let reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
          if (val && !reg.test(val)) {
            Toast(groupName + "中手机号格式不正确");
            resolve("mobilerules");
          } else {
            resolve("ok");
          }
        }
      });
    },
    // 4 邮箱验证
    handlerEmail(val, isRequire, groupName) {
      return new Promise(resolve => {
        let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
        if (isRequire) {
          if (!val) {
            Toast(groupName + "中邮箱不能为空");
            resolve("mobilenull");
          } else {
            if (!reg.test(val)) {
              Toast(groupName + "中邮箱格式不正确");
              resolve("emailnull");
            } else {
              resolve("ok");
            }
          }
        } else {
          if (val && !reg.test(val)) {
            Toast(groupName + "中邮箱格式不正确");
            resolve("emailnull");
          } else {
            resolve("ok");
          }
        }
      });
    },
    // 同投被保人
    sameBefore() {
      if (this.checked2) {
        if (
          this.tableData.appliIdentifyType == "01"
          //|| this.tableData.appliIdentifyType == "28"
        ) {
          this.tableData.appliBirthDate = this.getNum(
            this.tableData.appliIdentifyNumber
          );
          let temporaryVariable = this.tableData.appliBirthDate;

          if (this.$getAge(temporaryVariable) > 60) {
            Toast("年龄不在范围内");
            this.tableData.insuredBirthDate = "";
            this.checked2 = false;
            return;
          } else {
            this.tableData.insuredName = this.tableData.appliName;
            this.tableData.insuredIdentifyType = this.tableData.appliIdentifyType;
            this.tableData.insuredIdentifyNumber = this.tableData.appliIdentifyNumber;
            this.tableData.insuredBirthDate = this.tableData.appliBirthDate;
            this.tableData.insuredMobile = this.tableData.appliMobile;
            this.cardTypeInfo2.typeVal = this.$getCodeOrName(
              "bwIdCardList",
              this.tableData.insuredIdentifyType
            );
          }
          this.againCompute();
        } else {
          let temporaryVariable = this.tableData.appliBirthDate;
          if (this.$getAge(temporaryVariable) > 60) {
            Toast("年龄不在范围内");
            this.tableData.insuredBirthDate = "";
            this.checked2 = false;
            return;
          } else {
            this.tableData.insuredName = this.tableData.appliName;
            this.tableData.insuredIdentifyType = this.tableData.appliIdentifyType;
            this.tableData.insuredIdentifyNumber = this.tableData.appliIdentifyNumber;
            this.tableData.insuredBirthDate = this.tableData.appliBirthDate;
            this.tableData.insuredMobile = this.tableData.appliMobile;
            this.cardTypeInfo2.typeVal = this.$getCodeOrName(
              "bwIdCardList",
              this.tableData.insuredIdentifyType
            );
          }

          this.againCompute();
        }
      }
    },
    // 去除同投被保人
    deleteSameBefore() {
      if (!this.checked2) {
        this.tableData.insuredName = "";
        this.tableData.insuredIdentifyType = "";
        this.tableData.insuredIdentifyNumber = "";
        this.tableData.insuredBirthDate = "";
        this.tableData.insuredMobile = "";
        this.cardTypeInfo2.typeVal = this.$getCodeOrName(
          "bwIdCardList",
          this.tableData.insuredIdentifyType
        );
        this.againCompute();
      }
    },

    ...mapMutations("million", {
      setTenMillionData: "SETTENMILLIONDATA",
      updtPreminumsNum: "UPDTPREMINUMSNUM",
      setFormula: "SETFORMULA"
    }),
    //格式化钱数
    formatNum(num) {
      const numStr = num.toString().split("."); //先转为字符串，再分开整数和小数部分
      let numInt = numStr[0]; // 整数部分
      let numDec = numStr.length > 1 ? "." + numStr[1] : "";
      // 小数部分，只有原数字存在小数点的时候，分割完长度才会大于1，才需要存储小数，记得前面加小数点
      let resultInt = ""; //存储整数部分处理结果
      while (numInt.length > 3) {
        //当剩余整数部分长度大于3时继续处理
        resultInt = "," + numInt.slice(-3) + resultInt; //把后三位截出来，和处理结果拼在一起，前面加逗号
        numInt = numInt.slice(0, -3); // 剩下的部分是除去刚刚截掉的3位的部分
      }
      return numInt + resultInt + numDec; //结果是“剩余的不足3位的整数”+“处理好的整数结果”+“小数部分”
    },

    //     客户类型——纳税人类型——发票类型
    // customerType 1——taxPayerType 4——InvoiceType 2,3,4
    // customerType 2——taxPayerType 1——InvoiceType 1,2,3,4
    // customerType 2——taxPayerType 2,3——InvoiceType 2,3,4
    // customerType 3——taxPayerType 3——InvoiceType 2,3,4

    //      private String accountNo;// 开户行账号
    //  private String bankCode;// 开户行/银行代码
    //  private String companyName;// 公司名称
    //  private String copyDataFromType;// 开票公司信息(同投保人/同被保人/第三方)
    //  private String customerType;// 客户类型
    //  private String depositeBankName;// 开户行名称代码
    //  private String email;// 邮件地址
    //  private String invoiceType;// 发票类型
    //  private String mobile;// 手机号码
    //  private String taxPayerNo;// 纳税人识别号
    //  private String taxPayerType;// 纳税人类型
    //  private String taxRegisterAddress;// 税务登记地址
    //  private String taxRegisterTel;// 税务登记电话
    // 是否需要发票
    showBillingSwitch() {
      let _this = this;
      if (!this.showBilling) {
        // this.requestData.invoiceInfo = {
        //   //发票信息
        //   accountNo: "", // 银行卡账号
        //   companyName: "", // 公司名称
        //   copyDataFromType: "1", //
        //   customerType: "2", // 客户类型
        //   depositeBankName: "", //开户银行
        //   email: "", // 邮箱
        //   invoiceType: "1", //发票类型
        //   mobile: "", //注册电话
        //   taxPayerNo: "", //纳税人识别号
        //   taxPayerType: "1", // 纳税人类型
        //   taxRegisterAddress: "", //注册地址
        //   taxRegisterTel: "" //注册电话
        // };
      } else {
        // if (this.requestData.copyDataFromType) {
        //   this.sameInsuredType =
        //     this.requestData.copyDataFromType == "1" ? "y" : "n";
        // }

        // 同投保人
        if (this.sameInsuredType == "y") {
          //同投保人
          if (!this.isGroup) {
            //如果是个人
            if (_this.isNeedEleInvoice) {
              //是否需要电子发票
              _this.isShowCompanyInfo = true;
              _this.invoiceInfo.mobile = _this.formCreateApi.getValue(
                "appliMobile"
              );
              _this.invoiceInfo.email = _this.formCreateApi.getValue(
                "appliEmail"
              );
            }
          }

          this.invoiceInfo.mobile = _this.formCreateApi.getValue("appliMobile");
          this.invoiceInfo.copyDataFromType = "1";
          this.invoiceInfo.companyName = _this.formCreateApi.getValue(
            "appliName"
          );
        } else {
          //同被保人
          if (!this.isGroup) {
            //如果是个人
            if (_this.isNeedEleInvoice) {
              //是否需要电子发票
              _this.invoiceInfo.mobile = _this.formCreateApi.getValue(
                "insuredMobile"
              );
              _this.invoiceInfo.email = "";
            }
          }

          this.invoiceInfo.mobile = _this.formCreateApi.getValue(
            "insuredMobile"
          );
          this.invoiceInfo.copyDataFromType = "2";
          this.invoiceInfo.companyName = _this.formCreateApi.getValue(
            "insuredName"
          );
        }
      }
    },

    // 是否需要电子保单 切换 操作
    changeNeedEleInvoice(flag) {
      this.isNeedEleInvoice = flag;
    },

    // 更改是否同被保人
    changeSameInsuredType(type) {
      this.sameInsuredType = type;

      switch (this.sameInsuredType) {
        case "y":
          this.invoiceInfo.copyDataFromType = "1";
          this.invoiceInfo.companyName = this.formCreateApi.getValue(
            "appliName"
          );
          this.invoiceInfo.mobile = this.formCreateApi.getValue("appliMobile");
          this.invoiceInfo.email = this.formCreateApi.getValue("appliEmail");
          break;

        default:
          this.invoiceInfo.copyDataFromType = "2";
          this.invoiceInfo.companyName = this.formCreateApi.getValue(
            "insuredName"
          );
          this.invoiceInfo.mobile = this.formCreateApi.getValue(
            "insuredMobile"
          );
          this.invoiceInfo.email = "";
          break;
      }
    },
    // 客户类型——纳税人类型——发票类型
    //   customerType 1——taxPayerType 4——InvoiceType 2,3,4
    //   customerType 2——taxPayerType 1——InvoiceType 1,2,3,4
    //   customerType 2——taxPayerType 2,3——InvoiceType 2,3,4
    //   customerType 3——taxPayerType 3——InvoiceType 2,3,4

    // 开票信息-客户类型选择
    customer_onConfirm(value, index, flag) {
      this.billing.taxpayerInfo.taxPayerTypeVal = "";
      this.billing.invoiceInfo.invoiceTypeVal = "";
      this.billing.taxpayerInfo.taxPayerType = [];
      this.billing.invoiceInfo.invoiceType = [];
      this.billing.cutstomInfo.customerTypeVal = value;
      this.invoiceInfo.customerType = index + 1;
      if (flag) {
        if (this.invoiceInfo.customerType == 1) {
          this.billing.taxpayerInfo.taxPayerType = ["个人"];
          this.taxPayerTypeIndex = 0;
          this.billing.taxpayerInfo.taxPayerTypeVal = "个人";
          this.taxpayerInfo_onConfirm(
            this.billing.taxpayerInfo.taxPayerTypeVal,
            this.taxPayerTypeIndex,
            true
          );
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceInfo.taxPayerType = "4";
          // this.invoiceIndex = 1;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          // this.requestData.invoiceInfo.invoiceType = "3";
          let id2 = this.invoiceInfo.invoiceType;
          let typeVal2 = this.$getCodeOrName("invoiceTypeList", id2);
          this.billing.invoiceInfo.invoiceTypeVal = typeVal2
            ? typeVal2
            : "增值税电子普通发票";
          this.invoiceInfo.invoiceType = id2 ? id2 : "3";
          let index2 = this.billing.invoiceInfo.invoiceType.findIndex(
            item => item == this.billing.invoiceInfo.invoiceTypeVal
          );
          // this.invoiceIndex = index2 >= 0 ? index2 : 1;
          if (index2 >= 0) {
            this.invoiceIndex = index2;
          } else {
            this.invoiceIndex = 1;
            this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          }

          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.invoiceInfo.customerType == 2) {
          this.billing.taxpayerInfo.taxPayerType = [
            "一般纳税人 ",
            "小规模纳税人",
            "非增值税纳税人"
          ];
          // this.taxPayerTypeIndex = 0;
          // this.billing.taxpayerInfo.taxPayerTypeVal = "一般纳税人";
          // this.requestData.invoiceInfo.taxPayerType = "1";
          let id3 = this.invoiceInfo.taxPayerType;
          let typeVal3 = this.$getCodeOrName("taxpayerTypeList", id3);
          this.billing.taxpayerInfo.taxPayerTypeVal = typeVal3
            ? typeVal3
            : "一般纳税人";
          let index3 = this.billing.taxpayerInfo.taxPayerType.findIndex(
            item => item == this.billing.taxpayerInfo.taxPayerTypeVal
          );

          if (index3 >= 0) {
            this.taxPayerTypeIndex = index3;
            // this.billing.taxpayerInfo.taxPayerTypeVal = typeVal3;
          } else {
            this.taxPayerTypeIndex = 0;
            this.billing.taxpayerInfo.taxPayerTypeVal = "一般纳税人";
          }
          this.taxpayerInfo_onConfirm(
            this.billing.taxpayerInfo.taxPayerTypeVal,
            this.taxPayerTypeIndex,
            true
          );
          this.billing.invoiceInfo.invoiceType = [
            "增值税专用发票 ",
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          // this.invoiceIndex = 0;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税专用发票";
          // this.requestData.invoiceInfo.invoiceType = "1";
          let id4 = this.invoiceInfo.invoiceType;
          let typeVal4 = this.$getCodeOrName("invoiceTypeList", id4);
          this.invoiceInfo.invoiceType = id4 ? id4 : "1";
          this.billing.invoiceInfo.invoiceTypeVal = typeVal4
            ? typeVal4
            : "增值税电子普通发票";
          let index4 = this.billing.invoiceInfo.invoiceType.findIndex(
            item => item == this.billing.invoiceInfo.invoiceTypeVal
          );

          if (index4 >= 0) {
            this.invoiceIndex = index4;
            // this.billing.invoiceInfo.invoiceTypeVal = typeVal4;
          } else {
            this.invoiceIndex = 1;
            this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          }

          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.invoiceInfo.customerType == 3) {
          this.billing.taxpayerInfo.taxPayerType = ["非增值税纳税人"];
          this.billing.taxpayerInfo.taxPayerTypeVal = "非增值税纳税人";
          this.taxPayerTypeIndex = 0;
          this.invoiceInfo.taxPayerType = "3";
          this.taxpayerInfo_onConfirm(
            this.billing.taxpayerInfo.taxPayerTypeVal,
            this.taxPayerTypeIndex,
            true
          );
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          // this.invoiceIndex = 0;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税普通发票";
          // this.requestData.invoiceInfo.invoiceType = "2";
          let id5 = this.invoiceInfo.invoiceType;
          let typeVal5 = this.$getCodeOrName("invoiceTypeList", id5);
          this.invoiceInfo.invoiceType = id5 ? id5 : "2";
          this.billing.invoiceInfo.invoiceTypeVal = typeVal5
            ? typeVal5
            : "增值税普通发票";
          let index5 = this.billing.invoiceInfo.invoiceType.findIndex(
            item => item == this.billing.invoiceInfo.invoiceTypeVal
          );

          if (index5 >= 0) {
            this.invoiceIndex = index5;
            // this.billing.invoiceInfo.invoiceTypeVal = typeVal5;
          } else {
            this.invoiceIndex = 1;
            this.billing.invoiceInfo.invoiceTypeVal = "增值税普通发票";
          }
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
      } else {
        if (this.invoiceInfo.customerType == 1) {
          this.billing.taxpayerInfo.taxPayerType = ["个人"];
          this.taxPayerTypeIndex = 0;
          this.billing.taxpayerInfo.taxPayerTypeVal = "个人";
          this.taxpayerInfo_onConfirm(
            this.billing.taxpayerInfo.taxPayerTypeVal,
            this.taxPayerTypeIndex
          );
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceInfo.taxPayerType = "4";
          this.invoiceIndex = 1;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          this.invoiceInfo.invoiceType = "3";

          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.invoiceInfo.customerType == 2) {
          this.billing.taxpayerInfo.taxPayerType = [
            "一般纳税人 ",
            "小规模纳税人",
            "非增值税纳税人"
          ];
          this.taxPayerTypeIndex = 0;
          this.billing.taxpayerInfo.taxPayerTypeVal = "一般纳税人";
          this.invoiceInfo.taxPayerType = "1";

          this.taxpayerInfo_onConfirm(
            this.billing.taxpayerInfo.taxPayerTypeVal,
            this.taxPayerTypeIndex
          );
          this.billing.invoiceInfo.invoiceType = [
            "增值税专用发票 ",
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceIndex = 0;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税专用发票";
          this.invoiceInfo.invoiceType = "1";
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.invoiceInfo.customerType == 3) {
          this.billing.taxpayerInfo.taxPayerType = ["非增值税纳税人"];
          this.billing.taxpayerInfo.taxPayerTypeVal = "非增值税纳税人";
          this.taxPayerTypeIndex = 0;
          this.invoiceInfo.taxPayerType = "3";
          this.taxpayerInfo_onConfirm(
            this.billing.taxpayerInfo.taxPayerTypeVal,
            this.taxPayerTypeIndex
          );
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceIndex = 0;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税普通发票";
          this.invoiceInfo.invoiceType = "2";
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
      }

      this.billing.cutstomInfo.customerShowPicker = false;
    },

    //开票信息 - 纳税人类型
    taxpayerInfo_onConfirm(value, index, flag) {
      this.billing.taxpayerInfo.taxPayerTypeVal = value;
      let id = this.$getCodeOrName("taxpayerTypeList", value);
      this.invoiceInfo.taxPayerType = id ? id : index + 1;
      this.billing.taxpayerInfo.taxPayerShowPicker = false;
      if (flag) {
        if (
          this.invoiceInfo.taxPayerType == 2 ||
          this.invoiceInfo.taxPayerType == 3
        ) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          // this.invoiceIndex = 1;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          // this.requestData.invoiceInfo.invoiceType = "3";

          let id6 = this.invoiceInfo.invoiceType;
          let typeVal6 = this.$getCodeOrName("invoiceTypeList", id6);
          this.invoiceInfo.invoiceType = id6 ? id6 : "3";
          this.billing.invoiceInfo.invoiceTypeVal = typeVal6
            ? typeVal6
            : "增值税电子普通发票";
          let index6 = this.billing.invoiceInfo.invoiceType.findIndex(
            item => item == this.billing.invoiceInfo.invoiceTypeVal
          );
          if (index6 >= 0) {
            this.invoiceIndex = index6;
            // this.billing.invoiceInfo.invoiceTypeVal = typeVal6;
          } else {
            this.invoiceIndex = 1;
            this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          }
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.invoiceInfo.taxPayerType == 1) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税专用发票 ",
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          // this.invoiceIndex = 0;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税专用发票";
          // this.requestData.invoiceInfo.invoiceType = "1";

          let id7 = this.invoiceInfo.invoiceType;
          let typeVal7 = this.$getCodeOrName("invoiceTypeList", id7);
          this.invoiceInfo.invoiceType = id7 ? id7 : "1";
          this.billing.invoiceInfo.invoiceTypeVal = typeVal7 ? typeVal7 : "";
          let index7 = this.billing.invoiceInfo.invoiceType.findIndex(
            item => item == this.billing.invoiceInfo.invoiceTypeVal
          );
          if (index7 >= 0) {
            this.invoiceIndex = index7;
            // this.billing.invoiceInfo.invoiceTypeVal = typeVal7;
          } else {
            this.invoiceIndex = 0;
            this.billing.invoiceInfo.invoiceTypeVal = "增值税专用发票";
          }
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.invoiceInfo.taxPayerType == 4) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          // this.invoiceIndex = 0;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税普通发票";
          // this.requestData.invoiceInfo.invoiceType = "2";

          let id8 = this.invoiceInfo.invoiceType;
          let typeVal8 = this.$getCodeOrName("invoiceTypeList", id8);
          this.invoiceInfo.invoiceType = id8 ? id8 : "2";
          this.billing.invoiceInfo.invoiceTypeVal = typeVal8 ? typeVal8 : "";
          let index8 = this.billing.invoiceInfo.invoiceType.findIndex(
            item => item == this.billing.invoiceInfo.invoiceTypeVal
          );
          if (index8 >= 0) {
            this.invoiceIndex = index8;
            // this.billing.invoiceInfo.invoiceTypeVal = typeVal8;
          } else {
            this.invoiceIndex = 0;
            this.billing.invoiceInfo.invoiceTypeVal = "增值税普通发票";
          }
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
      } else {
        if (
          this.invoiceInfo.taxPayerType == 2 ||
          this.invoiceInfo.taxPayerType == 3
        ) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceIndex = 1;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          this.invoiceInfo.invoiceType = "3";
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.invoiceInfo.taxPayerType == 1) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税专用发票 ",
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceIndex = 0;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税专用发票";
          this.invoiceInfo.invoiceType = "1";
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.invoiceInfo.taxPayerType == 4) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceIndex = 0;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税普通发票";
          this.invoiceInfo.invoiceType = "2";

          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
      }
    },

    // 开票信息 - 发票类型
    invoiceInfo_onConfirm(value, index) {
      // console.log(1490, this.requestData.invoiceInfo.customerType);
      this.billing.invoiceInfo.invoiceTypeVal = value;
      let id = this.$getCodeOrName("invoiceTypeList", value);
      this.invoiceInfo.invoiceType = id ? id : index + 1;
      this.billing.invoiceInfo.invoicePicker = false;
      let inv = this.invoiceInfo.invoiceType;
      // this.isShowExpend = false;
      if (this.isExpend) {
        this.isExpend = false;
        this.isShowCompanyInfo = false;
      }
      switch (inv) {
        case 1: // 增值税专用发票 1
          this.invoiceType = "1";
          this.isShowCompanyInfo = true;
          this.isShowExpend = false;
          break;
        case 2: // 普通电子发票 2
          this.invoiceType = "2";
          this.isShowCompanyInfo = false;
          this.isShowExpend = true;
          break;
        case 3: // 电子普通发票 3
          this.invoiceType = "3";
          this.isShowCompanyInfo = false;
          this.isShowExpend = true;
          break;
        case 4: // 其他发票 4
          this.invoiceType = "4";
          this.isShowCompanyInfo = false;
          this.isShowExpend = true;
          break;
      }

      if (this.sameInsuredType == "y") {
        this.copyDataFromType = "1";
        // 公司名称 == 投保人姓名-----------------------------
        this.invoiceInfo.companyName = this.formCreateApi.getValue("appliName");
        this.invoiceInfo.mobile = this.formCreateApi.getValue("appliMobile");
        this.invoiceInfo.email = this.formCreateApi.getValue("appliEMail");
      } else {
        this.copyDataFromType = "2";
        this.invoiceInfo.mobile = this.formCreateApi.getValue("insuredMobile");
        this.invoiceInfo.companyName = this.formCreateApi.getValue(
          "insuredName"
        );
        this.invoiceInfo.mobile = this.formCreateApi.getValue("insuredMobile");
        this.invoiceInfo.email = "";
      }

      // this.isExpend = false;
    },

    //是否展开更多隐藏信息
    expendInfo() {
      if (this.isExpend) {
        this.isExpend = false;
        this.isShowCompanyInfo = false;
      } else {
        this.isExpend = true;
        this.isShowCompanyInfo = true;
      }
    },
    // 验证发票
    checkInvoice() {
      let checkInfo = [
        {
          type: "2",
          key: "customerType",
          customerType: "客户类型",
          isRequire: true,
          rules: ["客户类型不能为空"]
        },

        {
          type: "2",
          key: "taxPayerType",
          taxPayerType: "纳税人类型",
          isRequire: true,
          rules: ["纳税人类型不能为空"]
        },
        {
          type: "2",
          key: "invoiceType",
          invoiceType: "发票类型",
          isRequire: true,
          rules: ["发票类型不能为空"]
        },
        {
          type: "2",
          key: "companyName",
          companyName: "公司名称",
          isRequire: true,
          rules: ["公司名称不能为空"]
        },
        {
          type: "2",
          key: "taxPayerNo",
          taxPayerNo: "纳税人识别号",
          isRequire: true,
          rules: ["纳税人识别号不能为空"]
        },
        {
          type: "2",
          key: "mobile",
          taxRegisterTel: "移动电话",
          reg: /^1[3|4|5|6|8|7|9][0-9]\d{8}$/,
          rules: ["移动电话不能为空", "请输入正确的移动电话"]
        },
        {
          type: "2",
          key: "taxRegisterAddress",
          taxRegisterAddress: "注册地址",
          isRequire: true,
          rules: ["注册地址不能为空", "请输入100个字符以内的注册地址"]
        },
        {
          type: "2",
          key: "taxRegisterTel",
          taxRegisterTel: "注册电话",
          rules: ["注册电话不能为空", "请输入20个字符以内的注册电话"]
        },
        {
          type: "2",
          key: "depositeBankName",
          depositeBankName: "开户银行",
          isRequire: true,
          rules: ["开户银行不能为空"]
        },
        {
          type: "2",
          key: "accountNo",
          accountNo: "银行账号",
          reg: /[0-9]\-*/,
          isRequire: true,
          rules: ["银行卡账号不能为空", "请输入正确的银行账号"]
        },
        {
          type: "2",
          key: "email",
          reg: /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/,
          email: "邮箱",
          isRequire: false,
          rules: ["请输入正确的邮箱"]
        }
      ];
      let flag = true;
      for (let i = 0, len = checkInfo.length; i < len; i++) {
        let key = checkInfo[i].key;
        if (checkInfo[i].type == "2" && this.showBilling) {
          // 如果是个人
          if (!this.isGroup) {
            if (key == "mobile") {
              if (!this.invoiceInfo[key]) {
                Toast({
                  forbidClick: true,
                  message: checkInfo[i].rules[0],
                  type: "fail"
                });
                flag = false;
                break;
              } else {
                let value = this.invoiceInfo[key].toString().trim();
                if (!checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              }
            }
            if (key == "email" && this.invoiceInfo[key]) {
              let value = this.invoiceInfo[key].toString().trim();
              if (!checkInfo[i].reg.test(value)) {
                Toast({
                  forbidClick: true,
                  message: checkInfo[i].rules[0],
                  type: "fail"
                });
                flag = false;
                break;
              }
            }
          } else {
            //是团体
            if (
              key == "customerType" ||
              key == "taxPayerType" ||
              key == "invoiceType"
            ) {
              if (!this.invoiceInfo[key]) {
                Toast({
                  forbidClick: true,
                  message: checkInfo[i].rules[0],
                  type: "fail"
                });
                flag = false;
                break;
              }
            }
            let value = this.invoiceInfo[key];
            let inType = this.invoiceInfo.invoiceType;
            // console.log(2631, inType);
            // 1 增值税专用发票
            if (inType == "1") {
              if (!value) {
                if (key == "mobile") {
                  flag = true;
                  continue;
                }
                if (key == "email") {
                  flag = true;
                  continue;
                }
                Toast({
                  forbidClick: true,
                  message: checkInfo[i].rules[0],
                  type: "fail"
                });
                flag = false;
                break;
              } else {
                if (key == "taxRegisterAddress" && value.length > 100) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "taxRegisterTel" && value.length > 20) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "accountNo" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "email" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[0],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              }
            }
            //2 增值税普通发票 4 其他发票  客户类型不是境内个人或者机构
            if (
              (inType == "2" || inType == "4") &&
              this.invoiceInfo.customerType != "3"
            ) {
              if (!value) {
                if (key == "taxPayerNo" && !value) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[0],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              } else {
                if (key == "mobile" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "taxRegisterAddress" && value.length > 100) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "taxRegisterTel" && value.length > 20) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "accountNo" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "email" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[0],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              }
            }
            // 2 增值税普通发票 4 其他发票  客户类型是境内个人或者机构
            if (
              (inType == "2" || inType == "4") &&
              this.invoiceInfo.customerType == "3"
            ) {
              if (value) {
                if (key == "mobile" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "taxRegisterAddress" && value.length > 100) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "taxRegisterTel" && value.length > 20) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "accountNo" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "email" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[0],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              }
            }
            //3 增值税电子普通发票  客户类型不是境内个人或者机构
            if (inType == "3" && this.invoiceInfo.customerType != "3") {
              if (!value) {
                if (key == "taxPayerNo" && !value) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[0],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "mobile" && !value) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[0],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              } else {
                if (key == "mobile" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "taxRegisterAddress" && value.length > 100) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "taxRegisterTel" && value.length > 20) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "accountNo" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "email" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[0],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              }
            }
            //3 增值税电子普通发票  客户类型是境内个人或者机构
            if (inType == "3" && this.invoiceInfo.customerType == "3") {
              if (!value) {
                if (key == "mobile" && !value) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[0],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              } else {
                if (key == "mobile" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "taxRegisterAddress" && value.length > 100) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "taxRegisterTel" && value.length > 20) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "accountNo" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                if (key == "email" && !checkInfo[i].reg.test(value)) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[0],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              }
            }
          }
        }
      }

      if (!this.isGroup) {
        //如果是个人，给客户类型 1（境内个人、政府机关及事业单位） ，纳税人类型4 （个人），发票类型赋默认值（2增值税普通发票 ,3 增值税电子普通发票）   纳税人识别号 taxPayerNo 为证件号

        this.invoiceInfo.customerType = 1;
        this.invoiceInfo.taxPayerType = 4;
        if (this.isNeedEleInvoice) {
          this.invoiceInfo.invoiceType = 3;
        } else {
          this.invoiceInfo.invoiceType = 2;
        }

        this.invoiceInfo.accountNo = "";
        this.invoiceInfo.depositeBankName = "";
        this.invoiceInfo.taxPayerNo = "";
        this.invoiceInfo.taxRegisterAddress = "";
        this.invoiceInfo.taxRegisterTel = "";
      }

      return flag;
    }
  },
  computed: {
    formatSumPremium: {
      get() {
        return (
          // this.orderInfo.sumPremium &&
          // this.formatNum(this.orderInfo.sumPremium.toFixed(2))
          this.testSm && this.formatNum(Number(this.testSm).toFixed(2))
        );
      },
      set() {
        if (this.testSm) {
          return this.formatNum(Number(this.testSm).toFixed(2));
        }
        // if (this.orderInfo.sumPremium) {
        //   return this.formatNum(this.orderInfo.sumPremium.toFixed(2));
        // }
      }
    },
    productTit() {
      return this.detailData.title;
    },
    ...mapGetters("million", ["tenMillionData", "gResult"])
  },
  // beforeRouteEnter: (to, from, next) => {
  //   console.log(3418, from);
  //   next(vm => {
  //     if (from.name) {
  //       if (
  //         from.name.indexOf("OrderDetail") != -1 ||
  //         from.name.indexOf("orderList") != -1
  //       ) {
  //         vm.goBack = "index";
  //       } else {
  //         if (from.name.indexOf("prIndex") != -1) {
  //         }
  //         vm.goBack = "prev";
  //       }
  //     }
  //   });
  // },
  components: {
    globalNav
  }
};
</script>
<style lang="less" scoped>
.myStatistic {
  width: 10px !important;
  height: 10px !important;
}
// formcreate part
/deep/ .__fc_h {
  display: none !important;
}
.title-link-text {
  color: #3b8afe;
  &.cur {
    color: red;
  }
}
// formcreate part
#tenmillionForm {
  background: #f7f7f7;
  padding-bottom: 50px;
  padding-top: 2rem;
  width: 100%;
  height: 100%;
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  // position: absolute;
  // left: 0;
  // top: 0;
  box-sizing: border-box;
  overflow-x: hidden;
  /deep/.has-error .ant-input,
  .has-error .ant-input:hover {
    border-color: transparent;
  }
  /deep/.ant-input:hover {
    border-color: transparent;
  }
  /deep/.ant-input:focus {
    border-color: transparent;
  }
  /deep/.ant-row {
    background-color: white;
  }
  /deep/.ant-col-24 {
    padding: 0 7.5px;
    height: 58px;
  }
  /deep/.ant-input-affix-wrapper .ant-input:not(:last-child) {
    padding-right: 40px;
  }
  /deep/form .has-feedback .ant-input-affix-wrapper .ant-input-suffix {
    padding-right: 0;
  }
  /deep/.transLicenseNo,
  /deep/.transVehicleModelCode,
  /deep/.transEngineNo,
  /deep/.transApprLoad,
  /deep/.transVehicleAge,
  /deep/.transFrameNo,
  /deep/.transModel {
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #ebedf0;
    .ant-form-item-label {
      width: 25%;
    }
    .ant-form-item-control-wrapper {
      width: 75%;
    }
    .ant-input {
      border: none;
      text-align: right;
    }
    .ant-input-number-handler-wrap {
      border: none;
    }
    .ant-input-number:hover {
      border: none;
      box-shadow: none;
    }
    .ant-input-number {
      border: none;
    }
    .ant-input-number-input {
      text-align: center;
    }
    .ant-form-item-control {
      background: none;
    }
  }
  /deep/.commNumOfCopies {
    .ant-form-item-label {
      width: 70%;
    }
    .ant-form-item-control-wrapper {
      width: 30%;
    }
  }
  /deep/ .diyInput {
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #ebedf0;
    .ant-form-item-label {
      width: 30%;
    }
    .ant-form-item-control-wrapper {
      width: 70%;
    }
    .ant-input {
      border: none;
      text-align: right;
    }
    .ant-input-number-handler-wrap {
      border: none;
    }
    .ant-input-number:hover {
      border: none;
      box-shadow: none;
    }
    .ant-input-number {
      border: none;
    }
    .ant-input-number-input {
      text-align: center;
    }
    .ant-form-item-control {
      background: none;
    }
  }

  /deep/.diySelect {
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #ebedf0;
    .ant-form-item-label {
      width: 40%;
    }
    .ant-form-item-control-wrapper {
      width: 60%;
    }
    .ant-select-selection {
      // background: none;
      border: none;
    }
    .ant-select-focused .ant-select-selection,
    .ant-select-selection:focus,
    .ant-select-selection:active,
    .ant-select-selection:hover {
      border: none;
      box-shadow: none;
    }
    .ant-form-item-control {
      background: none;
    }
    // .ant-select-focused .ant-select-selection__placeholder {
    //   display: none !important;
    // }
    // .ant-select-visited .ant-select-selection__placeholder {
    //   display: none !important;
    // }
    // .ant-select-selection__placeholder {
    //   display: block !important;
    // }
  }

  .title-img {
    margin: 9px 10px 5px 5px;
    img {
      width: 71.5px !important;
      height: 53px !important;
    }
  }

  .rowTitle {
    height: 60px;
    background-color: #fff;
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
      width: 22px;
      height: 22px;
      margin: -5px 3px;
    }
  }
  .card {
    .card-img {
      margin: -4px 0 -4px 5px;
    }
    // margin-bottom: 5px;
    .tip-icon {
      margin: 4px 0 0 5px;
    }
    .tip-text {
      color: #3b8afe;
      font-size: 13px;
      margin-left: 5px;
    }
    .title-container {
      height: 50px;
      margin-bottom: 0px;
      background-color: white;
    }
    .select-customer-text {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
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
      color: rgba(59, 138, 254, 1);
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
    .tip-container {
      margin: 5px 15px;
      height: 34px;
      background: #3b8afe1a;
      border-radius: 2px;
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
  .allow-protocol-check-box {
    margin-right: 0.3rem;
  }
  .tabbar {
    position: fixed;
    -webkit-overflow-scroll: touch;
    bottom: 0;
    display: flex;
    width: 100%;
    z-index: 1;
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

    .btn {
      flex: 1;
      font-size: 16px;
      button {
        width: 100%;
      }
    }
  }

  .after::after {
    position: absolute;
    box-sizing: border-box;
    content: " ";
    pointer-events: none;
    right: 0;
    bottom: 0;
    left: 0.42667rem;
    border-bottom: 0.02667rem solid #ebedf0;
    -webkit-transform: scaleY(0.5);
    transform: scaleY(0.5);
  }
  .ant-calendar.ant-calendar-time {
    position: relative;
    top: 36px;
  }
}
#tenmillionForm {
  /deep/.formTitle {
    font-size: 14px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    line-height: 25px;

    /deep/.ant-form-item label {
      font-size: 14px;
      font-family: PingFangSC-Semibold, PingFang SC;
      font-weight: 600;
      color: #333333;
      line-height: 25px;
    }
    .ant-form-item-label > label {
      font-size: 14px;
      font-family: PingFangSC-Semibold, PingFang SC;
      font-weight: 600;
      color: #333333;
      line-height: 25px;
    }
    /deep/.ant-form-item-label {
      width: 80%;
    }
    /deep/.ant-form-item-control-wrapper {
      width: 20%;
      .ant-form-item-control {
        text-align: right;
        padding-right: 0.32rem;
        img {
          vertical-align: inherit;
        }
      }
    }
  }

  /deep/.birth {
    border-bottom: 1px solid #ebedf0;
    margin-bottom: 0;
    display: flex;
    align-items: center;
    .ant-form-item-label,
    .ant-form-item-control-wrapper {
      flex: 1;
    }
    .ant-form-item-control-wrapper {
      text-align: right;
      .birth {
        border-bottom: none;
      }

      /deep/.ant-form-item-label {
        text-align: right;
        width: 100%;
      }
      .ant-form-item-control-wrapper {
        flex: 0;
      }
      .anticon {
        color: rgba(0, 0, 0, 0.25);
        font-size: 0.32rem;
      }
      .titleIcon {
        font-size: 0.37333rem;
        font-weight: 400;
        color: #3b8afe;
        line-height: 0.53333rem;
      }
    }
    .ant-col-24 {
      height: auto;
    }
  }
  /deep/.labelForm {
    border-bottom: 1px solid #ebedf0;
    margin-bottom: 0;
    display: flex;
    align-items: center;
    .ant-form-item-label,
    .ant-form-item-control-wrapper {
      flex: 1;
    }
    .ant-form-item-label {
      font-size: 0.37333rem;
      font-weight: 600;
      color: #333333;
      line-height: 0.66667rem;
    }
    .ant-form-item {
      margin-bottom: 0;
    }
    .titleIcon {
      font-size: 0.37333rem;
      font-weight: 400;
      color: #3b8afe;
      line-height: 0.53333rem;
    }
    .ant-form-item-control-wrapper {
      flex: 1;
      display: flex;
      align-items: center;
      .ant-form-item-children {
        label {
          display: flex;
          align-items: center;
        }
      }
      .ant-col-24 {
        height: auto;
        flex: 1;
        .labelItem1 {
          display: flex;
          align-items: center;
          justify-content: flex-end;

          .ant-form-item-label {
            width: auto;
            flex: none;
          }
          .ant-form-item-control-wrapper {
            width: 20px;
            flex: none;
          }
        }
        .labelItem {
          display: flex;
          align-items: center;

          .ant-form-item-label {
            width: auto;
            flex: none;
          }
          .ant-form-item-control-wrapper {
            width: 100%;
            flex: none;
            .ant-form-item-children > label > div {
              width: 100%;
              display: flex;
              align-items: center;
            }
          }
          .labelItem2 {
            display: flex;
            align-items: center;
            .ant-form-item-control-wrapper {
              width: 100%;
              flex: none;
            }
          }
        }
      }
    }
    .ant-form-item-control {
      width: 100%;
    }
  }
  /deep/.uploadFile {
    .ant-col .ant-col-4 .ant-form-item-label {
      flex: none;
      width: 30%;
    }
    .ant-form-item-control > .ant-form-item-children > div > span {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .ant-upload-list {
      width: 80%;
    }
  }
}

.policyholdersInfo {
  background: rgba(255, 255, 255, 1);
  .van-popup {
    width: 100%;
  }
  ul {
    li {
      padding: 28px 15px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 14px;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      position: relative;
      height: 48px;
      line-height: 48px;
      border-bottom: 0.5px solid #f7f7f7;
      .van-cell {
        padding: 0;
      }
      .inputRightContent {
        position: absolute;
        top: 50%;
        right: 15px;
        transform: translateY(-50%);
      }
      button {
        width: 70px;
        height: 27px;
        background: rgba(255, 255, 255, 1);
        border-radius: 13px;
        font-size: 11px;
        font-weight: 400;
        line-height: 27px;
        color: rgba(136, 136, 136, 1);
        background: rgba(255, 255, 255, 1);
        border-radius: 13px;
        border: 1px solid rgba(229, 229, 229, 1);
      }
      .btnActive {
        border: 1px solid rgba(59, 138, 254, 1);
        color: rgba(59, 138, 254, 1);
      }
      .van-cell--required::before {
        position: absolute;
        left: -10px;
        top: 3px;
        color: #ee0a24;
        font-size: 0.37333rem;
        content: "*";
      }
      .showManyLicenseNo {
        padding-left: 30px;
      }
      .smallFont {
        font-size: 11px;
        font-weight: 400;
        color: rgba(136, 136, 136, 1);
      }
    }
    .insideLine {
      margin-left: 15px;
    }

    .date {
      .van-cell__title,
      .van-cell__value {
        flex: none;
      }
      .van-cell__title {
        width: 26%;
        flex-shrink: 0;
      }
      .van-cell__value {
        width: 75%;
        flex-shrink: 1;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
      }
    }

    li:last-child {
      border: none;
    }
    li:first-child {
      height: 53px;
      line-height: 53px;
      border: none;
    }
    li.center {
      justify-content: center;
      font-size: 16px;
    }
    .infoTitle {
      height: 48px;
      background: rgba(247, 247, 247, 1);
    }
    .eleInvoiceTip {
      margin: 5px 15px;
      height: 34px;
      background: #3b8afe1a;
      border-radius: 2px;
      font-size: 13px;
      padding: 0;
      color: #3b8afe;
      justify-content: start;
    }
    .right5 {
      margin-right: 5px;
    }
  }
  .personTitle {
    display: inline-block;
    vertical-align: sub;
    margin-right: 15px;
    width: 22px;
    height: 22px;
    img {
      width: 100%;
    }
  }
  .policyholdersInfoTitle {
    font-size: 14px;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
    line-height: 25px;
  }
  .selectInsuredInfo {
    font-size: 14px;
    font-weight: 400;
    color: rgba(59, 138, 254, 1);
    line-height: 20px;
  }
}
/deep/.formRow,
/deep/.titleAndIcon {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #ebedf0;
  .ant-form-item-label {
    width: 60%;
  }
  .ant-form-item-control-wrapper {
    width: 40%;
    .ant-col-24 {
      flex: 1;
      .ant-form-item-control-wrapper {
        width: 100%;
        text-align: center;
        .ant-input {
          padding: 5px;
          text-align: center;
        }
        .anticon {
          font-size: 18px;
          font-weight: 400;
          color: #969799;
        }
      }
    }
  }
}
/deep/ .ant-select-selection-selected-value {
  width: 100%;
  text-align: right;
}
/deep/ .ant-input {
  border: none;
  text-align: right;
}

/deep/ .ant-input:hover {
  border: none;
}
/deep/ .has-error .ant-input,
/deep/ .has-error .ant-input:hover {
  border: none;
  background: none;
}
/deep/ .ant-input:focus {
  border: none;
  box-shadow: none;
}
/deep/ img {
  vertical-align: initial;
  border-style: none;
}
/deep/form .has-feedback > .ant-select .ant-select-selection-selected-value,
/deep/form
  .has-feedback
  :not(.ant-input-group-addon)
  > .ant-select
  .ant-select-selection-selected-value {
  padding-right: 0.5rem;
}
/deep/ .van-picker-column__item {
  color: #8f8f8f !important;
}
/deep/ .van-picker-column__item--selected {
  color: black !important;
}

#tenmillionForm {
  .cascade {
    display: flex;
    font-size: 14px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    .outUl {
      flex: 1;
      padding: 5px 10px;
    }
    .childUl {
      flex: 1;
      padding: 5px 10px;
    }
    .leafUl {
      flex: 1;
      padding: 5px 10px;
    }
    .activeCity {
      background: rgba(59, 138, 254, 0.3);
    }
  }
  .van-sidebar-item--select {
    border-color: #1890ff;
  }
  /deep/ .formSwitch {
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #ebedf0;
    .ant-form-item-label {
      width: 30%;
    }
    .ant-form-item-control-wrapper {
      width: 70%;
      .ant-form-item-control {
        text-align: right;
        padding-right: 20px;
      }
    }
  }
  .empTip {
    margin: 0 15px;
    background: #ffe8ca;
    font-size: 13px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #fc820e;
    justify-content: start;
    padding: 5px 15px;
    border-radius: 10px;
    p {
      padding: 0;
      margin: 0;
    }
  }
}
/deep/ .van-nav-bar .van-icon {
  color: white;
}
/deep/ .ant-input[disabled] {
  background-color: #fff;
}
/deep/ .ant-select-disabled .ant-select-selection {
  background-color: #fff;
}
</style>

