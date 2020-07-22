<!--
 * @Description:  非车配置 --- 订单详情 --- 头部险种标题部分
 * @Date: 2020-06-17 14:03:56
 * @Author: zhangminjie
 * @LastEditTime: 2020-06-19 21:44:50
 * @FilePath: \nonCarCongfig\src\views\order\dynamicOrderDetail\common\topTitle.vue
-->
<template>
  <div class="topTitle">
    <div class="order">
      <van-card class="orderInfo">
        <div slot="title">
          <div class="orderInfoTop">
            <span>{{ insuranceInfo.desc }}</span>
          </div>
          <div class="orderInfoMid">
            <span>
              保障期限：{{ insuranceInfo.startDate }}至{{
                insuranceInfo.endDate
              }}
            </span>
            <p
              v-if="
                orderStauts == 0 &&
                  insuranceInfo.orderNo &&
                  !insuranceInfo.insureNo
              "
            ></p>
            <p v-else>
              <span class="strong">
                {{
                  orderStauts == 7 || orderStauts == 6
                    ? insuranceInfo.policyNo
                      ? insuranceInfo.policyNo
                      : insuranceInfo.insureNo
                    : insuranceInfo.insureNo
                }}
              </span>
              <span
                class="num"
                @click="copyPolicyNo"
                data-clipboard-action="copy"
                :data-clipboard-text="
                  orderStauts == 7 || orderStauts == 6
                    ? insuranceInfo.policyNo
                      ? insuranceInfo.policyNo
                      : insuranceInfo.insureNo
                    : insuranceInfo.insureNo
                "
                >复制</span
              >
            </p>
          </div>
        </div>
      </van-card>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import { Card } from "vant";
Vue.use(Card);
export default {
  name: "topTitle",
  props: {
    insuranceInfo: Object,
    orderStauts: Number
  },
  data: () => ({}),
  created() {
    console.log("insuranceInfo===" + JSON.stringify(this.insuranceInfo));
  },
  methods: {
    // 复制保单号
    copyPolicyNo() {
      let _this = this;
      let clipboard = new this.clipboard(".num");
      clipboard.on("success", function(e) {
        // console.info("Text:", e.text);
        _this.$toast("复制成功");
      });
      clipboard.on("error", function(e) {
        _this.$toast("复制失败");
        // console.error("Action:", e.action);
      });
    }
  }
};
</script>

<style lang="less" scoped>
.topTitle {
  background: rgba(247, 247, 247, 1);
  border-radius: 15px 15px 0px 0px;
  .orderTitle {
    font-size: 18px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    line-height: 25px;
  }
  .orderInfo {
    height: auto;
    background: rgba(255, 255, 255, 1);
    border-radius: 4px;
    display: flex;
    flex-direction: column;
    position: relative;
    box-sizing: border-box;
    padding: 15px;
  }
  .orderInfoTop {
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    line-height: 23px;
  }
  .orderInfoTop button {
    position: absolute;
    right: 0;
    top: 0;
    border: none;
    background: none;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    height: 21px;
    line-height: 21px;
  }
  .orderInfoTop .tempSave {
    width: 26px;
    color: rgba(102, 102, 102, 1);
  }
  .orderInfoTop .noPay,
  .guaranteed,
  .unused {
    height: 30px;
    line-height: 30px;
    font-size: 15px;
    color: rgba(255, 138, 61, 1);
  }
  .unused {
    color: rgba(51, 51, 51, 1);
  }
  .orderInfoMid {
    height: 42px;
    display: flex;
    flex-direction: column;
    margin-top: 5px;
    p {
      width: 345px;
      height: 32px;
      line-height: 32px;
      display: flex;
      // justify-content: space-between;
      align-items: center;
      border-radius: 5px;
      // background: rgba(216, 216, 216, 1);
      // padding: 0 8px;
      box-sizing: border-box;
      margin-top: 8px;
      .num {
        font-size: 12px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #000;
        line-height: 16px;
        margin-left: 15px;
        border: 1px solid #3b8afe;
        padding: 1px 3px;
        border-radius: 6px;
      }
    }
  }
  .orderInfoMid span {
    height: 19px;
    font-size: 13px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(153, 153, 153, 1);
    line-height: 19px;
  }
  .orderInfoMid span:last-child {
    margin-top: 4px;
  }
  .orderInfoBottom button {
    width: 82px;
    height: 30px;
    border-radius: 3px;
    border: 1px solid rgba(59, 138, 254, 0.25);
    outline: none;
    background: none;
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(59, 138, 254, 1);
    position: absolute;
    bottom: 5px;
    line-height: 30px;
  }
  .orderInfoBottom .copyOrder {
    right: 187px;
  }
  .orderInfoBottom .telInvoice {
    right: 102px;
  }
  .orderInfoBottom .telInsurance {
    right: 15px;
  }
  .strong {
    display: block;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
    line-height: 32px;
    font-size: 14px;
  }
}
</style>
