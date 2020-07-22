<template>
  <div>
    <globalNav :title="globalNav.title" @clickBack="$router.back()"></globalNav>
    <div class="content-wrap">
      <van-tabs v-model="actived" @click="onChange">
        <van-tab title="团队目标" v-if="isTeamLeader == 1">
          <responsibilityDetailCom :data="data" />
        </van-tab>
        <van-tab title="个人目标">
          <responsibilityDetailCom :data="data" />
        </van-tab>
      </van-tabs>
    </div>
  </div>
</template>

<script>
import { responsibilityListQuery } from "@src/http/module/performance";
import responsibilityDetailCom from "./responsibilityDetailCom";
import globalNav from "@/components/globalNav";
export default {
  components: {
    globalNav,
    responsibilityDetailCom
  },
  data: () => ({
    tData: {},
    pData: {},
    data: {},
    isTeamLeader: "",
    actived: 0,
    globalNav: {
      title: "责任书"
    }
  }),
  watch: {
    selectedMonth: function(value) {}
  },
  created() {
    // this.data = { ...this.$route.params };
    let userInfo = JSON.parse(localStorage.getItem("userInfo"));
    this.isTeamLeader = userInfo.isTeamLeader;
    responsibilityListQuery({}).then(
      res => {
        let data = res.data.list;
        this.tList = data.filter(item => item.responsibilityType === "T")[0];
        this.pList = data.filter(item => item.responsibilityType === "P")[0];
        this.globalNav.title = this.pList.responsibilityName + "明细";
        if (this.isTeamLeader == 1) {
          this.data = this.tList;
        } else {
          this.actived = 1;
          this.data = this.pList;
        }
      },
      error => {}
    );
  },
  computed: {},
  methods: {
    onChange(index, title) {
      if (title === "个人目标") {
        this.data = this.pList;
      } else {
        this.data = this.tList;
      }
    }
  }
};
</script>
<style scoped lang="less">
.content-wrap {
  background-color: #f7f7f7;
  margin-top: 80px;
  /deep/.van-tabs {
    position: relative;
    background: #fff;
  }
  /deep/[class*="van-hairline"]::after {
    border: none;
  }
  /deep/.van-tab__text {
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(102, 102, 102, 1);
  }
  /deep/ .van-tab--active {
    .van-tab__text {
      font-size: 17px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(0, 0, 0, 1);
    }
  }
  /deep/.van-tabs__line {
    display: block !important;
    width: 28px !important;
    height: 5px;
    background: rgba(59, 138, 254, 1);
    border-radius: 3px;
  }
}
</style>
