/* 曹征前 营销活动-红包待领取组件 2020.03.21
<noGetCom
        :showData="showData && showData"
        value="false"
        @applyAndAwardList="
    applyAndAwardList(
      showData &&
        showData.notGetAwardList &&
        showData.notGetAwardList[k].activityCode,
      showData.notGetAwardList && showData.notGetAwardList[k].awardLevel
    )
  "
/>

showData:"数据集合" ,value:"boolean",} */
<template>
  <div class="get-money">
    <div class="money-desc">
      <div style="width:44px;height:44px;margin-left: 10px">
        <img
                style="width:100%;height:100%"
                src="@/assets/activity/money.png"
                alt="edit"
                v-if="showData.notGetAwardList[keys].awardForm == 1"
        />
        <img
                style="width:100%;height:100%"
                src="@/assets/activity/gift.png"
                alt="edit"
                v-if="showData.notGetAwardList[keys].awardForm == 2"
        />
      </div>
      <div style="margin-left: 11px">
        <!--实时奖励的红包奖励-->
        <div style="color: #FF5000;font-size: 18px" v-if="showData.notGetAwardList[keys].awardForm == 1">
          {{
          showData &&
          showData.notGetAwardList &&
          showData.notGetAwardList[keys].amount
          }}元红包
        </div>
        <!--实时奖励的礼品奖励-->
        <div style="color: #FF5000;font-size: 18px" v-if="showData.notGetAwardList[keys].awardForm == 2">
          {{
          showData &&
          showData.notGetAwardList &&
          showData.notGetAwardList[keys].awardName
          }}
        </div>
        <div style="color: #FF5000;font-size: 14px;margin-top: 6px">
          {{
          showData &&
          showData.activityStart &&
          showData.activityStart.slice(0, 10)
          }}-{{
          showData &&
          showData.activityEnd &&
          showData.activityEnd.slice(0, 10)
          }}
        </div>
      </div>
    </div>
    <div style="height: 80%;border-left: 1px dotted #FFB999"></div>
    <div class="money-on" v-if="value" @click="$emit('applyAndAwardList')">
      <p class="order-on">立即领取</p>
    </div>
    <div class="money-on" v-else>
      <p class="order-on actied">立即领取</p>
    </div>
  </div>
</template>

<script>
  export default {
    name: "noGetCom",
    props: {
      showData: {},
      value: Boolean,
      keys:Number
    },
    created() {},
    data: () => ({}),
    methods: {}
  };
</script>

<style lang="less" scoped>
  .get-money {
    margin-top: 10px;
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    height: 74px;
    .money-desc {
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      background-color: #feece6;
      flex: 1;
      border-radius: 5px;
      height: 74px !important;
    }
    .money-on {
      width: 80px;
      background-color: #feece6;
      height: 100%;
      border-radius: 5px;
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      .order-on {
        color: #ffffff;
        background-color: #ff5000;
        width:50px;
        padding: 5px 8px;
        font-size: 12px;
        border-radius: 11.5px;
        text-align: center;
      }
      .actied {
        background-color: #d0d0d0;
      }
    }
  }
</style>
