<template>
  <div class="businessPlanCover">
    <businessHead />
    <div class="setPos">
      <business-normal
        :quotationObjInfo="insurePlan"
        :totalPayment="totalPayment"
        :claimInfo="claimInfo"
        :personInfo="personInfo"
      />
    </div>
    <!-- 客户权益 -->
    <div class="starItem">
      <van-checkbox v-model="checked" icon-size="16px"
        ><span class="commonText15">客户权益</span></van-checkbox
      >
      <star-equity :isEquity="false" :isOut="true" :starInfo="starRight" />
    </div>
    <!-- 为您优选 -->
    <div class="item">
      <van-checkbox v-model="checked" icon-size="16px" @change="checked = true"
        ><span class="commonText15">为您优选</span></van-checkbox
      >
      <div class="imgCover">
        <img src="" alt="暂无图片" />
      </div>
    </div>
    <!-- 联系人 -->
    <div class="botInfo">
      <div class="botItem">
        <div class="head">
          <img
            src="../../../assets/vehicle/businessPlan/defaultHead.png"
            alt=""
          />
        </div>
        <span class="commonText18">{{ operatorInfo.optName || "" }}</span>
      </div>
      <div class="botItem">
        <div class="icon">
          <img src="../../../assets/vehicle/businessPlan/mobile.png" alt="" />
        </div>
        <a :href="`tel:${operatorInfo.phone}`" class="commonText14 lineTip"
          >联系我</a
        >
      </div>
      <div class="botItem">
        <div class="icon">
          <img src="../../../assets/vehicle/businessPlan/email.png" alt="" />
        </div>
        <span class="commonText14">查看名片</span>
      </div>
    </div>
  </div>
</template>
<script>
// 头部
import businessHead from "@/components/businessHead.vue";
// 中间
import businessNormal from "@/components/businessNormal.vue";
// 星级
import starEquity from "@/components/starEquity.vue";
// 按需引入 vant
import { Toast } from "vant";
import "vant/lib/index.css";
import { getBizPlan } from "@src/http/module/vehicle";
export default {
  name: "businessPlanInside",
  components: {
    businessHead,
    businessNormal,
    starEquity
  },
  data() {
    return {
      checked: true,
      orderId: this.$route.query.orderId,
      starRight: {
        curLevel: 1,
        curPoints: 0,
        totPoints: 0,
        name: "",
        custIntegral: 0,
        custLevel: 0
      },
      operatorInfo: {},
      personInfo: {},
      claimInfo: [], // 上半年理赔
      totalPayment: 0 // 总保费
    };
  },
  methods: {
    // 获取订单报价信息
    async getBizPlan() {
      const res = await getBizPlan({
        orderId: this.orderId
      });
      console.log(`res`, res);
      /**
       
       */
      if (res.code == "1" && res.data) {
        // 上半年理赔
        if (Array.isArray(res.data.quoteInfoVo.trafficLastClaims)) {
          this.claimInfo.push(...res.data.quoteInfoVo.trafficLastClaims);
        }
        if (Array.isArray(res.data.quoteInfoVo.businessLastClaims)) {
          this.claimInfo.push(...res.data.quoteInfoVo.businessLastClaims);
        }
        // 总保费
        if (res.data.quoteInfoVo.plans[0].trafficChecked == "1") {
          if (Number(res.data.quoteInfoVo.plans[0].trafficDuePremium)) {
            this.totalPayment +=
              res.data.quoteInfoVo.plans[0].trafficDuePremium;
          }
          if (Number(res.data.quoteInfoVo.plans[0].tax.sumPayment)) {
            this.totalPayment += res.data.quoteInfoVo.plans[0].tax.sumPayment;
          }
        }
        if (res.data.quoteInfoVo.plans[0].businessChecked == "1") {
          if (Number(res.data.quoteInfoVo.plans[0].businessDuePremium)) {
            this.totalPayment +=
              res.data.quoteInfoVo.plans[0].businessDuePremium;
          }
        }
        if (res.data.quoteInfoVo.plans[0].wealthAccChecked == "1") {
          if (Number(res.data.quoteInfoVo.plans[0].wealthAccPremium)) {
            this.totalPayment += res.data.quoteInfoVo.plans[0].wealthAccPremium;
          }
        }
        this.totalPayment = Number(this.totalPayment.toFixed(2));

        this.personInfo = res.data.RightsUserInfoVO; // 用户信息
        this.operatorInfo = res.data.OperatorInfoVO; // 操作员信息
        this.starRight.userName = res.data.RightsUserInfoVO.name; // 客户名字

        // 星级
        // 现有
        const custOrderIntegralRes = res.data.custOrderIntegralRes;
        if (custOrderIntegralRes) {
          this.starRight.custIntegral =
            custOrderIntegralRes.custCoreIntegral.custIntegral; // 现有积分
          this.starRight.custLevel =
            custOrderIntegralRes.custCoreIntegral.custLevel; // 现有积分

          // 出单后
          this.starRight.curPoints = custOrderIntegralRes.newIntegral; // 出单后积分
          this.starRight.totPoints = custOrderIntegralRes.totalCore; // 出单后总积分
          this.starRight.curLevel = custOrderIntegralRes.newCustLevel; // 当前星级
        }
      } else {
        Toast(res.msg);
      }
    }
  },
  mounted() {
    this.getBizPlan();
    console.log(`$route${this.$route}`);
  }
};
</script>
<style lang="less" scoped>
// common
// font
.commonText15 {
  font-size: 15px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
  line-height: 21px;
}
.commonText18 {
  font-size: 18px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(0, 0, 0, 1);
  line-height: 16px;
}
.commonText14 {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(33, 85, 159, 1);
  line-height: 20px;
}
// common
.businessPlanCover {
  background: linear-gradient(
    180deg,
    rgba(191, 223, 255, 1) 0%,
    rgba(228, 241, 255, 1) 100%
  );
  padding-bottom: 65px;
  .setPos {
    position: relative;
    // height: 100%;
    top: -70px;
  }
  .starItem {
    background-color: white;
    margin: 0 11px;
    padding: 20px 12px 25px;
    border-radius: 10px;
    // 修改组件部分
    /deep/ .equityCover {
      border: none;
      border-radius: unset;
      padding: 34px 0 14px;
    }
  }
  .item {
    background-color: white;
    margin: 0 11px;
    margin-top: 10px;
    padding: 18px 19px 30px;
    border-radius: 10px;
    margin-bottom: 89px;
    .imgCover {
      width: 100%;
      height: 152px;
      margin-top: 15px;
      background-color: wheat;
    }
  }
  .botInfo {
    // padding: 14px;
    width: 100%;
    height: 65px;
    background: rgba(255, 255, 255, 1);
    display: flex;
    position: fixed;
    justify-content: space-around;
    bottom: 0;
    .botItem {
      display: flex;
      align-items: center;
      .lineTip {
        border-right: 1px solid #d7d7d7;
        padding-right: 23px;
      }
      .head {
        width: 38px;
        height: 38px;
        margin-right: 14px;
        img {
          width: 100%;
        }
      }
      .icon {
        width: 14px;
        height: 14px;
        margin-right: 5px;
        img {
          width: 100%;
        }
      }
    }
  }
}
</style>
