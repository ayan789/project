<template>
  <div class="incomeDetails">
    <div class="incomeDetails-header">
      <!-- @click="$router.go(-1)" -->
      <span @click="Back()"><van-icon name="arrow-left"/></span>
      <span>收入明细</span>
    </div>
    <van-row
      type="flex"
      justify="center"
      :style="{
        background:
          'linear-gradient(115deg,rgba(30, 70, 246, 1) 0%,rgba(39, 154, 255, 1) 100%)'
      }"
      class="incomeDetails-select"
    >
      <cost-select
        v-model="formData.month"
        @selected="selected"
        backgroungStyle="linear-gradient(115deg,rgba(30, 70, 246, 1) 0%,rgba(39, 154, 255, 1) 100%)"
      ></cost-select>
    </van-row>

    <div class="incomeDatils-amount">
      <p>
        <span>提现总额 </span>
        <span>{{ sumData.sumFeePayMonth }}</span>
      </p>
      <p>
        <span>扣税总额 </span>
        <span>{{ sumData.sumTaxDeductionMonth }}</span>
      </p>
      <p>
        <span>到账总额 </span>
        <span>{{ sumData.sumFeeAccountMonth }}</span>
      </p>
    </div>
    <div
      class="incomeDetails-content"
      v-for="(item, index) in queryData"
      :key="index"
    >
      <!-- <van-row
        class="incomeDetails-content--title"
        type="flex"
        justify="space-between"
      >
        <van-col span="6">日期</van-col>
        <van-col span="12">保单号/批单号</van-col>
        <van-col span="6" offset="2">手续费</van-col>
      </van-row> -->
      <div class="incomeDetails-content_">
        <div
          class="incomeDetails-content--details"
          v-for="(value, i) in item.planFeePays"
          :key="i"
        >
          <p>
            <span>{{ value.certiNo }}</span
            ><span>日期: {{ item.packageDate.replace(/-/g, "/") }}</span>
          </p>

          <p>
            {{
              Number(value.planFee) > 0
                ? `+${value.planFee}`
                : `-${value.planFee}`
            }}元
          </p>
        </div>
        <p
          class="incomeDetails-more"
          @click="morePackNo(item.packNo)"
          v-if="item.planFeePays.length >= 10 && morePackNoDes"
        >
          {{ morePackNoDes }}
        </p>
      </div>
    </div>
    <p
      @click="morePacks()"
      v-if="queryData.length >= 2"
      class="incomeDetails-more-packs"
    >
      更多批次
    </p>
  </div>
</template>

