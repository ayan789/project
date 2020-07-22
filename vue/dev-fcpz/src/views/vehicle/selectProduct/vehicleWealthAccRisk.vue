<!--
 * @Author: heweiwei
 * @Page: 联合销售方案
 * @Date: 2020-03-04 14:28:10
 * @Last Modified by: King
 * @Last Modified time: 2020-07-03 09:58:21
 * @FilePath: /src/views/vehicle/selectProduct/vehicleWealthAccRisk.vue
-->
<template>
  <div class="vehicle-wealth-acc-risk">
    <van-sticky>
      <nav-bar
        type="vehicle"
        title="联合销售方案"
        @clickBack="$router.back()"
      />
    </van-sticky>
    <div class="content">
      <div class="risk-contents">
        <div class="risk-kind-info" v-show="formulas.length">
          <span>险种</span>
          <span>方案</span>
          <span>份数</span>
          <span>保费</span>
        </div>
        <div
          class="risk-kind-item"
          v-for="item in formulas"
          :key="item.formulaGroupCode"
          @click="selectedRisk(item)"
        >
          <van-checkbox icon-size="16" :value="checked(item)">
            <span class="item-checkbox-title">{{ item.formulaGroupName }}</span>
          </van-checkbox>
          <div>{{ item.formulaName }}</div>
          <div>{{ item.count }}</div>
          <div>{{ item.premium }}</div>
        </div>
      </div>
      <!-- 被保人信息 -->
      <div
        class="group"
        v-if="
          risk.wealthAccFormulaGroupName &&
            !risk.wealthAccFormulaGroupName.includes('驾乘无忧')
        "
      >
        <van-cell class="item" style="background:#f7f7f7;">
          <span slot="title" class="item-title"> 被保人信息</span>
        </van-cell>
        <van-cell class="item" required>
          <span slot="title" class="item-title">姓名</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model="risk.wealthAccInsurant.insurantName"
              placeholder="请输入姓名"
              maxlength="50"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入姓名')"
            />
          </div>
        </van-cell>
        <van-cell class="item" required @click="setPicker('identifyType')">
          <span slot="title" class="item-title">证件类型</span>
          <div class="item-right">
            <span
              :class="{
                'item-placeholder': !risk.wealthAccInsurant.identityType
              }"
            >
              {{
                $utils.findText(
                  identifyTypes,
                  risk.wealthAccInsurant.identityType
                )
              }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
        </van-cell>
        <van-cell
          class="item"
          required
          v-if="requiredAge"
          @click="setDatePicker('dateOfBirth')"
        >
          <span slot="title" class="item-title">出生日期</span>
          <div class="item-right">
            <span
              :class="{
                'item-placeholder': !risk.wealthAccInsurant.dateOfBirth
              }"
            >
              {{ risk.wealthAccInsurant.dateOfBirth | date }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
        </van-cell>
        <van-cell class="item" required v-if="false">
          <span slot="title" class="item-title">性别</span>
          <div class="item-right">
            <whether-select
              v-model="risk.wealthAccInsurant.insuredSex"
              :options="[
                { text: '男', value: '1' },
                { text: '女', value: '2' }
              ]"
            />
          </div>
        </van-cell>
        <van-cell class="item" required v-if="false">
          <span slot="title" class="item-title">健康状态</span>
          <div class="item-right">
            <whether-select
              v-model="risk.wealthAccInsurant.healthStatus"
              :options="[
                { text: '健康', value: '1' },
                { text: '亚健康', value: '2' }
              ]"
            />
          </div>
        </van-cell>
        <van-cell required class="item">
          <span slot="title" class="item-title">证件号码</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model="risk.wealthAccInsurant.identityNo"
              placeholder="请输入证件号码"
              :maxlength="
                risk.wealthAccInsurant.identityType == '111' ? 18 : 32
              "
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入证件号码')"
            />
          </div>
        </van-cell>
        <van-cell class="item">
          <span slot="title" class="item-title">移动电话</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model="risk.wealthAccInsurant.phone"
              placeholder="请输入移动电话"
              maxlength="11"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入移动电话')"
            />
          </div>
        </van-cell>
      </div>
      <!-- 代理信息 -->
      <div class="group">
        <van-cell class="item">
          <span slot="title" class="item-title">是否代理</span>
          <div class="item-right">
            <whether-select
              :disabled="isAgentUser"
              v-model="risk.wealthAccChannel.isProxy"
              :options="[
                { text: '代理', value: '1' },
                { disabled: isAgentUser, text: '非代理', value: '0' }
              ]"
              @change="changeIsProxy"
            />
          </div>
        </van-cell>
        <van-cell
          class="item"
          v-if="risk.wealthAccChannel.isProxy == '1'"
          is-link
          required
          @click="selectAgent"
        >
          <span slot="title" class="item-title">代理人</span>
          <div class="item-right">
            <span
              :class="{
                'item-placeholder': !risk.wealthAccChannel.agentName
              }"
            >
              {{ risk.wealthAccChannel.agentName }}
            </span>
          </div>
        </van-cell>
        <van-cell
          class="item"
          v-if="risk.wealthAccChannel.isProxy == '0'"
          is-link
          required
          @click="selectAgent"
        >
          <span slot="title" class="item-title">直销/直团渠道</span>
          <div class="item-right">
            <span
              :class="{
                'item-placeholder': !risk.wealthAccChannel.businessNatureCode
              }"
            >
              {{ risk.wealthAccChannel.businessNatureName }}
            </span>
          </div>
        </van-cell>
      </div>
      <div class="group" v-if="areaCode != '3301' && isDefaultMerge == 'Y'">
        <van-cell class="item">
          <span slot="title" class="item-title">与车险保单合并打印</span>
          <div class="item-right">
            <whether-select
              v-model="risk.wealthAccMergePrint"
              :options="[
                { text: '是', value: '1' },
                { text: '否', value: '0' }
              ]"
            />
          </div>
        </van-cell>
      </div>
    </div>
    <div style="height:80px;">
      <confirmBottom @click="saveRisk" />
    </div>
    <!-- 方案选择 -->
    <van-popup v-if="edit" v-model="edit" closeable position="bottom">
      <div class="edit-container">
        <van-cell class="item">
          <span slot="title" class="edit-title">
            {{ editItem.formulaGroupName }}
          </span>
        </van-cell>
        <van-cell class="item item-projects" @click="setPicker('solution')">
          <span slot="title" class="item-title">产品方案</span>
          <div class="item-right">
            <!-- <whether-select
              v-model="editItem.formulaCode"
              :options="solutions"
              @change="changeSolution"
            /> -->
            <span
              :class="{
                'item-placeholder': !editItem.formulaCode
              }"
            >
              {{ $utils.findText(solutions, editItem.formulaCode) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
        </van-cell>
        <van-cell class="item">
          <span slot="title" class="item-title">保额/座</span>
          <div class="item-right">
            <span>{{ jointSalesSolutionAmount }}</span>
            <span>元</span>
          </div>
        </van-cell>
        <van-cell class="item">
          <span slot="title" class="item-title">保费</span>
          <div class="item-right">
            <span>
              {{ jointSalesSolutionPremium }}
            </span>
            <span>元/份</span>
          </div>
        </van-cell>
        <van-cell class="item">
          <span slot="title" class="item-title">
            份数(最多{{ jointSalesSolution.maxCount }}份)
          </span>
          <div class="item-right">
            <van-stepper
              v-model="editItem.count"
              integer
              disable-input
              :min="jointSalesSolution.minCount"
              :max="jointSalesSolution.maxCount"
              @change="changeCount"
            />
          </div>
        </van-cell>
        <van-cell class="item last-item" v-if="jointSalesSolutionItems.length">
          <span slot="title" class="item-title">保障内容</span>
        </van-cell>
        <div class="security-content-title">
          <div>保障责任</div>
          <div>保额(元)</div>
        </div>
        <div class="security-content">
          <div
            class="security-content-item"
            v-for="item in jointSalesSolutionItems"
            :key="item.clauseCode"
          >
            <div>{{ item.kindName }}</div>
            <div>{{ item.defaultAmount || "0" }}</div>
          </div>
        </div>
        <confirmBottom @click="selectedSolution" />
      </div>
    </van-popup>
    <van-popup v-model="dataPickerShow" position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :columns="pickerData"
        @confirm="dataPickerConfirm"
        @cancel="dataPickerCancel"
      />
    </van-popup>
    <van-popup v-model="datePickerShow" position="bottom" :overlay="true">
      <van-datetime-picker
        type="date"
        v-model="currentDate"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="datePickerConfirm"
        @cancel="datePickerCancel"
      />
    </van-popup>
  </div>
</template>
<script>
import WhetherSelect from "../vehicleInfo/whetherSelect";
import confirmBottom from "@/components/confirmBottom.vue";
import Config from "@src/common/json/newCoreDataDictionary";
import { Channel } from "@src/store/modules/channel/channel";
import {
  getAccidentalEstateInsurance,
  getMergePrint
} from "@src/http/module/vehicle";
import { Stepper } from "vant";
import { mapMutations, mapState } from "vuex";
import { Plan } from "../../../store/modules/vehicle/vehicle";
import { trackMap } from "@src/utils/index.js";
import { getAge } from "../../../utils";
export default {
  components: {
    confirmBottom,
    [Stepper.name]: Stepper,
    [WhetherSelect.name]: WhetherSelect
  },
  data() {
    let identifyTypes = Object.keys(Config.identifyType01).map(key => ({
      text: Config.identifyType01[key],
      value: key
    }));
    identifyTypes.unshift({ text: "请选择", value: "" });
    return {
      identifyTypes,
      formulas: [],
      editItem: {},
      jointSalesSolution: {},
      isDefaultMerge: "Y",
      classCode: "",
      pickerData: [],
      dataPickerType: "",
      dataPickerShow: false,
      currentDate: new Date(),
      minDate: new Date(),
      maxDate: new Date(),
      datePickerType: "",
      datePickerShow: false,
      requiredAge: false,
      minAge: 0,
      maxAge: 0
    };
  },
  activated() {
    this.init();
  },
  filters: {
    date(date) {
      return date && typeof date === "string" ? date.substr(0, 10) : "";
    }
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      car: ({ car }) => car,
      carOwner: ({ carOwner }) => carOwner,
      projectChannel: ({ projectChannel }) => projectChannel,
      quote: ({ quoteInfo }) => quoteInfo
    }),
    risk: {
      get: function() {
        let plan = null;
        if (this.quote.planCode) {
          plan = this.quote.plans.find(
            plan => plan.planCode === this.quote.planCode
          );
        } else if (this.quote.plans.length) {
          plan = this.quote.plans[0];
        }
        if (!plan) {
          plan = { ...Plan, planCode: this.quote.planCode };
        }
        return plan;
      },
      set: function(plan) {
        let active = this.quote.plans.findIndex(
          plan => plan.planCode === this.quote.planCode
        );
        if (active != -1) {
          this.$set(
            this.quote.plans,
            active,
            Object.assign(this.quote.plans[active], plan)
          );
        }
      }
    },
    isAgentUser() {
      const userInfo = this.localStorage.get("userInfo", {});
      const agentCode = userInfo.agentCode || "";
      return !!agentCode;
    },
    areaCode() {
      let userInfo = this.localStorage.get("userInfo", {});
      let comCode = userInfo.comCode || "";
      return comCode.substr(0, 4);
    },
    edit: {
      get: function() {
        return !!Object.keys(this.editItem).length;
      },
      set: function() {
        this.editItem = {};
      }
    },
    solutions() {
      return (this.editItem.jointSalesSolutionVOS || []).map(i => ({
        text: i.formulaName,
        value: i.formulaCode
      }));
    },
    jointSalesSolutionItems() {
      let jointSalesSolutionItems = [];
      try {
        jointSalesSolutionItems =
          this.jointSalesSolution.jointSalesSolutionItems || [];
      } catch (error) {}
      return jointSalesSolutionItems;
    },
    jointSalesSolutionPremium() {
      if (this.jointSalesSolution) {
        if (this.editItem.formulaGroupName == "驾乘无忧") {
          return this.jointSalesSolution.premium * this.car.seatCount;
        }
        return this.jointSalesSolution.premium;
      }
      return "";
    },
    jointSalesSolutionAmount() {
      if (this.jointSalesSolution) {
        if (this.editItem.formulaGroupName == "驾乘无忧") {
          return this.jointSalesSolution.amount * this.car.seatCount;
        }
        return this.jointSalesSolution.amount;
      }
      return "";
    }
  },
  methods: {
    ...mapMutations("vehicle", ["setWealthAccRisk"]),
    ...mapMutations("channel", ["setChannel"]),
    init() {
      getAccidentalEstateInsurance({
        orderId: this.orderId,
        comCode: this.localStorage.get("userInfo", {}).comCode,
        userCode: this.localStorage.get("userInfo", {}).userCode,
        maxSeatCount: this.car.seatCount
      }).then(
        res => {
          if (res.code === "1") {
            this.formulas = res.data;
            let formulaGroupIndex = this.formulas.findIndex(
              i => i.formulaGroupCode === this.risk.wealthAccFormulaGroupCode
            );
            if (formulaGroupIndex != -1) {
              let formulaGroup = this.formulas[formulaGroupIndex];
              this.classCode = formulaGroup.secondLine;
              let solutions = formulaGroup.jointSalesSolutionVOS;
              let solution = solutions.find(
                solution =>
                  solution.formulaCode == this.risk.wealthAccFormulaCode
              );
              if (solution) {
                let count = solution.count || solution.minCount;
                let premium = solution.premium * count;
                if (formulaGroup.formulaGroupName == "驾乘无忧") {
                  premium *= this.car.seatCount;
                }
                if (
                  solution.hasOwnProperty("insuredAgeLower") &&
                  solution.insuredAgeLower
                ) {
                  this.requiredAge = true;
                  this.minAge = solution.insuredAgeLower;
                  this.maxAge = solution.insuredAgeUpper;
                  console.log();
                } else {
                  this.requiredAge = false;
                  this.minAge = 0;
                  this.maxAge = 0;
                }
                this.risk.wealthAccPremium = premium;
                this.$set(
                  this.formulas,
                  formulaGroupIndex,
                  Object.assign(formulaGroup, {
                    formulaCode: this.risk.wealthAccFormulaCode,
                    formulaName: this.risk.wealthAccFormulaName,
                    count,
                    premium
                  })
                );
              }
            }
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    setDatePicker(type) {
      if (type === "dateOfBirth") {
        this.minDate = new Date();
        this.minDate.setFullYear(this.minDate.getFullYear() - this.maxAge);
        this.maxDate = new Date();
        this.maxDate.setFullYear(this.maxDate.getFullYear() - this.minAge);
      }
      this.datePickerType = type;
      this.datePickerShow = true;
    },
    datePickerConfirm(date) {
      this.risk.wealthAccInsurant[this.datePickerType] = date.format(
        "yyyy-MM-dd"
      );
      if (this.datePickerType === "dateOfBirth") {
        this.risk.wealthAccInsurant.age = getAge(date.format());
      }
      this.datePickerCancel();
    },
    datePickerCancel() {
      this.datePickerType = "";
      this.datePickerShow = false;
    },
    setPicker(type) {
      if (type == "identifyType") {
        this.pickerData = this.identifyTypes;
      } else if (type == "solution") {
        this.pickerData = this.solutions;
      }
      this.dataPickerType = type;
      this.dataPickerShow = true;
    },
    dataPickerConfirm(data) {
      if (this.dataPickerType == "identifyType") {
        this.risk.wealthAccInsurant.identityType = data.value;
      } else if (this.dataPickerType == "solution") {
        this.changeSolution(data.value);
      }
      this.dataPickerCancel();
    },
    dataPickerCancel() {
      this.pickerData = [];
      this.dataPickerType = "";
      this.dataPickerShow = false;
    },
    checked(item) {
      return item.formulaGroupCode === this.risk.wealthAccFormulaGroupCode;
    },
    input(e, type) {
      if (
        this.risk.wealthAccInsurant.identityType == "111" &&
        type == "identityNo"
      ) {
        // this.wealthAccInsurant.
      }
    },
    changeCount(count) {
      if (count < this.jointSalesSolution.minCount) {
        this.editItem.count = this.jointSalesSolution.minCount;
        return this.$toast(
          "最少购买" + this.jointSalesSolution.minCount + "份"
        );
      } else if (count > this.jointSalesSolution.maxCount) {
        this.editItem.count = this.jointSalesSolution.maxCount;
        return this.$toast(
          "最多购买" + this.jointSalesSolution.maxCount + "份"
        );
      }
      this.editItem.premium =
        this.jointSalesSolution.premium * this.editItem.count;
      this.editItem.amount =
        this.jointSalesSolution.amount * this.editItem.count;
      if (this.editItem.formulaGroupName == "驾乘无忧") {
        this.editItem.premium *= this.car.seatCount;
        this.editItem.amount *= this.car.seatCount;
      }
    },
    selectedRisk(item) {
      this.editItem = Object.assign({}, item);
      if (!item.jointSalesSolutionVOS.length) {
        return this.$dialog.alert({ message: "没有方案" });
      }

      this.jointSalesSolution = item.jointSalesSolutionVOS.find(
        i => i.formulaCode === item.formulaCode
      );
      if (!this.jointSalesSolution) {
        this.jointSalesSolution = this.editItem.jointSalesSolutionVOS[0] || {};
        this.changeSolution(this.jointSalesSolution.formulaCode);
      }
    },
    changeSolution(formulaCode) {
      this.jointSalesSolution = this.editItem.jointSalesSolutionVOS.find(
        i => i.formulaCode === formulaCode
      );
      this.editItem.formulaCode = this.jointSalesSolution.formulaCode;
      this.editItem.formulaName = this.jointSalesSolution.formulaName;
      this.editItem.count = this.jointSalesSolution.minCount;
      this.editItem.premium =
        this.jointSalesSolution.premium * this.editItem.count;
      this.editItem.amount =
        this.jointSalesSolution.amount * this.editItem.count;
      if (this.editItem.formulaGroupName == "驾乘无忧") {
        this.editItem.premium *= this.car.seatCount;
        this.editItem.amount *= this.car.seatCount;
      }
    },
    selectAgent() {
      const query = { risk: "DEA" };
      this.$root.$once("channelChange", this.setChannelConfirm);
      this.setChannel(Object.assign({}, this.risk.wealthAccChannel));
      this.$router.push({ name: "vehicleChannel", query });
    },
    setChannelConfirm(channel) {
      console.warn("setChannelConfirm", channel);
      this.risk.wealthAccChannel = JSON.parse(JSON.stringify(channel));
    },
    selectedSolution() {
      let save = {
        wealthAccSeatCount: this.car.seatCount, //联合销售计算保费时使用的座位数
        wealthAccUnitPrice: this.jointSalesSolution.premium,
        wealthAccBuyNum:
          this.editItem.count || this.jointSalesSolution.minCount, //联合销售购买份数
        wealthAccAmount: this.editItem.amount,
        wealthAccFormulaCode: this.jointSalesSolution.formulaCode, //联合销售方案代码
        wealthAccFormulaName: this.jointSalesSolution.formulaName, //联合销售方案名称
        wealthAccPremium: this.editItem.premium, //联合销售保费
        wealthAccFormulaGroupCode: this.editItem.formulaGroupCode, //联合销售方案组代码
        wealthAccFormulaGroupName: this.editItem.formulaGroupName, //联合销售方案组名称
        wealthAccRiskCode: this.editItem.productCode, //险种代码
        wealthAccRiskName: this.editItem.formulaGroupName //险种名称
      };
      if (
        this.jointSalesSolution.hasOwnProperty("insuredAgeLower") &&
        this.jointSalesSolution.insuredAgeLower
      ) {
        this.requiredAge = true;
        this.minAge = this.jointSalesSolution.insuredAgeLower;
        this.maxAge = this.jointSalesSolution.insuredAgeUpper;
      } else {
        this.requiredAge = false;
        this.minAge = 0;
        this.maxAge = 0;
      }
      let index = this.formulas.findIndex(
        i => i.formulaGroupCode === this.editItem.formulaGroupCode
      );
      if (index != -1) {
        let defaultAmount = this.jointSalesSolutionAmount;
        this.$set(
          this.formulas,
          index,
          Object.assign({}, this.editItem, { defaultAmount })
        );
      }
      this.classCode = this.editItem.secondLine;
      this.risk = Object.assign({}, this.risk, save);
      this.jointSalesSolution = {};
      this.editItem = {};
      let codeSuffix = "A";
      if (this.car.carKindCode.charAt(0) == "4") {
        codeSuffix = "B";
      } else if (this.car.carKindCode.charAt(0) == "5") {
        codeSuffix = "C";
      }
      getMergePrint({
        businessAttribute: this.risk.wealthAccChannel.businessNatureCode,
        orgCode: this.localStorage.get("userInfo", {}).branchCode,
        productCode: "DE" + codeSuffix,
        classCode: this.classCode
      }).then(
        res => {
          if (res.code == "1" && res.data) {
            //isMergePrint: "N",//是否显示合并打印(Y-是，N-否)
            //isDefaultMerge: "N"//是否默认勾选合并打印
            this.isDefaultMerge = res.data.isMergePrint;
            this.risk.wealthAccMergePrint =
              res.data.isDefaultMerge == "N" ? "0" : "1";
            //埋点
            trackMap("c_car_pro_program_adjustment", "车险产品财意险方案调整", {
              c_salesperson_type: "N",
              c_market_type: "Y",
              c_market_name: "",
              c_market_id: "",
              c_event_category: "车险出单"
            });
          } else {
            // this.$dialog.alert({ message: "" })
          }
        },
        error => {}
      );
    },
    changeIsProxy(isProxy) {
      this.risk.wealthAccChannel = Object.assign({}, Channel, {
        agentAgreementCode: "",
        agentAgreementName: "",
        agentCode: "",
        agentName: "",
        businessCode: "",
        businessName: "",
        businessType: "",
        channelName: "",
        isProxy
      });
    },
    saveRisk() {
      //验证/缓存数据
      if (this.validate()) {
        this.setWealthAccRisk(this.risk);
        this.$router.back();
      }
    },
    validate() {
      let props = {
        wealthAccBuyNum: { required: "联合销售购买份数" },
        wealthAccFormulaGroupCode: { required: "联合销售方案组代码" },
        wealthAccFormulaCode: { required: "联合销售方案代码" },
        wealthAccRiskCode: { required: "联合销售险种代码" }
      };
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(props[prop], this.risk[prop]);
          if (!status) {
            throw msg;
          }
        });
      } catch (error) {
        this.$toast.fail(error);
        return false;
      }
      if (this.requiredAge) {
        console.log(this.risk.wealthAccInsurant.age, this.minAge, this.maxAge);
        if (
          this.risk.wealthAccInsurant.age < this.minAge ||
          this.risk.wealthAccInsurant.age > this.maxAge
        ) {
          this.$toast.fail("被保人年龄不符合要求");
          return false;
        }
      }
      if (!this.risk.wealthAccFormulaGroupName.includes("驾乘无忧")) {
        props = {
          identityNo: { required: "证件号码" },
          identityType: { required: "证件类型" },
          insurantName: { required: "被保人姓名" }
        };
        try {
          Object.keys(props).forEach(prop => {
            let { status, msg } = this.$validate(
              props[prop],
              this.risk.wealthAccInsurant[prop]
            );
            if (!status) {
              throw msg;
            }
          });
        } catch (error) {
          this.$toast.fail(error);
          return false;
        }
      }
      props = {
        isProxy: { required: "是否代理" }
      };
      if (this.risk.wealthAccChannel.isProxy == "1") {
        props.agentCode = { required: "代理人" };
        props.agentAgreementCode = { required: "代理协议" };
      }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(
            props[prop],
            this.risk.wealthAccChannel[prop]
          );
          if (!status) {
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
.vehicle-wealth-acc-risk {
  width: 100%;
}
.content {
  display: flex;
  flex-direction: column;
  width: 100%;
  // height: calc(100% - 77px - 80px);
  overflow-y: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  div {
    display: flex;
  }
  .risk-contents {
    position: relative;
    flex-direction: column;
    padding: 15px 17px 0;
    background: white;
    margin-bottom: 10px;
    .risk-kind-info {
      color: #8e8e8e;
      font-size: 12px;
      & > span {
        display: flex;
      }
      & > span:first-child {
        padding-left: 20px;
        width: 110px;
      }
      & > span:not(:first-child) {
        flex: 1;
        justify-content: center;
      }
      & > span:nth-child(2) {
        flex: 1.5;
        justify-content: center;
      }
    }
    .risk-kind-item {
      min-height: 48px;
      align-items: center;
      position: relative;
      color: #333333;
      font-size: 14px;
      & > div {
        display: flex;
        align-items: center;
      }
      & > div:first-child {
        width: 130px;
        justify-content: flex-start;
      }
      & > div:not(:first-child) {
        flex: 1;
        justify-content: center;
      }
      & > div:nth-child(2) {
        flex: 1.5;
        justify-content: center;
        word-break: break-all;
        text-overflow: -o-ellipsis-lastline;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        line-clamp: 2;
        -webkit-box-orient: vertical;
      }
      // .item-dropdown-icon {
      //   position: relative;
      //   width: 0;
      //   height: 0;
      //   border: 3px solid;
      //   margin-top: -3px;
      //   margin-left: 10px;
      //   border-color: transparent transparent #b8b8b8 #b8b8b8;
      //   -webkit-transform: rotate(-45deg);
      //   transform: rotate(-45deg);
      //   opacity: 0.8;
      //   content: "";
      // }
    }
    .risk-kind-item:not(:last-child)::after {
      position: absolute;
      box-sizing: border-box;
      content: " ";
      pointer-events: none;
      right: 0;
      bottom: 0;
      left: 16px;
      border-bottom: 1px solid #ebedf0;
      -webkit-transform: scaleY(0.5);
      transform: scaleY(0.5);
    }
  }
  .group {
    flex-direction: column;
    margin-bottom: 10px;
  }
  .item {
    min-height: 48px;
    align-items: center;
    .item-placeholder {
      color: #ababab;
    }
    .item-title {
      font-size: 14px;
      color: #333333;
    }
    .item-right {
      flex: 1;
      flex-direction: row;
      word-break: keep-all;
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
      .item-right-arrow {
        margin-left: 8px;
      }
    }
  }
}
.edit-container {
  flex: 1;
  max-height: 400px;
  flex-direction: column;
  padding-bottom: 80px;
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  div {
    display: flex;
  }
  .edit-title {
    font-weight: 600;
  }
  .last-item::after {
    border: 0;
  }
  .item {
    min-height: 48px;
  }
  .item-right {
    flex: 1;
    overflow: hidden;
    flex-direction: row;
    word-break: keep-all;
    align-items: center;
    justify-content: flex-end;
    font-size: 14px;
    color: #333333;
  }
  .van-cell__value {
    flex: 2;
  }
  .security-content-title {
    display: flex;
    flex: 1;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    color: #8e8e8e;
    font-size: 12px;
    line-height: 17px;
    padding: 0 18px 8px;
  }
  .security-content {
    // max-height: 200px;
    // overflow: scroll;
    padding: 0 18px;
    flex-direction: column;
    .security-content-item {
      flex: 1;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      margin-top: 8px;
      color: #545454;
      font-size: 14px;
      line-height: 23px;
    }
    .security-content-item:first-child {
      margin-top: 0;
    }
  }
}
input::-webkit-input-placeholder,
textarea::-webkit-input-placeholder {
  color: #ababab;
  font-size: 14px;
}

input:-moz-placeholder,
textarea:-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}

input::-moz-placeholder,
textarea::-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}
input:-ms-input-placeholder,
textarea:-ms-input-placeholder {
  color: #ababab;
  font-size: 14px;
}
</style>
