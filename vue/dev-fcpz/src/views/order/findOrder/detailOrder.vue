<template>
  <div id="insuranceProducts">
    <div class="title-container">
      <div class="titleHeader">
        <van-nav-bar title="保单详情" left-arrow @click-left="$router.back()" />
      </div>
      <div class="divBox">
        <div class="bg">
          <div class="divLine">
            <img class="img" src="../../../assets/order/orderList/icon1.png" />
            <span class="divLable title">基本信息</span>
          </div>
          <div class="divLine">
            <span class="divLable">投保人：</span>
            <span class="divContent">{{ getData.appliName }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">证件类型：</span>
            <span class="divContent">
              {{ $utils.findText(identifyTypes, getData.cardType) }}
            </span>
          </div>
          <div class="divLine">
            <span class="divLable">证件号码：</span>
            <span class="divContent">{{ getData.cardNo }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">手机号码：</span>
            <span class="divContent">{{ getData.mobile }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">固定电话：</span>
            <span class="divContent">{{ getData.phone }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">保单号：</span>
            <span class="divContent">{{ getData.policyNo }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">保单期限：</span>
            <span
              class="divContent"
              style="word-wrap:break-word;
       display：inline-block;width:calc(100% - 140px);"
              >{{ getData.policyTerm }}</span
            >
          </div>
          <div style="clear:both"></div>
          <div class="divLine">
            <span class="divLable">车牌号：</span>
            <span class="divContent">{{ getData.LicenseNo }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">厂牌型号：</span>
            <span class="divContent">{{ getData.model }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">是否电子投保：</span>
            <span class="divContent">{{
              getData.isElectronicInsurance == "N" ? "是" : "否"
            }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">电子投保状态：</span>
            <span class="divContent">{{
              getData.electronicInsuranceState == "N" ? "是" : "否"
            }}</span>
          </div>
        </div>
        <div class="bg">
          <div class="divLine">
            <img class="img" src="../../../assets/order/orderList/icon4.png" />
            <span class="divLable title">保费信息</span>
          </div>
          <div class="divLine">
            <span class="divLable">优惠保费合计：</span>
            <span class="divContent">{{ getData.discountSum }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">实收保费合计：</span>
            <span class="divContent">{{ getData.collectSum }}</span>
          </div>
        </div>
        <div class="bg">
          <div class="divLine">
            <img class="img" src="../../../assets/order/orderList/icon2.png" />
            <span class="divLable title">险别信息</span>
          </div>
          <div v-for="(item, index) in getData.renewalKinds" :key="index">
            <div class="divLine">
              <span class="divLable">{{ item.kindName }}：</span>
              <span class="divContent">{{ item.duePremium }}</span>
            </div>
          </div>
        </div>
        <div class="bg">
          <div class="divLine">
            <img class="img" src="../../../assets/order/orderList/icon3.png" />
            <span class="divLable title">车辆信息</span>
          </div>
          <div class="divLine">
            <span class="divLable">品牌类型：</span>
            <span class="divContent">{{ getData.LicenseNoType }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">车辆种类：</span>
            <span class="divContent">{{ getData.carType }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">使用性质：</span>
            <span class="divContent">{{ getData.vehicleUseNatureCode }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">发动机号：</span>
            <span class="divContent">{{ getData.engineNo }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">车架号：</span>
            <span class="divContent">{{ getData.vin }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">核定载客：</span>
            <span class="divContent">{{ getData.approvalSeatCount }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">核定载质量：</span>
            <span class="divContent">{{ getData.approvalQuality }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">排量：</span>
            <span class="divContent">{{ getData.displacement }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">功率：</span>
            <span class="divContent">{{ getData.power }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">整备质量：</span>
            <span class="divContent">{{ getData.vehicleQuality }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">新车购置价格：</span>
            <span class="divContent">{{ getData.newCarPurchase }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">初次登记日期：</span>
            <span class="divContent">{{
              getData.initialRegistrationDate
            }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">实际使用年数：</span>
            <span class="divContent">{{ getData.useYearNum }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">车主：</span>
            <span class="divContent">{{ getData.carName }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">行驶区域：</span>
            <span class="divContent">{{ getData.travelRegion }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">指定驾驶员：</span>
            <span class="divContent">{{ getData.appointDriver }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">经办人名称：</span>
            <span class="divContent">{{ getData.handleName }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { queryDetail } from "@src/http/module/product.js";
import Config from "@src/common/json/newCoreDataDictionary";
import { Toast } from "vant";

export default {
  data() {
    return {
      getData: "",
      // 证件类型枚举
      identifyTypes: "",
      parmasD: {
        userCode: "",
        policyNo: "",
        queryType: ""
      }
    };
  },
  computed: {
    getData1: function() {
      return JSON.parse(this.$route.query.item);
    }
  },
  mounted() {
    this.onSubmit();
    this.$nextTick(() => {
      this.identifyTypes = Object.keys(Config.identifyType).map(key => ({
        text: Config.identifyType[key],
        value: key
      }));
    })
  },
  methods: {
    async onSubmit() {
      this.parmasD.userCode = this.getData1.userCode;
      this.parmasD.policyNo = this.getData1.policyNo;
      this.parmasD.queryType = this.getData1.queryType;
      await queryDetail(this.parmasD)
        .then(res => {
          if (res.code === "1") {
            this.getData = res.data;
          } else {
            Toast({
              duration: 2000,
              message: res.msg
            });
            this.$router.back();
          }
        })
        .catch(err => {});
    }
  }
};
</script>
<style lang="less" scoped>
#insuranceProducts {
  font-family: PingFangSC-Medium, PingFang SC;
  background: rgba(247, 247, 247, 1);
  .titleHeader {
    background: linear-gradient(
      129deg,
      rgb(30, 70, 246) 0%,
      rgb(39, 154, 255) 100%
    );
    .van-nav-bar {
      background: none;
    }
    .van-nav-bar__title {
      color: #fff !important;
    }
  }
  .divBox {
    padding: 5px 0;
    background: #fff;
    font-size: 14px;
    margin-bottom: 20px;
    .divLine {
      padding: 8px 10px;
      .img {
        width: 22px;
        float: left;
        margin-right: 5px;
        margin-top: -2px;
      }
      .title {
        color: #333;
        font-weight: 550;
      }
      .divLable {
        color: #454545;
        display: inline-block;
      }
      .divContent {
        color: #7a7a7a;
        float: right;
      }
    }
    .bg {
      margin-bottom: 20px;
    }
  }
}
</style>
