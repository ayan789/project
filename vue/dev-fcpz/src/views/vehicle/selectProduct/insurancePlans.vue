<!--
 * @Author: heweiwei
 * @Page: 投保方案
 * @Date: 2020-03-04 14:28:10
 * @Last Modified by: King
 * @Last Modified time: 2020-07-06 09:47:46
 * @FilePath: /src/views/vehicle/selectProduct/insurancePlans.vue
-->
<template>
  <div class="insurance-plans">
    <steps-nav-bar
      active="获取报价"
      type="vehicle"
      left
      @back="back"
      :right="
        control.quoted
          ? {
              icon: require('../../../assets/common/share.png'),
              click: share
            }
          : {
              icon: 'wap-home-o',
              click: goHome
            }
      "
    />
    <div class="tabs-container">
      <div
        v-for="(plan, index) in quote.plans"
        :key="index"
        @click="changeTabs(index)"
        :class="{
          'tab-item': true,
          'tab-active': plan.planCode == quote.planCode
        }"
      >
        {{ plan.planCode | planName }}
        <span v-show="plan.planCode == quote.planCode"></span>
      </div>
    </div>
    <div class="content">
      <div v-if="quote.plans.length">
        <insurancePlan v-model="plan" />
      </div>
      <div class="group" v-if="control.quoted">
        <van-cell class="item" is-link @click="toLastClaims">
          <span slot="title" class="item-title">上年理赔信息</span>
        </van-cell>
        <van-cell class="item" is-link @click="toFloatingCoefficient">
          <span slot="title" class="item-title">平台信息</span>
        </van-cell>
        <van-cell v-if="zhongflag" class="item" @click="heavygoodsRating">
          <span slot="title" class="item-title">重货评分</span>
        </van-cell>
        <van-cell class="item" is-link @click="fullOfferBtn">
          <span slot="title" class="item-title">整单优惠</span>
        </van-cell>
        <van-cell class="item" is-link @click="toQuotationNumber">
          <span slot="title" class="item-title">报价次数</span>
        </van-cell>
        <van-cell class="item" v-show="/y/i.test(quote.smartscore)">
          <div slot="title" class="item-title">
            车辆估值
          </div>
          <div class="item-right">{{ evalPrice }}</div>
        </van-cell>
        <van-cell class="item" v-show="/y/i.test(quote.xcarscore)">
          <div slot="title" class="item-title">
            X
            <sup>car</sup>指数
            <img
              class="item-title-icon"
              src="../../../assets/vehicle/vehicleInfo/tip.png"
              @click="showXcarTip"
            />
          </div>
          <div class="item-right">{{ xcarNum }}</div>
        </van-cell>
      </div>
    </div>
    <div class="plan-bottom">
      <div class="bottom-total">
        <div class="total-premium">{{ total | money }}</div>
        <div class="total-text">合计保费</div>
      </div>
      <div class="bottom-right-buttons">
        <div
          v-if="commission"
          class="estimated-fee"
          @click="estimatedCost = true"
        >
          {{ estimatedName }}
        </div>
        <div class="next-button" @click="next">下一步</div>
      </div>
    </div>
    <div class="floating-button" v-drag @click="preciseQuotation">
      <img src="../../../assets/vehicle/selectProduct/quotation.png" alt />
    </div>
    <!-- 预计费用弹窗 -->
    <van-popup
      v-if="commission"
      v-model="estimatedCost"
      class="popup-container"
    >
      <div class="popup-content">
        <div class="estimated-cost-content">
          <div class="content-title">预计费用详情</div>
          <div
            class="content-item"
            v-if="feeShowSwitch == '1' || feeShowSwitch == '3'"
          >
            <div class="content-item-title">C费用</div>
            <div class="list-title">
              <span></span>
              <span>不含税保费(元)</span>
              <span>收入比例(%)</span>
              <span>预计收入(元)</span>
            </div>
            <div class="list-item" v-if="plan.businessChecked == '1'">
              <span>商业险</span>
              <span>{{ plan.businessBeforeVatPremium }}</span>
              <span> {{ commercialFeecRate }}% </span>
              <span>
                {{ commercialFeecLimit }}
              </span>
            </div>
            <div class="list-item" v-if="plan.trafficChecked == '1'">
              <span>交强险</span>
              <span>{{ plan.trafficBeforeVatPremium }}</span>
              <span> {{ compulsoryFeecRate }}% </span>
              <span>
                {{ compulsoryFeecLimit }}
              </span>
            </div>
            <div class="estimated-cost-total">
              <span>C费用合计：</span>
              <span class="price">
                {{ totalCfeeLimit | money }}
              </span>
            </div>
          </div>
          <div
            class="content-item"
            v-if="
              channel.isProxy == '1' &&
                (feeShowSwitch == '2' || feeShowSwitch == '3')
            "
          >
            <div class="content-item-title">手续费</div>
            <div class="list-title">
              <span></span>
              <span>不含税保费(元)</span>
              <span>收入比例(%)</span>
              <span>预计收入(元)</span>
            </div>
            <div class="list-item" v-if="plan.businessChecked == '1'">
              <span>商业险</span>
              <span>{{ plan.businessBeforeVatPremium }}</span>
              <span> {{ commercialFeecRate1 }}% </span>
              <span>
                {{ commercialFeecLimit1 }}
              </span>
            </div>
            <div class="list-item" v-if="plan.trafficChecked == '1'">
              <span>交强险</span>
              <span>{{ plan.trafficBeforeVatPremium }}</span>
              <span> {{ compulsoryFeecRate1 }}% </span>
              <span>
                {{ compulsoryFeecLimit1 }}
              </span>
            </div>
            <div class="estimated-cost-total">
              <span>预计手续费用合计：</span>
              <span class="price">
                {{ totalCfeeLimit1 | money }}
              </span>
            </div>
          </div>
          <div class="activity" v-if="insureAwards.length">
            <div class="activity-award-row">
              <div class="activity-award">活动名称</div>
              <div class="activity-award">活动奖励</div>
            </div>
            <div
              class="activity-item"
              v-for="(activity, index) in insureAwards"
              :key="index"
            >
              <div class="activity-name">{{ activity.activityName }}</div>
              <div class="award-name">{{ activity | awardName }}</div>
            </div>
          </div>
        </div>
        <van-icon
          name="close"
          color="white"
          class="popup-close-image"
          @click="estimatedCost = false"
        />
      </div>
    </van-popup>
    <van-popup v-model="checkShow">
      <div class="check-container">
        <div
          class="check-item"
          v-for="(check, index) in checks"
          :key="index"
          v-show="check.checkFlag"
        >
          <div class="row">
            <span>续保标志</span>
            <span>{{ check.renewalFlag == "1" ? "续保" : "非续保" }}</span>
          </div>
          <div class="row">
            <span>投保单号</span>
            <span>{{ check.querySequenceNo }}</span>
          </div>
          <div class="row" v-if="check.checkCodeImage">
            <span>验证码</span>
            <img :src="'data:image/png;base64,' + check.checkCodeImage" />
          </div>
          <div class="row" v-if="check.checkCodeImage">
            <input v-model="check.checkCode" />
          </div>
          <div v-if="check.question" class="row">
            <span>{{ check.question }}</span>
          </div>
          <div v-if="check.question" class="row">
            <input v-model="check.answer" />
          </div>
        </div>
        <div class="button" @click="checkSubmit">确认</div>
      </div>
    </van-popup>
    <van-dialog
      v-model="showUWResult"
      title="核保信息"
      class="showAuditResultDialog"
    >
      <div class="info">
        <div>
          <label>核保结果类型：</label>
          <span>{{ uwTypeMap[uwResult.uwType] }}</span>
        </div>
        <div>
          <label>核保级别：</label>
          <span>{{ uwResult.uwLevel + "级" }}</span>
        </div>
        <span>
          <label>是否需要验车：</label>
          <span>
            {{ uwResult.isNeedCheck == "1" ? "是" : "否" }}
          </span>
        </span>
        <div>
          <label>规则匹配信息：</label>
        </div>
        <div>
          <div
            v-for="(val, index) in uwResult.underwriteRuleInfos"
            :key="index"
          >
            <label>{{ index + 1 }}、</label>
            <span>{{ val.ruleDescription }}</span>
          </div>
        </div>
        <div>
          <p v-if="uwResult.uwType == '1'" style="color:red;">
            👉该订单被拒保，请修改信息后重新出单。
          </p>
          <p v-else-if="uwResult.uwType == '2'" style="color:red;">
            👉该订单自核不通过，需返回修改，如仍需提交，可申请特批。
          </p>
          <p v-else-if="uwResult.uwType > 3" style="color:red;">
            👉该订单需人工核保，提交订单后请密切关注核保结果。
          </p>
        </div>
      </div>
    </van-dialog>
    <van-dialog
      v-model="showReInsured"
      title="重复投保提示"
      class="reinsured-dialog"
      @close="reinsuredClose"
      :show-confirm-button="reInsureItemHasEndTime"
      show-cancel-button
      confirm-button-text="重新报价"
      @confirm="startTimeWithReinsuredItem"
    >
      <div class="reinsured-content-tip">
        温馨提示: 点击重新报价，系统自动按照平台返回的终保日期进行重新报价。
      </div>
      <div class="reinsured-content">
        <div class="reinsured-content-tip">
          温馨提示: 请修改起保日期进行重新报价。
        </div>
        <div
          class="card"
          v-for="(reInsureItem, index) in insurancePlan.reInsureItem"
          :key="index"
        >
          <div class="cell cell-title">
            <div>
              {{
                reInsureItem.type == "traffic"
                  ? "交强险重复投保"
                  : reInsureItem.type == "business"
                  ? "商业险重复投保"
                  : ""
              }}
            </div>
          </div>
          <div class="cell">
            <div>重复投保单号</div>
            <div>{{ reInsureItem.policyNo }}</div>
          </div>
          <div class="cell">
            <div>车牌号</div>
            <div>{{ reInsureItem.licensePlateNo }}</div>
          </div>
          <div class="cell">
            <div>车架号</div>
            <div>{{ reInsureItem.vin }}</div>
          </div>
          <div class="cell">
            <div>保险公司</div>
            <div>{{ reInsureItem.insurerName }}</div>
          </div>
          <div class="cell long-info">
            <div>险种信息</div>
            <div>{{ reInsureItem.coverageItemList }}</div>
          </div>
          <div class="cell">
            <div>起保日期</div>
            <div>{{ reInsureItem.effectiveDate }}</div>
          </div>
          <div class="cell">
            <div>终保日期</div>
            <div>{{ reInsureItem.expireDate }}</div>
          </div>
          <div class="cell">
            <div>承保地区</div>
            <div>{{ reInsureItem.insurerArea }}</div>
          </div>
          <div class="cell">
            <div>重复投保公司种类</div>
            <div>{{ reInsureItem.insurerCompanyType }}</div>
          </div>
        </div>
      </div>
    </van-dialog>
  </div>
