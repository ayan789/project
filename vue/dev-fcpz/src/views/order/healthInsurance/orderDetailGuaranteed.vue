/* zhangminjie 订单详情--保障中 --生效*/
<template>
  <div
    class="orderDetailGuaranteed"
    id="orderDetailGuaranteed"
  >
    <orderDetailNav
      statusName="保障中"
      @goBackEvent="goBack"
    ></orderDetailNav>
    <orderDetailTopUnusedAndGuaranteed
      :insuranceInfo="insuranceInfo"
      :orderStauts="orderStauts"
      :insuranceType="insuranceType"
    ></orderDetailTopUnusedAndGuaranteed>
    <orderDetailMidContentNew
      :guaranteedCollageContent="formulaList"
      :insuranceCost="insuranceCost"
      :person="person"
      :personList="personList"
      :insuranceType="insuranceType"
      @redirectMoreInfoPageEvent="redirectMoreInfoPage"
    ></orderDetailMidContentNew>
    <orderDetailStatementUnusedYijian
      :orderStauts="orderStauts"
      :productCode="productCode"
      :isSheBao="isSheBao"
      :insuranceType="insuranceType"
      @copyInsuranceEvent="copyInsurance"
    ></orderDetailStatementUnusedYijian>
  </div>
</template>

<script>
import Vue from "vue";
import { Card, Button } from "vant";
import orderDetailMidContentNew from "../common/orderDetailMidContentNew.vue";
import orderDetailTopUnusedAndGuaranteed from "../common/orderDetailTopUnusedAndGuaranteed.vue";
import orderDetailStatementUnusedYijian from "../common/orderDetailStatementUnusedYijian.vue";
import orderDetailNav from "../common/orderDetailNav.vue";
import {
  storeOrderDetail,
  forwardPayProductInsureInfo
} from "@src/http/module/order.js";
import { mapMutations, mapGetters } from "vuex";

import { queryZc } from "@src/http/module/nonCar.js";

