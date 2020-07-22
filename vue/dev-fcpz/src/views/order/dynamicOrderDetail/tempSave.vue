<!--
 * @Description: 非车配置 --- 订单详情 -- 暂存未下单
 * @Date: 2020-06-17 13:41:52
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 17:26:27
 * @FilePath: \nonCarCongfig\src\views\order\dynamicOrderDetail\tempSave.vue
-->
<template>
  <div class="tempSave" id="tempSave">
    <headerNav
      statusName="暂存"
      :status="orderStauts"
      :isInsured="isInsured"
      @goBackEvent="goBack"
    />
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
      :order="order"
      :termsAndConditions="termsAndConditions"
      @continueInputEvent="continueInput"
    />
  </div>
</template>

<script>
import headerNav from "../dynamicOrderDetail/common/headerNav.vue";
import topTitle from "../dynamicOrderDetail/common/topTitle.vue";
import midContent from "../dynamicOrderDetail/common/midContent.vue";
import statement from "../dynamicOrderDetail/common/statement.vue";

import { getOrderDetail } from "@src/http/module/order.js";
export default {
  name: "tempSave",
  components: {
    headerNav,
    topTitle,
    midContent,
    statement
  },
  data: () => ({
    isUpload: false,
    orderStauts: 0, //暂存
    insuranceType: 1, // 0 ： 意健    2 财产险
    isInsured: true,
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
    orderId: "", //订单号
    orderInfoObj: {},
    elementList: [], //动态表单的数据
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
      accountNumber: "",
      taxEmail: ""
    }
  }),
  created() {
    let { orderId, insuranceCategory } = this.$route.query;
    this.orderId = orderId;
    this.insuranceType = insuranceCategory ? parseInt(insuranceCategory) : 0;
    this.getOrderDetailInfo();
  },
  methods: {
    //返回上一页
    goBack() {
      this.$router.push({
        path: "/order/orderList/orderList?moudle=0&status=1"
      });
    },
    //继续录入
    continueInput() {
      this.$router.push({
        name: "prIndex",
        query: { strategyId: this.strategyId, orderId: this.orderId }
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
    }
  }
};
</script>

<style lang="less" scoped>
#tempSave {
  width: 100%;
  height: auto;
  // height: calc(100vh - 100px);
  background: rgba(247, 247, 247, 1);
  // overflow: scroll;
  padding: 120px 0 110px 0;
}
</style>
