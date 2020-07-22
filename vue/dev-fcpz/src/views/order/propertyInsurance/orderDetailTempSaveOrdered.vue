<!--
 * @Description: 订单详情-暂存--已下单
 * @Date: 2020-03-13 19:12:35
 * @Author: zhagnminjie
 * @LastEditTime: 2020-05-15 13:46:45
 -->
<template>
  <div class="orderDetailTempSaveOrdered" id="orderDetailTempSaveOrdered">
    <orderDetailNav statusName="暂存" @goBackEvent="goBack"></orderDetailNav>
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
      :vechileInfoList="vechileInfoList"
      @redirectMoreInfoPageEvent="redirectMoreInfoPage"
    ></orderDetailMidContentNew>
    <orderDetailStatementUnused
      :orderStauts="orderStauts"
      :insuranceType="insuranceType"
      :isUpload="isUpload"
      @copyInsuranceEvent="copyInsurance"
      @uploadImageEvent="uploadImage"
    ></orderDetailStatementUnused>
  </div>
</template>

<script>
import Vue from "vue";
import { Card, Toast } from "vant";
import orderDetailMidContentNew from "../common/orderDetailMidContentNew.vue";
import orderDetailStatementUnused from "../common/orderDetailStatementUnused.vue";
import orderDetailTopUnusedAndGuaranteed from "../common/orderDetailTopUnusedAndGuaranteed.vue";
import orderDetailNav from "../common/orderDetailNav.vue";
import {
  storeOrderDetail,
  imageUpload,
  underwritingStatus
} from "@src/http/module/order.js";
import {
  imageCheck,
  updateUnderwritingStatus,
  queryZc
} from "@src/http/module/nonCar.js";

