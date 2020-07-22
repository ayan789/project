<template>
  <div class="noViolation">
    <consultLiterature></consultLiterature>
    <div class="description">暂无违章记录</div>
  </div>
</template>
<script>
import consultLiterature from "@/components/consultLiterature";
export default {
  components: {
    consultLiterature
  }
};
</script>
<style lang="less" scoped>
.noViolation {
  height: 609px;
  background: #fafafa;
  position: relative;
  .description {
    position: absolute;
    top: 339px;
    left: 146px;
    font-size: 14px;
    font-weight: 400;
    color: rgba(153, 153, 153, 1);
    line-height: 14px;
  }
}
</style>
