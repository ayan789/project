/* 曹征前公共组件--销售助手 */
<template>
  <div class="customerSell-div">
    <div class="desc">
      <div class="img-div" @click="roobit">
        <img
          style="width:100%;height:100%"
          src="@/assets/customer/customerEquity/robet.png"
          alt="edit"
        />
      </div>
      <p class="text">根据大数据分析显示，当前客户可能投保百万医疗的几率较高，请主动联系客户为其投保</p>
      <div class="know">
        <div class="button-a" style="border-right: 1px solid #F7F7F7;color:#0D84F2"><span @click="onKnow">了解产品</span>
        </div>
        <div class="button-a" style="margin-left: 10px;color:#14D8DE;"><span @click="onShare">分享报价</span></div>
      </div>
    </div>


  </div>
</template>

<script>
import { Toast } from 'vant';
import config from "../../../../vue.config.js";
export default {
  name: "customerSell",
  props: {
    showData: Object
  },
  created() {
    let api = "/api";
    this.env = config.devServer.proxy[api].target;
  },
  data: () => ({
    know: "",
    env: ""//点击图片后的链接前缀
  }),
  methods: {
    roobit() {
      Toast("敬请期待");
    },
    onShare() {
      this.$router.push({ name: 'millionPlan' });
    },
    onKnow() {
      this.$router.push({ name: 'millionMedicalCare' });
      // window.location.href=this.env+url;
    }
  }
};
</script>

<style lang="less" scoped>
.customerSell-div {
  padding: 30px 25px;
  position: relative;
  height: 60vh;

  .desc {
    border-radius: 10px;
    padding: 11px 20px 11px 20px;
    box-shadow: 0px 0.2px 5px 5px rgba(91, 91, 91, 0.09);
    width: 58vw;

    .know {
      display: flex;
      margin-top: 10px;

      .button-a {
        width: 50%;
        line-height: 26px;
        text-align: center;
      }
    }

    .img-div {
      width: 25vw;
      position: absolute;
      right: 0px;
      top: 0px;
    }

    .text {
      padding-bottom: 20px;
      border-bottom: 1px dotted #EDEEEF;
      color: #333333;
    }
  }
}
</style>
