<template>
  <div>
    <Global_globalNav
      title="操作轨迹"
      @clickBack="
        () => {
          this.$router.back();
        }
      "
    ></Global_globalNav>
    <div class="step-main" v-if="resultData.length != 0">
      <div class="steps" v-for="(item, index) in resultData" :key="index">
        <p class="steps-left inactive">
          <span class="van-multi-ellipsis--l2">{{
            item.operateTime | filterTime
          }}</span>
        </p>
        <p
          class="steps-line"
          :style="{
            background: index === resultData.length - 1 ? 'transparent' : ''
          }"
        >
          <span class="circle"></span>
        </p>
        <div class="steps-right">
          <p>
            <span>操作人姓名</span> <span>{{ item.operatorName }}</span>
          </p>
          <p>
            <span>操作人工号</span> <span>{{ item.operateEmp }}</span>
          </p>
          <p>
            <span>保单状态</span> <span>{{ item.handleStatusName }}</span>
          </p>
        </div>
      </div>
    </div>
    <div v-else class="noData"></div>
  </div>
</template>

<script>
import { operateTrajectoryQuery } from "@src/http/module/renewal.js";
export default {
  data: () => ({
    resultData: []
  }),
  filters: {
    filterTime(val) {
      return val && val.replace(/-/g, "/");
    }
  },
  async created() {
    try {
      let res = await operateTrajectoryQuery({
        policyNo: this.$route.params.policyNo || "",
        compulsoryFlag: this.$route.params.compulsoryFlag || "",
        frameNo: this.$route.params.frameno || "",
        licenseNo: this.$route.params.licenseNo || "",
        expiryDate: this.$route.params.respEndDate || "",
        riskCode: this.$route.params.riskCode || "",
        userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || ""
      });
      if (res.code === "1" && res.data) {
        this.resultData = Object.freeze([...res.data.trajectoryList]);
      } else {
        this.$dialog.alert({
          message: res.msg || res.message,
          callback: () => {
            this.$router.back();
          }
        });
      }
    } catch (err) {}
  }
};
</script>

<style lang="less" scoped>
.step-main {
  margin-top: 80px;
  padding-top: 50px;
  height: 100%;
  background: #fff;
  box-sizing: border-box;
  .steps {
    display: flex;
    display: -webkit-flex;
    .steps-left {
      color: rgba(0, 0, 0, 1);
      max-width: 100px;
      margin-right: 10px;
      text-align: right;
      span {
        display: inline-block;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #333333;
        // widt
      }
    }
    .steps-line {
      width: 1px;
      height: 101px;
      background: #dddddd;
      margin: 0 20px;
      position: relative;
      .circle {
        width: 22px;
        height: 22px;
        background: url("../../../assets/renewal/operationTrajectory.png"),
          no-repeat;
        background-size: cover;
        border-radius: 18px;
        display: inline-block;
        transform: translate(-50%, 0%);
      }
    }
    .steps-right {
      font-size: 13px;
      font-family: PingFang-SC-Medium, PingFang-SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      margin-left: 5px;
    }
    .inactive {
      color: #aaaaaa;
    }
  }
}
.noData {
  width: 180px;
  height: 150px;
  margin: 0 auto;
  margin-top: 76px;
  background: url("../../../assets/renewal/renewalListData.png") no-repeat;
  background-size: cover;
  position: relative;
  // img {
  //   width: 180px;
  //   height: 150px;
  //   display: block;
  // }
  &::after {
    left: 50%;
    position: absolute;
    bottom: -50px;
    transform: translateX(-50%);
    content: "暂无数据";
    display: block;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(153, 153, 153, 1);
  }
}
</style>
