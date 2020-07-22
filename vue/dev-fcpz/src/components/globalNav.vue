<template>
  <div class="hello">
    <van-nav-bar
      fixed
      :style="clear == '1' ? clearStyle : colorStyle"
      :title="title"
      :left-text="leftText"
      :left-arrow="leftArrow"
      @click-left="clickBack"
      @click-right="clickRight"
    >
      <div v-if="isSearch || isShare || isHome" id="rightFather" slot="right">
        <img :src="getImage()" />
      </div>
    </van-nav-bar>
    <div class="navigatorBottom" v-if="clear != '1'" />
  </div>
</template>

<script>
export default {
  name: "globalNav",
  props: {
    title: String, //导航栏标题
    clear: String, //是否显示透明背景色：'1'（透明），其他（默认）：不透明
    leftText: String, //导航栏左侧返回按钮文本
    //分享和搜索不同时显示：
    isSearch: Boolean, //导航栏右侧是否显示搜索按钮，默认不显示
    isShare: Boolean, //导航栏右侧是否显示分享按钮，默认不显示
    isHome: Boolean, //是否返回首页
    leftArrow:{ //导航栏左侧是否显示返回箭头
      type:Boolean,
      default:true
    }
  },

  data() {
    return {
      searchIcon: require("@/assets/common/search.png"),
      shareIcon: require("@/assets/common/share.png"),
      bakcIcon: require("@/assets/common/index.png"),

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
      } else if (this.isHome == true) {
        return this.bakcIcon;
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
.van-nav-bar--fixed {
  z-index: 99;
  height: 76px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

.van-nav-bar__left {
  bottom: 15px;
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
  width: 100vw;
  height: 15px;
  border-radius: 15px 15px 0px 0px;
  background-color: #ffffff;
  top: 65px;
  z-index: 1000;
  position: fixed;
}
</style>
