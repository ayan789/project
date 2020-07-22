<template>
  <div class="woodpecker-content-wrapper" :style="{'minHeight':minHeight+'px',margin:margin,background:'#ffffff'}">
    <div
      class="woodpecker-bg-status header"
      :style="{ height:headerHeight+'px','backgroundImage': 'url(' + whiteHeardTop + ')'}"
    ></div>
    <slot></slot>

  </div>
</template>
<script>
import whiteHeardTop from '@src/assets/activity/woodpecker/whiteHeardTop.png'
import whiteHeardBottom from '@src/assets/activity/woodpecker/whiteHeardBottom.png'
import blueHeardTop from '@src/assets/activity/woodpecker/blueHeardTop.png'
import blueHeardBottom from '@src/assets/activity/woodpecker/blueHeardBottom.png'
export default {
  props: ["minHeight", "headerHeight", "footerHeight",'margin','other'],
  data(){
    return {
      whiteHeardTop,whiteHeardBottom,blueHeardTop,blueHeardBottom,
    }
  }
};
</script>
<style lang="less" scoped>
.woodpecker-content-wrapper {
  color: rgb(8, 90, 81);
  width: 345px;
  border-radius: 12px;
  .header{
    margin-bottom: -20px;
  }
}
</style>