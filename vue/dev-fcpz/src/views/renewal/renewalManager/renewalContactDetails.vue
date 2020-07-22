<template>
  <div class="contacts">
    <div>
      <Global_globalNav
        title="保单详情"
        @clickBack="
          () => {
            this.$globalBack();
          }
        "
      ></Global_globalNav>
      <div class="contact-details">
        <div class="contact-details--basicInfo">
          <div class="title">
            <img src="../../../assets/renewal/d_car.png" alt="" />基本信息
          </div>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">投保人</van-col>
            <van-col span="12" class="details_content">{{
              resultData.appliName || ""
            }}</van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">车牌号</van-col>
            <van-col span="12" class="details_content">{{
              resultData.licenseNo || ""
            }}</van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">证件类型</van-col>
            <van-col span="12" class="details_content">{{
              resultData.identifyType | flfterIdentifyType
            }}</van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">证件号码</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.identifyNumber | filterMask(14, 0) }}
            </van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">手机号码</van-col>
            <van-col span="12" class="details_content">
              {{ isPersonSelf }}
              <img
                src="../../../assets/renewal/message.png"
                alt=""
                @click="sendMessage_"
              />
              <img
                src="../../../assets/renewal/phone.png"
                alt=""
                @click="callPhone"
              />
            </van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">固定号码</van-col>
            <van-col span="12" class="details_content">
              <a :href="`tel:${resultData.phoneNumber}`">{{ isPersonSelf_ }}</a>
            </van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="8" class="details_title">保单号</van-col>
            <van-col span="16" class="details_content">{{
              resultData.policyNo || ""
            }}</van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="8" class="details_title">保险期限</van-col>
            <van-col span="16" class="details_content">{{
              resultData.policyTime || ""
            }}</van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">厂牌型号</van-col>
            <van-col span="12" class="details_content">{{
              resultData.brandName || ""
            }}</van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">是否电子投保</van-col>
            <van-col span="12" class="details_content">{{
              resultData.electronicInsurance === "N" ? "否" : "是"
            }}</van-col>
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">电子投保状态</van-col>
            <van-col span="12" class="details_content">{{
              resultData.electronicInsuranceStatus | filterStatus
            }}</van-col>
          </van-row>
        </div>
        <div class="contact-details--premiumInfo">
          <div class="title">
            <img src="../../../assets/renewal/d_baofei.png" alt="" />保费信息
          </div>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">优惠保费合计</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.discount || "" }}元</van-col
            >
          </van-row>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">实收保费合计</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.duePremium || "" }}元</van-col
            >
          </van-row>
        </div>
        <div class="contact-details--RiskInfo">
          <div class="title">
            <img src="../../../assets/renewal/d_xianbie.png" alt="" />险种信息
          </div>
          <div
            v-for="(item, index) in this.resultData.kindResponseList"
            :key="index"
          >
            <van-row type="flex" justify="space-between">
              <van-col span="16" class="details_title">{{
                item.kindName
              }}</van-col>
              <van-col span="8" class="details_content"
                >{{ item.duePremium }}元</van-col
              >
            </van-row>
            <van-row type="flex" justify="space-between">
              <van-col span="16" class="details_title"
                >{{ item.kindName }}不计免赔</van-col
              >
              <van-col span="8" class="details_content"
                >{{ item.deductiblePremium }}元</van-col
              >
            </van-row>
          </div>
        </div>
        <div class="contact-details--carInfo">
          <div class="title">
            <img src="../../../assets/renewal/d_carinfo.png" alt="" />车辆信息
          </div>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">号牌类型</van-col>
            <van-col span="12" class="details_content">{{
              resultData.carResponse.licenseType | filterLicenseType
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">车辆种类</van-col>
            <van-col span="12" class="details_content">{{
              resultData.carResponse.carKindCode | filterCarType
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">使用性质</van-col>
            <van-col span="12" class="details_content">{{
              resultData.carResponse.useNatureName || ""
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">核定载客</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.carResponse.seatCount || "" }}人</van-col
            >
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">车架号</van-col>
            <van-col span="12" class="details_content">{{
              resultData.carResponse.frameNo || ""
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">核定载质量</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.carResponse.tonCount || "" }}千克</van-col
            >
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">排量</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.carResponse.exhaustScale || "" }}升</van-col
            >
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">功率</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.carResponse.powerScale || "" }}千瓦</van-col
            >
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">发动机号</van-col>
            <van-col span="12" class="details_content">{{
              resultData.carResponse.engineNo || ""
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">整备质量</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.carResponse.net || "" }}千克</van-col
            >
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">新车购置价</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.carResponse.purchasePrice || "" }}元</van-col
            >
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">初次登记日期</van-col>
            <van-col span="12" class="details_content">{{
              resultData.carResponse.enrollDate || ""
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">实际使用年限</van-col>
            <van-col span="12" class="details_content"
              >{{ resultData.carResponse.useYears || "" }}年</van-col
            >
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">车主</van-col>
            <van-col span="12" class="details_content">{{
              resultData.carOwner || ""
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="8" class="details_title">行驶区域</van-col>
            <van-col span="16" class="details_content">{{
              resultData.carResponse.runAreaCode || ""
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="8" class="details_title">经办人名称</van-col>
            <van-col span="16" class="details_content">{{
              resultData.carResponse.handlerName || ""
            }}</van-col>
          </van-row>
        </div>
      </div>
      <div class="contact-details--button">
        <!--  只有交商混合才显示 -->
        <van-button
          type="info"
          plain
          @click="queryDFA"
          v-if="resultData.compulsoryFlag === '1'"
        >
          {{ mark ? "交强险保单" : "商业险" }}
        </van-button>
        <van-button
          type="primary"
          color="#3B8AFE"
          @click="createProposaPost"
          v-if="
            ((paramsType != '2' && showButton === 'Y') ||
              appShowButton === 'Y') &&
              Object.keys(queryItem).length == 0
          "
          >生成计划书</van-button
        >
      </div>
    </div>
  </div>
</template>

<script>
//获取保单信息
import {
  queryRenewalPolicyInfo,
  createProposa
} from "@src/http/module/renewal.js";
//订单详情
import {
  doneTaskClick,
  addOrUpdateInteractions
} from "@src/http/module/customerApi.js";
import { Dialog } from "vant";
import { data } from "@src/utils/dataDictionary.js";
import { mapMutations } from "vuex";
import { trackMap } from "@src/utils/index.js";
export default {
  data: () => ({
    mark: true,
    queryData: {
      frameNo: "",
      licenseNo: "",
      orgId: "",
      policyNo: "",
      respEndDate: ""
    },
    resultData: {
      carResponse: {},
      kindResponseList: []
    }
  }),
  components: {
    [Dialog.Component.name]: Dialog.Component
  },
  filters: {
    //掩码
    filterMask(val, firstLen, endLen) {
      if (val) {
        let len = val.length;
        return (val =
          val.substr(0, firstLen) + "****" + val.substr(len - endLen));
      }
    },
    //电子保单状态
    filterStatus(val) {
      switch (val) {
        case "1":
          return "已完成电子投保标识";
        case "2":
          return "未完成电子投保";
        case "3":
          return "不能进行电子投保选择纸质投保";
        case "4":
          return "无需电子投保";
        case "5":
          return "客户主动放弃电子投保";
      }
    },
    filterLicenseType(val) {
      return data.licenseType[val] || "";
    },
    filterCarType(val) {
      return data.carKindCode[val] || "";
    },
    flfterIdentifyType(val) {
      return data.identifyType[val] || "";
    }
  },
  computed: {
    endDate: {
      get() {
        let { policyTime = "" } = { ...this.resultData };
        policyTime = policyTime.split("~")[1];
        policyTime = policyTime.split(" ")[1];
        return `${policyTime.split("-")[1]}月${policyTime.split("-")[2]}日`;
      }
    },
    showButton() {
      return this.$route.params.showButton || "N";
    },
    paramsType() {
      return this.$route.params.type;
    },
    isPersonSelf() {
      let userCode = this.$GetRequest("userCode", true)
        ? this.$GetRequest("userCode", true)
        : localStorage.getItem("userCode");
      let flag = "";
      if (this.$isNull(userCode)) {
        flag = false;
      } else {
        flag = userCode === this.resultData.handlerCode ? true : false;
      }
      if (flag) {
        return this.resultData.mobile;
      } else {
        if (!this.resultData.mobile) return "";
        let len = this.resultData.mobile.length;
        return (
          this.resultData.mobile.substr(0, 3) +
          "****" +
          this.resultData.mobile.substr(len - 4)
        );
      }
    },
    isPersonSelf_() {
      let userCode = this.$GetRequest("userCode", true)
        ? this.$GetRequest("userCode", true)
        : localStorage.getItem("userCode");
      let flag = "";
      if (this.$isNull(userCode)) {
        flag = false;
      } else {
        flag = userCode === this.resultData.handlerCode ? true : false;
      }
      if (flag) {
        return this.resultData.phoneNumber;
      } else {
        if (!this.resultData.phoneNumber) return "";
        let len = this.resultData.phoneNumber.length;
        let phone = this.resultData.phoneNumber.substr(0, len - 4);
        return phone + "****";
      }
    },
    /**
     * 客户模块跳续保详情
     * query item
     */
    queryItem() {
      return { ...this.$route.query.item } || {};
    },
    /**
     * 原生跳详情 车架号查询
     * ?frameNo=DGFDSH89730654876&compulsoryFlag=1&respEndDate=2020-04-25?showButton=Y
     */
    appFrameNo() {
      return this.$GetRequest("frameNo", true) || "";
    },
    appRespEndDate() {
      return this.$GetRequest("respEndDate", true) || "";
    },
    appCompulsoryFlag() {
      return this.$GetRequest("compulsoryFlag", true) || "";
    },
    appShowButton() {
      return this.$GetRequest("showButton", true) || "";
    }
  },
  async created() {
    await this.$globalSetLocalStorage();
    this.queryData = Object.assign({}, this.queryData, {
      frameNo: this.$route.params.frameno || this.appFrameNo || "",
      licenseNo: this.$route.params.licenseNo || "",
      orgId: this.$route.params.orgId || "",
      policyNo: this.$route.params.policyNo || this.queryItem.policyNo || "",
      //修改 默认交商标标识符未空
      compulsoryFlag: "",
      respEndDate: this.$route.params.respEndDate || this.appRespEndDate || "",
      userCode: this.$GetRequest("userCode", true)
        ? this.$GetRequest("userCode", true)
        : localStorage.getItem("userCode")
    });
    this.queryRenewalPolicyInfo_();
  },
  mounted() {
    //埋点
    if (Object.keys(this.queryItem).length) {
      trackMap("c_customer_serve_renewal_task_check", "客户服务续保任务查看", {
        c_salesperson_type: "N",
        c_market_type: "N",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "事件所属类别"
      });
    }
  },
  methods: {
    ...mapMutations("vehicle", [
      "setCarInfo",
      "setCarOwner",
      "setPolicyHolder",
      "setOrderId",
      "setRecognizee"
    ]),
    //拨打电话
    callPhone() {
      //调用接口参数
      const phoneNo = this.resultData.mobile;
      if (!phoneNo) {
        this.$dialog.alert({
          message: "没有电话号码！"
        });
        return false;
      }
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.getPhoneCall(phoneNo, data => {
        addOrUpdateInteractions({
          userCode: localStorage.getItem("userCode"),
          createUser: localStorage.getItem("userCode"),
          custNo: this.queryItem.custNo || "",
          remark: "",
          title: "打电话",
          type: "1"
        });
        if (JSON.stringify(this.queryItem) === "{}") return false;
        this.doneTaskClickPost();
        // }
      });
    },
    //发送短信
    sendMessage_() {
      if (!this.resultData.mobile) {
        this.$dialog.alert({
          message: "没有电话号码！"
        });
        return false;
      }
      let jsonObject = {
        messageDes: `尊敬的${this.resultData.appliName || ""}，您的爱车${this
          .resultData.licenseNo || ""}${
          this.paramsType === "3" ? "已" : "将"
        }于${this.endDate ||
          ""}保险到期，为使您的出行得到切实的保障，您可随时联系我。${JSON.parse(
          localStorage.getItem("userInfo")
        ).userName || ""}：${JSON.parse(localStorage.getItem("userInfo"))
          .userPhone || ""}`,
        phoneNo: `${this.resultData.mobile || ""}`
      };
      //已续保 和 认领 短信模板不用带入
      if (this.paramsType === "2" || this.paramsType === "4") {
        jsonObject = {
          messageDes: "",
          phoneNo: `${this.resultData.mobile || ""}`
        };
      }
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.sendMessage(jsonObject, data => {
        // this.$toast("sendMessage 接口调用成功，壳响应数据:" + data);
      });
    },
    //查看交强险保单
    queryDFA() {
      window.scroll(0, 0);
      this.queryData.compulsoryFlag = this.mark ? "2" : "3";
      this.mark = !this.mark;
      this.queryRenewalPolicyInfo_();
    },
    //生成计划书
    createProposaPost() {
      //埋点
      trackMap("c_my_renewal_generate_proposal", "我的续保生成计划书", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "续保"
      });
      createProposa({
        frameNo: this.$route.params.frameno || this.appFrameNo || "",
        orgId: this.$route.params.orgId || "",
        userCode: this.$GetRequest("userCode", true)
          ? this.$GetRequest("userCode", true)
          : localStorage.getItem("userCode"),
        compulsoryFlag:
          this.$route.params.compulsoryFlag || this.appCompulsoryFlag || "",
        phone: this.$route.params.phone || "",
        quoateType: this.$route.params.quoateType || "",
        respEndDate: this.$route.params.respEndDate || this.appRespEndDate || ""
      })
        .then(res => {
          if (res.code === "1") {
            if (res.data.jumpFlag === "2" || res.data.jumpFlag === "3") {
              // suncao 20200428
              this.setOrderId(res.data.orderId);
              this.$router.push({
                name: "vehicle",
                query: {
                  vehicleType:
                    res.data.vehicleKindCode == "11" &&
                    res.data.vehicleUseNatureCode == "85"
                      ? "1"
                      : this.checkCarType(res.data.vehicleKindCode)
                }
              });
            }
            //重复投保
            if (res.data.jumpFlag === "1" && res.data.errorCode === "10015") {
              this.$dialog.alert({
                message: res.data.errorMessage,
                showCancelButton: true,
                callback: action => {
                  if (action === "confirm") {
                    this.setOrderId(res.data.orderId);
                    this.$router.push({
                      name: "vehicle",
                      query: {
                        vehicleType:
                          res.data.vehicleKindCode == "11" &&
                          res.data.vehicleUseNatureCode == "85"
                            ? "1"
                            : this.checkCarType(res.data.vehicleKindCode)
                      }
                    });
                  } else if (action === "cancel") {
                  }
                }
              });
            }
          } else {
            this.$dialog.alert({
              message: res.msg
            });
          }
          if (JSON.stringify(this.queryItem) === "{}") return false;
          this.doneTaskClickPost();
        })
        .catch(err => {});
    },
    checkCarType(vehicleKindCode) {
      let carType = [
        {
          vehicleKindCode: ["11", "31", "21", "22"]
        },
        {
          vehicleKindCode: [
            "51",
            "52",
            "53",
            "61",
            "62",
            "63",
            "64",
            "9A",
            "9B",
            "AA",
            "AB",
            "AC",
            "AD",
            "AE",
            "BA",
            "BB",
            "BC",
            "BD",
            "BE",
            "CA",
            "CB",
            "CC",
            "CD",
            "CE"
          ]
        },
        {
          vehicleKindCode: [
            "411",
            "412",
            "413",
            "414",
            "42",
            "431",
            "432",
            "433",
            "434",
            "435",
            "437",
            "438",
            "44",
            "451",
            "452",
            "453",
            "46",
            "47",
            "48"
          ]
        }
      ];
      return (
        carType.findIndex(
          v => true === v.vehicleKindCode.includes(vehicleKindCode)
        ) + 2
      );
    },
    //完成动作
    async doneTaskClickPost() {
      await doneTaskClick({
        custNo: this.queryItem.custNo || "",
        remindId: this.queryItem.id || "",
        userCode: this.$GetRequest("userCode", true)
          ? this.$GetRequest("userCode", true)
          : localStorage.getItem("userCode")
      });
    },
    //查询保单信息
    async queryRenewalPolicyInfo_() {
      await queryRenewalPolicyInfo(this.queryData).then(res => {
        if (res.code === "1" && res.data) {
          this.resultData = { ...{} };
          this.resultData = { ...res.data };
        } else {
          this.$dialog.alert({
            message: res.msg || res.message,
            callback: () => {
              this.$globalBack();
            }
          });
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.contacts {
  // overflow: hidden;
}
.contact-details {
  // background: #f7f7f7;
  // height: calc(100vh - 125px);
  // overflow: scroll;
  // overflow-scrolling: touch;
  // -webkit-overflow-scrolling: touch;
  // border-radius: 15px 15px 0px 0px;
  position: relative;
  margin-top: 80px;
  a {
    color: #666666;
  }
  /deep/.van-row {
    margin-bottom: 6px;
  }
  & > div {
    background: #ffffff;
    margin-bottom: 10px;
    padding: 18px;
    .title {
      margin-bottom: 13px;
    }
  }
  & > div:last-child {
    // margin-bottom: 0;
  }
  /deep/.van-cell:not(:last-child)::after {
    border-bottom: none;
  }
  /deep/.van-cell__title {
    font-size: 14px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
  }
  .title {
    font-size: 16px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
    img {
      width: 22px;
      height: 22px;
      vertical-align: top;
      margin-right: 8px;
    }
  }
  .details_title {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
  }
  .details_content {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    text-align: right;
    a {
      width: 20px;
      height: 20px;
    }
    img {
      width: 20px;
      height: 20px;
      vertical-align: middle;
      margin-left: 9px;
    }
  }
  .contact-details--carInfo {
    // margin-bottom: 56px;
    &::after {
      content: "";
      display: inline-block;
      height: 50px;
      width: 100%;
    }
  }
}
.contact-details--button {
  position: fixed;
  bottom: 0;
  left: 0;
  // border-top: 12px solid #f7f7f7;
  padding: 0;
  width: 100%;
  display: flex;
  /deep/.van-button {
    flex: 1;
    border: none;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
  }
}
//头部
/deep/ .van-nav-bar {
  background: url(../../../assets/renewal/bavBackground.png) no-repeat 100%,
    100% !important;
  background-size: cover !important;
}
</style>