Vue.use(Card);
export default {
  name: "orderDetailTempSaveOrdered",
  components: {
    orderDetailMidContentNew,
    orderDetailStatementUnused,
    orderDetailTopUnusedAndGuaranteed,
    orderDetailNav,
    [Card.name]: Card
  },
  data: () => ({
    isUpload: true,
    orderStauts: 0, //暂存
    insuranceType: 1, //1 财产险 2 健康险
    btnName: "继续录入",
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
    insureNo: "",
    insurePersonInfo: {},
    personList: [],
    insurePersonInfoList: [], //被保人list
    orderInfoObj: {},
    orderNo: ""
  }),
  created() {
    this.getOrderInfo();
    //校验影像文件是否已经上传
    this.checkImgUpload();
  },
  methods: {
    // 复制投保单
    copyInsurance() {
      this.getQueryZcOrderInfo();
    },
    // 获取暂存订单
    getQueryZcOrderInfo() {
      let obj = { orderNo: this.orderNo };
      queryZc(obj).then(res => {
        if (res.code == "1" && res.data && res.data.storeOracleRequestVO) {
          // console.log(309, res.data.storeOracleRequestVO);

          this.orderInfoObj = JSON.parse(
            JSON.stringify(res.data.storeOracleRequestVO)
          );
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
              orderNo: "", //订单id
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
          // this.$router.push({
          //   name: "proInsuranceInformation"
          // });
          this.$router.push({
            path: "/nonCar/propertyInsurance/worryFree?isClear=0"
          });
        }
      });
    },

    //  上传影像
    uploadImage() {
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
              type: "0",
              titleBarTag: "1",
              shareUrl: res.data.url, //TODO suncao 影像上传
              titleBar: "影像上传",
              sharePlatform: ["0"],
              shareType: "url",
              title: "投保订单影像上传",
              imgURL:
                "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
              image: "", //分享图片（base64编码）, 与imgURL二选一
              desc: "尊敬的客户，您的投保订单已生成，可以在线上传影像"
            };
            console.log("请求壳子的参数===", JSON.stringify(param));
            this.$native.openWebView(param, data => {});
            // window.location.href = res.data.url;
          }
        }
      });
      // this.$router.push({
      //   name: "proOrderPortraitUpload",
      //   query: { proposalNo: this.insureNo }
      // });
    },
    // 获取影像上传的页面链接
    async queryImageUpload(obj) {
      return await imageUpload(obj);
    },
    //修改为待核保状态
    async editUnderwritingStatus() {
      return await underwritingStatus({ insureNo: this.insureNo });
    },
    // 检验影像上传的照片类型
    async imageCheck() {},
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
    //返回上一页
    goBack() {
      // this.$globalBack();
      this.$router.push({
        path: "/order/orderList/orderList?moudle=1&status=1"
      });
    },
    // 获取订单详情
    getOrderInfo() {
      // console.log(res);
      this.OrderDetail()
        .then(res => {
          if (res.code == "1" && res.data) {
            this.insuranceInfo = {
              insureNo: res.data.insureNo,
              desc: res.data.insuranceName,
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
            let formulasArr = res.data.formulas;
            if (res.data.formulas.length > 0) {
              if (formulasArr[0].addRiskAmountJson) {
                let addRiskAmountJson = JSON.parse(
                  formulasArr[0].addRiskAmountJson
                );
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
            }
            let {
              applicantName,
              appliIdentify,
              appliIdentifyType,
              mobileNo,
              insuredName,
              insuredIdentify,
              insuredIdentifyType,
              insuredMobile,
              relateInsured,
              email,
              orderNo
            } = res.data;
            this.orderNo = orderNo;
            this.person = {
              applicantName,
              appliIdentify,
              insuredName,
              insuredIdentify
            };
            this.insurePersonInfo = {
              applicantName,
              appliIdentify,
              appliIdentifyType,
              mobileNo,
              email,
              insuredName,
              insuredIdentify,
              insuredIdentifyType,
              insuredMobile,
              relateInsured //投被保人关系
            };
            this.insurePersonInfoList.push(this.insurePersonInfo);
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
            let typeVal = this.$getCodeOrName(
              "vehicleModelCodeList",
              res.data.carInfoVO.vehicleModelCode
            );

            this.vechileInfoList[0].desc = typeVal ? typeVal : "";
            this.vechileInfoList[1].desc = res.data.carInfoVO.licenseplateno;
            this.vechileInfoList[2].desc = res.data.carInfoVO.engineNo;
            this.vechileInfoList[3].desc = res.data.carInfoVO.approvalQuality;
            this.vechileInfoList[4].desc = res.data.carInfoVO.vehicleAge;
            this.vechileInfoList[5].desc = res.data.carInfoVO.vehicleBrand;

            this.insuranceCost[0].desc = res.data.insuranceName;
            this.insuranceCost[0].price = res.data.sumPremium;
            this.insuranceCost[1].price = res.data.sumPremium;
          }
        })
        .catch(() => {});
    },
    async OrderDetail() {
      // this.insureNo = "TYEF20530126020000000137";
      this.insureNo = this.$route.query.insureNo;
      return await storeOrderDetail({ insureNo: this.insureNo });
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
    },
    checkImgUpload() {
      //TODO suncao校验影像是否上传
      let checkParam = {
        userCode: localStorage.getItem("userCode"),
        comCode: localStorage.getItem("comCode"),
        bussNo: this.$route.query.insureNo,
        busiDate: new Date().getFullYear() + "0000",
        imgType: "UW_Y"
      };
      imageCheck(checkParam).then(res => {
        if (res.code == "1") {
          const arr = new Array();
          //TODO suncao如果已经上传 同步后台接口告知影像已经上传
          for (let key in res.data.imageNodes) {
            console.log("value======" + res.data.imageNodes[key].nodeId);
            arr.push(res.data.imageNodes[key].nodeId);
          }
          if (arr.includes("UWY004") && arr.includes("UWY302")) {
            let updateStatusParam = {
              insureNo: this.$route.query.insureNo,
              isIssueAfterPay: this.$route.query.isIssueAfterPay
            };
            updateUnderwritingStatus(updateStatusParam).then(res => {
              if (res.code == "1") {
                //TODO suncao如果已经上传 同步后台接口告知影像已经上传-跳转到待核保页面 把订单号带过去
                this.$router.push({
                  name: "proOrderDetailUnderwritten",
                  query: { insureNo: this.$route.query.insureNo }
                });
              } else {
                // this.$toast.fail(res.msg || res.message);
              }
            });
          }
        } else {
          this.$toast.fail(res.msg || res.message);
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
#orderDetailTempSaveOrdered {
  width: 100%;
  height: auto;
  // height: calc(100vh - 100px);
  background: rgba(247, 247, 247, 1);
  // overflow: scroll;
  padding: 95px 0;
}
</style>
