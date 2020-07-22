<template>
  <div class="productPoster">
    <navigationItem title="产品海报" />
    <div
      class="productPoster_content"
      :style="{ backgroundImage: 'url(' + $imageUrl.bg + ')' }"
    >
      <div ref="box">
        <van-swipe loop fit="contain" @change="onChange">
          <van-swipe-item v-for="(image, index) in tempImages" :key="index">
            <div
              class="productPoster_imageView"
              :style="{ backgroundImage: 'url(' + image + ')' }"
            >
              <div style="" class="productPoster_bg">
                <div
                  class="productPoster_qrView"
                  :style="{
                    justifyContent: index == '0' ? 'space-between' : 'center'
                  }"
                >
                  <div class="productPoster_leftText" v-if="index == '0'">
                    <div>详情咨询：{{ $route.query.item.name }}</div>
                    <div>联系方式：{{ $route.query.item.mobile }}</div>
                  </div>
                  <div
                    id="qrcode"
                    ref="qrcode"
                    class="productPoster_qrcode"
                  ></div>
                  <div
                    id="qrcode1"
                    ref="qrcode1"
                    class="productPoster_qrcode"
                    v-if="index == '1'"
                  ></div>
                </div>
              </div>
            </div>
          </van-swipe-item>
        </van-swipe>
      </div>

      <div class="productPoster_buttom">
        <div v-for="v in tempIcon">
          <div class="productPoster_iconitem" @click="shareClick(v)">
            <img :src="v.icon" alt="" class="iconitem_icon" />
            <span class="iconitem_title">{{ v.title }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import navigationItem from "../../customer/followUpremind/navigationItem";
import QRCode from "qrcodejs2";
import { Toast } from "vant";
import { getShareUrl } from "@src/http/module/customerApi.js";
export default {
  name: "productPoster",
  components: {
    navigationItem
  },
  data() {
    return {
      tempImages: [this.$imageUrl.img_51, this.$imageUrl.img_52],
      tempIcon: [
        { icon: this.$imageUrl.weixin, title: "微信好友", key: "0" },
        { icon: this.$imageUrl.moments, title: "微信朋友圈", key: "1" },
        { icon: this.$imageUrl.download, title: "下载", key: "99" }
      ],
      tempIndex: "0",
      tempQRUrl: ""
    };
  },

  async mounted() {
    let tempUrl = "";
    const {
      storeCode,
      comCode,
      isAgent,
      agentInfo,
      isSharePage,
      url,
      businessNatureCode,
      businessNatureName
    } = this.$route.query.item;
    if (this.$isNull(isAgent) || isAgent == "0") {
      tempUrl =
        url +
        "&storeCode=" +
        storeCode +
        "&comCode=" +
        comCode +
        "&isAgent=" +
        isAgent +
        "&agentInfo={}" +
        "&businessNatureCode=" +
        businessNatureCode +
        "&businessNatureName=" +
        businessNatureName +
        "&isSharePage=" +
        isSharePage;
    } else {
      tempUrl =
        url +
        "&storeCode=" +
        storeCode +
        "&comCode=" +
        comCode +
        "&isAgent=" +
        isAgent +
        "&agentInfo=" +
        agentInfo +
        "&businessNatureCode=" +
        businessNatureCode +
        "&businessNatureName=" +
        businessNatureName +
        "&isSharePage=" +
        isSharePage;
    }
    let res = await getShareUrl({ url: encodeURI(tempUrl)  });
    this.tempQRUrl = res.data.shortUrl || tempUrl;
    console.log("分享短连接：" + this.tempQRUrl);
    this.qrcode = new QRCode("qrcode", {
      text: tempUrl,
      width: 60,
      height: 60,
      correctLevel : QRCode.CorrectLevel.Q
    });
    this.qrcode1 = new QRCode("qrcode1", {
      text: tempUrl,
      width: 75,
      height: 75,
      correctLevel : QRCode.CorrectLevel.Q
    });
  },

  methods: {
    onChange(index) {
      this.tempIndex = index;
    },

    shareClick(v) {
      const { name, mobile } = this.$route.query.item;
      let jsonVal = {
        posterType: this.tempIndex + "", //海报类型 “0”表示文字和二维码，“1”表示仅有二维码（居中）
        name: this.tempIndex == "0" ? name : "", //仅在 posterType 为 0 时有值
        phone: this.tempIndex == "0" ? mobile : "", //仅在 posterType 为 0 时有值
        qrcodeURL: this.tempQRUrl, //二维码链接（原生转化成二维码图片）
        sharePlatform: v.key, //"0"表示微信好友；"1"表示微信朋友圈；”99“表示保存图片
        backImage: "", //背景图
        shareType: "img" //img
      };
      console.log(jsonVal);
      this.$native.sharePoster(jsonVal, data => {
        console.log("sharePoster 接口调用成功，壳响应数据:" + data);
      });
    }
  }
};
</script>

<style scoped lang="less">
.productPoster {
  position: fixed;
  width: 100vw;

  /deep/ .van-swipe__indicators {
    bottom: 0px;
  }

  .productPoster_content {
    width: 100%;
    height: 100%;
  }

  .productPoster_imageView {
    margin-top: 100px;
    width: 90%;
    background-size: cover;
    margin-left: 5%;
    border-radius: 20px;
  }

  .productPoster_bg {
    height: calc(390px * 1.1);
    width: 380px;
    background-size: cover;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    align-items: center;
    padding-bottom: 10px;
    .productPoster_qrcode {
      margin-right: 20px;
    }
  }

  .productPoster_buttom {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    margin-top: 30px;

    .productPoster_iconitem {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;

      .iconitem_icon {
        width: 50px;
        height: 50px;
        background-size: contain;
      }

      .iconitem_title {
        color: white;
        font-size: 12px;
        margin-top: 7px;
      }
    }
  }
}

.productPoster_qrView {
  width: 90%;
  display: flex;
  flex-direction: row;
  align-items: center;
  z-index: 100;
  margin-bottom: calc(15px * 1.1);
  margin-right: 5%;

  .productPoster_leftText {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 60px;
    color: #666666;
    font-size: 15px;
    font-weight: bold;
  }
}

.productPoster_qrView_ {
  position: absolute;
  bottom: 160px;
  width: 80%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  z-index: 100;
  margin-left: 10%;
}
</style>
