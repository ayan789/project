<template>
  <div>
    <van-sticky>
      <nav-bar
        type="vehicle"
        title="车船税"
        @clickBack="$router.back()"
      />
    </van-sticky>
    <div class="content">
      <van-cell class="item long-item">
        <span
          slot="title"
          class="item-title"
        >纳税类型</span>
        <div class="item-right">
          <whether-select
            v-model="tax.taxFlag"
            :options="taxFlags"
            @change="changeTaxFlag"
          />
        </div>
      </van-cell>
      <van-field
        class="item"
        required
        v-model="tax.taxpayerNo"
        rows="1"
        autosize
        type="textarea"
        placeholder="请输入纳税人识别号"
        input-align="right"
        maxlength="50"
      >
        <span
          slot="label"
          class="item-title"
        >纳税人识别号</span>
      </van-field>
      <van-field
        class="item"
        v-model="tax.taxpayerName"
        rows="1"
        autosize
        type="textarea"
        placeholder="请输入纳税人名称"
        input-align="right"
        maxlength="50"
      >
        <span
          slot="label"
          class="item-title"
        >纳税人名称</span>
      </van-field>
      <van-cell
        v-if="tax.taxFlag == 'C'"
        class="item"
        required
        @click="setPicker('deductiondueCode')"
      >
        <span
          slot="title"
          class="item-title"
        >减免税原因</span>
        <div class="item-right">
          <span :class="{
              'item-placeholder': !tax.deductiondueCode
            }">
            {{ $utils.findText(deductiondueCodes, tax.deductiondueCode) }}
          </span>
          <van-icon
            class="item-right-arrow"
            name="arrow"
          />
        </div>
      </van-cell>
      <van-cell
        v-if="tax.taxFlag == 'E'"
        class="item"
        required
        @click="setPicker('deductiondueCode1')"
      >
        <span
          slot="title"
          class="item-title"
        >减免税原因</span>
        <div class="item-right">
          <span :class="{
              'item-placeholder': !tax.deductiondueCode1
            }">
            {{ $utils.findText(deductiondueCodes, tax.deductiondueCode1) }}
          </span>
          <van-icon
            class="item-right-arrow"
            name="arrow"
          />
        </div>
      </van-cell>
      <van-cell
        v-if="tax.taxFlag == 'C'"
        class="item"
        required
        @click="setPicker('deductiondueType')"
      >
        <span
          slot="title"
          class="item-title"
        >减免税方案</span>
        <div class="item-right">
          <span :class="{
              'item-placeholder': !tax.deductiondueType
            }">
            {{ $utils.findText(deductiondueTypes, tax.deductiondueType) }}
          </span>
          <van-icon
            class="item-right-arrow"
            name="arrow"
          />
        </div>
      </van-cell>
      <van-field
        class="item"
        v-if="tax.taxFlag == 'C' && tax.deductiondueType == 'P'"
        v-model="tax.adjustTaxValue"
        rows="1"
        autosize
        placeholder="请输入"
        input-align="right"
        maxlength="2"
        type="digit"
      >
        <span
          slot="label"
          class="item-title"
        >减免比例</span>
        <span
          slot="right-icon"
          style="color:black"
        >%</span>
      </van-field>
      <van-field
        class="item"
        required
        v-if="tax.taxFlag == 'C' && tax.deductiondueType == 'A'"
        v-model="tax.adjustTaxValue"
        rows="1"
        autosize
        type="textarea"
        placeholder="请输入"
        input-align="right"
        maxlength="10"
        @blur="fixed"
      >
        <span
          slot="label"
          class="item-title"
        >减免金额</span>
      </van-field>
      <van-field
        class="item"
        v-if="tax.taxFlag == 'C' || tax.taxFlag == 'E'"
        v-model="tax.taxCertificateNo"
        rows="1"
        autosize
        type="textarea"
        placeholder="请输入"
        input-align="right"
        maxlength="30"
      >
        <span
          slot="label"
          class="item-title"
        >减免税凭证号</span>
      </van-field>
      <van-field
        class="item"
        required
        v-if="tax.taxFlag == 'C' || tax.taxFlag == 'E'"
        v-model="tax.taxDepartment"
        rows="1"
        autosize
        type="textarea"
        placeholder="请输入"
        input-align="right"
        maxlength="50"
      >
        <span
          slot="label"
          class="item-title"
        >税务机关</span>
      </van-field>
      <van-field
        class="item"
        required
        v-model="tax.taxDepartment"
        v-if="tax.taxFlag == 'P'"
        rows="1"
        autosize
        type="textarea"
        placeholder="请输入"
        input-align="right"
        maxlength="50"
      >
        <span
          slot="label"
          class="item-title"
        >凭证机关</span>
      </van-field>
      <van-field
        class="item"
        required
        v-model="tax.taxCertificateNo"
        v-if="tax.taxFlag == 'P'"
        rows="1"
        autosize
        type="textarea"
        placeholder="请输入"
        input-align="right"
        maxlength="30"
      >
        <span
          slot="label"
          class="item-title"
        >完税凭证号</span>
      </van-field>
      <van-cell
        class="item"
        required
        v-if="tax.taxFlag == 'P'"
        @click="datePickerShow = true"
      >
        <span
          slot="title"
          class="item-title"
        >完税凭证填发日期</span>
        <div class="item-right">
          <span :class="{
              'item-placeholder': !tax.taxDocumentDate
            }">
            {{ tax.taxDocumentDate | date }}
          </span>
        </div>
      </van-cell>
      <!-- 开具完税凭证地区 -->
      <van-cell
        class="item"
        v-if="tax.taxFlag == 'P'"
        @click="setPicker('taxPaidareaCode')"
      >
        <span
          slot="title"
          class="item-title"
        >开具完税凭证地区</span>
        <div class="item-right">
          <span :class="{
              'item-placeholder': !tax.taxPaidareaCode
            }">
            {{ $utils.findText(taxPaidAreaCodes, tax.taxPaidareaCode) }}
          </span>
        </div>
      </van-cell>
      <van-cell
        v-if="specialAreas"
        class="item"
        @click="specialAreasShow = true"
      >
        <span
          slot="title"
          class="item-title"
        >计税起始日期</span>
        <div class="item-right">
          <span>
            {{ tax.startTaxDate | date }}
          </span>
        </div>
      </van-cell>
    </div>
    <div class="confirm-button">
      <confirmBottom @click="confirm" />
    </div>
    <div class="confirm-button">
      <confirmBottom @click="confirm" />
    </div>
    <van-popup
      v-model="datePickerShow"
      position="bottom"
    >
      <van-datetime-picker
        title="填发日期"
        type="date"
        :maxDate="new Date()"
        @confirm="onDatePickerConfirm"
        @cancel="datePickerShow = false"
      />
    </van-popup>
    <van-popup
      v-model="dataPickerShow"
      position="bottom"
      :overlay="true"
    >
      <van-picker
        show-toolbar
        :columns="pickerData"
        :default-index="defaultIndex"
        @confirm="dataPickerConfirm"
        @cancel="dataPickerCancel"
      />
    </van-popup>
    <van-popup
      v-model="specialAreasShow"
      position="bottom"
    >
      <van-datetime-picker
        title="选择年月日"
        type="date"
        v-model="currentDate"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="onDatePickerConfirm1"
        @cancel="specialAreasShow = false"
      />
    </van-popup>
  </div>
