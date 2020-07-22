<!--
 * @Description: 公共组件---详情页中的小title
 * @Date: 2020-05-18 14:25:59
 * @Author: zhangminjie
 * @LastEditTime: 2020-05-18 14:27:52
 * @FilePath: \vue-sales\src\views\nonCar\autoConfig\common\title.vue
-->
<template>
  <h5
    class="h5-title"
    :style="{ backgroundImage: 'url(' + productDescTitle + ')' }"
  >
    <slot></slot>
  </h5>
</template>
<script>
import productDescTitle from "@/assets/nonCar/worryFree/productDesc-title.png";
export default {
  data() {
    return {
      productDescTitle
    };
  }
};
</script>
<style lang="less" scoped>
.h5-title {
  background-position: 0 0;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  width: 98px;
  height: 32px;
  align-items: center;
  justify-content: center;
  display: inline-flex;
  font-size: 16px;
  font-weight: 600;
  color: rgb(255, 255, 255);
}
</style>
