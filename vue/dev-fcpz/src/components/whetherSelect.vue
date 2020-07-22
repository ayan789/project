<template>
  <div class="whetherSelect">
    <div class="whetherVehicle">
      <div
        class="whetherBack"
        v-for="(item, index) in whetherOptions"
        :key="index"
        @click="regardlessSelect(index)"
        :class="{
          active: (typeof item === 'object' ? item.value : index) === selected
        }"
      >
        {{ typeof item === "object" ? item.text : item }}
      </div>
    </div>
  </div>
</template>
<script>
export default {
  model: {
    prop: "selected",
    event: "change"
  },
  props: {
    selected: [Number, Boolean, String, Object],
    whetherOptions: Array
  },
  data() {
    return {
      regardlessList: ["是", "否"]
    };
  },
  methods: {
    regardlessSelect(index) {
      this.$emit(
        "change",
        typeof this.whetherOptions[index] === "object"
          ? this.whetherOptions[index].value
          : index
      );
    }
  }
};
</script>
<style lang="less" scoped>
.whetherSelect {
  .whetherVehicle {
    position: absolute;
    bottom: 11px;
    right: 15px;
    .whetherBack {
      display: inline-block;
      width: 70px;
      height: 27px;
      line-height: 27px;
      text-align: center;
      font-size: 12px;
      color: #b9b9b9;
      border-radius: 3px;
      margin-left: 8px;
      border: 1px solid #eeeeee;
    }
    .active {
      background-color: #eef5ff;
      color: #2b79ed;
      border: 0px;
    }
  }
}
</style>
