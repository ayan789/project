<template>
  <div id="imageUpLoad">
    <globalNav
      :title="globalNav.title"
      :isShare="globalNav.isShare"
      @clickBack="backToPrePage"
      @clickRight="sharePage"
    />
    <div class="top">请选择影像</div>
    <ul class="cates" :style="isUp ? upStyle : downStyle">
      <li
        v-for="(item, index) in cardNames"
        :key="index"
        @click="selectType(index, item)"
        :style="chooseType == index ? selectStyle : unSelectStyle"
      >
        {{ item.name }}
        <span class="triangle" v-show="chooseType != index && item.isNeed"
          ><van-icon name="success"
        /></span>
      </li>
    </ul>
    <div id="upDown" @click="clickArrow">
      <img :src="isUp ? upArrow : downArrow" />
    </div>
    <div
      id="bottom"
      class="bottom"
      :class="[imageDatas.length <= 1 ? 'clearStyle' : '']"
    >
      <div
        class="imgList"
        v-for="(item, index) in currenShowImgs"
        :key="index"
        :id="index"
      >
        <div
          class="photo"
          v-if="index != currenShowImgs.length - 1"
          @click="getImages(item, index)"
        >
          <img :src="item" />
        </div>

        <!-- <input
          class="h5SelectImg"
          id="fileBtn"
          type="file"
          ref="referenceUpload"
          @change="referenceUpload"
          accept="image/*"
          capture="camera"
          input
        /> -->
        <div class="takephoto" @click="chooseImage">
          <img src="@/assets/customer/InteractiveRights/photo.png" />
        </div>
      </div>
      <van-row
        type="flex"
        justify="center"
        align="center"
        class="bottom-confirm"
      >
        <van-col class="confirm-button white" @click="goToIndex">关闭</van-col>
        <van-col class="confirm-button" @click="doUnderwriting">提交</van-col>
      </van-row>
    </div>
    <van-image-preview
      v-model="showPreview"
      :images="images"
      :closeable="closeable"
      @change="onChange"
    >
      <template v-slot:index>
        {{ imagesIndex + 1 }}/{{ images.length }}</template
      >
    </van-image-preview>
  </div>
</template>

<script>
//  影像上传页面
import globalNav from "@/components/globalNav.vue";
import Vue from "vue";
import { ImagePreview } from "vant";
import * as native from "../../../utils/JSBridge";

