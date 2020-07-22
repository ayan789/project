/* zhangminjie 订单详情-暂存--未下单 */
<template>
  <div
    class="orderDetailTempSave"
    id="orderDetailTempSave"
  >
    <orderDetailNav
      statusName="暂存"
      :status="orderStauts"
      @goBackEvent="goBack"
      :isInsured="isInsured"
    ></orderDetailNav>
    <orderDetailTopUnusedAndGuaranteed
      :insuranceInfo="insuranceInfo"
      :orderStauts="orderStauts"
      :orderNo="orderNo"
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
      :isUpload="isUpload"
      :productCode="productCode"
      :isSheBao="isSheBao"
      @copyInsuranceEvent="copyInsurance"
      @continueInputEvent="continueInput"
    ></orderDetailStatementUnusedYijian>
  </div>
</template>

<script>
import Vue from "vue";
import { Card } from "vant";
import orderDetailMidContentNew from "../common/orderDetailMidContentNew.vue";
import orderDetailStatementUnusedYijian from "../common/orderDetailStatementUnusedYijian.vue";
import orderDetailTopUnusedAndGuaranteed from "../common/orderDetailTopUnusedAndGuaranteed.vue";
import orderDetailNav from "../common/orderDetailNav.vue";
import {
  storeOrderDetail,
  forwardPayProductInsureInfo
} from "@src/http/module/order.js";
import { mapMutations, mapGetters } from "vuex";

