<!--
 * @Description: 非车配置 --- 订单详情---待核保
 * @Date: 2020-06-17 13:41:52
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 17:26:42
 * @FilePath: \nonCarCongfig\src\views\order\dynamicOrderDetail\underwritten.vue
-->

<template>
  <div class="underwritten" id="underwritten">
    <headerNav statusName="待核保" @goBackEvent="goBack" />
    <topTitle :insuranceInfo="insuranceInfo" :orderStauts="orderStauts" />
    <midContent
      :guaranteedCollageContent="formulaList"
      :insuranceCost="insuranceCost"
      :elementList="elementList"
      :invoiceFlag="invoiceFlag"
      :invoiceInfo="invoiceInfo"
    />
    <statement
      :orderStauts="orderStauts"
      :insuranceType="insuranceType"
      :isUpload="isUpload"
      :termsAndConditions="termsAndConditions"
      :order="order"
      @gotoSignatureEvent="gotoSignature"
      @submitUnderwritingEvent="twoUnderwriting"
      @uploadImageEvent="uploadImage"
    />
  </div>
</template>

<script>
import headerNav from "../dynamicOrderDetail/common/headerNav.vue";
import topTitle from "../dynamicOrderDetail/common/topTitle.vue";
import midContent from "../dynamicOrderDetail/common/midContent.vue";
import statement from "../dynamicOrderDetail/common/statement.vue";
import { imageUpload, getOrderDetail } from "@src/http/module/order.js";
import { applyPayTwo, underwritingTwo } from "@src/http/module/nonCar.js";