</template>
<script>
import insurancePlan from "./insurancePlan.vue";
import StepsNavBar from "@/components/stepsNavBar.vue";
import _ from "lodash";
import {
  getQuoteInfo,
  preciseQuotation,
  getAwardInfo,
  saveQuote,
  getShareCode,
  getAccidentalEstateInsurance
} from "@src/http/module/vehicle";
import { mapMutations, mapState, mapActions } from "vuex";
import { Sticky, Dialog } from "vant";
import { trackMap } from "@src/utils/index.js";
import { Plan } from "../../../store/modules/vehicle/vehicle";
export default {
  components: {
    [Sticky.name]: Sticky,
    [Dialog.name]: Dialog,
    [StepsNavBar.name]: StepsNavBar,
    insurancePlan
  },
  data() {
    return {
      requiredAge: false,
      minAge: 0,
      maxAge: 0,
      jqProductCode: "",
      syProductCode: "",
      zhongArray: [],
      newarray: [],
      zhongflag: false,
      vehicleInsdStatDTO: "",
      codeSuffixValue: "A",
      productCodeValue: "",
      detailed: "",
      evalPrice: "",
      active: 0,
      estimatedCost: false,
      checkShow: false,
      checks: [],
      // 核保结果状态
      uwTypeMap: {
        "1": "拒保",
        "2": "返回录单员修改",
        "4": "人工核保",
        "5": "人工核保",
        "6": "人工核保",
        "7": "人工核保"
      },
      // 核保结果信息弹框
      showUWResult: false,
      uwResult: {
        uwType: "3",
        uwLevel: "2",
        isNeedCheck: "1",
        underwriteRuleInfos: []
      },
      //重复投保提示
      showReInsured: false,
      //debug数据
      reInsureItem: [
        {
          type: "traffic",
          applicantionId: "",
          billDate: "",
          coverageItemList: "",
          effectiveDate: "",
          engineNo: "",
          expireDate: "2020-06-10 00:00:00",
          insurerArea: "",
          insurerCode: "",
          insurerCompanyType: "",
          licensePlateColorCode: "",
          licensePlateNo: "",
          licensePlateType: "",
          policyNo: "",
          vin: ""
        },
        {
          type: "business",
          applicantionId: "",
          billDate: "",
          coverageItemList:
            "车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险",
          effectiveDate: "",
          engineNo: "",
          expireDate: "2020-06-17 00:00:00",
          insurerArea: "",
          insurerCode: "",
          insurerCompanyType: "",
          licensePlateColorCode: "",
          licensePlateNo: "",
          licensePlateType: "",
          policyNo: "",
          vin: ""
        },
        {
          type: "business",
          applicantionId: "",
          billDate: "",
          coverageItemList:
            "车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险",
          effectiveDate: "",
          engineNo: "",
          expireDate: "",
          insurerArea: "",
          insurerCode: "",
          insurerCompanyType: "",
          licensePlateColorCode: "",
          licensePlateNo: "",
          licensePlateType: "",
          policyNo: "",
          vin: ""
        }
      ],
      areaCode: ""
    };
  },
  // beforeRouteLeave(to, from, next) {next();},
  activated() {
    let userInfo = this.localStorage.get("userInfo", {});
    let comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
    if (this.quote.plans.length == 0 || !this.control.vehicleQuote) {
      this.init();
    }
    this.control.vehicleQuote = true;
  },
  filters: {
    planName(planCode) {
      //s:简约版 e:经济版 l:豪华版
      switch (planCode) {
        case "s":
          return "简约版";
        case "e":
          return "经济版";
        case "l":
          return "豪华版";
        default:
          return "隐藏版";
      }
    },
    money(value) {
      if (isNaN(Number(value))) return "￥0.00";
      return "￥" + Number.parseFloat(value).toLocaleString();
    },
    awardName(activity) {
      /**
       * 活动奖励
       * awardForm字段
       * 1 红包
       * 2 礼品
       * 3 费率
       * 4 固定奖金
       * 5 礼品
       * 如果为1  amount为奖励金额 awardCount为奖励数量
       * 如果为2、5  awardCount为奖励数量 awardName为奖励名称
       * 如果为3、4  amount为奖励金额
       */
      let awardForm = _.result(activity, "awardForm", "");
      let amount = _.result(activity, "amount", "");
      let awardCount = _.result(activity, "awardCount", "");
      let awardName = _.result(activity, "awardName", "");
      switch (String(awardForm)) {
        case "1": {
          return (awardCount > 1 ? awardCount + "个" : "") + amount + "元红包";
        }
        case "2":
        case "5": {
          return (awardCount > 1 ? awardCount + "个" : "") + awardName;
        }
        case "3":
        case "4": {
          return (awardCount > 1 ? awardCount + "份" : "") + amount + "元奖金";
        }
        default:
          return awardName;
      }
    }
  },
  watch: {
    quoted(v) {
      this.control.quoted = v;
    },
    $route(to, from) {
      if (to.name == "insurancePlans" && from.name == "vehicle") {
        console.warn("from : ", from.name, ", to : ", to.name);
        this.initWealthAccInfo();
      }
    }
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      car: ({ car }) => car,
      carOwner: ({ carOwner }) => carOwner,
      policyHolder: ({ policyHolder }) => policyHolder,
      recognizee: ({ recognizee }) => recognizee,
      channel: ({ projectChannel }) => projectChannel,
      quote: ({ quoteInfo }) => quoteInfo,
      insurancePlan: ({ insurancePlan }) => insurancePlan,
      historyPolicy: ({ historyPolicy }) => historyPolicy,
      insureAwards: ({ insureAwards }) => insureAwards,
      control: ({ control }) => control,
      vehicleType: ({ vehicleType }) => vehicleType
    }),
    xcarNum() {
      return _.result(this.quote, "floating.xcarNum", "");
    },
    plan: {
      get: function() {
        return (
          this.quote.plans.find(
            plan => plan.planCode === this.quote.planCode
          ) ||
          this.quote.plans[0] ||
          {}
        );
      },
      set: function(plan) {
        this.$set(this.quote.plans, this.active, plan);
      }
    },
    reInsureItemHasEndTime() {
      return (
        this.insurancePlan.reInsureItem &&
        !!this.insurancePlan.reInsureItem.find(item => !!item.expireDate)
      );
    },
    commission() {
      if (this.quote.commission) {
        if (this.quote.commission.feeShowSwitch) {
          return this.quote.commission.feeShowSwitch > 0;
        }
      }
      return false;
    },
    feeShowSwitch() {
      return _.result(this.quote, "commission.feeShowSwitch", "");
    },
    commercialFeecRate() {
      return _.result(this.quote, "commission.commercialFeecRate", "0");
    },
    commercialFeecRate1() {
      return _.result(this.quote, "commission.commercialFeec1Rate", "0");
    },
    commercialFeecLimit() {
      return _.result(this.quote, "commission.commercialFeecLimit", "0");
    },
    commercialFeecLimit1() {
      return _.result(this.quote, "commission.commercialFeec1Limit", "0");
    },
    compulsoryFeecRate() {
      return _.result(this.quote, "commission.compulsoryFeecRate", "0");
    },
    compulsoryFeecRate1() {
      return _.result(this.quote, "commission.compulsoryFeec1Rate", "0");
    },
    compulsoryFeecLimit() {
      return _.result(this.quote, "commission.compulsoryFeecLimit", "0");
    },
    compulsoryFeecLimit1() {
      return _.result(this.quote, "commission.compulsoryFeec1Limit", "0");
    },
    totalCfeeLimit() {
      return _.result(this.quote, "commission.totalCfeeLimit", "0");
    },
    totalCfeeLimit1() {
      return _.result(this.quote, "commission.totalC1feeLimit", "0");
    },
    total() {
      let total = 0;
      let plan = this.insurancePlan || {};
      if (plan.trafficChecked == "1") {
        if (Number(plan.trafficDuePremium)) {
          total += plan.trafficDuePremium;
        }
        if (Number(plan.tax.sumPayment)) {
          total += plan.tax.sumPayment;
        }
      }
      if (plan.businessChecked == "1") {
        if (Number(plan.businessDuePremium)) {
          total += plan.businessDuePremium;
        }
      }
      if (plan.wealthAccChecked == "1") {
        if (Number(plan.wealthAccPremium)) {
          total += plan.wealthAccPremium;
        }
      }
      return Number(total).toFixed(2);
    },
    quoted() {
      if (this.insurancePlan) {
        return JSON.stringify(this.insurancePlan) == JSON.stringify(this.plan);
      }
      return false;
    },
    estimatedName() {
      if (this.$isAgent()) {
        return "预计佣金";
      } else {
        return "预计费用";
      }
    }
  },
  methods: {
    ...mapMutations("vehicle", [
      "initWealthAccInfo",
      "setQuoteInfo",
      "setUwResult",
      "setInsurancePlan",
      "setInsureAwards"
    ]),
    ...mapActions("vehicle", ["calculateActualValue", "getTaxConfig"]),
    async init() {
      let params = { orderId: this.orderId };
      try {
        let res = await getQuoteInfo(params);
        if (res.code === "1" && res.data) {
          console.log(res.data.plans, "1111");
          let plans = res.data.plans || [];
          let planIndex = plans.findIndex(
            plan => plan.planCode == res.data.planCode
          );
          // 历史保单信息 覆盖
          if (this.historyPolicy) {
            if (planIndex != -1) {
              plans[planIndex] = Object.assign(
                {},
                plans[planIndex],
                this.historyPolicy,
                {
                  planCode: plans[planIndex].planCode
                }
              );
            } else {
              plans[0] = Object.assign({}, plans[0], this.historyPolicy, {
                planCode: plans[0].planCode
              });
            }
          }
          // 无匹配方案 无历史保单信息 提供一个空白方案
          if (plans.length == 0) {
            plans[0] = Object.assign({}, Plan, {
              planCode: "s"
            });
          }
          let plan = plans.length
            ? plans.find(plan => plan.planCode == res.data.planCode) || plans[0]
            : {};
          this.setQuoteInfo(
            Object.assign({}, res.data, {
              plans,
              planCode: res.data.planCode || plan.planCode || "",
              reset: true
            })
          );
          this.getTaxConfig();
          let index = plans.findIndex(
            plan => plan.planCode == res.data.planCode
          );
          this.changeTabs(index == -1 ? 0 : index);
        } else {
          this.$dialog.alert({ message: res.msg });
          // if (this.quote.plans.length === 0) {
          //   this.setQuoteInfo({
          //     ...this.quote,
          //     planCode: "s",
          //     plans: [Object.assign({}, Plan, { planCode: "s" })]
          //   });
          // }
        }
        let wealth = await getAccidentalEstateInsurance({
          orderId: this.orderId,
          comCode: this.localStorage.get("userInfo", {}).comCode,
          userCode: this.localStorage.get("userInfo", {}).userCode,
          maxSeatCount: this.car.seatCount
        });
        if (wealth.code === "1") {
          let formulas = wealth.data;
          let newPlans = this.quote.plans.map(plan => {
            let formulaGroupIndex = formulas.findIndex(
              i => i.formulaGroupCode === plan.wealthAccFormulaGroupCode
            );
            if (formulaGroupIndex != -1) {
              let formulaGroup = formulas[formulaGroupIndex];
              let solutions = formulaGroup.jointSalesSolutionVOS;
              let solution = solutions.find(
                solution => solution.formulaCode == plan.wealthAccFormulaCode
              );
              if (solution) {
                if (
                  solution.hasOwnProperty("insuredAgeLower") &&
                  solution.insuredAgeLower
                ) {
                  this.requiredAge = true;
                  this.minAge = solution.insuredAgeLower;
                  this.maxAge = solution.insuredAgeUpper;
                } else {
                  this.requiredAge = false;
                  this.minAge = 0;
                  this.maxAge = 0;
                }
              } else {
                plan.wealthAccChecked = "0";
                plan.wealthAccFormulaGroupCode = "";
                plan.wealthAccFormulaGroupName = "";
                plan.wealthAccFormulaCode = "";
                plan.wealthAccFormulaName = "";
                plan.wealthAccBuyNum = "";
                plan.wealthAccPremium = "";
              }
            } else {
              plan.wealthAccChecked = "0";
              plan.wealthAccFormulaGroupCode = "";
              plan.wealthAccFormulaGroupName = "";
              plan.wealthAccFormulaCode = "";
              plan.wealthAccFormulaName = "";
              plan.wealthAccBuyNum = "";
              plan.wealthAccPremium = "";
            }
            return plan;
          });
          this.quote.plans.slice(0, this.quote.plans.length, ...newPlans);
          console.log(this.quote.plans, this.plan);
        } else {
          this.$dialog.alert({ message: wealth.msg });
        }
      } catch (error) {}
    },
    back() {
      this.$router.push({ name: "vehicle" });
    },
    changeTabs(index) {
      this.active = index;
      this.quote.planCode = this.quote.plans[index].planCode;
      this.plan = this.quote.plans[index];
      // this.setQuoteInfo(Object.assign({}, this.quote));
    },
    goHome() {
      this.$router.push({ path: "/" });
    },
    share() {
      // 修改为图片分项
      // let paramsObj = {
      //   sharePlatform: ["0", "1", "2", "4"],
      //   shareType: "img"
      // };
      // this.$native.shareLongShot(paramsObj, data => {
      //   console.log(data, "截图分享");
      // });
      let userInfo = JSON.parse(localStorage.getItem("userInfo"));
      let { userCode, userName, comCode, staffCode } = userInfo;
      this.plan.businessProductCode = this.syProductCode;
      this.plan.trafficProductCode = this.jqProductCode;
      saveQuote({
        ...this.quote,
        plans: [{ ...this.plan, ...this.insurancePlan }],
        comCode,
        staffCode,
        userCode,
        userName,
        orderId: this.orderId
      }).then(
        res => {
          if (res.code === "1") {
            this.$router.push({
              path: "/quoteInfoShare",
              query: { orderId: this.orderId }
            });
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    toLastClaims() {
      //console.log(this.quote.quoteCount.sumQuoteCount);
      const {
        quoteCount = {},
        businessLastClaims = [],
        trafficLastClaims = []
      } = this.quote;
      // const { sumQuoteCount } = quoteCount;
      // if (!sumQuoteCount) return this.$toast.fail("尚无上年理赔信息");
      const claims = [
        ...(businessLastClaims || []),
        ...(trafficLastClaims || [])
      ];
      if (!claims.length) return this.$toast.fail("尚无上年理赔信息");
      //埋点
      trackMap("c_car_pro_last_year_settle_info", "车险产品上年理赔信息", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "车险出单"
      });
      this.$router.push({
        name: "lastClaim",
        params: { claims }
      });
    },
    toFloatingCoefficient() {
      //埋点
      trackMap("c_car_pro_float_rate", "车险产品商业险浮动系数", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "车险出单"
      });
      this.$router.push({ name: "floatingCoefficient" });
    },
    //  整单优惠
    fullOfferBtn() {
      this.$router.push({
        name: "fullpreferential",
        params: { detailed: this.detailed }
      });
    },
    // 重货评分
    heavygoodsRating() {
      this.$router.push({ name: "heavygoodsRating", query: this.newarray });
    },
    toQuotationNumber() {
      //埋点
      trackMap("c_car_pro_offer_frequency", "车险产品", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "车险出单"
      });
      this.$router.push({ name: "quotationNumber" });
    },
    showXcarTip() {
      this.$dialog.alert({
        message:
          "X<sup>car</sup>指数是根据定价模型得到的车险标准保费下的整单预期赔付率，是对业务质量优劣的区分，指数越高表明业务质量可能越好。"
      });
    },
    checkSubmit() {
      try {
        this.checks.forEach(check => {
          if (check.checkCodeImage) {
            if (!check.checkCode) {
              throw "验证码未填写";
            }
          }
          if (check.question) {
            if (!check.answer) {
              throw "答案未填写";
            }
          }
        });
        this.checkShow = false;
        this.preciseQuotation();
      } catch (error) {
        this.$toast(error);
      }
    },
    preciseQuotation() {
      // if (this.car.carKindCode == "31") {
      //   Dialog.alert({
      //     message: "营业挂车和非营业挂车不能投保"
      //   }).then(() => {
      //     // on close
      //   });
      //   return;
      // }
      if (this.plan.trafficChecked != "1" && this.plan.businessChecked != "1") {
        return this.$toast("商业险和交强险必须选择至少一种");
      }
      if (!this.quote.plans.length || !this.validate()) {
        return;
      }
      if (this.plan.trafficChecked == "1" && this.areaCode == "1301") {
        if (!this.carOwner.areaDetail || !this.carOwner.areaCode) {
          Dialog.alert({
            title: "提示",
            message: "河北地区承保交强险，车主行政区域必填，请返回修改"
          }).then(() => {
            this.back();
          });
          return;
        }
      }
      let effectiveDate = ""; //起保时间
      if (this.plan.businessChecked == "1") {
        effectiveDate = this.plan.businessEffectiveStartDate.substr(0, 10);
      } else if (this.plan.trafficChecked == "1") {
        effectiveDate = this.plan.trafficEffectiveStartDate.substr(0, 10);
      }
      this.calculateActualValue({
        effectiveDate,
        callback: () => {
          let plan = { ...this.plan };
          let quoteInfo = { ...this.quote };
          delete quoteInfo.plans;
          if (this.plan.trafficChecked != "1") {
            plan.trafficDuePremium = "";
            this.plan.trafficDuePremium = "";
          }
          if (this.plan.businessChecked != "1") {
            plan.businessDuePremium = "";
            this.plan.businessDuePremium = "";
          }
          if (this.plan.wealthAccChecked != "1") {
            // plan.wealthAccPremium = "";
            // this.plan.wealthAccPremium = "";
            this.plan.wealthAccPremium = "";
          } else {
            if (!plan.wealthAccBuyNum) {
              return this.$dialog.alert({ message: "联合销售购买份数未选择" });
            }
            if (
              !plan.wealthAccFormulaGroupCode ||
              !plan.wealthAccFormulaCode ||
              !plan.wealthAccRiskCode
            ) {
              return this.$dialog.alert({ message: "请选择联合销售方案" });
            }
          }
          //
          let checksCopy = JSON.parse(JSON.stringify(this.checks));
          checksCopy.forEach(check => {
            delete check.checkCodeImage;
            delete check.checkCode;
          });
          let wealthAccInsurant = JSON.parse(
            JSON.stringify(this.plan.wealthAccInsurant)
          );
          if (
            this.plan.wealthAccFormulaGroupName &&
            this.plan.wealthAccFormulaGroupName.includes("驾乘无忧")
          ) {
            wealthAccInsurant = {};
          }
          // console.log(this.plan.businessRisks, "何韦韦===");
          // if (this.vehicleType != "1") {
          //   for (let i = 0; i < this.plan.businessRisks.length; i++) {
          //     if (this.plan.businessRisks[i].kindCode == "C101017") {
          //       return this.$dialog.alert({ message: "非家用车不可投保三责险法定节假日限额翻倍险" });
          //     }
          //   }
          // }

          let params = {
            ...quoteInfo,
            insurancePlan: {
              ...plan,
              reInsureItem: []
              // NOTE 后端处理
              // wealthAccInsurant
            },
            owner: { ...this.carOwner },
            // relationPeopleList: [{ ...this.policyHolder }, { ...this.recognizee }],
            channel: { ...this.channel },
            vehicleCheck: { ...this.car, vinNo: this.car.frameNo },
            orderId: this.orderId,
            checks: checksCopy,
            comCode: this.localStorage.get("userInfo", {}).comCode
          };
          //  拖拉机费率
          if (this.car.rateClassification) {
            params.regionPremRateCate = this.car.rateClassification;
          }
          //  产品代码
          if (this.vehicleType != "1") {
            // params.vehicleCheck.exhaustScale =
            //   params.vehicleCheck.exhaustScale / 1000;
            //  写死
            // params.vehicleCheck.exhaustScale = 0.025;
          }
          this.codeSuffixValue = "A";
          if (this.vehicleType != "1") {
            if (this.vehicleType == "2") {
              this.codeSuffixValue = "A";
            }
            if (this.vehicleType == "3") {
              this.codeSuffixValue = "C";
            }
            if (this.vehicleType == "4") {
              this.codeSuffixValue = "B";
            }
          }
          if (this.plan.trafficChecked == "1") {
            this.jqProductCode = "DF" + this.codeSuffixValue;
            params.jqProductCode = this.jqProductCode;
          }
          if (this.plan.businessChecked == "1") {
            this.syProductCode = "DE" + this.codeSuffixValue;
            params.syProductCode = this.syProductCode;
          }
          preciseQuotation(params).then(
            res => {
              // console.log(this.newarray, "何韦韦====？");
              if (res.code === "1") {
                this.detailed = res.data;
                // if (this.detailed.vehicleInsdStatDTO.length) {

                // }
                this.zhongArray = _.result(
                  this.detailed,
                  "vehicleInsdStatDTO",
                  []
                );
                if(this.zhongArray){
                  this.newarray = this.zhongArray.filter((item, index, arr) => {
                    return item.extensionMap.statisticSource == "carTruckMap";
                  });
                }
                if (
                  this.vehicleType != "1" &&
                  this.zhongArray.length &&
                  this.newarray.length
                ) {
                  this.zhongflag = true;
                } else {
                  this.zhongflag = false;
                }
                let {
                  trafficLastClaims,
                  businessLastClaims,
                  floating,
                  quoteCount,
                  commission,
                  insurancePlan,
                  checks,
                  uwResult,
                  ccicVehicleScore, //C指数
                  smartscore, //是否显示C指数
                  smartscoredetails, //是否显示风险因子影响度列表
                  xcarscore, //是否显示Xcar指数
                  trafficReinsureInfo, //交强险重复投保信息
                  trafficErrorMessage,
                  businessErrorMessage
                } = res.data;
                let { reInsureItem } = insurancePlan || {};
                if (this.checks.length > 0) {
                  this.checks = [];
                }
                this.evalPrice = res.data.evalPrice; //车辆估值
                if (Array.isArray(checks) && checks.length) {
                  this.checks = checks.map(check => {
                    return {
                      ...check,
                      checkCodeImage: check.checkCode,
                      checkCode: ""
                    };
                  });
                  this.checkShow = true;
                  return;
                }
                this.setQuoteInfo({
                  ...this.quote,
                  trafficLastClaims,
                  businessLastClaims,
                  floating,
                  quoteCount,
                  commission,
                  plans: null,
                  ccicVehicleScore, //C指数
                  smartscore, //是否显示C指数
                  smartscoredetails, //是否显示风险因子影响度列表
                  xcarscore //是否显示Xcar指数
                });
                if (insurancePlan.trafficIsEffective == "Y") {
                  insurancePlan.trafficIsEffective = "1";
                } else if (insurancePlan.trafficIsEffective == "N") {
                  insurancePlan.trafficIsEffective = "0";
                }
                if (insurancePlan.businessIsEffective == "Y") {
                  insurancePlan.businessIsEffective = "1";
                } else if (insurancePlan.businessIsEffective == "N") {
                  insurancePlan.businessIsEffective = "0";
                }
                let taxMixin = {};
                if (insurancePlan.tax) {
                  taxMixin.adjustTaxValue =
                    insurancePlan.tax.adjustTaxValue < 1
                      ? insurancePlan.tax.adjustTaxValue * 100
                      : insurancePlan.tax.adjustTaxValue;
                }
                reInsureItem = reInsureItem || [];
                reInsureItem = reInsureItem.map(item => ({
                  ...item,
                  type: "business"
                }));
                if (
                  trafficReinsureInfo &&
                  typeof trafficReinsureInfo == "object"
                ) {
                  const trafficReinsuredItem = {
                    type: "traffic",
                    applicantionId: "",
                    billDate: "",
                    coverageItemList: "",
                    effectiveDate: trafficReinsureInfo.effectiveStartDate,
                    engineNo: trafficReinsureInfo.engineNo,
                    expireDate: trafficReinsureInfo.effectiveEndDate,
                    insurerArea: trafficReinsureInfo.area,
                    insurerCode: "",
                    insurerCompanyType: "",
                    licensePlateColorCode: "",
                    licensePlateNo: trafficReinsureInfo.licenseNo,
                    licensePlateType: trafficReinsureInfo.licenseType,
                    policyNo: trafficReinsureInfo.policyNo,
                    vin: trafficReinsureInfo.frameNo
                  };
                  reInsureItem.unshift(trafficReinsuredItem);
                }
                this.plan = Object.assign(
                  {},
                  this.plan,
                  this.$utils.removeEmpty(insurancePlan),
                  {
                    tax: Object.assign(
                      this.plan.tax,
                      this.$utils.removeEmpty(insurancePlan.tax),
                      taxMixin
                    ),
                    reInsureItem,
                    trafficErrorMessage,
                    businessErrorMessage
                  }
                );
                this.setQuoteInfo({
                  ...this.quote,
                  trafficLastClaims,
                  businessLastClaims,
                  floating,
                  quoteCount,
                  commission,
                  plans: null,
                  ccicVehicleScore, //C指数
                  smartscore, //是否显示C指数
                  smartscoredetails, //是否显示风险因子影响度列表
                  xcarscore //是否显示Xcar指数
                });

                this.setInsurancePlan(Object.assign({}, this.plan));
                this.insurancePlan.totalPremium = this.total;
                this.plan.totalPremium = this.total;
                this.setUwResult(uwResult);
                this.uwResult = uwResult;
                if (this.commission) {
                  //获取报价活动奖励信息
                  let effectiveStartDate, effectiveEndDate;
                  let productList = [];
                  let ProductItem = {
                    afterTaxPremium: "", //实收不含税保费
                    categoryCode: "", //险类代码 11000-商业车险 18000-交强险 72000-意外伤害保险
                    productCode: "" //产品代码：交强DFX、商业DEX、财意riskcode
                  };
                  if (this.insurancePlan.businessChecked == "1") {
                    effectiveStartDate = this.insurancePlan
                      .businessEffectiveStartDate;
                    effectiveEndDate = this.insurancePlan
                      .businessEffectiveEndDate;
                  } else {
                    effectiveStartDate = this.insurancePlan
                      .trafficEffectiveStartDate;
                    effectiveEndDate = this.insurancePlan
                      .trafficEffectiveEndDate;
                  }
                  let codeSuffix = "A";
                  if (this.car.carKindCode.charAt(0) == "4") {
                    codeSuffix = "B";
                  } else if (this.car.carKindCode.charAt(0) == "5") {
                    codeSuffix = "C";
                  }
                  if (this.vehicleType == "2") {
                    codeSuffix = "A";
                  }
                  if (this.vehicleType == "3") {
                    codeSuffix = "A";
                  }
                  if (this.vehicleType == "2") {
                    codeSuffix = "C";
                  }
                  if (this.vehicleType == "2") {
                    codeSuffix = "B";
                  }
                  if (this.insurancePlan.trafficChecked == "1") {
                    productList.push({
                      afterTaxPremium: this.insurancePlan.trafficDuePremium,
                      categoryCode: "18000",
                      productCode: "DF" + codeSuffix
                    });
                  }
                  if (this.insurancePlan.businessChecked == "1") {
                    productList.push({
                      afterTaxPremium: this.insurancePlan.businessDuePremium,
                      categoryCode: "11000",
                      productCode: "DE" + codeSuffix
                    });
                  }
                  if (this.insurancePlan.wealthAccChecked == "1") {
                    productList.push({
                      afterTaxPremium: this.insurancePlan.wealthAccPremium,
                      categoryCode: "72000",
                      productCode: this.insurancePlan.wealthAccRiskCode
                    });
                  }
                  getAwardInfo({
                    effectiveStartDate,
                    effectiveEndDate,
                    productList,
                    reqType: "1",
                    orderId: this.orderId
                  })
                    .then(
                      res => {
                        if (res.code == "1") {
                          this.setInsureAwards(res.data);
                        }
                      },
                      error => {}
                    )
                    .finally(() => {
                      this.preciseQuotationEnd();
                    });
                } else {
                  this.preciseQuotationEnd();
                }
              } else {
                this.$dialog.alert({ message: res.msg }).then(() => {
                  this.checks = [];
                });
              }
            },
            error => {
              this.$dialog.alert({ message: "请稍后再试" });
            }
          );
        }
      });
    },
    preciseQuotationEnd() {
      const next = () => {
        if (
          Array.isArray(this.insurancePlan.reInsureItem) &&
          this.insurancePlan.reInsureItem.length
        ) {
          this.showReInsured = true;
        } else if (this.uwResult && this.uwResult.uwType != "3") {
          this.showUWResult = true;
        }
      };
      if (this.plan.trafficChecked == "1" && this.plan.trafficErrorMessage) {
        this.$dialog
          .alert({
            title: "交强险投保报错,请修改后重新报价",
            message: this.plan.trafficErrorMessage
          })
          .then(next);
      } else if (
        this.plan.businessChecked == "1" &&
        this.plan.businessErrorMessage
      ) {
        this.$dialog
          .alert({
            title: "商业险投保报错,请修改后重新报价",
            message: this.plan.businessErrorMessage
          })
          .then(next);
      } else {
        next();
      }
    },
    reinsuredClose() {
      if (this.uwResult && this.uwResult.uwType != "3") {
        this.showUWResult = true;
      }
    },
    startTimeWithReinsuredItem() {
      const reInsureItem = this.insurancePlan.reInsureItem;
      if (Array.isArray(reInsureItem) && reInsureItem.length) {
        //TODO: 重复发投保时间处理
        //effectiveDate   起保日期
        //expireDate      终保日期
        const trafficReInsureItem = reInsureItem.filter(
          item => item.type == "traffic"
        );
        const businessReInsureItem = reInsureItem.filter(
          item => item.type == "business"
        );
        trafficReInsureItem.map(item => {
          if (item.expireDate) {
            const now = new Date().format("yyyy-MM-dd");
            const itemEndDate = item.expireDate.substr(0, 10);
            if (now <= itemEndDate) {
              this.plan.trafficIsEffective = "0";
              const startDate = new Date(itemEndDate.replace(/-/g, "/"));
              this.plan.trafficEffectiveStartDate = startDate.format(
                "yyyy-MM-dd 00:00:00"
              );
              const endDate = new Date(startDate);
              endDate.setFullYear(startDate.getFullYear() + 1);
              endDate.setDate(startDate.getDate() - 1);
              this.plan.trafficEffectiveEndDate = endDate.format(
                "yyyy-MM-dd 23:59:59"
              );
            }
          }
        });
        businessReInsureItem.map(item => {
          if (item.expireDate) {
            const now = new Date().format("yyyy-MM-dd");
            const itemEndDate = item.expireDate.substr(0, 10);
            if (now <= itemEndDate) {
              this.plan.businessIsEffective = "0";
              const startDate = new Date(itemEndDate.replace(/-/g, "/"));
              this.plan.businessEffectiveStartDate = startDate.format(
                "yyyy-MM-dd 00:00:00"
              );
              const endDate = new Date(startDate);
              endDate.setFullYear(startDate.getFullYear() + 1);
              endDate.setDate(startDate.getDate() - 1);
              this.plan.businessEffectiveEndDate = endDate.format(
                "yyyy-MM-dd 23:59:59"
              );
            }
          }
        });
      }
      this.reinsuredClose();
      this.preciseQuotation();
    },
    saveQuote() {
      let userInfo = JSON.parse(localStorage.getItem("userInfo"));
      let { userCode, userName, comCode, staffCode, token } = userInfo;
      // if (this.zhongflag) {
      this.vehicleInsdStatDTO = this.zhongArray;
      // }
      console.log(userInfo, "userInfo");
      console.log(this.vehicleInsdStatDTO, "this.vehicleInsdStatDTO");
      console.log(this.plan, "this.plan ==1");
      console.log(this.insurancePlan, "this.insurancePlan ==1");
      this.plan.businessProductCode = this.syProductCode;
      this.plan.trafficProductCode = this.jqProductCode;
      let plan = { ...this.plan, ...this.insurancePlan };
      let reInsureItem = plan.reInsureItem;
      reInsureItem = reInsureItem.filter(item => item.type == "business");
      plan.reInsureItem = reInsureItem;
      saveQuote({
        ...this.quote,
        plans: [plan],
        comCode,
        staffCode,
        userCode,
        userName,
        orderId: this.orderId,
        vehicleInsdStatDTO: this.vehicleInsdStatDTO,
        token
      }).then(
        res => {
          if (res.code === "1") {
            this.$router.push({ name: "insureInformation" });
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    async next() {
      //未报价, 不可操作;
      if (!this.control.quoted)
        return this.$toast.fail("请点击报价按钮,重新报价");

      if (this.plan.trafficChecked == "1" && this.plan.trafficErrorMessage) {
        return this.$dialog.alert({
          title: "交强险投保报错,请修改后重新报价",
          message: this.plan.trafficErrorMessage
        });
      }
      if (this.plan.businessChecked == "1" && this.plan.businessErrorMessage) {
        return this.$dialog.alert({
          title: "商业险投保报错,请修改后重新报价",
          message: this.plan.businessErrorMessage
        });
      }
      trackMap("c_car_pro_next", "车险产品下一步", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "车险出单"
      });
      this.saveQuote();
    },
    validate() {
      const {
        trafficChecked,
        businessChecked,
        wealthAccChecked,
        businessRisks,
        tax,
        wealthAccInsurant,
        wealthAccChannel
      } = this.plan;
      let props = {};
      if (trafficChecked == "1") {
        /** 交强险校验
          trafficEffectiveEndDate: "", //交强险终保日期
          trafficEffectiveStartDate: "", //交强险起保日期
          trafficIsEffective: "", //交强险是否即时生效(y:是 n:否)
          trafficPremium: "", //交强险保费
         */
        // const now = new Date().format("yyyy-MM-dd");
        // const startDate = this.plan.trafficEffectiveStartDate.substr(0, 10);
        // if (this.plan.trafficIsEffective == "1") {
        //   if (now != startDate) {
        //     this.$toast.fail("交强险勾选立即投保时, 开始时间必须是当天");
        //     return false;
        //   }
        // } else {
        //   if (now >= startDate) {
        //     this.$toast.fail("交强险未勾选立即投保时, 开始时间必须大于当天");
        //     return false;
        //   }
        // }
        props = Object.assign(props, {
          trafficEffectiveStartDate: { required: "交强险起保日期" },
          trafficEffectiveEndDate: { required: "交强险终保日期" },
          "tax.taxFlag": { required: "纳税类型" },
          // "tax.taxpayerName": { required: "纳税人名称" },
          "tax.taxpayerNo": { required: "纳税人识别号" }
          // trafficIsEffective: { required: "交强险是否即时生效" }
        });
        if (tax.taxFlag == "C") {
          //减税
          // props.adjustRate = { required: "减免税比例" };
          props["tax.adjustTaxValue"] = { required: "减免税金额" };
          props["tax.deductiondueCode"] = { required: "减免税原因" };
          props["tax.deductiondueType"] = { required: "减免税方案" };
          // props["tax.taxCertificateNo"] = { required: "减免税凭证号" };
          props["tax.taxDepartment"] = { required: "税务机关名称" };
        }
        if (tax.taxFlag == "P") {
          //完税
          props["tax.taxDocumentDate"] = { required: "完税凭证填发日期" };
          props["tax.taxCertificateNo"] = { required: "完税凭证号" };
          props["tax.taxDepartment"] = { required: "凭证机关名称" };
          // props["tax.taxPaidareaCode"] = { required: "开具完税凭证地区代码" };
        }
        if (tax.taxFlag == "E") {
          //免税
          props["tax.deductiondueCode"] = { required: "减免税原因" };
          // props["tax.deductiondueType"] = { required: "减免税方案" };
          // props["tax.taxCertificateNo"] = { required: "减免税凭证号" };
          props["tax.taxDepartment"] = { required: "税务机关名称" };
        }
        if (this.control.quoted) {
          // props.trafficDuePremium = { required: "交强险保费" };
        }
      }
      if (businessChecked == "1") {
        /** 商业险校验
          businessEffectiveEndDate: "", //商业险终保日期
          businessEffectiveStartDate: "", //商业险起保日期
          businessIsEffective: "", //商业险是否即时生效(y:是 n:否)
          businessExpectDiscount: "", //商业险期望优惠折扣
          businessRisks: [], //商业险险别明细列表[...]
          newDevices: [], //新增设备信息[{...}]
         */
        // const now = new Date().format("yyyy-MM-dd");
        // const startDate = this.plan.businessEffectiveStartDate.substr(0, 10);
        // console.log(now, startDate);
        // if (this.plan.businessIsEffective == "1") {
        //   if (now != startDate) {
        //     this.$toast.fail("商业险勾选立即投保时, 开始时间必须是当天");
        //     return false;
        //   }
        // } else {
        //   if (now >= startDate) {
        //     this.$toast.fail("商业险未勾选立即投保时, 开始时间必须大于当天");
        //     return false;
        //   }
        // }
        props = Object.assign(props, {
          businessEffectiveStartDate: { required: "商业险起保日期" },
          businessEffectiveEndDate: { required: "商业险终保日期" },
          // businessIsEffective: { required: "商业险是否即时生效" },
          // businessExpectDiscount: { required: "商业险期望优惠折扣" },
          businessRisks: { required: "商业险险别", array: "未选择" }
        });
        if (businessRisks.find(risk => risk.kindName === "新增加设备损失险")) {
          props.newDevices = { array: "新增加设备损失险未添加新设备" };
        }
      }
      if (wealthAccChecked == "1") {
        /** 联合销售校验 */
        props = Object.assign(props, {
          wealthAccRiskCode: { required: "联合销售险种代码" },
          wealthAccFormulaGroupCode: { required: "联合销售方案组代码" },
          wealthAccFormulaCode: { required: "联合销售方案代码" },
          wealthAccBuyNum: { required: "联合销售购买份数" }
        });
        if (
          this.plan.wealthAccFormulaGroupName &&
          !this.plan.wealthAccFormulaGroupName.includes("驾乘无忧")
        ) {
          props["wealthAccInsurant.identityNo"] = {
            required: "联合销售被保人证件号码"
          };
          props["wealthAccInsurant.identityType"] = {
            required: "联合销售被保人证件类型"
          };
          props["wealthAccInsurant.insurantName"] = {
            required: "联合销售被保人姓名"
          };
          if (_.result(wealthAccInsurant, "identityType", "") == "111") {
            props["wealthAccInsurant.identityNo"].idcard = "不符合规则";
          }
          if (_.result(wealthAccInsurant, "identityType", "") == "553") {
            props["wealthAccInsurant.identityNo"].required = "外国人永久居留证";
            props["wealthAccInsurant.identityNo"].length = {
              length: 15,
              msg: "长度为15位"
            };
          }
        }
        props["wealthAccChannel.isProxy"] = { required: "联合销售代理信息" };

        if (wealthAccChannel.isProxy == "1") {
          props["wealthAccChannel.agentCode"] = { required: "联合销售代理人" };
          props["wealthAccChannel.agentAgreementCode"] = {
            required: "联合销售代理协议"
          };
        }
      }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(
            props[prop],
            _.result(this.plan, prop, "")
          );
          if (!status) {
            console.error(prop, status, msg);
            throw msg;
          }
        });
      } catch (error) {
        this.$toast.fail(error);
        return false;
      }
      return true;
    }
  }
};
</script>
<style lang="less" scoped>
.insurance-plans {
  .tabs-container {
    position: absolute;
    top: 90px;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding-bottom: 11px;
    background: transparent;
    .tab-item {
      font-size: 14px;
      font-weight: 500;
      color: #666666;
      margin-left: 20px;
    }
    .tab-active {
      height: 46px;
      font-size: 17px;
      font-weight: 500;
      color: black;
      display: flex;
      flex-direction: column;
      justify-content: flex-end;
      span {
        height: 5px;
        width: 28px;
        border-radius: 2.5px;
        background: #3b8afe;
        display: flex;
        align-self: center;
        margin-top: 8px;
      }
    }
  }
  .content {
    width: 100%;
    height: calc(100% - 146px - 54px);
    overflow: scroll;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    z-index: 99;
  }
  .group {
    flex-direction: column;
    background: white;
    margin-bottom: 10px;
    .item {
      height: 48px;
      align-items: center;
      .item-title {
        display: flex;
        flex-direction: row;
        align-items: center;
        font-size: 14px;
        color: #333333;
        sup {
          position: relative;
          height: 10px;
          line-height: 10px;
          align-self: flex-start;
        }
        .item-title-icon {
          margin-left: 8px;
          width: 18px;
          height: 18px;
          align-self: center;
        }
      }
    }
  }
  .getmessageBackground {
    height: 11px;
    background: #f7f7f7;
  }
}

