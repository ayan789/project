<template>
  <div class="whichSelected">
    <div class="whetherVehicle" :style="isabsolute ? absoluteStyle : {}">
      <div
        class="whetherBack"
        v-for="(item, index) in whetherOptions"
        :key="index"
        :style="getStyle()"
        @click="regardlessSelect(index)"
        :class="{ active: index == print }"
      >
        {{ item }}
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    whetherOptions: Array,
    width: String,
    space: String,
    isabsolute: Boolean
  },
  data() {
    return {
      print: 3,
      absoluteStyle: {
        position: "absolute"
      },
      regardlessList: ["是", "否"]
    };
  },
  methods: {
    getStyle() {
      return {
        width: this.width,
        marginLeft: this.space
      };
    },
    regardlessSelect(index) {
      this.print = index;
    }
  }
};
</script>
<style lang="less" scoped>
.whichSelected {
  .whetherVehicle {
    top: 11px;
    right: 15px;
    float: right;
    .whetherBack {
      display: inline-block;
      width: 60px;
      height: 27px;
      line-height: 27px;
      text-align: center;
      color: rgba(136, 136, 136, 1);
      border-radius: 5px;
      border: 1px solid rgba(136, 136, 136, 1);
      margin-left: 10px;
      font-size: 12px;
    }
    .active {
      color: #3b8afe;
      border: 1px solid rgba(59, 138, 254, 1);
    }
  }
}
</style>
