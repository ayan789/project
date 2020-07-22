<!--
 * @Author: King
 * @Page: 通用导航栏
 * @Date: 2020-03-04 16:22:29
 * @Last Modified by: King
 * @Last Modified time: 2020-05-15 16:05:58
 * @FilePath: /src/components/navBar.vue
-->

<template>
  <div class="nav-bar-container">
    <img class="background-img" v-if="backgroundImage" :src="backgroundImage" />
    <van-nav-bar
      :border="false"
      :style="clear == '1' ? clearStyle : colorStyle"
      :title="title"
      :left-text="leftText"
      :right-text="rightText"
      @click-left="clickBack"
      @click-right="clickRight"
    >
      <div slot="left" v-if="left">
        <van-icon name="arrow-left" color="white" size="22px" />
      </div>
      <div v-if="isSearch || isShare" id="rightFather" slot="right">
        <img :src="getImage()" />
      </div>
      <div v-if="rightIcon" slot="right">
        <van-icon :name="rightIcon" size="20" />
      </div>
    </van-nav-bar>
    <div class="navigatorBottom" v-if="clear != '1'" />
  </div>
</template>

<script>
export default {
  name: "nav-bar",
  props: {
    title: String, //导航栏标题
    type: String,
    clear: String, //是否显示透明背景色：'1'（透明），其他（默认）：不透明
    left: {
      type: Boolean,
      default: () => true
    },
    leftText: String, //导航栏左侧返回按钮文本
    rightIcon: String,
    rightText: String,
    //分享和搜索不同时显示：
    isSearch: Boolean, //导航栏右侧是否显示搜索按钮，默认不显示
    isShare: Boolean //导航栏右侧是否显示分享按钮，默认不显示
  },

  data() {
    let background =
      "linear-gradient(125deg,rgba(30, 70, 246, 1) 0%,rgba(39, 154, 255, 1) 100%)";
    if (this.type) {
      background = "transparent";
    }
    return {
      searchIcon: require("@/assets/common/search.png"),
      shareIcon: require("@/assets/common/share.png"),
      clearStyle: {
        background: "transparent"
      },
      colorStyle: {
        background
      }
    };
  },
  computed: {
    backgroundImage() {
      switch (this.type) {
        case "vehicle":
          return require("@/assets/vehicle/vehiclebg.png");
        default:
          return "";
      }
    }
  },
  methods: {
    getImage() {
      if (this.isSearch == true) {
        return this.searchIcon;
      } else {
        return this.shareIcon;
      }
    },
    clickBack: function() {
      this.$emit("clickBack", "点击了导航上的返回");
    },
    clickRight: function() {
      console.log("点击了导航上的右侧按钮");
      this.$emit("clickRight", "点击了导航上的右侧按钮");
    }
  }
};
</script>
<style scoped>
.nav-bar-container {
  position: relative;
  overflow: visible;
}
.background-img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border: 0;
}
.van-nav-bar {
  height: 77px;
  display: flex;
  position: relative;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.van-nav-bar__left {
  bottom: 17px;
}

.van-nav-bar__right {
  bottom: 17px;
}

.van-nav-bar__title {
  color: white;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
}

.van-nav-bar .van-icon {
  color: white;
}

.van-nav-bar__text {
  color: white;
}

#rightFather {
  height: 22px;
  width: 22px;
  padding: 11px 0px 11px 22px;
}

#rightFather > img {
  display: block;
  width: 22px;
  height: 22px;
  margin: 0 auto;
}

.navigatorBottom {
  width: 100%;
  height: 15px;
  border-radius: 15px 15px 0px 0px;
  background-color: #ffffff;
  bottom: -1px;
  position: absolute;
  z-index: 1;
}
</style>
