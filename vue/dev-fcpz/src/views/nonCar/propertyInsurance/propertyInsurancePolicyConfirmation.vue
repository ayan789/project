<!--
 * @Description: 财产险--保单确认
 * @Date: 2020-03-11 14:25:45
 * @Author: zhagnminjie
 * @LastEditTime: 2020-04-07 21:51:54
 -->
<template>
  <div id="propertyInsurancePolicyConfirmation">
    <globalNav :title="globalNav.title" @clickBack="backToPrePage"></globalNav>
    <div class="top-container">
      <div class="title-container">
        <div class="topLeft">
          <img
            src="@/assets/nonCar/propertyInsurance/infoInput.png"
            alt="header"
          />
        </div>
        <div class="topRight">
          <div class="title">{{ title }}</div>
          <div class="time">保障期限: {{ startDate }}至{{ endDate }}</div>
        </div>
        <div class="icon" @click="backToPrePage">
          <img src="@/assets/common/edit.png" alt="edit" />
        </div>
      </div>
      <person-information-card-property
        type="policyHolder"
      ></person-information-card-property>
      <person-information-card-property
        type="recognizee"
      ></person-information-card-property>
      <person-information-card-property
        type="vehicle"
      ></person-information-card-property>
      <person-information-card-property
        v-if="isShowEleInvoice"
        type="invoiceInfo"
      ></person-information-card-property>
      <van-row type="flex" align="center" class="confirm-info confirm-info100">
        <van-col class="confirm-info-t">总计保费</van-col>
        <van-col class="confirm-info-v">{{ premium }}元</van-col>
      </van-row>
      <p class="tip">
        说明：货运无忧保险产品，需先上传影像，再确认投保。
      </p>
    </div>

    <van-row
      type="flex"
      justify="space-between"
      align="center"
      class="bottom-confirm"
    >
      <van-col class="confirm-button white" @click="goUploadPage(true)"
        >上传影像(行驶证必传)</van-col
      >
      <van-col class="confirm-button" @click="apply">确认投保</van-col>
    </van-row>
  </div>
</template>

