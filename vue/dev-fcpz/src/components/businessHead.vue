<template>
  <div class="businessHeadCover">
    <div class="iconPos" @click="$router.go(-1)" v-show="back">
      <van-icon name="arrow-left" color="white" size="22px" />
    </div>
    <img
      src="../assets/vehicle/businessPlan/busNav.png"
      class="navImg"
      alt=""
    />
  </div>
</template>
<script>
export default {
  props: {
    back: {
      type: Boolean,
      default: true
    }
  }
};
</script>
<style lang="less" scoped>
.businessHeadCover {
  position: relative;
  .iconPos {
    position: absolute;
    left: 16px;
    top: 17px;
  }
  .navImg {
    width: 100%;
  }
}
</style>
