<!--
 * @Description: 非车配置---订单详情--暂存已下单
 * @Date: 2020-06-17 13:41:52
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 17:18:45
 * @FilePath: \nonCarCongfig\src\views\order\dynamicOrderDetail\tempSaveOrdered.vue
-->
<template>
  <div class="tempSaveOrdered" id="tempSaveOrdered">
    <headerNav statusName="暂存" @goBackEvent="goBack" />
    <topTitle :insuranceInfo="insuranceInfo" :orderStauts="orderStauts" />
    <midContent
      :guaranteedCollageContent="formulaList"
      :elementList="elementList"
      :insuranceCost="insuranceCost"
      :invoiceFlag="invoiceFlag"
      :invoiceInfo="invoiceInfo"
    />
    <statement
      :orderStauts="orderStauts"
      :insuranceType="insuranceType"
      :isUpload="isUpload"
      :termsAndConditions="termsAndConditions"
      :order="order"
      @copyInsuranceEvent="copyInsurance"
      @uploadImageEvent="uploadImage"
    />
  </div>
</template>

<script>
import headerNav from "../dynamicOrderDetail/common/headerNav.vue";
import topTitle from "../dynamicOrderDetail/common/topTitle.vue";
import midContent from "../dynamicOrderDetail/common/midContent.vue";
import statement from "../dynamicOrderDetail/common/statement.vue";

import {
  storeOrderDetail,
  imageUpload,
  underwritingStatus,
  getOrderDetail
} from "@src/http/module/order.js";
import {
  imageCheck,
  updateUnderwritingStatus
} from "@src/http/module/nonCar.js";

export default {
  name: "tempSaveOrdered",
  components: {
    midContent,
    statement,
    topTitle,
    headerNav
  },
  data: () => ({
    isUpload: true,
    orderStauts: 0, //暂存
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
    orderInfoObj: {},
    orderId: "",
    elementList: [],
    termsAndConditions: [], //条款
    order: {
      signStatus: "0",
      isNeedSign: "0",
      insureNo: ""
    },
    strategyId: "",
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
    //校验影像文件是否已经上传
    // this.checkImgUpload();
  },
  methods: {
    // 复制投保单
    copyInsurance() {
      this.$router.push({
        name: "prIndex",
        query: { strategyId: this.strategyId, orderId: this.orderId }
      });
    },
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
    //返回上一页
    goBack() {
      // this.$globalBack();
      this.$router.push({
        path: "/order/orderList/orderList?moudle=0&status=1"
      });
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
            strategyId,
            termsAndConditions,
            notUploadImgList,
            invoiceInfo
          } = res.data;
          this.strategyId = strategyId;
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
#tempSaveOrdered {
  width: 100%;
  height: auto;
  background: rgba(247, 247, 247, 1);
  padding: 100px 0 110px 0;
}
</style>