export default {
  name: "underwritten",
  components: {
    headerNav,
    topTitle,
    midContent,
    statement
  },
  data: () => ({
    isUpload: false,
    isCollage: false,
    orderStauts: 1, //待核保
    insuranceType: 1, // 0 ： 意健    2 财产险
    formulaList: [],
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
    isSeeFee: "1",
    elementList: [],
    orderId: "",
    termsAndConditions: [],
    order: {
      signStatus: "0",
      isNeedSign: "0",
      insureNo: ""
    },
    applyObj: {
      payApplyNo: "",
      insureNo: ""
    },
    invoiceFlag: "0",
    invoiceInfo: {
      clientType: "",
      taxPayerType: "",
      invoiceType: "",
      companyName: "",
      taxMobile: "",
      taxPayerNo: "",
      taxRegistryAddress: "",
      taxRegistryPhone: "",
      bankName: "",
      accountNumber: ""
    }
  }),

  created() {
    let { orderId, insuranceCategory } = this.$route.query;
    this.orderId = orderId;
    this.insuranceType = insuranceCategory ? parseInt(insuranceCategory) : 0;
    this.getOrderDetailInfo();
  },
  methods: {
    //非车配置接口---- 待核保 underwritingTwo
    async twoUnderwriting() {
      let _this = this;
      let res = await underwritingTwo({ insureNo: this.applyObj.insureNo });
      if (res.code == "1") {
        if (this.isSeeFee == "1") {
          this.$toast({ message: "核保成功", type: "success" });
          // 获取支付号
          _this.getApplyPayTwp();
        } else {
          this.$dialog.alert({
            title: "投保出单成功",
            message: "您的保单已生成，保单号为" + res.data.policyNo,
            confirmButtonText: "查看订单",
            callback: () => {
              this.$router.push({
                name: "guaranteed",
                query: {
                  orderId: this.orderId,
                  insuranceCategory: this.insuranceType
                }
              });
            }
          });
        }
      } else {
        this.$dialog.alert({ message: res.msg || res.message });
      }
    },
    // 获取支付号
    async getApplyPayTwp() {
      let request = await applyPayTwo({ insureNo: this.applyObj.insureNo });
      if (request.code == "1") {
        this.$toast({
          message: request.msg,
          type: "success"
        });
        if (request.data && request.data.noncarOrder.insureNo) {
          this.$router.push({
            name: "prPaymentOrder",
            query: {
              proposalNo: request.data.noncarOrder.insureNo
            }
          });
        }
      } else {
        this.$toast({
          message: request.msg || request.message,
          type: "fail"
        });
      }
    },
    //TODO suncao  上传影像
    //  上传影像
    uploadImage() {
      //TODO suncao跳转影像上传页面
      let uploadParam = {
        userCode: localStorage.getItem("userCode"),
        comCode: localStorage.getItem("comCode"),
        bussNo: this.order.insureNo,
        busiDate: new Date().getFullYear() + "0000",
        imgType: "UW_Y",
        flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0" //0:内网 1：外网
      };
      this.queryImageUpload(uploadParam).then(res => {
        if (res.code == "1") {
          this.$toast({
            message: res.msg,
            type: "success",
            position: "bottom"
          });
          if (res.data && res.data.url) {
            let param = {
              url: res.data.url,
              shareUrl: res.data.url, //TODO suncao 影像上传
              type: "0",
              titleBarTag: "1",
              titleBar: "影像上传",
              sharePlatform: ["0"],
              shareType: "url",
              title: "投保订单影像上传",
              imgURL:
                "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
              image: "", //分享图片（base64编码）, 与imgURL二选一
              desc: "尊敬的客户，您的投保订单已生成，可以在线上传影像"
            };
            console.log(
              "请求壳子的参数===",
              JSON.stringify(window.WebViewJavascriptBridge)
            );
            console.log("请求壳子的参数===" + window.WebViewJavascriptBridge);
            if (window.WebViewJavascriptBridge) {
              this.$native.openWebView(param, data => {});
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
    async queryImageUpload(obj) {
      return await imageUpload(obj);
    },
    //返回上一页
    goBack() {
      this.$router.push({
        path: "/order/orderList/orderList?moudle=0&status=1"
      });
      // this.$globalBack();
    },
    // 获取订单详情信息
    getOrderDetailInfo() {
      let obj = { orderId: this.orderId };
      getOrderDetail(obj)
        .then(res => {
          let {
            productName,
            insureNo,
            startDate,
            endDate,
            policyNo,
            orderNo,
            productList,
            elementList,
            sumPremium,
            order,
            termsAndConditions,
            notUploadImgList,
            invoiceInfo
          } = res.data;
          this.insuranceInfo = {
            insureNo,
            desc: productName,
            startDate: startDate ? startDate.split(" ")[0] : "",
            endDate: endDate ? endDate.split(" ")[0] : "",
            policyNo,
            orderNo
          };
          this.formulaList.splice(0, this.formulaList.length);
          if (productList && productList.length > 0) {
            for (let i = 0, len = productList.length; i < len; i++) {
              this.formulaList.push(productList[i]);
            }
          }
          this.elementList.splice(0, this.elementList.length);
          this.elementList = elementList;
          this.insuranceCost[0].desc = productName;
          this.insuranceCost[0].price = sumPremium;
          this.insuranceCost[1].price = sumPremium;
          this.orderInfoObj = JSON.parse(JSON.stringify(res.data));
          this.termsAndConditions.splice(0, this.termsAndConditions.length);
          this.termsAndConditions = termsAndConditions.content;
          if (order) {
            let {
              signStatus,
              isNeedSign,
              insureNo,
              isNeedUploadImg,
              isIssueAfterPay,
              payApplyNo,
              invoiceFlag
            } = order;
            this.order = {
              signStatus,
              isNeedSign,
              insureNo,
              isNeedUploadImg,
              notUploadImgList,
              isIssueAfterPay
            };
            this.applyObj = {
              payApplyNo,
              insureNo
            };
            this.invoiceFlag = invoiceFlag;
            if (invoiceFlag == 1 || invoiceFlag == 2) {
              if (invoiceFlag == 1 || invoiceFlag == 2) {
                this.invoiceInfo = JSON.parse(JSON.stringify(invoiceInfo));
                this.invoiceInfo.clientType = this.$getCodeOrName(
                  "customerTypeList",
                  this.invoiceInfo.clientType
                );
                this.invoiceInfo.taxPayerType = this.$getCodeOrName(
                  "taxpayerTypeList",
                  this.invoiceInfo.taxPayerType
                );
                this.invoiceInfo.invoiceType = this.$getCodeOrName(
                  "invoiceTypeList",
                  this.invoiceInfo.invoiceType
                );
              }
            }
          }
        })
        .catch(() => {});
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
    //跳转到签字页面
    gotoSignature() {
      this.$router.push({
        name: "prSign",
        query: {
          insureNo: this.insuranceInfo.insureNo
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
#underwritten {
  width: 100%;
  // height: calc(100vh - 100px);
  background: rgba(247, 247, 247, 1);
  // overflow: scroll;
  height: auto;
  padding: 100px 0 110px 0;
}
</style>
