<template>
  <div class="brave-red-title" :style="{'backgroundImage': 'url(' + redTitle + ')'}"><slot></slot></div>
</template>
<script>
import redTitle from '@src/assets/activity/brave/red-title.png'
export default {
  data(){
    return {
      redTitle
    }
  }
};
</script>
<style lang="less" scoped>
.brave-red-title {
  margin: 0 auto;
  width: 137px;
  height: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-repeat: no-repeat;
  font-size: 18px;
  font-family:PingFangSC-Semibold,PingFang SC;
  font-weight:600;
  color:rgba(255,255,255,1);
}
</style>
