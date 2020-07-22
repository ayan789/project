<template>
  <div class="authorization">
    <div :style="{'backgroundImage':'url('+$imageUrl.SQBG+')'}" class="BGStyle" v-if="dataStatus">
      <van-nav-bar
        fixed
        title="客户授权"
        @click-left="onClickLeft"
        style="background: transparent"
      />
      <img :src="$imageUrl.wsqbg" alt="" class="imgTipStyle">
    </div>

    <div class="contentView" v-if="dataStatus">
      <div class="contentStyle">
        <div class="textStyle" style="margin-top: 10px">您的客户经理{{temp_userName}}正在帮您查询违章情况，需要您</div>
        <div class="textStyle">的授权，您也可以通过下面二维码关注公众号，</div>
        <div>
          <span class="textStyle">在"</span>
          <span class="blueStyle">增值服务>星级免费权益>查违章</span>
          <span class="textStyle">"进行查询。</span>
        </div>

        <!--        <img :src="recommendCode" alt="" class="headImg">-->
        <div id="qrcode" ref="qrcode" style="margin-top: 15px;margin-bottom: 15px"></div>

        <div class="certificate">输入证件号后六位</div>
        <div class="passWordStyle">
          <van-password-input
            :value="passWord"
            :focused="showKeyboard"
            :mask="false"
            @focus="showKeyboard = true"
          />
        </div>

        <div class="buttonStyle" @click="toAuthorization">
          授权
        </div>
        <div class="queryInfor">
          <input
            type="radio"
            name="agree"
            @click="changeAgree"
          />
          <span class="textTitle">我已阅读并同意</span>
          <span class="agreementStyle" @click="agreemenClick">《隐私服务协议》</span>
        </div>
      </div>
      <van-number-keyboard
        :show="showKeyboard"
        extra-key="X"
        @input="onInput"
        @delete="onDelete"
        @blur="showKeyboard = false"
      />
    </div>

    <div v-else>
      <div class="queryRemindView_noData">
        <img :src="this.$imageUrl.pastDueImg" alt="" class="queryRemindView_img">
      </div>
    </div>
  </div>
</template>
<script>
import { PasswordInput, NumberKeyboard, Toast } from "vant";
import { getCustomerAuthorization } from "../../../http/module/customerApi";
import { mapState } from "vuex";
import QRCode from "qrcodejs2";
import { formatDate, getDayNum } from "../../../utils";

export default {
  components: {
    [PasswordInput.name]: PasswordInput,
    [NumberKeyboard.name]: NumberKeyboard
  },
  data() {
    return {
      passWord: "",
      showKeyboard: false,
      agree: false,
    };
  },

  computed: {
    ...mapState(["customerInfoData"]),
    recommendCode() {
      return decodeURIComponent(this.$GetRequest("recommendCode", true) + "") || this.$localStorage.recommendCode;
    },
    temp_userCode() {
      return this.$GetRequest("userCode", true) || localStorage.getItem("userCode");
    },
    temp_custNo() {
      return this.$GetRequest("custNo", true);
    },
    temp_userName() {
      return decodeURI(this.$GetRequest("userName", true) + "");
    },
    dataStatus() {
      return getDayNum(formatDate(+this.$GetRequest("shareData", true) || new Date().getTime())) < 8 ? true : false;
    }
  },

  mounted() {
    this.qrcode = new QRCode('qrcode', {
      text: encodeURI(this.recommendCode),
      width: 120,
      height: 120,
      colorDark: "#000",
      colorLight: "#fff",
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

    async toAuthorization() {
      if (this.agree) {
        const { userCode, custNo } = this.customerInfoData.CustInfo;
        let params = {
          userCode: this.temp_userCode, //业务员工号
          custNo: this.temp_custNo, //客户号
          authorizeType: '1',
          idCardNo: this.passWord //证件号后六位
        };
        let res = await getCustomerAuthorization(params);
        if (res.code == '1') {
          Toast("授权成功");
        } else {
          Toast({ message: res.msg, duration: 2 });
        }
      } else {
        Toast('请先选择协议！');
      }
    },
    changeAgree() {
      this.agree = !this.agree;
      //console.log(this.agree);
    },
    agreemenClick() {
      console.log('协议', localStorage.getItem("userInfo"));
      this.$router.push({ name: 'privacyDetal' });
    }
  }
};
</script>
<style lang="less">
.authorization {
  height: 667px;
  background: #f7f7f7;

  .BGStyle {
    width: 100%;
    background-size: contain;
    height: 201px;
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;

    .imgTipStyle {
      width: 127px;
      height: 97px;
      background-size: contain;
      margin-top: -40px;
    }
  }

  .contentView {
    width: 100vw;
    background-color: transparent;
    display: flex;
    justify-content: center;
    align-items: center;
    /*position: relative;*/

    flex-direction: column;

    .contentStyle {
      width: 345px;
      height: 510px;
      background-color: white;
      border-radius: 10px;
      display: flex;
      align-items: center;
      flex-direction: column;
      justify-content: flex-start;
      position: fixed;
      -webkit-overflow-scrolling: touch;
      overflow-scrolling: touch;
      overflow-y: scroll;
      top: 140px;
      /*height: calc(100vh - 180px);*/

      .textStyle {
        color: #333333;
        font-size: 13px;
      }

      .blueStyle {
        color: #2AA5FF;
        font-size: 13px;
      }

      .headImg {
        width: 115px;
        height: 115px;
        background-size: contain;
        margin: 20px 0px;
      }

      .certificate {
        color: #C1C1C1;
        font-size: 14px;
      }

      .passWordStyle {
        width: 100%;
        height: 59px;
        margin: 10px 0px;
      }

      .buttonStyle {
        width: 204px;
        line-height: 36px;
        height: 36px;
        background: linear-gradient(to right, #2A80FF, #2AA5FF);
        display: flex;
        justify-content: center;
        align-items: center;
        color: #FFFFFF;
        border-radius: 18px;
        margin-bottom: 10px;
      }

      .queryInfor {
        display: flex;
        flex-direction: row;
        align-items: center;
        margin-bottom: 30px;

        .textTitle {
          font-size: 12px;
          color: #666666;
          margin-left: 5px;

        }

        .agreementStyle {
          color: #2AA5FF;
          font-size: 12px;

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

.van-hairline--bottom::after {
  border-bottom-width: 0px;
}

.queryRemindView_img {
  width: 50%;
  height: 30%;
  background-size: contain;
}

.queryRemindView_noData {
  width: 100%;
  background: #f7f7f7;
  margin-top: 5%;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: calc(100vh);
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
  opacity: 100%;
}
</style>