<script>
import costSelect from "./costSelect";
import { feePayDetailQuery } from "@src/http/module/performance.js";
export default {
  components: {
    //下拉框
    costSelect
  },
  data: () => ({
    selectDate: "", //选中日期
    formData: {
      queryType: "0",
      userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
      agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
      handlerCode:
        JSON.parse(localStorage.getItem("userInfo")).handlerCode || "",
      month: "",
      page: { size: 10, page: 1 }
    },
    morePackNoPage: 1, //更多保单页数
    morePacksPage: 1, //更多批次页数
    morePackNoDes: "更多保单", //更多保单页数
    morePacksDes: "更多批次", //更多批次页数
    sumData: {
      sumFeePayMonth: "" || 0.0, //提现
      sumFeeAccountMonth: "" || 0.0, //到账
      sumTaxDeductionMonth: "" || 0.0 //扣税
    },
    queryData: []
  }),
  created() {
    // this.queryFreePay();
  },
  methods: {
    /* 修改--添加 */
    Back() {
      this.$globalBack();
    },
    //选择时间
    selected(val) {
      this.queryData = [...[]];
      delete this.formData["packNo"];
      this.formData = Object.assign({}, this.formData, {
        queryType: "0",
        userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
        agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
        handlerCode:
          JSON.parse(localStorage.getItem("userInfo")).handlerCode || "",
        page: { size: 10, page: 1 }
      });
      this.queryFreePay();
    },
    //点击查看更多保单
    morePackNo(packNo) {
      this.morePackNoPage += 1;
      this.formData = Object.assign({}, this.formData, {
        page: {
          page: this.morePackNoPage,
          size: 10
        },
        queryType: "2",
        packNo: packNo
      });
      //console.log(this.formData);
      this.queryFreePay();
    },
    //点击查看更多批次
    morePacks() {
      this.morePacksPage += 1;
      delete this.formData["packNo"];
      this.formData = Object.assign({}, this.formData, {
        page: {
          page: this.morePacksPage,
          size: 2
        },
        queryType: "1"
      });
      this.queryFreePay();
      //console.log(this.formData);
    },
    //查询提现明细
    async queryFreePay() {
      await feePayDetailQuery(this.formData)
        .then(res => {
          if (res.code === "1") {
            //查询当月
            if (this.formData.queryType === "0") {
              this.sumData = Object.assign({}, this.sumData, {
                sumFeePayMonth: res.data.feePayDetailResponseVO.sumFeePayMonth,
                sumFeeAccountMonth:
                  res.data.feePayDetailResponseVO.sumFeeAccountMonth,
                sumTaxDeductionMonth:
                  res.data.feePayDetailResponseVO.sumTaxDeductionMonth
              });
              this.queryData = [...res.data.feePayDetailResponseVO.packs];
            } else if (this.formData.queryType === "1") {
              //查询更多批次
              this.queryData.push(...res.data.feePayDetailResponseVO.packs);
            } else if (this.formData.queryType === "2") {
              //查询更多保单
              Object.keys(this.queryData).forEach(i => {
                if (this.queryData[i].packNo === this.formData.packNo) {
                  this.queryData[i].planFeePays.push(
                    ...res.data.feePayDetailResponseVO.packs[0].planFeePays
                  );
                }
              });
            }
          }
        })
        .catch(err => {});
    }
  }
};
</script>

<style lang="less" scoped>
.incomeDetails {
  background: #f5f5f5;
  .incomeDetails-header {
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
      font-size: 18px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
    }
    span:nth-child(1) {
      float: left;
    }
    span:nth-child(3) {
      float: right;
      font-size: 14px;
    }
  }
  /deep/.incomeDetails-select {
    position: relative;
    &::before {
      position: absolute;
      content: "";
      display: inline-block;
      top: 50%;
      left: 9;
      height: 0.8px;
      width: 100%;
      background: #ffffff;
      transform: scaleY(0.5);
      -webkit-transform: scaleY(0.5);
    }
  }
  .incomeDatils-amount {
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    justify-content: space-between;
    padding: 10px 15px;
    background: #fff;
    margin-bottom: 10px;
    background: linear-gradient(
      115deg,
      rgba(30, 70, 246, 1) 0%,
      rgba(39, 154, 255, 1) 100%
    );
    p {
      span:nth-child(1) {
        font-size: 14px;
        color: #fff;
        display: block;
      }
      span:nth-child(2) {
        font-size: 14px;
        color: #fff;
        display: block;
      }
    }
  }
  .incomeDetails-content {
    text-align: center;
    &--title {
      margin-bottom: 10px;
      /deep/ .van-col {
        font-size: 14px;
        color: #333;
      }
    }
    &--details {
      margin-bottom: 10px;
      padding: 15px 13px;
      display: -webkit-box;
      display: -webkit-flex;
      display: flex;
      -webkit-box-pack: justify;
      -webkit-justify-content: space-between;
      justify-content: space-between;
      align-items: center;
      -webkit-align-items: center;
      text-align: left;
      background: #fff;
      border-radius: 7px;
      p:nth-child(1) {
        span {
          display: block;
          font-size: 12px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(153, 153, 153, 1);
        }
        span:nth-child(1) {
          display: block;
          font-size: 15px;
          font-family: PingFang-SC-Medium, PingFang-SC;
          font-weight: 500;
          color: rgba(51, 51, 51, 1);
          margin-bottom: 5px;
        }
      }
    }
  }
  &-content_ {
    padding: 10px 0;
    margin: 0 16px;
    margin-bottom: 20px;
  }
  &-more {
    font-size: 15px;
    color: #3b8afe;
  }
  &-more-packs {
    font-size: 15px;
    color: #3b8afe;
    text-align: center;
    margin-bottom: 20px;
  }
}
</style>
