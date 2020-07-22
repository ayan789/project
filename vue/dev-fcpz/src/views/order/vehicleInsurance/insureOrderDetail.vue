<template>
  <div class="container">
    <!-- <globalNav :title="globalNav.title" @clickBack="goBack" /> -->
    <!-- 头像信息 -->
    <div class="header">
      <div class="orderDetail">
        <div class="title">订单详情</div>
        <img
          class="img"
          @click="goBack"
          src="../../../assets/order/orderList/iconBack.png"
          alt
        />
      </div>
      <div class="info" @click="togglePop('show')">
        <!-- <div class="infoLeft">{{ detailed.vehicleModelVO ? detailed.vehicleModelVO.licenseNo : "" }}</div> -->
        <div class="mid">
          <div class="plateNumber">
            {{
              detailed.vehicleModelVO ? detailed.vehicleModelVO.licenseNo : ""
            }}
          </div>
          <p>
            车主信息：
            {{ detailed.vehicleModelVO ? detailed.vehicleOwnerVO.name : "" }}
          </p>
          <p>
            证件号码：
            {{
              detailed.vehicleModelVO ? detailed.vehicleOwnerVO.identityNo : ""
            }}
          </p>
        </div>
        <div class="right">
          <van-icon name="arrow" />
        </div>
      </div>
      <img
        class="img"
        src="../../../assets/order/orderList/vehicleCar.png"
        alt
      />
      <div class="nsurancRisks">
        <div class="amountMoney">
          <div class="widthStyle widthStyle1">
            <span>{{ totalPremium }}</span>
            <span style="font-size: 12px;">元</span>
          </div>
          <div class="widthStyle">合计(保费+车船税)</div>
        </div>
      </div>
    </div>
    <!-- 订单信息 -->
    <div class="infoTop">
      <van-cell-group>
        <div>
          <van-cell v-if="detailed.riskFlag == '0' || detailed.riskFlag == '2'">
            <div slot="title">交强险</div>
            <div>
              {{ crossStrengthInsure.premium + "元" }}
            </div>
          </van-cell>
        </div>
        <div>
          <van-cell v-if="detailed.riskFlag == '0' || detailed.riskFlag == '1'">
            <div slot="title">商业险</div>
            <div>{{ businessInsure.premium + "元" }}</div>
          </van-cell>
        </div>
        <div>
          <van-cell
            v-if="
              detailed.vehicleTaxVo != null &&
                detailed.vehicleTaxVo.sumPayment != null
            "
          >
            <div slot="title">车船税</div>
            <div>
              {{
                (detailed.vehicleOrder
                  ? detailed.vehicleTaxVo.sumPayment
                  : "") + "元"
              }}
            </div>
          </van-cell>
        </div>
        <div>
          <van-cell
            v-if="
              detailed.wealchAccApplicationVO != null &&
                detailed.wealchAccApplicationVO.premium != null
            "
          >
            <div slot="title">联合销售</div>
            <div>
              {{
                (detailed.vehicleOrder
                  ? detailed.wealchAccApplicationVO.premium
                  : "") + "元"
              }}
            </div>
          </van-cell>
        </div>
      </van-cell-group>
      <div style="width:100%;height:20px;background:#f7f7f7;"></div>
      <van-cell value>
        <template slot="title">
          <span class="custom-title orderTitle">订单信息</span>
        </template>
        <span class="statusFlag">
          <span round type="info" v-if="detailed.orderStatus == '10'"
            >暂存</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '20'"
            >待提交</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '21'"
            >核保中</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '22'"
            >核保失败</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '23'"
            >核保通过</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '24'"
            >核保退回</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '30'"
            >已生效</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '31'"
            >已失效</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '32'"
            >满期</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '33'"
            >退保</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '40'"
            >待支付</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '41'"
            >支付中</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '42'"
            >已支付</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '50'"
            >已过期</span
          >
          <span round type="info" v-else-if="detailed.orderStatus == '51'"
            >待续保</span
          >
        </span>
      </van-cell>
    </div>
    <!-- 选项 -->
    <van-cell-group>
      <!--   交强险 -->
      <div
        class="marginRight_30"
        v-if="detailed.riskFlag == '0' || detailed.riskFlag == '2'"
      >
        <div
          v-if="crossStrengthInsure.applicationNo"
          class="num"
          @click="copyPolicyNo"
          data-clipboard-action="copy"
          :data-clipboard-text="crossStrengthInsure.applicationNo"
        >
          复制
        </div>
        <div
          class="first line_h"
          v-if="detailed.riskFlag == '0' || detailed.riskFlag == '2'"
        >
          <van-cell
            title="交强险投保单号"
            :value="crossStrengthInsure.applicationNo"
          ></van-cell>
        </div>
      </div>
      <div class="marginRight_30">
        <div
          v-if="crossStrengthInsure.policyNo"
          class="num"
          @click="copyPolicyNo"
          data-clipboard-action="copy"
          :data-clipboard-text="crossStrengthInsure.policyNo"
        >
          复制
        </div>
        <div
          class="first line_h"
          v-if="detailed.riskFlag == '0' || detailed.riskFlag == '2'"
        >
          <van-cell
            title="交强险保单号"
            :value="crossStrengthInsure.policyNo"
          ></van-cell>
        </div>
      </div>
      <div
        class="second line_h"
        v-if="detailed.riskFlag == '0' || detailed.riskFlag == '2'"
      >
        <van-cell title="交强险保险期" :value="crossStrengthInsureLimit" />
      </div>
      <div
        class="line_h"
        v-if="detailed.riskFlag == '0' || detailed.riskFlag == '2'"
      >
        <van-cell
          title="交强险即时生效"
          :value="crossStrengthInsure.immediatelyEffective == '1' ? '是' : '否'"
        />
      </div>
      <div
        class="marginRight_30"
        v-if="detailed.riskFlag == '0' || detailed.riskFlag == '1'"
      >
        <!-- 商业险 -->
        <div
          v-if="businessInsure.applicationNo"
          class="num"
          @click="copyPolicyNo"
          data-clipboard-action="copy"
          :data-clipboard-text="businessInsure.applicationNo"
        >
          复制
        </div>
        <div
          class="first line_h"
          v-if="detailed.riskFlag == '0' || detailed.riskFlag == '1'"
        >
          <van-cell
            title="商业险投保单号"
            :value="businessInsure.applicationNo"
          ></van-cell>
        </div>
      </div>
      <div class="marginRight_30">
        <div
          v-if="businessInsure.policyNo"
          class="num"
          @click="copyPolicyNo"
          data-clipboard-action="copy"
          :data-clipboard-text="businessInsure.policyNo"
        >
          复制
        </div>
        <div
          class="first line_h"
          v-if="detailed.riskFlag == '0' || detailed.riskFlag == '1'"
        >
          <van-cell
            title="商业险保单号"
            :value="businessInsure.policyNo"
          ></van-cell>
        </div>
      </div>
      <div
        class="second line_h"
        v-if="detailed.riskFlag == '0' || detailed.riskFlag == '1'"
      >
        <van-cell title="商业险保险期" :value="insureLimit" />
      </div>
      <div
        class="second line_h"
        v-if="detailed.riskFlag == '0' || detailed.riskFlag == '1'"
      >
        <van-cell
          title="商业险即时生效"
          :value="businessInsure.immediatelyEffective == '1' ? '是' : '否'"
        />
      </div>
      <div class="marginRight_30">
        <div
          v-if="detailed.payTypeNo"
          class="num"
          @click="copyPolicyNo"
          data-clipboard-action="copy"
          :data-clipboard-text="detailed.payTypeNo"
        >
          复制
        </div>
        <div class="first line_h">
          <van-cell title="支付号" :value="detailed.payTypeNo" />
        </div>
      </div>
      <div class="line_h">
        <van-cell title="验证码" :value="detailed.payVerificationCode" />
      </div>
      <div class="line_h">
        <van-cell
          title="保单来源"
          :value="detailed.systemSource == '1' ? 'APP' : '其他'"
        />
      </div>
      <div class="line_h">
        <van-cell
          title="是否电子投保"
          :value="detailed.electronicInsurance == '1' ? '是' : '否'"
        />
      </div>
      <div v-if="detailed.electronicInsuranceFlag == '1'">
        <van-cell title="电子投保状态" value="已完成电子投保标识" />
      </div>
      <div v-else-if="detailed.electronicInsuranceFlag == '2'">
        <van-cell title="电子投保状态" value="未完成电子投保" />
      </div>
      <div v-else-if="detailed.electronicInsuranceFlag == '3'">
        <van-cell title="电子投保状态" value="不能进行电子投保选择纸质投保" />
      </div>
      <div v-else-if="detailed.electronicInsuranceFlag == '4'">
        <van-cell title="电子投保状态" value="无需电子投保" />
      </div>
      <div v-else-if="detailed.electronicInsuranceFlag == '5'">
        <van-cell title="电子投保状态" value="客户主动放弃电子投保" />
      </div>
    </van-cell-group>
    <!-- 信息选项  -->
    <div style="margin-top: .3rem; padding-bottom: 2rem;">
      <van-cell-group>
        <van-cell value is-link @click="togglePop('quShow')">
          <template slot="title">
            <img
              src="@/assets/vehicle/orderList/channel.png"
              alt
              class="cellImg"
            />
            <span class="custom-title">渠道信息</span>
          </template>
        </van-cell>
        <van-cell value is-link @click="togglePop('carShow')">
          <template slot="title">
            <img
              src="@/assets/vehicle/orderList/vehicle.png"
              alt
              class="cellImg"
            />
            <span class="custom-title">车辆信息</span>
          </template>
        </van-cell>
        <van-cell value is-link @click="togglePop('xianShow')">
          <template slot="title">
            <img
              src="@/assets/vehicle/orderList/insure.png"
              alt
              class="cellImg"
            />
            <span class="custom-title">险别信息</span>
          </template>
        </van-cell>
        <van-cell
          v-if="detailed.riskFlag == '0' || detailed.riskFlag == '2'"
          value
          is-link
          @click="togglePop('vehicleTtaxShow')"
        >
          <template slot="title">
            <img
              src="@/assets/vehicle/orderList/insure.png"
              alt
              class="cellImg"
            />
            <span class="custom-title">车船税</span>
          </template>
        </van-cell>
        <van-cell value is-link @click="togglePop('fullOfferShow')">
          <template slot="title">
            <img
              src="@/assets/vehicle/orderList/insure.png"
              alt
              class="cellImg"
            />
            <span class="custom-title">整单优惠</span>
          </template>
        </van-cell>
        <van-cell value is-link @click="togglePop('terraceShow')">
          <template slot="title">
            <img
              src="@/assets/vehicle/orderList/platform.png"
              alt
              class="cellImg"
            />
            <span class="custom-title">平台信息</span>
          </template>
        </van-cell>
        <van-cell
          v-if="
            detailed.wealchAccApplicationVO != null &&
              detailed.wealchAccApplicationVO.premium != null
          "
          value
          is-link
          @click="togglePop('uniteShow')"
        >
          <template slot="title">
            <img
              src="@/assets/vehicle/orderList/channel.png"
              alt
              class="cellImg"
            />
            <span class="custom-title">联合销售</span>
          </template>
        </van-cell>
        <van-cell value is-link @click="togglePop('teShow')">
          <template slot="title">
            <img
              src="@/assets/vehicle/orderList/channel.png"
              alt
              class="cellImg"
            />
            <span class="custom-title">特别约定</span>
          </template>
        </van-cell>
      </van-cell-group>
    </div>
    <!-- 固定按钮 -->
    <div class="fixedButton">
      <van-button type="default" @click="sharePage">
        <div class="btnImgShare">
          <img
            class="btnImg"
            src="../../../assets/order/orderDetail/share.png"
            alt
          />
          分享
        </div>
      </van-button>
      <van-button
        v-if="orderStatus != '10'"
        class="num"
        data-clipboard-action="copy"
        :data-clipboard-text="detailed.applicationNo"
        type="default"
        @click="insureNumber"
      >
        <img
          class="btnImg"
          src="../../../assets/order/orderDetail/copy.png"
          alt
        />
        复制投保单
      </van-button>
      <van-button
        type="default"
        @click="continueInsurance"
        v-if="orderStatus == '10'"
        >继续报价</van-button
      >
      <van-button
        type="default"
        @click="continueInsurance"
        v-if="orderStatus == '24'"
        >投保修改</van-button
      >
      <!-- <van-button
        type="default"
        v-if="orderStatus == '10'"
      
        @click="togglePop('enterShow')"
        >继续录入</van-button
      >-->
      <van-button
        type="default"
        v-if="orderStatus == '40' && payExpireFlag == '0'"
        @click="gotoPayPage"
        >去支付</van-button
      >
      <van-button
        type="default"
        v-if="orderStatus == '30' && oneClickRenewalFlag == '1'"
        @click="onebuttonRenewal"
        >一键续保</van-button
      >
      <van-button
        type="default"
        v-if="orderStatus == '20' || orderStatus == '24'"
        @click="submitAudit"
        >提交核保</van-button
      >
    </div>

    <!-- 核保查询弹出层 -->
    <van-popup
      v-model="queryShow"
      position="bottom"
      closeable
      close-icon="arrow-left"
      close-icon-position="top-left"
      :style="{ height: '100%' }"
    >
      <popNav popTitle="核保信息" />
      <div class="popBody">
        <div class="commonRow">
          <div>核保状态</div>
          <div>0</div>
        </div>
        <h3>核保前置核保结果</h3>
        <van-cell-group>
          <van-cell title="核保结果类型" value="内容" />
          <van-cell title="核保级别" value="内容" />
          <van-cell title="是否需要验车" value="内容" />
        </van-cell-group>
      </div>
    </van-popup>

    <!-- 继续录入弹出层 -->
    <van-popup
      v-model="enterShow"
      position="bottom"
      closeable
      close-icon="arrow-left"
      close-icon-position="top-left"
      :style="{ height: '100%' }"
    >
      <popNav popTitle="继续录入" />
      <div class="popBody">
        <div class="form">
          <van-form @submit="onSubmit">
            <van-field name="switch" label="新车未上牌" required>
              <template #input>
                <van-switch v-model="checked" size="20" />
              </template>
            </van-field>
            <van-field
              v-model="carNum"
              name="车牌号"
              label="车牌号"
              placeholder="请输入车牌号"
              required
            >
              <van-button slot="button" size="mini" type="default"
                >查找</van-button
              >
            </van-field>
            <van-field
              v-model="carNum2"
              name="车架号"
              right-icon="scan"
              label="车架号"
              placeholder="车架号"
              required
            />
            <van-field
              readonly
              clickable
              name="calendar"
              :value="riqivalue"
              label="初登日期"
              required
              placeholder="点击选择日期"
              @click="showCalendar = true"
            />
            <van-calendar
              :min-date="minDate"
              v-model="showCalendar"
              @confirm="onConfirm"
            />
            <van-field
              v-model="date"
              name="发动机号"
              label="发动机号"
              placeholder="发动机号"
              required
            />
            <van-field
              v-model="date"
              name="厂牌型号"
              label="厂牌型号"
              placeholder="厂牌型号"
              required
            >
              <van-button slot="button" size="mini" type="default"
                >车型查询</van-button
              >
            </van-field>

            <van-field
              v-model="date"
              name="新车购置价"
              label="新车购置价"
              placeholder="新车购置价"
              required
            />
            <van-field
              v-model="date"
              name="车辆实际价"
              label="车辆实际价"
              placeholder="车辆实际价"
              required
            />
            <van-field
              v-model="date"
              name="核定载客数"
              label="核定载客数"
              placeholder="核定载客数"
              required
            />

            <van-field name="switch" label="是否外地车" required>
              <template #input>
                <van-switch v-model="checked" size="20" />
              </template>
            </van-field>
            <van-field name="switch" label="过户车标志" required>
              <template #input>
                <van-switch v-model="checked" size="20" />
              </template>
            </van-field>
            <van-field
              v-model="date"
              name="转登日期"
              right-icon="delete"
              label="转登日期"
              placeholder="转登日期"
              required
            />

            <van-field
              readonly
              clickable
              name="calendar"
              :value="riqivalue"
              label="转登日期"
              placeholder="点击选择日期"
              @click="showCalendar = true"
            />
            <van-calendar
              :min-date="minDate"
              v-model="showCalendar"
              @confirm="onConfirm"
            />
            <div style="margin: 16px;">
              <van-button round block type="info" native-type="submit"
                >提交</van-button
              >
            </div>
          </van-form>
        </div>
      </div>
    </van-popup>
    <!-- 特别约定弹出框 -->
    <!-- <van-popup v-model="incrementshowPopup">
      <div class="containerPopup">
        <div
          class="container"
          v-for="(item, index) in specialAgreementListVal"
          :key="index"
        >
          <div
            v-show="specialAgreementListVal != []"
            class="agreementDetail"
            v-for="(itm, i) in item.agreementDetail"
            :key="i"
          >
            <div>{{ itm.serviceTypeName }}</div>
            <div>{{ itm.serviceContent }}</div>
          </div>
        </div>
      </div>
    </van-popup>
    <van-popup v-model="incrementshowPopup1">
      <div class="containerPopup">
        <div
          class="container"
          v-for="(item, index) in trafficSpecialAgreementVal"
          :key="index"
        >
          <div
            v-show="trafficSpecialAgreementVal != []"
            class="agreementDetail"
            v-for="(itm, i) in item.agreementDetail"
            :key="i"
          >
            <div>{{ itm.serviceTypeName }}</div>
            <div>{{ itm.serviceContent }}</div>
          </div>
        </div>
      </div>
    </van-popup> -->
  </div>
