<!--选择投保人-->
<template>
  <div id="choose-policy-holder">
    <!-- <div class="navigatorBottom" v-if="clear != '1'" /> -->
    <div class="header-area">
      <van-nav-bar class="nav-bar" :border="false">
        <van-row slot="left">
          <van-col>
            <van-icon name="arrow-left" color="white" size="22px" />
          </van-col>
        </van-row>
        <van-row slot="title" class="nav-bar-title">选择投保人</van-row>
      </van-nav-bar>
      <van-tabs
        line-width="23px"
        line-height="4px"
        color="white"
        :border="false"
        background="transparent"
        title-active-color="#ffffff"
        title-inactive-color="#ffffffb8"
        class="tabs-container"
      >
        <template slot="nav-left">
          <div style="width:120px;"></div>
        </template>
        <template slot="nav-right">
          <div style="width:120px;"></div>
        </template>
        <van-tab title="客户" class="tab-title-container"> </van-tab>
        <van-tab title="公司" class="tab-title-container"> </van-tab>
      </van-tabs>
      <div class="search-area">
        <van-search v-model="value" placeholder="请输入搜索关键词" />
      </div>
    </div>
    <div class="body-content">
      <van-list>
        <van-row
          type="flex"
          v-for="item in list"
          :key="item.phone"
          class="list-item"
        >
          <van-col class="list-item-image">
            <img width="40px" height="40px" />
          </van-col>
          <van-col class="list-item-info">
            <div class="list-item-name">{{ item.name }}</div>
            <div class="list-item-phone">{{ item.phone }}</div>
          </van-col>

          <van-row type="flex" class="list-item-tags">
            <van-col v-for="tag in item.tags" :key="tag" class="list-item-tag">
              {{ tag }}
            </van-col>
          </van-row>
        </van-row>
      </van-list>
    </div>
  </div>
</template>

<script>
import { NavBar, Tab, Search, List } from "vant";
export default {
  components: {
    [NavBar.name]: NavBar,
    [Tab.name]: Tab,
    [Search.name]: Search,
    [List.name]: List
  },
  data: () => ({
    type: 0,
    tabs: ["客户", "公司"],
    activeTab: "",
    value: "",
    list: [
      { name: "安伊妮", phone: "17717517717", tags: ["高收入", "行业精英"] },
      { name: "安伊妮", phone: "17717617717", tags: ["高收入", "行业精英"] },
      { name: "安伊妮", phone: "17717717717", tags: ["高收入", "行业精英"] },
      { name: "安伊妮", phone: "17717817717", tags: ["高收入", "行业精英"] },
      { name: "安伊妮", phone: "17717917717", tags: ["高收入", "行业精英"] },
      { name: "安伊妮", phone: "17744417717", tags: ["高收入", "行业精英"] },
      { name: "安伊妮", phone: "17715217717", tags: ["高收入", "行业精英"] }
    ]
  }),
  mounted() {
    // this.changeActiveTab(this.tabs[0]);
    // this.lineEnter(this.$refs.line);
  },
  methods: {}
};
</script>

<style lang="less" scoped>
// .search-area {
//   margin-top: 10px;
// }
.body-content {
  background: #f7f7f7;
}
#choose-policy-holder {
  width: 100%;
  height: 100%;
}
.header-area {
  background: linear-gradient(129deg, #1e46f6 0%, #279aff 100%);
}
.tabs-container {
  margin-bottom: 15px;
}
.search-area {
  margin-top: -15px;
}
.van-search {
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
}
.van-search__content {
  border-radius: 30px;
}
.nav-bar {
  height: 44px;
  padding-top: 20px;
  background: transparent;
}
.navigatorBottom {
  width: 100vw;
  height: 15px;
  border-radius: 20px 20px 0px 0px;
  background-color: #ffffff;
  top: 99px;
  z-index: 10000;
  position: fixed;
}
.nav-bar-title {
  color: white;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  line-height: 44px;
}
.tabs-container {
  height: 44px;
  padding-bottom: 5px;
  background: transparent;
}
.tab-title-container {
  margin-top: 5px;
}
.list-item {
  margin-top: 10px;
  padding: 18px 15px;
  background: white;
}
.list-item-image {
  margin-right: 15px;
}
.list-item-info {
}
.list-item-name {
  font-size: 14px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 26px;
}
.list-item-phone {
  font-size: 12px;
  font-family: PingFang-SC-Medium, PingFang-SC;
  font-weight: 500;
  color: #999999;
  line-height: 26px;
}
.list-item-tags {
  flex: 1;
  justify-content: flex-end;
}
.list-item-tag {
  width: 58px;
  height: 19px;
  background: #afafaf2e;
  border-radius: 12px;
  text-align: center;
  font-size: 10px;
  font-family: PingFang-SC-Medium, PingFang-SC;
  font-weight: 500;
  color: #666666;
  line-height: 19px;
  margin-right: 8px;
}
</style>
