/* zhangminjie 订单详情公共组件--底部的协议声明--已失效订单*/
<template>
  <div class="statement">
    <!-- <p>
      查看
      <span>《投保须知及声明》</span>
      <span>《服务协议》</span>和
      <span>《保险条款》</span>
    </p> -->
    <p>
      查看
      <span @click="redirectTerms(1)">《投保须知》</span>、
      <span @click="redirectTerms(2)">《理赔指南》</span>、
      <span @click="redirectTerms(3)">《保险条款》</span>和
      <span @click="redirectTerms(4)">《特别约定》</span>
    </p>
    <div v-if="insuranceType == 1">
      <div class="bottom" v-if="orderStauts == 7 || orderStauts == 10">
        <button class="blue width100" @click="copyInsurance">复制投保单</button>
      </div>
      <div class="bottom" v-if="!(orderStauts == 7 || orderStauts == 10)">
        <button
          class="white"
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
          class="blue width100"
          v-if="orderStauts == 0 && !isUpload"
          @click="continueInput"
        >
          继续录入
        </button>
        <button
          class="white"
          v-if="orderStauts == 1"
          @click="submitUnderwriting"
        >
          提交核保
        </button>
        <button
          class="blue"
          v-if="orderStauts == 1 || (orderStauts == 0 && isUpload)"
          @click="uploadImage"
        >
          影像上传
        </button>
        <button
          class="blue"
          v-if="(orderStauts == 2 || orderStauts == 4) && isSeeFee != '0'"
          @click="immediatelyPay"
        >
          立即支付
        </button>
      </div>
    </div>
    <div v-if="insuranceType == 2">
      <div class="bottom" v-if="orderStauts == 7 || orderStauts == 10">
        <button class="blue width100" @click="copyInsurance">复制投保单</button>
      </div>
      <div class="bottom" v-if="!(orderStauts == 7 || orderStauts == 10)">
        <button
          class="white"
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
          v-if="orderStauts == 0 && !isUpload"
          @click="continueInput"
        >
          继续录入
        </button>
        <button
          class="white"
          v-if="orderStauts == 1"
          @click="submitUnderwriting"
        >
          提交核保
        </button>
        <button
          class="blue"
          v-if="orderStauts == 0 || (orderStauts == 2 && signStatus == '0')"
          @click="gotoSignature"
        >
          去签字
        </button>
        <button
          class="blue"
          v-if="orderStauts == 4 || (orderStauts == 2 && signStatus == '1')"
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
  name: "orderDetailStatementUnused",
  props: {
    insuranceType: Number, //
    orderStauts: Number, //订单状态
    isUpload: Boolean,
    signStatus: String,
    isSeeFee: String //0见费，1非见费
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
    // 立即支付
    immediatelyPay() {
      this.$emit("immediatelyPayEvent");
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
    },
    //去签字 gotoSignatureEvent
    gotoSignature() {
      this.$emit("gotoSignatureEvent");
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
    width: 188px;
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
    width: 188px;
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
  .width100 {
    width: 100%;
  }
}
</style>
