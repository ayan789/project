<!--
 * @Description: 
 * @Date: 2020-03-14 18:31:41
 * @Author: zhangminjie
 * @LastEditTime: 2020-03-30 11:04:10
 * @FilePath: \vue-sales\src\views\nonCar\common\moreInformation.vue
 -->
<template>
  <div id="non-car-more-information">
    <van-nav-bar title="更多信息" />
    <div class="content">
      <person-information-card type="policyHolder" />
      <person-information-card type="recognizee" />
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import PersonInformationCard from "./personInformation/personInformationCard";
export default {
  components: {
    [NavBar.name]: NavBar,
    [PersonInformationCard.name]: PersonInformationCard
  },
  data: () => ({}),
  methods: {}
};
</script>
、
<style>
#non-car-more-information {
  background-color: #f7f7f7;
}
.content {
  padding-top: 10px;
}
</style>
