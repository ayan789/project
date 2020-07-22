<template>
<body>
  <div class="background" @click="clickBackground"></div>
    <van-popup
      v-model="isShow"
      position="center"
      :overlay-style="{background: 'rgba(0, 0, 0, 0.3)'}"
      :overlay="true"
    >
    <insureMessage
      @confirmMessage="confirmMessage"
      :resultType="resultType"
      :insureLevel="insureLevel"
      :isNeedCheckCar="isNeedCheckCar"
      :moreInfos="moreInfos"
      :resultInfo="resultInfo"
    />
  </van-popup>
</body>
</template>

<script>
import insureMessage from "@/components/insureMessage.vue";
export default {
  name: "insureConfirm",
  components: {
    insureMessage
  },
  props: {
    // msg: String,
  },
  data() {
    return {
      isShow: true, //是否显示弹窗
      resultType: "返回录单员修改",
      insureLevel: "",
      isNeedCheckCar: "否",
      moreInfos: ["1. 投保人证件类型必须选身份证或护照", "2. 家用车出险1次"],
      resultInfo: "该订单自核不通过，需返回修改，如仍需提交，可申请特批。"
    };
  },
  methods: {
    confirmMessage() {
      //console.log("点击了一下确定");
      this.isShow = !this.isShow;
    },
    clickBackground() {
      //console.log("点击了背景");
      this.isShow = !this.isShow;
    }
  }
};
</script>
<style scoped>
.van-popup {
  width: calc(100% - 60px);
  border-radius: 5px;
}
.background {
  height: 100%;
  width: 100%;
}
</style>
