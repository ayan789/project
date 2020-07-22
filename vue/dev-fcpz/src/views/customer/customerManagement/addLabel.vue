<template>
  <div class="labelPage">
    <div class="nocarincome">
      <options :options="nocarList" v-model="value" :isMultiply="true"></options>
      <div class="labelLine"></div>
    </div>
    <div class="labelPageRecommend">
      <h3>保险偏好类</h3>
      <options :options="incomeList" v-model="value" :isMultiply="true"></options>
    </div>
    <div class="saveLabel">保存</div>
  </div>
</template>
<script>
import options from "@/components/options.vue";
export default {
  components: {
    options
  },
  data() {
    return {
      value: [],
      incomeList: [
        "价格敏感",
        "关注理赔",
        "非车险产品偏好",
        "喜欢理财",
        "高旅行为多"
      ],
      nocarList: ["非车险客户"]
    };
  },
  methods: {}
};
</script>
<style lang="less" scoped>
.labelPage {
  /*width:100%;*/
  padding: 14px 15px 0 15px;
  .nocarincome {
    .labelLine {
      width: 260px;
      height: 1px;
      background: #e5e6e8;
    }
  }

  .labelPageRecommend {
    padding: 18px 6px 0 15px;
    h3 {
      width: 77px;
      height: 21px;
      font-size: 15px;
      font-family: PingFang-SC-Medium, PingFang-SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 21px;
      margin-bottom: 11px;
      padding-left: 3px;
    }
  }
  .saveLabel {
    width: 345px;
    height: 44px;
    background: rgba(59, 138, 254, 1);
    border-radius: 23px;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 44px;
    text-align: center;
    position: absolute;
    bottom: 38px;
  }
}
</style>
