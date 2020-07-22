<!--
 * @Description: 
 * @Date: 2020-05-18 16:59:11
 * @Author: zhangminjie
 * @LastEditTime: 2020-05-18 17:01:33
 * @FilePath: \vue-sales\src\views\nonCar\autoConfig\common\question-item.vue
--> 
<template>
  <div
    class="question-item"
    :style="{ backgroundImage: 'url(' + question + ')' }"
    @click="isShow = !isShow"
  >
    <div class="question">
      <slot name="question"></slot>
      <van-icon :name="isShow ? 'arrow-up' : 'arrow-down'" color="#BFBFBF" />
    </div>
    <div class="answer" v-if="isShow"><slot name="answer"></slot></div>
  </div>
</template>
<script>
import question from "@/assets/nonCar/worryFree/question.png";
export default {
  name: "questionItem",
  props: ["index"],
  data() {
    return {
      question,
      isShow: false
    };
  },
  created() {
    if (this.index == 0) {
      this.isShow = true;
    }
  }
};
</script>
<style lang="less" scoped>
.question-item {
  background-size: 22px 20px;
  background-position: 0 0;
  background-repeat: no-repeat;
  padding-left: 32px;
  margin-top: 24px;
  position: relative;
  .question {
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 600;
    color: rgb(51, 51, 51);
    line-height: 20px;
    padding-right: 14px;
  }
  .answer {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    // color:rgb(196, 191, 191);
    color: #999999;
    line-height: 17px;
    margin-top: 8px;
  }
  .van-icon {
    position: absolute;
    right: 0;
    top: 3px;
  }
}
</style>
