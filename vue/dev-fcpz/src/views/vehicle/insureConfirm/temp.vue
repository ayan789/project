<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-04-03 20:25:08
 * @Last Modified by: King
 * @Last Modified time: 2020-04-10 20:25:54
 * @FilePath: /src/views/vehicle/insureConfirm/temp.vue
-->

<template>
  <div class="container">
    <nav-bar type="vehicle" title="人工核保" @clickBack="back" />
    <div class="content">
      <div class="title">人工核保</div>
      <div class="button" @click="pay">去支付</div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { getPayNo } from "@src/http/module/vehicle";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      proposalNo: ({ proposalNo }) => proposalNo,
      insurePlan: ({ insurancePlan }) => insurancePlan,
      policyHolder: ({ policyHolder }) => policyHolder
    })
  },
  methods: {
    back() {
      this.$router.back();
    },
    pay() {
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
                proposalNo: this.proposalNo, //投保单号
                totalPremium: this.insurePlan.totalPremium,
                policyHolder: this.policyHolder.name, //投保人姓名
                paymentNo: res.data.payNo,
                checkNo: ""
              }
            });
          }
        },
        error => {}
      );
    }
  }
};
</script>

<style lang="less" scoped>
.caontainer {
  width: 100%;
  height: 100%;
  background: #f7f7f7;
}
.content {
  display: flex;
  flex: 1;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.title {
}
.button {
  width: calc(100% - 32px);
  margin: 16px;
  border-radius: 8px;
  background: burlywood;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  height: 48px;
}
</style>
