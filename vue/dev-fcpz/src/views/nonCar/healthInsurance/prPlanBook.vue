<template>
  <div class="planBook">
    <globalNav
      v-if="isSharePage == '0'"
      :title="globalNav.title"
      :isShare="globalNav.isShare"
      @clickBack="goback"
      leftText=""
      @clickRight="sharePage"
    ></globalNav>
    <div class="pic">
      <img :src="guaranteeDetailSrc" />
    </div>
    <div class="productName">
      {{ millionInfo.formulaName }}
    </div>
    <div class="btn">
      <van-button
        type="info"
        @click="goMillionPlan"
      >{{ name }} 亲启</van-button>
    </div>
    <p>{{ businessName }}制作</p>
  </div>
</template>
<script>
import globalNav from "@/components/globalNav";
import { getShareUrl } from "@src/http/module/customerApi.js";
import { Toast } from "vant";
import { mapMutations } from "vuex";

export default {
  data () {
    return {
      globalNav: {
        title: "商业计划书",
        isShare: true
      },
      guaranteeDetailSrc: require("@/assets/nonCar/jihuashu.png"),
      name: "",
      businessName: "",
      millionInfo: "",
      isSharePage: 0,
      agentInfo: {}
    };
  },
  created () {
    let obj = this.$store.state.config.productInfo.metaDataList.find(item => {
      return item.name == "投保人姓名"
    })
    this.name = obj.value;
  },
  components: {
    globalNav
  },
  methods: {
    goback () {
      this.$router.go(-1);
    },
    goMillionPlan () {
      if (!this.$isWeiXin) {
        Toast("请点击右上角按钮，分享给客户");
      } else {
        this.$router.push({ name: "prPlanInfo" });
      }
    },
    async sharePage () {
      let res = await getShareUrl({
        url: window.location.href + "&isSharePage=1"
      });
      let shareUrl =
        res.data.shortUrl || window.location.href + "&isSharePage=1";
      console.log("分享短连接===" + shareUrl);
      const desc = `尊敬的 ${this.name}，您的保险专家 ${this.businessName} 为您精心准备的计划书，请查阅！`;
      const title = this.millionInfo.formulaName;

      //调用接口参数 TODO
      const jsonObject = {
        sharePlatform: ["0", "1"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
        shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
        title, //分享标题
        imgURL:
          "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
        image: "",
        url: shareUrl, //分享链接
        desc, //分享描述
        phoneNum: "" //电话号码,当需要短信分享时获取
      };
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.share(jsonObject, data => {
        // Toast("share 接口调用成功，壳响应数据:" + data);
      });
    }
  }
};
</script>

<style lang="less" scoped>
.planBook {
  background-color: #3694eb;
  box-sizing: border-box;
  padding-top: 2rem;
  .pic {
    width: 100%;
    height: 88%;
    margin: 0 auto;
    text-align: center;
    img {
      height: 100%;
      width: 100%;
    }
  }
  .btn {
    text-align: center;
    width: 100%;
    position: relative;
    button {
      position: absolute;
      width: 50%;
      margin-left: -25%;
      top: -3rem;
      border-radius: 10px;
    }
  }
  .productName {
    text-align: center;
    width: 100%;
    position: absolute;
    top: 25%;
    font-size: 18px;
  }
  p {
    color: #fff;
    text-align: center;
    font-size: 16px;
  }
}
/deep/ .navigatorBottom {
  display: none;
}
</style>
