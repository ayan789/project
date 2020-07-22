<template>
  <div class="content" ref="container">
    <div class="header" v-if="!this.isNoShare">
      <van-nav-bar fixed style="background:#FFF;" v-if="!this.isNoShare">
        <img
          :src="shareBack"
          @click="onClickLeft()"
          slot="left"
          style="width:50%;height:50%;"
        />
        <span slot="title" class="center">承诺书</span>
        <img :src="shareIcon" slot="right" @click="onClickRight()" />
      </van-nav-bar>
    </div>
    <div class="animated heartBeat">
      <div class="title">
        <img src="../../../assets/performance/icon6.png" />
      </div>
      <div class="word">
        为达成2020年业绩目标，本人郑重承诺
      </div>
      <div class="wordPrice">
        年度总保费
      </div>
      <div class="price">
        {{ (queryData.insurePremium / 10000).toFixed(2) }}万元
      </div>
      <div class="info">
        <div class="info_div">
          <div class="img">
            <img src="../../../assets/performance/icon4.png" />
          </div>
          <div class="wordInfo">
            <p class="p1">车险保费</p>
            <p class="p2">
              {{ (queryData.carInsurePremium / 10000).toFixed(2) }}万元
            </p>
          </div>
        </div>
        <div class="info_div">
          <div class="img">
            <img src="../../../assets/performance/icon1.png" />
          </div>
          <div class="wordInfo">
            <p class="p1">财产险保费</p>
            <p class="p2">
              {{ (queryData.propInsurePremium / 10000).toFixed(2) }}万元
            </p>
          </div>
        </div>
        <div class="info_div">
          <div class="img">
            <img src="../../../assets/performance/icon2.png" />
          </div>
          <div class="wordInfo">
            <p class="p1">意外险保费</p>
            <p class="p2">
              {{ (queryData.accInsurePremium / 10000).toFixed(2) }}万元
            </p>
          </div>
        </div>
        <div class="info_div">
          <div class="img">
            <img src="../../../assets/performance/icon3.png" />
          </div>
          <div class="wordInfo">
            <p class="p1">健康险保费</p>
            <p class="p2">
              {{ (queryData.heaInsurePremium / 10000).toFixed(2) }}万元
            </p>
          </div>
        </div>
        <div style="clear:both"></div>
      </div>
      <div class="name">
        <span class="person">承诺人(签名)：</span>
        <img class="personImg" crossOrigin="anonymous" :src="imageUrl" />
      </div>
      <div align="right" class="view-button-wrap" v-if="!this.isNoShare">
        <span @click="toDetailsPage">查看明细</span>
        <van-icon size="14" name="arrow" class="arrow-icon" />
      </div>
      <img class="picture" src="../../../assets/performance/icon5.png" />
    </div>
    <fire v-if="showFire" />
    <!-- <fire /> -->
  </div>
