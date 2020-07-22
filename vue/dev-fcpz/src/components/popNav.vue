<template>
  <div class="popNav">
    <van-icon
      @click="goBack"
      name="arrow-left"
    />

    {{ popTitle }}
  </div>
</template>
<script>
export default {
  name: "popNav",
  props: {
    popTitle: {
      type: String,
      default: "信息"
    }
  },
  data () {
    return {};
  },
  methods: {
    //返回上一页
    goBack () {
      this.$globalBack();
    }
  }
};
</script>
<style lang="less" scoped>
.popNav {
  // background-color: #f7f7f8;
  background: url("../assets/common/titleNav.png") no-repeat;
  background-size: 100% 84px;
  color: #ffffff;
  width: 100%;
  text-align: center;
  height: 64px;
  line-height: 44px;
  position: fixed;
  padding: 0.3rem 0;
  z-index: 1;
  font-size: 18px;
}
.van-icon {
  color: #ffffff;
  position: fixed;
  top: 24px;
  left: 10px;
}
</style>
