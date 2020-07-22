<!--
 * @Description: 非车配置---订单详情的头部状态栏
 * @Date: 2020-06-17 13:40:59
 * @Author: zhangminjie
 * @LastEditTime: 2020-06-29 20:59:51
 * @FilePath: \nonCarCongfig\src\views\order\dynamicOrderDetail\common\headerNav.vue
-->
<template>
  <div class="headerNav">
    <van-nav-bar title="订单详情" left-arrow @click-left="goBack" />
    <h1 class="hesuan">{{ statusName }}</h1>
    <h5 v-if="status == 4">
      距离失效还剩
      <van-count-down :time="time" format="DD 天 HH 时 mm 分 ss 秒" />
    </h5>
    <h5 v-if="status == 0 && isInsured">投保未确认，请继续完善保单信息</h5>
    <img :src="imgSrc" alt="header" />
    <div class="whiteSpace"></div>
  </div>
</template>

<script>
import { CountDown } from "vant";
export default {
  name: "headerNav",
  components: {
    [CountDown.name]: CountDown
  },
  props: {
    statusName: String, //保单状态名字 保障中，待支付...
    status: Number, //订单状态
    intervalDate: Number, //订单生效开始时间
    isInsured: Boolean //判断是否是暂存未下单 ,true就是暂存未下单
  },
  data: () => ({
    imgSrc: require("@/assets/order/orderDetail/workingRemotely.png"),
    time: ""
  }),
  watch: {
    intervalDate() {
      this.time = this.intervalDate * 1000;
    }
  },
  mounted() {
    //失效时间=保单生效日期零时-当前时间
    // this.time = this.intervalDate * 1000;
  },
  methods: {
    //返回上一页
    goBack() {
      this.$emit("goBackEvent");
      // this.$router.go(-1);
    }
  }
};
</script>

<style lang="less" scoped>
.headerNav {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 999;
  background: linear-gradient(
    92deg,
    rgba(33, 83, 235, 1) 0%,
    rgba(55, 147, 255, 1) 100%
  );
  padding-bottom: 20px;
  div,
  h1,
  h5 {
    box-sizing: border-box;
  }
  h1 {
    width: 100px;
    height: 18px;
    font-size: 18px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
    line-height: 18px;
    padding: 0 0 18px 15px;
  }
  h5 {
    // width: 210px;
    height: 20px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 20px;
    padding: 0 0 8px 15px;
    display: flex;
    .van-count-down {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(255, 255, 255, 1);
      line-height: 20px;
    }
  }

  .whiteSpace {
    width: 100%;
    height: 18px;
    border-radius: 15px 15px 0px 0px;
    background: #fff;
    margin-top: 8px;

    position: absolute;
    z-index: 111;
    bottom: -2px;
    left: 0;
  }
  img {
    width: 77px;
    height: auto;
    position: absolute;
    right: 15px;
    top: 20px;
    z-index: 999999;
  }
}

.van-nav-bar .van-icon {
  font-size: 18px;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
}
.van-nav-bar__title {
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
}
.van-nav-bar {
  height: 60px;
  line-height: 60px;
  width: 100%;
  background: linear-gradient(
    92deg,
    rgba(33, 83, 235, 1) 0%,
    rgba(55, 147, 255, 1) 100%
  );
}
[class*="van-hairline"]::after {
  border: none;
}
.orderTop div[data-v-fd6a7746],
.orderTop h1[data-v-fd6a7746],
.orderTop h5[data-v-fd6a7746] {
  box-sizing: border-box;
}
</style>
