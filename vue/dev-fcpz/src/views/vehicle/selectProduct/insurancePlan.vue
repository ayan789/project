<!--
 * @Author: heweiwei
 * @Page: 方案选择
 * @Date: 2020-03-06 11:00:30
 * @Last Modified by: King
 * @Last Modified time: 2020-07-01 15:52:49
 * @FilePath: /src/views/vehicle/selectProduct/insurancePlan.vue
-->
<template>
  <div class="insurance-plan">
    <div class="content">
      <div class="group">
        <div class="car-info-card">
          <div class="car-info-license">
            {{ car.licenseNo || "未上牌" }}
          </div>
          <div class="car-info-brand">
            {{ car.vehicleBrand }}
          </div>
          <div class="c-item" v-show="/y/i.test(quote.smartscore)">
            C指数
            <div class="c-item-value">
              {{ quote.ccicVehicleScore || "120" }}
            </div>
            <img
              class="c-item-icon"
              src="../../../assets/vehicle/vehicleInfo/tip.png"
              @click="showCTip"
            />
          </div>
        </div>
        <van-cell class="item group-title">
          <div slot="title" class="item-title">
            <van-checkbox
              v-model="trafficChecked"
              icon-size="16px"
              class="item-checkbox"
            >
              <span class="item-checkbox-text">交强险</span>
            </van-checkbox>
          </div>
          <div class="item-right" v-show="trafficChecked">
            <span
              class="item-right-premium"
              v-show="plan.trafficDuePremium && quoted"
            >
              {{ trafficPremium | money }}
            </span>
          </div>
        </van-cell>
        <div v-if="trafficChecked" class="risk-container">
          <van-cell class="item">
            <span slot="title" class="item-title">
              保险期限
            </span>
            <div
              class="item-right time-limit"
              :style="plan.trafficIsEffective == '1' ? 'color:#969799' : ''"
            >
              <span
                class="item-right-date"
                @click.stop="changeDatePickerType('trafficEffectiveStartDate')"
              >
                {{ plan.trafficEffectiveStartDate | insuranceDate }}
              </span>
              <img
                class="item-right-icon"
                src="../../../assets/vehicle/selectProduct/calendar.png"
              />
              <span>-</span>
              <span
                class="item-right-date"
                @click.stop="changeDatePickerType('trafficEffectiveEndDate')"
              >
                {{ plan.trafficEffectiveEndDate | insuranceEndDate }}
              </span>
              <img
                class="item-right-icon"
                src="../../../assets/vehicle/selectProduct/calendar.png"
              />
            </div>
          </van-cell>
          <van-cell class="item">
            <div slot="title" class="item-title">
              是否立即生效
              <img
                class="item-title-icon"
                src="../../../assets/vehicle/vehicleInfo/tip.png"
                @click="effectiveImmediatelyTip = true"
              />
            </div>
            <div class="item-right">
              <whether-select
                :disabled="disabledTrafficImmediately"
                allocMultiple
                v-model="plan.trafficIsEffective"
                :options="[
                  { text: '是', value: '1' },
                  { text: '否', value: '0' }
                ]"
                @change="v => changeImmediately(v, 'traffic')"
              />
            </div>
          </van-cell>
          <div class="kind-card">
            <div class="kind-card-title">
              <div class="kind-card-left">险种</div>
              <div class="kind-card-center">保额(元)</div>
              <div class="kind-card-right">保费(元)</div>
            </div>
            <div class="kind-card-item">
              <div class="kind-card-left">交强险</div>
              <div class="kind-card-center">122000</div>
              <div class="kind-card-right">
                {{ plan.trafficDuePremium | fixed }}
              </div>
            </div>
            <div class="kind-card-item">
              <div class="kind-card-left">车船税</div>
              <div
                class="kind-card-center"
                @click="$router.push({ name: 'vehicleTax' })"
              >
                {{ taxFlagText }}
                <img
                  width="13px"
                  height="13px"
                  src="../../../assets/vehicle/vehicleInfo/edit.png"
                />
              </div>

              <div v-if="plan.tax.sumPayment == '0'" class="kind-card-right">
                {{ plan.tax.sumPayment }}
              </div>
              <div v-else class="kind-card-right">
                {{ (plan.tax || {}).sumPayment | fixed }}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="group">
        <van-cell class="item group-title">
          <div slot="title" class="item-title">
            <van-checkbox
              v-model="businessChecked"
              icon-size="16px"
              class="item-checkbox"
            >
              <span class="item-checkbox-text">商业险</span>
            </van-checkbox>
          </div>
          <div class="item-right" v-show="businessChecked">
            <span
              class="item-right-premium"
              v-show="plan.businessDuePremium && quoted"
            >
              <s>{{ plan.businessStdPremium | money }}</s>
              {{ plan.businessDuePremium | money }}
            </span>
            <img
              class="group-right-icon"
              src="../../../assets/vehicle/vehicleInfo/editit.png"
              @click="$router.push({ name: 'vehicleCommercial' })"
            />
          </div>
        </van-cell>
        <div v-if="businessChecked" class="risk-container">
          <van-cell class="item">
            <span slot="title" class="item-title">保险期限</span>
            <div
              class="item-right time-limit"
              :style="plan.businessIsEffective == '1' ? 'color:#969799' : ''"
            >
              <span
                class="item-right-date"
                @click.stop="changeDatePickerType('businessEffectiveStartDate')"
              >
                {{ plan.businessEffectiveStartDate | insuranceDate }}
              </span>
              <img
                class="item-right-icon"
                src="../../../assets/vehicle/selectProduct/calendar.png"
              />
              <span>-</span>
              <span
                class="item-right-date"
                @click.stop="changeDatePickerType('businessEffectiveEndDate')"
              >
                {{ plan.businessEffectiveEndDate | insuranceEndDate }}
              </span>
              <img
                class="item-right-icon"
                src="../../../assets/vehicle/selectProduct/calendar.png"
              />
            </div>
          </van-cell>
          <van-cell class="item" v-if="!disabledBusinessImmediately">
            <div slot="title" class="item-title">
              是否立即生效
              <img
                class="item-title-icon"
                src="../../../assets/vehicle/vehicleInfo/tip.png"
                @click="effectiveImmediatelyTip = true"
              />
            </div>
            <div class="item-right">
              <whether-select
                v-model="plan.businessIsEffective"
                allocMultiple
                :options="[
                  { text: '是', value: '1' },
                  { text: '否', value: '0' }
                ]"
                @change="v => changeImmediately(v, 'business')"
              />
            </div>
          </van-cell>
          <div v-if="(plan.businessRisks || []).length > 0" class="kind-card">
            <div class="kind-card-title">
              <div class="kind-card-left kind-card-left-long">险种</div>
              <div class="kind-card-center">保额(元)</div>
              <div class="kind-card-right">保费(元)</div>
            </div>
            <div
              v-for="item in plan.businessRisks"
              :key="item.kindCode"
              style="flex-direction: column;"
            >
              <div class="kind-card-item">
                <div class="kind-card-left kind-card-left-long">
                  {{ item.kindName | kindName }}
                </div>
                <div class="kind-card-center">
                  {{ item | amount }}
                </div>
                <div class="kind-card-right">
                  {{ item.premium | fixed }}
                </div>
              </div>
              <div class="kind-card-item" v-if="item.kindFlag == '1'">
                <div class="kind-card-left kind-card-left-nocenter">
                  {{ item.kindName | kindName }}
                  (不计免赔)
                </div>
                <div class="kind-card-right">
                  {{ item.nonDeductiblePremium || "" }}
                </div>
              </div>
            </div>
          </div>
          <van-cell class="item">
            <div slot="title" class="item-title">
              期望优惠
              <img
                class="item-title-icon"
                src="../../../assets/vehicle/vehicleInfo/tip.png"
                @click="businessExpectDiscountTip = true"
              />
            </div>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model="plan.businessExpectDiscount"
                maxlength="10"
                placeholder="0到132.5 之间整数或小数"
                @focus="e => (e.target.placeholder = '')"
                @blur="businessExpectDiscountOnBlur"
              />
              <span>%</span>
            </div>
          </van-cell>
        </div>
      </div>
      <div class="group">
        <van-cell class="item group-title">
          <div slot="title" class="item-title">
            <van-checkbox
              :disabled="!trafficChecked && !businessChecked"
              v-model="wealthAccChecked"
              icon-size="16px"
              class="item-checkbox"
              checkbox
            >
              <span class="item-checkbox-text">联合销售</span>
            </van-checkbox>
          </div>
          <div class="item-right" v-show="wealthAccChecked">
            <span
              class="item-right-premium"
              v-show="plan.wealthAccPremium && quoted"
            >
              {{ plan.wealthAccPremium | money }}
            </span>
            <img
              class="group-right-icon"
              src="../../../assets/vehicle/vehicleInfo/editit.png"
              v-show="trafficChecked || businessChecked"
              @click="$router.push({ name: 'vehicleWealthAccRisk' })"
            />
          </div>
        </van-cell>
        <div v-if="wealthAccChecked" class="kind-card">
          <div class="kind-card-title">
            <div class="kind-card-left wealthacc-group-name">险种</div>
            <div class="kind-card-center wealthacc-name">方案</div>
            <div class="kind-card-center wealthacc-number">份数</div>
            <div class="kind-card-right wealthacc-premium">保费(元)</div>
          </div>
          <div class="kind-card-item">
            <div class="kind-card-left wealthacc-group-name">
              {{ plan.wealthAccFormulaGroupName }}
            </div>
            <div class="kind-card-center wealthacc-name">
              {{ plan.wealthAccFormulaName }}
            </div>
            <div class="kind-card-center wealthacc-number">
              {{ plan.wealthAccBuyNum }}
            </div>
            <div class="kind-card-right wealthacc-premium">
              {{ plan.wealthAccPremium | fixed }}
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 提示弹窗 -->
    <van-popup v-model="effectiveImmediatelyTip" class="popup-container">
      <div class="popup-content">
        <div class="content">
          <div class="text">
            当选择即时生效后，实际起保时间将按照缴费后下个时间点进行设置，且只有期望今天起保的单子可以选择即时生效。
          </div>
        </div>
        <van-icon
          name="close"
          color="white"
          class="popup-close-image"
          @click="effectiveImmediatelyTip = false"
        />
      </div>
    </van-popup>
    <!-- 请填写期望的商业险双自主系数，计算公式：自主核保系数*自主渠道系数。示例：自主核保系数和自主渠道系数分别为0.7和0.85，则计算结果59.5%。 -->
    <van-popup v-model="businessExpectDiscountTip" class="popup-container">
      <div class="popup-content">
        <div class="content">
          <div class="text">
            请填写期望的商业险双自主系数，计算公式：自主核保系数*自主渠道系数。示例：自主核保系数和自主渠道系数分别为0.7和0.85，则计算结果59.5%。
          </div>
        </div>
        <van-icon
          name="close"
          color="white"
          class="popup-close-image"
          @click="businessExpectDiscountTip = false"
        />
      </div>
    </van-popup>
    <!-- 日期选择 -->
    <van-popup v-model="datePickerShow" position="bottom">
      <van-datetime-picker
        type="datetime"
        v-model="currentDate"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="onDatePickerConfirm"
        @cancel="datePickerType = ''"
      />
    </van-popup>
  </div>
