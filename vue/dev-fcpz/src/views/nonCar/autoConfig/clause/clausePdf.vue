<!--
 * @Description: 非车产品配置---条款的pdf展示页面
 * @Date: 2020-06-01 16:33:36
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-02 16:03:25
 * @FilePath: \nonCarCongfig\src\views\nonCar\autoConfig\clause\clausePdf.vue
-->
<template>
  <div class="pdf clausePdf" id="clausePdf">
    <globalNav :title="$route.query.title" @clickBack="backToPrePage" />
    <div class="pdf-tab">
      <canvas v-for="page in pages" :id="'the-canvas' + page" :key="page">
      </canvas>
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../../components/globalNav";
import PDFJS from "pdfjs-dist";
export default {
  name: "personalTerms_Ckuan",
  components: {
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    appoint: "1",
    pdfDoc: null,
    pages: 0,
    havePdf: false
  }),
  mounted: function() {
    let obj = {};
    obj.url = this.$route.query.link;
    console.log("文件地址========"+obj.url);
    // TODO suncao
    // obj.url = "http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst/s3/browse/c3f2209c-cd5d-49de-a0bd-58f0d3db8a4b.pdf"
    obj.httpHeaders = {
      token: localStorage.getItem("token")
    };
    const CMAP_URL = "https://cdn.jsdelivr.net/npm/pdfjs-dist@2.0.943/cmaps/";
    obj.cMapUrl = CMAP_URL;
    obj.cMapPacked = true;
    this._loadFile(obj);
  },
  methods: {
    backToPrePage() {
      this.$globalBack();
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
      PDFJS.getDocument(url).then(pdf => {
        this.pdfDoc = pdf;
        this.pages = this.pdfDoc.numPages;
        this.$nextTick(() => {
          this._renderPage(1);
          this.havePdf = true;
        });
      });
    }
  }
};
</script>

<style lang="less" scoped>
canvas {
  display: block;
  background: rgba(255, 255, 255, 1);
}
#clausePdf {
  padding: 80px 0 0 0;
  background: rgba(255, 255, 255, 1);
  position: relative;

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
}
</style>
