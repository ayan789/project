<!--
 * @Description: 
 * @Date: 2020-03-30 11:48:32
 * @Author: zhangminjie
 * @LastEditTime: 2020-03-30 13:57:42
 * @FilePath: \vue-sales\src\views\nonCar\propertyInsurance\propertycList.vue
 -->
<!-- liumengjuan   特別条款 -->
<template>
  <div id="safeguard-detail" sticky>
    <!-- <van-nav-bar title="保障详情" /> -->
    <globalNav :title="globalNav.title" @clickBack="backToPrePage"></globalNav>
    <div class="title-container">
      <van-col class="coverage">条款须知</van-col>
      <van-col class="espent">保险条款</van-col>
      <van-col class="coverage-content">
        <div
          v-for="(text, index) in List"
          :key="index"
          class="espent"
          @click="argumrnt(index)"
        >
          {{ text }}
        </div>
      </van-col>
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  name: "propertyCList",
  components: {
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    appoint: "1",
    globalNav: {
      title: "保险条款"
    },
    List: [
      "大地险（备  -货运） [2014] 主65号一公",
      "大地险（备  -货运） [2014] 主59号一公"
    ]
  }),

  methods: {
    backToPrePage() {
      this.$globalBack();
    },
    argumrnt(index) {
      this.$router.push({ path: "propertyclause", query: { index: index } });
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
  width: 100%;
}
.item-title {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
}
.coverage-content {
  width: 100%;
}
.espent {
  width: 90%;
  line-height: 35px;
  border-bottom: 1px solid gainsboro;
}
.coverage {
  width: 90%;
  line-height: 40px;
  color: rgb(102, 102, 172);
  font-size: 16px;
  border-bottom: 1px solid gainsboro;
}
</style>
