<!--
 * @Author: King
 * @Page: Q码维护
 * @Date: 2020-05-15 15:19:35
 * @Last Modified by: King
 * @Last Modified time: 2020-06-24 10:01:28
 * @FilePath: /src/views/vehicle/vehicleInfo/QCodeMaintain.vue
-->
<template>
  <div class="container">
    <nav-bar title="Q码维护" @clickBack="back" />
    <form action="/">
      <van-search
        v-model="searchValue"
        placeholder="请输入业务编码/业务名称/项目名称"
        show-action
        @search="onSearch"
        @cancel="onCancel"
      />
    </form>
    <div class="tabs">
      <div
        :class="['tab', activeTab == 1 ? 'tab-active' : '']"
        @click="changeTab(1)"
      >
        出单可见
        <div
          :class="['tab-line', activeTab == 1 ? 'tab-active-line' : '']"
        ></div>
      </div>
      <div
        :class="['tab', activeTab == 0 ? 'tab-active' : '']"
        @click="changeTab(0)"
      >
        出单不可见
        <div
          :class="['tab-line', activeTab == 0 ? 'tab-active-line' : '']"
        ></div>
      </div>
    </div>
    <van-divider />
    <div class="list">
      <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
        <van-list
          v-model="loading"
          ref="list"
          :finished="finished"
          finished-text="没有更多了"
          :error.sync="error"
          error-text="请求失败，点击重新加载"
          :offset="100"
          @load="onLoad"
        >
          <div class="list-item" v-for="(item, index) in list" :key="index">
            <div>业务编码: {{ item.businessCode }}</div>
            <div>业务名称: {{ item.businessName }}</div>
            <div>
              项目名称:
              <div class="item-center">{{ item.projectName }}</div>
              <div class="button" @click="changeVisible(item, index)">
                {{ activeTab == 1 ? "设为不可见" : "设为可见" }}
              </div>
            </div>
            <van-divider :style="{ height: '1px' }" />
          </div>
          <template #finished>
            <div
              v-show="Array.isArray(list) && list.length == 0"
              class="noData"
            >
              <!-- <img :src="nodata_img" alt="" /> -->
            </div>
          </template>
        </van-list>
      </van-pull-refresh>
    </div>
  </div>
</template>

<script>
import { List, Divider } from "vant";
import { getPQList, setPQShow } from "../../../http/module/vehicle";
const params = {
  businessCode: "", //  string 业务编码-设置不可见时必传
  comCode: "", //  string 组织机构代码
  id: "", //  string 非可见记录的主键-设置可见时必传
  isShowFlag: "", //  string 是否可见标记 0-不可见 1-可见
  queryParam: "", //  string 搜索条件-支持模糊查询（业务编码、业务名称、项目名称）
  staffCode: "", //  string 业务员代码
  userCode: "" //  string 业务员编号
};
export default {
  components: {
    [List.name]: List,
    [Divider.name]: Divider
  },
  data() {
    const list = new Array(10).fill({}).map((item, index) => {
      return {
        businessCode: String(index),
        businessName: String(index),
        projectName: String(index)
      };
    });
    return {
      refreshing: false,
      loading: false,
      finished: false,
      error: false,
      activeTab: 1,
      searchValue: "",
      pageNo: 0,
      pageSize: 20,
      list: []
    };
  },
  mounted() {
    // this.$refs.list.$el.style = `
    //   height: calc(100% - ${this.$refs.list.offsetTop || 0}px);
    //   overflow: scroll;
    //   -webkit-overflow-scrolling: touch;
    //   overflow-scrolling: touch;
    // `;
    this.onRefresh();
  },
  methods: {
    back() {
      this.$native.closeWeb();
    },
    changeTab(tab) {
      this.activeTab = tab;
      this.list = [];
      this.finished = false;
      this.error = false;
      this.onRefresh();
    },
    onSearch(val) {
      this.list = [];
      this.finished = false;
      this.error = false;
      this.getPQList({
        ...params,
        queryParam: val,
        isShowFlag: String(this.activeTab),
        pageNo: 0,
        pageSize: 20
      });
    },
    onCancel() {},
    onRefresh() {
      this.list = [];
      this.getPQList({
        ...params,
        isShowFlag: String(this.activeTab),
        pageNo: 0,
        pageSize: 20
      });
      this.pageNo = 0;
    },
    onLoad() {
      this.getPQList({
        ...params,
        isShowFlag: String(this.activeTab),
        pageNo: this.pageNo,
        pageSize: 20
      });
    },
    getPQList(params) {
      this.loading = true;
      getPQList(params)
        .then(res => {
          if (res.code == 1) {
            this.error = false;
            let { qpcodeList } = res.data;
            qpcodeList = qpcodeList || [];
            this.list = [...this.list, ...qpcodeList];
            if (qpcodeList.length == 0 || qpcodeList.length < 20) {
              this.finished = true;
              console.log(this.finished);
            }
            this.pageNo += 1;
          } else {
            this.error = true;
            this.$dialog.alert({ message: res.msg });
          }
        })
        .catch(error => {
          console.log(error);
          this.error = true;
        })
        .finally(() => {
          this.loading = false;
          this.refreshing = false;
          this.$forceUpdate();
        });
    },
    changeVisible(item, index) {
      //isShowFlag 是否可见标记 0-不可见 1-可见
      let isShowFlag = item.isShowFlag;
      if (this.activeTab == 1) {
        //设为不可见
        isShowFlag = "0";
      } else {
        //设为可见
        isShowFlag = "1";
      }
      let params = { ...item, isShowFlag };
      delete params.userCode;
      setPQShow(params)
        .then(res => {
          if (res.code == 1) {
            this.onRefresh();
          }
        })
        .catch(error => {});
    }
  }
};
</script>

<style lang="less" scoped>
.container {
  background: white;
  overflow: hidden;
}
.tabs {
  display: flex;
  flex-direction: row;
  .tab {
    position: relative;
    padding: 8px;
    color: #333333;
    font-size: 14px;
    font-weight: 400;
  }
  .tab-active {
    color: #3b8afe;
    font-weight: 500;
  }
  .tab-line {
    position: absolute;
    bottom: 0;
    left: 10px;
    right: 10px;
    height: 4px;
    background: transparent;
    border-radius: 2px;
  }
  .tab-active-line {
    background: #3b8afe;
  }
}
.list {
  height: 467px;
  overflow-y: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  div {
    display: flex;
    flex-direction: column;
  }
  .list-item {
    flex-direction: column;
    align-items: stretch;
    padding: 0 20px;
    font-size: 14px;
    div {
      flex-direction: row;
      padding-top: 4px;
      justify-content: space-between;
      align-items: center;
      .item-center {
        flex: 1;
        margin-left: 4px;
      }
      .button {
        padding: 0 4px;
        border-radius: 4px;
        border: 1px solid #3b8afe;
      }
    }
  }
}
.noData {
  width: 180px;
  height: 150px;
  align-self: center;
  margin: 0 auto;
  margin-top: 76px;
  background: url("../../../assets/renewal/renewalListData.png") no-repeat;
  background-size: cover;
  position: relative;
  // img {
  //   width: 180px;
  //   height: 150px;
  //   display: block;
  // }
  &::after {
    left: 50%;
    position: absolute;
    bottom: -50px;
    transform: translateX(-50%);
    content: "暂无数据";
    display: block;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(153, 153, 153, 1);
  }
}
</style>
