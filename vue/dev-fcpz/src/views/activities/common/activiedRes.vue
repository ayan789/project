/* 曹征前 组件--报名成功弹窗2020-3-12 未使用*/
<template>
  <div>
    <div>测试页面</div>
    <!--<div>{{isShowResProp}}</div>-->
    <div v-if="isShowRes == true">
      <p v-if="isOrderSuccessProp == true">
        <button @click="colseAlert">报名已已成功{{ isShowRes }}</button>
      </p>
      <p v-else>
        <button>报名未成功</button>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "activiedRes",
  props: {
    isShowResProp: Boolean, // 是否显示
    isOrderSuccessProp: Boolean //是否展示成功页面
  },

  watch: {
    isShowResProp: function(val, old) {
      console.info("watch==val==>>", val);
      console.info("watch==old==>>", old);
      this.isShowRes = this.isShowResProp;
      console.info("watch==isShowRes==>>", this.isShowRes);
    }
  },
  components: {},
  data() {
    return {
      isShowRes: ""
    };
  },
  methods: {
    //关闭弹窗
    colseAlert: function() {
      this.isShowRes = false;
      console.info("close--->", this.isShowRes);
    }
  }
};
</script>

<style lang="less" scoped></style>
