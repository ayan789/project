<template>
  <div>
    <popNav popTitle="整单优惠信息" />
    <div class="popBody">
      <!-- <div class="backImg">
        <div class="title">险别信息</div>
      </div> -->
      <div class="card">
        <h4>交强险优惠信息</h4>
        <div class="rowXian1">
          <div>
            <div class="left_t">违法调整系数</div>
            <div class="right_t">
              {{ compulsoryObj.trafficViolationRate | rateValue }}
            </div>
          </div>
        </div>
        <div class="rowXian1">
          <div>
            <div>
              <div class="left_t">浮动原因</div>
              <div class="right_t">
                {{ compulsoryObj.trafficViolationRateFloatCauseDesc }}
              </div>
            </div>
          </div>
        </div>
        <div class="rowXian1">
          <div>
            <div class="left_t">理赔调整系数</div>
            <div class="right_t">
              {{ compulsoryObj.claimRate | rateValue }}
            </div>
          </div>
        </div>
        <div class="rowXian1">
          <div>
            <div>
              <div class="left_t">浮动原因</div>
              <div class="right_t">
                {{ compulsoryObj.claimRateFloatDesc }}
              </div>
            </div>
          </div>
        </div>
        <h4>商业险优惠信息</h4>
        <div class="rowXian1">
          <div>
            <div class="left_t">商业险优惠系数</div>
            <div class="right_t">
              {{ insureObj.discountCoefficientRate | rateValue }}
            </div>
          </div>
        </div>
        <div class="rowXian1">
          <div>
            <div class="left_t">自主核保系数</div>
            <div class="right_t">
              {{ insureObj.underwritingRate | rateValue }}
            </div>
          </div>
        </div>
        <div class="rowXian1">
          <div>
            <div>
              <div class="left_t">自主渠道系数</div>
              <div class="right_t">
                {{ insureObj.channelRate | rateValue }}
              </div>
            </div>
          </div>
        </div>
        <div class="rowXian1">
          <div>
            <div class="left_t">NCD系数</div>
            <div class="right_t">
              {{ insureObj.ncdRate | rateValue }}
            </div>
          </div>
        </div>
        <div class="rowXian1">
          <div>
            <div>
              <div class="left_t">浮动原因</div>
              <div class="right_t">
                {{ insureObj.ncdRateFloatCauseDesc }}
              </div>
            </div>
          </div>
        </div>
        <div class="rowXian1">
          <div>
            <div class="left_t">交通违法系数</div>
            <div class="right_t">
              {{ insureObj.trafficViolationRate | rateValue }}
            </div>
          </div>
        </div>
        <div class="rowXian1">
          <div>
            <div>
              <div class="left_t">浮动原因</div>
              <div class="right_t">
                {{ insureObj.trafficViolationRateFloatCauseDesc }}
              </div>
            </div>
          </div>
        </div>
        <h4>优惠明细</h4>
        <div class="rowXian">
          <div>险别</div>
          <div>标准保费(元)</div>
          <div>实交保费(元)</div>
        </div>
        <div class="rowXian" v-if="trafficChecked">
          <div>交强险</div>
          <div>{{ trafficStdPremium }}</div>
          <div>{{ trafficDuePremium }}</div>
        </div>
        <div v-if="businessChecked">
          <div
            class="rowXian"
            v-for="(item, index) in businessRisksList"
            :key="index"
          >
            <div>{{ item.kindName }}</div>
            <div>{{ item.standardPremium }}</div>
            <div>{{ item.premium }}</div>
          </div>
        </div>
        <!-- <div class="rowXian">
          <div>第三者责任险</div>
          <div>{{ partyLiabilityinsurance.standardPremium }}</div>
          <div>{{ partyLiabilityinsurance.premium }}</div>
        </div>
        <div class="rowXian">
          <div>盗抢险</div>
          <div>{{ theftAndrescue.standardPremium }}</div>
          <div>{{ theftAndrescue.premium }}</div>
        </div> -->
        <!-- <div class="rowXian">
          <div>司机险</div>
          <div>{{ chauffeur.standardPremium }}</div>
          <div>{{ chauffeur.premium }}</div>
        </div>
        <div class="rowXian">
          <div>乘客险</div>
          <div>{{ passenger.standardPremium }}</div>
          <div>{{ passenger.premium }}</div>
        </div> -->
      </div>
    </div>
  </div>
