<template>
  <van-row type="flex" class="switch-tabs-container">
    <div
      class="switch-tab-item"
      v-for="(tab, index) in tabs"
      :key="tab"
      @click="onChange(index)"
      :class="[activeTab === tab ? 'switch-tab-active' : '']"
    >
      <span>{{ tab }}</span>
    </div>
  </van-row>
</template>

<script>
export default {
  name: "switch-tabs",
  model: {
    prop: "activeTab",
    event: "change"
  },
  props: {
    tabs: Array,
    activeTab: [String, Number]
  },
  data: () => ({}),
  created() {
    if (!this.activeTab) {
      this.$emit("change", this.tabs[0] || "");
    }
  },
  methods: {
    onChange(index) {
      this.$emit("change", this.tabs[index]);
    }
  }
};
</script>

<style lang="less" scoped>
.switch-tabs-container {
  width: 344px;
  height: 37px;
  background: #eee;
  margin: 0 auto;
  border-radius: 23px;
  padding: 3px 4px;
  display: flex;
  align-items: center;

  .switch-tab-item {
    display: flex;
    flex: 1;
    height: 100%;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: #666666ae;
    font-size: 14px;
  }
  .switch-tab-active {
    background: white;
    border-radius: 31px;
    color: #3b8afe;
  }
}
</style>
