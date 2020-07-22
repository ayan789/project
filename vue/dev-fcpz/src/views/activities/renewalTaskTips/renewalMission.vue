/* zhangmingjie 续保任务提醒 */
<template>
  <div class="renewalMission">
    <h1>续保任务提醒</h1>
    <div class="mask" v-show="isShowDialog">
      <div class="renewalMissionDialog">
        <div class="dialogTop">
          <h1>续保任务提醒</h1>
          <div class="dialogContent">
            <div class="dialogIcon"></div>
            <div class="dialogContentText">
              <p>(T-6日)XX个续保任务已过期</p>
              <p>(T-6日)XX个续保任务已过期</p>
              <p class="sub">仅包含到期已报价保单</p>
            </div>
          </div>
        </div>
        <div class="dialogBottom">
          <button @click="quickProcessing">火速处理</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "renewalMission",

  data: () => ({
    isShowDialog: true
  }),
  methods: {
    // 切换已领取和待领取红包
    getChangeStatus(val) {
      this.selected = val;
    },
    //火速处理
    quickProcessing() {
      this.isShowDialog = false;
    }
  }
};
</script>

<style lang="less" scoped>
.renewalMission {
  font-family: PingFangSC-Medium, PingFang SC;
  div,
  p {
    box-sizing: border-box;
  }

  h1 {
    text-align: center;
  }
  button {
    outline: none;
    border: none;
  }
  .mask {
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
  }
  .renewalMissionDialog {
    margin: 0 auto;
    height: 374px;
    width: 301px;
    background: #ccc;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

    .dialogTop {
      height: 291px;
      margin-bottom: 10px;
      h1 {
        height: 32px;
        font-size: 23px;
        font-weight: 500;
        color: rgba(255, 255, 255, 1);
        line-height: 32px;
        text-align: center;
        padding: 45px 0 28px;
      }
      .dialogContent {
        width: 254px;
        height: 188px;
        background: rgba(255, 255, 255, 1);
        box-shadow: 0px 1px 40px 0px rgba(56, 81, 155, 0.21);
        border-radius: 5px;
        margin: 0 auto;
        position: relative;
        .dialogIcon {
          width: 81px;
          height: 81px;
          background: rgba(255, 255, 255, 1);
          box-shadow: 0px 1px 21px 0px rgba(75, 168, 255, 0.43);
          position: absolute;
          top: -16px;
          left: 50%;
          transform: translateX(-50%);
          border-radius: 50%;
        }
        .dialogContentText {
          padding: 86px 0 28px;
          text-align: center;
          p {
            font-size: 14px;
            font-weight: 400;
            color: rgba(51, 51, 51, 1);
            line-height: 20px;
            margin-bottom: 8px;
          }
          p:last-child {
            margin-bottom: 0;
          }
          .sub {
            font-size: 13px;
            color: rgba(153, 153, 153, 1);
            line-height: 18px;
          }
        }
      }
    }
    .dialogBottom {
      height: 71px;
      position: relative;
      button {
        width: 254px;
        height: 40px;
        background: linear-gradient(
          125deg,
          rgba(30, 70, 246, 1) 0%,
          rgba(39, 154, 255, 1) 100%
        );
        border-radius: 20px;
        line-height: 40px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        font-size: 16px;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
      }
    }
  }
}
</style>
