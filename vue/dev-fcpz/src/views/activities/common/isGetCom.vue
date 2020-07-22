/* 曹征前 营销活动-绩效奖金已领取组件 2020.03.21
<IsGetCom :name="v.awardName" :data="v.getAwardTime" />
 name:"名称" ,data:"领取日期",}
 */
<template>
  <div style="padding-top: 10px">
    <div class="no-div">
      <div class="no-one">
        <div style="width:44px;height:44px;margin-left:10px">
          <img
            style="width:100%;height:100%"
            src="@/assets/activity/nomoney.png"
            alt="edit"
          />
        </div>
        <div style="margin-left: 11px">
          <div style="font-size: 18px;color: #666666;font-weight:600">{{ awardForm == 1 ? amount+'元红包' : name+'*'+awardCount}} </div>
          <div style="font-size: 14px;margin-top: 6px;color: #666666">领取日期：{{data}}</div>
        </div>
      </div>
      <div class="no-two"></div>
      <div class="no-three">
        <p style="color:#666666;opacity: 0.43">已领取</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "isGetCom",
  props: {
awardForm:String,
  amount:String,
    name:String,
    data:String,
awardCount:String
  },
  created() {},
  data: () => ({
  }),
  methods: {}
};
</script>

<style lang="less" scoped>
  .no-div{
    display: flex;
    flex-direction: row ;
    justify-content: flex-start;
    align-items: center;
    height:74px;
    width:100%;
    .no-one{
      display: flex;
      flex-direction: row ;
      justify-content: flex-start ;
      align-items: center;
      height: 100%;
      background-color: #EDEDED;
      border-radius: 5px;
      flex:1;
    }
    .no-two{
      height: 80%;
      border-left: 1px dotted #999999;
    }
    .no-three{
      display: flex;
      flex-direction: row ;
      justify-content: center ;
      align-items: center;
      width:80px;
      height: 100%;
      background-color: #EDEDED;
      border-radius: 5px;
    }
  }
</style>
