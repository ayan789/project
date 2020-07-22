<template>
  <div class="indexPage">
    <!-- data-cl-event="c_home_car_insure" -->
    <h1>列表目录</h1>
    <ul>
      <li v-for="item in pages" :key="item.id">
        <h3 @click="redirectPage(item.id)">{{ item.title }}</h3>
      </li>
    </ul>
  </div>
</template>

<script>
import { trackMap } from "@src/utils/index.js";
export default {
  data: () => ({
    pages: [
      { id: 1, title: "首页", path: "" },
      { id: 2, title: "续保", path: "renewal" },
      { id: 3, title: "营销管理", path: "activities" },
      { id: 4, title: "非车险", path: "nocar" },
      { id: 5, title: "业绩", path: "performance" },
      { id: 6, title: "客户管理", path: "customer" },
      { id: 7, title: "车险投保", path: "vehicle" },
      { id: 16, title: "车辆类型选择", path: "selectVehicle" },
      { id: 8, title: "原生壳方法测试", path: "" },
      { id: 9, title: "订单管理", path: "order" },
      { id: 10, title: "登录", path: "login" },
      { id: 11, title: "保单查询", path: "order/findOrder/findOrder" },
      { id: 12, title: "我的活动", path: "activities" },
      { id: 13, title: "理赔查询", path: "customer/claimQuery" },
      { id: 14, title: "代理人手续费提现", path: "costReflect" },
      { id: 15, title: "代理人信息/个代日结", path: "personal_agent" }
    ]
  }),
  created() {
    let userInfo = {
      // address: "",
      // agentCode: "",
      // channelCode: "C0000030",
      // comCName: "昆明高新支公司营业一部（撤销）",
      // comCode: "",
      // comName: "昆明高新支公司营业一部（撤销）",
      // comRole: "0",
      // company: "",
      // deviceToken: "",
      // email: "",
      // handlerCode: "",
      // handlerName: "蹇晓霞",
      // isAuditMan: "0",
      // isSaleMan: "1",
      // isVipManager: "0",
      // loginFlag: "2",
      // makeCompany: "",
      // makeCompanyCName: "昆明高新支公司营业一部（撤销）",
      // mobile: "",
      // password: "",
      // phone: "",
      // postcode: "",
      // salaryPassword: "",
      // storeCode: "",
      // system: "",
      // terminalType: "",
      // token: "YccoJNOdktv4fuQr",
      // tokenIdx: "158453635878638",
      // userCode: "",
      // userEName: "JianXiaoXia",
      // userName: "蹇晓霞",
      // valid: "1",
      // version: "",
      // xinHuaAgentCode: "",
      // xinHuaAgreementNo: "",
      // xinHuaBusinessNature: "",
      // xinHuaComCode: "",
      // xinHuaUserCode: "",
      // xinHuaUserName: "",
      // homePermissionInfo: "首页权限",
      // rankname: "职级",
      // recommendCode: "推荐码"
    };
  },
  methods: {
    // 路由跳转
    redirectPage(type) {
      switch (type) {
        case 1:
          break;
        case 2:
          // _cl_tracker.track("c_1479895689829", "访问商城", " targetid ");
          this.$router.push({ path: "renewal" });
          break;
        case 3:
          this.$router.push({ path: "activities" });
          break;
        case 4:
          this.$router.push({ path: "nonCar" });
          break;
        case 5:
          this.$router.push({ path: "performance" });
          break;
        case 6:
          this.$router.push({ path: "customer" });
          break;
        case 7:
          // this.$router.push({ name: "vehicle" });
          this.$router.push({
            path: "/vehicle",
            query: { vehicleType: 1 }
          });
          break;
        case 8:
          this.$router.push({ path: "shell" });
          break;
        case 9:
          this.$router.push({ path: "order" });
          break;
        case 10:
          this.$router.push({ path: "login" });
          break;
        case 11:
          this.$router.push({ path: "order/findOrder/findOrder" });
          break;
        case 12:
          this.$router.push({ path: "activities" });
          break;
        case 13:
          this.$router.push({ path: "customer/claimQuery" });
          break;
        case 14:
          this.$router.push({ path: "costReflect" });
          break;
        case 15:
          this.$router.push({ path: "personal_agent" });
          break;
        case 16:
          this.$router.push({ path: "selectVehicle" });
          break;
        default:
          break;
      }
    }
  }
};
</script>

<style lang="less" scoped>
.indexPage {
  h1 {
    height: 40px;
    text-align: center;
    line-height: 40px;
  }
  ul {
    li {
      padding: 15px;
      h3 {
        font-size: 18px;
        cursor: pointer;
      }
    }
  }
}
</style>
