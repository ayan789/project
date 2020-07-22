<template>
  <div>
    <div class="recommend">
      <span>产品推荐</span>
      <div class="products">
        <div
          style="width: 50%;display: inline-block;margin-top: 5px;"
          v-for="(item, index) in product"
          :key="index"
        >
          <div class="coverage" v-if="index < 2">
            <div class="product-img" @click="toUrl(item.url)">
              <img
                 style="width:100%;height: 100%"
                 :src ="item.image"
              />
            </div>
            <div class="product-title">{{ item.title }}</div>
            <span style="margin-top: 4px">
              <span style="display: inline;" class="product-amount">{{ item.price }}</span>
              <span style="display: inline;margin-left: 2px">元</span>
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
// import "@/style/customer/productRecommendation.less";
import config from "../../vue.config.js"
export default {
  props: {
    product: Array
  },
  created(){
    let api = "/api"
    this.env = config.devServer.proxy[api].target;
  },
  data() {
    return {
        env:""//点击图片后的链接前缀
    };
  },
  methods: {
    toUrl: function(url) {
      // console.info("this.env",this.env)
      // console.info("this.env+url",this.env+"/#/"+url)
      // console.info("this.env+url",this.env+"/#/"+url)
      // console.info("localStorage.getItem("userCode")",localStorage.getItem("userCode"))
      // console.info("this.$localStorage.userInfo",this.$localStorage.token)
      // let urlSuffix = '?token='+this.$localStorage.token+'&userCode='+localStorage.getItem("userCode")
      // console.info("windowsHerf=","https://msupsale-test.ccic-net.com.cn"+"/#/"+url+urlSuffix)
      this.$router.push({ path: "/"+url});
      // console.info("this.$localStorage.userInfo",JSON.parse(this.$localStorage.userInfo).token)
      //https://msupsale-uat.ccic-net.com.cn/#/nonCar/millionMedicalCare?token=95D3BEBF780D7608B997511C5F3DD41F&userCode=8000067285&source=shell
      // window.location.href="https://msupsale-test.ccic-net.com.cn"+"/#/"+url+'?token='+this.$localStorage.token+'&userCode='+localStorage.getItem("userCode");
      // window.location.href=this.env+"/#/"+url;
    }
  }
};
</script>
<style lang="less" scoped>
.recommend {
  padding-top: 12px;
  span {
    display: block;
    color: #999999;
    line-height: 17px;
    padding-bottom: 24px;
  }
  .products {
    /*margin-top: 13px;*/
    .coverage {
      overflow: hidden;
      border-bottom: 1px solid #e7e7e7;
      border-radius: 10px;
      width: 162px;
      height: 180px;
      position: relative;
      background-color: #FFFFFF;
      .product-img {
        width: 100%;
        height: 120px;
        /*background-color: greenyellow;*/
      }
      .product-title {
        margin-top: 2px;
        color: #4a4a4a;
        font-size: 15px;
        margin-left: 10px;
      }
      .product-amount {
        color: #fc820e;
        font-size: 15px;
        margin-left: 10px;
      }
    }
  }

  .coverage:nth-last-child(1) {
    border-bottom: transparent;
  }
}
</style>
