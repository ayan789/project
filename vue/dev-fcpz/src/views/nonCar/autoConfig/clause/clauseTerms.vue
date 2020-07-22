<!--
 * @Description: 产品配置---条款列表
 * @Date: 2020-06-01 16:10:14
 * @Author: zhangminjie
 * @LastEditTime: 2020-06-29 14:17:12
 * @FilePath: \nonCarCongfig\src\views\nonCar\autoConfig\clause\clauseTerms.vue
--> 
<template>
  <div id="safeguard-detail" sticky>
    <globalNav :title="listObj.title" @clickBack="backToPrePage"></globalNav>
    <div class="title-container">
      <van-col class="coverage-content">
        <router-link
          v-for="item in list"
          :key="item.code"
          class="espent"
          :to="{
            name: 'clausePdf',
            query: { title: item.title, link: item.link }
          }"
          >{{ item.title }}
        </router-link>
      </van-col>
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../../components/globalNav";
export default {
  name: "personalTerms",
  components: {
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    listObj: {},
    list: []
  }),
  mounted() {
    this.listObj = JSON.parse(this.$route.query.list);
    this.list = this.listObj.list;
  },
  methods: {
    backToPrePage() {
      if ("scrollRestoration" in history) {
        history.scrollRestoration = "manual"; //改为manual之后，就不会记录滚动位置
      }
      this.$globalBack();
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
  display: block;
  color: rgb(59, 138, 254);
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
