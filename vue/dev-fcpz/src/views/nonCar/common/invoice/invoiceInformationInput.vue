<template>
  <div id="invoice-information-input">
    <van-cell title="发票抬头">
      <template slot="default">
        <van-button
          plain
          type="primary"
          :class="[isSameInsured ? 'selected-button' : '', 'select-button']"
          @click="isSameInsured = true"
        >同投保人</van-button>
        <van-button
          plain  
          class="select-button"
          :class="[!isSameInsured ? 'selected-button' : '', 'select-button']"
          @click="isSameInsured = false"
        >同被保人</van-button>
      </template>
    </van-cell>
    <van-cell title="是否需要电子发票按钮">
      <template slot="default">
        <van-button
          plain
          type="primary"
          :class="[isShould ? 'selected-button' : '', 'select-button']"
          @click="isShould = true"
        >是</van-button>
        <van-button
          plain
          class="select-button"
          :class="[!isShould ? 'selected-button' : '', 'select-button']"
          @click="isShould = false"
        >否</van-button>
      </template>
    </van-cell>
    <van-field v-model="mobile" label="手机号" placeholder="通过手机号接收发票链接" input-align="right"  />
    <van-field v-model="email" label="电子邮箱" placeholder="用来接收电子发票邮箱" input-align="right" />

    <div>
      <van-field
        readonly
        clickable
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
      <van-popup v-model="billing.cutstomInfo.customerShowPicker" position="bottom">
        <van-picker
          show-toolbar
          :columns="billing.cutstomInfo.cutstomClientType"
          @cancel="billing.cutstomInfo.customerShowPicker = false"
          @confirm="customer_onConfirm"
        />
      </van-popup>
    </div>

    <div>
      <van-field
        readonly
        clickable
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
      <van-popup v-model="billing.taxpayerInfo.taxPayerShowPicker" position="bottom">
        <van-picker
          show-toolbar
          :columns="billing.taxpayerInfo.taxPayerType"
          @cancel="billing.taxpayerInfo.taxPayerShowPicker = false"
          @confirm="taxpayerInfo_onConfirm"
        />
      </van-popup>
    </div>

    <div>
      <van-field
        readonly
        clickable
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
      <van-popup v-model="billing.invoiceInfo.invoicePicker" position="bottom">
        <van-picker
          show-toolbar
          :columns="billing.invoiceInfo.invoiceType"
          @cancel="billing.invoiceInfo.invoicePicker = false"
          @confirm="invoiceInfo_onConfirm"
        />
      </van-popup>
    </div>

    <van-field v-model="companyName" label="公司名称" placeholder="请输入公司名称" input-align="right" />

    <van-field v-model="taxPayerNo" label="纳税人识别号" placeholder="请输入识别号" input-align="right" />

    <van-field
      v-model="taxRegisterAddress"
      label="注册地址"
      placeholder="请输入注册地址"
      input-align="right"
    />

    <van-field v-model="taxRegisterTel" label="注册电话" placeholder="请输入注册电话" input-align="right" />

    <van-field v-model="depositeBankName" label="开户银行" placeholder="请输入开户行" input-align="right" />

    <van-field v-model="accountNo" label="银行账号" placeholder="请输入银行账号" input-align="right" />
  </div>
</template>