Vue.use(ImagePreview);
// import { ImagePreview } from "vant";
import {
  underwriting,
  uploadImage,
  h5imgQueryDownService,
  applyPay
} from "@src/http/module/nonCar.js";
import { Toast } from "vant";
export default {
  name: "orderPortraitUpload",
  components: {
    globalNav
  },
  data() {
    return {
      isUp: false, //初始全展开
      globalNav: {
        title: "影像上传",
        isShare: true
      },
      cardNames: [
        { id: 1, name: "身份证", type: "img_1", isNeed: false },
        { id: 2, name: "行驶证", type: "img_2", isNeed: false },
        { id: 3, name: "验车照片", type: "img_3", isNeed: false },
        { id: 4, name: "购车发票", type: "img_4", isNeed: false },
        { id: 5, name: "合格证", type: "img_5", isNeed: false },
        { id: 6, name: "质检补传", type: "img_6", isNeed: false },
        { id: 7, name: "一般纳税人资质证明", type: "img_7", isNeed: false },
        { id: 8, name: "关联交易合规审核文件", type: "img_8", isNeed: false },
        { id: 9, name: "投保单", type: "img_9", isNeed: false },
        { id: 10, name: "电子投保人单", type: "img_10", isNeed: false },
        { id: 11, name: "双录单证", type: "img_11", isNeed: false },
        { id: 12, name: "电子保单", type: "img_12", isNeed: false },
        { id: 13, name: "电子批单", type: "img_13", isNeed: false },
        { id: 14, name: "投保人照片", type: "img_14", isNeed: false },
        { id: 15, name: "出险证明", type: "img_15", isNeed: false },
        { id: 16, name: "批单正本", type: "img_16", isNeed: false },
        { id: 17, name: "发票正本", type: "img_17", isNeed: false },
        { id: 18, name: "上年保单", type: "img_18", isNeed: false },
        { id: 19, name: "保单正本", type: "img_19", isNeed: false },
        { id: 20, name: "录音文件", type: "img_20", isNeed: false },
        { id: 21, name: "反洗钱小组评审文件", type: "img_21", isNeed: false }
      ],
      downArrow: require("@/assets/customer/InteractiveRights/down_arrow.png"),
      upArrow: require("@/assets/customer/InteractiveRights/up_arrow.png"),
      imageDatas: [{ picPath: "last", pageId: "0", thumbnailPath: "" }],
      chooseType: 0, //默认选择位置
      upStyle: {
        overflow: "hidden"
      },
      downStyle: {
        height: "75px",
        overflow: "none"
      },
      selectStyle: {
        background: "#3b8afe",
        color: "#fff"
      },
      unSelectStyle: {
        background: "rgba(247, 247, 247, 1)",
        color: "#333333"
      },
      insuranceType: 1,
      chooseTypeName: "身份证",
      proposalNo: "", //投保单号
      custNo: "",
      comCode: "",
      showPreview: false,
      images: [],
      imagesIndex: 0,
      imageName: "",
      closeable: true,
      isNeed: false,
      uploadImgList: [],
      selectImgs: {},
      currentFlagType: "img_1"
    };
  },
  created() {},
  computed: {
    currenShowImgs() {
      const curImgs = this.selectImgs[this.currentFlagType];

      return curImgs;
    }
  },
  methods: {
    referenceUpload(event) {
      let inputDOM = this.$refs.referenceUpload;
      // 通过DOM取文件数据
      const fil = inputDOM.files;
    },
    // 返回上一页
    backToPrePage() {
      this.goToIndex();
    },
    // 分享页面信息
    sharePage() {
      let params = {
        custNo: this.custNo, //操作客户代码
        comCode: this.comCode, //所属分公司机构代码
        proposalNo: this.proposalNo, //投保单号
        token: JSON.parse(localStorage.getItem("userInfo")).token
      };

      //调用接口参数 TODO
      const jsonObject = {
        sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
        shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
        title: "影像上传", //分享标题
        imgURL:
          "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
        image:"",
        url:
          this.$rootPath +
          "/order/propertyInsurance/orderPortraitUpload?params=" +
          JSON.stringify(params), //分享链接
        desc: "分享描述》》》》》》》》》", //分享描述
        phoneNum: "" //电话号码,当需要短信分享时获取
      };
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.share(jsonObject, data => {
        // Toast("share 接口调用成功，壳响应数据:" + data);
      });
    },
    clickArrow: function() {
      this.isUp = !this.isUp;
    },
    chooseImage: function() {
      //TODO
      let _this = this;
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.setAvatar("1", data => {
        const res = JSON.parse(data);
        if (res && res.responseBody) {
          let curImgs = this.selectImgs[this.currentFlagType];
          if (!curImgs) {
            curImgs = [];
          }

          curImgs.push(res.responseBody);

          this.selectImgs[this.currentFlagType] = curImgs;
        }
      });
    },
    // 获取imgs
    getImages(item, index) {
      // this.imageName = item.orgFileName;
      this.showPreview = true;
      this.imagesIndex = index;
      // console.log(index);
    },
    // 切换当前图片
    onChange(index) {
      this.imagesIndex = index + 1;
      // this.imageName = this.imageDatas[index].orgFileName;
      // console.log(index);
    },
    //照片类型选择
    selectType: function(index, item) {
      this.chooseType = index;
      this.chooseTypeName = item.name;
      this.currentFlagType = item.type;
    },
    //跳转到首页
    goToIndex() {
      this.$native.openHome("", data => {
        Toast("跳转到首页，壳响应数据:" + data);
      });
    },
    // 格式化日期
    formatDate(resDate) {
      let year = resDate.getFullYear();
      let month = resDate.getMonth() + 1;
      let date = resDate.getDate();
      if (month < 9) {
        month = "0" + month;
      }
      if (date < 9) {
        date = "0" + date;
      }
      return year + month + date;
    },
    // 非车的上传影像
    async upload(imgData) {
      let that = this;
      let imgTypeInfo = [
        { title: "身份证", imgType: "CJXS001" },
        { title: "行驶证", imgType: "CJXS002" },
        { title: "其他", imgType: "CJXS099" }
      ];
      let imgType = this.chooseType;
      let imgTypeObj = imgTypeInfo.find(item => {
        return item.title == this.chooseTypeName;
      });
      if (imgTypeObj) {
        imgType = imgTypeObj.imgType;
      } else {
        imgType = imgTypeInfo[2].imgType;
      }
      let base64 = imgData;
      let uploadData = {
        custNo: this.custNo, //操作客户代码
        comCode: this.comCode, //所属分公司机构代码
        busiDate: this.formatDate(new Date()), //上传日期 YYYYMMDD
        bussNo: this.proposalNo, //投保单号
        files: [
          {
            file:
              base64 ||
              "iVBORw0KGgoAAAANSUhEUgAAABYAAAAWCAYAAADEtGw7AAACw0lEQVRIS7XV/WtNcRwH8Pf7e3dNFD+TQvKQ0gqxu3NnEvKsiPhFHvIQUuTe4adJ2Xbu+AM8237yWJRFni52N5r9QEYKKdJKYWbX7nbP960zLLLuw8r3x+/5ntc53/N9fz6H+E+DvlsSU4jSaZATIPXOZRoC2lNJjmypYNJfF65SEYmxyde41nKMPf5cLxJ27XMBSYm1IJQNNkB7Q5S1/rqQq7UGqhNAAokOcuHTCDt7Yce1VuTWxgiPZ0P/vO5UawWocySPG4NKz9Ndks0NEa79DfsP3JyI8kSucNjVEgtdIjAIZGUiwgNOTDshHE5EOWxAsFOjebC6CugygRcCD4E8CWkNyIuJCNfnDZe6KvOkemNYP2I01lxYTS/s6qSgjSSvfe7EitYKducFh6pUYgyuA4p3DefKlq3sKXU11QK3ADwOJrEsXsGuvlQ4rs36jZ1qTRd1yxAP2wq5/NUupkJVmmIM7ghoTSWxqHAoilKdeOLHMKc3Dh9RkTzdAfDEFnBx0x5+D9VokrGKA3gTHMr58R385rg2LXFDYznrssKlNZpsPcVh+LIDWOBntMTVOEL3AX7ospjbso/tfmE5MVmBGxqjPJMRnuVqvCfdE/HO6+S8Rwf5tbhSYwIB3QP52QYwp2kPP/VGNB/Yiek2pDkENzVEeWpmtUYVwEfRZdKc/eAAP/blPld4WoWGDB6iTwDrASwGsR1WURiiWyhrjrLtr2LKFS5xNZ/QlWCQI3rSqIW01D+otFj2qJzv/6nQXGEn5rkQd0nwSAwC0OR5XPdwP9/2W/a5wmHX2yKaGRRuFARxM76bXzL30jxSkWtDypQKS3BbQ5TH8sL+WLzqvAIf3ioNw/WJvTz7K8d6RiAloo7K3uj7e7gFJhPaIsMZjXvZ/BOuVjEMTgOYmMuvqd9d0T8DezQRCRzqa0ID3X6m+34AQZuxJjpyiDoAAAAASUVORK5CYII=",
            fileName: "111.png",
            imgType: imgType,
            imgTypeName: "png"
          }
        ]
      };
      let res = await uploadImage(uploadData);
      if (res.code == "1") {
        Toast({ message: "上传成功", type: "success", position: "bottom" });
        that.cardNames.find(item => {
          if (item.id == that.chooseType) {
            item.isNeed = true;
          }
        });
        that.queryh5imgQueryDownService();
      }
    },
    // 查询影像
    async queryh5imgQueryDownService() {
      let that = this;
      let request = {
        comCode: this.comCode,
        operator: this.custNo,
        insurNo: this.proposalNo
      };

      let res = await h5imgQueryDownService(request);
      this.imageDatas.splice(0, this.imageDatas.length - 1);
      this.images.splice(0, this.images.length);
      if (res.code == "1" && res.data && res.data.responseBody) {
        let { responseBody } = res.data;

        if (responseBody && responseBody.imageNodes.length > 0) {
          for (let i = 0, len = responseBody.imageNodes.length; i < len; i++) {
            that.imageDatas.unshift(
              JSON.parse(JSON.stringify(responseBody.imageNodes[i]))
            );
            that.cardNames.find(item => {
              if (item.id == that.chooseType) {
                item.isNeed = true;
              }
            });

            that.uploadImgList.push(that.chooseType);
            that.uploadImgList = Array.from(new Set(that.uploadImgList));
          }
        }
      }
      for (let i = 1, len = this.imageDatas.length; i < len; i++) {
        this.images.push(this.imageDatas[i].thumbnailPath);
      }
    },
    // 核保
    async doUnderwriting() {
      let _this = this;
      let res = await underwriting({ insureNo: this.proposalNo });
      if (res.code == "1") {
        Toast({ message: "核保成功", type: "success", position: "bottom" });
        // 获取支付号
        _this.getApplyPay();
      }
    },
    // 获取支付号
    async getApplyPay() {
      // if (!this.uploadImgList.includes("0")) {
      //   Toast({
      //     message: "请上传身份证照片",
      //     type: "fail",
      //     position: "bottom"
      //   });
      //   return false;
      // }
      // if (!this.uploadImgList.includes("1")) {
      //   Toast({
      //     message: "请上传行驶证照片",
      //     type: "fail",
      //     position: "bottom"
      //   });
      //   return false;
      // }
      // if (!this.uploadImgList.includes("3")) {
      //   Toast({
      //     message: "请上传投保单照片",
      //     type: "fail",
      //     position: "bottom"
      //   });
      //   return false;
      // }
      let request = await applyPay({ insureNo: this.proposalNo });
      // console.log(request);
      if (request.code == "1") {
        Toast({
          message: request.msg,
          type: "success",
          position: "bottom"
        });
        if (request.data && request.data.insureInfo) {
          let {
            insureNo,
            payApplyNo,
            identityCode,
            sumPremium,
            startDate
          } = request.data.insureInfo;
          this.$router.push({
            name: "paymentOrder",
            query: {
              params: JSON.stringify({
              proposalNo: insureNo,
              paymentNo: payApplyNo,
              checkNo: identityCode,
              sumPremium: sumPremium,
              startDate: startDate
            })
            }
          });
        }
      } else {
        Toast({
          message: request.msg,
          type: "success",
          position: "bottom"
        });
      }
    }
  }
};
</script>

