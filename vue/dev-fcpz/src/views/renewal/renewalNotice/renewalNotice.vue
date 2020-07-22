<template>
  <div class="renewal-notice">
    <div class="task-reminder">123</div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="less" scoped>
.renewal-notice {
  background: rgba(0, 0, 0, 1);
  opacity: 0.5;
  height: 100%;
  .task-reminder {
    width: 301px;
    height: 301px;
    background: #fff;
    position: fixed;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  }
}
</style>
