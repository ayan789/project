<!--
 * @Description: 未开具电子发票
 * @Date: 2020-03-31 10:47:47
 * @Author: zhangminjie
 * @LastEditTime: 2020-03-31 13:41:51
 * @FilePath: \vue-sales\src\views\order\elePolicy\invoiceApplicationUnOpened.vue
 -->
<template>
  <div class="invoiceApplicationUnOpened" id="invoiceApplicationUnOpened">
    <globalNav title="发票详情" @clickBack="backToPrePage" />
    <div class="top">
      <img :src="imgSrc" alt="top" />
    </div>
    <h1>暂未查询到电子发票</h1>
    <p>
      如您曾经开过纸质发票，则不能同时持有纸质发票和电子发票；如您未曾开过纸质发票和电子发票，请您点击下面的按钮开具电子发票
    </p>
    <button>申请开票</button>
  </div>
</template>
<script>
import { Overlay, Divider } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  components: {
    [Overlay.name]: Overlay,
    [Divider.name]: Divider,
    [globalNav.name]: globalNav
  },
  data() {
    return {
      imgSrc: require("@/assets/order/elePolicy/paySuccess.png")
    };
  },
  created() {},
  methods: {
    // 返回上一页
    backToPrePage() {
      this.$globalBack();
    }
  }
};
</script>
<style lang="less" scoped>
#invoiceApplicationUnOpened {
  padding: 150px 0 48px 0;
  background: rgba(255, 255, 255, 1);
  height: calc(100vh - 200px);
  text-align: center;
  position: relative;
  div {
    box-sizing: border-box;
  }
  .top {
    width: 140px;
    height: 135px;
    margin: 0 auto;
    img {
      width: 100%;
      height: auto;
    }
  }
  h1 {
    font-size: 20px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(50, 50, 51, 1);
    line-height: 20px;
    margin-top: 30px;
  }
  p {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
    padding: 0 35px;
    margin-top: 20px;
  }
  button {
    width: 344px;
    height: 46px;
    margin: 0 auto;
    background: linear-gradient(
      125deg,
      rgba(42, 128, 255, 1) 0%,
      rgba(42, 165, 255, 1) 100%
    );
    border-radius: 23px;
    border: none;
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
  }
}
</style>
