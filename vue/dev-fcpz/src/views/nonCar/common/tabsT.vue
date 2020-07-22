<template>
  <div class="absolute-container">
    <van-row type="flex" justify="space-between" class="tabs-container">
      <van-col
        v-for="tab in tabs"
        :key="tab"
        :ref="tab"
        :class="[tab === activeTab ? 'tab-item-active' : '', 'tab-item']"
        @click="changeActiveTab(tab)"
      >
        {{ tab }}
      </van-col>
    </van-row>
    <transition v-on:enter="lineEnter">
      <div
        ref="line"
        v-bind:css="false"
        :key="activeTab"
        class="tab-active-line"
      ></div>
    </transition>
  </div>
</template>

<script>
export default {
  data: () => ({
    type: 0,
    tabs: ["客户", "公司"],
    activeTab: ""
  }),
  mounted() {
    this.changeActiveTab(this.tabs[0]);
    this.lineEnter(this.$refs.line);
  },
  methods: {
    changeActiveTab(tab) {
      this.activeTab = tab;
    },
    lineEnter: function(el, done) {
      const tab = this.$refs[this.activeTab][0].$el;
      const left = tab.offsetLeft + (tab.offsetWidth - el.offsetWidth) / 2;
      el.style.left = left + "px";
      done && done();
    }
  }
};
</script>
<style lang="less" scoped>
.tabs-container {
  height: 44px;
  padding: 0 120px;
  background: linear-gradient(129deg, #1e46f6 0%, #279aff 100%);
  display: flex;
}
.tab-item {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 46px;
  opacity: 0.72;
}
.tab-item-active {
  font-size: 16px;
  opacity: 1;
}
.absolute-container {
  position: relative;
  width: 100%;
}
.tab-active-line {
  width: 21px;
  height: 3px;
  position: absolute;
  bottom: 5px;
  background: white;
  border-radius: 6px;
}
</style>
