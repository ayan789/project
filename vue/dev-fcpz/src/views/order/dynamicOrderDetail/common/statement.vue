<!--
 * @Description: 非车配置 -- 订单详情---底部声明部分
 * @Date: 2020-06-17 14:03:56
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 15:19:21
 * @FilePath: \nonCarCongfig\src\views\order\dynamicOrderDetail\common\statement.vue
-->
<template>
  <div class="statement">
    <p>
      查看
      <span
        v-for="(item, index) in termsAndConditions"
        :key="index"
        @click="gotoClause(item)"
      >
        {{
          index == termsAndConditions.length - 1
            ? "《" + item.title + "》"
            : "《" + item.title + "》、"
        }}</span
      >
    </p>
    <div v-if="insuranceType == 2">
      <div class="bottom" v-if="orderStauts == 7 || orderStauts == 10">
        <button class="blue width100" @click="copyInsurance">复制投保单</button>
      </div>
      <div class="bottom" v-if="!(orderStauts == 7 || orderStauts == 10)">
        <button
          class="blue"
          :class="[isSeeFee == '0' ? 'blue width100' : '']"
          v-if="
            (orderStauts == 0 && isUpload) ||
              orderStauts == 2 ||
              orderStauts == 4
          "
          @click="copyInsurance"
        >
          复制投保单
        </button>
        <button
          class="blue"
          v-if="
            order.insureNo && order.isNeedSign == '1' && order.signStatus == '0'
          "
          @click="gotoSignature(order.insureNo)"
        >
          去签字
        </button>
        <button
          class="blue width100"
          v-if="orderStauts == 0 && !order.insureNod"
          @click="continueInput"
        >
          继续录入
        </button>
        <button
          class="blue"
          v-if="orderStauts == 1"
          @click="submitUnderwriting"
        >
          提交核保
        </button>
        <button
          class="blue"
          v-if="order.insureNo && order.isNeedUploadImg == '1'"
          @click="uploadImage"
        >
          影像上传
        </button>
        <button
          class="blue"
          v-if="
            (order.isIssueAfterPay == '1' && orderStauts == 4) ||
              (orderStauts == 2 &&
                order.isNeedSign == '1' &&
                order.signStatus == '1') ||
              (orderStauts == 2 && order.isNeedSign == '0')
          "
          @click="immediatelyPay"
        >
          立即支付
        </button>
      </div>
    </div>
    <div v-if="insuranceType == 0">
      <div class="bottom" v-if="orderStauts == 7 || orderStauts == 10">
        <button class="blue width100" @click="copyInsurance">复制投保单</button>
      </div>
      <div class="bottom" v-if="!(orderStauts == 7 || orderStauts == 10)">
        <button
          class="blue"
          v-if="
            (orderStauts == 0 && isUpload) ||
              orderStauts == 2 ||
              orderStauts == 4
          "
          @click="copyInsurance"
        >
          复制投保单
        </button>
        <button
          class="blue width100"
          v-if="orderStauts == 0 && !order.insureNod"
          @click="continueInput"
        >
          继续录入
        </button>
        <button
          class="blue"
          v-if="orderStauts == 1"
          @click="submitUnderwriting"
        >
          提交核保
        </button>
        <button
          class="blue"
          v-if="order.insureNo && order.isNeedUploadImg == '1'"
          @click="uploadImage(order.insureNo)"
        >
          影像管理
        </button>
        <button
          class="blue"
          v-if="
            order.insureNo && order.isNeedSign == '1' && order.signStatus == '0'
          "
          @click="gotoSignature(order.insureNo)"
        >
          去签字
        </button>
        <button
          class="blue"
          v-if="
            orderStauts == 4 ||
              (orderStauts == 2 &&
                order.isNeedSign == '1' &&
                order.signStatus == '1') ||
              (orderStauts == 2 && order.isNeedSign == '0')
          "
          @click="immediatelyPay"
        >
          立即支付
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "statement",
  props: {
    insuranceType: Number, // 0 ： 意健    2 财产险
    orderStauts: Number, //订单状态
    isUpload: Boolean,
    signStatus: String,
    isSeeFee: String, //0见费，1非见费
    termsAndConditions: Array,
    order: Object //order对象
  },
  data: () => ({}),
  methods: {
    // 复制投保单
    copyInsurance() {
      this.$emit("copyInsuranceEvent");
    },
    // 继续录入
    continueInput() {
      this.$emit("continueInputEvent");
    },
    // 提交核保
    submitUnderwriting() {
      this.$emit("submitUnderwritingEvent");
    },
    // 上传影像
    uploadImage() {
      this.$emit("uploadImageEvent");
    },
    // 去签字
    gotoSignature() {
      this.$emit("gotoSignatureEvent");
    },
    // 立即支付
    immediatelyPay() {
      this.$emit("immediatelyPayEvent");
    },
    // 跳转到相应的条款内
    gotoClause(item) {
      if (item.children && item.children.length > 0) {
        let obj = {
          list: item.chlidren,
          title: item.title
        };
        this.$router.push({
          name: "clauseTerms",
          query: { list: JSON.stringify(obj) }
        });
      } else {
        this.$router.push({
          name: "clausePdf",
          query: { title: item.title, link: item.link }
        });
      }
    },
    // 跳转到相应的条款内容页面
    redirectTerms(type) {
      switch (type) {
        case 1: //投保须知
          this.$router.push({ name: "propertyInstructions" });
          break;

        case 2: //理赔指南
          this.$router.push({ name: "claim" });

          break;

        case 3: //保险条款
          this.$router.push({ name: "propertyCList" });
          break;
        case 4: //特别约定
          this.$router.push({ name: "propropertyArgument" });
          break;
      }
    }
  }
};
</script>

<style lang="less" scoped>
.statement {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(247, 247, 247, 1);
  box-sizing: border-box;
  button {
    border: none;
    outline: none;
  }
}
.statement p {
  width: 100%;
  height: 69px;
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  padding: 20px 15px 15px 15px;
  box-sizing: border-box;
}
.statement p span {
  color: #3b8afe;
}
.bottom {
  display: flex;
  box-shadow: 0px 2px 10px 0px rgba(0, 0, 0, 0.1);

  .white {
    flex: 1;
    // width: 188px;
    height: 46px;
    line-height: 46px;
    text-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(59, 138, 254, 1);
    background: #fff;
  }
  .blue {
    flex: 1;
    // width: 188px;
    height: 46px;
    line-height: 46px;
    background: linear-gradient(
      125deg,
      rgba(42, 128, 255, 1) 0%,
      rgba(42, 165, 255, 1) 100%
    );
    box-shadow: 0px 2px 10px 0px rgba(0, 0, 0, 0.1);
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    text-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  }
  .lightBlue {
    flex: 1;
    height: 46px;
    color: rgba(72, 104, 149, 1);
    box-shadow: 0px 2px 10px 0px rgba(0, 0, 0, 0.1);
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    text-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  }
  .width100 {
    width: 100%;
  }
}
</style>
