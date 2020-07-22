/* zhangminjie 公共组件--订单详情中间内容部分-ui修改前 */
<template>
  <div class="orderDetailMidContent">
    <div class="guaranteedContent">
      <van-card class="guaranteedContentInfo">
        <div slot="title">
          <div class="guaranteedContentTop">
            <span>保障内容</span>
          </div>
          <div class="guaranteedContentBottom">
            <p
              v-for="(item, index) in guaranteedCollageContent.slice(0, 3)"
              :key="item.id"
            >
              <span>{{ item.desc }}</span>
              <span>{{ item.price }}万</span>
            </p>
            <div class="collageContents" v-show="isCollage">
              <p
                v-for="item in guaranteedCollageContent.slice(3)"
                :key="item.id"
              >
                <span>{{ item.desc }}</span>
                <span>{{ item.price }}万</span>
              </p>
            </div>
            <div class="collageAllOut">
              <button class="collageAll" @click="collageList">展开全部</button>
              <van-icon
                class="collageAllIcon"
                name="arrow-down"
                v-if="!isCollage"
              />
              <van-icon
                class="collageAllIcon"
                name="arrow-up"
                v-if="isCollage"
              />
            </div>
          </div>
        </div>
      </van-card>
    </div>
    <div class="person">
      <van-card class="personInfo">
        <div slot="title">
          <div class="personInfoTop">
            <span>被投保人</span>
          </div>
          <div class="personInfoBottom">
            <p>
              <span>投保人</span>
              <span>
                {{ person.personName }}
                <van-icon name="arrow" />
              </span>
            </p>
            <p>
              <span>被保人（子女）</span>
              <span>
                {{ person.childName }}
                <van-icon name="arrow" />
              </span>
            </p>
          </div>
        </div>
      </van-card>
    </div>
    <div class="insuranceCost">
      <van-card class="insuranceCostInfo">
        <div slot="title">
          <div class="insuranceCostInfoTop">
            <span>保费</span>
          </div>
          <div class="insuranceCostInfoBottom">
            <p v-for="(item, index) in insuranceCost" :key="index">
              <span>{{ item.desc }}</span>
              <span :class="[item.type == 2 ? 'red' : '']"
                >{{ item.price }}万</span
              >
            </p>
          </div>
        </div>
      </van-card>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import { Card, Button } from "vant";
Vue.use(Card);
Vue.use(Button);
export default {
  props: {
    guaranteedCollageContent: Array, //传入的数组
    person: Object,
    isCollage: Boolean,
    insuranceCost: Array
  },
  data: () => ({
    isExpand: false
  }),
  methods: {
    // 展开全部
    collageList() {
      this.isExpand = this.isCollage;
      if (this.isExpand) {
        this.isExpand = false;
      } else {
        this.isExpand = true;
      }
      this.$emit("isCollageEvent", this.isExpand);
    }
  }
};
</script>

<style lang="less" scoped>
.orderDetailMidContent {
  .guaranteed,
  .unused {
    height: 30px;
    line-height: 30px;
    font-size: 15px;
    color: rgba(255, 138, 61, 1);
  }
  .unused {
    color: rgba(51, 51, 51, 1);
  }
  .guaranteedContentInfo,
  .personInfo,
  .insuranceCostInfo {
    background: rgba(255, 255, 255, 1);
    border-radius: 4px;
    display: flex;
    flex-direction: column;
    position: relative;
    box-sizing: border-box;
    padding: 15px;
  }
  .guaranteedContent,
  .person,
  .insuranceCost {
    margin-top: 10px;
  }
  .guaranteedContentTop {
    height: 22px;
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    line-height: 22px;
    position: relative;
    .benefitDetails {
      cursor: pointer;
      position: absolute;
      top: 0;
      right: 0;
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(59, 138, 254, 1);
      line-height: 19px;
    }
  }
  .guaranteedContentBottom {
    display: flex;
    flex-direction: column;
    margin-top: 10px;
    // min-height: 120px;
  }
  .guaranteedContentBottom p,
  .personInfoBottom p,
  .insuranceCostInfoBottom p {
    height: 30px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    line-height: 30px;
    display: flex;
    justify-content: space-between;
  }
  .guaranteedContentBottom p span:first-child {
    width: 210px;
  }
  .personInfoTop {
    width: 66px;
    height: 22px;
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    line-height: 22px;
  }
  .personInfoBottom p {
    margin-top: 14px;
  }
  .personInfoBottom p span {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
  }
  .personInfoBottom {
    font-family: PingFangSC-Regular, PingFang SC;
    .vehicleInfo {
      padding: 9px 14px;
      background: rgba(250, 250, 250, 1);
      li {
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: 13px;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
        line-height: 21px;
      }
    }
  }
  .insuranceCostInfoTop {
    width: 33px;
    height: 22px;
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    line-height: 22px;
  }
  .insuranceCostInfoBottom p {
    height: 30px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    line-height: 30px;
  }
  .red {
    color: #ff0064;
  }
}
</style>
