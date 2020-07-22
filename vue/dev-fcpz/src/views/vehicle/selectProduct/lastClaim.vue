<!--
 * @Author: King
 * @Page: 上年理赔信息
 * @Date: 2020-03-14 10:46:39
 * @Last Modified by: King
 * @Last Modified time: 2020-04-27 15:14:47
 * @FilePath: /src/views/vehicle/selectProduct/lastClaim.vue
-->

<template>
  <div class="last-claims">
    <van-sticky>
    <nav-bar type="vehicle" title="上年理赔信息" @clickBack="$router.back()" />
    </van-sticky>
    <div class="content">
      <div class="list-title">
        <div>理赔险种类型</div>
        <div>赔款金额</div>
        <div>出险日期</div>
        <div>结案时间</div>
      </div>
      <div class="item" v-for="(item, index) in claims" :key="index">
        <div>{{ item.claimType }}</div>
        <div>{{ item.claimAmount }}</div>
        <div>{{ item.riskDate }}</div>
        <div>{{ item.closeDate }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState("vehicle", {
      trafficLastClaims: ({ quoteInfo: { trafficLastClaims } }) =>
        trafficLastClaims,
      businessLastClaims: ({ quoteInfo: { businessLastClaims } }) =>
        businessLastClaims
    }),
    claims() {
      let result = [];
      if (Array.isArray(this.trafficLastClaims)) {
        result.push(...this.trafficLastClaims);
      }
      if (Array.isArray(this.businessLastClaims)) {
        result.push(...this.businessLastClaims);
      }
      return result;
    }
  }
};
</script>

<style lang="less" scoped>
.content {
  background: white;
  border-radius: 15px 15px 0 0;
  .list-title {
    display: flex;
    flex: 1;
    flex-direction: row;
    align-items: center;
    width: 100%;
    height: 20px;
    color: #8e8e8e;
    font-size: 12px;
    & > div {
      display: flex;
      flex: 1;
      justify-content: center;
      :first-child {
        justify-content: flex-start;
      }
      :last-child {
        justify-content: flex-end;
      }
    }
  }
  .item {
    color: #333333;
    font-size: 13px;
    display: flex;
    flex: 1;
    height: 48px;
    flex-direction: row;
    align-items: center;
    position: relative;
    & > div {
      display: flex;
      flex: 1;
      justify-content: center;
      :first-child {
        font-size: 14px;
        justify-content: flex-start;
      }
      :last-child {
        justify-content: flex-end;
      }
    }
  }
  .item:not(:last-child)::after {
    position: absolute;
    box-sizing: border-box;
    content: " ";
    pointer-events: none;
    right: 0;
    bottom: 0;
    left: 16px;
    border-bottom: 1px solid #ebedf0;
    -webkit-transform: scaleY(0.5);
    transform: scaleY(0.5);
  }
}
</style>
