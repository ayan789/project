<!--
 * @Description: 财产险-投保信息填写
 * @Date: 2020-03-11 14:25:45
 * @Author: zhangminjie
 * @LastEditTime: 2020-05-20 14:01:43
 * @FilePath: \dev-scyh\src\views\nonCar\propertyInsurance\insuranceInformation.vue
 -->
<template>
  <div class="insuranceInformation" id="insuranceInformation">
    <globalNav :title="globalNav.title" @clickBack="backToPrePage"></globalNav>
    <div class="insuranceInfoHeader">
      <img src="@/assets/nonCar/propertyInsurance/infoInput.png" alt="header" />
      <h3>货运无忧</h3>
    </div>
    <div class="policyholdersInfo top10">
      <ul>
        <li class="infoTitle">
          <div>
            <div class="personTitle">
              <img :src="toubao" alt="" />
            </div>
            <span class="policyholdersInfoTitle">投保人信息</span>
          </div>
          <div
            class="selectInsuredInfo rightCenter"
            @click="selectInsuredMethod(false)"
          >
            <span class="right13">选择投保人</span>
            <div class="subIcon">
              <img :src="identityImg" alt="" />
            </div>
          </div>
        </li>
        <li class="insideLine">
          <van-field
            v-model="requestData.appliName"
            required
            clearable
            name="appliName"
            label="投保人姓名"
            placeholder="请输入投保人姓名"
            input-align="right"
          />
        </li>
        <li class="insideLine">
          <van-field
            readonly
            clickable
            required
            :value="policyholder_certificateType.certificateTypeVal"
            placeholder="请选择证件类型"
            @click="policyholder_certificateType.showPicker = true"
            input-align="right"
          >
            <div slot="label">
              <span>证件类型</span>
            </div>
            <div slot="right-icon" class="rightCenter">
              <van-icon name="arrow" class="grayBf" />
            </div>
          </van-field>
          <van-popup
            v-model="policyholder_certificateType.showPicker"
            position="bottom"
          >
            <van-picker
              show-toolbar
              :columns="policyholder_certificateType.certificateTypeList"
              :default-index="0"
              @cancel="policyholder_certificateType.showPicker = false"
              @confirm="policyholder_onConfirm"
            />
          </van-popup>
        </li>
        <li class="insideLine">
          <van-field
            :formatter="removeAllSpace"
            v-model="requestData.appliIdentifyNumber"
            @blur="
              checkOrgCode(
                requestData.appliIdentifyNumber,
                requestData.appliIdentifyType
              )
            "
            name="appliIdentifyNumber"
            label="证件号码"
            required
            clearable
            placeholder="请输入证件号码"
            :input-align="
              requestData.appliIdentifyType == '111' ||
              requestData.appliIdentifyType == '01'
                ? 'right'
                : 'right'
            "
            :class="[
              requestData.appliIdentifyType == '111' ||
              requestData.appliIdentifyType == '01'
                ? 'idCard'
                : ''
            ]"
          />
          <!-- placeholder="请输入证件号码" -->
          <span
            v-show="
              requestData.appliIdentifyType == '111' ||
                requestData.appliIdentifyType == '01'
            "
            class="selectInsuredInfo inputRightContent"
            @click="ocrDistinguish('appliCard')"
          >
            <!-- <span class="right13" style="color:#C1C1C1">请输入证件号码</span> -->
            <van-icon name="photograph" size="16px" />
          </span>
        </li>
        <li class="insideLine">
          <van-field
            v-model="requestData.appliMobile"
            name="appliMobile"
            label="手机号"
            required
            clearable
            placeholder="请输入手机号"
            maxlength="11"
            type="digit"
            input-align="right"
          />
        </li>
        <li class="insideLine">
          <van-field
            v-model="requestData.appliEMail"
            name="appliEMail"
            clearable
            label="邮箱"
            input-align="right"
            placeholder="请输入邮箱地址，可接收电子保单"
          />
        </li>
      </ul>
    </div>
    <div class="insuredInfo policyholdersInfo top10">
      <ul>
        <li class="infoTitle">
          <div>
            <div class="personTitle">
              <img :src="beibao" alt="" />
            </div>
            <span class="policyholdersInfoTitle">被保人信息</span>
          </div>

          <div
            class="selectInsuredInfo rightCenter"
            @click="selectInsuredMethod(true)"
          >
            <span class="right13">选择被保人</span>
            <div class="subIcon">
              <img :src="identityImg" alt="" />
            </div>
          </div>
        </li>
        <li class="insideLine">
          <span>同投保人</span>
          <span class="rightCenter">
            <van-switch v-model="checked" size="14px" @change="insuredSwitch" />
          </span>
        </li>
        <li v-if="!checked" class="insideLine">
          <van-field
            v-model="requestData.insuredName"
            name="insuredName"
            label="被保人姓名"
            required
            clearable
            input-align="right"
            placeholder="请输入姓名"
          />
        </li>
        <li v-if="!checked" class="insideLine">
          <van-field
            readonly
            clickable
            required
            :value="insured_certificateType.certificateTypeVal"
            placeholder="请选择证件类型"
            @click="insured_certificateType.showPicker = true"
            input-align="right"
          >
            <div slot="label">
              <span>证件类型</span>
            </div>
            <div slot="right-icon" class="rightCenter">
              <van-icon name="arrow" class="grayBf" />
            </div>
          </van-field>
          <van-popup
            v-model="insured_certificateType.showPicker"
            position="bottom"
          >
            <van-picker
              show-toolbar
              :columns="insured_certificateType.certificateTypeList"
              :default-index="0"
              @cancel="insured_certificateType.showPicker = false"
              @confirm="insured_onConfirm"
            />
          </van-popup>
        </li>
        <li v-if="!checked" class="insideLine">
          <van-field
            :formatter="removeAllSpace"
            v-model="requestData.insuredIdentifyNumber"
            name="insuredIdentifyNumber"
            required
            clearable
            label-width="80px"
            :input-align="
              requestData.insuredIdentifyType == '111' ||
              requestData.insuredIdentifyType == '01'
                ? 'right'
                : 'right'
            "
            :class="[
              requestData.insuredIdentifyType == '111' ||
              requestData.insuredIdentifyType == '01'
                ? 'idCard'
                : ''
            ]"
            placeholder="请输入证件号码"
            label="证件号码"
          />

          <span
            v-show="
              requestData.insuredIdentifyType == '111' ||
                requestData.insuredIdentifyType == '01'
            "
            class="selectInsuredInfo inputRightContent"
            @click="ocrDistinguish('insuredCard')"
          >
            <!-- <span class="right13" style="color:#C1C1C1">请输入证件号码</span> -->
            <van-icon name="photograph" size="16px" />
          </span>
        </li>
        <li v-if="!checked" class="insideLine">
          <van-field
            v-model="requestData.insuredMobile"
            clearable
            name="insuredMobile"
            label="手机号"
            maxlength="11"
            type="digit"
            placeholder="请输入手机号"
            input-align="right"
          />
        </li>
      </ul>
    </div>
    <div class="insuredVehicle policyholdersInfo top10">
      <ul>
        <li class="infoTitle">
          <div>
            <div class="personTitle">
              <img :src="car" alt="" />
            </div>
            <span class="policyholdersInfoTitle">运输工具</span>
          </div>

          <div
            class="selectInsuredInfo rightCenter"
            @click="ocrDistinguish('vehicleCard')"
          >
            <span class="right13">行驶证识别</span>
            <van-icon name="photograph" size="18px" />
          </div>
        </li>

        <li class="insideLine">
          <van-field
            readonly
            clickable
            :value="insuredVehicle.vehicleTypeVal"
            name="车型"
            label="车型"
            required
            placeholder="请选择车型"
            input-align="right"
            @click="insuredVehicle.showPicker = true"
          >
            <div slot="right-icon" class="rightCenter">
              <van-icon name="arrow" class="grayBf" />
            </div>
          </van-field>
          <van-popup v-model="insuredVehicle.showPicker" position="bottom">
            <van-picker
              show-toolbar
              :columns="insuredVehicle.vehicleTypeList"
              @cancel="insuredVehicle.showPicker = false"
              @confirm="insuredVehicle_onConfirm"
            />
          </van-popup>
        </li>
        <li class="insideLine">
          <van-field
            clearable
            v-model="requestData.licenseNo"
            name="licenseNo"
            label="车牌号"
            placeholder="请输入车牌号"
            label-width="55px"
            input-align="right"
            required
            @blur="convertUpperCase('licenseNo')"
          />
        </li>
        <li class="insideLine">
          <van-field
            clearable
            v-model="requestData.engineNo"
            name="engineNo"
            label="发动机号"
            input-align="right"
            required
            placeholder="请输入发动机号"
            @blur="convertUpperCase('engineNo')"
          />
        </li>
        <li class="insideLine">
          <van-field
            clearable
            v-model="requestData.approvalQuality"
            name="approvalQuality"
            label="核定载质量/总牵引质量(吨)"
            input-align="right"
            label-width="170px"
            required
            placeholder="请输入吨位"
            @blur="oninput"
          />
        </li>
        <li class="insideLine">
          <van-field
            clearable
            v-model="requestData.vehicleAge"
            name="vehicleAge"
            label="车龄(年)"
            input-align="right"
            type="digit"
            required
            placeholder="请输入车龄"
          />
        </li>
        <li class="insideLine">
          <van-field
            clearable
            v-model="requestData.brandModelName"
            name="brandModelName"
            label="厂牌型号"
            input-align="right"
            required
            @blur="convertUpperCase('brandModelName')"
            placeholder="请输入厂牌型号"
          />
        </li>
      </ul>
    </div>
    <div class="coverPeriod policyholdersInfo top10">
      <ul>
        <li class="infoTitle">
          <div>
            <div class="personTitle">
              <img :src="carInfo" alt="" />
            </div>
            <span class="policyholdersInfoTitle">保障期限</span>
          </div>
        </li>
        <li class="insideLine">
          <span>保障年限</span>
          <span class="gary102">1年</span>
        </li>
        <li class="date insideLine">
          <van-cell
            :value="selectDate"
            required
            label-width="80px"
            @click="showDate = true"
            class="rightCenter"
          >
            <template slot="title">
              <span>保险起止日期</span>
            </template>
            <van-icon
              slot="right-icon"
              name="arrow"
              class="grayBf left13 verticalMid"
            />
          </van-cell>
          <van-popup
            v-model="showDate"
            position="bottom"
            :style="{ height: '70%' }"
          >
            <van-datetime-picker
              v-model="currentDate"
              type="date"
              :min-date="minDate"
              :max-date="maxDate"
              @confirm="selectDateConfirm"
              @cancel="showDate = false"
            />
          </van-popup>
        </li>
      </ul>
    </div>
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
            v-model="requestData.invoiceInfo.taxPayerNo"
            name="taxPayerNo"
            label="纳税人识别号"
            placeholder="请输入纳税人识别号"
            input-align="right"
            :required="this.requestData.invoiceInfo.customerType != '3'"
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
            v-model="requestData.invoiceInfo.mobile"
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
            v-model="requestData.invoiceInfo.taxRegisterAddress"
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
            v-model="requestData.invoiceInfo.taxRegisterTel"
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
            v-model="requestData.invoiceInfo.depositeBankName"
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
            v-model="requestData.invoiceInfo.companyName"
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
            v-model="requestData.invoiceInfo.accountNo"
            name="accountNo"
            label="银行账号"
            :required="invoiceType == '1'"
            placeholder="请输入银行账号"
            input-align="right"
            clearable
          />
        </li>
        <li
          v-show="showBilling && isNeedEleInvoice && isShowCompanyInfo"
          class="insideLine"
        >
          <van-field
            v-model="requestData.invoiceInfo.email"
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
    <div class="insuranceInformationBottomMid">
      <p>
        <van-checkbox
          v-model="agree"
          @click="changeAgree"
          checked-color="#3b8afe"
        />
        <span>
          本人已认真阅读并同意
          <strong @click="redirectTerms(3)">《保险条款》</strong>
          <strong @click="redirectTerms(1)">《投保须知》</strong>
          <strong @click="redirectTerms(2)">《理赔指南》</strong>
          <strong @click="redirectTerms(4)">《特别约定》</strong>
          和
          <strong @click="redirectTerms(5)">《责任免除》</strong>
          的全部内容
        </span>
      </p>
    </div>
    <div class="insuranceInformationBottom top10">
      <div class="insuranceInformationBottomBottom">
        <span>
          <span class="premium">保费</span>
          <span class="redCost">¥{{ premium }}</span>
        </span>
        <button @click="applyNow" :disabled="isDisabled">立即投保</button>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import {
  Field,
  Switch,
  Picker,
  Calendar,
  Toast,
  RadioGroup,
  Radio
} from "vant";
import globalNav from "../../../components/globalNav";
import { insureStatusZc, queryZc } from "@src/http/module/nonCar.js";
import { trackMap } from "@src/utils/index.js";
Vue.use(Calendar);
Vue.use(Picker);
Vue.use(Switch);
Vue.use(Field);
Vue.use(Toast);
Vue.use(Radio);
Vue.use(RadioGroup);
export default {
  name: "insuranceInformation",
  components: {
    globalNav
  },
  data: () => ({
    // 错误提示
    errApplyName: "", // 投保人姓名
    errApplyIdentifyNumber: "", // 证件号码
    errApplyMobile: "", // 手机号
    errApplyEmail: "", // 邮箱
    errApplyInsureName: "", // 被保人姓名
    errApplyInsuredIdentifyNumber: "", // 被保人证件号码
    errApplyInsuredMobile: "", // 被保人证件手机号
    errVehicleModelCode: "", // 车型
    errLicenseNo: "", // 车牌号
    errEngineNo: "", // 发动机号
    errApprovalQuality: "", // 核定载质量
    errVehicleAge: "", // 车龄
    errBrandModelName: "", // 厂牌号
    errTicketMobile: "", // 开票 手机号
    toubao: require("@/assets/nonCar/worryEdit/toubao.png"),
    beibao: require("@/assets/nonCar/worryEdit/beibao.png"),
    car: require("@/assets/nonCar/worryEdit/car.png"),
    carInfo: require("@/assets/nonCar/worryEdit/carInfo.png"),
    identityImg: require("@/assets/nonCar/worryEdit/identity.png"),
    username: "",
    checked: true,
    agree: false,
    globalNav: {
      title: "投保信息填写"
    },
    value: "",
    selectDate: "", //选择保险起期
    currentDate: new Date(),
    showDate: false, //显示日期控件
    calendarColor: "#3B8AFE", //日期控件的颜色
    e_invoiceType: "y", // 是否需要发票的标志
    sameInsuredType: "y", //是否同被保人
    showBilling: false,
    isShowExpend: false,
    //证件类型
    identifyType: [
      { id: "111", text: "身份证", type: "01" },
      { id: "801", text: "组织机构代码证", type: "07" },
      { id: "810", text: "三证合一代码", type: "21" },
      { id: "28", text: "港澳台居民居住证", type: "28" }
    ],
    policyholder_certificateType: {
      //投保人证件类型
      // certificateTypeList: [
      //   "身份证",
      //   "港澳台居民居住证",
      //   "组织机构代码证",
      //   "三证合一代码"
      // ],
      certificateTypeList: [
        "三证合一代码",
        "组织机构代码证",
        "身份证",
        "港澳台居民居住证"
      ],
      showPicker: false, //证件类型选择器
      certificateTypeVal: ""
    },
    insured_certificateType: {
      //投保人证件类型
      // certificateTypeList: [
      //   "身份证",
      //   "港澳台居民居住证",
      //   "组织机构代码证",
      //   "三证合一代码"
      // ],
      certificateTypeList: [
        "三证合一代码",
        "组织机构代码证",
        "身份证",
        "港澳台居民居住证"
      ],
      showPicker: false //证件类型选择器
    },
    insuredVehicle: {
      //车辆类型
      vehicleTypeList: [
        "卡车",
        "厢式货车",
        "封闭货车",
        "平板货车",
        "自卸货车",
        "特殊结构货车",
        "油罐车",
        "汽罐车",
        "液罐车",
        "冷藏车",
        "拖拉机",
        "集装箱",
        "半挂牵引车",
        "栅栏式货车",
        "重型普通货车"
      ],
      showPicker: false, //证件类型选择器
      vehicleType: "",
      vehicleTypeVal: ""
    },
    taxpayerTypeList: [
      { id: 1, text: "一般纳税人 " },
      { id: 2, text: "小规模纳税人" },
      { id: 3, text: "非增值税纳税人" },
      { id: 4, text: "个人" }
    ],
    invoiceTypeList: [
      { id: 1, text: "增值税专用发票 " },
      { id: 2, text: "增值税普通发票" },
      { id: 3, text: "增值税电子普通发票" },
      { id: 4, text: "其他发票" }
    ],
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
    // 车辆类型
    vehicleModelCodeList: [
      { id: "11", text: "拖拉机" },
      { id: "10", text: "冷藏车" },
      { id: "12", text: "集装箱" },
      { id: "01", text: "卡车" },
      { id: "03", text: "封闭货车" },
      { id: "02", text: "厢式货车" },
      { id: "05", text: "自卸货车" },
      { id: "04", text: "平板货车" },
      { id: "07", text: "油罐车" },
      { id: "06", text: "特殊结构货车" },
      { id: "09", text: "液罐车" },
      { id: "08", text: "汽罐车" },
      { id: "15", text: "重型普通货车" },
      { id: "14", text: "栅栏式货车" },
      { id: "13", text: "半挂牵引车" }
    ],
    requestData: {
      orderNo: "", //订单号
      custNo: "",
      appliName: "", //投保人姓名
      appliIdentifyNumber: "", //投保人证件号码
      appliMobile: "", // 投保人手机号码
      appliEMail: "", //投保人邮箱
      appliIdentity: "01", // 投保人与被保人的关系， 01：本人 99：其他
      appliIdentifyType: "810", //投保人证件类型

      insuredName: "", //被保人姓名
      insuredIdentifyNumber: "", //被保人证件号码
      insuredMobile: "", // 被保人手机号码
      insuredIdentifyType: "810", // 被保人证件类型

      licenseNo: "", // 车牌号
      engineNo: "", // 发动机号
      brandModelName: "", //厂牌型号
      approvalQuality: "", // 核定载质量
      vehicleAge: "", //车龄
      vehicleModelCode: "", //车型代码

      startDate: "", // 开始时间
      endDate: "", //结束时间
      invoiceInfo: {
        //发票信息
        copyDataFromType: "",
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
      }
    },
    isNeedEleInvoice: true,
    isGroup: true,
    formatPremium: "",
    isExpend: false,
    invoiceType: "1",
    isShowCompanyInfo: true,
    invoiceIndex: 0,
    taxPayerTypeIndex: 0,
    isDisabled: false,
    docmHeight: document.documentElement.clientHeight, //默认屏幕高度
    showHeight: document.documentElement.clientHeight, //实时屏幕高度
    // hidshow: true, //显示或者隐藏footer,
    isResize: false, //默认屏幕高度是否已获取
    minDate: new Date(new Date().getTime() + 24 * 60 * 60 * 1000),
    maxDate: new Date(new Date().getTime() + 24 * 60 * 60 * 1000 * 60),
    premium: "",
    fromRoute: {}
  }),

  beforeRouteEnter(to, from, next) {
    // console.log(961, to, from, next);
    next(vm => {
      vm.fromRoute = from;
      // console.log(970, vm);
    });
  },
  watch: {
    //当实时屏幕高度小于默认高度时, 说明键盘弹起来了, 这时就隐藏底部菜单, 当键盘收起时, 再显示底部菜单
    // showHeight() {
    //   if (this.docmHeight >= this.showHeight) {
    //     this.hidshow = false;
    //   } else {
    //     this.hidshow = true;
    //   }
    //   console.log(this.hidshow);
    // }
  },
  created() {
    window.onresize = () => {
      return (() => {
        if (!this.isResize) {
          //默认屏幕高度
          this.docmHeight = document.documentElement.clientHeight;
          this.isResize = true;
        }
        //实时屏幕高度
        this.showHeight = document.body.clientHeight;
        console.log(this.showHeight);
      })();
    };
    this.initData();
    this.watermarket();
  },
  destroyed() {},
  methods: {
    // 去除证件号码空格
    removeAllSpace(str) {
      return str.replace(/\s+/g, "");
    },
    // 水印
    watermarket() {
      this.$native.showOrCloseWatermarket(true, data => {
        // Toast("showOrCloseWatermarket 接口调用成功，壳响应数据:" + data);
      });
    },
    // init数据
    initData() {
      // console.log( window.location);
      let shareData = this.$store.state.nonCar.shareData.insuranceInfo;

      this.formatPremium = this.$store.state.nonCar.shareData.productInfo.formatPremium;
      this.premium = this.formatNum(
        parseInt(
          this.$store.state.nonCar.shareData.productInfo.sumPremium
        ).toFixed(2)
      );

      for (let key in shareData) {
        this.requestData[key] = shareData[key];
      }
      if (!this.requestData.storeCode) {
        this.requestData.storeCode = localStorage.getItem("userInfo")
          ? localStorage.getItem("storeCode")
          : "";
      }
      this.initPersonInfo();
      if (this.requestData.appliIdentity == "01") {
        this.checked = true;
      } else if (this.requestData.appliIdentity == "99") {
        this.checked = false;
      } else {
        this.checked = true;
      }
      if (this.showBilling) {
        // 同投保人
        if (this.sameInsuredType == "y") {
          this.requestData.invoiceInfo.mobile = this.requestData.appliMobile;
          this.requestData.invoiceInfo.email = this.requestData.appliEMail;
        }
        // 同被保人
        if (this.sameInsuredType == "n") {
          this.requestData.invoiceInfo.mobile = this.requestData.insuredMobile;
          this.requestData.invoiceInfo.email = "";
        }
      }
      // 投保人证件类型
      if (this.requestData.appliIdentifyType) {
        // let typeVal1 = this.$getCodeOrName(
        //   "hyIdCardCusList",
        //   this.requestData.appliIdentifyType
        // );
        let typeVal = this.$getCodeOrName(
          "hyIdCardList",
          this.requestData.appliIdentifyType
        );
        this.policyholder_certificateType.certificateTypeVal = typeVal
          ? typeVal
          : "";
      }
      // 被保人证件类型
      if (this.requestData.insuredIdentifyType) {
        let { insuredIdentifyType } = this.requestData;
        // let typeVal1 = this.$getCodeOrName(
        //   "hyIdCardCusList",
        //   this.requestData.insuredIdentifyType
        // );
        let typeVal = this.$getCodeOrName("hyIdCardList", insuredIdentifyType);
        this.insured_certificateType.certificateTypeVal = typeVal
          ? typeVal
          : "";

        // //TODO suncao渲染被保人信息
        // this.requestData.insuredName = this.requestData.insuredName; //被保人姓名
        // this.requestData.insuredIdentifyNumber = this.requestData.insuredIdentify; //被保人证件号码
        // this.requestData.insuredMobile = this.requestData.insuredMobile; // 被保人手机号码
      }
      // 车辆类型
      if (this.requestData.vehicleModelCode) {
        let { vehicleModelCode } = this.requestData;
        let typeVal = this.$getCodeOrName(
          "vehicleModelCodeList",
          vehicleModelCode
        );
        this.insuredVehicle.vehicleTypeVal = typeVal ? typeVal : "";

        //TODO suncao渲染车辆信息
        // this.requestData.licenseNo = this.requestData.carInfoVO.licenseplateno; // 车牌号  licenseplateno
        // this.requestData.engineNo = this.requestData.carInfoVO.engineNo; // 发动机号 engineNo
        // this.requestData.brandModelName = this.requestData.carInfoVO.vehicleBrand; //厂牌型号 vehicleBrand
        // this.requestData.approvalQuality = this.requestData.carInfoVO.approvalQuality; // 核定载质量 approvalQuality
        // this.requestData.vehicleAge = this.requestData.carInfoVO.vehicleAge; //车龄 vehicleAge
        // vehicleModelCode = this.requestData.carInfoVO., //车型代码 vehicleModelCode
      }
      // 开始时间
      if (this.requestData.startDate && this.requestData.endDate) {
        this.selectDate =
          this.requestData.startDate + "-" + this.requestData.endDate;
      } else {
        this.requestData.startDate = this.formatDate(this.minDate);
        let time = new Date(this.minDate.getTime());
        time.setFullYear(time.getFullYear() + 1);
        time.setDate(time.getDate() - 1);
        this.requestData.endDate = this.formatDate(time);
        this.selectDate =
          this.requestData.startDate + "-" + this.requestData.endDate;
      }
      // 发票信息
      if (this.requestData.invoiceInfo) {
        // console.log(996, this.requestData.invoiceInfo);
        // 客户类型
        let { customerType } = this.requestData.invoiceInfo;
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
        if (this.requestData.invoiceInfo.taxPayerType) {
          let id2 = this.requestData.invoiceInfo.taxPayerType;
          let typeVal2 = this.$getCodeOrName("taxpayerTypeList", id2);
          this.billing.taxpayerInfo.taxPayerTypeVal = typeVal2 ? typeVal2 : "";
        }
        // 发票类型
        if (this.requestData.invoiceInfo.invoiceType) {
          let id3 = this.requestData.invoiceInfo.invoiceType;
          let typeVal3 = this.$getCodeOrName("invoiceTypeList", id3);
          this.billing.invoiceInfo.invoiceTypeVal = typeVal3 ? typeVal3 : "";
        }

        // this.customer_onConfirm("境内企业", 1);
        // this.taxpayerInfo_onConfirm("一般纳税人", 0);
        // this.invoiceInfo_onConfirm("增值税专用发票", 0);
      }
    },
    oninput() {
      // 通过正则过滤小数点后两位
      this.requestData.approvalQuality =
        this.requestData.approvalQuality.match(/^\d*(\.?\d{0,2})/g)[0] || null;
      // console.log("重量", this.requestData.approvalQuality);
      if (parseInt(this.requestData.approvalQuality * 100) <= 0) {
        this.requestData.approvalQuality = "0.01";
      }
      if (Math.ceil(this.requestData.approvalQuality) >= 1000) {
        this.requestData.approvalQuality = "1000";
      }
      // this.doCheckData();
    },
    // 初始化投被保人信息
    initPersonInfo() {
      let customerInfo = this.$route.query.item;
      // console.log(1043, customerInfo);
      if (customerInfo && typeof customerInfo == "object") {
        let {
          custName,
          certificateType,
          certificateNo,
          isInsured,
          custNo,
          email,
          custType,
          custSourceType
        } = customerInfo;
        let tel;
        if (customerInfo.mobileList && customerInfo.mobileList.length > 0) {
          tel = customerInfo.mobileList[0].phoneNumber;
        }
        let orName = "";
        if (custType == 1) {
          orName = "cardType_person";
        } else {
          orName = "cardType_business";
        }
        // console.log(1066, this.$getCodeOrName(orName, certificateType));
        // 被保人
        if (isInsured) {
          this.checked = false;
          let id = this.$getCodeOrName(orName, certificateType);
          let o = {};
          o = {
            insuredName: custName,
            insuredMobile: tel,
            insuredIdentifyType: "",
            insuredIdentifyNumber: "",
            custNo,
            custType,
            custSourceType
          };
          if (id) {
            o.insuredIdentifyType = id;
            o.insuredIdentifyNumber = certificateNo;
          } else {
            o.insuredIdentifyType = "810";
            o.insuredIdentifyNumber = "";
          }
          this.$store.dispatch("nonCar/updateShareData", {
            info: o,
            type: 2
          });
        }
        if (!isInsured) {
          let id = this.$getCodeOrName(orName, certificateType);
          let o = {};
          o = {
            appliName: custName,
            appliMobile: tel,
            appliEMail: email || "",
            appliIdentifyType: "",
            appliIdentifyNumber: "",
            custNo,
            custType,
            custSourceType
          };
          if (id) {
            o.appliIdentifyType = id;
            o.appliIdentifyNumber = certificateNo;
          } else {
            o.appliIdentifyType = "810";
            o.appliIdentifyNumber = "";
          }
          this.$store.dispatch("nonCar/updateShareData", {
            info: o,
            type: 2
          });
        }
      }
      let shareData = this.$store.state.nonCar.shareData.insuranceInfo;
      this.requestData.appliName = shareData.appliName;
      this.requestData.appliMobile = shareData.appliMobile;
      this.requestData.appliEMail = shareData.appliEMail;
      this.requestData.appliIdentifyType = shareData.appliIdentifyType;
      this.requestData.appliIdentifyNumber = shareData.appliIdentifyNumber;
      this.requestData.insuredName = shareData.insuredName;
      this.requestData.insuredMobile = shareData.insuredMobile;
      this.requestData.insuredIdentifyType = shareData.insuredIdentifyType;
      this.requestData.insuredIdentifyNumber = shareData.insuredIdentifyNumber;
      this.requestData.custNo = shareData.custNo;
      this.requestData.custType = shareData.custType;
      this.requestData.custSourceType = shareData.custSourceType;
    },
    // 切换同意声明条款
    changeAgree() {
      // if (this.agree) {
      //   this.agree = false;
      // } else {
      //   this.agree = true;
      // }
    },
    // 返回上一页
    backToPrePage() {
      if (
        this.fromRoute &&
        (this.fromRoute.name.indexOf("OrderDetail") >= 0 ||
          this.fromRoute.name.indexOf("orderList") >= 0)
      ) {
        this.$router.push({
          name: "proWorryFree",
          query: { isClear: 0 }
        });
      } else {
        this.$globalBack();
      }
    },
    // 投保人-选择投保人
    selectInsuredMethod(isInsured) {
      this.$store.dispatch("nonCar/updateShareData", {
        info: this.requestData,
        type: 2
      });
      let parms = {
        custType: "1",
        insureType: "1",
        isInsured: isInsured || false
      };
      this.$router.push({ name: "myCustomer", query: { item: parms } });
    },
    // 投保人-证件类型选择
    policyholder_onConfirm(value, index) {
      // 证件类型的值
      this.policyholder_certificateType.certificateTypeVal = value;
      let id = this.$getCodeOrName("hyIdCardList", value);
      this.requestData.appliIdentifyType = id ? id : index;
      this.showBilling = false;
      this.showBillingSwitch();
      // 拾取器的展示与不展示
      this.policyholder_certificateType.showPicker = false;
    },
    // 被保人-证件类型选择
    insured_onConfirm(value, index) {
      // 被保人证件类型的值
      this.insured_certificateType.certificateTypeVal = value;
      let id = this.$getCodeOrName("hyIdCardList", value);
      this.requestData.insuredIdentifyType = id ? id : index;
      this.showBilling = false;
      this.showBillingSwitch();
      // 选择器的显示与隐藏
      this.insured_certificateType.showPicker = false;
    },
    // 车辆类型选择
    insuredVehicle_onConfirm(value, index) {
      this.insuredVehicle.vehicleTypeVal = value;
      let id = this.$getCodeOrName("vehicleModelCodeList", value);
      if (index < 10) {
        index = "0" + index;
      } else {
        index = index.toString();
      }
      this.requestData.vehicleModelCode = id ? id : index;
      this.insuredVehicle.showPicker = false;
    },
    // 开票信息-客户类型选择
    customer_onConfirm(value, index, flag) {
      this.billing.taxpayerInfo.taxPayerTypeVal = "";
      this.billing.invoiceInfo.invoiceTypeVal = "";
      this.billing.taxpayerInfo.taxPayerType = [];
      this.billing.invoiceInfo.invoiceType = [];
      this.billing.cutstomInfo.customerTypeVal = value;
      this.requestData.invoiceInfo.customerType = index + 1;
      if (flag) {
        if (this.requestData.invoiceInfo.customerType == 1) {
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
          this.requestData.invoiceInfo.taxPayerType = "4";
          // this.invoiceIndex = 1;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          // this.requestData.invoiceInfo.invoiceType = "3";
          let id2 = this.requestData.invoiceInfo.invoiceType;
          let typeVal2 = this.$getCodeOrName("invoiceTypeList", id2);
          this.billing.invoiceInfo.invoiceTypeVal = typeVal2
            ? typeVal2
            : "增值税电子普通发票";
          this.requestData.invoiceInfo.invoiceType = id2 ? id2 : "3";
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
        if (this.requestData.invoiceInfo.customerType == 2) {
          this.billing.taxpayerInfo.taxPayerType = [
            "一般纳税人 ",
            "小规模纳税人",
            "非增值税纳税人"
          ];
          // this.taxPayerTypeIndex = 0;
          // this.billing.taxpayerInfo.taxPayerTypeVal = "一般纳税人";
          // this.requestData.invoiceInfo.taxPayerType = "1";
          let id3 = this.requestData.invoiceInfo.taxPayerType;
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
          let id4 = this.requestData.invoiceInfo.invoiceType;
          let typeVal4 = this.$getCodeOrName("invoiceTypeList", id4);
          this.requestData.invoiceInfo.invoiceType = id4 ? id4 : "1";
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
        if (this.requestData.invoiceInfo.customerType == 3) {
          this.billing.taxpayerInfo.taxPayerType = ["非增值税纳税人"];
          this.billing.taxpayerInfo.taxPayerTypeVal = "非增值税纳税人";
          this.taxPayerTypeIndex = 0;
          this.requestData.invoiceInfo.taxPayerType = "3";
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
          let id5 = this.requestData.invoiceInfo.invoiceType;
          let typeVal5 = this.$getCodeOrName("invoiceTypeList", id5);
          this.requestData.invoiceInfo.invoiceType = id5 ? id5 : "2";
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
        if (this.requestData.invoiceInfo.customerType == 1) {
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
          this.requestData.invoiceInfo.taxPayerType = "4";
          this.invoiceIndex = 1;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          this.requestData.invoiceInfo.invoiceType = "3";

          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.requestData.invoiceInfo.customerType == 2) {
          this.billing.taxpayerInfo.taxPayerType = [
            "一般纳税人 ",
            "小规模纳税人",
            "非增值税纳税人"
          ];
          this.taxPayerTypeIndex = 0;
          this.billing.taxpayerInfo.taxPayerTypeVal = "一般纳税人";
          this.requestData.invoiceInfo.taxPayerType = "1";

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
          this.requestData.invoiceInfo.invoiceType = "1";
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.requestData.invoiceInfo.customerType == 3) {
          this.billing.taxpayerInfo.taxPayerType = ["非增值税纳税人"];
          this.billing.taxpayerInfo.taxPayerTypeVal = "非增值税纳税人";
          this.taxPayerTypeIndex = 0;
          this.requestData.invoiceInfo.taxPayerType = "3";
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
          this.requestData.invoiceInfo.invoiceType = "2";
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
      this.requestData.invoiceInfo.taxPayerType = id ? id : index + 1;
      this.billing.taxpayerInfo.taxPayerShowPicker = false;
      if (flag) {
        if (
          this.requestData.invoiceInfo.taxPayerType == 2 ||
          this.requestData.invoiceInfo.taxPayerType == 3
        ) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          // this.invoiceIndex = 1;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          // this.requestData.invoiceInfo.invoiceType = "3";

          let id6 = this.requestData.invoiceInfo.invoiceType;
          let typeVal6 = this.$getCodeOrName("invoiceTypeList", id6);
          this.requestData.invoiceInfo.invoiceType = id6 ? id6 : "3";
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
        if (this.requestData.invoiceInfo.taxPayerType == 1) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税专用发票 ",
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          // this.invoiceIndex = 0;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税专用发票";
          // this.requestData.invoiceInfo.invoiceType = "1";

          let id7 = this.requestData.invoiceInfo.invoiceType;
          let typeVal7 = this.$getCodeOrName("invoiceTypeList", id7);
          this.requestData.invoiceInfo.invoiceType = id7 ? id7 : "1";
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
        if (this.requestData.invoiceInfo.taxPayerType == 4) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          // this.invoiceIndex = 0;
          // this.billing.invoiceInfo.invoiceTypeVal = "增值税普通发票";
          // this.requestData.invoiceInfo.invoiceType = "2";

          let id8 = this.requestData.invoiceInfo.invoiceType;
          let typeVal8 = this.$getCodeOrName("invoiceTypeList", id8);
          this.requestData.invoiceInfo.invoiceType = id8 ? id8 : "2";
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
          this.requestData.invoiceInfo.taxPayerType == 2 ||
          this.requestData.invoiceInfo.taxPayerType == 3
        ) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceIndex = 1;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税电子普通发票";
          this.requestData.invoiceInfo.invoiceType = "3";
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.requestData.invoiceInfo.taxPayerType == 1) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税专用发票 ",
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceIndex = 0;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税专用发票";
          this.requestData.invoiceInfo.invoiceType = "1";
          this.invoiceInfo_onConfirm(
            this.billing.invoiceInfo.invoiceTypeVal,
            this.invoiceIndex
          );
        }
        if (this.requestData.invoiceInfo.taxPayerType == 4) {
          this.billing.invoiceInfo.invoiceType = [
            "增值税普通发票",
            "增值税电子普通发票",
            "其他发票"
          ];
          this.invoiceIndex = 0;
          this.billing.invoiceInfo.invoiceTypeVal = "增值税普通发票";
          this.requestData.invoiceInfo.invoiceType = "2";

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
      this.requestData.invoiceInfo.invoiceType = id ? id : index + 1;
      this.billing.invoiceInfo.invoicePicker = false;
      let inv = this.requestData.invoiceInfo.invoiceType;
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
        this.requestData.copyDataFromType = "1";
        // this.requestData.invoiceInfo.mobile = this.requestData.appliMobile;
        this.requestData.invoiceInfo.companyName = this.requestData.appliName;
        this.requestData.invoiceInfo.mobile = this.requestData.appliMobile;
        this.requestData.invoiceInfo.email = this.requestData.appliEMail;
      } else {
        this.requestData.copyDataFromType = "2";
        // this.requestData.invoiceInfo.mobile = this.requestData.insuredMobile;
        this.requestData.invoiceInfo.companyName = this.requestData.insuredName;
        this.requestData.invoiceInfo.mobile = this.requestData.insuredMobile;
        this.requestData.invoiceInfo.email = "";
      }

      // this.isExpend = false;
    },
    // 自动转换成大写
    convertUpperCase(type) {
      // this.doCheckData();
      if (type == "engineNo") {
        this.requestData.engineNo = this.requestData.engineNo
          .trim()
          .toLocaleUpperCase();
      }
      if (type == "licenseNo") {
        this.requestData.licenseNo = this.requestData.licenseNo
          .trim()
          .toLocaleUpperCase();
      }
      if (type == "brandModelName") {
        this.requestData.brandModelName = this.requestData.brandModelName
          .trim()
          .toLocaleUpperCase();
      }
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
              _this.requestData.appliIdentifyNumber = cardNo;
              _this.requestData.appliName = name;
              _this.requestData.appliIdentity = "111";
            }
            //埋点
            trackMap("c_no_car_isure_info_ocr", "非车险投被保人OCR识别", {
              c_salesperson_type: "N",
              c_market_type: "Y",
              c_market_name: "",
              c_market_id: "",
              c_event_category: "非车险出单"
            });
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
              _this.requestData.appliIdentifyNumber = cardNo;
              _this.requestData.appliName = name;
              _this.requestData.appliIdentity = "111";
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
            this.requestData.engineNo = dataParam.responseBody.enginePN;
            this.requestData.vehicleAge = this.$getAge(
              dataParam.responseBody.registerDate,
              new Date()
            )
              ? this.$getAge(dataParam.responseBody.registerDate, new Date())
              : "";
          });
          break;
        }
      }
    },
    // 跳转到相应的条款内容页面
    redirectTerms(type) {
      this.$store.dispatch("nonCar/updateShareData", {
        info: this.requestData,
        type: 2
      });
      switch (type) {
        case 1: //投保须知
          this.$router.push({ name: "propertyInstructions" });
          break;

        case 2: //理赔指南
          this.$router.push({ name: "claim" });
          break;

        case 3: //保险条款
          this.$router.push({ name: "propertyCList" });
          break;
        case 4: //特别约定
          this.$router.push({ name: "propropertyArgument" });
          break;
        case 5: //责任免除
          this.$router.push({ name: "Deductible" });
          break;
      }
    },
    //被保人-切换是否同被保人
    insuredSwitch() {
      // console.log(1420, this.requestData);
      // console.log(1413, this.requestData.insuredIdentifyType);
      // appliIdentity: "01", // 投保人与被保人的关系， 01：本人 99：其他
      if (this.checked) {
        this.requestData.insuredName = this.requestData.appliName;
        this.requestData.insuredIdentifyNumber = this.requestData.appliIdentifyNumber;
        this.requestData.insuredMobile = this.requestData.appliMobile;
        this.requestData.insuredIdentifyType = this.requestData.appliIdentifyType;
        this.requestData.appliIdentity = "01";
      } else {
        // console.log(1422, this.requestData.insuredIdentifyType);
        // if(this.)
        this.requestData.appliIdentity = "99";
        this.requestData.insuredName = "";
        this.requestData.insuredIdentifyNumber = "";
        this.requestData.insuredMobile = "";
        this.requestData.insuredIdentifyType = "810";
      }
      // console.log(1443, this.requestData);
      // console.log(1444, this.requestData.insuredIdentifyType);
      // console.log(1445, this.requestData.insuredIdentifyType);
      // let typeVal = this.$getCodeOrName(
      //   "hyIdCardList",
      //   this.requestData.insuredIdentifyType
      // );
      // this.insured_certificateType.certificateTypeVal = typeVal ? typeVal : "";
    },
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
          let obj = this.identifyType.find(item => {
            return item.id == _this.requestData.appliIdentifyType;
          });
          // 个人
          if (obj) {
            if (obj.type == "01" || obj.type == "28") {
              _this.isGroup = false;
              if (_this.isNeedEleInvoice) {
                _this.isShowCompanyInfo = true;
                _this.requestData.invoiceInfo.mobile =
                  _this.requestData.appliMobile;
                _this.requestData.invoiceInfo.email =
                  _this.requestData.appliEMail;
              }
            } else {
              _this.isGroup = true;
            }
          }
          this.requestData.invoiceInfo.mobile = this.requestData.appliMobile;
          this.requestData.copyDataFromType = "1";
          this.requestData.invoiceInfo.companyName = this.requestData.appliName;
        } else {
          //  同被保人
          let obj = this.identifyType.find(item => {
            return item.id == _this.requestData.insuredIdentifyType;
          });

          // 个人
          if (obj) {
            if (obj.type == "01" || obj.type == "28") {
              _this.isGroup = false;
              if (_this.isNeedEleInvoice) {
                _this.requestData.invoiceInfo.mobile =
                  _this.requestData.insuredMobile;
                _this.requestData.invoiceInfo.email = "";
              }
            } else {
              _this.isGroup = true;
            }
          }
          this.requestData.invoiceInfo.mobile = this.requestData.insuredMobile;
          this.requestData.copyDataFromType = "2";
          this.requestData.invoiceInfo.companyName = this.requestData.insuredName;
        }
      }
    },
    // 是否需要电子保单 切换 操作
    changeNeedEleInvoice(flag) {
      this.isNeedEleInvoice = flag;
    },
    // 格式化日期
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
    selectDateConfirm(date) {
      let startDate = this.formatDate(date);
      let endDate = new Date(date.getTime());
      endDate.setFullYear(endDate.getFullYear() + 1);
      endDate.setDate(endDate.getDate() - 1);
      this.selectDate = `${startDate} - ${this.formatDate(endDate)}`;
      this.requestData.startDate = startDate;
      this.requestData.endDate = this.formatDate(endDate);
      this.showDate = false;
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
    // 更改是否同被保人
    changeSameInsuredType(type) {
      this.sameInsuredType = type;

      switch (this.sameInsuredType) {
        case "y":
          this.requestData.copyDataFromType = "1";
          this.requestData.invoiceInfo.companyName = this.requestData.appliName;
          this.requestData.invoiceInfo.mobile = this.requestData.appliMobile;
          this.requestData.invoiceInfo.email = this.requestData.appliEMail;
          break;

        default:
          this.requestData.copyDataFromType = "2";
          this.requestData.invoiceInfo.companyName = this.requestData.insuredName;
          this.requestData.invoiceInfo.mobile = this.requestData.insuredMobile;
          this.requestData.invoiceInfo.email = "";
          break;
      }
    },
    // 验证code
    checkCode(val) {
      var p = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
      var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
      var parity = [1, 0, "X", 9, 8, 7, 6, 5, 4, 3, 2];
      var code = val.substring(17);
      if (p.test(val)) {
        var sum = 0;
        for (var i = 0; i < 17; i++) {
          sum += val[i] * factor[i];
        }
        if (parity[sum % 11] == code.toUpperCase()) {
          return true;
        }
      }
      return false;
    },
    // 验证日期
    checkDate(val) {
      var pattern = /^(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)$/;
      if (pattern.test(val)) {
        var year = val.substring(0, 4);
        var month = val.substring(4, 6);
        var date = val.substring(6, 8);
        var date2 = new Date(year + "-" + month + "-" + date);
        if (date2 && date2.getMonth() == parseInt(month) - 1) {
          return true;
        }
      }
      return false;
    },
    // 验证省份
    checkProv(val) {
      var pattern = /^[1-9][0-9]/;
      var provs = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江 ",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北 ",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏 ",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门"
      };
      if (pattern.test(val)) {
        if (provs[val]) {
          return true;
        }
      }
      return false;
    },
    // 验证证件号
    checkID(val) {
      if (this.checkCode(val)) {
        var date = val.substring(6, 14);
        if (this.checkDate(date)) {
          if (this.checkProv(val.substring(0, 2))) {
            return true;
          }
        }
      }
      return false;
    },
    //组织机构代码校验
    checkOrgCode(organizationCode, type) {
      if (type != "801") {
        return true;
      }
      var reg = /^$|^[0-9|A-Z]{8}[0-9|X]$/;
      var str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      var params = "3,7,9,10,5,8,4,2".split(",");
      var sum = 0;
      //调整组织机构代码可以输入10位
      if (organizationCode.length == 10) {
        if (organizationCode.substr(8, 1) != "-") {
          // Toast("您输入的组织机构代码长度不正确或者不符合规定。\n组织机构代码长度为10位，末位为数字或X,倒数第二位必须为'-'。");
          Toast("您输入的组织机构代码不正确");
          return false;
        }
        organizationCode = organizationCode.replace("-", "");
      }
      if (reg.test(organizationCode) == false) {
        //Toast("您输入的组织机构代码长度不正确或者不符合规定。\n组织机构代码长度为9位，末位为数字或X。");
        Toast("您输入的组织机构代码不正确");
        return false;
      } else {
        for (var i = 0; i < organizationCode.length - 1; i++) {
          //取字符串前8位的每位数字
          var temp = organizationCode.charAt(i);
          //当数字为"0"到"9"时
          if (str.indexOf(temp) == -1) {
            //当数字为0到9时,计算每位数字与参数的积并累加求和
            sum = sum + parseInt(temp) * params[i];
          } else {
            //当数字为"A"到"Z"时,计算每位数字与参数的积并累加求和
            sum = sum + (str.indexOf(temp) + 10) * params[i];
          }
        }
        var C9 = 11 - (sum % 11);
        if (organizationCode.length != 0) {
          if (
            (C9 == 10 && organizationCode.charAt(8) == "X") ||
            (C9 == 11 && organizationCode.charAt(8) == "0") ||
            C9 == organizationCode.charAt(8)
          ) {
            return true;
          } else {
            Toast("您输入的组织机构代码不正确。");
            return false;
          }
        }
      }
      return true;
    },
    //校验三证合一代码

    checkThreeInOneCode(idNumber) {
      // 调整三证合一代码只可以输入18位
      if (idNumber.length != 18) {
        return false;
      }
      var idmArray = new Array();
      var Wi = new Array(
        1,
        3,
        9,
        27,
        19,
        26,
        16,
        17,
        20,
        29,
        25,
        13,
        8,
        24,
        10,
        30,
        28
      );
      var chgnum = new Array(
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20,
        21,
        22,
        23,
        24,
        25,
        26,
        27,
        28,
        29,
        30
      );
      var chgchar = new Array(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "J",
        "K",
        "L",
        "M",
        "N",
        "P",
        "Q",
        "R",
        "T",
        "U",
        "W",
        "X",
        "Y"
      );
      var mathMax = 0;
      var reg = /^[^_IOZSVa-z\W]{2}\d{6}[^_IOZSVa-z\W]{10}$/;
      idmArray = idNumber.split("");
      if (!reg.test(idNumber)) {
        return false;
      }
      for (var i = 0; i < idmArray.length; i++) {
        for (var j = 0; j < chgchar.length; j++) {
          if (idmArray[i] == chgchar[j]) {
            idmArray[i] = chgnum[j];
          }
        }
        if (i < 17) {
          mathMax += Wi[i] * idmArray[i];
        }
      }
      if (idmArray[idmArray.length - 1] == 0) {
        idmArray[idmArray.length - 1] = 31;
      }
      if (idmArray[idmArray.length - 1] != 31 - (mathMax % 31)) {
        return false;
      } else {
        return true;
      }
    },
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
    // 验证数据
    doCheckData() {
      let checkInfo = [
        {
          type: "1",
          key: "appliName",
          appliName: "投保人姓名",
          isRequire: true,
          rules: ["投保人姓名不能为空", "请输入50个字符以内的投保人姓名"]
        },
        {
          type: "1",
          key: "appliIdentifyType",
          appliIdentifyType: "投保人证件类型",
          isRequire: true,
          rules: ["投保人证件类型不能为空"]
        },
        {
          type: "1",
          key: "appliIdentifyNumber",
          isRequire: true,
          appliIdentifyNumber: "投保人证件号码",
          rules: ["投保人证件号码不能为空", "请输入正确的投保人证件号码"]
        },
        {
          type: "1",
          key: "appliMobile",
          appliMobile: "投保人手机号码",
          reg: /^1[3|4|5|6|8|7|9][0-9]\d{8}$/,
          isRequire: true,
          rules: ["投保人手机号码不能为空", "请输入正确的投保人手机号码"]
        },
        {
          type: "1",
          key: "appliEMail",
          reg: /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/,
          appliEMail: "投保人邮箱",
          isRequire: false,
          rules: ["投保人邮箱不能为空", "请输入正确的投保人邮箱"]
        },
        {
          type: "3",
          key: "insuredName",
          insuredName: "被保人姓名",
          isRequire: true,
          rules: ["被保人姓名不能为空", "请输入50个字符以内的被保人姓名"]
        },
        {
          type: "3",
          key: "insuredIdentifyType",
          insuredIdentifyType: "被保人证件类型",
          isRequire: true,
          rules: ["被保人证件类型不能为空", "请输入正确的被保人证件类型"]
        },
        {
          type: "3",
          key: "insuredIdentifyNumber",
          insuredIdentifyNumber: "被保人证件号码",
          isRequire: true,
          rules: ["被保人证件号码不能为空", "请输入正确的被保人证件号码"]
        },
        {
          type: "3",
          key: "insuredMobile",
          insuredMobile: "被保人手机号码",
          reg: /^1[3|4|5|6|8|7|9][0-9]\d{8}$/,
          isRequire: true,
          rules: ["请输入正确的被保人手机号码"]
        },
        {
          type: "1",
          key: "vehicleModelCode",
          vehicleModelCode: "车型",
          isRequire: true,
          rules: ["车型不能为空", "请输入正确的车型"]
        },
        {
          type: "1",
          key: "licenseNo",
          licenseNo: "车牌号",
          isRequire: true,
          rules: ["车牌号不能为空", "请输入正确的车牌号"]
        },
        {
          type: "1",
          key: "engineNo",
          engineNo: "发动机号",
          isRequire: true,
          rules: ["发动机号不能为空", "请输入正确的发动机号"]
        },
        {
          type: "1",
          key: "approvalQuality",
          approvalQuality: "核定载质量",
          isRequire: true,
          rules: ["核定载质量不能为空", "请输入正确的核定载质量"]
        },
        {
          type: "1",
          key: "vehicleAge",
          vehicleAge: "车龄",
          isRequire: true,
          rules: ["车龄不能为空", "请输入正确的车龄"]
        },
        {
          type: "1",
          key: "brandModelName",
          brandModelName: "厂牌型号",
          isRequire: true,
          rules: ["厂牌型号不能为空", "请输入正确的厂牌型号"]
        },

        {
          type: "1",
          key: "startDate",
          startDate: "保险起止日期",
          isRequire: true,
          rules: ["保险起止日期不能为空"]
        },
        {
          type: "1",
          key: "endDate",
          endDate: "保险起止日期",
          isRequire: true,
          rules: ["保险起止日期不能为空"]
        },

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
        if (checkInfo[i].type == "1") {
          if (!this.requestData[key]) {
            if (key != "appliEMail") {
              Toast({
                forbidClick: true,
                message: checkInfo[i].rules[0],
                type: "fail"
              });
              flag = false;
              break;
            }
          } else {
            let value = this.requestData[key].toString().trim();
            if (checkInfo[i].reg && !checkInfo[i].reg.test(value)) {
              Toast({
                forbidClick: true,
                message: checkInfo[i].rules[1],
                type: "fail"
              });
              flag = false;
              break;
            }
            if (key == "appliName" && value.length > 50) {
              Toast({
                forbidClick: true,
                message: checkInfo[i].rules[1],
                type: "fail"
              });
              flag = false;
              break;
            }
            if (key == "appliIdentifyNumber") {
              if (
                this.requestData.appliIdentifyType == "111" ||
                this.requestData.appliIdentifyType == "28"
              ) {
                let result = this.checkID(value);
                if (!result) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              }
              // 组织结构代码
              else if (this.requestData.appliIdentifyType == "801") {
                let result = this.checkOrgCode(value);
                if (!result) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              } // 三证合一
              else if (this.requestData.appliIdentifyType == "810") {
                let result = this.checkThreeInOneCode(value);
                if (!result) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              }
              // 如果投保人不是同被保人
              if (!this.checked) {
                if (value == this.requestData.insuredIdentifyNumber) {
                  Toast({
                    forbidClick: true,
                    message: "投被保人证件号码不能相同",
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
              }
            }
            if (key == "appliEMail" && !checkInfo[i].reg.test(value)) {
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
        if (checkInfo[i].type == "2" && this.showBilling) {
          // 如果是个人
          if (!this.isGroup) {
            if (key == "mobile") {
              if (!this.requestData.invoiceInfo[key]) {
                Toast({
                  forbidClick: true,
                  message: checkInfo[i].rules[0],
                  type: "fail"
                });
                flag = false;
                break;
              } else {
                let value = this.requestData.invoiceInfo[key].toString().trim();
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
            if (key == "email" && this.requestData.invoiceInfo[key]) {
              let value = this.requestData.invoiceInfo[key].toString().trim();
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
              if (!this.requestData.invoiceInfo[key]) {
                Toast({
                  forbidClick: true,
                  message: checkInfo[i].rules[0],
                  type: "fail"
                });
                flag = false;
                break;
              }
            }
            let value = this.requestData.invoiceInfo[key];
            let inType = this.requestData.invoiceInfo.invoiceType;
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
              this.requestData.invoiceInfo.customerType != "3"
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
              this.requestData.invoiceInfo.customerType == "3"
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
            if (
              inType == "3" &&
              this.requestData.invoiceInfo.customerType != "3"
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
            if (
              inType == "3" &&
              this.requestData.invoiceInfo.customerType == "3"
            ) {
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
        // 被保人信息
        if (checkInfo[i].type == "3" && !this.checked) {
          let value = this.requestData[key]
            ? this.requestData[key].toString().trim()
            : this.requestData[key];
          if (!value) {
            if (key != "insuredMobile") {
              Toast({
                forbidClick: true,
                message: checkInfo[i][key] + "不能为空",
                type: "fail"
              });
              flag = false;
              break;
            }
          } else {
            if (key == "insuredName" && value.length > 50) {
              Toast({
                forbidClick: true,
                message: checkInfo[i].rules[1],
                type: "fail"
              });
              flag = false;
              break;
            }
            if (key == "insuredIdentifyNumber") {
              if (
                this.requestData.insuredIdentifyType == "111" ||
                this.requestData.insuredIdentifyType == "28"
              ) {
                let result = this.checkID(value);
                if (!result) {
                  Toast({
                    forbidClick: true,
                    message: checkInfo[i].rules[1],
                    type: "fail"
                  });
                  flag = false;
                  break;
                }
                // 组织结构代码
                else if (this.requestData.insuredIdentifyType == "801") {
                  let result = this.checkOrgCode(value);
                  if (!result) {
                    Toast({
                      forbidClick: true,
                      message: checkInfo[i].rules[1],
                      type: "fail"
                    });
                    flag = false;
                    break;
                  }
                } // 三证合一
                else if (this.requestData.insuredIdentifyType == "810") {
                  let result = this.checkThreeInOneCode(value);
                  if (!result) {
                    Toast({
                      forbidClick: true,
                      message: checkInfo[i].rules[1],
                      type: "fail"
                    });
                    flag = false;
                    break;
                  }
                }
                // if (value == this.requestData.appliIdentifyNumber) {
                //   Toast({
                //     forbidClick: true,
                //     message: "投被保人证件号码不能相同",
                //     type: "fail"
                //   });
                //   flag = false;
                //   break;
                // }
              }
            }
            if (key == "insuredMobile") {
              if (checkInfo[i].reg && !checkInfo[i].reg.test(value)) {
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
      return flag;
    },
    // 立即投保
    async applyNow() {
      this.isDisabled = true;
      if (this.checked) {
        this.requestData.insuredName = this.requestData.appliName;
        this.requestData.insuredIdentifyNumber = this.requestData.appliIdentifyNumber;
        this.requestData.insuredMobile = this.requestData.appliMobile;
        this.requestData.insuredIdentifyType = this.requestData.appliIdentifyType;
        this.requestData.appliIdentity = "01";
      }
      // this.insuredSwitch();
      if (!this.doCheckData()) {
        this.isDisabled = false;
        return false;
      }
      if (!this.agree) {
        Toast({
          forbidClick: true,
          message: "请阅读并同意保险条款及投保须知",
          position: "bottom"
        });
        this.isDisabled = false;
        return false;
      }
      let saveObj = {};
      if (!this.showBilling) {
        for (let key in this.requestData) {
          if (key != "invoiceInfo") {
            saveObj[key] = this.requestData[key];
          }
        }
      } else if (this.showBilling) {
        saveObj.invoiceInfo = {};
        switch (this.sameInsuredType) {
          case "y":
            this.requestData.invoiceInfo.copyDataFromType = "1";
            break;

          default:
            this.requestData.invoiceInfo.copyDataFromType = "2";
            break;
        }
        if (!this.isGroup && this.isNeedEleInvoice) {
          for (let key in this.requestData) {
            if (key == "invoiceInfo") {
              saveObj.invoiceInfo.mobile = this.requestData.invoiceInfo.mobile;
              saveObj.invoiceInfo.email = this.requestData.invoiceInfo.email;
              saveObj.invoiceInfo.invoiceType = "3";
              saveObj.invoiceInfo.taxPayerType = "4";
              this.requestData.companyName =
                this.requestData.copyDataFromType == "1"
                  ? this.requestData.appliName
                  : this.requestData.insuredName;
              saveObj.invoiceInfo.companyName = this.requestData.companyName;
            } else {
              saveObj[key] = this.requestData[key];
            }
          }
          this.requestData.invoiceInfo.taxPayerType = "4";
          this.requestData.invoiceInfo.invoiceType = "3";
          switch (this.sameInsuredType) {
            case "y":
              this.requestData.invoiceInfo.taxPayerNo = this.requestData.appliIdentifyNumber;
              saveObj.invoiceInfo.taxPayerNo = this.requestData.appliIdentifyNumber;
              break;

            default:
              this.requestData.invoiceInfo.taxPayerNo = this.requestData.insuredIdentifyNumber;
              saveObj.invoiceInfo.taxPayerNo = this.requestData.insuredIdentifyNumber;
              break;
          }
        }
        if (this.isGroup) {
          for (let key in this.requestData) {
            saveObj[key] = this.requestData[key];
          }
        }
      }
      this.$store.dispatch("nonCar/updateShareData", {
        info: saveObj,
        type: 2
      });
      let productInfo = this.$store.state.nonCar.shareData.productInfo;
      let insuranceInfo = this.$store.state.nonCar.shareData.insuranceInfo;
      let obj = {
        formulaCode: productInfo.formulaCode,
        insuranceCode: productInfo.riskCode,
        storeOracleRequestVO: {}
      };
      for (let key in productInfo) {
        obj.storeOracleRequestVO[key] = productInfo[key];
      }
      for (let key in insuranceInfo) {
        obj.storeOracleRequestVO[key] = insuranceInfo[key];
      }
      if (productInfo.isAgent == "0") {
        delete obj.storeOracleRequestVO.agentInfo;
      }
      if (insuranceInfo.orderNo) {
        obj.orderNo = insuranceInfo.orderNo;
      }

      delete obj.storeOracleRequestVO.orderNo;
      delete obj.storeOracleRequestVO.proposalNo;
      if (
        !this.showBilling &&
        obj.storeOracleRequestVO.hasOwnProperty("invoiceInfo")
      ) {
        delete obj.storeOracleRequestVO.invoiceInfo;
      }
      // this.$router.push({
      //   name: "propertyInsuranceConfirm",
      //   query: {
      //     isNeedEleInvoice: this.showBilling && this.isNeedEleInvoice
      //   }
      // });
      // return;
      // console.log(obj);
      insureStatusZc(obj).then(res => {
        if (res.code == "1") {
          saveObj.orderNo = res.data ? res.data.orderNo : "";
          this.$store.dispatch("nonCar/updateShareData", {
            info: saveObj,
            type: 2
          });
          this.$router.push({
            name: "propertyInsuranceConfirm",
            query: {
              isNeedEleInvoice: this.showBilling && this.isNeedEleInvoice
            }
          });
          //埋点
          trackMap("c_no_car_immediately_insure", "非车险投被保人立即投保", {
            c_salesperson_type: "N",
            c_market_type: "Y",
            c_market_name: "",
            c_market_id: "",
            c_event_category: "非车险出单"
          });
        } else {
          this.$toast.fail(res.msg || res.message);
        }
      });

      this.isDisabled = false;
    }
  }
};
</script>

<style lang="less" scoped>
#insuranceInformation {
  width: 100%;
  height: auto;
  // height: calc(100vh - 135px);
  // overflow: scroll;
  background: rgba(247, 247, 247, 1);
  font-family: PingFangSC-Semibold, PingFang SC;
  padding: 70px 0 66px 0;

  .idCard {
    // width: 95% !important;
    margin-right: 25px;
  }
  .top10 {
    margin-top: 10px;
  }
  .right10 {
    margin-right: 10px;
  }
  .right13 {
    margin-right: 13px;
  }
  .left13 {
    margin-left: 13px;
  }
  .rightCenter {
    display: flex;
    align-items: center;
    .subIcon {
      width: 20px;
      height: 20px;
      img {
        width: 100%;
      }
    }
  }
  .gary193 {
    color: rgba(193, 193, 193, 1);
  }
  .gary102 {
    color: rgba(102, 102, 102, 1);
  }
  .grayBf {
    color: #bfbfbf;
  }
  .verticalMid {
    vertical-align: middle;
  }
  div {
    box-sizing: border-box;
  }
  button {
    outline: none;
    background: none;
    border: none;
  }
  .van-cell:not(:last-child)::after {
    border-bottom: none;
  }
  .insuranceInfoHeader {
    background: rgba(255, 255, 255, 1);
    height: 70px;
    line-height: 70px;
    display: flex;
    align-items: center;
    h3 {
      font-size: 18px;
      font-weight: 600;
      color: rgba(51, 51, 51, 1);
      text-align: center;
    }
    img {
      height: 54px;
      width: auto;
      margin: 0 15px 0 8px;
    }
  }
  * {
    box-sizing: border-box;
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
        width: 345px;
        height: 34px;
        line-height: 34px;
        background: rgba(59, 138, 254, 0.1);
        border-radius: 2px;
        text-align: center;
        display: block;
        padding: 0 12px;
        font-size: 13px;
        font-weight: 400;
        color: rgba(59, 138, 254, 1);
        box-sizing: border-box;
        margin: 0 auto;
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
  .insuranceInformationBottomMid {
    // height: 67px;
    border-top: 1px solid #e5e6e8;
    // border-bottom: 1px solid #e5e6e8;
    padding-top: 8px;
    p {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 0 15px;
      input {
        width: 16px;
        height: 16px;
        background: rgba(235, 237, 240, 1);
        border: 1px solid rgba(200, 201, 204, 1);
        outline: none;
        margin: 18px 22px 18px 16px;
      }
      span {
        display: block;
        width: 313px;
        height: 38px;
        font-size: 13px;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        line-height: 19px;
        strong {
          color: #3b8afe;
        }
      }
    }
  }
  .insuranceInformationBottom {
    position: fixed;
    -webkit-overflow-scroll: touch;
    bottom: 0;
    left: 0;
    z-index: 999;
    width: 100%;
    background: rgba(255, 255, 255, 1);

    .insuranceInformationBottomBottom {
      // margin-top: 11px;
      height: 46px;
      display: flex;
      align-items: center;
      justify-content: space-between;
      .premium {
        margin-left: 15px;
        font-size: 12px;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
      }
      button {
        width: 120px;
        height: 46px;
        background: rgba(59, 138, 254, 1);
        font-size: 16px;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
        line-height: 46px;
        margin-left: 22px;
      }
      .redCost {
        margin-left: 15px;
        font-size: 17px;
        font-weight: 400;
        color: rgba(255, 0, 100, 1);
      }
    }
  }
}
</style>
