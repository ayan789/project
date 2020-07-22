<template>
  <div class="RegisteredManView">
    <navigationItem title="注册会员" />
    <div class="contentStyle">
      <div
        class="imgView"
        :style="{ backgroundImage: 'url(' + $imageUrl.zhucBG + ')' }"
      >
        <img
          :src="$imageUrl.zhuceLOGO"
          alt=""
          class="logoStyle"
        />
        <div
          :style="{ backgroundImage: 'url(' + $imageUrl.zhectitle + ')' }"
          class="zhectitleStyle"
        >
          <span class="titleStyle">客户经理{{ $localStorage.userName }}提醒您</span>
        </div>
        <img
          :src="$imageUrl.zhuce1"
          alt=""
          class="zhuce1Style"
        />
        <div class="imgQrcode">
          <img
            :src="qrCode_img"
            alt=""
            ref="qrcode"
            class="qrcode"
          />
        </div>
        <div class="funsStyle">
          <div class="borderStyle1" />
          <div class="borderStyle2" />
          <div class="borderStyle3" />
          <div class="textStyle">领取方式</div>
          <div class="borderStyle3" />
          <div class="borderStyle2" />
          <div class="borderStyle1" />
        </div>
        <div class="buttonText">
          <div>1、扫描上方二维码，关注公众号</div>
          <div>2、在公众号"增值服务>星级权益"中查看使用。</div>
        </div>
        <!--        <div class="RegisteredManView_bottom" @click="toRegisteredManViewItem">-->
        <!--          邀请客户注册-->
        <!--        </div>-->

        <div class="bottomStyle">
          <div
            class="buttonView"
            @click="toRegisteredManViewItem"
          >
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
import navigationItem from "./navigationItem";
import { doneTaskClick, getShareUrl } from "../../../http/module/customerApi";
import QRCode from "qrcodejs2";
import { Toast } from "vant";
import { mapState } from "vuex";
import { trackMap } from "@src/utils/index.js";
export default {
  name: "RegisteredManView",
  data () {
    return {
      qrcode: "",
      qrCode_img: ""
    };
  },
  components: {
    navigationItem
  },

  mounted () {
    this.creatQrCode();
    console.log("===>>> 72");
  },

  computed: {
    ...mapState(["customerData"]),
    leftArrow () {
      return this.$GetRequest("shareIsType", true) == "1" ? false : true;
    }
  },
  methods: {
    creatQrCode () {
      new QRCode(this.$refs.qrcode, {
        text: encodeURI(this.$localStorage.recommendCode),
        width: 150,
        height: 150,
        colorDark: "#000000",
        colorLight: "#ffffff"
      });
      let myCanvas = document.getElementsByTagName("canvas")[0];
      console.log(myCanvas.toDataURL("image/png"), "img====");
      this.qrCode_img = myCanvas.toDataURL("image/png");
    },

    async toRegisteredManViewItem () {
      const { recommendCode, userName } = this.$localStorage;
      let tempUrl =
        this.$getIP() +
        "/customer/RegisteredManViewItem?userName=" +
        encodeURI(userName) +
        "&recommendCode=" +
        encodeURI(recommendCode);
      console.log(tempUrl);
      let res = await getShareUrl({ url: tempUrl });
      if (res.code == "1") {
        let jsonObject = {
          qrcodeURL: recommendCode,
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
          if (tempData.resultCode == "1") {
            const { id } = this.$route.query.item;
            let params = {
              remindId: id
            };
            doneTaskClick(params);
            //埋点
            trackMap(
              "c_customer_serve_policy_expiration_remind",
              "客户服务保单到期提醒",
              {
                c_salesperson_type: "N",
                c_market_type: "N",
                c_market_name: "",
                c_market_id: "",
                c_event_category: "客户管理"
              }
            );
            //刷新跟进提醒页面
            let params_ = {
              userCode: localStorage.getItem("userCode"), //业务员号
              custNo: "", //客户号
              remindType: this.customerData.active //提醒类型
            };
            this.$store.dispatch("customerData/getfollowRemindDatat", params_);
            this.$router.back();
          } else {
            Toast(tempData.resultMsg);
          }
        });
      }
    }
  }
};
</script>

<style lang="less" scoped>
.RegisteredManView {
  position: fixed;
  width: 100%;
}

.contentStyle {
  width: 100%;
  background: #f7f7f7;
  margin-top: 65px;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: calc(100vh - 65px);
  display: flex;
  justify-content: center;
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

.zhuce1Style {
  width: 184px;
  height: 113px;
  background-size: contain;
  position: fixed;
  top: 170px;
  right: 30px;
}

.zhectitleStyle {
  width: 201px;
  height: 75px;
  background-size: contain;
  position: fixed;
  top: 170px;
  left: 20px;
  display: flex;
  justify-content: center;
}

.logoStyle {
  width: 130px;
  height: 28px;
  background-size: contain;
  position: fixed;
  top: 100px;
  left: 20px;
}

.titleStyle {
  font-size: 13px;
  color: #ff525e;
  margin-top: 10px;
}

.recommendCodeStyle {
  width: 140px;
  height: 140px;
  margin-top: 180px;
  border-color: #ffffff;
  opacity: 20%;
  border-width: 10px;
  border-style: solid;
  display: flex;
  justify-content: center;
  align-items: center;
}

.userNameIcon {
  width: 160px;
  height: 160px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.funsStyle {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin: 30px 0px;
}

.borderStyle3 {
  width: 6px;
  height: 6px;
  border-radius: 3px;
  background-color: white;
}

.borderStyle2 {
  width: 4px;
  height: 4px;
  border-radius: 2px;
  background-color: white;
  margin: 0px 5px;
}

.borderStyle1 {
  width: 2px;
  height: 2px;
  border-radius: 1px;
  background-color: white;
}

.textStyle {
  color: #ffffff;
  font-size: 19px;
  font-weight: bold;
  margin: 0px 20px;
}

.buttonText {
  color: #ffffff;
  font-size: 12px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}

.RegisteredManView_bottom {
  height: 50px;
  width: 100%;
  background-color: white;
  position: fixed;
  bottom: 0px;
  opacity: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
}

.imgQrcode {
  width: 150px;
  height: 216px;
  position: relative;
  .qrcode {
    position: absolute;
    z-index: 990000;
    bottom: 0;
  }
}

.bottomStyle {
  z-index: 99000;
  width: 100vw;
  height: 50px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;
  position: fixed;
  bottom: 0px;
}

.buttonView {
  height: 40px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background-color: white;
}

.bottomText {
  color: #666666;
  font-size: 16px;
}
</style>
