<!--
 * @Description: 产品配置 -- 健康须知 pdf展示
 * @Date: 2020-06-02 10:52:06
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 16:28:57
 * @FilePath: \nonCarCongfig\src\views\nonCar\autoConfig\healthNotice.vue
-->
<template>
  <div class="healthNotice pdf" id="healthNotice">
    <globalNav :title="title" @clickBack="backToPrePage" />
    <div class="pdf-tab">
      <canvas v-for="page in pages" :id="'the-canvas' + page" :key="page">
      </canvas>
      <div v-show="havePdf">
        <div class="radio">
          <van-radio-group
            v-model="radio"
            direction="horizontal"
            ref="radio"
            :disabled="disabled"
          >
            <van-radio name="1">否</van-radio>
            <van-radio name="2">是</van-radio>
          </van-radio-group>
        </div>
        <div class="white">
          <van-checkbox v-model="checked" class="checkbox" :disabled="disabled"
            >声明内容</van-checkbox
          >
          <van-cell
            style="padding:0 14px 0 20px;"
            value="本人确认以上填写的各项内容均真实，未发生健康与职业声明中所有情形；日后如发现有不实告知，保险公司有权按《保险法》等有关法律规定解除或部分解除保险合同。"
          />
        </div>

        <van-button type="info" size="large" @click="enter" :disabled="disabled"
          >下一步</van-button
        >
      </div>
    </div>

    <!-- <iframe
      frameborder="0"
      scrolling="auto"
      marginwidth="5px"
      :src="frameUrl"
      sandbox="allow-same-origin allow-top-navigation allow-forms allow-scripts"
    >
    </iframe> -->
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../components/globalNav";
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
    havePdf: false,
    healthNoticeObj: {},
    radio: "",
    checked: false,
    show: false,
    params: "",
    disabled: false,
    title: "",
    frameUrl: "",
    orderId: ""
  }),
  // watch: {
  //   healthNoticeObj: {
  //     hander(val) {
  //       console.log(83, val);
  //       this.healthNoticeObj = this.$route.query
  //         ? JSON.parse(this.$route.query.obj)
  //         : {};
  //       console.log(86, this.healthNoticeObj);
  //       this.frameUrl = this.healthNoticeObj.url;
  //       this.title = this.healthNoticeObj.title;
  //     },
  //     deep: true,
  //     immediate: true
  //   }
  // },

  mounted: function() {
    // console.log(97, JSON.parse(this.$route.query.obj));
    // // this.healthNoticeObj = JSON.parse(this.$route.query.obj);
    // let { title, url } = this.$route.query
    //   ? JSON.parse(this.$route.query.obj)
    //   : {};
    // this.frameUrl = url;
    // this.frameUrl =
    //   "http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst/s3/browse/88f01b0e-bd24-497a-a61a-419392d931bb.docx";
    // this.title = title;
    // console.log(this.frameUrl);
    let { title, url } = this.$route.query
      ? JSON.parse(this.$route.query.obj)
      : {};
    this.title = title;
    this.orderId = this.$route.query.orderId;
    let obj = {};
    obj.url = url;
  //  obj.url =  "http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst/s3/browse/de27ad33-a246-414c-a76a-b23920b15b3a.pdf"
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
      let strategyId = this.$store.state.config.detailData.strategyId;
      // this.$globalBack();
      if (this.orderId && this.orderId != 0) {
        this.$router.push({
          name: "prIndex",
          query: {
            strategyId: strategyId,
            orderId: this.orderId
          }
        });
      } else {
        this.$router.push({
          name: "prIndex",
          query: {
            strategyId: strategyId
          }
        });
      }
    },
    enter() {
      if (this.radio == "") {
        this.$toast("请认真阅读并勾选");
        return;
      }
      if (this.radio == "2") {
        this.$dialog
          .confirm({
            message:
              "尊敬的客户，根据您目前的健康状态，我公司对您本次的投保不能支持，感谢对我公司的关爱与支持",
            confirmButtonText: "返回产品",
            cancelButtonText: "我再看看"
          })
          .then(() => {
            this.$router.go(-1);
          })
          .catch(() => {
            return;
          });
      } else if (!this.checked) {
        this.$toast("请勾选底部声明内容");
        return;
      } else {
        if (this.orderId) {
          this.$router.push({
            name: "insuranceInfoInputFormcreate",
            orderId: this.orderId
          });
        } else {
          this.$router.push({
            name: "insuranceInfoInputFormcreate"
          });
        }
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

#healthNotice {
  padding: 80px 0 0 0;
  background: rgba(255, 255, 255, 1);
  position: relative;
  .white {
    background: #fff;
  }
  div {
    box-sizing: border-box;
  }
  .pdf {
    margin: 0 auto;
  }
  .pdf-tab {
    padding-bottom: 60px;
  }
  .main {
    position: relative;
    margin: 0 auto;
    width: 100%;
    height: 100%;
    overflow: scroll;
  }
  iframe {
    width: 100%;
    height: 100%;
    padding: 0 5px;
  }

  .main {
    position: relative;
    // margin: 0 auto;
    width: 100%;
    height: 100%;
    overflow: scroll;
  }
  /deep/ .van-cell__title,
  .van-cell__value {
    flex: auto;
    text-align: left;
    color: #999999;
  }
  .radio {
    background-color: #fff;
    margin: 0 auto;
    border-radius: 14px;
    padding: 0.6rem;
    /deep/ .van-radio-group,
    .van-radio-group--horizontal {
      justify-content: space-around;
      padding: 0.6rem;
      background-color: #f7f7f7;
    }
  }
  .checkbox {
    padding: 0.2rem;
    padding-left: 0.5rem;
    font-size: 14px;
    font-weight: 600;
  }
  .van-button,
  .van-button--info,
  .van-button--large {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
  }
}
</style>
