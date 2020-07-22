<template>
  <div class="tips-notice">
    <div class="agency">
      <span class="img">
        <img src="../../../assets/renewal/agency.png" alt="" />
      </span>
      <span class="title">待办</span>
      <div class="tips van-hairline--bottom" @click="isRead = false">
        <div class="tips-img">
          <img src="../../../assets/renewal/renewal-reminder.png" />
        </div>
        <div class="tips-content">
          <p class="tips-title">
            <span class="tips-title_"
              >续保提醒 <span class="tips-state" v-show="isRead"></span
            ></span>
            <span>2020/02/20 10:44</span>
          </p>
          <p class="tips-details van-ellipsis">
            【客户模块需求调研】快来填写问卷快来填写放大
          </p>
        </div>
      </div>
      <div class="tips van-hairline--bottom" @click="isRead = false">
        <div class="tips-img">
          <img src="../../../assets/renewal/pay.png" />
        </div>
        <div class="tips-content">
          <p class="tips-title">
            <span class="tips-title_"
              >待支付订单 <span class="tips-state" v-show="isRead"></span
            ></span>
            <span>2020/02/20 10:44</span>
          </p>
          <p class="tips-details van-ellipsis">
            【车牌号】核保通过，请尽快完成支付。
          </p>
        </div>
      </div>
      <div class="tips van-hairline--bottom" @click="isRead = false">
        <div class="tips-img">
          <img src="../../../assets/renewal/electronic- policy.png" />
        </div>
        <div class="tips-content">
          <p class="tips-title">
            <span class="tips-title_"
              >电子保单 <span class="tips-state" v-show="isRead"></span
            ></span>
            <span>2020/02/20 10:44</span>
          </p>
          <p class="tips-details van-ellipsis">
            电子保单客户授权成功，请查看电子保单。
          </p>
        </div>
      </div>
      <div class="tips " @click="isRead = false">
        <div class="tips-img">
          <img src="../../../assets/renewal/electronic- invoice.png" />
        </div>
        <div class="tips-content">
          <p class="tips-title">
            <span class="tips-title_"
              >电子发票 <span class="tips-state" v-show="isRead"></span
            ></span>
            <span>2020/02/20 10:44</span>
          </p>
          <p class="tips-details van-ellipsis">
            电子发票客户授权成功，请查看电子保单。
          </p>
        </div>
      </div>
    </div>
    <div class="agency_">
      <span class="img">
        <img src="../../../assets/renewal/notice.png" alt="" />
      </span>
      <span class="title">通知</span>
      <div class="tips van-hairline--bottom" @click="isRead = false">
        <div class="tips-img">
          <img src="../../../assets/renewal/activities.png" />
        </div>
        <div class="tips-content">
          <p class="tips-title">
            <span class="tips-title_"
              >营销活动 <span class="tips-state" v-show="isRead"></span
            ></span>
            <span>2020/02/20 10:44</span>
          </p>
          <p class="tips-details van-ellipsis">
            显示营销活动中所配置的消息提醒话术内容
          </p>
        </div>
      </div>
      <div class="tips van-hairline--bottom" @click="isRead = false">
        <div class="tips-img">
          <img src="../../../assets/renewal/assessment.png" />
        </div>
        <div class="tips-content">
          <p class="tips-title">
            <span class="tips-title_"
              >业绩考核 <span class="tips-state" v-show="isRead"></span
            ></span>
            <span>2020/02/20 10:44</span>
          </p>
          <p class="tips-details van-ellipsis">
            恭喜您本次考核晋升至XXX职级
          </p>
        </div>
      </div>
      <div class="tips" @click="isRead = false">
        <div class="tips-img">
          <img src="../../../assets/renewal/issue.png" />
        </div>
        <div class="tips-content">
          <p class="tips-title">
            <span class="tips-title_"
              >客户出单 <span class="tips-state" v-show="isRead"></span
            ></span>
            <span>2020/02/20 10:44</span>
          </p>
          <p class="tips-details van-ellipsis">
            【车牌号】已完成订单支付
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    isRead: true
  })
};
</script>
<style lang="less" scoped>
.tips-notice {
  background: #ffffff;
  height: 100%;

  .agency {
    padding: 0 15px;
    border-bottom: 10px solid #f5f5f5;
  }
  .agency_ {
    padding: 0 15px;
  }
  .img {
    width: 19px;
    height: 19px;
    margin-right: 10px;
    vertical-align: middle;
    img {
      width: 19px;
      height: 19px;
    }
  }
  .title {
    height: 22px;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
    margin-bottom: 20px;
    margin-top: 15px;
    display: inline-block;
  }
  .tips {
    display: flex;
    display: -webkit-flex;
    margin-bottom: 15px;
    padding-bottom: 15px;
    &-img {
      margin-right: 14px;
      height: 38px;
      width: 38px;
      img {
        height: 38px;
        width: 38px;
      }
    }
    &-title_ {
      position: relative;
    }
    .tips-content {
      flex: 1;
      -webkit-flex: 1;
      // position: relative;
      .tips-title {
        display: flex;
        display: -webkit-flex;
        justify-content: space-between;
        -webkit-justify-content: space-between;
        span:nth-child(1) {
          font-size: 14px;
          font-family: PingFangSC-Medium, PingFang SC;
          font-weight: 500;
          color: rgba(51, 51, 51, 1);
        }
        span:nth-child(2) {
          font-size: 10px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(187, 187, 187, 1);
        }
      }
      .tips-details {
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        width: 274px;
      }
      .tips-state {
        position: absolute;
        width: 8px;
        height: 8px;
        background: rgba(255, 71, 110, 1);
        border-radius: 8px;
        display: inline-block;
        right: -8px;
        top: 0;
      }
    }
  }
}
</style>
