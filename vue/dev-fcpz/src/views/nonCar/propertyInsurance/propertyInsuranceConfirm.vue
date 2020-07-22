<!--
 * @Description: 财产险--保单确认
 * @Date: 2020-03-11 14:25:45
 * @Author: zhagnminjie
 * @LastEditTime: 2020-05-13 18:15:36
   初始化判断有无保单号-》校验影像-》成功-》更新状态为待核保
   点击投保: 判断有无投保单号-》有-》直接调核保-》申请支付号-》跳转到支付页面
                              否-》下单-》上传影像
 -->
<template>
  <div id="propertyInsurancePolicyConfirmation">
    <div class="hello">
      <van-nav-bar
        fixed
        :style="colorStyle"
        :title="globalNav.title"
        left-arrow
        @click-left="backToPrePage"
        @click-right="onClickRight"
      >
        <template #left>
          <van-icon class="rigthIcon" name="arrow-left" size="22" />
        </template>
        <template #right>
          <van-icon class="rigthIcon" name="wap-home-o" size="22" />
        </template>
      </van-nav-bar>
      <div class="navigatorBottom" />
    </div>
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
        <div class="icon" @click="backToPrePage" v-show="!proposalNo">
          <img src="@/assets/common/edit.png" alt="edit" />
        </div>
      </div>
      <person-information-card-property
        type="policyHolder"
        :imgSrc="toubao"
      ></person-information-card-property>
      <person-information-card-property
        type="recognizee"
        :imgSrc="beibao"
      ></person-information-card-property>
      <person-information-card-property
        type="vehicle"
        :imgSrc="car"
      ></person-information-card-property>
      <person-information-card-property
        v-if="isShowEleInvoice"
        type="invoiceInfo"
        :imgSrc="beibao"
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
      <van-col class="confirm-button white" @click="apply(1)"
        >上传影像(<span class="small">行驶证、投保单必传</span>)</van-col
      >
      <van-col class="confirm-button" @click="apply">确认投保</van-col>
    </van-row>
  </div>
</template>

<script>
import personInformationCardProperty from "../common/personInformationCardProperty";
import { Button, NavBar, Dialog, Toast } from "vant";
import {
  placeOrder,
  imageCheck,
  underwriting,
  applyPay,
  updateUnderwritingStatus
} from "@src/http/module/nonCar.js";
import { imageUpload } from "@src/http/module/order.js";
import { trackMap } from "@src/utils/index.js";

