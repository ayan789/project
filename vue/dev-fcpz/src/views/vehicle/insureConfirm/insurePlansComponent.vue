<style scoped>
.father {
  padding: 17px;
  margin-bottom: 15px;
  background: white;
}
.cellLabel {
  color: #333333;
}
.cellContent {
  color: #333333;
}
.bottomInfo {
  margin: 6px 0px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
  line-height: 31.8px;
}

.left {
  float: left;
  width: 120px;
  text-align: left;
}

.right {
  float: right;
  width: calc(100% - 120px);
  text-align: right;
  overflow: hidden;
}

.icon {
  background: #3b8afe;
  width: 20px;
  height: 20px;
  border-radius: 20px;
  float: left;
}

.sc {
  width: 100%;
}

.title {
  float: left;
  height: 20px;
  line-height: 20px;
  margin-left: 5px;
  font-size: 14px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  line-height: 20px;
  width: 30%;
  text-align: left;
}

#down {
  height: 200px;
  margin: 0px 15px 40px 15px;
  width: calc(100% - 30px);
  background: white;
  /*border-radius: 8px;*/
}

.bLine {
  width: 4px;
  height: 14px;
  background: rgba(51, 51, 51, 1);
  border-radius: 2px;
  margin-top: 3px;
}

#bottomDiv {
  margin: 40px 15px 15px 15px;
  width: calc(100% - 30px);
  height: 50px;
  line-height: 46px;
  /*border-radius: 46px;*/
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(59, 138, 254, 1);
  /* color: white; */
}

#bottomDiv > :first-child {
  width: calc(50% - 7.5px);
  height: 46px;
  float: left;
  /*border-radius: 23px;*/
  background: white;
  color: #005bac;
  line-height: 46px;
  text-align: center;
  border: 1px solid #005bac;
}

#bottomDiv > :last-child {
  text-align: center;
  width: calc(50% - 7.5px);
  height: 46px;
  float: right;
  /*border-radius: 23px;*/
  background: #3b8afe;
  color: white;
  border: 1px solid white;
}

.item {
  margin-top: 10px;
  width: 100%;
}

.a {
  text-align: left;
  width: 100%;
  height: 23px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
  line-height: 23px;
}

.b {
  text-align: left;
  width: 100%;
  height: 23px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 23px;
}

.c {
  margin-top: 9px;
  background: rgba(250, 250, 250, 1);
  padding: 17px;
  border-radius: 2px;
}

.subTitleLeft {
  text-align: left;
}

.subTitleLeft,
.subTitleCenter {
  float: left;
}

.subTitleCenter {
  width: 33.3%;
  text-align: center;
}

.subTitleRight {
  float: right;
  text-align: right;
}

.subItem {
  width: 100%;
}

.itemName {
  text-align: left;
}

.amount {
  text-align: center;
}

.leftStar {
  float: left;
}

.rightScore {
  float: right;
  text-align: right;
}

.itemName,
.amount {
  float: left;
}

.fee {
  float: right;
  text-align: right;
}

#price {
  /*background: rgba(59, 138, 254, 0.1);*/
  border-radius: 2px;
  height: 60px;
  margin: 0px 0px 0px 0px;
  /*padding: 0 18px;*/
  line-height: 60px;
}

#price > div {
  float: left;
  width: 50%;
}

#price > :first-child {
  text-align: left;
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
}

#price > :last-child {
  text-align: right;
  font-size: 17px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #fc820e;
}

.edit {
  width: calc(70% - 25px);
  float: right;
  /* background:red; */
}

.lastIcon {
  float: right;
  width: 20px;
  height: 20px;
}

.edit > img {
  display: block;
  float: right;
  width: 20px;
  height: 20px;
}

.topIcon > h5 {
  width: calc(100% - 40px);
  height: 20px;
  margin-right: 20px;
  float: left;
  text-align: right;
  line-height: 20px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(59, 138, 254, 1);
  text-decoration: underline;
}

/* 折叠效果 */
.box {
  /* height: auto; */
  width: 100%;
}

.draw-enter-active,
.draw-leave-active {
  transition: all 0.3s ease;
}

.draw-enter,
    .draw-leave-to

        /* .fade-leave-active below version 2.1.8 */
 {
  height: 0;
}

#arrow {
  height: 38px;
  width: 100%;
  margin-top: 6px;
}

#arrow > img {
  display: block;
  width: 20px;
  height: 14px;
  margin: 12px calc(50% - 10px);
}
</style>
<template>
  <div class="insurePlan">
    <div class="father down">
      <div class="topTitle">
        <div class="icon"></div>
        <div class="title">{{ insurePlan.title }}</div>
      </div>
      <div class="bottomInfo">
        <div
          class="item"
          v-for="(item, index) in insurePlan.plans"
          :key="index"
        >
          <transition name="draw">
            <div class="box" v-show="index < 2 || (index >= 2 && toShow)">
              <div class="a">{{ item.title }}</div>
              <div class="b">{{ item.validTime }}</div>
              <div class="c">
                <div
                  class="subTitleLeft"
                  :style="
                    item.showInsuranceAmount == true
                      ? threeColStyle
                      : twoColStyle
                  "
                >
                  险种
                </div>
                <div
                  class="subTitleCenter"
                  v-if="item.showInsuranceAmount == true"
                >
                  保额（万元）
                </div>
                <div
                  class="subTitleRight"
                  :style="
                    item.showInsuranceAmount == true
                      ? threeColStyle
                      : twoColStyle
                  "
                >
                  保费（元）
                </div>
                <div
                  class="subItem"
                  v-for="(subItem, idx) in item.details"
                  :key="idx"
                >
                  <div
                    class="itemName"
                    :style="
                      item.showInsuranceAmount == true
                        ? threeColStyle
                        : twoColStyle
                    "
                  >
                    {{ subItem.title }}
                  </div>
                  <div
                    class="amount"
                    :style="
                      item.showInsuranceAmount == true
                        ? threeColStyle
                        : twoColStyle
                    "
                    v-if="item.showInsuranceAmount == true"
                  >
                    {{ subItem.insuranceAmount }}
                  </div>
                  <div
                    class="fee"
                    :style="
                      item.showInsuranceAmount == true
                        ? threeColStyle
                        : twoColStyle
                    "
                  >
                    {{ subItem.price }}
                  </div>
                </div>
              </div>
            </div>
          </transition>
        </div>
        <div id="arrow" @click="clickArrow">
          <img :src="arrowImg" />
        </div>
        <div id="price">
          <div>合计保费（元）</div>
          <div>{{ insurePlan.totalInsureAmount }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "insurePlan",
  props: {
    insurePlan: Object
  }
};
</script>
