<!--
 * @Description: 理赔指南
 * @Date: 2020-4-6 14:25:45
 * @Author: LiuMengJuan
 * @LastEditTime: 2020-04-03 18:53:22
 * @FilePath: \vue-sales\src\views\nonCar\common\vantab.vue
 -->
<template>
  <div class="productContent">
    <div class="header">
      <van-nav-bar title="理赔指南" left-arrow @click-left="onClickLeft()"></van-nav-bar>
    </div>
    <div class="titleWhite" id="2">
      <h5 class="selectDemont">理赔说明</h5>
      <img :src="productIcons[0]" class="lipei" />
    </div>
  </div>
</template>
<script>
export default {
  name: "claim",
  components: {},
  data() {
    return {
      productIcons: [require("@/assets/nonCar/propertyInsurance/bianZhi.png")]
    };
  },
  methods: {
    // 返回上一页
    onClickLeft() {
      this.$globalBack();
    }
  }
};
</script>
<style lang="less" scoped>
.productContent {
  background: linear-gradient(
    125deg,
    rgba(42, 128, 255, 1) 0%,
    rgba(42, 165, 255, 1) 100%
  );
  .lipei {
    width: 355px;
    height: 336px;
    margin: auto;
  }
  h5 {
    text-align: center;
    font-size: 16px;
    color: white;
    padding-bottom: 10px;
  }
  .titleWhite {
    text-align: center;
    padding: 15px 0 5px 0;
  }
}
</style>
