/* 曹征前 营销活动-红包待领取组件 2020.03.21
<MoneyList
  :name="v.awardName"
  :money="v.getAwardTime"
  :start="v.getAwardTime"
  :end="v.getAwardTime"
  :overtime="v.getAwardTime"
  :isGet="true"
/>
name:"名称" ,isGet:"true“未领取页面使用”，false“已领取页面使用”",} */
<template>
  <div class="pag-content">
    <div class="content-title">
      <p v-if="isGet == false">
        {{ activityName && activityName ? activityName : "后台返回空" }}
      </p>

      <p style="color: #666;" v-if="awardForm == 1 && isGet == true">
        {{ activityName }}
      </p>
      <p style="color: #666;" v-if="awardForm == 2 && isGet == true">
        {{ name }}
      </p>
      <p style="color: #333333">{{ isGet ? "" : "领福利" }}</p>
    </div>
    <div class="content-desc">
      <div style="width:42px;height:42px">
        <img
          style="width:100%;height:100%"
          src="@/assets/activity/redpag1.png"
          alt="edit"
          v-if="awardForm == '1'"
        />
        <img
          style="width:100%;height:100%"
          src="@/assets/activity/gift.png"
          alt="edit"
          v-if="awardForm == '2'"
        />
      </div>
      <div class="desc-count">
        <p style="color:#333333;font-size: 16px" v-if="awardForm == 1">
          {{ money }}元红包
        </p>
        <p style="color:#333333;font-size: 16px" v-if="awardForm == 2">
          {{ name }}
        </p>
        <p v-if="isGet" style="color:#999999;font-size: 14px">
          领取日期：{{ overtime }}
        </p>
        <p v-else style="color:#999999;font-size: 14px">
          有效期：{{ start && start.slice(0, 10) }}-{{
            end && end.slice(0, 10)
          }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MoneyList",
  props: {
    name: String, //红包名称
    money: String, //红包金额
    start: String, //红包开始日期，待领取页面使用
    end: String, //红包结束日期，待领取页面使用
    overtime: String, //领取时间，isGet为true的时候显示
    isGet: Boolean, //true“待领取页面使用”，false“已领取页面使用
    awardForm: String, //1红包，2礼品
    activityName: String, //活动名称
    activityCode: String, //活动编码
    activityUrl: String //活动链接
  },
  created() {},
  data: () => ({}),
  methods: {
    detail(activityUrl) {
      if (activityUrl) {
        if (activityUrl.indexOf("http") >= 0) {
          //轮播图返回的是真实的h5链接，直接跳转
          window.location.href = activityUrl;
        } else {
          let activityCode = this.activityCode;
          if (activityUrl.indexOf("brave") >= 0) {
            //勇士无敌活动
            this.$router.push({
              name: "brave",
              query: {
                item: {
                  activityCode: activityCode,
                  salesmanCode: localStorage.getItem("userCode")
                }
              }
            });
          } else if (activityUrl.indexOf("woodpecker") >= 0) {
            //啄木鸟活动
            this.$router.push({
              name: "woodpecker",
              query: {
                item: {
                  activityCode: activityCode,
                  salesmanCode: localStorage.getItem("userCode")
                }
              }
            });
          } else {
            //正常详情页
            this.$router.push({
              name: "activityDetail",
              query: {
                item: {
                  activityCode: activityCode,
                  salesmanCode: this.salesmanCode
                }
              }
            });
          }
        }
      }
    }
  }
};
</script>

<style lang="less" scoped>
.pag-content {
  background-color: white;
  .content-title {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #e5e6e8;
    padding-bottom: 5px;
  }
  .content-desc {
    margin-top: 10px;
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    .desc-count {
      margin-left: 10px;
    }
  }
}
</style>
