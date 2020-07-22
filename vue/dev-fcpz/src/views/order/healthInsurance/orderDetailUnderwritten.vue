/* zhangminjie 订单详情-待核保 */
<template>
  <div
    class="orderDetailUnderwritten"
    id="orderDetailUnderwritten"
  >
    <orderDetailNav
      statusName="待核保"
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
      :isUpload="isUpload"
      :productCode="productCode"
      :isSheBao="isSheBao"
      @submitUnderwritingEvent="submitUnderwriting"
      @uploadImageEvent="uploadImage"
    ></orderDetailStatementUnusedYijian>
  </div>
</template>

<script>
import Vue from "vue";
import { Card, Toast } from "vant";
import orderDetailMidContentNew from "../common/orderDetailMidContentNew.vue";
import orderDetailStatementUnusedYijian from "../common/orderDetailStatementUnusedYijian.vue";
import orderDetailTopUnusedAndGuaranteed from "../common/orderDetailTopUnusedAndGuaranteed.vue";
import orderDetailNav from "../common/orderDetailNav.vue";
import { storeOrderDetail, imageUpload } from "@src/http/module/order.js";
import { underwriting, applyPay } from "@src/http/module/nonCar.js";

Vue.use(Card);
export default {
  name: "orderDetailUnderwritten",
  components: {
    orderDetailMidContentNew,
    orderDetailStatementUnusedYijian,
    orderDetailTopUnusedAndGuaranteed,
    orderDetailNav
  },
  data: () => ({
    productCode: "FM50", //区分个人版和家庭版  FM50:个人版,FM51:家庭版
    isSheBao: "Y",
    isUpload: false,
    isCollage: false,
    orderStauts: 1, //待核保
    insuranceType: 2, //1 财产险 2 健康险
    formulaList: [],
    person: {
      applicantName: "",
      appliIdentify: "",
      insuredName: "",
      insuredIdentify: ""
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
      policyNo: ""
    },
    insureNo: "",
    insurePersonInfo: {},
    personList: [],
    insurePersonInfoList: [] //被保人list
  }),
  created () {
    this.getOrderInfo();
  },
  methods: {
    //TODO suncao 提交核保
    async submitUnderwriting () {
      let _this = this;
      let res = await underwriting({ insureNo: this.insureNo });
      if (res.code == "1") {
        Toast({ message: "核保成功", type: "success", position: "bottom" });
        // 获取支付号
        _this.getApplyPay(this.insureNo);
      }
    },
    // 获取支付号
    async getApplyPay () {
      let res = await applyPay({ insureNo: this.insureNo });
      console.dir("获取支付号成功");
      console.log(res);
      if (res.code == "1") {
        Toast({
          message: res.msg,
          type: "success",
          position: "bottom"
        });
        if (res.data && res.data.insureInfo) {
          // 跳转到支付页面
          let {
            insureNo,
            payApplyNo,
            identityCode,
            sumPremium,
            startDate,
            productName,
            applicantName,
            mobileNo,
            insuredName,
            insuredMobile
          } = res.data.insureInfo;
          // 立即支付

          // 跳转到支付页面
          this.$router.push({
            name: "paymentOrder",
            query: {
              params: JSON.stringify({
                proposalNo: insureNo,
                paymentNo: payApplyNo,
                checkNo: identityCode,
                sumPremium: sumPremium,
                startDate: startDate,
                productName: productName,
                applicantName: applicantName,
                mobileNo: mobileNo,
                insuredName: insuredName,
                insuredMobile: insuredMobile,
                insuranceType: 2 //保险类别 1财产险，2个人版，3家庭版
              })
            }
          });
        }
      } else {
        Toast({
          message: res.msg,
          type: "success",
          position: "bottom"
        });
      }
    },
    //TODO suncao  上传影像
    //  上传影像
    uploadImage () {
      //TODO suncao跳转影像上传页面
      let uploadParam = {
        userCode: localStorage.getItem("userCode"),
        comCode: localStorage.getItem("comCode"),
        bussNo: this.$route.query.insureNo,
        busiDate: new Date().getFullYear() + "0000",
        imgType: "UW_Y",
        flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0" //0:内网 1：外网
      };
      this.queryImageUpload(uploadParam).then(res => {
        if (res.code == "1") {
          Toast({ message: res.msg, type: "success", position: "bottom" });
          if (res.data && res.data.url) {
            let param = {
              url: res.data.url,
              shareUrl: res.data.url, //TODO suncao 影像上传
              type: "0",
              titleBarTag: "1",
              titleBar: "影像上传",
              sharePlatform: ["0", "1"],
              shareType: "url",
              title: "投保订单影像上传",
              imgURL:
                "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
              image: "", //分享图片（base64编码）, 与imgURL二选一
              desc: "尊敬的客户，您的投保订单已生成，可以在线上传影像"
            };
            console.log("请求壳子的参数===", JSON.stringify(param));
            if (window.WebViewJavascriptBridge) {
              this.$native.openWebView(param, data => { });
            } else {
              window.location.href = res.data.url;
            }
          }
        }
      });
      // this.$router.push({
      //   name: "proOrderPortraitUpload",
      //   query: { proposalNo: this.insureNo }
      // });
    },
    // 获取影像上传的页面链接
    async queryImageUpload (obj) {
      return await imageUpload(obj);
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
    //返回上一页
    goBack () {
      this.$router.push({
        path: "/order/orderList/orderList?moudle=0&status=1"
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
    // 获取订单详情
    // getOrderInfo() {
    //   let res = this.OrderDetail();
    //   // console.log(res);
    //   res
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
    //           mobileNo,
    //           insuredName,
    //           insuredIdentify,
    //           insuredIdentifyType,
    //           insuredMobile,
    //           relateInsured,
    //           emial
    //         } = res.data;
    //         let relate = this.$getCodeOrName("bwRelateInsured", relateInsured);
    //         relateInsured = relate ? relate : "";
    //         this.person = {
    //           applicantName,
    //           appliIdentify,
    //           insuredName,
    //           insuredIdentify,
    //           relateInsured
    //         };
    //         this.insurePersonInfo = {
    //           applicantName,
    //           appliIdentify,
    //           appliIdentifyType,
    //           mobileNo,
    //           emial,
    //           insuredName,
    //           insuredIdentify,
    //           insuredIdentifyType,
    //           insuredMobile,
    //           relateInsured //投被保人关系
    //         };
    //         this.insurePersonInfoList.push(this.insurePersonInfo);
    //         this.insuranceCost[0].desc = res.data.insuranceName;
    //         this.insuranceCost[0].price = res.data.sumPremium;
    //         this.insuranceCost[1].price = res.data.sumPremium;
    //       }
    //     })
    //     .catch(() => {});
    // },
    async OrderDetail () {
      this.insureNo = this.$route.query.insureNo;
      // this.insureNo = "TYEF20530126020000000206";
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
    }
  }
};
</script>

<style lang="less" scoped>
#orderDetailUnderwritten {
  width: 100%;
  height: auto;
  // height: calc(100vh - 100px);
  background: rgba(247, 247, 247, 1);
  // overflow: scroll;
  padding: 95px 0;
}
</style>