.popup-container {
  background-color: transparent;
  border: 0;
  div {
    display: flex;
  }
  .popup-content {
    flex-direction: column;
    align-items: center;
    .estimated-cost-content {
      width: 345px;
      background: white;
      flex-direction: column;
      word-break: keep-all;
      border-radius: 5px;
      .content-title {
        color: #323233;
        font-size: 16px;
        font-weight: 500;
        line-height: 20px;
        align-self: center;
        margin: 29px 15px 16px;
      }
      .content-item {
        flex-direction: column;
      }
      .content-item-title {
        margin: 0 15px 10px;
        color: #323233;
        font-size: 16px;
        font-weight: 500;
        line-height: 20px;
      }
      .list-title,
      .list-item {
        position: relative;
        flex-direction: row;
        margin: 0 15px;
        color: #323233;
        border-bottom: 0.5px solid #e5e6e8;
        span {
          display: flex;
          flex: 1 1 80px;
          word-break: keep-all;
          justify-content: center;
          align-items: center;
          text-align: center;
        }
        span:first-child {
          display: block;
          min-width: 36px;
          padding: 0 8px;
          flex: 0;
          font-size: 12px;
        }
      }
      .list-title {
        justify-content: flex-end;
        word-break: keep-all;
        white-space: nowrap;
        font-size: 12px;
        line-height: 17px;
        height: 37px;
        border-top: 0.5px solid #e5e6e8;

        background: #fafafa;
      }
      .list-item {
        justify-content: flex-start;
        align-items: center;
        font-size: 14px;
        font-weight: 500;
        line-height: 20px;
        height: 42px;
      }
      .estimated-cost-total {
        flex: 1;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        font-size: 14px;
        color: #323233;
        line-height: 20px;
        margin: 9px 31px 25px 23px;
        .price {
          color: #fc820e;
          font-weight: 500;
        }
      }
      .activity {
        position: relative;
        flex-direction: column;
        background: #3b8afe13;
        border-radius: 3px;
        box-sizing: border-box;
        padding: 16px 14px;
        margin: 0 15px 15px;
        max-height: 200px;
        overflow: scroll;
        -webkit-overflow-scrolling: touch;
        overflow-scrolling: touch;
        .activity-award-row {
          display: flex;
          flex-direction: row;
          justify-content: space-between;
        }
        .activity-award {
          color: #323233;
          font-size: 14px;
          line-height: 20px;
          font-weight: 500;
          margin-bottom: 8px;
        }
        .activity-item {
          word-break: break-all;
          flex-direction: row;
          justify-content: space-between;
          align-items: center;
          width: 100%;
          margin-top: 2px;
          .activity-name {
            flex: 1;
            word-break: break-all;
            flex-wrap: wrap;
            font-size: 14px;
            line-height: 20px;
            color: #333333;
          }
          .award-name {
            flex: 1;
            word-break: break-all;
            flex-direction: row;
            justify-content: flex-end;
            flex-wrap: wrap;
            font-size: 13px;
            line-height: 18px;
            color: #666666;
          }
        }
      }
    }
    .popup-close-image {
      width: 26px;
      height: 26px;
      font-size: 26px;
      margin-top: 18px;
    }
  }
}
.plan-bottom {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  bottom: 0;
  left: 0;
  height: 54px;
  background: white;
  width: 100%;
  align-self: flex-end;
  div {
    display: flex;
  }
  .bottom-total {
    width: 169px;
    height: 54px;
    display: flex;
    flex-direction: column;
    margin-left: 25px;
    .total-premium {
      margin-top: 8px;
      color: #fc820e;
      font-size: 18px;
      line-height: 25px;
      font-weight: 600;
    }
    .total-text {
      font-size: 12px;
      color: #ababab;
    }
  }
  .bottom-right-buttons {
    height: 100%;
  }
  .estimated-fee {
    display: flex;
    width: 86px;
    height: 100%;
    background: #eef5ff;
    align-self: flex-end;
    justify-content: center;
    align-items: center;
    line-height: 18px;
    font-size: 14px;
    color: #486895;
  }
  .next-button {
    display: flex;
    flex: 1;
    width: 120px;
    height: 100%;
    background: #3b8afe;
    font-size: 16px;
    color: white;
    line-height: 22px;
    align-self: flex-end;
    justify-content: center;
    align-items: center;
  }
}
.bottom-fixed {
  height: 74px;
  background: #f7f7f7;
}
.floating-button {
  position: absolute;
  bottom: 88px;
  right: 10px;
  width: 65px;
  height: 67px;
  img {
    width: 65px;
    height: 67px;
  }
}
.showAuditResultDialog {
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
.check-container {
  display: flex;
  width: 300px;
  padding: 16px;
  flex-direction: column;
  div {
    display: flex;
  }
  .check-item {
    flex-direction: column;
    .row {
      flex-direction: row;
      height: 32px;
      justify-content: space-between;
      align-items: center;
      img {
        width: auto;
        height: auto;
        max-width: 100%;
        max-height: 100%;
      }
      input {
        border: 1px solid gray;
        width: 100%;
        height: 100%;
      }
    }
  }
  .button {
    height: 32px;
    width: 100%;
    margin-top: 12px;
    color: white;
    justify-content: center;
    align-items: center;
    border-radius: 16px;
    background: cornflowerblue;
  }
}
.reinsured-dialog {
  .reinsured-content-tip {
    font-size: 14px;
    color: red;
    padding: 8px 20px;
    background: white;
  }
  .reinsured-content {
    height: 300px;
    overflow-y: scroll;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    background: #f7f7f7;
    div {
      display: flex;
      flex-direction: column;
    }
    .card {
      display: flex;
      flex-direction: column;
      margin: 10px 0;
      background: white;
      padding: 10px 20px;
      .cell {
        position: relative;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        font-size: 14px;
        min-height: 32px;
        padding-bottom: 6px;
        margin-bottom: 6px;
        max-height: 1024px;
        overflow: hidden;
        div:first-child {
          color: #454545;
        }
        div:last-child {
          color: #7a7a7a;
          word-wrap: wrap;
          word-break: break-all;
          flex-wrap: wrap;
          max-width: 160px;
          flex: 1 0 100%;
        }
      }
      .cell:not(:last-child)::after {
        position: absolute;
        box-sizing: border-box;
        content: " ";
        pointer-events: none;
        right: 0;
        bottom: 0;
        left: 0;
        border-bottom: 1px solid #ebedf0;
        -webkit-transform: scaleY(0.5);
        transform: scaleY(0.5);
      }
      .cell-title {
        color: black;
        font-weight: 500;
      }
      .long-info {
        height: auto;
        word-wrap: wrap;
        word-break: break-all;
        div:nth-child(2) {
          max-width: 200px;
        }
      }
    }
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
  .card {
    padding: 0.3rem;
    .rowXian {
      display: flex;
      div {
        flex: 1;
        line-height: 24px;
        justify-content: space-around;
      }
      div:nth-of-type(2) {
        text-align: center;
      }
      div:nth-of-type(3) {
        text-align: right;
      }
    }
    .rowXian1 {
      display: flex;
      div {
        flex: 1;
        line-height: 24px;
        justify-content: space-around;
        .left_t {
          float: left;
        }
        .right_t {
          float: right;
          padding-right: 10px;
        }
      }
      // div:nth-of-type(2) {
      //   text-align: center;
      // }
      // div:nth-of-type(3) {
      //   text-align: right;
      // }
    }
    h4 {
      padding: 0.3rem 0;
      position: relative;
      font-size: 14px;
      &::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: 0;
        right: auto;
        top: auto;
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
</style>
