<!--
 * @Author: heweiwei
 * @Page: 商业险险别选择
 * @Date: 2020-03-04 14:28:10
 * @Last Modified by: King
 * @Last Modified time: 2020-06-30 16:28:09
 * @FilePath: /src/views/vehicle/selectProduct/vehicleCommercial.vue
-->
<template>
  <div class="vehicle-commercial">
    <nav-bar type="vehicle" title="商业险" @clickBack="$router.back()" />
    <div class="content">
      <van-cell
        v-for="(item, index) in kindCodeList"
        :key="item.kindCode"
        class="item"
        @click.stop="showPopup(item, index)"
      >
        <div slot="title" class="item-title">
          <div
            class="item-checkbox-container"
            @click.stop="changeItemSelected(item, index)"
          >
            <van-checkbox
              class="item-checkbox"
              v-model="item.selected"
              icon-size="16px"
            >
            </van-checkbox>
          </div>
          <span class="item-checkbox-title">
            {{ item.kindName | kindName }}
          </span>
          <div
            v-if="item.selected && item.kindFlag === '1'"
            class="item-title-flag"
          >
            不计免赔
          </div>
        </div>
        <!-- 无法找到第三方特约险 -->
        <div v-if="item.kindCode === 'C100134'" class="item-right">
          <div></div>
        </div>
        <!-- 指定修理厂险 -->
        <div v-else-if="item.kindCode === 'C100135'" class="item-right">
          <div>
            {{ item.brokenCode == "2" ? "进口" : "国产" }}
            {{ item.deductibleRate }}%
          </div>
        </div>
        <!-- 玻璃单独破碎险 -->
        <div v-else-if="item.kindCode === 'C100126'" class="item-right">
          <div>
            {{
              $utils.findText(
                enumValues[item.kindCode].brokenCode,
                item.brokenCode
              )
            }}
          </div>
        </div>
        <div v-else-if="item.selected" class="item-right">
          <div class="item-right-drop">
            {{ item.amount }}
          </div>
        </div>
      </van-cell>
      <div style="height: 80px;" />
      <confirmBottom @click="saveRisks" />
      <van-popup v-model="show" position="bottom" closeable>
        <div class="popup-content">
          <van-cell class="item group">
            <span slot="title" class="item-title">{{ editItem.kindName }}</span>
            <!-- <div class="item-right">
              <van-icon name="close" @click="show = false" />
            </div> -->
          </van-cell>
          <van-cell
            class="item"
            v-if="enumValues[editItem.kindCode].showKindFlag"
          >
            <span slot="title" class="item-title">不计免赔</span>
            <whether-select
              v-model="editItem.kindFlag"
              :options="[
                { text: '是', value: '1' },
                { text: '否', value: '0' }
              ]"
            />
          </van-cell>
          <!-- 车辆损失保险(全面型) -->
          <van-cell
            class="item last-item"
            v-if="editItem.kindCode === 'C101011'"
          >
            <span slot="title" class="item-title">绝对免赔率</span>
          </van-cell>
          <!-- 车上人员责任保险(乘客) -->
          <van-cell v-if="editItem.kindCode === 'C100124'" class="item">
            <span slot="title" class="item-title">投保座位数</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model.number="editItem.quantity"
                placeholder="请输入"
                maxlength="3"
                @focus="e => (e.target.placeholder = '')"
                @blur="e => (e.target.placeholder = '请输入')"
              />
              <span class="item-right-input-unit">座</span>
            </div>
          </van-cell>
          <van-cell
            class="item last-item"
            v-if="editItem.kindCode === 'C100124'"
          >
            <span slot="title" class="item-title">每座保额</span>
          </van-cell>
          <!-- 车辆损失保险 -->
          <div v-if="editItem.kindCode === 'C100121'" class="select-container">
            <div class="select-title">自负额</div>
            <div class="select-unfolding-items">
              <div
                v-for="deductibleValue in enumValues[editItem.kindCode]
                  .deductibleValue"
                :key="deductibleValue.text"
                @click="changeDeductibleValue(deductibleValue)"
                :class="{
                  'select-unfolding-item': true,
                  'item-selected':
                    editItem.deductibleValue == deductibleValue.value
                }"
              >
                {{ deductibleValue.text }}
              </div>
            </div>
          </div>
          <!-- 修理期间费用补偿险 -->
          <van-cell v-if="editItem.kindCode === 'C100131'" class="item">
            <span slot="title" class="item-title">投保天数</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model.number="editItem.quantity"
                placeholder="请输入"
                maxlength="3"
                @input="input"
                @focus="e => (e.target.placeholder = '')"
                @blur="e => (e.target.placeholder = '请输入')"
              />
              <span class="item-right-input-unit">天</span>
            </div>
          </van-cell>
          <van-cell v-if="editItem.kindCode === 'C100131'" class="item">
            <span slot="title" class="item-title">每天保额</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model.number="editItem.unitAmount"
                placeholder="请输入"
                maxlength="8"
                @input="input"
                @focus="e => (e.target.placeholder = '')"
                @blur="e => (e.target.placeholder = '请输入')"
              />
              <span class="item-right-input-unit">元</span>
            </div>
          </van-cell>
          <!-- 玻璃破碎险 -->
          <van-cell v-if="editItem.kindCode === 'C100126'" class="item">
            <span slot="title" class="item-title">玻璃单独破碎险</span>
            <span class="item-right">
              {{
                $utils.findText(
                  enumValues[editItem.kindCode].brokenCode,
                  editItem.brokenCode
                )
              }}
            </span>
          </van-cell>
          <!-- 保额 枚举值 -->
          <van-cell
            class="item"
            v-if="Array.isArray(enumValues[editItem.kindCode].amount)"
            @click="folding = false"
          >
            <span slot="title" class="item-title">保险金额</span>
            <div class="item-right">
              <div>
                <div :class="{ 'item-placeholder': editItem.amount == '' }">
                  {{ editItem.amount }}
                </div>
              </div>
              <span class="item-right-input-unit">元</span>
            </div>
          </van-cell>
          <!-- 枚举 -->
          <div v-if="isEnumValue" class="select-container">
            <div v-if="folding || enumValue.length < 3">
              <div
                v-for="index in Math.min(
                  editItem.kindCode == 'C100122' ? 3 : 2,
                  enumValue.length
                )"
                :key="index"
                class="select-folding-items"
              >
                <div
                  @click="enumItemOnClick(index)"
                  :class="{
                    'select-folding-item': true,
                    'item-selected':
                      editItem.kindCode == 'C100122'
                        ? editItem[enumKey] == 500000 * index
                        : editItem[enumKey] == enumValue[index - 1].value
                  }"
                >
                  {{
                    editItem.kindCode == "C100122"
                      ? index * 5 + "0万"
                      : enumValue[index - 1].text
                  }}
                </div>
              </div>
            </div>
            <div v-else class="select-unfolding-items">
              <div
                v-for="item in enumValue"
                :key="item.text"
                @click="editItem[enumKey] = item.value"
                :class="{
                  'select-unfolding-item': true,
                  'item-selected': editItem[enumKey] == item.value
                }"
              >
                {{ item.text }}
              </div>
            </div>
            <van-icon
              v-if="enumValue.length > 2"
              :name="folding ? 'arrow-down' : 'arrow-up'"
              class="folding-icon"
              @click="toggleFolding"
            />
          </div>
          <!-- 指定修理厂险 -->
          <van-cell v-if="editItem.kindCode === 'C100135'" class="item">
            <span slot="title" class="item-title">浮动比例</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model.number="editItem.deductibleRate"
                :placeholder="
                  editItem.brokenCode == '2' ? '15％－60％' : '10％－30％'
                "
                maxlength="2"
                @input="e => input(e, 'C100135')"
                @focus="e => (e.target.placeholder = '')"
                @blur="
                  e =>
                    (e.target.placeholder =
                      editItem.brokenCode == '2' ? '15％－60％' : '10％－30％')
                "
              />
              <span class="item-right-input-unit">%</span>
            </div>
          </van-cell>
          <!-- 保额 -->
          <van-cell
            class="item"
            v-if="enumValues[editItem.kindCode].amount !== 'none'"
            v-show="!Array.isArray(enumValues[editItem.kindCode].amount)"
          >
            <span slot="title" class="item-title">保险金额</span>
            <div class="item-right">
              <input
                v-if="enumValues[editItem.kindCode].amount === 'input'"
                class="item-right-input"
                v-model.number="editItem.amount"
                placeholder="请输入"
                maxlength="8"
                @focus="e => (e.target.placeholder = '')"
                @blur="e => (e.target.placeholder = '请输入')"
              />
              <div v-else-if="enumValues[editItem.kindCode].amount === 'calc'">
                {{ editItem.amount }}
              </div>
              <span class="item-right-input-unit">元</span>
            </div>
          </van-cell>
          <div style="height:100px;" />
          <confirmBottom @click="addInsuanceKindItem(editItem)" />
        </div>
      </van-popup>
      <van-popup v-model="popupInfo">
        <div class="popupInfoTop">
          号“粤G6K119”的保单发生重复投保，与其重复投保的其他公司的保单信息如下：
        </div>
        <div class="popupCenter">
          投保确认码:02BGIC4400190015468487951 保单号：
        </div>
        <div @click="popupInfo = false" class="ensure">确定</div>
      </van-popup>
    </div>
  </div>
