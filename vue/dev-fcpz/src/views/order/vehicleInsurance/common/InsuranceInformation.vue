<template>
  <div>
    <popNav popTitle="险别信息" />
    <div class="popBody">
      <div class="backImg">
        <div class="title">险别信息</div>
      </div>
      <div class="rowXian rowXianTitle">
        <div>险别</div>
        <div>保额(元)</div>
        <div>保费(元)</div>
      </div>
      <div
        v-if="detailed.riskFlag == '0' || detailed.riskFlag == '2'"
        class="rowXian"
      >
        <div>交强险</div>
        <div>{{ detailed.compulsoryAmount }}</div>
        <div>{{ detailed.compulsoryPremium }}</div>
      </div>
      <div
        v-if="detailed.riskFlag == '0' || detailed.riskFlag == '1'"
        class="rowXian"
      >
        <div>商业险</div>
        <div>{{ detailed.amount }}</div>
        <div>{{ detailed.premium }}</div>
      </div>
      <div class="margin_20">
        <div
          v-for="(item, index) in responsibility"
          :key="index"
        >
          <div class="rowXian">
            <div>{{ item.kindName }}</div>
            <div>{{ amount(item) }}</div>
            <div>{{ item.premium }}</div>
          </div>
          <div
            v-if="item.kindFlag == '1'"
            class="rowXian"
          >
            <div>{{ item.kindName }}不计免赔</div>
            <div></div>
            <div>{{ item.nonDeductiblePremium }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import popNav from "@/components/popNav.vue";

export default {
  components: {
    popNav
  },
  data () {
    return {
      responsibility: [],
      detailed: {
        vehicleTaxVo: {}
      }
    };
  },
  created () {
    this.$nextTick(() => {
      this.detailed = this.$route.params;
      this.responsibility = this.detailed.vehicleKindVoList;
    });
  },
  methods: {
    //返回上一页
    goBack () {
      this.$globalBack();
    },
    amount (risk) {
      let amount = "";
      if (risk.brokenCode == "2") {
        amount += "进口";
      } else if (risk.brokenCode == "1") {
        amount += "国产";
      }
      if (risk.kindCode == "C100135") {
        //指定修理厂险
        if (risk.deductibleRate) {
          amount += risk.deductibleRate + "%";
        }
        return amount;
      } else if (risk.kindCode == "C100126") {
        //玻璃单独破碎险
        amount += "玻璃";
        return amount;
      }
      return risk.amount;
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
  .background {
    height: 14px;
    background: #efeff4;
  }
  .card {
    background-color: #fff;
    padding: 0.2rem;
    // margin-bottom: 0.3rem;
    h3 {
      padding: 0.3rem 0;
      position: relative;
      font-size: 16px;
      &::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: 0;
        right: auto;
        top: auto;
        height: 1px;
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
      padding: 0.1rem 0;
    }
  }
}
.rowXian {
  display: flex;
  background-color: #fff;
  padding: 0.2rem 15px;
  div:nth-of-type(1) {
    width: 40%;
  }
  div:nth-of-type(2) {
    width: 30%;
    text-align: center;
  }
  div:nth-of-type(3) {
    width: 30%;
    text-align: right;
  }
}
.rowXianTitle {
  padding: 0.3rem;
  background:rgba(244,244,244,1);
}
.backImg {
  height: 68px;
  background: url("../../../../assets/common/backCar.png") no-repeat;
  background-size: 100% 68px;
  .title {
    font-size: 18px;
    font-weight: 500;
    color: #000;
    line-height: 64px;
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
.margin_20 {
  padding-left: 20px;
  background: #fff;
}
.rowXian {
  font-size: 14px;
}
</style>
