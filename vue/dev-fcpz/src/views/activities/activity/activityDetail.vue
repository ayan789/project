/* 曹征前 活动详情 */
<template>
  <div class="activityDetail">
    <!--头部背景图片-->
    <div :class="isIng ? 'header' : 'header1'">
      <NavActivity :title="''" />
    </div>
    <!--活动详情具体逻辑-->
    <ActivityContent style="margin-top: -0.5px" @onIng="onIng" />
  </div>
</template>

<script>
import ActivityContent from "../common/activityContent.vue";
import NavActivity from "../common/navActivity";
export default {
  name: "activityDetail",
  components: {
    ActivityContent,
    NavActivity
  },
  data() {
    return {
      isIng: false //false不是进行中，true进行中
    };
  },
  methods: {
    onIng(value) {
      if (value && value == 2) {
        this.isIng = true;
      } else {
        this.isIng = false;
      }
    }
  }
};
</script>

<style lang="less" scoped>
.activityDetail {
  .header {
    width: 100%;
    height: 30vh;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: url("../../../assets/activity/heard.png");
    background-size: 100% 100%;
    padding-top: 20px;
  }
  .header1 {
    width: 100%;
    height: 30vh;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: url("../../../assets/activity/noActive.png");
    background-size: 100% 100%;
    padding-top: 20px;
  }
}
</style>
