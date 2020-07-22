<template>
  <div class="RegisteredManViewItem">
    <navigationItem title="注册会员" :leftArrow="leftArrow"/>
    <div class="contentStyle">
      <div class="imgView" :style="{'backgroundImage':'url('+$imageUrl.zhucBG+')'}">
        <img :src="$imageUrl.zhuceLOGO" alt="" class="logoStyle">
        <div :style="{'backgroundImage':'url('+$imageUrl.zhectitle+')'}" class="zhectitleStyle">
          <span class="titleStyle">客户经理{{userName}}提醒您</span>
        </div>
        <img :src="$imageUrl.zhuce1" alt="" class="zhuce1Style">
        <div class="recommendCodeStyle">
          <!--          <img :src="$localStorage.recommendCode" alt="" class="userNameIcon">-->
          <div class="userNameIcon">
            <div id="qrcode" ref="qrcode"></div>
          </div>
        </div>
        <div class="funsStyle">
          <div class="borderStyle1"/>
          <div class="borderStyle2"/>
          <div class="borderStyle3"/>
          <div class="textStyle">领取方式</div>
          <div class="borderStyle3"/>
          <div class="borderStyle2"/>
          <div class="borderStyle1"/>
        </div>
        <div class="buttonText">
          <div>1、扫描上方二维码，关注公众号</div>
          <div>2、在公众号"增值服务>星级权益"中查看使用。</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import QRCode from "qrcodejs2";
import navigationItem from "./navigationItem";
export default {
  name: "RegisteredManViewItem",
  components: {
    navigationItem
  },
  computed: {
    userName() {
      return decodeURI(this.$GetRequest("userName", true) + "");
    },
    recommendCode() {
      return decodeURIComponent(this.$GetRequest("recommendCode", true) + "");
    },
    leftArrow() {
      return false;
    }
  },
  mounted() {
    this.qrcode = new QRCode('qrcode', {
      text: encodeURI(this.recommendCode),
      width: 160,
      height: 160,
    });
  }
};
</script>

<style scoped>
.RegisteredManViewItem {
  position: fixed;
  width: 100%;
}

.contentStyle {
  width: 100%;
  background: #f7f7f7;
  margin-top: 65px;
  overflow: scroll;
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
  color: #FF525E;
  margin-top: 10px;
}

.recommendCodeStyle {
  width: 150px;
  height: 150px;
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
  color: #FFFFFF;
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
</style>
