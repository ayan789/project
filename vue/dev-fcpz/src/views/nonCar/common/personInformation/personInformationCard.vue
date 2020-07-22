<template>
  <div id="person-information-card">
    <van-row type="flex" align="center" class="title">
      <!-- <van-col class="title-icon"></van-col> -->
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
    data: Object,
    insurePersonInfo: Object
  },
  data: () => ({
    title: "",
    items: []
  }),
  created() {
    this.initData();
  },
  methods: {
    initData() {
      let policyHolder = [];
      let recognizee = [];
      let invoiceInfo = [];
      // console.log(44, this.insurePersonInfo);
      // 财产险
      if (this.insuranceType == 1) {
        if (this.insurePersonInfo) {
          let {
            applicantName,
            appliIdentify,
            appliIdentifyType,
            mobileNo,
            email,
            insuredName,
            insuredIdentify,
            insuredIdentifyType,
            insuredMobile,
            relateInsured
          } = this.insurePersonInfo;

          let val1 = this.$getCodeOrName("hyIdCardList", appliIdentifyType);
          appliIdentifyType = val1 ? val1 : "";
          let val = this.$getCodeOrName("hyIdCardList", insuredIdentifyType);
          insuredIdentifyType = val ? val : "";

          let companyName = "";
          let taxMobile = "";
          let taxEmail = "";
          let taxPayerNo = "";
          if (
            this.insurePersonInfo.invoiceInfo &&
            Object.keys(this.insurePersonInfo.invoiceInfo).length > 0
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
            { key: "", title: "手机号", value: mobileNo },
            { key: "", title: "邮箱", value: email }
          ];
          recognizee = [
            {
              key: "",
              title: "是否同被保人",
              value: relateInsured == "01" ? "是" : "否"
            },
            { key: "", title: "姓名", value: insuredName },
            { key: "", title: "证件类型", value: insuredIdentifyType },
            { key: "", title: "证件号码", value: insuredIdentify },
            { key: "", title: "手机号", value: insuredMobile }
          ];
          invoiceInfo = [
            { key: "", title: "发票抬头", value: companyName },
            { key: "", title: "纳税人识别码", value: taxPayerNo },
            { key: "", title: "联系电话", value: taxMobile },
            { key: "", title: "邮箱地址", value: taxEmail }
          ];
        }
      } else {
        if (this.insurePersonInfo) {
          let {
            applicantName,
            appliIdentify,
            appliIdentifyType,
            applicantBirthDate,
            email,
            mobileNo,
            insuredName,
            insuredIdentify,
            insuredIdentifyType,
            insuredMobile,
            relateInsured, //投被保人关系
            insuredBirthDate,
            insuredSheBaoType,
            familyName
          } = this.insurePersonInfo;

          let val = this.$getCodeOrName("bwIdCardList", appliIdentifyType);
          appliIdentifyType = val ? val : "";

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
            {
              key: "",
              title: "出生日期",
              value: applicantBirthDate ? applicantBirthDate.split(" ")[0] : ""
            },
            { key: "", title: "手机号", value: mobileNo },
            { key: "", title: "邮箱", value: email }
          ];
          // 意健险的家庭版
          if (familyName) {
            let {
              familyIdentifyType,
              familyIdentifyNo,
              mobile,
              familyRelation,
              insuredSheBaoType,
              insuredBirthDate
            } = this.insurePersonInfo;
            let v1 = this.$getCodeOrName("shebaoList", insuredSheBaoType);
            insuredSheBaoType = v1 ? v1 : "";
            let v2 = this.$getCodeOrName("bwIdCardList", familyIdentifyType);
            familyIdentifyType = v2 ? v2 : "";
            let relate1 = this.$getCodeOrName(
              "bwRelateInsured",
              familyRelation
            );
            familyRelation = relate1 ? relate1 : "";
            recognizee = [
              { key: "", title: "与投保人关系", value: familyRelation },
              { key: "", title: "姓名", value: familyName },
              { key: "", title: "证件类型", value: familyIdentifyType },
              { key: "", title: "证件号码", value: familyIdentifyNo },
              {
                key: "",
                title: "出生日期",
                value: insuredBirthDate ? insuredBirthDate.split(" ")[0] : ""
              },
              { key: "", title: "手机号", value: mobile },
              { key: "", title: "社保类型", value: insuredSheBaoType }
            ];
          } else {
            let shebao = this.$getCodeOrName("shebaoList", insuredSheBaoType);
            insuredSheBaoType = shebao ? shebao : "";
            let identifyType = this.$getCodeOrName(
              "bwIdCardList",
              insuredIdentifyType
            );
            insuredIdentifyType = identifyType ? identifyType : "";
            recognizee = [
              {
                key: "",
                title: "与投保人关系",
                value: relateInsured ? relateInsured : ""
              },
              { key: "", title: "姓名", value: insuredName },
              { key: "", title: "证件类型", value: insuredIdentifyType },
              { key: "", title: "证件号码", value: insuredIdentify },
              {
                key: "",
                title: "出生日期",
                value: insuredBirthDate ? insuredBirthDate.split(" ")[0] : ""
              },
              { key: "", title: "手机号", value: insuredMobile },
              { key: "", title: "社保类型", value: shebao }
            ];
          }

          invoiceInfo = [
            { key: "", title: "发票抬头", value: companyName },
            { key: "", title: "纳税人识别码", value: taxPayerNo },
            { key: "", title: "联系电话", value: taxMobile },
            { key: "", title: "邮箱地址", value: taxEmail }
          ];
        }
      }
      let data = this.data || {};
      switch (this.type) {
        case "recognizee":
          this.title = "被保人";
          this.items = recognizee.map(item => ({
            ...item,
            value: item.value || data[item.key]
          }));
          break;
        case "invoiceInfo":
          this.title = "开票信息";
          this.items = invoiceInfo.map(item => ({
            ...item,
            value: item.value || data[item.key]
          }));
          break;
        case "policyHolder":
        default:
          this.title = "投保人";
          this.items = policyHolder.map(item => ({
            ...item,
            value: item.value || data[item.key]
          }));
          break;
      }
    }
  }
};
</script>

<style lang="less" scoped>
#person-information-card {
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
  width: 11px;
  height: 16px;
  background: rgba(8, 77, 179, 1);
  margin-right: 8px;
}
.title-text {
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
  line-height: 22px;
}
.information-item {
  height: 30px;
  padding: 0px;
}
.information-item-text {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #666666;
  line-height: 30px;
}
</style>