<style lang="less" scoped>
#imageUpLoad {
  padding: 50px 0 50px 0;
  background-color: rgba(255, 255, 255, 1);
  height: calc(100vh - 130px);
  overflow: scroll;
}
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
.top {
  height: 48px;
  margin-top: 44px;
  width: 100%;
  font-size: 14px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 48px;
  text-align: left;
  text-indent: 1em;
}
li {
  float: left;
  line-height: 28px;
  font-size: 13px;
  font-weight: 400;
  color: #333333;
  padding: 0px 17.5px;
  /* width: 80px; */
  height: 28px;
  margin: 8px 0px 0px 8px;
  border-radius: 2px;
  position: relative;

  .van-icon {
    position: relative;
    display: inline-block;
    font: normal normal normal 0.37333rem/1 vant-icon;
    font-size: inherit;
    text-rendering: auto;
    -webkit-font-smoothing: antialiased;
    bottom: 3px;
    right: 15px;
    color: #fff;
  }
}
.cates {
  /* background: red; */
  color: #fff;
  margin: 8px 7px;
  width: calc(100% - 15px);
  overflow: hidden;
}

#upDown {
  /* background: yellow; */
  margin-top: 40px;
  height: 44px;
  width: 100px;
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
#upDown > img {
  display: block;
  width: 14px;
  height: 14px;
  object-fit: none;
  margin-top: 15px;
}

