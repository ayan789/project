<template>
  <div id="mycustomer">
    <div class="labelPage">
      <div
        class="optionsTabList"
        :style="{ backgroundImage: 'url(' + $imageUrl.custBG + ')' }"
      >
        <div class="leftStyle">
          <van-icon
            name="arrow-left"
            size="20"
            @click="back"
            v-if="!navigationStatus"
          />
          <div style="width: 20px;background-color:transparent " />
        </div>
        <div
          class="optionListitem"
          v-for="(item, index) in optionList"
          :key="index"
          @click="changeTabs(index)"
          :class="[editorCustomerData.active == index ? 'amplification' : '']"
        >
          {{ item.name }}
        </div>
        <div class="leftStyle">
          <van-icon
            name="chat-o"
            size="20"
            :info="editorCustomerData.taskCount"
            @click="toFollowRemind"
            v-if="editorCustomerData.taskCount > 0"
          />
          <van-icon name="chat-o" size="20" @click="toFollowRemind" v-else />
          <van-icon name="plus" size="20" @click="toAddCustomer" />
        </div>
      </div>
      <div class="navigatorBottom" />
      <div class="mycustomer_topView">
        <van-pull-refresh
          v-model="isLoading"
          success-text="刷新成功"
          @refresh="onRefresh"
        >
          <div v-show="editorCustomerData.active == 0">
            <individual
              :viewType="1"
              :tempListMap="tempListMap"
              @searchClick="searchClick($event)"
              :tipConfigList="tipConfigList"
              :tempParams="tempParams"
              :insureType="insureType"
              :isInsured="isInsured"
              @changeObj="changeObj"
            ></individual>
          </div>

          <div v-show="editorCustomerData.active == 1">
            <individual
              :viewType="2"
              :tempListMap="tempListMap"
              @searchClick="searchClick($event)"
              :tipConfigList="tipConfigList"
              :tempParams="tempParams"
              :insureType="insureType"
              :isInsured="isInsured"
              @changeObj="changeObj"
            ></individual>
          </div>
        </van-pull-refresh>
        <!--        <div class="mycustomer_peopleView">-->
        <!--          共有{{tempListMap.length}}人-->
        <!--        </div>-->
      </div>
    </div>
  </div>
