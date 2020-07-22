<!--
 * @Description: 
 * @Date: 2020-03-28 17:41:27
 * @Author: zhangminjie
 * @LastEditTime: 2020-03-30 13:54:44
 * @FilePath: \vue-sales\src\views\nonCar\propertyInsurance\propertyInstructions.vue
 -->
<!-- liumengjuan   特別约定 -->
<template>
  <div id="safeguard-detail" sticky>
    <!-- <van-nav-bar title="保障详情" /> -->
    <globalNav :title="globalNav.title" @clickBack="backToPrePage"></globalNav>
    <div class="title-container">
      <van-col class="coverage-content">
        <div v-for="(text, index) in XZ" :key="index">{{ text }}</div>
      </van-col>
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  name: "propertyInstructions",
  components: {
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    appoint: "1",
    globalNav: {
      title: "投保须知"
    },
    XZ: [
      "1. 仅限承保当地牌照货车作为承运工具的业务，异地承保业务需事先报总公司审核。",
      "2. 每一运输工具仅限投保一单，重复投保保额、赔偿限额不累计。"
    ]
  }),
  methods: {
    // 返回上一页
    backToPrePage() {
      this.$globalBack();
      // console.log(this.$router);
    }
  }
};
</script>

<style scoped>
#safeguard-detail {
  background-color: #f7f7f7;
  overflow: hidden;
}
#tab-content {
  padding-top: 10px;
}
#safeguard-detail {
  background: white;
}
.title-container {
  padding: 15px;
  margin: 60px 0 10px 0;
  background-color: white;
}
.item-title {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
}
.coverage-content {
  margin-top: 0.4rem;
}
</style>
