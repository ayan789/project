<template>
  <div class="cover">
    <div class="coverBg animated fadeOutUp" style="animation-delay: .5s;"></div>
    <div class="lightPart animated fadeIn" style="animation-delay: 2.5s;" v>
      <div
        class="light animated infinite fadeIn faster"
        style="animation-delay: 3s;"
      >
        <img :src="light" alt="" />
      </div>
    </div>
    <div class="actCup animated zoomIn" style="animation-delay: 1.5s;">
      <img :src="actCup" alt="" />
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      actCup: require("@/assets/performance/actCup.png"),
      light: require("@/assets/performance/light.png")
    };
  }
};
</script>
<style lang="less" scoped>
.cover {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8);
  animation: outCover 3s ease;
  animation-delay: 6s;
  -webkit-animation: outCover 3s ease;
  -webkit-animation-delay: 6s;
  .coverBg {
    position: absolute;
    height: 100%;
    width: 100%;
    background: black;
    z-index: 9;
  }
  //   光线
  .lightPart {
    position: relative;
    margin: 0 auto;
    top: 160px;
    .light {
      img {
        width: 100%;
      }
    }
  }
  //   图片
  .actCup {
    width: 251px;
    height: 350px;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    img {
      width: 100%;
      height: 100%;
    }
  }
}
@keyframes outCover {
  from {
    background-color: transparent;
    top: 0;
    left: 0;
    transform: scale(1);
  }
  to {
    top: 999px;
    left: -999px;
    transform: scale(0);
  }
}

@-webkit-keyframes outCover {
  from {
    background-color: transparent;
    top: 0;
    left: 0;
    transform: scale(1);
  }
  to {
    top: 999px;
    left: -999px;
    transform: scale(0);
  }
}
</style>
