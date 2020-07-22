/* zhangminjie 订单详情-待核保 */
<template>
  <div class="orderDetailUnderwritten" id="orderDetailUnderwritten">
    <orderDetailNav statusName="待核保" @goBackEvent="goBack"></orderDetailNav>
    <orderDetailTopUnusedAndGuaranteed
      :insuranceInfo="insuranceInfo"
      :orderStauts="orderStauts"
      :insuranceType="insuranceType"
    ></orderDetailTopUnusedAndGuaranteed>
    <orderDetailMidContentNew
      :guaranteedCollageContent="formulaList"
      :insuranceCost="insuranceCost"
      :person="person"
      :insuranceType="insuranceType"
      :vechileInfoList="vechileInfoList"
      :personList="personList"
      @redirectMoreInfoPageEvent="redirectMoreInfoPage"
    ></orderDetailMidContentNew>
    <orderDetailStatementUnused
      :orderStauts="orderStauts"
      :insuranceType="insuranceType"
      :isUpload="isUpload"
      @submitUnderwritingEvent="submitUnderwriting"
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
import { storeOrderDetail, imageUpload } from "@src/http/module/order.js";
import { underwriting, applyPay } from "@src/http/module/nonCar.js";

Vue.use(Card);
export default {
  name: "orderDetailUnderwritten",
  components: {
    orderDetailMidContentNew,
    orderDetailStatementUnused,
    orderDetailTopUnusedAndGuaranteed,
    orderDetailNav
  },
  data: () => ({
    isUpload: false,
    isCollage: false,
    orderStauts: 1, //待核保
    insuranceType: 1, //1 财产险 2 健康险
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
    isSeeFee: "1"
  }),

  created() {
    this.getOrderInfo();
  },
  methods: {
    //TODO suncao 提交核保
    async submitUnderwriting() {
      let _this = this;
      let res = await underwriting({ insureNo: this.insureNo });
      if (res.code == "1") {
        Toast({ message: "核保成功", type: "success", position: "bottom" });
        if (this.isSeeFee == "1") {
          // 获取支付号
          _this.getApplyPay(this.insureNo);
        } else {
          this.$dialog.alert({
            title: "投保出单成功",
            message: "您的保单已生成，保单号为" + res.data.policyNo,
            confirmButtonText: "查看订单",
            callback: () => {
              this.$router.push({
                name: "proOrderDetailGuaranteed",
                query: { insureNo: this.insureNo }
              });
            }
          });
          // this.$globalBack();
        }
      } else {
        this.$toast.fail(res.msg || res.message);
      }
    },
    // 获取支付号
    async getApplyPay() {
      let res = await applyPay({ insureNo: this.insureNo });
      console.dir("获取支付号成功");
      console.log(res);
      if (res.code == "1") {
        this.$toast.success(res.msg);

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
                insuranceType: 1 //保险类别 1财产险，2个人版，3家庭版
              })
            }
          });
        }
      } else {
        this.$toast.fail(res.msg || res.message);
      }
    },
    //TODO suncao  上传影像
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
      this.$router.push({
        path: "/order/orderList/orderList?moudle=1&status=1"
      });
      // this.$globalBack();
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
            this.orderStauts = parseInt(res.data.status);
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
              isSeeFee
            } = res.data;
            this.isSeeFee = isSeeFee;
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
      this.insureNo = this.$route.query.insureNo;
      // this.insureNo = "TYEF20530126020000000206";
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
    }
  }
};
</script>

<style lang="less" scoped>
#orderDetailUnderwritten {
  width: 100%;
  // height: calc(100vh - 100px);
  background: rgba(247, 247, 247, 1);
  // overflow: scroll;
  height: auto;
  padding: 95px 0;
}
</style>