</template>
<script>
import WhetherSelect from "../vehicleInfo/whetherSelect.vue";
import { Calendar, Cell } from "vant";
import { mapState, mapMutations } from "vuex";
import Config from "@src/common/json/newCoreDataDictionary";
import { initBusinessRisks } from "../../../store/modules/vehicle/vehicle";

export default {
  components: {
    [Calendar.name]: Calendar,
    [Cell.name]: Cell,
    [WhetherSelect.name]: WhetherSelect
  },
  model: {
    prop: "plan",
    event: "change"
  },
  props: {
    plan: Object
  },
  data() {
    return {
      flagStartDate: false,
      flagEndDate: false,
      effectiveImmediatelyTip: false,
      businessExpectDiscountTip: false,
      estimatedCost: false,
      datePickerType: "",
      currentDate: new Date(),
      minDate: null,
      maxDate: null,
      areaCode: ""
    };
  },
  created() {
    this.fixDate();
  },
  mounted() {
    const userInfo = this.localStorage.get("userInfo", {});
    const comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
    // console.log(this.plan.businessRisks,"heweiwei何韦韦")
  },
  watch: {
    // "plan.trafficEffectiveEndDate"(v) {
    //   if (!v || typeof v != "string") return;
    //   let compareDate = new Date(v.replace(/-/g, "/"));
    //   if (this.plan.trafficIsEffective != "1") {
    //     compareDate.setHours(0);
    //     compareDate.setMinutes(0);
    //     compareDate.setSeconds(0);
    //     compareDate.setDate(compareDate.getDate() + 1);
    //   }
    //   if (!v || new Date().format() < compareDate.format()) {
    //     this.fixDate();
    //   }
    // },
    // "plan.businessEffectiveEndDate"(v) {
    //   if (!v || typeof v != "string") return;
    //   let compareDate = new Date(v.replace(/-/g, "/"));
    //   if (this.plan.businessIsEffective != "1") {
    //     compareDate.setHours(0);
    //     compareDate.setMinutes(0);
    //     compareDate.setSeconds(0);
    //     compareDate.setDate(compareDate.getDate() + 1);
    //   }
    //   if (!v || new Date().format() < compareDate.format()) {
    //     this.fixDate();
    //   }
    // },
    "car.seatCount"(newValue) {
      if (newValue) {
        if (this.plan.wealthAccChecked == "1") {
          // if (this.plan.wealthAccFormulaGroupName.includes("驾乘无忧")) {
          //   let seatCount = this.plan.wealthAccSeatCount;
          //   let premium = this.plan.wealthAccPremium / seatCount;
          //   this.plan.wealthAccPremium = premium * newValue;
          // }
        }
        if (this.plan.wealthAccSeatCount != newValue) {
          this.plan.wealthAccFormulaGroupCode = "";
          this.plan.wealthAccFormulaGroupName = "";
          this.plan.wealthAccFormulaCode = "";
          this.plan.wealthAccFormulaName = "";
          this.plan.wealthAccRiskCode = "";
          this.plan.wealthAccRiskName = "";
          this.plan.wealthAccBuyNum = "";
          this.plan.wealthAccPremium = "";
        }
        this.plan.wealthAccSeatCount = newValue;
        //车上人员责任保险（乘客）
        let businessRisks = this.plan.businessRisks || [];
        let C100124Index = businessRisks.findIndex(
          item => item.kindCode == "C100124"
        );
        if (C100124Index != -1) {
          let C100124 = businessRisks[C100124Index];
          C100124.quantity = newValue - 1;
          let calcAmount = C100124.unitAmount * C100124.quantity;
          if (Number.isSafeInteger(calcAmount)) {
            C100124.amount = calcAmount;
          }
          businessRisks[C100124Index] = C100124;
          this.plan.businessRisks = businessRisks;
        }
      }
    },
    "plan.trafficEffectiveStartDate"(v) {
      let compareDate = new Date();
      if (this.plan.trafficIsEffective != "1") {
        // compareDate.setDate(compareDate.getDate() + 1);
      }
      if (!v || v < compareDate.format()) {
        this.fixDate();
      }
    },
    "plan.trafficEffectiveEndDate"(v) {
      if (this.flagEndDate && this.plan.businessChecked == "1") {
        this.plan.businessEffectiveEndDate = v;
      }
    },
    "plan.businessEffectiveStartDate"(v) {
      let compareDate = new Date();
      if (this.plan.businessIsEffective != "1") {
        // compareDate.setDate(compareDate.getDate() + 1);
      }
      if (!v || v < compareDate.format()) {
        this.fixDate();
      }
    }
  },
  filters: {
    money(value) {
      if (isNaN(Number(value))) return "￥0.00";
      return "￥" + Number.parseFloat(value).toLocaleString();
    },
    fixed(value) {
      if (!value) return "";
      if (isNaN(Number(value))) return "0.00";
      return Number(value).toFixed(2);
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
    businessRiskPremium(risk) {
      let premium = Number(
        Number.parseFloat(risk.premium + risk.nonDeductiblePremium)
      );
      if (premium) {
        return premium.toFixed(2);
      }
      return "";
    }
  },
  computed: {
    ...mapState("vehicle", {
      car: ({ car }) => car,
      quote: ({ quoteInfo }) => quoteInfo,
      quoted: ({ control: { quoted } }) => quoted,
      historyPolicy: ({ historyPolicy }) => historyPolicy
    }),
    trafficPremium() {
      let { trafficDuePremium, tax } = this.plan;
      let { sumPayment } = tax || {};
      return (
        Number.parseFloat(trafficDuePremium + sumPayment).toFixed(2) || "0"
      );
    },
    disabledTrafficImmediately() {
      // 青岛、海南、吉林、甘肃、四川、河南、宁波、上海、北京不支持交强险即时生效
      let userInfo = this.localStorage.get("userInfo", {});
      let comCode = userInfo.comCode || "";
      let areaCode = comCode.substr(0, 4);
      if (
        areaCode == "3702" || //青岛
        areaCode == "4601" || //海南
        areaCode == "2201" || //吉林
        areaCode == "6201" || //甘肃
        areaCode == "5101" || //四川
        areaCode == "4101" || //河南
        areaCode == "3302" || //宁波
        areaCode == "3101" ||
        areaCode == "3109" || //上海
        areaCode == "1101" //北京
      ) {
        return true;
      }
      return false;
    },
    disabledBusinessImmediately() {
      let userInfo = this.localStorage.get("userInfo", {});
      let comCode = userInfo.comCode || "";
      let areaCode = comCode.substr(0, 4);
      // 青岛、上海不支持商业险即时生效
      if (
        areaCode == "3702" || //青岛
        areaCode == "3101" ||
        areaCode == "3109" //上海
      ) {
        return true;
      }
      return false;
    },
    trafficChecked: {
      get: function() {
        return this.plan.trafficChecked == "1";
      },
      set: function(value) {
        this.plan.trafficChecked = value ? "1" : "0";
        if (value) {
          this.wealthAccChecked = true;
        }
        this.$emit("change", this.plan);
      }
    },
    businessChecked: {
      get: function() {
        return this.plan.businessChecked == "1";
      },
      set: function(value) {
        this.plan.businessChecked = value ? "1" : "0";
        if (value) {
          this.wealthAccChecked = true;
        }
        this.$emit("change", this.plan);
      }
    },
    wealthAccChecked: {
      get: function() {
        return this.plan.wealthAccChecked == "1";
      },
      set: function(value) {
        this.plan.wealthAccChecked = value ? "1" : "0";
        this.$emit("change", this.plan);
      }
    },
    datePickerShow: {
      get: function() {
        return !!this.datePickerType;
      },
      set: function() {
        this.datePickerType = "";
      }
    },
    taxFlagText() {
      let taxFlag = (this.plan.tax || {}).taxFlag;
      let taxFlags = {
        T: "正常缴税",
        C: "减税",
        P: "已完税",
        E: "免税",
        D: "有争议",
        R: "拒缴",
        B: "补税",
        N: "不征"
      };
      return taxFlags[taxFlag] || "";
    }
  },
  methods: {
    ...mapMutations("vehicle", ["calcUseYears"]),
    showCTip() {
      this.$dialog.alert({
        message: "C指数是对业务质量优劣的区分，指数越高表明业务质量可能越好。"
      });
    },
    input(e) {
      let value = e.target.value;
      let beforeValue = this.plan.businessExpectDiscount;
      this.plan.businessExpectDiscount = value;
      if (!value) {
        this.plan.businessExpectDiscount = "";
        return;
      } else if (/[^0-9|.|-]/.test(value)) {
        this.plan.businessExpectDiscount = beforeValue;
        this.$toast("请输入-100到100的整数或小数");
      } else {
        if (value < -100 || value > 100) {
          this.plan.businessExpectDiscount = beforeValue;
          this.$toast("请输入-100到100的整数或小数");
          return;
        }
        this.plan.businessExpectDiscount = value;
        return;
      }
      this.plan.businessExpectDiscount = beforeValue;
      this.$toast("请输入-100到100的整数或小数");
    },
    businessExpectDiscountOnBlur(e) {
      e.target.placeholder = "0到132.5 之间整数或小数";
      if (!this.plan.businessExpectDiscount) return;
      if (/^\d+(\.\d+)?$/.test(this.plan.businessExpectDiscount)) {
        console.warn("符合正则");
        if (
          this.plan.businessExpectDiscount < 0 ||
          this.plan.businessExpectDiscount > 132.5
        ) {
          this.plan.businessExpectDiscount = "";
          this.$toast("请输入0到132.5的整数或小数");
          e.target.focus();
        }
      } else {
        this.plan.businessExpectDiscount = "";
        this.$toast("请输入0到132.5的整数或小数");
        e.target.focus();
      }
    },
    fixDate() {
      let date = new Date();
      if (this.plan.trafficIsEffective != "1") {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        date.setDate(date.getDate() + 1);
      }
      if (date.format() > this.plan.trafficEffectiveStartDate) {
        if (this.historyPolicy && this.historyPolicy.trafficChecked == "1") {
          if (date.format() < this.historyPolicy.trafficEffectiveEndDate) {
            date = new Date(
              this.historyPolicy.trafficEffectiveEndDate.replace(/-/g, "/")
            );
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            date.setDate(date.getDate() + 1);
          }
        }
        console.warn("setTrafficStartDate : ", date.format());
        this.setStartDate("traffic", date);
      }
      date = new Date();
      if (this.plan.businessIsEffective != "1") {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        date.setDate(date.getDate() + 1);
      }
      if (date.format() > this.plan.businessEffectiveStartDate) {
        if (this.historyPolicy && this.historyPolicy.businessChecked == "1") {
          if (date.format() < this.historyPolicy.businessEffectiveEndDate) {
            date = new Date(
              this.historyPolicy.businessEffectiveEndDate.replace(/-/g, "/")
            );
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            date.setDate(date.getDate() + 1);
          }
        }
        console.warn("setBusinessStartDate : ", date.format());
        this.setStartDate("business", date);
      }
    },
    setStartDate(type, date) {
      console.warn("setStartDate : ", type, date);
      let nextDate = new Date(date);
      nextDate.setFullYear(date.getFullYear() + 1);
      let startFormat = "yyyy-MM-dd HH:mm:00";
      let endFormat = "yyyy-MM-dd HH:mm:00";
      if (this.plan[type + "IsEffective"] == "1") {
        if (type == "traffic") {
          startFormat = "yyyy-MM-dd 23:00:00";
          endFormat = startFormat;
        } else {
          startFormat = "yyyy-MM-dd 23:59:00";
          endFormat = "yyyy-MM-dd 24:00:00";
        }
      } else {
        if (date.getHours() == "00") {
          nextDate.setDate(nextDate.getDate() - 1);
          endFormat = "yyyy-MM-dd 24:00:00";
        }
        if (type == "business") {
          endFormat = "yyyy-MM-dd 24:00:00";
        }
      }
      this.plan[type + "EffectiveStartDate"] = date.format(startFormat);
      this.plan[type + "EffectiveEndDate"] = nextDate.format(endFormat);
    },
    setEndDate(type, date) {
      // date.setDate(date.getDate() + 1);
      let beforDate = new Date(date);
      beforDate.setFullYear(date.getFullYear() - 1);
      // beforDate.setDate(date.getDate() - 1);
      let startFormat = "yyyy-MM-dd 00:00:00";
      let endFormat = "yyyy-MM-dd 24:00:00";
      if (this.plan[type + "IsEffective"] == "1") {
        if (type == "traffic") {
          startFormat = "yyyy-MM-dd 23:00:00";
          endFormat = startFormat;
        } else {
          startFormat = "yyyy-MM-dd 23:59:59";
          endFormat = "yyyy-MM-dd 24:00:00";
        }
      }
      this.plan[type + "EffectiveStartDate"] = beforDate.format(startFormat);
      this.plan[type + "EffectiveEndDate"] = date.format(endFormat);
    },
    changeImmediately(v, type) {
      let date = new Date();
      if (v == "1") {
        if (type == "traffic") {
          this.setStartDate(type, date);
          this.plan.businessIsEffective = "1";
        }
        this.setStartDate("business", date);
      } else if (v == "0") {
        date.setDate(date.getDate() + 1);
        let compareDate = new Date(null);
        if (this.plan[type + "EffectiveStartDate"]) {
          compareDate = new Date(
            this.plan[type + "EffectiveStartDate"].replace(/-/g, "/")
          );
        }
        if (date > compareDate) {
          date.setHours(0);
          date.setMinutes(0);
          date.setSeconds(0);
          this.setStartDate(type, date);
        }
      }
    },
    changeDatePickerType(type) {
      switch (type) {
        case "trafficEffectiveStartDate": {
          if (this.plan.trafficIsEffective == "1") return;
          let minDate = new Date();
          minDate.setMinutes(minDate.getMinutes() + 1);
          if (this.plan.trafficEffectiveEndDate) {
            // let endDate = new Date(
            //   this.plan.trafficEffectiveEndDate.replace(/-/g, "/")
            // );
            // endDate.setFullYear(endDate.getFullYear() - 1);
            // if (endDate.getTime() > minDate.getTime()) {
            //   minDate = endDate;
            // }
          }
          // if (this.plan.trafficIsEffective == "0") {
          //   minDate.setDate(minDate.getDate() + 1);
          //   minDate.setHours(0);
          //   minDate.setMinutes(0);
          //   minDate.setSeconds(0);
          // }
          this.minDate = minDate;
          let maxDate = new Date(minDate);
          maxDate.setFullYear(maxDate.getFullYear() + 100);
          this.maxDate = maxDate;
          break;
        }
        case "trafficEffectiveEndDate": {
          if (this.plan.trafficIsEffective == "1") return;
          let defaultMinDate = new Date();
          defaultMinDate.setDate(defaultMinDate.getDate() + 1);
          defaultMinDate.setHours(0);
          defaultMinDate.setMinutes(0);
          defaultMinDate.setSeconds(0);
          let minDate = this.plan.trafficEffectiveStartDate
            ? new Date(this.plan.trafficEffectiveStartDate.replace(/-/g, "/"))
            : defaultMinDate;
          this.minDate = minDate;
          let maxDate = new Date(minDate);
          maxDate.setFullYear(maxDate.getFullYear() + 1);
          // maxDate.setDate(maxDate.getDate() - 1);
          // maxDate.setHours(23);
          // maxDate.setMinutes(59);
          this.maxDate = maxDate;
          break;
        }
        case "businessEffectiveStartDate": {
          if (this.plan.businessIsEffective == "1") return;
          let minDate = new Date();
          // minDate.setDate(minDate.getDate() + 1);
          // minDate.setHours(0);
          minDate.setMinutes(minDate.getMinutes() + 1);
          // minDate.setSeconds(0);
          if (this.plan.businessEffectiveEndDate) {
            // let endDate = new Date(
            //   this.plan.businessEffectiveEndDate.replace(/-/g, "/")
            // );
            // endDate.setFullYear(endDate.getFullYear() - 1);
            // if (endDate.getTime() > minDate.getTime()) {
            //   minDate = endDate;
            // }
          }
          // if (this.plan.businessIsEffective == "0") {
          //   minDate.setDate(minDate.getDate() + 1);
          //   minDate.setHours(0);
          //   minDate.setMinutes(0);
          //   minDate.setSeconds(0);
          // }
          this.minDate = minDate;
          let maxDate = new Date(minDate);
          maxDate.setFullYear(maxDate.getFullYear() + 100);
          this.maxDate = maxDate;
          break;
        }
        case "businessEffectiveEndDate": {
          if (this.plan.businessIsEffective == "1") return;
          let defaultMinDate = new Date();
          defaultMinDate.setDate(defaultMinDate.getDate() + 1);
          defaultMinDate.setHours(0);
          defaultMinDate.setMinutes(0);
          defaultMinDate.setSeconds(0);
          let minDate = this.plan.businessEffectiveStartDate
            ? new Date(this.plan.businessEffectiveStartDate.replace(/-/g, "/"))
            : defaultMinDate;
          this.minDate = minDate;
          let maxDate = new Date(minDate);
          maxDate.setFullYear(maxDate.getFullYear() + 1);
          // maxDate.setDate(maxDate.getDate() - 1);
          // maxDate.setHours(23);
          // maxDate.setMinutes(59);
          this.maxDate = maxDate;
          break;
        }
        default:
          break;
      }
      let currentDate = new Date();
      if (this.plan[type]) {
        console.warn(this.plan[type]);
        currentDate = new Date(
          this.plan[type].replace(/-/g, "/").replace(" 24:00", " 23:59")
        );
      } else {
        currentDate.setHours(currentDate.getHours() + 1);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);
      }
      this.datePickerType = type;
      this.currentDate = currentDate;
      this.$nextTick(() => {
        this.currentDate = currentDate;
      });
    },
    onDatePickerConfirm(date) {
      switch (this.datePickerType) {
        case "trafficEffectiveStartDate": {
          if (this.plan.trafficIsEffective == "1") return;
          if (
            this.businessChecked &&
            this.plan.trafficEffectiveStartDate ==
              this.plan.businessEffectiveStartDate
          ) {
            this.setStartDate("business", date);
          }
          this.setStartDate("traffic", date);
          break;
        }

        case "businessEffectiveStartDate": {
          if (this.plan.businessIsEffective == "1") return;
          this.setStartDate("business", date);
          break;
        }
        case "trafficEffectiveEndDate": {
          if (this.plan.trafficIsEffective == "1") return;
          let startHour = "";
          let format = "yyyy-MM-dd 24:00:00";
          if (this.plan.trafficEffectiveStartDate) {
            startHour = this.plan.trafficEffectiveStartDate.substr(11, 2);
          }
          if (startHour == date.getHours() && startHour != "00") {
            format = "yyyy-MM-dd HH:mm:00";
          }
          if (
            this.businessChecked &&
            this.plan.trafficEffectiveStartDate ==
              this.plan.businessEffectiveStartDate
          ) {
            if (
              this.plan.trafficEffectiveEndDate ==
              this.plan.businessEffectiveEndDate
            ) {
              this.plan.businessEffectiveEndDate = date.format(format);
            }
          }
          this.plan[this.datePickerType] = date.format(format);
          break;
        }
        case "businessEffectiveEndDate": {
          if (this.plan.businessIsEffective == "1") return;
          this.plan[this.datePickerType] = date.format("yyyy-MM-dd 24:00:00");
          // this.setEndDate("traffic", date);
          break;
        }
        default:
          this.plan[this.datePickerType] = date.format();
          break;
      }
      this.datePickerType = "";
      this.$emit("change", this.plan);
      this.calcUseYears();
    }
  },
  updated() {
    return;
    let traffic = this.plan.trafficEffectiveStartDate;
    let business = this.plan.businessEffectiveStartDate;
    let trafficEnd = this.plan.trafficEffectiveEndDate;
    let businessEnd = this.plan.businessEffectiveEndDate;
    let trafficVal =
      traffic.slice(0, 4) +
      traffic.slice(5, 7) +
      traffic.slice(8, 10) +
      traffic.slice(11, 13) +
      traffic.slice(14, 16) +
      traffic.slice(17, 19);
    let businessVal =
      business.slice(0, 4) +
      business.slice(5, 7) +
      business.slice(8, 10) +
      business.slice(11, 13) +
      business.slice(14, 16) +
      business.slice(17, 19);
    let trafficValEnd =
      trafficEnd.slice(0, 4) +
      trafficEnd.slice(5, 7) +
      trafficEnd.slice(8, 10) +
      trafficEnd.slice(11, 13) +
      trafficEnd.slice(14, 16) +
      trafficEnd.slice(17, 19);
    let businessValEnd =
      businessEnd.slice(0, 4) +
      businessEnd.slice(5, 7) +
      businessEnd.slice(8, 10) +
      businessEnd.slice(11, 13) +
      businessEnd.slice(14, 16) +
      businessEnd.slice(17, 19);
    if (trafficVal == businessVal) {
      this.flagStartDate = true;
    } else {
      this.flagStartDate = false;
    }
    if (trafficValEnd == businessValEnd) {
      this.flagEndDate = true;
    } else {
      this.flagEndDate = false;
    }
  }
};
</script>

