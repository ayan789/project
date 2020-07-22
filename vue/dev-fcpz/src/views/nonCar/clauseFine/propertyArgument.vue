<!-- liumengjuan   特別约定 -->
<template>
  <div id="safeguard-detail" sticky>
    <globalNav :title="globalNav.title" @clickBack="backToPrePage"></globalNav>
    <div class="title-container">
      <van-col class="coverage-content" v-if="appoint == '0'">
        <div v-for="(text, index) in JXTY" :key="index">{{ text }}</div>
      </van-col>
      <van-col class="coverage-content" v-if="appoint == '1'">
        <div v-for="(text, index) in AH" :key="index">{{ text }}</div>
      </van-col>
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  name: "propertyArgument",
  components: {
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    appoint: "1",
    globalNav: {
      title: "特别约定"
    },
    JXTY: [
      "1.承运车辆未年审或年审不合格、车辆行驶证不齐全、驾驶人员无有效驾驶证发生事故、车辆主、挂车未均挂牌，保险人不承担赔偿责任。",
      "2.主险保险标的因以下原因导致损失，保险人不承担赔偿责任：无外包装/裸装货物（包括集装箱）在运输过程中遭受的破碎、凹瘪、氧化、碰损、刮擦以及生锈损失，集装箱单独损失（即运输工具未发生碰撞、倾覆、火灾、爆炸），保险人不承担赔偿责任。",
      "3.主险除外标的：商品车、超高超大超长货物、原木、蔬菜、水果、禽、鱼、活动/植物、冷藏货物、钢琴、艺术品、金银珠宝、玉器、稀有/贵重金属、邮票、金融票据、易燃易爆危险品、玻璃及玻璃制品、玻璃瓶装货物、瓷砖、陶瓷及陶瓷制品、大理石等易碎品、大件机器设备、精密仪器设备、二手货物、废旧物资。在保险期间内增加除外货物导致的损失，保险人不承担赔偿责任。",
      "4.每一运输工具仅限投保一单，重复投保保额、赔偿限额不累计。"
    ],
    AH: [
      "(1).发生保险事故时，承运车辆违反有关机动车辆装载规定的，主险及附加险均另行加扣10%的绝对免赔率",
      "(2).承运车辆未年审或年审不合格、车辆行驶证不齐全、驾驶人员无有效驾驶证发生事故、车辆主、挂车未均挂牌，保险人不承担赔偿责任。",
      "(3).主险保险标的发生以下损失，保险人不承担赔偿责任：无外包装/裸装货物（包括集装箱）在运输过程中遭受的破碎、凹瘪、氧化、碰损、刮擦以及生锈损失；集装箱单独损失（即运输工具未发生碰撞、倾覆、火灾、爆炸），保险人不承担赔偿责任。",
      "(4).主险除外标的：商品车、超高超大超长货物、原木、蔬菜、水果、禽、鱼、活动/植物、冷藏货物、钢琴、艺术品、金银珠宝、玉器、稀有/贵重金属、邮票、金融票据、易燃易爆危险品、玻璃及玻璃制品、玻璃瓶装货物、瓷砖、陶瓷及陶瓷制品、大理石等易碎品、大件机器设备、精密仪器设备、二手货物、废旧物资。在保险期间内增加除外货物导致的损失，保险人不承担赔偿责任。",
      "(5).每一运输工具仅限投保一单，重复投保保额、赔偿限额不累计。"
    ]
  }),
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
