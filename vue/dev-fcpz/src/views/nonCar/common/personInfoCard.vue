<!--
 * @Description: 公共组件--转发支付确认页面--保单的基本信息
 * @Date: 2020-04-06 17:52:48
 * @Author: zhangminjie
 * @LastEditTime: 2020-04-06 22:19:36
 * @FilePath: \vue-sales\src\views\nonCar\common\personInfoCard.vue
 -->
<template>
  <div id="person-information-card">
    <van-cell-group :border="false" class="info">
      <van-cell
        v-for="item in items.slice(0, 1)"
        class="title"
        :key="item.title"
        :border="false"
        :title="item.title"
        :value="item.value"
        value-class="textStyle"
        :icon="item.icon"
      />
      <van-cell
        v-for="item in items.slice(1)"
        :key="item.title"
        :border="false"
        :title="item.title"
        :value="item.value"
        value-class="textStyle"
      />
    </van-cell-group>
  </div>
</template>

<script>
export default {
  name: "person-information-card",
  props: {
    type: String,
    insuranceType: Number,
    insurePersonInfo: Object,
  },
  data: () => ({
    items: [],
  }),
  created() {
    this.initData();
  },
  methods: {
    initData() {
      let policyHolder = []; //投保人信息
      let recognizee = []; // 被保人信息
      let invoiceInfo = []; // 发票信息
      let carInfo = []; //运输工具
      let guaranteeContent = []; //保障内容
      // 财产险
      if (this.insuranceType == 1) {
        let identifyType = [
          { id: "111", text: "身份证", type: "01" },
          { id: "801", text: "组织机构代码证", type: "07" },
          { id: "810", text: "三证合一代码", type: "21" },
          { id: "28", text: "港澳台居民居住证", type: "28" },
        ];

        if (this.insurePersonInfo) {
          let {
            applicantName,
            appliIdentify,
            appliIdentifyType,
            insuredName,
            insuredIdentify,
            insuredIdentifyType,
          } = this.insurePersonInfo;
          let obj = identifyType.find((item) => {
            return item.id == appliIdentifyType;
          });
          appliIdentifyType = obj ? obj.text : "";
          let obj1 = identifyType.find((item) => {
            return item.id == insuredIdentifyType;
          });
          insuredIdentifyType = obj1 ? obj1.text : "";
          let companyName = "";
          let taxMobile = "";
          let taxEmail = "";
          let taxPayerNo = "";

          policyHolder = [
            {
              title: "投保人信息",
              icon: "manager-o",
              type: 1,
              key: "",
            },
            { key: "appliName", title: "姓名", value: applicantName, type: 2 },
            {
              key: "appliIdentifyNumber",
              title: "证件类型",
              value: appliIdentifyType,
              type: 2,
            },
            {
              key: "applicertificateTypeVal",
              title: "证件号码",
              value: appliIdentify,
              type: 2,
            },
          ];
          recognizee = [
            { key: "", title: "被保人信息", icon: "manager-o", type: 1 },
            { key: "insuredName", title: "姓名", value: insuredName, type: 2 },
            {
              key: "insuredcertificateTypeVal",
              title: "证件类型",
              value: insuredIdentifyType,
              type: 2,
            },
            {
              key: "insuredIdentifyNumber",
              title: "证件号码",
              value: insuredIdentify,
              type: 2,
            },
          ];
          carInfo = [
            { key: "", title: "运输工具", icon: "manager-o", type: 1 },
            {
              key: "vehicleModelVal",
              title: "车型",
              value: insuredIdentifyType,
              type: 2,
            },
            {
              key: "licenseNo",
              title: "车牌号",
              value: insuredIdentify,
              type: 2,
            },
            { key: "engineNo", title: "发动机号", value: insuredName, type: 2 },
            {
              key: "approvalQuality",
              title: "核定载重量/总牵引质量(吨)",
              value: insuredIdentifyType,
              type: 1,
            },
            {
              key: "vehicleAge",
              title: "车龄",
              value: insuredIdentify,
              type: 2,
            },
            {
              key: "brandModelName",
              title: "厂牌型号",
              value: insuredIdentify,
              type: 2,
            },
          ];
          guaranteeContent = [
            { key: "", title: "保障内容", icon: "manager-o", type: 1 },
            {
              key: "",
              title: "货物运输车辆停运损失补偿金",
              value: companyName,
              type: 2,
            },
            { key: "", title: "车内装潢损失", value: taxMobile, type: 2 },
            { key: "", title: "车内财产损失", value: taxEmail, type: 2 },
          ];
          invoiceInfo = [
            { key: "", title: "开票信息", icon: "manager-o", type: 1 },
            {
              key: "companyName",
              title: "发票抬头",
              value: companyName,
              type: 2,
            },
            {
              key: "taxPayerNo",
              title: "纳税人识别码",
              value: taxMobile,
              type: 2,
            },
            { key: "mobile", title: "联系电话", value: taxEmail, type: 2 },
            { key: "email", title: "邮箱地址", value: taxPayerNo, type: 2 },
          ];
        }
      } else {
        let identifyType = [
          { id: "01", text: "身份证", type: "01" },
          { id: "02", text: "护照", type: "02" },
          { id: "03", text: "港澳台居住证", type: "03" },
        ];
        if (this.insurePersonInfo) {
          let {
            applicantName,
            appliIdentify,
            appliIdentifyType,
            mobileNo,
            emial,
            insuredName,
            insuredIdentify,
            insuredIdentifyType,
            insuredMobile,
          } = this.insurePersonInfo;
          let obj = identifyType.find((item) => {
            return item.id == appliIdentifyType;
          });
          appliIdentifyType = obj ? obj.text : "";
          let obj1 = identifyType.find((item) => {
            return item.id == insuredIdentifyType;
          });
          insuredIdentifyType = obj1 ? obj1.text : "";
          let companyName = "";
          let taxMobile = "";
          let taxEmail = "";
          let taxPayerNo = "";
          if (
            this.insurePersonInfo.invoiceInfo &&
            Object.keys(this.insurePersonInfo.invoiceInfo.length > 0)
          ) {
            companyName = this.insurePersonInfo.invoiceInfo.companyName;
            taxMobile = this.insurePersonInfo.invoiceInfo.taxMobile;
            taxEmail = this.insurePersonInfo.invoiceInfo.taxEmail;
            taxPayerNo = this.insurePersonInfo.invoiceInfo.taxPayerNo;
          }
          policyHolder = [
            { key: "", title: "姓名", value: applicantName },
            { key: "", title: "证件类型", value: appliIdentifyType },
            { key: "", title: "证件号码", value: appliIdentify },
            { key: "", title: "出生日期", value: "" },
            { key: "", title: "手机号", value: mobileNo },
            { key: "", title: "邮箱", value: emial },
          ];
          recognizee = [
            { key: "", title: "与投保人关系", value: "1" },
            { key: "", title: "姓名", value: insuredName },
            { key: "", title: "证件类型", value: insuredIdentifyType },
            { key: "", title: "证件号码", value: insuredIdentify },
            { key: "", title: "出生日期", value: "" },
            { key: "", title: "手机号", value: insuredMobile },
            { key: "", title: "邮箱", value: "" },
            { key: "", title: "社保类型", value: "" },
          ];
          invoiceInfo = [
            { key: "", title: "发票抬头", value: companyName },
            { key: "", title: "纳税人识别码", value: taxMobile },
            { key: "", title: "联系电话", value: taxEmail },
            { key: "", title: "邮箱地址", value: taxPayerNo },
          ];
        }
      }
      switch (this.type) {
        case "recognizee":
          this.items = recognizee.map((item) => ({
            ...item,
            value: this.insurePersonInfo[item.key] || "",
          }));
          break;
        case "invoiceInfo":
          this.items = invoiceInfo.map((item) => ({
            ...item,
            value: this.insurePersonInfo[item.key] || "",
          }));
          break;
        case "policyHolder":
          this.items = policyHolder.map((item) => ({
            ...item,
            value: this.insurePersonInfo[item.key] || "",
          }));
          break;
        case "guaranteeContent":
          this.items = guaranteeContent.map((item) => ({
            ...item,
            value: this.insurePersonInfo[item.key] || "",
          }));
          break;
        case "carInfo":
          this.items = carInfo.map((item) => ({
            ...item,
            value: this.insurePersonInfo[item.key] || "",
          }));
          break;
      }
    },
  },
};
</script>

<style lang="less" scoped>
#person-information-card {
  background: white;
  // padding: 0px 15px;
  margin-bottom: 10px;
  .van-cell__value {
    width: 70%;
    flex: none;
  }
  .van-cell__title {
    width: 30%;
    flex: none;
  }
  div {
    box-sizing: border-box;
  }
  .info {
    .title {
      font-size: 14px;
      font-family: PingFangSC-Semibold, PingFang SC;
      font-weight: 600;
      color: rgba(51, 51, 51, 1);
      line-height: 20px;
      padding: 15px auto;
      .van-cell__left-icon {
        color: rgba(8, 77, 179, 1);
      }
    }
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    line-height: 30px;
    margin-bottom: 10px;
  }
  .textStyle {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
  }
}
</style>
