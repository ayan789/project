<template>
  <div>
    <steps-nav-bar
      active="信息确认"
      type="vehicle"
      left
      @back="clickBack"
      :right="{ icon: 'wap-home-o', click: goHome }"
    />
    <div class="vehicle-info">
      <div class="vehicle-license">{{ carInfo.licenseNo || "未上牌" }}</div>
      <div class="vehicle-brand">
        {{ carInfo.vehicleBrand || "车辆品牌为空" }}
      </div>
    </div>
    <!-- 页面内容 -->
    <div class="content">
      <!--车辆-->
      <div class="card">
        <div class="item group">
          <div class="item-left">
            <img
              class="item-left-icon"
              src="../../../assets/vehicle/vehicleInfo/vehicle.png"
            />
            <div class="item-title">车辆信息</div>
          </div>
          <img
            class="item-right-icon"
            src="../../../assets/vehicle/vehicleInfo/editit.png"
            @click="editMessage('vehicleMessage')"
          />
        </div>
        <!--车辆展示信息-->
        <div
          v-for="(labelName, key) in showCarInfo"
          :key="key"
          class="info-row"
        >
          <div class="key-col">{{ labelName }}</div>
          <div class="value-col">{{ carInfo[key] }}</div>
        </div>
        <div class="more-info" @click="showMoreVehicleInfo">
          <span class="info-title">更多信息</span>
          <van-icon class="arrow" name="arrow" size="10" />
        </div>
      </div>
      <!--车主-->
      <div class="card">
        <div class="item group">
          <div class="item-left">
            <img
              class="item-left-icon"
              src="../../../assets/vehicle/vehicleInfo/carowner.png"
            />
            <div class="item-title">车主信息</div>
          </div>
          <img
            class="item-right-icon"
            src="../../../assets/vehicle/vehicleInfo/editit.png"
            @click="editMessage('ownerMessage')"
          />
        </div>
        <!--车主展示信息-->
        <div
          v-for="(labelName, key) in showOwnerInfo"
          :key="key"
          class="info-row"
        >
          <div class="key-col">{{ labelName }}</div>
          <!--证件类型做映射处理-->
          <div v-if="key == 'identityType'" class="value-col">
            {{ identifyType[carOwnerInfo[key]] }}
          </div>
          <div v-else class="value-col">{{ carOwnerInfo[key] }}</div>
        </div>
      </div>
      <!--投保方案-->
      <div class="card">
        <div class="item group">
          <div class="item-left">
            <img
              class="item-left-icon"
              src="../../../assets/vehicle/vehicleInfo/plan.png"
            />
            <div class="item-title">投保方案</div>
          </div>
          <img
            class="item-right-icon"
            src="../../../assets/vehicle/vehicleInfo/editit.png"
            @click="editMessage('insurePlan')"
          />
        </div>
        <!--交强险-->
        <div class="risk-info" v-if="insurePlan.trafficChecked == '1'">
          <div>
            <div class="risk-time-title">交强险保险期限</div>
            <div class="risk-time-value">
              {{ insurePlan.trafficEffectiveStartDate | insuranceDate }}
              -
              {{ insurePlan.trafficEffectiveEndDate | insuranceEndDate }}
            </div>
            <div class="info-col">
              <div class="key-row">
                <div>险种</div>
                <div>保费</div>
              </div>
              <div class="value-row">
                <div>交强险</div>
                <div>{{ insurePlan.trafficDuePremium }}</div>
              </div>
              <div class="value-row">
                <div>车船税</div>
                <div>{{ insurePlan.tax.sumPayment }}</div>
              </div>
            </div>
          </div>
        </div>
        <!--商业险-->
        <div class="risk-info" v-if="insurePlan.businessChecked == '1'">
          <div>
            <div class="risk-time-title">商业险保险期限</div>
            <div class="risk-time-value">
              {{ insurePlan.businessEffectiveStartDate | insuranceDate }}
              -
              {{ insurePlan.businessEffectiveEndDate | insuranceEndDate }}
            </div>
            <div class="info-col">
              <div class="key-row key-row-long">
                <div>险种</div>
                <div>保额(元)</div>
                <div>保费(元)</div>
              </div>
              <div
                v-for="(item, index) in insurePlan.businessRisks"
                :key="index"
              >
                <div class="value-row value-row-long">
                  <div>{{ item.kindName | kindName }}</div>
                  <div>{{ amount(item) }}</div>
                  <div>{{ item.premium }}</div>
                </div>
                <div
                  class="value-row value-row-nocenter"
                  v-show="item.kindFlag == '1'"
                >
                  <div>
                    {{ item.kindName | kindName }}
                    (不计免赔)
                  </div>
                  <div>{{ item.nonDeductiblePremium }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--联合销售-->
        <div
          class="risk-info"
          v-if="insurePlan.wealthAccChecked == '1' && toShow"
        >
          <div class="risk-time-title">联合销售</div>
          <div class="info-col">
            <div class="key-row">
              <div>险种</div>
              <div>方案</div>
              <div>份数</div>
              <div>保费(元)</div>
            </div>
            <div class="value-row">
              <div>{{ insurePlan.wealthAccFormulaGroupName }}</div>
              <div>{{ insurePlan.wealthAccFormulaName }}</div>
              <div>{{ insurePlan.wealthAccBuyNum }}</div>
              <div>{{ insurePlan.wealthAccPremium }}</div>
            </div>
          </div>
        </div>
        <div
          v-show="insurePlan.wealthAccChecked == '1'"
          class="unfolding-arrow"
          @click="clickArrow"
        >
          <img :src="arrowImg" />
        </div>
        <div class="item">
          <div class="total-premium">
            合计保费（元）
          </div>
          <div class="premium-value">
            <s v-if="insurePlan.businessChecked == '1'">
              {{ beforeTotalPayment }}
            </s>
            <span style="width:20px;"></span>
            {{ totalPayment || "0.00" }}
          </div>
        </div>
      </div>
      <!--特别约定-->
      <div class="card">
        <div
          :class="['item', orderParams.engageList.length ? 'group' : '']"
          @click="specialAgreement"
        >
          <div class="item-left">
            <img
              class="item-left-icon"
              src="../../../assets/vehicle/vehicleInfo/appoint.png"
            />
            <div class="item-title">特别约定</div>
          </div>
          <img
            class="item-right-icon"
            src="../../../assets/common/next.png"
            @click="clickNext('specialAppoint')"
          />
        </div>
        <div
          class="engages"
          v-show="engageList.engages.length || engageList.bizEngages.length"
        >
          <div v-show="engageList.engages.length" class="engages-type">
            交强险特约名称
          </div>
          <div
            class="engages-item"
            v-for="(item, idx) in engageList.engages"
            :key="'traffic-engages-' + idx"
          >
            <div>{{ item.specialAgreementName }}</div>
            <div>
              {{
                item.specialContent ||
                  item.specialAgreementContent ||
                  item.specialAgreementName
              }}
            </div>
          </div>
          <div v-show="engageList.bizEngages.length" class="engages-type">
            商业险特约名称
          </div>
          <div
            class="engages-item"
            v-for="(item, idx) in engageList.bizEngages"
            :key="'business-engages-' + idx"
          >
            <div>{{ item.specialAgreementName }}</div>
            <div>
              {{
                item.specialContent ||
                  item.specialAgreementContent ||
                  item.specialAgreementName
              }}
            </div>
          </div>
        </div>
      </div>
      <!-- 反洗钱 -->
      <div
        class="card"
        v-show="insurePlan.businessDuePremium >= 10000"
        @click="clickNext('cashMessage')"
      >
        <div class="item">
          <div class="item-left">
            <img
              class="item-left-icon"
              src="../../../assets/vehicle/vehicleInfo/anti.png"
            />
            <div class="item-title">{{ cashMessage.title }}</div>
          </div>
          <img class="item-right-icon" src="../../../assets/common/next.png" />
        </div>
      </div>
      <!--星级权益-->
      <div class="card" v-show="recognizee.custType == '01'">
        <div class="item group" @click="clickNext('starRight')">
          <div class="item-left">
            <img
              class="item-left-icon"
              src="../../../assets/vehicle/vehicleInfo/rightsinterests.png"
            />
            <div class="item-title">星级权益</div>
          </div>
          <img class="item-right-icon" src="../../../assets/common/next.png" />
        </div>
        <!-- new -->
        <div class="customer-info">
          <div class="star-level">
            <div class="stars">
              <img
                v-for="i in Number(rightsInterests.newCustLevel) || 1"
                class="star"
                :key="i"
                src="../../../assets/vehicle/vehicleInfo/star.png"
                alt=""
              />
            </div>
            <div class="customer-text">出单后星级</div>
          </div>
          <div class="customer-integral">
            <span class="integral">{{ rightsInterests.newIntegral }}</span>
            <span class="customer-text">出单后所获得积分</span>
          </div>
        </div>
      </div>
      <!-- 投保验证码 -->
      <div class="card" v-show="verifyCodeShow">
        <div class="item group">
          <div class="item-left">
            <img
              class="item-left-icon"
              src="../../../assets/vehicle/vehicleInfo/rightsinterests.png"
            />
            <div class="item-title">投保验证码</div>
          </div>
          <img class="item-right-icon" src="../../../assets/common/next.png" />
        </div>
        <div class="item">
          <div>验证码</div>
          <input v-model="verifyCode" class="verifycode-input" />
          <div
            class="send-verifycode-button"
            :style="countDownTime < 300 ? 'color: #ccc' : 'color: #3b8efe'"
            @click="sendVerifyCode"
          >
            {{ countDownTime < 300 ? countDownTime + " S" : "获取验证码" }}
          </div>
        </div>
      </div>
    </div>
    <!-- 底部按钮 -->
    <div class="bottom-info">
      <div class="business-plan-book" @click="clickBook">商业计划书</div>
      <div class="submit" @click="toProposal">
        {{ auditResult.uwType == "2" ? "特批申请" : "提交订单" }}
      </div>
    </div>
    <!-- 是否提交核保sheet -->
    <van-action-sheet
      v-model="showAudit"
      :actions="auditActions"
      description="是否提交核保？"
      cancel-text="取消"
      :close-on-click-overlay="false"
      @select="auditSelect"
      @cancel="auditSelect(10)"
    />
    <!-- 核保信息 -->
    <van-dialog
      v-model="showAuditResult"
      @close="auditResultClose"
      title="核保信息"
      class="dialog"
    >
      <div class="info">
        <div>
          <label>核保结果类型：</label>
          <span>{{ uwTypeMap[auditResult.uwType] }}</span>
        </div>
        <div>
          <label>核保级别：</label>
          <span>{{ auditResult.uwLevel + "级" }}</span>
        </div>
        <div>
          <label>是否需要验车：</label>
          <span>{{ auditResult.isNeedCheck == "1" ? "是" : "否" }}</span>
        </div>
        <!-- <div>
          <label>规则匹配信息：</label>
        </div>
        <div class="list">
          <div v-for="(val, index) in auditResult.uwInfoList" :key="index">
            <label class="index">{{ index + 1 }}、</label>
            <span v-for="(item, index) in val.underwriteRuleInfos" :key="index">
              {{ item.ruleDescription }}
            </span>
          </div>
        </div> -->
        <div>
          <p v-if="auditResult.uwType == '1'">
            该订单被拒保，请修改信息后重新出单。
          </p>
          <p v-else-if="auditResult.uwType == '2'">
            该订单自核不通过，需返回修改，如仍需提交，可申请特批。
          </p>
          <p v-else-if="auditResult.uwType > 3">
            该订单需人工核保，提交订单后请密切关注核保结果。
          </p>
        </div>
      </div>
    </van-dialog>
    <!-- 特别约定 -->
    <van-popup
      v-model="agreementShow"
      position="bottom"
      :round="false"
      style="width:100%;height:100%;border:none;"
    >
      <insureAppointConfirm @closeAgreement="closeAgreement" />
    </van-popup>
  </div>
</template>

<script>
import insureAppointConfirm from "./insureAppointConfirm";
import StepsNavBar from "@/components/stepsNavBar.vue";
import { mapState, mapMutations, mapActions } from "vuex";
import { Toast, Dialog } from "vant";
import config from "@src/common/json/newCoreDataDictionary";
import { trackMap } from "@src/utils/index.js";
import {
  getOrderIntegral,
  submitOrder,
  submitAudit,
  getPayNo,
  getShareCode,
  getEngageList,
  sendVerifyCode,
  getCheckNoSwitch
} from "@src/http/module/vehicle";
import { imageUpdate } from "@src/http/module/order";
import { getDetails } from "@src/http/module/order";
const netErr = "请求失败，请检查网络";
function deepCopy(obj) {
  return JSON.parse(JSON.stringify(obj));
}

export default {
  name: "vehicleInsureConfirm",
  components: {
    insureAppointConfirm,
    [StepsNavBar.name]: StepsNavBar
  },
  props: {
    // msg: String,
  },
  data() {
    return {
      // 核保结果状态
      uwTypeMap: {
        "1": "拒保",
        "2": "返回录单员修改",
        "4": "人工核保",
        "5": "人工核保",
        "6": "人工核保",
        "7": "人工核保"
      },
      // 特别约定页面
      agreementShow: false,
      // 是否提交审核弹框
      showAudit: false,
      // 核保结果信息弹框
      showAuditResult: false,
      // 是否提交审核弹框枚举
      auditActions: [
        { name: "是" },
        { name: "否" },
        { name: "否，我要去上传影像" }
      ],
      // 核保结果信息
      auditResult: {
        uwType: "3",
        uwInfoList: [],
        uwLevel: "",
        isNeedCheck: "1"
      },
      // 投保单号
      applicationNo: "",
      orderParams: {
        // 订单号
        orderId: "",
        // 反洗钱
        antiInfo: {},
        // 特别约定
        engageList: []
      },
      toShow: false, //联合销售下拉显示
      arrowImg: require("../../../assets/customer/InteractiveRights/down_arrow.png"),
      // 证件类型字典
      identifyType: config.identifyType,
      // 需要展示的车辆信息
      showCarInfo: {
        // licenseNo: "车牌号",
        frameNo: "车架号",
        enrollDate: "初登日期",
        engineNo: "发动机号"
        // vehicleBrand: "厂牌型号"
      },
      // 需要展示的车主信息
      showOwnerInfo: {
        name: "姓名",
        identityType: "证件类型",
        identityNo: "证件号码"
      },
      //反洗钱信息
      cashMessage: {
        title: "反洗钱信息"
      },
      antiFlag: "",
      verifyCodeShow: false,
      verifyCode: "",
      countDownTime: 300,
      countDownTimer: null
    };
  },
  computed: {
    ...mapState("vehicle", {
      orderId: state => state.orderId,
      carInfo: state => state.car,
      carOwnerInfo: state => state.carOwner,
      quoteInfo: state => state.quoteInfo,
      insurePlan: state => state.insurancePlan,
      uwResult: state => state.uwResult,
      policyHolder: state => state.policyHolder,
      recognizee: state => state.recognizee,
      antiInfo: state => state.antiInfo,
      engageList: state => state.engageList,
      rightsInterests: state => state.rightsInterests,
      control: state => state.control
    }),
    beforeTotalPayment() {
      if (this.insurePlan.totalPremium) {
        let beforeValue =
          this.insurePlan.totalPremium -
          this.insurePlan.businessDuePremium +
          this.insurePlan.businessStdPremium;
        return Number(beforeValue).toFixed(2);
      }
      let total = 0;
      if (this.insurePlan.trafficChecked == "1") {
        if (Number(this.insurePlan.trafficDuePremium)) {
          total += this.insurePlan.trafficDuePremium;
        }
        if (Number(this.insurePlan.tax.sumPayment)) {
          total += this.insurePlan.tax.sumPayment;
        }
      }
      if (this.insurePlan.businessChecked == "1") {
        if (Number(this.insurePlan.businessStdPremium)) {
          total += this.insurePlan.businessStdPremium;
        }
      }
      if (this.insurePlan.wealthAccChecked == "1") {
        if (Number(this.insurePlan.wealthAccPremium)) {
          total += this.insurePlan.wealthAccPremium;
        }
      }
      return total.toFixed(2);
    },
    totalPayment() {
      if (this.insurePlan.totalPremium) {
        return Number(this.insurePlan.totalPremium).toFixed(2);
      }
      let total = 0;
      if (this.insurePlan.trafficChecked == "1") {
        if (Number(this.insurePlan.trafficDuePremium)) {
          total += this.insurePlan.trafficDuePremium;
        }
        if (Number(this.insurePlan.tax.sumPayment)) {
          total += this.insurePlan.tax.sumPayment;
        }
      }
      if (this.insurePlan.businessChecked == "1") {
        if (Number(this.insurePlan.businessDuePremium)) {
          total += this.insurePlan.businessDuePremium;
        }
      }
      if (this.insurePlan.wealthAccChecked == "1") {
        if (Number(this.insurePlan.wealthAccPremium)) {
          total += this.insurePlan.wealthAccPremium;
        }
      }
      return total.toFixed(2);
    }
  },
  created() {},
  activated() {
    this.agreementShow = false;
    this.showAudit = false;
    this.applicationNo = "";
    this.agreementShow = false;
    this.showAudit = false;
    this.applicationNo = "";
    // 获取页面由来
    if (this.$route.params) {
      let params = this.$route.params;
      this.antiFlag = params.antiFlag;
    }
    let orderId = this.$GetRequest("orderId");
    if (orderId) {
      this.setOrderId(orderId);
    }
    // 订单ID
    this.orderParams.orderId = this.orderId;
    if (this.orderId && !this.control.vehicleConfirm) {
      if (
        this.control.vehicleInfoInit &&
        this.control.vehicleModelChecked &&
        this.control.vehicleQuote &&
        this.control.vehiclePrivyInfo
      ) {
        //新创建的订单
        this.starRightsinfo();
        this.getEngageList();
      } else {
        this.init();
      }
      this.control.vehicleConfirm = true;
    }
    this.auditResult.uwType = this.uwResult.uwType;
  },
  mounted() {
    const userInfo = this.localStorage.get("userInfo", {});
    const { comCode } = userInfo;
    getCheckNoSwitch({ comCode, orderId: this.orderId })
      .then(res => {
        if (res.code === "1") {
          // payCheckNoFlag "支付验证码开关 0-关闭 1-打开"
          const { payCheckNoFlag } = res.data;
          this.verifyCodeShow = payCheckNoFlag == "1" ? true : false;
        }
      })
      .catch(error => {});
  },
  methods: {
    ...mapMutations("vehicle", [
      "setOrderId",
      "setProposalNo",
      "setEngageList",
      "setRightsInterests"
    ]),
    ...mapActions("vehicle", ["initWithDetail"]),
    init() {
      getDetails({
        orderId: this.orderId,
        userCode: this.localStorage.get("userInfo", {}).userCode
      })
        .then(res => {
          if (res.code === "1") {
            if (this.antiFlag != "1" || !this.antiFlag) {
              this.initWithDetail(res.data);
              this.engageList.bizEngages &&
                this.packEngage(
                  this.engageList.bizEngages.map(item => ({
                    ...item,
                    selected: true
                  }))
                );
              this.engageList.engages &&
                this.packEngage(
                  this.engageList.engages.map(item => ({
                    ...item,
                    selected: true
                  }))
                );
              this.$nextTick(() => {
                this.$forceUpdate();
              });
            }
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    amount(risk) {
      let amount = "";
      if (risk.brokenCode == "2") {
        amount += "进口";
      } else if (risk.brokenCode == "1") {
        amount += "国产";
      }
      if (risk.kindCode == "C100135") {
        //指定修理厂险
        if (risk.deductibleRate) {
          amount += risk.deductibleRate + "%";
        }
        return amount;
      } else if (risk.kindCode == "C100126") {
        //玻璃单独破碎险
        amount += "玻璃";
        return amount;
      }
      return risk.amount;
    },
    //  星级权益接口
    async starRightsinfo() {
      const res = await getOrderIntegral({
        orderId: this.orderId
      });
      if (res.code == "1") {
        if (res.data) {
          let starRight = Object.assign({}, res.data, {
            insuredName: this.recognizee.name
          });
          this.setRightsInterests(starRight);
        }
      } else {
        Toast(res.msg);
      }
    },
    async getEngageList() {
      const res = await getEngageList({
        orderId: this.orderId
      });
      if (res.code == "1") {
        let { bizEngages, engages } = res.data;
        bizEngages = bizEngages || [];
        engages = engages || [];
        bizEngages &&
          this.packEngage(
            bizEngages.map(item => ({ ...item, selected: true }))
          );
        engages &&
          this.packEngage(engages.map(item => ({ ...item, selected: true })));
        //TODO
        this.setEngageList({ bizEngages, engages });
      }
    },
    // 显示特别约定页面
    specialAgreement() {
      this.agreementShow = true;
    },
    closeAgreement(agreements) {
      if (agreements) {
        let copyAgreements = deepCopy(agreements);
        this.orderParams.engageList.splice(
          0,
          this.orderParams.engageList.length
        );
        copyAgreements.bizEngages && this.packEngage(copyAgreements.bizEngages);
        copyAgreements.engages && this.packEngage(copyAgreements.engages);
        this.setEngageList(copyAgreements);
      }
      this.agreementShow = false;
    },
    clickBack() {
      if (this.$route.params.ocrFlag == "1") {
        this.$router.push({ name: "carOCR" });
      } else {
        this.applicationNo = "";
        this.$router.push({ name: "insureInformation" });
      }
    },
    auditResultClose() {
      /**"1": "拒保", "2": "返回录单员修改", "4" - "7": "人工核保", */
      if (this.auditResult.uwType == 1) {
        //拒保
      } else if (this.auditResult.uwType == 2) {
        //返回录单员修改
        return;
      } else if (this.auditResult.uwType > 3) {
        //人工核保
        // this.$router.push({ name: "temp" });
      }
      this.goHome();
    },
    goHome() {
      this.$router.push({ path: "/" });
    },
    // 商业计划书
    clickBook() {
      this.$router.push({
        name: "businessPlanShare",
        query: { orderId: this.orderId }
      });
    },
    auditSelect(action, index) {
      this.showAudit = false;
      switch (index) {
        // 是
        case 0: {
          //提交核保
          this.submitAudit();
          return;
        }
        case 1: {
          // 否
          break;
        }
        case 2: {
          // 否，上传影像
          this.gotoUploadImg(this.applicationNo);
          return;
        }
        default: {
          break;
        }
      }
      this.goHome();
    },
    async gotoUploadImg(insureNo) {
      let dateValue = new Date();
      let year = String(dateValue.getFullYear());
      let mounth = String(dateValue.getMonth());
      let day = String(dateValue.getDay());
      if (year.length == 1) {
        year = "0" + year;
      }
      if (day.length == 1) {
        day = "0" + day;
      }
      let dataParmas = year + mounth + day;
      let imgType = "";
      imgType = "UW_D";
      let params = {
        userCode: localStorage.getItem("userCode"),
        comCode: localStorage.getItem("comCode"),
        bussNo: insureNo,
        imgType,
        busiDate: dataParmas,
        flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0"
      };
      const res = await imageUpdate(params);
      if (res.code !== "1") {
        setTimeout(() => {
          Toast(res.msg);
          this.goHome();
        }, 500);
      } else {
        this.$native.openWebView({
          url: res.data.url,
          type: "0",
          titleBarTag: "1",
          titleBar: "影像上传",
          isBackHome: "1"
        });
      }
    },
    sendVerifyCode() {
      // verifyCode: this.verifyCode
      if (this.countDownTimer) return;
      sendVerifyCode({ orderId: this.orderId })
        .then(res => {
          if (res.code === "1") {
            this.countDownTime -= 1;
            this.countDownTimer = setInterval(() => {
              this.countDownTime -= 1;
              if (this.countDownTime <= 0) {
                clearInterval(this.countDownTimer);
                this.countDownTimer = null;
                this.countDownTime = 300;
              }
            }, 1000);
          }
        })
        .catch(error => {});
    },
    // 提交订单
    async toProposal() {
      let _this = this,
        bizAddAgreeFlag = true,
        tsAddAgreeFlag = true;
      let antiFlag = true;
      if (this.insurePlan.businessDuePremium >= 10000) {
        if (
          this.antiInfo &&
          typeof this.antiInfo == "object" &&
          Object.keys(this.antiInfo).length
        ) {
          // antiFlag = true;
        } else {
          antiFlag = false;
        }
      }
      // if (_this.applicationNo) {
      //   return _this.$dialog.alert({ message: "请不要重复提交" }).then(() => {
      //     this.$router.push({ path: "/" });
      //   });
      // }
      if (!this.orderParams.engageList.length) {
        // this.$dialog.alert({ message: "请选择特别约定条款" }).then(() => {
        //   this.agreementShow = true;
        // });
        // return;
      }
      if (this.insurePlan.businessChecked == "1") {
        bizAddAgreeFlag = this.orderParams.engageList.some(item => {
          return item.specialAgreementCode == "A00193";
        });
      }
      if (this.insurePlan.trafficChecked == "1") {
        tsAddAgreeFlag = this.orderParams.engageList.some(item => {
          return item.specialAgreementCode == "A00193";
        });
      }
      // 未选择增值特约提醒
      if (!bizAddAgreeFlag || !tsAddAgreeFlag) {
        Dialog.confirm({
          title: "提示信息",
          message:
            "投保交强险/商业险，未选择增值服务特别约定（交强险）/增值服务特别约定（商业险），请确认是否提交？",
          confirmButtonText: "是",
          cancelButtonText: "否"
        })
          .then(() => {
            if (antiFlag) {
              if (this.insurePlan.businessDuePremium < 10000) {
                this.requestOrder("jump");
                return;
              }
              this.requestOrder();
            } else {
              //chenjiafeng 2020/5/12
              if (this.insurePlan.businessDuePremium < 200000) {
                //当保费小于20万时，提示框增加跳过逻辑，不影响投保流程
                Dialog.confirm({
                  title: "提示信息",
                  message: "请录入反洗钱信息",
                  confirmButtonText: "是",
                  cancelButtonText: "跳过"
                })
                  .then(() => {
                    this.$router.push({ name: "insureAntiMoney" });
                  })
                  .catch(() => {
                    //不传反洗钱
                    this.requestOrder("jump");
                  });
              } else {
                //如果保费大于等于20万，反洗钱信息为必填，不可跳过
                Dialog.confirm({
                  title: "提示信息",
                  message: "请录入反洗钱信息",
                  confirmButtonText: "是",
                  showCancelButton: false
                }).then(() => {
                  this.$router.push({ name: "insureAntiMoney" });
                });
              }
            }
          })
          .catch(() => {
            // TODO 去选择增值服务特约
            _this.agreementShow = true;
          });
      } else {
        this.requestOrder();
      }
    },
    async requestOrder(type) {
      let _this = this;
      if (type == "jump") {
        //当用户跳过时，删除反洗钱字段
        for (let key in _this.orderParams) {
          if (key == "antiInfo") {
            delete _this.orderParams[key];
          }
        }
      } else {
        _this.orderParams.antiInfo = _this.antiInfo;
      }
      if (this.verifyCodeShow) {
        _this.orderParams.verifyCode = this.verifyCode;
      }
      // NOTE 提交订单
      _this.orderParams.isNeedSpecialApprove =
        this.auditResult.uwType == "2" ? "Y" : "N";
      try {
        let res = await submitOrder(_this.orderParams);
        if (res.code == "1" && res.data) {
          // 存储投保单号
          this.applicationNo = res.data.applicationNo;
          this.setProposalNo(res.data.applicationNo);
          this.showAudit = true;
          //埋点
          trackMap("c_car_submit_order", "车险提交订单", {
            c_salesperson_type: "N",
            c_market_type: "Y",
            c_market_name: "",
            c_market_id: "",
            c_event_category: "车险出单"
          });
        } else {
          this.$dialog.alert({ message: res.msg });
        }
      } catch (err) {
        this.$dialog
          .alert({ message: "下单失败,请到订单列表继续操作" })
          .then(() => {
            this.$router.push({ path: "/" });
          });
      }
    },
    // 提交核保
    async submitAudit() {
      let params = {
        // 投保单号
        businessNo: this.applicationNo,
        // 订单号
        orderId: this.orderId
      };
      try {
        let res = await submitAudit(params);
        if (res.code == "1" && res.data) {
          // 核保成功
          if (res.data.uwType == "3") {
            Dialog.alert({
              title: "核保结果",
              message: "核保成功，请前往支付"
            }).then(() => {
              let params = {
                orderId: this.orderId,
                cancelPaymentCodeFlag: "0",
                payType: ""
              };
              getPayNo(params).then(
                res => {
                  if (res.code === "1") {
                    this.$router.push({
                      name: "paymentIndex",
                      params: {
                        orderId: this.orderId, //订单号
                        proposalNo: this.applicationNo, //投保单号
                        totalPremium: String(this.insurePlan.totalPremium),
                        policyHolder: this.policyHolder.name, //投保人姓名
                        paymentNo: res.data.payApplyNo,
                        checkNo: res.data.payVerificationCode
                      }
                    });
                  } else {
                    this.$dialog
                      .alert({ message: res.msg || res.message })
                      .then(() => {
                        this.goHome();
                      });
                  }
                },
                error => {
                  this.$dialog.alert({ message: "获取支付号失败" }).then(() => {
                    this.goHome();
                  });
                }
              );
            });
          } else {
            // 弹框显示核保失败原因
            this.auditResult = res.data;
            this.showAuditResult = true;
          }
        } else {
          this.$dialog.alert({ message: res.msg }).then(() => {
            this.$router.push({ path: "/" });
          });
        }
      } catch (err) {
        this.$dialog
          .alert({ message: "核保失败,请在订单列表继续操作" })
          .then(() => {
            this.$router.push({ path: "/" });
          });
      }
    },
    share() {
      getShareCode({ orderId: this.orderId })
        .then(
          res => {
            if (res.code == "1") {
              //调用接口参数
              const jsonObject = {
                sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
                shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
                title: "保单详情", //分享标题
                imgURL:
                  "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //base64
                image: "", //分享图片（base64编码）, 与imgURL二选一
                url: this.$rootPath + "/insureInfo?shareCode=" + res.data, //分享链接
                desc: "尊敬的客户，请查看您的保单详情。" //分享描述
              };
              //调用壳方法，传递调用参数和接受响应数据
              this.$native.share(jsonObject, data => {
                console.log("share 接口调用成功，壳响应数据:" + data);
              });
            }
          },
          error => {}
        )
        .catch(error => {});
    },
    clickArrow: function() {
      this.toShow = !this.toShow;
      this.arrowImg =
        this.toShow == false
          ? require("../../../assets/customer/InteractiveRights/down_arrow.png")
          : require("../../../assets//customer/InteractiveRights/up_arrow.png");
    },
    // 更多信息
    showMoreVehicleInfo() {
      this.$router.push({ path: "/insureMoreInfo" });
    },
    // 编辑车辆、保险方案信息
    editMessage(info) {
      switch (info) {
        case "vehicleMessage": {
          this.$router.push({ path: "/vehicle" });
          break;
        }
        case "ownerMessage": {
          this.$router.push({ path: "/vehicle" });
          break;
        }
        case "insurePlan": {
          this.$router.push({ path: "/selectProduct/insurancePlans" });
          break;
        }
        default:
          break;
      }
    },
    clickNext(info) {
      switch (info) {
        case "specialAppoint": {
          this.agreementShow = true;
          break;
        }
        //反洗钱
        case "cashMessage": {
          this.$router.push({ name: "insureAntiMoney" });
          break;
        }
        //权益
        case "starRight": {
          this.$router.push({
            name: "insureStarRight",
            params: { starInfo: this.rightsInterests }
          });
          break;
        }
        default:
          break;
      }
    },
    // 封装特约入参
    packEngage(engages) {
      engages.map(item => {
        if (item.selected) {
          // delete item.selected;
          // 增值特约处理
          if (item.serviceTypes && item.serviceTypes.length) {
            let tmpObj = {
              serviceItems: []
            };
            // 数组拍平
            item.serviceTypes.map(serviceType => {
              serviceType.serviceItems.map(serviceItem => {
                if (serviceItem.selected) {
                  delete serviceItem.selected;
                  tmpObj.serviceItems.push(deepCopy(serviceItem));
                }
              });
            });
            delete item.serviceTypes;
            item.serviceItems = tmpObj.serviceItems;
            this.orderParams.engageList.push(item);
          } else {
            delete item.serviceTypes;
            this.orderParams.engageList.push(item);
          }
        }
      });
    }
  }
};
</script>
<style lang="less" scoped>
* {
  overflow: auto;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.content {
  width: 100%;
  height: calc(100% - 146px - 48px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  background: #f7f7f7;
}

/* 折叠效果 */
.box {
  width: 100%;
}

.draw-enter-active,
.draw-leave-active {
  transition: all 0.3s ease;
}

.draw-enter,
.draw-leave-to {
  /* .fade-leave-active below version 2.1.8 */
  height: 0;
}

.unfolding-arrow {
  height: 32px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.unfolding-arrow > img {
  display: block;
  width: 20px;
  height: 14px;
}
.dialog {
  .info {
    padding: 10px;
    font-size: 14px;
    color: #333;
    height: 300px;
    overflow-y: scroll;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    p,
    span {
      white-space: pre-wrap;
    }
  }
}
.content {
  div {
    display: flex;
    flex-direction: column;
  }
  .card {
    background: white;
    margin-bottom: 12px;
  }
  .group {
    margin: 8px 0px 2px;
  }
  .group::after {
    border: 0;
  }
  .group:last-child {
    margin: 0;
  }
  .item {
    height: 48px;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    align-self: stretch;
    padding: 0 15px;
    .item-left {
      flex-direction: row;
    }
    .item-left-icon {
      width: 22px;
      height: 22px;
      margin-right: 8px;
    }
    .item-title {
      font-size: 16px;
      font-weight: 600;
      color: #333333;
    }
    .item-right-icon {
      width: 18px;
      height: 18px;
    }
    .item-placeholder {
      color: #ababab;
    }
    .total-premium {
      font-size: 14px;
      font-weight: 500;
    }
    .premium-value {
      flex-direction: row;
      font-size: 16px;
      font-weight: 600;
      color: #fc820e;
      s {
        color: #929292;
        font-weight: 400;
      }
    }
  }
  .info-row {
    flex: 1;
    margin: 0 18px;
    flex-direction: row;
    justify-content: space-between;
    font-size: 14px;
    line-height: 23px;
    .key-col {
      color: #333333;
    }
    .value-col {
      color: #666666;
    }
  }
  .info-row:last-child {
    margin-bottom: 20px;
  }
  .info-col {
    margin: 12px 0;
    padding: 16px;
    background: #fafafa;
    border-radius: 2px;
    justify-content: space-between;
    .key-row {
      flex: 1;
      font-size: 13px;
      // line-height: 17px;
      margin-bottom: 8px;
      color: #8e8e8e;
      flex-direction: row;
      justify-content: space-between;
      div {
        flex: 1;
        text-align: center;
      }
      div:first-child {
        flex: 1.5;
        text-align: left;
      }
      div:last-child {
        text-align: right;
      }
    }
    .value-row {
      flex: 1;
      font-size: 14px;
      line-height: 23px;
      color: #545454;
      margin-bottom: 5px;
      flex-direction: row;
      justify-content: space-between;
      div {
        flex: 1;
        width: 100%;
        text-align: center;
      }
      div:first-child {
        flex: 1.5;
        text-align: left;
      }
      div:last-child {
        text-align: right;
      }
    }
    .key-row-long,
    .value-row-long {
      div:first-child {
        flex: 3;
        text-align: left;
      }
    }
    .value-row-nocenter {
      div:first-child {
        flex: 4.5;
        text-align: left;
      }
    }
  }
  .more-info {
    height: 48px;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    .info-title {
      font-size: 12px;
      color: #4c76b5;
    }
    .arrow {
      margin-left: 8px;
    }
  }
  .engages {
    padding: 0 16px 10px;
    border-radius: 2px;
    color: #545454;
    font-size: 14px;
    .engages-type {
      font-weight: 500;
      font-size: 14px;
      background: #fafafa;
      padding: 10px 16px 8px;
      margin: 0 -16px;
      color: #8e8e8e;
    }
    .engages-item {
      margin-top: 10px;
      div {
        width: 100%;
        flex-wrap: wrap;
        overflow: hidden;
        text-overflow: initial;
        white-space: normal;
      }
      div:first-child {
        color: #333333;
      }
      div:last-child {
        color: #666666;
      }
    }
  }
  .risk-info {
    flex: 1;
    margin: 0 20px 10px;
    background: white;
  }
  .risk-info:last-child {
    margin-bottom: 0;
  }
  .risk-time-title {
    font-size: 14px;
    line-height: 20px;
    color: #333333;
  }
  .risk-time-value {
    font-size: 12px;
    line-height: 17px;
    color: #ababab;
  }
  .customer-info {
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    margin: 0 20px;
    background: #fafafa;
    border-radius: 2px;
    height: 84px;
    margin-bottom: 20px;
    .star-level {
      flex-direction: column;
      justify-content: center;
      align-items: center;
      .stars {
        flex-direction: row;
        justify-content: center;
        margin-bottom: 8px;
        .star {
          width: 20px;
          height: 20px;
        }
      }
    }
    .customer-integral {
      flex-direction: column;
      justify-content: center;
      align-items: center;
      .integral {
        font-size: 18px;
        font-weight: 500;
        line-height: 25px;
        color: #3b8afe;
      }
    }
    .customer-text {
    }
  }
}
.bottom-info {
  height: 48px;
  background: white;
  font-size: 16px;
  display: flex;
  flex-direction: row;
  .business-plan-book {
    width: 167px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #3b8afe;
  }
  .submit {
    display: flex;
    flex: 1;
    justify-content: center;
    align-items: center;
    background: linear-gradient(125deg, #2a80ff 0%, #2aa5ff 100%);
    color: white;
  }
}
.vehicle-info {
  position: absolute;
  top: 95px;
  left: 20px;
  background: transparent;
  .vehicle-license {
    font-size: 20px;
    color: black;
    font-weight: 500;
  }
  .vehicle-brand {
    font-size: 14px;
    color: #444444;
  }
}
.verifycode-input {
  border: 1px solid #eee;
  width: 200px;
  height: 30px;
  text-align: right;
  padding: 0 10px;
}
.send-verifycode-button {
  width: 70px;
  text-align: right;
}
</style>