</template>
<script>
import popNav from "@/components/popNav.vue";
import { mapState } from "vuex";
export default {
  components: {
    popNav
  },
  data() {
    return {
      businessRisksList: [],
      responsibility: {},
      responsibilList: [],
      vehicleLossinsurance: {}, // 车辆损失险
      partyLiabilityinsurance: {}, //第三者责任险
      theftAndrescue: {}, //盗抢险
      detailed: {
        vehicleTaxVo: {}
      },
      underwritingCoefficient: "",
      underwritingCoefficientReason: "",
      channelCoefficient: "",
      channelCoefficientReason: "",
      illegalCoefficient: "",
      businessChannel: "",
      NCDChannel: "",
      NCDChannelReason: "",
      trafficOffence: "",
      trafficOffenceReason: "",
      carDamage: "",
      Inception: "",
      chauffeur: "",
      passenger: "",
      partyLiability: "",
      fullofferValue: "",
      trafficDuePremium: "",
      trafficStdPremium: "",
      businessChecked: false,
      trafficChecked: false
    };
  },
  computed: {
    ...mapState("vehicle", {
      // 交强险
      compulsoryObj: ({ trafficPlatformInfo }) => trafficPlatformInfo,
      // 商业险
      insureObj: ({ businessPlatformInfo }) => businessPlatformInfo
    })
  },
  filters: {
    rateValue(v) {
      if (v === 0 || v === "0") {
        return "1";
      }
      if (v < 0) {
        return (1 + v).toFixed(2);
      }
      return v;
    }
  },
  mounted() {
    this.detailed = this.$route.params.detailed;
    this.businessRisksList = this.detailed.insurancePlan.businessRisks;
    this.businessChecked =
      this.detailed.insurancePlan.businessChecked == "0" ? false : true;
    this.trafficChecked =
      this.detailed.insurancePlan.trafficChecked == "0" ? false : true;
    this.trafficDuePremium = this.detailed.insurancePlan.trafficDuePremium;
    this.trafficStdPremium = this.detailed.insurancePlan.trafficStdPremium;
    // 系数
    //  交强险优惠系数
    // let trafficInfo = this.detailed.floating.trafficFloatingItems;

    // let array = trafficInfo.filter(item => item.discountCode === "40");
    // this.underwritingCoefficient = array.length
    //   ? array[0].floatCoefficient
    //   : "";
    // this.underwritingCoefficientReason = array.length
    //   ? array[0].floatReasonDesc
    //   : "";
    //  交强险理赔调整
    // let array1 = trafficInfo.filter(item => item.discountCode === "30");
    // this.channelCoefficient = array1.length ? array1[0].floatCoefficient : "";
    // this.channelCoefficientReason = array1.length
    //   ? array1[0].floatReasonDesc
    //   : "";
    //  商业险优惠
    // this.fullofferValue = this.detailed.floating.discountCoefficient;
    // let businessInfo = this.detailed.floating.businessFloatingItems;
    // let businessArray1 = businessInfo.filter(
    //   item => item.discountCode === "10"
    // );
    // this.illegalCoefficient = businessArray1.length
    //   ? businessArray1[0].floatCoefficient
    //   : "";

    // let businessArray2 = businessInfo.filter(
    //   item => item.discountCode === "20"
    // );
    // this.businessChannel = businessArray2.length
    //   ? businessArray2[0].floatCoefficient
    //   : "";

    // let businessArray3 = businessInfo.filter(
    //   item => item.discountCode === "30"
    // );
    // this.NCDChannel = businessArray3.length
    //   ? businessArray3[0].floatCoefficient
    //   : "";
    // this.NCDChannelReason = businessArray3.length
    //   ? businessArray3[0].floatReasonDesc
    //   : "";

    // let businessArray4 = businessInfo.filter(
    //   item => item.discountCode === "40"
    // );
    // this.trafficOffence = businessArray4.length
    //   ? businessArray4[0].floatCoefficient
    //   : "";
    // this.trafficOffenceReason = businessArray4.length
    //   ? businessArray4[0].floatReasonDesc
    //   : "";
    //   险别
    //  车辆损失险
    // let insureArray = this.detailed.insurancePlan.businessRisks.filter(
    //   item => item.kindCode === "C100121"
    // );
    // if (insureArray[0] === undefined) {
    //   this.carDamage = {};
    // } else {
    //   this.carDamage = insureArray[0];
    // }
    //  第三者责任险
    // let insureArray1 = this.detailed.insurancePlan.businessRisks.filter(
    //   item => item.kindCode === "C100122"
    // );
    // if (insureArray1[0] === undefined) {
    //   this.partyLiability = {};
    // } else {
    //   this.partyLiability = insureArray1[0];
    // }
    //  盗抢险
    // let insureArray2 = this.detailed.insurancePlan.businessRisks.filter(
    //   item => item.kindCode === "C100125"
    // );
    // if (insureArray2[0] === undefined) {
    //   this.Inception = {};
    // } else {
    //   this.Inception = insureArray2[0];
    // }
    //  司机险
    // let insureArray3 = this.detailed.insurancePlan.businessRisks.filter(
    //   item => item.kindCode === "C100123"
    // );
    // if (insureArray3[0] === undefined) {
    //   this.chauffeur = {};
    // } else {
    //   this.chauffeur = insureArray3[0];
    // }
    //  乘客险
    // let insureArray4 = this.detailed.insurancePlan.businessRisks.filter(
    //   item => item.kindCode === "C100124"
    // );
    // if (insureArray4[0] === undefined) {
    //   this.passenger = {};
    // } else {
    //   this.passenger = insureArray4[0];
    // }
  },
  methods: {
    //返回上一页
    goBack() {
      this.$globalBack();
    }
  }
};
</script>
<style scoped lang="less">
.popBody {
  margin-top: 64px;
  background: #ffffff;
  left: 0;
  bottom: 0;
  top: 0;
  position: absolute;
  width: 100%;
  margin-right: 150px;
  padding-top: 10px;
  // .van-tabs {
  //   width: 240px;
  // }
  .card {
    padding: 0.3rem 15px;
    .rowXian {
      display: flex;
      div {
        flex: 1;
        line-height: 24px;
        justify-content: space-around;
      }
      div:nth-of-type(2) {
        text-align: center;
      }
      div:nth-of-type(3) {
        text-align: right;
      }
    }
    .rowXian1 {
      display: flex;
      div {
        flex: 1;
        line-height: 24px;
        justify-content: space-around;
        .left_t {
          float: left;
        }
        .right_t {
          float: right;
        }
      }
      // div:nth-of-type(2) {
      //   text-align: center;
      // }
      // div:nth-of-type(3) {
      //   text-align: right;
      // }
    }
    h4 {
      padding: 0.3rem 0;
      position: relative;
      font-size: 14px;
      &::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: 0;
        right: auto;
        top: auto;
        width: 100%;
        background-color: #e1e1e1;
        display: block;
        -webkit-transform-origin: 50% 100%;
        transform-origin: 50% 100%;
      }
    }
    li {
      display: flex;
      justify-content: space-between;
      font-size: 14px;
      // padding: 0.1rem 0;
    }
  }
}
.backImg {
  height: 69px;
  background: url("../../../assets/order/orderList/headers.png") no-repeat;
  .title {
    font-size: 16px;
    font-weight: 500;
    color: #000;
    line-height: 75px;
    padding-left: 20px;
  }
}
.van-icon {
  z-index: 9;
  top: 14px;
  left: 18px;
  font-size: 18px;
  position: fixed;
}
.rowXian1,
.rowXian {
  font-size: 14px;
}
</style>
