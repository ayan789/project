<!--
 * @Author: heweiwei
 * @Page: 
 * @Date: 2020-03-04 11:02:28
 * @Last Modified by: King
 * @Last Modified time: 2020-03-14 16:53:28
 * @FilePath: /src/components/confirmBottom.vue
-->
<template>
  <div class="default" @click="confirm">{{ title || "确定" }}</div>
</template>
<script>
export default {
  props: {
    title: String
  },
  methods: {
    confirm() {
      this.$emit("click");
    }
  }
};
</script>
<style scoped>
.default {
  width: 345px;
  height: 46px;
  /* background: rgba(59, 138, 254, 1); */
  background: linear-gradient(125deg, #2a80ff 0%, #2aa5ff 100%);
  border-radius: 23px;
  font-size: 16px;
  color: white;
  line-height: 46px;
  text-align: center;
  position: fixed;
  bottom: 15px;
  left: 16px;
  justify-content: center;
  align-items: center;
}
</style>
