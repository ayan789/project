<template>
  <!-- 投保确认 -->
  <div class="insuranceinfoConfirm" id="insuranceinfoConfirm">
    <globalNav
      :title="globalNav.title"
      @clickBack="goback"
      :isHome="isHome"
      @clickRight="clickRight"
    />

    <div class="card">
      <van-cell-group>
        <div class="title-container">
          <div class="topLeft">
            <img
              src="@/assets/nonCar/propertyInsurance/infoInput.png"
              alt="header"
            />
          </div>
          <div class="topRight">
            <div class="title">{{ prTitle }}</div>
            <div class="time">保障期限: {{ startDate }}至{{ endDate }}</div>
          </div>
          <div class="icon" @click="goback">
            <img src="@/assets/common/edit.png" alt="edit" />
          </div>
        </div>
      </van-cell-group>
    </div>
    <van-cell-group
      :border="!isNoBorder"
      class="list"
      v-for="(item, index) in elementList"
      :key="index"
    >
      <van-cell :border="!isNoBorder" icon="manager-o">
        <div slot="title" class="title">{{ item.groupName }}</div>
      </van-cell>
      <van-cell
        :border="!isNoBorder"
        v-for="(element, i) in item.elementList"
        :key="i"
        v-show="elementList.length > 0"
      >
        <div slot="title" class="content">
          {{ element.name }}
        </div>
        <div slot="default" class="content">{{ element.displayValue }}</div>
      </van-cell>
    </van-cell-group>

    <!-- <div class="card">
      <van-cell-group>
        <van-cell
          title="保障期限"
          icon="calender-o"
          class="select-customer-icon"
        />
        <van-cell
          title="起保日期"
          :value="`${startDate}` + ' - ' + `${endDate}`"
        />
      </van-cell-group>
    </div> -->
    <div class="list" v-if="isInvoice" id="invoice">
      <van-cell-group>
        <van-cell :border="!isNoBorder" icon="comment-o">
          <div slot="title" class="title">开票信息</div>
        </van-cell>
        <van-cell>
          <div slot="title" class="content">
            发票抬头
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.companyName }}
          </div>
        </van-cell>
        <van-cell>
          <div slot="title" class="content">
            手机号
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.mobile }}
          </div>
        </van-cell>

        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            客户类型
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.customerType }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            纳税人类型
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.taxPayerType }}
          </div>
        </van-cell>

        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            发票类型
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.invoiceType }}
          </div>
        </van-cell>

        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            公司名称
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.companyName }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            纳税人识别码
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.taxPayerNo }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            注册地址
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.taxRegisterAddress }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            注册电话
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.taxRegisterTel }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            开户银行
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.depositeBankName }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            银行账号
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.accountNo }}
          </div>
        </van-cell>
        <van-cell>
          <div slot="title" class="content">
            电子邮箱
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.email }}
          </div>
        </van-cell>
      </van-cell-group>
    </div>

    <div class="tabbar">
      <div class="info">
        保费
        <span>{{ formatSumPremium }}</span>
        元
      </div>
      <div class="btn">
        <van-button type="info" @click="createOrder">确认投保</van-button>
      </div>
    </div>
  </div>
</template>
<script>
import globalNav from "../../../components/globalNav";

import { createFromPlaceOrder } from "@src/http/module/nonCar.js";

import * as native from "../../../utils/JSBridge";
import { placeOrder } from "../../../http/module/nonCar";

