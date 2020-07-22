<template>
  <div class="queryRemindView">
    <navigationItem title="邀请客户"/>
    <div class="contentStyle">
      <div class="imgView" :style="{'backgroundImage':'url('+$imageUrl.zhucBG+')'}">
        <div class="topView">
          <div>
            <img :src="$imageUrl.yaoqicon" alt="" class="titleStyle">
            <img :src="$imageUrl.zhuce1" alt="" class="zhuce1Style">
          </div>
          <div>
            <div id="QRCodeNone" style="width:54%;height:200px;margin:8% 0 8% 23%;display:none;">
            </div>
            <img :src="qrCode_img" alt="" ref="qrcode" id="qrcode">
            <div class="textStyle">你的专属邀请二维码</div>
          </div>
        </div>

        <div class="contentText">
          <div>
            <span class="dadititle">未注册大地保险的用户</span>
          </div>
          <div class="itemStyle" v-for="(item,index) in custList" @click="tocustomerEquity(item)">
            <span class="dadititle1">{{item.custName}}</span>
            <div class="rightView">详情</div>
          </div>
        </div>

        <div class="bottomStyle">
          <div class="buttonView" @click="submitClick">
            <span class="bottomText">
              邀请客户注册
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { doneTaskClick, getqQeryUnregistList, getShareUrl } from '../../../http/module/customerApi';
import navigationItem from "./navigationItem";
import { Toast } from "vant";
import QRCode from "qrcodejs2";
export default {
  data() {
    return {
      custList: [],
      qrcode: "",
      qrCode_img: ""
    };
  },
  components: {
    navigationItem
  },
  async created() {
    const { userCode, custNo } = this.$route.query.item;
    let params = {
      userCode: localStorage.getItem("userCode"),
    };
    let res = await getqQeryUnregistList(params);
    if (res.code == '1') {
      this.custList = res.data.custList;
    }
  },
  mounted() {
    this.creatQrCode();
  },
  methods: {
    creatQrCode() {
      new QRCode(this.$refs.qrcode, {
        text: encodeURI(this.$localStorage.recommendCode),
        width: 120,
        height: 120,
        colorDark: "#000",
        colorLight: "#fff"
      });
      let myCanvas = document.getElementsByTagName('canvas')[0];
      console.log(myCanvas.toDataURL("image/png"), 'img====');
      this.qrCode_img = myCanvas.toDataURL("image/png");
    },
    clickBack() {
      this.$router.back();
    },
    //分享给客户
    async submitClick() {
      const { recommendCode, userName } = this.$localStorage;
      let tempUrl = this.$getIP() + "/customer/RegisteredManViewItem?userName=" + encodeURI(userName) + '&recommendCode=' + encodeURI(recommendCode);
      // let res = await getShareUrl({ url: tempUrl });
      // if (res.code == '1') {
        console.log("recommendCode======"+encodeURI(recommendCode))
        let jsonObject = {
          qrcodeURL: encodeURI(recommendCode),
          qrcodeHeaderImage: "",
          rank: "",
          name: userName,
          backImage: "",
          sharePlatform: ["0", "2"],
          shareType: "img",
          type: "21"
        };
        this.$native.shareInvite(jsonObject, data => {
          let tempData = JSON.parse(data);
          if (tempData.resultCode == '1') {
            const { id } = this.$route.query.item;
            let params = {
              remindId: id
            };
            doneTaskClick(params);
          } else {
            Toast(tempData.resultMsg);
          }
        });
      // }
    },
    tocustomerEquity(item) {
      let params = {
        userCode: localStorage.getItem("userCode"),
        custNo: item.custNo,
        custSourceType: '2',
        custType: '1'
      };
      console.log(params, '=====');
      this.$router.push({
        name: 'customerEquity',
        query: { viewType: '1', custNo: item.custNo, custSourceType: "2", custType: "1" }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.queryRemindView {
  position: fixed;
  width: 100%;
  height: 100%;
}

.contentStyle {
  width: 100%;
  background: #f7f7f7;
  margin-top: 55px;
  /*overflow: scroll;*/
  height: calc(100vh - 55px);
  /*display: flex;*/
  /*justify-content: center;*/
}

.imgView {
  width: 100%;
  height: 100%;
  background-size: cover;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.topView {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  padding: 0px 20px;
  height: 180px;
}

.recommendCodeStyle {
  width: 110px;
  height: 110px;
  border-color: #ffffff;
  opacity: 20%;
  border-width: 5px;
  border-style: solid;
  /*display: flex;*/
  /*justify-content: center;*/
  /*align-items: center;*/
}

.userNameIcon {
  width: 100px;
  height: 100px;
  background-size: contain;
}

.textStyle {
  color: white;
  font-size: 12px;
  margin: 10px 0px;
}

.titleStyle {
  width: 169px;
  height: 73px;
  background-size: contain;
}

.zhuce1Style {
  width: 110px;
  height: 80px;
  background-size: cover;
}

.bottomStyle {
  width: 100vw;
  height: 80px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  /*background-color: #1a8c37;*/
  position: fixed;
  bottom: 0px;
}

.buttonView {
  width: calc(100vw - 40px);
  height: 40px;
  border-radius: 20px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background-color: white;
}

.contentText {
  /*display: flex;*/
  /*flex-direction: column;*/
  /*justify-content: center;*/
  /*align-items: center;*/
  width: calc(100vw - 40px);
  height: 320px;
  border-radius: 10px;
  background-color: white;
  margin-bottom: 40px;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;

  .dadititle {
    color: #333333;
    font-size: 15px;
    margin-left: 5%;
  }
}

.bottomText {
  color: #666666;
  font-size: 16px;
}

.itemStyle {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 90%;
  line-height: 20px;
  height: 20px;
  margin: 5px 0px 5px 5%;
}

.dadititle1 {
  font-size: 14px;
  color: #666666;
}

.rightView {
  width: 50px;
  height: 20px;
  border: #FC820E 1px solid;
  border-radius: 25px;
  color: #FC820E;
  font-size: 13px;
  display: flex;
  justify-content: center;
  align-items: center;
}

#qrcode {
  display: inline-block;
  width: 120px;
  height: 120px;
  background-color: #fff; //设置白色背景色
  padding: 6px; // 利用padding的特性，挤出白边
}
</style>