<script>
import { Button, Toast } from "vant";
export default {
  components: {
    [Button.name]: Button
  },
  name: "invoice-info-input",
  props: {},
  data: () => ({
    taxpayerTypeList: [
      //纳税人类型
      { id: 1, text: "一般纳税人 " },
      { id: 2, text: "小规模纳税人" },
      { id: 3, text: "非增值税纳税人" },
      { id: 4, text: "个人" }
    ],
    invoiceTypeList: [
      //发票类型数据
      { id: 1, text: "增值税专用发票 " },
      { id: 1, text: "增值税普通发票" },
      { id: 1, text: "增值税电子普通发票" },
      { id: 1, text: "其他发票" }
    ],
    // 客户类型/纳税人类型/发票类型数据
    billing: {
      cutstomInfo: {
        // 客户类型
        cutstomClientType: [
          "境内个人、政府机关及事业单位",
          "境内企业",
          "境外个人或机构"
        ],
        customerShowPicker: false,
        customerTypeVal: ""
      },
      taxpayerInfo: {
        // 纳税人 Taxpayers
        taxPayerType: [],
        taxPayerShowPicker: false,
        taxPayerTypeVal: ""
      },
      invoiceInfo: {
        //发票
        invoiceType: [],
        invoicePicker: false,
        invoiceTypeVal: ""
      }
    },
    isSameInsured: false, // 发票抬头切换
    isShould:false,
    companyName: "", //公司名称
    taxPayerNo: "", //纳税人识别号
    taxRegisterAddress: "", //注册地址
    taxRegisterTel: "", //注册手机
    depositeBankName: "", //开户银行
    accountNo: "", //银行账号
    mobile: "", //手机号
    email: "", //电子邮箱
    customerType: "", //客户类型
    taxPayerType: "", // 纳税人类型
    InvoiceType: "" // 发票类型
  }),
 
  methods: {
    // 邮箱验证
    handlerEmail() {
      let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
      if (!reg.test(this.appliEMail)) {
        Toast("邮箱格式不正确");
      }
    },

    // 开票信息-客户类型选择
    customer_onConfirm(value, index) {
      this.billing.taxpayerInfo.taxPayerTypeVal = "";
      this.billing.invoiceInfo.invoiceTypeVal = "";
      this.billing.taxpayerInfo.taxPayerType = [];
      this.billing.invoiceInfo.invoiceType = [];
      this.billing.cutstomInfo.customerTypeVal = value;
      this.customerType = index + 1;
      if (this.customerType == 1) {
        this.billing.taxpayerInfo.taxPayerType = ["个人"];
      }
      if (this.customerType == 2) {
        this.billing.taxpayerInfo.taxPayerType = [
          "一般纳税人 ",
          "小规模纳税人",
          "非增值税纳税人"
        ];
      }
      if (this.customerType == 3) {
        this.billing.taxpayerInfo.taxPayerType = ["非增值税纳税人"];
      }
      this.billing.cutstomInfo.customerShowPicker = false;
    },

    //开票信息 - 纳税人类型
    taxpayerInfo_onConfirm(value, index) {
      this.billing.taxpayerInfo.taxPayerTypeVal = value;
      let obj = this.taxpayerTypeList.find(function(obj) {
        return obj.text == value;
      });
      if (obj) {
        this.taxPayerType = obj.id;
      } else {
        this.taxPayerType = index + 1;
      }
      this.billing.taxpayerInfo.taxPayerShowPicker = false;
      if (this.taxPayerType == 2 || this.taxPayerType == 3) {
        this.billing.invoiceInfo.invoiceType = [
          "增值税普通发票",
          "增值税电子普通发票",
          "其他发票"
        ];
      }
      if (this.taxPayerType == 1) {
        this.billing.invoiceInfo.invoiceType = [
          "增值税专用发票 ",
          "增值税普通发票",
          "增值税电子普通发票",
          "其他发票"
        ];
      }
      if (this.taxPayerType == 4) {
        this.billing.invoiceInfo.invoiceType = [
          "增值税普通发票",
          "增值税电子普通发票",
          "其他发票"
        ];
      }
    },

    // 开票信息 - 发票类型
    invoiceInfo_onConfirm(value, index) {
      this.billing.invoiceInfo.invoiceTypeVal = value;
      let obj = this.invoiceTypeList.find(function(obj) {
        return obj.text == value;
      });
      if (obj) {
        this.invoiceType = obj.id;
      } else {
        this.invoiceType = index + 1;
      }
      this.billing.invoiceInfo.invoicePicker = false;
    }
  }
};
</script>

<style scoped>
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
select {
  appearance: none;
  border: none;
  color: #323233;
  background: #fff;
  outline: none;
  -moz-appearance: none; /* Firefox */
  -webkit-appearance: none; /* Safari 和 Chrome */
}
</style>