<script>
import personInformationCardProperty from "../common/personInformationCardProperty";
import { Button, NavBar, Dialog, Toast } from "vant";
import globalNav from "../../../components/globalNav";
import {
  placeOrder,
  imageCheck,
  getUploadUrl,
  updateUnderwritingStatus
} from "@src/http/module/nonCar.js";
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    [personInformationCardProperty.name]: personInformationCardProperty,
    [Button.name]: Button,
    [Dialog.name]: Dialog,
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    title: "货运无忧",
    globalNav: {
      title: "保单确认"
    },
    startDate: "",
    endDate: "",
    premium: "",
    requestData: {},
    proposalNo: "",
    isShowEleInvoice: true
  }),
  created() {
    this.initData();
    this.watermarket();
  },
  destroyed() {
    const obj = {
      isOpenOrClose: "0",
      type: "17"
    };
  },
  methods: {
    watermarket() {
      const obj = {
        isOpenOrClose: "1",
        type: "17"
      };
      this.$native.showOrCloseWatermarket(true, data => {});
    },
    initData() {
      let {
        startDate,
        endDate,
        premium,
        isNeedEleInvoice
      } = this.$store.state.nonCar.personInformation;
      this.startDate = startDate;
      this.endDate = endDate;
      this.premium = premium;
      this.isShowEleInvoice = isNeedEleInvoice;
    },

    // 返回上一页
    backToPrePage() {
      this.$globalBack();
    },
    //  上传影像的弹框
    goUploadPage(isNeedPlaceOrder) {
      Dialog.confirm({
        title: "是否立即上传影像",
        message: "该产品需要上传影像，上传完投保单、行驶证之后才能提交核保",
        confirmButtonText: "上传影像",
        confirmButtonColor: "rgba(59, 138, 254, 1);",
        cancelButtonText: "稍后上传"
      })
        .then(() => {
          if (isNeedPlaceOrder) {
            this.doPlaceOrder().then(res => {
              if (res.code == "1") {
                this.proposalNo = res.data.proposalNo;
                this.$store.dispatch("nonCar/clearPersonInfo");
                this.$store.dispatch("nonCar/clearInsureInfo", true);
                let uploadParam = {
                  userCode: localStorage.getItem("userCode"),
                  comCode: localStorage.getItem("comCode"),
                  bussNo: this.proposalNo,
                  busiDate: new Date().getFullYear() + "0000",
                  imgType: "UW_Y",
                  flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0"
                };
                //TODO suncao跳转影像上传页面
                getUploadUrl(uploadParam).then(res => {
                  if (res.code == "1") {
                    //TODO suncao调用原生的方法，打开影像上传页面
                    this.$store.dispatch("nonCar/clearPersonInfo");
                    this.$store.dispatch("nonCar/clearInsureInfo", false);
                    console.log(res.data.url);
                    let param = {
                      url: res.data.url,
                      type: "0",
                      titleBarTag: "1",
                      titleBar: "影像上传",
                      sharePlatform: ["0", "1"],
                      shareType: "url",
                      title: "投保订单影像上传",
                      imgURL: "",
                      image: "",
                      url: res.data.url,
                      desc: "尊敬的客户，您的投保订单已生成，可以在线上传影像"
                    };
                    this.$native.openWebView(param, data => {});
                    // window.location.href = res.data.url;
                  }
                });
              } else {
                let uploadParam = {
                  userCode: localStorage.getItem("userCode"),
                  comCode: localStorage.getItem("comCode"),
                  bussNo: this.proposalNo,
                  busiDate: new Date().getFullYear() + "0000",
                  imgType: "UW_Y",
                  flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0"
                };
                //TODO suncao跳转影像上传页面
                getUploadUrl(uploadParam).then(res => {
                  if (res.code == "1") {
                    //TODO suncao调用原生的方法，打开影像上传页面
                    this.$store.dispatch("nonCar/clearPersonInfo");
                    this.$store.dispatch("nonCar/clearInsureInfo", false);
                    console.log(res.data.url);
                    window.location.href = res.data.url;
                  }
                });
              }
            });
          } else {
            let uploadParam = {
              userCode: localStorage.getItem("userCode"),
              comCode: localStorage.getItem("comCode"),
              bussNo: this.proposalNo,
              busiDate: new Date().getFullYear() + "0000",
              imgType: "UW_Y",
              flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0"
            };
            //TODO suncao跳转影像上传页面
            getUploadUrl(uploadParam).then(res => {
              if (res.code == "1") {
                //TODO suncao调用原生的方法，打开影像上传页面
                this.$store.dispatch("nonCar/clearPersonInfo");
                this.$store.dispatch("nonCar/clearInsureInfo", false);
                console.log(res.data.url);
                window.location.href = res.data.url;
              }
            });
          }
        })
        .catch(() => {
          if (isNeedPlaceOrder) {
            this.doPlaceOrder().then(res => {
              if (res.code == "1") {
                this.$store.dispatch("nonCar/clearPersonInfo");
                this.$store.dispatch("nonCar/clearInsureInfo", false);
                this.$native.showOrCloseWatermarket(false, data => {});
                this.$router.push({
                  path: "/order/orderList/orderList?moudle=1&status=1"
                });
              }
            });
          }
        });
    },
    // 执行下单
    async doPlaceOrder() {
      let { requestPlaceOrderData } = this.$store.state.nonCar;
      return await placeOrder(requestPlaceOrderData);
    },
    // 立即投保
    apply() {
      let {
        requestPlaceOrderData,
        personInformation
      } = this.$store.state.nonCar;
      this.doPlaceOrder().then(res => {
        if (res.code == "1") {
          this.proposalNo = res.data.proposalNo;
          let obj = { personInformation, requestPlaceOrderData };
          this.$store.dispatch("nonCar/savePersonInformation", obj);
          // this.$store.dispatch("nonCar/clearPersonInfo");
          // this.$store.dispatch("nonCar/clearInsureInfo", false);
          //this.goUploadPage(false);

          updateUnderwritingStatus({ insureNo: res.data.proposalNo }).then(
            res => {
              if (res.code == "1") {
                //TODO suncao如果已经上传 同步后台接口告知影像已经上传-跳转到待核保页面 把订单号带过去
                this.$native.showOrCloseWatermarket(false, data => {});
                this.$router.push({
                  name: "proOrderDetailUnderwritten",
                  query: { insureNo: this.$route.query.insureNo }
                });
              } else {
                this.$dialog.alert({
                  message: res.message || res.msg
                });
              }
            }
          );
        } else {
          this.$dialog.alert({
            message: res.message || res.msg
          });
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
#propertyInsurancePolicyConfirmation {
  padding-top: 65px;
  height: calc(100vh - 120px);
  // overflow: scroll;
  overflow-x: hidden;
}
.top-container {
  width: 375px;
  height: 71px;
  background: rgba(255, 255, 255, 1);
  border-radius: 20px 20px 0px 0px;
}
.title-container {
  height: 56px;
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
.tip {
  width: 300px;
  height: 45px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  line-height: 45px;
  margin: 0 auto;
}

.title {
  font-size: 18px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 25px;
}
.time {
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 19px;
}
.bottom-confirm {
  position: fixed;
  bottom: 0;
  left: 0;
  height: 48px;
  width: 100%;
  background-color: white;
  margin-top: 36px;
}

.confirm-info {
  width: 186px;
  background-color: white;
  padding: 0px 15px 0px 22px;
  line-height: 46px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
}
.confirm-info-t {
  font-size: 12px;
}
.confirm-info-v {
  font-size: 17px;
  color: #fc820e;
  margin-left: 5px;
}
.confirm-info100 {
  width: 100%;
  height: 53px;
  margin-top: -9px;
}
.confirm-button {
  width: 188px;
  height: 46px;
  background: rgba(59, 138, 254, 1);
  line-height: 46px;
  color: rgba(255, 255, 255, 1);
  text-align: center;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
}
.white {
  background-color: white;
  color: rgba(42, 138, 255, 1);
}
</style>
