<template>
  <div>
    <popNav popTitle="平台信息" />
    <!-- <div class="popBody">
      <div class="backImg">
        <div class="title">平台信息</div>
      </div>
    </div> -->
    <div class="popBody popBody1">
      <img class="img" src="../../../../assets/order/orderList/xiaoCar.png" alt="">
      <div
        class="whetherBack"
        v-for="(item, index) in whetherOptions"
        :key="index"
        @click="regardlessSelect(index)"
        :class="{ active: index == print }"
      >
        {{ item }}
        <span class="line" :class="{ backgroundLine: index == print }"></span>
      </div>
      <div>
        <insureBar v-show="print == 0" :insureObj="insureObj"></insureBar>
        <compulsoryInsurance v-show="print == 1" :compulsoryObj="compulsoryObj"></compulsoryInsurance>
      </div>
    </div>
  </div>
</template>
<script>
import popNav from "@/components/popNav.vue";
import insureBar from "@/components/insureBar.vue";
import compulsoryInsurance from "./compulsoryInsurance.vue";

export default {
  components: {
    popNav,
    insureBar,
    compulsoryInsurance
  },
  data() {
    return {
      print: "",
      whetherOptions: ["商业险", "交强险"],
      detailed: {
        vehicleTaxVo: {}
      },
      insureObj: {},
      compulsoryObj: {}
    };
  },
  created() {
    this.$nextTick(() => {
      this.detailed = this.$route.params;
      if (this.detailed.businessInfoPlatform == null) {
        this.insureObj = {};
      } else {
        this.insureObj = this.detailed.businessInfoPlatform;

        if (+this.insureObj.discountCoefficientRate < 0) {
          this.insureObj.discountCoefficientRate =
            +this.insureObj.discountCoefficientRate + 1;
        }
        if (+this.insureObj.ncdRate < 0) {
          this.insureObj.ncdRate = +this.insureObj.ncdRate + 1;
        }
        if (+this.insureObj.trafficViolationRate < 0) {
          this.insureObj.trafficViolationRate =
            +this.insureObj.trafficViolationRate + 1;
        }
      }
      if (this.detailed.trafficInfoPlatform == null) {
        this.compulsoryObj = {};
      } else {
        this.compulsoryObj = this.detailed.trafficInfoPlatform;
        if (+this.compulsoryObj.trafficViolationRate < 0) {
          this.compulsoryObj.trafficViolationRate =
            +this.compulsoryObj.trafficViolationRate + 1;
        }
        if (+this.compulsoryObj.claimRate < 0) {
          this.compulsoryObj.claimRate = +this.compulsoryObj.claimRate + 1;
        }
        if (+this.compulsoryObj.claimRate < 0) {
          this.compulsoryObj.claimRate = +this.compulsoryObj.claimRate + 1;
        }
      }
    });
  },
  methods: {
    //返回上一页
    goBack() {
      this.$globalBack();
    },
    regardlessSelect(index) {
      this.print = index;
    }
  }
};
</script>
<style scoped lang="less">
.popBody {
  background: #ffffff;
  left: 0;
  bottom: 0;
  top: 0;
  position: absolute;
  width: 100%;
  margin-right: 150px;
  padding-top: 16px;
  // .van-tabs {
  //   width: 240px;
  // }
}


.van-icon {
  z-index: 9;
  top: 14px;
  left: 18px;
  font-size: 18px;
  position: fixed;
}
.popBody1 {
  .img {
    width: 100px;
    height: 57px;
    position: absolute;
    right: 15px;
    top: 15px;
  }
  margin-top: 64px;
  margin-right: 150px;
  // background: url("../../../../assets/order/orderList/headers.png") no-repeat;
  // background: url("../../../../assets/common/backCar.png") no-repeat;
  // background-size: 375px 60px;
}
.whetherBack {
  display: inline-block;
  width: 60px;
  padding-top: 15px;
  // height: 75px;
  // line-height: 75px;
  text-align: center;
  color: #666666;
  border-radius: 5px;
  margin-left: 10px;
  font-size: 14px;
}
.active {
  font-size: 17px;
  font-weight: 500;
  color: rgba(0, 0, 0, 1);
}
.line {
  width: 28px;
  height: 5px;
  background: rgba(59, 138, 254, 1);
  border-radius: 3px;
  display: none;
  margin: 10px auto;
}
.backgroundLine {
  display: block;
}
</style>
