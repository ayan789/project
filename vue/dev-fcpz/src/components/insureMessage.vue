<template>
  <div id="toast">
    <h3 id="title">核保信息</h3>
    <h3>核保结果类型：{{ resultType }}</h3>
    <h3>核保级别:{{ insureLevel }}</h3>
    <h3>是否需要验车:{{ isNeedCheckCar }}</h3>
    <h3>规则匹配信息：</h3>
    <div v-for="(item, index) in moreInfos" :key="index">
      <h3>{{ item }}</h3>
    </div>
    <h3 id="lastInfo">{{ resultInfo }}</h3>
    <div id="confirm" @click="confirmInfo">确定</div>
  </div>
</template>

<script>
export default {
  name: "insureMessage",
  components: {},
  props: {
    resultType: String,
    insureLevel: String,
    isNeedCheckCar: String,
    moreInfos: Array,
    resultInfo: String
  },
  data() {
    return {
      //   resultType: "返回录单员修改",
      //   insureLevel: "",
      //   isNeedCheckCar: "否",
      //   moreInfos: ["1. 投保人证件类型必须选身份证或护照", "2. 家用车出险1次"],
      //   resultInfo: "该订单自核不通过，需返回修改，如仍需提交，可申请特批。"
    };
  },
  methods: {
    confirmInfo: function() {
      this.$emit("confirmMessage");
    }
  }
};
</script>
<style scoped>
h3 {
  /* height: 20px; */
  line-height: 20px;
  font-size: 14px;
  font-weight: 400;
  color: #333333;
  overflow: hidden;
  margin: 0 20px;
  text-align: left;
}
#title {
  text-align: left;
  font-size: 16px;
  font-weight: bold;
  color: rgba(50, 50, 51, 1);
  line-height: 42px;
  height: 42px;
  margin-top: 9px;
}
.background {
  background: #f7f7f7;
  width: 100%;
  height: 100%;
  position: absolute;
}
.line {
  height: 1px;
  margin-left: 20px;
  background-color: rgba(229, 230, 232, 1);
}
#toast {
  margin: 0 auto;
  background: white;
}
#toast > :nth-child(2) {
  margin-top: 10px;
}
#confirm {
  text-align: center;
  color: #3b8afe;
  line-height: 46px;
  font-size: 16px;
  font-weight: 400;
  margin-top: 40px;
  border-top: 1px solid rgba(229, 230, 232, 1);
}
#lastInfo {
  margin: 20px 20px;
  height: 100%;
}
#backToast {
  width: 100%;
  height: 100%;
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