.bottom {
  // width: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex: 1;
  flex-wrap: wrap;
  // padding: 0 15px;
}
.clearStyle {
  justify-content: flex-start;
  padding: 0 15px;
}
.imgList {
  width: 30%;
}
.takephoto,
.photo {
  background: #f7f7f7;
  width: 100%;
}
.h5SelectImg {
  background: #f7f7f7;
  width: 100%;
}

img {
  object-fit: fill;
  width: 100%;
  height: auto;
}
.takephoto > img {
  object-fit: none;
  background: #f7f7f7;
}
.bottom-confirm {
  position: fixed;
  bottom: 5px;
  left: 0;
  width: 100%;
  height: 46px;
  background: #fff;
}

.confirm-button {
  width: 165px;
  height: 46px;
  background: linear-gradient(
    125deg,
    rgba(42, 128, 255, 1) 0%,
    rgba(42, 165, 255, 1) 100%
  );
  border-radius: 23px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  line-height: 46px;
  text-align: center;
}
.white {
  width: 165px;
  height: 46px;
  background: rgba(255, 255, 255, 1);
  border-radius: 23px;
  border: 1px solid rgba(200, 201, 204, 1);
  font-size: 16px;
  color: rgba(102, 102, 102, 1);
  margin-right: 15px;
}
.unSubmit {
  background: rgba(204, 204, 204, 1);
  color: rgba(255, 255, 255, 1);
}
.triangle {
  position: absolute;
  height: 0;
  width: 0;
  border-bottom: 12px solid rgb(59, 138, 254);
  border-left: 20px solid transparent;
  border-radius: 0 0 0 3px;
  bottom: 0;
  right: 0;
  color: #fff;
  font-size: 14px;
}
</style>
