<template>
  <div class="construction">
    <!-- 客户个人信息模块 -->

    <div class="user-content">
      <div class="content-img">
        <img
          class="content-backImg"
          src="@/assets/customer/customerEquity/background@3x.png"
          alt="edit"
        />
      </div>
      <div class="content-all">
        <div class="content-top">
          <div class="content-top-left">
            <div class="iocn" @click="back">
              <img
                class="icon-img"
                src="@/assets/customer/customerEquity/leftArrowhead@3x.png"
                alt="edit"
              />
            </div>
            <div v-if="isPerson == 1 && custSourceType == 2">
              <img
                style="width:12px;height:12px;margin-left: 16px"
                src="@/assets/customer/customerEquity/location@3x.png"
                alt="edit"
              />
            </div>
            <span
              v-if="isPerson == 1 && custSourceType == 2"
              style="margin-left: 6px;color:white"
              >{{ customerInfoData.CustInfo.policyAdress }}</span
            >
          </div>
          <div class="content-top-right">
            <div
              v-if="custSourceType == '1'"
              class="icon-top-default"
              style="margin-right: 21px"
              @click="Dialog"
            >
              <img
                class="icon-top-img-default"
                src="@/assets/customer/customerEquity/delete.png"
                alt="edit"
              />
            </div>
            <div class="icon-top-default" @click="toWeChat">
              <img
                class="icon-top-img-default"
                src="@/assets/customer/customerEquity/service@3x.png"
                alt="edit"
              />
            </div>
            <div
              class="icon-top-default"
              style="margin-left:21px;"
              @click="compileCustomer"
            >
              <img
                class="icon-top-img-default"
                src="@/assets/customer/customerEquity/edit@3x.png"
                alt="edit"
              />
            </div>
          </div>
        </div>

        <div class="user-info">
          <div class="content-img-user" style="pointer-events: none">
            <img
              class="content-user-backImg"
              src="@/assets/customer/customerEquity/contentImg@3x.png"
              alt="edit"
              style="pointer-events: none"
            />
          </div>
          <!--<div>互动记录></div>-->
          <div
            style="color:#21304D;font-size: 22px;width:50vw;"
            @click="toEditorCustomerView"
          >
            <!--用户姓名-->
            {{ isPerson == 1 ? showCustomer.custName : teamCustInfo.custName }}
          </div>
          <div>
            <div @click="toInteractEditView" class="record">互动记录&nbsp></div>
            <div style="display: inline" v-if="isPerson == 1">
              <span style="color:#21304D;margin-right: 5.5px;font-size: 12px">{{
                showCustomer.sex ? (showCustomer.sex == "M" ? "男" : "女") : ""
              }}</span>
              <span class="icon-default" style="display: inline-block">
                <img
                  class="icon-img-default"
                  src="@/assets/customer/customerEquity/heart@3x.png"
                  alt="edit"
              /></span>
              <span style="color:#21304D;margin-left: 5.5px;font-size: 12px">
                {{ showCustomer.age }}岁</span
              >
            </div>

            <div v-if="isPerson == 1" style="display: inline;margin-left: 16px">
              <span class="icon-default" style="display: inline-block">
                <img
                  class="icon-img-default"
                  src="@/assets/customer/customerEquity/age@3x.png"
                  alt="edit"
              /></span>
              <span style="color:#21304D;font-size: 12px;margin-left: 5.5px">{{
                showCustomer.birthday && showCustomer.birthday.slice(0, 10)
              }}</span>
            </div>
            <div
              v-else
              style="display: flex;align-items: center;margin-bottom: 20px"
              @click="
                Tell(
                  teamCustInfo.mobileList &&
                    teamCustInfo.mobileList[0] &&
                    teamCustInfo.mobileList[0].phoneNumber
                )
              "
            >
              <div class="icon-default" style="display: flex;">
                <img
                  class="icon-img-default"
                  src="@/assets/customer/customerEquity/tel@3x.png"
                  alt="edit"
                />
              </div>
              <div style="margin-left: 7.5px">
                <!--电话-->
                {{ teamMobile }}
              </div>
            </div>
          </div>
          <div v-if="isPerson == 1" class="use-info-tel">
            <div @click="toCustomerStartLeveliew" style="display:inline-block;">
              <div style="display:inline-block;">
                <!--积分等级-->
                <!--{{ showCustomer.custLevel }}-->
                <span
                  class="icon-default"
                  style="display: inline-block;margin-right: 2px"
                  v-for="item in showCustomer.custLevel"
                  :key="item.index"
                >
                  <img
                    class="icon-img-default"
                    src="@/assets/customer/customerEquity/fivePoint@3x.png"
                    alt="edit"
                    v-if="item"
                  />
                  <img
                    class="icon-img-default"
                    src="@/assets/customer/customerEquity/fivePointNull@3x.png"
                    alt="edit"
                    v-else
                  />
                </span>
              </div>
              <span style="margin-left: 7px;color: #4A6391;font-size: 12px">
                <!--积分-->
                {{
                  showCustomer.custIntegral ? showCustomer.custIntegral : "0"
                }}
                积分</span
              >
            </div>
            <div
              style="display:flex;float:right;align-items: center"
              @click="Tell(showCustomer.mobile)"
            >
              <div class="icon-default" style="display: flex;">
                <img
                  class="icon-img-default"
                  src="@/assets/customer/customerEquity/tel@3x.png"
                  alt="edit"
                />
              </div>
              <div style="margin-left: 7.5px;color: #314D81;font-size: 14px">
                <!--电话-->
                {{ personMobile }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="isPerson == 1" class="labelPage">
      <van-tabs v-model="active" @click="changeTabs" color="blue">
        <div class="labeltab">
          <van-tab title="客户服务">
            <customerServe
              :product="product"
              :getQueryRemindTaskList="getQueryRemindTaskList"
              :custSourceType="custSourceType"
            ></customerServe>
          </van-tab>
          <van-tab title="客户信息">
            <customerInformation
              :datas="information"
              :product="product"
              :custSourceType="custSourceType"
              :custNo="custNo_temp + ''"
              :custType="custType_temp + ''"
            ></customerInformation>
          </van-tab>
          <van-tab title="客户权益">
            <customerRights
              :datas="interestsList"
              :product="product"
              :custSourceType="custSourceType"
              :custNo="custNo_temp + ''"
            ></customerRights>
          </van-tab>
          <van-tab v-if="isMillionsMedical == true" title="销售助手">
            <customerSell />
          </van-tab>
          <van-tab title="客户动态">
            <customerDynamic :custCode="custCode" :custNo="custNo_temp + ''" />
          </van-tab>
        </div>
      </van-tabs>
    </div>
    <div v-else class="comtry">
      <div
        v-if="custSourceType == '2'"
        style="padding-bottom: 20px;border-bottom:1px solid #E5E6E8"
      >
        <div>
          <div class="cust-title-a" @click="toRouter">
            <div style="display: flex;align-items: center">
              <div style="width:18px;height:18px;">
                <img
                  style="width:100%;height:100%"
                  src="@/assets/customer/customerEquity/cust.png"
                  alt="edit"
                />
              </div>
              <div style="float:left">
                <span style="font-weight: 500;margin-left: 15px">保险信息</span>
              </div>
            </div>
            <img
              style="width:9px;height:12px"
              src="@/assets/customer/customerEquity/rightArrowhead@3x.png"
              alt="edit"
            />
          </div>
          <div class="content-b">
            <p
              style="margin-bottom: 5px"
              v-for="item in teamCustInfo && teamCustInfo.remindList"
              :key="item.index"
            >
              {{ item }}
            </p>
          </div>
        </div>
      </div>
      <div style="margin-top: 20px">
        <div>
          <div class="cust-title">
            <div style="width:18px;height:18px;">
              <img
                style="width:100%;height:100%"
                src="@/assets/customer/customerEquity/cust.png"
                alt="edit"
              />
            </div>
            <div>
              <span style="font-weight: 500;margin-left: 15px">客户信息</span>
            </div>
          </div>
          <div class="content" style="font-size: 15px;padding: 12px;">
            <div class="cust-content" style="margin-bottom: 10px">
              <span style="color:#666666">企业经营范围</span>
              <span>{{ teamCustInfo.businessScope }}</span>
            </div>
            <div class="cust-content">
              <span style="color:#666666">可承保产品</span>
              <span>{{ teamCustInfo.contractedProducts }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--    <van-popup v-model="showTells" position="bottom" :style="{ height: '30%' }">-->
    <!--      <van-picker-->
    <!--        :columns="tellsTmp"-->
    <!--        @confirm="onConfirm"-->
    <!--        show-toolbar-->
    <!--        confirm-button-text="拨打"-->
    <!--      />-->
    <!--    </van-popup>-->
    <van-popup v-model="showTells" position="bottom" @close="cancelPhone">
      <van-picker
        title="手机号"
        show-toolbar
        :columns="tellsTmp"
        @confirm="onConfirm"
        @cancel="cancelPhone"
      />
    </van-popup>
  </div>
</template>
<script>
import customerRights from "./customerRights.vue";
import customerInformation from "./customerInformation.vue";
import customerDynamic from "./customerDynamic.vue";
import customerServe from "./customerServe.vue";
import customerSell from "./customerSell.vue";
import { mapState } from "vuex";
import {
  getQueryCustomerInformation,
  getQueryCustomerInterests
} from "../../../http/module/customerApi";
import { GetRequest } from "../../../utils";
import * as native from "../../../utils/JSBridge";
import $router from "../../../router";
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    customerRights,
    customerInformation,
    customerDynamic,
    customerServe,
    customerSell
  },
  data() {
    return {
      showCustomer: {}, //客户信息数据
      information: {}, //传给客户信息
      interestsList: [], //传给客户权益
      active: 0,
      product: [], //产品推荐列表
      item: {},
      getIndexHp: {}, //提醒列表
      isPerson: 1, //是个人客户? 0未知、1个人、2团体
      custSourceType: "1", //是本地客户？ 1本地客户、2保单客户
      teamCustInfo: {}, //团体客户请求回来的数据
      DynamicDate: {}, //客户动态的有效日期集合
      custCode: "",
      address: "",
      getQueryRemindTaskList: {},
      isMillionsMedical: false, //是否百万医疗
      personMobile: "", //个人处理132****0351电话
      teamMobile: "", //团队处理132****0351电话
      tells: [], //用户的电话列表
      tellsTmp: [], //脱敏显示的电话列表
      showTells: false //打电话列表
    };
  },
  computed: {
    ...mapState(["editorCustomerData", "customerInfoData"]),
    custType_temp() {
      return this.$GetRequest("source", true)
        ? this.$GetRequest("custType", true)
        : this.$route.query.custType;
    },
    viewType_temp() {
      return this.$GetRequest("source", true)
        ? this.$GetRequest("viewType", true)
        : this.$route.query.viewType;
    },
    custNo_temp() {
      return this.$GetRequest("source", true)
        ? this.$GetRequest("custNo", true)
        : this.$route.query.custNo;
    },
    custSourceType_temp() {
      return this.$GetRequest("source", true)
        ? this.$GetRequest("custSourceType", true)
        : this.$route.query.custSourceType;
    }
  },
  async created() {
    //个人客户请求参数
    let params_self = {
      userCode: localStorage.getItem("userCode"), //业务号
      custNo: "" + this.custNo_temp, //客户号
      // custNo: "80148973834", //客户号
      custSourceType: "" + this.custSourceType_temp, //客户标识
      custType: "" + this.custType_temp //客户类型
    };
    //团体客户请求参数
    let params_team = {
      custNo: "" + this.custNo_temp,
      custSourceType: "" + this.custSourceType_temp //1本地客户,2保单客户
    };
    let toType = "" + this.viewType_temp; //获取viewType 1、个人 2、团体
    this.isPerson = toType;
    if (toType == 1) {
      await this.$store.dispatch(
        "customerInfoData/getqueryCustInfo",
        params_self
      );
    } else if (toType == 2) {
      await this.$store.dispatch(
        "customerInfoData/getTeamCustInfo",
        params_team
      );
    }
    let custInfo = this.customerInfoData.CustInfo
      ? this.customerInfoData.CustInfo
      : "";
    let teamCustInfo = this.customerInfoData.teamCustInfo
      ? this.customerInfoData.teamCustInfo
      : "";
    this.teamCustInfo = teamCustInfo;
    this.custSourceType = this.custSourceType_temp;

    //传给客户积分
    this.itemStartLevel = {
      custNo: custInfo.custNo //客户号
    };
    //传给理赔
    this.item = {
      custNo: custInfo.custNo, //客户号
      userCode: custInfo.userCode //业务号
    };
    //传给违章
    this.items = {
      custNo: "" + this.custNo_temp, //客户号
      userCode: custInfo.userCode, //业务号
      carNo: custInfo.carNo, //车牌号
      frameNo: custInfo.frameNo, //车架号
      engineNo: custInfo.engineNo //发动机号
    };
    //计算客户等级星号显示
    let starArray = [false, false, false, false, false];
    let num = parseInt(custInfo.custLevel);
    // let num = 2;
    for (let i = 0; i < 5; i++) {
      if (num > 0) {
        starArray[i] = true;
        num = num - 1;
      }
    }
    let perTmp =
      custInfo.mobileList &&
      custInfo.mobileList[0] &&
      custInfo.mobileList[0].phoneNumber
        ? custInfo.mobileList[0].phoneNumber
        : "";
    if (perTmp) {
      //转化个人电话号码
      this.mobileShow(perTmp, "person");
    }

    let teamTmp =
      teamCustInfo.mobileList &&
      teamCustInfo.mobileList[0] &&
      teamCustInfo.mobileList[0].phoneNumber
        ? teamCustInfo.mobileList[0].phoneNumber
        : "";
    if (teamTmp) {
      //转化企业电话号码
      this.mobileShow(teamTmp, "team");
    }

    if (toType == 1) {
      let perTmpList = custInfo.mobileList ? custInfo.mobileList : [];
      this.toPoupTells(perTmpList);
    } else {
      let teamTmpList = teamCustInfo.mobileList ? teamCustInfo.mobileList : [];
      this.toPoupTells(teamTmpList);
    }
    this.showCustomer = {
      sex: custInfo.sex,
      custName: custInfo.custName, //用户姓名
      age: custInfo.age,
      birthday: custInfo.birthday,
      mobile:
        custInfo.mobileList &&
        custInfo.mobileList[0] &&
        custInfo.mobileList[0].phoneNumber
          ? custInfo.mobileList[0].phoneNumber
          : "",
      custLevel: starArray,
      custIntegral: custInfo.custIntegral,
      custSourceType: custInfo.custSourceType
    };
    this.custCode = custInfo.custNo;
    if (toType == 1) {
      if (this.custSourceType == "2") {
        //获取viewType 1、个人 2、团体)
        await this.getRemindList();
        this.getQueryRemindTaskList = this.customerInfoData.getQueryRemindTaskList;
        this.getQueryRemindTaskList.remindList.map((v, i) => {
          switch (v.remindType) {
            case "5":
              v.remindName = "待注册提醒";
              break;
            case "4":
              v.remindName = "权益提醒";
              break;
            case "3":
              v.remindName = "生日提醒";
              break;
            case "2":
              v.remindName = "续保提醒";
              break;
            case "1":
              v.remindName = "出险提醒";
              break;
          }
        });
      }
    }
    //获取底部产品推荐
    await this.$store.dispatch(
      //推荐产品方案
      "customerInfoData/getIndexHp",
      {
        createCode: localStorage.getItem("userCode"),
        orgCode: this.$localStorage.comCode,
        userCode: localStorage.getItem("userCode")
      }
    );
    let getIndexHp = this.customerInfoData.getIndexHp;
    let productTmp = [];
    if (getIndexHp) {
      for (let i = 0; i < getIndexHp.length; i++) {
        let tmp = getIndexHp[i].contentDetailList;
        if (tmp) {
          for (let j = 0; j < tmp.length; j++) {
            productTmp.push(tmp[j]);
          }
        }
      }
    }
    this.product = productTmp;
    this.isMillionsMedical = custInfo.isMillionsMedical;
  },

  methods: {
    cancelPhone() {
      this.showTells = false;
    },
    toRouter() {
      this.$router.push({
        name: "insuranceInfoView",
        query: {
          custNo: "" + this.custNo_temp,
          custType: "" + this.viewType_temp
        }
      });
    },
    toPoupTells(arr) {
      let tmplist = [];
      let tmplist1 = [];
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].phoneNumber) {
          let perTmp = arr[i].phoneNumber;
          let a = perTmp.slice(0, 3);
          let tmp = perTmp.length;
          let b = perTmp.slice(tmp - 4, tmp);
          let tell = a + "****" + b;
          tmplist.push(arr[i].phoneNumber);
          tmplist1.push(tell);
        }
      }
      this.tells = tmplist;
      this.tellsTmp = tmplist1;
    },
    mobileShow(perTmp, str) {
      let a = perTmp.slice(0, 3);
      let tmp = perTmp.length;
      let b = perTmp.slice(tmp - 4, tmp);
      if (str == "person") {
        this.personMobile = a + "****" + b;
      } else {
        this.teamMobile = a + "****" + b;
      }
    },
    //跳转微信
    toWeChat() {
      // Toast("打开微信")
      this.$native.openWeChat(data => {
        console.info(data, "打开微信的返回data");
        //埋点
        trackMap("c_customer_info_online_contact", "客户信息在线联系", {
          c_salesperson_type: "N",
          c_market_type: "Y",
          c_market_name: "",
          c_market_id: "",
          c_event_category: "客户管理"
        });
      });
    },
    //点击删除，弹窗
    Dialog() {
      this.$dialog
        .confirm({
          title: "",
          message: "删除该用户？",
          confirmButtonColor: "red"
        })
        .then(() => {
          this.deleteCust();
        })
        .catch(() => {});
    },
    //删除用户接口调用
    async deleteCust() {
      let param = {
        id:
          this.viewType_temp == "1"
            ? this.customerInfoData.CustInfo.id
            : this.customerInfoData.teamCustInfo.id,
        updateUser: localStorage.getItem("userCode")
      };
      await this.$store.dispatch(
        //删除客户
        "customerInfoData/deleteCustomer",
        param
      );
      let deleteCustomer = this.customerInfoData.deleteCustomer;
      if (deleteCustomer == 1) {
        if (this.$GetRequest("source", true)) {
          this.$native.closeWeb(() => {}, { routerName: "myCustomer" });
        } else {
          this.$router.back();
          console.log("H5删除===");
        }
      }
    },
    //拨打电话原生方法
    Tell(phone) {
      if (this.tells.length > 1) {
        this.showTells = true;
      } else {
        console.info(phone);
        phone &&
          this.$native.getPhoneCall(phone, data => {
            let res = JSON.parse(data);
            if (res.resultCode == "1") {
              let params = {
                custNo: this.custNo_temp,
                createUser: localStorage.getItem("userCode"),
                type: "1",
                title: "打电话",
                userCode: localStorage.getItem("userCode")
              };
              this.$store.dispatch(
                "customerData/addOrUpdateInteractions",
                params
              );
            }
          });
      }
    },
    //点击”拨打“，底部弹出的电话列表
    onConfirm(values, index) {
      console.info(this.tells[index]);
      let phone = this.tells[index];
      this.$native.getPhoneCall(phone, data => {
        let res = JSON.parse(data);
        if (res.resultCode == "1") {
          let params = {
            custNo: this.custNo_temp,
            createUser: localStorage.getItem("userCode"),
            type: "1",
            title: "打电话",
            userCode: localStorage.getItem("userCode")
          };
          this.$store.dispatch("customerData/addOrUpdateInteractions", params);
        }
      });
    },
    async getRemindList() {
      let params = {
        custNo: this.custNo_temp,
        userCode: localStorage.getItem("userCode")
      };
      await this.$store.dispatch(
        "customerInfoData/getQueryRemindTaskList",
        params
      );
    },
    compileCustomer() {
      this.$router.push({
        name: "increased",
        query: { statusType: "2", custType: this.custType_temp }
      });
    },
    back() {
      this.$globalBack();
    },
    toInteractEditView() {
      //埋点
      trackMap("c_customer_info_interactive_info", "客户信息互动信息", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "客户管理"
      });
      this.$router.push({
        name: "interactEdit",
        query: { item: this.items }
      });
    },
    toCustomerStartLeveliew() {
      this.$router.push({
        name: "customerStartLevel",
        query: { item: JSON.stringify(this.items) }
      });
    },

    async changeTabs(index) {
      this.active = index;
      let params = {
        custNo: this.custNo_temp, //客户号
        userCode: localStorage.getItem("userCode")
      };
      //埋点
      let tempObj = {
        "0": {
          eventId: "c_customer_info_customer_service",
          targetName: "客户信息客户服务"
        },
        "1": {
          eventId: "c_customer_info_customer_info",
          targetName: "客户信息客户信息"
        },
        "2": {
          eventId: "c_customer_info_customer_rights",
          targetName: "客户信息客户权益"
        },
        "3": {
          eventId: "c_customer_info_customer_dynamics",
          targetName: "客户信息客户动态"
        }
      };
      trackMap(
        tempObj[index].eventId,
        tempObj[index].targetName,
        index == "3"
          ? {
              c_salesperson_type: "N",
              c_market_type: "Y",
              c_market_name: "",
              c_market_id: "",
              c_user_code: this.custNo_temp,
              c_event_category: "客户管理"
            }
          : {
              c_salesperson_type: "N",
              c_market_type: "Y",
              c_market_name: "",
              c_market_id: "",
              c_event_category: "客户管理"
            }
      );
      tempObj = null;
      switch (index) {
        case 0:
          break;
        case 1:
          if (this.custSourceType == "2") {
            let res_1 = await getQueryCustomerInformation(params);
            this.information = res_1 && res_1.data;
          }
          break;
        case 2:
          if (this.custSourceType == "2") {
            let res_2 = await getQueryCustomerInterests(params);
            this.interestsList =
              res_2 && res_2.data && res_2.data.bbrList
                ? res_2 && res_2.data && res_2.data.bbrList
                : [];
          }
          break;
        case 4:
          break;
      }
    },
    // 编辑客户
    toEditorCustomerView() {
      this.$router.push({
        name: "increased",
        query: { statusType: "2" }
      });
    }
  }
};
</script>
<style lang="less" scoped>
.construction {
  position: static;
  background-color: #ffffff;
  font-size: 16px;

  /deep/ .van-picker__toolbar {
    margin-bottom: -40px;
    z-index: 1000;
  }

  .icon-default {
    width: 12px;
    height: 12px;

    .icon-img-default {
      width: 100%;
      height: 100%;
    }
  }

  .icon-top-default {
    width: 17px;
    height: 17px;

    .icon-top-img-default {
      width: 100%;
      height: 100%;
    }
  }

  .user-content {
    background-color: white;
    height: 200px;
    position: fixed;
    top: 0px;

    .content-img {
      width: 100%;

      .content-backImg {
        width: 100%;
        height: 160px;
        background-size: contain;
      }
    }

    .content-all {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      padding: 0px 16px;
      /*border:1px solid red;*/
      /*box-shadow: 0px 0.2px 17px  17px rgba(91, 91, 91, 0.09);*/
    }

    .content-top {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      margin-top: 20px;

      .content-top-left {
        display: flex;
        justify-content: flex-start;
        align-items: center;

        .iocn {
          width: 20px;
          height: 18px;

          .icon-img {
            width: 100%;
            height: 100%;
            /*transform: rotate(180deg);*/
          }
        }
      }

      .content-top-right {
        display: flex;
        justify-content: flex-start;
      }
    }

    .record {
      position: absolute;
      right: 0px;
      top: 0px;
      background-color: #e2f0ff;
      border-top-right-radius: 10px;
      border-bottom-left-radius: 10px;
      padding: 3px 11px;
      color: #4b6fa1;
    }

    .user-info {
      background-color: #ffffff;
      padding: 10px 12px 12px 18px;
      margin: 0 16px;
      border-radius: 10px;
      box-shadow: 0px 0.2px 5px 5px rgba(91, 91, 91, 0.09);
      position: absolute;
      top: 60px;
      left: 0;
      right: 0;

      .content-img-user {
        position: absolute;
        right: 0;
        top: 0;
        width: 50%;
        height: 100%;
        opacity: 0.5;

        .content-user-backImg {
          height: 100%;
          width: 100%;
        }
      }

      .use-info-tel {
        margin-top: 20px;
        /*display:flex;*/
      }
    }
  }

  .labelPage {
    background: white;
    margin-top: 200px;
    padding: 2px 0 6px 0;
    position: fixed;
    width: 100%;

    .optionsTabList {
      height: 46px;
      line-height: 46px;
      color: #666666;
      font-size: 12px;
      display: flex;
      padding: 0 15px;
      justify-content: space-around;
      align-items: center;

      div {
        padding: 10px 0;
        position: relative;

        span {
          height: 5px;
          width: 28px;
          border-radius: 6px;
          background: #3b8afe;
          display: block;
          margin: 0 auto;
        }
      }
    }
  }

  .comtry {
    background: white;
    margin-top: 200px;
    padding: 2px 25px 6px 25px;
    /*position: fixed;*/
    /*width: 100%;*/

    .cust-title-a {
      margin-top: 10px;
      display: flex;
      align-items: center;
      justify-content: space-between;
    }

    .content-b {
      margin-top: 10px;
      padding: 0 25px;
      color: #545454;
    }

    .cust-title {
      display: flex;
      align-items: center;
    }

    .content {
      margin-top: 18px;
      margin-left: 18px;
      background-color: #fafafa;

      .cust-content {
        display: flex;
        justify-content: space-between;
      }
    }
  }

  .amplification {
    font-size: 17px;
    color: #333333;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
  }
}

.labelPage .van-tabs__line {
  background-color: blue;
}

.van-tabs__nav--line {
  background: #f3f3f3;
}
</style>