</template>
<script>
import Html2canvas from "html2canvas";
import { getShareUrl } from "@src/http/module/customerApi.js";
import { Toast } from "vant";
import { trackMap } from "@src/utils/index.js";
import { responsibilityListQuery } from "@src/http/module/performance.js";
export default {
  components: {
    fire: () => import("./fire")
  },
  data: () => ({
    showFire: true, // 展示烟花
    queryData: "",
    shareIcon: require("@/assets/nonCar/common/shareBlack.png"),
    shareBack: require("@/assets/nonCar/common/back.png"),
    // isNoShare: false,
    isNoShare: true,
    imageUrl: "",
    id: ""
  }),
  mounted() {
    const timer = null;
    setTimeout(() => {
      clearTimeout(timer);
      this.showFire = false;
      /* -- */
      this.isNoShare = false;
    }, 8000);
  },
  created() {
    let params = decodeURIComponent(this.$GetRequest("data", true));
    if (!this.$isNull(params)) {
      this.isNoShare = JSON.parse(params).isNoShare;
      this.id = JSON.parse(params).id;
      localStorage.setItem("token", JSON.parse(params).token);
    }
    if (this.$route.query.id) {
      this.id = this.$route.query.id;
    }
    this.getInfo();
    // this.queryData = JSON.parse(this.$route.query.data) || params;
    // this.imageUrl = this.queryData.userSign;
  },
  methods: {
    // 返回上一页
    onClickLeft() {
      console.log("点击了返回按钮");
      if (window.WebViewJavascriptBridge) {
        this.$native.closeWeb(data => {});
      } else {
        this.$globalBack();
      }
    },
    getInfo() {
      responsibilityListQuery({ id: this.id }).then(response => {
        this.queryData = response.data.list[0];
        this.imageUrl = this.queryData.userSign;
      });
    },
    // 点击右边的分享按钮
    onClickRight() {
      console.log("点击了分享按钮");
      this.sharePage();
    },
    // 查看明细
    toDetailsPage() {
      this.$router.push({ name: "responsibilityDetail" });
    },
    async getShareUrlTo(tempUrl) {
      let res = await getShareUrl({ url: encodeURI(tempUrl) });
      //调用接口参数 TODO suncao
      console.log(res);
      const jsonObject = {
        sharePlatform: ["0", "1"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
        shareType: "img", //"url" - 链接； "img" - 图片； "text" - 文字
        title: "承诺书", //分享标题
        imgURL:
          "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
        image: "", //分享图片（base64编码）, 与imgURL二选一
        // url:
        //   this.$getIP() +
        //   "/performance/responsibility/promiseBook?params=" +
        //   JSON.stringify(params), //分享链接
        url: res.data.shortUrl,
        desc: "业绩承诺书", //分享描述
        phoneNum: "" //电话号码,当需要短信分享时获取
      };
      console.log(jsonObject);
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.share(jsonObject, data => {
        //Toast("share 接口调用成功，壳响应数据:" + data);
        console.log(data);
        if (data.resultCode === "1") {
          trackMap(
            "c_performance_responsibility_letter_share",
            "业绩责任书分享",
            {
              c_salesperson_type: "N",
              c_market_type: "Y",
              c_market_name: "",
              c_market_id: "",
              c_share_way:
                res.responseBody && res.responseBody.shareSuccessName,
              c_event_category: "事件所属类别"
            }
          );
        }
      });
    },
    // 分享页面信息  TODO  分享的图片base64太大了
    sharePage() {
      this.isNoShare = true; // 点击分享先去除头部导航栏 截图
      this.$nextTick(() => {
        Toast.loading();
        window.pageYOffset = 0;
        document.documentElement.scrollTop = 0;
        document.body.scrollTop = 0;
        const params = {
          useCORS: true,
          height: document.body.scrollHeight
        };
        Html2canvas(this.$refs.container, params).then(canvas => {
          let base64image = canvas.toDataURL("image/jpeg", 0.5);
          base64image = base64image.replace("data:image/jpeg;base64,", "");
          const jsonObject = {
            sharePlatform: ["0", "1"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
            shareType: "img", //"url" - 链接； "img" - 图片； "text" - 文字
            image: base64image //base64
          };
          Toast.clear();
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.share(jsonObject, data => {
            this.isNoShare = false; // 回调之后继续显示nav-bar
            // Toast("share 接口调用成功，壳响应数据:" + data);
          });
        });
      });
      // let params = {
      //   id: this.id,
      //   isNoShare: true,
      //   token: JSON.parse(localStorage.getItem("userInfo")).token
      // };
      // console.log(
      //   "url=========" +
      //     this.$getIP() +
      //     "/performance/responsibility/promiseBook?data=" +
      //     JSON.stringify(params)
      // );
      // getShareUrl(this.$getIP() +
      //     "/performance/responsibility/promiseBook?data=" +
      //     JSON.stringify(params));
      //TODO suncao长链接转成短连接process.env.VUE_APP_CSN_URL + "/shortUrl/getShortUrl"
      // this.getShareUrlTo(
      //   this.$getIP() +
      //     "/performance/responsibility/promiseBook?data=" +
      //     JSON.stringify(params)
      // );
    }
  }
};
</script>

<style lang="less" scoped>
.content {
  overflow-x: hidden;
  width: 100%;
  height: 100%;
  background: linear-gradient(#1457b2, #6bd1ff);
  position: relative;
  // padding-bottom: 20px;
  .header {
    position: fixed;
    height: 46px;
    width: 100%;
    background: rgba(33, 83, 235, 0.2);
    z-index: 999;
    .center {
      display: block;
      // margin-left: 150px;
      color: #000;
    }
    .van-nav-bar__title {
      color: #fff !important;
    }
    .van-nav-bar {
      background: none;
      display: flex;
      align-items: center;
      .van-icon-arrow-left::before {
        color: #fff;
      }
      img {
        width: 22px;
        height: auto;
        vertical-align: middle;
      }
    }
    .van-nav-bar .van-icon {
      font-size: 24px;
      font-weight: 500;
      color: rgba(74, 74, 74, 1);
    }
    .van-nav-bar__title {
      font-size: 18px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(74, 74, 74, 1);
    }
  }
  .title {
    text-align: right;
  }
  .word {
    text-align: center;
    font-size: 16px;
    color: #fff;
  }
  .wordPrice {
    text-align: center;
    font-size: 16px;
    color: #fff;
    // margin-top: 20px;
    margin-top: 5%;
  }
  .price {
    text-align: center;
    font-size: 46px;
    color: #fff;
  }
  .info {
    display: flex;
    flex-wrap: wrap;
    .info_div {
      width: 50%;
      color: #fff;
      display: flex;
      align-items: center;
      padding-left: 20px;
      margin-top: 30px;
      box-sizing: border-box;
      .img {
        display: block;
        width: 38px;
        height: 38px;
        float: left;
      }
      .wordInfo {
        margin-left: 15px;
        .p1 {
          font-size: 14px;
          text-align: left;
        }
        .p2 {
          font-size: 22px;
          margin-top: 5px;
        }
      }
    }
  }
  .name {
    margin-top: 10%;
    font-size: 14px;
    color: #fff;
    margin-left: 134px;
    .person {
      float: left;
      font-size: 16px;
    }
    .personImg {
      width: 200px;
      height: 100px;
    }
  }
  .view-button-wrap {
    font-size: 14px;
    padding-right: 20px;
    line-height: 20px;
    color: #416da9;
    margin-top: 5%;
  }
  .picture {
    position: absolute;
    bottom: 0;
    left: 0;
    height: 31%;
  }
  .van-icon::before {
    display: inline-block;
    vertical-align: middle;
  }
}
</style>