</template>
<script>
import WhetherSelect from "../vehicleInfo/whetherSelect";
import confirmBottom from "@/components/confirmBottom.vue";
import Config from "@src/common/json/newCoreDataDictionary";
import { mapState, mapMutations } from "vuex";
import { Tax } from "@src/store/modules/vehicle/vehicle";

export default {
  components: {
    [WhetherSelect.name]: WhetherSelect,
    confirmBottom
  },
  data () {
    return {
      specialAreasShow: false,
      specialAreas: true,
      maxDate: "",
      currentDate: new Date(),
      minDate: "",
      deductiondueTypeFlag: "p",
      tax: {
        adjustRate: "", //减免税比例
        adjustTaxValue: "", //减免税比例/减免税金额
        deductiondueCode: "", //减免税原因
        deductiondueType: "", //减免税方案
        ext: {}, //车船税扩展信息表{...}
        sumPayment: "", //合计税款[保费]
        taxCertificateNo: "", //完(减免)税凭证号
        taxDepartment: "", //税务(凭证)机关名称
        taxDocumentDate: "", //完税凭证填发日期
        taxFlag: "", //缴税标志
        taxPaidareaCode: "", //开具完税凭证地区代码
        taxpayerName: "", //纳税人名称
        taxpayerNo: "" //纳税人识别号
      },
      //缴税标志-平台税地区
      taxFlags: Object.keys(Config.taxFlagPlat).map(key => ({
        text: Config.taxFlagPlat[key],
        value: key
      })),
      //减免税原因
      deductiondueCodes: Object.keys(Config.deDuctiondueCodePlat).map(key => ({
        text: Config.deDuctiondueCodePlat[key],
        value: key
      })),

      //减免税方案
      deductiondueTypes: Object.keys(Config.deDuctiondueTypePlat).map(key => ({
        text: Config.deDuctiondueTypePlat[key],
        value: key
      })),
      //开具完税凭证地区
      taxPaidAreaCodes: Object.keys(Config.taxPaidAreaCode).map(key => ({
        text: Config.taxPaidAreaCode[key],
        value: key
      })),
      datePickerShow: false,
      dataPickerType: "",
      pickerData: [],
      defaultIndex: 0
    };
  },
  created () {
    this.minDate = new Date(new Date().getFullYear(), 0, 1);
    this.maxDate = new Date(
      new Date().getFullYear(),
      new Date().getMonth(),
      new Date().getDate()
    );
        let comCodeValue = localStorage.getItem("comCode").slice(0, 4);


    console.log(comCodeValue, "comCodeValue111111111111");
    //  摩托车地区  
    if (["3302","3201","2102","3301","3701"].includes(comCodeValue)) {
            //摩托车
        if (["51", "52", "53"].includes(this.car.carKindCode)) {
          this.specialAreas = false;
        }
    }

    //  拖拉机地区  
    if (["3201","3301","3302","3701"].includes(comCodeValue)) {
         // 拖拉机
      if (["61", "62", "63", "64"].includes(this.car.carKindCode)) {
        this.specialAreas = false;
      }
    }

    if (!this.plan.tax) {
      this.tax = Object.assign({}, Tax);
    } else {
      this.tax = Object.assign({}, this.plan.tax);
    }
    let taxpayerName = this.tax.taxpayerName || this.carOwner.name;
    let taxpayerNo = "";
    if (this.carOwner.identityType == "111") {
      taxpayerNo = this.tax.taxpayerNo || this.carOwner.identityNo;
    }
    if (this.carOwner.custType == "02") {
      taxpayerNo = "";
    }
    this.tax = Object.assign({}, this.tax, {
      taxpayerName,
      taxpayerNo
    });
    if (!this.tax.taxPaidareaCode) {
      this.setDefaultTaxPaidAreaCode();
    }
    this.plan.tax = Object.assign({}, this.tax);
  },
  filters: {
    date (date) {
      return (date || "").split(" ")[0].replace(/-/g, "/") || "请选择";
    }
  },
  computed: {
    ...mapState("vehicle", {
      carOwner: ({ carOwner }) => carOwner,
      car: ({ car }) => car,
      plan: ({ quoteInfo: { plans, planCode } }) =>
        plans.find(plan => plan.planCode === planCode) ||
        plans[0] || { tax: {} }
    }),
    dataPickerShow: {
      get: function () {
        return this.dataPickerType != "";
      },
      set: function (v) {
        this.dataPickerType = "";
        this.pickerData = [];
      }
    }
  },
  methods: {
    ...mapMutations("vehicle", ["setTax"]),
    setDefaultTaxPaidAreaCode () {
      const userInfo = this.localStorage.get("userInfo", {});
      const { comCode = "" } = userInfo;
      const areaCode = comCode.substring(0, 4);
      if (areaCode) {
        let p = this.taxPaidAreaCodes.find(v => v.value.startsWith(areaCode));
        if (!p) {
          p = this.taxPaidAreaCodes.find(v =>
            v.value.startsWith(areaCode.substring(0, 3))
          );
        }
        if (p) {
          this.tax.taxPaidareaCode = p.value;
        }
      }
    },
    fixed () {
      if (Number(this.tax.adjustTaxValue)) {
        this.tax.adjustTaxValue = Number(this.tax.adjustTaxValue).toFixed(2);
      }
    },
    specialAreasSh () {
      this.specialAreasShow = !this.specialAreasShow;
    },
    changeTaxFlag (taxFlag) {
      let tax = this.tax;
      if (taxFlag == "C") {
        //减税
        if (!localStorage.getItem("comCode").startsWith("3302")) {
          tax.deductiondueCode = tax.deductiondueCode || "E"; //减免税原因
        }
        tax.deductiondueType =
          tax.deductiondueType == "E" ? "P" : tax.deductiondueType || "P"; //减免税方案
        tax.adjustTaxValue = tax.adjustTaxValue || "50"; //减免税比例/减免税金额
      } else if (taxFlag == "P") {
        //已完税
      } else if (taxFlag == "E") {
        //免税
        if (!localStorage.getItem("comCode").startsWith("3302")) {
          tax.deductiondueCode = tax.deductiondueCode || "E"; //减免税原因
        }
        // tax.deductiondueCode = tax.deductiondueCode || "E"; //减免税原因
        tax.deductiondueType = "E"; //减免税方案
      } else if (taxFlag == "B") {
        //补税
      } else if (taxFlag == "D") {
        //有争议
      } else if (taxFlag == "R") {
        //拒缴
      }
      tax.taxFlag = taxFlag;
      tax.sumPayment = "";
      this.tax = tax;
    },
    setPicker (type) {
      switch (type) {
        case "deductiondueCode": {
          this.pickerData = this.deductiondueCodes;
          break;
        }
        case "deductiondueCode1": {
          this.pickerData = this.deductiondueCodes;
          break;
        }
        case "deductiondueType": {
          this.pickerData = [...this.deductiondueTypes];
          this.pickerData.shift();
          this.tax.adjustTaxValue = "";
          break;
        }
        case "taxPaidareaCode": {
          this.pickerData = this.taxPaidAreaCodes;
          break;
        }
        default:
          break;
      }
      this.pickerData = [{ text: "请选择", value: "" }, ...this.pickerData];
      this.defaultIndex = this.pickerData.findIndex(
        v => v.value == (this.tax[type] || "")
      );
      this.dataPickerType = type;
    },
    dataPickerConfirm (data) {
      switch (this.dataPickerType) {
        case "deductiondueCode": {
          //能源减免
          if (data.value == "E") {
            this.tax.adjustTaxValue = "50";
          } else {
            this.tax.adjustTaxValue = "";
          }
          break;
        }
        case "deductiondueType": {
          break;
        }
        default:
          break;
      }
      this.tax[this.dataPickerType] = data.value;
      this.dataPickerShow = false;
    },
    dataPickerCancel () {
      this.pickerData = [];
      this.dataPickerType = "";
    },
    changeDeductiondueCode (v) {
      //能源减免
      if (v == "E") {
        this.tax.adjustTaxValue = "50";
      } else {
        this.tax.adjustTaxValue = "";
      }
    },
    onDatePickerConfirm (date) {
      this.tax.taxDocumentDate = date.format();
      this.datePickerShow = false;
      console.log(date);
    },
    onDatePickerConfirm1 (date) {
      this.tax.startTaxDate = date.format();
      this.specialAreasShow = false;
    },
    confirm () {
      //TODO: 保存数据到store
      if (!this.validate()) {
        return;
      }
      this.plan.tax = Object.assign({}, this.tax);
      this.setTax(Object.assign({}, this.tax));
      this.$router.back();
    },
    validate () {
      let props = {
        taxFlag: { required: "纳税类型" },
        // taxpayerName: { required: "纳税人名称" },
        taxpayerNo: { required: "纳税人识别号" }
      };
      console.log(this.tax, "何韦韦");
      if (this.tax.taxFlag == "C") {
        //减税
        // props.adjustRate = { required: "减免税比例" };
        props.adjustTaxValue = { required: "减免税金额" };
        props.deductiondueCode = { required: "减免税原因" };
        props.deductiondueType = { required: "减免税方案" };
        // props.taxCertificateNo = { required: "减免税凭证号" };
        props.taxDepartment = { required: "税务机关名称" };
      }
      if (this.tax.taxFlag == "P") {
        //完税
        props.taxDocumentDate = { required: "完税凭证填发日期" };
        props.taxCertificateNo = { required: "完税凭证号" };
        props.taxDepartment = { required: "凭证机关名称" };
        // props.taxPaidareaCode = { required: "开具完税凭证地区代码" };
      }
      if (this.tax.taxFlag == "E") {
        //免税
        props.deductiondueCode1 = { required: "减免税原因" };
        // props.deductiondueType = { required: "减免税方案" };
        // props.taxCertificateNo = { required: "减免税凭证号" };
        props.taxDepartment = { required: "税务机关名称" };
      }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(props[prop], this.tax[prop]);
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
  // updated(){
  //   this.deductiondueTypeFlag = this.tax.deductiondueType
  //   // console.log(this.tax.deductiondueType,"1111111")
  // },
  // watch:{
  //   deductiondueTypeFlag: {
  //     handler(newValue, oldValue) {
  //       console.log(newValue)
  //       console.log(oldValue)
  //       if (newValue != oldValue) {
  //         this.tax.adjustTaxValue = ""
  //       }
  //     },
  //   }
  // }
};
</script>
<style lang="less" scoped>
.content {
  width: 100%;
  height: calc(100% - 77px - 80px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  div {
    display: flex;
  }
  .van-cell__value {
    flex: 2;
  }
  .group {
    flex-direction: column;
    background: #f7f7f7;
    padding-bottom: 10px;
  }
  .item {
    min-height: 48px;
    align-items: center;
    .item-title {
      font-size: 14px;
      color: #333333;
    }
    .item-placeholder {
      color: #ababab;
    }
    .item-right {
      flex: 1;
      word-break: break-all;
      align-items: center;
      justify-content: flex-end;
      font-size: 14px;
      color: #333333;
      .item-right-input {
        width: 100%;
        resize: none;
        text-align: right;
        border: 0;
        background: transparent;
        opacity: 1;
        font-size: 14px;
        color: #333333;
        overflow-y: visible;
      }
      .item-right-arrow {
        margin-left: 8px;
      }
      .item-right-text {
        font-size: 14px;
        color: #666666;
      }
    }
  }
  .long-item {
    .van-cell__value {
      flex: 3;
    }
  }
}
.confirm-button {
  width: 100%;
  height: 80px;
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
