<template>
  <div class="costReflect">
    <!-- <Global-globalNav></Global-globalNav> -->
    <div class="costReflect-header-background">
      <div class="costReflect-header">
        <span @click="back()"><van-icon name="arrow-left"/></span>
        <span>佣金</span>
        <span @click="$router.push('/incomeDetails')">
          <span v-if="isAgentCode">收入明细</span>
        </span>
      </div>
      <div class="cashOut-amount">
        <p>{{ queryData.planFee }}</p>
        <p>可提现手续费(元)</p>
        <van-button
          round
          plain
          type="info"
          @click="$router.push('/costReflectDetails')"
          v-if="isAgentCode && notAllowedPay!='1'"
          >提现</van-button
        >
      </div>
    </div>
    <div class="today-income">
      <p class="today-income--head" @click="showTodayDetails = true">
        <span>{{ queryData.pendingEffectFee }}</span
        ><span><van-icon name="arrow" size="12" color="#BFBFBF"/></span>
        <van-popup
          v-model="showTodayDetails"
          position="bottom"
          :style="{ height: '100%' }"
        >
          <today-details
            v-if="showTodayDetails"
            :showTodayDetails.sync="showTodayDetails"
            :routerParams = "routerParams"
          ></today-details>
        </van-popup>
      </p>
      <div class="today-income--content">
        <p>当日车险预计收入（元)</p>
      </div>
    </div>
    <div class="month-income">
      <p class="title">当月收入（元）</p>
      <div class="month-income--content">
        <!-- <p class="van-hairline--right"> -->
        <p>
          <span>{{ queryData.sumFeePayMonth }}元</span>
          <span>提现总额</span>
        </p>
        <!-- <p class="van-hairline--right"> -->
        <p >
          <span>{{ queryData.sumTaxDeductionMonth }}元</span>
          <span>扣税总额</span>
        </p>
        <p>
          <span>{{ queryData.sumFeeAccountMonth }}元</span>
          <span>到账总额</span>
        </p>
      </div>
    </div>
    <div class="year-income">
      <p class="title">当年收入（元）</p>
      <div class="year-income--content">
        <p>
          <span>{{ queryData.sumFeePayYear }}元</span>
          <span>提现总额</span>
        </p>
        <p>
          <span>{{ queryData.sumTaxDeductionYear }}元</span>
          <span>扣税总额</span>
        </p>
        <p>
          <span>{{ queryData.sumFeeAccountYear }}元</span>
          <span>到账总额</span>
        </p>
      </div>
    </div>
    <div class="reflect-rule">
      <p class="reflect-rule--head" @click="showRule = !showRule">
        <span>提现规则</span>
        <span :class="{ isRortate: showRule }"><van-icon name="arrow"/></span>
      </p>
      <div
        class="reflect-rule--content"
        :style="{ display: showRule ? 'block' : 'none' }"
      >
        <p>1、"可提现收入"为T-1税前收入， 具体提现金额可参照个代扣税说明；</p>
        <p>
          2、"当月累计提现金额"：指在销管系统中当月累计的已生成结算单金额，为税前收入，具体到账金额参照个代扣税说明；
        </p>
        <p>3、"可提现收入"大于{{ queryData.minPayFee }}元时方可提现；</p>
        <p>4、"当日车险收入"仅实时展示当日通过APP出单的车险手续费收入；</p>
        <p>5、因手续费调整等原因，"当日车险收入"仅为参考值；</p>
        <p>
          6、当代理人所出保单的归属经办人C3费用为负值时，该保单的手续费不可提现。
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { Button, Popup } from "vant";
import { planFeeInquiry } from "@src/http/module/performance.js";
export default {
  components: {
    [Button.name]: Button,
    [Popup.name]: Popup,
    //今日收入明细
    todayDetails: () => import("./costReflectTodayDetails")
  },
  data: () => ({
    showRule: false, //提现规则显示隐藏
    showTodayDetails: false, //今日收入明细弹出框
    isAgentCode: "", //业务员不展示提现按钮
    queryData: {
      planFee: "", //手续费
      pendingEffectFee: "", //待生效的手续费
      minPayFee: "", //最小提现金额
      sumFeePayMonth: "", //月提现总额
      sumTaxDeductionMonth: "", //月扣税总额
      sumFeeAccountMonth: "", //月到账总额
      sumFeePayYear: "", //年提现总额
      sumTaxDeductionYear: "", //年扣税总额
      sumFeeAccountYear: "" //年到账总额
    },
    notAllowedPay:""
  }),
  created() {
    //全局获取缓存数据
    this.$globalSetLocalStorage();
    this.isAgentCode = JSON.parse(localStorage.getItem("userInfo")).agentCode ? true : false;
    //提现页面初始化查询
    setTimeout(() => {
      this.InitPlanFeeInquiry();
    }, 800);
  },
  computed: {
    routerParams() {
      return { ...this.$route.params.item };
    }
  },
  methods: {
    back() {
      if (window.WebViewJavascriptBridge) {
        this.$native.closeWeb();
      } else {
        this.$router.push({ path: "/" });
      }
    },
    //提现页面查询
    async InitPlanFeeInquiry() {
      await planFeeInquiry({
        queryType: "01",
        // agentCode: this.$localStorage.agentCode || "",
        agentCode: this.isAgentCode? JSON.parse(localStorage.getItem("userInfo")).agentCode || "" : this.routerParams.agentCode || "",
        // userCode: this.routerParams.userCode || "",
        handlerCode: this.isAgentCode? JSON.parse(localStorage.getItem("userInfo")).handlerCode || "" : this.routerParams.handlerCode || ""
      })
        .then(res => {
          if (res.code === "1") {
            this.queryData = {
              ...res.data.planFeeInquiryResponseVO.planFees[0]
            };
            // this.queryData = { ...res.data.planFeeInquiryResponseVO };
            this.queryData = Object.assign(
              this.queryData,
              res.data.planFeeInquiryResponseVO
            );

            /* 修改--存值 */
            const sumFeePayMonth =
              res.data.planFeeInquiryResponseVO.planFees[0].sumFeePayMonth;
            localStorage.setItem("sumFeePayMonth", sumFeePayMonth);
            const minPayFee = 
              res.data.planFeeInquiryResponseVO.minPayFee;
            localStorage.setItem("minPayFee", minPayFee)
            //开关控制是否提现按钮
            this.notAllowedPay = res.data.planFeeInquiryResponseVO.notAllowedPay || "";

          } else {
            this.$dialog.alert({
              message: res.msg
            });
          }
        })
        .catch(err => {});
    }
  }
};
</script>

