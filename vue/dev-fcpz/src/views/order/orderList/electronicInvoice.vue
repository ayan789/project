<template>
  <div class="authorization">
    <div class="BGStyle">
      <!-- <van-nav-bar fixed title="客户授权" @click-left="onClickLeft" style="background: transparent" /> -->
      <div class="img">
        <img src="@/assets/order/orderList/DDinsuture.png" alt class="imgTipStyle" />
      </div>
      <div class="positionTop">尊敬的客户，欢迎选用大地保险</div>
    </div>
    <div class="contentView">
      <div class="contentStyle">
        <img class="img" src="@/assets/order/orderList/authorization.png" alt />
        <div class="describe">业务员需要下载您的电子发票，请您授权。</div>
        <div class="passWordStyle">
          <!-- <van-password-input
              :value="passWord"
              :focused="showKeyboard"
              :mask="false"
              @focus="showKeyboard = true"
            />
            <van-number-keyboard
              :show="showKeyboard"
              @input="onInput"
              @delete="onDelete"
              @blur="showKeyboard = false"
          />-->
          <van-field
            readonly
            clickable
            :value="passWord"
            placeholder="请输入证件号码后六位"
            @touchstart.native.stop="showKeyboard = true"
          />

          <van-number-keyboard
            v-model="passWord"
            :show="showKeyboard"
            :maxlength="6"
            @blur="showKeyboard = false"
          />
        </div>
        <!-- <div class="certificate">证件号后六位</div> -->

        <div class="btn">
          <div class="buttonStyle" @click="toAuthorization">授权</div>
          <div class="buttonStyle export" @click="downloadTemplate">自助下载</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { PasswordInput, NumberKeyboard, Toast } from "vant";
import {} from "../../../http/module/customerApi";
import { mapState } from "vuex";
import QRCode from "qrcodejs2";
import {
  customerAuthorization,
  electronicPolicy
} from "@src/http/module/order.js";
export default {
  components: {
    [PasswordInput.name]: PasswordInput,
    [NumberKeyboard.name]: NumberKeyboard
  },
  data() {
    return {
      passWord: "",
      showKeyboard: false,
      agree: false
    };
  },

  computed: {
    ...mapState(["customerInfoData"]),
    recommendCode() {
      return this.$GetRequest("recommendCode", true);
    },
    temp_userCode() {
      return this.$GetRequest("userCode", true);
    },
    temp_custNo() {
      return this.$GetRequest("custNo", true);
    },
    temp_userName() {
      return this.$GetRequest("userName", true);
    }
  },

  mounted() {
    this.qrcode = new QRCode("qrcode", {
      text: this.recommendCode,
      width: 120,
      height: 120
    });
  },

  methods: {
    onClickLeft() {
      this.$router.back();
    },
    onInput(key) {
      this.passWord = (this.passWord + key).slice(0, 6);
    },
    onDelete() {
      this.passWord = this.passWord.slice(0, this.passWord.length - 1);
    },

    toAuthorization() {
      let params = {
        policyNo: this.$route.query.policyNo,
        authUuid: this.$route.query.authUuid,
        authType: this.$route.query.authType,
        authCode: this.passWord,
        userCode: this.$route.query.userCode
      };
      customerAuthorization(params).then(res => {
        Toast(res.msg);
      });
    },
    downloadTemplate() {
      window.location.href = "http://www.95590.cn/";
      // electronicPolicy({
      //   policyNo: this.$route.query.policyNo
      // }).then(res => {
      //   if (res.code == "0") {
      //     Toast.allowMultiple();
      //     if (res.data.status != "0") {
      //       Toast({
      //         forbidClick: true,
      //         message: "请您10分钟后再次进行下载",
      //         type: "fail",
      //         duration: 3000
      //       });
      //     }
      //     setTimeout(() => {
      //       Toast.clear();
      //     }, 3000);
      //   } else {
      //     Toast.allowMultiple();
      //     Toast({
      //       forbidClick: true,
      //       message: res.msg,
      //       type: "success"
      //     });
      //     setTimeout(() => {
      //       Toast.clear();
      //     }, 2000);
      //     // this.$router.push({
      //     //   name: "elePolicyPreview",
      //     //   query: { downLoadUrl: res.data.downLoadUrl }
      //     // });
      //   }
      // });
    },
    changeAgree() {
      this.agree = !this.agree;
      //console.log(this.agree);
    },
    agreemenClick() {
      localStorage.getItem("userInfo");
    }
  }
};
</script>
<style lang="less">
.authorization {
  height: 667px;
  background: #f7f7f7;
  .van-hairline--left {
    border: 1px solid #ccc;
  }
  .van-password-input__security li {
    border: 1px solid #ccc;
    border-left: 0 solid #ccc;
  }
  .van-password-input__security li:nth-child(1) {
    border-left: 1px solid #ccc;
  }
  .van-cell:not(:last-child)::after {
    border-bottom: 0.02667rem solid #ccc;
  }
  .BGStyle {
    position: relative;
    width: 100%;
    background-size: contain;
    height: 201px;
    padding-top: 15px;
    display: flex;
    justify-content: center;
    // align-items: center;
    background: url("../../../assets/order/orderList/BG.png") no-repeat;
    background-size: 100% 100%;
    background: #2a80ff;
    .img {
      padding-top: 40px;
    }
    .imgTipStyle {
      width: 186px;
      background-size: 100% 100%;
    }
    .positionTop {
      width: 260px;
      text-align: center;
      position: absolute;
      top: 120px;
      left: 50%;
      color: #ffffff;
      font-size: 18px;
      transform: translateX(-50%);
    }
  }

  .contentView {
    width: 100vw;
    background-color: transparent;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    top: -40px;
    flex-direction: column;

    .contentStyle {
      width: 345px;
      height: 497px;
      background-color: white;
      border-radius: 10px;
      display: flex;
      align-items: center;
      flex-direction: column;
      justify-content: center;
      position: relative;
      .describe {
        // position: absolute;
        // top: 20px;
        // left: 50%;
        // width: 284px;
        // transform: translateX(-50%);
        margin-top: 64px;
        font-size: 14px;
      }

      .img {
        display: block;
        width: 172px;
        // height: 153px;
        position: absolute;
        top: 20px;
        left: 50%;
        transform: translateX(-50%);
      }
      .textStyle {
        color: #333333;
        font-size: 13px;
      }

      .blueStyle {
        color: #2aa5ff;
        font-size: 13px;
      }

      .headImg {
        width: 115px;
        height: 65px;
        margin: 6px 0px;
      }

      .certificate {
        color: #000;
        font-size: 14px;
      }

      .passWordStyle {
        padding-top: 20px;
        padding-left: 45px;
        width: 100%;
        height: 59px;
        margin: 10px 0px;
        .van-cell {
          width: 300px;
        }
      }
      .btn {
        .buttonStyle {
          float: left;
          display: inline;
          width: 100px;
          height: 36px;
          background: linear-gradient(to right, #2a80ff, #2aa5ff);
          display: flex;
          justify-content: center;
          align-items: center;
          color: #ffffff;
          border-radius: 18px;
          margin-bottom: 10px;
          margin-right: 20px;
        }
        .export {
          background: rgba(255, 255, 255, 1);
          border: 1px solid rgba(59, 138, 254, 1);
          color: #3b8afe;
        }
      }
    }
  }
}

.van-nav-bar .van-icon {
  color: white;
}

.van-nav-bar__text {
  color: white;
}

.van-nav-bar__title {
  color: white;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
}
</style>