</template>
<script>
import confirmBottom from "@/components/confirmBottom.vue";
import WhetherSelect from "../vehicleInfo/whetherSelect.vue";
import { Risk, initBusinessRisks } from "@src/store/modules/vehicle/vehicle";
import { mapMutations, mapState } from "vuex";
export default {
  components: {
    confirmBottom,
    [WhetherSelect.name]: WhetherSelect
  },
  data() {
    let kindCode = {
      C100121: "车辆损失保险",
      C100122: "第三者责任保险",
      C100125: "全车盗抢保险",
      C100123: "车上人员责任保险（司机）",
      C100124: "车上人员责任保险（乘客）",
      C100127: "自燃损失险",
      C100126: "玻璃单独破碎险",
      C100130: "发动机涉水损失险",
      C100129: "车身划痕损失险",
      C100133: "精神损害抚慰金损失险",
      C100135: "指定修理厂险",
      C100131: "修理期间费用补偿险",
      C100128: "新增加设备损失险",
      C101017: "三者险法定节假日限额翻倍险",
      C100134: "无法找到第三方特约险"
    };
    let enumValues = {
      "": {
        showKindFlag: true,
        amount: "none"
      },
      C101011: {
        showKindFlag: false,
        amount: "input"
      },
      C101012: {
        showKindFlag: false,
        amount: "none"
      },
      C101015: {
        showKindFlag: true,
        amount: "calc"
      },
      C100121: {
        showKindFlag: true,
        amount: "input",
        deductibleValue: [
          { text: "0元", value: "0" },
          { text: "300元", value: "300" },
          { text: "500元", value: "500" },
          { text: "1000元", value: "1000" },
          { text: "2000元", value: "2000" }
        ]
      },
      C100122: {
        showKindFlag: true,
        amount: [
          { value: "50000", text: "5万" },
          { value: "100000", text: "10万" },
          { value: "150000", text: "15万" },
          { value: "200000", text: "20万" },
          { value: "300000", text: "30万" },
          { value: "500000", text: "50万" },
          { value: "1000000", text: "100万" },
          { value: "1500000", text: "150万" },
          { value: "2000000", text: "200万" },
          { value: "2500000", text: "250万" },
          { value: "3000000", text: "300万" },
          { value: "3500000", text: "350万" },
          { value: "4000000", text: "400万" },
          { value: "4500000", text: "450万" },
          { value: "5000000", text: "500万" },
          { value: "5500000", text: "550万" },
          { value: "6000000", text: "600万" },
          { value: "6500000", text: "650万" },
          { value: "7000000", text: "700万" },
          { value: "7500000", text: "750万" },
          { value: "8000000", text: "800万" },
          { value: "8500000", text: "850万" },
          { value: "9000000", text: "900万" },
          { value: "9500000", text: "950万" },
          { value: "10000000", text: "1000万" }
        ]
      },
      C100125: {
        showKindFlag: true,
        amount: "input"
      },
      C100123: {
        showKindFlag: true,
        amount: [
          { value: "10000", text: "1万" },
          { value: "20000", text: "2万" },
          { value: "30000", text: "3万" },
          { value: "40000", text: "4万" },
          { value: "50000", text: "5万" },
          { value: "60000", text: "6万" },
          { value: "70000", text: "7万" },
          { value: "80000", text: "8万" },
          { value: "90000", text: "9万" },
          { value: "100000", text: "10万" },
          { value: "150000", text: "15万" },
          { value: "200000", text: "20万" },
          { value: "300000", text: "30万" },
          { value: "400000", text: "40万" },
          { value: "500000", text: "50万" },
          { value: "1000000", text: "100万" }
        ]
      },
      C100124: {
        showKindFlag: true,
        amount: "calc",
        unitAmount: [
          { value: "10000", text: "1万" },
          { value: "20000", text: "2万" },
          { value: "30000", text: "3万" },
          { value: "40000", text: "4万" },
          { value: "50000", text: "5万" },
          { value: "60000", text: "6万" },
          { value: "70000", text: "7万" },
          { value: "80000", text: "8万" },
          { value: "90000", text: "9万" },
          { value: "100000", text: "10万" },
          { value: "150000", text: "15万" },
          { value: "200000", text: "20万" },
          { value: "300000", text: "30万" },
          { value: "400000", text: "40万" },
          { value: "500000", text: "50万" },
          { value: "1000000", text: "100万" }
        ]
      },
      C100127: {
        showKindFlag: true,
        amount: "input"
      },
      C100126: {
        showKindFlag: false,
        amount: "none",
        brokenCode: [
          { value: "1", text: "国产玻璃" },
          { value: "2", text: "进口玻璃" }
          // { value: "3", text: "国产特质玻璃" },
          // { value: "4", text: "进口特质玻璃" }
        ]
      },
      C100130: {
        showKindFlag: true,
        amount: "none"
      },
      C100129: {
        showKindFlag: true,
        amount: [
          { value: "2000", text: "2千" },
          { value: "5000", text: "5千" },
          { value: "10000", text: "1万" },
          { value: "20000", text: "2万" }
        ]
      },
      C100133: {
        showKindFlag: true,
        amount: "input"
      },
      C100135: {
        showKindFlag: false,
        amount: "none",
        brokenCode: [
          { value: "1", text: "国产" },
          { value: "2", text: "进口" }
        ]
      },
      C100131: {
        showKindFlag: false,
        amount: "calc"
      },
      C100128: {
        showKindFlag: true,
        amount: "calc"
      },
      C101017: {
        showKindFlag: false,
        amount: "calc"
      },
      C100134: {
        showKindFlag: false,
        amount: "none"
      },
      C100148: {
        showKindFlag: false,
        amount: "none"
      },
      C100149: {
        showKindFlag: false,
        amount: "none"
      },
      C100132: {
        showKindFlag: false,
        amount: "input"
      }
    };
    const kindCodeList = Object.keys(kindCode).map(key => {
      let kindName = kindCode[key];
      return Object.assign({}, Risk, {
        kindCode: key, //险种代码
        kindName //险种名称
      });
    });
    console.log("data ===> vehicleType : ", this.vehicleType);
    return {
      kindCodeList,
      // kindCodeList: [],
      editItem: Object.assign({}, Risk),
      editIndex: -1,
      folding: true,
      popupInfo: false,
      enumValues,
      kindCode,
      areaCode: ""
    };
  },
  created() {
    const userInfo = this.localStorage.get("userInfo", {});
    const comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
  },
  activated() {
    const userInfo = this.localStorage.get("userInfo", {});
    const comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
    this.init();
    let { quoteInfo = {} } = this.$store.state.vehicle;
    let { plans = [] } = quoteInfo;
    let planIndex = plans.findIndex(i => i.planCode === quoteInfo.planCode);
    if (planIndex === -1) return;
    let plan = plans[planIndex];
    if ((plan.businessRisks || []).length) {
      this.kindCodeList = this.kindCodeList.map(item => {
        let selectedItem = plan.businessRisks.find(
          i => i.kindCode === item.kindCode
        );
        return selectedItem ? { ...selectedItem, selected: true } : item;
      });
    }
    console.log("activated");
    //特殊地区处理
    //货车
    const kindCode2 = {
      C100148: "起重、装卸、挖掘车辆损失扩展条款",
      C100149: "特种车辆固定设备、仪器损坏扩展条款",
      C100132: "车上货物责任险"
    };
    const kindCode3 = {
      C101011: "车辆损失保险（全面型）",
      C101012: "附加车轮单独损坏除外特约险（全面型）",
      C101015: "新增设备损失险（全面型）"
    };

    if (this.support && !this.kindCodeList.find(i => i.kindCode == "C101011")) {
      this.kindCodeList.unshift(
        ...Object.keys(kindCode3).map(key => {
          let kindName = kindCode3[key];
          return Object.assign({}, Risk, {
            kindCode: key, //险种代码
            kindName //险种名称
          });
        })
      );
    }

    if (this.van && !this.kindCodeList.find(i => i.kindCode == "C100148")) {
      this.kindCodeList.concat(
        Object.keys(kindCode2).map(key => {
          let kindName = kindCode2[key];
          return Object.assign({}, Risk, {
            kindCode: key, //险种代码
            kindName //险种名称
          });
        })
      );
    }

    //某些险种, 保险金额默认为车辆实际价格
    this.kindCodeList = initBusinessRisks(this.kindCodeList, {
      actualValue: this.car.actualValue,
      importFlag: this.car.importFlag,
      seatCount: this.car.seatCount,
      frameNo: this.car.frameNo,
      reset: false
    });
  },
  deactivated: function() {},
  watch: {
    show(show) {
      if (!show) {
        this.folding = true;
      }
    },
    "editItem.unitAmount"() {
      if (this.editItem.unitAmount && this.editItem.quantity) {
        this.calcAmount();
      }
    },
    "editItem.quantity"() {
      if (this.editItem.unitAmount && this.editItem.quantity) {
        this.calcAmount();
      }
    }
  },
  computed: {
    ...mapState("vehicle", {
      car: ({ car }) => car,
      carOwner: ({ carOwner }) => carOwner,
      vehicleType: ({ vehicleType }) => vehicleType
    }),
    support() {
      //此险种，只有宁波、湖北、江西、深圳存在此险
      let areaCode = this.areaCode;
      if (
        areaCode == "3302" ||
        areaCode == "4201" ||
        areaCode == "3601" ||
        areaCode == "4403"
      ) {
        console.warn(this.areaCode, "地区支持");
        //1.使用性质为家庭自用；车主类型为个人；
        if (this.car.useNatureCode == "85" && this.carOwner.custType == "01") {
          console.warn("使用性质为家庭自用；车主类型为个人");
          //  车辆种类是客车且座位数小于等于9座时，可投保车辆损失保险（全面型）。
          if (this.car.carKindCode == "11" && this.car.seatCount <= 9) {
            console.warn("车辆种类是客车且座位数小于等于9座时");
            return true;
          }
        }
        //  如果不满足投保条件，该责任不可见。
        /**
         * 2.该责任与车辆损失保险及其附加险
         *   玻璃单独破碎险、车身划痕损失险、新增加设备损失险、自燃损失险、
         *   发动机涉水损失险、车辆损失保险无法找到第三方特约险、不计免赔险、
         *   修理期间费用补偿险、指定修理厂险
         * 互斥，即不可同时承保。
         */
        //3.车辆损失保险（全面型）无绝对免赔额。
        //4.车辆损失保险（全面型）要与新增设备损失险（全面型）联动，
        //  即勾选了车辆损失保险（全面型），下面的新增设备损失险（全面型）自动勾选，保额保费默认为0，
        //  如果客户不录入新增设备，那么对应的新增设备损失险（全面型）保额保费即为0。
      }
      return false;
    },
    van() {
      return this.vehicleType == "4";
    },
    show: {
      get: function() {
        return !!this.editItem.kindCode;
      },
      set: function() {
        this.editIndex = -1;
        this.editItem = Object.assign({}, Risk);
      }
    },
    isEnumValue() {
      return (
        Array.isArray(this.enumValues[this.editItem.kindCode].brokenCode) ||
        Array.isArray(this.enumValues[this.editItem.kindCode].unitAmount) ||
        Array.isArray(this.enumValues[this.editItem.kindCode].amount) ||
        this.editItem.kindCode == "C101011"
      );
    },
    enumKey() {
      if (Array.isArray(this.enumValues[this.editItem.kindCode].brokenCode)) {
        return "brokenCode";
      } else if (
        Array.isArray(this.enumValues[this.editItem.kindCode].unitAmount)
      ) {
        return "unitAmount";
      } else if (
        Array.isArray(this.enumValues[this.editItem.kindCode].amount)
      ) {
        return "amount";
      } else if (this.editItem.kindCode == "C101011") {
        return "deductibleRate";
      }
      return "";
    },
    enumValue() {
      if (Array.isArray(this.enumValues[this.editItem.kindCode].brokenCode)) {
        return this.enumValues[this.editItem.kindCode].brokenCode;
      } else if (
        Array.isArray(this.enumValues[this.editItem.kindCode].unitAmount)
      ) {
        return this.enumValues[this.editItem.kindCode].unitAmount;
      } else if (
        Array.isArray(this.enumValues[this.editItem.kindCode].amount)
      ) {
        return this.enumValues[this.editItem.kindCode].amount;
      } else if (this.editItem.kindCode == "C101011") {
        return [
          { text: "无", value: "0" },
          { text: "5%", value: "0.05" },
          { text: "10%", value: "0.10" },
          { text: "15%", value: "0.15" },
          { text: "20%", value: "0.20" }
        ];
      }
      return [];
    },
    selectedItems() {
      return this.kindCodeList.filter(i => i.selected);
    }
  },
  methods: {
    ...mapMutations("vehicle", [
      "setNewDevices",
      "setBusinessRisks",
      "addBusinessRisk",
      "deleteBusinessRisk"
    ]),
    toggleFolding() {
      this.folding = !this.folding;
    },
    changeDeductibleValue(deductibleValue) {
      this.editItem.deductibleValue = deductibleValue.value;
      this.$forceUpdate();
    },
    input(e, type) {},
    init() {
      if (this.vehicleType == "3") {
        //摩托车、拖拉机、三轮汽车
        this.kindCode = {
          C100121: "车辆损失保险",
          C100122: "第三者责任保险",
          C100125: "全车盗抢保险",
          C100123: "车上人员责任保险（司机）",
          C100124: "车上人员责任保险（乘客）",
          C100134: "无法找到第三方特约险"
        };
      } else if (this.vehicleType == "4") {
        //特种车
        this.kindCode = {
          C100121: "车辆损失保险",
          C100122: "第三者责任保险",
          C100125: "全车盗抢保险",
          C100123: "车上人员责任保险（司机）",
          C100124: "车上人员责任保险（乘客）",
          C100127: "自燃损失险",
          C100126: "玻璃单独破碎险",
          C100133: "精神损害抚慰金损失险",
          C100135: "指定修理厂险",
          C100131: "修理期间费用补偿险",
          C100128: "新增加设备损失险",
          C100134: "无法找到第三方特约险",
          C100132: "车上货物责任险",
          C100148: "起重、装卸、挖掘车辆损失扩展条款",
          C100149: "特种车辆固定设备、仪器损坏扩展条款"
        };
      } else {
        //挂车
        if (this.car.carKindCode == "31") {
          this.kindCode = {
            C100121: "车辆损失保险",
            C100122: "第三者责任保险",
            C100125: "全车盗抢保险",
            C100123: "车上人员责任保险（司机）",
            C100124: "车上人员责任保险（乘客）",
            C100127: "自燃损失险",
            C100130: "发动机涉水损失险",
            C100129: "车身划痕损失险",
            C100133: "精神损害抚慰金损失险",
            C100135: "指定修理厂险",
            C100131: "修理期间费用补偿险",
            C100128: "新增加设备损失险",
            C101017: "三者险法定节假日限额翻倍险",
            C100134: "无法找到第三方特约险"
          };
        } else {
          // 家用车、货车/客车
          if (["85", "220", "230"].includes(this.car.useNatureCode)) {
            this.kindCode = {
              C100121: "车辆损失保险",
              C100122: "第三者责任保险",
              C100125: "全车盗抢保险",
              C100123: "车上人员责任保险（司机）",
              C100124: "车上人员责任保险（乘客）",
              C100127: "自燃损失险",
              C100126: "玻璃单独破碎险",
              C100130: "发动机涉水损失险",
              C100129: "车身划痕损失险",
              C100133: "精神损害抚慰金损失险",
              C100135: "指定修理厂险",
              C100131: "修理期间费用补偿险",
              C100128: "新增加设备损失险",
              C101017: "三者险法定节假日限额翻倍险",
              C100134: "无法找到第三方特约险"
            };
          } else {
            this.kindCode = {
              C100121: "车辆损失保险",
              C100122: "第三者责任保险",
              C100125: "全车盗抢保险",
              C100123: "车上人员责任保险（司机）",
              C100124: "车上人员责任保险（乘客）",
              C100127: "自燃损失险",
              C100126: "玻璃单独破碎险",
              C100129: "车身划痕损失险",
              C100133: "精神损害抚慰金损失险",
              C100135: "指定修理厂险",
              C100131: "修理期间费用补偿险",
              C100128: "新增加设备损失险",
              C101017: "三者险法定节假日限额翻倍险",
              C100134: "无法找到第三方特约险"
            };
          }
        }
      }
      this.kindCodeList = Object.keys(this.kindCode).map(key => {
        let kindName = this.kindCode[key];
        return Object.assign({}, Risk, {
          kindCode: key, //险种代码
          kindName //险种名称
        });
      });
      if (this.vehicleType == "4") {
        this.enumValues.C100126.brokenCode = [
          { value: "1", text: "国产玻璃" },
          { value: "2", text: "进口玻璃" },
          { value: "3", text: "国产特质玻璃" },
          { value: "4", text: "进口特质玻璃" }
        ];
      }
    },
    enumItemOnClick(index) {
      if (this.editItem.kindCode == "C100135") {
        // 指定修理厂险
        if (this.car.importFlag == "1" || this.car.importFlag == "2") {
          this.$toast.clear();
          this.$toast("该选项不可选");
        } else {
          this.editItem[this.enumKey] = this.enumValue[index - 1].value;
        }
      } else if (this.editItem.kindCode == "C100122") {
        // 第三者责任保险
        this.editItem[this.enumKey] = 500000 * index;
      } else {
        this.editItem[this.enumKey] = this.enumValue[index - 1].value;
      }
    },
    changeUnitAmount(value) {
      this.editItem.unitAmount = value;
      if (this.editItem.quantity) {
        this.calcAmount();
      }
    },
    calcAmount() {
      let amount = this.editItem.unitAmount * this.editItem.quantity;
      if (Number.isSafeInteger(amount)) {
        this.editItem.amount = amount;
      }
    },
    rulesChecker(item) {
      console.trace("rulesChecker");
      let result = true;
      //车辆损失保险（全面型）
      let C101011 = this.selectedItems.find(item => item.kindCode == "C101011");
      //车辆损失保险
      let C100121 = this.selectedItems.find(item => item.kindCode == "C100121");
      //第三者责任保险
      let C100122 = this.selectedItems.find(item => item.kindCode == "C100122");
      //司机险
      let C100123 = this.selectedItems.find(item => item.kindCode == "C100123");
      //乘客险
      let C100124 = this.selectedItems.find(item => item.kindCode == "C100124");
      switch (item.kindCode) {
        //车辆损失保险（全面型）
        case "C101011": {
          //此险种，只有宁波、湖北、江西、深圳存在此险
          //1.使用性质为家庭自用；车主类型为个人；
          //  车辆种类是客车且座位数小于等于9座时，可投保车辆损失保险（全面型）。
          //  如果不满足投保条件，该责任不可见。
          //2.该责任与车辆损失保险及其附加险
          // （玻璃单独破碎险、车身划痕损失险、新增加设备损失险、自燃损失险、发动机涉水损失险、车辆损失保险无法找到第三方特约险、不计免赔险、修理期间费用补偿险、指定修理厂险）互斥，即不可同时承保。
          if (C100121) {
            this.$dialog.alert({ message: "与车辆损失保险其附加险互斥" });
            item.selected = false;
            result = false;
          }
          if (this.show && !item.deductibleRate) {
            this.$dialog.alert({ message: "绝对免赔率必选" });
            result = false;
          }
          //3.车辆损失保险（全面型）无绝对免赔额。
          //4.车辆损失保险（全面型）要与新增设备损失险（全面型）联动，
          //  即勾选了车辆损失保险（全面型），下面的新增设备损失险（全面型）自动勾选，
          //  保额保费默认为0，如果客户不录入新增设备，那么对应的新增设备损失险（全面型）保额保费即为0。
          break;
        }
        //新增设备损失险（全面型）
        case "C101015": {
          if (!C101011) {
            this.$dialog.alert({
              message: "投保了车辆损失保险（全面型），可投保本附加险"
            });
            result = false;
          } else {
            // this.$dialog.alert({
            //   message: "投保了车辆损失保险（全面型），不可单独取消本附加险"
            // });
            // result = false;
          }
          if (C100121) {
            this.$dialog.alert({ message: "与车辆损失保险其附加险互斥" });
            item.selected = false;
            result = false;
          }
          break;
        }
        //附加车轮单独损坏除外特约险（全面型）
        case "C101012": {
          if (!C101011) {
            this.$dialog.alert({
              message: "投保了车辆损失保险（全面型），可投保本附加险"
            });
            result = false;
          } else {
            //无弹出框
            item.selected = !item.selected;
            if (item.selected) {
              this.addBusinessRisk(item);
            } else {
              this.deleteBusinessRisk(item);
            }
          }
          result = false;
          break;
        }
        //车辆损失保险
        case "C100121": {
          if (C101011) {
            this.$dialog.alert({ message: "与车辆损失保险（全面型）互斥" });
            item.selected = false;
            result = false;
          }
          //保险金额默认金额为车辆实际价值，可修改
          if (item.amount == "") {
            item.amount = this.car.actualValue;
          }
          break;
        }
        //第三者责任保险
        case "C100122": {
          break;
        }
        //全车盗抢保险
        case "C100125": {
          //全车盗抢保险保险金额默认金额为车辆实际价值，不能大于车辆损失险保险金额
          let C100121Amount = C100121 ? C100121.amount : 0;
          if (C100121Amount && item.amount > C100121Amount) {
            this.$dialog.alert({
              message: "全车盗抢保险保险金额不能大于车辆损失险保险金额"
            });
            item.amount = C100121Amount || item.amount;
            result = false;
          }
          if (this.show) {
            if (
              !item.amount ||
              !Number(item.amount) ||
              Number(item.amount) < 0
            ) {
              this.$dialog.alert({
                message: "请输入正确保额"
              });
              item.amount = "";
              result = false;
            }
          }
          break;
        }
        //车上人员责任保险（司机）
        case "C100123": {
          break;
        }
        //车上人员责任保险（乘客）
        case "C100124": {
          //点击展开按钮后，显示所有可选择的保险金额。
          //投保座位数默认核定载客数-1，不能大于核定载客数；
          if (item.quantity == "") {
            item.quantity = this.car.seatCount - 1;
          }
          if (this.show && item.quantity > this.car.seatCount) {
            this.$dialog.alert({ message: "投保座位数不能大于核定载客数" });
            item.quantity = this.car.seatCount - 1;
            this.editItem.quantity = this.car.seatCount - 1;
            result = false;
          } else if (this.show && item.quantity < 0) {
            this.$dialog.alert({ message: "投保座位数不能小于0" });
            item.quantity = this.car.seatCount - 1;
            this.editItem.quantity = this.car.seatCount - 1;
            result = false;
          }
          //保险金额=投保座位数X每座保额
          break;
        }
        //自燃损失险
        case "C100127": {
          if (C101011) {
            this.$dialog.alert({ message: "与车辆损失保险（全面型）互斥" });
            item.selected = false;
            result = false;
          }
          //投保了车辆损失保险的机动车，可投保本附加险
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
            result = false;
          } else if (item.amount > C100121.amount) {
            //保险金额默认金额为车辆实际价值，不能大于车辆损失险保险金额
            this.$dialog.alert({
              message: "不能大于车辆损失险保险金额"
            });
            item.amount = C100121.amount;
            result = false;
          }
          if (this.show) {
            if (
              !item.amount ||
              !Number(item.amount) ||
              Number(item.amount) < 0
            ) {
              this.$dialog.alert({
                message: "请输入正确保额"
              });
              item.amount = "";
              result = false;
            }
          }
          break;
        }
        //玻璃单独破碎险
        case "C100126": {
          if (C101011) {
            this.$dialog.alert({ message: "与车辆损失保险（全面型）互斥" });
            item.selected = false;
            result = false;
          }
          //投保了车辆损失保险的机动车，可投保本附加险
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
            result = false;
          }
          //玻璃的种类根据新核心返回的数据中车辆是否进口车进行预填

          //若返回为“进口车”则玻璃种类预填为“进口”，国产车、合资车预填为“国产”
          if (!this.show) {
            if (item.brokenCode == "2") {
              item.brokenCode = "2";
            } else {
              item.brokenCode = "1";
            }
          }
          break;
        }
        //发动机涉水损失险
        case "C100130": {
          if (C101011) {
            this.$dialog.alert({ message: "与车辆损失保险（全面型）互斥" });
            item.selected = false;
            result = false;
          }
          //无保额。只有在投保了车辆损失保险后，方可投保本附加险。
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
            result = false;
          } else {
            //无弹出界面
            // item.selected = !item.selected;
            // if (item.selected) {
            //   this.addBusinessRisk(item);
            // } else {
            //   this.deleteBusinessRisk(item);
            // }
          }
          // result = false;
          break;
        }
        //车身划痕损失险
        case "C100129": {
          if (C101011) {
            this.$dialog.alert({ message: "与车辆损失保险（全面型）互斥" });
            item.selected = false;
            result = false;
          }
          //投保了车辆损失保险的机动车，可投保本附加险
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
            result = false;
          }
          break;
        }
        //指定修理厂险
        case "C100135": {
          //默认字段：请输入浮动比例
          if (C101011) {
            this.$dialog.alert({ message: "与车辆损失保险（全面型）互斥" });
            item.selected = false;
            result = false;
          }
          //投保了车辆损失保险的机动车，可投保本附加险。
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
            result = false;
          }
          //如果新核心返回“国产、进口”，则显示国产或者进口且为只读不可以修改，”合资“显示为国产，
          //如果未返回则浮动比例前需要手动选择国产或者进口。手工录入浮动比例范围：
          //国产：10％－30％
          //进口：15％－60％
          else if (item.deductibleRate) {
            if (this.show) {
              if (item.brokenCode == "1") {
                if (item.deductibleRate < 10 || item.deductibleRate > 30) {
                  this.$toast.fail("国产浮动比例范围：10％－30％");
                  result = false;
                }
                //进口
              } else if (item.brokenCode == "2") {
                if (item.deductibleRate < 15 || item.deductibleRate > 60) {
                  this.$toast.fail("进口浮动比例范围：15％－60％");
                  result = false;
                }
              }
            }
          } else if (this.show) {
            this.$toast.fail("请先输入浮动率");
            result = false;
          }
          break;
        }
        //修理期间费用补偿险
        case "C100131": {
          if (C101011) {
            this.$dialog.alert({ message: "与车辆损失保险（全面型）互斥" });
            item.selected = false;
            result = false;
          }
          //只有在投保了车辆损失保险后，方可投保本附加险。
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
            result = false;
          }
          if (this.show) {
            //投保天数不能小于1且不能大于90
            let quantity = String(item.quantity);
            quantity = quantity.replace(/[^0-9]/g, "");
            quantity = Number(quantity);
            if (quantity && quantity >= 1 && quantity <= 90) {
              //符合
            } else {
              this.$dialog.alert({
                message: "请输入投保天数,且输入值为1天到90天之间"
              });
              item.quantity = "";
              result = false;
            }
            let unitAmount = String(item.unitAmount);
            unitAmount = unitAmount.replace(/[^0-9]/g, "");
            unitAmount = Number(unitAmount);
            if (unitAmount) {
              //符合
            } else {
              this.$dialog.alert({
                message: "请输入有效的每天保额"
              });
              item.unitAmount = "";
              result = false;
            }
          }
          //保险金额=投保天数X每天保额
          break;
        }
        //新增加设备损失险
        case "C100128": {
          if (C101011) {
            this.$dialog.alert({ message: "与车辆损失保险（全面型）互斥" });
            item.selected = false;
            result = false;
          }
          //只有在投保了车辆损失保险后，方可投保本附加险。
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
            result = false;
          }
          break;
        }
        //无法找到第三方特约险
        case "C100134": {
          if (C101011) {
            this.$dialog.alert({ message: "与车辆损失保险（全面型）互斥" });
            item.selected = false;
            result = false;
          }
          //只有在投保了车辆损失保险后，方可投保本附加险
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
          } else {
            //无弹出界面
            item.selected = !item.selected;
            if (item.selected) {
              this.addBusinessRisk(item);
            } else {
              this.deleteBusinessRisk(item);
            }
          }
          result = false;
          break;
        }
        //精神损害抚慰金损失险
        case "C100133": {
          //只有在投保了第三者责任保险或司机险、乘客险后，方可投保本附加险
          if (!C100122 && !C100123 && !C100124) {
            this.$dialog.alert({
              message: "投保了第三者责任保险或司机险、乘客险后，可投保本附加险"
            });
            result = false;
          }
          if (this.show) {
            if (
              !item.amount ||
              !Number(item.amount) ||
              Number(item.amount) < 0
            ) {
              this.$dialog.alert({
                message: "请输入正确保额"
              });
              item.amount = "";
              result = false;
            }
          }
          break;
        }
        //三者险法定节假日限额翻倍险
        case "C101017": {
          //保额为第三者责任保险X 2， 2020年04月18日12:29:24 变更 不用X2
          //投保了第三者责任保险，方可投保三者险法定节假日限额翻倍险
          if (!C100122) {
            this.$dialog.alert({
              message: "投保了第三者责任保险，可投保本附加险"
            });
          } else {
            //无弹出页面
            item.amount = C100122.amount;
            item.selected = !item.selected;
            if (item.selected) {
              this.addBusinessRisk(item);
            } else {
              this.deleteBusinessRisk(item);
            }
          }
          result = false;
          break;
        }
        //起重、装卸、挖掘车辆损失扩展条款
        //特种车辆固定设备、仪器损坏扩展条款
        case "C100148": {
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
            result = false;
          }
          break;
        }
        case "C100149": {
          if (!C100121) {
            this.$dialog.alert({
              message: "投保了车辆损失保险的机动车，可投保本附加险"
            });
            result = false;
          } else {
            item.selected = !item.selected;
            if (item.selected) {
              this.addBusinessRisk(item);
            } else {
              this.deleteBusinessRisk(item);
            }
            result = false;
          }
          break;
        }
        //车上货物责任险
        case "C100132": {
          if (!C100122) {
            this.$dialog.alert({
              message: "投保了第三者责任保险，可投保本附加险"
            });
            result = false;
          } else {
            if (this.show) {
              if (
                !item.amount ||
                !Number(item.amount) ||
                Number(item.amount) < 0
              ) {
                this.$dialog.alert({
                  message: "请输入正确保额"
                });
                item.amount = "";
                result = false;
              }
            }
          }
          break;
        }
        default:
          break;
      }
      return result;
    },
    changeItemSelected(item, index) {
      if (item.selected) {
        //车辆损失保险
        if (item.kindCode == "C100121") {
          //附加险
          //玻璃单独破碎险、车身划痕损失险、新增加设备损失险、自燃损失险、发动机涉水损失险、车辆损失保险无法找到第三方特约险、不计免赔险、修理期间费用补偿险、指定修理厂险
          let additionalRisks = [
            "C100126",
            "C100129",
            "C100128",
            "C100127",
            "C100130",
            "C100134",
            "C100131",
            "C100135"
          ];
          let include = false;
          for (let index = 0; index < this.selectedItems.length; index++) {
            const element = this.selectedItems[index];
            if (additionalRisks.includes(element.kindCode)) {
              include = true;
              break;
            }
          }
          if (include) {
            this.$dialog.alert({ message: "还有附加险未取消" });
            return;
          }
        }
        //车辆损失保险(全面型)
        else if (item.kindCode == "C101011") {
          //联动取消 附加车轮单独损坏除外特约险（全面型）
          let C101012 = this.kindCodeList.find(
            item => item.kindCode == "C101012"
          );
          if (C101012) {
            C101012.selected = false;
            this.deleteBusinessRisk({ kindCode: "C101012" });
          }
          //联动取消 新增设备损失险(全面型)
          let C101015 = this.kindCodeList.find(
            item => item.kindCode == "C101015"
          );
          if (C101015) {
            C101015.selected = false;
            this.deleteBusinessRisk({ kindCode: "C101015" });
          }
        }
        if (this.rulesChecker(item)) {
          item.selected = false;
          this.deleteBusinessRisk(item);
        } else {
          console.warn("规则");
        }
      } else {
        this.showPopup(item, index);
      }
    },
    showPopup(item, index) {
      if (this.rulesChecker(item)) {
        if (item.kindCode == "C100128") {
          this.$router.push({
            name: "equipmentDamage",
            params: { kindCode: item.kindCode, kindName: item.kindName }
          });
          return;
        }
        if (item.kindCode == "C101015") {
          this.$router.push({
            name: "equipmentDamage",
            params: { kindCode: item.kindCode, kindName: item.kindName }
          });
          return;
        }
        this.editItem = Object.assign({}, item);
        this.editIndex = index;
        if (this.isEnumValue) {
          //设置默认值
          this.editItem[this.enumKey] =
            this.editItem[this.enumKey] || this.enumValue[0].value;
          console.log("设置默认值", this.editItem);
        }
      }
    },
    addInsuanceKindItem(item) {
      let C101011 = this.selectedItems.find(item => item.kindCode == "C101011");
      if (this.rulesChecker(item)) {
        item.selected = true;
        this.addBusinessRisk(item);
        //车辆损失保险(全面型)
        if (item.kindCode == "C101011") {
          //联动添加 附加车轮单独损坏除外特约险（全面型）
          let litemIndexC101012 = this.kindCodeList.findIndex(
            item => item.kindCode == "C101012"
          );
          let litemC101012 = this.kindCodeList[litemIndexC101012];
          litemC101012.amount = "0";
          litemC101012.selected = true;
          this.addBusinessRisk(item);
          this.$set(this.kindCodeList, litemIndexC101012, litemC101012);
          //联动添加 新增设备损失险(全面型)
          let litemIndexC101015 = this.kindCodeList.findIndex(
            item => item.kindCode == "C101015"
          );
          let litemC101015 = this.kindCodeList[litemIndexC101015];
          litemC101015.amount = "0";
          litemC101015.selected = true;
          this.addBusinessRisk(item);
          this.$set(this.kindCodeList, litemIndexC101015, litemC101015);
        }
        this.$set(this.kindCodeList, this.editIndex, item);
        this.editItem = Object.assign({}, Risk);
        this.editIndex = -1;
      }
    },
    saveRisks() {
      let items = [];
      items = [...this.kindCodeList.filter(i => i.selected)];
      console.log(items);
      items.forEach(i => {
        delete i.selected;
      });
      console.log(items);
      this.setBusinessRisks(items);
      this.$router.back();
    }
  }
};
</script>
<style lang="less" scoped>
.vehicle-commercial {
}

