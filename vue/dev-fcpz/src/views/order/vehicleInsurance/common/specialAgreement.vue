<template>
  <div>
    <popNav popTitle="特别约定" />
    <div class="popBody">
      <div class="backImg">
        <div class="title">特别约定</div>
      </div>
      <div class="rowQuTitle" v-show="Array.isArray(specialAgreementListVal) && specialAgreementListVal.length">
        <div>商业险特约名称</div>
        <div>特约内容</div>
      </div>
      <div v-for="(item, index) in specialAgreementListVal" :key="'business' + index">
        <div class="rowQu1">
          <div>
            <div class="width_60">{{ item.specialAgreementName }}</div>
            <van-button v-if="item.specialAgreementCode == 'A00193'" @click="incrementPopup" round type="info" size="small">增值服务</van-button>
          </div>
          <div>{{ item.specialAgreementContent }}</div>
        </div>
      </div>
      <div class="rowQuTitle" v-show="Array.isArray(trafficSpecialAgreementVal) && trafficSpecialAgreementVal.length">
        <div>交强险特约名称</div>
        <div>特约内容</div>
      </div>
      <div v-for="(item, index) in trafficSpecialAgreementVal" :key="'traffic' + index">
        <div class="rowQu1">
          <div>
            <div>{{ item.specialAgreementName }}</div>
            <van-button v-if="item.specialAgreementCode == 'A00193'" @click="incrementPopup1" round type="info" size="small">增值服务</van-button>
          </div>
          <div>{{ item.specialAgreementContent }}</div>
        </div>
      </div>
    </div>
    <van-popup v-model="incrementshowPopup">
      <div class="containerPopup">
        <img class="img" @click="closeImg" src="../../../../assets/order/orderList/close.png" alt />
        <div class="container" v-for="(item, index) in specialAgreementListVal" :key="index">
          <div v-show="specialAgreementListVal != []" class="agreementDetail" v-for="(itm, i) in item.agreementDetail" :key="i">
            <div>{{ itm.serviceTypeName }}</div>
            <div>{{ itm.serviceContent }}</div>
          </div>
        </div>
      </div>
    </van-popup>
    <van-popup v-model="incrementshowPopup1">
      <div class="containerPopup">
        <img class="img" @click="closeImg" src="../../../../assets/order/orderList/close.png" alt />
        <div class="container" v-for="(item, index) in trafficSpecialAgreementVal" :key="index">
          <div v-show="trafficSpecialAgreementVal != []" class="agreementDetail" v-for="(itm, i) in item.agreementDetail" :key="i">
            <div>{{ itm.serviceTypeName }}</div>
            <div>{{ itm.serviceContent }}</div>
          </div>
        </div>
      </div>
    </van-popup>
  </div>
</template>
<script>
import popNav from "@/components/popNav.vue";

export default {
  components: {
    popNav,
  },
  data() {
    return {
      specialAgreementListVal: [],
      trafficSpecialAgreementVal: [],
      detailed: {
        vehicleTaxVo: {},
      },
      taxFlagSHs: "",
      incrementshowPopup1: false,
      incrementshowPopup: false,
    };
  },
  activated() {
    this.$nextTick(() => {
      this.detailed = this.$route.params;
      if (this.detailed.businessSpecialAgreement == null) {
        this.specialAgreementListVal = [];
      } else {
        this.specialAgreementListVal = this.detailed.businessSpecialAgreement.specialAgreementList;
      }
      if (this.detailed.trafficSpecialAgreement == null) {
        this.trafficSpecialAgreementVal = [];
      } else {
        this.trafficSpecialAgreementVal = this.detailed.trafficSpecialAgreement.specialAgreementList;
      }
      this.$forceUpdate();
      console.log(this.specialAgreementListVal.length, this.trafficSpecialAgreementVal.length);
    });
  },
  methods: {
    //返回上一页
    goBack() {
      this.$globalBack();
    },
    incrementPopup() {
      this.incrementshowPopup = true;
    },
    incrementPopup1() {
      this.incrementshowPopup1 = true;
    },
    closeImg() {
      this.incrementshowPopup = false;
      this.incrementshowPopup1 = false;
    },
  },
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
    width: 100%;
    height: 10px;
    background: rgba(245, 245, 245, 1);
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
.rowQu1 {
  display: flex;
  background-color: #fff;
  padding: 0.2rem;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    flex: 1;
  }
}
.rowQuTitle {
  display: flex;
  background-color: #ccc;
  flex-direction: row;
  justify-content: space-between;
  padding: 0 0.2rem;
  div {
    // text-align: center;
    // flex: 1;
    font-size: 14px;
    line-height: 40px;
  }
}
.backImg {
  height: 68px;
  background: url("../../../../assets/common/backCar.png") no-repeat;
  background-size: 100% 68px;
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
.containerPopup {
  position: relative;
  padding: 20px;
  .img {
    position: absolute;
    right: 8px;
    top: 8px;
  }
  .container {
    .agreementDetail {
      display: flex;
      width: 260px;
      div {
        flex: 1;
        margin-bottom: 10px;
      }
    }
  }
}
</style>
