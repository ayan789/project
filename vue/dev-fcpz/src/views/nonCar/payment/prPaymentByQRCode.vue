<template>
  <div
    id="super"
    v-if="!isShare"
  >
    <van-sticky>
      <nav-bar
        type="vehicle"
        title="二维码支付"
        clear="1"
        @clickBack="$router.back()"
      />
    </van-sticky>
    <div
      class="content"
      id="content"
      ref="container"
    >
      <div id="center">
        <div class="qrTitle">保费</div>
        <div class="price">{{ sumPremium }}</div>
        <div id="qrCode">
          <img
            v-if="qrcode"
            :src="'data:image/png;base64,' + qrcode"
          />
        </div>
        <div id="spaceLine"></div>
        <div id="info">
          <div class="father">
            <h3 class="left">姓名</h3>
            <h3 class="right">{{ applicantName }}</h3>
          </div>
          <div class="father">
            <h3 class="left">产品名称</h3>
            <h3 class="right">{{ productName }}</h3>
          </div>
          <div class="father">
            <h3 class="left">投保单号</h3>
            <h3 class="right">{{ proposalNo }}</h3>
          </div>
        </div>
      </div>
      <div id="bottom">
        <div id="shareTitle">分享至</div>
        <div
          class="shareFather"
          @click="shareInfo(0)"
        >
          <div class="shareIcon">
            <img src="../../../assets/vehicle/payment/wechatShare.png" />
          </div>
          <div class="shareText">微信</div>
        </div>
        <div
          class="shareFather"
          @click="shareInfo(1)"
        >
          <div class="shareIcon">
            <img src="../../../assets/vehicle/payment/alipayShare.png" />
          </div>
          <div class="shareText">支付宝</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getShareUrl } from "@src/http/module/customerApi.js";
import { getPayUrl, applyPay, getForwardUrl } from "@src/http/module/nonCar";
import QRCode from "qrcodejs2";

export default {
  name: "paymentByQRCode",
  components: {},
  data () {
    // const { params } = JSON.parse(this.$route.query.item)
    // const {
    //   proposalNo, //投保单号
    //   productName, //产品
    //   applicantName, //投保人姓名
    //   paymentNo,
    //   checkNo,
    //   sumPremium,
    //   mobileNo, //投保人手机号码
    //   insuredName, //被投保人姓名
    //   insuredMobile //被投保人电话
    // } = params
    return {
      qrcode: "",
      qrCode: null,
      routeItem: this.$route.query.item,
      proposalNo: JSON.parse(this.$route.query.item).proposalNo, //投保单号
      productName: JSON.parse(this.$route.query.item).productName, //产品
      applicantName: JSON.parse(this.$route.query.item).applicantName, //投保人姓名
      paymentNo: JSON.parse(this.$route.query.item).paymentNo,
      checkNo: JSON.parse(this.$route.query.item).checkNo,
      sumPremium: JSON.parse(this.$route.query.item).sumPremium,
      mobileNo: JSON.parse(this.$route.query.item).mobileNo, //投保人手机号码
      insuredName: JSON.parse(this.$route.query.item).insuredName, //被投保人姓名
      insuredMobile: JSON.parse(this.$route.query.item).insuredMobile, //被投保人电话
      dataurl: "",
      isShare: false,
      base64image: ""
    };
  },
  computed: {
    qrCode1 () {
      return this.qrcode;
    }
  },
  watch: {
    qrCode1 () {
      if (this.qrcode !== "") {
        this.getImg();
      }
      // return this.qrcode;
    }
  },
  created () {
    this.$toast.loading();

    if (this.$GetRequest("share")) {
      this.isShare = true;
      this.getRedirectUrl();
      this.$toast.clear();
      return;
    } else {
      this.isShare = false;
      localStorage.setItem(
        "paymentNo",
        JSON.parse(this.$route.query.item).paymentNo
      );
      localStorage.setItem(
        "checkNo",
        JSON.parse(this.$route.query.item).checkNo
      );
      let _this = this;
      this.$nextTick(() => {
        let currentPageUrl =
          _this.$rootPath +
          "/nonCar/payment/paymentByQRCode?item=" +
          encodeURIComponent(_this.routeItem) +
          "&share=1";
        _this.getShortUrl(currentPageUrl).then(request => {
          if (request.code == "1") {
            _this.qrCode = new QRCode("qrCode", {
              text: request.data.shortUrl,
              width: 190,
              height: 190
            });
          }
        });

        // this.setImage();
      });
    }

    // getPayUrl(params).then(
    //   res => {
    //     if (res.code === "1" && res.data) {
    //       // console.log(res)
    //       // this.qrcode = res.data.qrCode.replace("↵", "");
    //     }
    //   },
    //   error => {}
    // );

    //
  },

  methods: {
    // 获取短链接
    async getShortUrl (url) {
      return await getShareUrl({ url: url });
    },
    getImg () {
      this.$html2canvas(this.$refs.container).then(canvas => {
        let base64image = canvas.toDataURL("image/jpeg", 0.5);
        this.base64image = base64image.replace("data:image/jpeg;base64,", "");
        console.log(base64image);
      });
    },
    //   截取图片
    setImage () {
      let that = this;
      var canvas2 = document.createElement("canvas");
      // let _canvas = document.getElementById('child');
      let _canvas = document.getElementById("center");
      // var w = parseInt(window.getComputedStyle(_canvas).width);
      // var h = parseInt(window.getComputedStyle(_canvas).height);
      var w = parseInt(window.innerWidth) + 100;
      var h = parseInt(window.innerHeight);
      //将canvas画布放大若干倍，然后盛放在较小的容器内，就显得不模糊了
      canvas2.width = w * 4;
      canvas2.height = h * 4;
      canvas2.style.width = w + "px";
      canvas2.style.height = h + "px";
      //可以按照自己的需求，对context的参数修改,translate指的是偏移量
      var context = canvas2.getContext("2d");
      context.scale(2, 2);
      this.$html2canvas(document.getElementById("center"), {
        canvas: canvas2
      }).then(function (canvas) {
        var blob = that.getBlob(canvas);
        var oMyForm = new FormData();
        var fileName = "mobile" + ".jpg";
        oMyForm.append("file", blob, fileName);
        //              oMyForm.append("fileName", fileName);
        oMyForm.append("fileType", "image");
        oMyForm.append("user_id", that);
      });
    },
    getBlob (canvas) {
      //获取blob对象
      var data = canvas.toDataURL("image/jpeg", 1);
      this.dataurl = data;
      data = data.split(",")[1];
      data = window.atob(data);
      var ia = new Uint8Array(data.length);
      for (var i = 0; i < data.length; i++) {
        ia[i] = data.charCodeAt(i);
      }
      return new Blob([ia], {
        type: "image/jpeg"
      });
    },
    // 获取支付号
    async getApplyPay () {
      return await applyPay({ insureNo: this.proposalNo });
    },
    // 获取重定向地址
    getRedirectUrl () {
      let isWeiXin = this.$isWeiXin();
      let item = decodeURIComponent(this.$GetRequest("item"));
      let params = {
        proposalNo: JSON.parse(item).proposalNo,
        paymentNo: JSON.parse(item).paymentNo,
        checkNo: JSON.parse(item).checkNo,
        flag: "7"
      };
      if (isWeiXin) {
        params.flag = "7";
      } else {
        params.flag = "5";
      }

      getPayUrl(params).then(
        res => {
          if (res.code === "1" && res.data) {
            console.log("重定向路径：", res.data.url);
            window.location.href = res.data.url;
          }
        },
        error => { }
      );
    },
    shareInfo: function (index) {
      this.$html2canvas(this.$refs.container).then(canvas => {
        let base64image = canvas.toDataURL("image/jpeg", 0.5);
        this.base64image = base64image.replace("data:image/jpeg;base64,", "");
        console.log(base64image);
        console.log(this.base64image);
        console.log(index == 0 ? "微信" : "支付宝"); //TODO suncao
        if (index == 0) {
          const jsonObject = {
            sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
            shareType: "img", //"url" - 链接； "img" - 图片； "text" - 文字
            image: this.base64image //base64
          };
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.share(jsonObject, data => {
            // Toast("share 接口调用成功，壳响应数据:" + data);
          });
        } else {
          const jsonObject = {
            sharePlatform: ["4"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信 “4”：支付宝
            shareType: "img", //"url" - 链接； "img" - 图片； "text" - 文字
            image: this.base64image //base64
          };
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.share(jsonObject, data => {
            // Toast("share 接口调用成功，壳响应数据:" + data);
          });
        }
      });
    }
  }
};
</script>

<style scoped>
.content {
  width: 100%;
  height: calc(100% - 77px);
  overflow: scroll;
}
#super {
  width: 100%;
  height: 100%;
  position: absolute;
  background: linear-gradient(
    91deg,
    rgba(30, 70, 246, 1) 0%,
    rgba(39, 154, 255, 1) 100%
  );
}