</template>

<script>
import globalNav from "@/components/global.vue";
import insureBar from "@/components/insureBar.vue";
import compulsoryInsurance from "./common/compulsoryInsurance.vue";
import popNav from "@/components/popNav.vue";
import {
  getDetails,
  getoddNumb,
  oneClickRenewal
} from "@src/http/module/order.js";
import Config from "@src/common/json/newCoreDataDictionary";
import { Toast, Dialog } from "vant";
import {
  getPayNo,
  submitAudit,
  getShareCode
} from "@src/http/module/vehicle.js";

export default {
  name: "insureOrderDetail",
  components: {
    [Dialog.name]: Dialog,
    globalNav,
    popNav,
    insureBar,
    compulsoryInsurance
  },

  data() {
    return {
      vehicleTypeFlag: "",
      // incrementshowPopup: false,
      // incrementshowPopup1: false,
      specialAgreementListVal: [],
      trafficSpecialAgreementVal: [],
      useNatureCodes: Object.keys(Config.useNatureCode).map(key => ({
        text: Config.useNatureCode[key],
        value: key
      })),
      businessInsure: {},
      crossStrengthInsure: {},
      isProxyVal: "",
      isProxyFlag: "",
      whetherOptions: ["商业险", "交强险"],
      print: "",
      fl: false,
      policyHolder: "",
      insuredPerson: "",
      // 证件类型枚举
      identifyTypes: "",
      invoiceTypes: "",
      nationalityTypes: "",
      occupationTypes: "",
      licenseTypes: "", //号牌类型
      fuelTypes: "", //能源类型
      carKindCodes: "", //车辆类型
      specialCarFlags: "", //特殊车标志
      globalNav: {
        title: "订单详情"
      },
      orderStatus: "",
      responsibility: [],
      insureObj: {},
      compulsoryObj: {},
      terracevalue: 0,
      show: false, //header弹出层
      xianShow: false, //险别弹出层
      vehicleTtaxShow: false, //车船税
      fullOfferShow: false, //整单优惠
      uniteShow: false, //联合销售
      terraceShow: false, //平台信息
      quShow: false, //渠道弹出层
      teShow: false, //特约弹出层
      carShow: false, //车辆弹出层
      queryShow: false, //核保查询弹出层
      enterShow: false, //继续录入弹出层
      checked: true, //继续录入switch
      riqivalue: "", //日期默认
      showCalendar: false, //日期弹出层
      carNum: "", //车牌号默认
      carNum2: "", //车牌号默认
      date: "", //字段
      minDate: new Date(2018, 0, 1), //最早日期
      detailed: {},
      insureLimit: "",
      crossStrengthInsureLimit: "",
      orderIdValue: "",
      payExpireFlag: "",
      oneClickRenewalFlag: ""
    };
  },
  created() {
    this.getOrderDetail();
  },
  mounted() {
    this.$nextTick(() => {
      this.identifyTypes = Object.keys(Config.identifyType).map(key => ({
        text: Config.identifyType[key],
        value: key
      }));
      this.taxFlagSHs = Object.keys(Config.taxFlagSH).map(key => ({
        text: Config.taxFlagSH[key],
        value: key
      }));
      this.nationalityTypes = Object.keys(Config.countryCodeTJ).map(key => ({
        text: Config.countryCodeTJ[key],
        value: key
      }));
      this.invoiceTypes = Object.keys(Config.invoiceType).map(key => ({
        text: Config.invoiceType[key],
        value: key
      }));
      this.occupationTypes = Object.keys(Config.profession).map(key => ({
        text: Config.profession[key],
        value: key
      }));
      this.licenseTypes = Object.keys(Config.licenseType).map(key => ({
        text: Config.licenseType[key],
        value: key
      }));
      this.fuelTypes = Object.keys(Config.fuelType).map(key => ({
        text: Config.fuelType[key],
        value: key
      }));
      this.carKindCodes = Object.keys(Config.carKindCode).map(key => ({
        text: Config.carKindCode[key],
        value: key
      }));
      this.specialCarFlags = Object.keys(Config.specialCarFlag).map(key => ({
        text: Config.specialCarFlag[key],
        value: key
      }));
    });
  },
  computed: {
    totalPremium() {
      let total = 0;
      if (this.detailed.riskFlag == "0" || this.detailed.riskFlag == "2") {
        total += this.crossStrengthInsure.premium;
      }
      if (this.detailed.riskFlag == "0" || this.detailed.riskFlag == "1") {
        total += this.businessInsure.premium;
      }
      if (
        this.detailed.vehicleTaxVo != null &&
        this.detailed.vehicleTaxVo.sumPayment != null
      ) {
        total += this.detailed.vehicleOrder
          ? this.detailed.vehicleTaxVo.sumPayment
          : 0;
      }
      if (
        this.detailed.wealchAccApplicationVO != null &&
        this.detailed.wealchAccApplicationVO.premium != null
      ) {
        total += this.detailed.vehicleOrder
          ? this.detailed.wealchAccApplicationVO.premium
          : 0;
      }
      return String(total.toFixed(2));
    }
  },
  updated() {
    document.documentElement.scrollTop = localStorage.getItem("tePosiiton");
  },
  beforeRouteLeave(to, from, next) {
    let tePosiiton =
      document.documentElement && document.documentElement.scrollTop;
    localStorage.setItem("tePosiiton", tePosiiton);
    next();
  },
  methods: {
    //返回上一页
    goBack() {
      let statusFlag = sessionStorage.getItem("typeFlag");
      // this.$globalBack();
      this.$router.push({
        path: "/order/orderList/orderList?moudle=2&status=" + statusFlag
      });
    },
    copyInsureMessage: function() {
      //console.log("复制投保");
    },
    //  去支付
    gotoPayPage() {
      this.varifyCodeApply();
    },
    varifyCodeApply() {
      const params = {
        orderId: this.detailed.id,
        userCode: localStorage.getItem("userCode")
      };
      getPayNo(params).then(res => {
        if (res.code != "1") {
          setTimeout(() => {
            Toast(res.msg);
          }, 500);
        } else {
          this.$store.commit("orderList/setBacktrue");
          this.$router.push({
            name: "paymentIndex",
            params: {
              orderId: this.detailed.id, //订单号
              proposalNo: this.detailed.applicationNo, //投保单号
              totalPremium: res.data.payAmount, // 保费
              policyHolder: this.detailed.insurantName, //投保人姓名
              paymentNo: res.data.payApplyNo,
              checkNo: res.data.payVerificationCode,
              status: this.orderStatus
            }
          });
        }
      });
    },
    //  继续报价
    continueInsurance() {
      const orderId = this.detailed.id;
      if (orderId) {
        this.$router.push({
          name: "vehicle",
          query: {
            orderId: orderId,
            status: this.orderStatus,
            vehicleType: this.vehicleTypeFlag
          }
        });
      }
    },
    //复制投保单
    insureNumber() {
      // let _this = this;
      let clipboard = new this.clipboard(".num");
      clipboard.on("success", function(e) {
        // _this.$toast("复制成功");
      });
      clipboard.on("error", function(e) {
        // _this.$toast("复制失败");
      });
      let params = {
        orderId: this.orderIdValue
      };
      getoddNumb(params).then(res => {
        if (res.code == "1") {
          this.$router.push({
            name: "vehicle",
            query: {
              orderId: res.data.orderId,
              status: this.orderStatus,
              vehicleType: this.vehicleTypeFlag
            }
          });
        } else {
          setTimeout(() => {
            Toast(res.msg);
          }, 500);
        }
      });
    },
    //  一键续保
    onebuttonRenewal() {
      let params = {
        orderId: this.orderIdValue
      };
      oneClickRenewal(params).then(res => {
        if (res.code == "1") {
          this.$router.push({
            name: "vehicle",
            params: {
              orderId: res.data.orderId,
              status: this.orderStatus,
              vehicleType: this.vehicleTypeFlag
            }
          });
        } else {
          setTimeout(() => {
            Toast(res.msg);
          }, 500);
        }
      });
    },
    //  提交核保
    submitAudit() {
      Dialog.confirm({
        title: "",
        message: "是否提交核保？"
      })
        .then(() => {
          const orderId = this.orderIdValue;
          const params = {
            orderId,
            userCode: localStorage.getItem("userCode")
          };
          submitAudit(params).then(res => {
            if (res.code == "1") {
              if (res.data.uwType != "3") {
                Toast({
                  message: "提交核保成功",
                  type: "success",
                  position: "bottom"
                });
              } else {
                // 获取支付号
                this.varifyCodeApply();
              }
            } else {
              setTimeout(() => {
                Toast(res.msg);
              }, 500);
            }
          });
        })
        .catch(() => {
          // on cancel
        });
    },
    // 分享
    sharePage() {
      let licenseNo = "";
      let name = "";
      if (this.detailed.vehicleModelVO.licenseNo) {
        licenseNo = this.detailed.vehicleModelVO.licenseNo;
      }
      let licenseNoNum = "【" + licenseNo + "】商业计划书";
      if (this.detailed.operatorInfo) {
        name = this.detailed.operatorInfo.optName;
      }
      let nameNum =
        "尊敬的客户，专属客户经理[" +
        name +
        "]为您设计的商业计划书，请查阅！保险让生活更美好！";
      getShareCode({ orderId: this.orderIdValue }).then(res => {
        if (res.code == "1") {
          let recommendCode = this.localStorage.get("userInfo", {})
            .recommendCode;
          let tempUrl =
            this.$rootPath +
            "/businessPlanShare?shareCode=" +
            res.data +
            "&recommendCode=" +
            encodeURIComponent(recommendCode);
          let jsonObject = {
            sharePlatform: ["0", "2"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
            shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
            title: licenseNoNum, //分享标题
            imgURL:
              "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
            image: "", //分享图片（base64编码）, 与imgURL二选一
            url: tempUrl, //分享链接
            desc: nameNum, //分享描述
            phoneNum: "" //电话号码,当需要短信分享时获取
          };
          this.$native.share(jsonObject);
        }
      });
    },
    togglePop(i) {
      switch (i) {
        case "show":
          this.$router.push({
            name: "customerInfo",
            params: this.detailed
          });
          break;
        case "vehicleTtaxShow":
          this.$router.push({
            name: "vehicleTtax",
            params: this.detailed.vehicleTaxVo
          });
          // this.vehicleTtaxShow = !this.vehicleTtaxShow;
          break;
        case "fullOfferShow":
          this.$router.push({
            name: "fullOffer",
            params: this.detailed
          });
          break;
        case "xianShow":
          this.$router.push({
            name: "InsuranceInformation",
            params: this.detailed
          });
          break;
        case "quShow":
          this.$router.push({
            name: "channelInformation",
            params: this.detailed
          });
          break;
        case "carShow":
          this.$router.push({
            name: "vehicleInformation",
            params: this.detailed.vehicleModelVO
          });
          break;
        case "teShow":
          this.$router.push({
            name: "specialAgreement",
            params: this.detailed
          });
          break;
        case "queryShow":
          this.queryShow = !this.queryShow;
          break;
        case "enterShow":
          this.enterShow = !this.enterShow;
          break;
        case "uniteShow":
          this.$router.push({
            name: "machanceAssociation",
            params: this.detailed
          });
          break;
        case "terraceShow":
          this.$router.push({
            name: "platformInformation",
            params: this.detailed
          });
          break;
      }
    },
    onSubmit(values) {
      //console.log("submit", values);
    },
    onConfirm(date) {
      this.riqivalue = `${date.getFullYear()}-${date.getMonth() +
        1}-${date.getDate()}`;
      this.showCalendar = false;
    },
    // 增值服务弹出层
    // incrementPopup () {
    //   this.incrementshowPopup = true;
    // },
    // incrementPopup1 () {
    //   this.incrementshowPopup1 = true;
    // },
    //获取订单详情
    getOrderDetail() {
      if (this.$store.state.orderList.backInfoObj.id) {
        this.orderStatus = this.$store.state.orderList.backInfoObj.status;
        this.orderIdValue = this.$store.state.orderList.backInfoObj.id;
        this.payExpireFlag = this.$store.state.orderList.backInfoObj.payExpireFlag;
        this.oneClickRenewalFlag = this.$store.state.orderList.backInfoObj.oneClickRenewalFlag;
        this.vehicleTypeFlag = this.$store.state.orderList.backInfoObj.vehicleTypeFlag;
      } else {
        this.orderStatus = this.$route.query.status;
        this.orderIdValue = this.$route.query.id;
        this.payExpireFlag = this.$route.query.payExpireFlag;
        this.oneClickRenewalFlag = this.$route.query.oneClickRenewalFlag;
      }
      let params = {
        orderId: this.orderIdValue,
        userCode: localStorage.getItem("userCode")
      };
      getDetails(params).then(res => {
        this.detailed = res.data;
        // console.log(
        //   this.detailed.businessSpecialAgreement.specialAgreementList,
        //   "heweiwei"
        // );
        if (this.detailed.businessSpecialAgreement == null) {
          this.specialAgreementListVal = [];
        } else {
          this.specialAgreementListVal = this.detailed.businessSpecialAgreement.specialAgreementList;
        }
        if (this.detailed.trafficSpecialAgreement == null) {
          this.trafficSpecialAgreementVal = [];
        } else {
          this.trafficSpecialAgreementVal = this.detailed.trafficSpecialAgreement.specialAgreementList;
        }
        // console.log(res.data.businessInfoPlatform, "heweiwei");
        if (res.data.businessInfoPlatform == null) {
          this.insureObj = {};
        } else {
          this.insureObj = res.data.businessInfoPlatform;

          if (+this.insureObj.discountCoefficientRate < 0) {
            this.insureObj.discountCoefficientRate =
              +this.insureObj.discountCoefficientRate + 1;
          }
          if (+this.insureObj.ncdRate < 0) {
            this.insureObj.ncdRate = +this.insureObj.ncdRate + 1;
          }
          if (+this.insureObj.trafficViolationRate < 0) {
            this.insureObj.trafficViolationRate =
              +this.insureObj.trafficViolationRate + 1;
          }
        }
        if (this.detailed.vehicleModelVO == null) {
          this.detailed.vehicleModelVO = {};
        }
        // this.isProxyVal = this.detailed.wealchAccApplicationVO.isProxy;
        if (res.data.trafficInfoPlatform == null) {
          this.compulsoryObj = {};
        } else {
          this.compulsoryObj = res.data.trafficInfoPlatform;
          if (+this.compulsoryObj.trafficViolationRate < 0) {
            this.compulsoryObj.trafficViolationRate =
              +this.compulsoryObj.trafficViolationRate + 1;
          }
          if (+this.compulsoryObj.claimRate < 0) {
            this.compulsoryObj.claimRate = +this.compulsoryObj.claimRate + 1;
          }
          if (+this.compulsoryObj.claimRate < 0) {
            this.compulsoryObj.claimRate = +this.compulsoryObj.claimRate + 1;
          }
        }
        if (this.detailed.vehicleChannelVO) {
          this.isProxyFlag = this.detailed.vehicleChannelVO.isProxy;
        }

        if (this.detailed.relationPeopleList != null) {
          for (let i = 0; i < this.detailed.relationPeopleList.length; i++) {
            if (
              this.detailed.relationPeopleList &&
              this.detailed.relationPeopleList[i].relationPeopleType == "1"
            ) {
              this.policyHolder = this.detailed.relationPeopleList[i];
            }
            if (
              this.detailed.relationPeopleList &&
              this.detailed.relationPeopleList[i].relationPeopleType == "2"
            ) {
              this.insuredPerson = this.detailed.relationPeopleList[i];
              // console.log(this.insuredPerson, "this.insuredPerson==>1589");
            }
          }
        } else {
          this.detailed.relationPeopleList = [];
        }

        for (
          var k = 0;
          k < this.detailed.insuranceApplicationList.length;
          k++
        ) {
          if (
            this.detailed.insuranceApplicationList[k].riskCode.startsWith("DE")
          ) {
            this.businessInsure = this.detailed.insuranceApplicationList[k];
            if (
              this.businessInsure.effictiveDate &&
              this.businessInsure.expireDate
            ) {
              if (this.businessInsure.effictiveDate.slice(11) == "00:00:00") {
                this.businessInsure.effictiveDate =
                  this.businessInsure.effictiveDate.slice(0, 10) + " 0时";
                this.businessInsure.effictiveDate.slice(0, 10) + "0时";
              }
              if (this.businessInsure.expireDate.slice(11) == "23:59:59") {
                this.businessInsure.expireDate =
                  this.businessInsure.expireDate.slice(0, 10) + " 24时";
                this.businessInsure.expireDate.slice(0, 10) + "24时";
              }
              this.insureLimit =
                this.businessInsure.effictiveDate +
                "-" +
                this.businessInsure.expireDate;
            } else {
              this.insureLimit = "";
            }
          } else {
            this.crossStrengthInsure = this.detailed.insuranceApplicationList[
              k
            ];
            if (
              this.crossStrengthInsure.effictiveDate &&
              this.crossStrengthInsure.expireDate
            ) {
              if (
                this.crossStrengthInsure.effictiveDate.slice(11) == "00:00:00"
              ) {
                this.crossStrengthInsure.effictiveDate =
                  this.crossStrengthInsure.effictiveDate.slice(0, 10) + " 0时";
                this.crossStrengthInsure.effictiveDate.slice(0, 10) + "0时";
              }
              if (this.crossStrengthInsure.expireDate.slice(11) == "23:59:59") {
                this.crossStrengthInsure.expireDate =
                  this.crossStrengthInsure.expireDate.slice(0, 10) + " 24时";
                this.crossStrengthInsure.expireDate.slice(0, 10) + "24时";
              }
              this.crossStrengthInsureLimit =
                this.crossStrengthInsure.effictiveDate +
                "-" +
                this.crossStrengthInsure.expireDate;
            } else {
              this.crossStrengthInsureLimit = "";
            }
          }
        }
        this.responsibility = res.data.vehicleKindVoList;
      });
    },
    // 复制投保单号
    copyPolicyNo() {
      let _this = this;
      let clipboard = new this.clipboard(".num");
      if (!this.detailed.applicationNo) {
        Toast("投保单号为空，无法复制");
        return;
      }
      clipboard.on("success", function(e) {
        _this.$toast("复制成功");
      });
      clipboard.on("error", function(e) {
        _this.$toast("复制失败");
      });
    },
    // 投保单号
    InsurancePolicy() {
      let _this = this;
      let clipboard = new this.clipboard(".InsuranceNum");
      clipboard.on("success", function(e) {
        _this.$toast("复制成功");
      });
      clipboard.on("error", function(e) {
        _this.$toast("复制失败");
      });
    },
    //  保单号
    applicationForm() {
      let _this = this;
      let clipboard = new this.clipboard(".applicationFormNum");
      clipboard.on("success", function(e) {
        _this.$toast("复制成功");
      });
      clipboard.on("error", function(e) {
        _this.$toast("复制失败");
      });
    },
    regardlessSelect(index) {
      this.print = index;
    }
  }
};
</script>
<style scoped lang="less">
.whetherBack {
  display: inline-block;
  width: 60px;
  padding-top: 15px;
  // height: 75px;
  // line-height: 75px;
  text-align: center;
  color: #666666;
  border-radius: 5px;
  margin-left: 10px;
  font-size: 14px;
}
.active {
  font-size: 17px;
  font-weight: 500;
  color: rgba(0, 0, 0, 1);
}
.line {
  width: 28px;
  height: 5px;
  background: rgba(59, 138, 254, 1);
  border-radius: 3px;
  display: none;
  margin: 10px auto;
}
.containerPopup {
  padding: 20px;
  .container {
    .agreementDetail {
      display: flex;
      width: 260px;
      div {
        flex: 1;
        margin-bottom: 10px;
      }
    }
  }
}
.backgroundLine {
  display: block;
}
// .container {
//   /deep/.van-tabs__line {
//     background: #3b8afe;
//     width: 36px !important;
//   }
// }
.copyPolicyNo {
  // padding-right: 40px;
  position: relative;
  .num {
    position: absolute;
    right: 5px;
    top: 12px;
    padding: 2px 4px;
    border: 1px solid rgb(39, 154, 255);
    border-radius: 5px;
    z-index: 99;
  }
}
.carInfo {
  height: 75px;
  background: url("../../../assets/vehicle/orderList/carInfo.png") no-repeat;
  background-size: 100% 100%;
  div {
    .licensePlate {
      font-size: 20px;
      font-weight: 500;
      color: rgba(0, 0, 0, 1);
      line-height: 28px;
      padding-top: 15px;
      padding-left: 18px;
    }
    .licenseModel {
      padding-left: 18px;
      font-size: 14px;
      color: rgba(68, 68, 68, 1);
      line-height: 20px;
    }
  }
}
.header {
  width: 100%;
  // margin-top: 59px;
  color: #fff;
  font-size: 14px;
  position: relative;
  height: 200px;
  background: #fff;
  .orderDetail {
    position: fixed;
    width: 100%;
    top: 0;
    left: 0;
    z-index: 9999;
    .img {
      width: 18px;
      height: 18px;
      position: absolute;
      top: 50%;
      left: 20px;
      transform: translateY(-50%);
      z-index: 1;
    }
    .title {
      text-align: center;
      line-height: 50px;
      font-size: 17px;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
      // background: linear-gradient(92deg,rgb(50, 100, 248) 0%,rgb(67, 152, 250) 100%);
      background: url("../../../assets/order/orderList/headersTop.png");
      background-size: 100% 100%;
    }
  }
  .info {
    width: 375px;
    height: 202px;
    display: flex;
    align-items: center;
    background: url("../../../assets/vehicle/orderList/backgroundImg.png")
      no-repeat;
    background-size: 375px 202px;
  }
  .img {
    width: 74px;
    height: 29px;
    position: absolute;
    top: 132px;
    right: 28px;
    z-index: 99;
  }
  .nsurancRisks {
    width: 346px;
    height: 73px;
    box-shadow: 0px 6px 6px 0px rgba(231, 231, 231, 0.23);
    border-radius: 15px;
    position: absolute;
    top: 160px;
    z-index: 1;
    left: 15px;
    background: #fff;
    display: flex;
    align-content: center;
    align-items: center;
    justify-content: space-around;
    fy-content:flex-end div {
      text-align: center;
      float: left;
      // width: 48%;
      // height: 50%;
      color: #3b8afe;
      font-size: 20px;
      line-height: 35px;
      // display: flex;
      // justify-content: center;
      // align-items: center;
      // display: flex;
      // flex-wrap: wrap;
      // justify-content: space-between;
      // align-content: flex-start;
    }
    .amountMoney {
      font-size: 12px;
      color: rgba(102, 102, 102, 1);
    }
    .widthStyle {
      height: 26px;
      line-height: 30px;
      text-align: center;
    }
    .widthStyle1 {
      font-size: 20px;
      font-weight: 500;
      color: rgba(59, 138, 254, 1);
    }
  }
  .mid {
    margin-left: 0.5rem;
    width: 85%;
    padding-top: 15px;
    .plateNumber {
      font-size: 20px;
      margin-bottom: 5px;
      height: 26px;
    }
    p {
      line-height: 24px;
    }
  }
}
.infoTop {
  padding-top: 40px;
  background: #ffffff;
  .van-cell__value {
    padding-right: 0px;
  }
}
.margin_20 {
  padding-left: 20px;
  background: #fff;
}
.headerRow {
  display: flex;
  background: linear-gradient(
    125deg,
    rgb(30, 70, 246) 0%,
    rgb(39, 154, 255) 100%
  );
  color: #fff;
  font-size: 14px;
  border-top: 1px solid #2588e6;
  padding: 0.3rem 0;
  > div {
    flex: 1;
    text-align: center;
    div {
      color: #94baef;
      font-size: 13px;
    }
  }
}
.width_60 {
  width: 100px !important;
}
.fixedButton {
  display: flex;
  position: fixed;
  background-color: #fff;
  z-index: 99;
  bottom: 0;
  width: 100%;
  justify-content: space-around;
  .van-button {
    flex: 1;
    color: #486895;
    height: 54px;
    padding: 0;
    margin: 0;
    // .van-button__text {
    //   display: block;
    //   line-height: 12px;
    //   margin: auto 0;
    // }
    // .btnImg {
    //   width: 20px;
    //   // margin-top: 10px;
    //   float: left;
    //   margin-top: 10px;
    // }
    // .btnImgShare {
    //   padding: 0 20px;
    // }
  }
  button {
    border: 1px solid #ececec !important;
  }
}

.popBody {
  margin-top: 34px;
  background: #efeff4;
  left: 0;
  bottom: 0;
  top: 0;
  position: absolute;
  width: 100%;
  margin-right: 150px;
  padding-top: 10px;
  // .van-tabs {
  //   width: 240px;
  // }
  .background {
    height: 14px;
    background: #efeff4;
  }
  .card {
    background-color: #fff;
    padding: 0.2rem;
    // margin-bottom: 0.3rem;
    h3 {
      padding: 0.3rem 0;
      position: relative;
      font-size: 16px;
      &::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: 0;
        right: auto;
        top: auto;
        height: 1px;
        width: 100%;
        background-color: #e1e1e1;
        display: block;
        -webkit-transform-origin: 50% 100%;
        transform-origin: 50% 100%;
      }
    }
    li {
      display: flex;
      justify-content: space-between;
      font-size: 14px;
      padding: 0.1rem 0;
    }
  }
}
.copyPolicyNo .van-cell__title,
.van-cell__value {
  flex: none;
  color: #666666 !important;
  font-size: 14px;
}
.marginRight_30 {
  position: relative;
  font-size: 14px;
  .num {
    position: absolute;
    right: 4px;
    top: 12px;
    padding: 2px 4px;
    color: #3b8afe;
    border-radius: 5px;
    z-index: 99;
  }
  // span {
  //   margin-right: 29px;
  // }
}
.first {
  padding-right: 40px;
  .van-cell__title {
    width: 110px;
    padding: 0;
    span {
      width: 100px;
    }
  }
}
.line_h {
  height: 31px;
}
.van-tabs__nav {
  padding-right: 150px;
}
.popBody1 {
  margin-top: 43px;
  margin-right: 150px;
  background: url("../../../assets/order/orderList/headers.png") no-repeat;
}
.backImg {
  height: 69px;
  background: url("../../../assets/order/orderList/headers.png") no-repeat;
  .title {
    font-size: 16px;
    font-weight: 500;
    color: #000;
    line-height: 75px;
    padding-left: 20px;
  }
}
.orderTitle {
  width: 140px;
  display: block;
  height: 22px;
  font-size: 16px;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  line-height: 22px;
}
.statusFlag {
  float: right;
  span {
    display: block;
    // width: 74px;
    // height: 24px;
    color: #3b8afe;
    border-radius: 12px;
    font-size: 13px;
    text-align: center;
  }
}
// 小图标
.cellImg {
  width: 20px;
  height: 20px;
  margin-right: 0.2rem;
}

