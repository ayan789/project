/* 曹征前公共组件--内容 */
<template>
  <div class="nav-activity">
    <div
      style="
        height: 20px;
        width: 20px;
        position: absolute;
        left:10px;
        "
      @click="clickBack"
    >
      <img
        style="width:100%;height:100%"
        src="@/assets/activity/left.png"
        alt="edit"
      />
    </div>
    <div>
      <span style="font-size: 20px;color:#FFFFFF;font-weight: 500">{{ title }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "navActivity",
  props: {
    title: String
  },
  created() {},
  data: () => ({
    // title: "少林功夫好呀"
  }),
  methods: {
    clickBack() {
      this.$globalBack();
    }
  }
};
</script>

<style lang="less" scoped>
.nav-activity {
  display: flex;
    align-items: center;
    justify-content: center;
}
</style>
