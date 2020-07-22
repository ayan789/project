<template>
  <div class="pdf policyPreview" id="policyPreview">
    <!-- <globalNav :title="$route.query.title" @clickBack="backToPrePage" /> -->
    <!-- <div class="pdf-tab">
      <div
        :class="['btn-def', { 'btn-active': activeIndex == index }]"
        v-for="(item, index) in pdfList"
        :key="index"
        @click.stop="pdfClick(item.pdfUrl, index)"
      >{{ item.title }}</div>
    </div>
    <pdf v-for="i in numPages" :key="i" :src="pdfUrl" :page="i"></pdf>
    <!-- <p>
      电子保单pdf预览
    </p>-->
    <!-- <button @click="share">分享</button> -->

    <iframe
      width="100%"
      height="100%"
      marginheight="5px"
      marginwidth="5px"
      :src="frameUrl"
    >
    </iframe>
    <div class="mask" v-show="showMask">
      <div class="content">
        <img :src="srcUrl" alt="img" />
      </div>
    </div>
  </div>
</template>
<script>
import { Overlay, Divider, Toast } from "vant";
import globalNav from "../../../components/globalNav";

export default {
  components: {
    [Overlay.name]: Overlay,
    [Divider.name]: Divider,
    [globalNav.name]: globalNav
  },
  data() {
    return {
      showMask: false,
      srcUrl: require("@/assets/download/live_weixin.png"),
      frameUrl: ""
    };
  },
  mounted: function() {
    this.$nextTick(() => {
      // 判断是否是微信
      this.weixinTip();
      this.frameUrl = this.$GetRequest("shortLink", true)
        ? decodeURIComponent(this.$GetRequest("shortLink", true) + "")
        : "";
    });
  },
  methods: {
    // 返回上一页
    backToPrePage() {
      this.$globalBack();
    },
    // // 分享
    // share() {
    //   let param = {
    //     url: localStorage.getItem("shortLink"),
    //     type: "0",
    //     titleBarTag: "1",
    //     shareUrl: localStorage.getItem("shortLink"), //TODO suncao 影像上传
    //     titleBar: "电子发票预览",
    //     sharePlatform: ["0"],
    //     shareType: "url",
    //     title: "电子发票预览",
    //     imgURL:
    //       "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
    //     image: "", //分享图片（base64编码）, 与imgURL二选一
    //     desc: "尊敬的客户，您的电子发票已生成，可以在线预览电子发票"
    //   };
    //   console.log("请求壳子的参数===", JSON.stringify(param));
    //   this.$native.openWebView(param, data => {});
    // },
    weixinTip() {
      var ua = navigator.userAgent;
      var isWeixin = !!/MicroMessenger/i.test(ua);
      if (isWeixin) {
        this.showMask = true;
      } else {
        this.showMask = false;
      }
    }
  }
};
</script>
<style lang="less" scoped>
#policyPreview {
  padding: 80px 0 40px 0;
  background: #555;
  position: relative;
  height: calc(100vh - 120px);
  div {
    box-sizing: border-box;
  }
  .pdf {
    margin: 0 auto;
  }
  .main {
    position: relative;
    margin: 0 auto;
    width: 100%;
    height: 100%;
    overflow: scroll;
  }
  .arrow {
    float: right;
  }
  // p {
  //   width: 100%;
  //   height: 28px;
  //   font-size: 20px;
  //   font-family: PingFangSC-Regular, PingFang SC;
  //   font-weight: 400;
  //   color: rgba(51, 51, 51, 1);
  //   line-height: 28px;
  //   position: absolute;
  //   left: 50%;
  //   top: 50%;
  //   text-align: center;
  //   transform: translate(-50%, -50%);
  // }
  button {
    width: 100%;
    height: 46px;
    background: linear-gradient(
      125deg,
      rgba(42, 128, 255, 1) 0%,
      rgba(42, 165, 255, 1) 100%
    );
    border: none;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 46px;
    position: relative;
    bottom: 0;
  }
  .mask {
    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    width: 100%;
    height: 100%;
    background: #333;
    padding: 10px;
    img {
      margin: auto;
      right: 0;
      height: auto;
      max-width: 100%;
      max-height: 100%;
    }
    h2 {
      font-family: PingFangSC-Regular, PingFang SC;
      position: absolute;
      width: 100%;
      height: 20%;
      color: #fff;
      text-align: center;
      top: 50%;
    }
  }
}
</style>
