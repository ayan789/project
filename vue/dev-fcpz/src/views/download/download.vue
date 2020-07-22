<template>
  <div class="downloadCover">
    <!-- bg -->
    <img :src="topPage" class="bg" alt="" />
    <!-- wechat_hint-bg -->

    <!-- word -->
    <div class="info" v-show="false">
      <img :src="info" alt="" />
    </div>
    <!-- btnPart -->
    <div class="botPart">
      <div class="btn" @click="download('ios')">
        <a href="javascript:;" id="btn-ios-down" title="iOS版本下载">
          <img :src="iosBtn" alt="" />
        </a>
      </div>
      <div class="btn adBtn" @click="download('android')">
        <a href="javascript:;" id="btn-android-down" title="android版本下载">
          <img :src="adroidBtn" alt="" />
        </a>
      </div>
    </div>

    <van-overlay :show="isShowWechatHint">
      <img
        :src="wechatHint"
        class="wechat_hint"
        v-show="isShowWechatHint"
        alt=""
      />
    </van-overlay>
  </div>
</template>
<script>
import { Toast, Overlay } from "vant";
import { manageVersion } from "../../http/module/common.js";
export default {
  data() {
    return {
      bgPage: require("@/assets/download/bgPage.png"),
      topPage: require("@/assets/download/top_bg.png"),
      wechatHint: require("@/assets/download/live_weixin.png"),
      info: require("@/assets/download/info.png"),
      iosBtn: require("@/assets/download/iosBtn.png"),
      adroidBtn: require("@/assets/download/adroidBtn.png"),
      isShowWechatHint: false,
      androidDownUrl: "", //Android下载地址
      iosDownUrl: "", //IOS下载地址
      isIphone: false //判断是否iOS设备
    };
  },
  mounted() {
    var ua = window.navigator.userAgent.toLowerCase();
    console.log("current device userAgent:" + ua);

    const isWeiXin = this.$isWeiXin();
    if (isWeiXin) {
      this.isShowWechatHint = true;
    }

    this.isIphone = this.isIOS();
    console.log("=========isIOS:" + this.isIphone);

    this.downloadIos();
    this.downloadAndroid();
  },
  methods: {
    downloadAndroid() {
      //Android版本下载
      const params = {
        platform: "1"
      };

      manageVersion(params).then(res => {
        if (res.code == "1") {
          this.androidDownUrl = res.data.appUrl;
        }
      });
    },
    downloadIos() {
      //IOS版本下载
      const iosParams = {
        platform: "2"
      };

      manageVersion(iosParams).then(res => {
        if (res.code == "1") {
          this.iosDownUrl = res.data.appUrl;
        }
      });
    },
    download(name) {
      const isWeiXin = this.$isWeiXin();
      this.isIphone = this.isIOS();
      console.log("=========isIOS:" + this.isIphone);

      if (isWeiXin) {
        Toast("请在浏览器中下载！");
        return;
      }

      switch (name) {
        case "ios": {
          console.log("=========iosDownUrl:" + this.iosDownUrl);
          if (this.isIphone) {
            if (this.$isNull(this.iosDownUrl)) {
              this.downloadIos();
              Toast("请重新点击下载中！");
            } else {
              window.location.href = this.iosDownUrl;
            }
            // window.location.href =
            //   "itms-services://?action=download-manifest&url=https%3A%2F%2Ferp.fulan.com.cn%2FfulanMAM-app%2Fweb%2Fdownload%2Fplist%3Fid%3D3";
          } else {
            Toast("请在苹果手机中下载！");
          }
          break;
        }
        case "android": {
          console.log("=========androidDownUrl:" + this.androidDownUrl);
          if (this.isIphone) {
            Toast("请在安卓手机中下载！");
          } else {
            if (this.$isNull(this.androidDownUrl)) {
              this.downloadAndroid();
              Toast("请重新点击下载中！");
            } else {
              window.location.href = this.androidDownUrl;
            }
            // window.location.href =
            //   "https://uat-ccic-download.oss-cn-shanghai-finance-1-pub.aliyuncs.com/supsale/SuperSaleAndroid-uat.apk";
          }
          break;
        }
      }
    },
    //判断是否是IOS设备
    isIOS() {
      const userAgent = window.navigator.userAgent.toLowerCase();
      console.log("=========userAgent:\n" + userAgent);

      const isiOS = !!userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
      const mac = userAgent.indexOf("mac") > -1 ? true : false;
      const iphone = userAgent.indexOf("iphone") > -1 ? true : false;

      if (isiOS || mac || iphone) {
        return true;
      } else {
        return false;
      }
    }
  }
};
</script>
<style lang="less" scoped>
.downloadCover {
  position: relative;
  background: white;
  padding-bottom: 0px;
  .bg {
    width: 100%;
    height: 58%;
  }
  .wechat_hint {
    position: absolute;
    top: 10px;
    margin: 10px;
    right: 0;
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
  }
  .info {
    position: absolute;
    top: 32px;
    left: 0;
    right: 0;
    margin: auto;
    width: 150px;
    height: 39px;
    img {
      width: 100%;
    }
  }
  .botPart {
    position: relative;
    bottom: 0px;
    left: 0;
    right: 0;
    margin-top: 50px;
    .btn {
      width: 245px;
      height: 48px;
      margin: 0 auto;
      img {
        width: 100%;
      }
    }
    .adBtn {
      margin-top: 18px;
    }
  }
}
</style>
