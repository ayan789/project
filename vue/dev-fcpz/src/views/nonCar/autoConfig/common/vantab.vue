<!--
 * @Description: 货运无忧tab页面切换
 * @Date: 2020-4-6 14:25:45
 * @Author: LiuMengJuan
 * @LastEditTime: 2020-06-02 10:47:31
 * @FilePath: \nonCarCongfig\src\views\nonCar\autoConfig\common\vantab.vue
 -->
<template>
  <div
    ref="tabBox"
    id="vantab"
    v-show="showVantab"
  >
    <div
      :class="{ position: true, posShow: centerList }"
      ref="posFix"
    >
      <van-tabs
        swipeable
        sticky
        @click="esent($event)"
        color="#3B8AFE"
        line-height="5px"
        line-width="28px"
      >
        <van-tab
          v-for="(item, i) in message"
          :title="item.title"
          :key="i"
          title-style="{font-size:14px;font-family:PingFangSC-Medium,PingFang SC;font-weight:500; color:rgba(17,17,17,1); line-height:46px;}"
        />
      </van-tabs>
    </div>
    <div
      ref="navHeight"
      style="height: 1.22667rem;visibility: hidden;"
    ></div>
  </div>
</template>

<script>
export default {
  name: "vantab",
  props: {
    message: Array //导航栏标题
  },
  data () {
    return {
      posStyle: { visibility: "hidden" },
      centerList: false,
      showVantab: false,
      activeName: "0"
    };
  },
  mounted () {
    let that = this;
    that.showVantab = true;
    document
      .querySelector(".vantabClass")
      .addEventListener("scroll", function () {
        let scrTop = document.querySelector(".vantabClass").scrollTop;

        if (scrTop > 10) {
          let tabFlag = document.getElementById("vantab0") ? document.getElementById("vantab0").offsetTop : 0;
          if (tabFlag == 0) {
            tabFlag = document.getElementById("vantab1") ? document.getElementById("vantab1").offsetTop : 1;
          }
          if (tabFlag == 1) {
            tabFlag = document.getElementById("vantab2") ? document.getElementById("vantab2").offsetTop : 2;
          }
          if (tabFlag == 2) {
            tabFlag = document.getElementById("vantab3") ? document.getElementById("vantab3").offsetTop : 3;
          }
          if (tabFlag == 3) {
            tabFlag = document.getElementById("vantab4") ? document.getElementById("vantab4").offsetTop : 4;
          }
          let bannerTop = tabFlag
          let bannerHeight = that.$refs.posFix.offsetHeight;
          let navHeight = that.$refs.navHeight.offsetHeight;
          that.showVantab = true;
          let sTop = this.scrollTop;
          if (sTop >= bannerTop - bannerHeight - navHeight) {
            that.$refs.posFix.style.display = "block";
            that.centerList = true;
            that.$refs.posFix.style.marginTop = navHeight + "px";
          } else {
            that.$refs.posFix.style.display = "none";
            that.centerList = false;
            that.showVantab = false;
          }
        }
      });
  },

  methods: {
    esent (i) {
      this.activeName = i;
      var wfEle = document.querySelector(".vantabClass");
      switch (event.target.innerText) {
        case '产品简介':
          var temId = 0
          break;
        case '保障详情':
          var temId = 1
          break;
        case '理赔说明':
          var temId = 2
          break;
        case '常见问题':
          var temId = 3
          break
        case '条款须知':
          var temId = 4
          break
      }
      let popDot = document.getElementById(`vantab${temId}`).offsetTop;
      let bannerHeight = this.$refs.posFix.offsetHeight;
      let navHeight = this.$refs.navHeight.offsetHeight;
      wfEle.scrollTop = popDot - bannerHeight - navHeight;
    }
  }
};
</script>

<style lang="less" scoped>
#vantab {
  width: 100%;
  height: 100%;
  background: rgba(247, 247, 247, 1);
  font-family: PingFangSC-Semibold, PingFang SC;
  * {
    box-sizing: border-box;
  }
  button {
    outline: none;
    background: none;
    border: none;
  }
}
.position {
  width: 100%;
}
.posShow {
  position: fixed;
  top: 0;
  z-index: 11;
}
</style>
