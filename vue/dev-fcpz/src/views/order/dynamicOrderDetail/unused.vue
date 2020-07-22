<!--
 * @Description: 非车配置---订单详情---已失效订单
 * @Date: 2020-06-17 13:41:52
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 17:27:05
 * @FilePath: \nonCarCongfig\src\views\order\dynamicOrderDetail\unused.vue
-->
<template>
  <div class="unused" id="unused">
    <headerNav statusName="已失效" @goBackEvent="goBack" />
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
      :termsAndConditions="termsAndConditions"
      :order="order"
      @copyInsuranceEvent="copyInsurance"
      @uploadImageEvent="uploadImage"
    />
  </div>
</template>

<script>
import Vue from "vue";
import { Card, Button } from "vant";
import headerNav from "../dynamicOrderDetail/common/headerNav.vue";
import topTitle from "../dynamicOrderDetail/common/topTitle.vue";
import midContent from "../dynamicOrderDetail/common/midContent.vue";
import statement from "../dynamicOrderDetail/common/statement.vue";
import { getOrderDetail, imageUpload } from "@src/http/module/order.js";
Vue.use(Card);
Vue.use(Button);
export default {
  name: "orderDetailUnused",
  components: {
    headerNav,
    topTitle,
    midContent,
    statement
  },
  data: () => ({
    orderStauts: 10, //已失效
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
    orderNo: "",
    orderId: "",
    elementList: [],
    termsAndConditions: [],
    order: {
      signStatus: "0",
      isNeedSign: "0",
      insureNo: ""
    },
    strategyId: "",
    invoiceFlag: "0",
    invoiceInfo: {}
  }),
  created() {
    let { orderId, insuranceCategory } = this.$route.query;
    this.orderId = orderId;
    this.insuranceType = insuranceCategory ? parseInt(insuranceCategory) : 0;
    this.getOrderDetailInfo();
  },
  methods: {
    // 复制投保单
    copyInsurance() {
      this.$router.push({
        name: "prIndex",
        query: { strategyId: this.strategyId, orderId: this.orderId }
      });
    },
    //返回上一页
    goBack() {
      this.$router.push({
        path: "/order/orderList/orderList?moudle=0&status=0"
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
            termsAndConditions,
            order,
            strategyId,
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
    }
  }
};
</script>

<style lang="less" scoped>
#unused {
  width: 100%;
  // height: calc(100vh - 100px);
  background: rgba(247, 247, 247, 1);
  // overflow: scroll;
  height: auto;
  padding: 100px 0 110px 0;
}
</style>