#center {
  /* width: calc(100% - 30px); */
  width: 345px;
  margin: 20px 15px;
  height: 480px;
  background: white;
  border-radius: 4px;
}
.qrTitle {
  padding: 27px 0px 5px 0px;
  height: 20px;
  width: 100%;
  font-size: 14px;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
  line-height: 20px;
  text-align: center;
  /* background: yellow; */
}
.price {
  height: 56px;
  font-size: 40px;
  /* font-weight: 600; */
  color: rgba(51, 51, 51, 1);
  line-height: 56px;
  text-align: center;
  text-justify: auto;
}
#qrCode {
  width: 190px;
  height: 190px;
  margin: 0 auto;
  margin-top: 15px;
}

#qrCode > img {
  width: 190px;
  height: 190px;
  display: block;
}

#spaceLine {
  margin: 31px 0 0 0;
  height: 9px;
  background: #f5f5f5;
  width: 100%;
}

#info {
  margin: 10px 0;
}

.father {
  margin: 0 18px;
  height: 35px;
}
.father > h3 {
  height: 35px;
  line-height: 35px;
}
.left {
  width: 80px;
  float: left;
  text-align: left;
  font-size: 14px;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
}
.right {
  float: right;
  width: calc(100% - 80px);
  text-align: right;
  font-size: 14px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
}

#bottom {
  margin-top: 20px;
  height: 80px;
  width: 100%;
}

#shareTitle {
  margin: 0 auto;
  color: rgba(255, 255, 255, 0.3);
  font-size: 14px;
  font-weight: 400;
  height: 20px;
  line-height: 20px;
  text-align: center;
}

.shareFather {
  float: left;
  width: 50%;
  height: 40px;
  justify-content: center;
  display: flex;
}

.shareFather > div {
  float: left;
  height: 24px;
  line-height: 24px;
  font-size: 14px;
  font-weight: 400;
  color: rgba(255, 255, 255, 0.8);
}
.shareText {
  margin-left: 10px;
}
</style>
