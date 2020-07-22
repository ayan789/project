<!-- 货运无忧责任免赔除-->
<template>
  <div id="safeguard-detail" sticky>
    <globalNav :title="globalNav.title" @clickBack="backToPrePage"></globalNav>
    <div class="title-container">
      <van-col class="coverage-content" v-if="appoint == '0'">
        <div v-for="(text, index) in JXTY" v-html="text" :key="index">{{ text }}</div>
      </van-col>
      <van-col class="coverage-content" v-if="appoint == '1'">
        <div v-for="(text, index) in AH" v-html="text" :key="index">{{ text }}</div>
      </van-col>
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  name: "Deductible",
  components: {
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    appoint: "1",
    globalNav: {
      title: "责任免除"
    },
    eaer: "0",
    JXTY: [
      "1.附加第三者责任险每次事故绝对免赔额100万或被保险人从其他保单中获得的赔偿金额之和，两者以高者为准。",
      "2.主险每次事故绝对免赔额为2000元或损失金额的10%，两者以高者为准。若主险保险金额低于货物保险价值，保险人对货物损失按保险金额与保险价值的比例计算赔偿后再扣除免赔予以赔付。 ",
      "3. 发生保险事故时，违反有关机动车辆装载规定的，主险及附加险均另行加扣10%的绝对免赔率。除外货物导致的损失，保险人不承担赔偿责任。"
    ],
    AH: [
      "(1).主险每次事故绝对免赔额为1000元或损失金额的15%，两者以高者为准。若主险保险金额低于货物保险价值，保险人对货物损失按保险金额与保险价值的比例计算赔偿后再扣除免赔予以赔付。",
      "(2).附加险每次事故绝对免赔额100万或被保险人从其他保单中获得的赔偿金额之和，两者以高者为准。"
    ],
    arguemnt: ""
  }),

  created() {
    this.arguemnt = this.$route.query.index;
    console.log(888888888888888888888, this.arguemnt);
  },
  methods: {
    // 返回上一页
    backToPrePage() {
      this.$globalBack();
    }
  },
   created() {
    if (localStorage.getItem("comCode").substring(0, 4) == "3401") {
      this.appoint = "1";
    } else {
      this.appoint = "0";
    }
  }
};
</script>

<style scoped>
#safeguard-detail {
  padding-top: 2rem;
  /* padding: 2rem 0.4rem 0 0.4rem; */
  height: calc(100vh - 10px);
  overflow: scroll;
  background-color: white;
  box-sizing: border-box;
}
#safeguard-detail[data-v-51d8dd6c] {
  /* height: 400%; */
}
#tab-content {
  /* padding-top: 10px; */
}
.title-container {
  padding: 6px 0.4rem;
  /* margin: 60px 0 10px 0; */
  /* background-color: white; */
  /* height: 100%; */
  box-sizing: border-box;
}

.item-title {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
}
.coverage-content {
  /* margin-top: 0.4rem; */
  margin-bottom: 20px;
  background-color: white;
}
.coverage-content >>> * h3 {
  width: 70%;
  text-align: center;
  margin: auto;
  font-size: 14px;
  line-height: 25px;
}
.coverage-content >>> * h2 {
  text-align: center;
  padding: 10px;
}
</style>
