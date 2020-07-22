<template>
  <div>
    <popNav popTitle="车船税" />
    <div class="popBody">
      <div class="backImg">
        <div class="title">车船税</div>
      </div>
      <div class="rowQu">
        <div>纳税类型</div>
        <div
          v-if="detailed.vehicleTaxVo"
        >{{ $utils.findText(taxFlagSHs, detailed.vehicleTaxVo.taxFlag) }}</div>
      </div>
      <div class="rowQu">
        <div>纳税人识别号</div>
        <div v-if="detailed.vehicleTaxVo">{{ detailed.vehicleTaxVo.taxpayerNo }}</div>
      </div>
      <div class="rowQu">
        <div>纳税人名称</div>
        <div v-if="detailed.vehicleTaxVo">{{ detailed.vehicleTaxVo.taxpayerName }}</div>
      </div>
      <div class="rowQu">
        <div>减免税原因</div>
        <div v-if="detailed.vehicleTaxVo">{{ detailed.vehicleTaxVo.deductiondueCode }}</div>
      </div>
      <div class="rowQu">
        <div>减免税方案</div>
        <div v-if="detailed.vehicleTaxVo">{{ detailed.vehicleTaxVo.deductiondueType }}</div>
      </div>
      <div class="rowQu">
        <div>减免比例</div>
        <div v-if="detailed.vehicleTaxVo">{{ detailed.vehicleTaxVo.adjustTaxValue }}</div>
      </div>
      <div class="rowQu">
        <div>减免金额</div>
        <div v-if="detailed.vehicleTaxVo">{{ detailed.vehicleTaxVo.adjustTaxValue }}</div>
      </div>
      <div class="rowQu">
        <div>减免税凭证号</div>
        <div v-if="detailed.vehicleTaxVo">{{ detailed.vehicleTaxVo.taxCertificateNo }}</div>
      </div>
      <div class="rowQu">
        <div>税务机关</div>
        <div v-if="detailed.vehicleTaxVo">{{ detailed.vehicleTaxVo.taxDepartment }}</div>
      </div>
    </div>
  </div>
</template>
<script>
import popNav from "@/components/popNav.vue";
import Config from "@src/common/json/newCoreDataDictionary";

export default {
  components: {
    popNav
  },
  data() {
    return {
      detailed: {
        vehicleTaxVo: {}
      },
      taxFlagSHs:""
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.taxFlagSHs = Object.keys(Config.taxFlagSH).map(key => ({
        text: Config.taxFlagSH[key],
        value: key
      }));
      this.detailed.vehicleTaxVo = this.$route.params;
    });
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
  .background {
    height: 14px;
    background: #efeff4;
  }
  .card {
    background-color: #fff;
    // padding: 0.2rem;
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
.rowQu {
  position: relative;
  div {
    font-size: 14px;
  }
  .InsuranceNum,
  .applicationFormNum {
    position: absolute;
    right: 4px;
    top: 8px;
    padding: 1px 2px;
    border: 1px solid rgb(39, 154, 255);
    border-radius: 5px;
  }
  .InsuranceNum1 {
    position: absolute;
    right: 35px;
    top: 10px;
  }
  display: flex;
  background-color: #fff;
  padding: 0.2rem 15px;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    // text-align: center;
    align-content: center;
  }
  .rowQuLeft {
    flex: 1;
  }
  .rowQuRight {
    flex: 4;
    text-align: right;
  }
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
    padding-left: 15px;
  }
}
.van-icon {
  z-index: 9;
  top: 14px;
  left: 18px;
  font-size: 18px;
  position: fixed;

}
</style>