/deep/ .van-popup__close-icon {
  position: fixed !important;
  top: 0.25rem;
}

/deep/ .van-cell__title {
  display: flex;
  align-items: center;
}
.van-cell {
  padding: 0.26667rem 18px 0.26667rem 21px;
}
.van-cell__value {
  // padding-right: 28px;
}
// 险别信息
.rowXian {
  display: flex;
  background-color: #fff;
  padding: 0.2rem;
  div:nth-of-type(1) {
    width: 40%;
  }
  div:nth-of-type(2) {
    width: 30%;
    text-align: center;
  }
  div:nth-of-type(3) {
    width: 30%;
    text-align: center;
  }
}
// 渠道信息
.rowQu {
  position: relative;
  .InsuranceNum,
  .applicationFormNum {
    position: absolute;
    right: 4px;
    top: 8px;
    padding: 1px 2px;
    border: 1px solid rgb(39, 154, 255);
    border-radius: 5px;
  }
  .InsuranceNum1 {
    position: absolute;
    right: 35px;
    top: 10px;
  }
  display: flex;
  background-color: #fff;
  padding: 0.2rem;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    // text-align: center;
    align-content: center;
  }
  .rowQuLeft {
    flex: 1;
  }
  .rowQuRight {
    flex: 4;
    text-align: right;
  }
}
.rowQuTitle {
  display: flex;
  background-color: #ccc;
  div {
    text-align: center;
    flex: 1;
    font-size: 14px;
    line-height: 40px;
  }
}
.rowQu1 {
  display: flex;
  background-color: #fff;
  padding: 0.2rem;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    flex: 1;
  }
}

// 核保查询
.commonRow {
  display: flex;
  background-color: #fff;
  padding: 0.2rem;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    text-align: right;
  }
}
h3 {
  font-size: 16px;
  color: #6d6d72;
  padding: 0.2rem;
}

.form .van-field__body .van-button__text {
  color: #ff8c00;
}
/deep/ .van-button--default {
  border: none;
}
/deep/ .navigatorBottom {
  display: none;
}
.form {
  .van-field__control--custom {
    justify-content: flex-end;
  }
}
</style>
