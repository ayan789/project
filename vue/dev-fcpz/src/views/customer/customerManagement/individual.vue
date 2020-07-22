<template>
  <div class="individual">
    <div
      class="searchView"
      v-if="viewType == '1'"
    >
      <div class="searchStyle">
        <van-icon
          class="individual_searchIcon"
          name="search"
          size="20"
          color="#B8B8B8"
        />
        <van-field
          v-model="searchValue"
          placeholder="可搜索姓名"
          @input="searchClick"
          clearable
        />
      </div>
      <div
        class="individual_tipText"
        @click="changeType"
      >{{ tipText }}</div>
      <van-icon
        class="individual_searchIcon"
        :name="!status_type ? $imageUrl.down : $imageUrl.top"
        size="13"
        color="#B8B8B8"
        @click="changeType"
      />
    </div>
    <div
      v-else
      class="searchView"
    >
      <div class="searchStyle_">
        <van-icon
          class="individual_searchIcon"
          name="search"
          size="20"
          color="#B8B8B8"
        />
        <van-field
          v-model="searchValue"
          placeholder="可搜索姓名"
          @input="searchClick"
          clearable
        />
      </div>
    </div>
    <div
      class="tipView"
      v-if="viewType == '1'"
    >
      <div
        class="tipStyle"
        v-if="status_type"
      >
        <div
          v-for="(item_, index) in tipConfigList"
          :class="[
            item_ && item_.id == tempId ? 'itemView_' : 'individual_itemView'
          ]"
          @click="tipClick(item_)"
        >
          {{ item_ && item_.name }}
        </div>
      </div>
    </div>

    <van-index-bar
      highlight-color="#000000"
      :sticky="false"
    >
      <div
        v-for="(item_, index) in tempListMap"
        :key="index"
      >
        <van-index-anchor :index="item_.letter" />
        <van-cell
          v-for="(v, i) in item_.customerList"
          :key="i"
        >
          <div
            class="topContentView"
            @click="toCustomerEquityView(v)"
          >
            <div
              class="imgStyle"
              :style="{ backgroundImage: 'url(' + $imageUrl.headIcon + ')' }"
            >
              <img
                :src="v.sex == 'M' ? $imageUrl.MIcon : $imageUrl.WIcon"
                alt
                style="height: 15px;width:15px;position: relative;bottom: -25px;right: -25px"
                v-if="viewType == '1' && v.sex"
              />
            </div>
            <div class="individual_titleView">
              <div class="individual_titleStyle">
                <div class="textView">
                  {{ v.custName }}
                  <!--                  <img :src="$imageUrl.bao" alt="" v-if="v.isReg=='1'"-->
                  <!--                       class="iconStyle">-->
                  <img
                    :src="$imageUrl.borthday"
                    alt
                    class="iconStyle"
                    v-if="v.birthStatus"
                  />
                  <div
                    class="baoStyle"
                    v-if="v.custSourceType == '2'"
                  >保</div>
                </div>
              </div>
              <div v-if="viewType == '1'">
                <van-rate
                  v-model="+v.customerLevel"
                  allow-half
                  void-icon="star"
                  void-color="#eee"
                  color="#68A2FD"
                  gutter="1"
                  readonly
                />
              </div>
              <!--              <div>{{getPhoneNumber(v)}}</div>-->
            </div>
          </div>
          <!--          <div class="lineStyle"/>-->
          <div class="bottomView">
            <div
              class="individual_titleStyle"
              @click="toInsuranceInfo(v)"
            >
              <img
                :src="$imageUrl.file"
                alt
                class="iconStyle"
              />
              <span style="margin-left:10px">保单{{ v.policyCount }}</span>
            </div>
            <div
              class="individual_titleStyle"
              @click="phoneCall(v)"
            >
              <img
                :src="$imageUrl.phone"
                alt
                class="iconStyle"
              />
              <span style="margin-left:10px">联系客户</span>
            </div>
          </div>
        </van-cell>
      </div>
    </van-index-bar>
    <van-popup
      v-model="phoneShow"
      position="bottom"
      @close="cancelPhone"
    >
      <van-picker
        title="手机号"
        show-toolbar
        :loading="loading"
        :columns="phoneOptions"
        @confirm="phoneCallClick($event)"
        @cancel="cancelPhone"
      />
    </van-popup>
  </div>
