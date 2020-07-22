<!--
 * @Author: 
 * @Page: 
 * @Date: 2020-02-20 11:05:03
 * @Last Modified by: King
 * @Last Modified time: 2020-07-03 15:30:28
 * @FilePath: /src/App.vue
-->
<template>
  <a-config-provider :locale="locale">
    <div class="container" id="app">
      <keep-alive v-if="!destory">
        <router-view class="router-view" v-if="$route.meta.keepAlive" />
      </keep-alive>
      <router-view class="router-view" v-if="!$route.meta.keepAlive" />
    </div>
  </a-config-provider>
</template>
<script>
// 1.引入
import zhCn from "ant-design-vue/lib/locale-provider/zh_CN";
import vehicle from "./router/module/vehicle";
export default {
  data() {
    return {
      // 2.赋值
      locale: zhCn,
      destory: false
    };
  },
  watch: {
    $route(to, from) {
      if (vehicle.find(item => item.name == from.name)) {
        if (!vehicle.find(item => item.name == to.name)) {
          this.destory = true;
          console.warn("别说了, 先销毁一下");
          this.$nextTick(() => {
            this.destory = false;
            console.warn("销毁完毕, 恢复现场");
          });
        }
      }
    }
  },
  beforeDestroy() {
    this.$off();
  }
};
</script>
<style lang="less">
// .container {
//   width: 100%;
//   height: 100%;
//   overflow: hidden;
// }
// .router-view {
//   width: 100%;
//   height: 100%;
//   overflow: hidden;
//   user-select: none;
// }
#app {
  position: relative;
}
#app .router-view {
  width: 100%;
  height: 100%;
  user-select: none;
}
</style>