Vue.use(Card);
Vue.use(Button);
export default {
  name: "orderDetail",
  components: {
    orderDetailMidContentNew,
    orderDetailTopUnusedAndGuaranteed,
    orderDetailStatementUnusedYijian,
    orderDetailNav
  },
  data: () => ({
    orderNo: "",
    content: "",
    familyData: {},
    orderInfoObj: {},
    orderStauts: 7, //保障中
    insuranceType: 2, //1 财产险 2 健康险
    btnName: "继续录入",
    formulaList: [],
    person: {
      applicantName: "",
      appliIdentify: "",
      insuredName: "",
      insuredIdentify: "",
      relateInsured: ""
    },
    personList: [],
    insuranceCost: [
      {
        desc: "",
        price: 0,
        type: 1
      },
      {
        desc: "应付保费",
        price: 0,
        type: 2
      }
    ],
    insuranceInfo: {
      insureNo: "",
      desc: "",
      startDate: "",
      endDate: "",
      policyNo: ""
    },
    insureNo: "",
    insurePersonInfo: {},
    insurePersonInfoList: [], //被保人list
    productCode: "FM50", //区分个人版和家庭版  FM50:个人版,FM51:家庭版
    isSheBao: "Y"

  }),
  created () {
    this.getOrderInfo();
  },
  computed: {
    ...mapGetters("million", ["tenMillionData", "newData"])
  },
  methods: {
    // 复制投保单
    copyInsurance () {
      queryZc({ orderNo: this.orderNo }).then(res => {
        if (res.code == "1" && res.data) {
          if (this.orderLength > 1) {
            this.$router.push({
              name: "writeForm",
              params: {
                back: JSON.stringify("3"),
                item1: JSON.stringify(res.data)
              }
            });
          } else if (this.productCode == "FM50") {
            this.cleTenMillionData();
            let tS = this.tenMillionData;
            if (res.data.storeOracleRequestVO) {
              let copy = res.data.storeOracleRequestVO;
              for (let k in tS) {
                tS[k] = copy[k];
              }
              this.setTenMillionData(tS);
              let {
                premiumsJson,
                extensionJson,
                aKZEXClause,
                aEXTZClause,
                aEXYLClause,
                aEXZZClause
              } = copy;
              let formula = { premiumsJson, extensionJson };
              let result = [];
              if (aEXYLClause == "Y") {
                result.push("恶性肿瘤赴日医疗责任");
              }
              if (aEXZZClause == "Y") {
                result.push("恶性肿瘤扩展质子重离子医疗责任");
              }
              if (aKZEXClause == "Y") {
                result.push(
                  "扩展恶性肿瘤特需医疗责任(与重大疾病医疗保险责任共用保额)"
                );
              }
              if (aEXTZClause == "Y") {
                result.push("恶性肿瘤院外特种药品费用医疗责任");
              }
              this.setFormula({ formula, result });
              this.$router.push({
                name: "millionMedicalCare"
              });
            }
          } else if (this.productCode == "WTI01") {
            this.cleNewData()
            let tS = this.newData;
            if (res.data.storeOracleRequestVO) {
              let copy = res.data.storeOracleRequestVO;
              for (let k in tS) {
                tS[k] = copy[k];
              }
              tS.placeInfo = copy['addressInfo'].addressName
              this.setNewData(tS)
              this.$router.push({
                name: "stopCancer"
              });
            }

          }
        }
      });
    },

    // 订单转发支付查询信息
    async forwardPayProductInsureInfoQuery () {
      let time = this.formatTime(new Date());
      console.log(time);
      let obj = {
        forwardDate: time,
        proposalNo: this.insureNo,
        status: "1"
      };
      return await forwardPayProductInsureInfo(obj);
    },
    // 跳转到更多信息
    redirectMoreInfoPage () {
      this.$router.push({
        name: "orderMoreInformation",
        query: {
          insuranceType: this.insuranceType,
          insurePersonInfo: this.insurePersonInfo,
          insurePersonInfoList: this.insurePersonInfoList //被保人list
        }
      });
    },
    //返回上一页
    goBack () {
      this.$router.push({
        path: "/order/orderList/orderList?moudle=0&status=3"
      });
      // this.$globalBack();
    },
    // 获取订单详情
    getOrderInfo () {
      // console.log(res);
      this.OrderDetail()
        .then(res => {
          if (res.code == "1" && res.data) {
            this.orderNo = res.data.orderNo;
            this.orderLength = res.data.accidentalInjuryInfos.length;
            this.insuranceInfo = {
              insureNo: res.data.insureNo,
              desc: res.data.formulaName,
              startDate: res.data.startDate
                ? res.data.startDate.split(" ")[0]
                : "",
              endDate: res.data.endDate ? res.data.endDate.split(" ")[0] : "",
              policyNo: res.data.policyNo
            };
            localStorage.setItem(
              "requestPlaceOrderData",
              JSON.stringify(res.data)
            );

            this.formulaList.splice(0, this.formulaList.length);
            this.content = res.data.content;
            let formulasArr = res.data.formulas;
            if (res.data.formulas.length > 0) {
              if (formulasArr[0].amountInfoJson) {
                let addRiskAmountJson = JSON.parse(
                  formulasArr[0].amountInfoJson
                );
                let arr = [];
                if (formulasArr[0].productCode == "FM50") {
                  for (let [k, v] of Object.entries(addRiskAmountJson)) {
                    if (parseInt(k.substr(0, k.indexOf("#"))) <= 16) {
                      k = k.slice(3, k.length);
                      v = v / 10000;
                      arr.push({ name: k, value: v });
                    } else {
                      if (
                        this.content.aKZEXClause == "Y" &&
                        parseInt(k.substr(0, k.indexOf("#"))) == 17
                      ) {
                        k = k.slice(3, k.length);
                        v = v / 10000;
                        arr.push({ name: k, value: v });
                      }
                      if (
                        this.content.aEXTZClause == "Y" &&
                        parseInt(k.substr(0, k.indexOf("#"))) == 18
                      ) {
                        k = k.slice(3, k.length);
                        v = v / 10000;
                        arr.push({ name: k, value: v });
                      }
                      if (
                        this.content.aEXYLClause == "Y" &&
                        parseInt(k.substr(0, k.indexOf("#"))) == 19
                      ) {
                        k = k.slice(3, k.length);
                        v = v / 10000;
                        arr.push({ name: k, value: v });
                      }
                      if (
                        this.content.aEXZZClause == "Y" &&
                        parseInt(k.substr(0, k.indexOf("#"))) == 20
                      ) {
                        k = k.slice(3, k.length);
                        v = v / 10000;
                        arr.push({ name: k, value: v });
                      }
                    }
                  }
                } else {
                  for (let [k, v] of Object.entries(addRiskAmountJson)) {
                    if (parseInt(k.substr(0, k.indexOf("#"))) <= 17) {
                      k = k.slice(3, k.length);
                      v = v / 10000;
                      arr.push({ name: k, value: v });
                    } else {
                      if (
                        this.content.aKZEXClause == "Y" &&
                        parseInt(k.substr(0, k.indexOf("#"))) == 18
                      ) {
                        k = k.slice(3, k.length);
                        v = v / 10000;
                        arr.push({ name: k, value: v });
                      }
                      if (
                        this.content.aEXTZClause == "Y" &&
                        parseInt(k.substr(0, k.indexOf("#"))) == 19
                      ) {
                        k = k.slice(3, k.length);
                        v = v / 10000;
                        arr.push({ name: k, value: v });
                      }
                      if (
                        this.content.aEXYLClause == "Y" &&
                        parseInt(k.substr(0, k.indexOf("#"))) == 20
                      ) {
                        k = k.slice(3, k.length);
                        v = v / 10000;
                        arr.push({ name: k, value: v });
                      }
                      if (
                        this.content.aEXZZClause == "Y" &&
                        parseInt(k.substr(0, k.indexOf("#"))) == 21
                      ) {
                        k = k.slice(3, k.length);
                        v = v / 10000;
                        arr.push({ name: k, value: v });
                      }
                    }
                  }
                }
                this.formulaList = arr;
              }
            }
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
              insuredBirthDate,
              relateInsured,
              insuredSheBaoType,
              invoiceInfo,
              accidentalInjuryInfos,
              productCode,
              isSheBao
            } = res.data;
            this.productCode = productCode;
            this.isSheBao = isSheBao;
            let relate = this.$getCodeOrName("bwRelateInsured", relateInsured);
            relateInsured = relate ? relate : "";

            this.insurePersonInfoList.splice(
              0,
              this.insurePersonInfoList.length
            );
            this.personList.splice(0, this.personList.length);
            // 家庭版
            if (productCode == "FM51") {
              this.insurePersonInfo.accidentalInjuryInfos = [];
              this.insurePersonInfo = {
                applicantName,
                appliIdentify,
                appliIdentifyType,
                applicantBirthDate,
                mobileNo,
                email,
                accidentalInjuryInfos,
                invoiceInfo
              };
              this.insurePersonInfoList.push(
                ...this.insurePersonInfo.accidentalInjuryInfos
              );
              if (this.insurePersonInfo.accidentalInjuryInfos.length > 0) {
                for (let k of this.insurePersonInfo.accidentalInjuryInfos) {
                  let { familyRelation, familyName, familyIdentifyNo } = k;
                  let relate = this.$getCodeOrName(
                    "bwRelateInsured",
                    familyRelation
                  );
                  let relateInsured = relate ? relate : "";
                  k.relateInsured = relateInsured;
                  k.insuredName = familyName;
                  k.insuredIdentify = familyIdentifyNo;
                }
              }
              this.personList.push(
                ...this.insurePersonInfo.accidentalInjuryInfos
              );
              this.person = {
                applicantName,
                appliIdentify
              };
            } else {
              this.insurePersonInfo = {
                applicantName,
                appliIdentify,
                appliIdentifyType,
                applicantBirthDate,
                mobileNo,
                email,
                insuredName,
                insuredIdentify,
                insuredIdentifyType,
                insuredMobile,
                relateInsured, //投被保人关系
                invoiceInfo,
                insuredBirthDate,
                insuredSheBaoType
              };
              let person = {
                insuredName,
                insuredIdentify,
                insuredIdentifyType,
                insuredMobile,
                relateInsured, //投被保人关系
                insuredBirthDate
              };
              let relate1 = this.$getCodeOrName(
                "bwRelateInsured",
                relateInsured
              );
              relateInsured = relate1 ? relate1 : "";
              this.personList.push(person);
              this.person = {
                applicantName,
                appliIdentify,
                insuredName,
                insuredIdentify,
                relateInsured
              };
            }
            this.insurePersonInfo.invoiceInfo = {};
            if (
              !this.$isNull(res.data.invoiceInfo) &&
              Object.keys(res.data.invoiceInfo).length > 0
            ) {
              for (let key in res.data.invoiceInfo) {
                this.insurePersonInfo.invoiceInfo[key] =
                  res.data.invoiceInfo[key];
              }
            }
            this.insuranceCost[0].desc = res.data.insuranceName;
            this.insuranceCost[0].price = res.data.sumPremium;
            this.insuranceCost[1].price = res.data.sumPremium;
            this.orderInfo = {
              insureNo: res.data.insureNo,
              payApplyNo: res.data.payApplyNo,
              identityCode: res.data.identityCode,
              sumPremium: res.data.sumPremium,
              startDate: res.data.startDate,
              intervalDate: res.data.intervalDate,
              productName: res.data.insuranceName,
              applicantName,
              mobileNo,
              insuredName,
              insuredMobile
            };
          }
        })
        .catch(() => { });
    },
    // // 获取订单详情
    // getOrderInfo() {
    //   // console.log(res);
    //   this.OrderDetail()
    //     .then(res => {
    //       if (res.code == "1" && res.data) {
    //         this.insuranceInfo = {
    //           insureNo: res.data.insureNo,
    //           desc: res.data.formulaName,
    //           startDate: res.data.startDate
    //             ? res.data.startDate.split(" ")[0]
    //             : "",
    //           endDate: res.data.endDate ? res.data.endDate.split(" ")[0] : "",
    //           policyNo: res.data.policyNo
    //         };
    //         localStorage.setItem(
    //           "requestPlaceOrderData",
    //           JSON.stringify(res.data)
    //         );

    //         this.formulaList.splice(0, this.formulaList.length);
    //         let formulasArr = res.data.formulas;
    //         if (res.data.formulas.length > 0) {
    //           if (formulasArr[0].amountInfoJson) {
    //             let addRiskAmountJson = JSON.parse(
    //               formulasArr[0].amountInfoJson
    //             );
    //             let arr = [];
    //             for (let [k, v] of Object.entries(addRiskAmountJson)) {
    //               if (k.indexOf("#") >= 0) {
    //                 k = k.slice(3, k.length);
    //               }
    //               v = v / 10000;
    //               arr.push({ name: k, value: v });
    //             }
    //             this.formulaList = arr;
    //           }
    //         }
    //         let {
    //           applicantName,
    //           appliIdentify,
    //           appliIdentifyType,
    //           applicantBirthDate,
    //           email,
    //           mobileNo,
    //           insuredName,
    //           insuredIdentify,
    //           insuredIdentifyType,
    //           insuredMobile,
    //           insuredBirthDate,
    //           relateInsured,
    //           insuredSheBaoType,
    //           invoiceInfo,
    //           accidentalInjuryInfos,
    //           productCode
    //         } = res.data;
    //         this.productCode = productCode;
    //         let relate = this.$getCodeOrName("bwRelateInsured", relateInsured);
    //         relateInsured = relate ? relate : "";
    //         this.person = {
    //           applicantName,
    //           appliIdentify,
    //           insuredName,
    //           insuredIdentify,
    //           relateInsured
    //         };
    //         this.insurePersonInfoList.splice(
    //           0,
    //           this.insurePersonInfoList.length
    //         );
    //         // 家庭版
    //         if (productCode == "FM51") {
    //           this.insurePersonInfo.accidentalInjuryInfos = [];
    //           this.insurePersonInfo = {
    //             applicantName,
    //             appliIdentify,
    //             appliIdentifyType,
    //             applicantBirthDate,
    //             mobileNo,
    //             email,
    //             accidentalInjuryInfos,
    //             invoiceInfo
    //           };
    //           this.insurePersonInfoList.push(
    //             ...this.insurePersonInfo.accidentalInjuryInfos
    //           );
    //         } else {
    //           this.insurePersonInfo = {
    //             applicantName,
    //             appliIdentify,
    //             appliIdentifyType,
    //             applicantBirthDate,
    //             mobileNo,
    //             email,
    //             insuredName,
    //             insuredIdentify,
    //             insuredIdentifyType,
    //             insuredMobile,
    //             relateInsured, //投被保人关系
    //             invoiceInfo,
    //             insuredBirthDate,
    //             insuredSheBaoType
    //           };
    //         }
    //         this.insurePersonInfo.invoiceInfo = {};
    //         if (
    //           !this.$isNull(res.data.invoiceInfo) &&
    //           Object.keys(res.data.invoiceInfo).length > 0
    //         ) {
    //           for (let key in res.data.invoiceInfo) {
    //             this.insurePersonInfo.invoiceInfo[key] =
    //               res.data.invoiceInfo[key];
    //           }
    //         }

    //         this.insuranceCost[0].desc = res.data.insuranceName;
    //         this.insuranceCost[0].price = res.data.sumPremium;
    //         this.insuranceCost[1].price = res.data.sumPremium;
    //       }
    //       console.log(396, this.insurePersonInfoList);
    //     })
    //     .catch(() => {});
    // },
    // // 获取订单详情
    // getOrderInfo() {
    //   // console.log(res);
    //   this.OrderDetail()
    //     .then(res => {
    //       if (res.code == "1" && res.data) {
    //         this.orderNo = res.data.orderNo;

    //         this.insuranceInfo = {
    //           insureNo: res.data.insureNo,
    //           desc: res.data.formulaName,
    //           startDate: res.data.startDate
    //             ? res.data.startDate.split(" ")[0]
    //             : "",
    //           endDate: res.data.endDate ? res.data.endDate.split(" ")[0] : "",
    //           policyNo: res.data.policyNo
    //         };
    //         // this.familyData = res.data;

    //         // this.orderInfoObj = res.data;
    //         this.formulaList.splice(0, this.formulaList.length);
    //         let formulasArr = res.data.formulas;
    //         if (res.data.formulas.length > 0) {
    //           if (formulasArr[0].amountInfoJson) {
    //             let addRiskAmountJson = JSON.parse(
    //               formulasArr[0].amountInfoJson
    //             );
    //             let arr = [];
    //             for (let [k, v] of Object.entries(addRiskAmountJson)) {
    //               if (parseInt(k.substr(0, k.indexOf("#"))) <= 16) {
    //                 k = k.slice(3, k.length);
    //                 v = v / 10000;
    //                 arr.push({ name: k, value: v });
    //               } else {
    //                 if (
    //                   this.content.aKZEXClause == "Y" &&
    //                   parseInt(k.substr(0, k.indexOf("#"))) == 17
    //                 ) {
    //                   k = k.slice(3, k.length);
    //                   v = v / 10000;
    //                   arr.push({ name: k, value: v });
    //                 }
    //                 if (
    //                   this.content.aEXTZClause == "Y" &&
    //                   parseInt(k.substr(0, k.indexOf("#"))) == 18
    //                 ) {
    //                   k = k.slice(3, k.length);
    //                   v = v / 10000;
    //                   arr.push({ name: k, value: v });
    //                 }
    //                 if (
    //                   this.content.aEXYLClause == "Y" &&
    //                   parseInt(k.substr(0, k.indexOf("#"))) == 19
    //                 ) {
    //                   k = k.slice(3, k.length);
    //                   v = v / 10000;
    //                   arr.push({ name: k, value: v });
    //                 }
    //                 if (
    //                   this.content.aEXZZClause == "Y" &&
    //                   parseInt(k.substr(0, k.indexOf("#"))) == 20
    //                 ) {
    //                   k = k.slice(3, k.length);
    //                   v = v / 10000;
    //                   arr.push({ name: k, value: v });
    //                 }
    //               }
    //             }
    //             this.formulaList = arr;
    //           }
    //         }
    //         let {
    //           applicantName,
    //           appliIdentify,
    //           appliIdentifyType,
    //           mobileNo,
    //           insuredName,
    //           insuredIdentify,
    //           insuredIdentifyType,
    //           insuredMobile,
    //           relateInsured,
    //           emial
    //         } = res.data;
    //         console.log(
    //           "返回被保人信息",
    //           JSON.stringify(res.data.accidentalInjuryInfos)
    //         );
    //         let relate = this.$getCodeOrName("bwRelateInsured", relateInsured);
    //         relateInsured = relate ? relate : "";
    //         if (!this.$isNull(res.data.accidentalInjuryInfos)) {
    //           for (let key in res.data.accidentalInjuryInfos) {
    //             let personObj = {
    //               applicantName: res.data.applicantName,
    //               appliIdentify: res.data.appliIdentify,
    //               insuredName: res.data.accidentalInjuryInfos[key].familyName,
    //               insuredIdentify:
    //                 res.data.accidentalInjuryInfos[key].familyIdentifyNo,
    //               relateInsured: this.$getCodeOrName(
    //                 "bwRelateInsured",
    //                 res.data.accidentalInjuryInfos[key].familyRelation
    //               )
    //                 ? this.$getCodeOrName(
    //                     "bwRelateInsured",
    //                     res.data.accidentalInjuryInfos[key].familyRelation
    //                   )
    //                 : ""
    //             };
    //             this.personList.push(personObj);
    //             let insuranceObj = {
    //               applicantName: res.data.applicantName,
    //               appliIdentify: res.data.appliIdentify,
    //               appliIdentifyType: res.data.appliIdentifyType,
    //               mobileNo: res.data.mobileNo,
    //               emial: res.data.emial,
    //               insuredName: res.data.accidentalInjuryInfos[key].familyName,
    //               insuredIdentify:
    //                 res.data.accidentalInjuryInfos[key].familyIdentifyNo,
    //               relateInsured: this.$getCodeOrName(
    //                 "bwRelateInsured",
    //                 res.data.accidentalInjuryInfos[key].familyRelation
    //               )
    //                 ? this.$getCodeOrName(
    //                     "bwRelateInsured",
    //                     res.data.accidentalInjuryInfos[key].familyRelation
    //                   )
    //                 : "",
    //               insuredIdentifyType:
    //                 res.data.accidentalInjuryInfos[key].familyIdentifyType, //新增
    //               insuredMobile: res.data.accidentalInjuryInfos[key].mobile,
    //               insuredBirthDate:
    //                 res.data.accidentalInjuryInfos[key].insuredBirthDate,
    //               insuredSheBaoType:
    //                 res.data.accidentalInjuryInfos[key].insuredSheBaoType
    //             };
    //             //todo suncao
    //             this.insurePersonInfoList.push(insuranceObj);
    //           }
    //           this.insurePersonInfo = this.insurePersonInfoList[0];
    //         } else {
    //           this.person = {
    //             applicantName,
    //             appliIdentify,
    //             insuredName,
    //             insuredIdentify,
    //             relateInsured
    //           };
    //           this.insurePersonInfo = {
    //             applicantName,
    //             appliIdentify,
    //             appliIdentifyType,
    //             mobileNo,
    //             emial,
    //             insuredName,
    //             insuredIdentify,
    //             insuredIdentifyType,
    //             insuredMobile,
    //             relateInsured //投被保人关系
    //           };
    //           this.insurePersonInfoList.push(this.insurePersonInfo);
    //         }
    //         this.insuranceCost[0].desc = res.data.insuranceName;
    //         this.insuranceCost[0].price = res.data.sumPremium;
    //         this.insuranceCost[1].price = res.data.sumPremium;
    //         this.insurePersonInfo.invoiceInfo = {};
    //         if (Object.keys(res.data.invoiceInfo).length > 0) {
    //           for (let key in res.data.invoiceInfo) {
    //             this.insurePersonInfo.invoiceInfo[key] =
    //               res.data.invoiceInfo[key];
    //           }
    //         }
    //       }
    //     })
    //     .catch(() => {});
    // },
    async OrderDetail () {
      // this.insureNo = "TYEF20530126020000000137";
      this.insureNo = this.$route.query.insureNo;
      return await storeOrderDetail({ insureNo: this.insureNo });
    },
    //格式化钱数
    formatNum (num) {
      const numStr = num.toString().split("."); //先转为字符串，再分开整数和小数部分
      let numInt = numStr[0]; // 整数部分
      let numDec = numStr.length > 1 ? "." + numStr[1] : "";
      // 小数部分，只有原数字存在小数点的时候，分割完长度才会大于1，才需要存储小数，记得前面加小数点
      let resultInt = ""; //存储整数部分处理结果
      while (numInt.length > 3) {
        //当剩余整数部分长度大于3时继续处理
        resultInt = "," + numInt.slice(-3) + resultInt; //把后三位截出来，和处理结果拼在一起，前面加逗号
        numInt = numInt.slice(0, -3); // 剩下的部分是除去刚刚截掉的3位的部分
      }
      return numInt + resultInt + numDec; //结果是“剩余的不足3位的整数”+“处理好的整数结果”+“小数部分”
    },
    // 格式化时间
    formatTime (date) {
      let year = date.getFullYear();
      let month = date.getMonth() + 1;
      let day = date.getDate();
      let hour = date.getHours();
      let minute = date.getMinutes();
      let second = date.getSeconds();
      if (month < 9) {
        month = "0" + month;
      }
      if (day < 9) {
        day = "0" + day;
      }
      if (hour < 9) {
        hour = "0" + hour;
      }
      if (minute < 9) {
        minute = "0" + minute;
      }
      if (second < 9) {
        second = "0" + second;
      }
      return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
    },
    ...mapMutations("million", {
      setTenMillionData: "SETTENMILLIONDATA",
      setNewData: "SETNEWDATA",
      setFormula: "SETFORMULA",
      cleTenMillionData: "CLETENMILLIONDATA",
      cleNewData: "CLENEWDATA"

    })
  }
};
</script>

<style lang="less" scoped>
#orderDetailGuaranteed {
  width: 100%;
  // height: calc(100vh - 100px);
  background: rgba(247, 247, 247, 1);
  // overflow: scroll;
  height: auto;
  padding: 95px 0;
}
</style>
