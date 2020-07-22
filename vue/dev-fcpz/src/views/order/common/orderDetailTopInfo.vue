/* zhangminjie 订单详情--公共组件--暂存和待支付的头部卡片信息 */
<template>
  <div class="order">
    <van-card class="orderInfo">
      <div slot="title">
        <div class="orderInfoTop">
          <span>{{ insuranceInfo.desc }}</span>
          <button class="unused" v-if="orderStauts == 2" @click="tempSave(2)">
            暂存
          </button>
          <button
            class="guaranteed"
            v-if="orderStauts == 3"
            @click="tempSave(3)"
          >
            待支付
          </button>
        </div>
        <div class="orderInfoMid">
          <span>{{ insuranceInfo.id }}</span>
          <span>保障期限：{{ insuranceInfo.date }}</span>
        </div>
      </div>
    </van-card>
  </div>
</template>

<script>
export default {
  name: "orderDetailTopInfo",
  props: {
    insuranceInfo: Object,
    orderStauts: Number
  },
  data: () => ({}),
  methods: {
    // 按钮操作事件
    tempSave(status) {
      this.$emit("btnEvent", status);
    }
  }
};
</script>

<style lang="less" scoped>
.order {
  border-radius: 20px 20px 0px 0px;
  .van-card__content {
    min-height: auto;
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
    .orderInfoTop {
      height: 25px;
      font-size: 18px;
      font-family: PingFangSC-Semibold, PingFang SC;
      font-weight: 600;
      color: rgba(51, 51, 51, 1);
      line-height: 25px;
      button {
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
    }
  }
  .orderInfoMid {
    height: 42px;
    display: flex;
    flex-direction: column;
    margin-top: 5px;
    span {
      height: 19px;
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      line-height: 19px;
    }
    span:last-child {
      margin-top: 4px;
    }
  }
}
</style>
