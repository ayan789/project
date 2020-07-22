/* zhangminjie 订单详情公共组件--底部的协议声明--待支付，保障中，暂存*/
<template>
  <div class="orderDetailBottom">
    <div class="statement1" v-if="unique == 3">
      <p>
        查看
        <span>《投保须知及声明》</span>
        <span>《服务协议》</span>和
        <span>《保险条款》</span>
      </p>
    </div>

    <div class="statement" v-if="insuranceType == 1 || insuranceType == 2">
      <div class="statementTop">
        <p>
          <input
            class="agreeStatement"
            type="radio"
            name="agree"
            v-model="agree"
            @click="changeAgree"
          />
        </p>
        <p>
          我确认并同意
          <span>《投保须知及声明》</span>
          <span>《服务协议》</span>和
          <span>《保险条款》</span>
        </p>
      </div>
      <div class="statementBottom">
        <div class="copyInsuranceOut">
          <van-icon name="debit-pay" class="copyIcon" />
          <button class="copyInsurance">复制投保单</button>
        </div>
        <button class="immediatelyPay">{{ btnName }}</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    btnName: String,
    isAgree: Boolean,
    unique: Number, //区分健康险和财产险中保障中的状态
    insuranceType: Number //1 财产险 2 健康险
  },
  data: () => ({
    agree: false
  }),
  methods: {
    // 签署协议切换
    changeAgree() {
      this.agree = this.isAgree;
      if (this.agree) {
        this.agree = false;
      } else {
        this.agree = true;
      }
      this.$emit("changeAgreeEvent", this.agree);
    }
  }
};
</script>

<style lang="less" scoped>
.orderDetailBottom {
  border-top: 1px solid #e5e6e8;
  font-family: PingFangSC-Regular, PingFang SC;

  .statement1 {
    margin-top: 11px;
    height: 107px;
    padding: 11px 15px;
    background: rgba(247, 247, 247, 1);
    box-sizing: border-box;

    p {
      width: 100%;
      text-align: center;
      height: 19px;
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      line-height: 19px;

      span {
        color: #3b8afe;
      }
    }
  }

  .statement {
    padding-top: 7px;
    width: 100%;
    margin-top: 0;
    background: rgba(255, 255, 255, 1);
    display: flex;
    font-size: 13px;
    flex-direction: column;
    position: absolute;
    bottom: 0;

    .statementTop {
      display: flex;
      align-items: center;
      padding-right: 23px;
      height: 38px;
      font-size: 13px;
      line-height: 19px;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);

      p:first-child {
        width: 16px;
        height: 16px;
        margin: 0 22px 0 16px;
      }

      p:last-child {
        width: 299px;
        text-align: left;

        span {
          color: #3b8afe;
        }
      }

      .agreeStatement {
        width: 16px;
        height: 16px;
        vertical-align: middle;
        padding: 0;
      }
    }

    .statementBottom {
      width: 100%;
      height: 46px;
      display: flex;
      /*justify-content: space-between;*/
      /*align-items: center;*/
      flex-direction: row;

      button {
        outline: none;
        border: none;
        background: none;
        display: flex;
        flex: 1;
      }

      .copyInsuranceOut {
        display: flex;
        flex: 1;
        font-size: 11px;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        justify-content: end;
        align-items: center;
        height: 46px;

        .copyInsurance {
          width: 60px;
          height: 14px;
          line-height: 14px;
          margin-left: 7px;
        }

        .copyIcon {
          margin-left: 16px;
        }
      }

      .immediatelyPay {
        width: 120px;
        height: 46px;
        background: rgba(59, 138, 254, 1);
        font-size: 16px;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
        line-height: 22px;
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
  }
}
</style>
