<template>
  <div>
    <div class="pdf policyPreview" id="policyPreview">
      <globalNav title="电子保单" @clickBack="backToPrePage" />
      <img
        v-if="havePdf"
        @click="share"
        class="img"
        src="@/assets/common/share.png"
        alt=""
      />
      <div class="policyNoType">
        <div
          :class="[businessPolicyNo ? 'active' : '']"
          v-if="$route.query.businessPolicyNo"
          @click="clickpolicyPreview('businessPolicyNo')"
        >
          商业险
        </div>
        <div
          :class="[trafficPolicyNo ? 'active' : '']"
          v-if="$route.query.trafficPolicyNo"
          @click="clickpolicyPreview('trafficPolicyNo')"
        >
          交强险
        </div>
        <div
          :class="[wealthPolicyNo ? 'active' : '']"
          v-if="$route.query.wealthPolicyNo"
          @click="clickpolicyPreview('wealthPolicyNo')"
        >
          意健险
        </div>
        <div
          :class="[signPolicyNo1 ? 'active' : '']"
          v-if="$route.query.trafficPolicyNo"
          @click="clickpolicyPreview('signPolicyNo')"
        >
          交强险标志
        </div>
      </div>
      <div class="pdf-tab">
        <canvas v-for="page in pages" :id="'the-canvas' + page" :key="page">
        </canvas>
      </div>
    </div>
    <button class="shareBtn" @click="share" v-if="havePdf">分享</button>
  </div>
