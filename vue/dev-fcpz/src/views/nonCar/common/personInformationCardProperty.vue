/* zhangminjie 财产险--公共组件-保单信息卡片 */
<template>
  <div id="person-information-card-property" class="personCard">
    <van-row type="flex" align="center" class="title">
      <van-col class="title-icon"><img :src="imgSrc"/></van-col>
      <van-col class="title-text">{{ title }}</van-col>
    </van-row>
    <van-cell-group :border="false">
      <van-cell
        v-for="item in items"
        :key="item.title"
        :border="false"
        :title="item.title"
        :value="item.value"
        center
        class="information-item"
        title-class="information-item-text"
        value-class="information-item-text"
      >
      </van-cell>
    </van-cell-group>
  </div>
</template>

<script>
const policyHolder = [
  { key: "appliName", title: "姓名", value: "1" },
  { key: "appliIdentifyType", title: "证件类型", value: "1" },
  { key: "appliIdentifyNumber", title: "证件号码", value: "222" },
  { key: "appliMobile", title: "手机号", value: "" },
  { key: "appliEMail", title: "邮箱", value: "" }
];
const recognizee = [
  { key: "appliIdentity", title: "是否同投保人", value: "1" },
  { key: "insuredName", title: "姓名", value: "1" },
  { key: "insuredIdentifyType", title: "证件类型", value: "1" },
  { key: "insuredIdentifyNumber", title: "证件号码", value: "" },
  { key: "insuredMobile", title: "手机号", value: "" }
];
const vehicle = [
  { key: "vehicleModelCode", title: "车型", value: "" },
  { key: "licenseNo", title: "车牌号", value: "" },
  { key: "engineNo", title: "发动机号", value: "" },
  { key: "approvalQuality", title: "核定载重量/总牵引质量(吨)", value: "" },
  { key: "vehicleAge", title: "车龄", value: "" },
  { key: "brandModelName", title: "厂牌型号", value: "" }
];
const invoiceInfo = [
  { key: "companyName", title: "发票抬头", value: "" },
  { key: "taxPayerNo", title: "纳税人识别号", value: "" },
  { key: "mobile", title: "联系电话", value: "" },
  { key: "email", title: "邮箱地址", value: "" }
];
export default {
  name: "person-information-card-property",
  props: {
    type: String,
    imgSrc: String
  },
  data: () => ({
    title: "",
    items: []
  }),
  created() {
    let insuranceInfo = this.$store.state.nonCar.shareData.insuranceInfo;
    let orderObj = JSON.parse(JSON.stringify(insuranceInfo));
    for (let key in orderObj) {
      if (key == "appliIdentity") {
        orderObj[key] = orderObj[key] == "01" ? "是" : "否";
      }
      if (key == "appliIdentifyType") {
        let val = this.$getCodeOrName("hyIdCardList", orderObj[key]);
        orderObj[key] = val ? val : "";
      }
      if (key == "insuredIdentifyType") {
        let val = this.$getCodeOrName("hyIdCardList", orderObj[key]);
        orderObj[key] = val ? val : "";
      }
      if (key == "vehicleModelCode") {
        let val = this.$getCodeOrName("vehicleModelCodeList", orderObj[key]);
        orderObj[key] = val ? val : "";
      }
    }
    switch (this.type) {
      case "policyHolder":
        this.title = "投保人信息";
        this.items = policyHolder.map(item => ({
          ...item,
          value: orderObj[item.key] || ""
        }));
        break;
      case "recognizee":
        this.title = "被保人信息";
        this.items = recognizee.map(item => ({
          ...item,
          value: orderObj[item.key] || ""
        }));
        break;
      case "vehicle":
        this.title = "车辆信息";
        this.items = vehicle.map(item => ({
          ...item,
          value: orderObj[item.key] || ""
        }));
        break;
      case "invoiceInfo":
        this.title = "开票信息";
        this.items = invoiceInfo.map(item => ({
          ...item,
          value: orderObj.invoiceInfo[item.key] || ""
        }));
        break;
      default:
        break;
    }
  },
  methods: {}
};
</script>

<style lang="less" scoped>
#person-information-card-property {
  background: white;
  padding: 0px 16px;
  margin-bottom: 10px;
  .van-cell__value {
    width: 70%;
    flex: none;
  }
  .van-cell__title {
    width: 30%;
    flex: none;
  }
}
.title {
  height: 48px;
}
.title-icon {
  width: 22px;
  height: 22px;
  // background: #3b8afe;
  border-radius: 3px;
  margin-right: 8px;
  img {
    width: 100%;
    height: auto;
  }
}
.title-text {
  font-size: 16px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  line-height: 20px;
}
.information-item {
  height: auto;
  padding: 0px;
}

.vehicleInfo {
  padding: 9px 14px;
  font-size: 13px;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  line-height: 21px;
  background: rgba(250, 250, 250, 1);
}
.vehicleInfo:first-child {
  margin-top: 7px;
}
.information-item-text {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #666666;
  line-height: 30px;
}
</style>
