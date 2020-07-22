/* zhangminjie 订单详情--已失效订单 */
<template>
  <div
    class="orderDetailUnused"
    id="orderDetailUnused"
  >
    <orderDetailNav
      statusName="已失效"
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
      :insuranceType="insuranceType"
      :productCode="productCode"
      :isSheBao="isSheBao"
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
  name: "orderDetailUnused",
  components: {
    orderDetailMidContentNew,
    orderDetailTopUnusedAndGuaranteed,
    orderDetailStatementUnusedYijian,
    orderDetailNav
  },
  data: () => ({
    productCode: "FM50", //区分个人版和家庭版  FM50:个人版,FM51:家庭版
    isSheBao: "Y",
    orderStauts: 10, //已失效
    insuranceType: 2, //1 财产险 2 健康险
    formulaList: [],
    person: {
      applicantName: "",
      appliIdentify: ""
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
    orderInfo: {},
    orderNo: "",
    orderLength: "",
    signStatus: "",
    insurePersonInfoList: [] //被保人list
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
          if (this.productCode == "FM51") {
            this.$router.push({
              name: "writeForm",
              params: {
                back: JSON.stringify("3"),
                item1: JSON.stringify(res.data),
                copy: true
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
    // 复制投保单
    // copyInsurance() {
    //   this.forwardPayProductInsureInfoQuery().then(res => {
    //     if (res.code == "1" && res.data) {
    //       let {
    //         applicantName,
    //         appliIdentify,
    //         appliIdentifyType,
    //         insuredName,
    //         insuredIdentify,
    //         insuredIdentifyType,
    //         mobileNo,
    //         insuredMobile,
    //         email,
    //         relateInsured,
    //         startDate,
    //         endDate,
    //         sumPremium,
    //         isAgent
    //       } = res.data;
    //       let {
    //         licenseplateno,
    //         vehicleBrand,
    //         engineNo,
    //         approvalQuality,
    //         vehicleModelCode,
    //         vehicleAge
    //       } = res.data.carInfoVO;
    //       let [{ formulaCode, riskCode, productCode }] = res.data.formulas;
    //       let shareData = {
    //         productInfo: {
    //           storeCode: localStorage.getItem("userInfo")
    //             ? localStorage.getItem("storeCode")
    //             : "",
    //           productCode: productCode,
    //           riskCode: riskCode,
    //           formulaCode: formulaCode, //产品code
    //           premium: sumPremium, //保费
    //           isAgent: isAgent,
    //           businessNatureName: "", //直销
    //           businessNatureCode: "", //转换
    //           formulaSize: 1, //保险份数
    //           agentInfo: {
    //             agentCode: "",
    //             agentName: "",
    //             agreementNo: "",
    //             agreementName: "",
    //             businessNature: "",
    //             businessNature2: "",
    //             handler2Code: "",
    //             handler2Name: ""
    //           }
    //         },
    //         insuranceInfo: {
    //           orderNo: "", //订单id
    //           appliName: applicantName,
    //           appliIdentifyType: appliIdentifyType, //转换
    //           appliIdentifyNumber: appliIdentify,
    //           appliMobile: mobileNo,
    //           appliEMail: email,
    //           appliIdentity: relateInsured, //转换
    //           insuredName: insuredName,
    //           insuredIdentifyType: insuredIdentifyType, //转换
    //           insuredIdentifyNumber: insuredIdentify,
    //           insuredMobile: insuredMobile,
    //           vehicleModelCode: vehicleModelCode, //转换
    //           licenseNo: licenseplateno,
    //           engineNo: engineNo,
    //           approvalQuality: approvalQuality,
    //           vehicleAge: vehicleAge,
    //           brandModelName: vehicleBrand,
    //           startDate: startDate.split(" ")[0],
    //           endDate: endDate.split(" ")[0],
    //           invoiceInfo: {
    //             accountNo: "",
    //             companyName: "", //发票抬头
    //             copyDataFromType: "", // 同投被保人
    //             customerType: "", //转换
    //             depositeBankName: "",
    //             email: "",
    //             invoiceType: "", //转换
    //             mobile: "",
    //             taxPayerNo: "",
    //             taxPayerType: "", //转换
    //             taxRegisterAddress: "",
    //             taxRegisterTel: ""
    //           }
    //         }
    //       };

    //       if (
    //         res.data.invoiceInfo &&
    //         Object.keys(res.data.invoiceInfo).length > 0
    //       ) {
    //         shareData.insuranceInfo.invoiceInfo.accountNo =
    //           res.data.invoiceInfo.accountNumber;

    //         shareData.insuranceInfo.invoiceInfo.companyName =
    //           res.data.invoiceInfo.companyName;

    //         shareData.insuranceInfo.invoiceInfo.customerType =
    //           res.data.invoiceInfo.clientType;

    //         shareData.insuranceInfo.invoiceInfo.depositeBankName =
    //           res.data.invoiceInfo.bankName;

    //         shareData.insuranceInfo.invoiceInfo.email =
    //           res.data.invoiceInfo.taxEmail;

    //         shareData.insuranceInfo.invoiceInfo.invoiceType =
    //           res.data.invoiceInfo.invoiceType;

    //         shareData.insuranceInfo.invoiceInfo.mobile =
    //           res.data.invoiceInfo.taxMobile;

    //         shareData.insuranceInfo.invoiceInfo.taxPayerNo =
    //           res.data.invoiceInfo.taxPayerNo;

    //         shareData.insuranceInfo.invoiceInfo.taxPayerType =
    //           res.data.invoiceInfo.taxPayerType;

    //         shareData.insuranceInfo.invoiceInfo.taxRegisterAddress =
    //           res.data.invoiceInfo.taxRegisterAddress;

    //         shareData.insuranceInfo.invoiceInfo.taxRegisterTel =
    //           res.data.invoiceInfo.taxRegisterTel;
    //       }

    //       this.$store.dispatch("nonCar/updateShareData", {
    //         info: shareData.productInfo,
    //         type: 1
    //       });
    //       this.$store.dispatch("nonCar/updateShareData", {
    //         info: shareData.insuranceInfo,
    //         type: 2
    //       });
    //       this.$router.push({
    //         name: "proInsuranceInformation"
    //       });
    //     }
    //   });
    // },

    // 订单转发支付查询信息
    async forwardPayProductInsureInfoQuery () {
      let time = this.formatTime(new Date());
      let obj = {
        forwardDate: time,
        proposalNo: this.insureNo
      };
      return await forwardPayProductInsureInfo(obj);
    },
    // 跳转到更多页面
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
        path: "/order/orderList/orderList?moudle=0&status=0"
      });
      // this.$globalBack();
    },
    // 获取订单详情
    getOrderInfo () {
      // console.log(res);
      this.OrderDetail()
        .then(res => {
          console.dir(res);
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
#orderDetailUnused {
  width: 100%;
  // height: calc(100vh - 100px);
  height: auto;
  background: rgba(247, 247, 247, 1);
  // overflow: scroll;
  padding: 95px 0;
}
</style>