</template>
<script>
import individual from "./individual";
import { getQueryDicConfigInfo } from "../../../http/module/customerApi";
import mapper from "../../../utils/mapper";
import { mapState } from "vuex";
import { GetRequest, getIP } from "../../../utils/index";
import { Toast } from "vant";
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    individual
  },

  data() {
    return {
      isLoading: false,
      active: 0,
      flag: false,
      // number:"1111",
      optionList: [{ name: "个人客户" }, { name: "团体客户" }],
      // tempListMap: [],//客户数据
      tipConfigList: [{ name: "全部客户", code: null }], //标签数据
      tempParams: {
        searchCondition: "", //筛选条件
        searchFuzzy: "", //模糊搜索字段 //客户名称，企业名称，手机号
        custType: "1" //客户类型 1：个人；2：团队
      },
      tempListMa_1: [
        {
          letter: "A",
          customerList: [
            {
              custNo: "001",
              sex: "M",
              mobileList: [{ phoneNumber: "18512584721" }],
              certificateNo: "330824199534285311",
              certificateType: "01",
              custName: "阿里1",
              custSourceType: "1",
              customerLevel: null,
              isReg: null,
              birthStatus: false
            }
          ]
        }
      ]
    };
  },
  computed: {
    ...mapState(["editorCustomerData"]),
    tempListMap() {
      return this.editorCustomerData.customerList;
    },
    number() {
      this.editorCustomerData.number = 0;
      if (
        this.editorCustomerData.customerList &&
        this.editorCustomerData.customerList.length > 0
      ) {
        let array = this.editorCustomerData.customerList;
        for (let index = 0; index < array.length; index++) {
          const element = array[index];
          if (element.customerList && element.customerList.length > 0) {
            this.editorCustomerData.number += element.customerList.length;
          }
        }
      }
      // this.editorCustomerData.number = num;
      return this.editorCustomerData.number;
    },
    insureType() {
      let text = this.$route.query.item
        ? this.$route.query.item.insureType
        : "";
      return text;
    },
    isInsured() {
      let status = this.$route.query.item
        ? this.$route.query.item.isInsured
        : false;
      return status;
    },
    navigationStatus() {
      return this.$GetRequest("source", true)
        ? this.$GetRequest("source", true)
        : false;
    },
    temp_userCode() {
      localStorage.setItem("token", this.$GetRequest("token", true));
      return this.$GetRequest("userCode", true)
        ? this.$GetRequest("userCode", true)
        : localStorage.getItem("userCode");
    },
    temp_branchCode() {
      return (
        this.$GetRequest("branchCode", true) || this.$localStorage.branchCode
      );
    }
  },
  created() {
    //埋点 首页进来
    this.$GetRequest("source", true) === "shell" &&
      trackMap("c_home_customer_menu", "客户菜单", {
        c_salesperson_type: "Y",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "客户"
      });
  },
  async mounted() {
    this.$native.rejectMyCustomerList(() => {
      this.onRefresh();
    });
    console.log(this.tempListMap, "heweiwei");
    let num = 0;
    for (var i = 0; i < this.tempListMap.length; i++) {
      num += this.tempListMap[i].customerList.length;
    }
    setTimeout(() => {
      this.optionList[0].name = "个人客户" + "(" + this.number + ")";
    }, 200);
    // this.optionList =  [{ name: "个人客户111" }, { name: "团体客户111" }]

    //全局获取缓存数据
    this.tempParams.userCode = this.$GetRequest("userCode", true) || "";
    this.$globalSetLocalStorage();
    this.tempParams.custType = this.editorCustomerData.active + 1;
    const { item } = this.$route.query;
    if (this.insureType) {
      this.tempParams.custType = item ? item.custType : "1";
      // this.active = item ? +item.custType - 1 : 0;
      this.$store.commit(
        "editorCustomerData/myCustomer_active",
        item ? +item.custType - 1 : 0
      );
    }
    await this.getConfigInfo();
    await this.searchClick(this.tempParams, "1");
    //TODO 暂不使用是否注册标签
    // let paramsObj = JSON.parse(JSON.stringify(this.tempParams));
    // await this.searchClick_(paramsObj, '1');
  },
  updated() {
    if (!this.flag) {
      this.optionList[0].name = "个人客户" + "(" + this.number + ")";
    }
    // this.optionList[0].name = "个人客户" + "(" + this.number + ")"
    if (this.flag) {
      this.optionList[1].name = "团体客户" + "(" + this.number + ")";
    }
  },
  methods: {
    //下拉刷新
    async onRefresh() {
      const { searchCondition } = this.tempParams;
      if (searchCondition === "5" || searchCondition === "6") {
      } else {
        await this.searchClick(this.tempParams, "2");
      }
      this.isLoading = false;
    },
    //获取动态数据
    changeObj(params) {
      console.log(params, "liohh");
      this.tempParams = params;
      console.log(this.tempParams, "何韦韦");
    },
    back() {
      this.$router.back();
    },
    // 获取全部客户列表数据
    async searchClick(params_, status_type) {
      const { searchCondition, searchFuzzy, custType } = params_;
      let params = {
        searchCondition: searchCondition, //筛选条件
        searchFuzzy: searchFuzzy, //模糊搜索字段 //客户名称，企业名称，手机号
        custType: custType, //客户类型 1：个人；2：团队
        userCode: this.temp_userCode, //业务员号
        branchCode: this.temp_branchCode
      };
      this.tempParams.searchFuzzy = params.searchFuzzy;
      this.tempParams.custType = params.custType;
      console.log(this.tempParams, "==this.tempParams11111111");
      await this.$store.dispatch(
        "editorCustomerData/queryCustomerList",
        params
      );
    },
    //获取注册客户数据列表
    async searchClick_(params_, status_type) {
      const { searchFuzzy, custType } = params_;
      let params = {
        searchCondition: "5", //筛选条件
        searchFuzzy: searchFuzzy, //模糊搜索字段 //客户名称，企业名称，手机号
        custType: custType, //客户类型 1：个人；2：团队
        userCode: this.temp_userCode, //业务员号
        branchCode: this.temp_branchCode
      };

      await this.$store.dispatch(
        "editorCustomerData/queryCustomerList_",
        params
      );
    },
    // 查询字典配置信息
    async getConfigInfo() {
      let params = {
        code: mapper.myCustomerType[0].value //编码 policy_tag: 筛选标签
      };
      let res = await getQueryDicConfigInfo(params);
      //TODO 暂取消是否注册标签
      let temp_arr = [];
      temp_arr = res.data.filter(v => {
        return v.code !== "5" && v.code !== "6";
      });
      console.log(temp_arr, "temp_arr===");
      this.tipConfigList = this.tipConfigList.concat(temp_arr);
    },

    changeTabs(index) {
      console.log(index, "index==");
      this.editorCustomerData.number = 0;
      if (index == "1") {
        this.flag = true;
      } else {
        this.flag = false;
      }
      console.log(this.tempParams, "====tyu");
      const { searchCondition, searchFuzzy } = this.tempParams;
      if (searchCondition == "5" && !searchFuzzy && index == "0") {
        this.$store.commit("editorCustomerData/CHANGE_COSTOMER");
      } else if (searchCondition == "6" && !searchFuzzy && index == "0") {
        this.$store.commit("editorCustomerData/CHANGE_WCOSTOMER");
      } else {
        let params = {
          searchCondition: index == "0" ? searchCondition : "", //筛选条件
          searchFuzzy: searchFuzzy ? searchFuzzy : "", //模糊搜索字段 //客户名称，企业名称，手机号
          custType: index + 1, //客户类型 1：个人；2：团队
          userCode: this.temp_userCode || "" //业务员号
        };
        this.searchClick(params);
      }
      this.$store.commit("editorCustomerData/myCustomer_active", index);
    },

    toAddCustomer() {
      if (this.navigationStatus) {
        // Toast(localStorage.getItem("userCode"));
        let tempUrl =
          this.$getIP() +
          "/customer/increased?userCode=" +
          this.temp_userCode +
          "&source=" +
          this.navigationStatus;
        let params = {
          url: tempUrl,
          titleBarTag: "0"
        };
        this.$native.openWebView(params, data => {
          console.log("IP 地址" + tempUrl);
        });
      } else {
        this.$router.push({
          name: "increased",
          query: { userCode: this.temp_userCode }
        });
      }
    },

    toFollowRemind() {
      if (this.navigationStatus) {
        let tempUrl =
          this.$getIP() +
          "/customer/followRemind?source=" +
          this.navigationStatus;
        let params = {
          url: tempUrl,
          titleBarTag: "0"
        };
        this.$native.openWebView(params);
      } else {
        this.$router.push({ name: "followRemind" });
      }
    }
  }
};
</script>
<style lang="less" scoped>
#mycustomer {
  position: fixed;
  width: 100%;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;

  /*height: calc(100vh - 10px);*/
  /*overflow: scroll;*/

  /deep/ .van-picker__toolbar {
    margin-bottom: -40px;
    z-index: 1000;
    background-color: white;
  }

  /deep/ .van-index-bar__sidebar {
    top: 60%;
  }

  .amplification {
    font-size: 16px;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
    line-height: 46px;
  }

  .labelPage {
    background: #f3f3f3;

    // padding: 2px 0 6px 0;
    .optionsTabList {
      height: 80px;
      /*line-height: 46px;*/
      color: #fff;
      font-size: 14px;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      background-size: cover;

      .leftStyle {
        width: 80px;
        height: 50px;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-around;
      }

      .optionListitem {
        position: relative;

        span {
          position: absolute;
          top: 40px;
          left: 50%;
          transform: translateX(-50%);
          height: 5px;
          width: 28px;
          border-radius: 6px;
          background: #fff;
        }
      }
    }
  }

  .amplification {
    font-size: 16px;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
    line-height: 46px;
  }
}

.van-tabs--line .van-tabs__wrap {
  z-index: 1000;
  width: 100vw;
  position: fixed;
}

.mycustomer_topView {
  height: calc(100vh - 80px);
  overflow: scroll;
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

.mycustomer_peopleView {
  text-align: center;
  margin: 5px 0px;
  color: #c1c1c1;
  font-size: 14px;
}
</style>