</template>
<script>
// import "@/style/customer/customerManagement/individual.less";
import { Rate, Popup, Toast } from "vant";
import { getQueryCustPhone } from "../../../http/module/customerApi";
import * as native from "../../../utils/JSBridge";
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    [Rate.name]: Rate,
    [Popup.name]: Popup
  },
  data () {
    return {
      searchValue: "",
      activeName: "1",
      flag: "3",
      show: false,
      tempId: "",
      phoneShow: false,
      phoneOptions: [],
      loading: true,
      tempCustNo: "",
      tipText: "全部客户",
      status_type: false
    };
  },

  props: {
    viewType: Number,
    tempListMap: Array,
    tipConfigList: Array,
    tempParams: Object,
    insureType: String,
    isInsured: Boolean
  },

  watch: {
    tempParams: {
      handler: function (val, oldVal) {
        this.$nextTick(function () {
          this.searchValue = val && val.searchFuzzy;
        });
      },
      // 深度观察监听
      deep: true,
      immediate: true
    }
  },

  computed: {
    navigationStatus () {
      return this.$GetRequest("source", true)
        ? this.$GetRequest("source", true)
        : false;
    }
  },

  methods: {
    getPhoneNumber (v) {
      if (v) {
        if (Array.isArray(v.mobileList)) {
          let temp = v.mobileList[0];
          if (temp) {
            return temp.phoneNumber;
          }
        }
        return "";
      }
    },
    changeType () {
      this.status_type = !this.status_type;
    },
    //选中标签
    tipClick (item) {
      this.tipText = item.name;
      const { searchFuzzy, custType, userCode } = this.tempParams;
      let params = {
        searchCondition: item.code, //筛选条件
        searchFuzzy: searchFuzzy ? searchFuzzy : "", //模糊搜索字段
        custType: custType ? custType : "", //客户类型
        userCode: userCode ? userCode : "" //业务员号
      };
      if (item.code == "5" && !searchFuzzy) {
        this.$store.commit("editorCustomerData/CHANGE_COSTOMER");
        this.$emit("changeObj", params);
      } else if (item.code == "6" && !searchFuzzy) {
        this.$store.commit("editorCustomerData/CHANGE_WCOSTOMER");
        this.$emit("changeObj", params);
      } else {
        this.$emit("searchClick", params);
        this.$emit("changeObj", params);
      }
      this.tempId = item.id;
      //console.log(item, '===item');
    },
    showPopup () {
      this.show = true;
    },
    //搜索
    async searchClick (value) {
      const { searchCondition, custType, userCode } = this.tempParams;
      let params = {
        searchCondition: searchCondition ? searchCondition : "", //筛选条件
        searchFuzzy: value, //模糊搜索字段
        custType: custType ? custType : "", //客户类型
        userCode: userCode ? userCode : "" //业务员号
      };
      if (!value && searchCondition == "5") {
        this.$store.commit("editorCustomerData/CHANGE_COSTOMER");
        this.$emit("changeObj", params);
      } else if (!value && searchCondition == "6") {
        this.$store.commit("editorCustomerData/CHANGE_WCOSTOMER");
        this.$emit("changeObj", params);
      } else {
        this.$emit("searchClick", params, "2");
      }
    },

    // 跳转客户信息页面
    toCustomerEquityView (item) {
      console.log(item, "11");
      if (this.$route.query.flag == "info") {
        let obj = {
          certificateNo: item.certificateNo,
          certificateType: item.certificateType,
          custName: item.custName
        };
        this.$store.commit("nonCar/getinfoObj", obj);
        this.$router.push({ path: "/nonCar/millionPlan" });
      } else {
        item.custType = this.viewType;
        item.isInsured = this.isInsured;
        if (this.navigationStatus) {
          let tempUrl =
            this.$getIP() +
            "/customer/customerEquity?viewType=" +
            this.viewType +
            "&source=" +
            this.navigationStatus +
            "&custNo=" +
            item.custNo +
            "&custSourceType=" +
            item.custSourceType +
            "&custType=" +
            item.custType;
          let params = {
            url: tempUrl,
            titleBarTag: "0"
          };
          this.$native.openWebView(params);
        } else {
          // 目前 1 和 2 ，3，5已被用了，1 是货运无忧，2:百万医疗(个人版)，3:百万医疗(家庭版)String 类型 5 防癌险
          if (this.insureType == "1") {
            this.$router.push({
              name: "proInsuranceInformation",
              query: { item: item }
            });
          } else if (this.insureType == "2") {
            this.$router.push({
              name: "insuranceInformationInput",
              query: { item: item }
            });
          } else if (this.insureType == "3") {
            this.$root.$emit("selectUserInfo", JSON.stringify(item));
            this.$router.push({ name: "writeForm", query: { item: item } });
          } else if (this.insureType == "5") {
            this.$router.push({
              name: "stopCancerForm",
              query: { item: item }
            });
          } else {
            console.log(this.viewType, "===item===");
            this.$router.push({
              name: "customerEquity",
              query: {
                viewType: this.viewType,
                custNo: item.custNo,
                custSourceType: item.custSourceType,
                custType: item.custType
              }
            });
          }
        }
      }
    },
    toInsuranceInfo (item) {
      item.custType = this.viewType;
      if (item.policyCount) {
        if (this.navigationStatus) {
          let tempUrl =
            this.$getIP() +
            "/customer/insuranceInfoView?custNo=" +
            item.custNo +
            "&custType=" +
            item.custType +
            "&source=" +
            this.navigationStatus;
          let params = {
            url: tempUrl,
            titleBarTag: "0"
          };
          this.$native.openWebView(params);
        } else {
          this.$router.push({
            name: "insuranceInfoView",
            query: { custNo: item.custNo, custType: item.custType }
          });
        }
      } else {
        Toast("暂无保单");
      }
    },
    phoneCallClick (phoneNo) {
      console.log(phoneNo.value, "=====phoneNo");
      this.$native.getPhoneCall(phoneNo.value, data => {
        let res = JSON.parse(data);
        if (res.resultCode == "1") {
          let params = {
            custNo: this.tempCustNo,
            createUser: localStorage.getItem("userCode"),
            type: "1",
            title: "打电话",
            userCode: localStorage.getItem("userCode")
          };
          //埋点
          trackMap("c_customer_info_telephone_contact", "客户信息电话联系", {
            c_salesperson_type: "N",
            c_market_type: "Y",
            c_market_name: "",
            c_market_id: "",
            c_event_category: "客户管理"
          });
          this.$store.dispatch("customerData/addOrUpdateInteractions", params);
        }
      });
      this.phoneShow = false;
      this.phoneOptions = [];
    },

    cancelPhone () {
      this.phoneShow = false;
      this.phoneOptions = [];
    },

    async phoneCall (v) {
      this.tempCustNo = v.custNo;
      let tempArr = [];
      if (v.custSourceType == "2") {
        let res = await getQueryCustPhone({ custNo: v.custNo });
        if (res.code == "1" && res.data.length > 0) {
          tempArr = res.data;
        }
      } else {
        tempArr = v.mobileList;
      }
      if (tempArr.length > 1) {
        tempArr.map((v, i) => {
          if (v.phoneNumber) {
            let len = v.phoneNumber.length;
            let xx = v.phoneNumber.substring(3, len - 4);
            let values = v.phoneNumber.replace(xx, "****");
            let obj_ = {
              text: values,
              value: v.phoneNumber
            };
            this.phoneOptions.push(obj_);
          }
        });
        this.loading = false;
        this.phoneShow = true;
      } else if (tempArr.length === 1) {
        let temp_obj = {
          text: tempArr[0].phoneNumber,
          value: tempArr[0].phoneNumber
        };
        this.phoneCallClick(temp_obj);
      } else {
        Toast("暂无查到手机号！");
      }
    }
  }
};
</script>
<style lang="less">
.searchView {
  height: 42px;
  background-color: white;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.searchStyle {
  display: flex;
  width: 66%;
  justify-content: flex-start;
  align-items: center;
  background-color: #f4f4f4;
  margin-left: 15px;
  margin-right: 15px;
  border-radius: 30px;

  .individual_searchIcon {
    margin-left: 10px;
  }
}

.searchStyle_ {
  display: flex;
  width: 100%;
  justify-content: flex-start;
  align-items: center;
  background-color: #f4f4f4;
  margin-left: 15px;
  margin-right: 15px;
  border-radius: 30px;

  .individual_searchIcon {
    margin-left: 10px;
  }
}

.individual_tipText {
  font-size: 13px;
  color: #545454;
  margin-right: 10px;
}

.van-icon {
}

.individual {
  background-color: white;

  /deep/ .van-field__body {
    padding-left: 5px;
    /*border-bottom-right-radius: 25px;*/
    /*border-top-right-radius: 25px;*/
  }

  /deep/ .van-index-anchor {
    background-color: #f5f5f5 !important;
    line-height: 20px;
  }

  /deep/ .van-index-bar__index {
    padding: 2px 5px;
    font-size: 12px;
  }

  /deep/ .van-rate__icon {
    font-size: 15px;
  }
}

.topContentView {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 15px;
}

.imgStyle {
  width: 40px;
  height: 40px;
  border-radius: 20px;
  margin: 0px 15px;
  background-size: contain;
}

.individual_titleView {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  flex: 1;
}

.individual_titleStyle {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex: 1;
}

.birthStatusStyle {
  width: 14px;
  height: 14px;
  background-color: #0d84f2;
  margin: 8px;
}

.starStyle {
  margin-left: 40px+15px+14px;
}

.lineStyle {
  width: 90%;
  height: 1px;
  background-color: #e5e6e8;
  margin-left: 15px;
}

.bottomView {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  height: 41px;
}

.tipView {
  margin: 5px 15px;
}

/*.iconView {*/
/*  height: 20px;*/
/*  width: 20px;*/
/*  background-color: red;*/
/*  margin-left: 15px;*/
/*}*/

.individual_itemView {
  width: calc((100vw - 120px) / 3);
  height: 26px;
  justify-content: center;
  align-items: center;
  display: flex;
  background-color: #bebebe;
  border-radius: 20px;
  color: white;
  margin: 5px 15px;
}

.itemView_ {
  width: calc((100vw - 120px) / 3);
  height: 26px;
  margin: 5px 15px;
  justify-content: center;
  align-items: center;
  display: flex;
  border-radius: 20px;
  color: #ffffff;
  background-color: #235ef0; //EBF3FF
}

.van-collapse-item__content {
  display: flex;
  padding: 0;
}

.tipStyle {
  width: 100vw;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  /*height: 100px;*/
  flex-wrap: wrap;
}

.textView {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.iconStyle {
  width: 15px;
  height: 15px;
  background-size: contain;
  margin-left: 10px;
}

.baoStyle {
  width: 20px;
  height: 20px;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: #1a8c37 1px solid;
  margin-left: 10px;
  color: #1a8c37;
  font-size: 12px;
}
#mycustomer[data-v-13416b3e] .van-picker__toolbar {
  z-index: 99999 !important;
}
</style>
