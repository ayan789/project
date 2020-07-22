<template>
  <div id="signature-confirm">
    <div class="indexPage">
      <h1>Shell方法调用目录</h1>

      <van-cell-group>
        <van-field
          v-model="webUrl"
          placeholder="请输入openWebView:打开新网页地址"
        />
      </van-cell-group>
      <ul>
        <li v-for="item in funcs" :key="item.id">
          <van-button
            class="item"
            type="primary"
            @click="callNativeFunc(item.id)"
          >{{ item.name }}
          </van-button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { Toast } from "vant";

import * as native from "../utils/JSBridge";

//缓存调整URL
const nativeUrlKey = "nativeUrlKey";

export default {
  components: {},
  data: () => ({
    funcs: [
      { id: 1, name: "1.openWebView:打开新网页" },
      { id: 2, name: "2.setData:保存数据" },
      { id: 3, name: "3.getData:获取数据" },
      { id: 16, name: "16.getData:获取userInfo数据" },
      { id: 4, name: "4.openHome:打开首页" },
      { id: 5, name: "5.closeWeb:关闭网页" },
      { id: 6, name: "6.back:返回上一级页面" },
      { id: 7, name: "7.share:分享（微信、QQ、短信）" },
      { id: 8, name: "8.setAvatar:打开相机相册" },
      { id: 9, name: "9.savePicture:保存图片" },
      { id: 10, name: "10.1.identifyOCR:身份证OCR识别" },
      { id: 11, name: "10.2.identifyOCR:驾驶证OCR识别" },
      { id: 12, name: "11.shareToWxBySystem:分享电子保单PDF到微信" },
      { id: 13, name: "12.getLocation:获取定位" },
      { id: 14, name: "13.getPhoneCall:拨打电话" },
      { id: 15, name: "14.openLoginView:打开登录页面重新登录" },
      { id: 17, name: "15.openWeChat:仅打开微信" },
      { id: 18, name: "16.birthdayWishes:生日祝福" },
      { id: 19, name: "17.showOrCloseWatermarket:显示或隐藏水印" },
      { id: 20, name: "18.sharePoster:分享海报" },
    ],
    webUrl: "http://172.20.10.6:8066/index.html"
  }),
  mounted() {
    //调用壳方法，传递调用参数和接受响应数据
    native.getData(nativeUrlKey, data => {
      this.webUrl =
        JSON.parse(data).responseBody || "http://172.20.10.6:8066/index.html";
    });
  },
  methods: {
    // 调用壳方法
    callNativeFunc(type) {
      switch (type) {
        case 1: {
          //调用接口参数
          const jsonObject = {
            url: "",
            shareUrl:"", //TODO suncao 影像上传
            titleBarTag: "1",
            titleBar: '影像上传',
            sharePlatform: ["0", "1"],//0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
            shareType: "url",
            title: "影像上传",
            imgURL: "",
            image: "",
            desc: "尊敬的客户，您的投保订单已生成，可以在线上传影像"
          };

          //调用壳方法，传递调用参数和接受响应数据
          native.openWebView(jsonObject, data => {
            //Toast("openWebView接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 2: {
          //调用壳方法，传递调用参数和接受响应数据
          native.setData(nativeUrlKey, this.webUrl);
          break;
        }
        case 3: {
          //调用壳方法，传递调用参数和接受响应数据
          native.getData("nativeUrlKey", data => {
            //Toast("getData 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 16: {
          //调用壳方法，传递调用参数和接受响应数据
          native.getData("userInfo", data => {
            //Toast("getData 接口调用userInfo成功，壳响应数据:" + data);
          });
          break;
        }
        case 4: {
          //调用接口参数
          const data = "davinxxxxxx";

          //调用壳方法，传递调用参数和接受响应数据
          native.openHome(data, data => {
            //Toast("openHome 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 5: {
          //调用壳方法，接受响应数据
          native.closeWeb(data => {
            //Toast("closeWeb 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 6: {
          //调用壳方法，接受响应数据
          native.back(data => {
            //Toast("back 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 7: {
          //调用接口参数
          const jsonObject = {
            sharePlatform: ["0", "1", "3"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
            shareType: "text", //"url" - 链接； "img" - 图片； "text" - 文字
            title: "davin", //分享标题
            imgURL: "fdfdfdf", //分享图片地址, 与image二选一
            image: "davin", //分享图片（base64编码）, 与imgURL二选一
            url: "xxxx", //分享链接
            desc: "fdfdfdfdf", //分享描述
            phoneNum: "1346778888" //电话号码,当需要短信分享时获取
          };

          //调用壳方法，传递调用参数和接受响应数据
          native.share(jsonObject, data => {
            //Toast("share 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 8: {
          //0:相机/相册(不传默认0)；1：相机；2：相册
          const imgType = "1";

          //调用壳方法，传递调用参数和接受响应数据
          native.setAvatar(imgType, data => {
            //Toast("setAvatar 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 9: {
          //调用接口参数
          const imageBase64 = "base64xxxxxxx";

          //调用壳方法，传递调用参数和接受响应数据
          native.savePicture(imageBase64, data => {
            //Toast("savePicture 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 10: {
          //调用接口参数，"0" - 识别行驶证; "1" - 识别身份证
          const identifyType = "1";

          //调用壳方法，传递调用参数和接受响应数据
          native.identifyOCR(identifyType, data => {
            //Toast("identifyOCR 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 11: {
          const jsonObject = {
            identifyType: "0",//"0" - 识别行驶证; "1" - 识别身份证
            side: "face",//"face"：正面；"back": 反面;
          };

          //调用壳方法，传递调用参数和接受响应数据
          native.identifyOCR(jsonObject, data => {
            //Toast("identifyOCR 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 12: {
          //调用接口参数
          const downloadUrl = "http://xxx/xx/xxx";

          //调用壳方法，传递调用参数和接受响应数据
          native.shareToWxBySystem(downloadUrl, data => {
            //Toast("shareToWxBySystem 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 13: {
          //调用壳方法，传递调用参数和接受响应数据
          native.getLocation(data => {
            //Toast("getLocation 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 14: {
          //调用接口参数
          const phoneNo = "13567865678";

          //调用壳方法，传递调用参数和接受响应数据
          native.getPhoneCall(phoneNo, data => {
            //Toast("getPhoneCall 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 15: {
          //调用壳方法，传递调用参数和接受响应数据
          native.openLoginView(data => {
            //Toast("openLoginView 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 17: {
          //调用壳方法，传递调用参数和接受响应数据
          native.openWeChat(data => {
            //Toast("openWeChat 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 18: {
          //封装默认固定参数
          const jsonVal = {
            imageURL: "", //图片链接
            blessInfo: "", //被祝福人
            content: "", //祝福内容
            contactPer: "", //联系人
            contactPhone: "" //联系电话
          };
          //调用壳方法，传递调用参数和接受响应数据
          native.birthdayWishes(jsonVal, data => {
            //Toast("birthdayWishes 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        case 19: {
          //调用壳方法，传递调用参数和接受响应数据
          native.showOrCloseWatermarket(false, data => {
          });
          break;
        }
        case 20: {
          const jsonVal = {
            posterType: "", //海报类型 “0”表示文字和二维码，“1”表示仅有二维码（居中）
            name: "", //仅在 posterType 为 0 时有值
            phone: "", //仅在 posterType 为 0 时有值
            qrcodeURL: "", //二维码链接（原生转化成二维码图片）
            sharePlatform: "", //"0"表示微信好友；"1"表示微信朋友圈；”99“表示保存图片
            backImage: "",//背景图
            shareType: ""//img
          };
          //调用壳方法，传递调用参数和接受响应数据
          native.sharePoster(jsonVal, data => {
            //Toast("sharePoster 接口调用成功，壳响应数据:" + data);
          });
          break;
        }
        default: {
          //默认处理
          //Toast("没有命中方法，请核对！");
        }
      }
    }
  }
};
</script>

<style lang="less" scoped>
.indexPage {
  h1 {
    height: 40px;
    text-align: center;
    line-height: 40px;
  }

  ul {
    li {
      padding: 5px 15px;

      .item {
        font-size: 18px;
        width: 100%;
        cursor: pointer;
      }
    }
  }
}
</style>
