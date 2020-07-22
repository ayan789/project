<template>
  <div id="addMyCustomer">
    <Global_globalNav :title="titleText" @clickBack="clickBack" />
    <div class="contentStyle">
      <AddCustomerItem :statusType="$route.query.statusType" />
    </div>
    <div class="buttonStyle">
      <div class="buttonView" @click="submitClick">
        <span class="buttonText">保 存</span>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import AddCustomerItem from "./increassedItem/addCustomerItem";
import { convertNullToEmpty } from "../../../utils/validate";
import {
  mobilePhone,
  emailCheck,
  checkIdcard,
  isName,
  isLicensePlateNumber,
  isNull,
  vinCheck,
  licenseNo
} from "../../../utils/index";
import { Toast } from "vant";
import * as native from "../../../utils/JSBridge";
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    AddCustomerItem
  },
  data() {
    return {
      titleText: this.$route.query.statusType == 2 ? "编辑客户" : "新增客户"
    };
  },

  computed: {
    ...mapState(["editorCustomerData", "customerInfoData"]),
    userCode() {
      let value = "";
      if (this.$GetRequest("source", true)) {
        value = this.$GetRequest("userCode", true);
      } else {
        value = this.$route.query.userCode;
      }
      return value;
    }
  },

  created() {
    const { statusType, custType } = this.$route.query;
    if (statusType == 2) {
      let params =
        custType == "1"
          ? this.customerInfoData.CustInfo
          : this.customerInfoData.teamCustInfo;
      this.$store.commit("editorCustomerData/setData_", params);
      console.log(this.editorCustomerData.tempData, "=====");
    }
  },

  methods: {
    clickBack() {
      if (this.$route.query.statusType == 2) {
        this.$router.back();
        console.log("编辑返回===");
      } else {
        this.$globalBack();
        console.log("新增返回===");
      }
      this.$store.commit("editorCustomerData/clearData_");
    },
    submitClick() {
      const {
        custType,
        custName,
        certificateType,
        certificateNo,
        mobile,
        carNo,
        mobileList,
        email,
        frameNo
      } = this.editorCustomerData.tempData;
      //console.log(this.$route.query.tempParams, 'lo=');
      if (this.$route.query.statusType != 2) {
        this.editorCustomerData.tempData.businessNo = this.userCode;
      }
      let tempResultData = {
        custName: custName, //姓名
        certificateType: certificateType, //证件类型
        certificateNo: certificateNo, //证件号码
        mobile: mobileList //手机号
      };

      let resultType = convertNullToEmpty(tempResultData);
      if (resultType.type_check) {
        const {
          custName,
          certificateType,
          certificateNo,
          mobile
        } = resultType.resultObj;
        if (!custName) {
          Toast("请输入用户名!");
        } else if (!certificateType) {
          Toast("请选择证件类型!");
        } else if (!certificateNo) {
          Toast("请输入证件号码!");
        } else {
          Toast("请输入手机号!");
        }
      } else {
        let zg = /^[0-9a-zA-Z]*$/;
        let type = mobileList.filter(v => {
          return !mobilePhone(v.phoneNumber) == true;
        });

        if (!isName(custName)) {
          return Toast("请输入正确姓名");
        }

        if (
          certificateType == "01" &&
          certificateNo.length != 9 &&
          custType == "2"
        ) {
          return Toast("请输入正确的证件号码");
        }

        console.log(emailCheck(email), "emailCheck(email)====");
        if (
          checkIdcard(certificateNo) !== "验证通过" &&
          custType == "1" &&
          certificateType == "01"
        ) {
          return Toast(checkIdcard(certificateNo));
        } else if (!zg.test(certificateNo)) {
          return Toast("请输入正确的证件号码");
        } else if (type.length > 0) {
          return Toast("请输入正确的手机号！");
        } else if (email && !emailCheck(email)) {
          return Toast("请输入正确的邮箱！");
        } else if (!isNull(carNo) && !licenseNo(carNo)) {
          return Toast("请输入正确的车牌号！");
        } else if (!isNull(frameNo) && !vinCheck(frameNo)) {
          return Toast("请输入正确的车架号!");
        } else {
          if (custType != 2) {
            let editorCustomerDataTeamp = JSON.parse(
              JSON.stringify(this.editorCustomerData.tempData)
            );
            delete editorCustomerDataTeamp.unitNature;
            delete editorCustomerDataTeamp.businessScope;
            delete editorCustomerDataTeamp.contractedProducts;
            delete editorCustomerDataTeamp.remark;
            delete editorCustomerDataTeamp.mobileNumber;
            delete editorCustomerDataTeamp.addreeNumber;
            if (!carNo) {
              delete editorCustomerDataTeamp.carNo;
            }
            if (!frameNo) {
              delete editorCustomerDataTeamp.frameNo;
            }
            if (!email) {
              delete editorCustomerDataTeamp.email;
            }

            //个人客户
            this.$store.dispatch(
              "editorCustomerData/addSelfCustomerData",
              editorCustomerDataTeamp
            );
          } else {
            //团队客户
            let editorCustomerDataTeamp = JSON.parse(
              JSON.stringify(this.editorCustomerData.tempData)
            );
            delete editorCustomerDataTeamp.id;
            delete editorCustomerDataTeamp.carNo;
            delete editorCustomerDataTeamp.frameNo;
            delete editorCustomerDataTeamp.brandNo;
            delete editorCustomerDataTeamp.engineNo;
            delete editorCustomerDataTeamp.profession;
            delete editorCustomerDataTeamp.addressType;
            delete editorCustomerDataTeamp.mobileNumber;
            delete editorCustomerDataTeamp.addreeNumber;
            if (!email) {
              delete editorCustomerDataTeamp.email;
            }

            this.$store.dispatch(
              "editorCustomerData/addSelfCustomerData",
              editorCustomerDataTeamp
            );
            //埋点
            trackMap("c_customer_new_customer", "新增客户", {
              c_salesperson_type: "N",
              c_market_type: "Y",
              c_market_name: "",
              c_market_id: "",
              c_event_category: "客户管理"
            });
          }
        }
      }
    }
  }
};
</script>
<style lang="less" scoped>
#addMyCustomer {
  position: fixed;
  width: 100%;
}

.contentStyle {
  width: 100%;
  background: #f7f7f7;
  margin-top: 70px;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: calc(100vh - 150px);
}

.buttonStyle {
  width: 100vw;
  display: flex;
  flex-direction: row;
  justify-content: center;
  background-color: #ffffff;
  align-items: center;
  padding: 20px 0;
  position: fixed;
  bottom: 0;
  height: 40px;

  .buttonView {
    width: 90%;
    height: 7vh;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    border-radius: 25px;
    background: linear-gradient(to right, #2a80ff, #2aa5ff);

    .buttonText {
      font-size: 16px;
      color: #ffffff;
    }
  }
}
</style>
