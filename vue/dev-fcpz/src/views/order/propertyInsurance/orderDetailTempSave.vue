/* zhangminjie 订单详情-暂存--未下单 */
<template>
  <div class="orderDetailTempSave" id="orderDetailTempSave">
    <orderDetailNav
      statusName="暂存"
      :status="orderStauts"
      :isInsured="isInsured"
      @goBackEvent="goBack"
    ></orderDetailNav>
    <orderDetailTopUnusedAndGuaranteed
      :insuranceInfo="insuranceInfo"
      :orderStauts="orderStauts"
      :insuranceType="insuranceType"
      :orderNo="orderNo"
    ></orderDetailTopUnusedAndGuaranteed>
    <orderDetailMidContentNew
      :guaranteedCollageContent="formulaList"
      :insuranceCost="insuranceCost"
      :person="person"
      :personList="personList"
      :insuranceType="insuranceType"
      :vechileInfoList="vechileInfoList"
      @redirectMoreInfoPageEvent="redirectMoreInfoPage"
    ></orderDetailMidContentNew>
    <orderDetailStatementUnused
      :orderStauts="orderStauts"
      :insuranceType="insuranceType"
      :isUpload="isUpload"
      @copyInsuranceEvent="copyInsurance"
      @continueInputEvent="continueInput"
    ></orderDetailStatementUnused>
  </div>
</template>

<script>
import Vue from "vue";
import { Card } from "vant";
import orderDetailMidContentNew from "../common/orderDetailMidContentNew.vue";
import orderDetailStatementUnused from "../common/orderDetailStatementUnused.vue";
import orderDetailTopUnusedAndGuaranteed from "../common/orderDetailTopUnusedAndGuaranteed.vue";
import orderDetailNav from "../common/orderDetailNav.vue";
import { queryZc } from "@src/http/module/nonCar.js";

