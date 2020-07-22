<!--
 * @Description: 非车产品配置---条款的pdf展示页面
 * @Date: 2020-06-01 16:33:36
 * @Author: zhangminjie
 * @LastEditTime: 2020-06-01 17:25:33
 * @FilePath: \nonCarCongfig\src\views\nonCar\autoConfig\clause\clausePdf.vue
-->
<template>
  <div>
    <globalNav :title="$route.query.title" @clickBack="backToPrePage" />
    <iframe
    :src="src"
    style="overflow: auto; position: absolute; top: 0; right: 0; bottom: 0; left: 0; width: 100%; height: 100%"
    ></iframe>
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
    havePdf: false,
    src:""
  }),
  mounted: function() {
    // let obj = {};
      this.src = "https://view.officeapps.live.com/op/view.aspx?src=" + this.$route.query.link;
      // this.src = " https://view.officeapps.live.com/op/view.aspx?src=https://msupsale-test.ccic-net.com.cn/api/cst/s3/browse/e8300975-30b9-4c8f-97f4-afcc27848f98.docx";
    // this.src = "https://msupsale-test.ccic-net.com.cn/api/cst/s3/browse/e8300975-30b9-4c8f-97f4-afcc27848f98.docx"
  },
  methods: {
    backToPrePage() {
      this.$globalBack();
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