<style lang="less" scoped>
.costReflect {
  //   background: #f5f5f5;
  &-header-background {
    background: linear-gradient(
      115deg,
      rgba(30, 70, 246, 1) 0%,
      rgba(39, 154, 255, 1) 100%
    );
  }
  &-header {
    padding: 0 16px;
    height: 64px;
    line-height: 64px;
    text-align: center;
    display: flex;
    justify-content: space-between;
    span {
      font-size: 18px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
    }
    span:nth-child(1) {
    }
    span:nth-child(3) {
      font-size: 14px;
    }
  }
  .cashOut-amount {
    text-align: center;
    background: #fff;
    align-items: center;
    padding-bottom: 26px;
    background: linear-gradient(
      115deg,
      rgba(30, 70, 246, 1) 0%,
      rgba(39, 154, 255, 1) 100%
    );
    p:nth-child(1) {
      font-size: 42px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(255, 255, 255, 1);
    }
    p:nth-child(2) {
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(255, 255, 255, 1);
      margin-bottom: 17px;
    }
    /deep/.van-button {
      width: 68px;
      height: 26px;
      line-height: 26px;
    }
  }
  .today-income {
    background: #fff;
    padding: 20px 15px;
    margin-bottom: 10px;
    &--head {
      display: flex;
      display: -webkit-flex;
      justify-content: space-between;
      -webkit-justify-content: space-between;
      font-size: 21px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
    }
    &--content {
      p {
        font-size: 11px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
      }
    }
  }
  .title {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
  }
  .month-income {
    padding: 20px 15px;
    background: #fff;
    margin-bottom: 10px;
    &--content {
      margin-top: 20px;
      display: -webkit-box;
      display: -webkit-flex;
      display: flex;
      -webkit-box-pack: justify;
      -webkit-justify-content: space-between;
      justify-content: space-between;
      span {
        display: block;
        font-size: 14px;
      }
      span:nth-child(1) {
        // color: rgba(102, 102, 102, 1);
        color: #666;
        margin-bottom: 6px;
      }
      span:nth-child(1) {
        color: #999;
        // color: rgba(153, 153, 153, 1);
      }
    }
  }
  .year-income {
    padding: 20px 15px;
    background: #fff;
    margin-bottom: 10px;
    &--content {
      margin-top: 20px;
      display: -webkit-box;
      display: -webkit-flex;
      display: flex;
      -webkit-box-pack: justify;
      -webkit-justify-content: space-between;
      justify-content: space-between;
      span {
        display: block;
        font-size: 14px;
      }
      span:nth-child(1) {
        color: #666;
        margin-bottom: 6px;
      }
      span:nth-child(1) {
        color: #999;
      }
    }
  }
  .reflect-rule {
    padding: 20px 15px 0;
    background: #fff;
    margin-bottom: 20px;
    &--head {
      display: -webkit-box;
      display: -webkit-flex;
      display: flex;
      -webkit-box-pack: justify;
      -webkit-justify-content: space-between;
      justify-content: space-between;
      font-size: 14px;
      color: #333;
      border-bottom: 1px solid #f3f3f3;
      padding-bottom: 10px;
      margin-bottom: 10px;
      .isRortate {
        transform: rotate(90deg);
      }
    }
    &--content {
      p {
        color: #666;
        text-indent: 10px;
        line-height: 20px;
      }
    }
  }
}
</style>
