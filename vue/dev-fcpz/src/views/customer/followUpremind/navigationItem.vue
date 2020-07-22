<template>
  <div class="hello">
    <van-nav-bar
      fixed
      :style="{
        backgroundColor:'white'
      }"
      :title="title"
      :left-text="leftText"
      :left-arrow="leftArrow"
      @click-left="clickBack"
      @click-right="clickRight"
    >
      <div v-if="isSearch || isShare" id="rightFather" slot="right">
        <img :src="getImage()"/>
      </div>
      <div slot="right" style="color: #3B8AFE;font-size: 14px">
        {{rightText}}
      </div>
    </van-nav-bar>
  </div>
</template>

<script>
export default {
  name: "globalNav",
  props: {
    title: String, //导航栏标题
    clear: String, //是否显示透明背景色：'1'（透明），其他（默认）：不透明
    leftText: String, //导航栏左侧返回按钮文本
    rightText: String,//导航栏右侧返回按钮文本
    //分享和搜索不同时显示：
    isSearch: Boolean, //导航栏右侧是否显示搜索按钮，默认不显示
    isShare: Boolean, //导航栏右侧是否显示分享按钮，默认不显示
    leftArrow: {
      type: Boolean,
      default: true
    },//是否显示导航左边ICON
  },

  data() {
    return {
      searchIcon: require("@/assets/common/search.png"),
      shareIcon: require("@/assets/common/share.png"),
      clearStyle: {
        background: "transparent"
      },
      colorStyle: {
        background:
          "linear-gradient(125deg,rgba(30, 70, 246, 1) 0%,rgba(39, 154, 255, 1) 100%)"
      }
    };
  },
  methods: {
    getImage() {
      if (this.isSearch == true) {
        return this.searchIcon;
      } else {
        return this.shareIcon;
      }
    },
    clickBack: function () {
      this.$router.back();
      this.$emit("clickBack", "点击了导航上的返回");
    },
    clickRight: function () {
      console.log("点击了导航上的右侧按钮");
      this.$emit("clickRight", "点击了导航上的右侧按钮");
    }
  }
};
</script>
<style scoped>
.van-nav-bar--fixed {
  z-index: 99;
  height: 60px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

.van-nav-bar__left {
  bottom: 10px;
}

.van-nav-bar__right {
  bottom: 10px;
}

.van-nav-bar__title {
  color: #333333;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
}

.van-nav-bar .van-icon {
  color: #333333;
}

.van-nav-bar__text {
  color: #333333;
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
  width: 100vw;
  height: 15px;
  border-radius: 15px 15px 0px 0px;
  background-color: #ffffff;
  top: 65px;
  z-index: 1000;
  position: fixed;
}
</style>

