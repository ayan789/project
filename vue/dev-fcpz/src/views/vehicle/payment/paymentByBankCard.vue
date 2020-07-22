<template>
  <div id="super">
    <van-sticky>
      <nav-bar
        type="vehicle"
        title="银行卡支付"
        @clickBack="$router.back()"
        clear="1"
      />
    </van-sticky>
    <div class="content">
      <div id="topBg">
        <div id="topText">付款金额</div>
        <div>￥{{ totalPremium }}</div>
      </div>
      <van-cell title="卡种" center required>
        <whether-select v-model="cardType" :options="cardTypes" />
      </van-cell>
      <van-cell
        title="付款银行"
        is-link
        value="请选择"
        required
        @click="selectBank"
      />
      <van-field
        v-model="bankCardId"
        label="银行卡号"
        placeholder="请输入"
        input-align="right"
        required
      />
      <van-cell
        title="有效期"
        is-link
        value="请选择日期"
        required
        @click="selectTimer"
      />
      <van-field
        v-model="bankCardCode"
        label="卡验证码"
        placeholder="请输入"
        input-align="right"
        required
      />
      <van-field
        v-model="bankName"
        label="姓名"
        placeholder="请输入"
        input-align="right"
        required
      />
      <van-field
        v-model="idCard"
        label="身份证"
        placeholder="请输入"
        input-align="right"
        required
      />
      <van-field
        v-model="mobile"
        label="手机号"
        placeholder="请输入"
        input-align="right"
        required
      />
    </div>
    <van-popup v-model="showPicker" position="bottom" :overlay="true">
      <van-datetime-picker
        v-show="showPicker"
        v-model="currentDate"
        type="date"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="selectPickerComfirm"
        @cancel="cancelSelectPicker"
      />
    </van-popup>
    <div id="payment" @click="agreeToPay">同意并支付</div>
  </div>
</template>

<script>
import WhetherSelect from "../vehicleInfo/whetherSelect";
import { getPaymentUrl } from "@src/http/module/vehicle";
import { mapState } from "vuex";
export default {
  name: "paymentByBankCard",
  components: {
    [WhetherSelect.name]: WhetherSelect
  },
  data() {
    return {
      showPicker: false,
      minDate: new Date(1990, 1, 1),
      maxDate: new Date(2050, 1, 1),
      currentDate: new Date(),
      //卡种选中时的样式
      selectCard: {
        color: "#3B8AFE",
        borderColor: "#3B8AFE"
      },
      //卡种未选中时的样式
      unSelectCard: {
        color: "#888888",
        borderColor: "#E5E5E5"
      },
      cardType: "储蓄卡",
      cardTypes: [
        { text: "储蓄卡", value: "储蓄卡" },
        { text: "信用卡", value: "信用卡" }
      ],
      selectCardIndex: 0, //0:储蓄卡 1：信用卡
      bankType: "请选择", //付款银行
      bankCardId: "", //银行卡号
      limitedTime: "请选择", //有效期
      bankCardCode: "", //卡验证码
      bankName: "", //姓名
      idCard: "", //身份证
      mobile: "" //手机号
    };
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      totalPremium: ({ insurancePlan }) => insurancePlan.totalPremium,
      name: ({ carOwner: { name } }) => name,
      lisenceNo: ({ car: { lisenceNo } }) => lisenceNo
    })
  },
  created() {
    let params = { orderId: this.orderId, flag: "6" };
    getPaymentUrl(params).then(
      res => {
        if (res.code === "1" && res.data) {
          window.location.href = res.data.url;
        }
      },
      error => {}
    );
  },
  methods: {
    selectCardType: function(index) {
      this.selectCardIndex = index;
      //console.log(index == 0 ? "储蓄卡" : "信用卡");
    },
    selectTime: function() {
      //console.log("选择有效期");
    },
    selectBank: function() {
      //console.log("选择银行");
    },
    agreeToPay: function() {
      //console.log("同意并支付");
    },
    selectTimer() {
      this.showPicker = !this.showPicker;
    },
    selectPickerComfirm(value) {
      var date = new Date(value);
      var date_value =
        date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
      //console.log(date_value);
      this.showPicker = !this.showPicker;
    },
    cancelSelectPicker() {
      this.showPicker = !this.showPicker;
    }
  }
};
</script>

<style scoped>
.content {
  width: 100%;
  height: calc(100% - 77px - 80px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
}
.van-cell {
  height: 48px;
}
.van-field__control {
  height: 48px;
  margin-top: -8px;
  color: #ababab;
}
.van-cell__value {
  color: #ababab;
}
.whetherVehicle {
  position: relative;
}
.whichSelected {
  background: white;
  border-bottom: 1px solid #ebedf0;
}
.cardType {
  width: 100%;
  height: 48px;
}
.cardType > :first-child {
  width: 60px;
  float: left;
}
.cardType > :last-child {
  width: calc(100% - 60px);
  height: 48px;
  float: right;
}

body {
  width: 100%;
  height: 100%;
  position: absolute;
  background: #f7f7f7;
}
#topBg {
  width: 100%;
  height: 124px;
  text-align: center;
  font-size: 14px;
  font-weight: 400;
  color: white;
  background: linear-gradient(
    125deg,
    rgba(30, 70, 246, 1) 0%,
    rgba(39, 154, 255, 1) 100%
  );
}
#topText {
  height: 30px;
  line-height: 30px;
  color: rgba(255, 255, 255, 0.6);
}
#topBg > :last-child {
  height: 56px;
  font-size: 40px;
  color: rgba(255, 255, 255, 1);
  line-height: 56px;
}
/* 
span {
  display: block;
  height: 10px;
  background: white;
} */

#payment {
  position: absolute;
  z-index: 99;
  bottom: 15px;
  left: 15px;
  width: calc(100% - 30px);
  height: 46px;
  font-size: 16px;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  line-height: 46px;
  background: rgba(59, 138, 254, 1);
  border-radius: 23px;
  text-align: center;
}
</style>
