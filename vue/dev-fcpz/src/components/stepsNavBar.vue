<!--
 * @Author: King
 * @Page: 步骤导航栏
 * @Date: 2020-03-04 16:22:16
 * @Last Modified by: King
 * @Last Modified time: 2020-04-29 17:05:52
 * @FilePath: /src/components/stepsNavBar.vue
-->
<template>
  <div :class="['nav-bar', vehicle && 'vehicle-nav-bar']">
    <div
      v-if="left"
      :class="{ 'nav-bar-left': true, 'type-nav-bar-left': vehicle }"
      @click="back()"
    >
      <van-icon name="arrow-left" color="white" size="22px" />
    </div>
    <div
      class="nav-bar-right"
      v-if="right"
      :class="{ 'type-nav-bar-right': vehicle }"
    >
      <div @click="e => (right.click ? right.click(e) : '')">
        <img v-if="isSrc(right)" :src="right.icon" />
        <van-icon
          v-else-if="right.icon"
          :name="right.icon"
          :color="right.color || 'white'"
          :size="right.size || 22"
        />
        <span v-if="right.title">{{ right.title }}</span>
      </div>
    </div>
    <div class="nav-bar-right" v-if="rights">
      <div v-for="(item, index) in rights" :key="index" @click="item.click">
        <img v-if="isSrc(right)" :src="right.icon" />
        <van-icon
          v-else-if="right.icon"
          :name="right.icon"
          :color="right.color || 'white'"
          :size="right.size || 22"
        />
        <span v-if="item.title">{{ item.title }}</span>
      </div>
    </div>
    <div class="nav-bar-title">
      <!-- title 前有icon -->
      <div v-for="(step, index) in steps" :key="index" class="step">
        <span
          v-if="index !== 0"
          :class="{ line: true, 'active-line': active === step.name }"
        />
        <!-- 加图片 -->
        <img
          :class="['step-icon', active === step.name ? 'active-step-icon' : '']"
          :src="active === step.name ? step.simg : step.img"
          alt=""
          draggable="false"
        />
        <span :class="{ 'active-title': active === step.name }">
          {{ step.name }}
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "steps-nav-bar",
  props: {
    active: [Number, String],
    type: {
      type: String,
      default: ""
    },
    left: {
      type: Boolean,
      default: true
    },
    right: Object,
    rights: Array
  },
  data: () => ({
    steps: []
  }),
  computed: {
    vehicle() {
      return this.type == "vehicle";
    }
  },
  created() {
    if (this.type === "vehicle") {
      this.steps = [
        // ['车辆信息', '获取报价', '信息确认']
        {
          name: "车辆信息",
          img: require("../assets/vehicle/vehicleInfo/step1.png"),
          simg: require("../assets/vehicle/vehicleInfo/step1s.png")
        },
        {
          name: "获取报价",
          img: require("../assets/vehicle/vehicleInfo/step2.png"),
          simg: require("../assets/vehicle/vehicleInfo/step2s.png")
        },
        {
          name: "信息确认",
          img: require("../assets/vehicle/vehicleInfo/step3.png"),
          simg: require("../assets/vehicle/vehicleInfo/step3s.png")
        }
      ];
    }
  },
  methods: {
    back() {
      if (this.$listeners.back) {
        this.$listeners.back();
      }
    },
    isSrc(v) {
      if (v) {
        if (v.icon) {
          if (v.icon.indexOf("data:image") == 0) {
            return true;
          }
        }
      }
      return false;
    },
    src(v) {
      const path = v.replace(/.*(assets.*)/, "../$1");
      return path;
    }
  }
};
</script>
<style lang="less" scoped>
* {
  overflow: visible;
  box-sizing: content-box;
}
.nav-bar {
  height: 44px;
  padding: 20px 8px 0px;
  background: linear-gradient(129deg, #1e46f6 0%, #279aff 100%);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  position: relative;
}
.vehicle-nav-bar {
  background: url("../assets/vehicle/vehicleInfo/vehiclestepnav.png");
  background-repeat: no-repeat;
  background-size: 100%;
  height: 126px;
}
.nav-bar-left,
.nav-bar-right {
  position: absolute;
  display: flex;
  flex-direction: row;
  height: 44px;
  align-items: center;
  img {
    width: 22px;
    height: 22px;
  }
}
.nav-bar-left {
  left: 8px;
}
.nav-bar-right {
  right: 8px;
}
.type-nav-bar-left {
  bottom: 82px;
}
.type-nav-bar-right {
  bottom: 82px;
}
.nav-bar-title {
  position: absolute;
  left: 0;
  pointer-events: none;
  display: flex;
  flex: 1;
  flex-direction: row;
  justify-content: center;
  color: #ffffffcc;
  font-size: 12px;
  font-family: PingFangSC-Medium, PingFang SC;
  width: 100%;
  overflow: visible;
  word-break: keep-all;
  white-space: nowrap;
  .active-title {
    color: #ffffff;
    // font-size: 17px;
    font-weight: 500;
  }
}
.step {
  display: flex;
  align-items: center;
  overflow: visible;
  word-break: keep-all;
  white-space: nowrap;
  height: 44px;
  .step-icon {
    margin-right: 3px;
    width: 17px;
    height: 17px;
  }
  .active-step-icon {
    opacity: 1;
  }
}
.line {
  width: 26px;
  height: 1px;
  border-bottom: 1px dashed #ffffffcc;
  margin: 0 10px;
}
.active-line {
  border-bottom: 1px dashed #ffffff;
}
</style>