<style lang="less" scoped>
.insurance-plan {
  .content {
    display: flex;
    flex: 1;
    flex-direction: column;
    background: #f7f7f7;
    div {
      display: flex;
    }
    .van-cell__value {
      overflow: visible;
    }
    .risk-container {
      flex-direction: column;
    }
    .car-info-card {
      width: 344px;
      height: 75px;
      margin-top: 10px;
      background: #eef5ff;
      border-radius: 10px;
      align-self: center;
      flex-direction: column;
      position: relative;
      .car-info-license {
        font-size: 20px;
        line-height: 28px;
        height: 28px;
        margin: 12px 0 0 16px;
      }
      .car-info-brand {
        font-size: 14px;
        line-height: 20px;
        height: 20px;
        margin-left: 16px;
      }
    }
    .group {
      flex-direction: column;
      background: white;
      margin-bottom: 10px;
      .group-title::after {
        border-bottom: 0;
      }
      .group-right-icon {
        width: 18px;
        height: 18px;
      }
    }
    .item {
      height: 48px;
      align-items: center;
      .van-cell__value {
        flex: 2;
      }
      .item-title {
        align-items: center;
        font-size: 14px;
        color: #333333;
        .item-checkbox {
          font-size: 15px;
          .item-checkbox-text {
            font-size: 15px;
            font-weight: 500;
            line-height: 21px;
          }
        }
        .item-title-icon {
          margin-left: 8px;
          width: 18px;
          height: 18px;
          align-self: center;
        }
      }
      .item-right {
        flex: 1;
        word-break: keep-all;
        white-space: nowrap;
        align-items: center;
        justify-content: flex-end;
        font-size: 14px;
        color: #333333;
        .item-right-input {
          width: 100%;
          text-align: right;
          border: 0;
          background: transparent;
          opacity: 1;
          font-size: 14px;
          color: #333333;
        }
        .item-right-icon {
          margin: 0 4px;
          width: 14px;
          height: 14px;
        }
        .item-right-premium {
          font-size: 14px;
          s {
            color: #929292;
          }
          margin-right: 12px;
          color: orange;
        }
        .item-right-date {
          font-size: 12px;
        }
      }
      .time-limit {
        font-size: 12px;
        align-items: center;
      }
    }
    .kind-card {
      flex: 1;
      flex-direction: column;
      align-self: center;
      box-sizing: border-box;
      width: 345px;
      margin-top: 12px;
      margin-bottom: 12px;
      padding: 10px 18px 15px;
      background: #fafafa;
      word-break: keep-all;
      .kind-card-title {
        font-size: 13px;
        color: #8e8e8e;
        line-height: 17px;
        height: 17px;
      }
      .kind-card-item {
        font-size: 14px;
        color: #545454;
        line-height: 23px;
        margin-top: 8px;
        width: 100%;
      }
      .kind-card-left {
        width: 103px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        display: inline-block;
      }
      .kind-card-left-long {
        width: 180px;
      }
      .kind-card-left-nocenter {
        width: 240px;
      }
      .kind-card-center {
        flex: 1;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        position: relative;
      }
      .kind-card-right {
        flex: 1;
        flex-direction: row;
        justify-content: flex-end;
        align-items: center;
      }
      .wealthacc-group-name {
        width: 80px;
      }
      .wealthacc-name {
        flex: 2;
      }
      .wealthacc-number {
      }
      .wealthacc-premium {
      }
    }
    // .drop-down-icon::after {
    //   position: absolute;
    //   top: 50%;
    //   right: 10px;
    //   margin-top: -0.13333rem;
    //   border: 0.08rem solid;
    //   border-color: transparent transparent #b8b8b8 #b8b8b8;
    //   -webkit-transform: rotate(-45deg);
    //   transform: rotate(-45deg);
    //   opacity: 0.8;
    //   content: "";
    // }
  }
  .popup-container {
    background-color: transparent;
    border: 0;
    .popup-content {
      flex-direction: column;
      align-items: center;
      display: flex;
      .content {
        min-width: 285px;
        height: 175px;
        box-sizing: border-box;
        padding: 30px;
        border-radius: 5px;
        .text {
          font-size: 14px;
          color: #333333;
          line-height: 30px;
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
}
.c-item {
  position: absolute;
  top: 10px;
  right: 10px;
  color: red;
  display: flex;
  flex-direction: row;
  align-items: center;
  font-size: 14px;
  .c-item-value {
    margin: 0 10px 0 20px;
  }
  .c-item-icon {
    width: 20px;
    height: 20px;
  }
}
</style>