import { queryZc } from "@src/http/module/nonCar.js";
Vue.use(Card);
export default {
  name: "orderDetailTempSave",
  components: {
    orderDetailMidContentNew,
    orderDetailStatementUnusedYijian,
    orderDetailTopUnusedAndGuaranteed,
    orderDetailNav
  },
  data: () => ({
    productCode: "FM50", //区分个人版和家庭版  FM50:个人版,FM51:家庭版
    isSheBao: "Y",
    familyData: {},
    orderInfoObj: {},
    isUpload: false,
    orderStauts: 0, //暂存
    insuranceType: 2, //1 财产险 2 健康险
    btnName: "继续录入",
    isInsured: true,
    person: {
      applicantName: "",
      appliIdentify: "",
      insuredName: "",
      insuredIdentify: "",
      relateInsured: ""
    },
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
      policyNo: "",
      orderNo: ""
    },
    formulaList: [],
    insureNo: "",
    insurePersonInfo: {},
    personList: [],
    insurePersonInfoList: [], //被保人list
    orderNo: ""
  }),
  created () {
    this.orderNo = this.$route.query.orderNo;
    this.getQueryZcOrderInfo();
  },
  computed: {
    ...mapGetters("million", ["tenMillionData", "newData"])
  },
  methods: {
    //返回上一页
    goBack () {
      this.$router.push({
        path: "/order/orderList/orderList?moudle=0&status=1"
      });
      // this.$globalBack();
    },

    //继续录入
    continueInput () {
      if (this.productCode == "FM51") {
        this.$router.push({
          name: "writeForm",
          params: {
            back: JSON.stringify("3"),
            item1: JSON.stringify(this.familyData),
            isFromOrderList: true
          }
        });
      } else if (this.productCode == "FM50") {
        this.cleTenMillionData()

        this.$router.push({
          name: "insuranceInformationInput",
          params: {
            copy: JSON.stringify(this.orderInfoObj),
            orderNo: this.orderNo
          }
        });
      } else if (this.productCode == "WTI01") {
        this.cleNewData()
        let tS = this.newData;
        if (this.orderInfoObj) {
          let copy = this.orderInfoObj;
          for (let k in tS) {
            tS[k] = copy[k];
          }
          tS.placeInfo = copy['addressInfo'].addressName
          this.setNewData(tS)
          this.setNewAgesJson({ premiumsJson: this.orderInfoObj.premiumsJson })
          localStorage.setItem('newDataOrderNo', this.orderNo)
          this.$router.push({
            name: "stopCancerForm"
          });
        }
      }
    },
    // 复制投保单
    copyInsurance () {
      // queryZc({ orderNo: this.orderNo }).then(res => {
      // if (res.code == "1" && res.data) {
      if (this.productCode != "FM50") {
        this.$router.push({
          name: "writeForm",
          params: {
            back: JSON.stringify("3"),
            item1: JSON.stringify(this.familyData),
            copy: true
          }
        });
      } else {

        this.$router.push({
          name: "insuranceInformationInput",
          params: {
            copy: JSON.stringify(this.orderInfoObj)
          }
        });
      }
      // }
      // });
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
      console.log(time);
      let obj = {
        forwardDate: time,
        proposalNo: this.insureNo
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
          insurePersonInfoList: this.insurePersonInfoList
        }
      });
    },
    getQueryZcOrderInfo () {
      let obj = { orderNo: this.orderNo };
      queryZc(obj).then(res => {
        if (res.code == "1" && res.data && res.data.storeOracleRequestVO) {
          // console.log(309, res.data.storeOracleRequestVO);
          let data = res.data.storeOracleRequestVO;
          this.familyData = res.data;
          this.orderInfoObj = JSON.parse(
            JSON.stringify(res.data.storeOracleRequestVO)
          );
          this.insuranceInfo = {
            insureNo: data.insureNo,
            desc: data.productName,
            startDate: data.startDate ? data.startDate.split(" ")[0] : "",
            endDate: data.endDate ? data.endDate.split(" ")[0] : "",
            policyNo: data.policyNo,
            orderNo: res.data.orderNo
          };
          this.orderNo = res.data.orderNo;
          this.formulaList.splice(0, this.formulaList.length);
          if (data.amountInfoJson) {
            let amountInfoJson = JSON.parse(data.amountInfoJson);
            let arr = [];
            if (data.productCode == "FM50") {
              for (let [k, v] of Object.entries(amountInfoJson)) {
                if (parseInt(k.substr(0, k.indexOf("#"))) <= 16) {
                  k = k.slice(3, k.length);
                  v = v / 10000;
                  arr.push({ name: k, value: v });
                } else {
                  if (
                    data.aKZEXClause == "Y" &&
                    parseInt(k.substr(0, k.indexOf("#"))) == 17
                  ) {
                    k = k.slice(3, k.length);
                    v = v / 10000;
                    arr.push({ name: k, value: v });
                  }
                  if (
                    data.aEXTZClause == "Y" &&
                    parseInt(k.substr(0, k.indexOf("#"))) == 18
                  ) {
                    k = k.slice(3, k.length);
                    v = v / 10000;
                    arr.push({ name: k, value: v });
                  }
                  if (
                    data.aEXYLClause == "Y" &&
                    parseInt(k.substr(0, k.indexOf("#"))) == 19
                  ) {
                    k = k.slice(3, k.length);
                    v = v / 10000;
                    arr.push({ name: k, value: v });
                  }
                  if (
                    data.aEXZZClause == "Y" &&
                    parseInt(k.substr(0, k.indexOf("#"))) == 20
                  ) {
                    k = k.slice(3, k.length);
                    v = v / 10000;
                    arr.push({ name: k, value: v });
                  }
                }
              }
            } else {
              for (let [k, v] of Object.entries(amountInfoJson)) {
                if (parseInt(k.substr(0, k.indexOf("#"))) <= 17) {
                  k = k.slice(3, k.length);
                  v = v / 10000;
                  arr.push({ name: k, value: v });
                } else {
                  if (
                    data.aKZEXClause == "Y" &&
                    parseInt(k.substr(0, k.indexOf("#"))) == 18
                  ) {
                    k = k.slice(3, k.length);
                    v = v / 10000;
                    arr.push({ name: k, value: v });
                  }
                  if (
                    data.aEXTZClause == "Y" &&
                    parseInt(k.substr(0, k.indexOf("#"))) == 19
                  ) {
                    k = k.slice(3, k.length);
                    v = v / 10000;
                    arr.push({ name: k, value: v });
                  }
                  if (
                    data.aEXYLClause == "Y" &&
                    parseInt(k.substr(0, k.indexOf("#"))) == 20
                  ) {
                    k = k.slice(3, k.length);
                    v = v / 10000;
                    arr.push({ name: k, value: v });
                  }
                  if (
                    data.aEXZZClause == "Y" &&
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

          let {
            appliName,
            appliIdentifyType,
            appliIdentifyNumber,
            appliMobile,
            appliBirthDate,
            appliEMail,
            insuredName,
            insuredIdentifyType,
            insuredIdentifyNumber,
            insuredMobile,
            insuredBirthDate,
            appliIdentity,
            insuredSheBaoType,
            productCode,
            accidentalInjuryInfos,
            invoiceInfo,
            isSheBao
          } = data;
          this.productCode = productCode;
          this.isSheBao = isSheBao;
          // 家庭版
          if (productCode == "FM51") {
            this.insurePersonInfo.accidentalInjuryInfos = [];
            this.insurePersonInfo = {
              applicantName: appliName,
              appliIdentify: appliIdentifyNumber,
              appliIdentifyType: appliIdentifyType,
              applicantBirthDate: appliBirthDate,
              mobileNo: appliMobile,
              email: appliEMail,
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
              applicantName: appliName,
              appliIdentify: appliIdentifyNumber,
              insuredName,
              insuredIdentify: insuredIdentifyNumber
            };
          } else {
            this.insurePersonInfo = {
              applicantName: appliName,
              appliIdentify: appliIdentifyNumber,
              appliIdentifyType: appliIdentifyType,
              mobileNo: appliMobile,
              applicantBirthDate: appliBirthDate,
              emial: appliEMail,
              insuredName,
              insuredIdentify: insuredIdentifyNumber,
              insuredIdentifyType: insuredIdentifyType,
              insuredBirthDate,
              insuredMobile: insuredMobile,
              relateInsured: appliIdentity, //投被保人关系
              insuredSheBaoType
            };
            let person = {
              insuredName,
              insuredIdentify: insuredIdentifyNumber,
              insuredIdentifyType,
              insuredMobile,
              relateInsured: appliIdentity, //投被保人关系
              insuredBirthDate
            };
            let relate1 = this.$getCodeOrName(
              "bwRelateInsured",
              person.relateInsured
            );
            person.relateInsured = relate1 ? relate1 : "";
            this.personList.push(person);
            this.person = {
              applicantName: appliName,
              appliIdentify: appliIdentifyNumber,
              insuredName,
              insuredIdentify: insuredIdentifyNumber,
              relateInsured: appliIdentity
            };
          }
          this.insurePersonInfo.invoiceInfo = {};
          if (
            !this.$isNull(data.invoiceInfo) &&
            Object.keys(data.invoiceInfo).length > 0
          ) {
            for (let key in data.invoiceInfo) {
              this.insurePersonInfo.invoiceInfo[key] = data.invoiceInfo[key];
            }
            this.insurePersonInfo.invoiceInfo.taxMobile =
              data.invoiceInfo.mobile;
            this.insurePersonInfo.invoiceInfo.taxEmail = data.invoiceInfo.email;
          }
          this.insuranceCost[0].desc = data.productName;
          this.insuranceCost[0].price = data.sumPremium;
          this.insuranceCost[1].price = data.sumPremium;
        } else {
          this.$toast.fail(res.msg || res.message);
        }
      });
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
      cleNewData: "CLENEWDATA",
      setNewAgesJson: "SETNEWAGESJSON"

    })
  }
};
</script>

<style lang="less" scoped>
#orderDetailTempSave {
  width: 100%;
  height: auto;
  // height: calc(100vh - 100px);
  background: rgba(247, 247, 247, 1);
  // overflow: scroll;
  padding: 110px 0;
}
</style>
