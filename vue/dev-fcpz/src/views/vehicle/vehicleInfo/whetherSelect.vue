<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-03-06 16:17:46
 * @Last Modified by: King
 * @Last Modified time: 2020-06-30 10:23:33
 * @FilePath: /src/views/vehicle/vehicleInfo/whetherSelect.vue
-->

<template>
  <div class="container">
    <div class="whether-select">
      <div
        v-for="(item, index) in options"
        :key="index"
        @click="select(item)"
        :class="{
          'whether-button': true,
          'button-active': item.value === selected
        }"
      >
        {{ item.text }}
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "whether-select",
  model: {
    prop: "selected",
    event: "change"
  },
  props: {
    selected: {
      type: [Number, Boolean, String, Object],
      default: () => ""
    },
    options: Array,
    allocMultiple: {
      type: Boolean,
      default: false
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      init: true
    };
  },
  created() {
  },
  methods: {
    select(item) {
      if (this.disabled || item.disabled) {
        this.$toast.clear();
        return this.$toast("该选项不可选");
      }
      //重复点击不触发
      if (item.value === this.selected) {
        if (!this.allocMultiple) return;
      }
      this.$emit("change", item.value);
    }
  }
};
</script>
<style lang="less" scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row-reverse;
  overflow-x: auto;
  overflow-y: visible;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
}
.whether-select {
  display: flex;
  .whether-button {
    flex-shrink: 0;
    background: white;
    min-width: 37px;
    padding: 0 6px;
    height: 28px;
    line-height: 28px;
    text-align: center;
    font-size: 14px;
    color: #ababab;
    border-radius: 3px;
    border: 1px solid #dddddd;
    word-break: keep-all;
  }
  .whether-button:not(:first-child) {
    margin-left: 8px;
  }
  .button-active {
    background-color: #eef5ff;
    color: #2b79ed;
    border: 1px solid #eef5ff;
  }
}
</style>
