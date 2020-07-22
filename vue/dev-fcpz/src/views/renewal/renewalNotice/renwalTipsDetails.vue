<template>
  <div class="tips">
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="onLoad"
    >
      <div
        class="tips-details"
        v-for="(item, index) in noticeData"
        :key="index"
      >
        <p class="tips-time">{{ item.date }}</p>
        <div class="tips-details-conent">
          <p class="tips-head">
            <span>联系日期</span>
          </p>
          <p class="tips-remind">
            {{ item.des }}
          </p>
        </div>
      </div>
    </van-list>
  </div>
</template>

<script>
import { List } from "vant";
export default {
  components: {
    [List.name]: List
    // renderComponent: () => import("./renderComponents")
  },
  data: () => ({
    loading: false,
    finished: false,
    noticeData: [
      {
        date: "2020年2月20日 14:32",
        des:
          "温馨提醒：截止2月4日，您当前有XX张保单待续保，可至“首页--》我的续保”中处理。"
      }
    ]
  }),
  methods: {
    onLoad() {}
  }
};
</script>

<style lang="less" scoped>
.tips {
  background: #f7f7f7;
  padding: 0 15px;
  height: 100%;
  .tips-details {
    margin-bottom: 20px;
    .tips-time {
      font-size: 12px;
      font-family: PingFang-SC-Medium, PingFang-SC;
      font-weight: 500;
      color: rgba(102, 102, 102, 1);
      padding: 0 5px;
      background: rgba(226, 226, 226, 1);
      border-radius: 3px;
      opacity: 0.6;
      width: fit-content;
      width: -webkit-fit-content;
      margin: 0 auto;
      margin-bottom: 10px;
    }
    .tips-details-conent {
      width: 345px;
      height: 104px;
      background: rgba(255, 255, 255, 1);
      border-radius: 8px;
      padding: 20px 10px;
      box-sizing: border-box;
      .tips-head {
        display: flex;
        display: -webkit-flex;
        justify-content: space-between;
        -webkit-justify-content: space-between;
        span:nth-child(1) {
          font-size: 14px;
          font-family: PingFangSC-Semibold, PingFang SC;
          font-weight: 600;
          color: rgba(51, 51, 51, 1);
        }
        span:nth-child(2) {
          font-size: 14px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
        }
      }
      .tips-remind {
        width: inherit;
        font-size: 13px;
        font-family: PingFang-SC-Medium, PingFang-SC;
        font-weight: 500;
        color: rgba(102, 102, 102, 1);
        margin-top: 10px;
      }
    }
  }
  /deep/.van-button {
    width: 345px !important;
    width: 100%;
    position: fixed;
    bottom: 15px;
    height: 44px;
    // left: -50%;
    // transform: translateX(50%);
  }
}
</style>
