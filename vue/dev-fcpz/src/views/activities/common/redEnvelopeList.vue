/* zhangminjie 公共组件--红包列表 */
<template>
  <div class="redEnvelopeList">
    <van-collapse v-model="activeNames" class="redEnvelope top10">
      <van-collapse-item
        :name="item.id"
        v-for="item in redEnvelopeList"
        :key="item.id"
      >
        <div slot="title" class="redEnvelopeTitle">
          <div class="redEnvelopeTitleLeft"></div>
          <p class="redEnvelopeTitleRight">
            <span>{{ item.title }}</span>
            <span class="redNum">{{ item.money }}元红包X{{ item.num }}</span>
          </p>
        </div>
        <van-row
          type="flex"
          justify="space-between"
          align="center"
          class="redEnvelopeInfo"
          v-for="index in item.num"
          :key="index"
        >
          <van-col span="16" class="detail">
            <span></span>
            <span>{{ item.money }}元红包</span>
          </van-col>
          <van-col span="8" class="alginRight" v-if="item.type == 1">
            已领取
          </van-col>
          <van-col span="8" class="alginRight" v-if="item.type == 2">
            <button>立即领取</button>
          </van-col>
        </van-row>
      </van-collapse-item>
    </van-collapse>
  </div>
</template>

<script>
import Vue from "vue";
import { Tab, Tabs, Card, Image, Collapse, CollapseItem } from "vant";
Vue.use(Collapse);
Vue.use(CollapseItem);
Vue.use(Image);
Vue.use(Card);
Vue.use(Tab);
Vue.use(Tabs);
export default {
  name: "redEnvelopeList",
  props: {
    pageType: Number, // 1 活动规则页面  2 活动详情页面
    redEnvelopeList: Array
  },
  components: {},
  data: () => ({
    activeNames: []
  }),
  methods: {}
};
</script>

<style lang="less" scoped>
.redEnvelopeList {
  font-family: PingFangSC-Medium, PingFang SC;
  button {
    outline: none;
    border: none;
  }
  div,
  p {
    box-sizing: border-box;
  }
  .alginRight {
    text-align: right;
    padding-right: 18px;
  }
  .redEnvelope {
    width: 345px;
    background: rgba(255, 255, 255, 1);
    border-radius: 7px;
    .van-collapse-item {
      .van-cell {
        height: 82px;
        .van-cell__title {
          padding: 8px 0;
        }
      }
    }
    .redEnvelopeTitle {
      display: flex;
      font-size: 15px;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 22px;
      align-items: center;
      .redEnvelopeTitleLeft {
        width: 33px;
        height: 33px;
        background: pink;
        margin-right: 18px;
      }
      .redEnvelopeTitleRight {
        display: flex;
        flex-direction: column;
        font-size: 15px;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
      }
      .redNum {
        font-size: 13px;
        color: rgba(255, 138, 61, 1);
      }
    }
    .van-collapse-item__wrapper {
      border-radius: 0 0 7px 7px;
    }
    .detail {
      display: flex;
      align-items: center;
      span {
        font-size: 13px;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
      }
      span:first-child {
        display: inline-block;
        width: 33px;
        height: 33px;
        background: coral;
        margin-right: 13px;
        color: rgba(153, 153, 153, 1);
      }
    }
    .redEnvelopeInfo {
      margin-bottom: 5px;
      button {
        width: 91px;
        height: 29px;
        background: linear-gradient(
          215deg,
          rgba(246, 110, 30, 1) 0%,
          rgba(255, 160, 39, 1) 100%
        );
        border-radius: 15px;
        font-size: 13px;
        font-weight: 500;
        color: rgba(255, 255, 255, 1);
        line-height: 29px;
      }
    }
    .redEnvelopeInfo:last-child {
      margin-bottom: 0;
    }
  }
}
</style>