</template>
<script>
import { Overlay, Divider, Toast, Dialog } from "vant";
import globalNav from "../../../components/globalNav";
import { electronicPolicy } from "@src/http/module/order.js";
import PDFJS from "pdfjs-dist";
export default {
  name: "Pdf",
  components: {
    [Overlay.name]: Overlay,
    [Divider.name]: Divider,
    [globalNav.name]: globalNav
  },
  data() {
    return {
      pdfDoc: null,
      pages: 0,
      havePdf: false,
      policyNo: "",
      downLoadUrl: "",
      signPolicyNo: false,
      businessPolicyNo: false,
      trafficPolicyNo: false,
      signPolicyNo1: false,
      wealthPolicyNo: false
    };
  },
  created() {
    this.$nextTick(() => {
      if (this.$route.query.businessPolicyNo) {
        this.businessPolicyNo = true;
        this.policyNo = this.$route.query.businessPolicyNo;
        this.downLoadPolicy();
      } else {
        if (this.$route.query.trafficPolicyNo) {
          this.trafficPolicyNo = true;
          this.policyNo = this.$route.query.trafficPolicyNo;
          this.downLoadPolicy();
        }
      }
    });
  },
  mounted: function() {
    // let obj = {};
    // obj.url = localStorage.getItem("downLoadUrl");
    // obj.httpHeaders = {
    //   token: localStorage.getItem("token")
    // };
    // const CMAP_URL = "https://cdn.jsdelivr.net/npm/pdfjs-dist@2.0.943/cmaps/";
    // obj.cMapUrl = CMAP_URL;
    // obj.cMapPacked = true;
    // this._loadFile(obj);
  },
  methods: {
    //  电子保单接口
    downLoadPolicy() {
      electronicPolicy({
        policyNo: this.policyNo
      }).then(res => {
        if (res.code == "0") {
          Toast.allowMultiple();
          Toast({
            forbidClick: true,
            message: res.msg,
            type: "fail",
            duration: 3000
          });
          setTimeout(() => {
            Toast.clear();
          }, 3000);
        } else {
          if (res.data == "0") {
            Toast({
              forbidClick: true,
              message: res.msg,
              // message: "请您10分钟后再次进行下载",
              type: "fail",
              duration: 3000
            });
          } else {
            if (this.$isNull(res.data.policyS3path)) {
              Dialog.alert({
                message: res.data.responseHead.appMessage || ""
              }).then(() => {});
              return;
            }
            // localStorage.setItem("downLoadUrl", res.data.policyS3path);
            // localStorage.setItem("signS3pathUrl", res.data.signS3path);
            if (!this.signPolicyNo) {
              this.downLoadUrl = res.data.policyS3path;
            } else {
              this.downLoadUrl = res.data.signS3path;
            }
            let obj = {};
            obj.url = this.downLoadUrl;
            // debugger

            console.log(this.downLoadUrl, "何韦韦");
            let tokenValue = JSON.parse(localStorage.getItem("userInfo")).token;
            console.log(tokenValue, "162===>");
            obj.httpHeaders = {
              token: tokenValue
            };
            const CMAP_URL =
              "https://cdn.jsdelivr.net/npm/pdfjs-dist@2.0.943/cmaps/";
            obj.cMapUrl = CMAP_URL;
            obj.cMapPacked = true;
            this._loadFile(obj);
          }
        }
      });
    },
    clickpolicyPreview(type) {
      switch (type) {
        case "businessPolicyNo":
          (this.businessPolicyNo = true),
            (this.trafficPolicyNo = false),
            (this.signPolicyNo1 = false),
            (this.wealthPolicyNo = false),
            (this.signPolicyNo = false);
          this.policyNo = this.$route.query.businessPolicyNo;
          this.downLoadPolicy();
          break;
        case "trafficPolicyNo":
          (this.businessPolicyNo = false),
            (this.trafficPolicyNo = true),
            (this.wealthPolicyNo = false),
            (this.signPolicyNo1 = false),
            (this.signPolicyNo = false);
          this.policyNo = this.$route.query.trafficPolicyNo;
          this.downLoadPolicy();
          break;
        case "wealthPolicyNo":
          (this.businessPolicyNo = false),
            (this.trafficPolicyNo = false),
            (this.wealthPolicyNo = true),
            (this.signPolicyNo1 = false),
            (this.signPolicyNo = false);
          this.policyNo = this.$route.query.wealthPolicyNo;
          this.downLoadPolicy();
          break;
        case "signPolicyNo":
          (this.businessPolicyNo = false),
            (this.wealthPolicyNo = false),
            (this.trafficPolicyNo = false),
            (this.signPolicyNo1 = true),
            (this.signPolicyNo = true);
          this.policyNo = this.$route.query.trafficPolicyNo;
          this.downLoadPolicy();
          break;
      }
    },
    _renderPage(num) {
      this.pdfDoc.getPage(num).then(page => {
        let canvas = document.getElementById("the-canvas" + num);
        let ctx = canvas.getContext("2d");
        ctx.mozImageSmoothingEnabled = false;
        ctx.webkitImageSmoothingEnabled = false;
        ctx.msImageSmoothingEnabled = false;
        ctx.imageSmoothingEnabled = false;
        let dpr = window.devicePixelRatio || 1;
        let bsr =
          ctx.webkitBackingStorePixelRatio ||
          ctx.mozBackingStorePixelRatio ||
          ctx.msBackingStorePixelRatio ||
          ctx.oBackingStorePixelRatio ||
          ctx.backingStorePixelRatio ||
          1;
        let ratio = dpr / bsr;
        let viewport = page.getViewport(
          screen.availWidth / page.getViewport(1).width
        ); //这是让pdf文件的大小等于视口的大小
        canvas.width = viewport.width * ratio;
        canvas.height = viewport.height * ratio; //这里会进行压缩，解决模糊问题
        canvas.style.width = viewport.width + "px";
        canvas.style.height = viewport.height + "px";
        let renderContext = {
          canvasContext: ctx,
          viewport: viewport,
          transform: [ratio, 0, 0, ratio, 0, 0] //这里会进行放大，解决模糊问题
        };
        page.render(renderContext);
        if (this.pages > num) {
          this._renderPage(num + 1);
        }
      });
    },
    _loadFile(url) {
      // console.log(url, "111");
      // url.url =
      //   "http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst/s3/browse/PDEA20340119020000000003";
      PDFJS.getDocument(url).then(pdf => {
        this.pdfDoc = pdf;
        this.pages = this.pdfDoc.numPages;
        this.$nextTick(() => {
          this._renderPage(1);
          this.havePdf = true;
        });
      });
    },
    // 返回上一页
    backToPrePage() {
      this.$globalBack();
    },
    // 分享
    share() {
      //调用壳方法，传递调用参数和接受响应数据
      console.log("分享pdf的S3地址是----------：" + this.downLoadUrl);
      this.$native.shareToWxBySystem(this.downLoadUrl, data => {
        Toast("shareToWxBySystem 接口调用成功，壳响应数据:" + data);
      });
    }
  }
};
</script>
<style lang="less" scoped>
.img {
  position: fixed;
  top: 28px;
  right: 20px;
  z-index: 9999;
  color: red;
  width: 20px;
  height: 20px;
}
canvas {
  display: block;
}
.policyNoType {
  display: flex;
  div {
    font-size: 16px;
    flex: 1;
    text-align: center;
  }
}
.pdf-tab {
  padding-bottom: 46px;
}
#policyPreview {
  padding: 80px 0 40px 0;
  background: rgba(255, 255, 255, 1);
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
}
.shareBtn {
  position: fixed;
  left: 0;
  bottom: 0;
}
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
  position: fixed;
  bottom: 0;
}
.active {
  font-size: 18px !important;
  color: rgb(30, 70, 246) !important;
}
</style>
