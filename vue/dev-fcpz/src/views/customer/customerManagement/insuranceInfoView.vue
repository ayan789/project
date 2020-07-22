<template>
  <div class="queryRemindView">
    <div class="labelPage">
      <div class="optionsTabList" :style="{'backgroundImage':'url('+$imageUrl.custBG+')'}">
        <div class="leftStyle">
          <van-icon name="arrow-left" size="20" @click="clickBack"/>
          <div style="width: 20px;background-color:transparent "/>
        </div>
        <div
          class="optionListitem"
          v-for="(item, index) in optionList"
          :key="index"
          @click="changeTabs(index)"
          :class="[active == index ? 'amplification' : '']"
        >
          {{ item.name }}
        </div>
        <div class="leftStyle">
        </div>
      </div>
      <div class="navigatorBottom"/>
      <div class="topView">
        <div v-show="active == 0">
          <infoItemView :tempListMap="dataList" :active="active"/>
        </div>
        <div v-show="active == 1">
          <infoItemView :tempListMap="dataList" :active="active"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getQueryInsuranceList } from "../../../http/module/customerApi";
import infoItemView from "./infoItemView";
import { mapState } from "vuex";
export default {
  components: {
    infoItemView
  },
  name: "InsuranceInfoView",
  data() {
    return {
      active: 0,
      optionList: [{ name: "车险信息" }, { name: "非车险信息" }],
      dataList: [
        {
          count: "",//保单数量
          policyType: "",//个人：1 车险；0 非车险 ；团体：对应险种名称
          policyList: [
            {
              policyNo: "",//保单号
              licenseNo: "",//车牌号
              insuredName: "",//被保人
              custName: "",//投保人
              insuranceName: "",//险种名称
              respEndDate: "",//起保日期
              respStartDate: ""//终保日期
            }
          ]
        }
      ]
    };
  },
  computed: {
    custType_temp() {
      return this.$GetRequest("source", true) ? this.$GetRequest("custType", true) : this.$route.query.custType;
    },
    custNo_temp() {
      return this.$GetRequest("source", true) ? this.$GetRequest("custNo", true) : this.$route.query.custNo;
    },
  },

  async created() {
    this.changeTabs(0);
  },

  methods: {
    async changeTabs(index) {
      const { custNo, custType } = this.$route.query;
      let params = {
        custNo: this.custNo_temp,
        userCode: localStorage.getItem("userCode"),
        isCarInsurance: index == 1 ? '0' : '1',
        custType: this.custType_temp
      };
      let res = await getQueryInsuranceList(params);
      this.dataList = res.data;
      this.active = index;
    },

    clickBack() {
      this.$globalBack();
    },
  }
};
</script>

<style scoped lang="less">
.queryRemindView {
  position: fixed;
  width: 100%;
}

.labelPage {
  .optionsTabList {
    height: 80px;
    line-height: 86px;
    color: #fff;
    font-size: 14px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    background-size: cover;

    .leftStyle {
      width: 80px;
      height: 80px;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-around;
    }

    .optionListitem {
      position: relative;
    }
  }
}

.topView {
  height: calc(100vh - 60px);
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  background-color: #F5F5F5;
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

.amplification {
  font-size: 16px;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 86px;
}
</style>
