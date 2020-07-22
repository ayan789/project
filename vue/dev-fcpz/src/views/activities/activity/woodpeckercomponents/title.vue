<template>
  <div class="woodpecker-title">
    <div class="clip-text" v-if='index'>0{{index}}</div>
    <div class="line"></div>
    <span>
      <slot></slot>
    </span>
    <div class="line"></div>
  </div>
</template>
<script>
export default {
  props:['index']
};
</script>
<style lang="less" scoped>
.woodpecker-title {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 17px;
  font-size: 17px;
  font-family: "FZY4JW--GB1-0", "FZY4JW--GB1";
  margin: 23px 0 15px 0;
  color: rgb(8, 90, 81);
  & > span {
    margin: 0 10px;
  }
  .line {
    width: 12px;
    height: 2px;
    background: rgb(8, 90, 81);
    border-radius: 1px;
    opacity: 0.5;
  }
  .clip-text{
    position: absolute;
    right: 45%;
    top:-16px;
    width:34px;
    height:27px;
    font-size:27px;
    font-family:hzgb;
    color:rgba(8,90,81,1);
    line-height:27px;
    background:linear-gradient(180deg, rgba(8,90,81,1) 0%, rgba(57,135,127,0) 100%);
    -webkit-background-clip:text;
    -webkit-text-fill-color:transparent;
  }
}
</style>