export default {
  components: {
    [personInformationCardProperty.name]: personInformationCardProperty,
    [Button.name]: Button,
    [Dialog.name]: Dialog,
    [NavBar.name]: NavBar
  },
  data: () => ({
    title: "货运无忧",
    globalNav: {
      title: "保单确认"
    },
    startDate: "",
    endDate: "",
    formatPremium: "",
    requestData: {},
    proposalNo: "",
    isShowEleInvoice: true,
    status: 0,
    isNeedCheck: true,
    premium: "",
    toubao: require("@/assets/nonCar/worryEdit/toubao.png"),
    beibao: require("@/assets/nonCar/worryEdit/beibao.png"),
    car: require("@/assets/nonCar/worryEdit/car.png"),
    colorStyle: {
      background:
        "linear-gradient(125deg,rgba(30, 70, 246, 1) 0%,rgba(39, 154, 255, 1) 100%)"
    }
  }),
  created() {
    this.initData();
    this.watermarket();
  },
  destroyed() {},
  methods: {
    watermarket() {
      this.$native.showOrCloseWatermarket(true, data => {});
    },
    initData() {
      let insuranceInfo = this.$store.state.nonCar.shareData.insuranceInfo;
      let productInfo = this.$store.state.nonCar.shareData.productInfo;
      let query = this.$route.query;
      let { isNeedEleInvoice } = query;
      let { formatPremium, sumPremium } = productInfo;
      let { startDate, endDate, proposalNo } = insuranceInfo;
      this.startDate = startDate;
      this.endDate = endDate;
      this.formatPremium = formatPremium;
      this.premium = this.formatNum(parseInt(sumPremium).toFixed(2));
      this.isShowEleInvoice = isNeedEleInvoice;
      this.proposalNo = proposalNo;

      // console.log(126, insuranceInfo);
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
    // 回到首页
    onClickRight() {
      this.$native.openHome("", data => {});
    },
    // 返回上一页
    backToPrePage() {
      let obj = {
        proposalNo: ""
      };
      this.$store.dispatch("nonCar/updateShareData", {
        info: obj,
        type: 2
      });
      if (this.proposalNo) {
        this.$native.showOrCloseWatermarket(false, data => {});
        this.$router.push({
          path: "/order/orderList/orderList?moudle=1&status=1"
        });
      } else {
        this.$globalBack();
      }
    },
    //  上传影像的弹框
    goUploadPage() {
      Dialog.confirm({
        title: "是否立即上传影像",
        message: "该产品需要上传影像，上传完投保单、行驶证之后才能提交核保",
        confirmButtonText: "上传影像",
        confirmButtonColor: "rgba(59, 138, 254, 1);",
        cancelButtonText: "稍后上传"
      })
        .then(() => {
          if (!this.proposalNo) {
            this.doPlaceOrder().then(res => {
              if (res.code == "1") {
                this.proposalNo = res.data.proposalNo;
                // this.$store.dispatch("nonCar/clearShareData");
                let uploadParam = {
                  userCode: localStorage.getItem("userCode"),
                  comCode: localStorage.getItem("comCode"),
                  bussNo: this.proposalNo,
                  busiDate: new Date().getFullYear() + "0000",
                  imgType: "UW_Y",
                  flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0"
                };
                //TODO suncao跳转影像上传页面
                imageUpload(uploadParam).then(res => {
                  if (res.code == "1") {
                    // this.$store.dispatch("nonCar/clearShareData");
                    if (res.data && res.data.url) {
                      console.log(res.data.url);
                      let param = {
                        url: res.data.url,
                        shareUrl: res.data.url,
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
                      console.log("请求壳子的参数===", JSON.stringify(param));
                      this.$native.openWebView(param, data => {});
                      // window.location.href = res.data.url;
                    }
                    //TODO suncao调用原生的方法，打开影像上传页面
                  }
                  if (res.code == " G0001") {
                    Toast.allowMultiple();
                    Toast({
                      forbidClick: true,
                      message: res.message,
                      type: "fail",
                      duration: 3000
                    });
                    setTimeout(() => {
                      Toast.clear();
                    }, 3000);
                  }
                });
              } else {
                this.$dialog.alert({
                  message: res.msg
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
            imageUpload(uploadParam).then(res => {
              if (res.code == "1") {
                // this.$store.dispatch("nonCar/clearShareData");
                if (res.data && res.data.url) {
                  console.log(res.data.url);
                  let param = {
                    url: res.data.url,
                    shareUrl: res.data.url,
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
                  console.log("请求壳子的参数===", JSON.stringify(param));
                  if (window.WebViewJavascriptBridge) {
                    this.$native.openWebView(param, data => {});
                  } else {
                    window.location.href = res.data.url;
                  }
                }
                //TODO suncao调用原生的方法，打开影像上传页面
              }
            });
          }
        })
        .catch(() => {
          if (!this.proposalNo) {
            this.doPlaceOrder().then(res => {
              if (res.code == "1") {
                this.$router.push({
                  path: "/order/orderList/orderList?moudle=1&status=1"
                });
              } else {
                Toast.allowMultiple();
                Toast({
                  forbidClick: true,
                  message: "下单失败",
                  type: "fail",
                  duration: 3000
                });
                setTimeout(() => {
                  Toast.clear();
                }, 3000);
              }
            });
          } else {
            this.$router.push({
              path: "/order/orderList/orderList?moudle=1&status=1"
            });
          }
        });
    },
    // 执行下单
    async doPlaceOrder() {
      let obj = {};
      let productInfo = this.$store.state.nonCar.shareData.productInfo;
      let insuranceInfo = this.$store.state.nonCar.shareData.insuranceInfo;
      for (let key in productInfo) {
        obj[key] = productInfo[key];
      }
      for (let key in insuranceInfo) {
        if (this.isShowEleInvoice && key == "invoiceInfo") {
          obj[key] = insuranceInfo[key];
        }
        obj[key] = insuranceInfo[key];
      }
      if (!obj.orderNo) {
        delete obj.orderNo;
      }

      delete obj.copyDataFromType;
      delete obj.proposalNo;
      if (productInfo.isAgent == "0") {
        delete obj.agentInfo;
      }
      if (!this.isShowEleInvoice) {
        delete obj.invoiceInfo;
      }
      return await placeOrder(obj);
    },

    singleUpLoadImg() {
      let uploadParam = {
        userCode: localStorage.getItem("userCode"),
        comCode: localStorage.getItem("comCode"),
        bussNo: this.proposalNo,
        busiDate: new Date().getFullYear() + "0000",
        imgType: "UW_Y",
        flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0"
      };
      //TODO suncao跳转影像上传页面
      imageUpload(uploadParam).then(res => {
        if (res.code == "1") {
          // this.$store.dispatch("nonCar/clearShareData");
          if (res.data && res.data.url) {
            console.log(res.data.url);
            let param = {
              url: res.data.url,
              shareUrl: res.data.url,
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
            console.log("请求壳子的参数===", JSON.stringify(param));
            console.log(
              "是否在壳子里面====" +
                JSON.stringify(window.WebViewJavascriptBridge)
            );
            if (window.WebViewJavascriptBridge) {
              this.$native.openWebView(param, data => {});
            } else {
              window.location.href = res.data.url;
            }
          }
          //TODO suncao调用原生的方法，打开影像上传页面
        }
      });
    },
    // 立即投保
    apply(isImgBtn) {
      //埋点
      trackMap("c_no_car_ensure_insure", "非车险确认投保确认", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "非车险出单"
      });
      if (!this.proposalNo) {
        this.doPlaceOrder().then(res => {
          if (res.code == "1") {
            let obj = {
              proposalNo: res.data.proposalNo,
              orderNo: ""
            };
            this.$store.dispatch("nonCar/updateShareData", {
              info: obj,
              type: 2
            });
            this.proposalNo = res.data.proposalNo;
            if (isImgBtn == "1") {
              //点击了影像上传按钮
              return this.singleUpLoadImg();
            }
            this.goUploadPage();
          } else {
            this.$dialog.alert({
              message: res.msg
            });
          }
        });
      } else {
        if (isImgBtn == "1") {
          //点击了影像上传按钮
          return this.singleUpLoadImg();
        }
        if (this.isNeedCheck) {
          this.checkImgUpload(1);
        } else {
          this.submitUnderwriting();
        }
      }
    },
    //TODO suncao 提交核保
    async submitUnderwriting() {
      let _this = this;
      let res = await underwriting({ insureNo: this.proposalNo });
      if (res.code == "1") {
        Toast({ message: "核保成功", type: "success" });
        // TODO suncao 如果是非见费的，提示线下支付
        let isIssueAfterPay = this.$store.state.nonCar.shareData
          .isIssueAfterPay;
        if (isIssueAfterPay == "0") {
          this.$dialog.alert({
            title: "投保出单成功",
            message: "您的保单已生成，保单号为" + res.data.policyNo,
            confirmButtonText: "查看订单",
            callback: () => {
              this.$router.push({
                name: "proOrderDetailGuaranteed",
                query: { insureNo: this.proposalNo }
              });
            }
          });
          return;
        } else {
          // 获取支付号
          _this.getApplyPay(this.proposalNo);
        }
      } else {
        this.$toast.fail(res.msg || res.message);
      }
    },
    // 获取支付号
    async getApplyPay() {
      let res = await applyPay({ insureNo: this.proposalNo });
      // console.dir("获取支付号成功");
      if (res.code == "1") {
        Toast({
          message: res.msg,
          type: "success"
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
                insuranceType: 1
              })
            }
          });
        }
      } else {
        this.$toast.fail(res.msg || res.message);
      }
    },

    checkImgUpload(type) {
      //TODO suncao校验影像是否上传
      let checkParam = {
        userCode: localStorage.getItem("userCode"),
        comCode: localStorage.getItem("comCode"),
        bussNo: this.proposalNo,
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
            //更新核保状态
            if (res.code == "1") {
              //TODO suncao如果已经上传 同步后台接口告知影像已经上传-跳转到待核保页面 把订单号带过去
              // console.log(res);
              this.isNeedCheck = false;
              this.updateStatus({
                insureNo: this.proposalNo,
                isIssueAfterPay: this.$store.state.nonCar.shareData
                  .isIssueAfterPay
              }).then(request => {
                if (request.code == "1") {
                  this.isNeedCheck = false;
                  Toast.allowMultiple();
                  Toast({
                    forbidClick: true,
                    message: request.msg,
                    type: "success"
                  });
                  setTimeout(() => {
                    Toast.clear();
                  }, 2000);
                  console.log("更新核保状态成功");
                  if (type == 1) {
                    this.submitUnderwriting();
                  }
                  // if (type == 2) {
                  //   this.$router.push({
                  //     name: "orderLists"
                  //   });
                  // }
                } else {
                  this.isNeedCheck = true;
                  Toast.allowMultiple();
                  Toast({
                    forbidClick: true,
                    message: "更新核保状态失败",
                    type: "fail",
                    duration: 3000
                  });
                  setTimeout(() => {
                    Toast.clear();
                  }, 3000);
                }
              });
            }
          } else {
            this.isNeedCheck = true;
            this.goUploadPage();
          }
        }
        if (res.code == " G0001") {
          this.isNeedCheck = true;
          Toast.allowMultiple();
          Toast({
            forbidClick: true,
            message: res.message,
            type: "fail",
            duration: 3000
          });
          setTimeout(() => {
            Toast.clear();
          }, 3000);
        }
      });
    },
    // 更新核保状态
    async updateStatus(updateStatusParam) {
      return await updateUnderwritingStatus(updateStatusParam);
    }
  }
};
</script>

<style lang="less" scoped>
#propertyInsurancePolicyConfirmation {
  // padding-top: 65px;
  // height: calc(100vh - 120px);
  // overflow: scroll;
  // overflow-x: hidden;
  div {
    box-sizing: border-box;
  }
  .hello {
    height: 80px;
  }
  .small {
    font-size: 12px;
  }
  .van-nav-bar {
    background: linear-gradient(
      125deg,
      rgba(30, 70, 246, 1) 0%,
      rgba(39, 154, 255, 1) 100%
    );
    color: white;
    font-size: 0.48rem;
    height: 76px;
  }
  .navTitle {
    color: white;
    font-size: 0.48rem;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
  }
  .van-nav-bar--fixed {
    z-index: 99;
    height: 76px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    z-index: 999;
  }

  .van-nav-bar__left {
    bottom: 15px;
  }

  .van-nav-bar__right {
    bottom: 17px;
  }

  .van-nav-bar__title {
    color: white;
    font-size: 18px;
    font-family: PingFangSC-Medium, PingFang SC;
  }

  .van-nav-bar .van-icon {
    color: white;
  }

  .van-nav-bar__text {
    color: white;
  }

  #rightFather {
    height: 22px;
    width: 22px;
    padding: 11px 0px 11px 22px;
  }

  #rightFather > img {
    display: block;
    width: 22px;
    height: 22px;
    margin: 0 auto;
  }

  .navigatorBottom {
    width: 100vw;
    height: 15px;
    border-radius: 15px 15px 0px 0px;
    background-color: #ffffff;
    top: 65px;
    z-index: 1000;
    position: fixed;
  }
}
.top-container {
  width: 100%;
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
    width: auto;
    height: 63px;
    img {
      width: 73px;
      height: auto;
    }
  }
  .topRight {
    margin-left: 5px;
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