.popupInfoTop,
.popupCenter {
  padding: 0 20px;

  width: 285px;
  height: 40px;
  font-size: 14px;
  color: rgba(51, 51, 51, 1);
  line-height: 20px;
  margin-bottom: 10px;
}
.popupInfoTop {
  padding-top: 20px;
}
.popupCenter {
  margin-bottom: 43px;
}
.ensure {
  height: 46px;
  line-height: 46px;
  text-align: center;
  font-size: 14px;
  color: rgba(59, 138, 254, 1);
  border-top: 1px solid #eaeaea;
}

.content {
  width: 100%;
  height: calc(100% - 77px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  .van-cell__value {
    flex: 2;
  }
  .group {
    color: #333333;
    font-size: 16px;
    line-height: 22px;
    font-weight: 600;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
  }
  .item {
    display: flex;
    flex-direction: row;
    height: 48px;
    word-break: keep-all;
    white-space: nowrap;
    align-items: center;
    div {
      display: flex;
    }
    .item-title {
      color: #333333;
      font-size: 14px;
      justify-content: center;
      align-items: center;
      .item-title-flag {
        width: 49px;
        height: 14px;
        border-radius: 9px;
        border: 1px solid #fc820e;
        color: #fc820e;
        font-size: 10px;
        justify-content: center;
        align-items: center;
        margin-left: 8px;
      }
    }
    .item-checkbox-container {
      padding-right: 10px;
      .item-checkbox {
        pointer-events: none;
      }
    }
    .item-checkbox-title {
      color: #333333;
      font-size: 14px;
      line-height: 20px;
    }
    .item-right {
      flex: 1;
      flex-direction: row;
      justify-content: flex-end;
      align-items: center;
      font-size: 14px;
      color: #333333;
      word-break: keep-all;
      overflow: visible;
      .item-right-input {
        width: 100%;
        text-align: right;
        border: 0;
        background: transparent;
        opacity: 1;
        font-size: 14px;
        width: 200px;
        color: #333333;
      }
      .item-right-input-unit {
        margin-left: 4px;
      }
      // .item-right-drop {
      //   justify-content: flex-end;
      //   margin-right: 18px;
      // }
      // .item-right-drop::after {
      //   position: absolute;
      //   top: 50%;
      //   right: 0.10667rem;
      //   margin-top: -0.13333rem;
      //   border: 0.08rem solid;
      //   border-color: transparent transparent currentColor currentColor;
      //   -webkit-transform: rotate(-45deg);
      //   transform: rotate(-45deg);
      //   opacity: 0.8;
      //   content: "";
      // }
    }
  }
  .item-placeholder {
    color: #ababab;
  }
}
.popup-content {
  .select-container {
    padding: 14px 15px 24px;
    background: white;
    flex-direction: column;

    div {
      display: flex;
    }
    .select-title {
      color: #333333;
      font-size: 14px;
    }
    .select-folding-items {
      flex: 1;
      flex-direction: row;
      justify-content: center;
      margin-top: 8px;
      margin-bottom: 23px;
      .select-folding-item {
        color: #ababab;
        font-size: 14px;
        width: 95px;
        height: 28px;
        border-radius: 3px;
        border: 1px solid #dddddd;
        justify-content: center;
        align-items: center;
      }
      .select-folding-item:last-child {
        margin-left: 15px;
      }
      .item-selected {
        color: #2b79ed;
        background: #eef5ff;
        border: 1px solid #eef5ff;
      }
    }
    .select-unfolding-items {
      flex-direction: row;
      flex-wrap: wrap;
      justify-content: flex-start;
      padding-top: 8px;
      .select-unfolding-item {
        color: #ababab;
        font-size: 14px;
        min-width: 60px;
        height: 28px;
        border-radius: 3px;
        border: 1px solid #dddddd;
        justify-content: center;
        align-items: center;
        margin-bottom: 12px;
        margin-right: 8px;
      }
      .select-unfolding-item:nth-child(5n + 0) {
        margin-right: 0;
      }
      .item-selected {
        color: #2b79ed;
        background: #eef5ff;
        border: 1px solid #eef5ff;
      }
    }

    .folding-icon {
      display: flex;
      font-size: 14px;
      justify-content: center;
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
