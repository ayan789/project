<template>
  <div class="costReflectTodayDetails">
    <div class="costReflectTodayDetails-head">
      <span @click.stop="$emit('update:showTodayDetails', false)"
        ><van-icon name="arrow-left"
      /></span>
      <span>当日车险收入</span>
    </div>
    <p class="costReflectTodayDetails-total">
      <span>{{ planFeeSum }}</span>
      <span>收入合计</span>
    </p>
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      :error.sync="error"
      error-text="请求失败，点击重新加载"
      @load="onLoad"
      :immediate-check="false"
    >
      <div
        class="costReflectTodayDetails-content"
        v-for="(item, index) in todayData"
        :key="index"
      >
        <div>
          <span>
            <img src="../../../assets/performance/car.png" />
          </span>
          <span>{{ item.licenseNo }}</span>
        </div>
        <p>
          <span>险种</span>
          <span>车险</span>
        </p>
        <p>
          <span>保单号</span>
          <span>{{ item.policyNo }}</span>
        </p>
        <p>
          <span>保费</span>
          <span>{{ item.premium }}元</span>
        </p>
        <p>
          <span>投保日期</span>
          <span>{{ item.policyCreateDate }}</span>
        </p>
        <p>
          <span>预计收入</span>
          <span>{{ item.planFee }}元</span>
        </p>
      </div>
    </van-list>
  </div>
</template>

<script>
import { planFeeDetailInquiry } from "@src/http/module/performance.js";
import { List } from "vant";
export default {
  components: {
    [List.name]: List
  },
  props: ["showTodayDetails","routerParams"],
  data() {
    return {
      /* -- */
      isAgentCode: "",
      loading: false,
      finished: false,
      error: false,
      planFeeSum: "", //收入合计
      todayData: [],
      formData: {
        userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
        // agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
        agentCode: this.isAgentCode? JSON.parse(localStorage.getItem("userInfo")).agentCode || "" : this.routerParams.agentCode || "",
        handlerCode:
          JSON.parse(localStorage.getItem("userInfo")).handlerCode || "",
        page: {
          page: 1,
          size: 10
        }
      }
    }
  },
  // data: () => ({
  //   loading: false,
  //   finished: false,
  //   error: false,
  //   planFeeSum: "", //收入合计
  //   todayData: [],
  //   formData: {
  //     userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
  //     // agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
  //     agentCode: this.routerParams.agentCode || "",
  //     handlerCode:
  //       JSON.parse(localStorage.getItem("userInfo")).handlerCode || "",
  //     page: {
  //       page: 1,
  //       size: 10
  //     }
  //   }
  // }),
  beforeCreate() {
    //全局获取缓存数据
    this.$globalSetLocalStorage();
    this.isAgentCode = JSON.parse(localStorage.getItem("userInfo")).agentCode ? true : false;
  },
  created() {
    this.queryPlanFeeDetailInquiry();
  },
  methods: {
    onLoad() {
      this.queryPlanFeeDetailInquiry();
      this.formData.page.page += 1;
    },
    queryPlanFeeDetailInquiry() {
      planFeeDetailInquiry(this.formData)
        .then(res => {
          if (res.code === "1") {
            this.planFeeSum = res.data.planFeeSum;
            this.todayData.push(...res.data.planFeeDetails);
            this.loading = false;
            if (this.todayData.length >= res.data.page.rows) {
              this.finished = true;
            }
          }
        })
        .catch(err => {
          this.error = true;
        });
    }
  }
};
</script>

<style lang="less" scoped>
.costReflectTodayDetails {
  background: #f5f5f5;
  &-head {
    padding: 0 16px;
    background: linear-gradient(
      115deg,
      rgba(30, 70, 246, 1) 0%,
      rgba(39, 154, 255, 1) 100%
    );
    height: 64px;
    line-height: 64px;
    text-align: center;
    span {
      font-size: 17px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
    }
    span:nth-child(1) {
      float: left;
    }
  }
  &-total {
    margin: -1px 0 0px;
    text-align: center;
    color: #333;
    padding: 16px 0;
    background: linear-gradient(
      115deg,
      rgba(30, 70, 246, 1) 0%,
      rgba(39, 154, 255, 1) 100%
    );
    span:nth-child(1) {
      display: block;
      font-size: 40px;
      font-family: PingFangSC-Semibold, PingFang SC;
      font-weight: 600;
      color: rgba(255, 255, 255, 1);
    }
    span:nth-child(2) {
      display: block;
      font-size: 14px;
      font-family: PingFangHK-Regular, PingFangHK;
      font-weight: 400;
      color: rgba(255, 255, 255, 1);
    }
  }
  &-content {
    background: #fff;
    padding: 10px 18px;
    div {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      margin-bottom: 18px;
      span:nth-child(1) {
        display: inline-block;
        vertical-align: middle;
        width: 29px;
        height: 29px;
        background: rgba(241, 247, 255, 1);
        border-radius: 15px;
        border: 1px solid rgba(171, 209, 255, 1);
        margin-right: 10px;
        img {
          width: 30px;
          height: 30px;
          transform: scale(0.7);
          -webkit-transform: scale(0.7);
        }
      }
    }
    p {
      display: flex;
      display: -webkit-flex;
      justify-content: space-between;
      -webkit-justify-content: space-between;
      margin-bottom: 6px;
      span:nth-child(1) {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
      }
      span:nth-child(2) {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
      }
    }
    // p {
    //   margin-bottom: 10px;
    //   color: #666;
    //   font-size: 14px;
    // }
    // p:nth-child(1) {
    //   color: #333;
    //   font-size: 16px;
    // }
    // p:last-child {
    //   display: -webkit-box;
    //   display: -webkit-flex;
    //   display: flex;
    //   -webkit-box-pack: justify;
    //   -webkit-justify-content: space-between;
    //   justify-content: space-between;
    // }
  }
}
</style>