export default {
  components: {
    globalNav
  },
  data() {
    return {
      isWeiXin: false,
      orderId: "",
      isHome: true,
      globalNav: {
        title: "保单确认"
      },
      cc: localStorage.getItem("cc"),
      appliInfo: [], // 投保信息
      insuredInfo: [], // 被保信息
      startDate: "",
      endDate: "",
      elementList: [],
      invoiceInfo: {
        accountNo: "",
        companyName: "", //发票抬头
        copyDataFromType: "", //同投被保人
        customerType: "", //客户类型
        depositeBankName: "", //开户银行
        email: "", //邮箱
        invoiceType: "", //发票类型
        mobile: "", //移动电话
        taxPayerNo: "", //纳税人识别号
        taxPayerType: "", //纳税人类型
        taxRegisterAddress: "", //注册地址
        taxRegisterTel: "" //注册电话
      },
      formatSumPremium: 0,
      prTitle: "",
      isNoBorder: true,
      isInvoice: false,
      invoiceFlag: 0 //("0:无发票信息,1:个人，2：企业")
    };
  },
  mounted() {
    const { orderId } = this.$route.query;
    const { title } = this.$store.state.config.detailData;
    this.prTitle = title;
    const { insuranceInfo, formCreateData } = this.$store.state.config;
    this.orderId = orderId;
    let {
      startDate,
      endDate,
      metaDataList,
      invoiceInfo,
      sumPremium,
      isInvoice,
      invoiceFlag
    } = insuranceInfo;
    let { formatForm } = formCreateData;
    this.startDate = startDate;
    this.endDate = endDate;
    this.elementList.splice(0, this.elementList.length);
    if (sumPremium && (sumPremium + "").indexOf(".") != -1) {
      this.formatSumPremium = this.formatNum(sumPremium);
    } else {
      this.formatSumPremium = this.formatNum(sumPremium.toFixed(2));
    }
    let arr = [];
    metaDataList.forEach(item => {
      for (let i = 0; i < arr.length; i++) {
        let el = arr[i];
        if (el.groupId == item.groupId) {
          el.elementList.push(item);
          return;
        }
      }
      arr.push({ groupId: item.groupId, elementList: [item] });
    });
    arr.forEach(el => {
      let obj = formatForm.find(item => {
        return item.groupId == el.groupId && item.type == "label";
      });
      el.groupName = obj ? obj.title : "";
      if (el.groupName) {
        this.elementList.push(el);
      }
    });
    this.isInvoice = isInvoice;
    this.invoiceFlag = invoiceFlag;
    this.invoiceInfo = JSON.parse(JSON.stringify(invoiceInfo));
    if (this.isInvoice) {
      this.invoiceInfo.customerType = this.$getCodeOrName(
        "customerTypeList",
        this.invoiceInfo.customerType
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

    const isWeiXin = this.$isWeiXin();
    this.isWeiXin = this.$isWeiXin();
    if (isWeiXin) {
      this.globalNav.isHome = false;
    }
  },

  methods: {
    // 回到上一页
    goback() {
      this.$router.push({
        path: "/nonCar/insuranceInfoInputFormcreate",
        query: { orderId: this.orderId }
      });
    },
    // 点击右侧标题按钮，回到home页面
    clickRight() {
      if (window.WebViewJavascriptBridge) {
        this.$native.openHome("", data => {});
      }
    },
    // 确认投保
    createOrder() {
      let { isAgent } = this.$store.state.config.productInfo;
      let {
        metaDataList,
        startDate,
        endDate,
        timeLimit,
        sumPremium,
        invoiceInfo,
        isInvoice
      } = this.$store.state.config.insuranceInfo;
      let placeOrderData = {};
      for (let k in this.$store.state.config.productInfo) {
        placeOrderData[k] = this.$store.state.config.productInfo[k];
      }
      if (isAgent != "1") {
        delete placeOrderData.agentInfo;
      }
      delete placeOrderData.sumPremium;
      delete placeOrderData.metaDataList;
      placeOrderData.startDate = startDate;
      placeOrderData.endDate = endDate;
      placeOrderData.timeLimit = timeLimit;
      placeOrderData.sumPremium = sumPremium;
      placeOrderData.invoiceInfo = invoiceInfo;
      placeOrderData.metaDataList = [...metaDataList];
      placeOrderData.isTemp = 0;
      if (!isInvoice) {
        delete placeOrderData.invoiceInfo;
      }
      // const { orderParams } = this.$route.query;
      // let params = JSON.parse(orderParams);
      // params.orderId = this.orderId;
      // // params.sumPremium = 328; // 临时
      // params.isTemp = 0; // 暂存标识  1暂存 0提交核心
      // params.formulaSize = 1; // 临时
      console.log("placeOrderData---", placeOrderData);

      createFromPlaceOrder(placeOrderData).then(res => {
        if (res.code == 1) {
          localStorage.setItem("res", JSON.stringify(res));
          console.log("11111");
          const { order } = res.data;
          // orderStatus
          // "0": "暂存"
          // "1": "待核保"
          // "2": "已核保,核保成功"
          // "3": "核保失败"
          // "4": "待支付"
          // "5": "支付中"
          // "6": "已支付"
          // "7": "生效,保障中"
          // "8":刪除
          // "9": "核保中"
          // "10":已失效
          //"insuranceCategory"   0 ： 意健    2 财产险
          //
          // if (order.orderStatus == 1) {
          //   this.$router.push({
          //     name: "underwritten",
          //     query: {
          //       orderId: this.orderId,
          //       insuranceCategory: order.insuranceCategory
          //     }
          //   });
          // } else
          if (order.orderStatus == 7) {
            this.$router.push({
              name: "guaranteed",
              query: {
                orderId: this.orderId,
                insuranceCategory: order.insuranceCategory
              }
            });
          } else if (order.orderStatus == 2 || order.orderStatus == 1) {
            // res.data.insureNo
            // isNeedUploadImg 判断是否需要上传影像
            if (order.isNeedUploadImg != "0") {
              this.$dialog
                .confirm({
                  message: "尊敬的客户，您的投保订单已生成，可以在线上传影像",
                  confirmButtonText: "确认上传",
                  cancelButtonText: "取消"
                })
                .then(() => {
                  //调用接口参数
                  const jsonObject = {
                    url: "",
                    shareUrl: "", //TODO suncao 影像上传
                    titleBarTag: "1",
                    titleBar: "影像上传",
                    sharePlatform: ["0", "1"], //0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
                    shareType: "url",
                    title: "影像上传",
                    imgURL: "",
                    image: "",
                    desc: "尊敬的客户，您的投保订单已生成，可以在线上传影像"
                  };

                  //调用壳方法，传递调用参数和接受响应数据
                  native.openWebView(jsonObject, data => {
                    //Toast("openWebView接口调用成功，壳响应数据:" + data);
                  });
                })
                .catch(() => {
                  return;
                });
            }
            // isNeedSign 判断是否需要签名
            if (order.isNeedSign) {
              // 判断签名状态
              if (order.signStatus == "0") {
                // this.$dialog
                //   .confirm({
                //     message:
                //       "尊敬的客户，您的投保订单已生成，可以在线签名确认。",
                //     confirmButtonText: "确认签名",
                //     cancelButtonText: "取消"
                //   })
                //   .then(() => {
                //     // const signParams = {
                //     //   insureNo: order.insureNo || "",
                //     //   sumPremium: params.sumPremium,
                //     //   mobile: order.mobile,
                //     //   productName: order.productName,
                //     //   applicantName: params.appliName,
                //     //   insuredName: params.insuredName,
                //     //   insuredMobile: params.insuredMobile,
                //     //   isIssueAfterPay: order.isIssueAfterPay,
                //     //   isFlag: "P"
                //     // };
                //     // if (params.invoiceInfo) {
                //     //   signParams.invoiceType = params.invoiceInfo.invoiceType;
                //     // }
                //     this.$router.push({
                //       name: "prSign",
                //       query: { insureNo: order.insureNo }
                //     });
                //   })
                //   .catch(() => {
                //     return;
                //   });
                this.$router.push({
                  name: "prSign",
                  query: { insureNo: order.insureNo }
                });
              }
            }
          }
        } else {
          this.$dialog.alert({
            message: res.message || res.msg
          });
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
    }
  }
};
</script>
<style lang="less" scoped>
#insuranceinfoConfirm {
  padding: 60px 0;
  height: min-content !important ;
  .card {
    margin-top: 0.3rem;
  }
  .tabbar {
    display: flex;
    width: 100%;
    position: fixed;
    bottom: 0;
    .info {
      flex: 1;
      background-color: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 16px;

      span {
        color: #fc820e;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
      }
    }
    .btn {
      flex: 1;
      button {
        width: 100%;
      }
    }
  }

  .title-container {
    // height: 56px;
    padding: 15px;
    // margin: 0 0 10px 0;
    background-color: white;
    display: flex;
    align-items: center;
    flex: 1;
    position: relative;
    .topLeft {
      width: 103px;
      height: 63px;
      img {
        width: 73px;
        height: auto;
      }
    }
    .topRight {
      display: flex;
      flex-direction: column;
    }
    .icon {
      position: absolute;
      right: 26px;
      top: 26px;
      img {
        width: 18px;
        height: auto;
      }
    }
  }
  .title {
    font-size: 18px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    line-height: 25px;
    width: 80%;
  }
  .time {
    font-size: 13px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
    line-height: 19px;
  }
  .select-customer-icon {
    color: rgba(59, 138, 254, 1);
    div {
      font-size: 18px;
      font-family: PingFangSC-Semibold, PingFang SC;
      font-weight: 600;
      color: #333333;
      line-height: 25px;
    }
  }
  /deep/#rightFather {
    padding: 0;
  }
  /deep/.van-nav-bar__right {
    bottom: 50%;
    transform: translateY(50%);
  }
  .list {
    background: rgba(255, 255, 255, 1);
    border-radius: 4px;
    .title {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 22px;
    }
    .content {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
      line-height: 30px;
    }
    .red {
      color: #fc820e;
    }
  }
  #invoice {
    /deep/.van-cell__title {
      width: 40%;
      flex: none;
    }
    /deep/ .van-cell__value {
      width: 60%;
      flex: none;
    }
  }
}
</style>
