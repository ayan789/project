/* 曹征前公共组件--内容 */
<template>
  <div class="activity-performance" style="margin-bottom: 10px;">
    <div class="title-div" @click="toRoute">
      <div class="award-name">
        {{ showData.activityName ? showData.activityName : "后台未返回名称" }}
      </div>
      <div style="width:6px;height:10px;">
        <img
          style="width:100%;height:100%"
          src="@/assets/customer/customerEquity/rightArrowhead@3x.png"
          alt="edit"
        />
      </div>
    </div>
    <div class="conent-div">
      <div style="width:65px;height:65px;">
        <img
          style="width:100%;height:100%"
          src="@/assets/activity/gift.png"
          alt="edit"
          v-if="type == 5"
        />
        <img
          style="width:100%;height:100%"
          src="@/assets/activity/redpag1.png"
          alt="edit"
          v-else
        />
      </div>
      <div style="flex:1;padding-top: 10px;padding-left: 14px">
        <div v-if="isNone == 0">
          <span style="font-size: 17px; font-weight: 500"
            >您尚未达成活动指标，请继续努力</span
          >
        </div>
        <div v-if="isNone == 1">
          <div class="jixiao-text" v-if="type == 3">
            <span style="font-size: 17px; font-weight: 500">绩效奖金</span>
            <span style="font-size: 17px; color: #FC820E">{{
              showData.awardName ? showData.awardName : "后台未返回"
            }}元</span>
          </div>
          <div class="jixiao-text" v-if="type == 4">
            <span style="font-size: 17px; font-weight: 500">绩效奖金</span>
            <span style="font-size: 17px; color: #FC820E">{{
              showData.awardName ? showData.awardName : "后台未返回"
            }}元</span>
          </div>
          <div class="jixiao-text" v-if="type == 5">
            <span style="font-size: 17px; font-weight: 500">礼品</span>
            <span style="font-size: 17px; color: #FC820E">{{
              showData.awardName ? showData.awardName : "后台未返回"
            }}</span>
          </div>
        </div>
        <div v-if="isNone == 2">
          <div class="jixiao-text" v-if="type == 3">
            <span style="font-size: 17px; font-weight: 500">绩效奖金</span>
            <span style="font-size: 17px; color: #FC820E">{{
              showData.awardName ? showData.awardName : "后台未返回"
            }}元</span>
          </div>
          <div class="jixiao-text" v-if="type == 4">
            <span style="font-size: 17px; font-weight: 500">绩效奖金</span>
            <span style="font-size: 17px; color: #FC820E">{{
              showData.awardName ? showData.awardName : "后台未返回"
            }}元</span>
          </div>
          <div class="jixiao-text" v-if="type == 5">
            <span style="font-size: 17px; font-weight: 500">礼品</span>
            <span style="font-size: 17px; color: #FC820E">{{
              showData.awardName ? showData.awardName : "后台未返回"
            }}</span>
          </div>
        </div>
        <div style="padding-top: 10px" v-if="isNone == 1 || isNone == 2">
          <span class="text">达成时间:</span>
          <span class="text" style="padding-left: 5px" >
            <span> {{showData.achieveTime ? showData.achieveTime : "后台未返回"}}</span>
          </span>
        </div>
        <div style="padding-top: 20px" v-else>
          <span class="text"></span>
        </div>

        <div>
          <span class="text" v-if="type == 3"
            ><!--保费比率-->
            <span v-if="isNone == 0">本活动最高奖励：不含税保费*{{showData.maxAwardName}}%，请继续努力！</span>
            <span v-if="isNone == 1">本活动最高奖励：不含税保费*{{showData.maxAwardName}}%，请继续努力！</span>
            <span v-if="isNone == 2">恭喜您，已达成最高活动奖励指标！</span>
          </span>
          <span class="text" v-if="type == 4"
            ><!--固定奖励-->
            <span v-if="isNone == 0">本活动最高奖励：￥{{showData.maxAwardName}}，请继续努力！</span>
            <span v-if="isNone == 1">本活动最高奖励：￥{{showData.maxAwardName}}，请继续努力！</span>
            <span v-if="isNone == 2">恭喜您，已达成最高活动奖励指标！</span>
          </span>
          <span class="text" v-if="type == 5"
            ><!--礼品-->
            <span v-if="isNone == 0">本活动最高奖励：{{showData.maxAwardName}}，请继续努力！</span>
            <span v-if="isNone == 1">本活动最高奖励：{{showData.maxAwardName}}，请继续努力！</span>
            <span v-if="isNone == 2">恭喜您，已达成最高活动奖励指标！</span>
          </span>
        </div>
      </div>
    </div>
    <!--<p>awardName:{{ showData.awardName }}</p>-->
    <!--<p>maxAwardName:{{ showData.maxAwardName }}</p>-->
    <!--<p>awardForm:{{ showData.awardForm }}</p>-->
    <!--<p>activityCode:{{ showData.activityCode }}</p>-->
    <!--<p>activityName:{{ showData.activityName }}</p>-->
    <!--<p>acheiveTime:{{ showData.acheiveTime }}</p>-->
    <!--<p>curLevel:{{ showData.curLevel }}</p>-->
    <!--<p>isMaxLevel:{{ showData.isMaxLevel }}</p>-->
  </div>
</template>

<script>
export default {
  name: "performance",
  props: {
    showData: Object
  },
  created() {
    if (this.showData.awardName == "" || this.showData.awardName == null) {
      //未完成任何任务
      this.isNone = 0;
    } else if (this.showData.awardName && this.showData.isMaxLevel == 0) {
      //完成部分任务
      this.isNone = 1;
    } else if (this.showData.isMaxLevel == 1) {
      //完成全部任务
      this.isNone = 2;
    }

    switch (this.showData.awardForm) {
      case 3:
        this.type = 3;
        break;
      case 4:
        this.type = 4;
        break;
      case 5:
        this.type = 5;
        break;
      default:
        this.type = "";
    }
  },

  data: () => ({
    isNone: "", //没有完成任何任务0，完成部分1，全部完成2
    type: "" //3保费比率；4、固定奖励； 5礼品；
  }),
  methods: {
    toRoute() {
      this.$router.push({
        name: "activityDetail",
        query: {
          item: { activityCode: this.showData.activityCode, salesmanCode: localStorage.getItem("userCode")  }
        }
      });
    },
  }
};
</script>

<style lang="less" scoped>
.activity-performance {
  background-color: white;
  border-radius: 5px;
  padding: 11px 18px;
  .title-div {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-bottom: 10px;
    border-bottom: 1px solid #e5e6e8;
    .award-name {
      color: #333333;
      font-size: 18px;
      font-weight: 600;
    }
  }
  .conent-div {
    padding-top: 16px;
    display: flex;

    .jixiao-text {
      display: flex;
      justify-content: space-between;
    }
    .text {
      font-size: 15px;
      color: #999999;
    }
  }
}
</style>
