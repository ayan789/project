<template>
  <div>
    <div
      class="pdf policyPreview"
      id="policyPreview"
    >
      <globalNav
        title="电子保单"
        @clickBack="backToPrePage"
      />
      <div class="pdf-tab">
        <canvas
          v-for="page in pages"
          :id="'the-canvas' + page"
          :key="page"
        >
        </canvas>
      </div>
    </div>
    <button 
      class="shareBtn"
      @click="share"
      v-if="havePdf"
    >分享</button>
  </div>
</template>
<script>
import { Overlay, Divider, Toast } from "vant";
import globalNav from "../../../components/globalNav";
import PDFJS from "pdfjs-dist";
export default {
  name: "Pdf",
  components: {
    [Overlay.name]: Overlay,
    [Divider.name]: Divider,
    [globalNav.name]: globalNav
  },
  data () {
    return {
      pdfDoc: null,
      pages: 0,
      havePdf: false
    };
  },
  mounted: function () {
    let obj = {};
    obj.url = localStorage.getItem("downLoadUrl");
    obj.httpHeaders = {
      token: localStorage.getItem("token")
    };
    const CMAP_URL = "https://cdn.jsdelivr.net/npm/pdfjs-dist@2.0.943/cmaps/";
    obj.cMapUrl = CMAP_URL;
    obj.cMapPacked = true;
    this._loadFile(obj);
  },
  methods: {
    _renderPage (num) {
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
    _loadFile (url) {
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
    backToPrePage () {
      this.$globalBack();
    },
    // 分享
    share () {
      //调用壳方法，传递调用参数和接受响应数据
      console.log(
        "分享pdf的S3地址是----------：" + localStorage.getItem("downLoadUrl")
      );
      this.$native.shareToWxBySystem(
        localStorage.getItem("downLoadUrl"),
        data => {
          Toast("shareToWxBySystem 接口调用成功，壳响应数据:" + data);
        }
      );
    }
  }
};
</script>
<style lang="less" scoped>
canvas {
  display: block;
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
.shareBtn {
  position: fixed;
  left: 0;
  bottom: 0;
}
</style>