Vue.use(Card);
export default {
  name: "orderDetailTempSave",
  components: {
    orderDetailMidContentNew,
    orderDetailStatementUnused,
    orderDetailTopUnusedAndGuaranteed,
    orderDetailNav
  },
  data: () => ({
    isUpload: false,
    orderStauts: 0, //暂存
    insuranceType: 1, //1 财产险 2 健康险
    btnName: "继续录入",
    isInsured: true,
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
      policyNo: "",
      orderNo: ""
    },
    vechileInfoList: [
      {
        id: 1,
        title: "车型",
        desc: ""
      },
      {
        id: 2,
        title: "车牌号",
        desc: ""
      },
      {
        id: 3,
        title: "发动机号",
        desc: ""
      },
      {
        id: 4,
        title: "核定载重量/总牵引质量(吨)",
        desc: ""
      },
      {
        id: 5,
        title: "车龄",
        desc: ""
      },
      {
        id: 6,
        title: "厂牌型号",
        desc: ""
      }
    ],
    formulaList: [],
    insureNo: "",
    insurePersonInfo: {},
    personList: [],
    insurePersonInfoList: [], //被保人list
    orderNo: "", //订单号
    orderInfoObj: {}
  }),
  created() {
    // this.getOrderInfo();
    this.orderNo = this.$route.query.orderNo;
    this.getQueryZcOrderInfo();
  },
  methods: {
    //返回上一页
    goBack() {
      this.$router.push({
        path: "/order/orderList/orderList?moudle=1&status=1"
      });
      // this.$globalBack();
    },
    // 复制投保单
    copyInsurance() {
      this.setStoreData(false);
    },
    // 给缓存的数据赋值，更新缓存
    setStoreData(type) {
      let {
        formulaCode,
        riskCode,
        productCode,
        appliName,
        appliIdentifyType,
        appliIdentifyNumber,
        insuredName,
        insuredIdentifyType,
        insuredIdentifyNumber,
        appliMobile,
        insuredMobile,
        appliEMail,
        appliIdentity,
        startDate,
        endDate,
        sumPremium,
        isAgent,
        licenseNo,
        brandModelName,
        engineNo,
        approvalQuality,
        vehicleModelCode,
        vehicleAge,
        businessNatureName,
        businessNatureCode,
        custNo,
        custType,
        custSourceType,
        formatPremium
      } = this.orderInfoObj;
      let shareData = {
        productInfo: {
          storeCode: localStorage.getItem("userInfo")
            ? localStorage.getItem("storeCode")
            : "",
          productCode: productCode,
          riskCode: riskCode,
          formulaCode: formulaCode, //产品code
          formatPremium,
          sumPremium, //保费
          isAgent: isAgent,
          businessNatureName, //直销
          businessNatureCode, //转换
          formulaSize: 1, //保险份数
          agentInfo: {
            agentCode: "",
            agentName: "",
            agreementNo: "",
            agreementName: "",
            businessNature: "",
            businessNature2: "",
            handler2Code: "",
            handler2Name: ""
          }
        },
        insuranceInfo: {
          custNo,
          custType,
          custSourceType,
          proposalNo: "",
          orderNo: type ? this.orderNo : "", //订单id
          appliName,
          appliIdentifyType, //转换
          appliIdentifyNumber,
          appliMobile,
          appliEMail,
          appliIdentity, //转换
          insuredName: insuredName,
          insuredIdentifyType, //转换
          insuredIdentifyNumber,
          insuredMobile: insuredMobile,
          vehicleModelCode: vehicleModelCode, //转换
          licenseNo,
          engineNo,
          approvalQuality,
          vehicleAge,
          brandModelName,
          startDate: "",
          endDate: "",
          copyDataFromType: "1",
          invoiceInfo: {
            accountNo: "",
            companyName: "", //发票抬头
            copyDataFromType: "", // 同投被保人
            customerType: "", //转换
            depositeBankName: "",
            email: "",
            invoiceType: "", //转换
            mobile: "",
            taxPayerNo: "",
            taxPayerType: "", //转换
            taxRegisterAddress: "",
            taxRegisterTel: ""
          }
        }
      };
      if (
        this.orderInfoObj.agentInfo &&
        Object.keys(this.orderInfoObj.agentInfo).length > 0
      ) {
        let {
          agentCode,
          agentName,
          agreementNo,
          agreementName,
          businessNature,
          businessNature2,
          handler2Code,
          handler2Name
        } = this.orderInfoObj.agentInfo;
        shareData.productInfo.agentInfo = {
          agentCode,
          agentName,
          agreementNo,
          agreementName,
          businessNature,
          businessNature2,
          handler2Code,
          handler2Name
        };
      }
      if (
        this.orderInfoObj.invoiceInfo &&
        Object.keys(this.orderInfoObj.invoiceInfo).length > 0
      ) {
        let {
          invoiceType,
          copyDataFromType,
          bankCode,
          accountNo,
          companyName,
          taxPayerType,
          taxPayerNo,
          taxRegisterAddress,
          customerType,
          taxRegisterTel,
          email,
          mobile,
          depositeBankName
        } = this.orderInfoObj.invoiceInfo;
        shareData.insuranceInfo.copyDataFromType = copyDataFromType;
        shareData.insuranceInfo.invoiceInfo = {
          invoiceType,
          copyDataFromType,
          bankCode,
          accountNo,
          companyName,
          taxPayerType,
          taxPayerNo,
          taxRegisterAddress,
          customerType,
          taxRegisterTel,
          email,
          mobile,
          depositeBankName
        };
      }

      this.$store.dispatch("nonCar/updateShareData", {
        info: shareData.productInfo,
        type: 1
      });
      this.$store.dispatch("nonCar/updateShareData", {
        info: shareData.insuranceInfo,
        type: 2
      });
      this.$router.push({
        name: "proInsuranceInformation"
      });
    },
    //继续录入
    continueInput() {
      this.setStoreData(true);
    },
    // 跳转到更多信息
    redirectMoreInfoPage() {
      this.$router.push({
        name: "orderMoreInformation",
        query: {
          insuranceType: this.insuranceType,
          insurePersonInfo: this.insurePersonInfo,
          insurePersonInfoList: this.insurePersonInfoList
        }
      });
    },
    // queryZc
    // 获取暂存订单
    getQueryZcOrderInfo() {
      let obj = { orderNo: this.orderNo };
      queryZc(obj).then(res => {
        if (res.code == "1" && res.data && res.data.storeOracleRequestVO) {
          // console.log(309, res.data.storeOracleRequestVO);
          let data = res.data.storeOracleRequestVO;
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
          if (data.addRiskAmountJson) {
            let addRiskAmountJson = JSON.parse(data.addRiskAmountJson);
            let arr = [];
            for (let [k, v] of Object.entries(addRiskAmountJson)) {
              if (k.indexOf("#") >= 0) {
                k = k.slice(3, k.length);
              }
              v = v / 10000;
              arr.push({ name: k, value: v });
            }
            this.formulaList = arr;
          }

          let {
            appliName,
            appliIdentifyType,
            appliIdentifyNumber,
            appliMobile,
            appliEMail,
            insuredName,
            insuredIdentifyType,
            insuredIdentifyNumber,
            insuredMobile,
            appliIdentity
          } = data;
          this.person = {
            applicantName: appliName,
            appliIdentify: appliIdentifyNumber,
            insuredName,
            insuredIdentify: insuredIdentifyNumber
          };
          this.insurePersonInfo = {
            applicantName: appliName,
            appliIdentify: appliIdentifyNumber,
            appliIdentifyType: appliIdentifyType,
            mobileNo: appliMobile,
            email: appliEMail,
            insuredName: insuredName,
            insuredIdentify: insuredIdentifyNumber,
            insuredIdentifyType: insuredIdentifyType,
            insuredMobile: insuredMobile,
            relateInsured: appliIdentity //投被保人关系
          };
          let typeVal = this.$getCodeOrName(
            "vehicleModelCodeList",
            data.vehicleModelCode
          );
          this.insurePersonInfoList.push(this.insurePersonInfo);
          this.vechileInfoList[0].desc = typeVal ? typeVal : "";
          this.vechileInfoList[1].desc = data.licenseNo;
          this.vechileInfoList[2].desc = data.engineNo;
          this.vechileInfoList[3].desc = data.approvalQuality;
          this.vechileInfoList[4].desc = data.vehicleAge;
          this.vechileInfoList[5].desc = data.brandModelName;

          this.insuranceCost[0].desc = data.productName;
          this.insuranceCost[0].price = data.sumPremium;
          this.insuranceCost[1].price = data.sumPremium;
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
        } else {
          this.$toast.fail(res.msg || res.message);
        }
      });
    },

    //格式化钱数
    formatNum(num) {
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
    formatTime(date) {
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
    }
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
